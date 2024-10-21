package com.google.common.flogger;

import com.google.common.flogger.LoggingApi;
import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.Platform;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GoogleLogger extends AbstractLogger {
    private static final LoggingApi.NoOp NO_OP$ar$class_merging$ar$class_merging = new LoggingApi.NoOp((char[]) null);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface Api extends LoggingApi {
    }

    public GoogleLogger(LoggerBackend loggerBackend) {
        super(loggerBackend);
    }

    @Deprecated
    public static GoogleLogger forInjectedClassName(String str) {
        ContextDataProvider.checkArgument(!str.isEmpty(), "injected class name is empty");
        return new GoogleLogger(Platform.getBackend(str.replace('/', '.')));
    }

    @Override // com.google.common.flogger.AbstractLogger
    public final Api at(Level level) {
        boolean isLoggable = isLoggable(level);
        Platform.shouldForceLogging(getName(), level, isLoggable);
        return !isLoggable ? NO_OP$ar$class_merging$ar$class_merging : new Context(level);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Context extends GoogleLogContext implements Api {
        public Context(Level level) {
            super(level);
        }

        @Override // com.google.common.flogger.LogContext
        protected final /* synthetic */ AbstractLogger getLogger() {
            return GoogleLogger.this;
        }

        @Override // com.google.common.flogger.LogContext
        protected final /* bridge */ /* synthetic */ LoggingApi api() {
            return this;
        }
    }
}
