package com.google.mlkit.logging.schema;

import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ToxicityDetectionCreateEvent {
    public static final boolean arrayRangeEquals(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        bArr.getClass();
        for (int i4 = 0; i4 < i3; i4++) {
            if (bArr[i4 + i] != bArr2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }

    public static final void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) >= 0 && j2 <= j && j - j2 >= j3) {
            return;
        }
        throw new ArrayIndexOutOfBoundsException("size=" + j + " offset=" + j2 + " byteCount=" + j3);
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlin.coroutines.Continuation, kotlin.coroutines.jvm.internal.CoroutineStackFrame] */
    public static final Object startUndispatchedOrReturn(ScopeCoroutine scopeCoroutine, Object obj, Function2 function2) {
        Object completedExceptionally;
        try {
            if (!(function2 instanceof BaseContinuationImpl)) {
                completedExceptionally = OnDevicePoseDetectionLogEvent.wrapWithContinuationImpl(function2, obj, scopeCoroutine);
            } else {
                TypeIntrinsics.beforeCheckcastToFunctionOfArity$ar$ds(function2, 2);
                completedExceptionally = function2.invoke(obj, scopeCoroutine);
            }
        } catch (Throwable th) {
            completedExceptionally = new CompletedExceptionally(th);
        }
        if (completedExceptionally == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }
        Object makeCompletingOnce$kotlinx_coroutines_core = scopeCoroutine.makeCompletingOnce$kotlinx_coroutines_core(completedExceptionally);
        if (makeCompletingOnce$kotlinx_coroutines_core == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }
        if (makeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            Throwable th2 = ((CompletedExceptionally) makeCompletingOnce$kotlinx_coroutines_core).cause;
            ?? r1 = scopeCoroutine.uCont;
            if (DebugKt.RECOVER_STACK_TRACES && (r1 instanceof CoroutineStackFrame)) {
                throw StackTraceRecoveryKt.recoverFromStackFrame(th2, r1);
            }
            throw th2;
        }
        return JobSupportKt.unboxState(makeCompletingOnce$kotlinx_coroutines_core);
    }
}
