package kotlinx.coroutines.scheduling;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskContextImpl {
    private final int taskMode;

    public TaskContextImpl(int i) {
        this.taskMode = i;
    }

    public final int getTaskMode() {
        return this.taskMode;
    }
}
