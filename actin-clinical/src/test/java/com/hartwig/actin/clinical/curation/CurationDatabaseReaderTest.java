package com.hartwig.actin.clinical.curation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import com.google.common.io.Resources;
import com.hartwig.actin.clinical.curation.config.CancerRelatedComplicationConfig;
import com.hartwig.actin.clinical.curation.config.CurationConfig;
import com.hartwig.actin.clinical.curation.config.ECGConfig;
import com.hartwig.actin.clinical.curation.config.LesionLocationConfig;
import com.hartwig.actin.clinical.curation.config.MedicationDosageConfig;
import com.hartwig.actin.clinical.curation.config.NonOncologicalHistoryConfig;
import com.hartwig.actin.clinical.curation.config.OncologicalHistoryConfig;
import com.hartwig.actin.clinical.curation.config.PrimaryTumorConfig;
import com.hartwig.actin.clinical.curation.config.ToxicityConfig;
import com.hartwig.actin.clinical.curation.translation.AllergyTranslation;
import com.hartwig.actin.clinical.curation.translation.LaboratoryTranslation;
import com.hartwig.actin.datamodel.clinical.PriorSecondPrimary;
import com.hartwig.actin.datamodel.clinical.PriorTumorTreatment;

import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class CurationDatabaseReaderTest {

    private static final String CURATION_DIRECTORY = Resources.getResource("curation").getPath();

    @Test
    public void canReadFromTestDirectory() throws IOException {
        CurationDatabase database = CurationDatabaseReader.read(CURATION_DIRECTORY);

        assertPrimaryTumorConfigs(database.primaryTumorConfigs());
        assertLesionLocationConfigs(database.lesionLocationConfigs());
        assertOncologicalHistoryConfigs(database.oncologicalHistoryConfigs());
        assertNonOncologicalHistoryConfigs(database.nonOncologicalHistoryConfigs());
        assertECGConfigs(database.ecgConfigs());
        assertCancerRelatedComplicationConfigs(database.cancerRelatedComplicationConfigs());
        assertToxicityConfigs(database.toxicityConfigs());
        assertMedicationDosageConfigs(database.medicationDosageConfigs());

        assertLaboratoryTranslations(database.laboratoryTranslations());
        assertAllergyTranslations(database.allergyTranslations());
    }

    private static void assertPrimaryTumorConfigs(@NotNull List<PrimaryTumorConfig> configs) {
        assertEquals(1, configs.size());

        PrimaryTumorConfig config = configs.get(0);
        assertEquals("Unknown | Carcinoma", config.input());
        assertEquals("Unknown", config.primaryTumorLocation());
        assertEquals("CUP", config.primaryTumorSubLocation());
        assertEquals("Carcinoma", config.primaryTumorType());
        assertEquals(Strings.EMPTY, config.primaryTumorSubType());
        assertEquals(Strings.EMPTY, config.primaryTumorExtraDetails());
        assertEquals(1, config.doids().size());
        assertTrue(config.doids().contains("299"));
    }

    private static void assertLesionLocationConfigs(@NotNull List<LesionLocationConfig> configs) {
        assertEquals(1, configs.size());

        LesionLocationConfig config = configs.get(0);
        assertEquals("Lever", config.input());
        assertEquals("Liver", config.location());
    }

    private static void assertOncologicalHistoryConfigs(@NotNull List<OncologicalHistoryConfig> configs) {
        assertEquals(2, configs.size());

        OncologicalHistoryConfig config1 = find(configs, "Capecitabine/Oxaliplatin 2020");
        assertFalse(config1.ignore());

        PriorTumorTreatment curated1 = (PriorTumorTreatment) config1.curatedObject();
        assertEquals("Capecitabine+Oxaliplatin", curated1.name());
        assertEquals(2020, (int) curated1.year());
        assertEquals("chemotherapy", curated1.category());
        assertTrue(curated1.isSystemic());
        assertEquals("antimetabolite,platinum", curated1.chemoType());
        assertNull(curated1.immunoType());
        assertNull(curated1.targetedType());
        assertNull(curated1.hormoneType());
        assertNull(curated1.stemCellTransType());

        OncologicalHistoryConfig config2 = find(configs, "Breast 2018");
        assertFalse(config2.ignore());

        PriorSecondPrimary curated2 = (PriorSecondPrimary) config2.curatedObject();
        assertEquals("Breast", curated2.tumorLocation());
        assertEquals(Strings.EMPTY, curated2.tumorSubLocation());
        assertEquals("Carcinoma", curated2.tumorType());
        assertEquals(Strings.EMPTY, curated2.tumorSubType());
        assertTrue(curated2.doids().isEmpty());
        assertEquals(2018, (int) curated2.diagnosedYear());
        assertTrue(curated2.isSecondPrimaryActive());
    }

    private static void assertNonOncologicalHistoryConfigs(@NotNull List<NonOncologicalHistoryConfig> configs) {
        assertEquals(2, configs.size());
        NonOncologicalHistoryConfig config1 = find(configs, "Levercirrose/ sarcoidose");
        assertFalse(config1.ignore());

        assertEquals("Liver cirrhosis and sarcoidosis", config1.curated().name());
        assertEquals("Liver disease", config1.curated().category());
        assertEquals(2, config1.curated().doids().size());
        assertTrue(config1.curated().doids().contains("5082"));
        assertTrue(config1.curated().doids().contains("11335"));

        NonOncologicalHistoryConfig config2 = find(configs, "NA");
        assertTrue(config2.ignore());
        assertNull(config2.curated());
    }

    private static void assertECGConfigs(@NotNull List<ECGConfig> configs) {
        assertEquals(1, configs.size());

        ECGConfig config = configs.get(0);
        assertEquals("Sinus Tachycardia", config.input());
        assertEquals("Sinus tachycardia", config.interpretation());
    }

    private static void assertCancerRelatedComplicationConfigs(@NotNull List<CancerRelatedComplicationConfig> configs) {
        assertEquals(1, configs.size());

        CancerRelatedComplicationConfig config = configs.get(0);
        assertEquals("something", config.input());
        assertEquals("curated something", config.name());
    }

    private static void assertToxicityConfigs(@NotNull List<ToxicityConfig> configs) {
        assertEquals(1, configs.size());

        ToxicityConfig config = configs.get(0);
        assertEquals("Neuropathy GR3", config.input());
        assertEquals("Neuropathy", config.name());
        assertEquals(3, (int) config.grade());
    }

    private static void assertMedicationDosageConfigs(@NotNull List<MedicationDosageConfig> configs) {
        assertEquals(2, configs.size());

        MedicationDosageConfig config1 = find(configs, "once per day 50 mg");
        assertEquals("50", config1.dosage());
        assertEquals("mg", config1.unit());
        assertEquals("day", config1.frequencyUnit());
        assertFalse(config1.ifNeeded());

        MedicationDosageConfig config2 = find(configs, "empty");
        assertTrue(config2.dosage().isEmpty());
        assertTrue(config2.unit().isEmpty());
        assertTrue(config2.frequencyUnit().isEmpty());
        assertNull(config2.ifNeeded());
    }

    @NotNull
    private static <T extends CurationConfig> T find(@NotNull List<T> configs, @NotNull String input) {
        for (T config : configs) {
            if (config.input().equals(input)) {
                return config;
            }
        }

        throw new IllegalStateException("Could not find input '" + input + "' in configs");
    }

    private static void assertLaboratoryTranslations(@NotNull List<LaboratoryTranslation> translations) {
        assertEquals(1, translations.size());

        LaboratoryTranslation translation = translations.get(0);
        assertEquals("AC", translation.code());
        assertEquals("AC2", translation.translatedCode());
        assertEquals("ACTH", translation.name());
        assertEquals("Adrenocorticotropic hormone", translation.translatedName());
    }

    private static void assertAllergyTranslations(@NotNull List<AllergyTranslation> translations) {
        assertEquals(1, translations.size());

        AllergyTranslation translation = translations.get(0);
        assertEquals("SIMVASTATINE", translation.name());
        assertEquals("Simvastatin", translation.translatedName());
    }
}