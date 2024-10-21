package androidx.work.impl;

import java.util.concurrent.CancellationException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WorkerStoppedException extends CancellationException {
    public final int reason;

    public WorkerStoppedException(int i) {
        this.reason = i;
    }
}
