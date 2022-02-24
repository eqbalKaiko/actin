package com.hartwig.actin.algo.evaluation.complication;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;
import com.hartwig.actin.algo.evaluation.util.EvaluationFactory;
import com.hartwig.actin.clinical.datamodel.CancerRelatedComplication;

import org.jetbrains.annotations.NotNull;

public class HasSpecificComplication implements EvaluationFunction {

    @NotNull
    private final String termToFind;

    HasSpecificComplication(@NotNull final String termToFind) {
        this.termToFind = termToFind;
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        for (CancerRelatedComplication complication : record.clinical().cancerRelatedComplications()) {
            if (complication.name().contains(termToFind)) {
                return EvaluationFactory.create(EvaluationResult.PASS);
            }
        }

        return EvaluationFactory.create(EvaluationResult.FAIL);
    }
}
