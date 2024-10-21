package com.google.apps.tiktok.tracing;

import com.google.android.accessibility.brailleime.BrailleIme$21$$ExternalSyntheticLambda1;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.common.util.concurrent.DirectExecutor;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpanEndSignal implements Runnable, TraceCloseable {
    private boolean attachedToFuture;
    private boolean closed;
    private boolean skipTrace;
    private final boolean startedOnMain = ThreadUtil.isMainThread(null);
    private Trace trace;
    private Trace whileOpenTrace;

    public SpanEndSignal(Trace trace, boolean z) {
        this.skipTrace = false;
        this.trace = trace;
        this.whileOpenTrace = trace;
        this.skipTrace = z;
    }

    private final void endInternal() {
        this.closed = true;
        boolean z = this.startedOnMain;
        Trace trace = this.trace;
        if (z && !this.attachedToFuture) {
            ThreadUtil.isMainThread();
        }
        trace.setEndTime$ar$ds();
        this.trace = null;
    }

    public final void attachToFuture$ar$ds(ListenableFuture listenableFuture) {
        if (!this.closed) {
            if (!this.attachedToFuture) {
                this.attachedToFuture = true;
                this.trace.setKind$ar$ds();
                listenableFuture.addListener(this, DirectExecutor.INSTANCE);
                return;
            }
            throw new IllegalStateException("Signal is already attached to future");
        }
        throw new IllegalStateException("Span was already closed. Did you attach it to a future after calling Tracer.endSpan()?");
    }

    @Override // com.google.apps.tiktok.tracing.TraceCloseable, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Trace trace = this.whileOpenTrace;
        try {
            this.whileOpenTrace = null;
            if (!this.attachedToFuture) {
                if (!this.closed) {
                    endInternal();
                } else {
                    throw new IllegalStateException("Span was already closed!");
                }
            }
            if (trace != null) {
                trace.close();
            }
            if (this.skipTrace) {
                Tracer.set$ar$ds$76df68d1_0(SkipTrace.INSTANCE);
            }
        } catch (Throwable th) {
            if (trace != null) {
                try {
                    trace.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (!this.closed && this.attachedToFuture) {
            endInternal();
        } else {
            ThreadUtil.postOnMainThread(new BrailleIme$21$$ExternalSyntheticLambda1(6));
        }
    }
}
