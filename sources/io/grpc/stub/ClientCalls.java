package io.grpc.stub;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClientCalls {
    static final CallOptions.Key STUB_TYPE_OPTION;
    private static final Logger logger = Logger.getLogger(ClientCalls.class.getName());

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class GrpcFuture extends AbstractFuture {
        public final ClientCall call;

        public GrpcFuture(ClientCall clientCall) {
            this.call = clientCall;
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        protected final void interruptTask() {
            this.call.cancel("GrpcFuture was cancelled", null);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.common.util.concurrent.AbstractFuture
        public final String pendingToString() {
            MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
            stringHelper.addHolder$ar$ds("clientCall", this.call);
            return stringHelper.toString();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public final boolean set(Object obj) {
            return super.set(obj);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public final boolean setException(Throwable th) {
            return super.setException(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class StartableListener extends OnDeviceFaceMeshCreateLogEvent {
        private boolean isValueReceived;
        public final GrpcFuture responseFuture;
        private Object value;

        public StartableListener() {
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
        public final void onClose(Status status, Metadata metadata) {
            if (status.isOk()) {
                if (!this.isValueReceived) {
                    this.responseFuture.setException(new StatusRuntimeException(Status.INTERNAL.withDescription("No value received for unary call"), null));
                }
                this.responseFuture.set(this.value);
                return;
            }
            this.responseFuture.setException(new StatusRuntimeException(status, null));
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
        public final void onMessage(Object obj) {
            if (!this.isValueReceived) {
                this.value = obj;
                this.isValueReceived = true;
                return;
            }
            throw new StatusRuntimeException(Status.INTERNAL.withDescription("More than one value received for unary call"));
        }

        public StartableListener(GrpcFuture grpcFuture) {
            this();
            this.isValueReceived = false;
            this.responseFuture = grpcFuture;
        }

        @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
        public final void onHeaders(Metadata metadata) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum StubType {
        BLOCKING,
        FUTURE,
        ASYNC
    }

    static {
        if (!ContextDataProvider.stringIsNullOrEmpty(System.getenv("GRPC_CLIENT_CALL_REJECT_RUNNABLE"))) {
            Boolean.parseBoolean(System.getenv("GRPC_CLIENT_CALL_REJECT_RUNNABLE"));
        }
        STUB_TYPE_OPTION = new CallOptions.Key("internal-stub-type", null);
    }

    private ClientCalls() {
    }

    private static void asyncUnaryRequestCall(ClientCall clientCall, Object obj, StartableListener startableListener) {
        clientCall.start$ar$class_merging$ar$class_merging(startableListener, new Metadata());
        startableListener.responseFuture.call.request(2);
        try {
            clientCall.sendMessage(obj);
            clientCall.halfClose();
        } catch (Error | RuntimeException e) {
            throw cancelThrow(clientCall, e);
        }
    }

    private static RuntimeException cancelThrow(ClientCall clientCall, Throwable th) {
        try {
            clientCall.cancel(null, th);
        } catch (Error | RuntimeException e) {
            logger.logp(Level.SEVERE, "io.grpc.stub.ClientCalls", "cancelThrow", "RuntimeException encountered while closing call", e);
        }
        if (!(th instanceof RuntimeException)) {
            if (th instanceof Error) {
                throw ((Error) th);
            }
            throw new AssertionError(th);
        }
        throw ((RuntimeException) th);
    }

    public static ListenableFuture futureUnaryCall(ClientCall clientCall, Object obj) {
        GrpcFuture grpcFuture = new GrpcFuture(clientCall);
        asyncUnaryRequestCall(clientCall, obj, new StartableListener(grpcFuture));
        return grpcFuture;
    }
}
