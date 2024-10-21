package com.google.common.util.concurrent;

import _COROUTINE._BOUNDARY;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SequentialExecutor implements Executor {
    public static final LazyLogger log = new LazyLogger(SequentialExecutor.class);
    private final Executor executor;
    public final Deque queue = new ArrayDeque();
    public int workerRunningState$ar$edu = 1;
    public long workerRunCount = 0;
    private final QueueWorker worker = new QueueWorker();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class QueueWorker implements Runnable {
        Runnable task;

        public QueueWorker() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:11:0x0049, code lost:
        
            r1 = r1 | java.lang.Thread.interrupted();
            r0 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x004b, code lost:
        
            r11.task.run();
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0056, code lost:
        
            r3 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0083, code lost:
        
            r11.task = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0085, code lost:
        
            throw r3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:22:0x0058, code lost:
        
            r3 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x005a, code lost:
        
            com.google.common.util.concurrent.SequentialExecutor.log.get().logp(java.util.logging.Level.SEVERE, "com.google.common.util.concurrent.SequentialExecutor$QueueWorker", "workOnQueue", "Exception while executing runnable " + java.lang.String.valueOf(r11.task), (java.lang.Throwable) r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0080, code lost:
        
            r11.task = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x0043, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
        
            return;
         */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void run() {
            /*
                r11 = this;
                r0 = 0
                r1 = r0
            L2:
                r2 = 1
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L54
                java.util.Deque r3 = r3.queue     // Catch: java.lang.Throwable -> L54
                monitor-enter(r3)     // Catch: java.lang.Throwable -> L54
                if (r0 != 0) goto L27
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L86
                int r0 = r0.workerRunningState$ar$edu     // Catch: java.lang.Throwable -> L86
                r4 = 4
                if (r0 != r4) goto L1c
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L86
                if (r1 == 0) goto L43
                java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch: java.lang.Error -> L93
                r0.interrupt()     // Catch: java.lang.Error -> L93
                return
            L1c:
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L86
                long r5 = r0.workerRunCount     // Catch: java.lang.Throwable -> L86
                r7 = 1
                long r5 = r5 + r7
                r0.workerRunCount = r5     // Catch: java.lang.Throwable -> L86
                r0.workerRunningState$ar$edu = r4     // Catch: java.lang.Throwable -> L86
            L27:
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L86
                java.util.Deque r0 = r0.queue     // Catch: java.lang.Throwable -> L86
                java.lang.Object r0 = r0.poll()     // Catch: java.lang.Throwable -> L86
                java.lang.Runnable r0 = (java.lang.Runnable) r0     // Catch: java.lang.Throwable -> L86
                r11.task = r0     // Catch: java.lang.Throwable -> L86
                if (r0 != 0) goto L44
                com.google.common.util.concurrent.SequentialExecutor r0 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L86
                r0.workerRunningState$ar$edu = r2     // Catch: java.lang.Throwable -> L86
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L86
                if (r1 == 0) goto L43
                java.lang.Thread r0 = java.lang.Thread.currentThread()     // Catch: java.lang.Error -> L93
                r0.interrupt()     // Catch: java.lang.Error -> L93
            L43:
                return
            L44:
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L86
                boolean r0 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L54
                r1 = r1 | r0
                r0 = 0
                java.lang.Runnable r3 = r11.task     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
                r3.run()     // Catch: java.lang.Throwable -> L56 java.lang.Exception -> L58
                r11.task = r0     // Catch: java.lang.Throwable -> L54
            L52:
                r0 = r2
                goto L2
            L54:
                r0 = move-exception
                goto L89
            L56:
                r3 = move-exception
                goto L83
            L58:
                r3 = move-exception
                r9 = r3
                com.google.common.util.concurrent.LazyLogger r3 = com.google.common.util.concurrent.SequentialExecutor.log     // Catch: java.lang.Throwable -> L56
                java.util.logging.Logger r4 = r3.get()     // Catch: java.lang.Throwable -> L56
                java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L56
                java.lang.String r6 = "com.google.common.util.concurrent.SequentialExecutor$QueueWorker"
                java.lang.String r7 = "workOnQueue"
                java.lang.Runnable r3 = r11.task     // Catch: java.lang.Throwable -> L56
                java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L56
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L56
                r8.<init>()     // Catch: java.lang.Throwable -> L56
                java.lang.String r10 = "Exception while executing runnable "
                r8.append(r10)     // Catch: java.lang.Throwable -> L56
                r8.append(r3)     // Catch: java.lang.Throwable -> L56
                java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> L56
                r4.logp(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L56
                r11.task = r0     // Catch: java.lang.Throwable -> L54
                goto L52
            L83:
                r11.task = r0     // Catch: java.lang.Throwable -> L54
                throw r3     // Catch: java.lang.Throwable -> L54
            L86:
                r0 = move-exception
                monitor-exit(r3)     // Catch: java.lang.Throwable -> L86
                throw r0     // Catch: java.lang.Throwable -> L54
            L89:
                if (r1 == 0) goto L92
                java.lang.Thread r1 = java.lang.Thread.currentThread()     // Catch: java.lang.Error -> L93
                r1.interrupt()     // Catch: java.lang.Error -> L93
            L92:
                throw r0     // Catch: java.lang.Error -> L93
            L93:
                r0 = move-exception
                com.google.common.util.concurrent.SequentialExecutor r1 = com.google.common.util.concurrent.SequentialExecutor.this
                java.util.Deque r1 = r1.queue
                monitor-enter(r1)
                com.google.common.util.concurrent.SequentialExecutor r3 = com.google.common.util.concurrent.SequentialExecutor.this     // Catch: java.lang.Throwable -> L9f
                r3.workerRunningState$ar$edu = r2     // Catch: java.lang.Throwable -> L9f
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L9f
                throw r0
            L9f:
                r0 = move-exception
                monitor-exit(r1)     // Catch: java.lang.Throwable -> L9f
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SequentialExecutor.QueueWorker.run():void");
        }

        public final String toString() {
            String str;
            Runnable runnable = this.task;
            if (runnable != null) {
                return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_10(runnable, "SequentialExecutorWorker{running=", "}");
            }
            int i = SequentialExecutor.this.workerRunningState$ar$edu;
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            str = "null";
                        } else {
                            str = "RUNNING";
                        }
                    } else {
                        str = "QUEUED";
                    }
                } else {
                    str = "QUEUING";
                }
            } else {
                str = "IDLE";
            }
            return _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str, "SequentialExecutorWorker{state=", "}");
        }
    }

    public SequentialExecutor(Executor executor) {
        executor.getClass();
        this.executor = executor;
    }

    @Override // java.util.concurrent.Executor
    public final void execute(final Runnable runnable) {
        runnable.getClass();
        synchronized (this.queue) {
            int i = this.workerRunningState$ar$edu;
            if (i != 4 && i != 3) {
                long j = this.workerRunCount;
                Runnable runnable2 = new Runnable() { // from class: com.google.common.util.concurrent.SequentialExecutor.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        runnable.run();
                    }

                    public final String toString() {
                        return runnable.toString();
                    }
                };
                this.queue.add(runnable2);
                this.workerRunningState$ar$edu = 2;
                try {
                    this.executor.execute(this.worker);
                    if (this.workerRunningState$ar$edu != 2) {
                        return;
                    }
                    synchronized (this.queue) {
                        if (this.workerRunCount == j && this.workerRunningState$ar$edu == 2) {
                            this.workerRunningState$ar$edu = 3;
                        }
                    }
                    return;
                } catch (Throwable th) {
                    synchronized (this.queue) {
                        int i2 = this.workerRunningState$ar$edu;
                        boolean z = false;
                        if ((i2 == 1 || i2 == 2) && this.queue.removeLastOccurrence(runnable2)) {
                            z = true;
                        }
                        if ((th instanceof RejectedExecutionException) && !z) {
                            return;
                        } else {
                            throw th;
                        }
                    }
                }
            }
            this.queue.add(runnable);
        }
    }

    public final String toString() {
        Executor executor = this.executor;
        return "SequentialExecutor@" + System.identityHashCode(this) + "{" + String.valueOf(executor) + "}";
    }
}
