package com.hartwig.actin.molecular.orange.interpretation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import com.hartwig.actin.TestDataFactory;
import com.hartwig.actin.molecular.datamodel.ExperimentType;
import com.hartwig.actin.molecular.datamodel.MolecularEvidence;
import com.hartwig.actin.molecular.datamodel.MolecularRecord;
import com.hartwig.actin.molecular.orange.datamodel.ImmutableOrangeRecord;
import com.hartwig.actin.molecular.orange.datamodel.OrangeRecord;
import com.hartwig.actin.molecular.orange.datamodel.TestOrangeDataFactory;

import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class OrangeInterpreterTest {

    private static final double EPSILON = 1.0E-10;

    @Test
    public void canInterpretOrangeRecord() {
        MolecularRecord record = OrangeInterpreter.interpret(TestOrangeDataFactory.createProperTestMolecularRecord());

        assertEquals(TestDataFactory.TEST_SAMPLE, record.sampleId());
        assertEquals(ExperimentType.WGS, record.type());
        assertEquals(LocalDate.of(2022, 1, 20), record.date());
        assertTrue(record.hasReliableQuality());
        assertFalse(record.isMicrosatelliteUnstable());
        assertFalse(record.isHomologousRepairDeficient());
        assertEquals(8D, record.tumorMutationalBurden(), EPSILON);
        assertEquals(100, (int) record.tumorMutationalLoad());

        MolecularEvidence evidence = record.evidence();

        assertEquals(1, evidence.actinTrialEvidence().size());
        assertEquals(1, evidence.generalTrialEvidence().size());
        assertEquals(1, evidence.generalResponsiveEvidence().size());
        assertEquals(0, evidence.generalResistanceEvidence().size());
    }

    @Test
    public void canInterpretAllHomologousRepairStates() {
        MolecularRecord deficient = OrangeInterpreter.interpret(withHomologousRepairStatus(OrangeInterpreter.HOMOLOGOUS_REPAIR_DEFICIENT));
        assertTrue(deficient.isHomologousRepairDeficient());

        MolecularRecord proficient =
                OrangeInterpreter.interpret(withHomologousRepairStatus(OrangeInterpreter.HOMOLOGOUS_REPAIR_PROFICIENT));
        assertFalse(proficient.isHomologousRepairDeficient());

        MolecularRecord weird = OrangeInterpreter.interpret(withHomologousRepairStatus("not a valid status"));
        assertNull(weird.isHomologousRepairDeficient());
    }

    @NotNull
    private static OrangeRecord withHomologousRepairStatus(@NotNull String hrStatus) {
        return ImmutableOrangeRecord.builder()
                .from(TestOrangeDataFactory.createMinimalTestOrangeRecord())
                .homologousRepairStatus(hrStatus)
                .build();
    }

    @Test
    public void canInterpretAllMicrosatelliteInstabilityStates() {
        MolecularRecord unstable = OrangeInterpreter.interpret(withMicrosatelliteStatus(OrangeInterpreter.MICROSATELLITE_UNSTABLE));
        assertTrue(unstable.isMicrosatelliteUnstable());

        MolecularRecord stable = OrangeInterpreter.interpret(withMicrosatelliteStatus(OrangeInterpreter.MICROSATELLITE_STABLE));
        assertFalse(stable.isMicrosatelliteUnstable());

        MolecularRecord weird = OrangeInterpreter.interpret(withMicrosatelliteStatus("not a valid status"));
        assertNull(weird.isMicrosatelliteUnstable());
    }

    @NotNull
    private static OrangeRecord withMicrosatelliteStatus(@NotNull String microsatelliteStatus) {
        return ImmutableOrangeRecord.builder()
                .from(TestOrangeDataFactory.createMinimalTestOrangeRecord())
                .microsatelliteStabilityStatus(microsatelliteStatus)
                .build();
    }
}