package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlinx.coroutines.flow.FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlowKt__ErrorsKt$catchImpl$2$emit$1 extends ContinuationImpl {
    FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2 L$0$ar$dn$8e6f6c46_0$ar$class_merging;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2 this$0$ar$class_merging$95c4007c_0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__ErrorsKt$catchImpl$2$emit$1(FlowKt__TransformKt$onEach$$inlined$unsafeTransform$1.AnonymousClass2 anonymousClass2, Continuation continuation) {
        super(continuation);
        this.this$0$ar$class_merging$95c4007c_0 = anonymousClass2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0$ar$class_merging$95c4007c_0.emit(null, this);
    }
}
