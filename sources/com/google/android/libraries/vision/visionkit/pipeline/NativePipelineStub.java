package com.google.android.libraries.vision.visionkit.pipeline;

/* compiled from: PG */
/* loaded from: classes.dex */
final class NativePipelineStub implements NativePipeline {
    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public final long initialize(byte[] bArr, long j, long j2, long j3, long j4, long j5) {
        return 1L;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public final long initializeFrameBufferReleaseCallback(long j) {
        return 1L;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public final long initializeFrameManager() {
        return 1L;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public final long initializeIsolationCallback() {
        return 1L;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public final long initializeResultsCallback() {
        return 1L;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public final boolean receivePreviewFrameWithStreamName(long j, long j2, long j3, byte[] bArr, int i, int i2, int i3, int i4, String str) {
        return true;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public final boolean stop(long j) {
        return true;
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public final void clearAllReferences() {
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public final void start(long j) {
    }

    @Override // com.google.android.libraries.vision.visionkit.pipeline.NativePipeline
    public final void close(long j, long j2, long j3, long j4, long j5) {
    }
}
