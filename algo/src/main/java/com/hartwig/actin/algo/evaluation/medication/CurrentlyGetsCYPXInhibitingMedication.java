package com.hartwig.actin.algo.evaluation.medication;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;
import com.hartwig.actin.algo.evaluation.util.EvaluationFactory;

import org.jetbrains.annotations.NotNull;

public class CurrentlyGetsCYPXInhibitingMedication implements EvaluationFunction {

    CurrentlyGetsCYPXInhibitingMedication() {
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        return EvaluationFactory.create(EvaluationResult.UNDETERMINED);
    }
}
