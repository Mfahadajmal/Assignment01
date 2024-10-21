package io.grpc.cronet;

import io.grpc.internal.WritableBuffer;
import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetWritableBuffer implements WritableBuffer {
    public final ByteBuffer buffer;

    public CronetWritableBuffer(ByteBuffer byteBuffer) {
        byteBuffer.getClass();
        this.buffer = byteBuffer;
    }

    @Override // io.grpc.internal.WritableBuffer
    public final int readableBytes() {
        return this.buffer.position();
    }

    @Override // io.grpc.internal.WritableBuffer
    public final int writableBytes() {
        return this.buffer.remaining();
    }

    @Override // io.grpc.internal.WritableBuffer
    public final void write(byte b) {
        this.buffer.put(b);
    }

    @Override // io.grpc.internal.WritableBuffer
    public final void write(byte[] bArr, int i, int i2) {
        this.buffer.put(bArr, i, i2);
    }
}
