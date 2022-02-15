package com.hartwig.actin.algo.evaluation.general;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;

import org.jetbrains.annotations.NotNull;

public class PatientIsTreatedInHospital implements EvaluationFunction {

    PatientIsTreatedInHospital() {
    }

    @NotNull
    @Override
    public EvaluationResult evaluate(@NotNull PatientRecord record) {
        return EvaluationResult.NOT_EVALUATED;
    }
}