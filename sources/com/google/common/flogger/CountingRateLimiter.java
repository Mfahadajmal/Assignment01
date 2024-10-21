package com.google.common.flogger;

import java.util.concurrent.atomic.AtomicLong;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CountingRateLimiter extends RateLimitStatus {
    public static final LogSiteMap map = new LogSiteMap() { // from class: com.google.common.flogger.CountingRateLimiter.1
        @Override // com.google.common.flogger.LogSiteMap
        protected final /* synthetic */ Object initialValue() {
            return new CountingRateLimiter();
        }
    };
    public final AtomicLong invocationCount = new AtomicLong(2147483647L);

    @Override // com.google.common.flogger.RateLimitStatus
    public final void reset() {
        this.invocationCount.set(0L);
    }
}
