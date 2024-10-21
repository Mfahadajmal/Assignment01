package com.google.android.libraries.performance.primes.metrics.timer;

import android.os.SystemClock;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.metrics.core.PrimesDuration;
import com.google.android.libraries.performance.primes.metrics.core.PrimesInstant;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimerEvent {
    boolean hasTrace;
    public final PrimesDuration primesDuration;
    public int timerStatus$ar$edu;
    public static final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = new SpannableUtils$NonCopyableTextSpan();
    public static final TimerEvent EMPTY_TIMER = new TimerEvent();

    public TimerEvent() {
        this.timerStatus$ar$edu = 1;
        this.hasTrace = false;
        this.primesDuration = new PrimesDuration(clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging);
    }

    @Deprecated
    public static long getTime() {
        return SystemClock.elapsedRealtime();
    }

    public static boolean isEmpty(TimerEvent timerEvent) {
        if (timerEvent != null && timerEvent != EMPTY_TIMER) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final long getRealtimeDurationMillis() {
        PrimesDuration primesDuration = this.primesDuration;
        return primesDuration.endInstant.elapsedRealtimeMs - primesDuration.startInstant.elapsedRealtimeMs;
    }

    public TimerEvent(long j, long j2) {
        this.timerStatus$ar$edu = 1;
        this.hasTrace = false;
        if (j2 < j) {
            throw new IllegalArgumentException(ContextDataProvider.lenientFormat("End time %s is before start time %s.", Long.valueOf(j2), Long.valueOf(j)));
        }
        this.primesDuration = new PrimesDuration(new PrimesInstant(j, j), new PrimesInstant(j2, j2));
        this.timerStatus$ar$edu = 1;
    }
}
