package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NoopTimerMetricService extends TimerMetricService implements CustomDurationMetricService {
    @Override // com.google.android.libraries.performance.primes.metrics.timer.CustomDurationMetricService
    public final ListenableFuture record(NoPiiString noPiiString, long j, long j2, ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
        return ImmediateFuture.NULL;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerMetricService
    public final TimerEvent start() {
        return TimerEvent.EMPTY_TIMER;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerMetricService
    public final ListenableFuture stopAsFuture$ar$edu(TimerEvent timerEvent, NoPiiString noPiiString, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, int i) {
        return ImmediateFuture.NULL;
    }
}
