package com.google.mlkit.common.sdkinternal;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.gms.libs.punchclock.threads.TracingHandler;
import com.google.android.gms.tasks.Task;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.firebase.components.EventBus$$ExternalSyntheticLambda0;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MLTaskExecutor {
    private static MLTaskExecutor instance;
    private static final Object lock = new Object();
    public final Handler handler;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class WorkerThreadExecutor implements Executor {
        private static final /* synthetic */ WorkerThreadExecutor[] $VALUES;
        public static final WorkerThreadExecutor INSTANCE;

        static {
            WorkerThreadExecutor workerThreadExecutor = new WorkerThreadExecutor();
            INSTANCE = workerThreadExecutor;
            $VALUES = new WorkerThreadExecutor[]{workerThreadExecutor};
        }

        private WorkerThreadExecutor() {
        }

        public static WorkerThreadExecutor[] values() {
            return (WorkerThreadExecutor[]) $VALUES.clone();
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            MLTaskExecutor.getInstance().handler.post(runnable);
        }
    }

    private MLTaskExecutor(Looper looper) {
        this.handler = new TracingHandler(looper);
    }

    public static MLTaskExecutor getInstance() {
        MLTaskExecutor mLTaskExecutor;
        synchronized (lock) {
            if (instance == null) {
                HandlerThread handlerThread = new HandlerThread("MLHandler", 9);
                handlerThread.start();
                instance = new MLTaskExecutor(handlerThread.getLooper());
            }
            mLTaskExecutor = instance;
        }
        return mLTaskExecutor;
    }

    public final Task scheduleCallable(Callable callable) {
        AppLifecycleMonitor appLifecycleMonitor = new AppLifecycleMonitor((short[]) null);
        WorkerThreadExecutor.INSTANCE.execute(new EventBus$$ExternalSyntheticLambda0(callable, appLifecycleMonitor, 17));
        return (Task) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker;
    }
}
