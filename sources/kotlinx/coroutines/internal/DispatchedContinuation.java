package kotlinx.coroutines.internal;

import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CompletedWithCancellation;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.ThreadLocalEventLoop;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DispatchedContinuation extends DispatchedTask implements CoroutineStackFrame, Continuation {
    public final AtomicRef _reusableCancellableContinuation;
    public Object _state;
    public final Continuation continuation;
    public final Object countOrElement;
    public final CoroutineDispatcher dispatcher;

    public DispatchedContinuation(CoroutineDispatcher coroutineDispatcher, Continuation continuation) {
        super(-1);
        this.dispatcher = coroutineDispatcher;
        this.continuation = continuation;
        this._state = DispatchedContinuationKt.UNDEFINED;
        this.countOrElement = ThreadContextKt.threadContextElements(getContext());
        this._reusableCancellableContinuation = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final void cancelCompletedResult$kotlinx_coroutines_core(Object obj, Throwable th) {
        if (!(obj instanceof CompletedWithCancellation)) {
            return;
        }
        Function1 function1 = ((CompletedWithCancellation) obj).onCancellation;
        throw null;
    }

    public final void dispatchYield$kotlinx_coroutines_core(CoroutineContext coroutineContext, Object obj) {
        this._state = obj;
        this.resumeMode = 1;
        this.dispatcher.dispatchYield(coroutineContext, this);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlin.coroutines.Continuation, kotlin.coroutines.jvm.internal.CoroutineStackFrame] */
    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        return this.continuation;
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.continuation.getContext();
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final StackTraceElement getStackTraceElement() {
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        CoroutineDispatcher coroutineDispatcher = this.dispatcher;
        CoroutineContext context = this.continuation.getContext();
        Object state$default$ar$ds = OnDeviceTranslationLogEvent.toState$default$ar$ds(obj);
        if (coroutineDispatcher.isDispatchNeeded(context)) {
            this._state = state$default$ar$ds;
            this.resumeMode = 0;
            this.dispatcher.dispatch(context, this);
            return;
        }
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        ThreadLocalEventLoop threadLocalEventLoop = ThreadLocalEventLoop.INSTANCE;
        EventLoop eventLoop$kotlinx_coroutines_core$ar$ds = ThreadLocalEventLoop.getEventLoop$kotlinx_coroutines_core$ar$ds();
        if (eventLoop$kotlinx_coroutines_core$ar$ds.isUnconfinedLoopActive()) {
            this._state = state$default$ar$ds;
            this.resumeMode = 0;
            eventLoop$kotlinx_coroutines_core$ar$ds.dispatchUnconfined(this);
            return;
        }
        eventLoop$kotlinx_coroutines_core$ar$ds.incrementUseCount(true);
        try {
            CoroutineContext context2 = getContext();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(context2, this.countOrElement);
            try {
                this.continuation.resumeWith(obj);
                do {
                } while (eventLoop$kotlinx_coroutines_core$ar$ds.processUnconfinedEvent());
            } finally {
                ThreadContextKt.restoreThreadContext(context2, updateThreadContext);
            }
        } catch (Throwable th) {
            try {
                handleFatalException$kotlinx_coroutines_core(th, null);
            } finally {
                eventLoop$kotlinx_coroutines_core$ar$ds.decrementUseCount(true);
            }
        }
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Object takeState$kotlinx_coroutines_core() {
        Object obj = this._state;
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        this._state = DispatchedContinuationKt.UNDEFINED;
        return obj;
    }

    public final String toString() {
        return "DispatchedContinuation[" + this.dispatcher + ", " + DebugStringsKt.toDebugString(this.continuation) + "]";
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation getDelegate$kotlinx_coroutines_core() {
        return this;
    }
}
