package com.google.android.libraries.vision.visionkit.pipeline;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface NativePipeline {
    void clearAllReferences();

    void close(long j, long j2, long j3, long j4, long j5);

    long initialize(byte[] bArr, long j, long j2, long j3, long j4, long j5);

    long initializeFrameBufferReleaseCallback(long j);

    long initializeFrameManager();

    long initializeIsolationCallback();

    long initializeResultsCallback();

    boolean receivePreviewFrameWithStreamName(long j, long j2, long j3, byte[] bArr, int i, int i2, int i3, int i4, String str);

    void start(long j);

    boolean stop(long j);
}
