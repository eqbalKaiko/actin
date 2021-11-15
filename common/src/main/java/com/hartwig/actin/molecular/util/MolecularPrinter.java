package com.hartwig.actin.molecular.util;

import java.util.Set;
import java.util.StringJoiner;

import com.hartwig.actin.molecular.datamodel.MolecularRecord;
import com.hartwig.actin.molecular.interpretation.MolecularInterpretation;
import com.hartwig.actin.molecular.interpretation.MolecularInterpreter;
import com.hartwig.actin.util.DatamodelPrinter;

import org.jetbrains.annotations.NotNull;

public class MolecularPrinter {

    @NotNull
    private final DatamodelPrinter printer;

    public static void printRecord(@NotNull MolecularRecord record) {
        new MolecularPrinter(DatamodelPrinter.withDefaultIndentation()).print(record);
    }

    private MolecularPrinter(@NotNull final DatamodelPrinter printer) {
        this.printer = printer;
    }

    public void print(@NotNull MolecularRecord record) {
        printer.print("Sample: " + record.sampleId());
        printer.print("Has reliable quality: " + (record.hasReliableQuality() ? "Yes" : "No"));

        MolecularInterpretation interpretation = MolecularInterpreter.interpret(record);
        printer.print("Responsive mutations: " + concat(interpretation.applicableResponsiveEvents()));
        printer.print("Resistance mutations: " + concat(interpretation.applicableResistanceEvents()));
    }

    @NotNull
    private static String concat(@NotNull Set<String> strings) {
        StringJoiner joiner = new StringJoiner(", ");
        for (String string : strings) {
            joiner.add(string);
        }
        String result = joiner.toString();
        return !result.isEmpty() ? result : "None";
    }
}
