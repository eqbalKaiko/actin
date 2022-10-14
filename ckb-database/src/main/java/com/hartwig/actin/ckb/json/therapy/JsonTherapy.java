package com.hartwig.actin.ckb.json.therapy;

import java.time.LocalDate;
import java.util.List;

import com.hartwig.actin.ckb.json.CkbJsonObject;
import com.hartwig.actin.ckb.json.common.ClinicalTrialInfo;
import com.hartwig.actin.ckb.json.common.DescriptionInfo;
import com.hartwig.actin.ckb.json.common.DrugInfo;
import com.hartwig.actin.ckb.json.common.EvidenceInfo;
import com.hartwig.actin.ckb.json.common.GlobalApprovalStatusInfo;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class JsonTherapy implements CkbJsonObject {

    public abstract int id();

    @NotNull
    public abstract String therapyName();

    @Nullable
    public abstract List<String> synonyms();

    @NotNull
    public abstract List<DescriptionInfo> descriptions();

    @NotNull
    public abstract LocalDate createDate();

    @Nullable
    public abstract LocalDate updateDate();

    @NotNull
    public abstract List<EvidenceInfo> evidence();

    @NotNull
    public abstract List<ClinicalTrialInfo> clinicalTrials();

    @NotNull
    public abstract List<DrugInfo> drugs();

    @NotNull
    public abstract List<GlobalApprovalStatusInfo> globalApprovalStatuses();
}
