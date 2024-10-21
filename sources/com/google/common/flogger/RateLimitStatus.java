package com.google.common.flogger;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class RateLimitStatus {
    public static final RateLimitStatus DISALLOW = new RateLimitStatus() { // from class: com.google.common.flogger.RateLimitStatus.1
        @Override // com.google.common.flogger.RateLimitStatus
        public final void reset() {
        }
    };
    public static final RateLimitStatus ALLOW = new RateLimitStatus() { // from class: com.google.common.flogger.RateLimitStatus.1
        @Override // com.google.common.flogger.RateLimitStatus
        public final void reset() {
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LogGuard {
        public static final LogSiteMap guardMap = new LogSiteMap() { // from class: com.google.common.flogger.RateLimitStatus.LogGuard.1
            @Override // com.google.common.flogger.LogSiteMap
            public final /* synthetic */ Object initialValue() {
                return new LogGuard();
            }
        };
        public final AtomicBoolean shouldReset = new AtomicBoolean();
        public final AtomicInteger pendingLogCount = new AtomicInteger();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RateLimitStatus combine(RateLimitStatus rateLimitStatus, final RateLimitStatus rateLimitStatus2) {
        RateLimitStatus rateLimitStatus3;
        if (rateLimitStatus == null) {
            return rateLimitStatus2;
        }
        if (rateLimitStatus2 == null) {
            return rateLimitStatus;
        }
        RateLimitStatus rateLimitStatus4 = DISALLOW;
        if (rateLimitStatus != rateLimitStatus4 && rateLimitStatus2 != (rateLimitStatus3 = ALLOW)) {
            if (rateLimitStatus2 != rateLimitStatus4 && rateLimitStatus != rateLimitStatus3) {
                return new RateLimitStatus() { // from class: com.google.common.flogger.RateLimitStatus.2
                    @Override // com.google.common.flogger.RateLimitStatus
                    public final void reset() {
                        try {
                            RateLimitStatus.this.reset();
                        } finally {
                            rateLimitStatus2.reset();
                        }
                    }
                };
            }
            return rateLimitStatus2;
        }
        return rateLimitStatus;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract void reset();
}
