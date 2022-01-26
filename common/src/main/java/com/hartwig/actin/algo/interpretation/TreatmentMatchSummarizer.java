package com.hartwig.actin.algo.interpretation;

import java.util.Set;

import com.google.common.collect.Sets;
import com.hartwig.actin.algo.datamodel.CohortEligibility;
import com.hartwig.actin.algo.datamodel.TreatmentMatch;
import com.hartwig.actin.algo.datamodel.TrialEligibility;
import com.hartwig.actin.algo.util.EligibilityDisplay;

import org.jetbrains.annotations.NotNull;

public final class TreatmentMatchSummarizer {

    private TreatmentMatchSummarizer() {
    }

    @NotNull
    public static TreatmentMatchSummary summarize(@NotNull TreatmentMatch treatmentMatch) {
        int eligibleTrialCount = 0;
        int cohortCount = 0;
        int eligibleCohortCount = 0;
        int eligibleOpenCohortCount = 0;

        Set<String> eligibleTrials = Sets.newHashSet();
        Set<String> eligibleOpenCohorts = Sets.newHashSet();
        for (TrialEligibility trial : treatmentMatch.trialMatches()) {
            // A trial without cohorts is considered a cohort on its own.
            boolean hasCohorts = !trial.cohorts().isEmpty();
            cohortCount += hasCohorts ? trial.cohorts().size() : 1;

            if (trial.overallEvaluation().isPass()) {
                boolean hasNoCohortOrAtLeastOneEligible = !hasCohorts;
                for (CohortEligibility cohort : trial.cohorts()) {
                    if (cohort.overallEvaluation().isPass()) {
                        eligibleCohortCount += 1;
                        hasNoCohortOrAtLeastOneEligible = true;
                        if (cohort.metadata().open()) {
                            eligibleOpenCohortCount += 1;
                            eligibleOpenCohorts.add(EligibilityDisplay.cohortName(trial, cohort));
                        }
                    }
                }

                if (hasNoCohortOrAtLeastOneEligible) {
                    eligibleTrialCount += 1;
                    eligibleTrials.add(EligibilityDisplay.trialName(trial));

                    // Assume trials without specific cohorts are always open (or they should otherwise not exist
                    if (!hasCohorts) {
                        eligibleOpenCohortCount += 1;
                    }
                }
            }
        }

        return ImmutableTreatmentMatchSummary.builder()
                .trialCount(treatmentMatch.trialMatches().size())
                .eligibleTrialCount(eligibleTrialCount)
                .cohortCount(cohortCount)
                .eligibleCohortCount(eligibleCohortCount)
                .eligibleOpenCohortCount(eligibleOpenCohortCount)
                .build();
    }
}
