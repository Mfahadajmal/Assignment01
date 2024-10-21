package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.internal.DispatchedContinuation;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DebugStringsKt {
    public static final Object delay(long j, Continuation continuation) {
        if (j > 0) {
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(OnDevicePoseDetectionLogEvent.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            if (j < Long.MAX_VALUE) {
                getDelay(cancellableContinuationImpl.context).scheduleResumeAfterDelay$ar$class_merging$3a912d96_0(j, cancellableContinuationImpl);
            }
            Object result = cancellableContinuationImpl.getResult();
            if (result == CoroutineSingletons.COROUTINE_SUSPENDED) {
                return result;
            }
        }
        return Unit.INSTANCE;
    }

    public static final String getClassSimpleName(Object obj) {
        return obj.getClass().getSimpleName();
    }

    public static final Delay getDelay(CoroutineContext coroutineContext) {
        Delay delay;
        CoroutineContext.Element element = coroutineContext.get(ContinuationInterceptor.Key);
        if (element instanceof Delay) {
            delay = (Delay) element;
        } else {
            delay = null;
        }
        if (delay == null) {
            return DefaultExecutorKt.DefaultDelay;
        }
        return delay;
    }

    public static final String getHexAddress(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static final String toDebugString(Continuation continuation) {
        Object createFailure;
        if (continuation instanceof DispatchedContinuation) {
            return continuation.toString();
        }
        try {
            createFailure = continuation + "@" + getHexAddress(continuation);
        } catch (Throwable th) {
            createFailure = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th);
        }
        if (Result.m248exceptionOrNullimpl(createFailure) != null) {
            createFailure = continuation.getClass().getName() + "@" + getHexAddress(continuation);
        }
        return (String) createFailure;
    }
}
