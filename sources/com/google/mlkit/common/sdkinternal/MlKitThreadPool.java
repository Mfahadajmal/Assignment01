package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.common.util.concurrent.ForwardingExecutorService;
import com.google.common.util.concurrent.Futures$$ExternalSyntheticLambda1;
import java.util.Deque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MlKitThreadPool extends ForwardingExecutorService {
    public static final ThreadLocal queuedRunnables = new ThreadLocal();
    private final ThreadPoolExecutor threadPool;

    public MlKitThreadPool() {
        final ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(availableProcessors, availableProcessors, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.google.mlkit.common.sdkinternal.MlKitThreadPool$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return defaultThreadFactory.newThread(new Futures$$ExternalSyntheticLambda1(runnable, 7));
            }
        });
        this.threadPool = threadPoolExecutor;
        threadPoolExecutor.allowCoreThreadTimeOut(true);
    }

    public static void runOnThisThread(Deque deque, Runnable runnable) {
        StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(deque);
        deque.add(runnable);
        if (deque.size() > 1) {
            return;
        }
        do {
            runnable.run();
            deque.removeFirst();
            runnable = (Runnable) deque.peekFirst();
        } while (runnable != null);
    }

    @Override // com.google.common.util.concurrent.ForwardingExecutorService, com.google.common.collect.ForwardingObject
    protected final /* synthetic */ Object delegate() {
        return this.threadPool;
    }

    @Override // com.google.common.util.concurrent.ForwardingExecutorService, java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        Deque deque = (Deque) queuedRunnables.get();
        if (deque != null && deque.size() <= 1) {
            runOnThisThread(deque, runnable);
        } else {
            this.threadPool.execute(new Futures$$ExternalSyntheticLambda1(runnable, 6));
        }
    }

    @Override // com.google.common.util.concurrent.ForwardingExecutorService, com.google.common.collect.ForwardingObject
    protected final ExecutorService delegate() {
        return this.threadPool;
    }
}
