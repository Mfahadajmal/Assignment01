package com.google.common.util.concurrent;

import _COROUTINE._BOUNDARY;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimeoutFuture extends FluentFuture$TrustedFuture {
    public ListenableFuture delegateRef;
    public ScheduledFuture timer;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Fire implements Runnable {
        TimeoutFuture timeoutFutureRef;

        public Fire(TimeoutFuture timeoutFuture) {
            this.timeoutFutureRef = timeoutFuture;
        }

        @Override // java.lang.Runnable
        public final void run() {
            ListenableFuture listenableFuture;
            TimeoutFuture timeoutFuture = this.timeoutFutureRef;
            if (timeoutFuture != null && (listenableFuture = timeoutFuture.delegateRef) != null) {
                this.timeoutFutureRef = null;
                if (!listenableFuture.isDone()) {
                    try {
                        ScheduledFuture scheduledFuture = timeoutFuture.timer;
                        timeoutFuture.timer = null;
                        String str = "Timed out";
                        if (scheduledFuture != null) {
                            try {
                                long abs = Math.abs(scheduledFuture.getDelay(TimeUnit.MILLISECONDS));
                                if (abs > 10) {
                                    str = "Timed out (timeout delayed by " + abs + " ms after scheduled time)";
                                }
                            } catch (Throwable th) {
                                timeoutFuture.setException(new TimeoutFutureException(str));
                                throw th;
                            }
                        }
                        timeoutFuture.setException(new TimeoutFutureException(str + ": " + listenableFuture.toString()));
                        return;
                    } finally {
                        listenableFuture.cancel(true);
                    }
                }
                timeoutFuture.setFuture(listenableFuture);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class TimeoutFutureException extends TimeoutException {
        public TimeoutFutureException(String str) {
            super(str);
        }

        @Override // java.lang.Throwable
        public final synchronized Throwable fillInStackTrace() {
            setStackTrace(new StackTraceElement[0]);
            return this;
        }
    }

    public TimeoutFuture(ListenableFuture listenableFuture) {
        this.delegateRef = listenableFuture;
    }

    @Override // com.google.common.util.concurrent.AbstractFuture
    protected final void afterDone() {
        maybePropagateCancellationTo(this.delegateRef);
        ScheduledFuture scheduledFuture = this.timer;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.delegateRef = null;
        this.timer = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.AbstractFuture
    public final String pendingToString() {
        ListenableFuture listenableFuture = this.delegateRef;
        ScheduledFuture scheduledFuture = this.timer;
        if (listenableFuture != null) {
            String _BOUNDARY$ar$MethodOutlining$dc56d17a_10 = _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(listenableFuture, "inputFuture=[", "]");
            if (scheduledFuture != null) {
                long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
                if (delay > 0) {
                    return _BOUNDARY$ar$MethodOutlining$dc56d17a_10 + ", remaining delay=[" + delay + " ms]";
                }
                return _BOUNDARY$ar$MethodOutlining$dc56d17a_10;
            }
            return _BOUNDARY$ar$MethodOutlining$dc56d17a_10;
        }
        return null;
    }
}
