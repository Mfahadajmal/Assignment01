package com.google.android.libraries.phenotype.client.stable;

import android.content.Context;
import android.os.RemoteException;
import android.support.v7.widget.DropDownListView;
import android.util.Log;
import androidx.concurrent.futures.CallbackToFutureAdapter$Completer;
import androidx.concurrent.futures.CallbackToFutureAdapter$Resolver;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9;
import com.google.android.apps.aicore.aidl.IDownloadListener;
import com.google.android.apps.aicore.aidl.IDownloadListener2;
import com.google.android.apps.aicore.client.api.AiCoreClient;
import com.google.android.apps.aicore.client.api.AiCoreException;
import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.apps.aicore.client.api.DownloadCallback;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda1;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda16;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl;
import com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.PhenotypeContext;
import com.google.android.libraries.storage.protostore.SingleProcProtoDataStore;
import com.google.android.libraries.storage.protostore.XDataStore;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.apps.tiktok.tracing.TracePropagation;
import com.google.common.base.Pair;
import com.google.common.collect.ImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.GwtFluentFutureCatchingSpecialization;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2 implements AsyncFunction {
    public final /* synthetic */ Object PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$0;
    public final /* synthetic */ Object PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$1;
    public final /* synthetic */ Object PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$2;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2(AiCoreClientImpl aiCoreClientImpl, AiFeature aiFeature, DownloadCallback downloadCallback, int i) {
        this.switching_field = i;
        this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$1 = aiCoreClientImpl;
        this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$2 = aiFeature;
        this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$0 = downloadCallback;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.Object, com.google.common.util.concurrent.AsyncFunction] */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v4, types: [com.google.android.apps.aicore.client.api.DownloadCallback, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.util.concurrent.Future, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r13v17, types: [java.util.concurrent.Executor, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v10, types: [java.lang.Object, com.google.common.util.concurrent.AsyncFunction] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.concurrent.Future, com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object obj) {
        String str;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return ((XDataStore) ((FloatingActionButton.ShadowDelegateImpl) this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$2).FloatingActionButton$ShadowDelegateImpl$ar$this$0).variant$ar$class_merging.update$ar$ds$bf118803_0(this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$0, this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$1);
                    }
                    ?? r13 = this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$1;
                    return ((XDataStore) this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$2).variant$ar$class_merging.update$ar$ds$bf118803_0(this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$0, r13);
                }
                ?? r0 = this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$0;
                ?? r1 = this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$1;
                boolean equals = ContextDataProvider.getDone(r0).equals(ContextDataProvider.getDone(r1));
                Object obj2 = this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$2;
                if (equals) {
                    return ContextDataProvider.immediateFuture(obj);
                }
                AsyncFunction propagateAsyncFunction = TracePropagation.propagateAsyncFunction(new AiCoreBaseService$$ExternalSyntheticLambda1(obj2, (Object) r1, 4));
                SingleProcProtoDataStore singleProcProtoDataStore = (SingleProcProtoDataStore) obj2;
                ListenableFuture create = AbstractTransformFuture.create((ListenableFuture) r1, propagateAsyncFunction, singleProcProtoDataStore.ioExecutor);
                synchronized (singleProcProtoDataStore.lock) {
                }
                return create;
            }
            final AiCoreClient.ServiceContext serviceContext = (AiCoreClient.ServiceContext) obj;
            final ?? r02 = this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$0;
            Object obj3 = this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$2;
            final AiCoreClientImpl aiCoreClientImpl = (AiCoreClientImpl) this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$1;
            final AiFeature aiFeature = (AiFeature) obj3;
            return serviceContext.getDisconnectSignal().propagate(DropDownListView.Api33Impl.getFuture(new CallbackToFutureAdapter$Resolver() { // from class: com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl$$ExternalSyntheticLambda5
                @Override // androidx.concurrent.futures.CallbackToFutureAdapter$Resolver
                public final Object attachCompleter(CallbackToFutureAdapter$Completer callbackToFutureAdapter$Completer) {
                    AiCoreClient.ServiceContext serviceContext2 = serviceContext;
                    AiFeature aiFeature2 = aiFeature;
                    try {
                        int apiVersion = serviceContext2.getService().getApiVersion();
                        DownloadCallback downloadCallback = r02;
                        AiCoreClientImpl aiCoreClientImpl2 = AiCoreClientImpl.this;
                        if (apiVersion > 0) {
                            serviceContext2.getService().requestDownloadableFeatureWithDownloadListener2$ar$ds(aiFeature2.toParcelableAiFeature(), new IDownloadListener2.Stub(aiCoreClientImpl2, downloadCallback, aiFeature2, callbackToFutureAdapter$Completer));
                        } else {
                            serviceContext2.getService().requestDownloadableFeatureWithDownloadListener$ar$ds(aiFeature2.toParcelableAiFeature(), new IDownloadListener.Stub(aiCoreClientImpl2, downloadCallback, aiFeature2, callbackToFutureAdapter$Completer));
                        }
                        return "requestDownloadableFeatureFuture";
                    } catch (RemoteException e) {
                        Log.e(AiCoreClientImpl.TAG, "AiCore service failed to download feature ".concat(aiFeature2.getName()), e);
                        callbackToFutureAdapter$Completer.setException$ar$ds(new AiCoreException(1, 6, "AICore service failed to download feature ".concat(aiFeature2.getName()), e));
                        return "requestDownloadableFeatureFuture";
                    }
                }
            }), new PrimesController$$ExternalSyntheticLambda9(5));
        }
        List list = (List) obj;
        PackageInfo packageInfo = (PackageInfo) this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$0;
        if (!packageInfo.accountScoped) {
            list = ImmutableList.of((Object) "");
        }
        ImmutableList.Builder builder = new ImmutableList.Builder();
        Iterator it = list.iterator();
        while (true) {
            Object obj4 = this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$2;
            if (it.hasNext()) {
                Object obj5 = this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$1;
                String str2 = (String) it.next();
                if (!PhenotypeUpdateBroadcastReceiver.packageAndAccountCallbacks.containsKey(new Pair(obj5, str2))) {
                    PhenotypeContext phenotypeContext = (PhenotypeContext) obj4;
                    SnapshotHandler snapshotHandler = new SnapshotHandler(phenotypeContext, (String) obj5, str2, packageInfo.directBootAware);
                    if (packageInfo.stickyAccountSupport) {
                        Context context = phenotypeContext.context;
                        str = PhenotypeStickyAccount.getPreferences(context).getString(packageInfo.configPackage, "");
                    } else {
                        str = str2;
                    }
                    ListenableFuture latestSnapshot = snapshotHandler.getLatestSnapshot(str);
                    builder.add$ar$ds$4f674a09_0(AbstractTransformFuture.create(AbstractTransformFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(latestSnapshot), new AiCoreBaseService$$ExternalSyntheticLambda16(snapshotHandler, 6), phenotypeContext.getExecutor()), new PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda5(phenotypeContext, latestSnapshot, packageInfo, str2, 1), phenotypeContext.getExecutor()));
                }
            } else {
                return ContextDataProvider.whenAllComplete$ar$class_merging$ar$class_merging(builder.build()).call(new TimerMetricServiceWithTracingImpl$$ExternalSyntheticLambda0(2), ((PhenotypeContext) obj4).getExecutor());
            }
        }
    }

    public /* synthetic */ PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2(PackageInfo packageInfo, String str, PhenotypeContext phenotypeContext, int i) {
        this.switching_field = i;
        this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$0 = packageInfo;
        this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$1 = str;
        this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$2 = phenotypeContext;
    }

    public /* synthetic */ PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2(Object obj, Object obj2, Object obj3, int i) {
        this.switching_field = i;
        this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$2 = obj;
        this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$0 = obj2;
        this.PhenotypeUpdateBackgroundBroadcastReceiver$$ExternalSyntheticLambda2$ar$f$1 = obj3;
    }
}
