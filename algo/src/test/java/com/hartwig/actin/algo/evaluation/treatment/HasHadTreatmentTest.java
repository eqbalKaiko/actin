package com.hartwig.actin.algo.evaluation.treatment;

import static org.junit.Assert.assertEquals;

import java.util.List;

import com.google.common.collect.Lists;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.clinical.datamodel.PriorTumorTreatment;

import org.junit.Test;

public class HasHadTreatmentTest {

    @Test
    public void canEvaluate() {
        HasHadTreatment function = new HasHadTreatment("treatment 1");

        // Empty list
        List<PriorTumorTreatment> priorTumorTreatments = Lists.newArrayList();
        assertEquals(EvaluationResult.FAIL, function.evaluate(TreatmentEvaluationTestUtil.withPriorTumorTreatments(priorTumorTreatments)));

        // Add wrong treatment
        priorTumorTreatments.add(TreatmentEvaluationTestUtil.builder().name("treatment 2").build());
        assertEquals(EvaluationResult.FAIL, function.evaluate(TreatmentEvaluationTestUtil.withPriorTumorTreatments(priorTumorTreatments)));

        // Add correct treatment
        priorTumorTreatments.add(TreatmentEvaluationTestUtil.builder().name("treatment 1").build());
        assertEquals(EvaluationResult.PASS, function.evaluate(TreatmentEvaluationTestUtil.withPriorTumorTreatments(priorTumorTreatments)));
    }
}