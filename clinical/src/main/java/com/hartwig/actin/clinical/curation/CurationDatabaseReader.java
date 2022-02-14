package com.hartwig.actin.clinical.curation;

import java.io.IOException;
import java.util.List;

import com.hartwig.actin.clinical.curation.config.CancerRelatedComplicationConfig;
import com.hartwig.actin.clinical.curation.config.CancerRelatedComplicationConfigFactory;
import com.hartwig.actin.clinical.curation.config.CurationConfigFile;
import com.hartwig.actin.clinical.curation.config.ECGConfig;
import com.hartwig.actin.clinical.curation.config.ECGConfigFactory;
import com.hartwig.actin.clinical.curation.config.InfectionConfig;
import com.hartwig.actin.clinical.curation.config.InfectionConfigFactory;
import com.hartwig.actin.clinical.curation.config.LesionLocationConfig;
import com.hartwig.actin.clinical.curation.config.LesionLocationConfigFactory;
import com.hartwig.actin.clinical.curation.config.MedicationCategoryConfig;
import com.hartwig.actin.clinical.curation.config.MedicationCategoryConfigFactory;
import com.hartwig.actin.clinical.curation.config.MedicationDosageConfig;
import com.hartwig.actin.clinical.curation.config.MedicationDosageConfigFactory;
import com.hartwig.actin.clinical.curation.config.MolecularTestConfig;
import com.hartwig.actin.clinical.curation.config.MolecularTestConfigFactory;
import com.hartwig.actin.clinical.curation.config.NonOncologicalHistoryConfig;
import com.hartwig.actin.clinical.curation.config.NonOncologicalHistoryConfigFactory;
import com.hartwig.actin.clinical.curation.config.OncologicalHistoryConfig;
import com.hartwig.actin.clinical.curation.config.OncologicalHistoryConfigFactory;
import com.hartwig.actin.clinical.curation.config.PrimaryTumorConfig;
import com.hartwig.actin.clinical.curation.config.PrimaryTumorConfigFactory;
import com.hartwig.actin.clinical.curation.config.ToxicityConfig;
import com.hartwig.actin.clinical.curation.config.ToxicityConfigFactory;
import com.hartwig.actin.clinical.curation.translation.AllergyTranslation;
import com.hartwig.actin.clinical.curation.translation.AllergyTranslationFactory;
import com.hartwig.actin.clinical.curation.translation.BloodTransfusionTranslation;
import com.hartwig.actin.clinical.curation.translation.BloodTransfusionTranslationFactory;
import com.hartwig.actin.clinical.curation.translation.LaboratoryTranslation;
import com.hartwig.actin.clinical.curation.translation.LaboratoryTranslationFactory;
import com.hartwig.actin.clinical.curation.translation.TranslationFile;
import com.hartwig.actin.util.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

public final class CurationDatabaseReader {

    private static final Logger LOGGER = LogManager.getLogger(CurationDatabaseReader.class);

    private static final String PRIMARY_TUMOR_TSV = "primary_tumor.tsv";
    private static final String LESION_LOCATION_TSV = "lesion_location.tsv";
    private static final String ONCOLOGICAL_HISTORY_TSV = "oncological_history.tsv";
    private static final String NON_ONCOLOGICAL_HISTORY_TSV = "non_oncological_history.tsv";
    private static final String ECG_TSV = "ecg.tsv";
    private static final String INFECTION_TSV = "infection.tsv";
    private static final String CANCER_RELATED_COMPLICATION_TSV = "cancer_related_complication.tsv";
    private static final String TOXICITY_TSV = "toxicity.tsv";
    private static final String MOLECULAR_TEST_TSV = "molecular_test.tsv";
    private static final String MEDICATION_DOSAGE_TSV = "medication_dosage.tsv";
    private static final String MEDICATION_CATEGORY_TSV = "medication_category.tsv";

    private static final String LABORATORY_TRANSLATION_TSV = "laboratory_translation.tsv";
    private static final String ALLERGY_TRANSLATION_TSV = "allergy_translation.tsv";
    private static final String BLOOD_TRANSFUSION_TRANSLATION_TSV = "blood_transfusion_translation.tsv";

    private CurationDatabaseReader() {
    }

    @NotNull
    public static CurationDatabase read(@NotNull String clinicalCurationDirectory) throws IOException {
        LOGGER.info("Reading clinical curation config from {}", clinicalCurationDirectory);

        String basePath = Paths.forceTrailingFileSeparator(clinicalCurationDirectory);

        return ImmutableCurationDatabase.builder()
                .primaryTumorConfigs(readPrimaryTumorConfigs(basePath + PRIMARY_TUMOR_TSV))
                .lesionLocationConfigs(readLesionLocationConfigs(basePath + LESION_LOCATION_TSV))
                .oncologicalHistoryConfigs(readOncologicalHistoryConfigs(basePath + ONCOLOGICAL_HISTORY_TSV))
                .nonOncologicalHistoryConfigs(readNonOncologicalHistoryConfigs(basePath + NON_ONCOLOGICAL_HISTORY_TSV))
                .ecgConfigs(readECGConfigs(basePath + ECG_TSV))
                .infectionConfigs(readInfectionConfigs(basePath + INFECTION_TSV))
                .cancerRelatedComplicationConfigs(readCancerRelatedComplicationConfigs(basePath + CANCER_RELATED_COMPLICATION_TSV))
                .toxicityConfigs(readToxicityConfigs(basePath + TOXICITY_TSV))
                .molecularTestConfigs(readMolecularTestConfigs(basePath + MOLECULAR_TEST_TSV))
                .medicationDosageConfigs(readMedicationDosageConfigs(basePath + MEDICATION_DOSAGE_TSV))
                .medicationCategoryConfigs(readMedicationCategoryConfigs(basePath + MEDICATION_CATEGORY_TSV))
                .laboratoryTranslations(readLaboratoryTranslations(basePath + LABORATORY_TRANSLATION_TSV))
                .allergyTranslations(readAllergyTranslations(basePath + ALLERGY_TRANSLATION_TSV))
                .bloodTransfusionTranslations(readBloodTransfusionTranslations(basePath + BLOOD_TRANSFUSION_TRANSLATION_TSV))
                .build();
    }

    @NotNull
    private static List<PrimaryTumorConfig> readPrimaryTumorConfigs(@NotNull String tsv) throws IOException {
        List<PrimaryTumorConfig> configs = CurationConfigFile.read(tsv, new PrimaryTumorConfigFactory());
        LOGGER.info(" Read {} primary tumor configs from {}", configs.size(), tsv);
        return configs;
    }

    @NotNull
    private static List<LesionLocationConfig> readLesionLocationConfigs(@NotNull String tsv) throws IOException {
        List<LesionLocationConfig> configs = CurationConfigFile.read(tsv, new LesionLocationConfigFactory());
        LOGGER.info(" Read {} lesion location configs from {}", configs.size(), tsv);
        return configs;
    }

    @NotNull
    private static List<OncologicalHistoryConfig> readOncologicalHistoryConfigs(@NotNull String tsv) throws IOException {
        List<OncologicalHistoryConfig> configs = CurationConfigFile.read(tsv, new OncologicalHistoryConfigFactory());
        LOGGER.info(" Read {} oncological history configs from {}", configs.size(), tsv);
        return configs;
    }

    @NotNull
    private static List<NonOncologicalHistoryConfig> readNonOncologicalHistoryConfigs(@NotNull String tsv) throws IOException {
        List<NonOncologicalHistoryConfig> configs = CurationConfigFile.read(tsv, new NonOncologicalHistoryConfigFactory());
        LOGGER.info(" Read {} non-oncological history configs from {}", configs.size(), tsv);
        return configs;
    }

    @NotNull
    private static List<ECGConfig> readECGConfigs(@NotNull String tsv) throws IOException {
        List<ECGConfig> configs = CurationConfigFile.read(tsv, new ECGConfigFactory());
        LOGGER.info(" Read {} ECG configs from {}", configs.size(), tsv);
        return configs;
    }

    @NotNull
    private static List<InfectionConfig> readInfectionConfigs(@NotNull String tsv) throws IOException {
        List<InfectionConfig> configs = CurationConfigFile.read(tsv, new InfectionConfigFactory());
        LOGGER.info(" Read {} infection configs from {}", configs.size(), tsv);
        return configs;
    }

    @NotNull
    private static List<CancerRelatedComplicationConfig> readCancerRelatedComplicationConfigs(@NotNull String tsv) throws IOException {
        List<CancerRelatedComplicationConfig> configs = CurationConfigFile.read(tsv, new CancerRelatedComplicationConfigFactory());
        LOGGER.info(" Read {} cancer related complication configs from {}", configs.size(), tsv);
        return configs;
    }

    @NotNull
    private static List<ToxicityConfig> readToxicityConfigs(@NotNull String tsv) throws IOException {
        List<ToxicityConfig> configs = CurationConfigFile.read(tsv, new ToxicityConfigFactory());
        LOGGER.info(" Read {} toxicity configs from {}", configs.size(), tsv);
        return configs;
    }

    @NotNull
    private static List<MolecularTestConfig> readMolecularTestConfigs(@NotNull  String tsv) throws IOException {
        List<MolecularTestConfig> configs = CurationConfigFile.read(tsv, new MolecularTestConfigFactory());
        LOGGER.info(" Read {} molecular test configs from {}", configs.size(), tsv);
        return configs;
    }

    @NotNull
    private static List<MedicationDosageConfig> readMedicationDosageConfigs(@NotNull String tsv) throws IOException {
        List<MedicationDosageConfig> configs = CurationConfigFile.read(tsv, new MedicationDosageConfigFactory());
        LOGGER.info(" Read {} medication dosage configs from {}", configs.size(), tsv);
        return configs;
    }

    @NotNull
    private static List<MedicationCategoryConfig> readMedicationCategoryConfigs(@NotNull String tsv) throws IOException {
        List<MedicationCategoryConfig> configs = CurationConfigFile.read(tsv, new MedicationCategoryConfigFactory());
        LOGGER.info(" Read {} medication category configs from {}", configs.size(), tsv);
        return configs;
    }

    @NotNull
    private static List<LaboratoryTranslation> readLaboratoryTranslations(@NotNull String tsv) throws IOException {
        List<LaboratoryTranslation> translations = TranslationFile.read(tsv, new LaboratoryTranslationFactory());
        LOGGER.info(" Read {} laboratory translations from {}", translations.size(), tsv);
        return translations;
    }

    @NotNull
    private static List<AllergyTranslation> readAllergyTranslations(@NotNull String tsv) throws IOException {
        List<AllergyTranslation> translations = TranslationFile.read(tsv, new AllergyTranslationFactory());
        LOGGER.info(" Read {} allergy translations from {}", translations.size(), tsv);
        return translations;
    }

    @NotNull
    private static List<BloodTransfusionTranslation> readBloodTransfusionTranslations(@NotNull String tsv) throws IOException {
        List<BloodTransfusionTranslation> translations = TranslationFile.read(tsv, new BloodTransfusionTranslationFactory());
        LOGGER.info(" Read {} blood transfusion translations from {}", translations.size(), tsv);
        return translations;
    }
}
