package com.hartwig.actin.treatment.datamodel;

public enum EligibilityRule {
    // Composite rules combining other rules.
    AND,
    OR,
    NOT,
    WARN_ON_PASS,

    // Rules related to general patient characteristics
    IS_AT_LEAST_X_YEARS_OLD,
    IS_MALE,
    HAS_WHO_STATUS_OF_AT_MOST_X,
    IS_ABLE_AND_WILLING_TO_GIVE_ADEQUATE_INFORMED_CONSENT,
    IS_INVOLVED_IN_STUDY_PROCEDURES,
    IS_PARTICIPATING_IN_ANOTHER_TRIAL,
    HAS_LIFE_EXPECTANCY_OF_AT_LEAST_X_WEEKS,
    HAS_LIFE_EXPECTANCY_OF_AT_LEAST_X_MONTHS,
    PATIENT_IS_TREATED_IN_HOSPITAL_X,
    PATIENT_WILL_BE_PARTICIPATING_IN_COUNTRY_X,

    // Rules related to tumor and lesion locations
    PRIMARY_TUMOR_LOCATION_BELONGS_TO_DOID_X,
    HAS_ADVANCED_CANCER,
    HAS_METASTATIC_CANCER,
    HAS_LIVER_METASTASES,
    HAS_KNOWN_CNS_METASTASES,
    HAS_KNOWN_ACTIVE_CNS_METASTASES,
    HAS_KNOWN_SYMPTOMATIC_CNS_METASTASES,
    HAS_KNOWN_BRAIN_METASTASES,
    HAS_KNOWN_ACTIVE_BRAIN_METASTASES,
    HAS_KNOWN_SYMPTOMATIC_BRAIN_METASTASES,
    HAS_BONE_METASTASES,
    HAS_MEASURABLE_DISEASE_RECIST,
    HAS_BIOPSY_AMENABLE_LESION,
    HAS_COLLECTED_TUMOR_BIOPSY_WITHIN_X_MONTHS_BEFORE_IC,

    // Rules related to previous cancer treatments or previous primary tumors
    HAS_EXHAUSTED_SOC_TREATMENTS,
    HAS_DECLINED_SOC_TREATMENTS,
    HAS_HISTORY_OF_SECOND_MALIGNANCY,
    HAS_HISTORY_OF_SECOND_MALIGNANCY_BELONGING_TO_DOID_X,
    HAS_HISTORY_OF_SECOND_MALIGNANCY_BELONGING_TO_DOID_X_CURRENTLY_INACTIVE,
    EVERY_SECOND_MALIGNANCY_HAS_BEEN_CURED_SINCE_X_YEARS,
    HAS_HAD_AT_MOST_X_SYSTEMIC_TREATMENT_LINES,
    HAS_HAD_DRUG_NAME_X_TREATMENT,
    HAS_HAD_CATEGORY_X_TREATMENT,
    HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPE_Y,
    HAS_HAD_CATEGORY_X_TREATMENT_AND_AT_MOST_Y_LINES,
    HAS_HAD_FLUOROPYRIMIDINE_TREATMENT,
    HAS_HAD_MAX_X_NR_ANTI_PD_L1_OR_PD_1_IMMUNOTHERAPIES,
    IS_ELIGIBLE_FOR_ON_LABEL_DRUG_X,

    // Rules related to molecular results
    MOLECULAR_RESULTS_MUST_BE_AVAILABLE,
    MOLECULAR_RESULTS_MUST_BE_AVAILABLE_FOR_GENE_X,
    ACTIVATION_OR_AMPLIFICATION_OF_GENE_X,
    INACTIVATION_OF_GENE_X,
    ACTIVATING_MUTATION_IN_GENE_X,
    MUTATION_IN_GENE_X_OF_TYPE_Y,
    AMPLIFICATION_OF_GENE_X,
    DELETION_OF_GENE_X,
    ACTIVATING_FUSION_IN_GENE_X,
    SPECIFIC_FUSION_X,
    OVEREXPRESSION_OF_GENE_X,
    WILDTYPE_OF_GENE_X,
    MSI_SIGNATURE,
    HRD_SIGNATURE,
    TMB_OF_AT_LEAST_X,
    TML_OF_AT_LEAST_X,
    TML_OF_AT_MOST_X,

    // Rules related to recent laboratory measurements
    HAS_LEUKOCYTES_ABS_OF_AT_LEAST_X,
    HAS_LEUKOCYTES_ABS_LLN_OF_AT_LEAST_X,
    HAS_NEUTROPHILS_ABS_OF_AT_LEAST_X,
    HAS_THROMBOCYTES_ABS_OF_AT_LEAST_X,
    HAS_LYMPHOCYTES_ABS_OF_AT_LEAST_X,
    HAS_ALBUMIN_G_PER_DL_OF_AT_LEAST_X,
    HAS_ALBUMIN_LLN_OF_AT_LEAST_X,
    HAS_HEMOGLOBIN_G_PER_DL_OF_AT_LEAST_X,
    HAS_HEMOGLOBIN_MMOL_PER_L_OF_AT_LEAST_X,
    HAS_CREATININE_ULN_OF_AT_MOST_X,
    HAS_EGFR_CKD_EPI_OF_AT_LEAST_X,
    HAS_EGFR_MDRD_OF_AT_LEAST_X,
    HAS_CREATININE_CLEARANCE_CG_OF_AT_LEAST_X,
    HAS_TOTAL_BILIRUBIN_ULN_OF_AT_MOST_X,
    HAS_DIRECT_BILIRUBIN_ULN_OF_AT_MOST_X,
    HAS_INR_ULN_OF_AT_MOST_X,
    HAS_PT_ULN_OF_AT_MOST_X,
    HAS_APTT_ULN_OF_AT_MOST_X,
    HAS_ASAT_ULN_OF_AT_MOST_X,
    HAS_ALAT_ULN_OF_AT_MOST_X,
    HAS_ALP_ULN_OF_AT_MOST_X,
    HAS_LDH_ULN_OF_AT_MOST_X,
    HAS_CALCIUM_MG_PER_DL_OF_AT_MOST_X,
    HAS_CALCIUM_MMOL_PER_L_OF_AT_MOST_X,
    HAS_IONIZED_CALCIUM_MMOL_PER_L_OF_AT_MOST_X,
    HAS_CORRECTED_CALCIUM_ULN_OF_AT_MOST_X,
    HAS_BNP_ULN_OF_AT_MOST_X,
    HAS_TROPONIN_IT_ULN_OF_AT_MOST_X,
    HAS_POTASSIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_MAGNESIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_PHOSPHORUS_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_CORRECTED_CALCIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,

    // Rules related to other conditions
    HAS_HISTORY_OF_AUTOIMMUNE_DISEASE,
    HAS_HISTORY_OF_CARDIAC_DISEASE,
    HAS_HISTORY_OF_CARDIOVASCULAR_DISEASE,
    HAS_HISTORY_OF_GASTROINTESTINAL_DISEASE,
    HAS_HISTORY_OF_VASCULAR_DISEASE,
    HAS_HISTORY_OF_LUNG_DISEASE,
    HAS_HISTORY_OF_LIVER_DISEASE,
    HAS_HISTORY_OF_STROKE,
    HAS_HISTORY_OF_TIA,
    HAS_HISTORY_OF_SPECIFIC_CONDITION_WITH_DOID_X,
    HAS_GILBERT_DISEASE,
    HAS_HAD_ORGAN_TRANSPLANT,
    HAS_HYPERTENSION,
    HAS_DIABETES,
    HAS_KNOWN_MALABSORPTION_SYNDROME,
    IS_IN_DIALYSIS,
    HAS_SEVERE_CONCOMITANT_CONDITION,

    //Rules related to cardiac function
    HAS_CARDIAC_ARRHYTHMIA,
    HAS_CARDIAC_ARRHYTHMIA_OF_TYPE_X,
    HAS_LVEF_OF_AT_LEAST_X,
    HAS_LVEF_OF_AT_LEAST_X_IF_KNOWN,
    HAS_QTCF_OF_AT_MOST_X,
    HAS_LONG_QT_SYNDROME,
    HAS_RESTING_HEART_RATE_BETWEEN_X_AND_Y,

    // Rules related to infections
    HAS_ACTIVE_INFECTION,
    HAS_KNOWN_HEPATITIS_B_INFECTION,
    HAS_KNOWN_HEPATITIS_C_INFECTION,
    HAS_KNOWN_HIV_INFECTION,
    HAS_KNOWN_CYTOMEGALOVIRUS_INFECTION,
    ADHERENCE_TO_PROTOCOL_REGARDING_ATTENUATED_VACCINE_USE,

    // Rules depending on allergies / current medication
    HAS_ALLERGY_OF_NAME_X,
    HAS_ALLERGY_RELATED_TO_STUDY_MEDICATION,
    IS_ABLE_TO_SWALLOW_ORAL_MEDICATION,
    CURRENTLY_GETS_MEDICATION,
    CURRENTLY_GETS_OTHER_ANTI_CANCER_THERAPY,
    CURRENTLY_GETS_ANTICOAGULANT_MEDICATION,
    CURRENTLY_GETS_ANTIBIOTICS_MEDICATION,
    CURRENTLY_GETS_CORTICOSTEROID_MEDICATION,
    CURRENTLY_GETS_COUMADIN_DERIVATIVE_MEDICATION,
    CURRENTLY_GETS_IMMUNOSUPPRESSANT_MEDICATION,
    CURRENTLY_GETS_PAIN_MEDICATION,
    CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_CYP_X,
    CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_PGP,
    HAS_STABLE_ANTICOAGULANT_MEDICATION_DOSING,
    HAS_STABLE_PAIN_MEDICATION_DOSING,

    // Rules related to pregnancy/anticonception
    IS_BREASTFEEDING,
    IS_PREGNANT,
    IS_ABLE_AND_WILLING_TO_USE_ADEQUATE_ANTICONCEPTION_IF_REQUIRED,

    // Rules related to complications
    HAS_COMPLICATION_X,

    // Rules related to toxicity
    HAS_TOXICITY_OF_AT_LEAST_GRADE_X,
    HAS_TOXICITY_OF_AT_LEAST_GRADE_X_IN_Y,
    HAS_TOXICITY_OF_AT_LEAST_GRADE_X_IGNORING_Y,

    // Rules related to blood pressure measurements
    HAS_SBP_MMHG_OF_AT_LEAST_X,
    HAS_DBP_MMHG_OF_AT_LEAST_X,

    // Rules related to blood transfusions
    HAS_HAD_ERYTHROCYTE_TRANSFUSION_WITHIN_LAST_X_WEEKS,
    HAS_HAD_THROMBOCYTE_TRANSFUSION_WITHIN_LAST_X_WEEKS,

    // Rules related to surgery
    HAS_HAD_SURGERY_WITHIN_LAST_X_WEEKS
}
