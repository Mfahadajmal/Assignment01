package kotlinx.coroutines.internal;

import kotlin.jvm.functions.Function2;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConcurrentLinkedListKt {
    public static final Symbol CLOSED = new Symbol("CLOSED");

    public static final ConcurrentLinkedListNode close(ConcurrentLinkedListNode concurrentLinkedListNode) {
        while (true) {
            Object nextOrClosed = concurrentLinkedListNode.getNextOrClosed();
            Symbol symbol = CLOSED;
            if (nextOrClosed == symbol) {
                break;
            }
            ConcurrentLinkedListNode concurrentLinkedListNode2 = (ConcurrentLinkedListNode) nextOrClosed;
            if (concurrentLinkedListNode2 == null) {
                if (concurrentLinkedListNode._next.compareAndSet(null, symbol)) {
                    break;
                }
            } else {
                concurrentLinkedListNode = concurrentLinkedListNode2;
            }
        }
        return concurrentLinkedListNode;
    }

    public static final Object findSegmentInternal$ar$class_merging(ConcurrentLinkedListNode concurrentLinkedListNode, long j, Function2 function2) {
        while (true) {
            if (concurrentLinkedListNode.id >= j && !concurrentLinkedListNode.isRemoved()) {
                return concurrentLinkedListNode;
            }
            Object nextOrClosed = concurrentLinkedListNode.getNextOrClosed();
            Symbol symbol = CLOSED;
            if (nextOrClosed == symbol) {
                return symbol;
            }
            ConcurrentLinkedListNode concurrentLinkedListNode2 = (ConcurrentLinkedListNode) nextOrClosed;
            if (concurrentLinkedListNode2 == null) {
                concurrentLinkedListNode2 = (ConcurrentLinkedListNode) function2.invoke(Long.valueOf(concurrentLinkedListNode.id + 1), concurrentLinkedListNode);
                if (concurrentLinkedListNode._next.compareAndSet(null, concurrentLinkedListNode2)) {
                    if (concurrentLinkedListNode.isRemoved()) {
                        concurrentLinkedListNode.remove();
                    }
                }
            }
            concurrentLinkedListNode = concurrentLinkedListNode2;
        }
    }
}
