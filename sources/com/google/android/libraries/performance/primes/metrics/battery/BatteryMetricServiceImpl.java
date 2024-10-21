package com.google.android.libraries.performance.primes.metrics.battery;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.foreground.ForegroundListener;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryMetricServiceImpl extends BatteryMetricService implements AppLifecycleListener, ForegroundListener, MetricService {
    private final AtomicBoolean activityHasBeenCreated;
    private final AppLifecycleMonitor appLifecycleMonitor;
    private final Context applicationContext;
    private final Provider batteryCaptureProvider;
    private final Executor collectionExecutor;
    private final ForegroundTracker foregroundTracker;
    final AtomicBoolean inForeground = new AtomicBoolean();
    private final MetricRecorder metricRecorder;
    private final StatsStorage storage;

    /* JADX WARN: Multi-variable type inference failed */
    public BatteryMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Context context, AppLifecycleMonitor appLifecycleMonitor, ForegroundTracker foregroundTracker, ListeningScheduledExecutorService listeningScheduledExecutorService, Lazy<BatteryConfigurations> lazy, StatsStorage statsStorage, Provider<BatteryCapture> provider, Provider<SystemHealthProto$SamplingParameters> provider2, Executor executor) {
        new ConcurrentHashMap();
        this.activityHasBeenCreated = new AtomicBoolean(false);
        this.foregroundTracker = foregroundTracker;
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, provider2);
        this.applicationContext = context;
        this.appLifecycleMonitor = appLifecycleMonitor;
        this.collectionExecutor = Build.VERSION.SDK_INT < 31 ? executor : listeningScheduledExecutorService;
        this.storage = statsStorage;
        this.batteryCaptureProvider = provider;
    }

    private final ListenableFuture captureAndLog$ar$ds(final BatteryMetric$BatteryStatsDiff.SampleInfo sampleInfo) {
        return ContextDataProvider.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricServiceImpl$$ExternalSyntheticLambda2
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return BatteryMetricServiceImpl.this.m191x1a2d0423(sampleInfo, null);
            }
        }, this.collectionExecutor);
    }

    /* JADX WARN: Code restructure failed: missing block: B:96:0x0356, code lost:
    
        if ((r6 / r12) <= 3.472222222222222E-5d) goto L143;
     */
    /* JADX WARN: Removed duplicated region for block: B:103:0x05f1  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x05f4  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x00a3 A[Catch: all -> 0x0614, TryCatch #1 {, blocks: (B:9:0x0014, B:14:0x004a, B:17:0x004e, B:21:0x0054, B:22:0x009c, B:25:0x012a, B:177:0x00a3, B:179:0x00a9, B:181:0x00b1, B:183:0x00b6, B:185:0x00bc, B:186:0x00be, B:188:0x00c5, B:189:0x00ce, B:191:0x00d4, B:192:0x00dd, B:194:0x00e3, B:195:0x00ec, B:197:0x00f2, B:198:0x00fb, B:200:0x0101, B:201:0x0106, B:203:0x010a, B:205:0x010e, B:207:0x0115, B:209:0x011b, B:210:0x0126, B:221:0x005e, B:222:0x0080, B:224:0x0033), top: B:8:0x0014, inners: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00a0  */
    /* JADX WARN: Type inference failed for: r0v34, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v14, types: [javax.inject.Provider, java.lang.Object] */
    /* renamed from: lambda$captureAndLog$3$com-google-android-libraries-performance-primes-metrics-battery-BatteryMetricServiceImpl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ com.google.common.util.concurrent.ListenableFuture m191x1a2d0423(logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff.SampleInfo r19, com.google.android.libraries.performance.primes.NoPiiString r20) {
        /*
            Method dump skipped, instructions count: 1559
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricServiceImpl.m191x1a2d0423(logs.proto.wireless.performance.mobile.BatteryMetric$BatteryStatsDiff$SampleInfo, com.google.android.libraries.performance.primes.NoPiiString):com.google.common.util.concurrent.ListenableFuture");
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (!this.activityHasBeenCreated.getAndSet(true)) {
            onAppToForeground(null);
        }
    }

    public ListenableFuture<Void> onAppToBackground() {
        if (!DirectBootUtils.isUserUnlocked(this.applicationContext)) {
            return ImmediateFuture.NULL;
        }
        try {
            ContextDataProvider.checkState(this.inForeground.getAndSet(false));
            return captureAndLog$ar$ds(BatteryMetric$BatteryStatsDiff.SampleInfo.FOREGROUND_TO_BACKGROUND);
        } catch (Exception e) {
            return ContextDataProvider.immediateFailedFuture(e);
        }
    }

    public ListenableFuture<Void> onAppToForeground() {
        if (!DirectBootUtils.isUserUnlocked(this.applicationContext)) {
            return ImmediateFuture.NULL;
        }
        if (this.inForeground.getAndSet(true)) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/battery/BatteryMetricServiceImpl", "onAppToForeground", 141, "BatteryMetricServiceImpl.java")).log("App is already in the foreground.");
            return ContextDataProvider.immediateCancelledFuture();
        }
        return captureAndLog$ar$ds(BatteryMetric$BatteryStatsDiff.SampleInfo.BACKGROUND_TO_FOREGROUND);
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        this.foregroundTracker.register(this);
        this.appLifecycleMonitor.register(this);
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public void onAppToBackground(NoPiiString noPiiString) {
        onAppToBackground();
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public void onAppToForeground(NoPiiString noPiiString) {
        if (this.inForeground.get()) {
            return;
        }
        onAppToForeground();
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onActivityDestroyed(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onActivityPaused(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onActivityResumed(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onActivityStarted(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onActivityStopped(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onTrimMemory(int i) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}
