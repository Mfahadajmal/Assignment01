package org.chromium.net.urlconnection;

import java.io.InterruptedIOException;
import java.net.SocketTimeoutException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
final class MessageLoop implements Executor {
    private boolean mLoopFailed;
    private boolean mLoopRunning;
    private InterruptedIOException mPriorInterruptedIOException;
    private RuntimeException mPriorRuntimeException;
    private long mThreadId = -1;
    private final BlockingQueue mQueue = new LinkedBlockingQueue();

    private final Runnable take(boolean z, long j) {
        Runnable runnable;
        try {
            if (!z) {
                runnable = (Runnable) this.mQueue.take();
            } else {
                runnable = (Runnable) this.mQueue.poll(j, TimeUnit.NANOSECONDS);
            }
            if (runnable != null) {
                return runnable;
            }
            throw new SocketTimeoutException();
        } catch (InterruptedException e) {
            InterruptedIOException interruptedIOException = new InterruptedIOException();
            interruptedIOException.initCause(e);
            throw interruptedIOException;
        }
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        if (runnable != null) {
            try {
                this.mQueue.put(runnable);
                return;
            } catch (InterruptedException e) {
                throw new RejectedExecutionException(e);
            }
        }
        throw new IllegalArgumentException();
    }

    public final void loop() {
        loop(0);
    }

    public final void quit() {
        this.mLoopRunning = false;
    }

    public final void loop(int i) {
        long nanoTime = System.nanoTime();
        long convert = TimeUnit.NANOSECONDS.convert(i, TimeUnit.MILLISECONDS);
        if (this.mLoopFailed) {
            InterruptedIOException interruptedIOException = this.mPriorInterruptedIOException;
            if (interruptedIOException != null) {
                throw interruptedIOException;
            }
            throw this.mPriorRuntimeException;
        }
        if (!this.mLoopRunning) {
            this.mLoopRunning = true;
            while (this.mLoopRunning) {
                if (i == 0) {
                    try {
                        take(false, 0L).run();
                    } catch (InterruptedIOException e) {
                        this.mLoopRunning = false;
                        this.mLoopFailed = true;
                        this.mPriorInterruptedIOException = e;
                        throw e;
                    } catch (RuntimeException e2) {
                        this.mLoopRunning = false;
                        this.mLoopFailed = true;
                        this.mPriorRuntimeException = e2;
                        throw e2;
                    }
                } else {
                    take(true, (convert - System.nanoTime()) + nanoTime).run();
                }
            }
            return;
        }
        throw new IllegalStateException("Cannot run loop when it is already running.");
    }
}
