package okio;

import java.nio.ByteBuffer;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RealBufferedSink implements BufferedSink {
    public final Buffer bufferField = new Buffer();
    public boolean closed;
    public final Sink sink;

    public RealBufferedSink(Sink sink) {
        this.sink = sink;
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Throwable th;
        if (!this.closed) {
            try {
                Buffer buffer = this.bufferField;
                long j = buffer.size;
                th = null;
                if (j > 0) {
                    this.sink.write(buffer, j);
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                this.sink.close();
            } catch (Throwable th3) {
                if (th == null) {
                    th = th3;
                }
            }
            this.closed = true;
            if (th != null) {
                throw th;
            }
        }
    }

    public final BufferedSink emitCompleteSegments() {
        if (!this.closed) {
            long completeSegmentByteCount = this.bufferField.completeSegmentByteCount();
            if (completeSegmentByteCount > 0) {
                this.sink.write(this.bufferField, completeSegmentByteCount);
            }
            return this;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public final void flush() {
        if (!this.closed) {
            Buffer buffer = this.bufferField;
            long j = buffer.size;
            if (j > 0) {
                this.sink.write(buffer, j);
            }
            this.sink.flush();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        if (!this.closed) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "buffer(" + this.sink + ")";
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) {
        byteBuffer.getClass();
        if (!this.closed) {
            int write = this.bufferField.write(byteBuffer);
            emitCompleteSegments();
            return write;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public final void write$ar$ds$c3288001_0(byte[] bArr) {
        if (!this.closed) {
            this.bufferField.write(bArr);
            emitCompleteSegments();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public final void writeByte$ar$ds$1b66c408_0(int i) {
        if (!this.closed) {
            this.bufferField.writeByte$ar$ds(i);
            emitCompleteSegments();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public final void writeInt$ar$ds$7d1bee7_0(int i) {
        if (!this.closed) {
            this.bufferField.writeInt$ar$ds(i);
            emitCompleteSegments();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public final void writeShort$ar$ds$a45469cc_0(int i) {
        if (!this.closed) {
            this.bufferField.writeShort$ar$ds(i);
            emitCompleteSegments();
            return;
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.BufferedSink
    public final BufferedSink writeUtf8(String str) {
        str.getClass();
        if (!this.closed) {
            this.bufferField.writeUtf8(str);
            return emitCompleteSegments();
        }
        throw new IllegalStateException("closed");
    }

    @Override // okio.Sink
    public final void write(Buffer buffer, long j) {
        if (!this.closed) {
            this.bufferField.write(buffer, j);
            emitCompleteSegments();
            return;
        }
        throw new IllegalStateException("closed");
    }
}
