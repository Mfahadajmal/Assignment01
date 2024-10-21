package io.grpc;

import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.internal.ClientStream;
import io.grpc.internal.ClientTransport;
import io.grpc.internal.DelayedStream;
import io.grpc.internal.FailingClientStream;
import io.grpc.internal.GrpcUtil;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CallCredentials$MetadataApplier {
    private final CallOptions callOptions;
    private final Context ctx;
    public DelayedStream delayedStream;
    boolean finalized;
    private final RetryingNameResolver.ResolutionResultListener listener$ar$class_merging$96a3f82d_0$ar$class_merging$ar$class_merging;
    public final Object lock;
    private final MethodDescriptor method;
    private final Metadata origHeaders;
    public ClientStream returnedStream;
    private final ClientStreamTracer[] tracers;
    private final ClientTransport transport;

    public CallCredentials$MetadataApplier() {
    }

    private final void finalizeWith(ClientStream clientStream) {
        boolean z;
        boolean z2 = true;
        ContextDataProvider.checkState(!this.finalized, "already finalized");
        this.finalized = true;
        synchronized (this.lock) {
            if (this.returnedStream == null) {
                this.returnedStream = clientStream;
                z = true;
            } else {
                z = false;
            }
        }
        if (z) {
            this.listener$ar$class_merging$96a3f82d_0$ar$class_merging$ar$class_merging.onComplete();
            return;
        }
        if (this.delayedStream == null) {
            z2 = false;
        }
        ContextDataProvider.checkState(z2, "delayedStream is null");
        Runnable stream = this.delayedStream.setStream(clientStream);
        if (stream != null) {
            stream.run();
        }
        this.listener$ar$class_merging$96a3f82d_0$ar$class_merging$ar$class_merging.onComplete();
    }

    public final void apply(Metadata metadata) {
        ContextDataProvider.checkState(!this.finalized, "apply() or fail() already called");
        metadata.getClass();
        this.origHeaders.merge(metadata);
        Context attach = this.ctx.attach();
        try {
            ClientStream newStream = this.transport.newStream(this.method, this.origHeaders, this.callOptions, this.tracers);
            this.ctx.detach(attach);
            finalizeWith(newStream);
        } catch (Throwable th) {
            this.ctx.detach(attach);
            throw th;
        }
    }

    public final void fail(Status status) {
        ContextDataProvider.checkArgument(!status.isOk(), (Object) "Cannot fail with OK status");
        ContextDataProvider.checkState(!this.finalized, "apply() or fail() already called");
        finalizeWith(new FailingClientStream(GrpcUtil.replaceInappropriateControlPlaneStatus(status), this.tracers));
    }

    public CallCredentials$MetadataApplier(ClientTransport clientTransport, MethodDescriptor methodDescriptor, Metadata metadata, CallOptions callOptions, RetryingNameResolver.ResolutionResultListener resolutionResultListener, ClientStreamTracer[] clientStreamTracerArr) {
        this();
        this.lock = new Object();
        this.transport = clientTransport;
        this.method = methodDescriptor;
        this.origHeaders = metadata;
        this.callOptions = callOptions;
        this.ctx = Context.current();
        this.listener$ar$class_merging$96a3f82d_0$ar$class_merging$ar$class_merging = resolutionResultListener;
        this.tracers = clientStreamTracerArr;
    }
}
