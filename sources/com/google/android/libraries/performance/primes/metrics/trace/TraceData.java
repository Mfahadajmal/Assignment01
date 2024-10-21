package com.google.android.libraries.performance.primes.metrics.trace;

import android.os.Looper;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.common.flogger.GoogleLogger;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TraceData {
    public final AtomicInteger numOfSpans = new AtomicInteger(0);
    public final Map parentSpanToThreadData = new ConcurrentHashMap();
    private final ThreadLocal activeNode = new ThreadLocal() { // from class: com.google.android.libraries.performance.primes.metrics.trace.TraceData.1
        @Override // java.lang.ThreadLocal
        protected final /* bridge */ /* synthetic */ Object initialValue() {
            String concat;
            long id = Thread.currentThread().getId();
            if (Looper.myLooper() == Looper.getMainLooper()) {
                concat = "UI Thread";
            } else {
                concat = "Thread: ".concat(String.valueOf(Thread.currentThread().getName()));
            }
            SpanEvent spanEvent = new SpanEvent(concat, 1, id, 1);
            ArrayDeque arrayDeque = new ArrayDeque();
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/trace/TraceData$1", "initialValue", 62, "TraceData.java")).log("Instantiate thread-data, thread:%d name:%s", id, spanEvent.spanName);
            arrayDeque.push(spanEvent);
            TraceData.this.numOfSpans.incrementAndGet();
            TraceData.this.parentSpanToThreadData.put(spanEvent, arrayDeque);
            return new WeakReference(arrayDeque);
        }
    };
    public final List timerSpans = new ArrayList();
    public final SpanEvent rootSpan = new SpanEvent("", 1, Thread.currentThread().getId(), 2);

    public final ArrayDeque activeNodeStack() {
        return (ArrayDeque) ((WeakReference) this.activeNode.get()).get();
    }

    public final int getSpanCount() {
        return this.numOfSpans.get();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int incrementAndGetSpanCount() {
        return this.numOfSpans.incrementAndGet();
    }
}
