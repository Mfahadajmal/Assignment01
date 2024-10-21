package io.grpc.internal;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
final class SerializeReentrantCallsDirectExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SerializeReentrantCallsDirectExecutor.class.getName());
    private boolean executing;
    private ArrayDeque taskQueue;

    private final void completeQueuedTasks() {
        while (true) {
            Runnable runnable = (Runnable) this.taskQueue.poll();
            if (runnable != null) {
                try {
                    runnable.run();
                } catch (Throwable th) {
                    log.logp(Level.SEVERE, "io.grpc.internal.SerializeReentrantCallsDirectExecutor", "completeQueuedTasks", "Exception while executing runnable ".concat(runnable.toString()), th);
                }
            } else {
                return;
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.getClass();
        if (!this.executing) {
            this.executing = true;
            try {
                runnable.run();
                if (this.taskQueue != null) {
                    completeQueuedTasks();
                }
                this.executing = false;
                return;
            } catch (Throwable th) {
                try {
                    log.logp(Level.SEVERE, "io.grpc.internal.SerializeReentrantCallsDirectExecutor", "execute", "Exception while executing runnable " + runnable.toString(), th);
                    if (this.taskQueue != null) {
                        completeQueuedTasks();
                    }
                    this.executing = false;
                    return;
                } catch (Throwable th2) {
                    if (this.taskQueue != null) {
                        completeQueuedTasks();
                    }
                    this.executing = false;
                    throw th2;
                }
            }
        }
        if (this.taskQueue == null) {
            this.taskQueue = new ArrayDeque(4);
        }
        this.taskQueue.add(runnable);
    }
}
