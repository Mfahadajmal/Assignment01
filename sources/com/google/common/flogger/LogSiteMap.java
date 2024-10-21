package com.google.common.flogger;

import com.google.android.libraries.performance.primes.PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0;
import com.google.common.flogger.LogContext;
import com.google.common.flogger.backend.Metadata;
import j$.util.concurrent.ConcurrentHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LogSiteMap {
    public final ConcurrentHashMap concurrentMap = new ConcurrentHashMap();

    public final Object get(LogSiteKey logSiteKey, Metadata metadata) {
        Object obj = this.concurrentMap.get(logSiteKey);
        if (obj != null) {
            return obj;
        }
        Object initialValue = initialValue();
        Object putIfAbsent = this.concurrentMap.putIfAbsent(logSiteKey, initialValue);
        if (putIfAbsent == null) {
            int i = ((LogContext.MutableMetadata) metadata).keyValueCount;
            PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0 primesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0 = null;
            for (int i2 = 0; i2 < i; i2++) {
                if (LogContext.Key.LOG_SITE_GROUPING_KEY.equals(metadata.getKey(i2))) {
                    Object value = metadata.getValue(i2);
                    if (value instanceof LoggingScope) {
                        if (primesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0 == null) {
                            primesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0 = new PrimesExecutorsModule$PrimesThreadFactory$$ExternalSyntheticLambda0(this, logSiteKey, 19);
                        }
                        ((LoggingScope) value).onClose$ar$ds();
                    }
                }
            }
            return initialValue;
        }
        return putIfAbsent;
    }

    protected abstract Object initialValue();
}
