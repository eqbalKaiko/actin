package com.hartwig.actin.algo.evaluation.cardiacfunction;

import java.util.Map;

import com.google.common.collect.Maps;
import com.hartwig.actin.algo.evaluation.FunctionCreator;
import com.hartwig.actin.algo.evaluation.RuleMapper;
import com.hartwig.actin.algo.evaluation.RuleMappingResources;
import com.hartwig.actin.treatment.datamodel.EligibilityRule;

import org.jetbrains.annotations.NotNull;

public class CardiacFunctionRuleMapper extends RuleMapper {

    public CardiacFunctionRuleMapper(@NotNull final RuleMappingResources resources) {
        super(resources);
    }

    @NotNull
    @Override
    public Map<EligibilityRule, FunctionCreator> createMappings() {
        Map<EligibilityRule, FunctionCreator> map = Maps.newHashMap();

        map.put(EligibilityRule.HAS_CARDIAC_ARRHYTHMIA, hasAnyTypeOfCardiacArrhythmiaCreator());
        map.put(EligibilityRule.HAS_LVEF_OF_AT_LEAST_X, hasSufficientLVEFCreator(false));
        map.put(EligibilityRule.HAS_LVEF_OF_AT_LEAST_X_IF_KNOWN, hasSufficientLVEFCreator(true));
        map.put(EligibilityRule.HAS_QTC_OF_AT_MOST_X, hasLimitedQTCFCreator());
        map.put(EligibilityRule.HAS_QTCF_OF_AT_MOST_X, hasLimitedQTCFCreator());
        map.put(EligibilityRule.HAS_LONG_QT_SYNDROME, hasLongQTSyndromeCreator());
        map.put(EligibilityRule.HAS_NORMAL_CARDIAC_FUNCTION_BY_MUGA_OR_TTE, hasNormalCardiacFunctionByMUGAOrTTECreator());

        return map;
    }

    @NotNull
    private FunctionCreator hasAnyTypeOfCardiacArrhythmiaCreator() {
        return function -> new HasCardiacArrhythmia();
    }

    @NotNull
    private FunctionCreator hasSufficientLVEFCreator(boolean passIfUnknown) {
        return function -> {
            double minLVEF = functionInputResolver().createOneDoubleInput(function);
            return new HasSufficientLVEF(minLVEF, passIfUnknown);
        };
    }

    @NotNull
    private FunctionCreator hasLimitedQTCFCreator() {
        return function -> {
            double maxQTCF = functionInputResolver().createOneDoubleInput(function);
            return new HasLimitedQTCF(maxQTCF);
        };
    }

    @NotNull
    private FunctionCreator hasLongQTSyndromeCreator() {
        return function -> new HasLongQTSyndrome(doidModel());
    }

    @NotNull
    private  FunctionCreator hasNormalCardiacFunctionByMUGAOrTTECreator() {
        return function -> new HasNormalCardiacFunctionByMUGAOrTTE();
    }
}