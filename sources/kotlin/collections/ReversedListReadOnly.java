package kotlin.collections;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.ranges.IntRange;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ReversedListReadOnly extends AbstractList {
    public final List delegate;

    public ReversedListReadOnly(List list) {
        this.delegate = list;
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final Object get(int i) {
        if (new IntRange(0, OnDeviceLanguageIdentificationLogEvent.getLastIndex(this)).contains(i)) {
            return this.delegate.get(OnDeviceLanguageIdentificationLogEvent.getLastIndex(this) - i);
        }
        throw new IndexOutOfBoundsException("Element index " + i + " must be in range [" + new IntRange(0, OnDeviceLanguageIdentificationLogEvent.getLastIndex(this)) + "].");
    }

    @Override // kotlin.collections.AbstractCollection
    public final int getSize() {
        return this.delegate.size();
    }

    @Override // kotlin.collections.AbstractList, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return new ReversedListReadOnly$listIterator$1(this, 0);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return new ReversedListReadOnly$listIterator$1(this, 0);
    }

    @Override // kotlin.collections.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new ReversedListReadOnly$listIterator$1(this, i);
    }
}
