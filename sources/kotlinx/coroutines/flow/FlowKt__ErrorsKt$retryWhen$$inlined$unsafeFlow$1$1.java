package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* loaded from: classes.dex */
public final class FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1 extends ContinuationImpl {
    long J$0;
    FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 L$0$ar$dn$3b60d34e_0$ar$class_merging;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 this$0$ar$class_merging$63c4c74f_0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ErrorsKt$retryWhen$$inlined$unsafeFlow$1$1(FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1 flowKt__TransformKt$onEach$$inlined$unsafeTransform$1, Continuation continuation) {
        super(continuation);
        this.this$0$ar$class_merging$63c4c74f_0 = flowKt__TransformKt$onEach$$inlined$unsafeTransform$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0$ar$class_merging$63c4c74f_0.collect(null, this);
    }
}
