package com.google.android.libraries.vision.visionkit.pipeline;

import android.util.Log;
import com.google.android.libraries.vision.visionkit.base.L;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import com.google.protobuf.ExtensionRegistryLite;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Pipeline implements PipelineBufferCallback, PipelineResultsCallback, PipelineIsolationCallback {
    public final OrderVerifyingClientCall.State frameBuffer$ar$class_merging;
    public long nativeContext;
    private final long nativeFrameBufferReleaseCallback;
    public final long nativeFrameManager;
    private final long nativeIsolationCallback;
    public final NativePipeline nativePipeline;
    private final long nativeResultsCallback;

    public Pipeline(PipelineConfig pipelineConfig, ExtensionRegistryLite extensionRegistryLite) {
        if (pipelineConfig.soLibraryConfigurationCase_ == 5 && ((Boolean) pipelineConfig.soLibraryConfiguration_).booleanValue()) {
            this.nativePipeline = new NativePipelineStub();
        } else if (pipelineConfig.soLibraryConfigurationCase_ == 6 && ((Boolean) pipelineConfig.soLibraryConfiguration_).booleanValue()) {
            this.nativePipeline = new NativePipelineImpl(this, this, this, extensionRegistryLite);
        } else {
            this.nativePipeline = new NativePipelineImpl(this, this, this, extensionRegistryLite, null);
        }
        if ((pipelineConfig.bitField0_ & 32) != 0) {
            this.frameBuffer$ar$class_merging = new OrderVerifyingClientCall.State(pipelineConfig.experimentalMaxInflightFrames_);
        } else {
            this.frameBuffer$ar$class_merging = new OrderVerifyingClientCall.State(10);
        }
        long initializeFrameManager = this.nativePipeline.initializeFrameManager();
        this.nativeFrameManager = initializeFrameManager;
        long initializeFrameBufferReleaseCallback = this.nativePipeline.initializeFrameBufferReleaseCallback(initializeFrameManager);
        this.nativeFrameBufferReleaseCallback = initializeFrameBufferReleaseCallback;
        long initializeResultsCallback = this.nativePipeline.initializeResultsCallback();
        this.nativeResultsCallback = initializeResultsCallback;
        long initializeIsolationCallback = this.nativePipeline.initializeIsolationCallback();
        this.nativeIsolationCallback = initializeIsolationCallback;
        this.nativeContext = this.nativePipeline.initialize(pipelineConfig.toByteArray(), initializeFrameBufferReleaseCallback, initializeResultsCallback, initializeIsolationCallback, 0L, 0L);
    }

    public final synchronized void close() {
        long j = this.nativeContext;
        if (j != 0) {
            this.nativePipeline.stop(j);
            this.nativePipeline.close(this.nativeContext, this.nativeFrameManager, this.nativeFrameBufferReleaseCallback, this.nativeResultsCallback, this.nativeIsolationCallback);
            this.nativeContext = 0L;
            this.nativePipeline.clearAllReferences();
        }
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.PipelineIsolationCallback
    public final void closeFileDescriptor$ar$ds() {
        Log.w("VKP", "closeFileDescriptor called but is not available for this pipeline. Ignoring call.");
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.PipelineBufferCallback
    public final void onReleaseAtTimestampUs(long j) {
        this.frameBuffer$ar$class_merging.removeFrame(j);
    }

    public void onResult(Results results) {
        L.log.i(this, "Pipeline received results: ".concat(String.valueOf(String.valueOf(results))), new Object[0]);
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.PipelineIsolationCallback
    public final void openFileDescriptor$ar$ds() {
        Log.w("VKP", "openFileDescriptor called but is not available for this pipeline. Ignoring call.");
    }
}
