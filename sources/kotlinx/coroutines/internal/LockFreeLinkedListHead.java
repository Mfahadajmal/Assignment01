package kotlinx.coroutines.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LockFreeLinkedListHead extends LockFreeLinkedListNode {
    @Override // kotlinx.coroutines.internal.LockFreeLinkedListNode
    public final boolean isRemoved() {
        return false;
    }
}
