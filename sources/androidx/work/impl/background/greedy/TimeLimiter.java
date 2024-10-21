package androidx.work.impl.background.greedy;

import androidx.lifecycle.ViewModelStore;
import androidx.work.impl.background.greedy.DelayedWorkTracker;
import androidx.work.impl.model.WorkName;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimeLimiter {
    public final WorkName launcher$ar$class_merging$ar$class_merging;
    private final Object lock;
    private final ViewModelStore runnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final long timeoutMs;
    private final Map tracked;

    public TimeLimiter(ViewModelStore viewModelStore, WorkName workName) {
        long millis = TimeUnit.MINUTES.toMillis(90L);
        this.runnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = viewModelStore;
        this.launcher$ar$class_merging$ar$class_merging = workName;
        this.timeoutMs = millis;
        this.lock = new Object();
        this.tracked = new LinkedHashMap();
    }

    public final void cancel$ar$class_merging$ar$class_merging(ViewModelStore viewModelStore) {
        Runnable runnable;
        viewModelStore.getClass();
        synchronized (this.lock) {
            runnable = (Runnable) this.tracked.remove(viewModelStore);
        }
        if (runnable != null) {
            this.runnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.cancel(runnable);
        }
    }

    public final void track$ar$class_merging$ar$class_merging(ViewModelStore viewModelStore) {
        DelayedWorkTracker.AnonymousClass1 anonymousClass1 = new DelayedWorkTracker.AnonymousClass1(this, viewModelStore, 2, (char[]) null);
        synchronized (this.lock) {
        }
        this.runnableScheduler$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.scheduleWithDelay(this.timeoutMs, anonymousClass1);
    }
}
