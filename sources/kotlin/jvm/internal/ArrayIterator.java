package kotlin.jvm.internal;

import _COROUTINE._BOUNDARY;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayIterator implements Iterator, KMappedMarker {
    private final Object[] array;
    private int index;

    public ArrayIterator(Object[] objArr) {
        this.array = objArr;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.index < this.array.length) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        try {
            Object[] objArr = this.array;
            int i = this.index;
            this.index = i + 1;
            return objArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.index--;
            throw new NoSuchElementException(e.getMessage());
        }
    }

    @Override // java.util.Iterator
    public final void remove() {
        _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
    }
}
