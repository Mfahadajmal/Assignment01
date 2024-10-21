package com.google.common.util.concurrent;

import com.google.android.accessibility.brailleime.BrailleIme$21$$ExternalSyntheticLambda1;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.LockSupport;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class InterruptibleTask extends AtomicReference implements Runnable {
    private static final Runnable DONE;
    private static final Runnable PARKED;

    static {
        int i = 8;
        DONE = new BrailleIme$21$$ExternalSyntheticLambda1(i);
        PARKED = new BrailleIme$21$$ExternalSyntheticLambda1(i);
    }

    private final void waitForInterrupt(Thread thread) {
        Runnable runnable = (Runnable) get();
        Blocker blocker = null;
        boolean z = false;
        int i = 0;
        while (true) {
            if (!(runnable instanceof Blocker)) {
                if (runnable != PARKED) {
                    break;
                }
            } else {
                blocker = (Blocker) runnable;
            }
            i++;
            if (i > 1000) {
                Runnable runnable2 = PARKED;
                if (runnable == runnable2 || compareAndSet(runnable, runnable2)) {
                    if (!Thread.interrupted() && !z) {
                        z = false;
                    } else {
                        z = true;
                    }
                    LockSupport.park(blocker);
                }
            } else {
                Thread.yield();
            }
            runnable = (Runnable) get();
        }
        if (z) {
            thread.interrupt();
        }
    }

    public abstract void afterRanInterruptiblyFailure(Throwable th);

    public abstract void afterRanInterruptiblySuccess(Object obj);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void interruptTask() {
        Runnable runnable = (Runnable) get();
        if (runnable instanceof Thread) {
            Blocker blocker = new Blocker(this);
            blocker.setOwner(Thread.currentThread());
            if (compareAndSet(runnable, blocker)) {
                try {
                    Thread thread = (Thread) runnable;
                    thread.interrupt();
                    if (((Runnable) getAndSet(DONE)) == PARKED) {
                        LockSupport.unpark(thread);
                    }
                } catch (Throwable th) {
                    if (((Runnable) getAndSet(DONE)) == PARKED) {
                        LockSupport.unpark((Thread) runnable);
                    }
                    throw th;
                }
            }
        }
    }

    public abstract boolean isDone();

    @Override // java.lang.Runnable
    public final void run() {
        Thread currentThread = Thread.currentThread();
        Object obj = null;
        if (compareAndSet(null, currentThread)) {
            boolean z = !isDone();
            if (z) {
                try {
                    obj = runInterruptibly();
                } catch (Throwable th) {
                    try {
                        ContextDataProvider.restoreInterruptIfIsInterruptedException(th);
                        if (!compareAndSet(currentThread, DONE)) {
                            waitForInterrupt(currentThread);
                        }
                        afterRanInterruptiblyFailure(th);
                        return;
                    } catch (Throwable th2) {
                        if (!compareAndSet(currentThread, DONE)) {
                            waitForInterrupt(currentThread);
                        }
                        afterRanInterruptiblySuccess(null);
                        throw th2;
                    }
                }
            }
            if (!compareAndSet(currentThread, DONE)) {
                waitForInterrupt(currentThread);
            }
            if (z) {
                afterRanInterruptiblySuccess(obj);
            }
        }
    }

    public abstract Object runInterruptibly();

    public abstract String toPendingString();

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String str;
        Runnable runnable = (Runnable) get();
        if (runnable == DONE) {
            str = "running=[DONE]";
        } else if (runnable instanceof Blocker) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = "running=[RUNNING ON " + ((Thread) runnable).getName() + "]";
        } else {
            str = "running=[NOT STARTED YET]";
        }
        return str + ", " + toPendingString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Blocker extends AbstractOwnableSynchronizer implements Runnable {
        private final InterruptibleTask task;

        public Blocker(InterruptibleTask interruptibleTask) {
            this.task = interruptibleTask;
        }

        public final void setOwner(Thread thread) {
            super.setExclusiveOwnerThread(thread);
        }

        public final String toString() {
            return this.task.toString();
        }

        @Override // java.lang.Runnable
        public final void run() {
        }
    }
}
