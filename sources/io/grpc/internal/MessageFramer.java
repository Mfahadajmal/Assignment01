package io.grpc.internal;

import com.google.common.flogger.context.ContextDataProvider;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.MessageLite;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.protobuf.lite.ProtoInputStream;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MessageFramer implements Framer {
    private WritableBuffer buffer;
    public final WritableBufferAllocator bufferAllocator;
    private boolean closed;
    private long currentMessageWireSize;
    private int messagesBuffered;
    private final Sink sink;
    private final StatsTraceContext statsTraceCtx;
    private int maxOutboundMessageSize = -1;
    private Compressor compressor = Codec.Identity.NONE;
    private final boolean messageCompression = true;
    private final OutputStreamAdapter outputStreamAdapter = new OutputStreamAdapter();
    private final ByteBuffer headerScratch = ByteBuffer.allocate(5);
    private int currentMessageSeqNo = -1;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class OutputStreamAdapter extends OutputStream {
        public OutputStreamAdapter() {
        }

        @Override // java.io.OutputStream
        public final void write(int i) {
            write(new byte[]{(byte) i}, 0, 1);
        }

        @Override // java.io.OutputStream
        public final void write(byte[] bArr, int i, int i2) {
            MessageFramer.this.writeRaw(bArr, i, i2);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Sink {
        void deliverFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i);
    }

    public MessageFramer(Sink sink, WritableBufferAllocator writableBufferAllocator, StatsTraceContext statsTraceContext) {
        this.sink = sink;
        this.bufferAllocator = writableBufferAllocator;
        this.statsTraceCtx = statsTraceContext;
    }

    private final void commitToSink(boolean z, boolean z2) {
        WritableBuffer writableBuffer = this.buffer;
        this.buffer = null;
        this.sink.deliverFrame(writableBuffer, z, z2, this.messagesBuffered);
        this.messagesBuffered = 0;
    }

    private final void writeBufferChain(BufferChainOutputStream bufferChainOutputStream, boolean z) {
        Iterator it = bufferChainOutputStream.bufferList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((WritableBuffer) it.next()).readableBytes();
        }
        int i2 = this.maxOutboundMessageSize;
        if (i2 >= 0 && i > i2) {
            throw new StatusRuntimeException(Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "message too large %d > %d", Integer.valueOf(i), Integer.valueOf(this.maxOutboundMessageSize))));
        }
        this.headerScratch.clear();
        this.headerScratch.put(z ? (byte) 1 : (byte) 0).putInt(i);
        WritableBufferAllocator writableBufferAllocator = this.bufferAllocator;
        ByteBuffer byteBuffer = this.headerScratch;
        WritableBuffer allocate = writableBufferAllocator.allocate(5);
        allocate.write(byteBuffer.array(), 0, byteBuffer.position());
        if (i == 0) {
            this.buffer = allocate;
            return;
        }
        this.sink.deliverFrame(allocate, false, false, this.messagesBuffered - 1);
        this.messagesBuffered = 1;
        List list = bufferChainOutputStream.bufferList;
        for (int i3 = 0; i3 < list.size() - 1; i3++) {
            this.sink.deliverFrame((WritableBuffer) list.get(i3), false, false, 0);
        }
        this.buffer = (WritableBuffer) list.get(list.size() - 1);
        this.currentMessageWireSize = i;
    }

    private static int writeToOutputStream(InputStream inputStream, OutputStream outputStream) {
        ProtoInputStream protoInputStream = (ProtoInputStream) inputStream;
        MessageLite messageLite = protoInputStream.message;
        if (messageLite != null) {
            int serializedSize = messageLite.getSerializedSize();
            protoInputStream.message.writeTo(outputStream);
            protoInputStream.message = null;
            return serializedSize;
        }
        ByteArrayInputStream byteArrayInputStream = protoInputStream.partial;
        if (byteArrayInputStream == null) {
            return 0;
        }
        ExtensionRegistryLite extensionRegistryLite = ProtoLiteUtils.globalRegistry;
        outputStream.getClass();
        byte[] bArr = new byte[8192];
        long j = 0;
        while (true) {
            int read = byteArrayInputStream.read(bArr);
            if (read == -1) {
                int i = (int) j;
                protoInputStream.partial = null;
                return i;
            }
            outputStream.write(bArr, 0, read);
            j += read;
        }
    }

    @Override // io.grpc.internal.Framer
    public final void close() {
        if (!this.closed) {
            this.closed = true;
            WritableBuffer writableBuffer = this.buffer;
            if (writableBuffer != null && writableBuffer.readableBytes() == 0 && this.buffer != null) {
                this.buffer = null;
            }
            commitToSink(true, true);
        }
    }

    @Override // io.grpc.internal.Framer
    public final void flush() {
        WritableBuffer writableBuffer = this.buffer;
        if (writableBuffer != null && writableBuffer.readableBytes() > 0) {
            commitToSink(false, true);
        }
    }

    @Override // io.grpc.internal.Framer
    public final boolean isClosed() {
        return this.closed;
    }

    @Override // io.grpc.internal.Framer
    public final /* synthetic */ void setCompressor$ar$ds(Compressor compressor) {
        this.compressor = compressor;
    }

    @Override // io.grpc.internal.Framer
    public final void setMaxOutboundMessageSize(int i) {
        boolean z;
        if (this.maxOutboundMessageSize == -1) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "max size already set");
        this.maxOutboundMessageSize = i;
    }

    @Override // io.grpc.internal.Framer
    public final void writePayload(InputStream inputStream) {
        boolean z;
        int writeToOutputStream;
        if (!this.closed) {
            this.messagesBuffered++;
            this.currentMessageSeqNo++;
            this.currentMessageWireSize = 0L;
            this.statsTraceCtx.outboundMessage$ar$ds();
            if (this.compressor != Codec.Identity.NONE) {
                z = true;
            } else {
                z = false;
            }
            try {
                int available = inputStream.available();
                if (available != 0 && z) {
                    BufferChainOutputStream bufferChainOutputStream = new BufferChainOutputStream();
                    OutputStream compress = this.compressor.compress(bufferChainOutputStream);
                    try {
                        writeToOutputStream = writeToOutputStream(inputStream, compress);
                        compress.close();
                        int i = this.maxOutboundMessageSize;
                        if (i >= 0 && writeToOutputStream > i) {
                            throw new StatusRuntimeException(Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "message too large %d > %d", Integer.valueOf(writeToOutputStream), Integer.valueOf(this.maxOutboundMessageSize))));
                        }
                        writeBufferChain(bufferChainOutputStream, true);
                    } catch (Throwable th) {
                        compress.close();
                        throw th;
                    }
                } else if (available != -1) {
                    this.currentMessageWireSize = available;
                    int i2 = this.maxOutboundMessageSize;
                    if (i2 >= 0 && available > i2) {
                        throw new StatusRuntimeException(Status.RESOURCE_EXHAUSTED.withDescription(String.format(Locale.US, "message too large %d > %d", Integer.valueOf(available), Integer.valueOf(this.maxOutboundMessageSize))));
                    }
                    this.headerScratch.clear();
                    this.headerScratch.put((byte) 0).putInt(available);
                    if (this.buffer == null) {
                        this.buffer = this.bufferAllocator.allocate(this.headerScratch.position() + available);
                    }
                    writeRaw(this.headerScratch.array(), 0, this.headerScratch.position());
                    writeToOutputStream = writeToOutputStream(inputStream, this.outputStreamAdapter);
                } else {
                    BufferChainOutputStream bufferChainOutputStream2 = new BufferChainOutputStream();
                    writeToOutputStream = writeToOutputStream(inputStream, bufferChainOutputStream2);
                    writeBufferChain(bufferChainOutputStream2, false);
                }
                if (available != -1 && writeToOutputStream != available) {
                    throw new StatusRuntimeException(Status.INTERNAL.withDescription(String.format("Message length inaccurate %s != %s", Integer.valueOf(writeToOutputStream), Integer.valueOf(available))));
                }
                this.statsTraceCtx.outboundUncompressedSize$ar$ds();
                this.statsTraceCtx.outboundWireSize(this.currentMessageWireSize);
                this.statsTraceCtx.outboundMessageSent$ar$ds();
                return;
            } catch (StatusRuntimeException e) {
                throw e;
            } catch (IOException e2) {
                throw new StatusRuntimeException(Status.INTERNAL.withDescription("Failed to frame message").withCause(e2));
            } catch (RuntimeException e3) {
                throw new StatusRuntimeException(Status.INTERNAL.withDescription("Failed to frame message").withCause(e3));
            }
        }
        throw new IllegalStateException("Framer already closed");
    }

    public final void writeRaw(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            WritableBuffer writableBuffer = this.buffer;
            if (writableBuffer != null && writableBuffer.writableBytes() == 0) {
                commitToSink(false, false);
            }
            if (this.buffer == null) {
                this.buffer = this.bufferAllocator.allocate(i2);
            }
            int min = Math.min(i2, this.buffer.writableBytes());
            this.buffer.write(bArr, i, min);
            i += min;
            i2 -= min;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BufferChainOutputStream extends OutputStream {
        public final List bufferList = new ArrayList();
        private WritableBuffer current;

        public BufferChainOutputStream() {
        }

        @Override // java.io.OutputStream
        public final void write(int i) {
            WritableBuffer writableBuffer = this.current;
            if (writableBuffer != null && writableBuffer.writableBytes() > 0) {
                this.current.write((byte) i);
            } else {
                write(new byte[]{(byte) i}, 0, 1);
            }
        }

        @Override // java.io.OutputStream
        public final void write(byte[] bArr, int i, int i2) {
            if (this.current == null) {
                WritableBuffer allocate = MessageFramer.this.bufferAllocator.allocate(i2);
                this.current = allocate;
                this.bufferList.add(allocate);
            }
            while (i2 > 0) {
                int min = Math.min(i2, this.current.writableBytes());
                if (min == 0) {
                    int readableBytes = this.current.readableBytes();
                    WritableBuffer allocate2 = MessageFramer.this.bufferAllocator.allocate(Math.max(i2, readableBytes + readableBytes));
                    this.current = allocate2;
                    this.bufferList.add(allocate2);
                } else {
                    this.current.write(bArr, i, min);
                    i += min;
                    i2 -= min;
                }
            }
        }
    }
}
