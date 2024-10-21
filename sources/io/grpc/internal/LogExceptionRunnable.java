package io.grpc.internal;

import com.google.common.base.Throwables;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogExceptionRunnable implements Runnable {
    private static final Logger log = Logger.getLogger(LogExceptionRunnable.class.getName());
    private final Runnable task;

    public LogExceptionRunnable(Runnable runnable) {
        this.task = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.task.run();
        } catch (Throwable th) {
            log.logp(Level.SEVERE, "io.grpc.internal.LogExceptionRunnable", "run", "Exception while executing runnable ".concat(this.task.toString()), th);
            Throwables.throwIfUnchecked(th);
            throw new AssertionError(th);
        }
    }

    public final String toString() {
        return "LogExceptionRunnable(" + this.task.toString() + ")";
    }
}
