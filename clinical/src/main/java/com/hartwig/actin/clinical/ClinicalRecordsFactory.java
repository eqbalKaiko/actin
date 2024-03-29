package com.hartwig.actin.clinical;

import java.io.IOException;
import java.util.List;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.hartwig.actin.clinical.curation.CurationModel;
import com.hartwig.actin.clinical.curation.CurationUtil;
import com.hartwig.actin.clinical.datamodel.BloodTransfusion;
import com.hartwig.actin.clinical.datamodel.BodyWeight;
import com.hartwig.actin.clinical.datamodel.ClinicalRecord;
import com.hartwig.actin.clinical.datamodel.ClinicalStatus;
import com.hartwig.actin.clinical.datamodel.Complication;
import com.hartwig.actin.clinical.datamodel.ImmutableBloodTransfusion;
import com.hartwig.actin.clinical.datamodel.ImmutableBodyWeight;
import com.hartwig.actin.clinical.datamodel.ImmutableClinicalRecord;
import com.hartwig.actin.clinical.datamodel.ImmutableClinicalStatus;
import com.hartwig.actin.clinical.datamodel.ImmutableIntolerance;
import com.hartwig.actin.clinical.datamodel.ImmutableMedication;
import com.hartwig.actin.clinical.datamodel.ImmutablePatientDetails;
import com.hartwig.actin.clinical.datamodel.ImmutableSurgery;
import com.hartwig.actin.clinical.datamodel.ImmutableToxicity;
import com.hartwig.actin.clinical.datamodel.ImmutableTumorDetails;
import com.hartwig.actin.clinical.datamodel.ImmutableVitalFunction;
import com.hartwig.actin.clinical.datamodel.Intolerance;
import com.hartwig.actin.clinical.datamodel.LabValue;
import com.hartwig.actin.clinical.datamodel.Medication;
import com.hartwig.actin.clinical.datamodel.PatientDetails;
import com.hartwig.actin.clinical.datamodel.PriorMolecularTest;
import com.hartwig.actin.clinical.datamodel.PriorOtherCondition;
import com.hartwig.actin.clinical.datamodel.PriorSecondPrimary;
import com.hartwig.actin.clinical.datamodel.PriorTumorTreatment;
import com.hartwig.actin.clinical.datamodel.Surgery;
import com.hartwig.actin.clinical.datamodel.SurgeryStatus;
import com.hartwig.actin.clinical.datamodel.Toxicity;
import com.hartwig.actin.clinical.datamodel.ToxicitySource;
import com.hartwig.actin.clinical.datamodel.TumorDetails;
import com.hartwig.actin.clinical.datamodel.VitalFunction;
import com.hartwig.actin.clinical.feed.FeedModel;
import com.hartwig.actin.clinical.feed.bodyweight.BodyWeightEntry;
import com.hartwig.actin.clinical.feed.encounter.EncounterEntry;
import com.hartwig.actin.clinical.feed.intolerance.IntoleranceEntry;
import com.hartwig.actin.clinical.feed.lab.LabEntry;
import com.hartwig.actin.clinical.feed.lab.LabExtraction;
import com.hartwig.actin.clinical.feed.medication.MedicationEntry;
import com.hartwig.actin.clinical.feed.patient.PatientEntry;
import com.hartwig.actin.clinical.feed.questionnaire.Questionnaire;
import com.hartwig.actin.clinical.feed.questionnaire.QuestionnaireEntry;
import com.hartwig.actin.clinical.feed.questionnaire.QuestionnaireExtraction;
import com.hartwig.actin.clinical.feed.vitalfunction.VitalFunctionEntry;
import com.hartwig.actin.clinical.feed.vitalfunction.VitalFunctionExtraction;
import com.hartwig.actin.clinical.sort.ClinicalRecordComparator;
import com.hartwig.actin.clinical.sort.LabValueDescendingDateComparator;
import com.hartwig.actin.clinical.sort.MedicationByNameComparator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ClinicalRecordsFactory {

    private static final Logger LOGGER = LogManager.getLogger(ClinicalRecordsFactory.class);

    @NotNull
    private final FeedModel feed;
    @NotNull
    private final CurationModel curation;

    @NotNull
    public static List<ClinicalRecord> fromFeedAndCurationDirectories(@NotNull String clinicalFeedDirectory,
            @NotNull String clinicalCurationDirectory) throws IOException {
        return new ClinicalRecordsFactory(FeedModel.fromFeedDirectory(clinicalFeedDirectory),
                CurationModel.fromCurationDirectory(clinicalCurationDirectory)).create();
    }

    @VisibleForTesting
    ClinicalRecordsFactory(@NotNull final FeedModel feed, @NotNull final CurationModel curation) {
        this.feed = feed;
        this.curation = curation;
    }

    @NotNull
    @VisibleForTesting
    List<ClinicalRecord> create() {
        List<ClinicalRecord> records = Lists.newArrayList();
        LOGGER.info("Creating clinical model");
        for (String subject : feed.subjects()) {
            String patientId = toPatientId(subject);
            LOGGER.info(" Extracting data for patient {}", patientId);

            Questionnaire questionnaire = QuestionnaireExtraction.extract(feed.latestQuestionnaireEntry(subject));

            if (containsPatientId(records, patientId)) {
                throw new IllegalStateException("Cannot create clinical records. Duplicate patientId: " + patientId);
            }

            records.add(ImmutableClinicalRecord.builder()
                    .patientId(patientId)
                    .patient(extractPatientDetails(subject, questionnaire))
                    .tumor(extractTumorDetails(questionnaire))
                    .clinicalStatus(extractClinicalStatus(questionnaire))
                    .priorTumorTreatments(extractPriorTumorTreatments(questionnaire))
                    .priorSecondPrimaries(extractPriorSecondPrimaries(questionnaire))
                    .priorOtherConditions(extractPriorOtherConditions(questionnaire))
                    .priorMolecularTests(extractPriorMolecularTests(questionnaire))
                    .complications(extractComplications(questionnaire))
                    .labValues(extractLabValues(subject))
                    .toxicities(extractToxicities(subject, questionnaire))
                    .intolerances(extractIntolerances(subject))
                    .surgeries(extractSurgeries(subject))
                    .bodyWeights(extractBodyWeights(subject))
                    .vitalFunctions(extractVitalFunctions(subject))
                    .bloodTransfusions(extractBloodTransfusions(subject))
                    .medications(extractMedications(subject))
                    .build());
        }

        records.sort(new ClinicalRecordComparator());
        LOGGER.info("Evaluating curation database");
        curation.evaluate();

        return records;
    }

    private static boolean containsPatientId(@NotNull List<ClinicalRecord> records, @NotNull String patientId) {
        for (ClinicalRecord record : records) {
            if (record.patientId().equals(patientId)) {
                return true;
            }
        }

        return false;
    }

    @NotNull
    @VisibleForTesting
    static String toPatientId(@NotNull String subject) {
        String adjusted = subject;
        // Subjects have been passed with unexpected subject IDs in the past (e.g. without ACTN prefix)
        if (subject.length() == 10 && !subject.startsWith("ACTN")) {
            LOGGER.warn("Suspicious subject detected. Pre-fixing with 'ACTN': {}", subject);
            adjusted = "ACTN" + subject;
        }

        return adjusted.replaceAll("-", "");
    }

    @NotNull
    private PatientDetails extractPatientDetails(@NotNull String subject, @Nullable Questionnaire questionnaire) {
        PatientEntry patient = feed.patientEntry(subject);

        return ImmutablePatientDetails.builder()
                .gender(patient.gender())
                .birthYear(patient.birthYear())
                .registrationDate(patient.periodStart())
                .questionnaireDate(questionnaire != null ? questionnaire.date() : null)
                .build();
    }

    @NotNull
    private TumorDetails extractTumorDetails(@Nullable Questionnaire questionnaire) {
        if (questionnaire == null) {
            return ImmutableTumorDetails.builder().build();
        }

        String biopsyLocation = questionnaire.biopsyLocation();
        List<String> otherLesions = questionnaire.otherLesions();
        List<String> curatedOtherLesions = curation.curateOtherLesions(otherLesions);

        TumorDetails tumorDetails = ImmutableTumorDetails.builder()
                .from(curation.curateTumorDetails(questionnaire.tumorLocation(), questionnaire.tumorType()))
                .biopsyLocation(curation.curateBiopsyLocation(biopsyLocation))
                .stage(questionnaire.stage())
                .hasMeasurableDisease(questionnaire.hasMeasurableDisease())
                .hasBrainLesions(questionnaire.hasBrainLesions())
                .hasActiveBrainLesions(questionnaire.hasActiveBrainLesions())
                .hasCnsLesions(questionnaire.hasCnsLesions())
                .hasActiveCnsLesions(questionnaire.hasActiveCnsLesions())
                .hasBoneLesions(questionnaire.hasBoneLesions())
                .hasLiverLesions(questionnaire.hasLiverLesions())
                .otherLesions(curatedOtherLesions)
                .build();

        return curation.overrideKnownLesionLocations(tumorDetails, biopsyLocation, otherLesions);
    }

    @NotNull
    private ClinicalStatus extractClinicalStatus(@Nullable Questionnaire questionnaire) {
        if (questionnaire == null) {
            return ImmutableClinicalStatus.builder().build();
        }

        return ImmutableClinicalStatus.builder()
                .who(questionnaire.whoStatus())
                .infectionStatus(curation.curateInfectionStatus(questionnaire.infectionStatus()))
                .ecg(curation.curateECG(questionnaire.ecg()))
                .lvef(curation.determineLVEF(questionnaire.nonOncologicalHistory()))
                .build();
    }

    @NotNull
    private List<PriorTumorTreatment> extractPriorTumorTreatments(@Nullable Questionnaire questionnaire) {
        if (questionnaire == null) {
            return Lists.newArrayList();
        }

        List<PriorTumorTreatment> priorTumorTreatments = Lists.newArrayList();

        List<String> treatmentHistories = questionnaire.treatmentHistoryCurrentTumor();
        priorTumorTreatments.addAll(curation.curatePriorTumorTreatments(treatmentHistories));

        List<String> otherOncologicalHistories = questionnaire.otherOncologicalHistory();
        priorTumorTreatments.addAll(curation.curatePriorTumorTreatments(otherOncologicalHistories));

        return priorTumorTreatments;
    }

    @NotNull
    private List<PriorSecondPrimary> extractPriorSecondPrimaries(@Nullable Questionnaire questionnaire) {
        if (questionnaire == null) {
            return Lists.newArrayList();
        }

        List<PriorSecondPrimary> priorSecondPrimaries = Lists.newArrayList();
        priorSecondPrimaries.addAll(curation.curatePriorSecondPrimaries(questionnaire.otherOncologicalHistory()));
        priorSecondPrimaries.addAll(curation.curatePriorSecondPrimaries(questionnaire.secondaryPrimaries()));
        return priorSecondPrimaries;
    }

    @NotNull
    private List<PriorOtherCondition> extractPriorOtherConditions(@Nullable Questionnaire questionnaire) {
        if (questionnaire == null) {
            return Lists.newArrayList();
        }

        List<String> nonOncologicalHistories = questionnaire.nonOncologicalHistory();
        return curation.curatePriorOtherConditions(nonOncologicalHistories);
    }

    @NotNull
    private List<PriorMolecularTest> extractPriorMolecularTests(@Nullable Questionnaire questionnaire) {
        if (questionnaire == null) {
            return Lists.newArrayList();
        }

        List<PriorMolecularTest> priorMolecularTests = Lists.newArrayList();
        priorMolecularTests.addAll(curation.curatePriorMolecularTests("IHC", questionnaire.ihcTestResults()));
        priorMolecularTests.addAll(curation.curatePriorMolecularTests("PD-L1", questionnaire.pdl1TestResults()));
        return priorMolecularTests;
    }

    @Nullable
    private List<Complication> extractComplications(@Nullable Questionnaire questionnaire) {
        if (questionnaire != null) {
            List<String> complications = questionnaire.complications();
            return curation.curateComplications(complications);
        } else {
            return null;
        }
    }

    @NotNull
    private List<LabValue> extractLabValues(@NotNull String subject) {
        List<LabValue> values = Lists.newArrayList();
        for (LabEntry entry : feed.labEntries(subject)) {
            values.add(curation.translateLabValue(LabExtraction.extract(entry)));
        }

        values.sort(new LabValueDescendingDateComparator());

        return values;
    }

    @NotNull
    private List<Toxicity> extractToxicities(@NotNull String subject, @Nullable Questionnaire questionnaire) {
        List<Toxicity> toxicities = Lists.newArrayList();
        if (questionnaire != null) {
            List<String> unresolvedToxicities = questionnaire.unresolvedToxicities();
            toxicities.addAll(curation.curateQuestionnaireToxicities(unresolvedToxicities, questionnaire.date()));
        }

        List<QuestionnaireEntry> toxicityQuestionnaires = feed.toxicityQuestionnaireEntries(subject);
        for (QuestionnaireEntry entry : toxicityQuestionnaires) {
            Integer grade = extractGrade(entry);
            if (grade != null) {
                Toxicity toxicity = ImmutableToxicity.builder()
                        .name(entry.itemText())
                        .evaluatedDate(entry.authored())
                        .source(ToxicitySource.EHR)
                        .grade(grade)
                        .build();

                toxicities.add(curation.translateToxicity(toxicity));
            }
        }
        return toxicities;
    }

    @Nullable
    private static Integer extractGrade(@NotNull QuestionnaireEntry entry) {
        String value = entry.itemAnswerValueValueString();
        if (value.isEmpty()) {
            return null;
        }

        String curated;
        int notApplicableIndex = value.indexOf(". Not applicable");
        if (notApplicableIndex > 0) {
            curated = value.substring(0, notApplicableIndex);
        } else {
            curated = value;
        }
        return Integer.valueOf(curated);
    }

    @NotNull
    private List<Intolerance> extractIntolerances(@NotNull String subject) {
        List<Intolerance> intolerances = Lists.newArrayList();
        for (IntoleranceEntry entry : feed.intoleranceEntries(subject)) {
            Intolerance intolerance = ImmutableIntolerance.builder()
                    .name(CurationUtil.capitalizeFirstLetterOnly(entry.codeText()))
                    .category(CurationUtil.capitalizeFirstLetterOnly(entry.category()))
                    .type(CurationUtil.capitalizeFirstLetterOnly(entry.isSideEffect()))
                    .clinicalStatus(CurationUtil.capitalizeFirstLetterOnly(entry.clinicalStatus()))
                    .verificationStatus(CurationUtil.capitalizeFirstLetterOnly(entry.verificationStatus()))
                    .criticality(CurationUtil.capitalizeFirstLetterOnly(entry.criticality()))
                    .build();
            intolerances.add(curation.curateIntolerance(intolerance));
        }
        return intolerances;
    }

    @NotNull
    private List<Surgery> extractSurgeries(@NotNull String subject) {
        List<Surgery> surgeries = Lists.newArrayList();
        for (EncounterEntry entry : feed.uniqueEncounterEntries(subject)) {
            surgeries.add(ImmutableSurgery.builder().endDate(entry.periodEnd()).status(resolveSurgeryStatus(entry.encounterStatus())).build());
        }
        return surgeries;
    }

    @NotNull
    private static SurgeryStatus resolveSurgeryStatus(@NotNull String status) {
        String valueToFind = status.trim().replaceAll("-", "_");
        for (SurgeryStatus option : SurgeryStatus.values()) {
            if (option.toString().equalsIgnoreCase(valueToFind)) {
                return option;
            }
        }

        LOGGER.warn("Could not resolve surgery status '{}'", status);
        return SurgeryStatus.UNKNOWN;
    }

    @NotNull
    private List<BodyWeight> extractBodyWeights(@NotNull String subject) {
        List<BodyWeight> bodyWeights = Lists.newArrayList();
        for (BodyWeightEntry entry : feed.uniqueBodyWeightEntries(subject)) {
            bodyWeights.add(ImmutableBodyWeight.builder()
                    .date(entry.effectiveDateTime())
                    .value(entry.valueQuantityValue())
                    .unit(entry.valueQuantityUnit())
                    .build());
        }
        return bodyWeights;
    }

    @NotNull
    private List<VitalFunction> extractVitalFunctions(@NotNull String subject) {
        List<VitalFunction> vitalFunctions = Lists.newArrayList();
        for (VitalFunctionEntry entry : feed.vitalFunctionEntries(subject)) {
            vitalFunctions.add(ImmutableVitalFunction.builder()
                    .date(entry.effectiveDateTime())
                    .category(VitalFunctionExtraction.determineCategory(entry.codeDisplayOriginal()))
                    .subcategory(entry.componentCodeDisplay())
                    .value(entry.quantityValue())
                    .unit(entry.quantityUnit())
                    .build());
        }
        return vitalFunctions;
    }

    @NotNull
    private List<BloodTransfusion> extractBloodTransfusions(@NotNull String subject) {
        List<BloodTransfusion> bloodTransfusions = Lists.newArrayList();
        for (QuestionnaireEntry entry : feed.bloodTransfusionQuestionnaireEntries(subject)) {
            bloodTransfusions.add(curation.translateBloodTransfusion(ImmutableBloodTransfusion.builder()
                    .date(entry.authored())
                    .product(entry.itemAnswerValueValueString())
                    .build()));
        }
        return bloodTransfusions;
    }

    @NotNull
    private List<Medication> extractMedications(@NotNull String subject) {
        List<Medication> medications = Lists.newArrayList();

        for (MedicationEntry entry : feed.medicationEntries(subject)) {
            Medication dosageCurated = curation.curateMedicationDosage(entry.dosageInstructionText());

            ImmutableMedication.Builder builder = ImmutableMedication.builder();
            if (dosageCurated != null) {
                builder.from(dosageCurated);
            }

            String name = CurationUtil.capitalizeFirstLetterOnly(entry.code5ATCDisplay());
            if (name.isEmpty()) {
                String input = CurationUtil.capitalizeFirstLetterOnly(entry.codeText());
                name = curation.curateMedicationName(input);
            }

            if (name != null && !name.isEmpty()) {
                Medication medication = builder.name(name)
                        .codeATC(curation.curateMedicationCodeATC(entry.code5ATCCode()))
                        .status(curation.curateMedicationStatus(entry.status()))
                        .administrationRoute(curation.translateAdministrationRoute(entry.dosageInstructionRouteDisplay()))
                        .startDate(entry.periodOfUseValuePeriodStart())
                        .stopDate(entry.periodOfUseValuePeriodEnd())
                        .build();

                medications.add(curation.annotateWithMedicationCategory(medication));
            }
        }

        medications.sort(new MedicationByNameComparator());

        return medications;
    }
}
