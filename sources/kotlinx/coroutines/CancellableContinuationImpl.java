package kotlinx.coroutines;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent;
import com.google.mlkit.logging.schema.OtherError;
import com.google.mlkit.logging.schema.PointF2D;
import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function1;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CancellableContinuationImpl extends DispatchedTask implements CoroutineStackFrame, Waiter, Continuation {
    public final AtomicInt _decisionAndIndex;
    private final AtomicRef _parentHandle;
    public final AtomicRef _state;
    public final CoroutineContext context;
    public final Continuation delegate;

    public CancellableContinuationImpl(Continuation continuation, int i) {
        super(i);
        this.delegate = continuation;
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        this.context = continuation.getContext();
        this._decisionAndIndex = OnDeviceSubjectSegmentationCreateLogEvent.atomic(536870911);
        this._state = OnDeviceSubjectSegmentationCreateLogEvent.atomic(Active.INSTANCE);
        this._parentHandle = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);
    }

    private final void callSegmentOnCancellation$ar$class_merging(ConcurrentLinkedListNode concurrentLinkedListNode, Throwable th) {
        int i = this._decisionAndIndex.value & 536870911;
        if (i != 536870911) {
            try {
                concurrentLinkedListNode.onCancellation$ar$ds(i, this.context);
                return;
            } catch (Throwable th2) {
                CoroutineContext coroutineContext = this.context;
                toString();
                OtherError.handleCoroutineException(coroutineContext, new CompletionHandlerException("Exception in invokeOnCancellation handler for ".concat(toString()), th2));
                return;
            }
        }
        throw new IllegalStateException("The index for Segment.onCancellation(..) is broken");
    }

    private final void dispatchResume(int i) {
        int i2;
        boolean z;
        do {
            i2 = this._decisionAndIndex.value;
            int i3 = i2 >> 29;
            if (i3 != 0) {
                if (i3 == 1) {
                    boolean z2 = DebugKt.ASSERTIONS_ENABLED;
                    Continuation delegate$kotlinx_coroutines_core = getDelegate$kotlinx_coroutines_core();
                    if (i == 4) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (!z && (delegate$kotlinx_coroutines_core instanceof DispatchedContinuation) && PointF2D.isCancellableMode(i) == PointF2D.isCancellableMode(this.resumeMode)) {
                        CoroutineDispatcher coroutineDispatcher = ((DispatchedContinuation) delegate$kotlinx_coroutines_core).dispatcher;
                        CoroutineContext context = delegate$kotlinx_coroutines_core.getContext();
                        if (!coroutineDispatcher.isDispatchNeeded(context)) {
                            ThreadLocalEventLoop threadLocalEventLoop = ThreadLocalEventLoop.INSTANCE;
                            EventLoop eventLoop$kotlinx_coroutines_core$ar$ds = ThreadLocalEventLoop.getEventLoop$kotlinx_coroutines_core$ar$ds();
                            if (eventLoop$kotlinx_coroutines_core$ar$ds.isUnconfinedLoopActive()) {
                                eventLoop$kotlinx_coroutines_core$ar$ds.dispatchUnconfined(this);
                                return;
                            }
                            eventLoop$kotlinx_coroutines_core$ar$ds.incrementUseCount(true);
                            try {
                                PointF2D.resume(this, getDelegate$kotlinx_coroutines_core(), true);
                                do {
                                } while (eventLoop$kotlinx_coroutines_core$ar$ds.processUnconfinedEvent());
                                return;
                            } catch (Throwable th) {
                                try {
                                    handleFatalException$kotlinx_coroutines_core(th, null);
                                    return;
                                } finally {
                                    eventLoop$kotlinx_coroutines_core$ar$ds.decrementUseCount(true);
                                }
                            }
                        }
                        coroutineDispatcher.dispatch(context, this);
                        return;
                    }
                    PointF2D.resume(this, delegate$kotlinx_coroutines_core, z);
                    return;
                }
                throw new IllegalStateException("Already resumed");
            }
        } while (!this._decisionAndIndex.compareAndSet(i2, (536870911 & i2) + 1073741824));
    }

    private final DisposableHandle installParentHandle() {
        Job job = (Job) this.context.get(Job.Key$ar$class_merging$e5be0816_0);
        if (job == null) {
            return null;
        }
        DisposableHandle invokeOnCompletion$default$ar$ds = ScannerAutoZoomEvent.PredictedArea.invokeOnCompletion$default$ar$ds(job, true, new ChildContinuation(this), 2);
        this._parentHandle.compareAndSet(null, invokeOnCompletion$default$ar$ds);
        return invokeOnCompletion$default$ar$ds;
    }

    private static final void multipleHandlersError$ar$ds(Object obj, Object obj2) {
        throw new IllegalStateException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_2(obj2, obj, "It's prohibited to register multiple handlers, tried to register ", ", already has "));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void resumeImpl(Object obj, int i, Function1 function1) {
        Object obj2;
        do {
            obj2 = this._state.value;
            if (obj2 instanceof NotCompleted) {
            } else {
                if (obj2 instanceof CancelledContinuation) {
                    CancelledContinuation cancelledContinuation = (CancelledContinuation) obj2;
                    if (cancelledContinuation._resumed.compareAndSet(false, true)) {
                        if (function1 != null) {
                            callOnCancellation(function1, cancelledContinuation.cause);
                            return;
                        }
                        return;
                    }
                }
                Objects.toString(obj);
                throw new IllegalStateException("Already resumed, but proposed with update ".concat(String.valueOf(obj)));
            }
        } while (!this._state.compareAndSet(obj2, resumedState$ar$ds((NotCompleted) obj2, obj, i, function1)));
        detachChildIfNonResuable();
        dispatchResume(i);
    }

    private static final Object resumedState$ar$ds(NotCompleted notCompleted, Object obj, int i, Function1 function1) {
        Function1 function12;
        if (obj instanceof CompletedExceptionally) {
            boolean z = DebugKt.ASSERTIONS_ENABLED;
            return obj;
        }
        if (PointF2D.isCancellableMode(i)) {
            CancelHandler cancelHandler = null;
            if (function1 == null) {
                if (notCompleted instanceof CancelHandler) {
                    function12 = null;
                } else {
                    return obj;
                }
            } else {
                function12 = function1;
            }
            if (notCompleted instanceof CancelHandler) {
                cancelHandler = (CancelHandler) notCompleted;
            }
            return new CompletedContinuation(obj, cancelHandler, function12, null, 16);
        }
        return obj;
    }

    public final void callCancelHandler(CancelHandler cancelHandler, Throwable th) {
        try {
            cancelHandler.invoke(th);
        } catch (Throwable th2) {
            CoroutineContext coroutineContext = this.context;
            toString();
            OtherError.handleCoroutineException(coroutineContext, new CompletionHandlerException("Exception in invokeOnCancellation handler for ".concat(toString()), th2));
        }
    }

    public final void callOnCancellation(Function1 function1, Throwable th) {
        try {
            function1.invoke(th);
        } catch (Throwable th2) {
            CoroutineContext coroutineContext = this.context;
            toString();
            OtherError.handleCoroutineException(coroutineContext, new CompletionHandlerException("Exception in resume onCancellation handler for ".concat(toString()), th2));
        }
    }

    public final boolean cancel(Throwable th) {
        Object obj;
        boolean z;
        do {
            obj = this._state.value;
            z = false;
            if (!(obj instanceof NotCompleted)) {
                return false;
            }
            if ((obj instanceof CancelHandler) || (obj instanceof ConcurrentLinkedListNode)) {
                z = true;
            }
        } while (!this._state.compareAndSet(obj, new CancelledContinuation(this, th, z)));
        NotCompleted notCompleted = (NotCompleted) obj;
        if (notCompleted instanceof CancelHandler) {
            callCancelHandler((CancelHandler) obj, th);
        } else if (notCompleted instanceof ConcurrentLinkedListNode) {
            callSegmentOnCancellation$ar$class_merging((ConcurrentLinkedListNode) obj, th);
        }
        detachChildIfNonResuable();
        dispatchResume(this.resumeMode);
        return true;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final void cancelCompletedResult$kotlinx_coroutines_core(Object obj, Throwable th) {
        while (true) {
            Object obj2 = this._state.value;
            if (!(obj2 instanceof NotCompleted)) {
                if (!(obj2 instanceof CompletedExceptionally)) {
                    if (obj2 instanceof CompletedContinuation) {
                        CompletedContinuation completedContinuation = (CompletedContinuation) obj2;
                        if (!completedContinuation.getCancelled()) {
                            if (this._state.compareAndSet(obj2, CompletedContinuation.copy$default$ar$ds(completedContinuation, null, th, 15))) {
                                CancelHandler cancelHandler = completedContinuation.cancelHandler;
                                if (cancelHandler != null) {
                                    callCancelHandler(cancelHandler, th);
                                }
                                Function1 function1 = completedContinuation.onCancellation;
                                if (function1 != null) {
                                    callOnCancellation(function1, th);
                                    return;
                                }
                                return;
                            }
                        } else {
                            throw new IllegalStateException("Must be called at most once");
                        }
                    } else {
                        if (this._state.compareAndSet(obj2, new CompletedContinuation(obj2, null, null, th, 14))) {
                            return;
                        }
                    }
                } else {
                    return;
                }
            } else {
                throw new IllegalStateException("Not completed");
            }
        }
    }

    public final void completeResume(Object obj) {
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        dispatchResume(this.resumeMode);
    }

    public final void detachChild$kotlinx_coroutines_core() {
        DisposableHandle parentHandle = getParentHandle();
        if (parentHandle == null) {
            return;
        }
        parentHandle.dispose();
        this._parentHandle.setValue(NonDisposableHandle.INSTANCE);
    }

    public final void detachChildIfNonResuable() {
        if (!isReusable()) {
            detachChild$kotlinx_coroutines_core();
        }
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final CoroutineStackFrame getCallerFrame() {
        Continuation continuation = this.delegate;
        if (continuation instanceof CoroutineStackFrame) {
            return (CoroutineStackFrame) continuation;
        }
        return null;
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Continuation getDelegate$kotlinx_coroutines_core() {
        return this.delegate;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Throwable getExceptionalResult$kotlinx_coroutines_core(Object obj) {
        Throwable exceptionalResult$kotlinx_coroutines_core = super.getExceptionalResult$kotlinx_coroutines_core(obj);
        if (exceptionalResult$kotlinx_coroutines_core != null) {
            Continuation continuation = this.delegate;
            if (DebugKt.RECOVER_STACK_TRACES && (continuation instanceof CoroutineStackFrame)) {
                return StackTraceRecoveryKt.recoverFromStackFrame(exceptionalResult$kotlinx_coroutines_core, (CoroutineStackFrame) continuation);
            }
            return exceptionalResult$kotlinx_coroutines_core;
        }
        return null;
    }

    public final DisposableHandle getParentHandle() {
        return (DisposableHandle) this._parentHandle.value;
    }

    public final Object getResult() {
        int i;
        Job job;
        boolean isReusable = isReusable();
        do {
            i = this._decisionAndIndex.value;
            int i2 = i >> 29;
            if (i2 != 0) {
                if (i2 == 2) {
                    if (isReusable) {
                        releaseClaimedReusableContinuation$kotlinx_coroutines_core();
                    }
                    Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
                    if (state$kotlinx_coroutines_core instanceof CompletedExceptionally) {
                        Throwable th = ((CompletedExceptionally) state$kotlinx_coroutines_core).cause;
                        if (DebugKt.RECOVER_STACK_TRACES) {
                            throw StackTraceRecoveryKt.recoverFromStackFrame(th, this);
                        }
                        throw th;
                    }
                    if (PointF2D.isCancellableMode(this.resumeMode) && (job = (Job) this.context.get(Job.Key$ar$class_merging$e5be0816_0)) != null && !job.isActive()) {
                        CancellationException cancellationException = job.getCancellationException();
                        cancelCompletedResult$kotlinx_coroutines_core(state$kotlinx_coroutines_core, cancellationException);
                        if (DebugKt.RECOVER_STACK_TRACES) {
                            throw StackTraceRecoveryKt.recoverFromStackFrame(cancellationException, this);
                        }
                        throw cancellationException;
                    }
                    return getSuccessfulResult$kotlinx_coroutines_core(state$kotlinx_coroutines_core);
                }
                throw new IllegalStateException("Already suspended");
            }
        } while (!this._decisionAndIndex.compareAndSet(i, (536870911 & i) + 536870912));
        if (getParentHandle() == null) {
            installParentHandle();
        }
        if (isReusable) {
            releaseClaimedReusableContinuation$kotlinx_coroutines_core();
        }
        return CoroutineSingletons.COROUTINE_SUSPENDED;
    }

    @Override // kotlin.coroutines.jvm.internal.CoroutineStackFrame
    public final StackTraceElement getStackTraceElement() {
        return null;
    }

    public final Object getState$kotlinx_coroutines_core() {
        return this._state.value;
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Object getSuccessfulResult$kotlinx_coroutines_core(Object obj) {
        if (obj instanceof CompletedContinuation) {
            return ((CompletedContinuation) obj).result;
        }
        return obj;
    }

    public final void initCancellability() {
        DisposableHandle installParentHandle = installParentHandle();
        if (installParentHandle != null && !(getState$kotlinx_coroutines_core() instanceof NotCompleted)) {
            installParentHandle.dispose();
            this._parentHandle.setValue(NonDisposableHandle.INSTANCE);
        }
    }

    public final void invokeOnCancellation(Function1 function1) {
        OnDeviceTranslationLogEvent.invokeOnCancellation$ar$class_merging(this, new CancelHandler.UserSupplied(function1, 0));
    }

    @Override // kotlinx.coroutines.Waiter
    public final void invokeOnCancellation$ar$class_merging$3a98f194_0(ConcurrentLinkedListNode concurrentLinkedListNode, int i) {
        AtomicInt atomicInt;
        int i2;
        do {
            atomicInt = this._decisionAndIndex;
            i2 = atomicInt.value;
            if ((i2 & 536870911) != 536870911) {
                throw new IllegalStateException("invokeOnCancellation should be called at most once");
            }
        } while (!atomicInt.compareAndSet(i2, ((i2 >> 29) << 29) + i));
        invokeOnCancellationImpl(concurrentLinkedListNode);
    }

    public final void invokeOnCancellationImpl(Object obj) {
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        while (true) {
            Object obj2 = this._state.value;
            if (obj2 instanceof Active) {
                if (this._state.compareAndSet(obj2, obj)) {
                    return;
                }
            } else if (!(obj2 instanceof CancelHandler) && !(obj2 instanceof ConcurrentLinkedListNode)) {
                Throwable th = null;
                if (obj2 instanceof CompletedExceptionally) {
                    CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj2;
                    if (!completedExceptionally.makeHandled()) {
                        multipleHandlersError$ar$ds(obj, obj2);
                    }
                    if (obj2 instanceof CancelledContinuation) {
                        if (completedExceptionally != null) {
                            th = completedExceptionally.cause;
                        }
                        if (obj instanceof CancelHandler) {
                            callCancelHandler((CancelHandler) obj, th);
                            return;
                        } else {
                            obj.getClass();
                            callSegmentOnCancellation$ar$class_merging((ConcurrentLinkedListNode) obj, th);
                            return;
                        }
                    }
                    return;
                }
                if (obj2 instanceof CompletedContinuation) {
                    CompletedContinuation completedContinuation = (CompletedContinuation) obj2;
                    if (completedContinuation.cancelHandler != null) {
                        multipleHandlersError$ar$ds(obj, obj2);
                    }
                    if (!(obj instanceof ConcurrentLinkedListNode)) {
                        obj.getClass();
                        CancelHandler cancelHandler = (CancelHandler) obj;
                        if (completedContinuation.getCancelled()) {
                            callCancelHandler(cancelHandler, completedContinuation.cancelCause);
                            return;
                        } else {
                            if (this._state.compareAndSet(obj2, CompletedContinuation.copy$default$ar$ds(completedContinuation, cancelHandler, null, 29))) {
                                return;
                            }
                        }
                    } else {
                        return;
                    }
                } else if (!(obj instanceof ConcurrentLinkedListNode)) {
                    obj.getClass();
                    if (this._state.compareAndSet(obj2, new CompletedContinuation(obj2, (CancelHandler) obj, null, null, 28))) {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                multipleHandlersError$ar$ds(obj, obj2);
            }
        }
    }

    public final boolean isReusable() {
        if (this.resumeMode == 2 && ((DispatchedContinuation) this.delegate)._reusableCancellableContinuation.value != null) {
            return true;
        }
        return false;
    }

    public final void releaseClaimedReusableContinuation$kotlinx_coroutines_core() {
        DispatchedContinuation dispatchedContinuation;
        Continuation continuation = this.delegate;
        Throwable th = null;
        if (continuation instanceof DispatchedContinuation) {
            dispatchedContinuation = (DispatchedContinuation) continuation;
        } else {
            dispatchedContinuation = null;
        }
        if (dispatchedContinuation == null) {
            return;
        }
        while (true) {
            Object obj = dispatchedContinuation._reusableCancellableContinuation.value;
            Symbol symbol = DispatchedContinuationKt.REUSABLE_CLAIMED;
            if (obj == symbol) {
                if (dispatchedContinuation._reusableCancellableContinuation.compareAndSet(symbol, this)) {
                    break;
                }
            } else if (obj instanceof Throwable) {
                if (dispatchedContinuation._reusableCancellableContinuation.compareAndSet(obj, null)) {
                    th = (Throwable) obj;
                } else {
                    throw new IllegalArgumentException("Failed requirement.");
                }
            } else {
                Objects.toString(obj);
                throw new IllegalStateException("Inconsistent state ".concat(String.valueOf(obj)));
            }
        }
        if (th != null) {
            detachChild$kotlinx_coroutines_core();
            cancel(th);
        }
    }

    public final void resume(Object obj, Function1 function1) {
        resumeImpl(obj, this.resumeMode, function1);
    }

    public final void resumeUndispatched(CoroutineDispatcher coroutineDispatcher, Object obj) {
        DispatchedContinuation dispatchedContinuation;
        int i;
        Continuation continuation = this.delegate;
        CoroutineDispatcher coroutineDispatcher2 = null;
        if (continuation instanceof DispatchedContinuation) {
            dispatchedContinuation = (DispatchedContinuation) continuation;
        } else {
            dispatchedContinuation = null;
        }
        if (dispatchedContinuation != null) {
            coroutineDispatcher2 = dispatchedContinuation.dispatcher;
        }
        if (coroutineDispatcher2 == coroutineDispatcher) {
            i = 4;
        } else {
            i = this.resumeMode;
        }
        resumeImpl(obj, i, null);
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Throwable m248exceptionOrNullimpl = Result.m248exceptionOrNullimpl(obj);
        if (m248exceptionOrNullimpl != null) {
            if (DebugKt.RECOVER_STACK_TRACES) {
                m248exceptionOrNullimpl = StackTraceRecoveryKt.recoverFromStackFrame(m248exceptionOrNullimpl, this);
            }
            obj = new CompletedExceptionally(m248exceptionOrNullimpl);
        }
        resumeImpl(obj, this.resumeMode, null);
    }

    @Override // kotlinx.coroutines.DispatchedTask
    public final Object takeState$kotlinx_coroutines_core() {
        return getState$kotlinx_coroutines_core();
    }

    public final String toString() {
        String str;
        String debugString = DebugStringsKt.toDebugString(this.delegate);
        Object state$kotlinx_coroutines_core = getState$kotlinx_coroutines_core();
        if (state$kotlinx_coroutines_core instanceof NotCompleted) {
            str = "Active";
        } else if (state$kotlinx_coroutines_core instanceof CancelledContinuation) {
            str = "Cancelled";
        } else {
            str = "Completed";
        }
        return "CancellableContinuation(" + debugString + "){" + str + "}@" + DebugStringsKt.getHexAddress(this);
    }

    public final Object tryResume$ar$ds(Object obj, Function1 function1) {
        Object obj2;
        do {
            obj2 = this._state.value;
            if (!(obj2 instanceof NotCompleted)) {
                return obj2 instanceof CompletedContinuation ? null : null;
            }
        } while (!this._state.compareAndSet(obj2, resumedState$ar$ds((NotCompleted) obj2, obj, this.resumeMode, function1)));
        detachChildIfNonResuable();
        return CancellableContinuationImplKt.RESUME_TOKEN;
    }
}
