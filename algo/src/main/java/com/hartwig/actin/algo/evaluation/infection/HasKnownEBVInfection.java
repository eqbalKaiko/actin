package com.hartwig.actin.algo.evaluation.infection;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;
import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.evaluation.EvaluationFactory;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;
import com.hartwig.actin.algo.evaluation.othercondition.OtherConditionFunctions;
import com.hartwig.actin.clinical.datamodel.PriorOtherCondition;

import org.jetbrains.annotations.NotNull;

public class HasKnownEBVInfection implements EvaluationFunction {

    static final Set<String> EBV_TERMS = Sets.newHashSet();

    static {
        EBV_TERMS.add("EBV");
        EBV_TERMS.add("Epstein Barr");
    }

    HasKnownEBVInfection() {
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        List<PriorOtherCondition> clinicallyRelevant =
                OtherConditionFunctions.selectClinicallyRelevant(record.clinical().priorOtherConditions());
        for (PriorOtherCondition priorOtherCondition : clinicallyRelevant) {
            for (String ebvTerm : EBV_TERMS) {
                if (priorOtherCondition.name().toLowerCase().contains(ebvTerm.toLowerCase())) {
                    return EvaluationFactory.unrecoverable()
                            .result(EvaluationResult.PASS)
                            .addPassSpecificMessages("Patient has known EBV infection: " + priorOtherCondition.name())
                            .addPassGeneralMessages("Present EBV infection")
                            .build();
                }
            }
        }

        return EvaluationFactory.unrecoverable()
                .result(EvaluationResult.FAIL)
                .addFailSpecificMessages("Patient has no known EBV infection")
                .addFailGeneralMessages("Requested infection(s) not present")
                .build();
    }
}
