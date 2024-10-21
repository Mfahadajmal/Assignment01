package kotlin.collections.builders;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import com.google.mlkit.logging.schema.OnDeviceObjectCreateLogEvent;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.collections.AbstractMutableList;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.jvm.internal.markers.KMutableCollection;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ListBuilder extends AbstractMutableList implements List, RandomAccess, Serializable, KMutableCollection {
    public static final ListBuilder Empty;
    public Object[] array;
    public final ListBuilder backing;
    public boolean isReadOnly;
    public int length;
    public int offset;
    private final ListBuilder root;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Itr implements ListIterator, KMappedMarker {
        private int expectedModCount;
        private int index;
        private int lastIndex = -1;
        private final ListBuilder list;

        public Itr(ListBuilder listBuilder, int i) {
            this.list = listBuilder;
            this.index = i;
            this.expectedModCount = listBuilder.modCount;
        }

        private final void checkForComodification() {
            if (this.list.modCount == this.expectedModCount) {
            } else {
                throw new ConcurrentModificationException();
            }
        }

        @Override // java.util.ListIterator
        public final void add(Object obj) {
            checkForComodification();
            int i = this.index;
            this.index = i + 1;
            this.list.add(i, obj);
            this.lastIndex = -1;
            this.expectedModCount = this.list.modCount;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final boolean hasNext() {
            if (this.index < this.list.length) {
                return true;
            }
            return false;
        }

        @Override // java.util.ListIterator
        public final boolean hasPrevious() {
            if (this.index > 0) {
                return true;
            }
            return false;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final Object next() {
            checkForComodification();
            int i = this.index;
            ListBuilder listBuilder = this.list;
            if (i < listBuilder.length) {
                this.index = i + 1;
                this.lastIndex = i;
                return listBuilder.array[listBuilder.offset + i];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public final int nextIndex() {
            return this.index;
        }

        @Override // java.util.ListIterator
        public final Object previous() {
            checkForComodification();
            int i = this.index;
            if (i > 0) {
                int i2 = i - 1;
                this.index = i2;
                this.lastIndex = i2;
                ListBuilder listBuilder = this.list;
                return listBuilder.array[listBuilder.offset + i2];
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public final int previousIndex() {
            return this.index - 1;
        }

        @Override // java.util.ListIterator, java.util.Iterator
        public final void remove() {
            checkForComodification();
            int i = this.lastIndex;
            if (i != -1) {
                this.list.removeAt(i);
                this.index = this.lastIndex;
                this.lastIndex = -1;
                this.expectedModCount = this.list.modCount;
                return;
            }
            throw new IllegalStateException("Call next() or previous() before removing element from the iterator.");
        }

        @Override // java.util.ListIterator
        public final void set(Object obj) {
            checkForComodification();
            int i = this.lastIndex;
            if (i != -1) {
                this.list.set(i, obj);
                return;
            }
            throw new IllegalStateException("Call next() or previous() before replacing element from the iterator.");
        }
    }

    static {
        ListBuilder listBuilder = new ListBuilder(0);
        listBuilder.isReadOnly = true;
        Empty = listBuilder;
    }

    public ListBuilder() {
        this(10);
    }

    private final void addAllInternal(int i, Collection collection, int i2) {
        registerModification();
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.addAllInternal(i, collection, i2);
            this.array = this.backing.array;
            this.length += i2;
        } else {
            insertAtInternal(i, i2);
            Iterator it = collection.iterator();
            for (int i3 = 0; i3 < i2; i3++) {
                this.array[i + i3] = it.next();
            }
        }
    }

    private final void addAtInternal(int i, Object obj) {
        registerModification();
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.addAtInternal(i, obj);
            this.array = this.backing.array;
            this.length++;
        } else {
            insertAtInternal(i, 1);
            this.array[i] = obj;
        }
    }

    private final void checkForComodification() {
        ListBuilder listBuilder = this.root;
        if (listBuilder != null && listBuilder.modCount != this.modCount) {
            throw new ConcurrentModificationException();
        }
    }

    private final void insertAtInternal(int i, int i2) {
        int i3 = this.length + i2;
        if (i3 >= 0) {
            Object[] objArr = this.array;
            int length = objArr.length;
            if (i3 > length) {
                this.array = OnDeviceObjectCreateLogEvent.copyOfUninitializedElements(objArr, OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.newCapacity$kotlin_stdlib$ar$ds(length, i3));
            }
            Object[] objArr2 = this.array;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr2, objArr2, i + i2, i, this.offset + this.length);
            this.length += i2;
            return;
        }
        throw new OutOfMemoryError();
    }

    private final boolean isEffectivelyReadOnly() {
        if (this.isReadOnly) {
            return true;
        }
        ListBuilder listBuilder = this.root;
        if (listBuilder != null && listBuilder.isReadOnly) {
            return true;
        }
        return false;
    }

    private final void registerModification() {
        this.modCount++;
    }

    private final Object removeAtInternal(int i) {
        registerModification();
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            this.length--;
            return listBuilder.removeAtInternal(i);
        }
        Object[] objArr = this.array;
        Object obj = objArr[i];
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr, objArr, i, i + 1, this.offset + this.length);
        OnDeviceObjectCreateLogEvent.resetAt(this.array, (this.offset + this.length) - 1);
        this.length--;
        return obj;
    }

    private final void removeRangeInternal(int i, int i2) {
        if (i2 > 0) {
            registerModification();
        }
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            listBuilder.removeRangeInternal(i, i2);
        } else {
            Object[] objArr = this.array;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr, objArr, i, i + i2, this.length);
            Object[] objArr2 = this.array;
            int i3 = this.length;
            OnDeviceObjectCreateLogEvent.resetRange(objArr2, i3 - i2, i3);
        }
        this.length -= i2;
    }

    private final int retainOrRemoveAllInternal(int i, int i2, Collection collection, boolean z) {
        int i3;
        ListBuilder listBuilder = this.backing;
        if (listBuilder != null) {
            i3 = listBuilder.retainOrRemoveAllInternal(i, i2, collection, z);
        } else {
            int i4 = 0;
            int i5 = 0;
            while (i4 < i2) {
                int i6 = i4 + 1;
                int i7 = i4 + i;
                if (collection.contains(this.array[i7]) == z) {
                    Object[] objArr = this.array;
                    objArr[i + i5] = objArr[i7];
                    i5++;
                }
                i4 = i6;
            }
            int i8 = i2 - i5;
            Object[] objArr2 = this.array;
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr2, objArr2, i + i5, i + i2, this.length);
            Object[] objArr3 = this.array;
            int i9 = this.length;
            OnDeviceObjectCreateLogEvent.resetRange(objArr3, i9 - i8, i9);
            i3 = i8;
        }
        if (i3 > 0) {
            registerModification();
        }
        this.length -= i3;
        return i3;
    }

    private final Object writeReplace() {
        if (isEffectivelyReadOnly()) {
            return new SerializedCollection(this, 0);
        }
        throw new NotSerializableException("The list cannot be serialized while it is being built.");
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        checkIsMutable();
        checkForComodification();
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkPositionIndex$kotlin_stdlib$ar$ds(i, this.length);
        addAtInternal(this.offset + i, obj);
    }

    @Override // java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        collection.getClass();
        checkIsMutable();
        checkForComodification();
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkPositionIndex$kotlin_stdlib$ar$ds(i, this.length);
        int size = collection.size();
        addAllInternal(this.offset + i, collection, size);
        return size > 0;
    }

    public final void checkIsMutable() {
        if (!isEffectivelyReadOnly()) {
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        checkIsMutable();
        checkForComodification();
        removeRangeInternal(this.offset, this.length);
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        checkForComodification();
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        List list = (List) obj;
        Object[] objArr = this.array;
        int i = this.offset;
        int i2 = this.length;
        if (i2 == list.size()) {
            for (int i3 = 0; i3 < i2; i3++) {
                if (Intrinsics.areEqual(objArr[i + i3], list.get(i3))) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        checkForComodification();
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkElementIndex$kotlin_stdlib$ar$ds(i, this.length);
        return this.array[this.offset + i];
    }

    @Override // kotlin.collections.AbstractMutableList
    public final int getSize() {
        checkForComodification();
        return this.length;
    }

    @Override // java.util.AbstractList, java.util.Collection, java.util.List
    public final int hashCode() {
        int i;
        checkForComodification();
        Object[] objArr = this.array;
        int i2 = this.offset;
        int i3 = this.length;
        int i4 = 1;
        for (int i5 = 0; i5 < i3; i5++) {
            Object obj = objArr[i2 + i5];
            int i6 = i4 * 31;
            if (obj != null) {
                i = obj.hashCode();
            } else {
                i = 0;
            }
            i4 = i6 + i;
        }
        return i4;
    }

    @Override // java.util.AbstractList, java.util.List
    public final int indexOf(Object obj) {
        checkForComodification();
        for (int i = 0; i < this.length; i++) {
            if (Intrinsics.areEqual(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        checkForComodification();
        if (this.length == 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractList, java.util.List
    public final int lastIndexOf(Object obj) {
        checkForComodification();
        for (int i = this.length - 1; i >= 0; i--) {
            if (Intrinsics.areEqual(this.array[this.offset + i], obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean remove(Object obj) {
        checkIsMutable();
        checkForComodification();
        int indexOf = indexOf(obj);
        if (indexOf >= 0) {
            removeAt(indexOf);
        }
        if (indexOf >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean removeAll(Collection collection) {
        collection.getClass();
        checkIsMutable();
        checkForComodification();
        if (retainOrRemoveAllInternal(this.offset, this.length, collection, false) <= 0) {
            return false;
        }
        return true;
    }

    @Override // kotlin.collections.AbstractMutableList
    public final Object removeAt(int i) {
        checkIsMutable();
        checkForComodification();
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkElementIndex$kotlin_stdlib$ar$ds(i, this.length);
        return removeAtInternal(this.offset + i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean retainAll(Collection collection) {
        collection.getClass();
        checkIsMutable();
        checkForComodification();
        if (retainOrRemoveAllInternal(this.offset, this.length, collection, true) > 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        checkIsMutable();
        checkForComodification();
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkElementIndex$kotlin_stdlib$ar$ds(i, this.length);
        Object[] objArr = this.array;
        int i2 = this.offset + i;
        Object obj2 = objArr[i2];
        objArr[i2] = obj;
        return obj2;
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i, int i2) {
        ListBuilder listBuilder;
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkRangeIndexes$kotlin_stdlib$ar$ds(i, i2, this.length);
        Object[] objArr = this.array;
        int i3 = this.offset + i;
        ListBuilder listBuilder2 = this.root;
        boolean z = this.isReadOnly;
        if (listBuilder2 == null) {
            listBuilder = this;
        } else {
            listBuilder = listBuilder2;
        }
        return new ListBuilder(objArr, i3, i2 - i, z, this, listBuilder);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray() {
        checkForComodification();
        Object[] objArr = this.array;
        int i = this.offset;
        return OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyOfRange(objArr, i, this.length + i);
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        checkForComodification();
        Object[] objArr = this.array;
        int i = this.offset;
        int i2 = this.length;
        StringBuilder sb = new StringBuilder((i2 * 3) + 2);
        sb.append("[");
        for (int i3 = 0; i3 < i2; i3++) {
            if (i3 > 0) {
                sb.append(", ");
            }
            Object obj = objArr[i + i3];
            if (obj == this) {
                sb.append("(this Collection)");
            } else {
                sb.append(obj);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public ListBuilder(int i) {
        this(new Object[i], 0, 0, false, null, null);
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        checkForComodification();
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.checkPositionIndex$kotlin_stdlib$ar$ds(i, this.length);
        return new Itr(this, i);
    }

    private ListBuilder(Object[] objArr, int i, int i2, boolean z, ListBuilder listBuilder, ListBuilder listBuilder2) {
        this.array = objArr;
        this.offset = i;
        this.length = i2;
        this.isReadOnly = z;
        this.backing = listBuilder;
        this.root = listBuilder2;
        if (listBuilder != null) {
            this.modCount = listBuilder.modCount;
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final Object[] toArray(Object[] objArr) {
        objArr.getClass();
        checkForComodification();
        int length = objArr.length;
        int i = this.length;
        if (length < i) {
            Object[] objArr2 = this.array;
            int i2 = this.offset;
            Object[] copyOfRange = Arrays.copyOfRange(objArr2, i2, i + i2, objArr.getClass());
            copyOfRange.getClass();
            return copyOfRange;
        }
        Object[] objArr3 = this.array;
        int i3 = this.offset;
        OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(objArr3, objArr, 0, i3, i + i3);
        OnDeviceLanguageIdentificationLogEvent.terminateCollectionToArray$ar$ds(this.length, objArr);
        return objArr;
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        checkIsMutable();
        checkForComodification();
        addAtInternal(this.offset + this.length, obj);
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        collection.getClass();
        checkIsMutable();
        checkForComodification();
        int size = collection.size();
        addAllInternal(this.offset + this.length, collection, size);
        return size > 0;
    }
}
