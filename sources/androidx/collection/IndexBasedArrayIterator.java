package androidx.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class IndexBasedArrayIterator implements Iterator, KMappedMarker {
    private boolean canRemove;
    private int index;
    private int size;

    public IndexBasedArrayIterator(int i) {
        this.size = i;
    }

    protected abstract Object elementAt(int i);

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (this.index < this.size) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            Object elementAt = elementAt(this.index);
            this.index++;
            this.canRemove = true;
            return elementAt;
        }
        throw new NoSuchElementException();
    }

    @Override // java.util.Iterator
    public final void remove() {
        if (this.canRemove) {
            int i = this.index - 1;
            this.index = i;
            removeAt(i);
            this.size--;
            this.canRemove = false;
            return;
        }
        throw new IllegalStateException("Call next() before removing an element.");
    }

    protected abstract void removeAt(int i);
}
