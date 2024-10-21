package kotlinx.coroutines.internal;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.EventLoop;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.ThreadLocalEventLoop;
import kotlinx.coroutines.UndispatchedCoroutine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DispatchedContinuationKt {
    public static final Symbol UNDEFINED = new Symbol("UNDEFINED");
    public static final Symbol REUSABLE_CLAIMED = new Symbol("REUSABLE_CLAIMED");

    public static final void resumeCancellableWith$ar$ds(Continuation continuation, Object obj) {
        UndispatchedCoroutine undispatchedCoroutine;
        if (continuation instanceof DispatchedContinuation) {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
            Object state$ar$ds = OnDeviceTranslationLogEvent.toState$ar$ds(obj);
            if (dispatchedContinuation.dispatcher.isDispatchNeeded(dispatchedContinuation.getContext())) {
                dispatchedContinuation._state = state$ar$ds;
                dispatchedContinuation.resumeMode = 1;
                dispatchedContinuation.dispatcher.dispatch(dispatchedContinuation.getContext(), dispatchedContinuation);
                return;
            }
            boolean z = DebugKt.ASSERTIONS_ENABLED;
            ThreadLocalEventLoop threadLocalEventLoop = ThreadLocalEventLoop.INSTANCE;
            EventLoop eventLoop$kotlinx_coroutines_core$ar$ds = ThreadLocalEventLoop.getEventLoop$kotlinx_coroutines_core$ar$ds();
            if (eventLoop$kotlinx_coroutines_core$ar$ds.isUnconfinedLoopActive()) {
                dispatchedContinuation._state = state$ar$ds;
                dispatchedContinuation.resumeMode = 1;
                eventLoop$kotlinx_coroutines_core$ar$ds.dispatchUnconfined(dispatchedContinuation);
                return;
            }
            eventLoop$kotlinx_coroutines_core$ar$ds.incrementUseCount(true);
            try {
                Job job = (Job) dispatchedContinuation.getContext().get(Job.Key$ar$class_merging$e5be0816_0);
                if (job != null && !job.isActive()) {
                    CancellationException cancellationException = job.getCancellationException();
                    dispatchedContinuation.cancelCompletedResult$kotlinx_coroutines_core(state$ar$ds, cancellationException);
                    dispatchedContinuation.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(cancellationException));
                } else {
                    Continuation continuation2 = dispatchedContinuation.continuation;
                    Object obj2 = dispatchedContinuation.countOrElement;
                    CoroutineContext context = continuation2.getContext();
                    Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj2);
                    if (updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS) {
                        undispatchedCoroutine = CoroutineContextKt.updateUndispatchedCompletion(continuation2, context, updateThreadContext);
                    } else {
                        undispatchedCoroutine = null;
                    }
                    try {
                        dispatchedContinuation.continuation.resumeWith(obj);
                    } finally {
                        if (undispatchedCoroutine == null || undispatchedCoroutine.clearThreadContext()) {
                            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                        }
                    }
                }
                do {
                } while (eventLoop$kotlinx_coroutines_core$ar$ds.processUnconfinedEvent());
                return;
            } catch (Throwable th) {
                try {
                    dispatchedContinuation.handleFatalException$kotlinx_coroutines_core(th, null);
                    return;
                } finally {
                    eventLoop$kotlinx_coroutines_core$ar$ds.decrementUseCount(true);
                }
            }
        }
        continuation.resumeWith(obj);
    }
}
