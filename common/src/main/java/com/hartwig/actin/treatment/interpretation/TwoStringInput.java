package com.hartwig.actin.treatment.interpretation;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class TwoStringInput {

    @NotNull
    public abstract String string1();

    @NotNull
    public abstract String string2();

}
