package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ClientStream extends Stream {
    void appendTimeoutInsight(InsightBuilder insightBuilder);

    void cancel(Status status);

    Attributes getAttributes();

    void halfClose();

    void setDeadline(Deadline deadline);

    void setDecompressorRegistry(DecompressorRegistry decompressorRegistry);

    void setMaxInboundMessageSize(int i);

    void setMaxOutboundMessageSize(int i);

    void start(ClientStreamListener clientStreamListener);
}
