package com.hartwig.actin.clinical.feed.medication;

import java.util.Map;

import com.google.common.annotations.VisibleForTesting;
import com.hartwig.actin.clinical.feed.FeedEntryCreator;
import com.hartwig.actin.clinical.feed.FeedUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MedicationEntryCreator implements FeedEntryCreator<MedicationEntry> {

    public MedicationEntryCreator() {
    }

    @NotNull
    @Override
    public MedicationEntry fromParts(@NotNull Map<String, Integer> fieldIndexMap, @NotNull String[] parts) {
        return ImmutableMedicationEntry.builder()
                .subject(parts[fieldIndexMap.get("subject")])
                .medicationReferenceMedicationValue(parts[fieldIndexMap.get("medicationReference_Medication_value")])
                .medicationReferenceMedicationSystem(parts[fieldIndexMap.get("medicationReference_Medication_system")])
                .codeText(parts[fieldIndexMap.get("code_text")])
                .code5ATCDisplay(parts[fieldIndexMap.get("code5_ATC_display")])
                .indicationDisplay(parts[fieldIndexMap.get("indication_display")])
                .dosageInstructionDoseQuantityUnit(parts[fieldIndexMap.get("dosageInstruction_doseQuantity_unit")])
                .dosageInstructionDoseQuantityValue(FeedUtil.parseDouble(parts[fieldIndexMap.get("dosageInstruction_doseQuantity_value")]))
                .dosageInstructionFrequencyUnit(parts[fieldIndexMap.get("dosageInstruction_frequency_unit")])
                .dosageInstructionFrequencyValue(FeedUtil.parseOptionalDouble(parts[fieldIndexMap.get("dosageInstruction_frequency_value")]))
                .dosageInstructionMaxDosePerAdministration(FeedUtil.parseOptionalDouble(parts[fieldIndexMap.get(
                        "dosageInstruction_maxDosePerAdministration")]))
                .dosageInstructionPatientInstruction(parts[fieldIndexMap.get("dosageInstruction_patientInstruction")])
                .dosageInstructionAsNeededDisplay(parts[fieldIndexMap.get("dosageInstruction_asNeeded_display")])
                .dosageInstructionPeriodBetweenDosagesUnit(parts[fieldIndexMap.get("dosageInstruction_period_between_dosages_unit")])
                .dosageInstructionPeriodBetweenDosagesValue(FeedUtil.parseOptionalDouble(parts[fieldIndexMap.get(
                        "dosageInstruction_period_between_dosages_value")]))
                .dosageInstructionText(parts[fieldIndexMap.get("dosageInstruction_text")])
                .status(parts[fieldIndexMap.get("status")])
                .active(isActive(parts[fieldIndexMap.get("active")]))
                .dosageDoseValue(parts[fieldIndexMap.get("dosage_dose_value")])
                .dosageRateQuantityUnit(parts[fieldIndexMap.get("dosage_rateQuantity_unit")])
                .dosageDoseUnitDisplayOriginal(parts[fieldIndexMap.get("dosage_dose_unit_display_original")])
                .periodOfUseValuePeriodStart(FeedUtil.parseDate(parts[fieldIndexMap.get("periodOfUse_valuePeriod_start")]))
                .periodOfUseValuePeriodEnd(FeedUtil.parseOptionalDate(parts[fieldIndexMap.get("periodOfUse_valuePeriod_end")]))
                .stopTypeDisplay(parts[fieldIndexMap.get("stopType_display")])
                .categoryMedicationRequestCategoryDisplay(parts[fieldIndexMap.get("category_medicationRequestCategory_display")])
                .categoryMedicationRequestCategoryCodeOriginal(parts[fieldIndexMap.get("category_medicationRequestCategory_code_original")])
                .build();
    }

    @Nullable
    @VisibleForTesting
    static Boolean isActive(@NotNull String activeField) {
        // This field seems like a left-join where either the value is "active" or "null" in which case we can assume false.
        if (activeField.equalsIgnoreCase("null")) {
            return false;
        } else if (activeField.equals("active")) {
            return true;
        }

        return null;
    }
}
