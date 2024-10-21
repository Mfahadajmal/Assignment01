package kotlinx.coroutines.flow.internal;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ProducerCoroutine;
import kotlinx.coroutines.flow.FlowCollector;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChannelFlowOperator$collectWithContextUndispatched$2 extends SuspendLambda implements Function2 {
    final /* synthetic */ Object ChannelFlowOperator$collectWithContextUndispatched$2$ar$this$0;
    /* synthetic */ Object L$0;
    int label;
    private final /* synthetic */ int switching_field;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelFlowOperator$collectWithContextUndispatched$2(FlowCollector flowCollector, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.ChannelFlowOperator$collectWithContextUndispatched$2$ar$this$0 = flowCollector;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [kotlinx.coroutines.flow.FlowCollector, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation create(Object obj, Continuation continuation) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                ChannelFlowOperator$collectWithContextUndispatched$2 channelFlowOperator$collectWithContextUndispatched$2 = new ChannelFlowOperator$collectWithContextUndispatched$2((FlowCollector) this.ChannelFlowOperator$collectWithContextUndispatched$2$ar$this$0, continuation, 2);
                channelFlowOperator$collectWithContextUndispatched$2.L$0 = obj;
                return channelFlowOperator$collectWithContextUndispatched$2;
            }
            ChannelFlowOperator$collectWithContextUndispatched$2 channelFlowOperator$collectWithContextUndispatched$22 = new ChannelFlowOperator$collectWithContextUndispatched$2((ChannelFlow) this.ChannelFlowOperator$collectWithContextUndispatched$2$ar$this$0, continuation, 1);
            channelFlowOperator$collectWithContextUndispatched$22.L$0 = obj;
            return channelFlowOperator$collectWithContextUndispatched$22;
        }
        ChannelFlowOperator$collectWithContextUndispatched$2 channelFlowOperator$collectWithContextUndispatched$23 = new ChannelFlowOperator$collectWithContextUndispatched$2((ChannelFlowOperator) this.ChannelFlowOperator$collectWithContextUndispatched$2$ar$this$0, continuation, 0);
        channelFlowOperator$collectWithContextUndispatched$23.L$0 = obj;
        return channelFlowOperator$collectWithContextUndispatched$23;
    }

    @Override // kotlin.jvm.functions.Function2
    public final /* synthetic */ Object invoke(Object obj, Object obj2) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                return ((ChannelFlowOperator$collectWithContextUndispatched$2) create(obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
            }
            return ((ChannelFlowOperator$collectWithContextUndispatched$2) create((ProducerCoroutine) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
        }
        return ((ChannelFlowOperator$collectWithContextUndispatched$2) create((FlowCollector) obj, (Continuation) obj2)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r2v7, types: [kotlinx.coroutines.flow.FlowCollector, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                if (this.label != 0) {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                } else {
                    OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                    Object obj2 = this.L$0;
                    ?? r2 = this.ChannelFlowOperator$collectWithContextUndispatched$2$ar$this$0;
                    this.label = 1;
                    if (r2.emit(obj2, this) == coroutineSingletons) {
                        return coroutineSingletons;
                    }
                }
                return Unit.INSTANCE;
            }
            CoroutineSingletons coroutineSingletons2 = CoroutineSingletons.COROUTINE_SUSPENDED;
            if (this.label != 0) {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            } else {
                OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
                ProducerCoroutine producerCoroutine = (ProducerCoroutine) this.L$0;
                Object obj3 = this.ChannelFlowOperator$collectWithContextUndispatched$2$ar$this$0;
                this.label = 1;
                if (((ChannelFlow) obj3).collectTo$ar$class_merging(producerCoroutine, this) == coroutineSingletons2) {
                    return coroutineSingletons2;
                }
            }
            return Unit.INSTANCE;
        }
        CoroutineSingletons coroutineSingletons3 = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (this.label != 0) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
        } else {
            OnDeviceLanguageIdentificationLogEvent.IdentifyLanguageResult.throwOnFailure(obj);
            FlowCollector flowCollector = (FlowCollector) this.L$0;
            Object obj4 = this.ChannelFlowOperator$collectWithContextUndispatched$2$ar$this$0;
            this.label = 1;
            if (((ChannelFlowOperator) obj4).flowCollect(flowCollector, this) == coroutineSingletons3) {
                return coroutineSingletons3;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelFlowOperator$collectWithContextUndispatched$2(ChannelFlow channelFlow, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.ChannelFlowOperator$collectWithContextUndispatched$2$ar$this$0 = channelFlow;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChannelFlowOperator$collectWithContextUndispatched$2(ChannelFlowOperator channelFlowOperator, Continuation continuation, int i) {
        super(2, continuation);
        this.switching_field = i;
        this.ChannelFlowOperator$collectWithContextUndispatched$2$ar$this$0 = channelFlowOperator;
    }
}
