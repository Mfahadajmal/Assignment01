package kotlinx.coroutines.flow;

import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import java.util.Arrays;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.CancelHandler;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlow;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowKt;
import kotlinx.coroutines.flow.internal.AbstractSharedFlowSlot;
import kotlinx.coroutines.internal.Symbol;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SharedFlowImpl extends AbstractSharedFlow implements MutableSharedFlow, Flow {
    public Object[] buffer;
    private int bufferSize;
    public long minCollectorIndex;
    private final int onBufferOverflow$ar$edu;
    private int queueSize;
    public long replayIndex;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Emitter implements DisposableHandle {
        public final Continuation cont;
        public final SharedFlowImpl flow;
        public final long index;
        public final Object value;

        public Emitter(SharedFlowImpl sharedFlowImpl, long j, Object obj, Continuation continuation) {
            this.flow = sharedFlowImpl;
            this.index = j;
            this.value = obj;
            this.cont = continuation;
        }

        @Override // kotlinx.coroutines.DisposableHandle
        public final void dispose() {
            SharedFlowImpl sharedFlowImpl = this.flow;
            synchronized (sharedFlowImpl) {
                if (this.index < sharedFlowImpl.getHead()) {
                    return;
                }
                Object[] objArr = sharedFlowImpl.buffer;
                objArr.getClass();
                if (SharedFlowKt.getBufferAt(objArr, this.index) != this) {
                    return;
                }
                SharedFlowKt.setBufferAt(objArr, this.index, SharedFlowKt.NO_VALUE);
                sharedFlowImpl.cleanupTailLocked();
            }
        }
    }

    public SharedFlowImpl(int i) {
        this.onBufferOverflow$ar$edu = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0082 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0029  */
    /* JADX WARN: Type inference failed for: r0v12, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v15, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6, types: [kotlinx.coroutines.flow.FlowCollector, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v7, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ java.lang.Object collect$suspendImpl(kotlinx.coroutines.flow.SharedFlowImpl r19, kotlinx.coroutines.flow.FlowCollector r20, kotlin.coroutines.Continuation r21) {
        /*
            Method dump skipped, instructions count: 316
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.SharedFlowImpl.collect$suspendImpl(kotlinx.coroutines.flow.SharedFlowImpl, kotlinx.coroutines.flow.FlowCollector, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void dropOldestLocked() {
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        Object[] objArr = this.buffer;
        objArr.getClass();
        SharedFlowKt.setBufferAt(objArr, getHead(), null);
        this.bufferSize--;
        long head = getHead() + 1;
        if (this.replayIndex < head) {
            this.replayIndex = head;
        }
        if (this.minCollectorIndex < head) {
            if (this.nCollectors != 0 && (abstractSharedFlowSlotArr = this.slots) != null) {
                for (AbstractSharedFlowSlot abstractSharedFlowSlot : abstractSharedFlowSlotArr) {
                    if (abstractSharedFlowSlot != null) {
                        SharedFlowSlot sharedFlowSlot = (SharedFlowSlot) abstractSharedFlowSlot;
                        long j = sharedFlowSlot.index;
                        if (j >= 0 && j < head) {
                            sharedFlowSlot.index = head;
                        }
                    }
                }
            }
            this.minCollectorIndex = head;
        }
        boolean z = DebugKt.ASSERTIONS_ENABLED;
    }

    private final void enqueueLocked(Object obj) {
        int totalSize = getTotalSize();
        Object[] objArr = this.buffer;
        if (objArr == null) {
            objArr = growBuffer(null, 0, 2);
        } else {
            int length = objArr.length;
            if (totalSize >= length) {
                objArr = growBuffer(objArr, totalSize, length + length);
            }
        }
        SharedFlowKt.setBufferAt(objArr, getHead() + totalSize, obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.lang.Object[], java.lang.Object] */
    private final Continuation[] findSlotsToResumeLocked(Continuation[] continuationArr) {
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        SharedFlowSlot sharedFlowSlot;
        Continuation continuation;
        if (this.nCollectors != 0 && (abstractSharedFlowSlotArr = this.slots) != null) {
            int length = continuationArr.length;
            int i = 0;
            continuationArr = continuationArr;
            while (i < abstractSharedFlowSlotArr.length) {
                AbstractSharedFlowSlot abstractSharedFlowSlot = abstractSharedFlowSlotArr[i];
                if (abstractSharedFlowSlot != null && (continuation = (sharedFlowSlot = (SharedFlowSlot) abstractSharedFlowSlot).cont) != null && tryPeekLocked(sharedFlowSlot) >= 0) {
                    int length2 = continuationArr.length;
                    continuationArr = continuationArr;
                    if (length >= length2) {
                        ?? copyOf = Arrays.copyOf(continuationArr, Math.max(2, length2 + length2));
                        copyOf.getClass();
                        continuationArr = copyOf;
                    }
                    continuationArr[length] = continuation;
                    sharedFlowSlot.cont = null;
                    length++;
                }
                i++;
                continuationArr = continuationArr;
            }
        }
        return continuationArr;
    }

    private final long getBufferEndIndex() {
        return getHead() + this.bufferSize;
    }

    private final int getTotalSize() {
        return this.bufferSize + this.queueSize;
    }

    private final Object[] growBuffer(Object[] objArr, int i, int i2) {
        if (i2 > 0) {
            Object[] objArr2 = new Object[i2];
            this.buffer = objArr2;
            if (objArr != null) {
                long head = getHead();
                for (int i3 = 0; i3 < i; i3++) {
                    long j = i3 + head;
                    SharedFlowKt.setBufferAt(objArr2, j, SharedFlowKt.getBufferAt(objArr, j));
                }
            }
            return objArr2;
        }
        throw new IllegalStateException("Buffer size overflow");
    }

    private final boolean tryEmitLocked(Object obj) {
        if (this.nCollectors == 0) {
            boolean z = DebugKt.ASSERTIONS_ENABLED;
            enqueueLocked(obj);
            int i = this.bufferSize + 1;
            this.bufferSize = i;
            if (i > 1) {
                dropOldestLocked();
            }
            this.minCollectorIndex = getHead() + this.bufferSize;
            return true;
        }
        if (this.bufferSize > 0 && this.minCollectorIndex <= this.replayIndex) {
            int i2 = this.onBufferOverflow$ar$edu;
            int i3 = BufferOverflow.SUSPEND$ar$edu;
            if (i2 != 0) {
                int i4 = i2 - 1;
                if (i4 != 0) {
                    if (i4 == 2) {
                        return true;
                    }
                } else {
                    return false;
                }
            } else {
                throw null;
            }
        }
        enqueueLocked(obj);
        int i5 = this.bufferSize + 1;
        this.bufferSize = i5;
        if (i5 > 1) {
            dropOldestLocked();
        }
        long head = getHead() + this.bufferSize;
        long j = this.replayIndex;
        if (((int) (head - j)) > 1) {
            updateBufferLocked(j + 1, this.minCollectorIndex, getBufferEndIndex(), getHead() + this.bufferSize + this.queueSize);
        }
        return true;
    }

    private final long tryPeekLocked(SharedFlowSlot sharedFlowSlot) {
        long j = sharedFlowSlot.index;
        if (j < getBufferEndIndex()) {
            return j;
        }
        return -1L;
    }

    private final void updateBufferLocked(long j, long j2, long j3, long j4) {
        long min = Math.min(j2, j);
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        for (long head = getHead(); head < min; head++) {
            Object[] objArr = this.buffer;
            objArr.getClass();
            SharedFlowKt.setBufferAt(objArr, head, null);
        }
        this.replayIndex = j;
        this.minCollectorIndex = j2;
        this.bufferSize = (int) (j3 - min);
        this.queueSize = (int) (j4 - j3);
    }

    public final void cleanupTailLocked() {
        Object[] objArr = this.buffer;
        objArr.getClass();
        while (this.queueSize > 0 && SharedFlowKt.getBufferAt(objArr, (getHead() + getTotalSize()) - 1) == SharedFlowKt.NO_VALUE) {
            this.queueSize--;
            SharedFlowKt.setBufferAt(objArr, getHead() + getTotalSize(), null);
        }
    }

    @Override // kotlinx.coroutines.flow.SharedFlow, kotlinx.coroutines.flow.Flow
    public final Object collect(FlowCollector flowCollector, Continuation continuation) {
        return collect$suspendImpl(this, flowCollector, continuation);
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public final /* synthetic */ AbstractSharedFlowSlot createSlot() {
        return new SharedFlowSlot();
    }

    @Override // kotlinx.coroutines.flow.internal.AbstractSharedFlow
    public final /* bridge */ /* synthetic */ AbstractSharedFlowSlot[] createSlotArray$ar$ds() {
        return new SharedFlowSlot[2];
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public final Object emit(Object obj, Continuation continuation) {
        Emitter emitter;
        if (tryEmit(obj)) {
            return Unit.INSTANCE;
        }
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(OnDevicePoseDetectionLogEvent.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        Continuation[] continuationArr = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized (this) {
            if (tryEmitLocked(obj)) {
                cancellableContinuationImpl.resumeWith(Unit.INSTANCE);
                continuationArr = findSlotsToResumeLocked(continuationArr);
                emitter = null;
            } else {
                Emitter emitter2 = new Emitter(this, getTotalSize() + getHead(), obj, cancellableContinuationImpl);
                enqueueLocked(emitter2);
                this.queueSize++;
                emitter = emitter2;
            }
        }
        if (emitter != null) {
            cancellableContinuationImpl.invokeOnCancellationImpl(new CancelHandler.UserSupplied(emitter, 2));
        }
        for (Continuation continuation2 : continuationArr) {
            if (continuation2 != null) {
                continuation2.resumeWith(Unit.INSTANCE);
            }
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result != CoroutineSingletons.COROUTINE_SUSPENDED) {
            result = Unit.INSTANCE;
        }
        if (result != CoroutineSingletons.COROUTINE_SUSPENDED) {
            return Unit.INSTANCE;
        }
        return result;
    }

    public final long getHead() {
        return Math.min(this.minCollectorIndex, this.replayIndex);
    }

    @Override // kotlinx.coroutines.flow.MutableSharedFlow
    public final boolean tryEmit(Object obj) {
        int i;
        boolean z;
        Continuation[] continuationArr = AbstractSharedFlowKt.EMPTY_RESUMES;
        synchronized (this) {
            if (tryEmitLocked(obj)) {
                continuationArr = findSlotsToResumeLocked(continuationArr);
                z = true;
            } else {
                z = false;
            }
        }
        for (Continuation continuation : continuationArr) {
            if (continuation != null) {
                continuation.resumeWith(Unit.INSTANCE);
            }
        }
        return z;
    }

    public final Continuation[] updateCollectorIndexLocked$kotlinx_coroutines_core(long j) {
        int i;
        long j2;
        long j3;
        long j4;
        long j5;
        AbstractSharedFlowSlot[] abstractSharedFlowSlotArr;
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        if (j <= this.minCollectorIndex) {
            long head = getHead();
            long j6 = this.bufferSize + head;
            int i2 = 0;
            if (this.nCollectors != 0 && (abstractSharedFlowSlotArr = this.slots) != null) {
                for (AbstractSharedFlowSlot abstractSharedFlowSlot : abstractSharedFlowSlotArr) {
                    if (abstractSharedFlowSlot != null) {
                        long j7 = ((SharedFlowSlot) abstractSharedFlowSlot).index;
                        if (j7 >= 0 && j7 < j6) {
                            j6 = j7;
                        }
                    }
                }
            }
            if (j6 > this.minCollectorIndex) {
                long bufferEndIndex = getBufferEndIndex();
                if (this.nCollectors > 0) {
                    i = Math.min(this.queueSize, 1 - ((int) (bufferEndIndex - j6)));
                } else {
                    i = this.queueSize;
                }
                Continuation[] continuationArr = AbstractSharedFlowKt.EMPTY_RESUMES;
                long j8 = this.queueSize + bufferEndIndex;
                if (i > 0) {
                    continuationArr = new Continuation[i];
                    Object[] objArr = this.buffer;
                    objArr.getClass();
                    long j9 = bufferEndIndex;
                    while (true) {
                        if (bufferEndIndex < j8) {
                            Object bufferAt = SharedFlowKt.getBufferAt(objArr, bufferEndIndex);
                            j2 = j6;
                            Symbol symbol = SharedFlowKt.NO_VALUE;
                            if (bufferAt != symbol) {
                                bufferAt.getClass();
                                int i3 = i2 + 1;
                                Emitter emitter = (Emitter) bufferAt;
                                j3 = j8;
                                continuationArr[i2] = emitter.cont;
                                SharedFlowKt.setBufferAt(objArr, bufferEndIndex, symbol);
                                SharedFlowKt.setBufferAt(objArr, j9, emitter.value);
                                j9++;
                                if (i3 >= i) {
                                    break;
                                }
                                i2 = i3;
                            } else {
                                j3 = j8;
                            }
                            bufferEndIndex++;
                            j6 = j2;
                            j8 = j3;
                        } else {
                            j2 = j6;
                            j3 = j8;
                            break;
                        }
                    }
                    j4 = j9;
                } else {
                    j2 = j6;
                    j3 = j8;
                    j4 = bufferEndIndex;
                }
                long j10 = j4 - head;
                if (this.nCollectors == 0) {
                    j5 = j4;
                } else {
                    j5 = j2;
                }
                updateBufferLocked(Math.max(this.replayIndex, j4 - Math.min(1, (int) j10)), j5, j4, j3);
                cleanupTailLocked();
                if (continuationArr.length == 0) {
                    return continuationArr;
                }
                return findSlotsToResumeLocked(continuationArr);
            }
        }
        return AbstractSharedFlowKt.EMPTY_RESUMES;
    }
}
