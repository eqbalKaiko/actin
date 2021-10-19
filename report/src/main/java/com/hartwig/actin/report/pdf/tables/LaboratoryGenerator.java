package com.hartwig.actin.report.pdf.tables;

import java.time.LocalDate;
import java.util.List;

import com.google.common.collect.Lists;
import com.hartwig.actin.datamodel.clinical.ClinicalRecord;
import com.hartwig.actin.datamodel.clinical.LabValue;
import com.hartwig.actin.report.pdf.util.Cells;
import com.hartwig.actin.report.pdf.util.Formats;
import com.hartwig.actin.report.pdf.util.Tables;
import com.itextpdf.layout.element.Table;

import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LaboratoryGenerator implements TableGenerator {

    @NotNull
    private final ClinicalRecord record;
    private final float keyWidth;
    private final float valueWidth;

    public LaboratoryGenerator(@NotNull final ClinicalRecord record, final float keyWidth, final float valueWidth) {
        this.record = record;
        this.keyWidth = keyWidth;
        this.valueWidth = valueWidth;
    }

    @NotNull
    @Override
    public String title() {
        return "Laboratory (" + Formats.date(mostRecentDate(record.labValues())) + ")";
    }

    @NotNull
    @Override
    public Table contents() {
        float key1Width = keyWidth / 2;
        float key2Width = keyWidth - key1Width;

        Table table = Tables.createFixedWidthCols(new float[] { key1Width, key2Width, valueWidth });

        table.addCell(Cells.createKey("Liver function"));
        addLabEntryByName(table, "Total bilirubin");
        table.addCell(Cells.createEmpty());
        addLabEntryByCode(table, "ASAT");
        table.addCell(Cells.createEmpty());
        addLabEntryByCode(table, "ALAT");
        table.addCell(Cells.createEmpty());
        addLabEntryByCode(table, "ALP");
        table.addCell(Cells.createEmpty());
        addLabEntryByName(table, "Albumin");

        table.addCell(Cells.createKey("Kidney function"));
        addLabEntryByName(table, "Creatinine");
        table.addCell(Cells.createEmpty());
        addLabEntryByName(table, "CKD-EPI eGFR");

        table.addCell(Cells.createKey("Hemoglobin"));
        addLabEntryByName(table, "Hemoglobin", false);

        table.addCell(Cells.createKey("Thrombocytes"));
        addLabEntryByName(table, "Thrombocytes", false);

        table.addCell(Cells.createKey("PT"));
        addLabEntryByCode(table, "PT", false);

        table.addCell(Cells.createKey("INR"));
        addLabEntryByCode(table, "INR", false);

        return table;
    }

    private void addLabEntryByName(@NotNull Table table, @NotNull String name) {
        addLabEntryByName(table, name, true);
    }

    private void addLabEntryByName(@NotNull Table table, @NotNull String name, boolean displayHeader) {
        List<LabValue> filtered = filterByName(record.labValues(), name);
        addLabEntry(table, displayHeader ? name : Strings.EMPTY, mostRecent(filtered));
    }

    private void addLabEntryByCode(@NotNull Table table, @NotNull String code) {
        addLabEntryByCode(table, code, true);
    }

    private void addLabEntryByCode(@NotNull Table table, @NotNull String code, boolean displayHeader) {
        List<LabValue> filtered = filterByCode(record.labValues(), code);
        addLabEntry(table, displayHeader ? code : Strings.EMPTY, mostRecent(filtered));
    }

    private void addLabEntry(@NotNull Table table, @NotNull String header, @Nullable LabValue lab) {
        if (lab == null) {
            table.addCell(Cells.createKey(header));
            table.addCell(Cells.createValue(Strings.EMPTY));
        } else {
            String key = header;
            Double refLimitLow = lab.refLimitLow();
            Double refLimitUp = lab.refLimitUp();
            if (refLimitLow != null || refLimitUp != null) {
                String limit;
                if (refLimitLow == null) {
                    limit = " < " + refLimitUp;
                } else if (refLimitUp == null) {
                    limit = " > " + refLimitLow;
                } else {
                    limit = refLimitLow + " - " + refLimitUp;
                }
                if (key.isEmpty()) {
                    key = "(" + limit + " " + lab.unit() + ")";
                } else {
                    key = key + " (" + limit + " " + lab.unit() + ")";
                }
            }

            String value = lab.value() + " " + lab.unit();
            LocalDate mostRecentDate = mostRecentDate(record.labValues());
            if (mostRecentDate.isAfter(lab.date())) {
                value = value + " (" + Formats.date(lab.date()) + ")";
            }

            table.addCell(Cells.createKey(key));
            table.addCell(Cells.createValue(value));
        }
    }

    @Nullable
    private static LocalDate mostRecentDate(@NotNull List<LabValue> labValues) {
        LabValue mostRecent = mostRecent(labValues);
        return mostRecent != null ? mostRecent.date() : null;
    }

    @Nullable
    private static LabValue mostRecent(@NotNull List<LabValue> labValues) {
        LabValue mostRecent = null;
        for (LabValue labValue : labValues) {
            if (mostRecent == null || labValue.date().isAfter(mostRecent.date())) {
                mostRecent = labValue;
            }
        }

        return mostRecent;
    }

    @Nullable
    private static List<LabValue> filterByName(@NotNull List<LabValue> labValues, @NotNull String name) {
        List<LabValue> filtered = Lists.newArrayList();
        for (LabValue labValue : labValues) {
            if (labValue.name().equals(name)) {
                filtered.add(labValue);
            }
        }
        return filtered;
    }

    @Nullable
    private static List<LabValue> filterByCode(@NotNull List<LabValue> labValues, @NotNull String code) {
        List<LabValue> filtered = Lists.newArrayList();
        for (LabValue labValue : labValues) {
            if (labValue.code().equals(code)) {
                filtered.add(labValue);
            }
        }
        return filtered;
    }
}
