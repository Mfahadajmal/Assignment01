package com.google.common.flogger.context;

import java.io.Closeable;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NoOpContextDataProvider extends ContextDataProvider {
    public static final ContextDataProvider NO_OP_INSTANCE = new NoOpContextDataProvider();

    public NoOpContextDataProvider() {
        new NoOpScopedLoggingContext();
    }

    public final String toString() {
        return "No-op Provider";
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class NoOpScopedLoggingContext extends ContextDataProvider implements Closeable {
        public NoOpScopedLoggingContext() {
            new AtomicBoolean();
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public final void close() {
        }
    }
}
