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
    CAN_GIVE_ADEQUATE_INFORMED_CONSENT,
    IS_INVOLVED_IN_STUDY_PROCEDURES,
    IS_PARTICIPATING_IN_ANOTHER_TRIAL,
    HAS_PARTICIPATED_IN_CURRENT_TRIAL,
    HAS_RAPIDLY_DETORIATING_CONDITION,
    HAS_LIFE_EXPECTANCY_OF_AT_LEAST_X_WEEKS,
    HAS_LIFE_EXPECTANCY_OF_AT_LEAST_X_MONTHS,
    PATIENT_IS_TREATED_IN_HOSPITAL_X,
    PATIENT_WILL_BE_PARTICIPATING_IN_COUNTRY_X,
    PATIENT_IS_LEGALLY_INSTITUTIONALIZED,
    IS_ABLE_AND_WILLING_TO_NOT_USE_CONTACT_LENSES,

    // Rules related to tumor and lesion locations
    PRIMARY_TUMOR_LOCATION_BELONGS_TO_DOID_X,
    HAS_MELANOMA_OF_UNKNOWN_PRIMARY,
    HAS_STAGE_X,
    HAS_ADVANCED_CANCER,
    HAS_METASTATIC_CANCER,
    HAS_ANY_LESION,
    HAS_LIVER_METASTASES,
    HAS_KNOWN_CNS_METASTASES,
    HAS_KNOWN_ACTIVE_CNS_METASTASES,
    HAS_KNOWN_SYMPTOMATIC_CNS_METASTASES,
    HAS_KNOWN_BRAIN_METASTASES,
    HAS_KNOWN_ACTIVE_BRAIN_METASTASES,
    HAS_KNOWN_SYMPTOMATIC_BRAIN_METASTASES,
    HAS_BONE_METASTASES,
    HAS_LUNG_METASTASES,
    HAS_MEASURABLE_DISEASE_RECIST,
    HAS_BIOPSY_AMENABLE_LESION,
    HAS_INJECTION_AMENABLE_LESION,
    HAS_PROGRESSIVE_DISEASE_ACCORDING_TO_SPECIFIC_CRITERIA,
    HAS_MRI_VOLUME_MEASUREMENT_AMENABLE_LESION,
    HAS_SUPERSCAN_BONE_SCAN,
    HAS_LOW_RISK_OF_HEMORRHAGE_UPON_TREATMENT,
    HAS_COLLECTED_TUMOR_BIOPSY_WITHIN_X_MONTHS_BEFORE_IC,
    HAS_HISTOLOGICAL_DOCUMENTATION_OF_TUMOR_TYPE,

    // Rules related to previous cancer treatments
    IS_ELIGIBLE_FOR_TREATMENT_WITH_CURATIVE_INTENT,
    HAS_EXHAUSTED_SOC_TREATMENTS,
    HAS_DECLINED_SOC_TREATMENTS,
    HAS_HAD_AT_LEAST_X_APPROVED_TREATMENT_LINES,
    HAS_HAD_AT_LEAST_X_SYSTEMIC_TREATMENT_LINES,
    HAS_HAD_AT_MOST_X_SYSTEMIC_TREATMENT_LINES,
    HAS_HAD_TREATMENT_NAME_X,
    HAS_HAD_CATEGORY_X_TREATMENT,
    HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPE_Y,
    HAS_HAD_CATEGORY_X_TREATMENT_IGNORING_TYPE_Y,
    HAS_HAD_CATEGORY_X_TREATMENT_AND_AT_LEAST_Y_LINES,
    HAS_HAD_CATEGORY_X_TREATMENT_AND_AT_MOST_Y_LINES,
    HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPE_Y_AND_AT_LEAST_Z_LINES,
    HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPE_Y_AND_AT_MOST_Z_LINES,
    HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPES_Y_AND_AT_MOST_Z_LINES,
    HAS_HAD_FLUOROPYRIMIDINE_TREATMENT,
    HAS_HAD_TAXANE_TREATMENT,
    HAS_HAD_TAXANE_TREATMENT_AND_AT_MOST_X_LINES,
    HAS_HAD_TYROSINE_KINASE_TREATMENT,
    HAS_HAD_INTRATUMURAL_INJECTION_TREATMENT,
    IS_ELIGIBLE_FOR_ON_LABEL_DRUG_X,

    // Rules related to previous primary tumors
    HAS_HISTORY_OF_SECOND_MALIGNANCY,
    HAS_HISTORY_OF_SECOND_MALIGNANCY_BELONGING_TO_DOID_X,
    HAS_HISTORY_OF_SECOND_MALIGNANCY_BELONGING_TO_DOID_X_CURRENTLY_INACTIVE,
    EVERY_SECOND_MALIGNANCY_HAS_BEEN_CURED_SINCE_X_YEARS,

    // Rules related to molecular results
    MOLECULAR_RESULTS_MUST_BE_AVAILABLE,
    MOLECULAR_RESULTS_MUST_BE_AVAILABLE_FOR_GENE_X,
    ACTIVATION_OR_AMPLIFICATION_OF_GENE_X,
    INACTIVATION_OF_GENE_X,
    ACTIVATING_MUTATION_IN_GENE_X,
    MUTATION_IN_GENE_X_OF_TYPE_Y,
    AMPLIFICATION_OF_GENE_X,
    DELETION_OF_GENE_X,
    FUSION_IN_GENE_X,
    SPECIFIC_FUSION_OF_X_TO_Y,
    OVEREXPRESSION_OF_GENE_X,
    NON_EXPRESSION_OF_GENE_X,
    EXPRESSION_OF_GENE_X_BY_IHC,
    EXPRESSION_OF_GENE_X_BY_IHC_OF_EXACTLY_Y,
    EXPRESSION_OF_GENE_X_BY_IHC_OF_AT_LEAST_Y,
    WILDTYPE_OF_GENE_X,
    MSI_SIGNATURE,
    HRD_SIGNATURE,
    TMB_OF_AT_LEAST_X,
    TML_OF_AT_LEAST_X,
    TML_OF_AT_MOST_X,
    PD_L1_SCORE_CPS_OF_AT_LEAST_X,
    PD_L1_SCORE_CPS_OF_AT_MOST_X,

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
    HAS_GLUCOSE_PL_MMOL_PER_L_OF_AT_MOST_X,
    HAS_SERUM_TESTOSTERONE_NG_PER_DL_OF_AT_MOST_X,
    HAS_CREATININE_MG_PER_DL_OF_AT_MOST_X,
    HAS_CREATININE_ULN_OF_AT_MOST_X,
    HAS_EGFR_CKD_EPI_OF_AT_LEAST_X,
    HAS_EGFR_MDRD_OF_AT_LEAST_X,
    HAS_CREATININE_CLEARANCE_CG_OF_AT_LEAST_X,
    HAS_TOTAL_BILIRUBIN_ULN_OF_AT_MOST_X,
    HAS_TOTAL_BILIRUBIN_UMOL_PER_L_OF_AT_MOST_X,
    HAS_DIRECT_BILIRUBIN_ULN_OF_AT_MOST_X,
    HAS_INR_ULN_OF_AT_MOST_X,
    HAS_PT_ULN_OF_AT_MOST_X,
    HAS_APTT_ULN_OF_AT_MOST_X,
    HAS_PTT_ULN_OF_AT_MOST_X,
    HAS_ASAT_ULN_OF_AT_MOST_X,
    HAS_ALAT_ULN_OF_AT_MOST_X,
    HAS_ALP_ULN_OF_AT_MOST_X,
    HAS_LDH_ULN_OF_AT_MOST_X,
    HAS_PHOSPHORUS_ULN_OF_AT_MOST_X,
    HAS_AFP_ULN_OF_AT_LEAST_X,
    HAS_CA125_ULN_OF_AT_LEAST_X,
    HAS_HCG_ULN_OF_AT_LEAST_X,
    HAS_CALCIUM_MG_PER_DL_OF_AT_MOST_X,
    HAS_CALCIUM_MMOL_PER_L_OF_AT_MOST_X,
    HAS_IONIZED_CALCIUM_MMOL_PER_L_OF_AT_MOST_X,
    HAS_CORRECTED_CALCIUM_ULN_OF_AT_MOST_X,
    HAS_SERUM_POTASSIUM_MMOL_PER_L_OF_AT_LEAST_X,
    HAS_BNP_ULN_OF_AT_MOST_X,
    HAS_TROPONIN_IT_ULN_OF_AT_MOST_X,
    HAS_TRIGLYCERIDE_MMOL_PER_L_OF_AT_MOST_X,
    HAS_POTASSIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_CORRECTED_POTASSIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_MAGNESIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_CORRECTED_MAGNESIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_PHOSPHORUS_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_CORRECTED_PHOSPHORUS_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_CALCIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_CORRECTED_CALCIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_TOTAL_PROTEIN_IN_URINE_OF_AT_LEAST_X,
    HAS_TOTAL_PROTEIN_IN_24H_URINE_OF_AT_LEAST_X,

    // Rules related to other conditions
    HAS_HISTORY_OF_AUTOIMMUNE_DISEASE,
    HAS_HISTORY_OF_CARDIAC_DISEASE,
    HAS_HISTORY_OF_CARDIOVASCULAR_DISEASE,
    HAS_HISTORY_OF_GASTROINTESTINAL_DISEASE,
    HAS_HISTORY_OF_IMMUNE_SYSTEM_DISEASE,
    HAS_HISTORY_OF_VASCULAR_DISEASE,
    HAS_HISTORY_OF_LUNG_DISEASE,
    HAS_HISTORY_OF_LIVER_DISEASE,
    HAS_HISTORY_OF_STROKE,
    HAS_HISTORY_OF_TIA,
    HAS_HISTORY_OF_SPECIFIC_CONDITION_WITH_DOID_X,
    HAS_HISTORY_OF_SPECIFIC_CONDITION_X_BY_NAME,
    HAS_GILBERT_DISEASE,
    HAS_HAD_ORGAN_TRANSPLANT,
    HAS_HYPERTENSION,
    HAS_DIABETES,
    HAS_HISTORY_OF_ANAPHYLAXIS,
    HAS_POTENTIAL_ABSORPTION_DIFFICULTIES,
    HAS_POTENTIAL_ORAL_MEDICATION_DIFFICULTIES,
    HAS_POTENTIAL_CONTRAINDICATION_TO_MRI,
    IS_IN_DIALYSIS,
    HAS_ADEQUATE_VEIN_ACCESS_FOR_LEUKAPHERESIS,
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
    HAS_KNOWN_HEPATITIS_A_INFECTION,
    HAS_KNOWN_HEPATITIS_B_INFECTION,
    HAS_KNOWN_HEPATITIS_C_INFECTION,
    HAS_KNOWN_HIV_INFECTION,
    HAS_KNOWN_CYTOMEGALOVIRUS_INFECTION,
    HAS_KNOWN_TUBERCOLOSIS_INFECTION,
    HAS_CURRENT_COVID_19_INFECTION,
    ADHERENCE_TO_PROTOCOL_REGARDING_ATTENUATED_VACCINE_USE,

    // Rules depending on allergies / current medication
    HAS_ALLERGY_OF_NAME_X,
    HAS_ALLERGY_RELATED_TO_STUDY_MEDICATION,
    CURRENTLY_GETS_MEDICATION,
    CURRENTLY_GETS_NAME_X_MEDICATION,
    CURRENTLY_GETS_CATEGORY_X_MEDICATION,
    CURRENTLY_GETS_ANTICOAGULANT_MEDICATION,
    CURRENTLY_GETS_AZOLE_MEDICATION,
    CURRENTLY_GETS_BONE_RESORPTIVE_MEDICATION,
    CURRENTLY_GETS_CORTICOSTEROID_MEDICATION,
    CURRENTLY_GETS_COUMADIN_DERIVATIVE_MEDICATION,
    CURRENTLY_GETS_GONADORELIN_MEDICATION,
    CURRENTLY_GETS_IMMUNOSUPPRESSANT_MEDICATION,
    CURRENTLY_GETS_OAT3_INHIBITORS_MEDICATION,
    CURRENTLY_GETS_PAIN_MEDICATION,
    CURRENTLY_GETS_PROHIBITED_MEDICATION,
    CURRENTLY_GETS_POTENTIALLY_QT_PROLONGATING_MEDICATION,
    CURRENTLY_GETS_COLONY_STIMULATING_FACTORS,
    CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_CYP_X,
    CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_PGP,
    CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_OATP_X,
    CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_BCRP,
    HAS_STABLE_ANTICOAGULANT_MEDICATION_DOSING,
    HAS_STABLE_PAIN_MEDICATION_DOSING,

    // Rules related to washout period
    HAS_RECEIVED_DRUG_X_CANCER_THERAPY_WITHIN_Y_WEEKS,
    HAS_RECEIVED_CATEGORY_X_CANCER_THERAPY_WITHIN_Y_WEEKS,
    HAS_RECEIVED_RADIOTHERAPY_WITHIN_X_WEEKS,
    HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_WITHIN_X_WEEKS,
    HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_EXCL_CATEGORY_X_WITHIN_Y_WEEKS,
    HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_WITHIN_X_WEEKS_Y_HALF_LIVES,
    HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_EXCL_CATEGORY_X_WITHIN_Y_WEEKS_Z_HALF_LIVES,
    WILL_REQUIRE_ANY_ANTICANCER_THERAPY_DURING_TRIAL,

    // Rules related to pregnancy/anticonception
    IS_BREASTFEEDING,
    IS_PREGNANT,
    USES_ADEQUATE_ANTICONCEPTION,

    // Rules related to complications
    HAS_COMPLICATION_X,

    // Rules related to toxicity
    HAS_TOXICITY_OF_AT_LEAST_GRADE_X,
    HAS_TOXICITY_OF_AT_LEAST_GRADE_X_IN_Y,
    HAS_TOXICITY_OF_AT_LEAST_GRADE_X_IGNORING_Y,
    HAS_EXPERIENCED_IMMUNE_RELATED_ADVERSE_EVENTS,

    // Rules related to vital function measurements
    HAS_SBP_MMHG_OF_AT_LEAST_X,
    HAS_DBP_MMHG_OF_AT_LEAST_X,
    HAS_PULSE_OXYMETRY_OF_AT_LEAST_X,
    HAS_BODY_WEIGHT_OF_AT_LEAST_X,

    // Rules related to blood transfusions
    HAS_HAD_ERYTHROCYTE_TRANSFUSION_WITHIN_LAST_X_WEEKS,
    HAS_HAD_THROMBOCYTE_TRANSFUSION_WITHIN_LAST_X_WEEKS,

    // Rules related to surgery
    HAS_HAD_RECENT_SURGERY,
    HAS_HAD_SURGERY_WITHIN_LAST_X_WEEKS,

    // Rules related to smoking
    HAS_SMOKED_WITHIN_X_MONTHS
}
