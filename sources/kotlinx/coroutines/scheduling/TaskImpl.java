package kotlinx.coroutines.scheduling;

import kotlinx.coroutines.DebugStringsKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskImpl extends Task {
    public final Runnable block;

    public TaskImpl(Runnable runnable, long j, TaskContextImpl taskContextImpl) {
        super(j, taskContextImpl);
        this.block = runnable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.block.run();
    }

    public final String toString() {
        Runnable runnable = this.block;
        return "Task[" + DebugStringsKt.getClassSimpleName(runnable) + "@" + DebugStringsKt.getHexAddress(runnable) + ", " + this.submissionTime + ", " + this.taskContext$ar$class_merging + "]";
    }
}
