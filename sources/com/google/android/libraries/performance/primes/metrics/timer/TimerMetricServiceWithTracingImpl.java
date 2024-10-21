package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.core.PrimesDuration;
import com.google.android.libraries.performance.primes.metrics.trace.TraceMetricServiceImpl;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.internal.RetriableStream;
import java.util.concurrent.Callable;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimerMetricServiceWithTracingImpl extends TimerMetricService implements MetricService, CustomDurationMetricService {
    private static final Callable EMPTY_CALLABLE = new TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0(0);
    private final TimerMetricServiceImpl timerMetricService;
    private final TraceMetricServiceImpl traceMetricService$ar$class_merging;

    /* JADX WARN: Type inference failed for: r1v1, types: [javax.inject.Provider, java.lang.Object] */
    public TimerMetricServiceWithTracingImpl(TimerMetricServiceImpl timerMetricServiceImpl, Optional optional) {
        this.timerMetricService = timerMetricServiceImpl;
        this.traceMetricService$ar$class_merging = (TraceMetricServiceImpl) optional.get().get();
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.CustomDurationMetricService
    public final ListenableFuture record(NoPiiString noPiiString, long j, long j2, ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
        return this.timerMetricService.record(noPiiString, j, j2, extensionMetric$MetricExtension);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerMetricService
    public final synchronized TimerEvent start() {
        TimerEvent start;
        start = this.timerMetricService.start();
        if (!TimerEvent.isEmpty(start) && this.traceMetricService$ar$class_merging.beginTracingIfNotStarted$ar$ds()) {
            start.hasTrace = true;
        }
        return start;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerMetricService
    public final ListenableFuture stopAsFuture$ar$edu(TimerEvent timerEvent, NoPiiString noPiiString, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, int i) {
        ListenableFuture listenableFuture;
        ListenableFuture[] listenableFutureArr = new ListenableFuture[2];
        listenableFutureArr[0] = this.timerMetricService.stopAsFuture$ar$edu(timerEvent, noPiiString, extensionMetric$MetricExtension, 1);
        if (timerEvent != null && !TimerEvent.isEmpty(timerEvent)) {
            String str = noPiiString.value;
            if (timerEvent.hasTrace) {
                listenableFuture = this.traceMetricService$ar$class_merging.endTracingIfStarted$ar$ds(str);
            } else {
                PrimesDuration primesDuration = timerEvent.primesDuration;
                TraceMetricServiceImpl.sideLoadSpan$ar$ds(str, primesDuration.startInstant.elapsedRealtimeMs, timerEvent.getRealtimeDurationMillis());
                listenableFuture = ImmediateFuture.NULL;
            }
        } else {
            listenableFuture = ImmediateFuture.NULL;
        }
        listenableFutureArr[1] = listenableFuture;
        return new RetriableStream.HedgingPlan(true, ImmutableList.copyOf(listenableFutureArr)).call(EMPTY_CALLABLE, DirectExecutor.INSTANCE);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public final /* synthetic */ void onApplicationStartup() {
    }
}
