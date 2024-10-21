package io.grpc.internal;

import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.KnownLength;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReadableBuffers {
    public static final ReadableBuffer EMPTY_BUFFER = new ByteArrayWrapper(new byte[0], 0, 0);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class BufferInputStream extends InputStream implements KnownLength, InputStreamRetargetInterface {
        private final ReadableBuffer buffer;

        public BufferInputStream(ReadableBuffer readableBuffer) {
            readableBuffer.getClass();
            this.buffer = readableBuffer;
        }

        @Override // java.io.InputStream
        public final int available() {
            return this.buffer.readableBytes();
        }

        @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
            this.buffer.close();
        }

        @Override // java.io.InputStream
        public final void mark(int i) {
            this.buffer.mark();
        }

        @Override // java.io.InputStream
        public final boolean markSupported() {
            return this.buffer.markSupported();
        }

        @Override // java.io.InputStream
        public final int read() {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            return this.buffer.readUnsignedByte();
        }

        @Override // java.io.InputStream
        public final void reset() {
            this.buffer.reset();
        }

        @Override // java.io.InputStream
        public final long skip(long j) {
            int min = (int) Math.min(this.buffer.readableBytes(), j);
            this.buffer.skipBytes(min);
            return min;
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public final /* synthetic */ long transferTo(OutputStream outputStream) {
            return DesugarInputStream.transferTo(this, outputStream);
        }

        @Override // java.io.InputStream
        public final int read(byte[] bArr, int i, int i2) {
            if (this.buffer.readableBytes() == 0) {
                return -1;
            }
            int min = Math.min(this.buffer.readableBytes(), i2);
            this.buffer.readBytes(bArr, i, min);
            return min;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ByteArrayWrapper extends AbstractReadableBuffer {
        final byte[] bytes;
        final int end;
        int mark = -1;
        int offset;

        public ByteArrayWrapper(byte[] bArr, int i, int i2) {
            boolean z;
            boolean z2;
            if (i >= 0) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkArgument(z, (Object) "offset must be >= 0");
            if (i2 >= 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            ContextDataProvider.checkArgument(z2, (Object) "length must be >= 0");
            int i3 = i2 + i;
            ContextDataProvider.checkArgument(i3 <= 0, (Object) "offset + length exceeds array boundary");
            this.bytes = bArr;
            this.offset = i;
            this.end = i3;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public final void mark() {
            this.mark = this.offset;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public final boolean markSupported() {
            return true;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final /* bridge */ /* synthetic */ ReadableBuffer readBytes(int i) {
            checkReadable(i);
            int i2 = this.offset;
            this.offset = i2 + i;
            return new ByteArrayWrapper(this.bytes, i2, i);
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final int readUnsignedByte() {
            checkReadable(1);
            int i = this.offset;
            this.offset = i + 1;
            return this.bytes[i] & 255;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final int readableBytes() {
            return this.end - this.offset;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public final void reset() {
            int i = this.mark;
            if (i != -1) {
                this.offset = i;
                return;
            }
            throw new InvalidMarkException();
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final void skipBytes(int i) {
            checkReadable(i);
            this.offset += i;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final void readBytes(OutputStream outputStream, int i) {
            checkReadable(i);
            outputStream.write(this.bytes, this.offset, i);
            this.offset += i;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final void readBytes(ByteBuffer byteBuffer) {
            byteBuffer.getClass();
            int remaining = byteBuffer.remaining();
            checkReadable(remaining);
            byteBuffer.put(this.bytes, this.offset, remaining);
            this.offset += remaining;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final void readBytes(byte[] bArr, int i, int i2) {
            System.arraycopy(this.bytes, this.offset, bArr, i, i2);
            this.offset += i2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ByteReadableBufferWrapper extends AbstractReadableBuffer {
        final ByteBuffer bytes;

        public ByteReadableBufferWrapper(ByteBuffer byteBuffer) {
            byteBuffer.getClass();
            this.bytes = byteBuffer;
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public final void mark() {
            this.bytes.mark();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public final boolean markSupported() {
            return true;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final /* bridge */ /* synthetic */ ReadableBuffer readBytes(int i) {
            checkReadable(i);
            ByteBuffer byteBuffer = this.bytes;
            ByteBuffer duplicate = byteBuffer.duplicate();
            duplicate.limit(byteBuffer.position() + i);
            this.bytes.position(this.bytes.position() + i);
            return new ByteReadableBufferWrapper(duplicate);
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final int readUnsignedByte() {
            checkReadable(1);
            return this.bytes.get() & 255;
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final int readableBytes() {
            return this.bytes.remaining();
        }

        @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
        public final void reset() {
            this.bytes.reset();
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final void skipBytes(int i) {
            checkReadable(i);
            this.bytes.position(this.bytes.position() + i);
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final void readBytes(OutputStream outputStream, int i) {
            checkReadable(i);
            if (this.bytes.hasArray()) {
                ByteBuffer byteBuffer = this.bytes;
                outputStream.write(byteBuffer.array(), byteBuffer.arrayOffset() + byteBuffer.position(), i);
                ByteBuffer byteBuffer2 = this.bytes;
                byteBuffer2.position(byteBuffer2.position() + i);
                return;
            }
            byte[] bArr = new byte[i];
            this.bytes.get(bArr);
            outputStream.write(bArr);
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final void readBytes(ByteBuffer byteBuffer) {
            byteBuffer.getClass();
            int remaining = byteBuffer.remaining();
            checkReadable(remaining);
            ByteBuffer byteBuffer2 = this.bytes;
            int limit = byteBuffer2.limit();
            this.bytes.limit(byteBuffer2.position() + remaining);
            byteBuffer.put(this.bytes);
            this.bytes.limit(limit);
        }

        @Override // io.grpc.internal.ReadableBuffer
        public final void readBytes(byte[] bArr, int i, int i2) {
            checkReadable(i2);
            this.bytes.get(bArr, i, i2);
        }
    }
}
