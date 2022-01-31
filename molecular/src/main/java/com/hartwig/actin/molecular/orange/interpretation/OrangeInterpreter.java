package com.hartwig.actin.molecular.orange.interpretation;

import com.hartwig.actin.molecular.datamodel.ExperimentType;
import com.hartwig.actin.molecular.datamodel.ImmutableMolecularRecord;
import com.hartwig.actin.molecular.datamodel.MolecularRecord;
import com.hartwig.actin.molecular.orange.datamodel.OrangeRecord;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class OrangeInterpreter {

    private static final Logger LOGGER = LogManager.getLogger(OrangeInterpreter.class);

    static final String MICROSATELLITE_STABLE = "MSS";
    static final String MICROSATELLITE_UNSTABLE = "MSI";

    static final String HOMOLOGOUS_REPAIR_DEFICIENT = "HR_DEFICIENT";
    static final String HOMOLOGOUS_REPAIR_PROFICIENT = "HR_PROFICIENT";

    private OrangeInterpreter() {
    }

    @NotNull
    public static MolecularRecord interpret(@NotNull OrangeRecord record) {
        OrangeEventExtraction extraction = OrangeEventExtractor.extract(record);

        return ImmutableMolecularRecord.builder()
                .sampleId(record.sampleId())
                .type(ExperimentType.WGS)
                .date(record.date())
                .hasReliableQuality(record.hasReliableQuality())
                .mutations(extraction.mutations())
                .activatedGenes(extraction.activatedGenes())
                .inactivatedGenes(extraction.inactivatedGenes())
                .amplifiedGenes(extraction.amplifiedGenes())
                .wildtypeGenes(extraction.wildtypeGenes())
                .fusions(extraction.fusions())
                .isMicrosatelliteUnstable(isMSI(record.microsatelliteStabilityStatus()))
                .isHomologousRepairDeficient(isHRD(record.homologousRepairStatus()))
                .tumorMutationalBurden(record.tumorMutationalBurden())
                .tumorMutationalLoad(record.tumorMutationalLoad())
                .actinTreatmentEvidence(OrangeEvidenceFactory.createActinTreatmentEvidence(record.evidences()))
                .generalTrialSource("iClusion")
                .generalTrialEvidence(OrangeEvidenceFactory.createGeneralTrialEvidence(record.evidences()))
                .generalEvidenceSource("CKB")
                .generalResponsiveEvidence(OrangeEvidenceFactory.createGeneralResponsiveEvidence(record.evidences()))
                .generalResistanceEvidence(OrangeEvidenceFactory.createGeneralResistanceEvidence(record.evidences()))
                .build();
    }

    @Nullable
    private static Boolean isMSI(@NotNull String microsatelliteStatus) {
        if (microsatelliteStatus.equals(MICROSATELLITE_UNSTABLE)) {
            return true;
        } else if (microsatelliteStatus.equals(MICROSATELLITE_STABLE)) {
            return false;
        }

        LOGGER.warn("Cannot interpret microsatellite status '{}'", microsatelliteStatus);
        return null;
    }

    @Nullable
    private static Boolean isHRD(@NotNull String homologousRepairStatus) {
        if (homologousRepairStatus.equals(HOMOLOGOUS_REPAIR_DEFICIENT)) {
            return true;
        } else if (homologousRepairStatus.equals(HOMOLOGOUS_REPAIR_PROFICIENT)) {
            return false;
        }

        LOGGER.warn("Cannot interpret homologous repair status '{}'", homologousRepairStatus);
        return null;
    }
}
