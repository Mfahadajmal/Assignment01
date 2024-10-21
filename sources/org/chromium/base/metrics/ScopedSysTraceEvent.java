package org.chromium.base.metrics;

import android.os.Trace;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScopedSysTraceEvent implements AutoCloseable {
    public ScopedSysTraceEvent(String str) {
        Trace.beginSection(str);
    }

    @Override // java.lang.AutoCloseable
    public final void close() {
        Trace.endSection();
    }
}
