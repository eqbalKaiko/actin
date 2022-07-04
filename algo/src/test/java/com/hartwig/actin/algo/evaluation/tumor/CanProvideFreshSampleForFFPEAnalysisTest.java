package com.hartwig.actin.algo.evaluation.tumor;

import static com.hartwig.actin.algo.evaluation.EvaluationAssert.assertEvaluation;

import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.molecular.datamodel.ExperimentType;

import org.junit.Test;

public class CanProvideFreshSampleForFFPEAnalysisTest {

    @Test
    public void canEvaluate() {
        CanProvideFreshSampleForFFPEAnalysis function = new CanProvideFreshSampleForFFPEAnalysis();

        assertEvaluation(EvaluationResult.UNDETERMINED,
                function.evaluate(TumorTestFactory.withMolecularExperimentType(ExperimentType.PANEL)));
        assertEvaluation(EvaluationResult.PASS, function.evaluate(TumorTestFactory.withMolecularExperimentType(ExperimentType.WGS)));
    }
}