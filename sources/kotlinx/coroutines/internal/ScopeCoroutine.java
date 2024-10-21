package kotlinx.coroutines.internal;

import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlinx.coroutines.AbstractCoroutine;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ScopeCoroutine extends AbstractCoroutine implements CoroutineStackFrame {
    public final Continuation uCont;

    public ScopeCoroutine(CoroutineContext coroutineContext, Continuation continuation) {
        super(coroutineContext, true);
        this.uCont = continuation;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.JobSupport
    public void afterCompletion(Object obj) {
        Continuation continuation = this.uCont;
        DispatchedContinuationKt.resumeCancellableWith$ar$ds(OnDevicePoseDetectionLogEvent.intercepted(continuation), OnDeviceTranslationLogEvent.recoverResult(obj, continuation));
    }

    @Override // kotlinx.coroutines.AbstractCoroutine
    protected void afterResume(Object obj) {
        Continuation continuation = this.uCont;
        continuation.resumeWith(OnDeviceTranslationLogEvent.recoverResult(obj, continuation));
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.coroutines.Continuation, kotlin.coroutines.jvm.internal.CoroutineStackFrame] */
    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        ?? r0 = this.uCont;
        if (r0 instanceof CoroutineStackFrame) {
            return r0;
        }
        return null;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlinx.coroutines.JobSupport
    protected final boolean isScopedCoroutine() {
        return true;
    }
}
