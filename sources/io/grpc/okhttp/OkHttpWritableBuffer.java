package io.grpc.okhttp;

import io.grpc.internal.WritableBuffer;
import okio.Buffer;

/* compiled from: PG */
/* loaded from: classes.dex */
final class OkHttpWritableBuffer implements WritableBuffer {
    public final Buffer buffer;
    private int readableBytes;
    private int writableBytes;

    public OkHttpWritableBuffer(Buffer buffer, int i) {
        this.buffer = buffer;
        this.writableBytes = i;
    }

    @Override // io.grpc.internal.WritableBuffer
    public final int readableBytes() {
        return this.readableBytes;
    }

    @Override // io.grpc.internal.WritableBuffer
    public final int writableBytes() {
        return this.writableBytes;
    }

    @Override // io.grpc.internal.WritableBuffer
    public final void write(byte b) {
        this.buffer.writeByte$ar$ds(b);
        this.writableBytes--;
        this.readableBytes++;
    }

    @Override // io.grpc.internal.WritableBuffer
    public final void write(byte[] bArr, int i, int i2) {
        this.buffer.write$ar$ds$66c9c9c2_0(bArr, i, i2);
        this.writableBytes -= i2;
        this.readableBytes += i2;
    }
}
