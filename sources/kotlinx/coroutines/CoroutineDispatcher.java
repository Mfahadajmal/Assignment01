package kotlinx.coroutines;

import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.AbstractCoroutineContextKey;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class CoroutineDispatcher extends AbstractCoroutineContextElement implements ContinuationInterceptor {
    public static final ExecutorCoroutineDispatcher.Key Key$ar$class_merging$80195e29_0 = new ExecutorCoroutineDispatcher.Key(null);

    public CoroutineDispatcher() {
        super(ContinuationInterceptor.Key);
    }

    public abstract void dispatch(CoroutineContext coroutineContext, Runnable runnable);

    public void dispatchYield(CoroutineContext coroutineContext, Runnable runnable) {
        dispatch(coroutineContext, runnable);
    }

    @Override // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.CoroutineContext.Element, kotlin.coroutines.CoroutineContext
    public final CoroutineContext.Element get(CoroutineContext.Key key) {
        if (key instanceof AbstractCoroutineContextKey) {
            AbstractCoroutineContextKey abstractCoroutineContextKey = (AbstractCoroutineContextKey) key;
            if (abstractCoroutineContextKey.isSubKey$kotlin_stdlib(getKey())) {
                CoroutineContext.Element tryCast$kotlin_stdlib = abstractCoroutineContextKey.tryCast$kotlin_stdlib(this);
                if (tryCast$kotlin_stdlib instanceof CoroutineContext.Element) {
                    return tryCast$kotlin_stdlib;
                }
                return null;
            }
            return null;
        }
        if (ContinuationInterceptor.Key == key) {
            return this;
        }
        return null;
    }

    @Override // kotlin.coroutines.ContinuationInterceptor
    public final Continuation interceptContinuation(Continuation continuation) {
        return new DispatchedContinuation(this, continuation);
    }

    public boolean isDispatchNeeded(CoroutineContext coroutineContext) {
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0019, code lost:
    
        if (kotlin.coroutines.ContinuationInterceptor.Key == r2) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0014, code lost:
    
        if (r2.tryCast$kotlin_stdlib(r1) != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
    
        return kotlin.coroutines.EmptyCoroutineContext.INSTANCE;
     */
    @Override // kotlin.coroutines.AbstractCoroutineContextElement, kotlin.coroutines.CoroutineContext
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlin.coroutines.CoroutineContext minusKey(kotlin.coroutines.CoroutineContext.Key r2) {
        /*
            r1 = this;
            boolean r0 = r2 instanceof kotlin.coroutines.AbstractCoroutineContextKey
            if (r0 == 0) goto L17
            kotlin.coroutines.AbstractCoroutineContextKey r2 = (kotlin.coroutines.AbstractCoroutineContextKey) r2
            kotlin.coroutines.CoroutineContext$Key r0 = r1.getKey()
            boolean r0 = r2.isSubKey$kotlin_stdlib(r0)
            if (r0 == 0) goto L1e
            kotlin.coroutines.CoroutineContext$Element r2 = r2.tryCast$kotlin_stdlib(r1)
            if (r2 == 0) goto L1e
            goto L1b
        L17:
            kotlin.coroutines.ContinuationInterceptor$Key r0 = kotlin.coroutines.ContinuationInterceptor.Key
            if (r0 != r2) goto L1e
        L1b:
            kotlin.coroutines.EmptyCoroutineContext r2 = kotlin.coroutines.EmptyCoroutineContext.INSTANCE
            return r2
        L1e:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.CoroutineDispatcher.minusKey(kotlin.coroutines.CoroutineContext$Key):kotlin.coroutines.CoroutineContext");
    }

    @Override // kotlin.coroutines.ContinuationInterceptor
    public final void releaseInterceptedContinuation(Continuation continuation) {
        CancellableContinuationImpl cancellableContinuationImpl;
        DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) continuation;
        do {
        } while (dispatchedContinuation._reusableCancellableContinuation.value == DispatchedContinuationKt.REUSABLE_CLAIMED);
        Object obj = dispatchedContinuation._reusableCancellableContinuation.value;
        if (obj instanceof CancellableContinuationImpl) {
            cancellableContinuationImpl = (CancellableContinuationImpl) obj;
        } else {
            cancellableContinuationImpl = null;
        }
        if (cancellableContinuationImpl != null) {
            cancellableContinuationImpl.detachChild$kotlinx_coroutines_core();
        }
    }

    public String toString() {
        return DebugStringsKt.getClassSimpleName(this) + "@" + DebugStringsKt.getHexAddress(this);
    }
}
