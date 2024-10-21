package com.google.common.flogger;

import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
final class DurationRateLimiter extends RateLimitStatus {
    public static final LogSiteMap map = new LogSiteMap() { // from class: com.google.common.flogger.DurationRateLimiter.1
        @Override // com.google.common.flogger.LogSiteMap
        protected final /* synthetic */ Object initialValue() {
            return new DurationRateLimiter();
        }
    };
    public final AtomicLong lastTimestampNanos = new AtomicLong(-1);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class RateLimitPeriod {
        public final boolean equals(Object obj) {
            throw null;
        }

        public final int hashCode() {
            throw null;
        }

        public final String toString() {
            throw null;
        }
    }

    @Override // com.google.common.flogger.RateLimitStatus
    public final void reset() {
        this.lastTimestampNanos.set(Math.max(-this.lastTimestampNanos.get(), 0L));
    }
}
