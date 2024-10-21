package com.google.common.flogger.android;

import com.google.common.flogger.AbstractLogger;
import com.google.common.flogger.GoogleLogContext;
import com.google.common.flogger.LoggingApi;
import com.google.common.flogger.android.AndroidAbstractLogger;
import com.google.common.flogger.backend.LoggerBackend;
import com.google.common.flogger.backend.Platform;
import java.util.logging.Level;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AndroidFluentLogger extends AndroidAbstractLogger {
    public AndroidFluentLogger(LoggerBackend loggerBackend) {
        super(loggerBackend);
    }

    public static AndroidFluentLogger create$ar$ds$7ce4a6ce_0() {
        return new AndroidFluentLogger(Platform.getBackend("xRPC"));
    }

    @Override // com.google.common.flogger.AbstractLogger
    public final AndroidAbstractLogger.Api at(Level level) {
        boolean isLoggable = isLoggable(level);
        Platform.shouldForceLogging(getName(), level, isLoggable);
        return !isLoggable ? NO_OP$ar$class_merging$bfbdd76d_0$ar$class_merging : new Context(level);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Context extends GoogleLogContext implements AndroidAbstractLogger.Api {
        public Context(Level level) {
            super(level);
        }

        @Override // com.google.common.flogger.LogContext
        protected final /* synthetic */ AbstractLogger getLogger() {
            return AndroidFluentLogger.this;
        }

        @Override // com.google.common.flogger.LogContext
        protected final /* bridge */ /* synthetic */ LoggingApi api() {
            return this;
        }
    }
}
