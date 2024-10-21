package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.network.NetworkEvent;
import com.google.android.libraries.performance.primes.metrics.timer.TimerEvent;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
final class NoopPrimesApi implements PrimesApi {
    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final TimerEvent startTimer() {
        return TimerEvent.EMPTY_TIMER;
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void initialize() {
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void startCrashMonitor() {
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void startMemoryMonitor() {
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void recordNetwork(NetworkEvent networkEvent) {
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void recordDuration(NoPiiString noPiiString, long j, long j2, ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void stopTimer$ar$edu$2eed496a_0(TimerEvent timerEvent, NoPiiString noPiiString, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, int i) {
    }
}
