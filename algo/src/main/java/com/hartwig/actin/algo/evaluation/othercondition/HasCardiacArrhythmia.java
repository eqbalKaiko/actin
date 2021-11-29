package com.hartwig.actin.algo.evaluation.othercondition;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;
import com.hartwig.actin.clinical.datamodel.ECGAberration;

import org.jetbrains.annotations.NotNull;

public class HasCardiacArrhythmia implements EvaluationFunction {

    HasCardiacArrhythmia() {
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        ECGAberration ecgAberration = record.clinical().clinicalStatus().ecgAberration();
        if (ecgAberration == null) {
            return Evaluation.FAIL;
        }

        return ecgAberration.hasSigAberrationLatestECG() ? Evaluation.PASS : Evaluation.FAIL;
    }
}
