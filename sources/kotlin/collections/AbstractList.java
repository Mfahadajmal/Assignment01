package kotlin.collections;

import _COROUTINE._BOUNDARY;
import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import j$.util.List;
import j$.util.Spliterator;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import java.util.function.UnaryOperator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class AbstractList extends AbstractCollection implements List, KMappedMarker, j$.util.List {
    public static final OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult Companion$ar$class_merging$4320ca7e_0$ar$class_merging = new OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class IteratorImpl implements Iterator, KMappedMarker {
        public int index;

        public IteratorImpl() {
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.index < AbstractList.this.getSize()) {
                return true;
            }
            return false;
        }

        @Override // java.util.Iterator
        public final Object next() {
            if (hasNext()) {
                AbstractList abstractList = AbstractList.this;
                int i = this.index;
                this.index = i + 1;
                return abstractList.get(i);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_6();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ListIteratorImpl extends IteratorImpl implements ListIterator, KMappedMarker {
        public ListIteratorImpl(int i) {
            super();
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkPositionIndex$kotlin_stdlib$ar$ds(i, AbstractList.this.getSize());
            this.index = i;
        }

        @Override // java.util.ListIterator
        public final void add(Object obj) {
            _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_7();
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            if (this.index > 0) {
                return true;
            }
            return false;
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public final Object previous() {
            if (hasPrevious()) {
                AbstractList abstractList = AbstractList.this;
                int i = this.index - 1;
                this.index = i;
                return abstractList.get(i);
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator
        public final void set(Object obj) {
            _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_7();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SubList extends AbstractList implements RandomAccess {
        private final int _size;
        private final int fromIndex;
        private final AbstractList list;

        public SubList(AbstractList abstractList, int i, int i2) {
            this.list = abstractList;
            this.fromIndex = i;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkRangeIndexes$kotlin_stdlib$ar$ds(i, i2, abstractList.getSize());
            this._size = i2 - i;
        }

        @Override // kotlin.collections.AbstractList, java.util.List
        public final Object get(int i) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkElementIndex$kotlin_stdlib$ar$ds(i, this._size);
            return this.list.get(this.fromIndex + i);
        }

        @Override // kotlin.collections.AbstractCollection
        public final int getSize() {
            return this._size;
        }
    }

    @Override // java.util.List
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        Collection collection = (Collection) obj;
        collection.getClass();
        if (size() == collection.size()) {
            Iterator it = collection.iterator();
            Iterator<E> it2 = iterator();
            while (it2.hasNext()) {
                if (!Intrinsics.areEqual(it2.next(), it.next())) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.List
    public abstract Object get(int i);

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int i;
        int i2 = 1;
        for (Object obj : this) {
            int i3 = i2 * 31;
            if (obj != null) {
                i = obj.hashCode();
            } else {
                i = 0;
            }
            i2 = i3 + i;
        }
        return i2;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            if (!Intrinsics.areEqual(it.next(), obj)) {
                i++;
            } else {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.Collection, java.lang.Iterable, java.util.List
    public Iterator iterator() {
        return new IteratorImpl();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (Intrinsics.areEqual(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }

    @Override // java.util.List
    public ListIterator listIterator() {
        return new ListIteratorImpl(0);
    }

    @Override // java.util.List
    public final Object remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, j$.util.List
    public final void replaceAll(UnaryOperator unaryOperator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // java.util.List, j$.util.List
    public final void sort(Comparator comparator) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // kotlin.collections.AbstractCollection, java.util.Collection, java.lang.Iterable, j$.util.Collection
    public final /* synthetic */ Spliterator spliterator() {
        return List.CC.$default$spliterator(this);
    }

    @Override // java.util.List
    public final java.util.List subList(int i, int i2) {
        return new SubList(this, i, i2);
    }

    @Override // java.util.List
    public ListIterator listIterator(int i) {
        return new ListIteratorImpl(i);
    }
}
