package com.hartwig.actin.algo.evaluation.laboratory;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.datamodel.ImmutableEvaluation;
import com.hartwig.actin.clinical.datamodel.BodyWeight;
import com.hartwig.actin.clinical.datamodel.Gender;
import com.hartwig.actin.clinical.datamodel.LabValue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class HasSufficientDerivedCreatinineClearance implements LabEvaluationFunction {

    private static final Logger LOGGER = LogManager.getLogger(HasSufficientDerivedCreatinineClearance.class);

    private static final double DEFAULT_MIN_WEIGHT_FEMALE = 50D;
    private static final double DEFAULT_MIN_WEIGHT_MALE = 65D;

    private final int referenceYear;
    @NotNull
    private final CreatinineClearanceMethod method;
    private final double minCreatinineClearance;

    HasSufficientDerivedCreatinineClearance(final int referenceYear, @NotNull final CreatinineClearanceMethod method,
            final double minCreatinineClearance) {
        this.referenceYear = referenceYear;
        this.method = method;
        this.minCreatinineClearance = minCreatinineClearance;
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record, @NotNull LabValue labValue) {
        switch (method) {
            case EGFR_MDRD:
                return evaluateMDRD(record, labValue);
            case EGFR_CKD_EPI:
                return evaluateCKDEPI(record, labValue);
            case COCKCROFT_GAULT:
                return evaluateCockcroftGault(record, labValue);
            default: {
                LOGGER.warn("No creatinine clearance function implemented for '{}'", method);
                return ImmutableEvaluation.builder().result(EvaluationResult.NOT_IMPLEMENTED).build();
            }
        }
    }

    @NotNull
    private Evaluation evaluateMDRD(@NotNull PatientRecord record, @NotNull LabValue creatinine) {
        return evaluateValues("MDRD", toMDRD(record, creatinine), creatinine.comparator());
    }

    @NotNull
    @VisibleForTesting
    List<Double> toMDRD(@NotNull PatientRecord record, @NotNull LabValue creatinine) {
        List<Double> mdrdValues = Lists.newArrayList();

        int age = referenceYear - record.clinical().patient().birthYear();

        double base = 175 * Math.pow(creatinine.value() / 88.4, -1.154) * Math.pow(age, -0.203);

        double adjusted = base;
        if (record.clinical().patient().gender() == Gender.FEMALE) {
            adjusted = base * 0.742;
        }

        mdrdValues.add(adjusted);
        mdrdValues.add(adjusted * 1.212);

        return mdrdValues;
    }

    @NotNull
    private Evaluation evaluateCKDEPI(@NotNull PatientRecord record, @NotNull LabValue creatinine) {
        return evaluateValues("CKDEPI", toCKDEPI(record, creatinine), creatinine.comparator());
    }

    @NotNull
    @VisibleForTesting
    List<Double> toCKDEPI(@NotNull PatientRecord record, @NotNull LabValue creatinine) {
        List<Double> ckdepiValues = Lists.newArrayList();

        int age = referenceYear - record.clinical().patient().birthYear();

        boolean isFemale = record.clinical().patient().gender() == Gender.FEMALE;
        double correction = isFemale ? 61.9 : 79.6;
        double power = isFemale ? -0.329 : -0.411;

        double factor1 = Math.pow(Math.min(creatinine.value() / correction, 1), power);
        double factor2 = Math.pow(Math.max(creatinine.value() / correction, 1), -1.209);

        double base = 141 * factor1 * factor2 * Math.pow(0.993, age);

        double adjusted = base;
        if (isFemale) {
            adjusted = base * 1.018;
        }

        ckdepiValues.add(adjusted);
        ckdepiValues.add(adjusted * 1.159);

        return ckdepiValues;
    }

    @NotNull
    private Evaluation evaluateCockcroftGault(@NotNull PatientRecord record, @NotNull LabValue creatinine) {
        boolean isFemale = record.clinical().patient().gender() == Gender.FEMALE;

        Double weight = determineWeight(record.clinical().bodyWeights());
        boolean weightIsKnown = weight != null;
        if (!weightIsKnown) {
            weight = isFemale ? DEFAULT_MIN_WEIGHT_FEMALE : DEFAULT_MIN_WEIGHT_MALE;
        }

        int age = referenceYear - record.clinical().patient().birthYear();

        double base = (140 - age) * weight / (0.81 * creatinine.value());

        double genderCorrected = isFemale ? base * 0.85 : base;

        EvaluationResult result = LabEvaluation.evaluateVersusMinValue(genderCorrected, creatinine.comparator(), minCreatinineClearance);

        if (!weightIsKnown) {
            if (result == EvaluationResult.FAIL) {
                result = EvaluationResult.UNDETERMINED;
            } else if (result == EvaluationResult.PASS) {
                result = EvaluationResult.WARN;
            }
        }

        return toEvaluation(result, "Cockcroft-Gault");
    }

    @Nullable
    private static Double determineWeight(@NotNull List<BodyWeight> bodyWeights) {
        Double weight = null;
        LocalDate mostRecentDate = null;
        for (BodyWeight bodyWeight : bodyWeights) {
            if (mostRecentDate == null || bodyWeight.date().isAfter(mostRecentDate)) {
                weight = bodyWeight.value();
                mostRecentDate = bodyWeight.date();
            }
        }

        return weight;
    }

    @NotNull
    private Evaluation evaluateValues(@NotNull String code, @NotNull List<Double> values, @NotNull String comparator) {
        Set<EvaluationResult> evaluations = Sets.newHashSet();
        for (Double value : values) {
            evaluations.add(LabEvaluation.evaluateVersusMinValue(value, comparator, minCreatinineClearance));
        }

        EvaluationResult result;
        if (evaluations.contains(EvaluationResult.FAIL)) {
            result = evaluations.contains(EvaluationResult.PASS) ? EvaluationResult.UNDETERMINED : EvaluationResult.FAIL;
        } else if (evaluations.contains(EvaluationResult.UNDETERMINED)) {
            result = EvaluationResult.UNDETERMINED;
        } else {
            // Every value should be pass.
            result = EvaluationResult.PASS;
        }

        return toEvaluation(result, code);
    }

    @NotNull
    private static Evaluation toEvaluation(@NotNull EvaluationResult result, @NotNull String code) {
        ImmutableEvaluation.Builder builder = ImmutableEvaluation.builder().result(result);
        if (result == EvaluationResult.FAIL) {
            builder.addFailSpecificMessages(code + " is insufficient");
        } else if (result == EvaluationResult.UNDETERMINED) {
            builder.addUndeterminedSpecificMessages(code + " evaluation led to ambiguous results");
        } else if (result.isPass()) {
            builder.addPassSpecificMessages(code + " sufficient");
        }
        return builder.build();
    }
}
