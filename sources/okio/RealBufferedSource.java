package okio;

import _COROUTINE._BOUNDARY;
import java.io.EOFException;
import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RealBufferedSource implements BufferedSource {
    public final Buffer bufferField = new Buffer();
    public boolean closed;
    public final Source source;

    public RealBufferedSource(Source source) {
        this.source = source;
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (!this.closed) {
            this.closed = true;
            this.source.close();
            this.bufferField.clear();
        }
    }

    @Override // okio.BufferedSource
    public final boolean exhausted() {
        if (!this.closed) {
            Buffer buffer = this.bufferField;
            if (buffer.exhausted() && this.source.read(buffer, 8192L) == -1) {
                return true;
            }
            return false;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSource
    public final Buffer getBuffer() {
        return this.bufferField;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        if (!this.closed) {
            return true;
        }
        return false;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public final int read(ByteBuffer byteBuffer) {
        byteBuffer.getClass();
        Buffer buffer = this.bufferField;
        if (buffer.size == 0 && this.source.read(buffer, 8192L) == -1) {
            return -1;
        }
        return this.bufferField.read(byteBuffer);
    }

    @Override // okio.BufferedSource
    public final byte readByte() {
        require(1L);
        return this.bufferField.readByte();
    }

    @Override // okio.BufferedSource
    public final byte[] readByteArray(long j) {
        require(j);
        return this.bufferField.readByteArray(j);
    }

    @Override // okio.BufferedSource
    public final ByteString readByteString(long j) {
        require(j);
        return this.bufferField.readByteString(j);
    }

    @Override // okio.BufferedSource
    public final int readInt() {
        require(4L);
        return this.bufferField.readInt();
    }

    @Override // okio.BufferedSource
    public final short readShort() {
        require(2L);
        return this.bufferField.readShort();
    }

    @Override // okio.BufferedSource
    public final void require(long j) {
        Buffer buffer;
        if (j >= 0) {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            do {
                buffer = this.bufferField;
                if (buffer.size >= j) {
                    return;
                }
            } while (this.source.read(buffer, 8192L) != -1);
            throw new EOFException();
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j, "byteCount < 0: "));
    }

    @Override // okio.BufferedSource
    public final void skip(long j) {
        if (!this.closed) {
            while (j > 0) {
                Buffer buffer = this.bufferField;
                if (buffer.size == 0 && this.source.read(buffer, 8192L) == -1) {
                    throw new EOFException();
                }
                long min = Math.min(j, this.bufferField.size);
                this.bufferField.skip(min);
                j -= min;
            }
            return;
        }
        throw new IllegalStateException("closed");
    }

    public final String toString() {
        return "buffer(" + this.source + ")";
    }

    @Override // okio.Source
    public final long read(Buffer buffer, long j) {
        if (j >= 0) {
            if (this.closed) {
                throw new IllegalStateException("closed");
            }
            Buffer buffer2 = this.bufferField;
            if (buffer2.size == 0 && this.source.read(buffer2, 8192L) == -1) {
                return -1L;
            }
            return this.bufferField.read(buffer, Math.min(j, this.bufferField.size));
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_8(j, "byteCount < 0: "));
    }
}
