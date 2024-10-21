package io.grpc.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractReadableBuffer implements ReadableBuffer {
    /* JADX INFO: Access modifiers changed from: protected */
    public final void checkReadable(int i) {
        if (readableBytes() >= i) {
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public boolean markSupported() {
        return false;
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void reset() {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.internal.ReadableBuffer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void mark() {
    }
}
