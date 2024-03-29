package com.hartwig.actin.treatment.datamodel;

public enum EligibilityRule {
    // Composite rules combining other rules.
    AND,
    OR,
    NOT,
    WARN_IF,

    // Rules related to general patient characteristics / statements
    IS_AT_LEAST_X_YEARS_OLD,
    IS_MALE,
    IS_FEMALE,
    HAS_WHO_STATUS_OF_AT_MOST_X,
    HAS_WHO_STATUS_OF_AT_EXACTLY_X,
    HAS_KARNOFSKY_SCORE_OF_AT_LEAST_X,
    HAS_LANSKY_SCORE_OF_AT_LEAST_X,
    CAN_GIVE_ADEQUATE_INFORMED_CONSENT,
    HAS_RAPIDLY_DETERIORATING_CONDITION,
    HAS_LIFE_EXPECTANCY_OF_AT_LEAST_X_WEEKS,
    HAS_LIFE_EXPECTANCY_OF_AT_LEAST_X_MONTHS,
    IS_TREATED_IN_HOSPITAL_X,
    WILL_PARTICIPATE_IN_TRIAL_IN_COUNTRY_X,
    IS_LEGALLY_INSTITUTIONALIZED,
    IS_INVOLVED_IN_STUDY_PROCEDURES,

    // Rules related to tumor and lesion localization
    HAS_SOLID_PRIMARY_TUMOR,
    HAS_SOLID_PRIMARY_TUMOR_INCLUDING_LYMPHOMA,
    HAS_PRIMARY_TUMOR_LOCATION_BELONGING_TO_DOID_TERM_X,
    HAS_CANCER_OF_UNKNOWN_PRIMARY_AND_TYPE_X,
    HAS_CANCER_WITH_NEUROENDOCRINE_COMPONENT,
    HAS_CANCER_WITH_SMALL_CELL_COMPONENT,
    HAS_BREAST_CANCER_HORMONE_POSITIVE_AND_HER2_NEGATIVE,
    HAS_PROSTATE_CANCER_WITH_SMALL_CELL_COMPONENT,
    HAS_OVARIAN_CANCER_WITH_MUCINOUS_COMPONENT,
    HAS_OVARIAN_BORDERLINE_TUMOR,
    HAS_STOMACH_UNDIFFERENTIATED_TUMOR,
    HAS_SECONDARY_GLIOBLASTOMA,
    HAS_CYTOLOGICAL_DOCUMENTATION_OF_TUMOR_TYPE,
    HAS_HISTOLOGICAL_DOCUMENTATION_OF_TUMOR_TYPE,
    HAS_STAGE_X,
    HAS_LOCALLY_ADVANCED_CANCER,
    HAS_METASTATIC_CANCER,
    HAS_UNRESECTABLE_CANCER,
    HAS_UNRESECTABLE_STAGE_III_CANCER,
    HAS_RECURRENT_CANCER,
    HAS_INCURABLE_CANCER,
    HAS_ANY_LESION,
    HAS_LIVER_METASTASES,
    HAS_KNOWN_CNS_METASTASES,
    HAS_KNOWN_ACTIVE_CNS_METASTASES,
    HAS_KNOWN_BRAIN_METASTASES,
    HAS_KNOWN_ACTIVE_BRAIN_METASTASES,
    HAS_EXTRACRANIAL_METASTASES,
    HAS_BONE_METASTASES,
    HAS_BONE_METASTASES_ONLY,
    HAS_LUNG_METASTASES,
    HAS_BIOPSY_AMENABLE_LESION,
    HAS_COLLECTED_TUMOR_BIOPSY_WITHIN_X_MONTHS_BEFORE_IC,
    CAN_PROVIDE_FRESH_TISSUE_SAMPLE_FOR_FFPE_ANALYSIS,
    CAN_PROVIDE_ARCHIVAL_OR_FRESH_TISSUE_SAMPLE_FOR_FFPE_ANALYSIS,
    MEETS_SPECIFIC_REQUIREMENTS_REGARDING_BIOPSY,
    HAS_MEASURABLE_DISEASE,
    HAS_MEASURABLE_DISEASE_RECIST,
    HAS_PROGRESSIVE_DISEASE_ACCORDING_TO_SPECIFIC_CRITERIA,
    HAS_INJECTION_AMENABLE_LESION,
    HAS_MRI_VOLUME_MEASUREMENT_AMENABLE_LESION,
    HAS_EVIDENCE_OF_CNS_HEMORRHAGE_BY_MRI,
    HAS_INTRATUMORAL_HEMORRHAGE_BY_MRI,
    HAS_LOW_RISK_OF_HEMORRHAGE_UPON_TREATMENT,
    HAS_SUPERSCAN_BONE_SCAN,
    HAS_CHILD_PUGH_CLASS_X_LIVER_SCORE,
    HAS_BCLC_STAGE_X,

    // Rules related to previous cancer treatments
    IS_ELIGIBLE_FOR_TREATMENT_WITH_CURATIVE_INTENT,
    IS_ELIGIBLE_FOR_ON_LABEL_TREATMENT_X,
    HAS_EXHAUSTED_SOC_TREATMENTS,
    HAS_HAD_AT_LEAST_X_APPROVED_TREATMENT_LINES,
    HAS_HAD_AT_LEAST_X_SYSTEMIC_TREATMENT_LINES,
    HAS_HAD_AT_MOST_X_SYSTEMIC_TREATMENT_LINES,
    HAS_HAD_ANY_CANCER_TREATMENT,
    HAS_HAD_TREATMENT_NAME_X,
    HAS_HAD_TREATMENT_NAME_X_WITHIN_Y_WEEKS,
    HAS_HAD_ANY_CANCER_TREATMENT_IGNORING_CATEGORIES_WITH_NAME_X,
    HAS_HAD_CATEGORY_X_TREATMENT,
    HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPES_Y,
    HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPES_Y_WITHIN_Z_WEEKS,
    HAS_HAD_CATEGORY_X_TREATMENT_IGNORING_TYPES_Y,
    HAS_HAD_CATEGORY_X_TREATMENT_AND_AT_LEAST_Y_LINES,
    HAS_HAD_CATEGORY_X_TREATMENT_AND_AT_MOST_Y_LINES,
    HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPES_Y_AND_AT_LEAST_Z_LINES,
    HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPES_Y_AND_AT_MOST_Z_LINES,
    HAS_PROGRESSIVE_DISEASE_FOLLOWING_NAME_X_TREATMENT,
    HAS_PROGRESSIVE_DISEASE_FOLLOWING_CATEGORY_X_TREATMENT,
    HAS_PROGRESSIVE_DISEASE_FOLLOWING_CATEGORY_X_TREATMENT_OF_TYPES_Y,
    HAS_PROGRESSIVE_DISEASE_FOLLOWING_CATEGORY_X_TREATMENT_OF_TYPES_Y_AND_AT_LEAST_Z_CYCLES,
    HAS_PROGRESSIVE_DISEASE_FOLLOWING_AT_LEAST_X_TREATMENT_LINES,
    HAS_HAD_COMPLETE_RESECTION,
    HAS_HAD_PARTIAL_RESECTION,
    HAS_HAD_RESECTION_WITHIN_X_WEEKS,
    HAS_HAD_INTRATUMORAL_INJECTION_TREATMENT,
    HAS_CUMULATIVE_ANTHRACYCLINE_EXPOSURE_OF_AT_MOST_X_MG_PER_M2_DOXORUBICIN_OR_EQUIVALENT,
    HAS_PREVIOUSLY_PARTICIPATED_IN_CURRENT_TRIAL,
    IS_PARTICIPATING_IN_ANOTHER_TRIAL,

    // Rules related to previous primary tumors
    HAS_ACTIVE_SECOND_MALIGNANCY,
    HAS_HISTORY_OF_SECOND_MALIGNANCY,
    HAS_HISTORY_OF_SECOND_MALIGNANCY_BELONGING_TO_DOID_TERM_X,
    HAS_HISTORY_OF_SECOND_MALIGNANCY_WITHIN_X_YEARS,

    // Rules related to molecular results
    ACTIVATION_OR_AMPLIFICATION_OF_GENE_X,
    INACTIVATION_OF_GENE_X,
    ACTIVATING_MUTATION_IN_GENE_X,
    MUTATION_IN_GENE_X_OF_TYPE_Y,
    AMPLIFICATION_OF_GENE_X,
    FUSION_IN_GENE_X,
    WILDTYPE_OF_GENE_X,
    EXON_SKIPPING_GENE_X_EXON_Y,
    MSI_SIGNATURE,
    HRD_SIGNATURE,
    TMB_OF_AT_LEAST_X,
    TML_OF_AT_LEAST_X,
    TML_OF_AT_MOST_X,
    HAS_HLA_TYPE_X,
    OVEREXPRESSION_OF_GENE_X,
    NON_EXPRESSION_OF_GENE_X,
    EXPRESSION_OF_GENE_X_BY_IHC,
    EXPRESSION_OF_GENE_X_BY_IHC_OF_EXACTLY_Y,
    EXPRESSION_OF_GENE_X_BY_IHC_OF_AT_LEAST_Y,
    PD_L1_SCORE_CPS_OF_AT_LEAST_X,
    PD_L1_SCORE_CPS_OF_AT_MOST_X,
    PD_L1_SCORE_TPS_OF_AT_MOST_X,
    PD_L1_STATUS_MUST_BE_AVAILABLE,
    HAS_PSMA_POSITIVE_PET_SCAN,
    MOLECULAR_RESULTS_MUST_BE_AVAILABLE,
    MOLECULAR_TEST_MUST_HAVE_BEEN_DONE_FOR_GENE_X,
    MOLECULAR_TEST_MUST_HAVE_BEEN_DONE_FOR_PROMOTER_OF_GENE_X,

    // Rules related to recent laboratory measurements
    HAS_LEUKOCYTES_ABS_OF_AT_LEAST_X,
    HAS_LEUKOCYTES_ABS_LLN_OF_AT_LEAST_X,
    HAS_LYMPHOCYTES_ABS_OF_AT_LEAST_X,
    HAS_LYMPHOCYTES_CELLS_PER_MM3_OF_AT_LEAST_X,
    HAS_NEUTROPHILS_ABS_OF_AT_LEAST_X,
    HAS_THROMBOCYTES_ABS_OF_AT_LEAST_X,
    HAS_HEMOGLOBIN_G_PER_DL_OF_AT_LEAST_X,
    HAS_HEMOGLOBIN_MMOL_PER_L_OF_AT_LEAST_X,
    HAS_INR_ULN_OF_AT_MOST_X,
    HAS_PT_ULN_OF_AT_MOST_X,
    HAS_APTT_ULN_OF_AT_MOST_X,
    HAS_PTT_ULN_OF_AT_MOST_X,
    HAS_D_DIMER_OUTSIDE_REF_UPPER_LIMIT,
    HAS_ALBUMIN_G_PER_DL_OF_AT_LEAST_X,
    HAS_ALBUMIN_LLN_OF_AT_LEAST_X,
    HAS_ASAT_ULN_OF_AT_MOST_X,
    HAS_ALAT_ULN_OF_AT_MOST_X,
    HAS_ALP_ULN_OF_AT_MOST_X,
    HAS_ALP_ULN_OF_AT_LEAST_X,
    HAS_TOTAL_BILIRUBIN_ULN_OF_AT_MOST_X,
    HAS_TOTAL_BILIRUBIN_UMOL_PER_L_OF_AT_MOST_X,
    HAS_DIRECT_BILIRUBIN_ULN_OF_AT_MOST_X,
    HAS_DIRECT_BILIRUBIN_PERCENTAGE_OF_TOTAL_OF_AT_MOST_X,
    HAS_CREATININE_MG_PER_DL_OF_AT_MOST_X,
    HAS_CREATININE_ULN_OF_AT_MOST_X,
    HAS_EGFR_CKD_EPI_OF_AT_LEAST_X,
    HAS_EGFR_MDRD_OF_AT_LEAST_X,
    HAS_CREATININE_CLEARANCE_CG_OF_AT_LEAST_X,
    HAS_CREATININE_CLEARANCE_BETWEEN_X_AND_Y,
    HAS_BNP_ULN_OF_AT_MOST_X,
    HAS_TROPONIN_IT_ULN_OF_AT_MOST_X,
    HAS_TRIGLYCERIDE_MMOL_PER_L_OF_AT_MOST_X,
    HAS_AMYLASE_ULN_OF_AT_MOST_X,
    HAS_LIPASE_ULN_OF_AT_MOST_X,
    HAS_CALCIUM_MG_PER_DL_OF_AT_MOST_X,
    HAS_CALCIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_CORRECTED_CALCIUM_ULN_OF_AT_MOST_X,
    HAS_CORRECTED_CALCIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_IONIZED_CALCIUM_MMOL_PER_L_OF_AT_MOST_X,
    HAS_MAGNESIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_CORRECTED_MAGNESIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_PHOSPHORUS_ULN_OF_AT_MOST_X,
    HAS_PHOSPHORUS_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_CORRECTED_PHOSPHORUS_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_POTASSIUM_MMOL_PER_L_OF_AT_LEAST_X,
    HAS_POTASSIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_CORRECTED_POTASSIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS,
    HAS_POTENTIAL_HYPOKALEMIA,
    HAS_POTENTIAL_HYPOMAGNESEMIA,
    HAS_POTENTIAL_HYPOCALCEMIA,
    HAS_SERUM_TESTOSTERONE_NG_PER_DL_OF_AT_MOST_X,
    HAS_AFP_ULN_OF_AT_LEAST_X,
    HAS_CA125_ULN_OF_AT_LEAST_X,
    HAS_HCG_ULN_OF_AT_LEAST_X,
    HAS_LDH_ULN_OF_AT_MOST_X,
    HAS_PSA_UG_PER_L_OF_AT_LEAST_X,
    HAS_PSA_LLN_OF_AT_LEAST_X,
    HAS_TOTAL_PROTEIN_IN_URINE_OF_AT_LEAST_X,
    HAS_TOTAL_PROTEIN_IN_24H_URINE_OF_AT_LEAST_X,
    HAS_GLUCOSE_PL_MMOL_PER_L_OF_AT_MOST_X,

    // Rules related to other conditions
    HAS_HISTORY_OF_SPECIFIC_CONDITION_WITH_DOID_TERM_X,
    HAS_HISTORY_OF_SPECIFIC_CONDITION_X_BY_NAME,
    HAS_HISTORY_OF_AUTOIMMUNE_DISEASE,
    HAS_HISTORY_OF_ANGINA,
    HAS_HISTORY_OF_BRAIN_DISEASE,
    HAS_HISTORY_OF_CARDIAC_DISEASE,
    HAS_HISTORY_OF_CARDIOVASCULAR_DISEASE,
    HAS_HISTORY_OF_CONGESTIVE_HEART_FAILURE_WITH_AT_LEAST_NYHA_CLASS_X,
    HAS_HISTORY_OF_CENTRAL_NERVOUS_SYSTEM_DISEASE,
    HAS_HISTORY_OF_GASTROINTESTINAL_DISEASE,
    HAS_HISTORY_OF_IMMUNE_SYSTEM_DISEASE,
    HAS_HISTORY_OF_INTERSTITIAL_LUNG_DISEASE,
    HAS_HISTORY_OF_LIVER_DISEASE,
    HAS_HISTORY_OF_LUNG_DISEASE,
    HAS_HISTORY_OF_MYOCARDIAL_INFARCT,
    HAS_HISTORY_OF_MYOCARDIAL_INFARCT_WITHIN_X_MONTHS,
    HAS_HISTORY_OF_PNEUMONITIS,
    HAS_HISTORY_OF_STROKE,
    HAS_HISTORY_OF_THROMBOEMBOLIC_EVENT,
    HAS_HISTORY_OF_ARTERIAL_THROMBOEMBOLIC_EVENT,
    HAS_HISTORY_OF_VENOUS_THROMBOEMBOLIC_EVENT,
    HAS_HISTORY_OF_VASCULAR_DISEASE,
    HAS_SEVERE_CONCOMITANT_CONDITION,
    HAS_HAD_ORGAN_TRANSPLANT,
    HAS_HAD_ORGAN_TRANSPLANT_WITHIN_X_YEARS,
    HAS_GILBERT_DISEASE,
    HAS_HYPERTENSION,
    HAS_HYPOTENSION,
    HAS_DIABETES,
    HAS_POTENTIAL_ABSORPTION_DIFFICULTIES,
    HAS_POTENTIAL_ORAL_MEDICATION_DIFFICULTIES,
    HAS_POTENTIAL_CONTRAINDICATION_TO_CT,
    HAS_POTENTIAL_CONTRAINDICATION_TO_MRI,
    HAS_POTENTIAL_CONTRAINDICATION_TO_PET_MRI,
    HAS_POST_OPERATIVE_BASELINE_CONTRAST_ENHANCED_MRI_SCAN,
    HAS_MRI_SCAN_DOCUMENTING_STABLE_DISEASE,
    IS_IN_DIALYSIS,
    HAS_ADEQUATE_VEIN_ACCESS_FOR_LEUKAPHERESIS,

    //Rules related to cardiac function
    HAS_POTENTIAL_SIGNIFICANT_HEART_DISEASE,
    HAS_CARDIAC_ARRHYTHMIA,
    HAS_LVEF_OF_AT_LEAST_X,
    HAS_LVEF_OF_AT_LEAST_X_IF_KNOWN,
    HAS_QTC_OF_AT_MOST_X,
    HAS_QTCF_OF_AT_MOST_X,
    HAS_QTCF_OF_AT_LEAST_X,
    HAS_JTC_OF_AT_LEAST_X,
    HAS_LONG_QT_SYNDROME,
    HAS_NORMAL_CARDIAC_FUNCTION_BY_MUGA_OR_TTE,
    HAS_FAMILY_HISTORY_OF_IDIOPATHIC_SUDDEN_DEATH,

    // Rules related to infections
    HAS_ACTIVE_INFECTION,
    HAS_KNOWN_EBV_INFECTION,
    HAS_KNOWN_HEPATITIS_A_INFECTION,
    HAS_KNOWN_HEPATITIS_B_INFECTION,
    HAS_KNOWN_HEPATITIS_C_INFECTION,
    HAS_KNOWN_HIV_INFECTION,
    HAS_KNOWN_CYTOMEGALOVIRUS_INFECTION,
    HAS_KNOWN_TUBERCULOSIS_INFECTION,
    MEETS_COVID_19_INFECTION_REQUIREMENTS,
    MEETS_COVID_19_VACCINATION_REQUIREMENTS,
    IS_FULLY_VACCINATED_AGAINST_COVID_19,
    HAS_RECEIVED_LIVE_VACCINE_WITHIN_X_MONTHS,
    ADHERENCE_TO_PROTOCOL_REGARDING_ATTENUATED_VACCINE_USE,

    // Rules depending on current medication
    CURRENTLY_GETS_NAME_X_MEDICATION,
    CURRENTLY_GETS_CATEGORY_X_MEDICATION,
    HAS_RECEIVED_CATEGORY_X_MEDICATION_WITHIN_Y_WEEKS,
    CURRENTLY_GETS_ANTICOAGULANT_MEDICATION,
    CURRENTLY_GETS_AZOLE_MEDICATION,
    CURRENTLY_GETS_BONE_RESORPTIVE_MEDICATION,
    CURRENTLY_GETS_COUMARIN_DERIVATIVE_MEDICATION,
    CURRENTLY_GETS_GONADORELIN_MEDICATION,
    CURRENTLY_GETS_IMMUNOSUPPRESSANT_MEDICATION,
    CURRENTLY_GETS_PROHIBITED_MEDICATION,
    CURRENTLY_GETS_POTENTIALLY_QT_PROLONGATING_MEDICATION,
    CURRENTLY_GETS_MEDICATION_INHIBITING_CYP_X,
    CURRENTLY_GETS_MEDICATION_INDUCING_CYP_X,
    HAS_RECEIVED_MEDICATION_INDUCING_CYP_X_WITHIN_Y_WEEKS,
    CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_CYP_X,
    CURRENTLY_GETS_MEDICATION_SUBSTRATE_OF_CYP_X,
    CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_PGP,
    CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_BCRP,
    HAS_STABLE_ANTICOAGULANT_MEDICATION_DOSING,
    IS_WILLING_TO_TAKE_PREMEDICATION,

    // Rules related to washout period
    HAS_RECEIVED_DRUGS_X_CANCER_THERAPY_WITHIN_Y_WEEKS,
    HAS_RECEIVED_DRUGS_X_CANCER_THERAPY_WITHIN_Y_WEEKS_Z_HALF_LIVES,
    HAS_RECEIVED_CATEGORIES_X_CANCER_THERAPY_WITHIN_Y_WEEKS,
    HAS_RECEIVED_CATEGORIES_X_CANCER_THERAPY_WITHIN_Y_WEEKS_Z_HALF_LIVES,
    HAS_RECEIVED_RADIOTHERAPY_WITHIN_X_WEEKS,
    HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_WITHIN_X_WEEKS,
    HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_EXCL_CATEGORIES_X_WITHIN_Y_WEEKS,
    HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_WITHIN_X_WEEKS_Y_HALF_LIVES,
    HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_EXCL_CATEGORIES_X_WITHIN_Y_WEEKS_Z_HALF_LIVES,
    WILL_REQUIRE_ANY_ANTICANCER_THERAPY_DURING_TRIAL,
    HAS_RECEIVED_HERBAL_MEDICATION_OR_DIETARY_SUPPLEMENTS_WITHIN_X_WEEKS,

    // Rules related to pregnancy/anticonception
    IS_BREASTFEEDING,
    IS_PREGNANT,
    USES_ADEQUATE_ANTICONCEPTION,
    ADHERES_TO_SPERM_OR_EGG_DONATION_PRESCRIPTIONS,

    // Rules related to complications
    HAS_ANY_COMPLICATION,
    HAS_COMPLICATION_X,
    HAS_COMPLICATION_OF_CATEGORY_X,
    HAS_POTENTIAL_UNCONTROLLED_TUMOR_RELATED_PAIN,
    HAS_LEPTOMENINGEAL_DISEASE,

    // Rules related to allergies/toxicities
    HAS_INTOLERANCE_TO_NAME_X,
    HAS_INTOLERANCE_BELONGING_TO_DOID_TERM_X,
    HAS_INTOLERANCE_TO_TAXANE,
    HAS_INTOLERANCE_RELATED_TO_STUDY_MEDICATION,
    HAS_HISTORY_OF_ANAPHYLAXIS,
    HAS_EXPERIENCED_IMMUNE_RELATED_ADVERSE_EVENTS,
    HAS_TOXICITY_OF_AT_LEAST_GRADE_X,
    HAS_TOXICITY_OF_AT_LEAST_GRADE_X_IN_Y,
    HAS_TOXICITY_OF_AT_LEAST_GRADE_X_IGNORING_Y,

    // Rules related to vital function measurements
    HAS_SBP_MMHG_OF_AT_LEAST_X,
    HAS_SBP_MMHG_OF_AT_MOST_X,
    HAS_DBP_MMHG_OF_AT_LEAST_X,
    HAS_DBP_MMHG_OF_AT_MOST_X,
    HAS_PULSE_OXIMETRY_OF_AT_LEAST_X,
    HAS_RESTING_HEART_RATE_BETWEEN_X_AND_Y,
    HAS_BODY_WEIGHT_OF_AT_LEAST_X,

    // Rules related to blood transfusions
    REQUIRES_REGULAR_HEMATOPOIETIC_SUPPORT,
    HAS_HAD_ERYTHROCYTE_TRANSFUSION_WITHIN_LAST_X_WEEKS,
    HAS_HAD_THROMBOCYTE_TRANSFUSION_WITHIN_LAST_X_WEEKS,

    // Rules related to surgery
    HAS_HAD_RECENT_SURGERY,
    HAS_HAD_SURGERY_WITHIN_LAST_X_WEEKS,
    HAS_HAD_SURGERY_WITHIN_LAST_X_MONTHS,

    // Rules related to lifestyle
    IS_ABLE_AND_WILLING_TO_NOT_USE_CONTACT_LENSES
}
