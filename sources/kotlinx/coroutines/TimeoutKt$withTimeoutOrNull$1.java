package kotlinx.coroutines;

import com.google.mlkit.logging.schema.ScannerAutoZoomEvent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TimeoutKt$withTimeoutOrNull$1 extends ContinuationImpl {
    public Object L$0;
    public Ref$ObjectRef L$1$ar$dn$8be4f154_0;
    public int label;
    public /* synthetic */ Object result;

    public TimeoutKt$withTimeoutOrNull$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ScannerAutoZoomEvent.withTimeoutOrNull(0L, null, this);
    }
}
