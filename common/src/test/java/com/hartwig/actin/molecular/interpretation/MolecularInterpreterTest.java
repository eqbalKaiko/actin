package com.hartwig.actin.molecular.interpretation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.hartwig.actin.molecular.datamodel.EvidenceDirection;
import com.hartwig.actin.molecular.datamodel.EvidenceLevel;
import com.hartwig.actin.molecular.datamodel.ImmutableMolecularRecord;
import com.hartwig.actin.molecular.datamodel.ImmutableMolecularTreatmentEvidence;
import com.hartwig.actin.molecular.datamodel.MolecularRecord;
import com.hartwig.actin.molecular.datamodel.MolecularTreatmentEvidence;
import com.hartwig.actin.molecular.datamodel.TestMolecularDataFactory;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class MolecularInterpreterTest {

    @Test
    public void canInterpretMolecularRecord() {
        MolecularRecord record = recordWithEvidence(createTestEvidences());

        MolecularInterpretation interpretation = MolecularInterpreter.interpret(record);

        Set<String> responsiveEvidence = interpretation.ckbApplicableResponsiveEvents();
        assertEquals(1, responsiveEvidence.size());
        assertTrue(responsiveEvidence.contains("Event 1"));

        Set<String> resistanceEvents = interpretation.ckbApplicableResistanceEvents();
        assertEquals(1, resistanceEvents.size());
        assertTrue(resistanceEvents.contains("Event 4 (Treatment 1)"));
    }

    @NotNull
    private static List<MolecularTreatmentEvidence> createTestEvidences() {
        List<MolecularTreatmentEvidence> evidences = Lists.newArrayList();

        ImmutableMolecularTreatmentEvidence.Builder ckbBuilder = ImmutableMolecularTreatmentEvidence.builder().addSources("CKB");
        // Should be included, all good.
        evidences.add(ckbBuilder.treatment("Treatment 1")
                .direction(EvidenceDirection.RESPONSIVE)
                .level(EvidenceLevel.A)
                .onLabel(true)
                .genomicEvent("Event 1")
                .build());

        // Should be filtered out since it is C-level off-label evidence.
        evidences.add(ckbBuilder.treatment("Treatment 2")
                .direction(EvidenceDirection.RESPONSIVE)
                .level(EvidenceLevel.C)
                .onLabel(false)
                .genomicEvent("Event 2")
                .build());

        // Should be filtered out as it starts with CDKN2A.
        evidences.add(ckbBuilder.treatment("Treatment 4")
                .direction(EvidenceDirection.RESPONSIVE)
                .level(EvidenceLevel.A)
                .onLabel(true)
                .genomicEvent("CDKN2A loss")
                .build());

        // Should be filtered out as it is VEGFA amp.
        evidences.add(ckbBuilder.treatment("Treatment 2")
                .direction(EvidenceDirection.RESPONSIVE)
                .level(EvidenceLevel.A)
                .onLabel(true)
                .genomicEvent("VEGFA full gain")
                .build());

        // Should be filtered since there is no responsive evidence for treatment 3
        evidences.add(ckbBuilder.treatment("Treatment 3")
                .direction(EvidenceDirection.RESISTANT)
                .level(EvidenceLevel.B)
                .onLabel(false)
                .genomicEvent("Event 3")
                .build());

        // Should be included, all good.
        evidences.add(ckbBuilder.treatment("Treatment 1")
                .direction(EvidenceDirection.RESISTANT)
                .level(EvidenceLevel.A)
                .onLabel(false)
                .genomicEvent("Event 4")
                .build());

        // Should be filtered since evidence level is not high enough
        evidences.add(ckbBuilder.treatment("Treatment 1")
                .direction(EvidenceDirection.RESISTANT)
                .level(EvidenceLevel.B)
                .onLabel(false)
                .genomicEvent("Event 5")
                .build());

        return evidences;
    }

    @NotNull
    private static MolecularRecord recordWithEvidence(@NotNull List<MolecularTreatmentEvidence> evidences) {
        return ImmutableMolecularRecord.builder()
                .from(TestMolecularDataFactory.createMinimalTestMolecularRecord())
                .evidences(evidences)
                .build();
    }
}