package com.google.android.apps.aicore.client.api.internal;

import android.content.Context;
import android.net.Uri;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import android.support.v7.widget.DropDownListView;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9;
import com.google.android.accessibility.talkback.contextmenu.ListMenuManager$$ExternalSyntheticLambda3;
import com.google.android.apps.aicore.client.api.AiCoreException;
import com.google.android.apps.aicore.client.api.DownloadCallback;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.transmitter.MetricSnapshot;
import com.google.android.libraries.performance.primes.transmitter.clearcut.ClearcutMetricTransmitter;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.phenotype.client.stable.PhenotypeAccountStore;
import com.google.android.libraries.storage.protostore.SingleProcProtoDataStore;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Executor;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SystemHealthMetric;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AiCoreBaseService$$ExternalSyntheticLambda1 implements AsyncFunction {
    public final /* synthetic */ Object AiCoreBaseService$$ExternalSyntheticLambda1$ar$f$0;
    public final /* synthetic */ Object f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ AiCoreBaseService$$ExternalSyntheticLambda1(AiCoreBaseService aiCoreBaseService, DownloadCallback downloadCallback, int i) {
        this.switching_field = i;
        this.f$1 = aiCoreBaseService;
        this.AiCoreBaseService$$ExternalSyntheticLambda1$ar$f$0 = downloadCallback;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.google.android.apps.aicore.client.api.DownloadCallback, java.lang.Object] */
    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object obj) {
        int i = this.switching_field;
        int i2 = 3;
        if (i != 0) {
            boolean z = true;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        Object obj2 = this.AiCoreBaseService$$ExternalSyntheticLambda1$ar$f$0;
                        SingleProcProtoDataStore singleProcProtoDataStore = (SingleProcProtoDataStore) obj2;
                        singleProcProtoDataStore.writeDataSync((Uri) ContextDataProvider.getDone(singleProcProtoDataStore.fileFuture), obj);
                        Object obj3 = singleProcProtoDataStore.lock;
                        ?? r1 = this.f$1;
                        synchronized (obj3) {
                            ((SingleProcProtoDataStore) obj2).cachedData = r1;
                        }
                        return ContextDataProvider.immediateFuture(obj);
                    }
                    ConcurrentMap concurrentMap = PhenotypeAccountStore.accountCommitterByPackage;
                    ImmutableList.Builder builder = new ImmutableList.Builder();
                    PhenotypeContext phenotypeContext = (PhenotypeContext) this.AiCoreBaseService$$ExternalSyntheticLambda1$ar$f$0;
                    builder.add$ar$ds$4f674a09_0(phenotypeContext.context);
                    int i3 = DirectBootUtils.DirectBootUtils$ar$NoOp;
                    builder.add$ar$ds$4f674a09_0(DirectBootUtils.getDeviceProtectedStorageContextOrFallback(phenotypeContext.context));
                    ImmutableList build = builder.build();
                    int i4 = ((RegularImmutableList) build).size;
                    for (int i5 = 0; i5 < i4; i5++) {
                        Object obj4 = this.f$1;
                        File file = new File(String.valueOf(((Context) build.get(i5)).getFilesDir()) + "/phenotype/shared/" + ((String) obj4));
                        if (file.exists()) {
                            z = PhenotypeAccountStore.deleteRecursively(file);
                        }
                    }
                    if (z) {
                        return ImmediateFuture.NULL;
                    }
                    return ContextDataProvider.immediateFailedFuture(new IOException("Unable to remove snapshots for removed user"));
                }
                SchedulerOptions.Builder builder2 = (SchedulerOptions.Builder) ((MetricSnapshot) obj).toBuilder();
                builder2.copyOnWrite();
                MetricSnapshot metricSnapshot = (MetricSnapshot) builder2.instance;
                Object obj5 = this.f$1;
                obj5.getClass();
                metricSnapshot.systemHealthMetric_ = (SystemHealthProto$SystemHealthMetric) obj5;
                metricSnapshot.bitField0_ |= 1;
                MetricSnapshot metricSnapshot2 = (MetricSnapshot) builder2.build();
                ClearcutMetricTransmitter clearcutMetricTransmitter = (ClearcutMetricTransmitter) this.AiCoreBaseService$$ExternalSyntheticLambda1$ar$f$0;
                return clearcutMetricTransmitter.snapshotTransmitter.transmit(clearcutMetricTransmitter.context, metricSnapshot2);
            }
            Integer num = (Integer) obj;
            int intValue = num.intValue();
            ?? r7 = this.AiCoreBaseService$$ExternalSyntheticLambda1$ar$f$0;
            Object obj6 = this.f$1;
            if (intValue != 0) {
                if (intValue != 1 && intValue != 2) {
                    if (intValue != 3) {
                        Objects.toString(num);
                        return ContextDataProvider.immediateFailedFuture(AiCoreException.newDownloadError("Unexpected feature status: ".concat(String.valueOf(num))));
                    }
                    AiCoreBaseService aiCoreBaseService = (AiCoreBaseService) obj6;
                    aiCoreBaseService.feature.getName();
                    aiCoreBaseService.callbackExecutor.execute(new ListMenuManager$$ExternalSyntheticLambda3(obj6, (Object) r7, 7, (char[]) null));
                    return ImmediateFuture.NULL;
                }
                AiCoreBaseService aiCoreBaseService2 = (AiCoreBaseService) obj6;
                return aiCoreBaseService2.client.requestDownloadableFeature(aiCoreBaseService2.feature, r7);
            }
            Executor executor = ((AiCoreBaseService) obj6).callbackExecutor;
            AiCoreException newDownloadError = AiCoreException.newDownloadError("Feature is unavailable.");
            executor.execute(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(obj6, (Object) r7, (Object) newDownloadError, 12, (byte[]) null));
            return ContextDataProvider.immediateFailedFuture(newDownloadError);
        }
        AiCoreBaseService.InferenceServiceContext inferenceServiceContext = (AiCoreBaseService.InferenceServiceContext) obj;
        return inferenceServiceContext.disconnectSignal().propagate(DropDownListView.Api33Impl.getFuture(new AiCoreBaseService$$ExternalSyntheticLambda17((AiCoreBaseService) this.AiCoreBaseService$$ExternalSyntheticLambda1$ar$f$0, inferenceServiceContext, this.f$1, 0)), new PrimesController$$ExternalSyntheticLambda9(i2));
    }

    public /* synthetic */ AiCoreBaseService$$ExternalSyntheticLambda1(Object obj, Object obj2, int i) {
        this.switching_field = i;
        this.AiCoreBaseService$$ExternalSyntheticLambda1$ar$f$0 = obj;
        this.f$1 = obj2;
    }
}
