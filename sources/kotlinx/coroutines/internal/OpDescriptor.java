package kotlinx.coroutines.internal;

import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationCreateLogEvent;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.DebugKt;
import kotlinx.coroutines.DebugStringsKt;
import kotlinx.coroutines.JobSupport;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class OpDescriptor {
    final /* synthetic */ Object $expect$inlined;
    private final AtomicRef _consensus;
    public final LockFreeLinkedListNode newNode;
    public LockFreeLinkedListNode oldNext;
    final /* synthetic */ JobSupport this$0;

    public OpDescriptor() {
    }

    public final /* bridge */ /* synthetic */ void complete(Object obj, Object obj2) {
        boolean z;
        LockFreeLinkedListNode lockFreeLinkedListNode;
        LockFreeLinkedListNode lockFreeLinkedListNode2 = (LockFreeLinkedListNode) obj;
        if (obj2 == null) {
            z = true;
        } else {
            z = false;
        }
        if (z) {
            lockFreeLinkedListNode = this.newNode;
        } else {
            lockFreeLinkedListNode = this.oldNext;
        }
        if (lockFreeLinkedListNode != null && lockFreeLinkedListNode2._next.compareAndSet(this, lockFreeLinkedListNode) && z) {
            LockFreeLinkedListNode lockFreeLinkedListNode3 = this.newNode;
            LockFreeLinkedListNode lockFreeLinkedListNode4 = this.oldNext;
            lockFreeLinkedListNode4.getClass();
            lockFreeLinkedListNode3.finishAdd(lockFreeLinkedListNode4);
        }
    }

    public final Object perform(Object obj) {
        Object obj2 = this._consensus.value;
        if (obj2 == AtomicKt.NO_DECISION) {
            obj2 = prepare(obj);
            boolean z = DebugKt.ASSERTIONS_ENABLED;
            Object obj3 = this._consensus.value;
            Object obj4 = AtomicKt.NO_DECISION;
            if (obj3 != obj4) {
                obj2 = obj3;
            } else if (!this._consensus.compareAndSet(obj4, obj2)) {
                obj2 = this._consensus.value;
            }
        }
        complete(obj, obj2);
        return obj2;
    }

    public final /* bridge */ /* synthetic */ Object prepare(Object obj) {
        if (this.this$0.getState$kotlinx_coroutines_core() == this.$expect$inlined) {
            return null;
        }
        return LockFreeLinkedListKt.CONDITION_FALSE;
    }

    public final String toString() {
        return DebugStringsKt.getClassSimpleName(this) + "@" + DebugStringsKt.getHexAddress(this);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public OpDescriptor(LockFreeLinkedListNode lockFreeLinkedListNode, JobSupport jobSupport, Object obj) {
        this(lockFreeLinkedListNode);
        this.this$0 = jobSupport;
        this.$expect$inlined = obj;
    }

    public OpDescriptor(byte[] bArr) {
        this();
        this._consensus = OnDeviceSubjectSegmentationCreateLogEvent.atomic(AtomicKt.NO_DECISION);
    }

    public OpDescriptor(LockFreeLinkedListNode lockFreeLinkedListNode) {
        this((byte[]) null);
        this.newNode = lockFreeLinkedListNode;
    }
}
