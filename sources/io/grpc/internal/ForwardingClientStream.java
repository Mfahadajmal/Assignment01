package io.grpc.internal;

import com.google.common.base.MoreObjects$ToStringHelper;
import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.Attributes;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.DecompressorRegistry;
import io.grpc.Status;
import java.io.InputStream;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ForwardingClientStream implements ClientStream {
    @Override // io.grpc.internal.ClientStream
    public final void appendTimeoutInsight(InsightBuilder insightBuilder) {
        delegate().appendTimeoutInsight(insightBuilder);
    }

    @Override // io.grpc.internal.ClientStream
    public final void cancel(Status status) {
        delegate().cancel(status);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ClientStream delegate();

    @Override // io.grpc.internal.Stream
    public final void flush() {
        delegate().flush();
    }

    @Override // io.grpc.internal.ClientStream
    public final Attributes getAttributes() {
        throw null;
    }

    @Override // io.grpc.internal.ClientStream
    public final void halfClose() {
        delegate().halfClose();
    }

    @Override // io.grpc.internal.Stream
    public final boolean isReady() {
        return delegate().isReady();
    }

    @Override // io.grpc.internal.Stream
    public final void optimizeForDirectExecutor() {
        delegate().optimizeForDirectExecutor();
    }

    @Override // io.grpc.internal.Stream
    public final void request(int i) {
        delegate().request(i);
    }

    @Override // io.grpc.internal.Stream
    public final void setCompressor(Compressor compressor) {
        delegate().setCompressor(compressor);
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDeadline(Deadline deadline) {
        delegate().setDeadline(deadline);
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
        delegate().setDecompressorRegistry(decompressorRegistry);
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxInboundMessageSize(int i) {
        delegate().setMaxInboundMessageSize(i);
    }

    @Override // io.grpc.internal.ClientStream
    public final void setMaxOutboundMessageSize(int i) {
        delegate().setMaxOutboundMessageSize(i);
    }

    @Override // io.grpc.internal.ClientStream
    public void start(ClientStreamListener clientStreamListener) {
        throw null;
    }

    public final String toString() {
        MoreObjects$ToStringHelper stringHelper = ContextDataProvider.toStringHelper(this);
        stringHelper.addHolder$ar$ds("delegate", delegate());
        return stringHelper.toString();
    }

    @Override // io.grpc.internal.Stream
    public final void writeMessage(InputStream inputStream) {
        delegate().writeMessage(inputStream);
    }
}
