package com.google.apps.tiktok.tracing;

import com.google.apps.tiktok.tracing.SpanExtras;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SkipTrace extends ExtraTrackingTrace {
    public static final SkipTrace INSTANCE = new SkipTrace();
    private static final UUID ROOT_SKIP_TRACE_ID = UUID.randomUUID();

    public SkipTrace() {
        super("<skip trace>", ROOT_SKIP_TRACE_ID, SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS);
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final Trace createChildTrace$ar$ds(String str, SpanExtras spanExtras) {
        throw new IllegalStateException("Can't create child trace for no trace!");
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final SpanExtras getMetadata() {
        return SpanExtras.SpanExtrasImpl.EMPTY_EXTRAS;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final void setEndTime$ar$ds() {
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final void setKind$ar$ds() {
    }
}
