package io.grpc.internal;

import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.Decompressor;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.perfmark.TaskCloseable;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.Closeable;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MessageDeframer implements Closeable, Deframer {
    private boolean compressedFlag;
    private Decompressor decompressor;
    private TaskCloseable fullStreamDecompressor$ar$class_merging;
    private int inboundBodyWireSize;
    public Listener listener;
    private int maxInboundMessageSize;
    private CompositeReadableBuffer nextFrame;
    private long pendingDeliveries;
    private final StatsTraceContext statsTraceCtx;
    private final TransportTracer transportTracer;
    private int state$ar$edu$1c138f31_0 = 1;
    private int requiredLength = 5;
    private CompositeReadableBuffer unprocessed = new CompositeReadableBuffer();
    private boolean inDelivery = false;
    private int currentMessageSeqNo = -1;
    private boolean closeWhenComplete = false;
    private volatile boolean stopDelivery = false;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Listener {
        void bytesRead(int i);

        void deframeFailed(Throwable th);

        void deframerClosed(boolean z);

        void messagesAvailable$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent);
    }

    public MessageDeframer(Listener listener, Decompressor decompressor, int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
        this.listener = listener;
        this.decompressor = decompressor;
        this.maxInboundMessageSize = i;
        this.statsTraceCtx = statsTraceContext;
        this.transportTracer = transportTracer;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x012b, code lost:
    
        throw new io.grpc.StatusRuntimeException(io.grpc.Status.RESOURCE_EXHAUSTED.withDescription(java.lang.String.format(java.util.Locale.US, "gRPC message exceeds maximum size %d: %d", java.lang.Integer.valueOf(r8.maxInboundMessageSize), java.lang.Integer.valueOf(r8.requiredLength))));
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x013d, code lost:
    
        if (r8.closeWhenComplete == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0143, code lost:
    
        if (isStalled() == false) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x0145, code lost:
    
        close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x014a, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void deliver() {
        /*
            Method dump skipped, instructions count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.grpc.internal.MessageDeframer.deliver():void");
    }

    private final boolean isStalled() {
        if (this.unprocessed.readableBytes == 0) {
            return true;
        }
        return false;
    }

    private final boolean readRequiredBytes() {
        int i = 0;
        try {
            if (this.nextFrame == null) {
                this.nextFrame = new CompositeReadableBuffer();
            }
            int i2 = 0;
            while (true) {
                try {
                    int i3 = this.requiredLength - this.nextFrame.readableBytes;
                    if (i3 > 0) {
                        int i4 = this.unprocessed.readableBytes;
                        if (i4 == 0) {
                            if (i2 > 0) {
                                this.listener.bytesRead(i2);
                                if (this.state$ar$edu$1c138f31_0 == 2) {
                                    this.statsTraceCtx.inboundWireSize(i2);
                                    this.inboundBodyWireSize += i2;
                                }
                            }
                            return false;
                        }
                        int min = Math.min(i3, i4);
                        i2 += min;
                        this.nextFrame.addBuffer(this.unprocessed.readBytes(min));
                    } else {
                        if (i2 > 0) {
                            this.listener.bytesRead(i2);
                            if (this.state$ar$edu$1c138f31_0 == 2) {
                                this.statsTraceCtx.inboundWireSize(i2);
                                this.inboundBodyWireSize += i2;
                                return true;
                            }
                            return true;
                        }
                        return true;
                    }
                } catch (Throwable th) {
                    int i5 = i2;
                    th = th;
                    i = i5;
                    if (i > 0) {
                        this.listener.bytesRead(i);
                        if (this.state$ar$edu$1c138f31_0 == 2) {
                            this.statsTraceCtx.inboundWireSize(i);
                            this.inboundBodyWireSize += i;
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, io.grpc.internal.Deframer
    public final void close() {
        if (isClosed()) {
            return;
        }
        CompositeReadableBuffer compositeReadableBuffer = this.nextFrame;
        boolean z = false;
        if (compositeReadableBuffer != null && compositeReadableBuffer.readableBytes > 0) {
            z = true;
        }
        try {
            CompositeReadableBuffer compositeReadableBuffer2 = this.unprocessed;
            if (compositeReadableBuffer2 != null) {
                compositeReadableBuffer2.close();
            }
            CompositeReadableBuffer compositeReadableBuffer3 = this.nextFrame;
            if (compositeReadableBuffer3 != null) {
                compositeReadableBuffer3.close();
            }
            this.fullStreamDecompressor$ar$class_merging = null;
            this.unprocessed = null;
            this.nextFrame = null;
            this.listener.deframerClosed(z);
        } catch (Throwable th) {
            this.fullStreamDecompressor$ar$class_merging = null;
            this.unprocessed = null;
            this.nextFrame = null;
            throw th;
        }
    }

    @Override // io.grpc.internal.Deframer
    public final void closeWhenComplete() {
        if (isClosed()) {
            return;
        }
        if (isStalled()) {
            close();
        } else {
            this.closeWhenComplete = true;
        }
    }

    @Override // io.grpc.internal.Deframer
    public final void deframe(ReadableBuffer readableBuffer) {
        boolean z = true;
        try {
            if (!isClosed() && !this.closeWhenComplete) {
                this.unprocessed.addBuffer(readableBuffer);
                try {
                    deliver();
                    return;
                } catch (Throwable th) {
                    th = th;
                    z = false;
                    if (z) {
                        readableBuffer.close();
                    }
                    throw th;
                }
            }
            readableBuffer.close();
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final boolean isClosed() {
        if (this.unprocessed == null) {
            return true;
        }
        return false;
    }

    @Override // io.grpc.internal.Deframer
    public final void request(int i) {
        ContextDataProvider.checkArgument(true, (Object) "numMessages must be > 0");
        if (isClosed()) {
            return;
        }
        this.pendingDeliveries += i;
        deliver();
    }

    @Override // io.grpc.internal.Deframer
    public final void setDecompressor(Decompressor decompressor) {
        ContextDataProvider.checkState(true, "Already set full stream decompressor");
        this.decompressor = decompressor;
    }

    @Override // io.grpc.internal.Deframer
    public final void setMaxInboundMessageSize(int i) {
        this.maxInboundMessageSize = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SizeEnforcingInputStream extends FilterInputStream implements InputStreamRetargetInterface {
        private long count;
        private long mark;
        private long maxCount;
        private final int maxMessageSize;
        private final StatsTraceContext statsTraceCtx;

        public SizeEnforcingInputStream(InputStream inputStream, int i, StatsTraceContext statsTraceContext) {
            super(inputStream);
            this.mark = -1L;
            this.maxMessageSize = i;
            this.statsTraceCtx = statsTraceContext;
        }

        private final void reportCount() {
            if (this.count > this.maxCount) {
                this.statsTraceCtx.inboundUncompressedSize$ar$ds();
                this.maxCount = this.count;
            }
        }

        private final void verifySize() {
            long j = this.count;
            int i = this.maxMessageSize;
            if (j <= i) {
                return;
            }
            throw new StatusRuntimeException(Status.RESOURCE_EXHAUSTED.withDescription("Decompressed gRPC message exceeds maximum size " + i));
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public final synchronized void mark(int i) {
            this.in.mark(i);
            this.mark = this.count;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public final int read() {
            int read = this.in.read();
            if (read != -1) {
                this.count++;
            }
            verifySize();
            reportCount();
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public final synchronized void reset() {
            if (this.in.markSupported()) {
                if (this.mark != -1) {
                    this.in.reset();
                    this.count = this.mark;
                } else {
                    throw new IOException("Mark not set");
                }
            } else {
                throw new IOException("Mark not supported");
            }
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public final long skip(long j) {
            long skip = this.in.skip(j);
            this.count += skip;
            verifySize();
            reportCount();
            return skip;
        }

        @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
        public final /* synthetic */ long transferTo(OutputStream outputStream) {
            return DesugarInputStream.transferTo(this, outputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public final int read(byte[] bArr, int i, int i2) {
            int read = this.in.read(bArr, i, i2);
            if (read != -1) {
                this.count += read;
            }
            verifySize();
            reportCount();
            return read;
        }
    }
}
