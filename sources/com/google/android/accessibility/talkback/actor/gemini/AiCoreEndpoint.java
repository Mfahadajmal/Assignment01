package com.google.android.accessibility.talkback.actor.gemini;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.accessibility.braille.brailledisplay.platform.Connectioneer$AspectConnection$$ExternalSyntheticLambda2;
import com.google.android.accessibility.talkback.TalkBackAnalyticsImpl;
import com.google.android.accessibility.talkback.VoiceActionMonitor$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.actor.gemini.ArateaEndpoint;
import com.google.android.accessibility.talkback.actor.gemini.GeminiActor;
import com.google.android.accessibility.talkback.actor.gemini.GeminiFunctionUtils;
import com.google.android.accessibility.talkback.dialog.BaseDialog;
import com.google.android.accessibility.talkback.imagedescription.ImageDescriptionProcessor;
import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.apps.aicore.client.api.AiCoreClient;
import com.google.android.apps.aicore.client.api.AiCoreClientOptions;
import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.apps.aicore.client.api.DownloadCallback;
import com.google.android.apps.aicore.client.api.imagedescription.ImageDescriptionRequest;
import com.google.android.apps.aicore.client.api.imagedescription.ImageDescriptionServiceOptions;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda1;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda16;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.auth.oauth2.OAuth2Credentials;
import com.google.common.collect.Cut;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Range;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import googledata.experiments.mobile.accessibility_suite.features.GeminiConfig;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AiCoreEndpoint implements GeminiActor.GeminiEndpoint, SharedPreferences.OnSharedPreferenceChangeListener {
    public final AiCoreClient aiCoreClient;
    private final AiCorePackageChangeReceiver aiCorePackageChangeReceiver;
    public AiServiceUpdateDialog aiCoreUpdateDialog;
    private long aiCoreVersion;
    public AiFeature aiFeature;
    public ListenableFuture aiFeatureSize;
    public AiServiceUpdateDialog astreaUpdateDialog;
    public final Context context;
    public AiFeatureDownloadCallback downloadCallback;
    public AiFeatureDownloadDialog downloadDialog;
    private final Handler handler;
    public boolean hasAiCore;
    public boolean hasRequestDownload;
    private AiCoreBaseService imageDescriptionService$ar$class_merging;
    private int initializeAiFeatureRetryCount;
    public ListenableFuture listFeatures;
    private Pair outstandingRequest;
    public final SharedPreferences perfs;
    private ListenableFuture preparingService;
    private ListenableFuture requestDownloadFuture;
    public int status;
    public final boolean withService;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AiCorePackageChangeReceiver extends BroadcastReceiver {
        public AiCorePackageChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "android.intent.action.PACKAGE_REPLACED")) {
                String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                LogUtils.d("AiCoreEndpointGemini", "package updated : %s", schemeSpecificPart);
                if (TextUtils.equals(schemeSpecificPart, "com.google.android.aicore")) {
                    AiCoreEndpoint.this.initializeAiFeature();
                }
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface AiFeatureDownloadCallback {
        void onDownloadCompleted();

        void onDownloadProgress(long j, long j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DownloadCallBackInternal implements DownloadCallback {
        public long sizeInBytes = -1;

        /* compiled from: PG */
        /* renamed from: com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint$DownloadCallBackInternal$1, reason: invalid class name */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 implements FutureCallback {
            final /* synthetic */ Object AiCoreEndpoint$DownloadCallBackInternal$1$ar$this$1;
            private final /* synthetic */ int switching_field;

            public AnonymousClass1(Object obj, int i) {
                this.switching_field = i;
                this.AiCoreEndpoint$DownloadCallBackInternal$1$ar$this$1 = obj;
            }

            @Override // com.google.common.util.concurrent.FutureCallback
            public final void onFailure(Throwable th) {
                int i = this.switching_field;
                if (i != 0) {
                    if (i != 1) {
                        if (i != 2) {
                            ((AbstractFuture) this.AiCoreEndpoint$DownloadCallBackInternal$1$ar$this$1).setException(th);
                            return;
                        }
                        synchronized (((AiCoreBaseService) this.AiCoreEndpoint$DownloadCallBackInternal$1$ar$this$1).lock) {
                            ((AiCoreBaseService) this.AiCoreEndpoint$DownloadCallBackInternal$1$ar$this$1).inferenceServiceContext = null;
                        }
                        return;
                    }
                    LogUtils.d("AiCoreEndpointGemini", "Fail on AiFeature List", new Object[0]);
                    return;
                }
                LogUtils.w("AiCoreEndpointGemini", "Get AiFeature size fail: %s", th);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.google.common.util.concurrent.FutureCallback
            public final /* synthetic */ void onSuccess(Object obj) {
                int i;
                int i2 = this.switching_field;
                if (i2 != 0) {
                    if (i2 != 1) {
                        if (i2 != 2) {
                            ((AbstractFuture) this.AiCoreEndpoint$DownloadCallBackInternal$1$ar$this$1).set((OAuth2Credentials.OAuthValue) obj);
                            return;
                        } else {
                            return;
                        }
                    }
                    ImmutableList immutableList = (ImmutableList) obj;
                    int size = immutableList.size();
                    AiFeature aiFeature = null;
                    int i3 = 0;
                    while (true) {
                        i = 3;
                        if (i3 >= size) {
                            break;
                        }
                        AiFeature aiFeature2 = (AiFeature) immutableList.get(i3);
                        LogUtils.d("AiCoreEndpointGemini", "AiFeature : %s, type : %s, id: %d", aiFeature2.getModelName(), Integer.valueOf(aiFeature2.getType()), Integer.valueOf(aiFeature2.getId()));
                        if (aiFeature2.getType() == 19 && aiFeature2.getId() == 223) {
                            aiFeature = aiFeature2;
                        }
                        i3++;
                    }
                    if (aiFeature != null) {
                        LogUtils.d("AiCoreEndpointGemini", "Has Preferred Model : %s (%s)", aiFeature.getName(), aiFeature.getModelName());
                        AiCoreEndpoint aiCoreEndpoint = (AiCoreEndpoint) this.AiCoreEndpoint$DownloadCallBackInternal$1$ar$this$1;
                        aiCoreEndpoint.aiFeature = aiFeature;
                        AiFeature aiFeature3 = aiCoreEndpoint.aiFeature;
                        AiCoreClientImpl aiCoreClientImpl = (AiCoreClientImpl) aiCoreEndpoint.aiCoreClient;
                        aiCoreEndpoint.aiFeatureSize = AbstractTransformFuture.create(aiCoreClientImpl.getService(), new AiCoreBaseService$$ExternalSyntheticLambda16(aiFeature3, i), aiCoreClientImpl.workerExecutor);
                        AiCoreEndpoint aiCoreEndpoint2 = (AiCoreEndpoint) this.AiCoreEndpoint$DownloadCallBackInternal$1$ar$this$1;
                        aiCoreEndpoint2.hasAiCore = true;
                        aiCoreEndpoint2.perfs.edit().putBoolean("pref_gemini_has_aicore_cached_key", true).apply();
                        AiCoreEndpoint aiCoreEndpoint3 = (AiCoreEndpoint) this.AiCoreEndpoint$DownloadCallBackInternal$1$ar$this$1;
                        if (aiCoreEndpoint3.withService) {
                            if (aiCoreEndpoint3.hasRequestDownload) {
                                aiCoreEndpoint3.initializeService();
                            }
                            Object obj2 = this.AiCoreEndpoint$DownloadCallBackInternal$1$ar$this$1;
                            ((AiCoreEndpoint) obj2).updateAiFeatureStatus(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(obj2, 12));
                            return;
                        }
                        aiCoreEndpoint3.updateAiFeatureStatus(null);
                        return;
                    }
                    return;
                }
                ((DownloadCallBackInternal) this.AiCoreEndpoint$DownloadCallBackInternal$1$ar$this$1).sizeInBytes = ((Long) obj).longValue();
            }
        }

        public DownloadCallBackInternal() {
            ListenableFuture listenableFuture = AiCoreEndpoint.this.aiFeatureSize;
            if (listenableFuture != null) {
                ContextDataProvider.addCallback(listenableFuture, new AnonymousClass1(this, 0), DirectExecutor.INSTANCE);
            }
        }

        @Override // com.google.android.apps.aicore.client.api.DownloadCallback
        public final void onDownloadCompleted$ar$ds() {
            LogUtils.d("AiCoreEndpointGemini", "Feature download completed.", new Object[0]);
            AiCoreEndpoint aiCoreEndpoint = AiCoreEndpoint.this;
            aiCoreEndpoint.updateAiFeatureStatus(new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(aiCoreEndpoint, 13));
            AiCoreEndpoint aiCoreEndpoint2 = AiCoreEndpoint.this;
            if (aiCoreEndpoint2.hasRequestDownload) {
                Toast.makeText(aiCoreEndpoint2.context, R.string.message_aifeature_download_complete, 1).show();
            }
            AiCoreEndpoint.this.setHasRequestDownload(false);
            AiFeatureDownloadCallback aiFeatureDownloadCallback = AiCoreEndpoint.this.downloadCallback;
            if (aiFeatureDownloadCallback != null) {
                aiFeatureDownloadCallback.onDownloadCompleted();
            }
        }

        @Override // com.google.android.apps.aicore.client.api.DownloadCallback
        public final void onDownloadFailed$ar$ds() {
            LogUtils.d("AiCoreEndpointGemini", "Feature download failed.", new Object[0]);
            AiCoreEndpoint.this.updateAiFeatureStatus(null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.apps.aicore.client.api.DownloadCallback
        public final void onDownloadProgress$ar$ds(long j) {
            if (this.sizeInBytes <= 0 && AiCoreEndpoint.this.aiFeatureSize.isDone()) {
                LogUtils.d("AiCoreEndpointGemini", "Update total size.", new Object[0]);
                try {
                    this.sizeInBytes = ((Long) AiCoreEndpoint.this.aiFeatureSize.get()).longValue();
                } catch (InterruptedException e) {
                    LogUtils.d("AiCoreEndpointGemini", "Update total size fail: %s", e);
                } catch (ExecutionException e2) {
                    LogUtils.d("AiCoreEndpointGemini", "Update total size fail: %s", e2);
                }
            }
            LogUtils.d("AiCoreEndpointGemini", "Feature downloading...%d/%d", Long.valueOf(this.sizeInBytes), Long.valueOf(j));
            AiFeatureDownloadCallback aiFeatureDownloadCallback = AiCoreEndpoint.this.downloadCallback;
            if (aiFeatureDownloadCallback != null) {
                aiFeatureDownloadCallback.onDownloadProgress(j, this.sizeInBytes);
            }
        }

        @Override // com.google.android.apps.aicore.client.api.DownloadCallback
        public final void onDownloadStarted$ar$ds() {
            LogUtils.d("AiCoreEndpointGemini", "Feature download started.", new Object[0]);
            AiCoreEndpoint.this.updateAiFeatureStatus(null);
        }
    }

    public AiCoreEndpoint(Context context, boolean z) {
        ExecutorService executorService;
        Executor executor;
        boolean z2;
        AiCoreClientOptions.Builder builder = new AiCoreClientOptions.Builder(null);
        if (context != null) {
            builder.context = context;
            AiCoreClientOptions.MainThreadExecutor mainThreadExecutor = AiCoreClientOptions.MainThreadExecutor.INSTANCE;
            if (mainThreadExecutor != null) {
                builder.callbackExecutor = mainThreadExecutor;
                ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
                if (newCachedThreadPool != null) {
                    builder.workerExecutor = newCachedThreadPool;
                    builder.set$0 = (byte) 3;
                    Context context2 = builder.context;
                    if (context2 != null && (executorService = builder.workerExecutor) != null && (executor = builder.callbackExecutor) != null) {
                        AiCoreClientImpl aiCoreClientImpl = new AiCoreClientImpl(new AiCoreClientOptions(context2, executorService, executor));
                        this.status = 0;
                        this.aiCoreVersion = -1L;
                        this.aiCoreClient = aiCoreClientImpl;
                        this.withService = z;
                        Context context3 = aiCoreClientImpl.context;
                        this.context = context3;
                        this.handler = new Handler(Looper.getMainLooper());
                        this.downloadDialog = new AiFeatureDownloadDialog(context3, new Connectioneer$AspectConnection$$ExternalSyntheticLambda2(this, 11));
                        this.aiCoreUpdateDialog = new AiServiceUpdateDialog(context3, context3.getString(R.string.aicore_app_name), "com.google.android.aicore");
                        this.astreaUpdateDialog = new AiServiceUpdateDialog(context3, context3.getString(R.string.astrea_app_name), "com.google.android.as.oss");
                        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(context3);
                        this.perfs = sharedPreferences;
                        z2 = sharedPreferences.getBoolean(context3.getResources().getString(R.string.perf_aicore_feature_download_requested), false);
                        this.hasRequestDownload = z2;
                        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
                        AiCorePackageChangeReceiver aiCorePackageChangeReceiver = new AiCorePackageChangeReceiver();
                        this.aiCorePackageChangeReceiver = aiCorePackageChangeReceiver;
                        initializeAiFeature();
                        IntentFilter intentFilter = new IntentFilter();
                        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
                        intentFilter.addDataScheme("package");
                        intentFilter.addDataSchemeSpecificPart("com.google.android.aicore", 0);
                        context3.registerReceiver(aiCorePackageChangeReceiver, intentFilter);
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    if (builder.context == null) {
                        sb.append(" context");
                    }
                    if (builder.workerExecutor == null) {
                        sb.append(" workerExecutor");
                    }
                    if (builder.callbackExecutor == null) {
                        sb.append(" callbackExecutor");
                    }
                    if ((builder.set$0 & 1) == 0) {
                        sb.append(" bindImportantEnabled");
                    }
                    if ((builder.set$0 & 2) == 0) {
                        sb.append(" bindWaivePriorityEnabled");
                    }
                    throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
                }
                throw new NullPointerException("Null workerExecutor");
            }
            throw new NullPointerException("Null callbackExecutor");
        }
        throw new NullPointerException("Null context");
    }

    public final void aiCoreOptIn(int i) {
        if (i != 2) {
            if (i != 3) {
                return;
            }
            setHasRequestDownload(false);
            initializeService();
            return;
        }
        initializeService();
    }

    @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiEndpoint
    public final void cancelCommand() {
        cancelExistingRequestIfNeeded();
    }

    public final void cancelExistingRequestIfNeeded() {
        Pair pair = this.outstandingRequest;
        if (pair != null && !((ListenableFuture) pair.first).isDone()) {
            ((ListenableFuture) this.outstandingRequest.first).cancel(false);
            this.outstandingRequest = null;
        }
    }

    /* JADX WARN: Type inference failed for: r7v8, types: [java.util.List, java.lang.Object] */
    @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiEndpoint
    public final boolean createRequestGeminiCommand(String str, Bitmap bitmap, boolean z, GeminiActor.GeminiResponseListener geminiResponseListener) {
        int i = 0;
        LogUtils.v("AiCoreEndpointGemini", "createRequestGeminiCommand manual: %s", Boolean.valueOf(z));
        updateAiFeatureStatus(null);
        if (this.withService) {
            if (needAiCoreUpdate() && z) {
                LogUtils.d("AiCoreEndpointGemini", "Find AiCore stub.", new Object[0]);
                displayAiCoreUpdateDialog();
                geminiResponseListener.onError$ar$edu(7);
                return false;
            }
            if (needAstreaUpdate() && z) {
                LogUtils.d("AiCoreEndpointGemini", "Need Astrea update.", new Object[0]);
                displayAstreaUpdateDialog();
                geminiResponseListener.onError$ar$edu(7);
                return false;
            }
            if (hasAiCoreCached() && !this.hasAiCore) {
                if (this.listFeatures.isDone()) {
                    LogUtils.d("AiCoreEndpointGemini", "Retry initializing AiFeature.", new Object[0]);
                    initializeAiFeature();
                    int i2 = this.initializeAiFeatureRetryCount + 1;
                    this.initializeAiFeatureRetryCount = i2;
                    if (i2 >= 3) {
                        LogUtils.w("AiCoreEndpointGemini", "Retry for 3 times, clear the cache.", new Object[0]);
                        SpannableUtils$IdentifierSpan.remove(this.perfs, "pref_gemini_has_aicore_cached_key");
                        this.initializeAiFeatureRetryCount = 0;
                    }
                }
                LogUtils.w("AiCoreEndpointGemini", "hasAiCore not ready yet, or something went wrong when initialing the AiFeature.", new Object[0]);
                geminiResponseListener.onError$ar$edu(1);
                return false;
            }
            if (this.status == 1 && !this.hasRequestDownload && z) {
                displayAiFeatureDownloadDialog(null);
                geminiResponseListener.onError$ar$edu(7);
                return false;
            }
            if (isAiFeatureDownloading()) {
                LogUtils.d("AiCoreEndpointGemini", "Feature download request isn't finished.", new Object[0]);
                geminiResponseListener.onError$ar$edu(7);
                if (this.status == 2 && this.imageDescriptionService$ar$class_merging == null) {
                    LogUtils.d("AiCoreEndpointGemini", "Initialize the service due to download already started.", new Object[0]);
                    aiCoreOptIn(2);
                }
                return false;
            }
            if (this.imageDescriptionService$ar$class_merging == null) {
                LogUtils.d("AiCoreEndpointGemini", "Service is null.", new Object[0]);
                if (isAiFeatureAvailable()) {
                    LogUtils.d("AiCoreEndpointGemini", "The AiFeature is ready but the service is null.", new Object[0]);
                    aiCoreOptIn(3);
                }
                geminiResponseListener.onError$ar$edu(1);
                return false;
            }
            ListenableFuture listenableFuture = this.preparingService;
            if (listenableFuture != null && !listenableFuture.isDone()) {
                LogUtils.d("AiCoreEndpointGemini", "Service is preparing.", new Object[0]);
                geminiResponseListener.onError$ar$edu(1);
                return false;
            }
            if (isAiFeatureAvailable()) {
                ?? r7 = new OnDeviceTextDetectionLoadLogEvent((byte[]) null, (byte[]) null, (char[]) null).setImages$ar$class_merging$ar$class_merging(ImmutableList.of((Object) bitmap)).OnDeviceTextDetectionLoadLogEvent$ar$errorCode;
                if (r7 != 0) {
                    ImageDescriptionRequest imageDescriptionRequest = new ImageDescriptionRequest(r7);
                    AiCoreBaseService aiCoreBaseService = this.imageDescriptionService$ar$class_merging;
                    ListenableFuture create = AbstractTransformFuture.create(aiCoreBaseService.getOrCreateServiceContext(), new AiCoreBaseService$$ExternalSyntheticLambda1(aiCoreBaseService, imageDescriptionRequest, i), aiCoreBaseService.workerExecutor);
                    cancelExistingRequestIfNeeded();
                    this.outstandingRequest = new Pair(create, Boolean.valueOf(z));
                    ContextDataProvider.addCallback(create, new ArateaEndpoint.AnonymousClass1(this, geminiResponseListener, create, 1), DirectExecutor.INSTANCE);
                    return true;
                }
                throw new IllegalStateException("Missing required properties: images");
            }
            LogUtils.d("AiCoreEndpointGemini", "AiFeature is not available.", new Object[0]);
            geminiResponseListener.onError$ar$edu(1);
            return false;
        }
        throw new IllegalStateException("For requesting a command, please enable the service in AiCoreEndpoint.");
    }

    public final void displayAiCoreUpdateDialog() {
        this.handler.post(new VoiceActionMonitor$$ExternalSyntheticLambda0(this, 7));
    }

    public final void displayAiFeatureDownloadDialog(Consumer consumer) {
        if (consumer != null) {
            this.downloadDialog.extraButtonClickCallback = consumer;
        }
        this.handler.post(new VoiceActionMonitor$$ExternalSyntheticLambda0(this, 6));
    }

    public final void displayAstreaUpdateDialog() {
        this.handler.post(new VoiceActionMonitor$$ExternalSyntheticLambda0(this, 5));
    }

    public final boolean hasAiCore() {
        if (!GeminiConfig.enableOnDeviceGeminiImageCaptioning(this.context)) {
            return false;
        }
        if (needAiCoreUpdate()) {
            return true;
        }
        ListenableFuture listenableFuture = this.listFeatures;
        if (listenableFuture == null || !listenableFuture.isDone()) {
            LogUtils.w("AiCoreEndpointGemini", "listFeatures isn't finished.", new Object[0]);
        }
        if (hasAiCoreCached()) {
            return true;
        }
        return this.hasAiCore;
    }

    public final boolean hasAiCoreCached() {
        return this.perfs.getBoolean("pref_gemini_has_aicore_cached_key", false);
    }

    @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiEndpoint
    public final boolean hasPendingTransaction() {
        Pair pair = this.outstandingRequest;
        if (pair != null && !((ListenableFuture) pair.first).isDone() && ((Boolean) this.outstandingRequest.second).booleanValue()) {
            return true;
        }
        return false;
    }

    public final void initializeAiFeature() {
        long versionCodeCompat = SpannableUtils$IdentifierSpan.getVersionCodeCompat(this.context, "com.google.android.aicore");
        this.aiCoreVersion = versionCodeCompat;
        LogUtils.d("AiCoreEndpointGemini", "AiCore version: %d", Long.valueOf(versionCodeCompat));
        try {
            AiCoreClient aiCoreClient = this.aiCoreClient;
            ListenableFuture create = AbstractTransformFuture.create(((AiCoreClientImpl) aiCoreClient).getService(), new AccessibilityEventUtils$$ExternalSyntheticLambda0(6), ((AiCoreClientImpl) aiCoreClient).workerExecutor);
            this.listFeatures = create;
            ContextDataProvider.addCallback(create, new DownloadCallBackInternal.AnonymousClass1(this, 1), DirectExecutor.INSTANCE);
        } catch (Exception e) {
            LogUtils.e("AiCoreEndpointGemini", "AiCore is not supported/Exception:%s", e.toString());
        }
    }

    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object, com.google.android.apps.aicore.client.api.AiCoreClient] */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.google.android.apps.aicore.client.api.DownloadCallback, java.lang.Object] */
    public final void initializeService() {
        ?? r3;
        Object obj;
        if (this.imageDescriptionService$ar$class_merging != null) {
            LogUtils.d("AiCoreEndpointGemini", "Service already existed.", new Object[0]);
            return;
        }
        if (this.requestDownloadFuture == null) {
            this.requestDownloadFuture = this.aiCoreClient.requestDownloadableFeature(this.aiFeature, new DownloadCallBackInternal());
        }
        if (!this.withService) {
            LogUtils.d("AiCoreEndpointGemini", "Service shouldn't be enabled in this endpoint.", new Object[0]);
            return;
        }
        AiCoreClient aiCoreClient = this.aiCoreClient;
        AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent(null, null, null, null);
        aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats = aiCoreClient;
        aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey = new DownloadCallback() { // from class: com.google.android.apps.aicore.client.api.imagedescription.ImageDescriptionServiceOptions.1
            @Override // com.google.android.apps.aicore.client.api.DownloadCallback
            public final /* synthetic */ void onDownloadCompleted$ar$ds() {
            }

            @Override // com.google.android.apps.aicore.client.api.DownloadCallback
            public final /* synthetic */ void onDownloadFailed$ar$ds() {
            }

            @Override // com.google.android.apps.aicore.client.api.DownloadCallback
            public final /* synthetic */ void onDownloadStarted$ar$ds() {
            }

            @Override // com.google.android.apps.aicore.client.api.DownloadCallback
            public final /* synthetic */ void onDownloadProgress$ar$ds(long j) {
            }
        };
        AiFeature aiFeature = this.aiFeature;
        if (aiFeature != null) {
            aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount = aiFeature;
            ?? r0 = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats;
            if (r0 != 0 && (r3 = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey) != 0 && (obj = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount) != null) {
                ImageDescriptionServiceOptions imageDescriptionServiceOptions = new ImageDescriptionServiceOptions(r0, r3, (AiFeature) obj);
                AiCoreBaseService aiCoreBaseService = new AiCoreBaseService(imageDescriptionServiceOptions.aiCoreClient, imageDescriptionServiceOptions.feature, imageDescriptionServiceOptions.downloadCallback, null);
                this.imageDescriptionService$ar$class_merging = aiCoreBaseService;
                this.preparingService = AbstractTransformFuture.create(aiCoreBaseService.getOrCreateServiceContext(), new AiCoreBaseService$$ExternalSyntheticLambda16(aiCoreBaseService, 2), aiCoreBaseService.workerExecutor);
                return;
            }
            StringBuilder sb = new StringBuilder();
            if (aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats == null) {
                sb.append(" aiCoreClient");
            }
            if (aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey == null) {
                sb.append(" downloadCallback");
            }
            if (aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount == null) {
                sb.append(" feature");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        throw new NullPointerException("Null feature");
    }

    public final boolean isAiFeatureAvailable() {
        if (this.status == 3) {
            return true;
        }
        return false;
    }

    public final boolean isAiFeatureDownloading() {
        if (isAiFeatureAvailable()) {
            return false;
        }
        ListenableFuture listenableFuture = this.requestDownloadFuture;
        if ((listenableFuture == null || listenableFuture.isDone()) && this.status != 2) {
            return false;
        }
        return true;
    }

    public final boolean needAiCoreUpdate() {
        if (this.aiCoreVersion == -1) {
            this.aiCoreVersion = SpannableUtils$IdentifierSpan.getVersionCodeCompat(this.context, "com.google.android.aicore");
        }
        return new Range(new Cut.BelowValue(49749L), new Cut.AboveValue(50008L)).apply(Long.valueOf(this.aiCoreVersion));
    }

    public final boolean needAstreaUpdate() {
        long versionCodeCompat = SpannableUtils$IdentifierSpan.getVersionCodeCompat(this.context, "com.google.android.as.oss");
        if (versionCodeCompat >= 59459) {
            return false;
        }
        LogUtils.d("AiCoreEndpointGemini", "Current Astrea version %s. Needs to update.", Long.valueOf(versionCodeCompat));
        return true;
    }

    @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
    public final void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
        if (!TextUtils.isEmpty(str) && TextUtils.equals(str, this.context.getString(R.string.perf_aicore_feature_download_requested))) {
            this.hasRequestDownload = sharedPreferences.getBoolean(str, false);
        }
    }

    public final void onUnbind() {
        ((AiCoreClientImpl) this.aiCoreClient).cleanUpConnection();
        this.perfs.unregisterOnSharedPreferenceChangeListener(this);
        this.context.unregisterReceiver(this.aiCorePackageChangeReceiver);
    }

    public final void setHasRequestDownload(boolean z) {
        this.hasRequestDownload = z;
        if (z) {
            SpannableUtils$IdentifierSpan.putBooleanPref(this.perfs, ((AiCoreClientImpl) this.aiCoreClient).context.getResources(), R.string.perf_aicore_feature_download_requested, true);
        } else {
            SpannableUtils$IdentifierSpan.remove(this.perfs, ((AiCoreClientImpl) this.aiCoreClient).context.getString(R.string.perf_aicore_feature_download_requested));
        }
    }

    public final void updateAiFeatureStatus(Consumer consumer) {
        AiFeature aiFeature = this.aiFeature;
        if (aiFeature != null) {
            ContextDataProvider.addCallback(this.aiCoreClient.getFeatureStatus(aiFeature), new ImageDescriptionProcessor.AnonymousClass1(this, consumer, 1, null), DirectExecutor.INSTANCE);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AiFeatureDownloadDialog extends BaseDialog {
        Consumer extraButtonClickCallback;
        final Consumer onPositiveButtonClick;
        private final SharedPreferences perfs;

        public AiFeatureDownloadDialog(Context context, Consumer consumer) {
            super(context, R.string.dialog_title_on_device_ai_download_needed, null);
            setPositiveButtonStringRes(R.string.positive_button_gemini_download_dialog);
            this.onPositiveButtonClick = consumer;
            this.perfs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final View getCustomizedView() {
            ScrollView scrollView = (ScrollView) LayoutInflater.from(this.context).inflate(R.layout.confirm_download_dialog, (ViewGroup) null);
            ((TextView) scrollView.findViewById(R.id.confirm_download_dialog_subtitle)).setText(R.string.powered_by_gemini);
            TextView textView = (TextView) scrollView.findViewById(R.id.confirm_download_dialog_message);
            String string = this.context.getString(R.string.dialog_message_gen_ai_tos_link);
            String string2 = this.context.getString(R.string.dialog_message_on_device_ai_model_download, string);
            SpannableString spannableString = new SpannableString(string2);
            int indexOf = string2.indexOf(string);
            if (indexOf >= 0) {
                Context context = this.context;
                GeminiFunctionUtils.DescribeImageCandidate describeImageCandidate = GeminiFunctionUtils.confirmDownloadOrPerformImageCaptioning;
                spannableString.setSpan(new GeminiFunctionUtils.AnonymousClass8(context, this), indexOf, string.length() + indexOf, 0);
            }
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            textView.setText(spannableString);
            return scrollView;
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final String getMessageString() {
            return null;
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final void handleDialogClick(int i) {
            if (i != -1) {
                return;
            }
            this.onPositiveButtonClick.accept(null);
            TalkBackAnalyticsImpl.onGeminiAiCoreDialogAction(this.perfs, this.context, 2);
            Consumer consumer = this.extraButtonClickCallback;
            if (consumer != null) {
                consumer.accept(null);
            }
            Toast.makeText(this.context, R.string.message_aifeature_downloading, 1).show();
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final void handleDialogDismiss() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AiServiceUpdateDialog extends BaseDialog {
        private final String packageName;
        private final SharedPreferences perfs;
        private final String serviceName;

        public AiServiceUpdateDialog(Context context, String str, String str2) {
            super(context, R.string.ai_services_update_dialog_title, null);
            this.serviceName = str;
            this.packageName = str2;
            this.perfs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
            setTitle(context.getString(R.string.ai_services_update_dialog_title, str));
            setPositiveButtonStringRes(R.string.ai_services_update_dialog_button_text);
            setButtonEnabled(-2, true);
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final View getCustomizedView() {
            return null;
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final String getMessageString() {
            return this.context.getString(R.string.ai_services_update_dialog_message, this.serviceName);
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final void handleDialogClick(int i) {
            int i2;
            if (i != -1) {
                return;
            }
            SharedPreferences sharedPreferences = this.perfs;
            Context context = this.context;
            if (true != this.serviceName.equals(context.getString(R.string.aicore_app_name))) {
                i2 = 6;
            } else {
                i2 = 4;
            }
            TalkBackAnalyticsImpl.onGeminiAiCoreDialogAction(sharedPreferences, context, i2);
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=".concat(this.packageName)));
            intent.addFlags(268435456);
            this.context.startActivity(intent);
        }

        @Override // com.google.android.accessibility.talkback.dialog.BaseDialog
        public final void handleDialogDismiss() {
        }
    }
}
