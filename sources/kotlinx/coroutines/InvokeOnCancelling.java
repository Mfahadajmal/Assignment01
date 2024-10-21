package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import kotlinx.atomicfu.AtomicInt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class InvokeOnCancelling extends JobCancellingNode {
    private final AtomicInt _invoked = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0);
    private final InternalCompletionHandler handler;

    public InvokeOnCancelling(InternalCompletionHandler internalCompletionHandler) {
        this.handler = internalCompletionHandler;
    }

    @Override // kotlinx.coroutines.InternalCompletionHandler
    public final void invoke(Throwable th) {
        if (this._invoked.compareAndSet(0, 1)) {
            this.handler.invoke(th);
        }
    }
}
