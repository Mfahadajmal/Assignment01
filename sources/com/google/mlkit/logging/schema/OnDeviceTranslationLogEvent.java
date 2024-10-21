package com.google.mlkit.logging.schema;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import kotlin.Result;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OnDeviceTranslationLogEvent {
    public static final void disposeOnCancellation$ar$class_merging(CancellableContinuationImpl cancellableContinuationImpl, DisposableHandle disposableHandle) {
        cancellableContinuationImpl.invokeOnCancellationImpl(new CancelHandler.UserSupplied(disposableHandle, 2));
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x002e, code lost:
    
        if (r0 == null) goto L61;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0030, code lost:
    
        r5 = kotlinx.coroutines.DebugKt.ASSERTIONS_ENABLED;
        r5 = r0._state.value;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0038, code lost:
    
        if ((r5 instanceof kotlinx.coroutines.CompletedContinuation) == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x003a, code lost:
    
        r5 = ((kotlinx.coroutines.CompletedContinuation) r5).idempotentResume;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x003e, code lost:
    
        r0._decisionAndIndex.setValue(536870911);
        r0._state.setValue(kotlinx.coroutines.Active.INSTANCE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x004d, code lost:
    
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0054, code lost:
    
        return new kotlinx.coroutines.CancellableContinuationImpl(r5, 2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final kotlinx.coroutines.CancellableContinuationImpl getOrCreateCancellableContinuation(kotlin.coroutines.Continuation r5) {
        /*
            boolean r0 = r5 instanceof kotlinx.coroutines.internal.DispatchedContinuation
            if (r0 != 0) goto Lb
            kotlinx.coroutines.CancellableContinuationImpl r0 = new kotlinx.coroutines.CancellableContinuationImpl
            r1 = 1
            r0.<init>(r5, r1)
            return r0
        Lb:
            r0 = r5
            kotlinx.coroutines.internal.DispatchedContinuation r0 = (kotlinx.coroutines.internal.DispatchedContinuation) r0
            kotlinx.atomicfu.AtomicRef r1 = r0._reusableCancellableContinuation
        L10:
            java.lang.Object r2 = r1.value
            if (r2 != 0) goto L1d
            kotlinx.atomicfu.AtomicRef r0 = r0._reusableCancellableContinuation
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.internal.DispatchedContinuationKt.REUSABLE_CLAIMED
            r0.setValue(r1)
            r0 = 0
            goto L2e
        L1d:
            boolean r3 = r2 instanceof kotlinx.coroutines.CancellableContinuationImpl
            if (r3 == 0) goto L55
            kotlinx.atomicfu.AtomicRef r3 = r0._reusableCancellableContinuation
            kotlinx.coroutines.internal.Symbol r4 = kotlinx.coroutines.internal.DispatchedContinuationKt.REUSABLE_CLAIMED
            boolean r3 = r3.compareAndSet(r2, r4)
            if (r3 == 0) goto L10
            r0 = r2
            kotlinx.coroutines.CancellableContinuationImpl r0 = (kotlinx.coroutines.CancellableContinuationImpl) r0
        L2e:
            if (r0 == 0) goto L4e
            boolean r5 = kotlinx.coroutines.DebugKt.ASSERTIONS_ENABLED
            kotlinx.atomicfu.AtomicRef r5 = r0._state
            java.lang.Object r5 = r5.value
            boolean r1 = r5 instanceof kotlinx.coroutines.CompletedContinuation
            if (r1 == 0) goto L3e
            kotlinx.coroutines.CompletedContinuation r5 = (kotlinx.coroutines.CompletedContinuation) r5
            java.lang.Object r5 = r5.idempotentResume
        L3e:
            kotlinx.atomicfu.AtomicInt r5 = r0._decisionAndIndex
            r1 = 536870911(0x1fffffff, float:1.0842021E-19)
            r5.setValue(r1)
            kotlinx.atomicfu.AtomicRef r5 = r0._state
            kotlinx.coroutines.Active r1 = kotlinx.coroutines.Active.INSTANCE
            r5.setValue(r1)
            return r0
        L4e:
            kotlinx.coroutines.CancellableContinuationImpl r0 = new kotlinx.coroutines.CancellableContinuationImpl
            r1 = 2
            r0.<init>(r5, r1)
            return r0
        L55:
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.internal.DispatchedContinuationKt.REUSABLE_CLAIMED
            if (r2 == r3) goto L10
            boolean r3 = r2 instanceof java.lang.Throwable
            if (r3 == 0) goto L5e
            goto L10
        L5e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.util.Objects.toString(r2)
            java.lang.String r0 = r2.toString()
            java.lang.String r1 = "Inconsistent state "
            java.lang.String r0 = r1.concat(r0)
            r5.<init>(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent.getOrCreateCancellableContinuation(kotlin.coroutines.Continuation):kotlinx.coroutines.CancellableContinuationImpl");
    }

    public static final void invokeOnCancellation$ar$class_merging(CancellableContinuationImpl cancellableContinuationImpl, CancelHandler cancelHandler) {
        cancellableContinuationImpl.invokeOnCancellationImpl(cancelHandler);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Object recoverResult(Object obj, Continuation continuation) {
        if (obj instanceof CompletedExceptionally) {
            Throwable th = ((CompletedExceptionally) obj).cause;
            if (DebugKt.RECOVER_STACK_TRACES && (continuation instanceof CoroutineStackFrame)) {
                th = StackTraceRecoveryKt.recoverFromStackFrame(th, continuation);
            }
            return OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th);
        }
        return obj;
    }

    public static final Object toState$ar$ds(Object obj) {
        Throwable m248exceptionOrNullimpl = Result.m248exceptionOrNullimpl(obj);
        if (m248exceptionOrNullimpl == null) {
            return obj;
        }
        return new CompletedExceptionally(m248exceptionOrNullimpl);
    }

    public static /* synthetic */ Object toState$default$ar$ds(Object obj) {
        return toState$ar$ds(obj);
    }
}
