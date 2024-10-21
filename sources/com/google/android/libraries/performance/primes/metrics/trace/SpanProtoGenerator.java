package com.google.android.libraries.performance.primes.metrics.trace;

import com.google.android.libraries.vision.visionkit.pipeline.SchedulerOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import logs.proto.wireless.performance.mobile.PrimesTraceOuterClass$Span;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpanProtoGenerator {
    private long nextId = 1;
    public final SpanEvent rootSpan;

    public SpanProtoGenerator(SpanEvent spanEvent) {
        this.rootSpan = spanEvent;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void traverse(SpanEvent spanEvent, long j, ArrayList arrayList) {
        List list;
        if (spanEvent.children == null) {
            if (spanEvent.children == null) {
                list = Collections.emptyList();
            } else {
                list = spanEvent.children;
            }
        } else {
            list = spanEvent.children;
            spanEvent.children = null;
        }
        if (!spanEvent.isThreadRootSpan() || !list.isEmpty()) {
            SchedulerOptions.Builder builder = (SchedulerOptions.Builder) PrimesTraceOuterClass$Span.DEFAULT_INSTANCE.createBuilder();
            int i = spanEvent.eventNameType$ar$edu;
            String str = spanEvent.spanName;
            builder.copyOnWrite();
            PrimesTraceOuterClass$Span primesTraceOuterClass$Span = (PrimesTraceOuterClass$Span) builder.instance;
            str.getClass();
            primesTraceOuterClass$Span.bitField0_ |= 1;
            primesTraceOuterClass$Span.constantName_ = str;
            long j2 = spanEvent.startMs;
            builder.copyOnWrite();
            PrimesTraceOuterClass$Span primesTraceOuterClass$Span2 = (PrimesTraceOuterClass$Span) builder.instance;
            primesTraceOuterClass$Span2.bitField0_ |= 512;
            primesTraceOuterClass$Span2.startTimeMs_ = j2;
            long durationMs = spanEvent.getDurationMs();
            builder.copyOnWrite();
            PrimesTraceOuterClass$Span primesTraceOuterClass$Span3 = (PrimesTraceOuterClass$Span) builder.instance;
            primesTraceOuterClass$Span3.bitField0_ |= 1024;
            primesTraceOuterClass$Span3.durationMs_ = durationMs;
            long j3 = spanEvent.threadId;
            builder.copyOnWrite();
            PrimesTraceOuterClass$Span primesTraceOuterClass$Span4 = (PrimesTraceOuterClass$Span) builder.instance;
            primesTraceOuterClass$Span4.bitField0_ |= 4096;
            primesTraceOuterClass$Span4.threadId_ = j3;
            long j4 = this.nextId;
            this.nextId = 1 + j4;
            builder.copyOnWrite();
            PrimesTraceOuterClass$Span primesTraceOuterClass$Span5 = (PrimesTraceOuterClass$Span) builder.instance;
            primesTraceOuterClass$Span5.bitField0_ |= 8;
            primesTraceOuterClass$Span5.id_ = j4;
            builder.copyOnWrite();
            PrimesTraceOuterClass$Span primesTraceOuterClass$Span6 = (PrimesTraceOuterClass$Span) builder.instance;
            primesTraceOuterClass$Span6.bitField0_ |= 16;
            primesTraceOuterClass$Span6.parentId_ = j;
            int i2 = spanEvent.spanType$ar$edu - 1;
            if (i2 != 1) {
                if (i2 != 3) {
                    int i3 = PrimesTraceOuterClass$Span.SpanType.NONE$ar$edu$f0a1aee3_0;
                    builder.copyOnWrite();
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span7 = (PrimesTraceOuterClass$Span) builder.instance;
                    int i4 = i3 - 1;
                    if (i3 != 0) {
                        primesTraceOuterClass$Span7.spanType_ = i4;
                        primesTraceOuterClass$Span7.bitField0_ |= 8192;
                    } else {
                        throw null;
                    }
                } else {
                    int i5 = PrimesTraceOuterClass$Span.SpanType.TIMER$ar$edu;
                    builder.copyOnWrite();
                    PrimesTraceOuterClass$Span primesTraceOuterClass$Span8 = (PrimesTraceOuterClass$Span) builder.instance;
                    int i6 = i5 - 1;
                    if (i5 != 0) {
                        primesTraceOuterClass$Span8.spanType_ = i6;
                        primesTraceOuterClass$Span8.bitField0_ |= 8192;
                    } else {
                        throw null;
                    }
                }
            } else {
                int i7 = PrimesTraceOuterClass$Span.SpanType.TRACE$ar$edu;
                builder.copyOnWrite();
                PrimesTraceOuterClass$Span primesTraceOuterClass$Span9 = (PrimesTraceOuterClass$Span) builder.instance;
                int i8 = i7 - 1;
                if (i7 != 0) {
                    primesTraceOuterClass$Span9.spanType_ = i8;
                    primesTraceOuterClass$Span9.bitField0_ |= 8192;
                } else {
                    throw null;
                }
            }
            if (spanEvent.isThreadRootSpan()) {
                long j5 = ((SpanEvent) list.get(list.size() - 1)).endMs - spanEvent.startMs;
                builder.copyOnWrite();
                PrimesTraceOuterClass$Span primesTraceOuterClass$Span10 = (PrimesTraceOuterClass$Span) builder.instance;
                primesTraceOuterClass$Span10.bitField0_ |= 1024;
                primesTraceOuterClass$Span10.durationMs_ = j5;
            }
            arrayList.add((PrimesTraceOuterClass$Span) builder.build());
            Iterator it = list.iterator();
            while (it.hasNext()) {
                traverse((SpanEvent) it.next(), ((PrimesTraceOuterClass$Span) builder.instance).id_, arrayList);
            }
        }
    }
}
