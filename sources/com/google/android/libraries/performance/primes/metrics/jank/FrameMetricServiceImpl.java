package com.google.android.libraries.performance.primes.metrics.jank;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.SystemClock;
import android.os.Trace;
import android.util.ArrayMap;
import android.view.FrameMetrics;
import android.view.Window;
import android.view.Window$OnFrameMetricsAvailableListener;
import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.flogger.logargs.NonSensitiveLogParameterInternal;
import com.google.android.libraries.performance.primes.foreground.ForegroundListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.core.perfetto.PerfettoTrigger;
import com.google.android.libraries.performance.primes.metrics.jank.PerfettoTraceConfigurations$JankPerfettoConfigurations;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import dagger.Lazy;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$HistogramBucket;
import logs.proto.wireless.performance.mobile.SystemHealthProto$JankMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;
import org.chromium.net.NetError;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FrameMetricServiceImpl extends JankMetricService implements ForegroundListener, MetricService {
    private final ActivityLevelJankMonitor activityLevelJankMonitor;
    private final AppLifecycleMonitor appLifecycleMonitor;
    private final Context context;
    private final FrameMetricsListener frameMetricsListener;
    private final Provider frameTimeHistogramProvider;
    private final ArrayMap measurements;
    private final MetricRecorder metricRecorder;
    private final Provider perfettoConfigurations;
    private final Lazy perfettoTrigger;
    private final Supplier perfettoTriggerName;
    private final WindowTracker windowTracker;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FrameMetricsListener implements Window$OnFrameMetricsAvailableListener {
        private final Provider computeMaxAcceptedFrameTimeFromWindow;
        private final Supplier defaultMaxAcceptedFrameTimeNsSupplier;
        private boolean initialized;
        private LegacyDeadlineTracker legacyDeadlineTracker;
        private long maxAcceptedFrameTimeNs;
        private final ArrayMap measurements;

        public FrameMetricsListener(Context context, ArrayMap arrayMap, Provider provider) {
            this.defaultMaxAcceptedFrameTimeNsSupplier = ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(context, 7));
            this.measurements = arrayMap;
            this.computeMaxAcceptedFrameTimeFromWindow = provider;
        }

        public final void onFrameMetricsAvailable(Window window, FrameMetrics frameMetrics, int i) {
            long metric;
            long metric2;
            long j;
            long metric3;
            char c;
            int i2;
            int i3;
            int i4;
            LegacyDeadlineTracker legacyDeadlineTracker;
            if (!this.initialized) {
                this.initialized = true;
                if (LegacyDeadlineTracker.isSupported()) {
                    legacyDeadlineTracker = new LegacyDeadlineTracker();
                } else {
                    legacyDeadlineTracker = null;
                }
                this.legacyDeadlineTracker = legacyDeadlineTracker;
                if (this.computeMaxAcceptedFrameTimeFromWindow.get() != null && ((Boolean) this.computeMaxAcceptedFrameTimeFromWindow.get()).booleanValue()) {
                    this.maxAcceptedFrameTimeNs = 1.0E9f / window.getWindowManager().getDefaultDisplay().getRefreshRate();
                } else {
                    this.maxAcceptedFrameTimeNs = ((Long) this.defaultMaxAcceptedFrameTimeNsSupplier.get()).longValue();
                }
            }
            metric = frameMetrics.getMetric(9);
            if (metric != 1) {
                metric2 = frameMetrics.getMetric(8);
                LegacyDeadlineTracker legacyDeadlineTracker2 = this.legacyDeadlineTracker;
                if (legacyDeadlineTracker2 != null) {
                    j = legacyDeadlineTracker2.computeNextDeadlineDuration(frameMetrics, this.maxAcceptedFrameTimeNs);
                } else {
                    j = this.maxAcceptedFrameTimeNs;
                }
                metric3 = frameMetrics.getMetric(13);
                ArrayMap arrayMap = this.measurements;
                synchronized (arrayMap) {
                    int size = arrayMap.size();
                    int i5 = 0;
                    while (i5 < size) {
                        FrameTimeHistogram frameTimeHistogram = (FrameTimeHistogram) arrayMap.valueAt(i5);
                        int i6 = i5;
                        int i7 = (int) (metric2 / 1000000);
                        if (i7 < 0) {
                            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFinest()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameTimeHistogram", "addFrame", 84, "FrameTimeHistogram.java")).log("Invalid frame time: %d", metric2);
                            frameTimeHistogram.droppedReportCount++;
                            c = '\b';
                        } else {
                            frameTimeHistogram.renderedFrameCount++;
                            if (metric3 > 0) {
                                int i8 = (int) ((metric2 - metric3) / 1000000);
                                if (frameTimeHistogram.maxSlackTimeMs < i8) {
                                    frameTimeHistogram.maxSlackTimeMs = i8;
                                }
                                int[] iArr = frameTimeHistogram.slackBuckets;
                                if (i8 < 20) {
                                    if (i8 >= -20) {
                                        i3 = ((i8 + 20) >> 1) + 12;
                                    } else if (i8 >= -30) {
                                        i3 = ((i8 + 30) / 5) + 10;
                                    } else if (i8 >= -100) {
                                        i3 = ((i8 + 100) / 10) + 3;
                                    } else if (i8 >= -200) {
                                        i3 = ((i8 + 200) / 50) + 1;
                                    } else {
                                        i3 = 0;
                                    }
                                } else {
                                    if (i8 < 30) {
                                        i4 = ((i8 - 20) / 5) + 32;
                                    } else if (i8 < 100) {
                                        i3 = ((i8 - 30) / 10) + 34;
                                    } else if (i8 < 200) {
                                        i4 = ((i8 - 50) / 100) + 41;
                                    } else if (i8 < 1000) {
                                        i3 = ((i8 + NetError.ERR_CERT_COMMON_NAME_INVALID) / 100) + 43;
                                    } else {
                                        i3 = 51;
                                    }
                                    i3 = i4;
                                }
                                iArr[i3] = iArr[i3] + 1;
                                if (metric2 > metric3) {
                                    frameTimeHistogram.jankyFrameCount++;
                                    frameTimeHistogram.totalJankyFrameDurationMs += i7;
                                }
                                if (metric2 > j) {
                                    frameTimeHistogram.framesMissingRefreshRateBasedDrawDeadline++;
                                    frameTimeHistogram.totalDurationOfFramesMissingRefreshRateDeadlineMs += i7;
                                }
                            } else if (metric2 > j) {
                                frameTimeHistogram.jankyFrameCount++;
                                frameTimeHistogram.totalJankyFrameDurationMs += i7;
                            }
                            int[] iArr2 = frameTimeHistogram.buckets;
                            if (i7 <= 20) {
                                c = '\b';
                                if (i7 >= 8) {
                                    i2 = (i7 >> 1) - 2;
                                } else {
                                    i2 = i7 / 4;
                                }
                            } else {
                                c = '\b';
                                if (i7 <= 30) {
                                    i2 = (i7 / 5) + 4;
                                } else if (i7 <= 100) {
                                    i2 = (i7 / 10) + 7;
                                } else if (i7 <= 200) {
                                    i2 = (i7 / 50) + 15;
                                } else if (i7 <= 1000) {
                                    i2 = (i7 / 100) + 17;
                                } else {
                                    i2 = 27;
                                }
                            }
                            iArr2[i2] = iArr2[i2] + 1;
                            frameTimeHistogram.droppedReportCount += i;
                            if (frameTimeHistogram.maxFrameDurationMs < i7) {
                                frameTimeHistogram.maxFrameDurationMs = i7;
                            }
                            frameTimeHistogram.totalFrameDurationMs += i7;
                        }
                        i5 = i6 + 1;
                    }
                }
                return;
            }
            LegacyDeadlineTracker legacyDeadlineTracker3 = this.legacyDeadlineTracker;
            if (legacyDeadlineTracker3 != null) {
                legacyDeadlineTracker3.computeNextDeadlineDuration(frameMetrics, this.maxAcceptedFrameTimeNs);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class MeasurementKey {
        public static MeasurementKey create(Activity activity) {
            return new AutoValue_FrameMetricServiceImpl_MeasurementKey(new NoPiiString(activity.getClass().getName()));
        }

        public final boolean equals(Object obj) {
            if (obj instanceof MeasurementKey) {
                MeasurementKey measurementKey = (MeasurementKey) obj;
                if (stringValue().equals(measurementKey.stringValue())) {
                    measurementKey.isActivity$ar$ds();
                    return true;
                }
                return false;
            }
            return false;
        }

        public final int hashCode() {
            return (stringValue().hashCode() * 31) ^ 1231;
        }

        public abstract void isActivity$ar$ds();

        public abstract NoPiiString noPiiEventName();

        public final String stringValue() {
            return noPiiEventName().value;
        }
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v1, types: [javax.inject.Provider, java.lang.Object] */
    public FrameMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Context context, AppLifecycleMonitor appLifecycleMonitor, Lazy<JankConfigurations> lazy, ActivityLevelJankMonitor activityLevelJankMonitor, Provider<FrameTimeHistogram> provider, Provider<SystemHealthProto$SamplingParameters> provider2, Executor executor, Lazy<PerfettoTrigger> lazy2, WindowTrackerFactory windowTrackerFactory, Provider<Boolean> provider3, Provider<PerfettoTraceConfigurations$JankPerfettoConfigurations> provider4, JankObserverFactory jankObserverFactory) {
        ArrayMap arrayMap = new ArrayMap();
        this.measurements = arrayMap;
        ContextDataProvider.checkState(true);
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, provider2);
        this.context = context;
        this.appLifecycleMonitor = appLifecycleMonitor;
        this.frameTimeHistogramProvider = provider;
        this.activityLevelJankMonitor = activityLevelJankMonitor;
        this.perfettoTrigger = lazy2;
        this.perfettoTriggerName = ContextDataProvider.memoize(new FrameMetricServiceImpl$$ExternalSyntheticLambda0((Object) provider4, context, 0));
        this.perfettoConfigurations = provider4;
        FrameMetricsListener frameMetricsListener = new FrameMetricsListener(context, arrayMap, provider3);
        this.frameMetricsListener = frameMetricsListener;
        Lazy lazy3 = (Lazy) windowTrackerFactory.WindowTrackerFactory$ar$handlerProvider.get();
        lazy3.getClass();
        ListeningScheduledExecutorService listeningScheduledExecutorService = (ListeningScheduledExecutorService) windowTrackerFactory.WindowTrackerFactory$ar$executorProvider.get();
        listeningScheduledExecutorService.getClass();
        this.windowTracker = new WindowTracker(lazy3, listeningScheduledExecutorService, frameMetricsListener);
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public void onAppToBackground(NoPiiString noPiiString) {
        synchronized (this.measurements) {
            this.measurements.clear();
        }
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        this.appLifecycleMonitor.register(this.windowTracker);
        this.appLifecycleMonitor.register(this.activityLevelJankMonitor);
    }

    public void start(Activity activity) {
        boolean isEnabled;
        MeasurementKey create = MeasurementKey.create(activity);
        if (!this.metricRecorder.shouldCollectMetric(create.stringValue())) {
            return;
        }
        synchronized (this.measurements) {
            if (this.measurements.size() >= 25) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameMetricServiceImpl", "start", 174, "FrameMetricServiceImpl.java")).log("Too many concurrent measurements, ignoring %s", create);
                return;
            }
            FrameTimeHistogram frameTimeHistogram = (FrameTimeHistogram) this.measurements.put(create, ((FrameTimeHistogram_Factory) this.frameTimeHistogramProvider).get());
            if (frameTimeHistogram != null) {
                this.measurements.put(create, frameTimeHistogram);
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameMetricServiceImpl", "start", 187, "FrameMetricServiceImpl.java")).log("measurement already started: %s", create);
                return;
            }
            if (this.measurements.size() == 1) {
                this.windowTracker.startCollecting();
            }
            if (Build.VERSION.SDK_INT >= 29) {
                isEnabled = Trace.isEnabled();
                if (isEnabled) {
                    Trace.beginAsyncSection(String.format("J<%s>", create.stringValue()), 352691800);
                }
            }
        }
    }

    public ListenableFuture<Void> stopAsFuture(Activity activity) {
        FrameTimeHistogram frameTimeHistogram;
        long elapsedRealtime;
        SystemHealthProto$PackedHistogram systemHealthProto$PackedHistogram;
        boolean isEnabled;
        MeasurementKey create = MeasurementKey.create(activity);
        if (!this.metricRecorder.shouldRecordMetric()) {
            return ImmediateFuture.NULL;
        }
        synchronized (this.measurements) {
            frameTimeHistogram = (FrameTimeHistogram) this.measurements.remove(create);
            if (this.measurements.isEmpty()) {
                this.windowTracker.stopCollecting();
            }
        }
        if (frameTimeHistogram == null) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atInfo()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameMetricServiceImpl", "stopAsFuture", 247, "FrameMetricServiceImpl.java")).log("Measurement not found: %s", new NonSensitiveLogParameterInternal(((AutoValue_FrameMetricServiceImpl_MeasurementKey) create).noPiiEventName));
            return ImmediateFuture.NULL;
        }
        String stringValue = create.stringValue();
        int i = 0;
        if (Build.VERSION.SDK_INT >= 29) {
            isEnabled = Trace.isEnabled();
            if (isEnabled) {
                Trace.endAsyncSection(String.format("J<%s>", stringValue), 352691800);
                int i2 = -1;
                for (PerfettoTraceConfigurations$JankPerfettoConfigurations.Counter counter : ((PerfettoTraceConfigurations$JankPerfettoConfigurations) this.perfettoConfigurations.get()).counter_) {
                    int forNumber$ar$edu$9c03f9c5_0 = PerfettoTraceConfigurations$JankPerfettoConfigurations.CounterType.forNumber$ar$edu$9c03f9c5_0(counter.type_);
                    if (forNumber$ar$edu$9c03f9c5_0 == 0) {
                        forNumber$ar$edu$9c03f9c5_0 = PerfettoTraceConfigurations$JankPerfettoConfigurations.CounterType.COUNTER_UNKNOWN$ar$edu;
                    }
                    if (forNumber$ar$edu$9c03f9c5_0 != 0) {
                        switch (forNumber$ar$edu$9c03f9c5_0 - 1) {
                            case 0:
                                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/FrameMetricServiceImpl", "endTraceSectionAndEmitCounters", 394, "FrameMetricServiceImpl.java")).log("UNKNOWN COUNTER with %s as the name", counter.name_);
                                continue;
                            case 1:
                                i2 = 0;
                                break;
                            case 2:
                                i2 = frameTimeHistogram.jankyFrameCount;
                                break;
                            case 3:
                                i2 = frameTimeHistogram.renderedFrameCount;
                                break;
                            case 4:
                                i2 = frameTimeHistogram.droppedReportCount;
                                break;
                            case 5:
                                i2 = frameTimeHistogram.maxFrameDurationMs;
                                break;
                            case 6:
                                i2 = frameTimeHistogram.totalJankyFrameDurationMs;
                                break;
                            case 7:
                                i2 = frameTimeHistogram.totalFrameDurationMs;
                                break;
                        }
                        Trace.setCounter(counter.name_.replace("%EVENT_NAME%", stringValue), i2);
                    } else {
                        throw null;
                    }
                }
            }
        }
        if (frameTimeHistogram.renderedFrameCount == 0) {
            return ImmediateFuture.NULL;
        }
        if (((PerfettoTraceConfigurations$JankPerfettoConfigurations) this.perfettoConfigurations.get()).flushingToPerfettoEnabled_ && frameTimeHistogram.totalFrameDurationMs <= TimeUnit.SECONDS.toMillis(9L) && frameTimeHistogram.jankyFrameCount != 0) {
            ((PerfettoTrigger) this.perfettoTrigger.get()).trigger((String) this.perfettoTriggerName.get());
        }
        long j = frameTimeHistogram.recordingStartTimeMs;
        elapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = elapsedRealtime - j;
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$JankMetric.DEFAULT_INSTANCE.createBuilder();
        builder.copyOnWrite();
        SystemHealthProto$JankMetric systemHealthProto$JankMetric = (SystemHealthProto$JankMetric) builder.instance;
        systemHealthProto$JankMetric.bitField0_ |= 16;
        systemHealthProto$JankMetric.recordingDurationMs_ = ((int) j2) + 1;
        int i3 = frameTimeHistogram.jankyFrameCount;
        builder.copyOnWrite();
        SystemHealthProto$JankMetric systemHealthProto$JankMetric2 = (SystemHealthProto$JankMetric) builder.instance;
        systemHealthProto$JankMetric2.bitField0_ |= 1;
        systemHealthProto$JankMetric2.jankyFrameCount_ = i3;
        int i4 = frameTimeHistogram.renderedFrameCount;
        builder.copyOnWrite();
        SystemHealthProto$JankMetric systemHealthProto$JankMetric3 = (SystemHealthProto$JankMetric) builder.instance;
        systemHealthProto$JankMetric3.bitField0_ |= 2;
        systemHealthProto$JankMetric3.renderedFrameCount_ = i4;
        int i5 = frameTimeHistogram.droppedReportCount;
        builder.copyOnWrite();
        SystemHealthProto$JankMetric systemHealthProto$JankMetric4 = (SystemHealthProto$JankMetric) builder.instance;
        systemHealthProto$JankMetric4.bitField0_ |= 4;
        systemHealthProto$JankMetric4.droppedReportCount_ = i5;
        int i6 = frameTimeHistogram.totalJankyFrameDurationMs;
        builder.copyOnWrite();
        SystemHealthProto$JankMetric systemHealthProto$JankMetric5 = (SystemHealthProto$JankMetric) builder.instance;
        systemHealthProto$JankMetric5.bitField0_ |= 32;
        systemHealthProto$JankMetric5.jankyDurationMs_ = i6;
        int i7 = frameTimeHistogram.totalFrameDurationMs;
        builder.copyOnWrite();
        SystemHealthProto$JankMetric systemHealthProto$JankMetric6 = (SystemHealthProto$JankMetric) builder.instance;
        systemHealthProto$JankMetric6.bitField0_ |= 64;
        systemHealthProto$JankMetric6.durationMs_ = i7;
        int i8 = frameTimeHistogram.maxFrameDurationMs;
        builder.copyOnWrite();
        SystemHealthProto$JankMetric systemHealthProto$JankMetric7 = (SystemHealthProto$JankMetric) builder.instance;
        systemHealthProto$JankMetric7.bitField0_ |= 8;
        systemHealthProto$JankMetric7.maxFrameRenderTimeMs_ = i8;
        int i9 = frameTimeHistogram.maxSlackTimeMs;
        if (i9 != Integer.MIN_VALUE) {
            int[] iArr = frameTimeHistogram.slackBuckets;
            int[] iArr2 = FrameTimeHistogram.NEGATIVE_SLACK_BUCKET_BOUNDS;
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$PackedHistogram.DEFAULT_INSTANCE.createBuilder();
            int i10 = 0;
            while (true) {
                if (i10 < 52) {
                    if (iArr2[i10] > i9) {
                        builder2.addPopulation$ar$ds(0);
                        builder2.addLowerBound$ar$ds(i9 + 1);
                        systemHealthProto$PackedHistogram = (SystemHealthProto$PackedHistogram) builder2.build();
                    } else {
                        int i11 = iArr[i10];
                        if (i11 > 0 || (i10 > 0 && iArr[i10 - 1] > 0)) {
                            builder2.addPopulation$ar$ds(i11);
                            builder2.addLowerBound$ar$ds(iArr2[i10]);
                        }
                        i10++;
                    }
                } else {
                    if (iArr[51] > 0) {
                        builder2.addLowerBound$ar$ds(i9 + 1);
                        builder2.addPopulation$ar$ds(0);
                    }
                    systemHealthProto$PackedHistogram = (SystemHealthProto$PackedHistogram) builder2.build();
                }
            }
            builder.copyOnWrite();
            SystemHealthProto$JankMetric systemHealthProto$JankMetric8 = (SystemHealthProto$JankMetric) builder.instance;
            systemHealthProto$PackedHistogram.getClass();
            systemHealthProto$JankMetric8.slackTimeHistogram_ = systemHealthProto$PackedHistogram;
            systemHealthProto$JankMetric8.bitField0_ |= 2048;
            int i12 = frameTimeHistogram.framesMissingRefreshRateBasedDrawDeadline;
            builder.copyOnWrite();
            SystemHealthProto$JankMetric systemHealthProto$JankMetric9 = (SystemHealthProto$JankMetric) builder.instance;
            systemHealthProto$JankMetric9.bitField0_ |= 512;
            systemHealthProto$JankMetric9.framesMissingRefreshRateBasedDrawDeadline_ = i12;
            int i13 = frameTimeHistogram.totalDurationOfFramesMissingRefreshRateDeadlineMs;
            builder.copyOnWrite();
            SystemHealthProto$JankMetric systemHealthProto$JankMetric10 = (SystemHealthProto$JankMetric) builder.instance;
            systemHealthProto$JankMetric10.bitField0_ |= 1024;
            systemHealthProto$JankMetric10.durationOfFramesMissingRefreshRateBasedDeadlineMs_ = i13;
        }
        while (i < 28) {
            int i14 = i + 1;
            if (frameTimeHistogram.buckets[i] > 0) {
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$HistogramBucket.DEFAULT_INSTANCE.createBuilder();
                int i15 = frameTimeHistogram.buckets[i];
                builder3.copyOnWrite();
                SystemHealthProto$HistogramBucket systemHealthProto$HistogramBucket = (SystemHealthProto$HistogramBucket) builder3.instance;
                systemHealthProto$HistogramBucket.bitField0_ |= 1;
                systemHealthProto$HistogramBucket.count_ = i15;
                int i16 = FrameTimeHistogram.BUCKETS_BOUNDS[i];
                builder3.copyOnWrite();
                SystemHealthProto$HistogramBucket systemHealthProto$HistogramBucket2 = (SystemHealthProto$HistogramBucket) builder3.instance;
                systemHealthProto$HistogramBucket2.bitField0_ |= 2;
                systemHealthProto$HistogramBucket2.min_ = i16;
                if (i14 < 28) {
                    int i17 = FrameTimeHistogram.BUCKETS_BOUNDS[i14] - 1;
                    builder3.copyOnWrite();
                    SystemHealthProto$HistogramBucket systemHealthProto$HistogramBucket3 = (SystemHealthProto$HistogramBucket) builder3.instance;
                    systemHealthProto$HistogramBucket3.bitField0_ |= 4;
                    systemHealthProto$HistogramBucket3.max_ = i17;
                }
                builder.copyOnWrite();
                SystemHealthProto$JankMetric systemHealthProto$JankMetric11 = (SystemHealthProto$JankMetric) builder.instance;
                SystemHealthProto$HistogramBucket systemHealthProto$HistogramBucket4 = (SystemHealthProto$HistogramBucket) builder3.build();
                systemHealthProto$HistogramBucket4.getClass();
                Internal.ProtobufList protobufList = systemHealthProto$JankMetric11.frameTimeHistogram_;
                if (!protobufList.isModifiable()) {
                    systemHealthProto$JankMetric11.frameTimeHistogram_ = GeneratedMessageLite.mutableCopy(protobufList);
                }
                systemHealthProto$JankMetric11.frameTimeHistogram_.add(systemHealthProto$HistogramBucket4);
            }
            i = i14;
        }
        SystemHealthProto$JankMetric systemHealthProto$JankMetric12 = (SystemHealthProto$JankMetric) builder.build();
        Optional refreshRate = DisplayStats.getRefreshRate(this.context);
        if (refreshRate.isPresent()) {
            SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) systemHealthProto$JankMetric12.toBuilder();
            int intValue = ((Float) refreshRate.get()).intValue();
            builder4.copyOnWrite();
            SystemHealthProto$JankMetric systemHealthProto$JankMetric13 = (SystemHealthProto$JankMetric) builder4.instance;
            systemHealthProto$JankMetric13.bitField0_ |= 256;
            systemHealthProto$JankMetric13.deviceRefreshRate_ = intValue;
            systemHealthProto$JankMetric12 = (SystemHealthProto$JankMetric) builder4.build();
        }
        SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.createBuilder();
        builder5.copyOnWrite();
        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder5.instance;
        systemHealthProto$JankMetric12.getClass();
        systemHealthProto$SystemHealthMetric.jankMetric_ = systemHealthProto$JankMetric12;
        systemHealthProto$SystemHealthMetric.bitField0_ |= 1024;
        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric2 = (SystemHealthProto$SystemHealthMetric) builder5.build();
        MetricRecorder metricRecorder = this.metricRecorder;
        Metric.Builder newBuilder = Metric.newBuilder();
        newBuilder.setMetric$ar$ds(systemHealthProto$SystemHealthMetric2);
        newBuilder.metricExtension = null;
        newBuilder.accountableComponentName = "Activity";
        newBuilder.customEventName = create.stringValue();
        newBuilder.setIsEventNameConstant$ar$ds(true);
        return metricRecorder.recordMetric(newBuilder.build());
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public /* synthetic */ void onAppToForeground(NoPiiString noPiiString) {
    }
}
