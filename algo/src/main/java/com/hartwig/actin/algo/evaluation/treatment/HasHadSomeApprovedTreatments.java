package com.hartwig.actin.algo.evaluation.treatment;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.datamodel.ImmutableEvaluation;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;

import org.jetbrains.annotations.NotNull;

public class HasHadSomeApprovedTreatments implements EvaluationFunction {

    private final int minApprovedTreatments;

    HasHadSomeApprovedTreatments(final int minApprovedTreatments) {
        this.minApprovedTreatments = minApprovedTreatments;
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        if (record.clinical().priorTumorTreatments().isEmpty() && minApprovedTreatments > 0) {
            return ImmutableEvaluation.builder()
                    .result(EvaluationResult.FAIL)
                    .addFailMessages("Patient has had no prior tumor treatment")
                    .build();
        }
        
        return ImmutableEvaluation.builder()
                .result(EvaluationResult.UNDETERMINED)
                .addUndeterminedMessages("Currently the number of approved treatments cannot be determined")
                .build();
    }
}
