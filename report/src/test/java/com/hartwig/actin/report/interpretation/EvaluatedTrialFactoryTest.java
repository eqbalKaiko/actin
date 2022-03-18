package com.hartwig.actin.report.interpretation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Objects;

import com.google.common.collect.Sets;
import com.hartwig.actin.algo.datamodel.TestTreatmentMatchFactory;
import com.hartwig.actin.algo.datamodel.TreatmentMatch;
import com.hartwig.actin.molecular.datamodel.ImmutableMolecularEvidence;
import com.hartwig.actin.molecular.datamodel.MolecularEvidence;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

public class EvaluatedTrialFactoryTest {

    @Test
    public void canCreateEvaluatedTrials() {
        TreatmentMatch treatmentMatch = TestTreatmentMatchFactory.createProperTreatmentMatch();
        List<EvaluatedTrial> trials = EvaluatedTrialFactory.create(treatmentMatch, Sets.newHashSet());

        assertEquals(4, trials.size());

        EvaluatedTrial cohortA = findByTrialAndCohort(trials, "TEST-TRIAL-1", "Cohort A");
        assertFalse(cohortA.hasMolecularEvidence());
        assertFalse(cohortA.isPotentiallyEligible());
        assertTrue(cohortA.isOpen());
        assertTrue(cohortA.warnings().isEmpty());
        assertFalse(cohortA.fails().isEmpty());

        EvaluatedTrial cohortB = findByTrialAndCohort(trials, "TEST-TRIAL-1", "Cohort B");
        assertFalse(cohortB.hasMolecularEvidence());
        assertTrue(cohortB.isPotentiallyEligible());
        assertTrue(cohortB.isOpen());
        assertTrue(cohortB.warnings().isEmpty());
        assertTrue(cohortB.fails().isEmpty());

        EvaluatedTrial cohortC = findByTrialAndCohort(trials, "TEST-TRIAL-1", "Cohort C");
        assertFalse(cohortC.hasMolecularEvidence());
        assertFalse(cohortC.isPotentiallyEligible());
        assertTrue(cohortC.isOpen());
        assertTrue(cohortC.warnings().isEmpty());
        assertFalse(cohortC.fails().isEmpty());

        EvaluatedTrial trial2 = findByTrialAndCohort(trials, "TEST-TRIAL-2", null);
        assertFalse(trial2.hasMolecularEvidence());
        assertFalse(trial2.isPotentiallyEligible());
        assertTrue(trial2.isOpen());
        assertFalse(trial2.warnings().isEmpty());
        assertFalse(trial2.fails().isEmpty());
    }

    @Test
    public void canAnnotateWithMolecularEvidence() {
        TreatmentMatch treatmentMatch = TestTreatmentMatchFactory.createProperTreatmentMatch();
        MolecularEvidence evidence = ImmutableMolecularEvidence.builder().event("some event").treatment("TEST-TRIAL-1").build();

        List<EvaluatedTrial> trials = EvaluatedTrialFactory.create(treatmentMatch, Sets.newHashSet(evidence));

        assertTrue(findByTrialAndCohort(trials, "TEST-TRIAL-1", "Cohort A").hasMolecularEvidence());
        assertTrue(findByTrialAndCohort(trials, "TEST-TRIAL-1", "Cohort B").hasMolecularEvidence());
        assertTrue(findByTrialAndCohort(trials, "TEST-TRIAL-1", "Cohort C").hasMolecularEvidence());
        assertFalse(findByTrialAndCohort(trials, "TEST-TRIAL-2", null).hasMolecularEvidence());
    }

    @NotNull
    private static EvaluatedTrial findByTrialAndCohort(@NotNull List<EvaluatedTrial> trials, @NotNull String trialToFind,
            @Nullable String cohortToFind) {
        for (EvaluatedTrial trial : trials) {
            if (trial.acronym().equals(trialToFind) && Objects.equals(trial.cohort(), cohortToFind)) {
                return trial;
            }
        }

        throw new IllegalStateException("Could not find trial for cohort: " + cohortToFind);
    }
}