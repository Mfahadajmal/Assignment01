package kotlinx.coroutines.channels;

import com.google.mlkit.logging.schema.SmartReplyGeneratorOptions;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProduceKt$awaitClose$1 extends ContinuationImpl {
    public Object L$0;
    public Object L$1;
    public int label;
    public /* synthetic */ Object result;

    public ProduceKt$awaitClose$1(Continuation continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SmartReplyGeneratorOptions.awaitClose$ar$class_merging(null, null, this);
    }
}
