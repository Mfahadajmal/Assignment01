package io.grpc.internal;

import j$.util.concurrent.ConcurrentLinkedQueue;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SerializingExecutor implements Executor, Runnable {
    private static final AtomicHelper atomicHelper;
    private static final Logger log = Logger.getLogger(SerializingExecutor.class.getName());
    private final Executor executor;
    private final Queue runQueue = new ConcurrentLinkedQueue();
    public volatile int runState = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class AtomicHelper {
        public abstract boolean runStateCompareAndSet$ar$ds(SerializingExecutor serializingExecutor);

        public abstract void runStateSet$ar$ds(SerializingExecutor serializingExecutor);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FieldUpdaterAtomicHelper extends AtomicHelper {
        private final AtomicIntegerFieldUpdater runStateUpdater;

        public FieldUpdaterAtomicHelper(AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            this.runStateUpdater = atomicIntegerFieldUpdater;
        }

        @Override // io.grpc.internal.SerializingExecutor.AtomicHelper
        public final boolean runStateCompareAndSet$ar$ds(SerializingExecutor serializingExecutor) {
            return this.runStateUpdater.compareAndSet(serializingExecutor, 0, -1);
        }

        @Override // io.grpc.internal.SerializingExecutor.AtomicHelper
        public final void runStateSet$ar$ds(SerializingExecutor serializingExecutor) {
            this.runStateUpdater.set(serializingExecutor, 0);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SynchronizedAtomicHelper extends AtomicHelper {
        @Override // io.grpc.internal.SerializingExecutor.AtomicHelper
        public final boolean runStateCompareAndSet$ar$ds(SerializingExecutor serializingExecutor) {
            synchronized (serializingExecutor) {
                if (serializingExecutor.runState == 0) {
                    serializingExecutor.runState = -1;
                    return true;
                }
                return false;
            }
        }

        @Override // io.grpc.internal.SerializingExecutor.AtomicHelper
        public final void runStateSet$ar$ds(SerializingExecutor serializingExecutor) {
            synchronized (serializingExecutor) {
                serializingExecutor.runState = 0;
            }
        }
    }

    static {
        AtomicHelper synchronizedAtomicHelper;
        try {
            synchronizedAtomicHelper = new FieldUpdaterAtomicHelper(AtomicIntegerFieldUpdater.newUpdater(SerializingExecutor.class, "runState"));
        } catch (Throwable th) {
            log.logp(Level.SEVERE, "io.grpc.internal.SerializingExecutor", "getAtomicHelper", "FieldUpdaterAtomicHelper failed", th);
            synchronizedAtomicHelper = new SynchronizedAtomicHelper();
        }
        atomicHelper = synchronizedAtomicHelper;
    }

    public SerializingExecutor(Executor executor) {
        executor.getClass();
        this.executor = executor;
    }

    private final void schedule(Runnable runnable) {
        if (atomicHelper.runStateCompareAndSet$ar$ds(this)) {
            try {
                this.executor.execute(this);
            } catch (Throwable th) {
                if (runnable != null) {
                    this.runQueue.remove(runnable);
                }
                atomicHelper.runStateSet$ar$ds(this);
                throw th;
            }
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        runnable.getClass();
        this.runQueue.add(runnable);
        schedule(runnable);
    }

    @Override // java.lang.Runnable
    public final void run() {
        Runnable runnable;
        try {
            Executor executor = this.executor;
            while (executor == this.executor && (runnable = (Runnable) this.runQueue.poll()) != null) {
                try {
                    runnable.run();
                } catch (RuntimeException e) {
                    log.logp(Level.SEVERE, "io.grpc.internal.SerializingExecutor", "run", "Exception while executing runnable " + runnable.toString(), (Throwable) e);
                }
            }
            atomicHelper.runStateSet$ar$ds(this);
            if (!this.runQueue.isEmpty()) {
                schedule(null);
            }
        } catch (Throwable th) {
            atomicHelper.runStateSet$ar$ds(this);
            throw th;
        }
    }
}
