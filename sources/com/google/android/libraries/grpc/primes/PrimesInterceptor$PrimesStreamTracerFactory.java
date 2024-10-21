package com.google.android.libraries.grpc.primes;

import com.google.android.libraries.performance.primes.metrics.network.NetworkEvent;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshLoadLogEvent;
import io.grpc.ClientStreamTracer;
import io.grpc.internal.GrpcUtil;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesInterceptor$PrimesStreamTracerFactory extends OnDeviceFaceMeshLoadLogEvent {
    public final Object lock;
    public final NetworkEvent networkEvent;
    public boolean streamClosed;
    public PrimesInterceptor$PrimesStreamTracer tracer;

    public PrimesInterceptor$PrimesStreamTracerFactory(NetworkEvent networkEvent) {
        super(null);
        this.lock = new Object();
        this.streamClosed = false;
        this.networkEvent = networkEvent;
        if (!ContextDataProvider.stringIsNullOrEmpty("application/grpc")) {
            networkEvent.contentType = "application/grpc";
        }
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshLoadLogEvent
    public final ClientStreamTracer newClientStreamTracer$ar$ds() {
        synchronized (this.lock) {
            if (this.tracer != null) {
                return new GrpcUtil.AnonymousClass2();
            }
            PrimesInterceptor$PrimesStreamTracer primesInterceptor$PrimesStreamTracer = new PrimesInterceptor$PrimesStreamTracer(this.networkEvent);
            this.tracer = primesInterceptor$PrimesStreamTracer;
            return primesInterceptor$PrimesStreamTracer;
        }
    }
}
