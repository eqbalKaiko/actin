package com.hartwig.actin.algo.evaluation.general;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;

import org.jetbrains.annotations.NotNull;

public class IsBreastfeeding implements EvaluationFunction {

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        // TODO Implement
        return Evaluation.UNDETERMINED;
    }
}
