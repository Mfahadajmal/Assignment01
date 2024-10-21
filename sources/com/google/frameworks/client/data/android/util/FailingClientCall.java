package com.google.frameworks.client.data.android.util;

import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FailingClientCall extends ClientCall {
    private final Status error;

    public FailingClientCall(Status status) {
        status.getClass();
        this.error = status;
    }

    @Override // io.grpc.ClientCall
    public final void start$ar$class_merging$ar$class_merging(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata) {
        onDeviceFaceMeshCreateLogEvent.getClass();
        metadata.getClass();
        onDeviceFaceMeshCreateLogEvent.onClose(this.error, new Metadata());
    }

    @Override // io.grpc.ClientCall
    public final void halfClose() {
    }

    @Override // io.grpc.ClientCall
    public final void request(int i) {
    }

    @Override // io.grpc.ClientCall
    public final void sendMessage(Object obj) {
    }

    @Override // io.grpc.ClientCall
    public final void cancel(String str, Throwable th) {
    }
}
