package com.google.common.flogger;

import com.google.common.flogger.DurationRateLimiter;
import com.google.common.flogger.LogSite;
import com.google.common.flogger.MetadataKey;
import com.google.common.flogger.RateLimitStatus;
import com.google.common.flogger.backend.LogData;
import com.google.common.flogger.backend.LoggingException;
import com.google.common.flogger.backend.Metadata;
import com.google.common.flogger.backend.Platform;
import com.google.common.flogger.backend.TemplateContext;
import com.google.common.flogger.context.Tags;
import com.google.common.flogger.parser.MessageParser;
import com.google.common.flogger.util.RecursionDepth;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LogContext implements LoggingApi, LogData {
    private static final String LITERAL_VALUE_MESSAGE = new String();
    private Object[] args;
    private final Level level;
    private LogSite logSite;
    public MutableMetadata metadata;
    public RateLimitStatus rateLimitStatus;
    private TemplateContext templateContext;
    public final long timestampNanos;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Key {
        public static final MetadataKey LOG_CAUSE = new MetadataKey("cause", Throwable.class, false, false);
        public static final MetadataKey LOG_EVERY_N = new MetadataKey("ratelimit_count", Integer.class, false, false);
        public static final MetadataKey LOG_SAMPLE_EVERY_N = new MetadataKey("sampling_count", Integer.class, false, false);
        public static final MetadataKey LOG_AT_MOST_EVERY = new MetadataKey("ratelimit_period", DurationRateLimiter.RateLimitPeriod.class, false, false);
        public static final MetadataKey SKIPPED_LOG_COUNT = new MetadataKey("skipped", Integer.class, false, false);
        public static final MetadataKey LOG_SITE_GROUPING_KEY = new MetadataKey(Object.class) { // from class: com.google.common.flogger.LogContext.Key.1
            @Override // com.google.common.flogger.MetadataKey
            public final void emitRepeated(Iterator it, MetadataKey.KeyValueHandler keyValueHandler) {
                if (it.hasNext()) {
                    Object next = it.next();
                    if (!it.hasNext()) {
                        keyValueHandler.handle(this.label, next);
                        return;
                    }
                    StringBuilder sb = new StringBuilder("[");
                    sb.append(next);
                    do {
                        sb.append(',');
                        sb.append(it.next());
                    } while (it.hasNext());
                    String str = this.label;
                    sb.append(']');
                    keyValueHandler.handle(str, sb.toString());
                }
            }
        };
        public static final MetadataKey WAS_FORCED = new MetadataKey("forced", Boolean.class, false, false);
        public static final MetadataKey TAGS = new MetadataKey(Tags.class) { // from class: com.google.common.flogger.LogContext.Key.2
            @Override // com.google.common.flogger.MetadataKey
            public final /* bridge */ /* synthetic */ void emit(Object obj, MetadataKey.KeyValueHandler keyValueHandler) {
                Tags.LightweightTagMap.SortedArraySet.AnonymousClass1 anonymousClass1 = new Tags.LightweightTagMap.SortedArraySet.AnonymousClass1();
                while (anonymousClass1.hasNext()) {
                    Map.Entry entry = (Map.Entry) anonymousClass1.next();
                    if (!((Set) entry.getValue()).isEmpty()) {
                        Iterator it = ((Set) entry.getValue()).iterator();
                        while (it.hasNext()) {
                            keyValueHandler.handle((String) entry.getKey(), it.next());
                        }
                    } else {
                        keyValueHandler.handle((String) entry.getKey(), null);
                    }
                }
            }
        };
        public static final MetadataKey CONTEXT_STACK_SIZE = new MetadataKey("stack_size", StackSize.class, false, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class MutableMetadata extends Metadata {
        public Object[] keyValuePairs = new Object[8];
        public int keyValueCount = 0;

        final void addValue(MetadataKey metadataKey, Object obj) {
            int indexOf;
            if (!metadataKey.canRepeat && (indexOf = indexOf(metadataKey)) != -1) {
                obj.getClass();
                this.keyValuePairs[indexOf + indexOf + 1] = obj;
                return;
            }
            int i = this.keyValueCount + 1;
            Object[] objArr = this.keyValuePairs;
            int length = objArr.length;
            if (i + i > length) {
                this.keyValuePairs = Arrays.copyOf(objArr, length + length);
            }
            Object[] objArr2 = this.keyValuePairs;
            int i2 = this.keyValueCount;
            int i3 = i2 + i2;
            metadataKey.getClass();
            objArr2[i3] = metadataKey;
            obj.getClass();
            objArr2[i3 + 1] = obj;
            this.keyValueCount = i2 + 1;
        }

        @Override // com.google.common.flogger.backend.Metadata
        public final Object findValue(MetadataKey metadataKey) {
            int indexOf = indexOf(metadataKey);
            if (indexOf != -1) {
                return metadataKey.cast(this.keyValuePairs[indexOf + indexOf + 1]);
            }
            return null;
        }

        @Override // com.google.common.flogger.backend.Metadata
        public final MetadataKey getKey(int i) {
            if (i < this.keyValueCount) {
                return (MetadataKey) this.keyValuePairs[i + i];
            }
            throw new IndexOutOfBoundsException();
        }

        @Override // com.google.common.flogger.backend.Metadata
        public final Object getValue(int i) {
            if (i < this.keyValueCount) {
                return this.keyValuePairs[i + i + 1];
            }
            throw new IndexOutOfBoundsException();
        }

        public final int indexOf(MetadataKey metadataKey) {
            for (int i = 0; i < this.keyValueCount; i++) {
                if (this.keyValuePairs[i + i].equals(metadataKey)) {
                    return i;
                }
            }
            return -1;
        }

        @Override // com.google.common.flogger.backend.Metadata
        public final int size() {
            return this.keyValueCount;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("Metadata{");
            for (int i = 0; i < this.keyValueCount; i++) {
                sb.append(" '");
                sb.append(getKey(i));
                sb.append("': ");
                sb.append(getValue(i));
            }
            sb.append(" }");
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public LogContext(Level level) {
        long currentTimeNanos = Platform.getCurrentTimeNanos();
        this.metadata = null;
        this.logSite = null;
        this.rateLimitStatus = null;
        this.templateContext = null;
        this.args = null;
        level.getClass();
        this.level = level;
        this.timestampNanos = currentTimeNanos;
    }

    private final void logImpl(String str, Object... objArr) {
        this.args = objArr;
        for (int i = 0; i < objArr.length; i++) {
            Object obj = objArr[i];
            if (obj instanceof LazyArg) {
                objArr[i] = ((LazyArg) obj).evaluate();
            }
        }
        if (str != LITERAL_VALUE_MESSAGE) {
            this.templateContext = new TemplateContext(getMessageParser(), str);
        }
        Tags injectedTags = Platform.getInjectedTags();
        if (!injectedTags.isEmpty()) {
            Tags tags = (Tags) getMetadata().findValue(Key.TAGS);
            if (tags != null && !tags.isEmpty()) {
                injectedTags = injectedTags.isEmpty() ? tags : new Tags(new Tags.LightweightTagMap(injectedTags.map, tags.map));
            }
            addMetadata(Key.TAGS, injectedTags);
        }
        AbstractLogger logger = getLogger();
        try {
            RecursionDepth recursionDepth = (RecursionDepth) RecursionDepth.holder.get();
            int i2 = recursionDepth.value + 1;
            recursionDepth.value = i2;
            if (i2 != 0) {
                try {
                    if (i2 <= 100) {
                        logger.backend.log(this);
                    } else {
                        AbstractLogger.reportError("unbounded recursion in log statement", this);
                    }
                    if (recursionDepth != null) {
                        recursionDepth.close();
                        return;
                    }
                    return;
                } finally {
                }
            }
            throw new AssertionError("Overflow of RecursionDepth (possible error in core library)");
        } catch (RuntimeException e) {
            try {
                logger.backend.handleError(e, this);
            } catch (LoggingException e2) {
                throw e2;
            } catch (RuntimeException e3) {
                AbstractLogger.reportError(e3.getClass().getName() + ": " + e3.getMessage(), this);
                try {
                    e3.printStackTrace(System.err);
                } catch (RuntimeException unused) {
                }
            }
        }
    }

    private final boolean shouldLog() {
        int i;
        boolean z = true;
        if (this.logSite == null) {
            this.logSite = Platform.getCallerFinder().findLogSite(LogContext.class, 1);
        }
        LogSiteKey logSiteKey = this.logSite;
        if (logSiteKey != LogSite.INVALID) {
            MutableMetadata mutableMetadata = this.metadata;
            if (mutableMetadata != null && (i = mutableMetadata.keyValueCount) > 0) {
                logSiteKey.getClass();
                for (int i2 = 0; i2 < i; i2++) {
                    if (Key.LOG_SITE_GROUPING_KEY.equals(mutableMetadata.getKey(i2))) {
                        Object value = mutableMetadata.getValue(i2);
                        if (value instanceof LoggingScope) {
                            logSiteKey = ((LoggingScope) value).specialize$ar$ds();
                        } else {
                            logSiteKey = new SpecializedLogSiteKey(logSiteKey, value);
                        }
                    }
                }
            }
        } else {
            logSiteKey = null;
        }
        boolean postProcess = postProcess(logSiteKey);
        RateLimitStatus rateLimitStatus = this.rateLimitStatus;
        if (rateLimitStatus != null) {
            RateLimitStatus.LogGuard logGuard = (RateLimitStatus.LogGuard) RateLimitStatus.LogGuard.guardMap.get(logSiteKey, this.metadata);
            int incrementAndGet = logGuard.pendingLogCount.incrementAndGet();
            int i3 = -1;
            if (rateLimitStatus != RateLimitStatus.DISALLOW && logGuard.shouldReset.compareAndSet(false, true)) {
                try {
                    rateLimitStatus.reset();
                    logGuard.shouldReset.set(false);
                    logGuard.pendingLogCount.addAndGet(-incrementAndGet);
                    i3 = (-1) + incrementAndGet;
                } catch (Throwable th) {
                    logGuard.shouldReset.set(false);
                    throw th;
                }
            }
            if (postProcess && i3 > 0) {
                this.metadata.addValue(Key.SKIPPED_LOG_COUNT, Integer.valueOf(i3));
            }
            if (i3 < 0) {
                z = false;
            }
            return postProcess & z;
        }
        return postProcess;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void addMetadata(MetadataKey metadataKey, Object obj) {
        if (this.metadata == null) {
            this.metadata = new MutableMetadata();
        }
        this.metadata.addValue(metadataKey, obj);
    }

    protected abstract LoggingApi api();

    @Override // com.google.common.flogger.backend.LogData
    public final Object[] getArguments() {
        if (this.templateContext != null) {
            return this.args;
        }
        throw new IllegalStateException("cannot get arguments unless a template context exists");
    }

    @Override // com.google.common.flogger.backend.LogData
    public final Level getLevel() {
        return this.level;
    }

    @Override // com.google.common.flogger.backend.LogData
    public final Object getLiteralArgument() {
        if (this.templateContext == null) {
            return this.args[0];
        }
        throw new IllegalStateException("cannot get literal argument if a template context exists");
    }

    @Override // com.google.common.flogger.backend.LogData
    public final LogSite getLogSite() {
        LogSite logSite = this.logSite;
        if (logSite != null) {
            return logSite;
        }
        throw new IllegalStateException("cannot request log site information prior to postProcess()");
    }

    protected abstract AbstractLogger getLogger();

    protected abstract MessageParser getMessageParser();

    @Override // com.google.common.flogger.backend.LogData
    public final Metadata getMetadata() {
        MutableMetadata mutableMetadata = this.metadata;
        if (mutableMetadata != null) {
            return mutableMetadata;
        }
        return Metadata.Empty.INSTANCE;
    }

    @Override // com.google.common.flogger.backend.LogData
    public final TemplateContext getTemplateContext() {
        return this.templateContext;
    }

    @Override // com.google.common.flogger.backend.LogData
    public final long getTimestampNanos() {
        return this.timestampNanos;
    }

    @Override // com.google.common.flogger.LoggingApi
    public final boolean isEnabled() {
        if (!wasForced() && !getLogger().isLoggable(this.level)) {
            return false;
        }
        return true;
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str) {
        if (shouldLog()) {
            logImpl(LITERAL_VALUE_MESSAGE, str);
        }
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void logVarargs(String str, Object[] objArr) {
        if (shouldLog()) {
            logImpl(str, Arrays.copyOf(objArr, objArr.length));
        }
    }

    protected boolean postProcess(LogSiteKey logSiteKey) {
        throw null;
    }

    @Override // com.google.common.flogger.backend.LogData
    public final boolean wasForced() {
        if (this.metadata != null && Boolean.TRUE.equals(this.metadata.findValue(Key.WAS_FORCED))) {
            return true;
        }
        return false;
    }

    @Override // com.google.common.flogger.LoggingApi
    public final LoggingApi withCause(Throwable th) {
        MetadataKey metadataKey = Key.LOG_CAUSE;
        metadataKey.getClass();
        if (th != null) {
            addMetadata(metadataKey, th);
        }
        return api();
    }

    @Override // com.google.common.flogger.LoggingApi
    public final LoggingApi withInjectedLogSite(String str, String str2, int i, String str3) {
        LogSite.InjectedLogSite injectedLogSite = new LogSite.InjectedLogSite(str, str2, i, str3);
        if (this.logSite == null) {
            this.logSite = injectedLogSite;
        }
        return api();
    }

    @Override // com.google.common.flogger.LoggingApi
    public final LoggingApi withStackTrace(StackSize stackSize) {
        stackSize.getClass();
        if (stackSize != StackSize.NONE) {
            addMetadata(Key.CONTEXT_STACK_SIZE, stackSize);
        }
        return api();
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str, int i) {
        if (shouldLog()) {
            logImpl(str, Integer.valueOf(i));
        }
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str, long j) {
        if (shouldLog()) {
            logImpl(str, Long.valueOf(j));
        }
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str, long j, Object obj) {
        if (shouldLog()) {
            logImpl(str, Long.valueOf(j), obj);
        }
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str, Object obj) {
        if (shouldLog()) {
            logImpl(str, obj);
        }
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str, Object obj, Object obj2) {
        if (shouldLog()) {
            logImpl(str, obj, obj2);
        }
    }

    @Override // com.google.common.flogger.LoggingApi
    public final void log(String str, Object obj, Object obj2, Object obj3) {
        if (shouldLog()) {
            logImpl(str, obj, obj2, obj3);
        }
    }
}
