package com.hartwig.actin.algo.evaluation.tumor;

import java.util.Set;

import com.hartwig.actin.PatientRecord;
import com.hartwig.actin.algo.datamodel.Evaluation;
import com.hartwig.actin.algo.datamodel.EvaluationResult;
import com.hartwig.actin.algo.evaluation.EvaluationFactory;
import com.hartwig.actin.algo.evaluation.EvaluationFunction;
import com.hartwig.actin.doid.DoidModel;
import com.hartwig.actin.treatment.input.datamodel.TumorTypeInput;

import org.jetbrains.annotations.NotNull;

public class HasCancerOfUnknownPrimary implements EvaluationFunction {

    static final String CANCER_DOID = "162";
    static final String ORGAN_SYSTEM_CANCER_DOID = "0050686";

    static final String CUP_PRIMARY_TUMOR_SUB_LOCATION = "CUP";

    @NotNull
    private final DoidModel doidModel;
    @NotNull
    private final TumorTypeInput categoryOfCUP;

    HasCancerOfUnknownPrimary(@NotNull final DoidModel doidModel, @NotNull final TumorTypeInput categoryOfCUP) {
        this.doidModel = doidModel;
        this.categoryOfCUP = categoryOfCUP;
    }

    @NotNull
    @Override
    public Evaluation evaluate(@NotNull PatientRecord record) {
        Set<String> tumorDoids = record.clinical().tumor().doids();

        if (!DoidEvaluationFunctions.hasConfiguredDoids(tumorDoids)) {
            return EvaluationFactory.unrecoverable()
                    .result(EvaluationResult.UNDETERMINED)
                    .addUndeterminedSpecificMessages("No tumor location/type configured for patient, undetermined if CUP")
                    .addUndeterminedGeneralMessages("Unconfigured tumor location/type")
                    .build();
        }

        boolean hasCorrectCUPCategory = DoidEvaluationFunctions.isOfExclusiveDoidType(doidModel, tumorDoids, categoryOfCUP.doid());
        boolean hasOrganSystemCancer = DoidEvaluationFunctions.isOfDoidType(doidModel, tumorDoids, ORGAN_SYSTEM_CANCER_DOID);

        if (hasCorrectCUPCategory && !hasOrganSystemCancer) {
            String tumorSubLocation = record.clinical().tumor().primaryTumorSubLocation();
            boolean isCUP = tumorSubLocation != null && tumorSubLocation.equals(CUP_PRIMARY_TUMOR_SUB_LOCATION);

            if (isCUP) {
                return EvaluationFactory.unrecoverable()
                        .result(EvaluationResult.PASS)
                        .addPassSpecificMessages("Patient has cancer of unknown primary (CUP) of type " + categoryOfCUP.display())
                        .addPassGeneralMessages("Tumor type is CUP (" + categoryOfCUP.display() + ")")
                        .build();
            } else {
                return EvaluationFactory.unrecoverable()
                        .result(EvaluationResult.WARN)
                        .addWarnSpecificMessages("Patient has cancer of type " + categoryOfCUP.display() + " but not a CUP")
                        .addWarnGeneralMessages("Tumor type may be CUP (" + categoryOfCUP.display() + ")")
                        .build();
            }
        }

        if (DoidEvaluationFunctions.isOfExactDoid(tumorDoids, CANCER_DOID)) {
            return EvaluationFactory.unrecoverable()
                    .result(EvaluationResult.UNDETERMINED)
                    .addUndeterminedSpecificMessages(
                            "Patient has tumor type 'cancer' configured, unknown tumor type and uncertain if actually CUP")
                    .addUndeterminedGeneralMessages("Undetermined CUP tumor type / if actually CUP")
                    .build();
        }

        return EvaluationFactory.unrecoverable()
                .result(EvaluationResult.FAIL)
                .addFailSpecificMessages("Patient has no cancer of unknown primary (CUP) of type " + categoryOfCUP.display())
                .addFailGeneralMessages("Tumor type is no CUP (" + categoryOfCUP.display() + ")")
                .build();
    }
}

