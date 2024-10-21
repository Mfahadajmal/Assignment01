package com.google.android.libraries.performance.primes.transmitter.clearcut;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.transmitter.AccountProvider;
import com.google.android.libraries.performance.primes.transmitter.ExperimentsProvider;
import com.google.android.libraries.performance.primes.transmitter.MetricSnapshot;
import com.google.android.libraries.performance.primes.transmitter.ZwiebackCookieOverrideProvider;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import java.util.List;
import java.util.concurrent.Callable;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClearcutMetricSnapshotBuilder {
    private final AccountProvider accountProvider;
    public final boolean anonymous;
    private final ExperimentsProvider experimentsProvider;
    public final String logSource;
    public final String mendelPackageName;
    private final ZwiebackCookieOverrideProvider zwiebackCookieOverrideProvider;

    public ClearcutMetricSnapshotBuilder(String str, String str2, AccountProvider accountProvider, ExperimentsProvider experimentsProvider, ZwiebackCookieOverrideProvider zwiebackCookieOverrideProvider, boolean z) {
        str2.getClass();
        this.logSource = str2;
        accountProvider.getClass();
        this.accountProvider = accountProvider;
        experimentsProvider.getClass();
        this.experimentsProvider = experimentsProvider;
        zwiebackCookieOverrideProvider.getClass();
        this.zwiebackCookieOverrideProvider = zwiebackCookieOverrideProvider;
        this.anonymous = z;
        this.mendelPackageName = "com.google.android.libraries.performance.primes#".concat(String.valueOf(str));
    }

    public final ListenableFuture buildExtension() {
        final ListenableFuture accountName = this.accountProvider.getAccountName();
        final ListenableFuture experimentIds = this.experimentsProvider.getExperimentIds();
        final ListenableFuture zwiebackCookieOverride = this.zwiebackCookieOverrideProvider.getZwiebackCookieOverride();
        return ContextDataProvider.whenAllComplete$ar$class_merging$c090da7e_0$ar$class_merging(accountName, experimentIds, zwiebackCookieOverride).call(new Callable() { // from class: com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricSnapshotBuilder$$ExternalSyntheticLambda0
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.concurrent.Callable
            public final Object call() {
                SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) ClearcutMetricSnapshot.DEFAULT_INSTANCE.createBuilder();
                builder.copyOnWrite();
                ClearcutMetricSnapshot clearcutMetricSnapshot = (ClearcutMetricSnapshot) builder.instance;
                clearcutMetricSnapshot.bitField0_ |= 1;
                ClearcutMetricSnapshotBuilder clearcutMetricSnapshotBuilder = ClearcutMetricSnapshotBuilder.this;
                clearcutMetricSnapshot.logSource_ = clearcutMetricSnapshotBuilder.logSource;
                builder.copyOnWrite();
                ClearcutMetricSnapshot clearcutMetricSnapshot2 = (ClearcutMetricSnapshot) builder.instance;
                clearcutMetricSnapshot2.bitField0_ |= 2;
                clearcutMetricSnapshot2.mendelPackageName_ = clearcutMetricSnapshotBuilder.mendelPackageName;
                builder.copyOnWrite();
                ClearcutMetricSnapshot clearcutMetricSnapshot3 = (ClearcutMetricSnapshot) builder.instance;
                clearcutMetricSnapshot3.bitField0_ |= 4;
                clearcutMetricSnapshot3.anonymous_ = clearcutMetricSnapshotBuilder.anonymous;
                builder.copyOnWrite();
                ClearcutMetricSnapshot clearcutMetricSnapshot4 = (ClearcutMetricSnapshot) builder.instance;
                clearcutMetricSnapshot4.bitField0_ |= 32;
                clearcutMetricSnapshot4.requireCheckbox_ = false;
                ListenableFuture listenableFuture = accountName;
                ListenableFuture listenableFuture2 = experimentIds;
                ListenableFuture listenableFuture3 = zwiebackCookieOverride;
                try {
                    Optional optional = (Optional) ContextDataProvider.getDone(listenableFuture);
                    if (optional.isPresent()) {
                        String str = (String) optional.get();
                        builder.copyOnWrite();
                        ClearcutMetricSnapshot clearcutMetricSnapshot5 = (ClearcutMetricSnapshot) builder.instance;
                        clearcutMetricSnapshot5.bitField0_ |= 16;
                        clearcutMetricSnapshot5.uploadAccountName_ = str;
                    }
                } catch (Exception e) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotBuilder", "lambda$buildExtension$0", 94, "ClearcutMetricSnapshotBuilder.java")).log("Failed to set Account Name, falling back to Zwieback logging.");
                }
                try {
                    List list = (List) ContextDataProvider.getDone(listenableFuture2);
                    builder.copyOnWrite();
                    ClearcutMetricSnapshot clearcutMetricSnapshot6 = (ClearcutMetricSnapshot) builder.instance;
                    Internal.IntList intList = clearcutMetricSnapshot6.experimentIds_;
                    if (!intList.isModifiable()) {
                        clearcutMetricSnapshot6.experimentIds_ = GeneratedMessageLite.mutableCopy(intList);
                    }
                    AbstractMessageLite.addAll(list, clearcutMetricSnapshot6.experimentIds_);
                } catch (Exception e2) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withCause(e2)).withInjectedLogSite("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotBuilder", "lambda$buildExtension$0", 102, "ClearcutMetricSnapshotBuilder.java")).log("Failed to set external Experiment Ids.");
                }
                try {
                    Optional optional2 = (Optional) ContextDataProvider.getDone(listenableFuture3);
                    if (optional2.isPresent()) {
                        String str2 = (String) optional2.get();
                        builder.copyOnWrite();
                        ClearcutMetricSnapshot clearcutMetricSnapshot7 = (ClearcutMetricSnapshot) builder.instance;
                        clearcutMetricSnapshot7.bitField0_ |= 8;
                        clearcutMetricSnapshot7.zwiebackCookieOverride_ = str2;
                    }
                } catch (Exception e3) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withCause(e3)).withInjectedLogSite("com/google/android/libraries/performance/primes/transmitter/clearcut/ClearcutMetricSnapshotBuilder", "lambda$buildExtension$0", BrailleInputEvent.CMD_CONTROL_NEXT, "ClearcutMetricSnapshotBuilder.java")).log("Failed to set Zwieback.");
                }
                SchedulerOptions.Builder builder2 = (SchedulerOptions.Builder) MetricSnapshot.DEFAULT_INSTANCE.createBuilder();
                builder2.setExtension$ar$ds(ClearcutMetricSnapshot.clearcutMetricSnapshot, (ClearcutMetricSnapshot) builder.build());
                return (MetricSnapshot) builder2.build();
            }
        }, DirectExecutor.INSTANCE);
    }
}
