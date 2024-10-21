package com.google.apps.tiktok.tracing;

import com.google.apps.tiktok.tracing.ErrorTrace;
import com.google.apps.tiktok.tracing.SpanExtras;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class MissingRootTrace extends AbstractTrace implements ErrorTrace {
    static final ErrorTrace.MissingTraceException DISABLED_EXCEPTION = new ErrorTrace.TraceCauseCheckingDisabled();
    public final ErrorTrace.MissingTraceException exception;

    public MissingRootTrace() {
        super("<missing root>", InsecureUuidGenerator.instance.nextUuid());
        ErrorTrace.MissingTraceException missingTraceException;
        if (TraceCheckingFlag.isEnabled()) {
            missingTraceException = new ErrorTrace.MissingTraceException();
        } else {
            missingTraceException = DISABLED_EXCEPTION;
        }
        this.exception = missingTraceException;
    }

    @Override // com.google.apps.tiktok.tracing.ErrorTrace
    public final Trace createChildTrace(String str, SpanExtras spanExtras, boolean z) {
        if (z) {
            Tracer.checkTrace$ar$ds$c243405c_0(true);
        }
        return new MissingTraceSpan(str, this, spanExtras, z);
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final Trace createChildTrace$ar$ds(String str, SpanExtras spanExtras) {
        Tracer.checkTrace$ar$ds$c243405c_0(true);
        return createChildTrace(str, spanExtras, true);
    }

    @Override // com.google.apps.tiktok.tracing.ErrorTrace
    public final /* synthetic */ Exception getException() {
        return this.exception;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final SpanExtras getExtras() {
        return SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final SpanExtras getMetadata() {
        throw null;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final void setEndTime$ar$ds() {
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final void setKind$ar$ds() {
    }
}
