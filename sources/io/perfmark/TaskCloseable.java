package io.perfmark;

import java.io.Closeable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TaskCloseable implements Closeable {
    static final TaskCloseable INSTANCE = new TaskCloseable(0);
    private final /* synthetic */ int switching_field;

    private TaskCloseable(int i) {
        this.switching_field = i;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        if (this.switching_field == 0) {
            int i = PerfMark.PerfMark$ar$NoOp;
            return;
        }
        throw null;
    }
}
