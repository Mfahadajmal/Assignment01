package kotlinx.coroutines;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DisposeOnCompletion extends JobNode {
    private final DisposableHandle handle;

    public DisposeOnCompletion(DisposableHandle disposableHandle) {
        this.handle = disposableHandle;
    }

    @Override // kotlinx.coroutines.InternalCompletionHandler
    public final void invoke(Throwable th) {
        this.handle.dispose();
    }
}
