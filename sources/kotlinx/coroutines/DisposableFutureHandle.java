package kotlinx.coroutines;

import java.util.concurrent.Future;

/* compiled from: PG */
/* loaded from: classes.dex */
final class DisposableFutureHandle implements DisposableHandle {
    private final Future future;

    public DisposableFutureHandle(Future future) {
        this.future = future;
    }

    @Override // kotlinx.coroutines.DisposableHandle
    public final void dispose() {
        this.future.cancel(false);
    }

    public final String toString() {
        return "DisposableFutureHandle[" + this.future + "]";
    }
}
