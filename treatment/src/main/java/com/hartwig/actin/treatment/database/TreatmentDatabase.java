package com.hartwig.actin.treatment.database;

import java.util.List;

import com.hartwig.actin.treatment.database.config.CohortConfig;
import com.hartwig.actin.treatment.database.config.InclusionCriteriaConfig;
import com.hartwig.actin.treatment.database.config.TrialConfig;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class TreatmentDatabase {

    @NotNull
    public abstract List<TrialConfig> trialConfigs();

    @NotNull
    public abstract List<CohortConfig> cohortConfigs();

    @NotNull
    public abstract List<InclusionCriteriaConfig> inclusionCriteriaConfigs();
}