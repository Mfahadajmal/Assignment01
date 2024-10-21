package io.grpc;

import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ForwardingClientCall extends PartialForwardingClientCall {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SimpleForwardingClientCall extends ForwardingClientCall {
        public final ClientCall delegate;

        /* JADX INFO: Access modifiers changed from: protected */
        public SimpleForwardingClientCall(ClientCall clientCall) {
            this.delegate = clientCall;
        }

        @Override // io.grpc.ForwardingClientCall, io.grpc.PartialForwardingClientCall
        protected final ClientCall delegate() {
            return this.delegate;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.PartialForwardingClientCall
    public abstract ClientCall delegate();

    @Override // io.grpc.ClientCall
    public void sendMessage(Object obj) {
        delegate().sendMessage(obj);
    }

    @Override // io.grpc.ClientCall
    public void start$ar$class_merging$ar$class_merging(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata) {
        throw null;
    }
}
