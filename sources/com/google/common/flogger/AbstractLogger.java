package com.google.common.flogger;

import com.google.common.flogger.LoggingApi;
import com.google.common.flogger.backend.LogData;
import com.google.common.flogger.backend.LogSiteFormatters;
import com.google.common.flogger.backend.LoggerBackend;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractLogger<API extends LoggingApi<API>> {
    public final LoggerBackend backend;

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractLogger(LoggerBackend loggerBackend) {
        this.backend = loggerBackend;
    }

    public static void reportError(String str, LogData logData) {
        StringBuilder sb = new StringBuilder();
        sb.append(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date(TimeUnit.NANOSECONDS.toMillis(logData.getTimestampNanos()))));
        sb.append(": logging error [");
        LogSiteFormatters.DEFAULT.appendLogSite(logData.getLogSite(), sb);
        sb.append("]: ");
        sb.append(str);
        System.err.println(sb);
        System.err.flush();
    }

    public abstract LoggingApi at(Level level);

    public final LoggingApi atFine() {
        return at(Level.FINE);
    }

    public final LoggingApi atFiner() {
        return at(Level.FINER);
    }

    public final LoggingApi atFinest() {
        return at(Level.FINEST);
    }

    public final LoggingApi atInfo() {
        return at(Level.INFO);
    }

    public final LoggingApi atSevere() {
        return at(Level.SEVERE);
    }

    public final LoggingApi atWarning() {
        return at(Level.WARNING);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String getName() {
        return this.backend.getLoggerName();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isLoggable(Level level) {
        return this.backend.isLoggable(level);
    }
}
