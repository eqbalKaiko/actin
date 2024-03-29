package com.hartwig.actin.algo.evaluation.infection;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.evaluation.EvaluationFactory;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;

import org.jetbrains.annotations.NotNull;

public class MeetsCovid19InfectionRequirements implements EvaluationFunction {

    MeetsCovid19InfectionRequirements() {
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        return EvaluationFactory.unrecoverable()
                .result(EvaluationResult.UNDETERMINED)
                .addUndeterminedSpecificMessages("Currently Covid-19 infection status cannot be determined")
                .addUndeterminedSpecificMessages("COVID-19 infection")
                .build();
    }
}
