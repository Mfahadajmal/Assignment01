package com.google.common.util.concurrent;

import com.google.common.util.concurrent.ExecutionList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ListenableFutureTask extends FutureTask implements ListenableFuture {
    private final ExecutionList executionList;

    public ListenableFutureTask(Callable callable) {
        super(callable);
        this.executionList = new ExecutionList();
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public final void addListener(Runnable runnable, Executor executor) {
        executor.getClass();
        ExecutionList executionList = this.executionList;
        synchronized (executionList) {
            if (!executionList.executed) {
                executionList.runnables = new ExecutionList.RunnableExecutorPair(runnable, executor, executionList.runnables);
            } else {
                ExecutionList.executeListener(runnable, executor);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.util.concurrent.Executor, java.lang.Object] */
    @Override // java.util.concurrent.FutureTask
    protected final void done() {
        ExecutionList executionList = this.executionList;
        synchronized (executionList) {
            if (executionList.executed) {
                return;
            }
            executionList.executed = true;
            Object obj = executionList.runnables;
            Object obj2 = null;
            executionList.runnables = null;
            while (obj != null) {
                ExecutionList.RunnableExecutorPair runnableExecutorPair = (ExecutionList.RunnableExecutorPair) obj;
                Object obj3 = runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next;
                runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next = obj2;
                obj2 = obj;
                obj = obj3;
            }
            while (obj2 != null) {
                ExecutionList.RunnableExecutorPair runnableExecutorPair2 = (ExecutionList.RunnableExecutorPair) obj2;
                ExecutionList.executeListener(runnableExecutorPair2.ExecutionList$RunnableExecutorPair$ar$runnable, runnableExecutorPair2.ExecutionList$RunnableExecutorPair$ar$executor);
                obj2 = runnableExecutorPair2.ExecutionList$RunnableExecutorPair$ar$next;
            }
        }
    }

    @Override // java.util.concurrent.FutureTask, java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) {
        long nanos = timeUnit.toNanos(j);
        if (nanos <= 2147483647999999999L) {
            return super.get(j, timeUnit);
        }
        return super.get(Math.min(nanos, 2147483647999999999L), TimeUnit.NANOSECONDS);
    }
}
