package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.libraries.vision.visionkit.base.L;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;

/* compiled from: PG */
/* loaded from: classes.dex */
class NativePipelineImpl implements NativePipeline {
    private ExtensionRegistryLite extensionRegistry;
    private PipelineBufferCallback pipelineBufferCallback;
    private PipelineIsolationCallback pipelineIsolationCallback;
    private PipelineResultsCallback pipelineResultsCallback;

    public NativePipelineImpl(PipelineBufferCallback pipelineBufferCallback, PipelineResultsCallback pipelineResultsCallback, PipelineIsolationCallback pipelineIsolationCallback, ExtensionRegistryLite extensionRegistryLite) {
        this.pipelineBufferCallback = pipelineBufferCallback;
        this.pipelineResultsCallback = pipelineResultsCallback;
        this.pipelineIsolationCallback = pipelineIsolationCallback;
        this.extensionRegistry = extensionRegistryLite;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public final void clearAllReferences() {
        this.extensionRegistry = null;
        this.pipelineBufferCallback = null;
        this.pipelineResultsCallback = null;
        this.pipelineIsolationCallback = null;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public native void close(long j, long j2, long j3, long j4, long j5);

    public void closeFileDescriptor(int i) {
        this.pipelineIsolationCallback.closeFileDescriptor$ar$ds();
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public native long initialize(byte[] bArr, long j, long j2, long j3, long j4, long j5);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public native long initializeFrameBufferReleaseCallback(long j);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public native long initializeFrameManager();

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public native long initializeIsolationCallback();

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public native long initializeResultsCallback();

    public void onReleaseAtTimestampUs(long j) {
        this.pipelineBufferCallback.onReleaseAtTimestampUs(j);
    }

    public void onResult(byte[] bArr) {
        try {
            this.pipelineResultsCallback.onResult((Results) GeneratedMessageLite.parseFrom(Results.DEFAULT_INSTANCE, bArr, this.extensionRegistry));
        } catch (InvalidProtocolBufferException e) {
            L.log.e$ar$ds(e, new Object[0]);
        }
    }

    public int openFileDescriptor(String str) {
        this.pipelineIsolationCallback.openFileDescriptor$ar$ds();
        return -1;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public native boolean receivePreviewFrameWithStreamName(long j, long j2, long j3, byte[] bArr, int i, int i2, int i3, int i4, String str);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public native void start(long j);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public native boolean stop(long j);

    public NativePipelineImpl(PipelineBufferCallback pipelineBufferCallback, PipelineResultsCallback pipelineResultsCallback, PipelineIsolationCallback pipelineIsolationCallback, ExtensionRegistryLite extensionRegistryLite, byte[] bArr) {
        this(pipelineBufferCallback, pipelineResultsCallback, pipelineIsolationCallback, extensionRegistryLite);
        System.loadLibrary("accessibilityscreenunderstanding");
    }
}
