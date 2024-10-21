package com.google.mlkit.logging.schema;

import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import io.perfmark.Tag;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.internal.ContextScope;
import kotlinx.coroutines.internal.ScopeCoroutine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OtherError {
    public static final CoroutineScope CoroutineScope(CoroutineContext coroutineContext) {
        if (coroutineContext.get(Job.Key$ar$class_merging$e5be0816_0) == null) {
            coroutineContext = coroutineContext.plus(ScannerAutoZoomEvent.PredictedArea.Job$default$ar$class_merging$ar$ds());
        }
        return new ContextScope(coroutineContext);
    }

    public static final Object coroutineScope(Function2 function2, Continuation continuation) {
        ScopeCoroutine scopeCoroutine = new ScopeCoroutine(continuation.getContext(), continuation);
        Object startUndispatchedOrReturn = ToxicityDetectionCreateEvent.startUndispatchedOrReturn(scopeCoroutine, scopeCoroutine, function2);
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        return startUndispatchedOrReturn;
    }

    public static final void handleCoroutineException(CoroutineContext coroutineContext, Throwable th) {
        try {
            CoroutineExceptionHandler coroutineExceptionHandler = (CoroutineExceptionHandler) coroutineContext.get(CoroutineExceptionHandler.Key$ar$class_merging$908abe57_0);
            if (coroutineExceptionHandler != null) {
                coroutineExceptionHandler.handleException$ar$ds$fd9f1d10_0();
            } else {
                SubjectInfo.handleUncaughtCoroutineException(coroutineContext, th);
            }
        } catch (Throwable th2) {
            SubjectInfo.handleUncaughtCoroutineException(coroutineContext, handlerException(th, th2));
        }
    }

    public static final Throwable handlerException(Throwable th, Throwable th2) {
        if (th == th2) {
            return th;
        }
        RuntimeException runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
        Tag.addSuppressed(runtimeException, th);
        return runtimeException;
    }
}
