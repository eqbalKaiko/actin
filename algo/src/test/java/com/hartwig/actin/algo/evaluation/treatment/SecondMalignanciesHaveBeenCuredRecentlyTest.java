package com.hartwig.actin.algo.evaluation.treatment;

import static com.hartwig.actin.algo.evaluation.EvaluationAssert.assertEvaluation;

import java.util.List;

import com.google.common.collect.Lists;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.clinical.datamodel.ImmutablePriorSecondPrimary;
import com.hartwig.actin.clinical.datamodel.PriorSecondPrimary;

import org.apache.logging.log4j.util.Strings;
import org.junit.Test;

public class SecondMalignanciesHaveBeenCuredRecentlyTest {

    @Test
    public void canEvaluate() {
        SecondMalignanciesHaveBeenCuredRecently function = new SecondMalignanciesHaveBeenCuredRecently();

        List<PriorSecondPrimary> priorSecondPrimaries = Lists.newArrayList();
        assertEvaluation(EvaluationResult.PASS, function.evaluate(TreatmentTestFactory.withPriorSecondPrimaries(priorSecondPrimaries)));

        PriorSecondPrimary secondPrimaryInactive = ImmutablePriorSecondPrimary.builder()
                .tumorLocation(Strings.EMPTY)
                .tumorSubLocation(Strings.EMPTY)
                .tumorType(Strings.EMPTY)
                .tumorSubType(Strings.EMPTY)
                .treatmentHistory(Strings.EMPTY)
                .isActive(false)
                .build();

        priorSecondPrimaries.add(secondPrimaryInactive);
        assertEvaluation(EvaluationResult.PASS, function.evaluate(TreatmentTestFactory.withPriorSecondPrimaries(priorSecondPrimaries)));

        PriorSecondPrimary secondPrimaryActive = ImmutablePriorSecondPrimary.builder().from(secondPrimaryInactive).isActive(true).build();
        priorSecondPrimaries.add(secondPrimaryActive);

        assertEvaluation(EvaluationResult.FAIL, function.evaluate(TreatmentTestFactory.withPriorSecondPrimaries(priorSecondPrimaries)));
    }
}