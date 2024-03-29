package com.hartwig.actin.clinical.feed.questionnaire;

import com.hartwig.actin.clinical.feed.FeedEntryCreator;
import com.hartwig.actin.clinical.feed.FeedLine;

import org.jetbrains.annotations.NotNull;

public class QuestionnaireEntryCreator implements FeedEntryCreator<QuestionnaireEntry> {

    private static final String MANUAL_QUESTIONNAIRE_DESCRIPTION = "INT Consult";

    private final boolean invalidateManualQuestionnaires;

    public QuestionnaireEntryCreator(final boolean invalidateManualQuestionnaires) {
        this.invalidateManualQuestionnaires = invalidateManualQuestionnaires;
    }

    @NotNull
    @Override
    public QuestionnaireEntry fromLine(@NotNull final FeedLine line) {
        return ImmutableQuestionnaireEntry.builder()
                .subject(line.trimmed("subject"))
                .authored(line.date("authored"))
                .description(line.string("description"))
                .itemText(line.string("item_text"))
                .itemAnswerValueValueString(line.string("item_answer_value_valueString"))
                .build();
    }

    @Override
    public boolean isValid(@NotNull final FeedLine line) {
        return !invalidateManualQuestionnaires || !line.string("description").equals(MANUAL_QUESTIONNAIRE_DESCRIPTION);
    }
}
