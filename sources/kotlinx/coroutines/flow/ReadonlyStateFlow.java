package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReadonlyStateFlow implements StateFlow, Flow {
    private final /* synthetic */ StateFlow $$delegate_0;

    public ReadonlyStateFlow(StateFlow stateFlow) {
        this.$$delegate_0 = stateFlow;
    }

    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        return this.$$delegate_0.collect(flowCollector, continuation);
    }

    @Override // kotlinx.coroutines.flow.StateFlow
    public final Object getValue() {
        return this.$$delegate_0.getValue();
    }
}
