package com.hartwig.actin.clinical.feed.questionnaire;

import static org.junit.Assert.assertEquals;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class QuestionnaireVersionTest {

    @Test
    public void canResolveAllVersions() {
        assertEquals(QuestionnaireVersion.V1_1,
                QuestionnaireVersion.version(entry(TestQuestionnaireFactory.createTestQuestionnaireValueV1_1())));
        assertEquals(QuestionnaireVersion.V1_0,
                QuestionnaireVersion.version(entry(TestQuestionnaireFactory.createTestQuestionnaireValueV1_0())));
        assertEquals(QuestionnaireVersion.V0_1,
                QuestionnaireVersion.version(entry(TestQuestionnaireFactory.createTestQuestionnaireValueV0_1())));
    }

    @NotNull
    private static QuestionnaireEntry entry(@NotNull String questionnaire) {
        return ImmutableQuestionnaireEntry.builder()
                .from(TestQuestionnaireFactory.createTestQuestionnaireEntry())
                .itemAnswerValueValueString(questionnaire)
                .build();
    }
}