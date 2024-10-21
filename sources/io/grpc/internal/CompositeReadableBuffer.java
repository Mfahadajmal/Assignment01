package io.grpc.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.InvalidMarkException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CompositeReadableBuffer extends AbstractReadableBuffer {
    private static final NoThrowReadOperation BYTE_ARRAY_OP;
    private static final NoThrowReadOperation BYTE_BUF_OP;
    private static final NoThrowReadOperation SKIP_OP;
    private static final ReadOperation STREAM_OP = new ReadOperation() { // from class: io.grpc.internal.CompositeReadableBuffer.5
        @Override // io.grpc.internal.CompositeReadableBuffer.ReadOperation
        public final /* bridge */ /* synthetic */ int read(ReadableBuffer readableBuffer, int i, Object obj, int i2) {
            readableBuffer.readBytes((OutputStream) obj, i);
            return 0;
        }
    };
    private static final NoThrowReadOperation UBYTE_OP;
    private boolean marked;
    private final Deque readableBuffers;
    public int readableBytes;
    private Deque rewindableBuffers;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    interface NoThrowReadOperation extends ReadOperation {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ReadOperation {
        int read(ReadableBuffer readableBuffer, int i, Object obj, int i2);
    }

    static {
        final int i = 1;
        UBYTE_OP = new NoThrowReadOperation() { // from class: io.grpc.internal.CompositeReadableBuffer.2
            @Override // io.grpc.internal.CompositeReadableBuffer.ReadOperation
            public final /* synthetic */ int read(ReadableBuffer readableBuffer, int i2, Object obj, int i3) {
                int i4 = i;
                if (i4 != 0) {
                    if (i4 != 1) {
                        if (i4 != 2) {
                            ByteBuffer byteBuffer = (ByteBuffer) obj;
                            int limit = byteBuffer.limit();
                            byteBuffer.limit(byteBuffer.position() + i2);
                            readableBuffer.readBytes(byteBuffer);
                            byteBuffer.limit(limit);
                            return 0;
                        }
                        readableBuffer.readBytes((byte[]) obj, i3, i2);
                        return i3 + i2;
                    }
                    return readableBuffer.readUnsignedByte();
                }
                readableBuffer.skipBytes(i2);
                return 0;
            }
        };
        final int i2 = 0;
        SKIP_OP = new NoThrowReadOperation() { // from class: io.grpc.internal.CompositeReadableBuffer.2
            @Override // io.grpc.internal.CompositeReadableBuffer.ReadOperation
            public final /* synthetic */ int read(ReadableBuffer readableBuffer, int i22, Object obj, int i3) {
                int i4 = i2;
                if (i4 != 0) {
                    if (i4 != 1) {
                        if (i4 != 2) {
                            ByteBuffer byteBuffer = (ByteBuffer) obj;
                            int limit = byteBuffer.limit();
                            byteBuffer.limit(byteBuffer.position() + i22);
                            readableBuffer.readBytes(byteBuffer);
                            byteBuffer.limit(limit);
                            return 0;
                        }
                        readableBuffer.readBytes((byte[]) obj, i3, i22);
                        return i3 + i22;
                    }
                    return readableBuffer.readUnsignedByte();
                }
                readableBuffer.skipBytes(i22);
                return 0;
            }
        };
        final int i3 = 2;
        BYTE_ARRAY_OP = new NoThrowReadOperation() { // from class: io.grpc.internal.CompositeReadableBuffer.2
            @Override // io.grpc.internal.CompositeReadableBuffer.ReadOperation
            public final /* synthetic */ int read(ReadableBuffer readableBuffer, int i22, Object obj, int i32) {
                int i4 = i3;
                if (i4 != 0) {
                    if (i4 != 1) {
                        if (i4 != 2) {
                            ByteBuffer byteBuffer = (ByteBuffer) obj;
                            int limit = byteBuffer.limit();
                            byteBuffer.limit(byteBuffer.position() + i22);
                            readableBuffer.readBytes(byteBuffer);
                            byteBuffer.limit(limit);
                            return 0;
                        }
                        readableBuffer.readBytes((byte[]) obj, i32, i22);
                        return i32 + i22;
                    }
                    return readableBuffer.readUnsignedByte();
                }
                readableBuffer.skipBytes(i22);
                return 0;
            }
        };
        final int i4 = 3;
        BYTE_BUF_OP = new NoThrowReadOperation() { // from class: io.grpc.internal.CompositeReadableBuffer.2
            @Override // io.grpc.internal.CompositeReadableBuffer.ReadOperation
            public final /* synthetic */ int read(ReadableBuffer readableBuffer, int i22, Object obj, int i32) {
                int i42 = i4;
                if (i42 != 0) {
                    if (i42 != 1) {
                        if (i42 != 2) {
                            ByteBuffer byteBuffer = (ByteBuffer) obj;
                            int limit = byteBuffer.limit();
                            byteBuffer.limit(byteBuffer.position() + i22);
                            readableBuffer.readBytes(byteBuffer);
                            byteBuffer.limit(limit);
                            return 0;
                        }
                        readableBuffer.readBytes((byte[]) obj, i32, i22);
                        return i32 + i22;
                    }
                    return readableBuffer.readUnsignedByte();
                }
                readableBuffer.skipBytes(i22);
                return 0;
            }
        };
    }

    public CompositeReadableBuffer() {
        new ArrayDeque(2);
        this.readableBuffers = new ArrayDeque();
    }

    private final void advanceBuffer() {
        if (this.marked) {
            this.rewindableBuffers.add((ReadableBuffer) this.readableBuffers.remove());
            ReadableBuffer readableBuffer = (ReadableBuffer) this.readableBuffers.peek();
            if (readableBuffer != null) {
                readableBuffer.mark();
                return;
            }
            return;
        }
        ((ReadableBuffer) this.readableBuffers.remove()).close();
    }

    private final void advanceBufferIfNecessary() {
        if (((ReadableBuffer) this.readableBuffers.peek()).readableBytes() == 0) {
            advanceBuffer();
        }
    }

    private final int execute(ReadOperation readOperation, int i, Object obj, int i2) {
        checkReadable(i);
        if (!this.readableBuffers.isEmpty()) {
            advanceBufferIfNecessary();
        }
        while (i > 0 && !this.readableBuffers.isEmpty()) {
            ReadableBuffer readableBuffer = (ReadableBuffer) this.readableBuffers.peek();
            int min = Math.min(i, readableBuffer.readableBytes());
            i2 = readOperation.read(readableBuffer, min, obj, i2);
            i -= min;
            this.readableBytes -= min;
            advanceBufferIfNecessary();
        }
        if (i <= 0) {
            return i2;
        }
        throw new AssertionError("Failed executing read operation");
    }

    private final int executeNoThrow(NoThrowReadOperation noThrowReadOperation, int i, Object obj, int i2) {
        try {
            return execute(noThrowReadOperation, i, obj, i2);
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public final void addBuffer(ReadableBuffer readableBuffer) {
        boolean z;
        if (this.marked && this.readableBuffers.isEmpty()) {
            z = true;
        } else {
            z = false;
        }
        if (!(readableBuffer instanceof CompositeReadableBuffer)) {
            this.readableBuffers.add(readableBuffer);
            this.readableBytes += readableBuffer.readableBytes();
        } else {
            CompositeReadableBuffer compositeReadableBuffer = (CompositeReadableBuffer) readableBuffer;
            while (!compositeReadableBuffer.readableBuffers.isEmpty()) {
                this.readableBuffers.add((ReadableBuffer) compositeReadableBuffer.readableBuffers.remove());
            }
            this.readableBytes += compositeReadableBuffer.readableBytes;
            compositeReadableBuffer.readableBytes = 0;
            compositeReadableBuffer.close();
        }
        if (z) {
            ((ReadableBuffer) this.readableBuffers.peek()).mark();
        }
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        while (!this.readableBuffers.isEmpty()) {
            ((ReadableBuffer) this.readableBuffers.remove()).close();
        }
        if (this.rewindableBuffers != null) {
            while (!this.rewindableBuffers.isEmpty()) {
                ((ReadableBuffer) this.rewindableBuffers.remove()).close();
            }
        }
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
    public final void mark() {
        if (this.rewindableBuffers == null) {
            this.rewindableBuffers = new ArrayDeque(Math.min(this.readableBuffers.size(), 16));
        }
        while (!this.rewindableBuffers.isEmpty()) {
            ((ReadableBuffer) this.rewindableBuffers.remove()).close();
        }
        this.marked = true;
        ReadableBuffer readableBuffer = (ReadableBuffer) this.readableBuffers.peek();
        if (readableBuffer != null) {
            readableBuffer.mark();
        }
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
    public final boolean markSupported() {
        Iterator it = this.readableBuffers.iterator();
        while (it.hasNext()) {
            if (!((ReadableBuffer) it.next()).markSupported()) {
                return false;
            }
        }
        return true;
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final ReadableBuffer readBytes(int i) {
        ReadableBuffer readableBuffer;
        int i2;
        ReadableBuffer readableBuffer2;
        if (i <= 0) {
            return ReadableBuffers.EMPTY_BUFFER;
        }
        checkReadable(i);
        this.readableBytes -= i;
        ReadableBuffer readableBuffer3 = null;
        CompositeReadableBuffer compositeReadableBuffer = null;
        while (true) {
            ReadableBuffer readableBuffer4 = (ReadableBuffer) this.readableBuffers.peek();
            int readableBytes = readableBuffer4.readableBytes();
            if (readableBytes > i) {
                readableBuffer2 = readableBuffer4.readBytes(i);
                i2 = 0;
            } else {
                if (this.marked) {
                    readableBuffer = readableBuffer4.readBytes(readableBytes);
                    advanceBuffer();
                } else {
                    readableBuffer = (ReadableBuffer) this.readableBuffers.poll();
                }
                ReadableBuffer readableBuffer5 = readableBuffer;
                i2 = i - readableBytes;
                readableBuffer2 = readableBuffer5;
            }
            if (readableBuffer3 == null) {
                readableBuffer3 = readableBuffer2;
            } else {
                if (compositeReadableBuffer == null) {
                    compositeReadableBuffer = new CompositeReadableBuffer(i2 != 0 ? Math.min(this.readableBuffers.size() + 2, 16) : 2);
                    compositeReadableBuffer.addBuffer(readableBuffer3);
                    readableBuffer3 = compositeReadableBuffer;
                }
                compositeReadableBuffer.addBuffer(readableBuffer2);
            }
            if (i2 <= 0) {
                return readableBuffer3;
            }
            i = i2;
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final int readUnsignedByte() {
        return executeNoThrow(UBYTE_OP, 1, null, 0);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final int readableBytes() {
        return this.readableBytes;
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
    public final void reset() {
        if (this.marked) {
            ReadableBuffer readableBuffer = (ReadableBuffer) this.readableBuffers.peek();
            if (readableBuffer != null) {
                int readableBytes = readableBuffer.readableBytes();
                readableBuffer.reset();
                this.readableBytes += readableBuffer.readableBytes() - readableBytes;
            }
            while (true) {
                ReadableBuffer readableBuffer2 = (ReadableBuffer) this.rewindableBuffers.pollLast();
                if (readableBuffer2 != null) {
                    readableBuffer2.reset();
                    this.readableBuffers.addFirst(readableBuffer2);
                    this.readableBytes += readableBuffer2.readableBytes();
                } else {
                    return;
                }
            }
        } else {
            throw new InvalidMarkException();
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final void skipBytes(int i) {
        executeNoThrow(SKIP_OP, i, null, 0);
    }

    public CompositeReadableBuffer(int i) {
        new ArrayDeque(2);
        this.readableBuffers = new ArrayDeque(i);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final void readBytes(OutputStream outputStream, int i) {
        execute(STREAM_OP, i, outputStream, 0);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final void readBytes(ByteBuffer byteBuffer) {
        executeNoThrow(BYTE_BUF_OP, byteBuffer.remaining(), byteBuffer, 0);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public final void readBytes(byte[] bArr, int i, int i2) {
        executeNoThrow(BYTE_ARRAY_OP, i2, bArr, i);
    }
}
