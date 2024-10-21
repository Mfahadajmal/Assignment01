package com.google.common.flogger.backend.android;

import android.util.Log;
import com.google.common.flogger.LogContext;
import com.google.common.flogger.android.AndroidLogTag;
import com.google.common.flogger.android.LogcatMetadata;
import com.google.common.flogger.backend.KeyValueFormatter;
import com.google.common.flogger.backend.LogData;
import com.google.common.flogger.backend.LogSiteFormatter;
import com.google.common.flogger.backend.LogSiteFormatters;
import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.MessageUtils;
import com.google.common.flogger.backend.Metadata;
import com.google.common.flogger.backend.MetadataHandler;
import com.google.common.flogger.backend.MetadataKeyValueHandlers;
import com.google.common.flogger.backend.MetadataProcessor;
import com.google.common.flogger.backend.Platform;
import com.google.common.flogger.backend.SimpleMessageFormatter;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.flogger.parser.MessageBuilder;
import com.google.common.flogger.parser.ParseException;
import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SimpleAndroidLoggerBackend extends AbstractAndroidBackend {
    public static final Set DEFAULT_KEYS_TO_IGNORE;
    public static final MetadataHandler DEFAULT_METADATA_HANDLER;
    public static final Factory SINGLETON_DEFAULT_FACTORY;
    private final Level includeFormatArgumentsLevel;
    private final Set keysToIgnore;
    private final LogSiteFormatter logSiteFormatter;
    private final MetadataHandler metadataHandler;
    private final String tagName;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Factory implements AndroidBackendFactory {
        private final Level includeFormatArgumentsLevel;
        public final Set keysToIgnore;
        public final LogSiteFormatter logSiteFormatter;
        public final MetadataHandler metadataHandler;
        public final String prefix;

        public Factory(LogSiteFormatter logSiteFormatter, Level level, Set set, MetadataHandler metadataHandler) {
            this.prefix = "";
            this.logSiteFormatter = logSiteFormatter;
            this.includeFormatArgumentsLevel = level;
            this.keysToIgnore = set;
            this.metadataHandler = metadataHandler;
        }

        @Override // com.google.common.flogger.backend.android.AndroidBackendFactory
        public final LoggerBackend create(String str) {
            return new SimpleAndroidLoggerBackend(str, this.logSiteFormatter, this.includeFormatArgumentsLevel, this.keysToIgnore, this.metadataHandler);
        }

        private Factory() {
            this(LogSiteFormatters.NO_OP, Level.ALL, SimpleAndroidLoggerBackend.DEFAULT_KEYS_TO_IGNORE, SimpleAndroidLoggerBackend.DEFAULT_METADATA_HANDLER);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LogSiteBasedBackend extends AbstractAndroidBackend {
        private final boolean alwaysLog;
        private final Level includeFormatArgumentsLevel;
        private final Set keysToIgnore;
        private final LogSiteFormatter logSiteFormatter;
        private final MetadataHandler metadataHandler;
        private final String prefix;

        public LogSiteBasedBackend(String str, LogSiteFormatter logSiteFormatter, Level level, boolean z, Set set, MetadataHandler metadataHandler) {
            super(str);
            this.prefix = "";
            this.logSiteFormatter = logSiteFormatter;
            this.includeFormatArgumentsLevel = level;
            this.alwaysLog = true;
            this.keysToIgnore = set;
            this.metadataHandler = metadataHandler;
        }

        @Override // com.google.common.flogger.backend.LoggerBackend
        public final boolean isLoggable(Level level) {
            return true;
        }

        @Override // com.google.common.flogger.backend.LoggerBackend
        public final void log(LogData logData) {
            String str = (String) logData.getMetadata().findValue(AndroidLogTag.TAG);
            if (str == null) {
                str = getLoggerName();
            }
            if (str == null) {
                str = logData.getLogSite().getClassName();
                int indexOf = str.indexOf(36, str.lastIndexOf(46));
                if (indexOf >= 0) {
                    str = str.substring(0, indexOf);
                }
            }
            SimpleAndroidLoggerBackend.log(logData, ContextDataProvider.getValidTag$ar$ds(str), this.logSiteFormatter, this.includeFormatArgumentsLevel, this.keysToIgnore, this.metadataHandler);
        }
    }

    static {
        Set unmodifiableSet = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList(LogContext.Key.LOG_CAUSE, AndroidLogTag.TAG, LogcatMetadata.DO_NOT_LOG_TO_LOGCAT)));
        DEFAULT_KEYS_TO_IGNORE = unmodifiableSet;
        MetadataHandler defaultHandler = MetadataKeyValueHandlers.getDefaultHandler(unmodifiableSet);
        DEFAULT_METADATA_HANDLER = defaultHandler;
        SINGLETON_DEFAULT_FACTORY = new Factory(LogSiteFormatters.NO_OP, Level.ALL, unmodifiableSet, defaultHandler);
    }

    public SimpleAndroidLoggerBackend(String str, LogSiteFormatter logSiteFormatter, Level level, Set set, MetadataHandler metadataHandler) {
        super(str);
        this.tagName = ContextDataProvider.getValidTag$ar$ds(str);
        this.logSiteFormatter = logSiteFormatter;
        this.includeFormatArgumentsLevel = level;
        this.keysToIgnore = set;
        this.metadataHandler = metadataHandler;
    }

    @Override // com.google.common.flogger.backend.LoggerBackend
    public final boolean isLoggable(Level level) {
        String str = this.tagName;
        int androidLevel$ar$edu = ContextDataProvider.getAndroidLevel$ar$edu(level);
        if (!Log.isLoggable(str, androidLevel$ar$edu) && !Log.isLoggable("all", androidLevel$ar$edu)) {
            return false;
        }
        return true;
    }

    @Override // com.google.common.flogger.backend.LoggerBackend
    public final void log(LogData logData) {
        log(logData, this.tagName, this.logSiteFormatter, this.includeFormatArgumentsLevel, this.keysToIgnore, this.metadataHandler);
    }

    public static void log(LogData logData, String str, LogSiteFormatter logSiteFormatter, Level level, Set set, MetadataHandler metadataHandler) {
        MetadataProcessor simpleProcessor;
        MetadataProcessor metadataProcessor;
        String sb;
        int androidLevel$ar$edu;
        Boolean bool = (Boolean) logData.getMetadata().findValue(LogcatMetadata.DO_NOT_LOG_TO_LOGCAT);
        if (bool == null || !bool.booleanValue()) {
            Metadata injectedMetadata = Platform.getInjectedMetadata();
            Metadata metadata = logData.getMetadata();
            int size = metadata.size();
            if (size == 0) {
                metadataProcessor = MetadataProcessor.EMPTY_PROCESSOR;
            } else {
                if (size <= 28) {
                    simpleProcessor = new MetadataProcessor.LightweightProcessor(injectedMetadata, metadata);
                } else {
                    simpleProcessor = new MetadataProcessor.SimpleProcessor(injectedMetadata, metadata);
                }
                metadataProcessor = simpleProcessor;
            }
            boolean z = logData.getLevel().intValue() < level.intValue();
            if (logSiteFormatter.equals(LogSiteFormatters.NO_OP) && !z) {
                int i = SimpleMessageFormatter.SimpleMessageFormatter$ar$NoOp;
                if (logData.getTemplateContext() == null && metadataProcessor.keyCount() <= set.size() && set.containsAll(metadataProcessor.keySet())) {
                    sb = MessageUtils.safeToString(logData.getLiteralArgument());
                    Throwable th = (Throwable) logData.getMetadata().findValue(LogContext.Key.LOG_CAUSE);
                    androidLevel$ar$edu = ContextDataProvider.getAndroidLevel$ar$edu(logData.getLevel());
                    if (androidLevel$ar$edu != 2 || androidLevel$ar$edu == 3 || androidLevel$ar$edu == 4) {
                        return;
                    }
                    if (androidLevel$ar$edu != 5) {
                        Log.e(str, sb, th);
                        return;
                    } else {
                        Log.w(str, sb, th);
                        return;
                    }
                }
            }
            StringBuilder sb2 = new StringBuilder();
            if (logSiteFormatter.appendLogSite(logData.getLogSite(), sb2)) {
                sb2.append(" ");
            }
            if (z && logData.getTemplateContext() != null) {
                sb2.append("(REDACTED) ");
                sb2.append(logData.getTemplateContext().message);
            } else {
                if (logData.getTemplateContext() != null) {
                    MessageBuilder messageBuilder = new MessageBuilder(logData.getTemplateContext(), logData.getArguments(), sb2);
                    messageBuilder.getParser().parseImpl(messageBuilder);
                    int i2 = messageBuilder.pmask;
                    if (((i2 + 1) & i2) == 0 && (messageBuilder.maxIndex <= 31 || i2 == -1)) {
                        Object buildImpl = messageBuilder.buildImpl();
                        if (logData.getArguments().length > messageBuilder.maxIndex + 1) {
                            ((StringBuilder) buildImpl).append(" [ERROR: UNUSED LOG ARGUMENTS]");
                        }
                    } else {
                        throw new ParseException(String.format("unreferenced arguments [first missing index=%d]", Integer.valueOf(Integer.numberOfTrailingZeros(~i2))));
                    }
                } else {
                    sb2.append(MessageUtils.safeToString(logData.getLiteralArgument()));
                }
                int i3 = SimpleMessageFormatter.SimpleMessageFormatter$ar$NoOp;
                KeyValueFormatter keyValueFormatter = new KeyValueFormatter(sb2);
                metadataProcessor.process(metadataHandler, keyValueFormatter);
                if (keyValueFormatter.haveSeenValues) {
                    keyValueFormatter.out.append(keyValueFormatter.suffix);
                }
            }
            sb = sb2.toString();
            Throwable th2 = (Throwable) logData.getMetadata().findValue(LogContext.Key.LOG_CAUSE);
            androidLevel$ar$edu = ContextDataProvider.getAndroidLevel$ar$edu(logData.getLevel());
            if (androidLevel$ar$edu != 2) {
            }
        }
    }
}
