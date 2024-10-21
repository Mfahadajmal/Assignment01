package io.grpc.internal;

import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.CallOptions;
import io.grpc.ClientStreamTracer;
import io.grpc.InternalLogId;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class FailingClientTransport implements ClientTransport {
    final Status error;
    private final ClientStreamListener.RpcProgress rpcProgress;

    public FailingClientTransport(Status status, ClientStreamListener.RpcProgress rpcProgress) {
        ContextDataProvider.checkArgument(!status.isOk(), (Object) "error must not be OK");
        this.error = status;
        this.rpcProgress = rpcProgress;
    }

    @Override // io.grpc.InternalWithLogId
    public final InternalLogId getLogId() {
        throw new UnsupportedOperationException("Not a real transport");
    }

    @Override // io.grpc.internal.ClientTransport
    public final ClientStream newStream(MethodDescriptor methodDescriptor, Metadata metadata, CallOptions callOptions, ClientStreamTracer[] clientStreamTracerArr) {
        return new FailingClientStream(this.error, this.rpcProgress, clientStreamTracerArr);
    }
}
