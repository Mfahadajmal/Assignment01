package kotlinx.coroutines.scheduling;

import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SchedulerCoroutineDispatcher extends ExecutorCoroutineDispatcher {
    private final int corePoolSize;
    private final CoroutineScheduler coroutineScheduler;
    private final long idleWorkerKeepAliveNs;
    private final int maxPoolSize;
    private final String schedulerName;

    public SchedulerCoroutineDispatcher(int i, int i2, long j, String str) {
        this.corePoolSize = i;
        this.maxPoolSize = i2;
        this.idleWorkerKeepAliveNs = j;
        this.schedulerName = str;
        this.coroutineScheduler = new CoroutineScheduler(i, i2, j, str);
    }

    public void close() {
        this.coroutineScheduler.close();
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        CoroutineScheduler.dispatch$default$ar$class_merging$ar$ds(this.coroutineScheduler, runnable, false, 6);
    }

    public final void dispatchWithContext$kotlinx_coroutines_core$ar$class_merging(Runnable runnable, TaskContextImpl taskContextImpl, boolean z) {
        this.coroutineScheduler.dispatch$ar$class_merging(runnable, taskContextImpl, z);
    }

    @Override // kotlinx.coroutines.CoroutineDispatcher
    public final void dispatchYield(CoroutineContext coroutineContext, Runnable runnable) {
        CoroutineScheduler.dispatch$default$ar$class_merging$ar$ds(this.coroutineScheduler, runnable, true, 2);
    }
}
