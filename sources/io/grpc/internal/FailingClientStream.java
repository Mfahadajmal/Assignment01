package io.grpc.internal;

import com.google.common.flogger.context.ContextDataProvider;
import io.grpc.ClientStreamTracer;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FailingClientStream extends NoopClientStream {
    private final Status error;
    private final ClientStreamListener.RpcProgress rpcProgress;
    private boolean started;
    private final ClientStreamTracer[] tracers;

    public FailingClientStream(Status status, ClientStreamListener.RpcProgress rpcProgress, ClientStreamTracer[] clientStreamTracerArr) {
        ContextDataProvider.checkArgument(!status.isOk(), (Object) "error must not be OK");
        this.error = status;
        this.rpcProgress = rpcProgress;
        this.tracers = clientStreamTracerArr;
    }

    @Override // io.grpc.internal.NoopClientStream, io.grpc.internal.ClientStream
    public final void appendTimeoutInsight(InsightBuilder insightBuilder) {
        insightBuilder.appendKeyValue$ar$ds("error", this.error);
        insightBuilder.appendKeyValue$ar$ds("progress", this.rpcProgress);
    }

    @Override // io.grpc.internal.NoopClientStream, io.grpc.internal.ClientStream
    public final void start(ClientStreamListener clientStreamListener) {
        ContextDataProvider.checkState(!this.started, "already started");
        this.started = true;
        int i = 0;
        while (true) {
            ClientStreamTracer[] clientStreamTracerArr = this.tracers;
            if (i < clientStreamTracerArr.length) {
                clientStreamTracerArr[i].streamClosed$ar$ds();
                i++;
            } else {
                clientStreamListener.closed(this.error, this.rpcProgress, new Metadata());
                return;
            }
        }
    }

    public FailingClientStream(Status status, ClientStreamTracer[] clientStreamTracerArr) {
        this(status, ClientStreamListener.RpcProgress.PROCESSED, clientStreamTracerArr);
    }
}
