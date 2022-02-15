package com.hartwig.actin.algo.datamodel;

import java.util.Map;

import com.hartwig.actin.treatment.datamodel.CohortMetadata;
import com.hartwig.actin.treatment.datamodel.Eligibility;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class CohortEligibility {

    @NotNull
    public abstract CohortMetadata metadata();

    @NotNull
    public abstract EvaluationResult overallEvaluation();

    @NotNull
    public abstract Map<Eligibility, Evaluation> evaluations();
}
