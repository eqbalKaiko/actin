package com.hartwig.actin.algo.evaluation.general;

import static org.junit.Assert.assertEquals;

import com.hartwig.actin.TestDataFactory;
import com.hartwig.actin.algo.datamodel.Evaluation;

import org.junit.Test;

public class HasSufficientLifeExpectancyTest {

    @Test
    public void canEvaluate() {
        HasSufficientLifeExpectancy function = new HasSufficientLifeExpectancy();

        assertEquals(Evaluation.IGNORED, function.evaluate(TestDataFactory.createMinimalTestPatientRecord()));
    }

}