package androidx.collection;

import _COROUTINE._BOUNDARY;
import java.util.Iterator;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SparseArrayKt$valueIterator$1 implements Iterator, KMappedMarker {
    final /* synthetic */ SparseArrayCompat $this_valueIterator;
    private int index;

    public SparseArrayKt$valueIterator$1(SparseArrayCompat sparseArrayCompat) {
        this.$this_valueIterator = sparseArrayCompat;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.index < this.$this_valueIterator.size()) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        int i = this.index;
        this.index = i + 1;
        return this.$this_valueIterator.valueAt(i);
    }

    @Override // java.util.Iterator
    public final void remove() {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }
}
