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
public final class RetryPolicy {
    final double backoffMultiplier;
    final long initialBackoffNanos;
    final int maxAttempts;
    final long maxBackoffNanos;
    final Long perAttemptRecvTimeoutNanos;
    final Set retryableStatusCodes;

    public RetryPolicy(int i, long j, long j2, double d, Long l, Set set) {
        this.maxAttempts = i;
        this.initialBackoffNanos = j;
        this.maxBackoffNanos = j2;
        this.backoffMultiplier = d;
        this.perAttemptRecvTimeoutNanos = l;
        this.retryableStatusCodes = ImmutableSet.copyOf((Collection) set);
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof RetryPolicy)) {
            return false;
        }
        RetryPolicy retryPolicy = (RetryPolicy) obj;
        if (this.maxAttempts != retryPolicy.maxAttempts || this.initialBackoffNanos != retryPolicy.initialBackoffNanos || this.maxBackoffNanos != retryPolicy.maxBackoffNanos || Double.compare(this.backoffMultiplier, retryPolicy.backoffMultiplier) != 0 || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.perAttemptRecvTimeoutNanos, retryPolicy.perAttemptRecvTimeoutNanos) || !_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.retryableStatusCodes, retryPolicy.retryableStatusCodes)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.maxAttempts), Long.valueOf(this.initialBackoffNanos), Long.valueOf(this.maxBackoffNanos), Double.valueOf(this.backoffMultiplier), this.perAttemptRecvTimeoutNanos, this.retryableStatusCodes});
    }

    public final String toString() {
        MoreObjects$ToStringHelper add = ContextDataProvider.toStringHelper(this).add("maxAttempts", this.maxAttempts).add("initialBackoffNanos", this.initialBackoffNanos).add("maxBackoffNanos", this.maxBackoffNanos);
        add.addUnconditionalHolder$ar$ds("backoffMultiplier", String.valueOf(this.backoffMultiplier));
        add.addHolder$ar$ds("perAttemptRecvTimeoutNanos", this.perAttemptRecvTimeoutNanos);
        add.addHolder$ar$ds("retryableStatusCodes", this.retryableStatusCodes);
        return add.toString();
    }
}
