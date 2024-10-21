package kotlinx.coroutines;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class InvokeOnCompletion extends JobNode {
    private final InternalCompletionHandler handler;

    public InvokeOnCompletion(InternalCompletionHandler internalCompletionHandler) {
        this.handler = internalCompletionHandler;
    }

    @Override // kotlinx.coroutines.InternalCompletionHandler
    public final void invoke(Throwable th) {
        this.handler.invoke(th);
    }
}
