package kotlinx.coroutines.flow;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.flow.internal.NullSurrogateKt;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StateFlowImpl extends AbstractSharedFlow implements Flow, StateFlow, MutableSharedFlow {
    private final AtomicRef _state;
    private int sequence;

    public StateFlowImpl(Object obj) {
        this._state = OnDeviceSubjectSegmentationCreateLogEvent.atomic(obj);
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0088, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r13, r14) == false) goto L37;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00e6, code lost:
    
        if (kotlin.Unit.INSTANCE != r1) goto L31;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x0079, code lost:
    
        if (r14 == r1) goto L54;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x007f A[Catch: all -> 0x0038, TryCatch #1 {all -> 0x0038, blocks: (B:13:0x0034, B:14:0x0079, B:16:0x007f, B:18:0x0084, B:20:0x00a4, B:22:0x00b5, B:24:0x00d7, B:25:0x00dc, B:27:0x00e4, B:32:0x008a, B:35:0x0091, B:43:0x004d, B:45:0x0057, B:46:0x006a), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0084 A[Catch: all -> 0x0038, TryCatch #1 {all -> 0x0038, blocks: (B:13:0x0034, B:14:0x0079, B:16:0x007f, B:18:0x0084, B:20:0x00a4, B:22:0x00b5, B:24:0x00d7, B:25:0x00dc, B:27:0x00e4, B:32:0x008a, B:35:0x0091, B:43:0x004d, B:45:0x0057, B:46:0x006a), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b5 A[Catch: all -> 0x0038, TryCatch #1 {all -> 0x0038, blocks: (B:13:0x0034, B:14:0x0079, B:16:0x007f, B:18:0x0084, B:20:0x00a4, B:22:0x00b5, B:24:0x00d7, B:25:0x00dc, B:27:0x00e4, B:32:0x008a, B:35:0x0091, B:43:0x004d, B:45:0x0057, B:46:0x006a), top: B:7:0x0022 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0090  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /* JADX WARN: Type inference failed for: r13v14, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v3, types: [kotlinx.coroutines.Job] */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v5, types: [kotlinx.coroutines.Job, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v2, types: [kotlinx.coroutines.flow.FlowCollector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x00b3 -> B:14:0x0079). Please report as a decompilation issue!!! */
    @Override // kotlinx.coroutines.flow.Flow
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object collect(kotlinx.coroutines.flow.FlowCollector r13, kotlin.coroutines.Continuation r14) {
        /*
            Method dump skipped, instructions count: 252
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.StateFlowImpl.collect(kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public final /* synthetic */ AbstractSharedFlowSlot createSlot() {
        return new StateFlowSlot();
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public final /* bridge */ /* synthetic */ AbstractSharedFlowSlot[] createSlotArray$ar$ds() {
        return new StateFlowSlot[2];
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        setValue(obj);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.coroutines.flow.StateFlow
    public final Object getValue() {
        Symbol symbol = NullSurrogateKt.NULL;
        Object obj = this._state.value;
        if (obj == symbol) {
            return null;
        }
        return obj;
    }

    public final void setValue(Object obj) {
        int i;
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        Symbol symbol;
        if (obj == null) {
            obj = NullSurrogateKt.NULL;
        }
        synchronized (this) {
            if (Intrinsics.areEqual(this._state.value, obj)) {
                return;
            }
            this._state.setValue(obj);
            int i2 = this.sequence;
            if ((i2 & 1) == 0) {
                int i3 = i2 + 1;
                this.sequence = i3;
                AbstractSharedFlowSlot[] abstractSharedFlowSlotArr2 = this.slots;
                while (true) {
                    StateFlowSlot[] stateFlowSlotArr = (StateFlowSlot[]) abstractSharedFlowSlotArr2;
                    if (stateFlowSlotArr != null) {
                        for (StateFlowSlot stateFlowSlot : stateFlowSlotArr) {
                            if (stateFlowSlot != null) {
                                while (true) {
                                    Object obj2 = stateFlowSlot._state.get();
                                    if (obj2 != null && obj2 != (symbol = StateFlowKt.PENDING)) {
                                        Symbol symbol2 = StateFlowKt.NONE;
                                        if (obj2 == symbol2) {
                                            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_28(stateFlowSlot._state, obj2, symbol)) {
                                                break;
                                            }
                                        } else if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_28(stateFlowSlot._state, obj2, symbol2)) {
                                            ((CancellableContinuationImpl) obj2).resumeWith(Unit.INSTANCE);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    synchronized (this) {
                        i = this.sequence;
                        if (i == i3) {
                            this.sequence = i3 + 1;
                            return;
                        }
                        abstractSharedFlowSlotArr = this.slots;
                    }
                    abstractSharedFlowSlotArr2 = abstractSharedFlowSlotArr;
                    i3 = i;
                }
            } else {
                this.sequence = i2 + 2;
            }
        }
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public final boolean tryEmit(Object obj) {
        setValue(obj);
        return true;
    }
}
