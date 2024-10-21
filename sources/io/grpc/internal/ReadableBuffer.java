package io.grpc.internal;

import java.io.Closeable;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ReadableBuffer extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();

    void mark();

    boolean markSupported();

    ReadableBuffer readBytes(int i);

    void readBytes(OutputStream outputStream, int i);

    void readBytes(ByteBuffer byteBuffer);

    void readBytes(byte[] bArr, int i, int i2);

    int readUnsignedByte();

    int readableBytes();

    void reset();

    void skipBytes(int i);
}
