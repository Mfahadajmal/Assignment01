package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlin.collections.ArrayDeque;
import kotlinx.coroutines.EventLoopImplBase;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class EventLoop extends CoroutineDispatcher {
    private boolean shared;
    public ArrayDeque unconfinedQueue;
    private long useCount;

    public EventLoop() {
    }

    private static final long delta$ar$ds(boolean z) {
        if (z) {
            return 4294967296L;
        }
        return 1L;
    }

    public final void decrementUseCount(boolean z) {
        long delta$ar$ds = this.useCount - delta$ar$ds(z);
        this.useCount = delta$ar$ds;
        if (delta$ar$ds <= 0) {
            boolean z2 = DebugKt.ASSERTIONS_ENABLED;
            if (this.shared) {
                shutdown();
            }
        }
    }

    public final void dispatchUnconfined(DispatchedTask dispatchedTask) {
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (arrayDeque == null) {
            arrayDeque = new ArrayDeque();
            this.unconfinedQueue = arrayDeque;
        }
        arrayDeque.addLast(dispatchedTask);
    }

    protected abstract Thread getThread();

    public final void incrementUseCount(boolean z) {
        this.useCount += delta$ar$ds(z);
        if (!z) {
            this.shared = true;
        }
    }

    public final boolean isUnconfinedLoopActive() {
        if (this.useCount >= delta$ar$ds(true)) {
            return true;
        }
        return false;
    }

    public final boolean isUnconfinedQueueEmpty() {
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (arrayDeque != null) {
            return arrayDeque.isEmpty();
        }
        return true;
    }

    public long processNextEvent() {
        throw null;
    }

    public final boolean processUnconfinedEvent() {
        Object removeFirst;
        ArrayDeque arrayDeque = this.unconfinedQueue;
        if (arrayDeque != null) {
            if (arrayDeque.isEmpty()) {
                removeFirst = null;
            } else {
                removeFirst = arrayDeque.removeFirst();
            }
            DispatchedTask dispatchedTask = (DispatchedTask) removeFirst;
            if (dispatchedTask != null) {
                dispatchedTask.run();
                return true;
            }
            return false;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reschedule(long j, EventLoopImplBase.DelayedTask delayedTask) {
        DefaultExecutor.INSTANCE.schedule(j, delayedTask);
    }

    public void shutdown() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void unpark() {
        Thread thread = getThread();
        if (Thread.currentThread() != thread) {
            LockSupport.unpark(thread);
        }
    }

    public EventLoop(byte[] bArr) {
        this();
    }
}
