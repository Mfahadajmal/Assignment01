package kotlinx.coroutines.channels;

import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.Waiter;
import kotlinx.coroutines.internal.ConcurrentLinkedListNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReceiveCatching implements Waiter {
    public final CancellableContinuationImpl cont;

    public ReceiveCatching(CancellableContinuationImpl cancellableContinuationImpl) {
        this.cont = cancellableContinuationImpl;
    }

    @Override // kotlinx.coroutines.Waiter
    public final void invokeOnCancellation$ar$class_merging$3a98f194_0(ConcurrentLinkedListNode concurrentLinkedListNode, int i) {
        this.cont.invokeOnCancellation$ar$class_merging$3a98f194_0(concurrentLinkedListNode, i);
    }
}
