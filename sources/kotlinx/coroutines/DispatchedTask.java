package kotlinx.coroutines;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OtherError;
import com.google.mlkit.logging.schema.PointF2D;
import io.perfmark.Tag;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.StackTraceRecoveryKt;
import kotlinx.coroutines.internal.ThreadContextKt;
import kotlinx.coroutines.scheduling.Task;
import kotlinx.coroutines.scheduling.TasksKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class DispatchedTask extends Task {
    public int resumeMode;

    public DispatchedTask(int i) {
        super(0L, TasksKt.NonBlockingContext$ar$class_merging);
        this.resumeMode = i;
    }

    public void cancelCompletedResult$kotlinx_coroutines_core(Object obj, Throwable th) {
        throw null;
    }

    public abstract Continuation getDelegate$kotlinx_coroutines_core();

    public Throwable getExceptionalResult$kotlinx_coroutines_core(Object obj) {
        CompletedExceptionally completedExceptionally;
        if (obj instanceof CompletedExceptionally) {
            completedExceptionally = (CompletedExceptionally) obj;
        } else {
            completedExceptionally = null;
        }
        if (completedExceptionally == null) {
            return null;
        }
        return completedExceptionally.cause;
    }

    public final void handleFatalException$kotlinx_coroutines_core(Throwable th, Throwable th2) {
        if (th == null && th2 == null) {
            return;
        }
        if (th != null && th2 != null) {
            Tag.addSuppressed(th, th2);
        }
        if (th == null) {
            th = th2;
        }
        OtherError.handleCoroutineException(getDelegate$kotlinx_coroutines_core().getContext(), new CoroutinesInternalError(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(this, "Fatal exception in coroutines machinery for ", ". Please read KDoc to 'handleFatalException' method and report this incident to maintainers"), th));
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [kotlin.coroutines.Continuation, kotlin.coroutines.jvm.internal.CoroutineStackFrame] */
    @Override // java.lang.Runnable
    public final void run() {
        Object createFailure;
        Throwable th;
        UndispatchedCoroutine undispatchedCoroutine;
        Job job;
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        try {
            try {
                DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) getDelegate$kotlinx_coroutines_core();
                ?? r1 = dispatchedContinuation.continuation;
                Object obj = dispatchedContinuation.countOrElement;
                CoroutineContext context = r1.getContext();
                Object updateThreadContext = ThreadContextKt.updateThreadContext(context, obj);
                th = null;
                if (updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS) {
                    undispatchedCoroutine = CoroutineContextKt.updateUndispatchedCompletion(r1, context, updateThreadContext);
                } else {
                    undispatchedCoroutine = null;
                }
                try {
                    CoroutineContext context2 = r1.getContext();
                    Object takeState$kotlinx_coroutines_core = takeState$kotlinx_coroutines_core();
                    Throwable exceptionalResult$kotlinx_coroutines_core = getExceptionalResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core);
                    if (exceptionalResult$kotlinx_coroutines_core == null && PointF2D.isCancellableMode(this.resumeMode)) {
                        job = (Job) context2.get(Job.Key$ar$class_merging$e5be0816_0);
                    } else {
                        job = null;
                    }
                    if (job != null && !job.isActive()) {
                        Throwable cancellationException = job.getCancellationException();
                        cancelCompletedResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core, cancellationException);
                        if (DebugKt.RECOVER_STACK_TRACES) {
                            cancellationException = StackTraceRecoveryKt.recoverFromStackFrame(cancellationException, r1);
                        }
                        r1.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(cancellationException));
                    } else if (exceptionalResult$kotlinx_coroutines_core != null) {
                        r1.resumeWith(OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(exceptionalResult$kotlinx_coroutines_core));
                    } else {
                        r1.resumeWith(getSuccessfulResult$kotlinx_coroutines_core(takeState$kotlinx_coroutines_core));
                    }
                    createFailure = Unit.INSTANCE;
                } finally {
                    if (undispatchedCoroutine == null || undispatchedCoroutine.clearThreadContext()) {
                        ThreadContextKt.restoreThreadContext(context, updateThreadContext);
                    }
                }
            } catch (Throwable th2) {
                createFailure = OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.createFailure(th2);
            }
        } catch (Throwable th3) {
            th = th3;
            createFailure = Unit.INSTANCE;
        }
        handleFatalException$kotlinx_coroutines_core(th, Result.m248exceptionOrNullimpl(createFailure));
    }

    public abstract Object takeState$kotlinx_coroutines_core();

    public Object getSuccessfulResult$kotlinx_coroutines_core(Object obj) {
        return obj;
    }
}
