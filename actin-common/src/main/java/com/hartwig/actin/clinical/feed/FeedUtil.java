package com.hartwig.actin.clinical.feed;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;

import org.jetbrains.annotations.NotNull;

public final class FeedUtil {

    private FeedUtil() {
    }

    @NotNull
    public static List<String> readFeedFile(@NotNull String feedTsv) throws IOException {
        // EMC delivers the feed files encoded in UTF_16LE so need to convert.
        return Files.readAllLines(new File(feedTsv).toPath(), StandardCharsets.UTF_16LE);
    }

    @NotNull
    public static String[] splitFeedLine(@NotNull String line, @NotNull String delimiter) {
        return cleanQuotes(line.split(delimiter));
    }

    @NotNull
    public static Map<String, Integer> createFieldIndexMap(@NotNull String header, @NotNull String delimiter) {
        String[] items = splitFeedLine(header, delimiter);
        Map<String, Integer> fieldIndexMap = Maps.newHashMap();

        for (int i = 0; i < items.length; ++i) {
            fieldIndexMap.put(items[i], i);
        }

        return fieldIndexMap;
    }

    @NotNull
    @VisibleForTesting
    static String[] cleanQuotes(@NotNull String[] inputs) {
        String[] cleaned = new String[inputs.length];

        for (int i = 0; i < inputs.length; i++) {
            cleaned[i] = cleanQuotes(inputs[i]);
        }

        return cleaned;
    }

    @NotNull
    private static String cleanQuotes(@NotNull String input) {
        int firstQuote = input.indexOf("\"");
        int lastQuote = input.lastIndexOf("\"");
        String cleaned = input;
        if (firstQuote >= 0 && lastQuote >= 0 && lastQuote > firstQuote) {
            cleaned = input.substring(firstQuote + 1, lastQuote);
        }

        // Replace all double quotes with single quotes.
        return cleaned.replaceAll("\"\"", "\"");
    }
}
