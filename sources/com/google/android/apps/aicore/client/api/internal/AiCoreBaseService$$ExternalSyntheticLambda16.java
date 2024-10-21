package com.google.android.apps.aicore.client.api.internal;

import android.net.Uri;
import android.os.RemoteException;
import android.support.v7.widget.DropDownListView;
import android.util.Log;
import com.google.android.accessibility.selecttospeak.PrimesController$$ExternalSyntheticLambda9;
import com.google.android.apps.aicore.aidl.IAICoreService;
import com.google.android.apps.aicore.client.api.AiCoreClient;
import com.google.android.apps.aicore.client.api.AiCoreException;
import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService;
import com.google.android.libraries.performance.primes.metrics.jank.JankMetricService;
import com.google.android.libraries.phenotype.client.api.PhenotypeRuntimeException;
import com.google.android.libraries.phenotype.client.stable.FlagStore;
import com.google.android.libraries.phenotype.client.stable.SnapshotHandler;
import com.google.android.libraries.phenotype.client.stable.SnapshotProto$Snapshot;
import com.google.android.libraries.phenotype.client.stable.StorageInfoHandler;
import com.google.android.libraries.storage.protostore.SingleProcProtoDataStore;
import com.google.android.libraries.storage.protostore.XDataStore;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AiCoreBaseService$$ExternalSyntheticLambda16 implements AsyncFunction {
    public final /* synthetic */ Object AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ AiCoreBaseService$$ExternalSyntheticLambda16(Object obj, int i) {
        this.switching_field = i;
        this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v30, types: [com.google.common.base.Function, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v35, types: [com.google.android.libraries.storage.protostore.ProtoDataMigration, java.lang.Object] */
    @Override // com.google.common.util.concurrent.AsyncFunction
    public final ListenableFuture apply(Object obj) {
        ListenableFuture listenableFuture;
        int i = 0;
        switch (this.switching_field) {
            case 0:
                AiCoreClient.ServiceContext serviceContext = (AiCoreClient.ServiceContext) obj;
                try {
                    return ContextDataProvider.immediateFuture(new AiCoreBaseService.InferenceServiceContext(((AiCoreBaseService) this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0).createFeatureService(serviceContext.getService()), serviceContext.getDisconnectSignal()));
                } catch (RemoteException e) {
                    return ContextDataProvider.immediateFailedFuture(new AiCoreException(2, 6, "Failed to initialize service.", e));
                } catch (RuntimeException e2) {
                    return ContextDataProvider.immediateFailedFuture(new AiCoreException(2, 0, "Failed to initialize service.", e2));
                }
            case 1:
                return ((AiCoreBaseService) this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0).client.getServiceContext();
            case 2:
                AiCoreBaseService.InferenceServiceContext inferenceServiceContext = (AiCoreBaseService.InferenceServiceContext) obj;
                return inferenceServiceContext.disconnectSignal().propagate(DropDownListView.Api33Impl.getFuture(new AiCoreBaseService$$ExternalSyntheticLambda4(this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0, inferenceServiceContext, i)), new PrimesController$$ExternalSyntheticLambda9(4));
            case 3:
                IAICoreService iAICoreService = (IAICoreService) obj;
                String str = AiCoreClientImpl.TAG;
                Object obj2 = this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0;
                try {
                    return ContextDataProvider.immediateFuture(Long.valueOf(iAICoreService.getDownloadableSizeInBytes(((AiFeature) obj2).toParcelableAiFeature())));
                } catch (RemoteException e3) {
                    AiFeature aiFeature = (AiFeature) obj2;
                    Log.e(AiCoreClientImpl.TAG, "AiCore service failed to get downloadable size for ".concat(aiFeature.getName()), e3);
                    return ContextDataProvider.immediateFailedFuture(new AiCoreException(3, 6, "AiCore service failed to get downloadable size for ".concat(aiFeature.getName()), e3));
                }
            case 4:
                return ((SnapshotHandler) this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0).updateStoredSnapshot((SnapshotProto$Snapshot) obj);
            case 5:
                PhenotypeRuntimeException phenotypeRuntimeException = (PhenotypeRuntimeException) obj;
                int i2 = phenotypeRuntimeException.errorCode;
                if (i2 == 29501 || i2 == 29537 || i2 == 29538 || i2 == 29539 || i2 == 29540 || i2 == 29541 || i2 == 29542 || i2 == 29543 || i2 == 29544) {
                    Object obj3 = this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0;
                    int i3 = phenotypeRuntimeException.errorCode;
                    FlagStore flagStore = (FlagStore) obj3;
                    boolean shouldUseSharedStorage = flagStore.snapshotHandler.shouldUseSharedStorage();
                    String str2 = "Failed to commit due to stale snapshot for " + flagStore.configPackage + ". Experiments may be delayed til next app start. Error code: " + i3;
                    if (!shouldUseSharedStorage) {
                        str2 = str2.concat(". Triggering flag update.");
                    }
                    Log.w("MobStoreFlagStore", str2);
                    if (!shouldUseSharedStorage) {
                        flagStore.handleFlagUpdates();
                    }
                }
                return ContextDataProvider.immediateFailedFuture(phenotypeRuntimeException);
            case 6:
                return ((SnapshotHandler) this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0).updateStoredSnapshot((SnapshotProto$Snapshot) obj);
            case 7:
                return ContextDataProvider.nonCancellationPropagating((ListenableFuture) ((StorageInfoHandler) this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0).memoizedStorageInfosUpdateFromGms.get());
            case 8:
                return this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0.migrate$ar$ds();
            case 9:
                SingleProcProtoDataStore singleProcProtoDataStore = (SingleProcProtoDataStore) this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0;
                singleProcProtoDataStore.writeDataSync((Uri) ContextDataProvider.getDone(singleProcProtoDataStore.fileFuture), obj);
                return ImmediateFuture.NULL;
            case 10:
                SingleProcProtoDataStore singleProcProtoDataStore2 = (SingleProcProtoDataStore) this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0;
                return ContextDataProvider.immediateFuture(singleProcProtoDataStore2.readDataSync((Uri) ContextDataProvider.getDone(singleProcProtoDataStore2.fileFuture)));
            case 11:
                Uri uri = (Uri) obj;
                Uri addSuffix = JankMetricService.addSuffix(uri, ".bak");
                Object obj4 = this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0;
                try {
                    if (((SingleProcProtoDataStore) obj4).storage$ar$class_merging$ar$class_merging$ar$class_merging.exists(addSuffix)) {
                        ((SingleProcProtoDataStore) obj4).storage$ar$class_merging$ar$class_merging$ar$class_merging.rename(addSuffix, uri);
                    }
                    return ImmediateFuture.NULL;
                } catch (IOException e4) {
                    return ContextDataProvider.immediateFailedFuture(e4);
                }
            case 12:
                Object obj5 = this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0;
                synchronized (((SingleProcProtoDataStore) obj5).lock) {
                    listenableFuture = ((SingleProcProtoDataStore) obj5).cachedData;
                }
                return listenableFuture;
            case 13:
                return ContextDataProvider.immediateFuture(this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0.apply(obj));
            case 14:
                return ((XDataStore) this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0).variantInit.get();
            case 15:
                return ((XDataStore) this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0).m206x3ee98819();
            default:
                Object obj6 = this.AiCoreBaseService$$ExternalSyntheticLambda16$ar$f$0;
                ((IOException) obj6).addSuppressed((IOException) obj);
                throw ((Throwable) obj6);
        }
    }
}
