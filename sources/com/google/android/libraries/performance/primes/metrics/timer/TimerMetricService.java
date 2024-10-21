package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.common.util.concurrent.ListenableFuture;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class TimerMetricService {
    public abstract TimerEvent start();

    public abstract ListenableFuture stopAsFuture$ar$edu(TimerEvent timerEvent, NoPiiString noPiiString, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, int i);
}
