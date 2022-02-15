package com.hartwig.actin.algo.evaluation.tumor;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.evaluation.EvaluationFactory;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;

import org.jetbrains.annotations.NotNull;

public class HasKnownActiveCnsMetastases implements EvaluationFunction {

    HasKnownActiveCnsMetastases() {
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        Boolean hasKnownActiveCnsMetastases = record.clinical().tumor().hasActiveCnsLesions();
        if (hasKnownActiveCnsMetastases == null) {
            return EvaluationFactory.create(EvaluationResult.FAIL);
        }

        EvaluationResult result = hasKnownActiveCnsMetastases ? EvaluationResult.PASS : EvaluationResult.FAIL;
        return EvaluationFactory.create(result);
    }
}
