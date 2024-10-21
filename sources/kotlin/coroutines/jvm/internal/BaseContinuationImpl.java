package kotlin.coroutines.jvm.internal;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.io.Serializable;
import java.util.Objects;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseContinuationImpl implements Continuation, CoroutineStackFrame, Serializable {
    public final Continuation completion;

    public BaseContinuationImpl(Continuation continuation) {
        this.completion = continuation;
    }

    public Continuation create(Object obj, Continuation continuation) {
        throw new UnsupportedOperationException("create(Any?;Continuation) has not been overridden");
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.completion;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public StackTraceElement getStackTraceElement() {
        return null;
    }

    protected abstract Object invokeSuspend(Object obj);

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Continuation continuation = this;
        while (true) {
            continuation.getClass();
            BaseContinuationImpl baseContinuationImpl = (BaseContinuationImpl) continuation;
            Continuation continuation2 = baseContinuationImpl.completion;
            continuation2.getClass();
            try {
                obj = baseContinuationImpl.invokeSuspend(obj);
                if (obj == CoroutineSingletons.COROUTINE_SUSPENDED) {
                    return;
                }
            } catch (Throwable th) {
                obj = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th);
            }
            baseContinuationImpl.releaseIntercepted();
            if (continuation2 instanceof BaseContinuationImpl) {
                continuation = continuation2;
            } else {
                continuation2.resumeWith(obj);
                return;
            }
        }
    }

    public String toString() {
        String name = getClass().getName();
        Objects.toString(name);
        return "Continuation at ".concat(String.valueOf(name));
    }

    protected void releaseIntercepted() {
    }
}
