package com.google.mlkit.logging.schema;

import _COROUTINE._BOUNDARY;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SubjectSegmentationOptionalModuleLogEvent {
    public static final void checkParallelism(int i) {
        if (i > 0) {
        } else {
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Expected positive parallelism level, but got "));
        }
    }

    public static final long wo$ar$ds(long j, long j2) {
        return j & (~j2);
    }

    public final long updateHead(long j, int i) {
        return wo$ar$ds(j, 1073741823L) | i;
    }
}
