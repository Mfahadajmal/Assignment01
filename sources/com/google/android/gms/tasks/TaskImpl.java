package com.google.android.gms.tasks;

import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import io.grpc.internal.RetriableStream;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskImpl extends Task {
    public volatile boolean canceled;
    public boolean complete;
    public Exception exception;
    private Object result;
    public final Object lock = new Object();
    public final RetriableStream.FutureCanceller listenerQueue$ar$class_merging = new RetriableStream.FutureCanceller();

    private final void checkNotCompleteLocked() {
        String str;
        if (this.complete) {
            if (isComplete()) {
                Exception exception = getException();
                if (exception == null) {
                    if (!isSuccessful()) {
                        if (this.canceled) {
                            str = "cancellation";
                        } else {
                            str = "unknown issue";
                        }
                    } else {
                        str = "result ".concat(String.valueOf(String.valueOf(getResult())));
                    }
                } else {
                    str = "failure";
                }
                throw new DuplicateTaskCompletionException("Complete with: ".concat(str), exception);
            }
            throw new IllegalStateException("DuplicateTaskCompletionException can only be created from completed Task.");
        }
    }

    @Override // com.google.android.gms.tasks.Task
    public final void addOnCompleteListener$ar$ds(OnCompleteListener onCompleteListener) {
        addOnCompleteListener$ar$ds$6dfdfa2c_0(TaskExecutors.MAIN_THREAD, onCompleteListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public final void addOnCompleteListener$ar$ds$6dfdfa2c_0(Executor executor, OnCompleteListener onCompleteListener) {
        this.listenerQueue$ar$class_merging.add(new OnCanceledCompletionListener(TaskTracing.traceExecutor(executor), onCompleteListener, 2));
        flushIfComplete();
    }

    @Override // com.google.android.gms.tasks.Task
    public final void addOnFailureListener$ar$ds(OnFailureListener onFailureListener) {
        addOnFailureListener$ar$ds$7efc8a85_0(TaskExecutors.MAIN_THREAD, onFailureListener);
    }

    @Override // com.google.android.gms.tasks.Task
    public final void addOnFailureListener$ar$ds$7efc8a85_0(Executor executor, OnFailureListener onFailureListener) {
        this.listenerQueue$ar$class_merging.add(new OnCanceledCompletionListener(TaskTracing.traceExecutor(executor), onFailureListener, 3));
        flushIfComplete();
    }

    @Override // com.google.android.gms.tasks.Task
    public final Task addOnSuccessListener(OnSuccessListener onSuccessListener) {
        addOnSuccessListener$ar$ds(TaskExecutors.MAIN_THREAD, onSuccessListener);
        return this;
    }

    @Override // com.google.android.gms.tasks.Task
    public final void addOnSuccessListener$ar$ds(Executor executor, OnSuccessListener onSuccessListener) {
        this.listenerQueue$ar$class_merging.add(new OnCanceledCompletionListener(TaskTracing.traceExecutor(executor), onSuccessListener, 4));
        flushIfComplete();
    }

    public final void flushIfComplete() {
        synchronized (this.lock) {
            if (!this.complete) {
                return;
            }
            this.listenerQueue$ar$class_merging.flush(this);
        }
    }

    @Override // com.google.android.gms.tasks.Task
    public final Exception getException() {
        Exception exc;
        synchronized (this.lock) {
            exc = this.exception;
        }
        return exc;
    }

    @Override // com.google.android.gms.tasks.Task
    public final Object getResult() {
        Object obj;
        synchronized (this.lock) {
            StrictModeUtils$VmPolicyBuilderCompatS.checkState(this.complete, "Task is not yet complete");
            if (!this.canceled) {
                Exception exc = this.exception;
                if (exc == null) {
                    obj = this.result;
                } else {
                    throw new RuntimeExecutionException(exc);
                }
            } else {
                throw new CancellationException("Task is already canceled.");
            }
        }
        return obj;
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean isComplete() {
        boolean z;
        synchronized (this.lock) {
            z = this.complete;
        }
        return z;
    }

    @Override // com.google.android.gms.tasks.Task
    public final boolean isSuccessful() {
        boolean z;
        synchronized (this.lock) {
            z = false;
            if (this.complete && !this.canceled && this.exception == null) {
                z = true;
            }
        }
        return z;
    }

    public final void setException(Exception exc) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$4e7b8cd1_1(exc, "Exception must not be null");
        synchronized (this.lock) {
            checkNotCompleteLocked();
            this.complete = true;
            this.exception = exc;
        }
        this.listenerQueue$ar$class_merging.flush(this);
    }

    public final void setResult(Object obj) {
        synchronized (this.lock) {
            checkNotCompleteLocked();
            this.complete = true;
            this.result = obj;
        }
        this.listenerQueue$ar$class_merging.flush(this);
    }

    public final void trySetCanceled$ar$ds() {
        synchronized (this.lock) {
            if (this.complete) {
                return;
            }
            this.complete = true;
            this.canceled = true;
            this.listenerQueue$ar$class_merging.flush(this);
        }
    }

    public final boolean trySetResult(Object obj) {
        synchronized (this.lock) {
            if (this.complete) {
                return false;
            }
            this.complete = true;
            this.result = obj;
            this.listenerQueue$ar$class_merging.flush(this);
            return true;
        }
    }
}
