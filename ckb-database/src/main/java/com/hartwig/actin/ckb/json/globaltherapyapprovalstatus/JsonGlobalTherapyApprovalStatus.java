package com.hartwig.actin.ckb.json.globaltherapyapprovalstatus;

import java.util.List;

import com.hartwig.actin.ckb.json.CkbJsonObject;
import com.hartwig.actin.ckb.json.common.GlobalApprovalStatusInfo;

import org.immutables.value.Value;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Value.Immutable
@Value.Style(passAnnotations = { NotNull.class, Nullable.class })
public abstract class JsonGlobalTherapyApprovalStatus implements CkbJsonObject {

    public abstract int totalCount();

    @NotNull
    public abstract List<GlobalApprovalStatusInfo> globalApprovalStatuses();
}
