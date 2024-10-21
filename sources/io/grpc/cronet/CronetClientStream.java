package io.grpc.cronet;

import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.cronet.CronetChannelBuilder;
import io.grpc.internal.AbstractClientStream;
import io.grpc.internal.AbstractStream;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.Http2ClientStreamTransportState;
import io.grpc.internal.ReadableBuffers;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportFrameUtil;
import io.grpc.internal.TransportTracer;
import io.grpc.okhttp.OkHttpClientStream;
import io.grpc.okhttp.OkHttpWritableBufferAllocator;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import org.chromium.net.BidirectionalStream;
import org.chromium.net.CronetException;
import org.chromium.net.UrlResponseInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetClientStream extends AbstractClientStream {
    public final Object annotation;
    public final Collection annotations;
    public final boolean delayRequestHeader;
    public final Executor executor;
    public final Metadata headers;
    final boolean idempotent;
    private final OkHttpClientStream.Sink sink$ar$class_merging;
    public final Runnable startCallback;
    public final TransportState state;
    public final StatsTraceContext statsTraceCtx;
    public BidirectionalStream stream;
    public CronetChannelBuilder.StreamBuilderFactory streamFactory;
    public final CronetClientTransport transport;
    public final String url;
    public final String userAgent;
    public static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocateDirect(0);

    @Deprecated
    static final CallOptions.Key CRONET_ANNOTATION_KEY = new CallOptions.Key("cronet-annotation", null);
    public static final CallOptions.Key CRONET_ANNOTATIONS_KEY = new CallOptions.Key("cronet-annotations", null);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BidirectionalStreamCallback extends BidirectionalStream.Callback {
        private List trailerList;

        public BidirectionalStreamCallback() {
        }

        private final void reportHeaders(List list, boolean z) {
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                arrayList.add((String) entry.getKey());
                arrayList.add((String) entry.getValue());
            }
            byte[][] bArr = new byte[arrayList.size()];
            for (int i = 0; i < arrayList.size(); i += 2) {
                bArr[i] = ((String) arrayList.get(i)).getBytes(StandardCharsets.UTF_8);
                int i2 = i + 1;
                bArr[i2] = ((String) arrayList.get(i2)).getBytes(StandardCharsets.UTF_8);
            }
            byte[][] rawSerializedHeaders = TransportFrameUtil.toRawSerializedHeaders(bArr);
            Charset charset = InternalMetadata.US_ASCII;
            Metadata metadata = new Metadata(rawSerializedHeaders);
            CronetClientStream cronetClientStream = CronetClientStream.this;
            int i3 = TransportState.CronetClientStream$TransportState$ar$NoOp;
            synchronized (cronetClientStream.state.lock) {
                TransportState transportState = CronetClientStream.this.state;
                if (z) {
                    transportState.transportTrailersReceived(metadata);
                } else {
                    transportState.transportHeadersReceived(metadata);
                }
            }
        }

        private static final Status toGrpcStatus$ar$ds(UrlResponseInfo urlResponseInfo) {
            return GrpcUtil.httpStatusToGrpcStatus(urlResponseInfo.getHttpStatusCode());
        }

        @Override // org.chromium.net.BidirectionalStream.Callback
        public final void onCanceled(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo) {
            Status status;
            int i = TransportState.CronetClientStream$TransportState$ar$NoOp;
            synchronized (CronetClientStream.this.state.lock) {
                status = CronetClientStream.this.state.cancelReason;
                if (status == null) {
                    if (urlResponseInfo != null) {
                        status = toGrpcStatus$ar$ds(urlResponseInfo);
                    } else {
                        status = Status.CANCELLED.withDescription("stream cancelled without reason");
                    }
                }
            }
            CronetClientStream.this.finishStream(status);
        }

        @Override // org.chromium.net.BidirectionalStream.Callback
        public final void onFailed(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo, CronetException cronetException) {
            CronetClientStream.this.finishStream(Status.UNAVAILABLE.withCause(cronetException));
        }

        @Override // org.chromium.net.BidirectionalStream.Callback
        public final void onReadCompleted(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer, boolean z) {
            List list;
            byteBuffer.flip();
            int i = TransportState.CronetClientStream$TransportState$ar$NoOp;
            synchronized (CronetClientStream.this.state.lock) {
                CronetClientStream.this.state.readClosed = z;
                if (byteBuffer.remaining() != 0) {
                    TransportState.m242$$Nest$mtransportDataReceived$ar$ds(CronetClientStream.this.state, byteBuffer);
                }
            }
            if (z && (list = this.trailerList) != null) {
                reportHeaders(list, true);
            }
        }

        @Override // org.chromium.net.BidirectionalStream.Callback
        public final void onResponseHeadersReceived(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo) {
            reportHeaders(urlResponseInfo.getAllHeadersAsList(), false);
            bidirectionalStream.read(ByteBuffer.allocateDirect(4096));
        }

        @Override // org.chromium.net.BidirectionalStream.Callback
        public final void onResponseTrailersReceived(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo, UrlResponseInfo.HeaderBlock headerBlock) {
            boolean z;
            List<Map.Entry<String, String>> asList = headerBlock.getAsList();
            this.trailerList = asList;
            int i = TransportState.CronetClientStream$TransportState$ar$NoOp;
            synchronized (CronetClientStream.this.state.lock) {
                z = CronetClientStream.this.state.readClosed;
            }
            if (z) {
                reportHeaders(asList, true);
            }
        }

        @Override // org.chromium.net.BidirectionalStream.Callback
        public final void onStreamReady(BidirectionalStream bidirectionalStream) {
            int i = TransportState.CronetClientStream$TransportState$ar$NoOp;
            synchronized (CronetClientStream.this.state.lock) {
                CronetClientStream.this.state.onStreamAllocated();
                TransportState transportState = CronetClientStream.this.state;
                transportState.streamReady = true;
                transportState.writeAllPendingData();
            }
        }

        @Override // org.chromium.net.BidirectionalStream.Callback
        public final void onSucceeded(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo) {
            boolean z;
            int i = TransportState.CronetClientStream$TransportState$ar$NoOp;
            synchronized (CronetClientStream.this.state.lock) {
                z = false;
                if (this.trailerList != null && CronetClientStream.this.state.readClosed) {
                    z = true;
                }
            }
            if (!z) {
                List list = this.trailerList;
                if (list != null) {
                    reportHeaders(list, true);
                } else if (urlResponseInfo != null) {
                    reportHeaders(urlResponseInfo.getAllHeadersAsList(), true);
                } else {
                    throw new AssertionError("No response header or trailer");
                }
            }
            CronetClientStream.this.finishStream(toGrpcStatus$ar$ds(urlResponseInfo));
        }

        @Override // org.chromium.net.BidirectionalStream.Callback
        public final void onWriteCompleted(BidirectionalStream bidirectionalStream, UrlResponseInfo urlResponseInfo, ByteBuffer byteBuffer, boolean z) {
            int i = TransportState.CronetClientStream$TransportState$ar$NoOp;
            synchronized (CronetClientStream.this.state.lock) {
                CronetClientStream cronetClientStream = CronetClientStream.this;
                TransportState transportState = cronetClientStream.state;
                if (!transportState.firstWriteComplete) {
                    transportState.firstWriteComplete = true;
                    cronetClientStream.statsTraceCtx.clientOutboundHeaders();
                }
                CronetClientStream.this.state.onSentBytes(byteBuffer.position());
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PendingData {
        public final ByteBuffer buffer;
        final boolean endOfStream;
        final boolean flush;

        public PendingData(ByteBuffer byteBuffer, boolean z, boolean z2) {
            this.buffer = byteBuffer;
            this.endOfStream = z;
            this.flush = z2;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TransportState extends Http2ClientStreamTransportState {
        public static final /* synthetic */ int CronetClientStream$TransportState$ar$NoOp = 0;
        private int bytesPendingProcess;
        public Status cancelReason;
        public boolean cancelSent;
        public boolean firstWriteComplete;
        public final Object lock;
        public final Collection pendingData;
        public boolean readClosed;
        public boolean streamReady;

        /* renamed from: -$$Nest$mtransportDataReceived$ar$ds, reason: not valid java name */
        static /* bridge */ /* synthetic */ void m242$$Nest$mtransportDataReceived$ar$ds(TransportState transportState, ByteBuffer byteBuffer) {
            transportState.bytesPendingProcess += byteBuffer.remaining();
            super.transportDataReceived(new ReadableBuffers.ByteReadableBufferWrapper(byteBuffer), false);
        }

        public TransportState(int i, StatsTraceContext statsTraceContext, Object obj, TransportTracer transportTracer, CallOptions callOptions) {
            super(i, statsTraceContext, transportTracer, callOptions);
            this.pendingData = new ArrayList();
            this.cancelSent = false;
            this.lock = obj;
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public final void bytesRead(int i) {
            BidirectionalStream bidirectionalStream = CronetClientStream.this.stream;
            bidirectionalStream.getClass();
            int i2 = this.bytesPendingProcess - i;
            this.bytesPendingProcess = i2;
            if (i2 == 0 && !this.readClosed) {
                bidirectionalStream.read(ByteBuffer.allocateDirect(4096));
            }
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public final void deframeFailed(Throwable th) {
            http2ProcessingFailed(Status.fromThrowable(th), true, new Metadata());
        }

        @Override // io.grpc.internal.Http2ClientStreamTransportState
        protected final void http2ProcessingFailed(Status status, boolean z, Metadata metadata) {
            BidirectionalStream bidirectionalStream = CronetClientStream.this.stream;
            bidirectionalStream.getClass();
            bidirectionalStream.cancel();
            transportReportStatus(status, z, metadata);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.internal.AbstractStream.TransportState
        public final void onStreamAllocated() {
            super.onStreamAllocated();
        }

        @Override // io.grpc.internal.ApplicationThreadDeframerListener$TransportExecutor
        public final void runOnTransportThread(Runnable runnable) {
            synchronized (this.lock) {
                runnable.run();
            }
        }

        public final void writeAllPendingData() {
            for (PendingData pendingData : this.pendingData) {
                CronetClientStream.this.streamWrite(pendingData.buffer, pendingData.endOfStream, pendingData.flush);
            }
            this.pendingData.clear();
        }
    }

    public CronetClientStream(String str, String str2, Executor executor, Metadata metadata, CronetClientTransport cronetClientTransport, Runnable runnable, Object obj, int i, MethodDescriptor methodDescriptor, StatsTraceContext statsTraceContext, CallOptions callOptions, TransportTracer transportTracer) {
        super(new OkHttpWritableBufferAllocator(1), statsTraceContext, transportTracer, metadata, callOptions, false);
        this.sink$ar$class_merging = new OkHttpClientStream.Sink(this, 1);
        this.url = str;
        this.userAgent = str2;
        this.statsTraceCtx = statsTraceContext;
        this.executor = executor;
        this.headers = metadata;
        this.transport = cronetClientTransport;
        this.startCallback = runnable;
        this.idempotent = false;
        this.delayRequestHeader = methodDescriptor.type == MethodDescriptor.MethodType.UNARY;
        this.annotation = callOptions.getOption(CRONET_ANNOTATION_KEY);
        this.annotations = (Collection) callOptions.getOption(CRONET_ANNOTATIONS_KEY);
        this.state = new TransportState(i, statsTraceContext, obj, transportTracer, callOptions);
        optimizeForDirectExecutor();
    }

    @Override // io.grpc.internal.AbstractClientStream
    protected final /* synthetic */ AbstractClientStream.Sink abstractClientStreamSink() {
        return this.sink$ar$class_merging;
    }

    public final void finishStream(Status status) {
        this.transport.finishStream(this, status);
    }

    @Override // io.grpc.internal.ClientStream
    public final Attributes getAttributes() {
        return Attributes.EMPTY;
    }

    public final void streamWrite(ByteBuffer byteBuffer, boolean z, boolean z2) {
        BidirectionalStream bidirectionalStream = this.stream;
        if (bidirectionalStream != null) {
            bidirectionalStream.write(byteBuffer, z);
            if (z2) {
                this.stream.flush();
            }
        }
    }

    @Override // io.grpc.internal.AbstractClientStream, io.grpc.internal.AbstractStream
    protected final /* synthetic */ AbstractStream.TransportState transportState() {
        return this.state;
    }

    @Override // io.grpc.internal.AbstractClientStream
    protected final /* synthetic */ AbstractStream.TransportState transportState$ar$class_merging() {
        return this.state;
    }
}
