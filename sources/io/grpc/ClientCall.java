package io.grpc;

import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ClientCall {
    public abstract void cancel(String str, Throwable th);

    public abstract void halfClose();

    public abstract void request(int i);

    public abstract void sendMessage(Object obj);

    public abstract void start$ar$class_merging$ar$class_merging(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent, Metadata metadata);
}
