package io.grpc.internal;

import io.grpc.ChannelLogger;
import io.grpc.InternalChannelz$ChannelTrace$Event;
import io.grpc.InternalLogId;
import java.text.MessageFormat;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ChannelLoggerImpl extends ChannelLogger {
    private final TimeProvider time;
    private final ChannelTracer tracer;

    public ChannelLoggerImpl(ChannelTracer channelTracer, TimeProvider timeProvider) {
        channelTracer.getClass();
        this.tracer = channelTracer;
        timeProvider.getClass();
        this.time = timeProvider;
    }

    private final boolean isTraceable$ar$edu(int i) {
        if (i != 1) {
            synchronized (this.tracer.lock) {
            }
            return false;
        }
        return false;
    }

    static void logOnly$ar$edu(InternalLogId internalLogId, int i, String str) {
        Level javaLogLevel$ar$edu = toJavaLogLevel$ar$edu(i);
        if (ChannelTracer.logger.isLoggable(javaLogLevel$ar$edu)) {
            ChannelTracer.logOnly(internalLogId, javaLogLevel$ar$edu, str);
        }
    }

    public static Level toJavaLogLevel$ar$edu(int i) {
        int i2 = i - 1;
        if (i2 != 1) {
            if (i2 != 2 && i2 != 3) {
                return Level.FINEST;
            }
            return Level.FINE;
        }
        return Level.FINER;
    }

    @Override // io.grpc.ChannelLogger
    public final void log$ar$edu(int i, String str) {
        InternalChannelz$ChannelTrace$Event.Severity severity;
        logOnly$ar$edu(this.tracer.logId, i, str);
        if (isTraceable$ar$edu(i) && i != 1) {
            ChannelTracer channelTracer = this.tracer;
            InternalChannelz$ChannelTrace$Event.Builder builder = new InternalChannelz$ChannelTrace$Event.Builder();
            builder.InternalChannelz$ChannelTrace$Event$Builder$ar$description = str;
            int i2 = i - 1;
            if (i2 != 2) {
                if (i2 != 3) {
                    severity = InternalChannelz$ChannelTrace$Event.Severity.CT_INFO;
                } else {
                    severity = InternalChannelz$ChannelTrace$Event.Severity.CT_ERROR;
                }
            } else {
                severity = InternalChannelz$ChannelTrace$Event.Severity.CT_WARNING;
            }
            builder.InternalChannelz$ChannelTrace$Event$Builder$ar$severity = severity;
            builder.setTimestampNanos$ar$ds(this.time.currentTimeNanos());
            channelTracer.traceOnly(builder.m241build());
        }
    }

    @Override // io.grpc.ChannelLogger
    public final void log$ar$edu$7fdc135b_0(int i, String str, Object... objArr) {
        String format;
        Level javaLogLevel$ar$edu = toJavaLogLevel$ar$edu(i);
        if (!isTraceable$ar$edu(i) && !ChannelTracer.logger.isLoggable(javaLogLevel$ar$edu)) {
            format = null;
        } else {
            format = MessageFormat.format(str, objArr);
        }
        log$ar$edu(i, format);
    }
}
