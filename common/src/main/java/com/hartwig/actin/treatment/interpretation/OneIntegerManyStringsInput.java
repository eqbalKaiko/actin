package com.hartwig.actin.treatment.interpretation;

import java.util.List;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class OneIntegerManyStringsInput {

    public abstract int integer();

    @NotNull
    public abstract List<String> strings();
}
