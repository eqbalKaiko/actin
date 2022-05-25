package com.hartwig.actin.algo.evaluation.lifestyle;

import java.util.Map;

import com.google.common.collect.Maps;
import com.hartwig.actin.algo.evaluation.FunctionCreator;
import com.hartwig.actin.treatment.datamodel.EligibilityRule;

import org.jetbrains.annotations.NotNull;

public final class LifestyleRuleMapping {

    private LifestyleRuleMapping() {
    }

    @NotNull
    public static Map<EligibilityRule, FunctionCreator> create() {
        Map<EligibilityRule, FunctionCreator> map = Maps.newHashMap();

        map.put(EligibilityRule.IS_ABLE_AND_WILLING_TO_NOT_USE_CONTACT_LENSES, isWillingToNotUseContactLensesCreator());

        return map;
    }

    @NotNull
    private static FunctionCreator isWillingToNotUseContactLensesCreator() {
        return function -> new IsWillingToNotUseContactLenses();
    }
}