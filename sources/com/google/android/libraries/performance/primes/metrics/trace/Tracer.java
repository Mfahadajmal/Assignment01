package com.google.android.libraries.performance.primes.metrics.trace;

import android.os.SystemClock;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.common.flogger.GoogleLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Tracer {
    public static int maxBufferSize = 0;
    public static int minSpanDurationMs = 10;
    public static final AtomicReference traceData = new AtomicReference();

    public static void endSpan(SpanEvent spanEvent) {
        if (!spanEvent.equals(SpanEvent.EMPTY_SPAN)) {
            if (spanEvent.endMs < 0) {
                spanEvent.endMs = SystemClock.elapsedRealtime();
            }
            AtomicReference atomicReference = traceData;
            TraceData traceData2 = (TraceData) atomicReference.get();
            if (traceData2 != null) {
                if (spanEvent != ((SpanEvent) traceData2.activeNodeStack().poll())) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/trace/Tracer", "endSpan", 174, "Tracer.java")).log("Incorrect Span passed. Ignore...");
                    return;
                }
                if (spanEvent.getDurationMs() >= minSpanDurationMs) {
                    if (traceData2.incrementAndGetSpanCount() < maxBufferSize) {
                        SpanEvent spanEvent2 = (SpanEvent) traceData2.activeNodeStack().peek();
                        if (spanEvent2 != null) {
                            if (spanEvent2.children == Collections.EMPTY_LIST) {
                                spanEvent2.children = new ArrayList();
                            }
                            if (spanEvent2.children != null) {
                                spanEvent2.children.add(spanEvent);
                                return;
                            }
                            return;
                        }
                        ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/trace/TraceData", "linkToParent", 108, "TraceData.java")).log("null Parent for Span: %s", spanEvent.spanName);
                        return;
                    }
                    ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/trace/Tracer", "endSpan", 183, "Tracer.java")).log("Dropping trace as max buffer size is hit. Size: %d", traceData2.getSpanCount());
                    atomicReference.set(null);
                }
            }
        }
    }
}
