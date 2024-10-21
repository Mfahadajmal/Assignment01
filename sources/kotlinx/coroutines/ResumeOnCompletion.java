package kotlinx.coroutines;

import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ResumeOnCompletion extends JobNode {
    private final Continuation continuation;

    public ResumeOnCompletion(Continuation continuation) {
        this.continuation = continuation;
    }

    @Override // kotlinx.coroutines.InternalCompletionHandler
    public final void invoke(Throwable th) {
        this.continuation.resumeWith(Unit.INSTANCE);
    }
}
