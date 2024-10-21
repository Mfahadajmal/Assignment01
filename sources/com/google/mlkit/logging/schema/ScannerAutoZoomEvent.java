package com.google.mlkit.logging.schema;

import java.util.concurrent.CancellationException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.CoroutineStackFrame;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CompletedExceptionally;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.DisposeOnCompletion;
import kotlinx.coroutines.InternalCompletionHandler;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobImpl;
import kotlinx.coroutines.JobKt__JobKt$invokeOnCompletion$1;
import kotlinx.coroutines.JobSupport;
import kotlinx.coroutines.JobSupportKt;
import kotlinx.coroutines.SupervisorJobImpl;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutCoroutine;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ScannerAutoZoomEvent {
    public static /* synthetic */ JobImpl SupervisorJob$default$ar$class_merging$ar$ds() {
        return new SupervisorJobImpl(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v2, types: [kotlin.coroutines.jvm.internal.CoroutineStackFrame] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    public static final Object setupTimeout(TimeoutCoroutine timeoutCoroutine, Function2 function2) {
        Object completedExceptionally;
        ?? r4;
        PredictedArea.invokeOnCompletion$default$ar$ds(timeoutCoroutine, false, new DisposeOnCompletion(DebugStringsKt.getDelay(timeoutCoroutine.uCont.getContext()).invokeOnTimeout(timeoutCoroutine.time, timeoutCoroutine, timeoutCoroutine.context)), 3);
        try {
            if (!(function2 instanceof BaseContinuationImpl)) {
                completedExceptionally = OnDevicePoseDetectionLogEvent.wrapWithContinuationImpl(function2, timeoutCoroutine, timeoutCoroutine);
            } else {
                TypeIntrinsics.beforeCheckcastToFunctionOfArity$ar$ds(function2, 2);
                completedExceptionally = function2.invoke(timeoutCoroutine, timeoutCoroutine);
            }
        } catch (Throwable th) {
            completedExceptionally = new CompletedExceptionally(th);
        }
        if (completedExceptionally == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }
        Object makeCompletingOnce$kotlinx_coroutines_core = timeoutCoroutine.makeCompletingOnce$kotlinx_coroutines_core(completedExceptionally);
        if (makeCompletingOnce$kotlinx_coroutines_core == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return CoroutineSingletons.COROUTINE_SUSPENDED;
        }
        if (makeCompletingOnce$kotlinx_coroutines_core instanceof CompletedExceptionally) {
            Throwable th2 = ((CompletedExceptionally) makeCompletingOnce$kotlinx_coroutines_core).cause;
            if ((th2 instanceof TimeoutCancellationException) && ((TimeoutCancellationException) th2).coroutine == timeoutCoroutine) {
                if (completedExceptionally instanceof CompletedExceptionally) {
                    th2 = ((CompletedExceptionally) completedExceptionally).cause;
                    Continuation continuation = timeoutCoroutine.uCont;
                    if (DebugKt.RECOVER_STACK_TRACES) {
                        boolean z = continuation instanceof CoroutineStackFrame;
                        r4 = continuation;
                        if (!z) {
                            throw th2;
                        }
                    } else {
                        throw th2;
                    }
                } else {
                    return completedExceptionally;
                }
            } else {
                Continuation continuation2 = timeoutCoroutine.uCont;
                if (DebugKt.RECOVER_STACK_TRACES) {
                    boolean z2 = continuation2 instanceof CoroutineStackFrame;
                    r4 = continuation2;
                    if (!z2) {
                        throw th2;
                    }
                } else {
                    throw th2;
                }
            }
            throw StackTraceRecoveryKt.recoverFromStackFrame(th2, r4);
        }
        return JobSupportKt.unboxState(makeCompletingOnce$kotlinx_coroutines_core);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0061 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0022  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object withTimeoutOrNull(long r7, kotlin.jvm.functions.Function2 r9, kotlin.coroutines.Continuation r10) {
        /*
            boolean r0 = r10 instanceof kotlinx.coroutines.TimeoutKt$withTimeoutOrNull$1
            if (r0 == 0) goto L13
            r0 = r10
            kotlinx.coroutines.TimeoutKt$withTimeoutOrNull$1 r0 = (kotlinx.coroutines.TimeoutKt$withTimeoutOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            kotlinx.coroutines.TimeoutKt$withTimeoutOrNull$1 r0 = new kotlinx.coroutines.TimeoutKt$withTimeoutOrNull$1
            r0.<init>(r10)
        L18:
            java.lang.Object r10 = r0.result
            kotlin.coroutines.intrinsics.CoroutineSingletons r1 = kotlin.coroutines.intrinsics.CoroutineSingletons.COROUTINE_SUSPENDED
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L34
            if (r2 != r4) goto L2c
            kotlin.jvm.internal.Ref$ObjectRef r7 = r0.L$1$ar$dn$8be4f154_0
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r10)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L2a
            goto L57
        L2a:
            r8 = move-exception
            goto L5b
        L2c:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L34:
            com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(r10)
            r5 = 0
            int r10 = (r7 > r5 ? 1 : (r7 == r5 ? 0 : -1))
            if (r10 > 0) goto L3e
            return r3
        L3e:
            kotlin.jvm.internal.Ref$ObjectRef r10 = new kotlin.jvm.internal.Ref$ObjectRef
            r10.<init>()
            r0.L$0 = r9     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L58
            r0.L$1$ar$dn$8be4f154_0 = r10     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L58
            r0.label = r4     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L58
            kotlinx.coroutines.TimeoutCoroutine r2 = new kotlinx.coroutines.TimeoutCoroutine     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L58
            r2.<init>(r7, r0)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L58
            r10.element = r2     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L58
            java.lang.Object r10 = setupTimeout(r2, r9)     // Catch: kotlinx.coroutines.TimeoutCancellationException -> L58
            if (r10 != r1) goto L57
            return r1
        L57:
            return r10
        L58:
            r7 = move-exception
            r8 = r7
            r7 = r10
        L5b:
            kotlinx.coroutines.Job r9 = r8.coroutine
            java.lang.Object r7 = r7.element
            if (r9 != r7) goto L62
            return r3
        L62:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.mlkit.logging.schema.ScannerAutoZoomEvent.withTimeoutOrNull(long, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PredictedArea {
        public static /* synthetic */ JobImpl Job$default$ar$class_merging$ar$ds() {
            return new JobImpl(null);
        }

        public static final void cancel(CoroutineContext coroutineContext, CancellationException cancellationException) {
            Job job = (Job) coroutineContext.get(Job.Key$ar$class_merging$e5be0816_0);
            if (job != null) {
                job.cancel(cancellationException);
            }
        }

        public static /* synthetic */ void cancel$default$ar$ds$e5a45333_0(CoroutineContext coroutineContext) {
            cancel(coroutineContext, null);
        }

        public static final void ensureActive(CoroutineContext coroutineContext) {
            Job job = (Job) coroutineContext.get(Job.Key$ar$class_merging$e5be0816_0);
            if (job != null) {
                ensureActive(job);
            }
        }

        public static /* synthetic */ DisposableHandle invokeOnCompletion$default$ar$ds(Job job, boolean z, InternalCompletionHandler internalCompletionHandler, int i) {
            boolean z2;
            boolean z3 = true;
            if ((i & 2) != 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (1 != ((z ? 1 : 0) & ((i & 1) ^ 1))) {
                z3 = false;
            }
            if (job instanceof JobSupport) {
                return ((JobSupport) job).invokeOnCompletionInternal$kotlinx_coroutines_core(z3, z2, internalCompletionHandler);
            }
            return job.invokeOnCompletion(z3, z2, new JobKt__JobKt$invokeOnCompletion$1(internalCompletionHandler, 0));
        }

        public static final void ensureActive(Job job) {
            if (!job.isActive()) {
                throw job.getCancellationException();
            }
        }
    }
}
