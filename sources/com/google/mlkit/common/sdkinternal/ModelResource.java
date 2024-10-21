package com.google.mlkit.common.sdkinternal;

import com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskImpl;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ModelResource {
    public final TaskQueue taskQueue;
    public final AtomicInteger refCount = new AtomicInteger(0);
    public final AtomicBoolean isLoaded = new AtomicBoolean(false);

    /* JADX INFO: Access modifiers changed from: protected */
    public ModelResource(TaskQueue taskQueue) {
        this.taskQueue = taskQueue;
    }

    public final Task callAfterLoad$ar$class_merging$ar$class_merging(final Executor executor, Callable callable, final AppLifecycleMonitor appLifecycleMonitor) {
        boolean z;
        if (this.refCount.get() > 0) {
            z = true;
        } else {
            z = false;
        }
        StrictModeUtils$VmPolicyBuilderCompatS.checkState(z);
        if (appLifecycleMonitor.isCancellationRequested()) {
            TaskImpl taskImpl = new TaskImpl();
            taskImpl.trySetCanceled$ar$ds();
            return taskImpl;
        }
        final AppLifecycleMonitor appLifecycleMonitor2 = new AppLifecycleMonitor((byte[]) null, (char[]) null);
        final AppLifecycleMonitor appLifecycleMonitor3 = new AppLifecycleMonitor((AppLifecycleMonitor) appLifecycleMonitor2.AppLifecycleMonitor$ar$tracker);
        this.taskQueue.submit(new Executor() { // from class: com.google.mlkit.common.sdkinternal.ModelResource$$ExternalSyntheticLambda1
            @Override // java.util.concurrent.Executor
            public final void execute(Runnable runnable) {
                try {
                    executor.execute(runnable);
                } catch (RuntimeException e) {
                    if (appLifecycleMonitor.isCancellationRequested()) {
                        appLifecycleMonitor2.cancel();
                    } else {
                        appLifecycleMonitor3.setException(e);
                    }
                    throw e;
                }
            }
        }, new ModelResource$$ExternalSyntheticLambda2(this, appLifecycleMonitor, appLifecycleMonitor2, callable, appLifecycleMonitor3, 0));
        return (Task) appLifecycleMonitor3.AppLifecycleMonitor$ar$tracker;
    }

    public abstract void load();

    public abstract void release();
}
