package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent;
import com.google.mlkit.logging.schema.OtherError;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AbstractCoroutine extends JobSupport implements Job, Continuation, CoroutineScope {
    public final CoroutineContext context;

    public AbstractCoroutine(CoroutineContext coroutineContext, boolean z) {
        super(z);
        initParentJob((Job) coroutineContext.get(Job.Key$ar$class_merging$e5be0816_0));
        this.context = coroutineContext.plus(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void afterResume(Object obj) {
        afterCompletion(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.JobSupport
    public final String cancellationExceptionMessage() {
        return String.valueOf(DebugStringsKt.getClassSimpleName(this)).concat(" was cancelled");
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public final CoroutineContext getCoroutineContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void handleOnCompletionException$kotlinx_coroutines_core(Throwable th) {
        OtherError.handleCoroutineException(this.context, th);
    }

    @Override // kotlinx.coroutines.JobSupport
    public String nameString$kotlinx_coroutines_core() {
        String str = null;
        if (DebugKt.DEBUG) {
            CoroutineContext coroutineContext = this.context;
            CoroutineId coroutineId = (CoroutineId) coroutineContext.get(CoroutineId.Key$ar$class_merging$3b7a11c9_0);
            if (coroutineId != null) {
                if (((CoroutineName) coroutineContext.get(CoroutineName.Key$ar$class_merging$1ccfc3dd_0)) == null) {
                    str = "coroutine#" + coroutineId.id;
                } else {
                    throw null;
                }
            }
        }
        if (str == null) {
            return DebugStringsKt.getClassSimpleName(this);
        }
        return "\"" + str + "\":" + DebugStringsKt.getClassSimpleName(this);
    }

    @Override // kotlinx.coroutines.JobSupport
    protected final void onCompletionInternal(Object obj) {
        if (obj instanceof CompletedExceptionally) {
            CompletedExceptionally completedExceptionally = (CompletedExceptionally) obj;
            onCancelled(completedExceptionally.cause, completedExceptionally._handled.getValue());
        } else {
            onCompleted(obj);
        }
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object obj) {
        Object state$ar$ds;
        state$ar$ds = OnDeviceTranslationLogEvent.toState$ar$ds(obj);
        Object makeCompletingOnce$kotlinx_coroutines_core = makeCompletingOnce$kotlinx_coroutines_core(state$ar$ds);
        if (makeCompletingOnce$kotlinx_coroutines_core == JobSupportKt.COMPLETING_WAITING_CHILDREN) {
            return;
        }
        afterResume(makeCompletingOnce$kotlinx_coroutines_core);
    }

    protected void onCompleted(Object obj) {
    }

    protected void onCancelled(Throwable th, boolean z) {
    }
}
