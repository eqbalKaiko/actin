package com.hartwig.actin.algo.evaluation.molecular;

import static org.junit.Assert.assertEquals;

import com.hartwig.actin.algo.datamodel.EvaluationResult;

import org.junit.Test;

public class GeneIsActivatedOrAmplifiedTest {

    @Test
    public void canEvaluate() {
        GeneIsActivatedOrAmplified function = new GeneIsActivatedOrAmplified("gene 1");

        assertEquals(EvaluationResult.FAIL, function.evaluate(MolecularTestFactory.withActivatedGene("gene 2")));

        assertEquals(EvaluationResult.PASS, function.evaluate(MolecularTestFactory.withActivatedGene("gene 1")));
        assertEquals(EvaluationResult.PASS, function.evaluate(MolecularTestFactory.withAmplifiedGene("gene 1")));
    }
}