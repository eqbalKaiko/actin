package com.hartwig.actin.algo.evaluation.molecular;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;

import org.jetbrains.annotations.NotNull;

public class GeneIsWildtype implements EvaluationFunction {

    @NotNull
    private final String gene;

    GeneIsWildtype(@NotNull final String gene) {
        this.gene = gene;
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull final PatientRecord record) {
        if (record.molecular().wildtypeGenes().contains(gene)) {
            return Evaluation.PASS;
        }

        return MolecularUtil.noMatchFound(record.molecular());
    }
}
