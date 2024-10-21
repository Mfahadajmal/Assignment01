package com.google.android.play.core.serviceconnection;

import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class SafeRunnable implements Runnable {
    public final AppLifecycleMonitor source$ar$class_merging$6cd5309_0$ar$class_merging;

    public SafeRunnable() {
        this.source$ar$class_merging$6cd5309_0$ar$class_merging = null;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            unsafeRun();
        } catch (Exception e) {
            setException(e);
        }
    }

    public final void setException(Exception exc) {
        AppLifecycleMonitor appLifecycleMonitor = this.source$ar$class_merging$6cd5309_0$ar$class_merging;
        if (appLifecycleMonitor != null) {
            appLifecycleMonitor.trySetException(exc);
        }
    }

    protected abstract void unsafeRun();

    public SafeRunnable(AppLifecycleMonitor appLifecycleMonitor) {
        this.source$ar$class_merging$6cd5309_0$ar$class_merging = appLifecycleMonitor;
    }
}
