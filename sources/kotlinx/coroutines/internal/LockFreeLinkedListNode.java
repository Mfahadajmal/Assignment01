package kotlinx.coroutines.internal;

import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import kotlin.jvm.internal.PropertyReference;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugStringsKt;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LockFreeLinkedListNode {
    public final AtomicRef _next = OnDeviceSubjectSegmentationCreateLogEvent.atomic(this);
    public final AtomicRef _prev = OnDeviceSubjectSegmentationCreateLogEvent.atomic(this);
    public final AtomicRef _removedRef = OnDeviceSubjectSegmentationCreateLogEvent.atomic((Object) null);

    /* JADX WARN: Code restructure failed: missing block: B:22:0x003f, code lost:
    
        if (r3._next.compareAndSet(r2, ((kotlinx.coroutines.internal.Removed) r4).ref) == false) goto L34;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlinx.coroutines.internal.LockFreeLinkedListNode correctPrev$ar$ds() {
        /*
            r7 = this;
        L0:
            kotlinx.atomicfu.AtomicRef r0 = r7._prev
            java.lang.Object r0 = r0.value
            kotlinx.coroutines.internal.LockFreeLinkedListNode r0 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r0
            r1 = 0
            r2 = r0
        L8:
            r3 = r1
        L9:
            kotlinx.atomicfu.AtomicRef r4 = r2._next
            java.lang.Object r4 = r4.value
            if (r4 != r7) goto L1b
            if (r0 != r2) goto L12
            return r2
        L12:
            kotlinx.atomicfu.AtomicRef r1 = r7._prev
            boolean r0 = r1.compareAndSet(r0, r2)
            if (r0 == 0) goto L0
            return r2
        L1b:
            boolean r5 = r7.isRemoved()
            if (r5 == 0) goto L22
            return r1
        L22:
            if (r4 != 0) goto L25
            return r2
        L25:
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.OpDescriptor
            if (r5 == 0) goto L2f
            kotlinx.coroutines.internal.OpDescriptor r4 = (kotlinx.coroutines.internal.OpDescriptor) r4
            r4.perform(r2)
            goto L0
        L2f:
            boolean r5 = r4 instanceof kotlinx.coroutines.internal.Removed
            if (r5 == 0) goto L4a
            if (r3 == 0) goto L43
            kotlinx.coroutines.internal.Removed r4 = (kotlinx.coroutines.internal.Removed) r4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r4 = r4.ref
            kotlinx.atomicfu.AtomicRef r5 = r3._next
            boolean r2 = r5.compareAndSet(r2, r4)
            if (r2 == 0) goto L0
            r2 = r3
            goto L8
        L43:
            kotlinx.atomicfu.AtomicRef r2 = r2._prev
            java.lang.Object r2 = r2.value
            kotlinx.coroutines.internal.LockFreeLinkedListNode r2 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r2
            goto L9
        L4a:
            r3 = r4
            kotlinx.coroutines.internal.LockFreeLinkedListNode r3 = (kotlinx.coroutines.internal.LockFreeLinkedListNode) r3
            r6 = r3
            r3 = r2
            r2 = r6
            goto L9
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.internal.LockFreeLinkedListNode.correctPrev$ar$ds():kotlinx.coroutines.internal.LockFreeLinkedListNode");
    }

    public final void finishAdd(LockFreeLinkedListNode lockFreeLinkedListNode) {
        LockFreeLinkedListNode lockFreeLinkedListNode2;
        do {
            lockFreeLinkedListNode2 = (LockFreeLinkedListNode) lockFreeLinkedListNode._prev.value;
            if (getNext() != lockFreeLinkedListNode) {
                return;
            }
        } while (!lockFreeLinkedListNode._prev.compareAndSet(lockFreeLinkedListNode2, this));
        if (isRemoved()) {
            lockFreeLinkedListNode.correctPrev$ar$ds();
        }
    }

    public final Object getNext() {
        while (true) {
            Object obj = this._next.value;
            if (!(obj instanceof OpDescriptor)) {
                return obj;
            }
            ((OpDescriptor) obj).perform(this);
        }
    }

    public final LockFreeLinkedListNode getNextNode() {
        Removed removed;
        Object next = getNext();
        if (next instanceof Removed) {
            removed = (Removed) next;
        } else {
            removed = null;
        }
        if (removed != null) {
            return removed.ref;
        }
        next.getClass();
        return (LockFreeLinkedListNode) next;
    }

    public final LockFreeLinkedListNode getPrevNode() {
        LockFreeLinkedListNode correctPrev$ar$ds = correctPrev$ar$ds();
        if (correctPrev$ar$ds == null) {
            Object obj = this._prev.value;
            while (true) {
                LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) obj;
                if (!lockFreeLinkedListNode.isRemoved()) {
                    return lockFreeLinkedListNode;
                }
                obj = lockFreeLinkedListNode._prev.value;
            }
        } else {
            return correctPrev$ar$ds;
        }
    }

    public boolean isRemoved() {
        return getNext() instanceof Removed;
    }

    public String toString() {
        return new PropertyReference(this) { // from class: kotlinx.coroutines.internal.LockFreeLinkedListNode$toString$1
            @Override // kotlin.jvm.internal.PropertyReference
            public final Object get() {
                return DebugStringsKt.getClassSimpleName(this.receiver);
            }
        } + "@" + DebugStringsKt.getHexAddress(this);
    }
}
