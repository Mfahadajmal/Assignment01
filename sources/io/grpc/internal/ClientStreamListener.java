package io.grpc.internal;

import io.grpc.Metadata;
import io.grpc.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ClientStreamListener extends StreamListener {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum RpcProgress {
        PROCESSED,
        REFUSED,
        DROPPED,
        MISCARRIED
    }

    void closed(Status status, RpcProgress rpcProgress, Metadata metadata);

    void headersRead(Metadata metadata);
}
