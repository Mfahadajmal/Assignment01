package kotlinx.coroutines.flow;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class StateFlowSlot extends AbstractSharedFlowSlot {
    public final AtomicReference _state = new AtomicReference(null);

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public final /* bridge */ /* synthetic */ boolean allocateLocked(Object obj) {
        if (this._state.get() != null) {
            return false;
        }
        this._state.set(StateFlowKt.NONE);
        return true;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public final /* bridge */ /* synthetic */ Continuation[] freeLocked(Object obj) {
        this._state.set(null);
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }
}
