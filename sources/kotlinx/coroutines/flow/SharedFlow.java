package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface SharedFlow extends Flow {
    @Override // kotlinx.coroutines.flow.Flow
    Object collect(FlowCollector flowCollector, Continuation continuation);
}
