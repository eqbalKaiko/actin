package com.hartwig.actin.algo.calendar;

import java.time.LocalDate;

import org.jetbrains.annotations.NotNull;

public final class TestReferenceDateProviderFactory {

    private TestReferenceDateProviderFactory() {
    }

    @NotNull
    public static ReferenceDateProvider createCurrentDate() {
        LocalDate date = LocalDate.now();
        return () -> date;
    }
}