package com.hartwig.actin.report.pdf.tables;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import com.google.common.collect.Lists;
import com.hartwig.actin.algo.datamodel.TreatmentMatch;
import com.hartwig.actin.algo.interpretation.EvaluatedTrial;
import com.hartwig.actin.algo.interpretation.EvaluatedTrialExtractor;
import com.hartwig.actin.report.pdf.util.Cells;
import com.hartwig.actin.report.pdf.util.Formats;
import com.hartwig.actin.report.pdf.util.Tables;
import com.itextpdf.layout.element.Table;

import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;

public class EligibleActinTrialsGenerator implements TableGenerator {

    @NotNull
    private final List<EvaluatedTrial> trials;
    @NotNull
    private final String title;
    private final float trialIdColWidth;
    private final float acronymColWidth;
    private final float cohortColWidth;
    private final float molecularEventColWidth;
    private final float criteriaToCheckColWidth;
    private final float mainCheckColWidth;

    @NotNull
    public static EligibleActinTrialsGenerator forOpenTrials(@NotNull TreatmentMatch treatmentMatch, @NotNull String source,
            float contentWidth) {
        List<EvaluatedTrial> openAndEligible = Lists.newArrayList();
        for (EvaluatedTrial trial : EvaluatedTrialExtractor.extract(treatmentMatch)) {
            if (trial.isPotentiallyEligible() && trial.isOpen()) {
                openAndEligible.add(trial);
            }
        }

        String title = source + " trials that are open and considered eligible (" + openAndEligible.size() + ")";
        return create(openAndEligible, title, contentWidth);
    }

    @NotNull
    public static EligibleActinTrialsGenerator forClosedTrials(@NotNull TreatmentMatch treatmentMatch, @NotNull String source,
            float contentWidth) {
        List<EvaluatedTrial> closedAndEligible = Lists.newArrayList();
        for (EvaluatedTrial trial : EvaluatedTrialExtractor.extract(treatmentMatch)) {
            if (trial.isPotentiallyEligible() && !trial.isOpen()) {
                closedAndEligible.add(trial);
            }
        }

        String title = source + " trials that are closed but considered eligible (" + closedAndEligible.size() + ")";
        return create(closedAndEligible, title, contentWidth);
    }

    @NotNull
    private static EligibleActinTrialsGenerator create(@NotNull List<EvaluatedTrial> trials, @NotNull String title, float contentWidth) {
        float trialIdColWidth = contentWidth / 10;
        float acronymColWidth = contentWidth / 10;
        float cohortColWidth = contentWidth / 4;
        float molecularColWidth = contentWidth / 10;
        float criteriaToCheckColWidth = contentWidth / 10;
        float mainCheckColWidth =
                contentWidth - (trialIdColWidth + acronymColWidth + cohortColWidth + molecularColWidth + criteriaToCheckColWidth);

        return new EligibleActinTrialsGenerator(trials,
                title,
                trialIdColWidth,
                acronymColWidth,
                cohortColWidth,
                molecularColWidth,
                criteriaToCheckColWidth,
                mainCheckColWidth);
    }

    private EligibleActinTrialsGenerator(@NotNull final List<EvaluatedTrial> trials, @NotNull final String title,
            final float trialIdColWidth, final float acronymColWidth, final float cohortColWidth, final float molecularEventColWidth,
            final float criteriaToCheckColWidth, final float mainCheckColWidth) {
        this.trials = trials;
        this.title = title;
        this.trialIdColWidth = trialIdColWidth;
        this.acronymColWidth = acronymColWidth;
        this.cohortColWidth = cohortColWidth;
        this.molecularEventColWidth = molecularEventColWidth;
        this.criteriaToCheckColWidth = criteriaToCheckColWidth;
        this.mainCheckColWidth = mainCheckColWidth;
    }

    @NotNull
    @Override
    public String title() {
        return title;
    }

    @NotNull
    @Override
    public Table contents() {
        Table table = Tables.createFixedWidthCols(trialIdColWidth,
                acronymColWidth,
                cohortColWidth,
                molecularEventColWidth,
                criteriaToCheckColWidth,
                mainCheckColWidth);

        table.addHeaderCell(Cells.createHeader("Trial ID"));
        table.addHeaderCell(Cells.createHeader("Acronym"));
        table.addHeaderCell(Cells.createHeader("Cohort"));
        table.addHeaderCell(Cells.createHeader("Molecular event"));
        table.addHeaderCell(Cells.createHeader("# Criteria to check"));
        table.addHeaderCell(Cells.createHeader("# Main check"));

        for (EvaluatedTrial trial : trials) {
            table.addCell(Cells.createContent(trial.trialId()));
            table.addCell(Cells.createContent(trial.acronym()));
            table.addCell(Cells.createContent(trial.cohort() != null ? trial.cohort() : Strings.EMPTY));
            table.addCell(Cells.createContent("TODO"));
            table.addCell(Cells.createContent(String.valueOf(trial.evaluationsToCheckCount())));
            table.addCell(Cells.createContent(concat(trial.evaluationsToCheckMessages())));
        }

        return Tables.makeWrapping(table);
    }

    @NotNull
    private static String concat(@NotNull Set<String> strings) {
        StringJoiner joiner = Formats.commaJoiner();
        for (String string : strings) {
            joiner.add(string);
        }
        return Formats.valueOrDefault(joiner.toString(), "None");
    }
}
