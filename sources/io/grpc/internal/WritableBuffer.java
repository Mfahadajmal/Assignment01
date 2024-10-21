package io.grpc.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface WritableBuffer {
    int readableBytes();

    int writableBytes();

    void write(byte b);

    void write(byte[] bArr, int i, int i2);
}
