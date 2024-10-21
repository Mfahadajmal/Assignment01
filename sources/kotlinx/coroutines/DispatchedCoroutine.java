package kotlinx.coroutines;

import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceTranslationLogEvent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.internal.DispatchedContinuationKt;
import kotlinx.coroutines.internal.ScopeCoroutine;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DispatchedCoroutine extends ScopeCoroutine {
    public final AtomicInt _decision;

    public DispatchedCoroutine(CoroutineContext coroutineContext, Continuation continuation) {
        super(coroutineContext, continuation);
        this._decision = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.JobSupport
    public final void afterCompletion(Object obj) {
        afterResume(obj);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.internal.ScopeCoroutine, kotlinx.coroutines.AbstractCoroutine
    public final void afterResume(Object obj) {
        do {
            int i = this._decision.value;
            if (i != 0) {
                if (i == 1) {
                    Continuation continuation = this.uCont;
                    DispatchedContinuationKt.resumeCancellableWith$ar$ds(OnDevicePoseDetectionLogEvent.intercepted(continuation), OnDeviceTranslationLogEvent.recoverResult(obj, continuation));
                    return;
                }
                throw new IllegalStateException("Already resumed");
            }
        } while (!this._decision.compareAndSet(0, 2));
    }
}
