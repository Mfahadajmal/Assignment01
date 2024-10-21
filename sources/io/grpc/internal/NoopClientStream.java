package io.grpc.internal;

import io.grpc.Attributes;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Status;
import java.io.InputStream;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NoopClientStream implements ClientStream {
    public static final NoopClientStream INSTANCE = new NoopClientStream();

    @Override // io.grpc.internal.ClientStream
    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        insightBuilder.append$ar$ds("noop");
    }

    @Override // io.grpc.internal.ClientStream
    public final Attributes getAttributes() {
        throw null;
    }

    @Override // io.grpc.internal.Stream
    public final boolean isReady() {
        return false;
    }

    @Override // io.grpc.internal.Stream
    public final void flush() {
    }

    @Override // io.grpc.internal.ClientStream
    public final void halfClose() {
    }

    @Override // io.grpc.internal.Stream
    public final void optimizeForDirectExecutor() {
    }

    @Override // io.grpc.internal.ClientStream
    public final void cancel(Status status) {
    }

    @Override // io.grpc.internal.Stream
    public final void request(int i) {
    }

    @Override // io.grpc.internal.Stream
    public final void setCompressor(Compressor compressor) {
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDeadline(Deadline deadline) {
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxInboundMessageSize(int i) {
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxOutboundMessageSize(int i) {
    }

    @Override // io.grpc.internal.ClientStream
    public void start(ClientStreamListener clientStreamListener) {
    }

    @Override // io.grpc.internal.Stream
    public final void writeMessage(InputStream inputStream) {
    }
}
