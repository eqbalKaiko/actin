package com.hartwig.actin.algo.evaluation.tumor;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hartwig.actin.ImmutablePatientRecord;
import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.TestDataFactory;
import com.hartwig.actin.clinical.datamodel.ImmutableClinicalRecord;
import com.hartwig.actin.clinical.datamodel.ImmutableTumorDetails;
import com.hartwig.actin.clinical.datamodel.TestClinicalFactory;
import com.hartwig.actin.clinical.datamodel.TumorDetails;
import com.hartwig.actin.clinical.datamodel.TumorStage;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

final class TumorTestFactory {

    private TumorTestFactory() {
    }

    @NotNull
    public static PatientRecord withTumorTypeAndDoids(@Nullable String primaryTumorType, @Nullable String primaryTumorSubType,
            @NotNull String... doids) {
        return withTumorTypeAndDoids(primaryTumorType, primaryTumorSubType, Lists.newArrayList(doids));
    }

    @NotNull
    public static PatientRecord withTumorTypeAndDoids(@Nullable String primaryTumorType, @Nullable String primaryTumorSubType,
            @Nullable List<String> doids) {
        return withTumorDetails(ImmutableTumorDetails.builder()
                .primaryTumorType(primaryTumorType)
                .primaryTumorSubType(primaryTumorSubType)
                .doids(doids)
                .build());
    }

    @NotNull
    public static PatientRecord withDoids(@NotNull String... doids) {
        return withDoids(Sets.newHashSet(doids));
    }

    @NotNull
    public static PatientRecord withDoidAndDetails(@NotNull String doid, @NotNull String extraDetails) {
        return withTumorDetails(ImmutableTumorDetails.builder().addDoids(doid).primaryTumorExtraDetails(extraDetails).build());
    }

    @NotNull
    public static PatientRecord withDoids(@Nullable Set<String> doids) {
        return withTumorDetails(ImmutableTumorDetails.builder().doids(doids).build());
    }

    @NotNull
    public static PatientRecord withTumorStage(@Nullable TumorStage stage) {
        return withTumorDetails(ImmutableTumorDetails.builder().stage(stage).build());
    }

    @NotNull
    public static PatientRecord withMeasurableDisease(@Nullable Boolean hasMeasurableDisease) {
        return withTumorDetails(ImmutableTumorDetails.builder().hasMeasurableDisease(hasMeasurableDisease).build());
    }

    @NotNull
    public static PatientRecord withMeasurableDiseaseAndDoid(@Nullable Boolean hasMeasurableDisease, @NotNull String doid) {
        return withTumorDetails(ImmutableTumorDetails.builder().hasMeasurableDisease(hasMeasurableDisease).addDoids(doid).build());
    }

    @NotNull
    public static PatientRecord withBrainAndCnsLesions(@Nullable Boolean hasBrainLesions, @Nullable Boolean hasCnsLesions) {
        return withTumorDetails(ImmutableTumorDetails.builder().hasBrainLesions(hasBrainLesions).hasCnsLesions(hasCnsLesions).build());
    }

    @NotNull
    public static PatientRecord withActiveBrainAndCnsLesions(@Nullable Boolean hasActiveBrainLesions,
            @Nullable Boolean hasActiveCnsLesions) {
        return withTumorDetails(ImmutableTumorDetails.builder()
                .hasActiveBrainLesions(hasActiveBrainLesions)
                .hasActiveCnsLesions(hasActiveCnsLesions)
                .build());
    }

    @NotNull
    public static PatientRecord withBrainLesions(@Nullable Boolean hasBrainLesions) {
        return withTumorDetails(ImmutableTumorDetails.builder().hasBrainLesions(hasBrainLesions).build());
    }

    @NotNull
    public static PatientRecord withActiveBrainLesions(@Nullable Boolean hasActiveBrainLesions) {
        return withTumorDetails(ImmutableTumorDetails.builder().hasActiveBrainLesions(hasActiveBrainLesions).build());
    }

    @NotNull
    public static PatientRecord withCnsLesions(@Nullable Boolean hasCnsLesions) {
        return withTumorDetails(ImmutableTumorDetails.builder().hasCnsLesions(hasCnsLesions).build());
    }

    @NotNull
    public static PatientRecord withBoneLesions(@Nullable Boolean hasBoneLesions) {
        return withTumorDetails(ImmutableTumorDetails.builder().hasBoneLesions(hasBoneLesions).build());
    }

    @NotNull
    public static PatientRecord withBoneAndLiverLesions(@Nullable Boolean hasBoneLesions, @Nullable Boolean hasLiverLesions) {
        return withTumorDetails(ImmutableTumorDetails.builder().hasBoneLesions(hasBoneLesions).hasLiverLesions(hasLiverLesions).build());
    }

    @NotNull
    public static PatientRecord withLiverLesions(@Nullable Boolean hasLiverLesions) {
        return withTumorDetails(ImmutableTumorDetails.builder().hasLiverLesions(hasLiverLesions).build());
    }

    @NotNull
    public static PatientRecord withLungLesions(@Nullable Boolean hasLungLesions) {
        return withTumorDetails(ImmutableTumorDetails.builder().hasLungLesions(hasLungLesions).build());
    }

    @NotNull
    public static PatientRecord withOtherLesions(@Nullable List<String> otherLesions) {
        return withTumorDetails(ImmutableTumorDetails.builder().otherLesions(otherLesions).build());
    }

    @NotNull
    public static PatientRecord withTumorDetails(@NotNull TumorDetails tumor) {
        return ImmutablePatientRecord.builder()
                .from(TestDataFactory.createMinimalTestPatientRecord())
                .clinical(ImmutableClinicalRecord.builder()
                        .from(TestClinicalFactory.createMinimalTestClinicalRecord())
                        .tumor(tumor)
                        .build())
                .build();
    }
}
