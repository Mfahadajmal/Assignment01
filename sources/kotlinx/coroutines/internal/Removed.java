package kotlinx.coroutines.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Removed {
    public final LockFreeLinkedListNode ref;

    public Removed(LockFreeLinkedListNode lockFreeLinkedListNode) {
        this.ref = lockFreeLinkedListNode;
    }

    public final String toString() {
        return "Removed[" + this.ref + "]";
    }
}
