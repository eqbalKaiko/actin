CREATE OR REPLACE VIEW trialEvaluation
AS (
SELECT  referenceDate, referenceDateIsLive, sampleId, trialMatch.code AS trialId, trial.acronym AS trialAcronym,
        IF(trial.id IN (SELECT trialId FROM cohort),1,0) AS trialHasCohorts, trialMatch.isEligible AS isEligibleTrial,
        cohortMatch.code AS cohortId, description AS cohortDescription, cohortMatch.open AS cohortOpen, blacklist AS cohortBlacklist,
        cohortMatch.isEligible AS isEligibleCohort,
        eligibility AS eligibilityRule, result, passSpecificMessages, passGeneralMessages, warnSpecificMessages, warnGeneralMessages,
        undeterminedSpecificMessages, undeterminedGeneralMessages, failSpecificMessages, failGeneralMessages
FROM evaluation
INNER JOIN trialMatch ON trialMatch.id = evaluation.trialMatchId
INNER JOIN treatmentMatch ON treatmentMatch.id = trialMatch.treatmentMatchId
INNER JOIN trial ON trial.code = trialMatch.code
LEFT JOIN cohortMatch ON trialMatch.id = cohortMatch.trialMatchId AND cohortMatch.Id = evaluation.cohortMatchId
ORDER BY sampleId
);

CREATE OR REPLACE VIEW criteriaMapping
AS (
SELECT  trial.code AS trialId, acronym AS trialAcronym, cohort.code AS cohortId, description AS cohortDescription,
        reference.code AS criterionCode, text AS criterionText, display AS eligibilityRule
FROM eligibilityReference
INNER JOIN (SELECT DISTINCT id, trialId, cohortId, display FROM eligibility) AS a ON a.id = eligibilityReference.eligibilityId
INNER JOIN reference ON eligibilityReference.referenceId = reference.Id
INNER JOIN trial ON trial.id = a.trialId
LEFT JOIN cohort ON cohort.id = a.cohortId
ORDER BY trialId
);