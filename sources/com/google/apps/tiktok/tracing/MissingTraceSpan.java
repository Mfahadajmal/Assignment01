package com.google.apps.tiktok.tracing;

import com.google.apps.tiktok.tracing.ErrorTrace;
import com.google.apps.tiktok.tracing.SpanExtras;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MissingTraceSpan extends ExtraTrackingTrace implements ErrorTrace {
    public final Exception exception;
    private final boolean logged;

    public MissingTraceSpan(String str, ErrorTrace errorTrace, SpanExtras spanExtras, boolean z) {
        super("<missing root>:".concat(String.valueOf(str)), errorTrace, SpanExtras.copyCombine(spanExtras, SpanExtras.SpanExtrasImpl.NO_TRACING_EXTRAS));
        this.exception = errorTrace.getException();
        this.logged = z;
    }

    @Override // com.google.apps.tiktok.tracing.ErrorTrace
    public final Trace createChildTrace(String str, SpanExtras spanExtras, boolean z) {
        if (z && !this.logged) {
            Tracer.checkTrace$ar$ds$c243405c_0(true);
        }
        boolean z2 = true;
        if ((!z || this.logged) && !this.logged) {
            z2 = false;
        }
        return new MissingTraceSpan(str, this, spanExtras, z2);
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final Trace createChildTrace$ar$ds(String str, SpanExtras spanExtras) {
        return createChildTrace(str, spanExtras, true);
    }

    @Override // com.google.apps.tiktok.tracing.ErrorTrace
    public final Exception getException() {
        return this.exception;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final SpanExtras getMetadata() {
        return SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS;
    }

    public MissingTraceSpan(String str, SpanExtras spanExtras, boolean z) {
        super("<missing root>:".concat(String.valueOf(str)), InsecureUuidGenerator.instance.nextUuid(), SpanExtras.copyCombine(spanExtras, SpanExtras.SpanExtrasImpl.NO_TRACING_EXTRAS));
        if (TraceCheckingFlag.isEnabled()) {
            this.exception = new ErrorTrace.MissingTraceException();
        } else {
            this.exception = MissingRootTrace.DISABLED_EXCEPTION;
        }
        this.logged = z;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final void setEndTime$ar$ds() {
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final void setKind$ar$ds() {
    }
}
