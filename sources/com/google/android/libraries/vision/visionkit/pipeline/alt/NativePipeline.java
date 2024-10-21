package com.google.android.libraries.vision.visionkit.pipeline.alt;

import android.graphics.Bitmap;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface NativePipeline {
    long initialize(byte[] bArr, long j, long j2, long j3, long j4, long j5);

    long initializeFrameBufferReleaseCallback(long j);

    long initializeFrameManager();

    long initializeIsolationCallback();

    long initializeResultsCallback();

    byte[] processBitmap(long j, long j2, Bitmap bitmap, int i, int i2, int i3, int i4);

    void start(long j);

    boolean stop(long j);

    void waitUntilIdle(long j);
}
