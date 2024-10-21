package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.common.util.concurrent.ListenableFuture;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface CustomDurationMetricService {
    ListenableFuture record(NoPiiString noPiiString, long j, long j2, ExtensionMetric$MetricExtension extensionMetric$MetricExtension);
}
