package com.google.android.libraries.performance.primes.metrics.trace;

import _COROUTINE._BOUNDARY;
import android.text.TextUtils;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda1;
import com.google.android.libraries.performance.primes.PrimesConfigurations$Builder$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricService;
import com.google.android.libraries.performance.primes.metrics.network.NetworkMetricServiceImpl$$ExternalSyntheticLambda3;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySampler;
import com.google.android.libraries.performance.primes.sampling.RateLimiting;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicReference;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraceMetricServiceImpl extends ApplicationExitMetricService implements MetricService {
    private final ListeningScheduledExecutorService executorService;
    public final MetricRecorder metricRecorder;
    public final AtomicReference nonTikTokSampler;
    public final AppLifecycleMonitor probabilitySamplerFactory$ar$class_merging$ar$class_merging;
    public final RateLimiting rateLimiter;
    public final Lazy tikTokTraceConfigurationsProvider;
    public final Lazy traceConfigurationsProvider;

    public TraceMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy lazy, Lazy lazy2, Provider provider, AppLifecycleMonitor appLifecycleMonitor) {
        super((char[]) null);
        AtomicReference atomicReference = new AtomicReference();
        this.nonTikTokSampler = atomicReference;
        this.executorService = listeningScheduledExecutorService;
        this.tikTokTraceConfigurationsProvider = lazy;
        this.traceConfigurationsProvider = lazy2;
        this.probabilitySamplerFactory$ar$class_merging$ar$class_merging = appLifecycleMonitor;
        this.metricRecorder = metricRecorderFactory.create(listeningScheduledExecutorService, new Lazy() { // from class: com.google.android.libraries.performance.primes.metrics.trace.TraceMetricServiceImpl$$ExternalSyntheticLambda3
            @Override // dagger.Lazy
            public final Object get() {
                return TraceConfigurations.newBuilder().setEnabled(true).build();
            }
        }, provider);
        this.rateLimiter = new RateLimiting(new PrimesConfigurations$Builder$$ExternalSyntheticLambda0(lazy, 5), RateLimiting.CLOCK$ar$class_merging$ar$class_merging$ar$class_merging);
        atomicReference.set(appLifecycleMonitor.create(1.0f));
    }

    public static final void sideLoadSpan$ar$ds(String str, long j, long j2) {
        TraceData traceData;
        if (!TextUtils.isEmpty(str) && j2 > 0 && (traceData = (TraceData) Tracer.traceData.get()) != null && traceData.rootSpan.startMs <= j) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/trace/TraceData", "sideLoadSpan", BrailleInputEvent.CMD_LINK_PREVIOUS, "TraceData.java")).log("Sideload span: %s. startMs: %d, durationMs: %d", str, Long.valueOf(j), Long.valueOf(j2));
            SpanEvent spanEvent = new SpanEvent(str, 1, j, j + j2, Thread.currentThread().getId(), 4);
            synchronized (traceData.timerSpans) {
                traceData.timerSpans.add(spanEvent);
            }
            traceData.incrementAndGetSpanCount();
        }
    }

    public final boolean beginTracingIfNotStarted$ar$ds() {
        if (!((ProbabilitySampler) this.nonTikTokSampler.get()).isSampleAllowed()) {
            return false;
        }
        if (Tracer.traceData.get() == null && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_18(Tracer.traceData, new TraceData())) {
            Tracer.minSpanDurationMs = 5;
            Tracer.maxBufferSize = 1000;
            return true;
        }
        ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/trace/Tracer", "start", 66, "Tracer.java")).log("Ignore Tracer.start(), current active trace...");
        return false;
    }

    public final ListenableFuture endTracingIfStarted$ar$ds(String str) {
        if (true != TextUtils.isEmpty(null)) {
            str = null;
        }
        ContextDataProvider.checkState(!TextUtils.isEmpty(str));
        TraceData traceData = (TraceData) Tracer.traceData.getAndSet(null);
        if (traceData != null) {
            traceData.rootSpan.spanName = str;
        }
        if (traceData == null) {
            return ImmediateFuture.NULL;
        }
        return ContextDataProvider.submitAsync(new NetworkMetricServiceImpl$$ExternalSyntheticLambda3(this, traceData, 2, null), this.executorService);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public final void onApplicationStartup() {
        this.executorService.execute(new TrainingActivity$$ExternalSyntheticLambda1(this, 18));
    }
}
