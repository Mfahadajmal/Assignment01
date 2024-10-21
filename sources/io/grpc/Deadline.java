package io.grpc;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshLogEvent;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Deadline implements Comparable {
    private static final long MAX_OFFSET;
    private static final long MIN_OFFSET;
    private static final long NANOS_PER_SECOND;
    public static final OnDeviceFaceMeshLogEvent SYSTEM_TICKER$ar$class_merging$ar$class_merging$ar$class_merging = new OnDeviceFaceMeshLogEvent(null, null);
    public final long deadlineNanos;
    public volatile boolean expired;
    private final OnDeviceFaceMeshLogEvent ticker$ar$class_merging$ar$class_merging;

    static {
        long nanos = TimeUnit.DAYS.toNanos(36500L);
        MAX_OFFSET = nanos;
        MIN_OFFSET = -nanos;
        NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1L);
    }

    public Deadline(OnDeviceFaceMeshLogEvent onDeviceFaceMeshLogEvent, long j, long j2) {
        boolean z;
        this.ticker$ar$class_merging$ar$class_merging = onDeviceFaceMeshLogEvent;
        long min = Math.min(MAX_OFFSET, Math.max(MIN_OFFSET, j2));
        this.deadlineNanos = j + min;
        if (min <= 0) {
            z = true;
        } else {
            z = false;
        }
        this.expired = z;
    }

    @Override // java.lang.Comparable
    public final int compareTo(Deadline deadline) {
        OnDeviceFaceMeshLogEvent onDeviceFaceMeshLogEvent = this.ticker$ar$class_merging$ar$class_merging;
        if (onDeviceFaceMeshLogEvent == deadline.ticker$ar$class_merging$ar$class_merging) {
            long j = this.deadlineNanos - deadline.deadlineNanos;
            if (j < 0) {
                return -1;
            }
            return j > 0 ? 1 : 0;
        }
        throw new AssertionError("Tickers (" + onDeviceFaceMeshLogEvent.toString() + " and " + deadline.ticker$ar$class_merging$ar$class_merging.toString() + ") don't match. Custom Ticker should only be used in tests!");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Deadline)) {
            return false;
        }
        Deadline deadline = (Deadline) obj;
        if (this.ticker$ar$class_merging$ar$class_merging == deadline.ticker$ar$class_merging$ar$class_merging && this.deadlineNanos == deadline.deadlineNanos) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.asList(this.ticker$ar$class_merging$ar$class_merging, Long.valueOf(this.deadlineNanos)).hashCode();
    }

    public final long timeRemaining(TimeUnit timeUnit) {
        long nanoTime = System.nanoTime();
        if (!this.expired && this.deadlineNanos - nanoTime <= 0) {
            this.expired = true;
        }
        return timeUnit.convert(this.deadlineNanos - nanoTime, TimeUnit.NANOSECONDS);
    }

    public final String toString() {
        long timeRemaining = timeRemaining(TimeUnit.NANOSECONDS);
        long abs = Math.abs(timeRemaining);
        long j = NANOS_PER_SECOND;
        long j2 = abs / j;
        long abs2 = Math.abs(timeRemaining) % j;
        StringBuilder sb = new StringBuilder();
        if (timeRemaining < 0) {
            sb.append('-');
        }
        sb.append(j2);
        if (abs2 > 0) {
            sb.append(String.format(Locale.US, ".%09d", Long.valueOf(abs2)));
        }
        sb.append("s from now");
        OnDeviceFaceMeshLogEvent onDeviceFaceMeshLogEvent = this.ticker$ar$class_merging$ar$class_merging;
        if (onDeviceFaceMeshLogEvent != SYSTEM_TICKER$ar$class_merging$ar$class_merging$ar$class_merging) {
            sb.append(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(onDeviceFaceMeshLogEvent, " (ticker=", ")"));
        }
        return sb.toString();
    }
}
