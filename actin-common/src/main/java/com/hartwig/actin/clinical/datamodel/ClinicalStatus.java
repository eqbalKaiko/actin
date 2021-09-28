package com.hartwig.actin.clinical.datamodel;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class ClinicalStatus {

    @Nullable
    public abstract Integer who();

    @Nullable
    public abstract Boolean hasCurrentInfection();

    @Nullable
    public abstract String infectionDescription();

    @Nullable
    public abstract Boolean hasSigAberrationLatestEcg();

    @Nullable
    public abstract String ecgAberrationDescription();

    @Nullable
    public abstract String cancerRelatedComplication();
}
