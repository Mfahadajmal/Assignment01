package kotlinx.coroutines.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SegmentOrClosed {
    /* renamed from: getSegment-impl$ar$class_merging, reason: not valid java name */
    public static final ConcurrentLinkedListNode m261getSegmentimpl$ar$class_merging(Object obj) {
        if (obj != ConcurrentLinkedListKt.CLOSED) {
            obj.getClass();
            return (ConcurrentLinkedListNode) obj;
        }
        throw new IllegalStateException("Does not contain segment");
    }

    /* renamed from: isClosed-impl, reason: not valid java name */
    public static final boolean m262isClosedimpl(Object obj) {
        if (obj == ConcurrentLinkedListKt.CLOSED) {
            return true;
        }
        return false;
    }

    public final boolean equals(Object obj) {
        throw null;
    }

    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        throw null;
    }
}
