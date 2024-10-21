package com.google.android.libraries.performance.primes.sampling;

import android.os.SystemClock;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RateLimiting {
    public static final SpannableUtils$NonCopyableTextSpan CLOCK$ar$class_merging$ar$class_merging$ar$class_merging = new SpannableUtils$NonCopyableTextSpan();
    private final SpannableUtils$NonCopyableTextSpan clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging;
    private final Provider rateLimit;
    private final Object mutex = new Object();
    private int eventCount = 0;
    private long firstEventInLastSecond = 0;

    public RateLimiting(Provider provider, SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan) {
        this.rateLimit = provider;
        this.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
    }

    public static RateLimiting fixed(final int i) {
        return new RateLimiting(new Provider() { // from class: com.google.android.libraries.performance.primes.sampling.RateLimiting$$ExternalSyntheticLambda0
            @Override // javax.inject.Provider
            public final Object get() {
                return Integer.valueOf(i);
            }
        }, CLOCK$ar$class_merging$ar$class_merging$ar$class_merging);
    }

    public final void incrementEventCount() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        synchronized (this.mutex) {
            this.eventCount++;
            if (elapsedRealtime - this.firstEventInLastSecond > 1000) {
                this.eventCount = 0;
                this.firstEventInLastSecond = elapsedRealtime;
            }
        }
    }

    public final boolean isRateLimitExceeded() {
        int intValue = ((Integer) this.rateLimit.get()).intValue();
        if (intValue == 0) {
            return true;
        }
        if (intValue != Integer.MAX_VALUE) {
            synchronized (this.mutex) {
                if (this.eventCount >= intValue) {
                    long j = this.firstEventInLastSecond;
                    if (SystemClock.elapsedRealtime() - j <= 1000) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
