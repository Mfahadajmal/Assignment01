package com.google.android.accessibility.talkback.imagedescription;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.accessibility.talkback.actor.gemini.AiCoreEndpoint;
import com.google.android.accessibility.talkback.analytics.TalkBackAnalytics;
import com.google.android.accessibility.talkback.imagecaption.CaptionRequest;
import com.google.android.accessibility.talkback.imagecaption.ImageDescriptionRequest;
import com.google.android.accessibility.talkback.imagecaption.Request;
import com.google.android.accessibility.utils.caption.ImageCaptionUtils$CaptionType;
import com.google.android.accessibility.utils.caption.Result;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.nlp.garcon.ondevice.pipeline.Caption;
import com.google.android.libraries.nlp.garcon.ondevice.pipeline.ImageCaptioningPipelineOutput;
import com.google.android.libraries.surveys.internal.config.SurveyConfigProvider;
import com.google.android.libraries.surveys.internal.network.grpc.NetworkCallerGrpc;
import com.google.android.libraries.surveys.internal.network.provider.NetworkCaller;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.libraries.vision.visionkit.pipeline.PipelineConfig;
import com.google.android.libraries.vision.visionkit.pipeline.Results;
import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import com.google.android.libraries.vision.visionkit.pipeline.Subgraph;
import com.google.android.libraries.vision.visionkit.pipeline.alt.Pipeline;
import com.google.common.base.Throwables;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.FutureCallback;
import com.google.drishti.proto.GraphTemplateProto$TemplateArgument;
import com.google.drishti.proto.GraphTemplateProto$TemplateDict;
import com.google.frameworks.client.data.android.impl.TransportChannel;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.search.mdi.aratea.proto.FeatureName;
import google.internal.feedback.v1.Service$GetSurveyStartupConfigResponse;
import google.internal.feedback.v1.Survey$SurveyExperimentFlags;
import googledata.experiments.mobile.accessibility_suite.features.ImageCaption;
import io.grpc.CallCredentials$RequestInfo;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.Status;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImageDescriptionProcessor {
    public static final boolean supportImageDescription = true;
    public final TalkBackAnalytics analytics;
    public final Context context;
    public final Pipeline pipeline;
    public final Object resultListenerLock = new Object();
    public final ScheduledExecutorService scheduler;

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.talkback.imagedescription.ImageDescriptionProcessor$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements FutureCallback {
        final /* synthetic */ Object ImageDescriptionProcessor$1$ar$this$0;
        final /* synthetic */ Object ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0;
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(Object obj, Object obj2, int i) {
            this.switching_field = i;
            this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0 = obj2;
            this.ImageDescriptionProcessor$1$ar$this$0 = obj;
        }

        @Override // com.google.common.util.concurrent.FutureCallback
        public final void onFailure(Throwable th) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        ((TransportChannel.EnqueueingClientCall) this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0).clientCallFutureFailed = true;
                        ((OnDeviceFaceMeshCreateLogEvent) this.ImageDescriptionProcessor$1$ar$this$0).onClose(Status.fromThrowable(th), new Metadata());
                        return;
                    } else {
                        Log.e("NetworkCallerGrpc", "Failed to get survey startup config.", th);
                        SurveyConfigProvider.instance.feedback1pEnabled = false;
                        ((NetworkCallerGrpc) this.ImageDescriptionProcessor$1$ar$this$0).shutdownChannel();
                        ((NetworkCaller) this.ImageDescriptionProcessor$1$ar$this$0).handleGetSurveyStartupConfigResponse$ar$class_merging$ar$class_merging(null, (CallCredentials$RequestInfo) this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0);
                        return;
                    }
                }
                LogUtils.d("AiCoreEndpointGemini", "Fail to get AiFeatureStatus : %s", th);
                ((AiCoreEndpoint) this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0).status = 0;
                return;
            }
            LogUtils.w("ImageDescriptionProcessor", "onFailure=".concat(String.valueOf(Throwables.getStackTraceAsString(th))), new Object[0]);
            synchronized (((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).resultListenerLock) {
                ((ImageDescriptionRequest) this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0).onFailure(FeatureName.STYLUS_IMAGE_CAPTION$ar$edu);
            }
            ((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).stop();
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v6, types: [java.util.function.Consumer, java.lang.Object] */
        @Override // com.google.common.util.concurrent.FutureCallback
        public final /* synthetic */ void onSuccess(Object obj) {
            ImageDescriptionInfo imageDescriptionInfo;
            String str;
            ImmutableList immutableList;
            StringBuilder sb;
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        ClientCall clientCall = (ClientCall) obj;
                        try {
                            Object obj2 = this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0;
                            ((TransportChannel.EnqueueingClientCall) obj2).delegate = clientCall;
                            Iterator it = ((TransportChannel.EnqueueingClientCall) obj2).pending.iterator();
                            while (it.hasNext()) {
                                ((Runnable) it.next()).run();
                            }
                            return;
                        } catch (Throwable th) {
                            onFailure(th);
                            return;
                        }
                    }
                    Service$GetSurveyStartupConfigResponse service$GetSurveyStartupConfigResponse = (Service$GetSurveyStartupConfigResponse) obj;
                    SurveyConfigProvider surveyConfigProvider = SurveyConfigProvider.instance;
                    Survey$SurveyExperimentFlags survey$SurveyExperimentFlags = service$GetSurveyStartupConfigResponse.surveyExperimentFlags_;
                    if (survey$SurveyExperimentFlags == null) {
                        survey$SurveyExperimentFlags = Survey$SurveyExperimentFlags.DEFAULT_INSTANCE;
                    }
                    surveyConfigProvider.feedback1pEnabled = survey$SurveyExperimentFlags.sendRequestsToFeedbackOneplatform_;
                    ((NetworkCallerGrpc) this.ImageDescriptionProcessor$1$ar$this$0).shutdownChannel();
                    ((NetworkCaller) this.ImageDescriptionProcessor$1$ar$this$0).handleGetSurveyStartupConfigResponse$ar$class_merging$ar$class_merging(service$GetSurveyStartupConfigResponse, (CallCredentials$RequestInfo) this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0);
                    return;
                }
                Integer num = (Integer) obj;
                LogUtils.d("AiCoreEndpointGemini", "Update AiFeatureStatus : %d", num);
                ((AiCoreEndpoint) this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0).status = num.intValue();
                ?? r0 = this.ImageDescriptionProcessor$1$ar$this$0;
                if (r0 != 0) {
                    r0.accept(num);
                    return;
                }
                return;
            }
            Results results = (Results) obj;
            synchronized (((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).resultListenerLock) {
                String str2 = null;
                if ((results.bitField0_ & 536870912) != 0) {
                    ImageCaptioningPipelineOutput imageCaptioningPipelineOutput = results.imageCaptionOutput_;
                    if (imageCaptioningPipelineOutput == null) {
                        imageCaptioningPipelineOutput = ImageCaptioningPipelineOutput.DEFAULT_INSTANCE;
                    }
                    if (imageCaptioningPipelineOutput.captions_.size() > 0) {
                        Caption caption = (Caption) imageCaptioningPipelineOutput.captions_.get(0);
                        if (caption.captionQualityScore_ >= ImageCaption.getImageDescriptionHighQualityThreshold(((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).context)) {
                            ((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).analytics.onImageCaptionEvent(32);
                        } else if (caption.captionQualityScore_ >= ImageCaption.getImageDescriptionLowQualityThreshold(((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).context)) {
                            ((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).analytics.onImageCaptionEvent(33);
                        } else {
                            ((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).analytics.onImageCaptionEvent(34);
                        }
                        imageDescriptionInfo = new ImageDescriptionInfo(caption.captionQualityScore_, caption.captionText_, ImmutableList.copyOf((Collection) caption.labels_));
                        if (imageDescriptionInfo != null && !TextUtils.isEmpty(imageDescriptionInfo.captionText)) {
                            Object obj3 = this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0;
                            ((Request) obj3).stopTimeoutRunnable();
                            ImageCaptionUtils$CaptionType imageCaptionUtils$CaptionType = ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION;
                            Context context = ((ImageDescriptionRequest) obj3).context;
                            str = imageDescriptionInfo.captionText;
                            if (!TextUtils.isEmpty(str) && imageDescriptionInfo.captionQualityScore < ImageCaption.getImageDescriptionLowQualityThreshold(context)) {
                                immutableList = imageDescriptionInfo.labels;
                                if (immutableList != null && !immutableList.isEmpty()) {
                                    int size = immutableList.size();
                                    sb = new StringBuilder((String) immutableList.get(0));
                                    for (int i2 = 1; i2 < 5 && i2 < size; i2++) {
                                        sb.append(", ");
                                        sb.append((String) immutableList.get(i2));
                                    }
                                    str2 = sb.toString();
                                }
                                ((CaptionRequest) obj3).onCaptionFinish(new Result(imageCaptionUtils$CaptionType, str2, imageDescriptionInfo.captionQualityScore));
                                LogUtils.d("ImageDescriptionRequest", "onSuccess=".concat(imageDescriptionInfo.toString()), new Object[0]);
                                ((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).stop();
                                return;
                            }
                            str2 = str;
                            ((CaptionRequest) obj3).onCaptionFinish(new Result(imageCaptionUtils$CaptionType, str2, imageDescriptionInfo.captionQualityScore));
                            LogUtils.d("ImageDescriptionRequest", "onSuccess=".concat(imageDescriptionInfo.toString()), new Object[0]);
                            ((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).stop();
                            return;
                        }
                        ((ImageDescriptionRequest) this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0).onFailure(400);
                    }
                }
                imageDescriptionInfo = null;
                if (imageDescriptionInfo != null) {
                    Object obj32 = this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0;
                    ((Request) obj32).stopTimeoutRunnable();
                    ImageCaptionUtils$CaptionType imageCaptionUtils$CaptionType2 = ImageCaptionUtils$CaptionType.IMAGE_DESCRIPTION;
                    Context context2 = ((ImageDescriptionRequest) obj32).context;
                    str = imageDescriptionInfo.captionText;
                    if (!TextUtils.isEmpty(str)) {
                        immutableList = imageDescriptionInfo.labels;
                        if (immutableList != null) {
                            int size2 = immutableList.size();
                            sb = new StringBuilder((String) immutableList.get(0));
                            while (i2 < 5) {
                                sb.append(", ");
                                sb.append((String) immutableList.get(i2));
                            }
                            str2 = sb.toString();
                        }
                        ((CaptionRequest) obj32).onCaptionFinish(new Result(imageCaptionUtils$CaptionType2, str2, imageDescriptionInfo.captionQualityScore));
                        LogUtils.d("ImageDescriptionRequest", "onSuccess=".concat(imageDescriptionInfo.toString()), new Object[0]);
                        ((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).stop();
                        return;
                    }
                    str2 = str;
                    ((CaptionRequest) obj32).onCaptionFinish(new Result(imageCaptionUtils$CaptionType2, str2, imageDescriptionInfo.captionQualityScore));
                    LogUtils.d("ImageDescriptionRequest", "onSuccess=".concat(imageDescriptionInfo.toString()), new Object[0]);
                    ((ImageDescriptionProcessor) this.ImageDescriptionProcessor$1$ar$this$0).stop();
                    return;
                }
                ((ImageDescriptionRequest) this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0).onFailure(400);
            }
        }

        public AnonymousClass1(Object obj, Object obj2, int i, byte[] bArr) {
            this.switching_field = i;
            this.ImageDescriptionProcessor$1$ar$this$0 = obj2;
            this.ImageDescriptionProcessor$1$ar$val$listener$ar$class_merging$d334d7fb_0 = obj;
        }
    }

    public ImageDescriptionProcessor(Context context, TalkBackAnalytics talkBackAnalytics) {
        Pipeline pipeline;
        this.context = context;
        this.analytics = talkBackAnalytics;
        if (getGarconConfig() == null) {
            pipeline = null;
        } else {
            pipeline = new Pipeline(getGarconConfig(), "pipeline", Pipeline.getGeneratedOrEmptyRegistry());
        }
        this.pipeline = pipeline;
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final PipelineConfig getGarconConfig() {
        try {
            String str = FileUtils.copyAssetSubFolderSafe$ar$edu(this.context, "captioning_garcon_models", 1) + "/";
            SystemHealthProto$PackedHistogram.Builder builder = (SystemHealthProto$PackedHistogram.Builder) GraphTemplateProto$TemplateDict.DEFAULT_INSTANCE.createBuilder();
            SystemHealthProto$PackedHistogram.Builder builder2 = (SystemHealthProto$PackedHistogram.Builder) GraphTemplateProto$TemplateDict.Parameter.DEFAULT_INSTANCE.createBuilder();
            builder2.copyOnWrite();
            GraphTemplateProto$TemplateDict.Parameter parameter = (GraphTemplateProto$TemplateDict.Parameter) builder2.instance;
            parameter.bitField0_ |= 1;
            parameter.key_ = "base_directory";
            SystemHealthProto$PackedHistogram.Builder builder3 = (SystemHealthProto$PackedHistogram.Builder) GraphTemplateProto$TemplateArgument.DEFAULT_INSTANCE.createBuilder();
            String valueOf = String.valueOf(str);
            builder3.copyOnWrite();
            GraphTemplateProto$TemplateArgument graphTemplateProto$TemplateArgument = (GraphTemplateProto$TemplateArgument) builder3.instance;
            graphTemplateProto$TemplateArgument.paramValueCase_ = 1;
            graphTemplateProto$TemplateArgument.paramValue_ = valueOf.concat("/");
            builder2.copyOnWrite();
            GraphTemplateProto$TemplateDict.Parameter parameter2 = (GraphTemplateProto$TemplateDict.Parameter) builder2.instance;
            GraphTemplateProto$TemplateArgument graphTemplateProto$TemplateArgument2 = (GraphTemplateProto$TemplateArgument) builder3.build();
            graphTemplateProto$TemplateArgument2.getClass();
            parameter2.value_ = graphTemplateProto$TemplateArgument2;
            parameter2.bitField0_ |= 2;
            builder.copyOnWrite();
            GraphTemplateProto$TemplateDict graphTemplateProto$TemplateDict = (GraphTemplateProto$TemplateDict) builder.instance;
            GraphTemplateProto$TemplateDict.Parameter parameter3 = (GraphTemplateProto$TemplateDict.Parameter) builder2.build();
            parameter3.getClass();
            Internal.ProtobufList protobufList = graphTemplateProto$TemplateDict.arg_;
            if (!protobufList.isModifiable()) {
                graphTemplateProto$TemplateDict.arg_ = GeneratedMessageLite.mutableCopy(protobufList);
            }
            graphTemplateProto$TemplateDict.arg_.add(parameter3);
            GraphTemplateProto$TemplateDict graphTemplateProto$TemplateDict2 = (GraphTemplateProto$TemplateDict) builder.build();
            SchedulerOptions.Builder builder4 = (SchedulerOptions.Builder) SchedulerOptions.DEFAULT_INSTANCE.createBuilder();
            SystemHealthProto$PackedHistogram.Builder builder5 = (SystemHealthProto$PackedHistogram.Builder) Subgraph.DEFAULT_INSTANCE.createBuilder();
            builder5.addInputStreamName$ar$ds("selected_frame");
            builder5.addInputStreamName$ar$ds("image_metadata");
            builder5.copyOnWrite();
            Subgraph subgraph = (Subgraph) builder5.instance;
            subgraph.bitField0_ |= 1;
            subgraph.subgraphName_ = "GarconImageCaptionSubgraph";
            builder5.copyOnWrite();
            Subgraph subgraph2 = (Subgraph) builder5.instance;
            graphTemplateProto$TemplateDict2.getClass();
            subgraph2.templateDict_ = graphTemplateProto$TemplateDict2;
            subgraph2.bitField0_ |= 4;
            builder4.addCustomSubgraph$ar$ds$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(builder5);
            SchedulerOptions schedulerOptions = (SchedulerOptions) builder4.build();
            SystemHealthProto$PackedHistogram.Builder builder6 = (SystemHealthProto$PackedHistogram.Builder) PipelineConfig.DEFAULT_INSTANCE.createBuilder();
            builder6.copyOnWrite();
            PipelineConfig pipelineConfig = (PipelineConfig) builder6.instance;
            schedulerOptions.getClass();
            pipelineConfig.schedulerOptions_ = schedulerOptions;
            pipelineConfig.bitField0_ |= 1;
            return (PipelineConfig) builder6.build();
        } catch (IOException unused) {
            LogUtils.e("ImageDescriptionProcessor", "Fail to initiate the Garcon pipeline because model folder can not be found!", new Object[0]);
            return null;
        }
    }

    public final void stop() {
        Pipeline pipeline = this.pipeline;
        if (pipeline != null) {
            try {
                long j = pipeline.nativeContext;
                if (j != 0) {
                    if (pipeline.nativePipeline.stop(j)) {
                        return;
                    } else {
                        throw new IllegalStateException("Pipeline did not stop successfully.");
                    }
                }
                throw new IllegalStateException("Pipeline has been closed or was not initialized");
            } catch (IllegalStateException unused) {
                LogUtils.e("ImageDescriptionProcessor", "Fail to stop the AmbientKit pipeline.", new Object[0]);
            }
        }
    }
}
