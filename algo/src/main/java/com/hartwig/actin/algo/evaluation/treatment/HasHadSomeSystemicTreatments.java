package com.hartwig.actin.algo.evaluation.treatment;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.datamodel.ImmutableEvaluation;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;
import com.hartwig.actin.clinical.datamodel.PriorTumorTreatment;

import org.jetbrains.annotations.NotNull;

public class HasHadSomeSystemicTreatments implements EvaluationFunction {

    private final int minSystemicTreatments;

    HasHadSomeSystemicTreatments(final int minSystemicTreatments) {
        this.minSystemicTreatments = minSystemicTreatments;
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        int systemicCount = 0;
        for (PriorTumorTreatment treatment : record.clinical().priorTumorTreatments()) {
            if (treatment.isSystemic()) {
                systemicCount++;
            }
        }

        EvaluationResult result = systemicCount >= minSystemicTreatments ? EvaluationResult.PASS : EvaluationResult.FAIL;
        ImmutableEvaluation.Builder builder = ImmutableEvaluation.builder().result(result);
        if (result == EvaluationResult.FAIL) {
            builder.addFailMessages("Patient did not receive at least " + minSystemicTreatments + " systemic treatments");
        } else if (result.isPass()) {
            builder.addPassMessages("Patient received at least " + minSystemicTreatments + " systemic treatments");
        }

        return builder.build();
    }
}
