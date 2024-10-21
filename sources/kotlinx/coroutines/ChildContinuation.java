package kotlinx.coroutines;

import java.util.concurrent.CancellationException;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.internal.DispatchedContinuation;
import kotlinx.coroutines.internal.DispatchedContinuationKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChildContinuation extends JobCancellingNode {
    public final CancellableContinuationImpl child;

    public ChildContinuation(CancellableContinuationImpl cancellableContinuationImpl) {
        this.child = cancellableContinuationImpl;
    }

    @Override // kotlinx.coroutines.InternalCompletionHandler
    public final void invoke(Throwable th) {
        CancellationException cancellationException = getJob().getCancellationException();
        CancellableContinuationImpl cancellableContinuationImpl = this.child;
        if (cancellableContinuationImpl.isReusable()) {
            DispatchedContinuation dispatchedContinuation = (DispatchedContinuation) cancellableContinuationImpl.delegate;
            AtomicRef atomicRef = dispatchedContinuation._reusableCancellableContinuation;
            while (true) {
                Object obj = atomicRef.value;
                if (Intrinsics.areEqual(obj, DispatchedContinuationKt.REUSABLE_CLAIMED)) {
                    if (dispatchedContinuation._reusableCancellableContinuation.compareAndSet(DispatchedContinuationKt.REUSABLE_CLAIMED, cancellationException)) {
                        return;
                    }
                } else if (!(obj instanceof Throwable)) {
                    if (dispatchedContinuation._reusableCancellableContinuation.compareAndSet(obj, null)) {
                        break;
                    }
                } else {
                    return;
                }
            }
        }
        cancellableContinuationImpl.cancel(cancellationException);
        cancellableContinuationImpl.detachChildIfNonResuable();
    }
}
