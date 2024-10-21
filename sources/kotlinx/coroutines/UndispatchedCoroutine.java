package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent;
import kotlin.Pair;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.internal.ScopeCoroutine;
import kotlinx.coroutines.internal.ThreadContextKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UndispatchedCoroutine extends ScopeCoroutine {
    private volatile boolean threadLocalIsSet;
    private final ThreadLocal threadStateToRecover;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public UndispatchedCoroutine(kotlin.coroutines.CoroutineContext r2, kotlin.coroutines.Continuation r3) {
        /*
            r1 = this;
            kotlinx.coroutines.UndispatchedMarker r0 = kotlinx.coroutines.UndispatchedMarker.INSTANCE
            kotlin.coroutines.CoroutineContext$Element r0 = r2.get(r0)
            if (r0 != 0) goto Lf
            kotlinx.coroutines.UndispatchedMarker r0 = kotlinx.coroutines.UndispatchedMarker.INSTANCE
            kotlin.coroutines.CoroutineContext r0 = r2.plus(r0)
            goto L10
        Lf:
            r0 = r2
        L10:
            r1.<init>(r0, r3)
            java.lang.ThreadLocal r0 = new java.lang.ThreadLocal
            r0.<init>()
            r1.threadStateToRecover = r0
            kotlin.coroutines.CoroutineContext r3 = r3.getContext()
            kotlin.coroutines.ContinuationInterceptor$Key r0 = kotlin.coroutines.ContinuationInterceptor.Key
            kotlin.coroutines.CoroutineContext$Element r3 = r3.get(r0)
            boolean r3 = r3 instanceof kotlinx.coroutines.CoroutineDispatcher
            if (r3 != 0) goto L33
            r3 = 0
            java.lang.Object r3 = kotlinx.coroutines.internal.ThreadContextKt.updateThreadContext(r2, r3)
            kotlinx.coroutines.internal.ThreadContextKt.restoreThreadContext(r2, r3)
            r1.saveThreadContext(r2, r3)
        L33:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.UndispatchedCoroutine.<init>(kotlin.coroutines.CoroutineContext, kotlin.coroutines.Continuation):void");
    }

    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    protected final void afterResume(Object obj) {
        if (this.threadLocalIsSet) {
            Pair pair = (Pair) this.threadStateToRecover.get();
            if (pair != null) {
                ThreadContextKt.restoreThreadContext((CoroutineContext) pair.first, pair.second);
            }
            this.threadStateToRecover.remove();
        }
        Object recoverResult = OnDeviceTranslationLogEvent.recoverResult(obj, this.uCont);
        Continuation continuation = this.uCont;
        CoroutineContext context = continuation.getContext();
        UndispatchedCoroutine undispatchedCoroutine = null;
        Object updateThreadContext = ThreadContextKt.updateThreadContext(context, null);
        if (updateThreadContext != ThreadContextKt.NO_THREAD_ELEMENTS) {
            undispatchedCoroutine = CoroutineContextKt.updateUndispatchedCompletion(continuation, context, updateThreadContext);
        }
        try {
            this.uCont.resumeWith(recoverResult);
            if (undispatchedCoroutine != null && !undispatchedCoroutine.clearThreadContext()) {
                return;
            }
            ThreadContextKt.restoreThreadContext(context, updateThreadContext);
        } catch (Throwable th) {
            if (undispatchedCoroutine == null || undispatchedCoroutine.clearThreadContext()) {
                ThreadContextKt.restoreThreadContext(context, updateThreadContext);
            }
            throw th;
        }
    }

    public final boolean clearThreadContext() {
        boolean z;
        if (this.threadLocalIsSet && this.threadStateToRecover.get() == null) {
            z = true;
        } else {
            z = false;
        }
        this.threadStateToRecover.remove();
        if (!z) {
            return true;
        }
        return false;
    }

    public final void saveThreadContext(CoroutineContext coroutineContext, Object obj) {
        this.threadLocalIsSet = true;
        this.threadStateToRecover.set(new Pair(coroutineContext, obj));
    }
}
