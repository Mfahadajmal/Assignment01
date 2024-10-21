package kotlin.collections;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.List;
import java.util.ListIterator;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReversedListReadOnly$listIterator$1 implements ListIterator, KMappedMarker {
    private final ListIterator delegateIterator;
    final /* synthetic */ ReversedListReadOnly this$0;

    public ReversedListReadOnly$listIterator$1(ReversedListReadOnly reversedListReadOnly, int i) {
        this.this$0 = reversedListReadOnly;
        List list = reversedListReadOnly.delegate;
        if (new IntRange(0, reversedListReadOnly.size()).contains(i)) {
            this.delegateIterator = list.listIterator(reversedListReadOnly.size() - i);
            return;
        }
        throw new IndexOutOfBoundsException("Position index " + i + " must be in range [" + new IntRange(0, reversedListReadOnly.size()) + "].");
    }

    @Override // java.util.ListIterator
    public final void add(Object obj) {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_7();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final boolean hasNext() {
        return this.delegateIterator.hasPrevious();
    }

    @Override // java.util.ListIterator
    public final boolean hasPrevious() {
        return this.delegateIterator.hasNext();
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final Object next() {
        return this.delegateIterator.previous();
    }

    @Override // java.util.ListIterator
    public final int nextIndex() {
        return OnDeviceLanguageIdentificationLogEvent.reverseIteratorIndex$CollectionsKt__ReversedViewsKt(this.this$0, this.delegateIterator.previousIndex());
    }

    @Override // java.util.ListIterator
    public final Object previous() {
        return this.delegateIterator.next();
    }

    @Override // java.util.ListIterator
    public final int previousIndex() {
        return OnDeviceLanguageIdentificationLogEvent.reverseIteratorIndex$CollectionsKt__ReversedViewsKt(this.this$0, this.delegateIterator.nextIndex());
    }

    @Override // java.util.ListIterator, java.util.Iterator
    public final void remove() {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }

    @Override // java.util.ListIterator
    public final void set(Object obj) {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_7();
    }
}
