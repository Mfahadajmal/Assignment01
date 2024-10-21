package kotlinx.coroutines;

import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NodeList extends LockFreeLinkedListHead implements Incomplete {
    public final String getString(String str) {
        StringBuilder sb = new StringBuilder("List{");
        sb.append(str);
        sb.append("}[");
        Object next = getNext();
        next.getClass();
        boolean z = true;
        for (LockFreeLinkedListNode lockFreeLinkedListNode = (LockFreeLinkedListNode) next; !Intrinsics.areEqual(lockFreeLinkedListNode, this); lockFreeLinkedListNode = lockFreeLinkedListNode.getNextNode()) {
            if (lockFreeLinkedListNode instanceof JobNode) {
                JobNode jobNode = (JobNode) lockFreeLinkedListNode;
                if (!z) {
                    sb.append(", ");
                }
                sb.append(jobNode);
                z = false;
            }
        }
        sb.append("]");
        return sb.toString();
    }

    @Override // kotlinx.coroutines.Incomplete
    public final boolean isActive() {
        return true;
    }

    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public final String toString() {
        if (DebugKt.DEBUG) {
            return getString("Active");
        }
        return super.toString();
    }

    @Override // kotlinx.coroutines.Incomplete
    public final NodeList getList() {
        return this;
    }
}
