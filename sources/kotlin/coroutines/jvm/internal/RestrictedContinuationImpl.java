package kotlin.coroutines.jvm.internal;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class RestrictedContinuationImpl extends BaseContinuationImpl {
    public RestrictedContinuationImpl(Continuation continuation) {
        super(continuation);
        if (continuation != null && continuation.getContext() != EmptyCoroutineContext.INSTANCE) {
            throw new IllegalArgumentException("Coroutines with restricted suspension must have EmptyCoroutineContext");
        }
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }
}
