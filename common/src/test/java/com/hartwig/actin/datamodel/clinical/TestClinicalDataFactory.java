package com.hartwig.actin.datamodel.clinical;

import java.time.LocalDate;
import java.util.List;

import com.google.common.collect.Lists;
import com.hartwig.actin.datamodel.TestDataFactory;

import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;

public final class TestClinicalDataFactory {

    private static final LocalDate TODAY = LocalDate.now();

    private TestClinicalDataFactory() {
    }

    @NotNull
    public static ClinicalRecord createMinimalTestClinicalRecord() {
        return ImmutableClinicalRecord.builder()
                .sampleId(TestDataFactory.TEST_SAMPLE)
                .patient(createTestPatientDetails())
                .tumor(createTestTumorDetails())
                .clinicalStatus(createTestClinicalStatus())
                .build();
    }

    @NotNull
    public static ClinicalRecord createProperTestClinicalRecord() {
        return ImmutableClinicalRecord.builder()
                .from(createMinimalTestClinicalRecord())
                .priorTumorTreatments(createTestPriorTumorTreatments())
                .priorSecondPrimaries(createTestPriorSecondPrimaries())
                .priorOtherConditions(createTestPriorOtherConditions())
                .cancerRelatedComplications(createTestCancerRelatedComplications())
                .otherComplications(createTestOtherComplications())
                .labValues(createTestLabValues())
                .toxicities(createTestToxicities())
                .allergies(createTestAllergies())
                .surgeries(createTestSurgeries())
                .bloodPressures(createTestBloodPressures())
                .bloodTransfusions(createTestBloodTransfusions())
                .medications(createTestMedications())
                .build();
    }

    @NotNull
    private static PatientDetails createTestPatientDetails() {
        return ImmutablePatientDetails.builder()
                .gender(Gender.MALE)
                .birthYear(1950)
                .registrationDate(TODAY.minusDays(12))
                .questionnaireDate(TODAY.minusDays(4))
                .build();
    }

    @NotNull
    private static TumorDetails createTestTumorDetails() {
        return ImmutableTumorDetails.builder()
                .primaryTumorLocation("Skin")
                .primaryTumorSubLocation(Strings.EMPTY)
                .primaryTumorType("Melanoma")
                .primaryTumorSubType(Strings.EMPTY)
                .primaryTumorExtraDetails(Strings.EMPTY)
                .addDoids("8923")
                .stage(TumorStage.IV)
                .hasMeasurableLesionRecist(true)
                .hasBrainLesions(false)
                .hasActiveBrainLesions(null)
                .hasSymptomaticBrainLesions(null)
                .hasCnsLesions(true)
                .hasActiveCnsLesions(null)
                .hasSymptomaticCnsLesions(true)
                .hasBoneLesions(false)
                .hasLiverLesions(true)
                .hasOtherLesions(true)
                .addOtherLesions("Pulmonal")
                .addOtherLesions("Abdomen")
                .biopsyLocation("Liver")
                .build();
    }

    @NotNull
    private static ClinicalStatus createTestClinicalStatus() {
        return ImmutableClinicalStatus.builder()
                .who(1)
                .hasActiveInfection(false)
                .hasSigAberrationLatestEcg(true)
                .ecgAberrationDescription("Atrial arrhythmia")
                .build();
    }

    @NotNull
    private static List<PriorTumorTreatment> createTestPriorTumorTreatments() {
        List<PriorTumorTreatment> priorTumorTreatments = Lists.newArrayList();

        priorTumorTreatments.add(ImmutablePriorTumorTreatment.builder()
                .name("Surgery")
                .year(1998)
                .category("Surgery")
                .isSystemic(false)
                .build());

        priorTumorTreatments.add(ImmutablePriorTumorTreatment.builder()
                .name("Vemurafenib")
                .year(1999)
                .category("Targeted therapy")
                .isSystemic(true)
                .targetedType("BRAF inhibitor")
                .build());

        priorTumorTreatments.add(ImmutablePriorTumorTreatment.builder()
                .name("Ipilimumab")
                .year(1999)
                .category("Immunotherapy")
                .isSystemic(true)
                .immunoType("Anti-CTLA-4")
                .build());

        return priorTumorTreatments;
    }

    @NotNull
    private static List<PriorSecondPrimary> createTestPriorSecondPrimaries() {
        List<PriorSecondPrimary> priorSecondPrimaries = Lists.newArrayList();

        priorSecondPrimaries.add(ImmutablePriorSecondPrimary.builder()
                .tumorLocation("Bone/Soft tissue")
                .tumorSubLocation(Strings.EMPTY)
                .tumorType("Schwannoma")
                .tumorSubType(Strings.EMPTY)
                .addDoids("3192")
                .diagnosedYear(1998)
                .isSecondPrimaryActive(true)
                .build());

        return priorSecondPrimaries;
    }

    @NotNull
    private static List<PriorOtherCondition> createTestPriorOtherConditions() {
        List<PriorOtherCondition> priorOtherConditions = Lists.newArrayList();

        priorOtherConditions.add(ImmutablePriorOtherCondition.builder()
                .name("Pancreatitis")
                .addDoids("4989")
                .category("Pancreas disease")
                .build());

        priorOtherConditions.add(ImmutablePriorOtherCondition.builder()
                .name("Myocardial infarction")
                .addDoids("5844")
                .category("Vascular disease")
                .build());

        return priorOtherConditions;
    }

    @NotNull
    private static List<CancerRelatedComplication> createTestCancerRelatedComplications() {
        List<CancerRelatedComplication> cancerRelatedComplications = Lists.newArrayList();

        cancerRelatedComplications.add(ImmutableCancerRelatedComplication.builder().name("None").build());

        return cancerRelatedComplications;
    }

    @NotNull
    private static List<Complication> createTestOtherComplications() {
        List<Complication> otherComplications = Lists.newArrayList();

        otherComplications.add(ImmutableComplication.builder().name(Strings.EMPTY).specialty(Strings.EMPTY).onsetDate(TODAY.minusDays(36))
                .category(Strings.EMPTY)
                .status(Strings.EMPTY)
                .build());

        return otherComplications;
    }

    @NotNull
    private static List<LabValue> createTestLabValues() {
        List<LabValue> labValues = Lists.newArrayList();

        labValues.add(ImmutableLabValue.builder().date(TODAY.minusDays(15))
                .code("ASAT")
                .name("Aspartate aminotransferase")
                .comparator(Strings.EMPTY)
                .value(36)
                .unit("U/l")
                .refLimitUp(35D)
                .isOutsideRef(true)
                .build());

        labValues.add(ImmutableLabValue.builder().date(TODAY.minusDays(14))
                .code("Hb")
                .name("Hemoglobin")
                .comparator(Strings.EMPTY)
                .value(5.5)
                .unit("mmol/L")
                .refLimitLow(6.5D)
                .refLimitUp(9.5D)
                .isOutsideRef(true)
                .build());

        labValues.add(ImmutableLabValue.builder().date(TODAY.minusDays(13))
                .code("THROMBO")
                .name("Thrombocytes")
                .comparator(Strings.EMPTY)
                .value(155)
                .unit("10*9/L")
                .refLimitLow(155D)
                .refLimitUp(350D)
                .isOutsideRef(false)
                .build());

        labValues.add(ImmutableLabValue.builder().date(TODAY.minusDays(14))
                .code("THROMBO")
                .name("Thrombocytes")
                .comparator(Strings.EMPTY)
                .value(151)
                .unit("10*9/L")
                .refLimitLow(155D)
                .refLimitUp(350D)
                .isOutsideRef(true)
                .build());

        labValues.add(ImmutableLabValue.builder().date(TODAY.minusDays(15))
                .code("THROMBO")
                .name("Thrombocytes")
                .comparator(Strings.EMPTY)
                .value(150)
                .unit("10*9/L")
                .refLimitLow(155D)
                .refLimitUp(350D)
                .isOutsideRef(true)
                .build());

        labValues.add(ImmutableLabValue.builder().date(TODAY.minusDays(13))
                .code("LEUKO")
                .name("Leukocytes")
                .comparator(Strings.EMPTY)
                .value(6.5)
                .unit("10^9/L")
                .refLimitLow(3D)
                .refLimitUp(10D)
                .isOutsideRef(false)
                .build());

        labValues.add(ImmutableLabValue.builder().date(TODAY.minusDays(13))
                .code("CKD-EPIeGFR")
                .name("CKD-EPI eGFR")
                .comparator(">")
                .value(100)
                .unit("mL/min")
                .refLimitLow(100D)
                .isOutsideRef(false)
                .build());

        return labValues;
    }

    @NotNull
    private static List<Toxicity> createTestToxicities() {
        List<Toxicity> toxicities = Lists.newArrayList();

        toxicities.add(ImmutableToxicity.builder().name("Nausea").evaluatedDate(TODAY.minusDays(20))
                .source(ToxicitySource.EHR)
                .grade(1)
                .build());

        toxicities.add(ImmutableToxicity.builder().name("Fatigue").evaluatedDate(TODAY.minusDays(20))
                .source(ToxicitySource.EHR)
                .grade(2)
                .build());

        toxicities.add(ImmutableToxicity.builder().name("Dizziness").evaluatedDate(TODAY.minusDays(20))
                .source(ToxicitySource.QUESTIONNAIRE)
                .grade(null)
                .build());

        return toxicities;
    }

    @NotNull
    private static List<Allergy> createTestAllergies() {
        List<Allergy> allergies = Lists.newArrayList();

        allergies.add(ImmutableAllergy.builder().name("Wasps").category("Environment").criticality("Unable-to-assess").build());
        allergies.add(ImmutableAllergy.builder().name("Pembrolizumab").category("Medication").criticality("High").build());

        return allergies;
    }

    @NotNull
    private static List<Surgery> createTestSurgeries() {
        List<Surgery> surgeries = Lists.newArrayList();

        surgeries.add(ImmutableSurgery.builder().endDate(TODAY.minusDays(12)).build());

        return surgeries;
    }

    @NotNull
    private static List<BloodPressure> createTestBloodPressures() {
        List<BloodPressure> bloodPressures = Lists.newArrayList();

        bloodPressures.add(ImmutableBloodPressure.builder().date(TODAY.minusDays(10))
                .category("Mean blood pressure")
                .value(99)
                .unit("mm[Hg]")
                .build());

        return bloodPressures;
    }

    @NotNull
    private static List<BloodTransfusion> createTestBloodTransfusions() {
        List<BloodTransfusion> bloodTransfusions = Lists.newArrayList();

        bloodTransfusions.add(ImmutableBloodTransfusion.builder().date(TODAY.minusDays(45))
                .product("Thrombocyte concentrate")
                .build());

        return bloodTransfusions;
    }

    @NotNull
    private static List<Medication> createTestMedications() {
        List<Medication> medications = Lists.newArrayList();

        medications.add(ImmutableMedication.builder()
                .name("Ibuprofen")
                .type("NSAIDs")
                .dosageMin(750D)
                .dosageMax(750D)
                .dosageUnit("mg")
                .frequency(1D)
                .frequencyUnit("day")
                .ifNeeded(false)
                .startDate(TODAY.minusDays(90))
                .stopDate(TODAY.minusDays(30))
                .active(true)
                .build());

        return medications;
    }
}
