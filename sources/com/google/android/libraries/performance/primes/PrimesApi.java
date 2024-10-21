package com.google.android.libraries.performance.primes;

import com.google.android.libraries.performance.primes.metrics.network.NetworkEvent;
import com.google.android.libraries.performance.primes.metrics.timer.TimerEvent;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface PrimesApi {
    void initialize();

    void recordDuration(NoPiiString noPiiString, long j, long j2, ExtensionMetric$MetricExtension extensionMetric$MetricExtension);

    void recordNetwork(NetworkEvent networkEvent);

    void startCrashMonitor();

    void startMemoryMonitor();

    TimerEvent startTimer();

    void stopTimer$ar$edu$2eed496a_0(TimerEvent timerEvent, NoPiiString noPiiString, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, int i);
}
