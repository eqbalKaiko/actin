package com.hartwig.actin.algo.evaluation.pregnancy;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.datamodel.ImmutableEvaluation;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;

import org.jetbrains.annotations.NotNull;

public class WillingToAdhereToDonationPrescriptions implements EvaluationFunction {

    WillingToAdhereToDonationPrescriptions() {
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        return ImmutableEvaluation.builder()
                .result(EvaluationResult.NOT_EVALUATED)
                .addPassMessages("Assumed that patient will adhere to relevant sperm/egg donation prescriptions")
                .build();
    }
}
