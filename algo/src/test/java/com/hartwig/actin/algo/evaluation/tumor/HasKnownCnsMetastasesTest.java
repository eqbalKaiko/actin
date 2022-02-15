package com.hartwig.actin.algo.evaluation.tumor;

import static org.junit.Assert.assertEquals;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.clinical.datamodel.ImmutableTumorDetails;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

public class HasKnownCnsMetastasesTest {

    @Test
    public void canEvaluate() {
        HasKnownCnsMetastases function = new HasKnownCnsMetastases();

        assertEquals(EvaluationResult.PASS, function.evaluate(patientWithCnsLesions(true)));
        assertEquals(EvaluationResult.FAIL, function.evaluate(patientWithCnsLesions(false)));
        assertEquals(EvaluationResult.FAIL, function.evaluate(patientWithCnsLesions(null)));
    }

    @NotNull
    private static PatientRecord patientWithCnsLesions(@Nullable Boolean hasCnsLesions) {
        return TumorEvaluationTestUtil.withTumorDetails(ImmutableTumorDetails.builder().hasCnsLesions(hasCnsLesions).build());
    }
}