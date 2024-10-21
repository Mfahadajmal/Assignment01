package io.grpc.internal;

import io.grpc.ChannelLogger;
import io.grpc.InternalChannelz$ChannelTrace$Event;
import io.grpc.InternalLogId;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChannelTracer {
    static final Logger logger = Logger.getLogger(ChannelLogger.class.getName());
    public final Collection events;
    public final Object lock = new Object();
    public final InternalLogId logId;

    public ChannelTracer(InternalLogId internalLogId, long j, String str) {
        internalLogId.getClass();
        this.logId = internalLogId;
        this.events = null;
        InternalChannelz$ChannelTrace$Event.Builder builder = new InternalChannelz$ChannelTrace$Event.Builder();
        builder.InternalChannelz$ChannelTrace$Event$Builder$ar$description = str.concat(" created");
        builder.InternalChannelz$ChannelTrace$Event$Builder$ar$severity = InternalChannelz$ChannelTrace$Event.Severity.CT_INFO;
        builder.setTimestampNanos$ar$ds(j);
        reportEvent(builder.m241build());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void logOnly(InternalLogId internalLogId, Level level, String str) {
        Logger logger2 = logger;
        if (logger2.isLoggable(level)) {
            LogRecord logRecord = new LogRecord(level, "[" + String.valueOf(internalLogId) + "] " + str);
            logRecord.setLoggerName(logger2.getName());
            logRecord.setSourceClassName(logger2.getName());
            logRecord.setSourceMethodName("log");
            logger2.log(logRecord);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void reportEvent(InternalChannelz$ChannelTrace$Event internalChannelz$ChannelTrace$Event) {
        Level level;
        int ordinal = internalChannelz$ChannelTrace$Event.severity.ordinal();
        if (ordinal != 2) {
            if (ordinal != 3) {
                level = Level.FINEST;
            } else {
                level = Level.FINE;
            }
        } else {
            level = Level.FINER;
        }
        traceOnly(internalChannelz$ChannelTrace$Event);
        logOnly(this.logId, level, internalChannelz$ChannelTrace$Event.description);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void traceOnly(InternalChannelz$ChannelTrace$Event internalChannelz$ChannelTrace$Event) {
        synchronized (this.lock) {
        }
    }
}
