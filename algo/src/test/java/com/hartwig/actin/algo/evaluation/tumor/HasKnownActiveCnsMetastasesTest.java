package com.hartwig.actin.algo.evaluation.tumor;

import static org.junit.Assert.assertEquals;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.clinical.datamodel.ImmutableTumorDetails;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

public class HasKnownActiveCnsMetastasesTest {

    @Test
    public void canEvaluate() {
        HasKnownActiveCnsMetastases function = new HasKnownActiveCnsMetastases();

        assertEquals(Evaluation.PASS, function.evaluate(patientWithActiveCnsLesions(true)));
        assertEquals(Evaluation.FAIL, function.evaluate(patientWithActiveCnsLesions(false)));
        assertEquals(Evaluation.FAIL, function.evaluate(patientWithActiveCnsLesions(null)));
    }

    @NotNull
    private static PatientRecord patientWithActiveCnsLesions(@Nullable Boolean hasActiveCnsLesions) {
        return TumorEvaluationTestUtil.withTumorDetails(ImmutableTumorDetails.builder().hasActiveCnsLesions(hasActiveCnsLesions).build());
    }
}