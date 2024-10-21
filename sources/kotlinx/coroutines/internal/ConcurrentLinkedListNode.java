package kotlinx.coroutines.internal;

import com.google.mlkit.common.sdkinternal.ExecutorSelector;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.atomicfu.TraceBase$None;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.NotCompleted;
import kotlinx.coroutines.channels.BufferedChannel;
import kotlinx.coroutines.channels.BufferedChannelKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConcurrentLinkedListNode implements NotCompleted {
    private final BufferedChannel _channel;
    public final AtomicRef _next;
    private final AtomicRef _prev;
    private final AtomicInt cleanedAndPointers;
    private final ExecutorSelector data$ar$class_merging$ar$class_merging$ar$class_merging;
    public final long id;

    public ConcurrentLinkedListNode(long j, ConcurrentLinkedListNode concurrentLinkedListNode, BufferedChannel bufferedChannel, int i) {
        this(j, concurrentLinkedListNode, i);
        this._channel = bufferedChannel;
        int i2 = BufferedChannelKt.SEGMENT_SIZE;
        this.data$ar$class_merging$ar$class_merging$ar$class_merging = new ExecutorSelector(i2 + i2);
    }

    public final boolean casState$kotlinx_coroutines_core(int i, Object obj, Object obj2) {
        return this.data$ar$class_merging$ar$class_merging$ar$class_merging.get(i + i + 1).compareAndSet(obj, obj2);
    }

    public final void cleanElement$kotlinx_coroutines_core(int i) {
        setElementLazy(i, null);
    }

    public final void cleanPrev() {
        this._prev.lazySet(null);
    }

    public final boolean decPointers$kotlinx_coroutines_core() {
        AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = AtomicInt.FU;
        AtomicInt atomicInt = this.cleanedAndPointers;
        int addAndGet = atomicIntegerFieldUpdater.addAndGet(atomicInt, -65536);
        OnDeviceSubjectSegmentationInferenceLogEvent onDeviceSubjectSegmentationInferenceLogEvent = atomicInt.trace$ar$class_merging$ar$class_merging;
        TraceBase$None traceBase$None = TraceBase$None.INSTANCE;
        if (addAndGet == BufferedChannelKt.SEGMENT_SIZE && !isTail()) {
            return true;
        }
        return false;
    }

    public final Object getAndSetState$kotlinx_coroutines_core(int i, Object obj) {
        return this.data$ar$class_merging$ar$class_merging$ar$class_merging.get(i + i + 1).getAndSet(obj);
    }

    public final BufferedChannel getChannel() {
        BufferedChannel bufferedChannel = this._channel;
        bufferedChannel.getClass();
        return bufferedChannel;
    }

    public final Object getElement$kotlinx_coroutines_core(int i) {
        return this.data$ar$class_merging$ar$class_merging$ar$class_merging.get(i + i).value;
    }

    public final ConcurrentLinkedListNode getNext() {
        Object nextOrClosed = getNextOrClosed();
        if (nextOrClosed == ConcurrentLinkedListKt.CLOSED) {
            return null;
        }
        return (ConcurrentLinkedListNode) nextOrClosed;
    }

    public final Object getNextOrClosed() {
        return this._next.value;
    }

    public final ConcurrentLinkedListNode getPrev() {
        return (ConcurrentLinkedListNode) this._prev.value;
    }

    public final Object getState$kotlinx_coroutines_core(int i) {
        return this.data$ar$class_merging$ar$class_merging$ar$class_merging.get(i + i + 1).value;
    }

    public final boolean isRemoved() {
        if (this.cleanedAndPointers.value == BufferedChannelKt.SEGMENT_SIZE && !isTail()) {
            return true;
        }
        return false;
    }

    public final boolean isTail() {
        if (getNext() == null) {
            return true;
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:51:0x004d, code lost:
    
        cleanElement$kotlinx_coroutines_core(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0050, code lost:
    
        if (r4 == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0052, code lost:
    
        getChannel();
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0055, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onCancellation$ar$ds(int r3, kotlin.coroutines.CoroutineContext r4) {
        /*
            r2 = this;
            int r4 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            if (r3 < r4) goto L6
            r4 = 1
            goto L7
        L6:
            r4 = 0
        L7:
            if (r4 == 0) goto Lc
            int r0 = kotlinx.coroutines.channels.BufferedChannelKt.SEGMENT_SIZE
            int r3 = r3 - r0
        Lc:
            r2.getElement$kotlinx_coroutines_core(r3)
        Lf:
            java.lang.Object r0 = r2.getState$kotlinx_coroutines_core(r3)
            boolean r1 = r0 instanceof kotlinx.coroutines.Waiter
            if (r1 != 0) goto L56
            boolean r1 = r0 instanceof kotlinx.coroutines.channels.WaiterEB
            if (r1 == 0) goto L1c
            goto L56
        L1c:
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            if (r0 == r1) goto L4d
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
            if (r0 != r1) goto L25
            goto L4d
        L25:
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_EB
            if (r0 == r1) goto Lf
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.RESUMING_BY_RCV
            if (r0 == r1) goto Lf
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.DONE_RCV
            if (r0 == r3) goto L70
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.BUFFERED
            if (r0 == r3) goto L70
            kotlinx.coroutines.internal.Symbol r3 = kotlinx.coroutines.channels.BufferedChannelKt.CHANNEL_CLOSED
            if (r0 != r3) goto L3a
            goto L70
        L3a:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.util.Objects.toString(r0)
            java.lang.String r4 = java.lang.String.valueOf(r0)
            java.lang.String r0 = "unexpected state: "
            java.lang.String r4 = r0.concat(r4)
            r3.<init>(r4)
            throw r3
        L4d:
            r2.cleanElement$kotlinx_coroutines_core(r3)
            if (r4 == 0) goto L70
            r2.getChannel()
            return
        L56:
            if (r4 == 0) goto L5b
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_SEND
            goto L5d
        L5b:
            kotlinx.coroutines.internal.Symbol r1 = kotlinx.coroutines.channels.BufferedChannelKt.INTERRUPTED_RCV
        L5d:
            boolean r0 = r2.casState$kotlinx_coroutines_core(r3, r0, r1)
            if (r0 == 0) goto Lf
            r2.cleanElement$kotlinx_coroutines_core(r3)
            r0 = r4 ^ 1
            r2.onCancelledRequest(r3, r0)
            if (r4 == 0) goto L70
            r2.getChannel()
        L70:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.ConcurrentLinkedListNode.onCancellation$ar$ds(int, kotlin.coroutines.CoroutineContext):void");
    }

    public final void onCancelledRequest(int i, boolean z) {
        if (z) {
            getChannel().waitExpandBufferCompletion$kotlinx_coroutines_core((this.id * BufferedChannelKt.SEGMENT_SIZE) + i);
        }
        onSlotCleaned();
    }

    public final void onSlotCleaned() {
        if (this.cleanedAndPointers.incrementAndGet() == BufferedChannelKt.SEGMENT_SIZE) {
            remove();
        }
    }

    public final void remove() {
        Object obj;
        ConcurrentLinkedListNode concurrentLinkedListNode;
        ConcurrentLinkedListNode next;
        boolean z = DebugKt.ASSERTIONS_ENABLED;
        if (isTail()) {
            return;
        }
        while (true) {
            ConcurrentLinkedListNode prev = getPrev();
            while (prev != null && prev.isRemoved()) {
                prev = (ConcurrentLinkedListNode) prev._prev.value;
            }
            ConcurrentLinkedListNode next2 = getNext();
            next2.getClass();
            while (next2.isRemoved() && (next = next2.getNext()) != null) {
                next2 = next;
            }
            AtomicRef atomicRef = next2._prev;
            do {
                obj = atomicRef.value;
                if (((ConcurrentLinkedListNode) obj) == null) {
                    concurrentLinkedListNode = null;
                } else {
                    concurrentLinkedListNode = prev;
                }
            } while (!atomicRef.compareAndSet(obj, concurrentLinkedListNode));
            if (prev != null) {
                prev._next.setValue(next2);
            }
            if (!next2.isRemoved() || next2.isTail()) {
                if (prev == null || !prev.isRemoved()) {
                    return;
                }
            }
        }
    }

    public final Object retrieveElement$kotlinx_coroutines_core(int i) {
        Object element$kotlinx_coroutines_core = getElement$kotlinx_coroutines_core(i);
        cleanElement$kotlinx_coroutines_core(i);
        return element$kotlinx_coroutines_core;
    }

    public final void setElementLazy(int i, Object obj) {
        this.data$ar$class_merging$ar$class_merging$ar$class_merging.get(i + i).lazySet(obj);
    }

    public final void setState$kotlinx_coroutines_core(int i, Object obj) {
        this.data$ar$class_merging$ar$class_merging$ar$class_merging.get(i + i + 1).setValue(obj);
    }

    public final boolean tryIncPointers$kotlinx_coroutines_core() {
        AtomicInt atomicInt;
        int i;
        do {
            atomicInt = this.cleanedAndPointers;
            i = atomicInt.value;
            if (i == BufferedChannelKt.SEGMENT_SIZE && !isTail()) {
                return false;
            }
        } while (!atomicInt.compareAndSet(i, 65536 + i));
        return true;
    }

    public ConcurrentLinkedListNode(ConcurrentLinkedListNode concurrentLinkedListNode) {
        this._next = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);
        this._prev = OnDeviceSubjectSegmentationCreateLogEvent.atomic(concurrentLinkedListNode);
    }

    public ConcurrentLinkedListNode(long j, ConcurrentLinkedListNode concurrentLinkedListNode, int i) {
        this(concurrentLinkedListNode);
        this.id = j;
        this.cleanedAndPointers = OnDeviceSubjectSegmentationCreateLogEvent.atomic(i << 16);
    }
}
