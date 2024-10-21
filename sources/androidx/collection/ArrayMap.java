package androidx.collection;

import com.google.mlkit.logging.schema.OnDeviceLanguageIdentificationLogEvent;
import j$.util.Map;
import java.lang.reflect.Array;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ArrayMap extends SimpleArrayMap implements Map, j$.util.Map {
    EntrySet mEntrySet;
    KeySet mKeySet;
    ValueCollection mValues;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class EntrySet extends AbstractSet {
        public EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator iterator() {
            return new MapIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return ArrayMap.this.size;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class KeyIterator extends IndexBasedArrayIterator {
        public KeyIterator() {
            super(ArrayMap.this.size);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected final Object elementAt(int i) {
            return ArrayMap.this.keyAt(i);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected final void removeAt(int i) {
            ArrayMap.this.removeAt(i);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class MapIterator implements Iterator, Map.Entry {
        int mEnd;
        boolean mEntryValid;
        int mIndex = -1;

        public MapIterator() {
            this.mEnd = ArrayMap.this.size - 1;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (this.mEntryValid) {
                if (!(obj instanceof Map.Entry)) {
                    return false;
                }
                Map.Entry entry = (Map.Entry) obj;
                if (!Intrinsics.areEqual(entry.getKey(), ArrayMap.this.keyAt(this.mIndex)) || !Intrinsics.areEqual(entry.getValue(), ArrayMap.this.valueAt(this.mIndex))) {
                    return false;
                }
                return true;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            if (this.mEntryValid) {
                return ArrayMap.this.keyAt(this.mIndex);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            if (this.mEntryValid) {
                return ArrayMap.this.valueAt(this.mIndex);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.mIndex < this.mEnd) {
                return true;
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            int hashCode;
            if (this.mEntryValid) {
                Object keyAt = ArrayMap.this.keyAt(this.mIndex);
                Object valueAt = ArrayMap.this.valueAt(this.mIndex);
                int i = 0;
                if (keyAt == null) {
                    hashCode = 0;
                } else {
                    hashCode = keyAt.hashCode();
                }
                if (valueAt != null) {
                    i = valueAt.hashCode();
                }
                return hashCode ^ i;
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        @Override // java.util.Iterator
        public final /* bridge */ /* synthetic */ Object next() {
            if (hasNext()) {
                this.mIndex++;
                this.mEntryValid = true;
                return this;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (this.mEntryValid) {
                ArrayMap.this.removeAt(this.mIndex);
                this.mIndex--;
                this.mEnd--;
                this.mEntryValid = false;
                return;
            }
            throw new IllegalStateException();
        }

        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            if (this.mEntryValid) {
                return ArrayMap.this.setValueAt(this.mIndex, obj);
            }
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        }

        public final String toString() {
            return getKey() + "=" + getValue();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ValueIterator extends IndexBasedArrayIterator {
        public ValueIterator() {
            super(ArrayMap.this.size);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected final Object elementAt(int i) {
            return ArrayMap.this.valueAt(i);
        }

        @Override // androidx.collection.IndexBasedArrayIterator
        protected final void removeAt(int i) {
            ArrayMap.this.removeAt(i);
        }
    }

    public ArrayMap() {
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ Object compute(Object obj, BiFunction biFunction) {
        return Map.CC.$default$compute(this, obj, biFunction);
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ Object computeIfAbsent(Object obj, Function function) {
        return Map.CC.$default$computeIfAbsent(this, obj, function);
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ Object computeIfPresent(Object obj, BiFunction biFunction) {
        return Map.CC.$default$computeIfPresent(this, obj, biFunction);
    }

    @Override // java.util.Map
    public final Set entrySet() {
        EntrySet entrySet = this.mEntrySet;
        if (entrySet == null) {
            EntrySet entrySet2 = new EntrySet();
            this.mEntrySet = entrySet2;
            return entrySet2;
        }
        return entrySet;
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ void forEach(BiConsumer biConsumer) {
        Map.CC.$default$forEach(this, biConsumer);
    }

    @Override // java.util.Map
    public final Set keySet() {
        KeySet keySet = this.mKeySet;
        if (keySet == null) {
            KeySet keySet2 = new KeySet();
            this.mKeySet = keySet2;
            return keySet2;
        }
        return keySet;
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ Object merge(Object obj, Object obj2, BiFunction biFunction) {
        return Map.CC.$default$merge(this, obj, obj2, biFunction);
    }

    @Override // java.util.Map
    public final void putAll(java.util.Map map) {
        ensureCapacity(this.size + map.size());
        for (Map.Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override // java.util.Map, j$.util.Map
    public final /* synthetic */ void replaceAll(BiFunction biFunction) {
        Map.CC.$default$replaceAll(this, biFunction);
    }

    public final boolean retainAll(Collection collection) {
        int i = this.size;
        for (int i2 = i - 1; i2 >= 0; i2--) {
            if (!collection.contains(keyAt(i2))) {
                removeAt(i2);
            }
        }
        if (i != this.size) {
            return true;
        }
        return false;
    }

    @Override // java.util.Map
    public final Collection values() {
        ValueCollection valueCollection = this.mValues;
        if (valueCollection == null) {
            ValueCollection valueCollection2 = new ValueCollection();
            this.mValues = valueCollection2;
            return valueCollection2;
        }
        return valueCollection;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class KeySet implements Set {
        public KeySet() {
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Set, java.util.Collection
        public final void clear() {
            ArrayMap.this.clear();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean contains(Object obj) {
            return ArrayMap.this.containsKey(obj);
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean containsAll(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!ArrayMap.this.containsKey(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Set) {
                Set set = (Set) obj;
                try {
                    if (size() == set.size()) {
                        if (containsAll(set)) {
                            return true;
                        }
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public final int hashCode() {
            int hashCode;
            int i = 0;
            for (int i2 = ArrayMap.this.size - 1; i2 >= 0; i2--) {
                Object keyAt = ArrayMap.this.keyAt(i2);
                if (keyAt == null) {
                    hashCode = 0;
                } else {
                    hashCode = keyAt.hashCode();
                }
                i += hashCode;
            }
            return i;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean isEmpty() {
            return ArrayMap.this.isEmpty();
        }

        @Override // java.util.Set, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            return new KeyIterator();
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean remove(Object obj) {
            int indexOfKey = ArrayMap.this.indexOfKey(obj);
            if (indexOfKey >= 0) {
                ArrayMap.this.removeAt(indexOfKey);
                return true;
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean removeAll(Collection collection) {
            ArrayMap arrayMap = ArrayMap.this;
            int i = arrayMap.size;
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                arrayMap.remove(it.next());
            }
            if (i != arrayMap.size) {
                return true;
            }
            return false;
        }

        @Override // java.util.Set, java.util.Collection
        public final boolean retainAll(Collection collection) {
            return ArrayMap.this.retainAll(collection);
        }

        @Override // java.util.Set, java.util.Collection
        public final int size() {
            return ArrayMap.this.size;
        }

        @Override // java.util.Set, java.util.Collection
        public final Object[] toArray() {
            int i = ArrayMap.this.size;
            Object[] objArr = new Object[i];
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = ArrayMap.this.keyAt(i2);
            }
            return objArr;
        }

        @Override // java.util.Set, java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            int size = size();
            if (objArr.length < size) {
                objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
            }
            for (int i = 0; i < size; i++) {
                objArr[i] = ArrayMap.this.keyAt(i);
            }
            if (objArr.length > size) {
                objArr[size] = null;
            }
            return objArr;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ValueCollection implements Collection {
        public ValueCollection() {
        }

        @Override // java.util.Collection
        public final boolean add(Object obj) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean addAll(Collection collection) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final void clear() {
            ArrayMap.this.clear();
        }

        @Override // java.util.Collection
        public final boolean contains(Object obj) {
            if (ArrayMap.this.__restricted$indexOfValue(obj) >= 0) {
                return true;
            }
            return false;
        }

        @Override // java.util.Collection
        public final boolean containsAll(Collection collection) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                if (!contains(it.next())) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return ArrayMap.this.isEmpty();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            return new ValueIterator();
        }

        @Override // java.util.Collection
        public final boolean remove(Object obj) {
            int __restricted$indexOfValue = ArrayMap.this.__restricted$indexOfValue(obj);
            if (__restricted$indexOfValue >= 0) {
                ArrayMap.this.removeAt(__restricted$indexOfValue);
                return true;
            }
            return false;
        }

        @Override // java.util.Collection
        public final boolean removeAll(Collection collection) {
            int i = ArrayMap.this.size;
            int i2 = 0;
            boolean z = false;
            while (i2 < i) {
                if (collection.contains(ArrayMap.this.valueAt(i2))) {
                    ArrayMap.this.removeAt(i2);
                    i--;
                    i2--;
                    z = true;
                }
                i2++;
            }
            return z;
        }

        @Override // java.util.Collection
        public final boolean retainAll(Collection collection) {
            int i = ArrayMap.this.size;
            int i2 = 0;
            boolean z = false;
            while (i2 < i) {
                if (!collection.contains(ArrayMap.this.valueAt(i2))) {
                    ArrayMap.this.removeAt(i2);
                    i--;
                    i2--;
                    z = true;
                }
                i2++;
            }
            return z;
        }

        @Override // java.util.Collection
        public final int size() {
            return ArrayMap.this.size;
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            int i = ArrayMap.this.size;
            Object[] objArr = new Object[i];
            for (int i2 = 0; i2 < i; i2++) {
                objArr[i2] = ArrayMap.this.valueAt(i2);
            }
            return objArr;
        }

        @Override // java.util.Collection
        public final Object[] toArray(Object[] objArr) {
            int size = size();
            if (objArr.length < size) {
                objArr = (Object[]) Array.newInstance(objArr.getClass().getComponentType(), size);
            }
            for (int i = 0; i < size; i++) {
                objArr[i] = ArrayMap.this.valueAt(i);
            }
            if (objArr.length > size) {
                objArr[size] = null;
            }
            return objArr;
        }
    }

    public ArrayMap(int i) {
        super(i);
    }

    public ArrayMap(SimpleArrayMap simpleArrayMap) {
        super((byte[]) null);
        int i = simpleArrayMap.size;
        ensureCapacity(this.size + i);
        if (this.size != 0) {
            for (int i2 = 0; i2 < i; i2++) {
                put(simpleArrayMap.keyAt(i2), simpleArrayMap.valueAt(i2));
            }
        } else if (i > 0) {
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$edac78be_0(simpleArrayMap.hashes, this.hashes, 0, 0, i);
            OnDeviceLanguageIdentificationLogEvent.IdentifyPossibleLanguagesResult.copyInto$ar$ds$e21159aa_0(simpleArrayMap.array, this.array, 0, 0, i + i);
            this.size = i;
        }
    }
}
