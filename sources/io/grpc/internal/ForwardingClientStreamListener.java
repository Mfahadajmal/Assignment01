package io.grpc.internal;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ForwardingClientStreamListener implements ClientStreamListener {
    @Override // io.grpc.internal.ClientStreamListener
    public void closed(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ClientStreamListener delegate();

    @Override // io.grpc.internal.ClientStreamListener
    public final void headersRead(Metadata metadata) {
        delegate().headersRead(metadata);
    }

    @Override // io.grpc.internal.StreamListener
    public final void messagesAvailable$ar$class_merging$ar$class_merging(OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent) {
        delegate().messagesAvailable$ar$class_merging$ar$class_merging(onDeviceTextDetectionLoadLogEvent);
    }

    @Override // io.grpc.internal.StreamListener
    public final void onReady() {
        delegate().onReady();
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("delegate", delegate());
        return stringHelper.toString();
    }
}
