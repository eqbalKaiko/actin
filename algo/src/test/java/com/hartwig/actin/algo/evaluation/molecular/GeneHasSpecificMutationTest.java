package com.hartwig.actin.algo.evaluation.molecular;

import static org.junit.Assert.assertEquals;

import com.hartwig.actin.algo.datamodel.EvaluationResult;

import org.junit.Test;

public class GeneHasSpecificMutationTest {

    @Test
    public void canEvaluate() {
        GeneHasSpecificMutation function = new GeneHasSpecificMutation("gene 1", "mutation 1");

        assertEquals(EvaluationResult.FAIL, function.evaluate(MolecularTestFactory.withGeneMutation("gene 1", "mutation 2")).result());
        assertEquals(EvaluationResult.FAIL, function.evaluate(MolecularTestFactory.withGeneMutation("gene 2", "mutation 1")).result());
        assertEquals(EvaluationResult.PASS, function.evaluate(MolecularTestFactory.withGeneMutation("gene 1", "mutation 1")).result());
    }
}