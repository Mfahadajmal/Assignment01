package kotlinx.coroutines.flow;

import com.google.mlkit.logging.schema.StainRemovalOptionalModuleLogEvent;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.jvm.internal.Ref$ObjectRef;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlowKt__ReduceKt$first$1 extends ContinuationImpl {
    public Ref$ObjectRef L$0$ar$dn$80b34a63_0;
    public FlowKt__ReduceKt$first$$inlined$collectWhile$1 L$1$ar$dn$80b34a63_0;
    public int label;
    public /* synthetic */ Object result;

    public FlowKt__ReduceKt$first$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return StainRemovalOptionalModuleLogEvent.first(null, this);
    }
}
