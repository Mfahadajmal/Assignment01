package io.grpc.internal;

import com.google.common.util.concurrent.Futures$$ExternalSyntheticLambda1;
import com.google.mlkit.logging.schema.OnDeviceImageLabelDetectionLogEvent;
import io.grpc.SynchronizationContext;
import io.grpc.util.MultiChildLoadBalancer;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BackoffPolicyRetryScheduler implements RetryScheduler {
    private static final Logger logger = Logger.getLogger(BackoffPolicyRetryScheduler.class.getName());
    public ExponentialBackoffPolicy policy$ar$class_merging;
    private final OnDeviceImageLabelDetectionLogEvent policyProvider$ar$class_merging$ar$class_merging$ar$class_merging;
    private final ScheduledExecutorService scheduledExecutorService;
    public MultiChildLoadBalancer.AcceptResolvedAddrRetVal scheduledHandle$ar$class_merging$ar$class_merging;
    private final SynchronizationContext syncContext;

    public BackoffPolicyRetryScheduler(OnDeviceImageLabelDetectionLogEvent onDeviceImageLabelDetectionLogEvent, ScheduledExecutorService scheduledExecutorService, SynchronizationContext synchronizationContext) {
        this.policyProvider$ar$class_merging$ar$class_merging$ar$class_merging = onDeviceImageLabelDetectionLogEvent;
        this.scheduledExecutorService = scheduledExecutorService;
        this.syncContext = synchronizationContext;
    }

    @Override // io.grpc.internal.RetryScheduler
    public final void reset() {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        this.syncContext.execute(new Futures$$ExternalSyntheticLambda1(this, 9));
    }

    @Override // io.grpc.internal.RetryScheduler
    public final void schedule(Runnable runnable) {
        this.syncContext.throwIfNotInThisSynchronizationContext();
        if (this.policy$ar$class_merging == null) {
            this.policy$ar$class_merging = new ExponentialBackoffPolicy();
        }
        MultiChildLoadBalancer.AcceptResolvedAddrRetVal acceptResolvedAddrRetVal = this.scheduledHandle$ar$class_merging$ar$class_merging;
        if (acceptResolvedAddrRetVal != null && acceptResolvedAddrRetVal.isPending()) {
            return;
        }
        long nextBackoffNanos = this.policy$ar$class_merging.nextBackoffNanos();
        this.scheduledHandle$ar$class_merging$ar$class_merging = this.syncContext.schedule$ar$class_merging$ar$class_merging(runnable, nextBackoffNanos, TimeUnit.NANOSECONDS, this.scheduledExecutorService);
        logger.logp(Level.FINE, "io.grpc.internal.BackoffPolicyRetryScheduler", "schedule", "Scheduling DNS resolution backoff for {0}ns", Long.valueOf(nextBackoffNanos));
    }
}
