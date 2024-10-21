package com.google.android.libraries.performance.primes.metrics.trace;

import android.os.SystemClock;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import j$.util.DesugarCollections;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpanEvent implements Closeable {
    public volatile List children;
    long endMs;
    final int eventNameType$ar$edu;
    String spanName;
    final int spanType$ar$edu;
    public final long startMs;
    final long threadId;
    private static final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = new SpannableUtils$NonCopyableTextSpan();
    static final SpanEvent EMPTY_SPAN = new SpanEvent("", 1, SystemClock.elapsedRealtime(), -1, Thread.currentThread().getId(), 3);

    public SpanEvent(String str, int i, long j, long j2, long j3, int i2) {
        this.spanName = str;
        this.eventNameType$ar$edu = 1;
        this.startMs = j;
        this.endMs = j2;
        this.threadId = j3;
        this.spanType$ar$edu = i2;
        if (i2 == 1) {
            this.children = DesugarCollections.synchronizedList(new ArrayList());
        } else {
            this.children = Collections.emptyList();
        }
    }

    public final void addChildSpans(List list) {
        if (this.children == Collections.EMPTY_LIST) {
            this.children = new ArrayList();
        }
        if (this.children != null) {
            this.children.addAll(list);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Tracer.endSpan(this);
    }

    public final long getDurationMs() {
        long j = this.endMs;
        if (j == -1) {
            return -1L;
        }
        return j - this.startMs;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isThreadRootSpan() {
        if (this.spanType$ar$edu == 1) {
            return true;
        }
        return false;
    }

    public SpanEvent(String str, int i, long j, int i2) {
        this(str, 1, SystemClock.elapsedRealtime(), -1L, j, i2);
    }
}
