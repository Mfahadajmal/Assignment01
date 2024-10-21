package kotlinx.coroutines.scheduling;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class Task implements Runnable {
    public long submissionTime;
    public TaskContextImpl taskContext$ar$class_merging;

    public Task(long j, TaskContextImpl taskContextImpl) {
        this.submissionTime = j;
        this.taskContext$ar$class_merging = taskContextImpl;
    }

    public Task() {
        this(0L, TasksKt.NonBlockingContext$ar$class_merging);
    }
}
