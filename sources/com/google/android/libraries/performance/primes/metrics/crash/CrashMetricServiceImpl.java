package com.google.android.libraries.performance.primes.metrics.crash;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.flogger.RecentLogs;
import com.google.android.libraries.performance.primes.foreground.ForegroundListener;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.persistent.PersistentStorage;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.android.libraries.storage.protostore.ProtoDataStoreFactory;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import dagger.Lazy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.Thread;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.SystemHealthProto$CrashMetric;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PrimesStats;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashMetricServiceImpl extends CrashMetricService implements MetricService, AppLifecycleListener, ForegroundListener {
    volatile NoPiiString activeComponentName;
    private final AppLifecycleMonitor appLifecycleMonitor;
    public final Lazy configs;
    public final PersistentStorage crashBuilder$ar$class_merging;
    public final ProtoDataStoreFactory crashLoopMonitor$ar$class_merging;
    public final Provider crashLoopMonitorFlags;
    public final Provider crashedTikTokTraceConfigs;
    private final Executor deferredExecutor;
    private final ForegroundTracker foregroundTracker;
    private final MetricRecorder metricRecorder;
    private final Optional nativeCrashHandler;
    private final AppLifecycleMonitor probabilitySamplerFactory$ar$class_merging$ar$class_merging;
    private final Provider recordingTimeouts;
    private final AtomicBoolean isPrimesExceptionHandlerDefaultHandler = new AtomicBoolean();
    private final AtomicInteger queuedCrashMonitorInitialized = new AtomicInteger();
    private final AtomicInteger queuedFirstActivityCreated = new AtomicInteger();
    private final AtomicInteger queuedCustomLaunched = new AtomicInteger();
    private final AtomicBoolean activityHasBeenCreated = new AtomicBoolean(false);
    public final AtomicBoolean loggedCrashLoopMonitorInitialized = new AtomicBoolean(false);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class PrimesUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        private final Thread.UncaughtExceptionHandler handlerToWrap;

        public PrimesUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
            this.handlerToWrap = uncaughtExceptionHandler;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:13:0x05cc  */
        /* JADX WARN: Removed duplicated region for block: B:16:? A[SYNTHETIC] */
        /* JADX WARN: Type inference failed for: r2v15, types: [java.lang.Object, dagger.Lazy] */
        @Override // java.lang.Thread.UncaughtExceptionHandler
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void uncaughtException(java.lang.Thread r22, java.lang.Throwable r23) {
            /*
                Method dump skipped, instructions count: 1488
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metrics.crash.CrashMetricServiceImpl.PrimesUncaughtExceptionHandler.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
        }
    }

    public CrashMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Executor executor, Lazy lazy, Optional optional, AppLifecycleMonitor appLifecycleMonitor, ForegroundTracker foregroundTracker, AppLifecycleMonitor appLifecycleMonitor2, Provider provider, Provider provider2, Provider provider3, ProtoDataStoreFactory protoDataStoreFactory, PersistentStorage persistentStorage) {
        this.configs = lazy;
        this.nativeCrashHandler = optional;
        this.appLifecycleMonitor = appLifecycleMonitor;
        this.foregroundTracker = foregroundTracker;
        this.probabilitySamplerFactory$ar$class_merging$ar$class_merging = appLifecycleMonitor2;
        this.metricRecorder = metricRecorderFactory.create(DirectExecutor.INSTANCE, lazy, null);
        this.deferredExecutor = executor;
        this.recordingTimeouts = provider;
        this.crashedTikTokTraceConfigs = provider2;
        this.crashLoopMonitorFlags = provider3;
        this.crashLoopMonitor$ar$class_merging = protoDataStoreFactory;
        this.crashBuilder$ar$class_merging = persistentStorage;
    }

    private final void enqueueStartupEvent$ar$ds$ar$edu(final int i, final AtomicInteger atomicInteger) {
        atomicInteger.getAndIncrement();
        ContextDataProvider.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.crash.CrashMetricServiceImpl$$ExternalSyntheticLambda0
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                if (atomicInteger.getAndDecrement() <= 0) {
                    return ImmediateFuture.NULL;
                }
                int i2 = i;
                CrashMetricServiceImpl crashMetricServiceImpl = CrashMetricServiceImpl.this;
                return crashMetricServiceImpl.recordStartupEventWithSampling$ar$edu(i2, (CrashConfigurations) crashMetricServiceImpl.configs.get());
            }
        }, this.deferredExecutor);
    }

    public final boolean isCrashLoopMonitorEnabled() {
        return ((CrashLoopMonitorFlags) this.crashLoopMonitorFlags.get()).enabled_;
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/crash/CrashMetricServiceImpl", "onActivityCreated", 416, "CrashMetricServiceImpl.java")).log("onActivityCreated");
        if (!this.activityHasBeenCreated.getAndSet(true)) {
            enqueueStartupEvent$ar$ds$ar$edu(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_FIRST_ACTIVITY_LAUNCHED$ar$edu, this.queuedFirstActivityCreated);
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final void onActivityResumed(Activity activity) {
        ContextDataProvider.submitAsync(new CrashMetricServiceImpl$$ExternalSyntheticLambda1(this, 2), this.deferredExecutor);
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final void onActivityStarted(Activity activity) {
        this.activeComponentName = NoPiiString.fromClass(activity.getClass());
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public final void onAppToBackground(NoPiiString noPiiString) {
        this.activeComponentName = null;
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public final void onApplicationStartup() {
        if (this.nativeCrashHandler.isPresent()) {
            ((NativeCrashHandler) ((Provider) this.nativeCrashHandler.get()).get()).initialize$ar$ds$1f92b6f7_0();
        }
        this.appLifecycleMonitor.register(this);
        this.foregroundTracker.register(this);
        enqueueStartupEvent$ar$ds$ar$edu(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_CRASH_MONITORING_INITIALIZED$ar$edu, this.queuedCrashMonitorInitialized);
        ContextDataProvider.submitAsync(new CrashMetricServiceImpl$$ExternalSyntheticLambda1(this, 0), this.deferredExecutor);
    }

    public final ListenableFuture recordStartupEventWithSampling$ar$edu(int i, CrashConfigurations crashConfigurations) {
        return recordStartupEventWithSampling$ar$edu$4790f8f2_0(i, crashConfigurations, crashConfigurations.getStartupSamplePercentage() / 100.0f);
    }

    public final ListenableFuture recordStartupEventWithSampling$ar$edu$4790f8f2_0(int i, CrashConfigurations crashConfigurations, float f) {
        if (!crashConfigurations.isEnabled()) {
            return ImmediateFuture.NULL;
        }
        if (!this.probabilitySamplerFactory$ar$class_merging$ar$class_merging.create(f).isSampleAllowed()) {
            return ImmediateFuture.NULL;
        }
        MetricRecorder metricRecorder = this.metricRecorder;
        Metric.Builder newBuilder = Metric.newBuilder();
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.createBuilder();
        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$PrimesStats.DEFAULT_INSTANCE.createBuilder();
        float f2 = 1.0f / f;
        builder2.copyOnWrite();
        SystemHealthProto$PrimesStats systemHealthProto$PrimesStats = (SystemHealthProto$PrimesStats) builder2.instance;
        systemHealthProto$PrimesStats.bitField0_ |= 2;
        systemHealthProto$PrimesStats.estimatedCount_ = (int) f2;
        builder2.copyOnWrite();
        SystemHealthProto$PrimesStats systemHealthProto$PrimesStats2 = (SystemHealthProto$PrimesStats) builder2.instance;
        int i2 = i - 1;
        if (i != 0) {
            systemHealthProto$PrimesStats2.primesEvent_ = i2;
            systemHealthProto$PrimesStats2.bitField0_ |= 1;
            builder.copyOnWrite();
            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder.instance;
            SystemHealthProto$PrimesStats systemHealthProto$PrimesStats3 = (SystemHealthProto$PrimesStats) builder2.build();
            systemHealthProto$PrimesStats3.getClass();
            systemHealthProto$SystemHealthMetric.primesStats_ = systemHealthProto$PrimesStats3;
            systemHealthProto$SystemHealthMetric.bitField0_ |= 4194304;
            newBuilder.setMetric$ar$ds((SystemHealthProto$SystemHealthMetric) builder.build());
            return metricRecorder.recordMetric(newBuilder.build());
        }
        throw null;
    }

    public final void reportCrash(SystemHealthProto$CrashMetric systemHealthProto$CrashMetric) {
        RecentLogs.TimestampCollection timestampCollection;
        int i;
        SystemHealthProto$CrashMetric.CrashLoopInfo crashLoopInfo;
        CrashConfigurations crashConfigurations = (CrashConfigurations) this.configs.get();
        if (crashConfigurations.isEnabled()) {
            Optional optional = (Optional) this.metricRecorder.MetricRecorder$ar$recentLogs;
            if (optional.isPresent()) {
                timestampCollection = ((RecentLogs) optional.get()).getTimestamp();
            } else {
                timestampCollection = RecentLogs.TimestampCollection.EMPTY;
            }
            boolean z = false;
            if (isCrashLoopMonitorEnabled()) {
                ProtoDataStoreFactory protoDataStoreFactory = this.crashLoopMonitor$ar$class_merging;
                if (!((AtomicBoolean) protoDataStoreFactory.ProtoDataStoreFactory$ar$pdsCache).getAndSet(false)) {
                    SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$CrashMetric.CrashLoopInfo.DEFAULT_INSTANCE.createBuilder();
                    int i2 = SystemHealthProto$CrashMetric.CrashLoopInfo.LoopState.NO_LOOP$ar$edu;
                    builder.copyOnWrite();
                    SystemHealthProto$CrashMetric.CrashLoopInfo crashLoopInfo2 = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder.instance;
                    int i3 = i2 - 1;
                    if (i2 != 0) {
                        crashLoopInfo2.loopState_ = i3;
                        crashLoopInfo2.bitField0_ |= 1;
                        crashLoopInfo = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder.build();
                    } else {
                        throw null;
                    }
                } else {
                    Object obj = protoDataStoreFactory.ProtoDataStoreFactory$ar$storage$ar$class_merging;
                    SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$CrashMetric.CrashLoopInfo.DEFAULT_INSTANCE.createBuilder();
                    EarlyCrashLoopMonitor earlyCrashLoopMonitor = (EarlyCrashLoopMonitor) obj;
                    CrashLoopMonitorFlags crashLoopMonitorFlags = (CrashLoopMonitorFlags) earlyCrashLoopMonitor.flags.get();
                    if (SystemClock.uptimeMillis() - earlyCrashLoopMonitor.startTimeMs > crashLoopMonitorFlags.timeoutMs_) {
                        int i4 = SystemHealthProto$CrashMetric.CrashLoopInfo.LoopState.NO_LOOP_TIMEOUT$ar$edu;
                        builder2.copyOnWrite();
                        SystemHealthProto$CrashMetric.CrashLoopInfo crashLoopInfo3 = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder2.instance;
                        int i5 = i4 - 1;
                        if (i4 != 0) {
                            crashLoopInfo3.loopState_ = i5;
                            crashLoopInfo3.bitField0_ |= 1;
                            crashLoopInfo = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder2.build();
                        } else {
                            throw null;
                        }
                    } else {
                        Optional optional2 = (Optional) earlyCrashLoopMonitor.storageDirSupplier.get();
                        Optional optional3 = (Optional) earlyCrashLoopMonitor.processNameSupplier.get();
                        if (optional2.isPresent() && optional3.isPresent()) {
                            CrashCounter crashCounter = new CrashCounter((File) optional2.get(), (String) optional3.get());
                            int i6 = crashCounter.get();
                            builder2.copyOnWrite();
                            SystemHealthProto$CrashMetric.CrashLoopInfo crashLoopInfo4 = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder2.instance;
                            crashLoopInfo4.bitField0_ |= 2;
                            crashLoopInfo4.previousCrashCount_ = i6;
                            int i7 = i6 + 1;
                            if (i7 >= crashLoopMonitorFlags.overflowThreshold_) {
                                int i8 = SystemHealthProto$CrashMetric.CrashLoopInfo.LoopState.LOOP_OVERFLOW$ar$edu;
                                builder2.copyOnWrite();
                                SystemHealthProto$CrashMetric.CrashLoopInfo crashLoopInfo5 = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder2.instance;
                                int i9 = i8 - 1;
                                if (i8 != 0) {
                                    crashLoopInfo5.loopState_ = i9;
                                    crashLoopInfo5.bitField0_ |= 1;
                                    crashLoopInfo = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder2.build();
                                } else {
                                    throw null;
                                }
                            } else {
                                if (crashCounter.maybeLoad()) {
                                    crashCounter.value++;
                                    SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) CrashLoopStorage.DEFAULT_INSTANCE.createBuilder();
                                    int i10 = crashCounter.value;
                                    builder3.copyOnWrite();
                                    CrashLoopStorage crashLoopStorage = (CrashLoopStorage) builder3.instance;
                                    crashLoopStorage.bitField0_ |= 1;
                                    crashLoopStorage.crashCount_ = i10;
                                    CrashLoopStorage crashLoopStorage2 = (CrashLoopStorage) builder3.build();
                                    boolean z2 = false;
                                    while (true) {
                                        try {
                                            FileOutputStream fileOutputStream = new FileOutputStream(crashCounter.getFile());
                                            try {
                                                crashLoopStorage2.writeTo(fileOutputStream);
                                                fileOutputStream.close();
                                                break;
                                            } finally {
                                                try {
                                                    break;
                                                } catch (Throwable th) {
                                                }
                                            }
                                        } catch (FileNotFoundException unused) {
                                            if (z2) {
                                                break;
                                            }
                                            crashCounter.dir.mkdirs();
                                            z2 = true;
                                        } catch (IOException e) {
                                            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/crash/CrashCounter", "increment", 68, "CrashCounter.java")).log("failed to write counter to disk.");
                                        }
                                    }
                                }
                                if (i7 >= crashLoopMonitorFlags.detectionThreshold_) {
                                    int i11 = SystemHealthProto$CrashMetric.CrashLoopInfo.LoopState.LOOP_DETECTED$ar$edu;
                                    builder2.copyOnWrite();
                                    SystemHealthProto$CrashMetric.CrashLoopInfo crashLoopInfo6 = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder2.instance;
                                    int i12 = i11 - 1;
                                    if (i11 != 0) {
                                        crashLoopInfo6.loopState_ = i12;
                                        crashLoopInfo6.bitField0_ |= 1;
                                        crashLoopInfo = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder2.build();
                                    } else {
                                        throw null;
                                    }
                                } else {
                                    int i13 = SystemHealthProto$CrashMetric.CrashLoopInfo.LoopState.LOOP_COUNTED$ar$edu;
                                    builder2.copyOnWrite();
                                    SystemHealthProto$CrashMetric.CrashLoopInfo crashLoopInfo7 = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder2.instance;
                                    int i14 = i13 - 1;
                                    if (i13 != 0) {
                                        crashLoopInfo7.loopState_ = i14;
                                        crashLoopInfo7.bitField0_ |= 1;
                                        crashLoopInfo = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder2.build();
                                    } else {
                                        throw null;
                                    }
                                }
                            }
                        } else {
                            int i15 = SystemHealthProto$CrashMetric.CrashLoopInfo.LoopState.LOOP_STATE_ERROR$ar$edu;
                            builder2.copyOnWrite();
                            SystemHealthProto$CrashMetric.CrashLoopInfo crashLoopInfo8 = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder2.instance;
                            int i16 = i15 - 1;
                            if (i15 != 0) {
                                crashLoopInfo8.loopState_ = i16;
                                crashLoopInfo8.bitField0_ |= 1;
                                crashLoopInfo = (SystemHealthProto$CrashMetric.CrashLoopInfo) builder2.build();
                            } else {
                                throw null;
                            }
                        }
                    }
                }
                SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) systemHealthProto$CrashMetric.toBuilder();
                builder4.copyOnWrite();
                SystemHealthProto$CrashMetric systemHealthProto$CrashMetric2 = (SystemHealthProto$CrashMetric) builder4.instance;
                crashLoopInfo.getClass();
                systemHealthProto$CrashMetric2.crashLoopInfo_ = crashLoopInfo;
                systemHealthProto$CrashMetric2.bitField0_ |= 2048;
                systemHealthProto$CrashMetric = (SystemHealthProto$CrashMetric) builder4.build();
                int forNumber$ar$edu$eac8bba5_0 = SystemHealthProto$CrashMetric.CrashLoopInfo.LoopState.forNumber$ar$edu$eac8bba5_0(crashLoopInfo.loopState_);
                if (forNumber$ar$edu$eac8bba5_0 == 0) {
                    forNumber$ar$edu$eac8bba5_0 = SystemHealthProto$CrashMetric.CrashLoopInfo.LoopState.LOOP_STATE_UNSPECIFIED$ar$edu;
                }
                if (forNumber$ar$edu$eac8bba5_0 == SystemHealthProto$CrashMetric.CrashLoopInfo.LoopState.LOOP_DETECTED$ar$edu) {
                    z = true;
                }
            }
            try {
                if (ThreadUtil.isMainThread()) {
                    i = ((CrashRecordingTimeouts) this.recordingTimeouts.get()).mainThreadTimeoutMs_;
                } else {
                    i = ((CrashRecordingTimeouts) this.recordingTimeouts.get()).bgThreadTimeoutMs_;
                }
                long j = i;
                MetricRecorder metricRecorder = this.metricRecorder;
                Metric.Builder newBuilder = Metric.newBuilder();
                SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.createBuilder();
                builder5.copyOnWrite();
                SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder5.instance;
                systemHealthProto$CrashMetric.getClass();
                systemHealthProto$SystemHealthMetric.crashMetric_ = systemHealthProto$CrashMetric;
                systemHealthProto$SystemHealthMetric.bitField0_ |= 64;
                newBuilder.setMetric$ar$ds((SystemHealthProto$SystemHealthMetric) builder5.build());
                newBuilder.metricExtension = null;
                newBuilder.debugLogsTime = timestampCollection;
                newBuilder.setDebugLogsSize$ar$ds(crashConfigurations.getDebugLogsSize());
                metricRecorder.recordMetric(newBuilder.build()).get(j, TimeUnit.MILLISECONDS);
            } catch (InterruptedException unused2) {
                Thread.currentThread().interrupt();
            } catch (Throwable unused3) {
            }
            while (this.queuedCrashMonitorInitialized.getAndDecrement() > 0) {
                recordStartupEventWithSampling$ar$edu(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_CRASH_MONITORING_INITIALIZED$ar$edu, crashConfigurations);
            }
            if (isCrashLoopMonitorEnabled() && !this.loggedCrashLoopMonitorInitialized.getAndSet(true)) {
                recordStartupEventWithSampling$ar$edu$4790f8f2_0(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_CRASH_LOOP_MONITOR_INITIALIZED$ar$edu, crashConfigurations, ((CrashLoopMonitorFlags) this.crashLoopMonitorFlags.get()).logInitSampleRate_);
            }
            while (this.queuedFirstActivityCreated.getAndDecrement() > 0) {
                recordStartupEventWithSampling$ar$edu(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_FIRST_ACTIVITY_LAUNCHED$ar$edu, crashConfigurations);
            }
            while (this.queuedCustomLaunched.getAndDecrement() > 0) {
                recordStartupEventWithSampling$ar$edu(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_CUSTOM_LAUNCHED$ar$edu, crashConfigurations);
            }
            if (z) {
                crashConfigurations.getCrashLoopListener();
            }
        }
    }

    @Override // com.google.android.libraries.performance.primes.metrics.crash.CrashMetricService
    public final void setPrimesExceptionHandlerAsDefaultHandler() {
        if (this.isPrimesExceptionHandlerDefaultHandler.compareAndSet(false, true)) {
            Thread.setDefaultUncaughtExceptionHandler(new PrimesUncaughtExceptionHandler(Thread.getDefaultUncaughtExceptionHandler()));
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivityDestroyed(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivityPaused(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivityStopped(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public final /* synthetic */ void onAppToForeground(NoPiiString noPiiString) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onTrimMemory(int i) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}
