package com.hartwig.actin.algo.evaluation.treatment;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.google.common.collect.Lists;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.clinical.datamodel.PriorTumorTreatment;

import org.junit.Test;

public class HasHadLimitedSystemicTreatmentsTest {

    @Test
    public void canEvaluate() {
        HasHadLimitedSystemicTreatments function = new HasHadLimitedSystemicTreatments(1);

        // Empty list
        List<PriorTumorTreatment> priorTumorTreatments = Lists.newArrayList();
        assertEquals(EvaluationResult.PASS,
                function.evaluate(TreatmentEvaluationTestUtil.withPriorTumorTreatments(priorTumorTreatments)).result());

        // Add one non-systemic
        priorTumorTreatments.add(TreatmentEvaluationTestUtil.builder().isSystemic(false).build());
        assertEquals(EvaluationResult.PASS,
                function.evaluate(TreatmentEvaluationTestUtil.withPriorTumorTreatments(priorTumorTreatments)).result());

        // Add one systemic
        priorTumorTreatments.add(TreatmentEvaluationTestUtil.builder().isSystemic(true).build());
        assertEquals(EvaluationResult.PASS,
                function.evaluate(TreatmentEvaluationTestUtil.withPriorTumorTreatments(priorTumorTreatments)).result());

        // Add one more systemic
        priorTumorTreatments.add(TreatmentEvaluationTestUtil.builder().isSystemic(true).build());
        assertEquals(EvaluationResult.FAIL,
                function.evaluate(TreatmentEvaluationTestUtil.withPriorTumorTreatments(priorTumorTreatments)).result());
    }
}