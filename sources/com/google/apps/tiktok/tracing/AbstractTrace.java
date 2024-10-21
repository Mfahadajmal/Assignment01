package com.google.apps.tiktok.tracing;

import com.google.apps.tiktok.tracing.Tracer;
import java.util.UUID;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class AbstractTrace implements Trace {
    private final String name;
    private final Trace parent;
    private final UUID uuid;

    public AbstractTrace(String str, Trace trace) {
        this.name = str;
        this.parent = trace;
        this.uuid = trace.getRootTraceId();
    }

    @Override // com.google.apps.tiktok.tracing.TraceCloseable, java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Tracer.ThreadState currentThreadState = Tracer.getCurrentThreadState();
        Trace trace = currentThreadState.trace;
        if (trace != null) {
            if (this == trace) {
                Tracer.set(currentThreadState, trace.getParent());
                return;
            }
            throw new Tracer.EndedWrongTraceException("Tried to end span " + getName() + ", but that span is not the current span. The current span is " + trace.getName() + ".");
        }
        throw new Tracer.EndedNoTraceException("Tried to end [" + getName() + "], but no trace was active. This is caused by mismatched or missing calls to beginSpan.");
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final String getName() {
        return this.name;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final Trace getParent() {
        return this.parent;
    }

    @Override // com.google.apps.tiktok.tracing.Trace
    public final UUID getRootTraceId() {
        return this.uuid;
    }

    /* JADX WARN: Code restructure failed: missing block: B:119:0x01ff, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00a4, code lost:
    
        r15 = r1.activeNode.children;
        r4 = java.lang.Integer.valueOf(r13);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00b0, code lost:
    
        if (r15.containsKey(r4) != false) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00b2, code lost:
    
        r1.activeNode.children.put(r4, new com.google.apps.tiktok.tracing.SuffixTree.Node(r7, 1073741824));
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00be, code lost:
    
        if (r14 == null) goto L120;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00c0, code lost:
    
        r14.suffixLink = r1.activeNode;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x00cd, code lost:
    
        if (r14 == null) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x00cf, code lost:
    
        r14.suffixLink = r1.activeNode;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x00d3, code lost:
    
        r1.activeEdge = r7;
        r1.activeLength++;
        r1.walkDown();
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x01ce, code lost:
    
        if (r1.regionEquals(r12, r13, r14, (r14 + r13) - r12) != false) goto L64;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01ee A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0253  */
    /* JADX WARN: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01ed  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String toString() {
        /*
            Method dump skipped, instructions count: 733
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.apps.tiktok.tracing.AbstractTrace.toString():java.lang.String");
    }

    public AbstractTrace(String str, UUID uuid) {
        this.name = str;
        this.parent = null;
        this.uuid = uuid;
    }
}
