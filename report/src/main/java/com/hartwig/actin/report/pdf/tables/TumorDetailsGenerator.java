package com.hartwig.actin.report.pdf.tables;

import java.util.StringJoiner;

import com.hartwig.actin.datamodel.clinical.ClinicalRecord;
import com.hartwig.actin.datamodel.clinical.TumorDetails;
import com.hartwig.actin.datamodel.clinical.TumorStage;
import com.hartwig.actin.report.pdf.util.Cells;
import com.hartwig.actin.report.pdf.util.Clinical;
import com.hartwig.actin.report.pdf.util.Formats;
import com.hartwig.actin.report.pdf.util.Styles;
import com.hartwig.actin.report.pdf.util.Tables;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;

import org.jetbrains.annotations.NotNull;

public class TumorDetailsGenerator implements TableGenerator {

    @NotNull
    private final ClinicalRecord record;
    private final float keyWidth;
    private final float valueWidth;

    public TumorDetailsGenerator(@NotNull final ClinicalRecord record, final float keyWidth, final float valueWidth) {
        this.record = record;
        this.keyWidth = keyWidth;
        this.valueWidth = valueWidth;
    }

    @NotNull
    @Override
    public String title() {
        return "Tumor details (" + Clinical.questionnaireDate(record) + ")";
    }

    @NotNull
    @Override
    public Table contents() {
        Table table = Tables.createFixedWidthCols(new float[] { keyWidth, valueWidth });

        table.addCell(Cells.createKey("Stage"));
        table.addCell(Cells.createValue(stage(record.tumor())));

        table.addCell(Cells.createKey("Primary tumor location"));
        table.addCell(Cells.createValue(tumorLocation(record.tumor())));

        table.addCell(Cells.createKey("Primary tumor type"));
        table.addCell(Cells.createValue(tumorType(record.tumor())));

        table.addCell(Cells.createKey("Biopsy location"));
        table.addCell(Cells.createValue(biopsyLocation(record.tumor())));

        table.addCell(Cells.createEmpty());
        table.addCell(Cells.createEmpty());

        table.addCell(Cells.createKey("Lesions in CNS / Brain / Bone  / Liver"));
        table.addCell(Cells.create(lesionsParagraph(record.tumor())));

        table.addCell(Cells.createKey("Other lesions"));
        table.addCell(Cells.createValue(lesionsOther(record.tumor())));

        table.addCell(Cells.createKey("Measurable disease (RECIST)"));
        table.addCell(Cells.createValue(Formats.yesNoUnknown(record.tumor().hasMeasurableLesionRecist())));

        return table;
    }

    @NotNull
    private static String stage(@NotNull TumorDetails tumor) {
        TumorStage stage = tumor.stage();
        return stage != null ? stage.display() : Formats.UNKNOWN;
    }

    @NotNull
    private static String tumorLocation(@NotNull TumorDetails tumor) {
        String tumorLocation = tumor.primaryTumorLocation();

        if (tumorLocation != null) {
            String tumorSubLocation = tumor.primaryTumorSubLocation();
            return (tumorSubLocation != null && !tumorSubLocation.isEmpty())
                    ? tumorLocation + " (" + tumorSubLocation + ")"
                    : tumorLocation;
        }

        return Formats.UNKNOWN;
    }

    @NotNull
    private static String tumorType(@NotNull TumorDetails tumor) {
        String tumorType = tumor.primaryTumorType();

        if (tumorType != null) {
            String tumorSubType = tumor.primaryTumorSubType();
            return (tumorSubType != null && !tumorSubType.isEmpty()) ? tumorType + " (" + tumorSubType + ")" : tumorType;
        }

        return Formats.UNKNOWN;
    }

    @NotNull
    private static String biopsyLocation(@NotNull TumorDetails tumor) {
        String biopsyLocation = tumor.biopsyLocation();
        return biopsyLocation != null ? biopsyLocation : Formats.UNKNOWN;
    }

    @NotNull
    private static Paragraph lesionsParagraph(@NotNull TumorDetails tumor) {
        Paragraph lesions = new Paragraph();

        lesions.add(renderStyledText(Formats.yesNoUnknown(tumor.hasCnsLesions())));
        lesions.add(new Text(" / ").addStyle(Styles.labelStyle()));
        lesions.add(renderStyledText(Formats.yesNoUnknown(tumor.hasBrainLesions())));
        lesions.add(new Text(" / ").addStyle(Styles.labelStyle()));
        lesions.add(renderStyledText(Formats.yesNoUnknown(tumor.hasBoneLesions())));
        lesions.add(new Text(" / ").addStyle(Styles.labelStyle()));
        lesions.add(renderStyledText(Formats.yesNoUnknown(tumor.hasLiverLesions())));

        return lesions;
    }

    @NotNull
    private static Text renderStyledText(@NotNull String value) {
        Style style = !value.equals(Formats.UNKNOWN) ? Styles.tableValueStyle() : Styles.labelStyle();
        return new Text(value).addStyle(style);
    }

    @NotNull
    private static String lesionsOther(@NotNull TumorDetails tumor) {
        Boolean hasOtherLesions = tumor.hasOtherLesions();
        if (hasOtherLesions == null) {
            return Formats.UNKNOWN;
        }

        if (hasOtherLesions) {
            assert tumor.otherLesions() != null && !tumor.otherLesions().isEmpty();
            StringJoiner joiner = Formats.commaJoiner();
            for (String lesion : tumor.otherLesions()) {
                joiner.add(lesion);
            }
            return joiner.toString();
        } else {
            return "No";
        }
    }
}