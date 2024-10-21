package androidx.work.impl.workers;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintTrackingWorkerKt$awaitConstraintsNotMet$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;

    public ConstraintTrackingWorkerKt$awaitConstraintsNotMet$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ConstraintTrackingWorkerKt.awaitConstraintsNotMet$ar$class_merging$ar$class_merging$ar$class_merging(null, null, this);
    }
}
