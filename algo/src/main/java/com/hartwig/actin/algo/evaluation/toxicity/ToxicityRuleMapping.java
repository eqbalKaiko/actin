package com.hartwig.actin.algo.evaluation.toxicity;

import java.util.Map;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hartwig.actin.algo.doid.DoidModel;
import com.hartwig.actin.algo.evaluation.FunctionCreator;
import com.hartwig.actin.treatment.datamodel.EligibilityRule;
import com.hartwig.actin.treatment.interpretation.FunctionInputResolver;
import com.hartwig.actin.treatment.interpretation.single.OneIntegerManyStrings;
import com.hartwig.actin.treatment.interpretation.single.OneIntegerOneString;

import org.jetbrains.annotations.NotNull;

public final class ToxicityRuleMapping {

    private ToxicityRuleMapping() {
    }

    @NotNull
    public static Map<EligibilityRule, FunctionCreator> create(@NotNull DoidModel doidModel) {
        Map<EligibilityRule, FunctionCreator> map = Maps.newHashMap();

        map.put(EligibilityRule.HAS_ALLERGY_OF_NAME_X, hasAllergyWithSpecificNameCreator());
        map.put(EligibilityRule.HAS_ALLERGY_BELONGING_TO_DOID_X, hasAllergyWithSpecificDoidCreator(doidModel));
        map.put(EligibilityRule.HAS_ALLERGY_TO_TAXANE, hasAllergyToTaxaneCreator());
        map.put(EligibilityRule.HAS_ALLERGY_RELATED_TO_STUDY_MEDICATION, hasAllergyRelatedToStudyMedicationCreator());
        map.put(EligibilityRule.HAS_HISTORY_OF_ANAPHYLAXIS, hasHistoryAnaphylaxisCreator());
        map.put(EligibilityRule.HAS_EXPERIENCED_IMMUNE_RELATED_ADVERSE_EVENTS, hasExperiencedImmuneRelatedAdverseEventsCreator());
        map.put(EligibilityRule.HAS_TOXICITY_OF_AT_LEAST_GRADE_X, hasToxicityWithGradeCreator());
        map.put(EligibilityRule.HAS_TOXICITY_OF_AT_LEAST_GRADE_X_IN_Y, hasToxicityWithGradeAndNameCreator());
        map.put(EligibilityRule.HAS_TOXICITY_OF_AT_LEAST_GRADE_X_IGNORING_Y, hasToxicityWithGradeIgnoringNamesCreator());

        return map;
    }

    @NotNull
    private static FunctionCreator hasAllergyWithSpecificNameCreator() {
        return function -> {
            String termToFind = FunctionInputResolver.createOneStringInput(function);
            return new HasAllergyWithSpecificName(termToFind);
        };
    }

    @NotNull
    private static FunctionCreator hasAllergyWithSpecificDoidCreator(@NotNull DoidModel doidModel) {
        return function -> {
            String doidToFind = FunctionInputResolver.createOneStringInput(function);
            return new HasAllergyWithSpecificDoid(doidModel, doidToFind);
        };
    }

    @NotNull
    private static FunctionCreator hasAllergyToTaxaneCreator() {
        return function -> new HasAllergyToTaxanes();
    }

    @NotNull
    private static FunctionCreator hasAllergyRelatedToStudyMedicationCreator() {
        return function -> new HasAllergyRelatedToStudyMedication();
    }

    @NotNull
    private static FunctionCreator hasHistoryAnaphylaxisCreator() {
        return function -> new HasHistoryOfAnaphylaxis();
    }

    @NotNull
    private static FunctionCreator hasExperiencedImmuneRelatedAdverseEventsCreator() {
        return function -> new HasExperiencedImmuneRelatedAdverseEvents();
    }

    @NotNull
    private static FunctionCreator hasToxicityWithGradeCreator() {
        return function -> {
            int minGrade = FunctionInputResolver.createOneIntegerInput(function);
            return new HasToxicityWithGrade(minGrade, null, Sets.newHashSet());
        };
    }

    @NotNull
    private static FunctionCreator hasToxicityWithGradeAndNameCreator() {
        return function -> {
            OneIntegerOneString input = FunctionInputResolver.createOneIntegerOneStringInput(function);
            return new HasToxicityWithGrade(input.integer(), input.string(), Sets.newHashSet());
        };
    }

    @NotNull
    private static FunctionCreator hasToxicityWithGradeIgnoringNamesCreator() {
        return function -> {
            OneIntegerManyStrings input = FunctionInputResolver.createOneIntegerManyStringsInput(function);
            return new HasToxicityWithGrade(input.integer(), null, Sets.newHashSet(input.strings()));
        };
    }
}
