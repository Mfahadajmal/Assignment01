package com.google.android.accessibility.talkback.actor.gemini;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.accessibility.talkback.actor.gemini.DaggerGrpcComponent;
import com.google.android.accessibility.talkback.actor.gemini.GeminiActor;
import com.google.android.accessibility.utils.AccessibilityEventUtils$$ExternalSyntheticLambda0;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.apps.aicore.client.api.AiCoreException;
import com.google.android.apps.aicore.client.api.internal.AiCoreClientImpl$$ExternalSyntheticLambda2;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.surveys.SurveyRequest;
import com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc;
import com.google.android.libraries.surveys.internal.network.provider.NetworkCaller;
import com.google.android.libraries.surveys.internal.utils.Stopwatch;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractCatchingFuture;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ExecutionList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.GwtFluentFutureCatchingSpecialization;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.logging.schema.OnDeviceExplicitContentLoadLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import com.google.protobuf.ByteString;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.search.mdi.aratea.proto.ArateaInputData;
import com.google.search.mdi.aratea.proto.ArateaServiceGrpc;
import com.google.search.mdi.aratea.proto.Backend;
import com.google.search.mdi.aratea.proto.FeatureName;
import com.google.search.mdi.aratea.proto.GenerateModelConfiguration;
import com.google.search.mdi.aratea.proto.GenerateRequest;
import com.google.search.mdi.aratea.proto.GenerateResponse;
import com.google.search.mdi.aratea.proto.GenerationSignalOverride;
import com.google.search.mdi.aratea.proto.Image;
import googledata.experiments.mobile.accessibility_suite.features.GeminiConfig;
import io.grpc.MethodDescriptor;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import io.grpc.stub.ClientCalls;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArateaEndpoint implements GeminiActor.GeminiEndpoint {
    private final ArateaServiceGrpc.ArateaServiceFutureStub arateaServiceFutureStub;
    private final Context context;
    private final String model;
    private ListenableFuture outstandingRequest;
    private final ScheduledExecutorService scheduledExecutorService;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.actor.gemini.ArateaEndpoint$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements FutureCallback {
        final /* synthetic */ Object ArateaEndpoint$1$ar$this$0;
        final /* synthetic */ Object ArateaEndpoint$1$ar$val$geminiResponseListener;
        final /* synthetic */ Object ArateaEndpoint$1$ar$val$proxyResponseListenableFuture;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(NetworkCallerGrpc networkCallerGrpc, GeneratedMessageLite generatedMessageLite, Stopwatch stopwatch, int i) {
            this.switching_field = i;
            this.ArateaEndpoint$1$ar$val$proxyResponseListenableFuture = generatedMessageLite;
            this.ArateaEndpoint$1$ar$val$geminiResponseListener = stopwatch;
            this.ArateaEndpoint$1$ar$this$0 = networkCallerGrpc;
        }

        /* JADX WARN: Type inference failed for: r0v10, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v21, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v3, types: [com.google.common.util.concurrent.ListenableFuture, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r10v10, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r10v15, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r10v2, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r10v4, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r10v5, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r10v8, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        @Override // com.google.common.util.concurrent.FutureCallback
        public final void onFailure(Throwable th) {
            int i;
            int i2 = this.switching_field;
            if (i2 == 0) {
                ((ArateaEndpoint) this.ArateaEndpoint$1$ar$this$0).outstandingRequest = null;
                if (this.ArateaEndpoint$1$ar$val$proxyResponseListenableFuture.isCancelled()) {
                    LogUtils.d("ArateaEndpoint", "Task's cancelled. Reason:%s", th.toString());
                    this.ArateaEndpoint$1$ar$val$geminiResponseListener.onError$ar$edu(8);
                    return;
                } else {
                    LogUtils.d("ArateaEndpoint", "onFailure : %s", th);
                    this.ArateaEndpoint$1$ar$val$geminiResponseListener.onResponse$ar$edu(3, null);
                    return;
                }
            }
            if (i2 == 1) {
                ((AiCoreEndpoint) this.ArateaEndpoint$1$ar$this$0).outstandingRequest = null;
                if (this.ArateaEndpoint$1$ar$val$proxyResponseListenableFuture.isCancelled()) {
                    LogUtils.d("AiCoreEndpointGemini", "Task's cancelled. Reason:%s", th.toString());
                    this.ArateaEndpoint$1$ar$val$geminiResponseListener.onError$ar$edu(8);
                    return;
                }
                LogUtils.d("AiCoreEndpointGemini", "response onFailure : %s", th);
                if ((th instanceof AiCoreException) && ((i = ((AiCoreException) th).errorCode) == 10 || i == 11 || i == 4 || i == 15)) {
                    this.ArateaEndpoint$1$ar$val$geminiResponseListener.onResponse$ar$edu(4, "");
                    return;
                } else {
                    this.ArateaEndpoint$1$ar$val$geminiResponseListener.onError$ar$edu(1);
                    return;
                }
            }
            if (i2 == 2) {
                ((ExecutionList.RunnableExecutorPair) this.ArateaEndpoint$1$ar$val$geminiResponseListener).ExecutionList$RunnableExecutorPair$ar$next = null;
                if (this.ArateaEndpoint$1$ar$val$proxyResponseListenableFuture.isCancelled()) {
                    LogUtils.d("GeminiRestRequestPerformer", "Task's cancelled. Reason:%s", th.toString());
                    Object obj = this.ArateaEndpoint$1$ar$this$0;
                    LogUtils.v("GeminiEndpoint", "Cancelled a pending task.", new Object[0]);
                    ((FloatingActionButton.ShadowDelegateImpl) obj).FloatingActionButton$ShadowDelegateImpl$ar$this$0.onError$ar$edu(8);
                    return;
                }
                LogUtils.d("GeminiRestRequestPerformer", "response onFailure : %s", th);
                ((FloatingActionButton.ShadowDelegateImpl) this.ArateaEndpoint$1$ar$this$0).onFailure(th.toString());
                return;
            }
            if (i2 != 3) {
                if (i2 != 4) {
                    if (i2 != 5) {
                        Log.e("NetworkCallerGrpc", "Failed to fetch survey.", th);
                        ((NetworkCaller) this.ArateaEndpoint$1$ar$this$0).runOnRequestFailedCallback(SurveyRequest.ErrorType.FAILED_TO_FETCH_SURVEY);
                        ((NetworkCallerGrpc) this.ArateaEndpoint$1$ar$this$0).shutdownChannel();
                        return;
                    } else {
                        Log.e("NetworkCallerGrpc", "Failed to fetch survey.", th);
                        ((NetworkCaller) this.ArateaEndpoint$1$ar$this$0).runOnRequestFailedCallback(SurveyRequest.ErrorType.FAILED_TO_FETCH_SURVEY);
                        ((NetworkCallerGrpc) this.ArateaEndpoint$1$ar$this$0).shutdownChannel();
                        return;
                    }
                }
                Log.e("NetworkCallerGrpc", "Failed to record event.", th);
                ((NetworkCallerGrpc) this.ArateaEndpoint$1$ar$this$0).shutdownChannel();
                return;
            }
            Log.e("NetworkCallerGrpc", "Failed to record event.", th);
            ((NetworkCallerGrpc) this.ArateaEndpoint$1$ar$this$0).shutdownChannel();
        }

        /* JADX WARN: Removed duplicated region for block: B:66:0x018a A[ADDED_TO_REGION] */
        /* JADX WARN: Type inference failed for: r0v13, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v14, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r0v6, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v24, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v29, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        /* JADX WARN: Type inference failed for: r2v7, types: [com.google.android.accessibility.talkback.actor.gemini.GeminiActor$GeminiResponseListener, java.lang.Object] */
        @Override // com.google.common.util.concurrent.FutureCallback
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final /* synthetic */ void onSuccess(java.lang.Object r18) {
            /*
                Method dump skipped, instructions count: 682
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.actor.gemini.ArateaEndpoint.AnonymousClass1.onSuccess(java.lang.Object):void");
        }

        public AnonymousClass1(ExecutionList.RunnableExecutorPair runnableExecutorPair, FloatingActionButton.ShadowDelegateImpl shadowDelegateImpl, ListenableFuture listenableFuture, int i) {
            this.switching_field = i;
            this.ArateaEndpoint$1$ar$this$0 = shadowDelegateImpl;
            this.ArateaEndpoint$1$ar$val$proxyResponseListenableFuture = listenableFuture;
            this.ArateaEndpoint$1$ar$val$geminiResponseListener = runnableExecutorPair;
        }

        public AnonymousClass1(Object obj, GeminiActor.GeminiResponseListener geminiResponseListener, ListenableFuture listenableFuture, int i) {
            this.switching_field = i;
            this.ArateaEndpoint$1$ar$val$geminiResponseListener = geminiResponseListener;
            this.ArateaEndpoint$1$ar$val$proxyResponseListenableFuture = listenableFuture;
            this.ArateaEndpoint$1$ar$this$0 = obj;
        }
    }

    public ArateaEndpoint(Context context, Application application) {
        this.context = context;
        OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = new OnDeviceTextDetectionLoadLogEvent();
        onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = new ApplicationModule(application);
        OnDeviceExplicitContentLoadLogEvent.checkBuilderRequirement(onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode, ApplicationModule.class);
        this.arateaServiceFutureStub = (ArateaServiceGrpc.ArateaServiceFutureStub) new DaggerGrpcComponent.GrpcComponentImpl((ApplicationModule) onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode).provideArateaServiceFutureStubProvider.get();
        this.scheduledExecutorService = ContextDataProvider.listeningDecorator(Executors.newSingleThreadScheduledExecutor());
        this.model = GeminiConfig.geminiModel(context);
    }

    @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiEndpoint
    public final void cancelCommand() {
        LogUtils.d("ArateaEndpoint", "cancelCommand", new Object[0]);
        ListenableFuture listenableFuture = this.outstandingRequest;
        if (listenableFuture != null && !listenableFuture.isDone()) {
            this.outstandingRequest.cancel(false);
            this.outstandingRequest = null;
        }
    }

    @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiEndpoint
    public final boolean createRequestGeminiCommand(String str, Bitmap bitmap, boolean z, GeminiActor.GeminiResponseListener geminiResponseListener) {
        LogUtils.v("ArateaEndpoint", "createRequestGeminiCommand - aratea", new Object[0]);
        if (!SpannableUtils$IdentifierSpan.isNetworkConnected(this.context)) {
            LogUtils.d("ArateaEndpoint", "Internet is not connected", new Object[0]);
            geminiResponseListener.onError$ar$edu(3);
        } else if (bitmap == null) {
            LogUtils.d("ArateaEndpoint", "screenshot is not provided.", new Object[0]);
            geminiResponseListener.onError$ar$edu(4);
        } else if (TextUtils.isEmpty(str)) {
            LogUtils.d("ArateaEndpoint", "command part is empty.", new Object[0]);
            geminiResponseListener.onError$ar$edu(5);
        } else {
            byte[] encodeImageToByteArray = SpannableUtils$NonCopyableTextSpan.encodeImageToByteArray(bitmap);
            if (encodeImageToByteArray == null) {
                geminiResponseListener.onError$ar$edu(6);
                return false;
            }
            int i = 1;
            LogUtils.v("ArateaEndpoint", "getArateaGeminiRequest %s", this.model);
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) GenerateRequest.DEFAULT_INSTANCE.createBuilder();
            int i2 = FeatureName.TALKBACK_IMAGE_CAPTION$ar$edu;
            builder.copyOnWrite();
            GenerateRequest generateRequest = (GenerateRequest) builder.instance;
            int i3 = i2 - 1;
            if (i2 != 0) {
                generateRequest.featureName_ = i3;
                generateRequest.bitField0_ |= 1;
                SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) GenerateModelConfiguration.DEFAULT_INSTANCE.createBuilder();
                SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) GenerationSignalOverride.DEFAULT_INSTANCE.createBuilder();
                int i4 = Backend.VERTEX_AI$ar$edu;
                builder3.copyOnWrite();
                GenerationSignalOverride generationSignalOverride = (GenerationSignalOverride) builder3.instance;
                int i5 = i4 - 1;
                if (i4 != 0) {
                    generationSignalOverride.backend_ = i5;
                    generationSignalOverride.bitField0_ |= 1;
                    String str2 = this.model;
                    builder3.copyOnWrite();
                    GenerationSignalOverride generationSignalOverride2 = (GenerationSignalOverride) builder3.instance;
                    str2.getClass();
                    generationSignalOverride2.bitField0_ |= 2;
                    generationSignalOverride2.signalName_ = str2;
                    builder2.copyOnWrite();
                    GenerateModelConfiguration generateModelConfiguration = (GenerateModelConfiguration) builder2.instance;
                    GenerationSignalOverride generationSignalOverride3 = (GenerationSignalOverride) builder3.build();
                    generationSignalOverride3.getClass();
                    generateModelConfiguration.generationSignalOverride_ = generationSignalOverride3;
                    generateModelConfiguration.bitField0_ |= 8;
                    builder.copyOnWrite();
                    GenerateRequest generateRequest2 = (GenerateRequest) builder.instance;
                    GenerateModelConfiguration generateModelConfiguration2 = (GenerateModelConfiguration) builder2.build();
                    generateModelConfiguration2.getClass();
                    generateRequest2.modelConfig_ = generateModelConfiguration2;
                    generateRequest2.bitField0_ = 4 | generateRequest2.bitField0_;
                    SystemHealthProto$PackedHistogram.Builder builder4 = (SystemHealthProto$PackedHistogram.Builder) ArateaInputData.DEFAULT_INSTANCE.createBuilder();
                    SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) Image.DEFAULT_INSTANCE.createBuilder();
                    ByteString copyFrom = ByteString.copyFrom(encodeImageToByteArray);
                    builder5.copyOnWrite();
                    Image image = (Image) builder5.instance;
                    image.bitField0_ |= 1;
                    image.serializedBytes_ = copyFrom;
                    builder4.copyOnWrite();
                    ArateaInputData arateaInputData = (ArateaInputData) builder4.instance;
                    Image image2 = (Image) builder5.build();
                    image2.getClass();
                    arateaInputData.inputData_ = image2;
                    arateaInputData.inputDataCase_ = 2;
                    builder.addInputData$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging(builder4);
                    SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) ArateaInputData.DEFAULT_INSTANCE.createBuilder();
                    String nullToEmpty = ContextDataProvider.nullToEmpty(str);
                    builder6.copyOnWrite();
                    ArateaInputData arateaInputData2 = (ArateaInputData) builder6.instance;
                    arateaInputData2.inputDataCase_ = 1;
                    arateaInputData2.inputData_ = nullToEmpty;
                    builder.addInputData$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging(builder6);
                    GenerateRequest generateRequest3 = (GenerateRequest) builder.build();
                    ArateaServiceGrpc.ArateaServiceFutureStub arateaServiceFutureStub = this.arateaServiceFutureStub;
                    MethodDescriptor methodDescriptor = ArateaServiceGrpc.getGenerateMethod;
                    if (methodDescriptor == null) {
                        synchronized (ArateaServiceGrpc.class) {
                            methodDescriptor = ArateaServiceGrpc.getGenerateMethod;
                            if (methodDescriptor == null) {
                                MethodDescriptor.Builder newBuilder = MethodDescriptor.newBuilder();
                                newBuilder.type = MethodDescriptor.MethodType.UNARY;
                                newBuilder.fullMethodName = MethodDescriptor.generateFullMethodName("mdi.aratea.ArateaService", "Generate");
                                newBuilder.setSampledToLocalTracing$ar$ds();
                                GenerateRequest generateRequest4 = GenerateRequest.DEFAULT_INSTANCE;
                                ExtensionRegistryLite extensionRegistryLite = ProtoLiteUtils.globalRegistry;
                                newBuilder.requestMarshaller = new ProtoLiteUtils.MessageMarshaller(generateRequest4);
                                newBuilder.responseMarshaller = new ProtoLiteUtils.MessageMarshaller(GenerateResponse.DEFAULT_INSTANCE);
                                methodDescriptor = newBuilder.build();
                                ArateaServiceGrpc.getGenerateMethod = methodDescriptor;
                            }
                        }
                    }
                    ListenableFuture create = AbstractCatchingFuture.create(AbstractTransformFuture.create(GwtFluentFutureCatchingSpecialization.from$ar$class_merging(ContextDataProvider.withTimeout(ClientCalls.futureUnaryCall(arateaServiceFutureStub.channel.newCall(methodDescriptor, arateaServiceFutureStub.callOptions), generateRequest3), 12000L, TimeUnit.MILLISECONDS, this.scheduledExecutorService)), new AiCoreClientImpl$$ExternalSyntheticLambda2(this, 1), this.scheduledExecutorService), Throwable.class, new AccessibilityEventUtils$$ExternalSyntheticLambda0(i), this.scheduledExecutorService);
                    ListenableFuture listenableFuture = this.outstandingRequest;
                    if (listenableFuture != null && !listenableFuture.isDone()) {
                        this.outstandingRequest.cancel(false);
                    }
                    this.outstandingRequest = create;
                    if (create == null) {
                        geminiResponseListener.onResponse$ar$edu(3, null);
                        return false;
                    }
                    ContextDataProvider.addCallback(create, new AnonymousClass1(this, geminiResponseListener, create, 0), this.scheduledExecutorService);
                    return true;
                }
                throw null;
            }
            throw null;
        }
        return false;
    }

    @Override // com.google.android.accessibility.talkback.actor.gemini.GeminiActor.GeminiEndpoint
    public final boolean hasPendingTransaction() {
        ListenableFuture listenableFuture = this.outstandingRequest;
        if (listenableFuture != null && !listenableFuture.isDone()) {
            return true;
        }
        return false;
    }
}
