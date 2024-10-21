package io.grpc.okhttp;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.ToxicityDetectionCreateEvent;
import io.grpc.internal.AbstractReadableBuffer;
import io.grpc.internal.ReadableBuffer;
import java.io.EOFException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import okio.Buffer;
import okio.Segment;
import okio.SegmentPool;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class OkHttpReadableBuffer extends AbstractReadableBuffer {
    private final Buffer buffer;

    public OkHttpReadableBuffer(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        this.buffer.clear();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final ReadableBuffer readBytes(int i) {
        Buffer buffer = new Buffer();
        buffer.write(this.buffer, i);
        return new OkHttpReadableBuffer(buffer);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final int readUnsignedByte() {
        try {
            return this.buffer.readByte() & 255;
        } catch (EOFException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final int readableBytes() {
        return (int) this.buffer.size;
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final void skipBytes(int i) {
        try {
            this.buffer.skip(i);
        } catch (EOFException e) {
            throw new IndexOutOfBoundsException(e.getMessage());
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final void readBytes(OutputStream outputStream, int i) {
        outputStream.getClass();
        Buffer buffer = this.buffer;
        long j = i;
        ToxicityDetectionCreateEvent.checkOffsetAndCount(buffer.size, 0L, j);
        Segment segment = buffer.head;
        while (j > 0) {
            segment.getClass();
            int min = (int) Math.min(j, segment.limit - segment.pos);
            outputStream.write(segment.data, segment.pos, min);
            int i2 = segment.pos + min;
            segment.pos = i2;
            long j2 = min;
            buffer.size -= j2;
            j -= j2;
            if (i2 == segment.limit) {
                Segment pop = segment.pop();
                buffer.head = pop;
                SegmentPool.recycle(segment);
                segment = pop;
            }
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final void readBytes(ByteBuffer byteBuffer) {
        throw new UnsupportedOperationException();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final void readBytes(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            int read = this.buffer.read(bArr, i, i2);
            if (read == -1) {
                throw new IndexOutOfBoundsException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(i2, "EOF trying to read ", " bytes"));
            }
            i2 -= read;
            i += read;
        }
    }
}
