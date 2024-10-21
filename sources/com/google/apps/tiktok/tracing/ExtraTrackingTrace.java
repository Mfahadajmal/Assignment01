package com.google.apps.tiktok.tracing;

import com.google.common.flogger.context.ContextDataProvider;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class ExtraTrackingTrace extends AbstractTrace {
    private final SpanExtras extras;

    public ExtraTrackingTrace(String str, Trace trace, SpanExtras spanExtras) {
        super(str, trace);
        ContextDataProvider.checkArgument(spanExtras.isFrozen);
        this.extras = spanExtras;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final SpanExtras getExtras() {
        return SpanExtras.copyCombine(this.extras, getMetadata());
    }

    public ExtraTrackingTrace(String str, UUID uuid, SpanExtras spanExtras) {
        super(str, uuid);
        ContextDataProvider.checkArgument(spanExtras.isFrozen);
        this.extras = spanExtras;
    }
}
