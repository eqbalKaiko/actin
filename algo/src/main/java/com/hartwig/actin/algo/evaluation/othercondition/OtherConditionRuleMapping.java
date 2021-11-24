package com.hartwig.actin.algo.evaluation.othercondition;

import java.util.Map;

import com.google.common.collect.Maps;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.evaluation.FunctionCreator;
import com.hartwig.actin.treatment.datamodel.EligibilityRule;

import org.jetbrains.annotations.NotNull;

public final class OtherConditionRuleMapping {

    private OtherConditionRuleMapping() {
    }

    @NotNull
    public static Map<EligibilityRule, FunctionCreator> create() {
        Map<EligibilityRule, FunctionCreator> map = Maps.newHashMap();

        map.put(EligibilityRule.HAS_SIGNIFICANT_CONCOMITANT_ILLNESS, hasSignificantConcomitantIllnessCreator());
        map.put(EligibilityRule.HAS_HISTORY_OF_AUTOIMMUNE_DISEASE, hasHistoryOfAutoimmuneDiseaseCreator());
        map.put(EligibilityRule.HAS_HISTORY_OF_CARDIAC_DISEASE, hasHistoryOfCardiacDiseaseCreator());
        map.put(EligibilityRule.HAS_HISTORY_OF_CARDIOVASCULAR_DISEASE, notImplementedCreator());
        map.put(EligibilityRule.HAS_HISTORY_OF_LUNG_DISEASE, notImplementedCreator());
        map.put(EligibilityRule.HAS_HISTORY_OF_STROKE, notImplementedCreator());
        map.put(EligibilityRule.HAS_HISTORY_OF_TIA, notImplementedCreator());
        map.put(EligibilityRule.HAS_GILBERT_DISEASE, hasGilbertDiseaseCreator());
        map.put(EligibilityRule.HAS_CARDIAC_ARRHYTHMIA, notImplementedCreator());
        map.put(EligibilityRule.HAS_HYPERTENSION, notImplementedCreator());
        map.put(EligibilityRule.HAS_KNOWN_LVEF_OF_AT_MOST_X, notImplementedCreator());
        map.put(EligibilityRule.HAS_KNOWN_MALABSORPTION_SYNDROME, notImplementedCreator());

        return map;
    }

    @NotNull
    private static FunctionCreator hasSignificantConcomitantIllnessCreator() {
        return function -> new HasSignificantConcomitantIllness();
    }

    @NotNull
    private static FunctionCreator hasHistoryOfAutoimmuneDiseaseCreator() {
        return function -> new HasHistoryOfAutoimmuneDisease();
    }

    @NotNull
    private static FunctionCreator hasHistoryOfCardiacDiseaseCreator() {
        return function -> new HasHistoryOfCardiacDisease();
    }

    @NotNull
    private static FunctionCreator hasGilbertDiseaseCreator() {
        return function -> new HasGilbertDisease();
    }

    @NotNull
    private static FunctionCreator notImplementedCreator() {
        return function -> evaluation -> Evaluation.NOT_IMPLEMENTED;
    }
}