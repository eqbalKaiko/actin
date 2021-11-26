package com.hartwig.actin.algo.evaluation.treatment;

import java.util.Map;

import com.google.common.collect.Maps;
import com.hartwig.actin.algo.evaluation.FunctionCreator;
import com.hartwig.actin.treatment.datamodel.EligibilityRule;
import com.hartwig.actin.treatment.interpretation.EligibilityParameterResolver;

import org.jetbrains.annotations.NotNull;

public final class TreatmentRuleMapping {

    private TreatmentRuleMapping() {
    }

    @NotNull
    public static Map<EligibilityRule, FunctionCreator> create() {
        Map<EligibilityRule, FunctionCreator> map = Maps.newHashMap();

        map.put(EligibilityRule.HAS_EXHAUSTED_SOC_TREATMENTS, hasExhaustedSOCTreatmentsCreator());
        map.put(EligibilityRule.HAS_CONTRAINDICATION_FOR_EXHAUSTING_SOC_TREATMENTS, hasContraindicationForSOCTreatmentsCreator());
        map.put(EligibilityRule.HAS_UNAVAILABILITY_FOR_EXHAUSTING_SOC_TREATMENTS, hasUnavailabilityForSOCTreatmentsCreator());
        map.put(EligibilityRule.HAS_DECLINED_SOC_TREATMENTS, hasDeclinedSOCTreatmentsCreator());
        map.put(EligibilityRule.HAS_HISTORY_OF_SECOND_MALIGNANCY, hasHistoryOfSecondMalignancyCreator());
        map.put(EligibilityRule.SECOND_MALIGNANCY_HAS_BEEN_CURED_SINCE_X_YEARS, secondMalignancyHasBeenCuredRecentlyCreator());
        map.put(EligibilityRule.HAS_HAD_AT_MOST_X_SYSTEMIC_TREATMENT_LINES, hasHadLimitedSystemicTreatmentsCreator());
        map.put(EligibilityRule.HAS_HAD_IMMUNOTHERAPY_TREATMENT, hasHadImmunotherapyTreatmentCreator());
        map.put(EligibilityRule.HAS_HAD_MAX_X_NR_ANTI_PD_L1_OR_PD_1_IMMUNOTHERAPIES, hasHadLimitedAntiPDL1OrPD1ImmunotherapiesCreator());
        map.put(EligibilityRule.HAS_HAD_STEM_CELL_TRANSPLANTATION, hasHadStemCellTransplantationCreator());
        map.put(EligibilityRule.IS_ELIGIBLE_FOR_ON_LABEL_DRUG_X, isEligibleForOnLabelDrugCreator());

        return map;
    }

    @NotNull
    private static FunctionCreator hasExhaustedSOCTreatmentsCreator() {
        return function -> new HasExhaustedSOCTreatments();
    }

    @NotNull
    private static FunctionCreator hasContraindicationForSOCTreatmentsCreator() {
        return function -> new HasContraindicationForSOCTreatments();
    }

    @NotNull
    private static FunctionCreator hasUnavailabilityForSOCTreatmentsCreator() {
        return function -> new HasUnavailabilityForSOCTreatments();
    }

    @NotNull
    private static FunctionCreator hasDeclinedSOCTreatmentsCreator() {
        return function -> new HasDeclinedSOCTreatments();
    }

    @NotNull
    private static FunctionCreator hasHistoryOfSecondMalignancyCreator() {
        return function -> new HasHistoryOfSecondMalignancy();
    }

    @NotNull
    private static FunctionCreator secondMalignancyHasBeenCuredRecentlyCreator() {
        return function -> new SecondMalignancyHasBeenCuredRecently();
    }

    @NotNull
    private static FunctionCreator hasHadLimitedSystemicTreatmentsCreator() {
        return function -> {
            int maxSystemicTreatments = EligibilityParameterResolver.createOneIntegerParameter(function);
            return new HasHadLimitedSystemicTreatments(maxSystemicTreatments);
        };
    }

    @NotNull
    private static FunctionCreator hasHadImmunotherapyTreatmentCreator() {
        return function -> new HasHadImmunotherapyTreatment();
    }

    @NotNull
    private static FunctionCreator hasHadLimitedAntiPDL1OrPD1ImmunotherapiesCreator() {
        return function -> {
            int maxAntiPDL1OrPD1Immunotherapies = EligibilityParameterResolver.createOneIntegerParameter(function);
            return new HasHadLimitedAntiPDL1OrPD1Immunotherapies(maxAntiPDL1OrPD1Immunotherapies);
        };
    }

    @NotNull
    private static FunctionCreator hasHadStemCellTransplantationCreator() {
        return function -> new HasHadStemCellTransplantation();
    }

    @NotNull
    private static FunctionCreator isEligibleForOnLabelDrugCreator() {
        return function -> new IsEligibleForOnLabelDrug();
    }
}
