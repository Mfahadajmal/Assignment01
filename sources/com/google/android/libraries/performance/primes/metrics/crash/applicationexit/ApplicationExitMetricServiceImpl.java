package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.collect.UnmodifiableListIterator;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import dagger.Lazy;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.ApplicationExitInfo;
import logs.proto.wireless.performance.mobile.ApplicationExitMetric;
import logs.proto.wireless.performance.mobile.ApplicationExitReasons;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitMetricServiceImpl extends ApplicationExitMetricService implements MetricService {
    private final Provider appExitCollectionEnabledProvider;
    private final Provider appExitReasonsToReportProvider;
    private final Context application;
    private final Lazy applicationExitConfigurations;
    private final ApplicationExitInfoCapture applicationExitInfoCapture;
    private final Executor deferrableExecutor;
    private final MetricRecorder metricRecorder;
    private final Provider sharedPrefsProvider;

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: $r8$lambda$JszHidJD-ADHAMcTsJTyv8ZizdU, reason: not valid java name */
    public static /* synthetic */ ListenableFuture m196$r8$lambda$JszHidJDADHAMcTsJTyv8ZizdU(final ApplicationExitMetricServiceImpl applicationExitMetricServiceImpl) {
        String processName;
        if (!((ApplicationExitConfigurations) applicationExitMetricServiceImpl.applicationExitConfigurations.get()).isEnabled()) {
            return ImmediateFuture.NULL;
        }
        Context context = applicationExitMetricServiceImpl.application;
        Lazy lazy = applicationExitMetricServiceImpl.applicationExitConfigurations;
        String packageName = context.getPackageName();
        String reportingProcessShortName = ((ApplicationExitConfigurations) lazy.get()).getReportingProcessShortName();
        String valueOf = String.valueOf(packageName);
        String valueOf2 = String.valueOf(reportingProcessShortName);
        processName = Application.getProcessName();
        if (!processName.equals(valueOf.concat(valueOf2))) {
            return ImmediateFuture.NULL;
        }
        if (!((Boolean) applicationExitMetricServiceImpl.appExitCollectionEnabledProvider.get()).booleanValue()) {
            return ImmediateFuture.NULL;
        }
        final List<ApplicationExitInfo> applicationExits = applicationExitMetricServiceImpl.applicationExitInfoCapture.getApplicationExits(0, 0, ((SharedPreferences) applicationExitMetricServiceImpl.sharedPrefsProvider.get()).getString("lastExitProcessName", null), ((SharedPreferences) applicationExitMetricServiceImpl.sharedPrefsProvider.get()).getLong("lastExitTimestamp", -1L));
        if (applicationExits.isEmpty()) {
            return ImmediateFuture.NULL;
        }
        ApplicationExitReasons applicationExitReasons = (ApplicationExitReasons) applicationExitMetricServiceImpl.appExitReasonsToReportProvider.get();
        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) ApplicationExitMetric.DEFAULT_INSTANCE.createBuilder();
        int i = ((RegularImmutableList) applicationExits).size;
        builder.copyOnWrite();
        ApplicationExitMetric applicationExitMetric = (ApplicationExitMetric) builder.instance;
        applicationExitMetric.bitField0_ |= 2;
        applicationExitMetric.totalExits_ = i;
        builder.copyOnWrite();
        ApplicationExitMetric applicationExitMetric2 = (ApplicationExitMetric) builder.instance;
        applicationExitReasons.getClass();
        applicationExitMetric2.reportableReasons_ = applicationExitReasons;
        applicationExitMetric2.bitField0_ |= 1;
        HashSet hashSet = new HashSet();
        for (int i2 = 0; i2 < applicationExitReasons.exitReason_.size(); i2++) {
            int forNumber$ar$edu$f66dba11_0 = ApplicationExitInfo.Reason.forNumber$ar$edu$f66dba11_0(applicationExitReasons.exitReason_.getInt(i2));
            if (forNumber$ar$edu$f66dba11_0 == 0) {
                forNumber$ar$edu$f66dba11_0 = ApplicationExitInfo.Reason.REASON_UNSET$ar$edu;
            }
            int i3 = forNumber$ar$edu$f66dba11_0 - 1;
            if (forNumber$ar$edu$f66dba11_0 != 0) {
                hashSet.add(Integer.valueOf(i3));
            } else {
                throw null;
            }
        }
        UnmodifiableListIterator it = ((ImmutableList) applicationExits).iterator();
        while (it.hasNext()) {
            ApplicationExitInfo applicationExitInfo = (ApplicationExitInfo) it.next();
            int forNumber$ar$edu$f66dba11_02 = ApplicationExitInfo.Reason.forNumber$ar$edu$f66dba11_0(applicationExitInfo.reason_);
            if (forNumber$ar$edu$f66dba11_02 == 0) {
                forNumber$ar$edu$f66dba11_02 = ApplicationExitInfo.Reason.REASON_UNSET$ar$edu;
            }
            int i4 = forNumber$ar$edu$f66dba11_02 - 1;
            if (forNumber$ar$edu$f66dba11_02 != 0) {
                if (hashSet.contains(Integer.valueOf(i4))) {
                    builder.copyOnWrite();
                    ApplicationExitMetric applicationExitMetric3 = (ApplicationExitMetric) builder.instance;
                    applicationExitInfo.getClass();
                    Internal.ProtobufList protobufList = applicationExitMetric3.applicationExitInfo_;
                    if (!protobufList.isModifiable()) {
                        applicationExitMetric3.applicationExitInfo_ = GeneratedMessageLite.mutableCopy(protobufList);
                    }
                    applicationExitMetric3.applicationExitInfo_.add(applicationExitInfo);
                }
            } else {
                throw null;
            }
        }
        ApplicationExitMetric applicationExitMetric4 = (ApplicationExitMetric) builder.build();
        MetricRecorder metricRecorder = applicationExitMetricServiceImpl.metricRecorder;
        Metric.Builder newBuilder = Metric.newBuilder();
        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.createBuilder();
        builder2.copyOnWrite();
        SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder2.instance;
        applicationExitMetric4.getClass();
        systemHealthProto$SystemHealthMetric.applicationExitMetric_ = applicationExitMetric4;
        systemHealthProto$SystemHealthMetric.bitField0_ |= 65536;
        newBuilder.setMetric$ar$ds((SystemHealthProto$SystemHealthMetric) builder2.build());
        return AbstractTransformFuture.create(metricRecorder.recordMetric(newBuilder.build()), new Function() { // from class: com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl$$ExternalSyntheticLambda1
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                ApplicationExitMetricServiceImpl.this.m199x72f88691(applicationExits, (Void) obj);
                return null;
            }
        }, applicationExitMetricServiceImpl.deferrableExecutor);
    }

    public ApplicationExitMetricServiceImpl(MetricRecorderFactory metricRecorderFactory, Context context, Executor executor, ApplicationExitInfoCapture applicationExitInfoCapture, Provider<SharedPreferences> provider, Lazy<ApplicationExitConfigurations> lazy, Provider<Boolean> provider2, Provider<ApplicationExitReasons> provider3) {
        this.metricRecorder = metricRecorderFactory.create(executor, lazy, null);
        this.application = context;
        this.deferrableExecutor = executor;
        this.applicationExitInfoCapture = applicationExitInfoCapture;
        this.sharedPrefsProvider = provider;
        this.applicationExitConfigurations = lazy;
        this.appExitCollectionEnabledProvider = provider2;
        this.appExitReasonsToReportProvider = provider3;
    }

    /* renamed from: lambda$sendInBackground$0$com-google-android-libraries-performance-primes-metrics-crash-applicationexit-ApplicationExitMetricServiceImpl, reason: not valid java name */
    public /* synthetic */ void m197x81a4fae8() {
        ContextDataProvider.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl$$ExternalSyntheticLambda0
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return ApplicationExitMetricServiceImpl.m196$r8$lambda$JszHidJDADHAMcTsJTyv8ZizdU(ApplicationExitMetricServiceImpl.this);
            }
        }, this.deferrableExecutor);
    }

    /* renamed from: lambda$sendInBackground$1$com-google-android-libraries-performance-primes-metrics-crash-applicationexit-ApplicationExitMetricServiceImpl, reason: not valid java name */
    public /* synthetic */ ListenableFuture m198x8fb124c7() {
        return DirectBootUtils.runWhenUnlocked(this.application, new Runnable() { // from class: com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                ApplicationExitMetricServiceImpl.this.m197x81a4fae8();
            }
        });
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x0027 */
    /* renamed from: lambda$sendInBackgroundAsFuture$2$com-google-android-libraries-performance-primes-metrics-crash-applicationexit-ApplicationExitMetricServiceImpl, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ java.lang.Void m199x72f88691(java.util.List r6, java.lang.Void r7) {
        /*
            r5 = this;
            r7 = 0
            java.lang.Object r6 = r6.get(r7)
            logs.proto.wireless.performance.mobile.ApplicationExitInfo r6 = (logs.proto.wireless.performance.mobile.ApplicationExitInfo) r6
        L7:
            java.lang.String r0 = r6.processName_
            long r1 = r6.timestampMillis_
            javax.inject.Provider r3 = r5.sharedPrefsProvider
            java.lang.Object r3 = r3.get()
            android.content.SharedPreferences r3 = (android.content.SharedPreferences) r3
            android.content.SharedPreferences$Editor r3 = r3.edit()
            java.lang.String r4 = "lastExitProcessName"
            android.content.SharedPreferences$Editor r0 = r3.putString(r4, r0)
            java.lang.String r3 = "lastExitTimestamp"
            android.content.SharedPreferences$Editor r0 = r0.putLong(r3, r1)
            boolean r0 = r0.commit()
            if (r0 != 0) goto L49
            int r7 = r7 + 1
            r0 = 3
            if (r7 < r0) goto L7
            com.google.common.flogger.GoogleLogger r6 = com.google.android.libraries.performance.primes.PrimesLoggerHolder.singletonLogger
            com.google.common.flogger.LoggingApi r6 = r6.atWarning()
            com.google.common.flogger.GoogleLogger$Api r6 = (com.google.common.flogger.GoogleLogger.Api) r6
            java.lang.String r7 = "updateLastRecordedAppExit"
            r0 = 180(0xb4, float:2.52E-43)
            java.lang.String r1 = "com/google/android/libraries/performance/primes/metrics/crash/applicationexit/ApplicationExitMetricServiceImpl"
            java.lang.String r2 = "ApplicationExitMetricServiceImpl.java"
            com.google.common.flogger.LoggingApi r6 = r6.withInjectedLogSite(r1, r7, r0, r2)
            com.google.common.flogger.GoogleLogger$Api r6 = (com.google.common.flogger.GoogleLogger.Api) r6
            java.lang.String r7 = "Failed to persist most recent App Exit"
            r6.log(r7)
        L49:
            r6 = 0
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl.m199x72f88691(java.util.List, java.lang.Void):java.lang.Void");
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public void onApplicationStartup() {
        sendInBackground();
    }

    public void sendInBackground() {
        ContextDataProvider.submitAsync(new AsyncCallable() { // from class: com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricServiceImpl$$ExternalSyntheticLambda2
            @Override // com.google.common.util.concurrent.AsyncCallable
            public final ListenableFuture call() {
                return ApplicationExitMetricServiceImpl.this.m198x8fb124c7();
            }
        }, this.deferrableExecutor);
    }
}
