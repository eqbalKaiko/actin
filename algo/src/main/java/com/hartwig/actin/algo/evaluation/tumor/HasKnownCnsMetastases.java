package com.hartwig.actin.algo.evaluation.tumor;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;

import org.jetbrains.annotations.NotNull;

public class HasKnownCnsMetastases implements EvaluationFunction {

    HasKnownCnsMetastases() {
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        Boolean hasKnownCnsMetastases = record.clinical().tumor().hasCnsLesions();
        if (hasKnownCnsMetastases == null) {
            return Evaluation.FAIL;
        }

        return hasKnownCnsMetastases ? Evaluation.PASS : Evaluation.FAIL;
    }
}