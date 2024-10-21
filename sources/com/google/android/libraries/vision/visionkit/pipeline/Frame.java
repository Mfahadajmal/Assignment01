package com.google.android.libraries.vision.visionkit.pipeline;

import com.google.android.libraries.vision.visionkit.camera.manager.Size;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Frame {
    public final int colorSpace$ar$edu;
    public final byte[] rawData;
    public final int rotation$ar$edu;
    public final Size size;
    public final long timestampUs;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public int colorSpace$ar$edu;
        public byte[] rawData;
        public int rotation$ar$edu;
        public Size size;
        public long timestampUs;
    }

    public Frame(byte[] bArr, long j, Size size, int i, int i2) {
        this.rawData = bArr;
        this.timestampUs = j;
        this.size = size;
        this.colorSpace$ar$edu = i;
        this.rotation$ar$edu = i2;
    }
}
