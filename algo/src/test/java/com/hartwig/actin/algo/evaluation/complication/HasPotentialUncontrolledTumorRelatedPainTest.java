package com.hartwig.actin.algo.evaluation.complication;

import static com.hartwig.actin.algo.evaluation.EvaluationAssert.assertEvaluation;

import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.medication.MedicationStatusInterpretation;
import com.hartwig.actin.clinical.datamodel.Complication;
import com.hartwig.actin.clinical.datamodel.ImmutableMedication;
import com.hartwig.actin.clinical.datamodel.Medication;

import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class HasPotentialUncontrolledTumorRelatedPainTest {

    @Test
    public void canEvaluateOnComplication() {
        HasPotentialUncontrolledTumorRelatedPain function =
                new HasPotentialUncontrolledTumorRelatedPain(medication -> MedicationStatusInterpretation.ACTIVE);

        assertEvaluation(EvaluationResult.FAIL, function.evaluate(ComplicationTestFactory.withComplications(null)));

        Complication wrong = ComplicationTestFactory.builder().addCategories("just a category").build();
        assertEvaluation(EvaluationResult.FAIL, function.evaluate(ComplicationTestFactory.withComplication(wrong)));

        Complication match = ComplicationTestFactory.builder()
                .addCategories("this is category: " + HasPotentialUncontrolledTumorRelatedPain.SEVERE_PAIN_COMPLICATION)
                .build();
        assertEvaluation(EvaluationResult.PASS, function.evaluate(ComplicationTestFactory.withComplication(match)));
    }

    @Test
    public void canEvaluateOnMedication() {
        HasPotentialUncontrolledTumorRelatedPain function =
                new HasPotentialUncontrolledTumorRelatedPain(medication -> MedicationStatusInterpretation.ACTIVE);

        Medication wrong = medicationBuilder().name("just some medication").build();
        assertEvaluation(EvaluationResult.FAIL, function.evaluate(ComplicationTestFactory.withMedication(wrong)));

        Medication match = medicationBuilder().name(HasPotentialUncontrolledTumorRelatedPain.SEVERE_PAIN_MEDICATION).build();
        assertEvaluation(EvaluationResult.PASS, function.evaluate(ComplicationTestFactory.withMedication(match)));
    }

    @NotNull
    private static ImmutableMedication.Builder medicationBuilder() {
        return ImmutableMedication.builder().name(Strings.EMPTY).codeATC(Strings.EMPTY);
    }
}