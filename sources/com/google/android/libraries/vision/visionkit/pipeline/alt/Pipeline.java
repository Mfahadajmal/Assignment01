package com.google.android.libraries.vision.visionkit.pipeline.alt;

import com.google.android.libraries.vision.visionkit.pipeline.PipelineBufferCallback;
import com.google.android.libraries.vision.visionkit.pipeline.PipelineConfig;
import com.google.android.libraries.vision.visionkit.pipeline.PipelineIsolationCallback;
import com.google.android.libraries.vision.visionkit.pipeline.PipelineResultsCallback;
import com.google.android.libraries.vision.visionkit.pipeline.Results;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Protobuf;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Pipeline implements PipelineBufferCallback, PipelineResultsCallback, PipelineIsolationCallback {
    public final ExtensionRegistryLite extensionRegistry;
    public final OrderVerifyingClientCall.State frameBuffer$ar$class_merging;
    public long nativeContext;
    private long nativeFrameBufferReleaseCallback;
    private long nativeFrameManager;
    private long nativeIsolationCallback;
    public final NativePipeline nativePipeline;
    private long nativeResultsCallback;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public Pipeline() {
        /*
            r3 = this;
            com.google.android.libraries.vision.visionkit.pipeline.PipelineConfig r0 = com.google.android.libraries.vision.visionkit.pipeline.PipelineConfig.DEFAULT_INSTANCE
            com.google.protobuf.GeneratedMessageLite$Builder r0 = r0.createBuilder()
            logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram$Builder r0 = (logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram.Builder) r0
            r0.copyOnWrite()
            MessageType extends com.google.protobuf.GeneratedMessageLite<MessageType, BuilderType> r1 = r0.instance
            com.google.android.libraries.vision.visionkit.pipeline.PipelineConfig r1 = (com.google.android.libraries.vision.visionkit.pipeline.PipelineConfig) r1
            int r2 = r1.bitField0_
            r2 = r2 | 2
            r1.bitField0_ = r2
            r2 = 1
            r1.enableMobileiqTracing_ = r2
            com.google.protobuf.GeneratedMessageLite r0 = r0.build()
            com.google.android.libraries.vision.visionkit.pipeline.PipelineConfig r0 = (com.google.android.libraries.vision.visionkit.pipeline.PipelineConfig) r0
            com.google.android.libraries.vision.visionkit.pipeline.Configuration r1 = new com.google.android.libraries.vision.visionkit.pipeline.Configuration
            r1.<init>(r0)
            com.google.android.libraries.vision.visionkit.pipeline.PipelineConfig r0 = r1.pipelineConfig
            java.lang.String r1 = "demopipeline"
            com.google.protobuf.ExtensionRegistryLite r2 = getGeneratedOrEmptyRegistry()
            r3.<init>(r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.vision.visionkit.pipeline.alt.Pipeline.<init>():void");
    }

    public static ExtensionRegistryLite getGeneratedOrEmptyRegistry() {
        ExtensionRegistryLite generatedRegistry = ExtensionRegistryLite.getGeneratedRegistry();
        if (generatedRegistry != null) {
            return generatedRegistry;
        }
        Protobuf protobuf = Protobuf.INSTANCE;
        return ExtensionRegistryLite.EMPTY_REGISTRY_LITE;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.PipelineIsolationCallback
    public final void closeFileDescriptor$ar$ds() {
        throw null;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.PipelineBufferCallback
    public final void onReleaseAtTimestampUs(long j) {
        throw null;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.PipelineResultsCallback
    public final void onResult(Results results) {
        throw null;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.PipelineIsolationCallback
    public final void openFileDescriptor$ar$ds() {
        throw null;
    }

    public Pipeline(PipelineConfig pipelineConfig, String str, ExtensionRegistryLite extensionRegistryLite) {
        if (pipelineConfig.soLibraryConfigurationCase_ == 5 && ((Boolean) pipelineConfig.soLibraryConfiguration_).booleanValue()) {
            this.nativePipeline = new NativePipelineStub();
        } else if (pipelineConfig.soLibraryConfigurationCase_ == 6 && ((Boolean) pipelineConfig.soLibraryConfiguration_).booleanValue()) {
            this.nativePipeline = new NativePipelineImpl(this, this, this, extensionRegistryLite);
        } else {
            this.nativePipeline = new NativePipelineImpl(str, this, this, this, extensionRegistryLite);
        }
        if ((pipelineConfig.bitField0_ & 32) != 0) {
            this.frameBuffer$ar$class_merging = new OrderVerifyingClientCall.State(pipelineConfig.experimentalMaxInflightFrames_);
        } else {
            this.frameBuffer$ar$class_merging = new OrderVerifyingClientCall.State(10);
        }
        this.extensionRegistry = extensionRegistryLite;
        long initializeFrameManager = this.nativePipeline.initializeFrameManager();
        this.nativeFrameManager = initializeFrameManager;
        this.nativeFrameBufferReleaseCallback = this.nativePipeline.initializeFrameBufferReleaseCallback(initializeFrameManager);
        this.nativeResultsCallback = this.nativePipeline.initializeResultsCallback();
        this.nativeIsolationCallback = this.nativePipeline.initializeIsolationCallback();
        this.nativeContext = this.nativePipeline.initialize(pipelineConfig.toByteArray(), this.nativeFrameBufferReleaseCallback, this.nativeResultsCallback, this.nativeIsolationCallback, 0L, 0L);
    }
}
