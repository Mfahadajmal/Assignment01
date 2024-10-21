package com.google.mlkit.logging.schema;

import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.CoroutineContextKt;
import kotlinx.coroutines.DispatchedCoroutine;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.UndispatchedCoroutine;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceSubjectSegmentationLoadLogEvent {
    public static final Object withContext(CoroutineContext coroutineContext, Function2 function2, Continuation continuation) {
        Object unboxState;
        CoroutineContext context = continuation.getContext();
        CoroutineContext newCoroutineContext = CoroutineContextKt.newCoroutineContext(context, coroutineContext);
        ScannerAutoZoomEvent.PredictedArea.ensureActive(newCoroutineContext);
        if (newCoroutineContext == context) {
            ScopeCoroutine scopeCoroutine = new ScopeCoroutine(newCoroutineContext, continuation);
            unboxState = ToxicityDetectionCreateEvent.startUndispatchedOrReturn(scopeCoroutine, scopeCoroutine, function2);
        } else if (Intrinsics.areEqual(newCoroutineContext.get(ContinuationInterceptor.Key), context.get(ContinuationInterceptor.Key))) {
            UndispatchedCoroutine undispatchedCoroutine = new UndispatchedCoroutine(newCoroutineContext, continuation);
            CoroutineContext coroutineContext2 = undispatchedCoroutine.context;
            Object updateThreadContext = ThreadContextKt.updateThreadContext(coroutineContext2, null);
            try {
                Object startUndispatchedOrReturn = ToxicityDetectionCreateEvent.startUndispatchedOrReturn(undispatchedCoroutine, undispatchedCoroutine, function2);
                ThreadContextKt.restoreThreadContext(coroutineContext2, updateThreadContext);
                unboxState = startUndispatchedOrReturn;
            } catch (Throwable th) {
                ThreadContextKt.restoreThreadContext(coroutineContext2, updateThreadContext);
                throw th;
            }
        } else {
            DispatchedCoroutine dispatchedCoroutine = new DispatchedCoroutine(newCoroutineContext, continuation);
            TextDetectionOptionalModuleOptions.startCoroutineCancellable$default$ar$ds(function2, dispatchedCoroutine, dispatchedCoroutine);
            AtomicInt atomicInt = dispatchedCoroutine._decision;
            while (true) {
                int i = atomicInt.value;
                if (i != 0) {
                    if (i == 2) {
                        unboxState = JobSupportKt.unboxState(dispatchedCoroutine.getState$kotlinx_coroutines_core());
                        if (unboxState instanceof CompletedExceptionally) {
                            throw ((CompletedExceptionally) unboxState).cause;
                        }
                    } else {
                        throw new IllegalStateException("Already suspended");
                    }
                } else if (dispatchedCoroutine._decision.compareAndSet(0, 1)) {
                    unboxState = CoroutineSingletons.COROUTINE_SUSPENDED;
                    break;
                }
            }
        }
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return unboxState;
    }
}
