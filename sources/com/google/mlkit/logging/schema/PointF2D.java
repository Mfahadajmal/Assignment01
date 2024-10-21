package com.google.mlkit.logging.schema;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.DispatchedTask;
import kotlinx.coroutines.UndispatchedCoroutine;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.ThreadContextKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PointF2D {
    public static final CancellationException CancellationException(String str, Throwable th) {
        CancellationException cancellationException = new CancellationException(str);
        cancellationException.initCause(th);
        return cancellationException;
    }

    public static final boolean isCancellableMode(int i) {
        if (i == 1 || i == 2) {
            return true;
        }
        return false;
    }

    public static final void resume(DispatchedTask dispatchedTask, Continuation continuation, boolean z) {
        Object successfulResult$kotlinx_coroutines_core;
        UndispatchedCoroutine undispatchedCoroutine;
        Object takeState$kotlinx_coroutines_core = dispatchedTask.takeState$kotlinx_coroutines_core();
        Throwable exceptionalResult$kotlinx_coroutines_core = dispatchedTask.getExceptionalResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
        if (exceptionalResult$kotlinx_coroutines_core != null) {
            successfulResult$kotlinx_coroutines_core = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(exceptionalResult$kotlinx_coroutines_core);
        } else {
            successfulResult$kotlinx_coroutines_core = dispatchedTask.getSuccessfulResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
        }
        if (z) {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
            Continuation continuation2 = dispatchedContinuation.continuation;
            Object obj = dispatchedContinuation.countOrElement;
            CoroutineContext context = continuation2.getContext();
            Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj);
            if (updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS) {
                undispatchedCoroutine = CoroutineContextKt.updateUndispatchedCompletion(continuation2, context, updateThreadContext);
            } else {
                undispatchedCoroutine = null;
            }
            try {
                dispatchedContinuation.continuation.resumeWith(successfulResult$kotlinx_coroutines_core);
                if (undispatchedCoroutine != null && !undispatchedCoroutine.clearThreadContext()) {
                    return;
                }
                ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                return;
            } catch (Throwable th) {
                if (undispatchedCoroutine == null || undispatchedCoroutine.clearThreadContext()) {
                    ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                }
                throw th;
            }
        }
        continuation.resumeWith(successfulResult$kotlinx_coroutines_core);
    }
}
