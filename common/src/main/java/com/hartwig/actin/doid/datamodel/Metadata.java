package com.hartwig.actin.doid.datamodel;

import java.util.List;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class Metadata {

    @Nullable
    public abstract Definition definition();

    @Nullable
    public abstract List<String> subsets();

    @Nullable
    public abstract List<Xref> xrefs();

    @Nullable
    public abstract List<Synonym> synonyms();

    @Nullable
    public abstract List<BasicPropertyValue> basicPropertyValues();

    @Nullable
    public abstract String snomedConceptId();

    @Nullable
    public abstract Boolean deprecated();

    @Nullable
    public abstract List<String> comments();

}
