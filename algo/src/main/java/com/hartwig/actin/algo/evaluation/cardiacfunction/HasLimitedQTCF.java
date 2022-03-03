package com.hartwig.actin.algo.evaluation.cardiacfunction;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.datamodel.ImmutableEvaluation;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;
import com.hartwig.actin.clinical.datamodel.ECG;

import org.jetbrains.annotations.NotNull;

public class HasLimitedQTCF implements EvaluationFunction {

    static final String EXPECTED_QTCF_UNIT = "ms";

    private final double maxQTCF;

    HasLimitedQTCF(final double maxQTCF) {
        this.maxQTCF = maxQTCF;
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        ECG ecg = record.clinical().clinicalStatus().ecg();
        if (ecg == null || ecg.qtcfValue() == null || ecg.qtcfUnit() == null) {
            return ImmutableEvaluation.builder()
                    .result(EvaluationResult.UNDETERMINED)
                    .addUndeterminedMessages("QTCF details are missing")
                    .build();
        }

        Integer value = ecg.qtcfValue();
        String unit = ecg.qtcfUnit();

        if (!unit.equalsIgnoreCase(EXPECTED_QTCF_UNIT)) {
            return ImmutableEvaluation.builder()
                    .result(EvaluationResult.UNDETERMINED)
                    .addUndeterminedMessages("QTCF measure not in '" + EXPECTED_QTCF_UNIT + "': " + unit)
                    .build();
        }

        EvaluationResult result = Double.compare(value, maxQTCF) <= 0 ? EvaluationResult.PASS : EvaluationResult.FAIL;
        ImmutableEvaluation.Builder builder = ImmutableEvaluation.builder().result(result);
        if (result == EvaluationResult.FAIL) {
            builder.addFailMessages("QTCF of " + value + " " + unit + " exceeds maximum threshold of " + maxQTCF);
        } else if (result == EvaluationResult.PASS) {
            builder.addPassMessages("QTCF of " + value + " " + unit + " is below maximum threshold of " + maxQTCF);
        }

        return builder.build();

    }
}
