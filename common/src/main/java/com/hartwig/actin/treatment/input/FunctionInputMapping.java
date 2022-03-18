package com.hartwig.actin.treatment.input;

import java.util.Map;

import com.google.common.collect.Maps;
import com.hartwig.actin.treatment.datamodel.EligibilityRule;
import com.hartwig.actin.treatment.input.single.FunctionInput;

final class FunctionInputMapping {

    static final Map<EligibilityRule, FunctionInput> RULE_INPUT_MAP = Maps.newHashMap();

    static {
        RULE_INPUT_MAP.put(EligibilityRule.IS_AT_LEAST_X_YEARS_OLD, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.IS_MALE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_WHO_STATUS_OF_AT_MOST_X, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KARNOFSKY_SCORE_OF_AT_LEAST_X, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LANSKY_SCORE_OF_AT_LEAST_X, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.CAN_GIVE_ADEQUATE_INFORMED_CONSENT, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_RAPIDLY_DETERIORATING_CONDITION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LIFE_EXPECTANCY_OF_AT_LEAST_X_WEEKS, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LIFE_EXPECTANCY_OF_AT_LEAST_X_MONTHS, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.IS_TREATED_IN_HOSPITAL_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.WILL_PARTICIPATE_IN_TRIAL_IN_COUNTRY_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.IS_LEGALLY_INSTITUTIONALIZED, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.IS_INVOLVED_IN_STUDY_PROCEDURES, FunctionInput.NONE);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_PRIMARY_TUMOR_LOCATION_BELONGING_TO_DOID_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_PRIMARY_TUMOR_LOCATION_BELONGING_ONLY_TO_DOID_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_PRIMARY_TUMOR_LOCATION_EQUAL_TO_DOID_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CANCER_OF_UNKNOWN_PRIMARY_AND_TYPE_X, FunctionInput.ONE_TUMOR_TYPE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CYTOLOGICAL_DOCUMENTATION_OF_TUMOR_TYPE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTOLOGICAL_DOCUMENTATION_OF_TUMOR_TYPE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_STAGE_X, FunctionInput.ONE_TUMOR_STAGE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_ADVANCED_CANCER, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_METASTATIC_CANCER, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_ANY_LESION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LIVER_METASTASES, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_CNS_METASTASES, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_ACTIVE_CNS_METASTASES, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_BRAIN_METASTASES, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_ACTIVE_BRAIN_METASTASES, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_BONE_METASTASES, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LUNG_METASTASES, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_BIOPSY_AMENABLE_LESION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_COLLECTED_TUMOR_BIOPSY_WITHIN_X_MONTHS_BEFORE_IC, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_MEASURABLE_DISEASE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_MEASURABLE_DISEASE_RECIST, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_PROGRESSIVE_DISEASE_ACCORDING_TO_SPECIFIC_CRITERIA, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_INJECTION_AMENABLE_LESION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_MRI_VOLUME_MEASUREMENT_AMENABLE_LESION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_INTRATUMORAL_HEMORRHAGE_BY_MRI, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_SUPERSCAN_BONE_SCAN, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LOW_RISK_OF_HEMORRHAGE_UPON_TREATMENT, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.MANUFACTURED_T_CELLS_ARE_WITHIN_SHELF_LIFE, FunctionInput.NONE);

        RULE_INPUT_MAP.put(EligibilityRule.IS_ELIGIBLE_FOR_TREATMENT_WITH_CURATIVE_INTENT, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.IS_ELIGIBLE_FOR_ON_LABEL_DRUG_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_EXHAUSTED_SOC_TREATMENTS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_AT_LEAST_X_APPROVED_TREATMENT_LINES, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_AT_LEAST_X_SYSTEMIC_TREATMENT_LINES, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_AT_MOST_X_SYSTEMIC_TREATMENT_LINES, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_TREATMENT_NAME_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_CATEGORY_X_TREATMENT, FunctionInput.ONE_TREATMENT);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPES_Y, FunctionInput.ONE_TYPED_TREATMENT_MANY_STRINGS);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_CATEGORY_X_TREATMENT_IGNORING_TYPES_Y, FunctionInput.ONE_TYPED_TREATMENT_MANY_STRINGS);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_CATEGORY_X_TREATMENT_AND_AT_LEAST_Y_LINES, FunctionInput.ONE_TREATMENT_ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_CATEGORY_X_TREATMENT_AND_AT_MOST_Y_LINES, FunctionInput.ONE_TREATMENT_ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPES_Y_AND_AT_LEAST_Z_LINES,
                FunctionInput.ONE_TYPED_TREATMENT_MANY_STRINGS_ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_CATEGORY_X_TREATMENT_OF_TYPES_Y_AND_AT_MOST_Z_LINES,
                FunctionInput.ONE_TYPED_TREATMENT_MANY_STRINGS_ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_INTRATUMORAL_INJECTION_TREATMENT, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.IS_PARTICIPATING_IN_ANOTHER_TRIAL, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_PARTICIPATED_IN_CURRENT_TRIAL, FunctionInput.NONE);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_ACTIVE_SECOND_MALIGNANCY, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_SECOND_MALIGNANCY_BELONGING_TO_DOID_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_SECOND_MALIGNANCY_WITHIN_X_YEARS, FunctionInput.ONE_INTEGER);

        RULE_INPUT_MAP.put(EligibilityRule.ACTIVATION_OR_AMPLIFICATION_OF_GENE_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.INACTIVATION_OF_GENE_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.ACTIVATING_MUTATION_IN_GENE_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.MUTATION_IN_GENE_X_OF_TYPE_Y, FunctionInput.TWO_STRINGS);
        RULE_INPUT_MAP.put(EligibilityRule.AMPLIFICATION_OF_GENE_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.DELETION_OF_GENE_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.FUSION_IN_GENE_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.SPECIFIC_FUSION_OF_X_TO_Y, FunctionInput.TWO_STRINGS);
        RULE_INPUT_MAP.put(EligibilityRule.WILDTYPE_OF_GENE_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.MSI_SIGNATURE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HRD_SIGNATURE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.TMB_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.TML_OF_AT_LEAST_X, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.TML_OF_AT_MOST_X, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HLA_A_TYPE_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.OVEREXPRESSION_OF_GENE_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.NON_EXPRESSION_OF_GENE_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.EXPRESSION_OF_GENE_X_BY_IHC, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.EXPRESSION_OF_GENE_X_BY_IHC_OF_EXACTLY_Y, FunctionInput.ONE_STRING_ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.EXPRESSION_OF_GENE_X_BY_IHC_OF_AT_LEAST_Y, FunctionInput.ONE_STRING_ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.PD_L1_SCORE_CPS_OF_AT_LEAST_X, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.PD_L1_SCORE_CPS_OF_AT_MOST_X, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.PD_L1_SCORE_TPS_OF_AT_MOST_X, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.MOLECULAR_RESULTS_MUST_BE_AVAILABLE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.MOLECULAR_RESULTS_MUST_BE_AVAILABLE_FOR_GENE_X, FunctionInput.ONE_STRING);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_LEUKOCYTES_ABS_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LEUKOCYTES_ABS_LLN_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LYMPHOCYTES_ABS_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LYMPHOCYTES_CELLS_PER_MM3_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_NEUTROPHILS_ABS_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_THROMBOCYTES_ABS_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HEMOGLOBIN_G_PER_DL_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HEMOGLOBIN_MMOL_PER_L_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_INR_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_PT_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_APTT_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_PTT_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_ALBUMIN_G_PER_DL_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_ALBUMIN_LLN_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_ASAT_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_ALAT_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_ALP_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_TOTAL_BILIRUBIN_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_TOTAL_BILIRUBIN_UMOL_PER_L_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_DIRECT_BILIRUBIN_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_DIRECT_BILIRUBIN_PERCENTAGE_OF_TOTAL_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CREATININE_MG_PER_DL_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CREATININE_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_EGFR_CKD_EPI_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_EGFR_MDRD_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CREATININE_CLEARANCE_CG_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_BNP_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_TROPONIN_IT_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_TRIGLYCERIDE_MMOL_PER_L_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CALCIUM_MG_PER_DL_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CALCIUM_MMOL_PER_L_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_IONIZED_CALCIUM_MMOL_PER_L_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CORRECTED_CALCIUM_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CALCIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CORRECTED_CALCIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_MAGNESIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CORRECTED_MAGNESIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_PHOSPHORUS_WITHIN_INSTITUTIONAL_NORMAL_LIMITS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CORRECTED_PHOSPHORUS_WITHIN_INSTITUTIONAL_NORMAL_LIMITS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_POTASSIUM_MMOL_PER_L_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_POTASSIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CORRECTED_POTASSIUM_WITHIN_INSTITUTIONAL_NORMAL_LIMITS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_SERUM_TESTOSTERONE_NG_PER_DL_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LDH_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_PHOSPHORUS_ULN_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_AFP_ULN_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CA125_ULN_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HCG_ULN_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_TOTAL_PROTEIN_IN_URINE_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_TOTAL_PROTEIN_IN_24H_URINE_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_GLUCOSE_PL_MMOL_PER_L_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_SPECIFIC_CONDITION_WITH_DOID_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_SPECIFIC_CONDITION_X_BY_NAME, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_AUTOIMMUNE_DISEASE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_CARDIAC_DISEASE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_CARDIOVASCULAR_DISEASE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_GASTROINTESTINAL_DISEASE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_IMMUNE_SYSTEM_DISEASE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_LIVER_DISEASE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_LUNG_DISEASE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_MYOCARDIAL_INFARCT, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_STROKE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_TIA, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_VASCULAR_DISEASE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_SEVERE_CONCOMITANT_CONDITION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_ORGAN_TRANSPLANT, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_GILBERT_DISEASE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HYPERTENSION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_DIABETES, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_POTENTIAL_ABSORPTION_DIFFICULTIES, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_POTENTIAL_ORAL_MEDICATION_DIFFICULTIES, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_POTENTIAL_CONTRAINDICATION_TO_CT, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_POTENTIAL_CONTRAINDICATION_TO_MRI, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.IS_IN_DIALYSIS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_ADEQUATE_VEIN_ACCESS_FOR_LEUKAPHERESIS, FunctionInput.NONE);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_CARDIAC_ARRHYTHMIA, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CARDIAC_ARRHYTHMIA_OF_TYPE_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LVEF_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LVEF_OF_AT_LEAST_X_IF_KNOWN, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_QTC_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_QTCF_OF_AT_MOST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_LONG_QT_SYNDROME, FunctionInput.NONE);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_ACTIVE_INFECTION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_EBV_INFECTION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_HEPATITIS_A_INFECTION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_HEPATITIS_B_INFECTION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_HEPATITIS_C_INFECTION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_HIV_INFECTION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_HTLV_INFECTION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_CYTOMEGALOVIRUS_INFECTION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_KNOWN_TUBERCOLOSIS_INFECTION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_CURRENT_COVID_19_INFECTION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.IS_FULLY_VACCINATED_AGAINST_COVID_19, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.ADHERENCE_TO_PROTOCOL_REGARDING_ATTENUATED_VACCINE_USE, FunctionInput.NONE);

        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_NAME_X_MEDICATION, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_CATEGORY_X_MEDICATION, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_ANTICOAGULANT_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_AZOLE_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_BONE_RESORPTIVE_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_CORTICOSTEROID_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_GONADORELIN_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_COUMARIN_DERIVATIVE_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_DISEASE_MODIFYING_AGENTS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_IMMUNOSUPPRESSANT_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_OAT3_INHIBITORS_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_PAIN_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_PROHIBITED_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_POTENTIALLY_QT_PROLONGATING_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_COLONY_STIMULATING_FACTORS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_CYP_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_OATP_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_BCRP, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.CURRENTLY_GETS_MEDICATION_INHIBITING_OR_INDUCING_PGP, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_STABLE_ANTICOAGULANT_MEDICATION_DOSING, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_STABLE_PAIN_MEDICATION_DOSING, FunctionInput.NONE);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_RECEIVED_DRUG_X_CANCER_THERAPY_WITHIN_Y_WEEKS, FunctionInput.ONE_STRING_ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_RECEIVED_CATEGORY_X_CANCER_THERAPY_WITHIN_Y_WEEKS, FunctionInput.ONE_STRING_ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_RECEIVED_RADIOTHERAPY_WITHIN_X_WEEKS, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_WITHIN_X_WEEKS, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_EXCL_CATEGORY_X_WITHIN_Y_WEEKS,
                FunctionInput.ONE_STRING_ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_WITHIN_X_WEEKS_Y_HALF_LIVES, FunctionInput.TWO_INTEGERS);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_RECEIVED_ANY_ANTI_CANCER_THERAPY_EXCL_CATEGORY_X_WITHIN_Y_WEEKS_Z_HALF_LIVES,
                FunctionInput.ONE_STRING_TWO_INTEGERS);
        RULE_INPUT_MAP.put(EligibilityRule.WILL_REQUIRE_ANY_ANTICANCER_THERAPY_DURING_TRIAL, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_RECEIVED_HERBAL_MEDICATION_OR_DIETARY_SUPPLEMENTS_WITHIN_X_WEEKS, FunctionInput.ONE_INTEGER);

        RULE_INPUT_MAP.put(EligibilityRule.IS_BREASTFEEDING, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.IS_PREGNANT, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.USES_ADEQUATE_ANTICONCEPTION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.ADHERES_TO_SPERM_OR_EGG_DONATION_PRESCRIPTIONS, FunctionInput.NONE);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_COMPLICATION_X, FunctionInput.ONE_STRING);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_ALLERGY_OF_NAME_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_ALLERGY_BELONGING_TO_DOID_X, FunctionInput.ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_ALLERGY_TO_TAXANE, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_ALLERGY_RELATED_TO_STUDY_MEDICATION, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HISTORY_OF_ANAPHYLAXIS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_EXPERIENCED_IMMUNE_RELATED_ADVERSE_EVENTS, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_TOXICITY_OF_AT_LEAST_GRADE_X, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_TOXICITY_OF_AT_LEAST_GRADE_X_IN_Y, FunctionInput.ONE_INTEGER_ONE_STRING);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_TOXICITY_OF_AT_LEAST_GRADE_X_IGNORING_Y, FunctionInput.ONE_INTEGER_MANY_STRINGS);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_SBP_MMHG_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_DBP_MMHG_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_PULSE_OXYMETRY_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_BODY_WEIGHT_OF_AT_LEAST_X, FunctionInput.ONE_DOUBLE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_RESTING_HEART_RATE_BETWEEN_X_AND_Y, FunctionInput.TWO_DOUBLES);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_ERYTHROCYTE_TRANSFUSION_WITHIN_LAST_X_WEEKS, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_THROMBOCYTE_TRANSFUSION_WITHIN_LAST_X_WEEKS, FunctionInput.ONE_INTEGER);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_RECENT_SURGERY, FunctionInput.NONE);
        RULE_INPUT_MAP.put(EligibilityRule.HAS_HAD_SURGERY_WITHIN_LAST_X_WEEKS, FunctionInput.ONE_INTEGER);

        RULE_INPUT_MAP.put(EligibilityRule.HAS_SMOKED_WITHIN_X_MONTHS, FunctionInput.ONE_INTEGER);
        RULE_INPUT_MAP.put(EligibilityRule.IS_ABLE_AND_WILLING_TO_NOT_USE_CONTACT_LENSES, FunctionInput.NONE);
    }

    private FunctionInputMapping() {
    }
}
