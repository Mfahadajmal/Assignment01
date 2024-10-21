package io.grpc.cronet;

import com.google.common.util.concurrent.Futures$$ExternalSyntheticLambda1;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.Status;
import io.grpc.cronet.CronetChannelBuilder;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ConnectionClientTransport;
import io.grpc.internal.GrpcAttributes;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.ManagedClientTransport;
import io.grpc.internal.StatsTraceContext;
import io.grpc.internal.TransportTracer;
import java.net.InetSocketAddress;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CronetClientTransport implements ConnectionClientTransport {
    private final InetSocketAddress address;
    public Attributes attrs;
    private final String authority;
    public final Executor executor;
    public boolean goAway;
    public Status goAwayStatus;
    public ManagedClientTransport.Listener listener;
    private final InternalLogId logId;
    public final int maxMessageSize;
    public boolean started;
    private boolean startedGoAway;
    private boolean stopped;
    public final CronetChannelBuilder.StreamBuilderFactory streamFactory;
    public final TransportTracer transportTracer;
    public final String userAgent;
    public final Object lock = new Object();
    public final Set streams = Collections.newSetFromMap(new IdentityHashMap());

    /* compiled from: PG */
    /* renamed from: io.grpc.cronet.CronetClientTransport$1StartCallback, reason: invalid class name */
    /* loaded from: classes.dex */
    final class C1StartCallback implements Runnable {
        final CronetClientStream clientStream;
        final /* synthetic */ CronetClientTransport this$0;
        final /* synthetic */ CallOptions val$callOptions;
        final /* synthetic */ Metadata val$headers;
        final /* synthetic */ MethodDescriptor val$method;
        final /* synthetic */ StatsTraceContext val$statsTraceCtx;
        final /* synthetic */ String val$url;

        public C1StartCallback(CronetClientTransport cronetClientTransport, String str, Metadata metadata, MethodDescriptor methodDescriptor, StatsTraceContext statsTraceContext, CallOptions callOptions) {
            this.val$url = str;
            this.val$headers = metadata;
            this.val$method = methodDescriptor;
            this.val$statsTraceCtx = statsTraceContext;
            this.val$callOptions = callOptions;
            this.this$0 = cronetClientTransport;
            this.clientStream = new CronetClientStream(str, cronetClientTransport.userAgent, cronetClientTransport.executor, metadata, cronetClientTransport, this, cronetClientTransport.lock, cronetClientTransport.maxMessageSize, methodDescriptor, statsTraceContext, callOptions, cronetClientTransport.transportTracer);
        }

        @Override // java.lang.Runnable
        public final void run() {
            synchronized (this.this$0.lock) {
                CronetClientTransport cronetClientTransport = this.this$0;
                if (cronetClientTransport.goAway) {
                    this.clientStream.state.transportReportStatus(cronetClientTransport.goAwayStatus, true, new Metadata());
                } else if (cronetClientTransport.started) {
                    CronetClientStream cronetClientStream = this.clientStream;
                    cronetClientTransport.streams.add(cronetClientStream);
                    CronetClientStream.this.streamFactory = cronetClientTransport.streamFactory;
                } else {
                    throw new AssertionError("Transport is not started");
                }
            }
        }
    }

    public CronetClientTransport(CronetChannelBuilder.StreamBuilderFactory streamBuilderFactory, InetSocketAddress inetSocketAddress, String str, String str2, Attributes attributes, Executor executor, int i, TransportTracer transportTracer) {
        inetSocketAddress.getClass();
        this.address = inetSocketAddress;
        this.logId = InternalLogId.allocate(getClass(), inetSocketAddress.toString());
        this.authority = str;
        this.userAgent = GrpcUtil.getGrpcUserAgent("cronet", str2);
        this.maxMessageSize = i;
        this.executor = executor;
        this.streamFactory = streamBuilderFactory;
        this.transportTracer = transportTracer;
        Attributes attributes2 = Attributes.EMPTY;
        Attributes.Builder builder = new Attributes.Builder(Attributes.EMPTY);
        builder.set$ar$ds$d0d6fadb_0(GrpcAttributes.ATTR_SECURITY_LEVEL, SecurityLevel.PRIVACY_AND_INTEGRITY);
        builder.set$ar$ds$d0d6fadb_0(GrpcAttributes.ATTR_CLIENT_EAG_ATTRS, attributes);
        this.attrs = builder.build();
    }

    public final void finishStream(CronetClientStream cronetClientStream, Status status) {
        synchronized (this.lock) {
            if (this.streams.remove(cronetClientStream)) {
                Status.Code code = status.code;
                boolean z = true;
                if (code != Status.Code.CANCELLED && code != Status.Code.DEADLINE_EXCEEDED) {
                    z = false;
                }
                cronetClientStream.state.transportReportStatus(status, z, new Metadata());
                stopIfNecessary();
            }
        }
    }

    @Override // io.grpc.internal.ConnectionClientTransport
    public final Attributes getAttributes() {
        return this.attrs;
    }

    @Override // io.grpc.InternalWithLogId
    public final InternalLogId getLogId() {
        return this.logId;
    }

    @Override // io.grpc.internal.ClientTransport
    public final /* bridge */ /* synthetic */ ClientStream newStream(MethodDescriptor methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
        return new C1StartCallback(this, "https://" + this.authority + "/".concat(methodDescriptor.fullMethodName), metadata, methodDescriptor, StatsTraceContext.newClientContext(clientStreamTracerArr, this.attrs, metadata), callOptions).clientStream;
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final void shutdown(Status status) {
        synchronized (this.lock) {
            if (this.goAway) {
                return;
            }
            synchronized (this.lock) {
                if (this.startedGoAway) {
                    return;
                }
                this.startedGoAway = true;
                this.listener.transportShutdown(status);
                synchronized (this.lock) {
                    this.goAway = true;
                    this.goAwayStatus = status;
                }
                stopIfNecessary();
            }
        }
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final void shutdownNow(Status status) {
        throw null;
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final Runnable start(ManagedClientTransport.Listener listener) {
        this.listener = listener;
        synchronized (this.lock) {
            this.started = true;
        }
        return new Futures$$ExternalSyntheticLambda1(this, 8, null);
    }

    final void stopIfNecessary() {
        synchronized (this.lock) {
            if (this.goAway && !this.stopped && this.streams.isEmpty()) {
                this.stopped = true;
                this.listener.transportTerminated();
            }
        }
    }

    public final String toString() {
        InetSocketAddress inetSocketAddress = this.address;
        return super.toString() + "(" + inetSocketAddress.toString() + ")";
    }
}
