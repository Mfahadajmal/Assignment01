package com.google.android.libraries.performance.primes;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import androidx.work.impl.WorkerWrapper$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleTracker$Callbacks;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.common.base.Optional;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import j$.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DeferrableExecutor implements Executor, AppLifecycleListener {
    private final Provider deferAfterMainLooperIdle;
    public final ListeningScheduledExecutorService executor;
    private final AppLifecycleMonitor lifecycleMonitor;
    private final ConcurrentLinkedQueue tasks = new ConcurrentLinkedQueue();
    private volatile boolean canExecute = false;
    private final AtomicBoolean maxDelayScheduled = new AtomicBoolean();

    public DeferrableExecutor(ListeningScheduledExecutorService listeningScheduledExecutorService, AppLifecycleMonitor appLifecycleMonitor, Provider provider) {
        this.executor = listeningScheduledExecutorService;
        this.lifecycleMonitor = appLifecycleMonitor;
        this.deferAfterMainLooperIdle = provider;
        Object obj = ((StatsStorage) appLifecycleMonitor.AppLifecycleMonitor$ar$tracker).StatsStorage$ar$storage;
        int i = AppLifecycleTracker$Callbacks.AppLifecycleTracker$Callbacks$ar$NoOp;
        if (((AppLifecycleTracker$Callbacks) obj).resumedCount.get() > 0) {
            onFirstResume();
        } else {
            appLifecycleMonitor.register(this);
        }
    }

    private final void drainPending() {
        while (true) {
            Runnable runnable = (Runnable) this.tasks.poll();
            if (runnable != null) {
                this.executor.execute(runnable);
            } else {
                return;
            }
        }
    }

    private final void onFirstResume() {
        this.executor.schedule(new WorkerWrapper$$ExternalSyntheticLambda0(this, 8), 3000L, TimeUnit.MILLISECONDS);
    }

    @Override // java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        if (this.canExecute) {
            this.executor.execute(runnable);
            return;
        }
        this.tasks.add(runnable);
        if (this.canExecute) {
            drainPending();
        } else if (!this.maxDelayScheduled.getAndSet(true)) {
            if (((Boolean) ((Optional) this.deferAfterMainLooperIdle.get()).or((Object) false)).booleanValue()) {
                Looper.getMainLooper().getQueue().addIdleHandler(new MessageQueue.IdleHandler() { // from class: com.google.android.libraries.performance.primes.DeferrableExecutor$$ExternalSyntheticLambda0
                    @Override // android.os.MessageQueue.IdleHandler
                    public final boolean queueIdle() {
                        DeferrableExecutor deferrableExecutor = DeferrableExecutor.this;
                        deferrableExecutor.executor.schedule(new WorkerWrapper$$ExternalSyntheticLambda0(deferrableExecutor, 7), 7000L, TimeUnit.MILLISECONDS);
                        return false;
                    }
                });
            } else {
                this.executor.schedule(new WorkerWrapper$$ExternalSyntheticLambda0(this, 7), 7000L, TimeUnit.MILLISECONDS);
            }
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final void onActivityResumed(Activity activity) {
        this.lifecycleMonitor.unregister(this);
        onFirstResume();
    }

    public final void unblock() {
        this.canExecute = true;
        drainPending();
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivityDestroyed(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivityPaused(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivityStarted(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivityStopped(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onTrimMemory(int i) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}
