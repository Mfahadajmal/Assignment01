package kotlinx.coroutines.flow;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.flow.internal.ChannelFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class ChannelFlowBuilder extends ChannelFlow {
    private final Function2 block;

    public ChannelFlowBuilder(Function2 function2, CoroutineContext coroutineContext, int i, int i2) {
        super(coroutineContext, -2, i2);
        this.block = function2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object collectTo$suspendImpl$ar$class_merging(ChannelFlowBuilder channelFlowBuilder, ProducerCoroutine producerCoroutine, Continuation continuation) {
        Object invoke = channelFlowBuilder.block.invoke(producerCoroutine, continuation);
        if (invoke == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return invoke;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public Object collectTo$ar$class_merging(ProducerCoroutine producerCoroutine, Continuation continuation) {
        return collectTo$suspendImpl$ar$class_merging(this, producerCoroutine, continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public final String toString() {
        return "block[" + this.block + "] -> " + super.toString();
    }
}
