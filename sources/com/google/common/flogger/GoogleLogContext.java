package com.google.common.flogger;

import com.google.common.flogger.DurationRateLimiter;
import com.google.common.flogger.LogContext;
import com.google.common.flogger.backend.Metadata;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.flogger.parser.DefaultPrintfMessageParser;
import com.google.common.flogger.parser.MessageParser;
import com.google.common.flogger.util.CallerFinder;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class GoogleLogContext extends LogContext implements LoggingApi {
    /* JADX INFO: Access modifiers changed from: protected */
    public GoogleLogContext(Level level) {
        super(level);
    }

    @Override // com.google.common.flogger.LogContext
    protected final MessageParser getMessageParser() {
        return DefaultPrintfMessageParser.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v10, types: [com.google.common.flogger.SamplingRateLimiter] */
    /* JADX WARN: Type inference failed for: r11v11, types: [com.google.common.flogger.RateLimitStatus] */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7, types: [com.google.common.flogger.RateLimitStatus] */
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.common.flogger.RateLimitStatus] */
    @Override // com.google.common.flogger.LogContext
    protected final boolean postProcess(LogSiteKey logSiteKey) {
        int indexOf;
        int i;
        DurationRateLimiter durationRateLimiter;
        boolean z;
        CountingRateLimiter countingRateLimiter;
        ?? r11;
        int i2;
        Metadata metadata = getMetadata();
        int size = metadata.size();
        int i3 = 0;
        while (true) {
            if (i3 >= size) {
                break;
            }
            if (metadata.getKey(i3).label == "eye3tag") {
                if (metadata.findValue(LogContext.Key.LOG_CAUSE) == null && metadata.findValue(LogContext.Key.CONTEXT_STACK_SIZE) == null) {
                    addMetadata(LogContext.Key.CONTEXT_STACK_SIZE, StackSize.SMALL);
                }
            } else {
                i3++;
            }
        }
        LogContext.MutableMetadata mutableMetadata = this.metadata;
        if (mutableMetadata != null) {
            if (logSiteKey != null) {
                long j = this.timestampNanos;
                LogSiteMap logSiteMap = DurationRateLimiter.map;
                if (((DurationRateLimiter.RateLimitPeriod) mutableMetadata.findValue(LogContext.Key.LOG_AT_MOST_EVERY)) == null) {
                    durationRateLimiter = null;
                } else {
                    durationRateLimiter = (DurationRateLimiter) DurationRateLimiter.map.get(logSiteKey, mutableMetadata);
                    if (j >= 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    ContextDataProvider.checkArgument(z, "timestamp cannot be negative");
                    long j2 = durationRateLimiter.lastTimestampNanos.get();
                    if (j2 < 0) {
                        durationRateLimiter.lastTimestampNanos.compareAndSet(j2, -j);
                    } else {
                        throw null;
                    }
                }
                LogContext.MutableMetadata mutableMetadata2 = this.metadata;
                LogSiteMap logSiteMap2 = CountingRateLimiter.map;
                if (((Integer) mutableMetadata2.findValue(LogContext.Key.LOG_EVERY_N)) == null) {
                    countingRateLimiter = null;
                } else {
                    CountingRateLimiter countingRateLimiter2 = (CountingRateLimiter) CountingRateLimiter.map.get(logSiteKey, mutableMetadata2);
                    AtomicLong atomicLong = countingRateLimiter2.invocationCount;
                    countingRateLimiter = countingRateLimiter2;
                    if (atomicLong.incrementAndGet() < r5.intValue()) {
                        countingRateLimiter = CountingRateLimiter.DISALLOW;
                    }
                }
                RateLimitStatus combine = RateLimitStatus.combine(durationRateLimiter, countingRateLimiter);
                LogContext.MutableMetadata mutableMetadata3 = this.metadata;
                LogSiteMap logSiteMap3 = SamplingRateLimiter.map;
                Integer num = (Integer) mutableMetadata3.findValue(LogContext.Key.LOG_SAMPLE_EVERY_N);
                if (num != null && num.intValue() > 0) {
                    r11 = (SamplingRateLimiter) SamplingRateLimiter.map.get(logSiteKey, mutableMetadata3);
                    if (((Random) SamplingRateLimiter.random.get()).nextInt(num.intValue()) == 0) {
                        i2 = r11.pendingCount.incrementAndGet();
                    } else {
                        i2 = r11.pendingCount.get();
                    }
                    if (i2 <= 0) {
                        r11 = SamplingRateLimiter.DISALLOW;
                    }
                } else {
                    r11 = 0;
                }
                RateLimitStatus combine2 = RateLimitStatus.combine(combine, r11);
                this.rateLimitStatus = combine2;
                if (combine2 == RateLimitStatus.DISALLOW) {
                    return false;
                }
            }
            StackSize stackSize = (StackSize) this.metadata.findValue(LogContext.Key.CONTEXT_STACK_SIZE);
            if (stackSize != null) {
                MetadataKey metadataKey = LogContext.Key.CONTEXT_STACK_SIZE;
                LogContext.MutableMetadata mutableMetadata4 = this.metadata;
                if (mutableMetadata4 != null && (indexOf = mutableMetadata4.indexOf(metadataKey)) >= 0) {
                    int i4 = indexOf + indexOf;
                    int i5 = i4 + 2;
                    while (true) {
                        i = mutableMetadata4.keyValueCount;
                        if (i5 >= i + i) {
                            break;
                        }
                        Object obj = mutableMetadata4.keyValuePairs[i5];
                        if (!obj.equals(metadataKey)) {
                            Object[] objArr = mutableMetadata4.keyValuePairs;
                            objArr[i4] = obj;
                            objArr[i4 + 1] = objArr[i5 + 1];
                            i4 += 2;
                        }
                        i5 += 2;
                    }
                    mutableMetadata4.keyValueCount = i - ((i5 - i4) >> 1);
                    while (i4 < i5) {
                        mutableMetadata4.keyValuePairs[i4] = null;
                        i4++;
                    }
                }
                Throwable th = (Throwable) getMetadata().findValue(LogContext.Key.LOG_CAUSE);
                int i6 = stackSize.maxDepth;
                if (i6 <= 0 && i6 != -1) {
                    throw new IllegalArgumentException("invalid maximum depth: 0");
                }
                addMetadata(LogContext.Key.LOG_CAUSE, new LogSiteStackTrace(th, stackSize, CallerFinder.STACK_GETTER.getStackForCaller$ar$ds(LogContext.class, i6)));
            }
        }
        return true;
    }
}
