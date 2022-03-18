package com.hartwig.actin.algo.evaluation.molecular;

import java.util.List;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.datamodel.ImmutableEvaluation;
import com.hartwig.actin.algo.evaluation.EvaluationFactory;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;
import com.hartwig.actin.clinical.datamodel.PriorMolecularTest;

import org.jetbrains.annotations.NotNull;

public class HasSufficientPDL1ByIHC implements EvaluationFunction {

    private final int minPDL1;

    HasSufficientPDL1ByIHC(final int minPDL1) {
        this.minPDL1 = minPDL1;
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        List<PriorMolecularTest> pdl1Tests = PriorMolecularTestFunctions.allPDL1TestsByCPS(record.clinical().priorMolecularTests());
        for (PriorMolecularTest ihcTest : pdl1Tests) {
            boolean hasSufficientPDL1 = false;

            Double scoreValue = ihcTest.scoreValue();
            if (scoreValue != null && Double.compare(scoreValue, minPDL1) >= 0) {
                hasSufficientPDL1 = true;
            }

            if (hasSufficientPDL1) {
                return EvaluationFactory.unrecoverable()
                        .result(EvaluationResult.PASS)
                        .addPassSpecificMessages("PD-L1 expression measured by CPS meets at least desired level of " + minPDL1)
                        .build();
            }
        }

        ImmutableEvaluation.Builder builder = EvaluationFactory.unrecoverable().result(EvaluationResult.FAIL);

        if (!pdl1Tests.isEmpty()) {
            builder.addFailSpecificMessages("No PD-L1 IHC test found where level exceeds desired level of " + minPDL1);
        } else {
            builder.addFailSpecificMessages("No test result found; PD-L1 has not been tested by IHC");
        }

        return builder.build();
    }
}
