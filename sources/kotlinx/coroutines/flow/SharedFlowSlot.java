package kotlinx.coroutines.flow;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SharedFlowSlot extends AbstractSharedFlowSlot {
    public Continuation cont;
    public long index = -1;

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public final /* bridge */ /* synthetic */ boolean allocateLocked(Object obj) {
        SharedFlowImpl sharedFlowImpl = (SharedFlowImpl) obj;
        if (this.index >= 0) {
            return false;
        }
        long j = sharedFlowImpl.replayIndex;
        if (j < sharedFlowImpl.minCollectorIndex) {
            sharedFlowImpl.minCollectorIndex = j;
        }
        this.index = j;
        return true;
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot
    public final /* bridge */ /* synthetic */ Continuation[] freeLocked(Object obj) {
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        long j = this.index;
        this.index = -1L;
        this.cont = null;
        return ((SharedFlowImpl) obj).updateCollectorIndexLocked$kotlinx_coroutines_core(j);
    }
}
