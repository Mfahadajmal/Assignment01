package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.EventLoopImplBase;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultExecutor extends EventLoopImplBase implements Runnable {
    public static final DefaultExecutor INSTANCE;
    private static final long KEEP_ALIVE_NANOS;
    private static volatile Thread _thread;
    private static volatile int debugStatus;

    static {
        Long l;
        DefaultExecutor defaultExecutor = new DefaultExecutor();
        INSTANCE = defaultExecutor;
        defaultExecutor.incrementUseCount(false);
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        try {
            l = Long.getLong("kotlinx.coroutines.DefaultExecutor.keepAlive", 1000L);
        } catch (SecurityException unused) {
            l = 1000L;
        }
        KEEP_ALIVE_NANOS = timeUnit.toNanos(l.longValue());
    }

    private DefaultExecutor() {
    }

    private final synchronized void acknowledgeShutdownIfNeeded() {
        if (!isShutdownRequested$ar$ds()) {
            return;
        }
        debugStatus = 3;
        this._queue.setValue(null);
        this._delayed.setValue(null);
        notifyAll();
    }

    private final synchronized Thread createThreadSync() {
        Thread thread = _thread;
        if (thread == null) {
            Thread thread2 = new Thread(this, "kotlinx.coroutines.DefaultExecutor");
            _thread = thread2;
            thread2.setContextClassLoader(getClass().getClassLoader());
            thread2.setDaemon(true);
            thread2.start();
            return thread2;
        }
        return thread;
    }

    private static final boolean isShutdownRequested$ar$ds() {
        int i = debugStatus;
        if (i != 2 && i != 3) {
            return false;
        }
        return true;
    }

    private final synchronized boolean notifyStartup() {
        if (isShutdownRequested$ar$ds()) {
            return false;
        }
        debugStatus = 1;
        notifyAll();
        return true;
    }

    private static final void shutdownError$ar$ds() {
        throw new RejectedExecutionException("DefaultExecutor was shut down. This error indicates that Dispatchers.shutdown() was invoked prior to completion of exiting coroutines, leaving coroutines in incomplete state. Please refer to Dispatchers.shutdown documentation for more details");
    }

    @Override // kotlinx.coroutines.EventLoopImplBase
    public final void enqueue(Runnable runnable) {
        if (debugStatus == 4) {
            shutdownError$ar$ds();
        }
        super.enqueue(runnable);
    }

    @Override // kotlinx.coroutines.EventLoop
    protected final Thread getThread() {
        Thread thread = _thread;
        if (thread == null) {
            return createThreadSync();
        }
        return thread;
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.Delay
    public final DisposableHandle invokeOnTimeout(long j, Runnable runnable, CoroutineContext coroutineContext) {
        return scheduleInvokeOnTimeout(j, runnable);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.EventLoop
    public final void reschedule(long j, EventLoopImplBase.DelayedTask delayedTask) {
        shutdownError$ar$ds();
    }

    @Override // java.lang.Runnable
    public final void run() {
        ThreadLocalEventLoop threadLocalEventLoop = ThreadLocalEventLoop.INSTANCE;
        ThreadLocalEventLoop.ref.set(this);
        try {
            if (notifyStartup()) {
                long j = Long.MAX_VALUE;
                while (true) {
                    Thread.interrupted();
                    long processNextEvent = processNextEvent();
                    if (processNextEvent == Long.MAX_VALUE) {
                        long nanoTime = System.nanoTime();
                        if (j == Long.MAX_VALUE) {
                            j = KEEP_ALIVE_NANOS + nanoTime;
                        }
                        long j2 = j - nanoTime;
                        if (j2 <= 0) {
                            break;
                        } else {
                            processNextEvent = OnDeviceSmartReplyLogEvent.SmartReply.coerceAtMost(Long.MAX_VALUE, j2);
                        }
                    } else {
                        j = Long.MAX_VALUE;
                    }
                    if (processNextEvent > 0) {
                        if (isShutdownRequested$ar$ds()) {
                            break;
                        } else {
                            LockSupport.parkNanos(this, processNextEvent);
                        }
                    }
                }
            }
            _thread = null;
            acknowledgeShutdownIfNeeded();
            if (!isEmpty()) {
                getThread();
            }
        } catch (Throwable th) {
            _thread = null;
            acknowledgeShutdownIfNeeded();
            if (!isEmpty()) {
                getThread();
            }
            throw th;
        }
    }

    @Override // kotlinx.coroutines.EventLoopImplBase, kotlinx.coroutines.EventLoop
    public final void shutdown() {
        debugStatus = 4;
        super.shutdown();
    }
}
