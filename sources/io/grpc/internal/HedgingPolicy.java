package io.grpc.internal;

import _COROUTINE._BOUNDARY;
import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.collect.ImmutableSet;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class HedgingPolicy {
    final long hedgingDelayNanos;
    final int maxAttempts;
    final Set nonFatalStatusCodes;

    public HedgingPolicy(int i, long j, Set set) {
        this.maxAttempts = i;
        this.hedgingDelayNanos = j;
        this.nonFatalStatusCodes = ImmutableSet.copyOf((Collection) set);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            HedgingPolicy hedgingPolicy = (HedgingPolicy) obj;
            if (this.maxAttempts == hedgingPolicy.maxAttempts && this.hedgingDelayNanos == hedgingPolicy.hedgingDelayNanos && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.nonFatalStatusCodes, hedgingPolicy.nonFatalStatusCodes)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.maxAttempts), Long.valueOf(this.hedgingDelayNanos), this.nonFatalStatusCodes});
    }

    public final String toString() {
        MoreObjects$ToStringHelper add = ContextDataProvider.toStringHelper(this).add("maxAttempts", this.maxAttempts).add("hedgingDelayNanos", this.hedgingDelayNanos);
        add.addHolder$ar$ds("nonFatalStatusCodes", this.nonFatalStatusCodes);
        return add.toString();
    }
}
