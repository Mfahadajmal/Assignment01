package io.grpc.internal;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.Attributes;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ManagedClientTransport;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ForwardingConnectionClientTransport implements ConnectionClientTransport {
    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ConnectionClientTransport delegate();

    @Override // io.grpc.internal.ConnectionClientTransport
    public final Attributes getAttributes() {
        return delegate().getAttributes();
    }

    @Override // io.grpc.InternalWithLogId
    public final InternalLogId getLogId() {
        return delegate().getLogId();
    }

    @Override // io.grpc.internal.ClientTransport
    public ClientStream newStream(MethodDescriptor methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
        throw null;
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public void shutdown(Status status) {
        delegate().shutdown(status);
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public void shutdownNow(Status status) {
        throw null;
    }

    @Override // io.grpc.internal.ManagedClientTransport
    public final Runnable start(ManagedClientTransport.Listener listener) {
        return delegate().start(listener);
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("delegate", delegate());
        return stringHelper.toString();
    }
}
