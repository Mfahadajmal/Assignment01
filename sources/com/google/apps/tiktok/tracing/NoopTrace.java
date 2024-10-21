package com.google.apps.tiktok.tracing;

import com.google.apps.tiktok.tracing.SpanExtras;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NoopTrace extends ExtraTrackingTrace {
    public static final UUID ROOT_NOOP_TRACE_ID = UUID.randomUUID();

    private NoopTrace(NoopTrace noopTrace, String str, SpanExtras spanExtras) {
        super(str, noopTrace, spanExtras);
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final Trace createChildTrace$ar$ds(String str, SpanExtras spanExtras) {
        return new NoopTrace(this, str, spanExtras);
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final SpanExtras getMetadata() {
        return SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS;
    }

    public NoopTrace(UUID uuid, SpanExtras spanExtras) {
        super("Missing Trace", uuid, spanExtras);
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final void setEndTime$ar$ds() {
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final void setKind$ar$ds() {
    }
}
