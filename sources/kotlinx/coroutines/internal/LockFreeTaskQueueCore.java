package kotlinx.coroutines.internal;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import com.google.mlkit.logging.schema.SubjectSegmentationOptionalModuleLogEvent;
import kotlinx.atomicfu.AtomicLong;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LockFreeTaskQueueCore {
    public static final SubjectSegmentationOptionalModuleLogEvent Companion$ar$class_merging$ed6cfe9a_0 = new SubjectSegmentationOptionalModuleLogEvent();
    public static final Symbol REMOVE_FROZEN = new Symbol("REMOVE_FROZEN");
    private final AtomicRef _next = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);
    public final AtomicLong _state = OnDeviceSubjectSegmentationCreateLogEvent.atomic(0L);
    private final ExecutorSelector array$ar$class_merging$ar$class_merging$ar$class_merging;
    private final int capacity;
    private final int mask;
    private final boolean singleConsumer;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Placeholder {
        public final int index;

        public Placeholder(int i) {
            this.index = i;
        }
    }

    public LockFreeTaskQueueCore(int i, boolean z) {
        this.capacity = i;
        this.singleConsumer = z;
        int i2 = i - 1;
        this.mask = i2;
        this.array$ar$class_merging$ar$class_merging$ar$class_merging = new ExecutorSelector(i);
        if (i2 <= 1073741823) {
            if ((i & i2) == 0) {
                return;
            } else {
                throw new IllegalStateException("Check failed.");
            }
        }
        throw new IllegalStateException("Check failed.");
    }

    public final int addLast(Object obj) {
        while (true) {
            long j = this._state.value;
            if ((3458764513820540928L & j) != 0) {
                if ((j & 2305843009213693952L) == 0) {
                    return 1;
                }
                return 2;
            }
            int i = this.mask;
            int i2 = (int) ((j & 1152921503533105152L) >> 30);
            int i3 = (int) (1073741823 & j);
            if (((i2 + 2) & i) == (i3 & i)) {
                return 1;
            }
            if (!this.singleConsumer) {
                if (this.array$ar$class_merging$ar$class_merging$ar$class_merging.get(i2 & i).value != null) {
                    int i4 = this.capacity;
                    if (i4 < 1024 || ((i2 - i3) & 1073741823) > (i4 >> 1)) {
                        break;
                    }
                }
            }
            if (this._state.compareAndSet(j, (((i2 + 1) & 1073741823) << 30) | SubjectSegmentationOptionalModuleLogEvent.wo$ar$ds(j, 1152921503533105152L))) {
                this.array$ar$class_merging$ar$class_merging$ar$class_merging.get(i2 & i).setValue(obj);
                LockFreeTaskQueueCore lockFreeTaskQueueCore = this;
                while ((lockFreeTaskQueueCore._state.value & 1152921504606846976L) != 0) {
                    lockFreeTaskQueueCore = lockFreeTaskQueueCore.next();
                    Object obj2 = lockFreeTaskQueueCore.array$ar$class_merging$ar$class_merging$ar$class_merging.get(lockFreeTaskQueueCore.mask & i2).value;
                    if ((obj2 instanceof Placeholder) && ((Placeholder) obj2).index == i2) {
                        lockFreeTaskQueueCore.array$ar$class_merging$ar$class_merging$ar$class_merging.get(lockFreeTaskQueueCore.mask & i2).setValue(obj);
                    } else {
                        lockFreeTaskQueueCore = null;
                    }
                    if (lockFreeTaskQueueCore == null) {
                        return 0;
                    }
                }
                return 0;
            }
        }
        return 1;
    }

    public final boolean close() {
        AtomicLong atomicLong;
        long j;
        do {
            atomicLong = this._state;
            j = atomicLong.value;
            if ((j & 2305843009213693952L) != 0) {
                return true;
            }
            if ((1152921504606846976L & j) != 0) {
                return false;
            }
        } while (!atomicLong.compareAndSet(j, 2305843009213693952L | j));
        return true;
    }

    public final boolean isEmpty() {
        long j = this._state.value;
        if (((int) (1073741823 & j)) == ((int) ((j & 1152921503533105152L) >> 30))) {
            return true;
        }
        return false;
    }

    public final LockFreeTaskQueueCore next() {
        long j;
        while (true) {
            AtomicLong atomicLong = this._state;
            j = atomicLong.value;
            if ((j & 1152921504606846976L) != 0) {
                break;
            }
            long j2 = j | 1152921504606846976L;
            if (atomicLong.compareAndSet(j, j2)) {
                j = j2;
                break;
            }
        }
        AtomicRef atomicRef = this._next;
        while (true) {
            LockFreeTaskQueueCore lockFreeTaskQueueCore = (LockFreeTaskQueueCore) atomicRef.value;
            if (lockFreeTaskQueueCore != null) {
                return lockFreeTaskQueueCore;
            }
            AtomicRef atomicRef2 = this._next;
            int i = this.capacity;
            LockFreeTaskQueueCore lockFreeTaskQueueCore2 = new LockFreeTaskQueueCore(i + i, this.singleConsumer);
            long j3 = 1152921503533105152L & j;
            int i2 = (int) (1073741823 & j);
            while (true) {
                int i3 = this.mask;
                int i4 = i2 & i3;
                if (i4 != (i3 & ((int) (j3 >> 30)))) {
                    Object obj = this.array$ar$class_merging$ar$class_merging$ar$class_merging.get(i4).value;
                    if (obj == null) {
                        obj = new Placeholder(i2);
                    }
                    lockFreeTaskQueueCore2.array$ar$class_merging$ar$class_merging$ar$class_merging.get(lockFreeTaskQueueCore2.mask & i2).setValue(obj);
                    i2++;
                }
            }
            lockFreeTaskQueueCore2._state.setValue(SubjectSegmentationOptionalModuleLogEvent.wo$ar$ds(j, 1152921504606846976L));
            atomicRef2.compareAndSet(null, lockFreeTaskQueueCore2);
        }
    }

    public final Object removeFirstOrNull() {
        while (true) {
            long j = this._state.value;
            long j2 = 1152921504606846976L;
            if ((j & 1152921504606846976L) != 0) {
                return REMOVE_FROZEN;
            }
            int i = this.mask;
            int i2 = (int) (j & 1073741823);
            int i3 = i2 & i;
            if ((((int) ((1152921503533105152L & j) >> 30)) & i) == i3) {
                return null;
            }
            Object obj = this.array$ar$class_merging$ar$class_merging$ar$class_merging.get(i3).value;
            if (obj == null) {
                if (this.singleConsumer) {
                    return null;
                }
            } else {
                if (obj instanceof Placeholder) {
                    return null;
                }
                int i4 = (i2 + 1) & 1073741823;
                if (this._state.compareAndSet(j, Companion$ar$class_merging$ed6cfe9a_0.updateHead(j, i4))) {
                    this.array$ar$class_merging$ar$class_merging$ar$class_merging.get(this.mask & i2).setValue(null);
                    return obj;
                }
                if (this.singleConsumer) {
                    LockFreeTaskQueueCore lockFreeTaskQueueCore = this;
                    while (true) {
                        AtomicLong atomicLong = lockFreeTaskQueueCore._state;
                        while (true) {
                            long j3 = atomicLong.value;
                            long j4 = j3 & 1073741823;
                            boolean z = DebugKt.ASSERTIONS_ENABLED;
                            if ((j3 & j2) != 0) {
                                lockFreeTaskQueueCore = lockFreeTaskQueueCore.next();
                                break;
                            }
                            if (lockFreeTaskQueueCore._state.compareAndSet(j3, Companion$ar$class_merging$ed6cfe9a_0.updateHead(j3, i4))) {
                                lockFreeTaskQueueCore.array$ar$class_merging$ar$class_merging$ar$class_merging.get(lockFreeTaskQueueCore.mask & ((int) j4)).setValue(null);
                                lockFreeTaskQueueCore = null;
                                break;
                            }
                            j2 = 1152921504606846976L;
                        }
                        if (lockFreeTaskQueueCore == null) {
                            return obj;
                        }
                        j2 = 1152921504606846976L;
                    }
                }
            }
        }
    }
}
