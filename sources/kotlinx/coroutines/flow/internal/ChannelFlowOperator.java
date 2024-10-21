package kotlinx.coroutines.flow.internal;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChannelFlowOperator extends ChannelFlow {
    protected final Flow flow;

    public ChannelFlowOperator(Flow flow, CoroutineContext coroutineContext, int i, int i2) {
        super(coroutineContext, 0, i2);
        this.flow = flow;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow, kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        Object collect$suspendImpl = ChannelFlow.collect$suspendImpl(this, flowCollector, continuation);
        if (collect$suspendImpl == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return collect$suspendImpl;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public final Object collectTo$ar$class_merging(ProducerCoroutine producerCoroutine, Continuation continuation) {
        Object flowCollect = flowCollect(new SendingCollector(producerCoroutine), continuation);
        if (flowCollect == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return flowCollect;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final Object flowCollect(FlowCollector flowCollector, Continuation continuation) {
        Object collect = this.flow.collect(flowCollector, continuation);
        if (collect == CoroutineSingletons.COROUTINE_SUSPENDED) {
            return collect;
        }
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.internal.ChannelFlow
    public final String toString() {
        return this.flow + " -> " + super.toString();
    }

    public /* synthetic */ ChannelFlowOperator(Flow flow, int i, int i2) {
        this(flow, EmptyCoroutineContext.INSTANCE, 0, i2);
    }
}
