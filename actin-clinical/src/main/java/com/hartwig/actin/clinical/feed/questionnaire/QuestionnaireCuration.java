package com.hartwig.actin.clinical.feed.questionnaire;

import java.util.Map;

import com.google.common.collect.Maps;
import com.hartwig.actin.datamodel.clinical.TumorStage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

final class QuestionnaireCuration {

    private static final Logger LOGGER = LogManager.getLogger(QuestionnaireCuration.class);

    private static final Map<String, Boolean> OPTION_MAPPING = Maps.newHashMap();
    private static final Map<String, TumorStage> STAGE_MAPPING = Maps.newHashMap();

    static {
        OPTION_MAPPING.put("no", false);
        OPTION_MAPPING.put("No", false);
        OPTION_MAPPING.put("NO", false);
        OPTION_MAPPING.put("n.v.t.", null);
        OPTION_MAPPING.put("n.v.t", null);
        OPTION_MAPPING.put("nvt", null);
        OPTION_MAPPING.put("nvt.", null);
        OPTION_MAPPING.put("NA", false);
        OPTION_MAPPING.put("yes", true);
        OPTION_MAPPING.put("Yes", true);
        OPTION_MAPPING.put("YES", true);
        OPTION_MAPPING.put("unknown", null);
        OPTION_MAPPING.put("Unknown", null);
        OPTION_MAPPING.put("UNKNOWN", null);
        OPTION_MAPPING.put("-", null);
        OPTION_MAPPING.put("yes/no", null);

        STAGE_MAPPING.put("I", TumorStage.I);
        STAGE_MAPPING.put("II", TumorStage.II);
        STAGE_MAPPING.put("2", TumorStage.II);
        STAGE_MAPPING.put("IIb", TumorStage.IIB);
        STAGE_MAPPING.put("III", TumorStage.III);
        STAGE_MAPPING.put("3", TumorStage.III);
        STAGE_MAPPING.put("IIIc", TumorStage.IIIC);
        STAGE_MAPPING.put("IV", TumorStage.IV);
        STAGE_MAPPING.put("4", TumorStage.IV);
        STAGE_MAPPING.put("cT3N2M1", TumorStage.IV);
    }

    private QuestionnaireCuration() {
    }

    @Nullable
    public static TumorStage toStage(@Nullable String stage) {
        if (stage == null || stage.isEmpty()) {
            return null;
        }

        if (!STAGE_MAPPING.containsKey(stage)) {
            LOGGER.warn("Unrecognized questionnaire tumor stage: '{}'", stage);
            return null;
        }

        return STAGE_MAPPING.get(stage);
    }

    @Nullable
    public static Boolean toOption(@Nullable String option) {
        if (option == null || option.isEmpty()) {
            return null;
        }

        if (!isConfiguredOption(option)) {
            LOGGER.warn("Unrecognized questionnaire option: '{}'", option);
            return null;
        }

        return OPTION_MAPPING.get(option);
    }

    public static boolean isConfiguredOption(@Nullable String option) {
        if (option == null) {
            return false;
        }

        return OPTION_MAPPING.containsKey(option);
    }

    @Nullable
    public static Integer toWHO(@Nullable String integer) {
        if (integer == null || integer.isEmpty()) {
            return null;
        }

        int value = Integer.parseInt(integer);
        if (value >= 0 && value <= 4) {
            return value;
        } else {
            LOGGER.warn("Unrecognized WHO status: '{}'", value);
            return null;
        }
    }
}