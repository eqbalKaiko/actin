package com.hartwig.actin.algo.evaluation.othercondition;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.doid.DoidModel;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;
import com.hartwig.actin.clinical.datamodel.PriorOtherCondition;

import org.jetbrains.annotations.NotNull;

public class HasHadSpecificPriorCondition implements EvaluationFunction {

    @NotNull
    private final DoidModel doidModel;
    @NotNull
    private final String doidToFind;

    HasHadSpecificPriorCondition(@NotNull final DoidModel doidModel, @NotNull final String doidToFind) {
        this.doidModel = doidModel;
        this.doidToFind = doidToFind;
    }

    @NotNull
    @Override
    public EvaluationResult evaluate(@NotNull PatientRecord record) {
        for (PriorOtherCondition priorOtherCondition : record.clinical().priorOtherConditions()) {
            for (String doid : priorOtherCondition.doids()) {
                if (doidModel.doidWithParents(doid).contains(doidToFind)) {
                    return EvaluationResult.PASS;
                }
            }
        }

        return EvaluationResult.FAIL;
    }
}
