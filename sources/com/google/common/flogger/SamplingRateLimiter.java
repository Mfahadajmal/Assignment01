package com.google.common.flogger;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class SamplingRateLimiter extends RateLimitStatus {
    public static final LogSiteMap map = new LogSiteMap() { // from class: com.google.common.flogger.SamplingRateLimiter.1
        @Override // com.google.common.flogger.LogSiteMap
        protected final /* synthetic */ Object initialValue() {
            return new SamplingRateLimiter();
        }
    };
    public static final ThreadLocal random = new ThreadLocal() { // from class: com.google.common.flogger.SamplingRateLimiter.2
        @Override // java.lang.ThreadLocal
        protected final /* synthetic */ Object initialValue() {
            return new Random();
        }
    };
    final AtomicInteger pendingCount = new AtomicInteger();

    @Override // com.google.common.flogger.RateLimitStatus
    public final void reset() {
        this.pendingCount.decrementAndGet();
    }
}
