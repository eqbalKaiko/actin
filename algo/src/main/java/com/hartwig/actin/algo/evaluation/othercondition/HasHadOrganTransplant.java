package com.hartwig.actin.algo.evaluation.othercondition;

import com.google.common.annotations.VisibleForTesting;
import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;
import com.hartwig.actin.clinical.datamodel.PriorOtherCondition;

import org.jetbrains.annotations.NotNull;

public class HasHadOrganTransplant implements EvaluationFunction {

    @VisibleForTesting
    static final String ORGAN_TRANSPLANT_CATEGORY = "Organ transplant";

    HasHadOrganTransplant() {
    }

    @NotNull
    @Override
    public EvaluationResult evaluate(@NotNull PatientRecord record) {
        for (PriorOtherCondition priorOtherCondition : record.clinical().priorOtherConditions()) {
            if (priorOtherCondition.category().equals(ORGAN_TRANSPLANT_CATEGORY)) {
                return EvaluationResult.PASS;
            }
        }

        return EvaluationResult.FAIL;
    }
}
