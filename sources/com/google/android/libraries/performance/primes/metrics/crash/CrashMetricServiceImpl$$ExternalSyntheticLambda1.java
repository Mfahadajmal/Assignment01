package com.google.android.libraries.performance.primes.metrics.crash;

import android.net.Uri;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda16;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.performance.primes.metrics.core.Metric;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorder;
import com.google.android.libraries.performance.primes.metrics.network.NetworkMetricServiceImpl;
import com.google.android.libraries.storage.file.common.FileStorageUnavailableException;
import com.google.android.libraries.storage.protostore.ProtoDataStoreFactory;
import com.google.android.libraries.storage.protostore.SingleProcProtoDataStore;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.base.Optional;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncCallable;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PrimesStats;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class CrashMetricServiceImpl$$ExternalSyntheticLambda1 implements AsyncCallable {
    public final /* synthetic */ Object CrashMetricServiceImpl$$ExternalSyntheticLambda1$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ CrashMetricServiceImpl$$ExternalSyntheticLambda1(Object obj, int i) {
        this.switching_field = i;
        this.CrashMetricServiceImpl$$ExternalSyntheticLambda1$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v17, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v39, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v7, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v4, types: [com.google.common.base.Supplier, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r4v0, types: [com.google.common.base.Supplier, java.lang.Object] */
    @Override // com.google.common.util.concurrent.AsyncCallable
    public final ListenableFuture call() {
        int i = 1;
        switch (this.switching_field) {
            case 0:
                CrashMetricServiceImpl crashMetricServiceImpl = (CrashMetricServiceImpl) this.CrashMetricServiceImpl$$ExternalSyntheticLambda1$ar$f$0;
                if (crashMetricServiceImpl.isCrashLoopMonitorEnabled() && !crashMetricServiceImpl.loggedCrashLoopMonitorInitialized.getAndSet(true)) {
                    return crashMetricServiceImpl.recordStartupEventWithSampling$ar$edu$4790f8f2_0(SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_CRASH_LOOP_MONITOR_INITIALIZED$ar$edu, (CrashConfigurations) crashMetricServiceImpl.configs.get(), ((CrashLoopMonitorFlags) crashMetricServiceImpl.crashLoopMonitorFlags.get()).logInitSampleRate_);
                }
                return ImmediateFuture.NULL;
            case 1:
                ProtoDataStoreFactory protoDataStoreFactory = (ProtoDataStoreFactory) this.CrashMetricServiceImpl$$ExternalSyntheticLambda1$ar$f$0;
                Optional optional = (Optional) protoDataStoreFactory.ProtoDataStoreFactory$ar$executor.get();
                Optional optional2 = (Optional) protoDataStoreFactory.ProtoDataStoreFactory$ar$variantFactories.get();
                if (optional.isPresent() && optional2.isPresent()) {
                    CrashCounter crashCounter = new CrashCounter((File) optional.get(), (String) optional2.get());
                    int i2 = crashCounter.get();
                    crashCounter.getFile().delete();
                    crashCounter.value = 0;
                    crashCounter.loaded = true;
                    if (i2 >= ((CrashLoopMonitorFlags) protoDataStoreFactory.ProtoDataStoreFactory$ar$obfuscator.get()).detectionThreshold_) {
                        Object obj = protoDataStoreFactory.ProtoDataStoreFactory$ar$pdsConfigs;
                        int i3 = SystemHealthProto$PrimesStats.PrimesEvent.PRIMES_CRASH_LOOP_MONITOR_RECOVERED$ar$edu;
                        Metric.Builder newBuilder = Metric.newBuilder();
                        SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$SystemHealthMetric.DEFAULT_INSTANCE.createBuilder();
                        SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) SystemHealthProto$PrimesStats.DEFAULT_INSTANCE.createBuilder();
                        builder2.copyOnWrite();
                        SystemHealthProto$PrimesStats systemHealthProto$PrimesStats = (SystemHealthProto$PrimesStats) builder2.instance;
                        int i4 = i3 - 1;
                        if (i3 != 0) {
                            systemHealthProto$PrimesStats.primesEvent_ = i4;
                            systemHealthProto$PrimesStats.bitField0_ |= 1;
                            builder.copyOnWrite();
                            SystemHealthProto$SystemHealthMetric systemHealthProto$SystemHealthMetric = (SystemHealthProto$SystemHealthMetric) builder.instance;
                            SystemHealthProto$PrimesStats systemHealthProto$PrimesStats2 = (SystemHealthProto$PrimesStats) builder2.build();
                            systemHealthProto$PrimesStats2.getClass();
                            systemHealthProto$SystemHealthMetric.primesStats_ = systemHealthProto$PrimesStats2;
                            systemHealthProto$SystemHealthMetric.bitField0_ |= 4194304;
                            newBuilder.setMetric$ar$ds((SystemHealthProto$SystemHealthMetric) builder.build());
                            return ((MetricRecorder) obj).recordMetric(newBuilder.build());
                        }
                        throw null;
                    }
                    return ImmediateFuture.NULL;
                }
                return ImmediateFuture.NULL;
            case 2:
                CrashMetricServiceImpl crashMetricServiceImpl2 = (CrashMetricServiceImpl) this.CrashMetricServiceImpl$$ExternalSyntheticLambda1$ar$f$0;
                if (crashMetricServiceImpl2.isCrashLoopMonitorEnabled()) {
                    ProtoDataStoreFactory protoDataStoreFactory2 = crashMetricServiceImpl2.crashLoopMonitor$ar$class_merging;
                    if (!((AtomicBoolean) protoDataStoreFactory2.ProtoDataStoreFactory$ar$pdsCache).getAndSet(false)) {
                        ListenableFuture listenableFuture = ImmediateFuture.NULL;
                    } else {
                        ContextDataProvider.submitAsync(new CrashMetricServiceImpl$$ExternalSyntheticLambda1(protoDataStoreFactory2, i), protoDataStoreFactory2.ProtoDataStoreFactory$ar$logger$ar$class_merging$b9e9d160_0);
                    }
                }
                return ImmediateFuture.NULL;
            case 3:
                return ImmediateFuture.NULL;
            case 4:
                return ((NetworkMetricServiceImpl) this.CrashMetricServiceImpl$$ExternalSyntheticLambda1$ar$f$0).sendPendingEvents();
            case 5:
                Object obj2 = this.CrashMetricServiceImpl$$ExternalSyntheticLambda1$ar$f$0;
                AsyncFunction propagateAsyncFunction = TracePropagation.propagateAsyncFunction(new AiCoreBaseService$$ExternalSyntheticLambda16(obj2, 11));
                SingleProcProtoDataStore singleProcProtoDataStore = (SingleProcProtoDataStore) obj2;
                return ContextDataProvider.nonCancellationPropagating(AbstractTransformFuture.create(singleProcProtoDataStore.fileFuture, propagateAsyncFunction, singleProcProtoDataStore.ioExecutor));
            case 6:
                Object obj3 = this.CrashMetricServiceImpl$$ExternalSyntheticLambda1$ar$f$0;
                SingleProcProtoDataStore singleProcProtoDataStore2 = (SingleProcProtoDataStore) obj3;
                try {
                    return ContextDataProvider.immediateFuture(((SingleProcProtoDataStore) obj3).readDataSync((Uri) ContextDataProvider.getDone(singleProcProtoDataStore2.fileFuture)));
                } catch (IOException e) {
                    StatsStorage statsStorage = new StatsStorage(obj3);
                    if (!singleProcProtoDataStore2.optionalIOExceptionHandler.isPresent()) {
                        return ContextDataProvider.immediateFailedFuture(e);
                    }
                    if (!(e instanceof FileStorageUnavailableException) && !(e.getCause() instanceof FileStorageUnavailableException)) {
                        return AbstractTransformFuture.create(((AppLifecycleMonitor) singleProcProtoDataStore2.optionalIOExceptionHandler.get()).handleReadException$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(e, statsStorage), TracePropagation.propagateAsyncFunction(new AiCoreBaseService$$ExternalSyntheticLambda16(obj3, 10)), singleProcProtoDataStore2.ioExecutor);
                    }
                    return ContextDataProvider.immediateFailedFuture(e);
                }
            default:
                return this.CrashMetricServiceImpl$$ExternalSyntheticLambda1$ar$f$0;
        }
    }
}
