package com.google.common.util.concurrent;

import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class LazyLogger {
    private volatile Logger logger;
    private final String loggerName;

    public LazyLogger(Class cls) {
        this.loggerName = cls.getName();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Logger get() {
        Logger logger = this.logger;
        if (logger != null) {
            return logger;
        }
        synchronized (this) {
            Logger logger2 = this.logger;
            if (logger2 != null) {
                return logger2;
            }
            Logger logger3 = Logger.getLogger(this.loggerName);
            this.logger = logger3;
            return logger3;
        }
    }
}
