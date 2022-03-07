package com.hartwig.actin.algo.evaluation.infection;

import java.util.Set;

import com.google.common.collect.Sets;
import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.datamodel.ImmutableEvaluation;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;
import com.hartwig.actin.clinical.datamodel.PriorOtherCondition;

import org.jetbrains.annotations.NotNull;

public class HasKnownHTLVInfection implements EvaluationFunction {

    static final Set<String> HTLV_TERMS = Sets.newHashSet();

    static {
        HTLV_TERMS.add("HTLV");
    }

    HasKnownHTLVInfection() {
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        for (PriorOtherCondition priorOtherCondition : record.clinical().priorOtherConditions()) {
            for (String htlvTerm : HTLV_TERMS) {
                if (priorOtherCondition.name().toLowerCase().contains(htlvTerm.toLowerCase())) {
                    return ImmutableEvaluation.builder()
                            .result(EvaluationResult.PASS)
                            .addPassMessages("Patient has known HTLV infection: " + priorOtherCondition.name())
                            .build();
                }
            }
        }

        return ImmutableEvaluation.builder()
                .result(EvaluationResult.FAIL)
                .addFailMessages("Patient has no known HTLV infection")
                .build();
    }
}
