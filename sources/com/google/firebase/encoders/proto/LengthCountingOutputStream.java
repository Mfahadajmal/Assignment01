package com.google.firebase.encoders.proto;

import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
final class LengthCountingOutputStream extends OutputStream {
    public long length = 0;

    @Override // java.io.OutputStream
    public final void write(int i) {
        this.length++;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr) {
        this.length += bArr.length;
    }

    @Override // java.io.OutputStream
    public final void write(byte[] bArr, int i, int i2) {
        int length;
        int i3;
        if (i >= 0 && i <= (length = bArr.length) && i2 >= 0 && (i3 = i + i2) <= length && i3 >= 0) {
            this.length += i2;
            return;
        }
        throw new IndexOutOfBoundsException();
    }
}
