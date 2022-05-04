package com.hartwig.actin.molecular.datamodel;

import java.time.LocalDate;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hartwig.actin.TestDataFactory;
import com.hartwig.actin.molecular.datamodel.characteristics.ImmutableMolecularCharacteristics;
import com.hartwig.actin.molecular.datamodel.characteristics.ImmutablePredictedTumorOrigin;
import com.hartwig.actin.molecular.datamodel.characteristics.MolecularCharacteristics;
import com.hartwig.actin.molecular.datamodel.driver.DriverLikelihood;
import com.hartwig.actin.molecular.datamodel.driver.FusionDriverType;
import com.hartwig.actin.molecular.datamodel.driver.ImmutableAmplification;
import com.hartwig.actin.molecular.datamodel.driver.ImmutableDisruption;
import com.hartwig.actin.molecular.datamodel.driver.ImmutableFusion;
import com.hartwig.actin.molecular.datamodel.driver.ImmutableHomozygousDisruption;
import com.hartwig.actin.molecular.datamodel.driver.ImmutableLoss;
import com.hartwig.actin.molecular.datamodel.driver.ImmutableMolecularDrivers;
import com.hartwig.actin.molecular.datamodel.driver.ImmutableVariant;
import com.hartwig.actin.molecular.datamodel.driver.ImmutableVirus;
import com.hartwig.actin.molecular.datamodel.driver.MolecularDrivers;
import com.hartwig.actin.molecular.datamodel.driver.VariantDriverType;
import com.hartwig.actin.molecular.datamodel.evidence.EvidenceEntry;
import com.hartwig.actin.molecular.datamodel.evidence.ImmutableEvidenceEntry;
import com.hartwig.actin.molecular.datamodel.evidence.ImmutableMolecularEvidence;
import com.hartwig.actin.molecular.datamodel.evidence.MolecularEvidence;
import com.hartwig.actin.molecular.datamodel.mapping.GeneMutation;
import com.hartwig.actin.molecular.datamodel.mapping.ImmutableFusionGene;
import com.hartwig.actin.molecular.datamodel.mapping.ImmutableGeneMutation;
import com.hartwig.actin.molecular.datamodel.mapping.ImmutableMappedActinEvents;
import com.hartwig.actin.molecular.datamodel.mapping.MappedActinEvents;
import com.hartwig.actin.molecular.datamodel.pharmaco.ImmutableHaplotype;
import com.hartwig.actin.molecular.datamodel.pharmaco.ImmutablePharmacoEntry;
import com.hartwig.actin.molecular.datamodel.pharmaco.PharmacoEntry;

import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;

public final class TestMolecularDataFactory {

    private static final LocalDate TODAY = LocalDate.now();

    private static final int DAYS_SINCE_MOLECULAR_ANALYSIS = 5;

    private TestMolecularDataFactory() {
    }

    @NotNull
    public static MolecularRecord createMinimalTestMolecularRecord() {
        return ImmutableMolecularRecord.builder()
                .sampleId(TestDataFactory.TEST_SAMPLE)
                .type(ExperimentType.WGS)
                .hasReliableQuality(true)
                .characteristics(ImmutableMolecularCharacteristics.builder().build())
                .drivers(ImmutableMolecularDrivers.builder().build())
                .evidence(createMinimalTestEvidence())
                .mappedEvents(ImmutableMappedActinEvents.builder().build())
                .build();
    }

    @NotNull
    public static MolecularRecord createProperTestMolecularRecord() {
        return ImmutableMolecularRecord.builder()
                .from(createMinimalTestMolecularRecord())
                .date(TODAY.minusDays(DAYS_SINCE_MOLECULAR_ANALYSIS))
                .characteristics(createTestCharacteristics())
                .drivers(createTestDrivers())
                .pharmaco(createTestPharmaco())
                .evidence(createTestEvidence())
                .mappedEvents(createTestMappedEvents())
                .build();
    }

    @NotNull
    public static MolecularRecord createExhaustiveTestMolecularRecord() {
        return ImmutableMolecularRecord.builder()
                .from(createProperTestMolecularRecord())
                .drivers(createExhaustiveTestDrivers())
                .mappedEvents(createExhaustiveTestMappedEvents())
                .build();
    }

    @NotNull
    private static MolecularEvidence createMinimalTestEvidence() {
        return ImmutableMolecularEvidence.builder()
                .actinSource(Strings.EMPTY)
                .externalTrialSource(Strings.EMPTY)
                .evidenceSource(Strings.EMPTY)
                .build();
    }

    @NotNull
    private static MolecularCharacteristics createTestCharacteristics() {
        return ImmutableMolecularCharacteristics.builder()
                .purity(0.98)
                .hasReliablePurity(true)
                .predictedTumorOrigin(ImmutablePredictedTumorOrigin.builder().tumorType("Melanoma").likelihood(0.996).build())
                .isMicrosatelliteUnstable(false)
                .isHomologousRepairDeficient(false)
                .tumorMutationalBurden(13.71)
                .tumorMutationalLoad(185)
                .build();
    }

    @NotNull
    private static MolecularDrivers createTestDrivers() {
        return ImmutableMolecularDrivers.builder()
                .addVariants(ImmutableVariant.builder()
                        .event("BRAF V600E")
                        .driverLikelihood(DriverLikelihood.HIGH)
                        .gene("BRAF")
                        .impact("p.V600E")
                        .variantCopyNumber(4.1)
                        .totalCopyNumber(6.0)
                        .driverType(VariantDriverType.HOTSPOT)
                        .clonalLikelihood(1.0)
                        .build())
                .addLosses(ImmutableLoss.builder()
                        .event("PTEN del")
                        .driverLikelihood(DriverLikelihood.HIGH)
                        .gene("PTEN")
                        .isPartial(true)
                        .build())
                .build();
    }

    @NotNull
    private static Set<PharmacoEntry> createTestPharmaco() {
        return Sets.newHashSet(ImmutablePharmacoEntry.builder()
                .gene("DPYD")
                .addHaplotypes(ImmutableHaplotype.builder().name("1* HOM").function("Normal function").build())
                .build());
    }

    @NotNull
    private static MolecularEvidence createTestEvidence() {
        return ImmutableMolecularEvidence.builder()
                .actinSource("local")
                .actinTrials(createTestActinTrials())
                .externalTrialSource("external")
                .externalTrials(createTestExternalTrials())
                .evidenceSource("general")
                .approvedEvidence(createTestApprovedEvidence())
                .onLabelExperimentalEvidence(createTestOnLabelExperimentalEvidence())
                .offLabelExperimentalEvidence(createTestOffLabelExperimentalEvidence())
                .preClinicalEvidence(createTestPreClinicalEvidence())
                .knownResistanceEvidence(createTestKnownResistanceEvidence())
                .suspectResistanceEvidence(createTestSuspectResistanceEvidence())
                .build();
    }

    @NotNull
    private static Set<EvidenceEntry> createTestActinTrials() {
        Set<EvidenceEntry> result = Sets.newHashSet();

        result.add(ImmutableEvidenceEntry.builder().event("BRAF V600E").treatment("Trial 1").build());
        result.add(ImmutableEvidenceEntry.builder().event("High TML").treatment("Trial 1").build());

        return result;
    }

    @NotNull
    private static Set<EvidenceEntry> createTestExternalTrials() {
        Set<EvidenceEntry> result = Sets.newHashSet();

        result.add(ImmutableEvidenceEntry.builder().event("BRAF V600E").treatment("Trial 1").build());
        result.add(ImmutableEvidenceEntry.builder().event("High TML").treatment("Trial 1").build());

        return result;
    }

    @NotNull
    private static Set<EvidenceEntry> createTestApprovedEvidence() {
        Set<EvidenceEntry> result = Sets.newHashSet();

        result.add(ImmutableEvidenceEntry.builder().event("BRAF V600E").treatment("Vemurafenib").build());
        result.add(ImmutableEvidenceEntry.builder().event("BRAF V600E").treatment("Dabrafenib").build());
        result.add(ImmutableEvidenceEntry.builder().event("High TML").treatment("Nivolumab").build());

        return result;
    }

    @NotNull
    private static Set<EvidenceEntry> createTestOnLabelExperimentalEvidence() {
        Set<EvidenceEntry> result = Sets.newHashSet();

        result.add(ImmutableEvidenceEntry.builder().event("High TML").treatment("Pembrolizumab").build());

        return result;
    }

    @NotNull
    private static Set<EvidenceEntry> createTestOffLabelExperimentalEvidence() {
        Set<EvidenceEntry> result = Sets.newHashSet();

        result.add(ImmutableEvidenceEntry.builder().event("BRAF V600E").treatment("Trametinib").build());

        return result;
    }

    @NotNull
    private static Set<EvidenceEntry> createTestPreClinicalEvidence() {
        Set<EvidenceEntry> result = Sets.newHashSet();

        result.add(ImmutableEvidenceEntry.builder().event("BRAF V600E").treatment("Pre-clinical treatment").build());

        return result;
    }

    @NotNull
    private static Set<EvidenceEntry> createTestKnownResistanceEvidence() {
        Set<EvidenceEntry> result = Sets.newHashSet();

        result.add(ImmutableEvidenceEntry.builder().event("BRAF V600E").treatment("Erlotinib").build());

        return result;
    }

    @NotNull
    private static Set<EvidenceEntry> createTestSuspectResistanceEvidence() {
        Set<EvidenceEntry> result = Sets.newHashSet();

        result.add(ImmutableEvidenceEntry.builder().event("BRAF V600E").treatment("Some treatment").build());

        return result;
    }

    @NotNull
    private static MappedActinEvents createTestMappedEvents() {
        return ImmutableMappedActinEvents.builder()
                .mutations(createTestMutations())
                .activatedGenes(Sets.newHashSet("BRAF"))
                .inactivatedGenes(Sets.newHashSet("PTEN", "CDKN2A"))
                .amplifiedGenes(Sets.newHashSet())
                .wildtypeGenes(Sets.newHashSet())
                .fusions(Lists.newArrayList())
                .build();
    }

    @NotNull
    private static Set<GeneMutation> createTestMutations() {
        Set<GeneMutation> mutations = Sets.newHashSet();

        mutations.add(ImmutableGeneMutation.builder().gene("BRAF").mutation("V600E").build());

        return mutations;
    }

    @NotNull
    private static MolecularDrivers createExhaustiveTestDrivers() {
        return ImmutableMolecularDrivers.builder()
                .from(createTestDrivers())
                .addAmplifications(ImmutableAmplification.builder()
                        .event("MYC amp")
                        .driverLikelihood(DriverLikelihood.HIGH)
                        .gene("MYC")
                        .copies(38)
                        .isPartial(false)
                        .build())
                .addHomozygousDisruptions(ImmutableHomozygousDisruption.builder()
                        .event("PTEN disruption")
                        .driverLikelihood(DriverLikelihood.HIGH)
                        .gene("PTEN")
                        .build())
                .addDisruptions(ImmutableDisruption.builder()
                        .event(Strings.EMPTY)
                        .driverLikelihood(DriverLikelihood.LOW)
                        .gene("PTEN")
                        .type("DEL")
                        .junctionCopyNumber(1.1)
                        .undisruptedCopyNumber(1.8)
                        .range("Intron 1 downstream")
                        .build())
                .addFusions(ImmutableFusion.builder()
                        .event("EML4-ALK fusion")
                        .driverLikelihood(DriverLikelihood.HIGH)
                        .fiveGene("EML4")
                        .threeGene("ALK")
                        .details("Exon 2 - Exon 4")
                        .driverType(FusionDriverType.KNOWN)
                        .build())
                .addViruses(ImmutableVirus.builder()
                        .event("HPV positive")
                        .driverLikelihood(DriverLikelihood.HIGH)
                        .name("Human papillomavirus type 16d")
                        .integrations(3)
                        .build())
                .build();
    }

    @NotNull
    private static MappedActinEvents createExhaustiveTestMappedEvents() {
        return ImmutableMappedActinEvents.builder()
                .from(createTestMappedEvents())
                .amplifiedGenes(Sets.newHashSet("AMP"))
                .wildtypeGenes(Sets.newHashSet("WILD"))
                .fusions(Lists.newArrayList(ImmutableFusionGene.builder().fiveGene("FIVE").threeGene("THREE").build()))
                .build();
    }
}
