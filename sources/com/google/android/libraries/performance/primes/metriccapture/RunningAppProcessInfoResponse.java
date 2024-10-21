package com.google.android.libraries.performance.primes.metriccapture;

import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RunningAppProcessInfoResponse {
    public final boolean getStatus;
    private final ImmutableList runningAppProcessInfosInternal;

    public RunningAppProcessInfoResponse() {
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof RunningAppProcessInfoResponse) {
            RunningAppProcessInfoResponse runningAppProcessInfoResponse = (RunningAppProcessInfoResponse) obj;
            if (this.getStatus == runningAppProcessInfoResponse.getStatus() && ContextDataProvider.equalsImpl(this.runningAppProcessInfosInternal, runningAppProcessInfoResponse.runningAppProcessInfosInternal())) {
                return true;
            }
        }
        return false;
    }

    public final ImmutableList getRunningAppProcessInfos() {
        if (getStatus()) {
            return runningAppProcessInfosInternal();
        }
        throw new IllegalStateException("Failed to get RunningAppProcessInfos, check status first.");
    }

    public final boolean getStatus() {
        return this.getStatus;
    }

    public final int hashCode() {
        int i;
        if (true != this.getStatus) {
            i = 1237;
        } else {
            i = 1231;
        }
        return ((i ^ 1000003) * 1000003) ^ this.runningAppProcessInfosInternal.hashCode();
    }

    public final ImmutableList runningAppProcessInfosInternal() {
        return this.runningAppProcessInfosInternal;
    }

    public final String toString() {
        return "RunningAppProcessInfoResponse{getStatus=" + this.getStatus + ", runningAppProcessInfosInternal=" + this.runningAppProcessInfosInternal.toString() + "}";
    }

    public RunningAppProcessInfoResponse(boolean z, ImmutableList immutableList) {
        this();
        this.getStatus = z;
        if (immutableList == null) {
            throw new NullPointerException("Null runningAppProcessInfosInternal");
        }
        this.runningAppProcessInfosInternal = immutableList;
    }
}
