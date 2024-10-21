package com.google.apps.tiktok.tracing;

import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface Trace extends TraceCloseable {
    Trace createChildTrace$ar$ds(String str, SpanExtras spanExtras);

    SpanExtras getExtras();

    SpanExtras getMetadata();

    String getName();

    Trace getParent();

    UUID getRootTraceId();

    void setEndTime$ar$ds();

    void setKind$ar$ds();
}
