package com.google.mlkit.logging.schema;

import kotlin.time.DurationUnit;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.atomicfu.TraceBase$None;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceSubjectSegmentationCreateLogEvent {
    public static final AtomicInt atomic(int i) {
        return new AtomicInt(i, TraceBase$None.INSTANCE);
    }

    public static final long convertDurationUnitOverflow(long j, DurationUnit durationUnit, DurationUnit durationUnit2) {
        durationUnit.getClass();
        durationUnit2.getClass();
        return durationUnit2.timeUnit.convert(j, durationUnit.timeUnit);
    }

    public static final AtomicLong atomic(long j) {
        return new AtomicLong(j, TraceBase$None.INSTANCE);
    }

    public static final AtomicRef atomic(Object obj) {
        return new AtomicRef(obj, TraceBase$None.INSTANCE);
    }

    public static final AtomicBoolean atomic(boolean z) {
        return new AtomicBoolean(z, TraceBase$None.INSTANCE);
    }
}
