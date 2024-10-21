package com.google.android.libraries.vision.visionkit.pipeline.alt;

import android.graphics.Bitmap;
import android.util.Log;
import com.google.android.libraries.vision.visionkit.base.L;
import com.google.android.libraries.vision.visionkit.pipeline.PipelineBufferCallback;
import com.google.android.libraries.vision.visionkit.pipeline.PipelineIsolationCallback;
import com.google.android.libraries.vision.visionkit.pipeline.PipelineResultsCallback;
import com.google.android.libraries.vision.visionkit.pipeline.Results;
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

    public void closeFileDescriptor(int i) {
        Log.w("VKP", "closeFileDescriptor called but is not available for this pipeline. Ignoring call.");
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.NativePipeline
    public native long initialize(byte[] bArr, long j, long j2, long j3, long j4, long j5);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.NativePipeline
    public native long initializeFrameBufferReleaseCallback(long j);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.NativePipeline
    public native long initializeFrameManager();

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.NativePipeline
    public native long initializeIsolationCallback();

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.NativePipeline
    public native long initializeResultsCallback();

    public void onReleaseAtTimestampUs(long j) {
        ((Pipeline) this.pipelineBufferCallback).frameBuffer$ar$class_merging.removeFrame(j);
    }

    public void onResult(byte[] bArr) {
        try {
            Results results = (Results) GeneratedMessageLite.parseFrom(Results.DEFAULT_INSTANCE, bArr, this.extensionRegistry);
            L.log.i(this.pipelineResultsCallback, "Pipeline received results: ".concat(String.valueOf(String.valueOf(results))), new Object[0]);
        } catch (InvalidProtocolBufferException e) {
            L.log.e$ar$ds(e, new Object[0]);
        }
    }

    public int openFileDescriptor(String str) {
        Log.w("VKP", "openFileDescriptor called but is not available for this pipeline. Ignoring call.");
        return -1;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.NativePipeline
    public native byte[] processBitmap(long j, long j2, Bitmap bitmap, int i, int i2, int i3, int i4);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.NativePipeline
    public native void start(long j);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.NativePipeline
    public native boolean stop(long j);

    @Override // com.google.android.libraries.vision.visionkit.pipeline.alt.NativePipeline
    public native void waitUntilIdle(long j);

    public NativePipelineImpl(String str, PipelineBufferCallback pipelineBufferCallback, PipelineResultsCallback pipelineResultsCallback, PipelineIsolationCallback pipelineIsolationCallback, ExtensionRegistryLite extensionRegistryLite) {
        this(pipelineBufferCallback, pipelineResultsCallback, pipelineIsolationCallback, extensionRegistryLite);
        System.loadLibrary(str);
    }
}
