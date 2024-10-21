package com.google.android.gms.usagereporting.internal;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.usagereporting.UsageReportingOptInOptions;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OptInOptionsResultImpl implements Result {
    public final UsageReportingOptInOptions optInOptions;
    private final Status status;

    public OptInOptionsResultImpl(Status status, UsageReportingOptInOptions usageReportingOptInOptions) {
        this.status = status;
        this.optInOptions = usageReportingOptInOptions;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        throw null;
    }

    public final String toString() {
        boolean z;
        if (this.optInOptions.optInUsageReporting == 1) {
            z = true;
        } else {
            z = false;
        }
        return String.format("OptInOptionsResultImpl[%s]", Boolean.valueOf(z));
    }
}
