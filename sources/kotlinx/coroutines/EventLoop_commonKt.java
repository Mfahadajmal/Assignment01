package kotlinx.coroutines;

import kotlinx.coroutines.internal.Symbol;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EventLoop_commonKt {
    public static final Symbol DISPOSED_TASK = new Symbol("REMOVED_TASK");
    public static final Symbol CLOSED_EMPTY = new Symbol("CLOSED_EMPTY");

    public static final long delayToNanos(long j) {
        if (j <= 0) {
            return 0L;
        }
        if (j >= 9223372036854L) {
            return Long.MAX_VALUE;
        }
        return j * 1000000;
    }
}
