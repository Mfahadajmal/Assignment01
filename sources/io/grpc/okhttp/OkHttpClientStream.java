package io.grpc.okhttp;

import _COROUTINE._BOUNDARY;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.io.BaseEncoding;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.InternalMetadata;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.cronet.CronetClientStream;
import io.grpc.cronet.CronetWritableBuffer;
import io.grpc.internal.AbstractClientStream;
import io.grpc.internal.AbstractStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.Http2ClientStreamTransportState;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportFrameUtil;
import io.grpc.internal.TransportTracer;
import io.grpc.internal.WritableBuffer;
import io.grpc.okhttp.OutboundFlowController;
import io.grpc.okhttp.internal.framed.ErrorCode;
import io.grpc.okhttp.internal.framed.Header;
import io.perfmark.Impl;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.SSLSocketFactory;
import okio.Buffer;
import okio.ByteString;
import org.chromium.net.BidirectionalStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class OkHttpClientStream extends AbstractClientStream {
    public static final Buffer EMPTY_BUFFER = new Buffer();
    private final Attributes attributes;
    public final String authority;
    public final MethodDescriptor method;
    private final Sink sink;
    public final TransportState state;
    public final StatsTraceContext statsTraceCtx;
    public boolean useGet;
    public final String userAgent;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Sink implements AbstractClientStream.Sink {
        final /* synthetic */ AbstractClientStream OkHttpClientStream$Sink$ar$this$0;
        private final /* synthetic */ int switching_field;

        public Sink(AbstractClientStream abstractClientStream, int i) {
            this.switching_field = i;
            this.OkHttpClientStream$Sink$ar$this$0 = abstractClientStream;
        }

        @Override // io.grpc.internal.AbstractClientStream.Sink
        public final void cancel(Status status) {
            if (this.switching_field != 0) {
                int i = CronetClientStream.TransportState.CronetClientStream$TransportState$ar$NoOp;
                synchronized (((CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0).state.lock) {
                    CronetClientStream.TransportState transportState = ((CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0).state;
                    if (transportState.cancelSent) {
                        return;
                    }
                    transportState.cancelSent = true;
                    transportState.cancelReason = status;
                    Iterator it = transportState.pendingData.iterator();
                    while (it.hasNext()) {
                        ((CronetClientStream.PendingData) it.next()).buffer.clear();
                    }
                    transportState.pendingData.clear();
                    AbstractClientStream abstractClientStream = this.OkHttpClientStream$Sink$ar$this$0;
                    BidirectionalStream bidirectionalStream = ((CronetClientStream) abstractClientStream).stream;
                    if (bidirectionalStream != null) {
                        bidirectionalStream.cancel();
                    } else {
                        ((CronetClientStream) abstractClientStream).transport.finishStream((CronetClientStream) abstractClientStream, status);
                    }
                    return;
                }
            }
            int i2 = PerfMark.PerfMark$ar$NoOp;
            TransportState transportState2 = ((OkHttpClientStream) this.OkHttpClientStream$Sink$ar$this$0).state;
            int i3 = TransportState.OkHttpClientStream$TransportState$ar$NoOp;
            synchronized (transportState2.lock) {
                ((OkHttpClientStream) this.OkHttpClientStream$Sink$ar$this$0).state.cancel(status, true, null);
            }
        }

        @Override // io.grpc.internal.AbstractClientStream.Sink
        public final void writeFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i) {
            Buffer buffer;
            boolean z3;
            ByteBuffer byteBuffer;
            if (this.switching_field != 0) {
                int i2 = CronetClientStream.TransportState.CronetClientStream$TransportState$ar$NoOp;
                synchronized (((CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0).state.lock) {
                    if (((CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0).state.cancelSent) {
                        return;
                    }
                    if (writableBuffer != null) {
                        byteBuffer = ((CronetWritableBuffer) writableBuffer).buffer;
                        byteBuffer.flip();
                    } else {
                        byteBuffer = CronetClientStream.EMPTY_BUFFER;
                    }
                    this.OkHttpClientStream$Sink$ar$this$0.onSendingBytes(byteBuffer.remaining());
                    AbstractClientStream abstractClientStream = this.OkHttpClientStream$Sink$ar$this$0;
                    CronetClientStream.TransportState transportState = ((CronetClientStream) abstractClientStream).state;
                    if (!transportState.streamReady) {
                        transportState.pendingData.add(new CronetClientStream.PendingData(byteBuffer, z, z2));
                    } else {
                        ((CronetClientStream) abstractClientStream).streamWrite(byteBuffer, z, z2);
                    }
                    return;
                }
            }
            int i3 = PerfMark.PerfMark$ar$NoOp;
            if (writableBuffer == null) {
                buffer = OkHttpClientStream.EMPTY_BUFFER;
            } else {
                buffer = ((OkHttpWritableBuffer) writableBuffer).buffer;
                int i4 = (int) buffer.size;
                if (i4 > 0) {
                    this.OkHttpClientStream$Sink$ar$this$0.onSendingBytes(i4);
                }
            }
            TransportState transportState2 = ((OkHttpClientStream) this.OkHttpClientStream$Sink$ar$this$0).state;
            int i5 = TransportState.OkHttpClientStream$TransportState$ar$NoOp;
            synchronized (transportState2.lock) {
                TransportState transportState3 = ((OkHttpClientStream) this.OkHttpClientStream$Sink$ar$this$0).state;
                if (!transportState3.cancelSent) {
                    if (transportState3.canStart) {
                        transportState3.pendingData.write(buffer, (int) buffer.size);
                        transportState3.pendingDataHasEndOfStream |= z;
                        transportState3.flushPendingData |= z2;
                    } else {
                        if (transportState3.id != -1) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        ContextDataProvider.checkState(z3, "streamId should be set");
                        transportState3.outboundFlow.data(z, transportState3.outboundFlowState, buffer, z2);
                    }
                }
                TransportTracer transportTracer = this.OkHttpClientStream$Sink$ar$this$0.transportTracer;
                if (i != 0) {
                    transportTracer.messagesSent += i;
                    transportTracer.timeProvider.currentTimeNanos();
                }
            }
        }

        @Override // io.grpc.internal.AbstractClientStream.Sink
        public final void writeHeaders(Metadata metadata, byte[] bArr) {
            if (this.switching_field != 0) {
                ((CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0).startCallback.run();
                CronetClientStream cronetClientStream = (CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0;
                if (cronetClientStream.streamFactory == null) {
                    return;
                }
                CronetClientStream.BidirectionalStreamCallback bidirectionalStreamCallback = new CronetClientStream.BidirectionalStreamCallback();
                String str = cronetClientStream.url;
                if (bArr != null) {
                    str = str + "?" + BaseEncoding.BASE64.encode(bArr);
                }
                CronetClientStream cronetClientStream2 = (CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0;
                BidirectionalStream.Builder newBidirectionalStreamBuilder = cronetClientStream2.streamFactory.newBidirectionalStreamBuilder(str, bidirectionalStreamCallback, cronetClientStream2.executor);
                if (bArr != null) {
                    newBidirectionalStreamBuilder.setHttpMethod("GET");
                }
                if (((CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0).delayRequestHeader) {
                    newBidirectionalStreamBuilder.delayRequestHeadersUntilFirstFlush(true);
                }
                CronetClientStream cronetClientStream3 = (CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0;
                Object obj = cronetClientStream3.annotation;
                if (obj != null || cronetClientStream3.annotations != null) {
                    if (obj != null) {
                        newBidirectionalStreamBuilder.addRequestAnnotation(obj);
                    }
                    Collection collection = ((CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0).annotations;
                    if (collection != null) {
                        Iterator it = collection.iterator();
                        while (it.hasNext()) {
                            newBidirectionalStreamBuilder.addRequestAnnotation(it.next());
                        }
                    }
                }
                CronetClientStream cronetClientStream4 = (CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0;
                newBidirectionalStreamBuilder.addHeader(GrpcUtil.USER_AGENT_KEY.name, cronetClientStream4.userAgent);
                newBidirectionalStreamBuilder.addHeader(GrpcUtil.CONTENT_TYPE_KEY.name, "application/grpc");
                newBidirectionalStreamBuilder.addHeader("te", "trailers");
                byte[][] http2Headers = TransportFrameUtil.toHttp2Headers(cronetClientStream4.headers);
                for (int i = 0; i < http2Headers.length; i += 2) {
                    String str2 = new String(http2Headers[i], StandardCharsets.UTF_8);
                    if (!GrpcUtil.CONTENT_TYPE_KEY.name.equalsIgnoreCase(str2) && !GrpcUtil.USER_AGENT_KEY.name.equalsIgnoreCase(str2) && !GrpcUtil.TE_HEADER.name.equalsIgnoreCase(str2)) {
                        newBidirectionalStreamBuilder.addHeader(str2, new String(http2Headers[i + 1], StandardCharsets.UTF_8));
                    }
                }
                ((CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0).stream = newBidirectionalStreamBuilder.build();
                ((CronetClientStream) this.OkHttpClientStream$Sink$ar$this$0).stream.start();
                return;
            }
            int i2 = PerfMark.PerfMark$ar$NoOp;
            String _BOUNDARY$ar$MethodOutlining$dc56d17a_0 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(((OkHttpClientStream) this.OkHttpClientStream$Sink$ar$this$0).method.fullMethodName, "/");
            if (bArr != null) {
                ((OkHttpClientStream) this.OkHttpClientStream$Sink$ar$this$0).useGet = true;
                _BOUNDARY$ar$MethodOutlining$dc56d17a_0 = _BOUNDARY$ar$MethodOutlining$dc56d17a_0 + "?" + BaseEncoding.BASE64.encode(bArr);
            }
            TransportState transportState = ((OkHttpClientStream) this.OkHttpClientStream$Sink$ar$this$0).state;
            int i3 = TransportState.OkHttpClientStream$TransportState$ar$NoOp;
            synchronized (transportState.lock) {
                ((OkHttpClientStream) this.OkHttpClientStream$Sink$ar$this$0).state.streamReady(metadata, _BOUNDARY$ar$MethodOutlining$dc56d17a_0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class TransportState extends Http2ClientStreamTransportState {
        public static final /* synthetic */ int OkHttpClientStream$TransportState$ar$NoOp = 0;
        public boolean canStart;
        public boolean cancelSent;
        public boolean flushPendingData;
        public final ExceptionHandlingFrameWriter frameWriter;
        public int id;
        private final int initialWindowSize;
        public final Object lock;
        public final OutboundFlowController outboundFlow;
        public OutboundFlowController.StreamState outboundFlowState;
        public final Buffer pendingData;
        public boolean pendingDataHasEndOfStream;
        private int processedWindow;
        public List requestHeaders;
        public final Tag tag;
        private final OkHttpClientTransport transport;
        private int window;

        public TransportState(int i, StatsTraceContext statsTraceContext, Object obj, ExceptionHandlingFrameWriter exceptionHandlingFrameWriter, OutboundFlowController outboundFlowController, OkHttpClientTransport okHttpClientTransport, int i2, CallOptions callOptions) {
            super(i, statsTraceContext, OkHttpClientStream.this.transportTracer, callOptions);
            this.pendingData = new Buffer();
            this.pendingDataHasEndOfStream = false;
            this.flushPendingData = false;
            this.cancelSent = false;
            this.canStart = true;
            this.id = -1;
            this.lock = obj;
            this.frameWriter = exceptionHandlingFrameWriter;
            this.outboundFlow = outboundFlowController;
            this.transport = okHttpClientTransport;
            this.window = i2;
            this.processedWindow = i2;
            this.initialWindowSize = i2;
            this.tag = Impl.NO_TAG;
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public final void bytesRead(int i) {
            int i2 = this.processedWindow - i;
            this.processedWindow = i2;
            int i3 = this.initialWindowSize;
            if (i2 <= i3 * 0.5f) {
                int i4 = i3 - i2;
                this.window += i4;
                this.processedWindow = i2 + i4;
                this.frameWriter.windowUpdate(this.id, i4);
            }
        }

        public final void cancel(Status status, boolean z, Metadata metadata) {
            if (this.cancelSent) {
                return;
            }
            this.cancelSent = true;
            if (this.canStart) {
                OkHttpClientTransport okHttpClientTransport = this.transport;
                OkHttpClientStream okHttpClientStream = OkHttpClientStream.this;
                okHttpClientTransport.pendingStreams.remove(okHttpClientStream);
                okHttpClientTransport.maybeClearInUse(okHttpClientStream);
                this.requestHeaders = null;
                this.pendingData.clear();
                this.canStart = false;
                if (metadata == null) {
                    metadata = new Metadata();
                }
                transportReportStatus(status, true, metadata);
                return;
            }
            this.transport.finishStream(this.id, status, ClientStreamListener.RpcProgress.PROCESSED, z, ErrorCode.CANCEL, metadata);
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public final void deframeFailed(Throwable th) {
            cancel(Status.fromThrowable(th), true, new Metadata());
        }

        @Override // io.grpc.internal.AbstractStream.TransportState, io.grpc.internal.MessageDeframer.Listener
        public final void deframerClosed(boolean z) {
            if (!this.outboundClosed) {
                this.transport.finishStream(this.id, null, ClientStreamListener.RpcProgress.PROCESSED, false, ErrorCode.CANCEL, null);
            } else {
                this.transport.finishStream(this.id, null, ClientStreamListener.RpcProgress.PROCESSED, false, null, null);
            }
            super.deframerClosed(z);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final OutboundFlowController.StreamState getOutboundFlowState() {
            OutboundFlowController.StreamState streamState;
            synchronized (this.lock) {
                streamState = this.outboundFlowState;
            }
            return streamState;
        }

        @Override // io.grpc.internal.Http2ClientStreamTransportState
        protected final void http2ProcessingFailed(Status status, boolean z, Metadata metadata) {
            cancel(status, false, metadata);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.internal.AbstractStream.TransportState
        public final void onStreamAllocated() {
            super.onStreamAllocated();
            TransportTracer transportTracer = this.transportTracer;
            transportTracer.streamsStarted++;
            transportTracer.timeProvider.currentTimeNanos();
        }

        @Override // io.grpc.internal.ApplicationThreadDeframerListener$TransportExecutor
        public final void runOnTransportThread(Runnable runnable) {
            synchronized (this.lock) {
                runnable.run();
            }
        }

        public final void streamReady(Metadata metadata, String str) {
            OkHttpClientStream okHttpClientStream = OkHttpClientStream.this;
            boolean z = okHttpClientStream.useGet;
            SSLSocketFactory sSLSocketFactory = this.transport.sslSocketFactory;
            Header header = Headers.HTTPS_SCHEME_HEADER;
            metadata.getClass();
            String str2 = okHttpClientStream.authority;
            str2.getClass();
            metadata.discardAll(GrpcUtil.CONTENT_TYPE_KEY);
            metadata.discardAll(GrpcUtil.TE_HEADER);
            metadata.discardAll(GrpcUtil.USER_AGENT_KEY);
            Charset charset = InternalMetadata.US_ASCII;
            ArrayList arrayList = new ArrayList(metadata.size + 7);
            if (sSLSocketFactory == null) {
                arrayList.add(Headers.HTTP_SCHEME_HEADER);
            } else {
                arrayList.add(Headers.HTTPS_SCHEME_HEADER);
            }
            if (z) {
                arrayList.add(Headers.METHOD_GET_HEADER);
            } else {
                arrayList.add(Headers.METHOD_HEADER);
            }
            String str3 = okHttpClientStream.userAgent;
            arrayList.add(new Header(Header.TARGET_AUTHORITY, str2));
            arrayList.add(new Header(Header.TARGET_PATH, str));
            arrayList.add(new Header(GrpcUtil.USER_AGENT_KEY.name, str3));
            arrayList.add(Headers.CONTENT_TYPE_HEADER);
            arrayList.add(Headers.TE_HEADER);
            byte[][] http2Headers = TransportFrameUtil.toHttp2Headers(metadata);
            for (int i = 0; i < http2Headers.length; i += 2) {
                ByteString of = ByteString.of(http2Headers[i]);
                if (of.getSize$third_party_java_src_okio_okio_jvm() != 0 && of.internalGet$third_party_java_src_okio_okio_jvm(0) != 58) {
                    arrayList.add(new Header(of, ByteString.of(http2Headers[i + 1])));
                }
            }
            this.requestHeaders = arrayList;
            OkHttpClientTransport okHttpClientTransport = this.transport;
            OkHttpClientStream okHttpClientStream2 = OkHttpClientStream.this;
            Status status = okHttpClientTransport.goAwayStatus;
            if (status != null) {
                okHttpClientStream2.state.transportReportStatus(status, ClientStreamListener.RpcProgress.MISCARRIED, true, new Metadata());
            } else if (okHttpClientTransport.streams.size() >= okHttpClientTransport.maxConcurrentStreams) {
                okHttpClientTransport.pendingStreams.add(okHttpClientStream2);
                okHttpClientTransport.setInUse(okHttpClientStream2);
            } else {
                okHttpClientTransport.startStream(okHttpClientStream2);
            }
        }

        public final void transportDataReceived(Buffer buffer, boolean z, int i) {
            int i2 = this.window - (((int) buffer.size) + i);
            this.window = i2;
            this.processedWindow -= i;
            if (i2 < 0) {
                this.frameWriter.rstStream(this.id, ErrorCode.FLOW_CONTROL_ERROR);
                this.transport.finishStream(this.id, Status.INTERNAL.withDescription("Received data size exceeded our receiving window size"), ClientStreamListener.RpcProgress.PROCESSED, false, null, null);
            } else {
                super.transportDataReceived(new OkHttpReadableBuffer(buffer), z);
            }
        }
    }

    public OkHttpClientStream(MethodDescriptor methodDescriptor, Metadata metadata, ExceptionHandlingFrameWriter exceptionHandlingFrameWriter, OkHttpClientTransport okHttpClientTransport, OutboundFlowController outboundFlowController, Object obj, int i, int i2, String str, String str2, StatsTraceContext statsTraceContext, TransportTracer transportTracer, CallOptions callOptions) {
        super(new OkHttpWritableBufferAllocator(0), statsTraceContext, transportTracer, metadata, callOptions, false);
        this.sink = new Sink(this, 0);
        this.useGet = false;
        this.statsTraceCtx = statsTraceContext;
        this.method = methodDescriptor;
        this.authority = str;
        this.userAgent = str2;
        this.attributes = okHttpClientTransport.attributes;
        this.state = new TransportState(i, statsTraceContext, obj, exceptionHandlingFrameWriter, outboundFlowController, okHttpClientTransport, i2, callOptions);
    }

    @Override // io.grpc.internal.AbstractClientStream
    protected final /* synthetic */ AbstractClientStream.Sink abstractClientStreamSink() {
        return this.sink;
    }

    @Override // io.grpc.internal.ClientStream
    public final Attributes getAttributes() {
        return this.attributes;
    }

    public final MethodDescriptor.MethodType getType() {
        return this.method.type;
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
