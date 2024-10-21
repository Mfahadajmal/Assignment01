package com.google.android.libraries.performance.primes.metrics.timer;

import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.core.PrimesDuration;
import com.google.android.libraries.performance.primes.metrics.core.PrimesInstant;
import com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySampler;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableSet;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import dagger.Lazy;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Locale;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$EndStatus;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$TimerMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimerMetricServiceImpl extends TimerMetricService implements MetricService, CustomDurationMetricService {
    static final ImmutableSet RESERVED_EVENT_NAMES = ImmutableSet.of((Object) "Cold startup", (Object) "Cold startup interactive", (Object) "Cold startup interactive before onDraw", (Object) "Warm startup", (Object) "Warm startup interactive", (Object) "Warm startup interactive before onDraw", (Object[]) new String[]{"Warm startup activity onStart", "Cold startup class loading", "Cold startup from process creation", "Cold startup interactive before onDraw from process creation", "Cold startup interactive from process creation"});
    public static final /* synthetic */ int TimerMetricServiceImpl$ar$NoOp = 0;
    private final Executor deferredExecutor;
    public final Lazy globalConfigurations;
    public final MetricRecorder metricRecorder;
    public final Supplier sampler;
    public final Lazy timerConfigurations;

    public TimerMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Executor executor, Lazy lazy, Lazy lazy2, Provider provider, AppLifecycleMonitor appLifecycleMonitor) {
        new ConcurrentHashMap();
        this.metricRecorder = metricRecorderFactory.create(DirectExecutor.INSTANCE, lazy, provider);
        this.deferredExecutor = executor;
        this.timerConfigurations = lazy;
        this.globalConfigurations = lazy2;
        this.sampler = ContextDataProvider.memoize(new FrameMetricServiceImpl$$ExternalSyntheticLambda0(appLifecycleMonitor, lazy, 2));
    }

    private final ListenableFuture recordSystemHealthMetricInBackground$ar$ds(final String str, final long j, final TimerEvent timerEvent, final ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
        return ContextDataProvider.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceImpl$$ExternalSyntheticLambda3
            public final /* synthetic */ boolean f$1 = true;
            public final /* synthetic */ boolean f$3 = true;

            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                long realtimeDurationMillis;
                int i;
                TimerMetricServiceImpl timerMetricServiceImpl = TimerMetricServiceImpl.this;
                if (!((ProbabilitySampler) timerMetricServiceImpl.sampler.get()).isSampleAllowed()) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/timer/TimerMetricServiceImpl", "lambda$recordSystemHealthMetricInBackground$3", 435, "TimerMetricServiceImpl.java")).log("TimerMetric not recorded, metric was rejected by sampling configuration.");
                    return ImmediateFuture.NULL;
                }
                TimerEvent timerEvent2 = timerEvent;
                long j2 = j;
                ((TimerConfigurations) timerMetricServiceImpl.timerConfigurations.get()).getPerEventConfigurationFlags();
                MetricRecorder metricRecorder = timerMetricServiceImpl.metricRecorder;
                Metric.Builder newBuilder = Metric.newBuilder();
                newBuilder.setIsEventNameConstant$ar$ds(true);
                newBuilder.sampleRatePermille = Long.valueOf(j2);
                Optional optional = (Optional) timerMetricServiceImpl.globalConfigurations.get();
                SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.createBuilder();
                boolean booleanValue = ((Boolean) optional.transform(new AccessibilityEventUtils$$ExternalSyntheticLambda0(18)).or((Object) false)).booleanValue();
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$TimerMetric.DEFAULT_INSTANCE.createBuilder();
                if (booleanValue) {
                    PrimesDuration primesDuration = timerEvent2.primesDuration;
                    realtimeDurationMillis = primesDuration.endInstant.uptimeMillis - primesDuration.startInstant.uptimeMillis;
                } else {
                    realtimeDurationMillis = timerEvent2.getRealtimeDurationMillis();
                }
                builder2.copyOnWrite();
                SystemHealthProto$TimerMetric systemHealthProto$TimerMetric = (SystemHealthProto$TimerMetric) builder2.instance;
                systemHealthProto$TimerMetric.bitField0_ = 1 | systemHealthProto$TimerMetric.bitField0_;
                systemHealthProto$TimerMetric.durationMs_ = realtimeDurationMillis;
                if (timerEvent2.timerStatus$ar$edu - 1 != 0) {
                    i = PrimesTraceOuterClass$EndStatus.UNKNOWN_STATUS$ar$edu;
                } else {
                    i = PrimesTraceOuterClass$EndStatus.UNKNOWN_STATUS$ar$edu;
                }
                ExtensionMetric$MetricExtension extensionMetric$MetricExtension2 = extensionMetric$MetricExtension;
                String str2 = str;
                builder2.copyOnWrite();
                SystemHealthProto$TimerMetric systemHealthProto$TimerMetric2 = (SystemHealthProto$TimerMetric) builder2.instance;
                int i2 = i - 1;
                if (i != 0) {
                    systemHealthProto$TimerMetric2.endStatus_ = i2;
                    systemHealthProto$TimerMetric2.bitField0_ |= 2;
                    SystemHealthProto$TimerMetric systemHealthProto$TimerMetric3 = (SystemHealthProto$TimerMetric) builder2.build();
                    builder.copyOnWrite();
                    SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder.instance;
                    systemHealthProto$TimerMetric3.getClass();
                    systemHealthProto$SystemHealthMetric.timerMetric_ = systemHealthProto$TimerMetric3;
                    systemHealthProto$SystemHealthMetric.bitField0_ |= 16;
                    newBuilder.setMetric$ar$ds((SystemHealthProto$SystemHealthMetric) builder.build());
                    newBuilder.customEventName = str2;
                    newBuilder.metricExtension = extensionMetric$MetricExtension2;
                    return metricRecorder.recordMetric(newBuilder.build());
                }
                throw null;
            }
        }, this.deferredExecutor);
    }

    private final synchronized ListenableFuture stop$ar$edu$793c4093_0$ar$ds(TimerEvent timerEvent, String str, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, int i) {
        ListenableFuture immediateFailedFuture;
        if (TimerEvent.isEmpty(timerEvent)) {
            return ImmediateFuture.NULL;
        }
        long samplingRatePermilleIfShouldCollect = this.metricRecorder.samplingRatePermilleIfShouldCollect(str);
        if (samplingRatePermilleIfShouldCollect != -1) {
            timerEvent.primesDuration.endInstant = PrimesInstant.create$ar$class_merging$f6703772_0$ar$ds();
            timerEvent.timerStatus$ar$edu = 1;
            if (!TimerEvent.isEmpty(timerEvent) && !ContextDataProvider.stringIsNullOrEmpty(str)) {
                if (RESERVED_EVENT_NAMES.contains(str)) {
                    immediateFailedFuture = ContextDataProvider.immediateFailedFuture(new IllegalArgumentException(String.format(Locale.US, "%s is reserved event. Dropping timer.", str)));
                } else {
                    immediateFailedFuture = recordSystemHealthMetricInBackground$ar$ds(str, samplingRatePermilleIfShouldCollect, timerEvent, extensionMetric$MetricExtension);
                }
                return immediateFailedFuture;
            }
            immediateFailedFuture = ContextDataProvider.immediateFailedFuture(new IllegalArgumentException("Can't record an event that was never started or has been stopped already"));
            return immediateFailedFuture;
        }
        return ImmediateFuture.NULL;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.CustomDurationMetricService
    public final ListenableFuture record(NoPiiString noPiiString, long j, long j2, ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
        TimerEvent timerEvent = new TimerEvent(j, j2);
        MetricRecorder metricRecorder = this.metricRecorder;
        String str = noPiiString.value;
        long samplingRatePermilleIfShouldCollect = metricRecorder.samplingRatePermilleIfShouldCollect(str);
        if (samplingRatePermilleIfShouldCollect == -1) {
            return ImmediateFuture.NULL;
        }
        return recordSystemHealthMetricInBackground$ar$ds(str, samplingRatePermilleIfShouldCollect, timerEvent, extensionMetric$MetricExtension);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerMetricService
    public final TimerEvent start() {
        if (!this.metricRecorder.shouldRecordMetric()) {
            return TimerEvent.EMPTY_TIMER;
        }
        return new TimerEvent();
    }

    @Override // com.google.android.libraries.performance.primes.metrics.timer.TimerMetricService
    public final ListenableFuture stopAsFuture$ar$edu(TimerEvent timerEvent, NoPiiString noPiiString, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, int i) {
        return stop$ar$edu$793c4093_0$ar$ds(timerEvent, noPiiString.value, extensionMetric$MetricExtension, 1);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public final /* synthetic */ void onApplicationStartup() {
    }
}
