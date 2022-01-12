package com.hartwig.actin.algo.evaluation.bloodpressure;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import com.google.common.collect.Lists;
import com.hartwig.actin.ImmutablePatientRecord;
import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.TestDataFactory;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.clinical.datamodel.ImmutableClinicalRecord;
import com.hartwig.actin.clinical.datamodel.ImmutableVitalFunction;
import com.hartwig.actin.clinical.datamodel.TestClinicalDataFactory;
import com.hartwig.actin.clinical.datamodel.VitalFunction;
import com.hartwig.actin.clinical.datamodel.VitalFunctionCategory;

import org.apache.logging.log4j.util.Strings;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

public class HasSufficientBloodPressureTest {

    private static final LocalDate REFERENCE_DATE = LocalDate.of(2020, 11, 19);

    @Test
    public void canEvaluate() {
        BloodPressureCategory category = BloodPressureCategory.SYSTOLIC;
        HasSufficientBloodPressure function = new HasSufficientBloodPressure(category, 100);
        List<VitalFunction> vitalFunctions = Lists.newArrayList();

        assertEquals(Evaluation.UNDETERMINED, function.evaluate(withVitalFunctions(vitalFunctions)));

        vitalFunctions.add(builder().date(REFERENCE_DATE).subcategory(category.display()).value(110).build());
        assertEquals(Evaluation.PASS, function.evaluate(withVitalFunctions(vitalFunctions)));

        // Fail when the average falls below 100
        vitalFunctions.add(builder().date(REFERENCE_DATE.minusDays(1)).subcategory(category.display()).value(70).build());
        assertEquals(Evaluation.FAIL, function.evaluate(withVitalFunctions(vitalFunctions)));

        // Succeed again when the average goes above 100
        vitalFunctions.add(builder().date(REFERENCE_DATE.minusDays(2)).subcategory(category.display()).value(110).build());
        vitalFunctions.add(builder().date(REFERENCE_DATE.minusDays(3)).subcategory(category.display()).value(110).build());
        vitalFunctions.add(builder().date(REFERENCE_DATE.minusDays(4)).subcategory(category.display()).value(110).build());
        assertEquals(Evaluation.PASS, function.evaluate(withVitalFunctions(vitalFunctions)));

        // Still succeed since we only take X most recent measures.
        vitalFunctions.add(builder().date(REFERENCE_DATE.minusDays(5)).subcategory(category.display()).value(20).build());
        vitalFunctions.add(builder().date(REFERENCE_DATE.minusDays(6)).subcategory(category.display()).value(20).build());
        vitalFunctions.add(builder().date(REFERENCE_DATE.minusDays(7)).subcategory(category.display()).value(20).build());
        assertEquals(Evaluation.PASS, function.evaluate(withVitalFunctions(vitalFunctions)));
    }

    @Test
    public void canFilterOnCategory() {
        HasSufficientBloodPressure function = new HasSufficientBloodPressure(BloodPressureCategory.SYSTOLIC, 100);
        List<VitalFunction> bloodPressures = Lists.newArrayList();

        bloodPressures.add(builder().date(REFERENCE_DATE).subcategory(BloodPressureCategory.DIASTOLIC.display()).value(110).build());
        assertEquals(Evaluation.UNDETERMINED, function.evaluate(withVitalFunctions(bloodPressures)));
    }

    @NotNull
    private static PatientRecord withVitalFunctions(@NotNull List<VitalFunction> vitalFunctions) {
        return ImmutablePatientRecord.builder()
                .from(TestDataFactory.createMinimalTestPatientRecord())
                .clinical(ImmutableClinicalRecord.builder()
                        .from(TestClinicalDataFactory.createMinimalTestClinicalRecord())
                        .vitalFunctions(vitalFunctions)
                        .build())
                .build();
    }

    @NotNull
    private static ImmutableVitalFunction.Builder builder() {
        return ImmutableVitalFunction.builder()
                .date(LocalDate.of(2017, 7, 7))
                .category(VitalFunctionCategory.BLOOD_PRESSURE)
                .subcategory(Strings.EMPTY)
                .value(0D)
                .unit(Strings.EMPTY);
    }
}