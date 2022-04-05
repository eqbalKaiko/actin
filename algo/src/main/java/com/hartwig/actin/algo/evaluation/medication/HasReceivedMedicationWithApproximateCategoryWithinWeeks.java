package com.hartwig.actin.algo.evaluation.medication;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.evaluation.EvaluationFactory;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;

import org.jetbrains.annotations.NotNull;

public class HasReceivedMedicationWithApproximateCategoryWithinWeeks implements EvaluationFunction {

    //TODO: Implement according to README
    HasReceivedMedicationWithApproximateCategoryWithinWeeks() {
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        return EvaluationFactory.unrecoverable()
                .result(EvaluationResult.UNDETERMINED)
                .addUndeterminedSpecificMessages("Currently not determined if patient received requested medication")
                .addUndeterminedGeneralMessages("Medication requirements")
                .build();
    }
}