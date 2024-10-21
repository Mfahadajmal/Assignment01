package com.google.frameworks.client.data.android.internal;

import com.google.apps.tiktok.tracing.SkipTrace;
import com.google.apps.tiktok.tracing.Trace;
import com.google.apps.tiktok.tracing.TraceReference;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.common.android.concurrent.FutureCallbackViewModel$$ExternalSyntheticLambda1;
import com.google.common.util.concurrent.Futures$$ExternalSyntheticLambda1;
import com.google.firebase.components.EventBus$$ExternalSyntheticLambda0;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import io.grpc.Metadata;
import io.grpc.Status;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TracePropagatingClientCallListener extends OnDeviceFaceMeshCreateLogEvent {
    public final OnDeviceFaceMeshCreateLogEvent delegate$ar$class_merging$a40ae667_0$ar$class_merging;
    private final TraceReference traceReference = new TraceReference(Tracer.getOrCreateDebug());

    public TracePropagatingClientCallListener(OnDeviceFaceMeshCreateLogEvent onDeviceFaceMeshCreateLogEvent) {
        this.delegate$ar$class_merging$a40ae667_0$ar$class_merging = onDeviceFaceMeshCreateLogEvent;
    }

    private final void runWithTraceReference(final Runnable runnable) {
        Trace trace = Tracer.get();
        if (trace != null && trace != SkipTrace.INSTANCE) {
            runnable.run();
        } else {
            final Trace trace2 = this.traceReference.trace;
            new Runnable() { // from class: com.google.apps.tiktok.tracing.TracePropagation$propagate$1
                /* JADX WARN: Type inference failed for: r1v0, types: [com.google.apps.tiktok.tracing.Trace, com.google.apps.tiktok.tracing.TraceCloseable] */
                @Override // java.lang.Runnable
                public final void run() {
                    Trace trace3 = Tracer.set(Tracer.getCurrentThreadState(), TraceCloseable.this);
                    try {
                        runnable.run();
                    } finally {
                    }
                }

                public final String toString() {
                    return "propagating=[" + runnable + "]";
                }
            }.run();
        }
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
    public final void onClose(Status status, Metadata metadata) {
        runWithTraceReference(new FutureCallbackViewModel$$ExternalSyntheticLambda1((OnDeviceFaceMeshCreateLogEvent) this, status, metadata, 13));
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
    public final void onHeaders(Metadata metadata) {
        runWithTraceReference(new EventBus$$ExternalSyntheticLambda0(this, metadata, 14, null));
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
    public final void onMessage(Object obj) {
        runWithTraceReference(new EventBus$$ExternalSyntheticLambda0(this, obj, 15, null));
    }

    @Override // com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent
    public final void onReady() {
        runWithTraceReference(new Futures$$ExternalSyntheticLambda1(this.delegate$ar$class_merging$a40ae667_0$ar$class_merging, 5));
    }
}
