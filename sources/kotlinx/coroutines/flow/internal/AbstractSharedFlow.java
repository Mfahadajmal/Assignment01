package kotlinx.coroutines.flow.internal;

import java.util.Arrays;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractSharedFlow {
    public int nCollectors;
    private int nextIndex;
    public AbstractSharedFlowSlot[] slots;

    /* JADX INFO: Access modifiers changed from: protected */
    public final AbstractSharedFlowSlot allocateSlot() {
        AbstractSharedFlowSlot abstractSharedFlowSlot;
        synchronized (this) {
            AbstractSharedFlowSlot[] abstractSharedFlowSlotArr = this.slots;
            if (abstractSharedFlowSlotArr == null) {
                abstractSharedFlowSlotArr = createSlotArray$ar$ds();
                this.slots = abstractSharedFlowSlotArr;
            } else {
                int i = this.nCollectors;
                int length = abstractSharedFlowSlotArr.length;
                if (i >= length) {
                    Object[] copyOf = Arrays.copyOf(abstractSharedFlowSlotArr, length + length);
                    copyOf.getClass();
                    abstractSharedFlowSlotArr = (AbstractSharedFlowSlot[]) copyOf;
                    this.slots = abstractSharedFlowSlotArr;
                }
            }
            int i2 = this.nextIndex;
            do {
                abstractSharedFlowSlot = abstractSharedFlowSlotArr[i2];
                if (abstractSharedFlowSlot == null) {
                    abstractSharedFlowSlot = createSlot();
                    abstractSharedFlowSlotArr[i2] = abstractSharedFlowSlot;
                }
                i2++;
                if (i2 >= abstractSharedFlowSlotArr.length) {
                    i2 = 0;
                }
            } while (!abstractSharedFlowSlot.allocateLocked(this));
            this.nextIndex = i2;
            this.nCollectors++;
        }
        return abstractSharedFlowSlot;
    }

    protected abstract AbstractSharedFlowSlot createSlot();

    protected abstract AbstractSharedFlowSlot[] createSlotArray$ar$ds();

    /* JADX INFO: Access modifiers changed from: protected */
    public final void freeSlot(AbstractSharedFlowSlot abstractSharedFlowSlot) {
        int i;
        Continuation[] freeLocked;
        synchronized (this) {
            int i2 = this.nCollectors - 1;
            this.nCollectors = i2;
            if (i2 == 0) {
                this.nextIndex = 0;
            }
            abstractSharedFlowSlot.getClass();
            freeLocked = abstractSharedFlowSlot.freeLocked(this);
        }
        for (Continuation continuation : freeLocked) {
            if (continuation != null) {
                continuation.resumeWith(Unit.INSTANCE);
            }
        }
    }
}
