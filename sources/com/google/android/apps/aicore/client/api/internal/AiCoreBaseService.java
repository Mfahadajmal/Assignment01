package com.google.android.apps.aicore.client.api.internal;

import com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint;
import com.google.android.apps.aicore.aidl.IAICoreService;
import com.google.android.apps.aicore.aidl.ICancellationCallback;
import com.google.android.apps.aicore.aidl.IImageDescriptionResultCallback;
import com.google.android.apps.aicore.aidl.IImageDescriptionStreamingCallback;
import com.google.android.apps.aicore.aidl.IPrepareInferenceEngineCallback;
import com.google.android.apps.aicore.client.api.AiCoreClient;
import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.apps.aicore.client.api.DownloadCallback;
import com.google.android.apps.aicore.client.api.imagedescription.ImageDescriptionRequest;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.gms.common.api.GoogleApi;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.GwtFluentFutureCatchingSpecialization;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import java.util.List;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AiCoreBaseService {
    public static final String TAG = "AiCoreBaseService";
    private ListenableFuture cachedServiceContext;
    public final Executor callbackExecutor;
    public final AiCoreClient client;
    private final DownloadCallback downloadCallback;
    protected final AiFeature feature;
    public ListenableFuture inferenceServiceContext;
    public final Object lock;
    public final ListeningExecutorService workerExecutor;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class InferenceServiceContext {
        private final FailureSignal disconnectSignal;
        private final Object getService;

        public InferenceServiceContext() {
        }

        public final FailureSignal disconnectSignal() {
            return this.disconnectSignal;
        }

        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof InferenceServiceContext) {
                InferenceServiceContext inferenceServiceContext = (InferenceServiceContext) obj;
                if (this.getService.equals(inferenceServiceContext.getService()) && this.disconnectSignal.equals(inferenceServiceContext.disconnectSignal())) {
                    return true;
                }
            }
            return false;
        }

        public final Object getService() {
            return this.getService;
        }

        public final int hashCode() {
            return ((this.getService.hashCode() ^ 1000003) * 1000003) ^ this.disconnectSignal.hashCode();
        }

        public final String toString() {
            FailureSignal failureSignal = this.disconnectSignal;
            return "InferenceServiceContext{getService=" + this.getService.toString() + ", disconnectSignal=" + failureSignal.toString() + "}";
        }

        public InferenceServiceContext(Object obj, FailureSignal failureSignal) {
            this();
            if (obj == null) {
                throw new NullPointerException("Null getService");
            }
            this.getService = obj;
            this.disconnectSignal = failureSignal;
        }
    }

    protected AiCoreBaseService(AiCoreClient aiCoreClient, AiFeature aiFeature, DownloadCallback downloadCallback) {
        this.lock = new Object();
        this.client = aiCoreClient;
        this.feature = aiFeature;
        this.downloadCallback = downloadCallback;
        AiCoreClientImpl aiCoreClientImpl = (AiCoreClientImpl) aiCoreClient;
        this.workerExecutor = aiCoreClientImpl.workerExecutor;
        this.callbackExecutor = aiCoreClientImpl.callbackExecutor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final /* bridge */ /* synthetic */ Object createFeatureService(IAICoreService iAICoreService) {
        return iAICoreService.getImageDescriptionService(this.feature.toParcelableAiFeature());
    }

    public final ListenableFuture getOrCreateServiceContext() {
        ListenableFuture listenableFuture;
        synchronized (this.lock) {
            if (this.cachedServiceContext == this.client.getServiceContext() && (listenableFuture = this.inferenceServiceContext) != null) {
                return listenableFuture;
            }
            int i = 1;
            ListenableFuture create = AbstractTransformFuture.create(AbstractTransformFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(AbstractTransformFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(this.client.getFeatureStatus(this.feature)), new AiCoreBaseService$$ExternalSyntheticLambda1(this, this.downloadCallback, i), this.workerExecutor)), new AiCoreBaseService$$ExternalSyntheticLambda16(this, i), this.workerExecutor), new AiCoreBaseService$$ExternalSyntheticLambda16(this, 0), this.workerExecutor);
            this.inferenceServiceContext = create;
            this.cachedServiceContext = this.client.getServiceContext();
            ContextDataProvider.addCallback(create, new AiCoreEndpoint.DownloadCallBackInternal.AnonymousClass1(this, 2), DirectExecutor.INSTANCE);
            return create;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    public final /* bridge */ /* synthetic */ ICancellationCallback prepareInferenceEngineInternal(Object obj, IPrepareInferenceEngineCallback iPrepareInferenceEngineCallback) {
        return obj.prepareInferenceEngine(iPrepareInferenceEngineCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v4, types: [java.util.List, java.lang.Object] */
    public final /* bridge */ /* synthetic */ ICancellationCallback runInferenceInternal$ar$class_merging$53e9187e_0$ar$class_merging(Object obj, Object obj2, ApplicationModule applicationModule) {
        GoogleApi.Settings.Builder builder = new GoogleApi.Settings.Builder((byte[]) null, (byte[]) null);
        List images = ((ImageDescriptionRequest) obj2).getImages();
        if (images != null) {
            builder.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging = images;
            builder.GoogleApi$Settings$Builder$ar$looper = null;
            ?? r4 = builder.GoogleApi$Settings$Builder$ar$mapper$ar$class_merging;
            if (r4 != 0) {
                return obj.runCancellableInference(new com.google.android.apps.aicore.aidl.ImageDescriptionRequest((List) r4, (IImageDescriptionStreamingCallback) null), new IImageDescriptionResultCallback.Stub(applicationModule));
            }
            throw new IllegalStateException("Missing required properties: images");
        }
        throw new NullPointerException("Null images");
    }

    public AiCoreBaseService(AiCoreClient aiCoreClient, AiFeature aiFeature, DownloadCallback downloadCallback, byte[] bArr) {
        this(aiCoreClient, aiFeature, downloadCallback);
    }
}
