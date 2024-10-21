package io.grpc.internal;

import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.CallOptions;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Grpc;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.AbstractStream;
import io.grpc.internal.MessageFramer;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractClientStream extends AbstractStream implements ClientStream, MessageFramer.Sink {
    public static final Logger log = Logger.getLogger(AbstractClientStream.class.getName());
    private volatile boolean cancelled;
    private final Framer framer;
    private Metadata headers;
    public final boolean shouldBeCountedForInUse;
    public final TransportTracer transportTracer;
    private final boolean useGet;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Sink {
        void cancel(Status status);

        void writeFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i);

        void writeHeaders(Metadata metadata, byte[] bArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractClientStream(WritableBufferAllocator writableBufferAllocator, StatsTraceContext statsTraceContext, TransportTracer transportTracer, Metadata metadata, CallOptions callOptions, boolean z) {
        transportTracer.getClass();
        this.transportTracer = transportTracer;
        this.shouldBeCountedForInUse = !Boolean.TRUE.equals(callOptions.getOption(GrpcUtil.CALL_OPTIONS_RPC_OWNED_BY_BALANCER));
        this.useGet = false;
        this.framer = new MessageFramer(this, writableBufferAllocator, statsTraceContext);
        this.headers = metadata;
    }

    protected abstract Sink abstractClientStreamSink();

    @Override // io.grpc.internal.ClientStream
    public final void appendTimeoutInsight(InsightBuilder insightBuilder) {
        insightBuilder.appendKeyValue$ar$ds("remote_addr", getAttributes().get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR));
    }

    @Override // io.grpc.internal.ClientStream
    public final void cancel(Status status) {
        ContextDataProvider.checkArgument(!status.isOk(), (Object) "Should not cancel with OK status");
        this.cancelled = true;
        abstractClientStreamSink().cancel(status);
    }

    @Override // io.grpc.internal.MessageFramer.Sink
    public final void deliverFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i) {
        boolean z3 = true;
        if (writableBuffer == null && !z) {
            z3 = false;
        }
        ContextDataProvider.checkArgument(z3, (Object) "null frame before EOS");
        abstractClientStreamSink().writeFrame(writableBuffer, z, z2, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractStream
    public final Framer framer() {
        return this.framer;
    }

    @Override // io.grpc.internal.ClientStream
    public final void halfClose() {
        if (!transportState$ar$class_merging().outboundClosed) {
            transportState$ar$class_merging().outboundClosed = true;
            framer().close();
        }
    }

    @Override // io.grpc.internal.AbstractStream, io.grpc.internal.Stream
    public final boolean isReady() {
        if (super.isReady() && !this.cancelled) {
            return true;
        }
        return false;
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDeadline(Deadline deadline) {
        this.headers.discardAll(GrpcUtil.TIMEOUT_KEY);
        this.headers.put(GrpcUtil.TIMEOUT_KEY, Long.valueOf(Math.max(0L, deadline.timeRemaining(TimeUnit.NANOSECONDS))));
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
        boolean z;
        AbstractStream.TransportState transportState$ar$class_merging = transportState$ar$class_merging();
        if (transportState$ar$class_merging.listener == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "Already called start");
        decompressorRegistry.getClass();
        transportState$ar$class_merging.decompressorRegistry = decompressorRegistry;
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxInboundMessageSize(int i) {
        transportState$ar$class_merging().deframer.setMaxInboundMessageSize(i);
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxOutboundMessageSize(int i) {
        this.framer.setMaxOutboundMessageSize(i);
    }

    @Override // io.grpc.internal.ClientStream
    public final void start(ClientStreamListener clientStreamListener) {
        boolean z;
        AbstractStream.TransportState transportState$ar$class_merging = transportState$ar$class_merging();
        if (transportState$ar$class_merging.listener == null) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkState(z, "Already called setListener");
        transportState$ar$class_merging.listener = clientStreamListener;
        abstractClientStreamSink().writeHeaders(this.headers, null);
        this.headers = null;
    }

    @Override // io.grpc.internal.AbstractStream
    protected /* bridge */ /* synthetic */ AbstractStream.TransportState transportState() {
        throw null;
    }

    protected abstract AbstractStream.TransportState transportState$ar$class_merging();
}
