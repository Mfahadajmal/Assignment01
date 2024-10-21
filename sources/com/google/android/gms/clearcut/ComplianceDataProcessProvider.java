package com.google.android.gms.clearcut;

import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ComplianceDataProcessProvider {
    ComplianceProductData getCurrentComplianceProductData();

    ListenableFuture getCurrentComplianceSocsData();
}
