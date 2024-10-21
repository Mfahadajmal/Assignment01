package com.google.common.collect;

import _COROUTINE._BOUNDARY;
import com.google.common.flogger.context.ContextDataProvider;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class CompactHashMap extends AbstractMap implements Serializable {
    public static final Object NOT_FOUND = new Object();
    transient int[] entries;
    private transient Set entrySetView;
    private transient Set keySetView;
    transient Object[] keys;
    public transient int metadata;
    public transient int size;
    private transient Object table;
    transient Object[] values;
    private transient Collection valuesView;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class EntrySetView extends AbstractSet {
        public EntrySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.entrySet().contains(obj);
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                int indexOf = CompactHashMap.this.indexOf(entry.getKey());
                if (indexOf != -1 && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(CompactHashMap.this.value(indexOf), entry.getValue())) {
                    return true;
                }
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator iterator() {
            return CompactHashMap.this.entrySetIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.entrySet().remove(obj);
            }
            if (obj instanceof Map.Entry) {
                Map.Entry entry = (Map.Entry) obj;
                CompactHashMap compactHashMap = CompactHashMap.this;
                if (!compactHashMap.needsAllocArrays()) {
                    int hashTableMask = compactHashMap.hashTableMask();
                    Object key = entry.getKey();
                    Object value = entry.getValue();
                    CompactHashMap compactHashMap2 = CompactHashMap.this;
                    int remove = ContextDataProvider.remove(key, value, hashTableMask, compactHashMap2.requireTable(), compactHashMap2.requireEntries(), compactHashMap2.requireKeys(), compactHashMap2.requireValues());
                    if (remove != -1) {
                        CompactHashMap.this.moveLastEntry(remove, hashTableMask);
                        r10.size--;
                        CompactHashMap.this.incrementModCount();
                        return true;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return CompactHashMap.this.size();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class Itr implements Iterator {
        int currentIndex;
        int expectedMetadata;
        int indexToRemove = -1;

        public Itr() {
            this.expectedMetadata = CompactHashMap.this.metadata;
            this.currentIndex = CompactHashMap.this.firstEntryIndex();
        }

        private final void checkForConcurrentModification() {
            if (CompactHashMap.this.metadata == this.expectedMetadata) {
            } else {
                throw new ConcurrentModificationException();
            }
        }

        public abstract Object getOutput(int i);

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.currentIndex >= 0) {
                return true;
            }
            return false;
        }

        @Override // java.util.Iterator
        public final Object next() {
            checkForConcurrentModification();
            if (hasNext()) {
                int i = this.currentIndex;
                this.indexToRemove = i;
                Object output = getOutput(i);
                this.currentIndex = CompactHashMap.this.getSuccessor(this.currentIndex);
                return output;
            }
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public final void remove() {
            boolean z;
            checkForConcurrentModification();
            if (this.indexToRemove >= 0) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkRemove(z);
            this.expectedMetadata += 32;
            int i = this.indexToRemove;
            CompactHashMap compactHashMap = CompactHashMap.this;
            compactHashMap.remove(compactHashMap.key(i));
            this.currentIndex--;
            this.indexToRemove = -1;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class KeySetView extends AbstractSet {
        public KeySetView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            return CompactHashMap.this.containsKey(obj);
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator iterator() {
            final CompactHashMap compactHashMap = CompactHashMap.this;
            Map delegateOrNull = compactHashMap.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.keySet().iterator();
            }
            return new Itr() { // from class: com.google.common.collect.CompactHashMap.1
                @Override // com.google.common.collect.CompactHashMap.Itr
                public final Object getOutput(int i) {
                    return CompactHashMap.this.key(i);
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.keySet().remove(obj);
            }
            if (CompactHashMap.this.removeHelper(obj) == CompactHashMap.NOT_FOUND) {
                return false;
            }
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return CompactHashMap.this.size();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class MapEntry extends AbstractMapEntry {
        private final Object key;
        private int lastKnownIndex;

        public MapEntry(int i) {
            this.key = CompactHashMap.this.key(i);
            this.lastKnownIndex = i;
        }

        private final void updateLastKnownIndex() {
            int i = this.lastKnownIndex;
            if (i != -1 && i < CompactHashMap.this.size() && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(this.key, CompactHashMap.this.key(this.lastKnownIndex))) {
                return;
            }
            this.lastKnownIndex = CompactHashMap.this.indexOf(this.key);
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public final Object getKey() {
            return this.key;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public final Object getValue() {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.get(this.key);
            }
            updateLastKnownIndex();
            int i = this.lastKnownIndex;
            if (i == -1) {
                return null;
            }
            return CompactHashMap.this.value(i);
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public final Object setValue(Object obj) {
            Map delegateOrNull = CompactHashMap.this.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.put(this.key, obj);
            }
            updateLastKnownIndex();
            int i = this.lastKnownIndex;
            if (i == -1) {
                CompactHashMap.this.put(this.key, obj);
                return null;
            }
            CompactHashMap compactHashMap = CompactHashMap.this;
            Object value = compactHashMap.value(i);
            compactHashMap.setValue(this.lastKnownIndex, obj);
            return value;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ValuesView extends AbstractCollection {
        public ValuesView() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final void clear() {
            CompactHashMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public final Iterator iterator() {
            final CompactHashMap compactHashMap = CompactHashMap.this;
            Map delegateOrNull = compactHashMap.delegateOrNull();
            if (delegateOrNull != null) {
                return delegateOrNull.values().iterator();
            }
            return new Itr() { // from class: com.google.common.collect.CompactHashMap.3
                @Override // com.google.common.collect.CompactHashMap.Itr
                public final Object getOutput(int i) {
                    return CompactHashMap.this.value(i);
                }
            };
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public final int size() {
            return CompactHashMap.this.size();
        }
    }

    public CompactHashMap() {
        init(3);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        objectInputStream.defaultReadObject();
        int readInt = objectInputStream.readInt();
        if (readInt >= 0) {
            init(readInt);
            for (int i = 0; i < readInt; i++) {
                put(objectInputStream.readObject(), objectInputStream.readObject());
            }
            return;
        }
        throw new InvalidObjectException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(readInt, "Invalid size: "));
    }

    private final int resizeTable(int i, int i2, int i3, int i4) {
        int i5 = i2 - 1;
        Object createTable = ContextDataProvider.createTable(i2);
        if (i4 != 0) {
            ContextDataProvider.tableSet(createTable, i3 & i5, i4 + 1);
        }
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        for (int i6 = 0; i6 <= i; i6++) {
            int tableGet = ContextDataProvider.tableGet(requireTable, i6);
            while (tableGet != 0) {
                int i7 = tableGet - 1;
                int i8 = requireEntries[i7];
                int hashPrefix = ContextDataProvider.getHashPrefix(i8, i) | i6;
                int i9 = hashPrefix & i5;
                int tableGet2 = ContextDataProvider.tableGet(createTable, i9);
                ContextDataProvider.tableSet(createTable, i9, tableGet);
                requireEntries[i7] = ContextDataProvider.maskCombine(hashPrefix, tableGet2, i5);
                tableGet = i8 & i;
            }
        }
        this.table = createTable;
        setHashTableMask(i5);
        return i5;
    }

    private final void setHashTableMask(int i) {
        this.metadata = ContextDataProvider.maskCombine(this.metadata, 32 - Integer.numberOfLeadingZeros(i), 31);
    }

    private void writeObject(ObjectOutputStream objectOutputStream) {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeInt(size());
        Iterator entrySetIterator = entrySetIterator();
        while (entrySetIterator.hasNext()) {
            Map.Entry entry = (Map.Entry) entrySetIterator.next();
            objectOutputStream.writeObject(entry.getKey());
            objectOutputStream.writeObject(entry.getValue());
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        if (needsAllocArrays()) {
            return;
        }
        incrementModCount();
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            this.metadata = ContextDataProvider.constrainToRange(size(), 3, 1073741823);
            delegateOrNull.clear();
            this.table = null;
            this.size = 0;
            return;
        }
        Arrays.fill(requireKeys(), 0, this.size, (Object) null);
        Arrays.fill(requireValues(), 0, this.size, (Object) null);
        Object requireTable = requireTable();
        if (requireTable instanceof byte[]) {
            Arrays.fill((byte[]) requireTable, (byte) 0);
        } else if (requireTable instanceof short[]) {
            Arrays.fill((short[]) requireTable, (short) 0);
        } else {
            Arrays.fill((int[]) requireTable, 0);
        }
        Arrays.fill(requireEntries(), 0, this.size, 0);
        this.size = 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.containsKey(obj);
        }
        if (indexOf(obj) == -1) {
            return false;
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsValue(Object obj) {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull == null) {
            for (int i = 0; i < this.size; i++) {
                if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(obj, value(i))) {
                    return true;
                }
            }
            return false;
        }
        return delegateOrNull.containsValue(obj);
    }

    final Map delegateOrNull() {
        Object obj = this.table;
        if (obj instanceof Map) {
            return (Map) obj;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        Set set = this.entrySetView;
        if (set == null) {
            EntrySetView entrySetView = new EntrySetView();
            this.entrySetView = entrySetView;
            return entrySetView;
        }
        return set;
    }

    final Iterator entrySetIterator() {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.entrySet().iterator();
        }
        return new Itr() { // from class: com.google.common.collect.CompactHashMap.2
            @Override // com.google.common.collect.CompactHashMap.Itr
            public final /* bridge */ /* synthetic */ Object getOutput(int i) {
                return new MapEntry(i);
            }
        };
    }

    final int firstEntryIndex() {
        if (isEmpty()) {
            return -1;
        }
        return 0;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.get(obj);
        }
        int indexOf = indexOf(obj);
        if (indexOf == -1) {
            return null;
        }
        return value(indexOf);
    }

    final int getSuccessor(int i) {
        int i2 = i + 1;
        if (i2 < this.size) {
            return i2;
        }
        return -1;
    }

    public final int hashTableMask() {
        return (1 << (this.metadata & 31)) - 1;
    }

    final void incrementModCount() {
        this.metadata += 32;
    }

    public final int indexOf(Object obj) {
        if (needsAllocArrays()) {
            return -1;
        }
        int smearedHash = ContextDataProvider.smearedHash(obj);
        int hashTableMask = hashTableMask();
        int tableGet = ContextDataProvider.tableGet(requireTable(), smearedHash & hashTableMask);
        if (tableGet == 0) {
            return -1;
        }
        int hashPrefix = ContextDataProvider.getHashPrefix(smearedHash, hashTableMask);
        do {
            int i = tableGet - 1;
            int i2 = requireEntries()[i];
            if (ContextDataProvider.getHashPrefix(i2, hashTableMask) == hashPrefix && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(obj, key(i))) {
                return i;
            }
            tableGet = i2 & hashTableMask;
        } while (tableGet != 0);
        return -1;
    }

    final void init(int i) {
        ContextDataProvider.checkArgument(true, (Object) "Expected size must be >= 0");
        this.metadata = ContextDataProvider.constrainToRange(i, 1, 1073741823);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public final Object key(int i) {
        return requireKeys()[i];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set keySet() {
        Set set = this.keySetView;
        if (set == null) {
            KeySetView keySetView = new KeySetView();
            this.keySetView = keySetView;
            return keySetView;
        }
        return set;
    }

    final void moveLastEntry(int i, int i2) {
        Object requireTable = requireTable();
        int[] requireEntries = requireEntries();
        Object[] requireKeys = requireKeys();
        Object[] requireValues = requireValues();
        int size = size();
        int i3 = size - 1;
        if (i < i3) {
            int i4 = i + 1;
            Object obj = requireKeys[i3];
            requireKeys[i] = obj;
            requireValues[i] = requireValues[i3];
            requireKeys[i3] = null;
            requireValues[i3] = null;
            requireEntries[i] = requireEntries[i3];
            requireEntries[i3] = 0;
            int smearedHash = ContextDataProvider.smearedHash(obj) & i2;
            int tableGet = ContextDataProvider.tableGet(requireTable, smearedHash);
            if (tableGet == size) {
                ContextDataProvider.tableSet(requireTable, smearedHash, i4);
                return;
            }
            while (true) {
                int i5 = tableGet - 1;
                int i6 = requireEntries[i5];
                int i7 = i6 & i2;
                if (i7 != size) {
                    tableGet = i7;
                } else {
                    requireEntries[i5] = ContextDataProvider.maskCombine(i6, i4, i2);
                    return;
                }
            }
        } else {
            requireKeys[i] = null;
            requireValues[i] = null;
            requireEntries[i] = 0;
        }
    }

    final boolean needsAllocArrays() {
        if (this.table == null) {
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Object obj, Object obj2) {
        int min;
        if (needsAllocArrays()) {
            ContextDataProvider.checkState(needsAllocArrays(), "Arrays already allocated");
            int i = this.metadata;
            int max = Math.max(i + 1, 2);
            int highestOneBit = Integer.highestOneBit(max);
            if (max > highestOneBit && (highestOneBit = highestOneBit + highestOneBit) <= 0) {
                highestOneBit = 1073741824;
            }
            int max2 = Math.max(4, highestOneBit);
            this.table = ContextDataProvider.createTable(max2);
            setHashTableMask(max2 - 1);
            this.entries = new int[i];
            this.keys = new Object[i];
            this.values = new Object[i];
        }
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.put(obj, obj2);
        }
        int[] requireEntries = requireEntries();
        Object[] requireKeys = requireKeys();
        Object[] requireValues = requireValues();
        int i2 = this.size;
        int i3 = i2 + 1;
        int smearedHash = ContextDataProvider.smearedHash(obj);
        int hashTableMask = hashTableMask();
        int i4 = smearedHash & hashTableMask;
        int tableGet = ContextDataProvider.tableGet(requireTable(), i4);
        if (tableGet == 0) {
            if (i3 > hashTableMask) {
                hashTableMask = resizeTable(hashTableMask, ContextDataProvider.newCapacity(hashTableMask), smearedHash, i2);
            } else {
                ContextDataProvider.tableSet(requireTable(), i4, i3);
            }
        } else {
            int hashPrefix = ContextDataProvider.getHashPrefix(smearedHash, hashTableMask);
            int i5 = 0;
            while (true) {
                int i6 = tableGet - 1;
                int i7 = requireEntries[i6];
                if (ContextDataProvider.getHashPrefix(i7, hashTableMask) == hashPrefix && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(obj, requireKeys[i6])) {
                    Object obj3 = requireValues[i6];
                    requireValues[i6] = obj2;
                    return obj3;
                }
                int i8 = i7 & hashTableMask;
                i5++;
                if (i8 == 0) {
                    if (i5 >= 9) {
                        LinkedHashMap linkedHashMap = new LinkedHashMap(hashTableMask() + 1, 1.0f);
                        int firstEntryIndex = firstEntryIndex();
                        while (firstEntryIndex >= 0) {
                            linkedHashMap.put(key(firstEntryIndex), value(firstEntryIndex));
                            firstEntryIndex = getSuccessor(firstEntryIndex);
                        }
                        this.table = linkedHashMap;
                        this.entries = null;
                        this.keys = null;
                        this.values = null;
                        incrementModCount();
                        return linkedHashMap.put(obj, obj2);
                    }
                    if (i3 > hashTableMask) {
                        hashTableMask = resizeTable(hashTableMask, ContextDataProvider.newCapacity(hashTableMask), smearedHash, i2);
                    } else {
                        requireEntries[i6] = ContextDataProvider.maskCombine(i7, i3, hashTableMask);
                    }
                } else {
                    tableGet = i8;
                }
            }
        }
        int length = requireEntries().length;
        if (i3 > length && (min = Math.min(1073741823, (Math.max(1, length >>> 1) + length) | 1)) != length) {
            this.entries = Arrays.copyOf(requireEntries(), min);
            this.keys = Arrays.copyOf(requireKeys(), min);
            this.values = Arrays.copyOf(requireValues(), min);
        }
        requireEntries()[i2] = ContextDataProvider.maskCombine(smearedHash, 0, hashTableMask);
        requireKeys()[i2] = obj;
        setValue(i2, obj2);
        this.size = i3;
        incrementModCount();
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.remove(obj);
        }
        Object removeHelper = removeHelper(obj);
        if (removeHelper == NOT_FOUND) {
            return null;
        }
        return removeHelper;
    }

    public final Object removeHelper(Object obj) {
        if (!needsAllocArrays()) {
            int hashTableMask = hashTableMask();
            int remove = ContextDataProvider.remove(obj, null, hashTableMask, requireTable(), requireEntries(), requireKeys(), null);
            if (remove != -1) {
                Object value = value(remove);
                moveLastEntry(remove, hashTableMask);
                this.size--;
                incrementModCount();
                return value;
            }
        }
        return NOT_FOUND;
    }

    public final int[] requireEntries() {
        int[] iArr = this.entries;
        iArr.getClass();
        return iArr;
    }

    public final Object[] requireKeys() {
        Object[] objArr = this.keys;
        objArr.getClass();
        return objArr;
    }

    public final Object requireTable() {
        Object obj = this.table;
        obj.getClass();
        return obj;
    }

    public final Object[] requireValues() {
        Object[] objArr = this.values;
        objArr.getClass();
        return objArr;
    }

    public final void setValue(int i, Object obj) {
        requireValues()[i] = obj;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        Map delegateOrNull = delegateOrNull();
        if (delegateOrNull != null) {
            return delegateOrNull.size();
        }
        return this.size;
    }

    public final Object value(int i) {
        return requireValues()[i];
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Collection values() {
        Collection collection = this.valuesView;
        if (collection == null) {
            ValuesView valuesView = new ValuesView();
            this.valuesView = valuesView;
            return valuesView;
        }
        return collection;
    }

    public CompactHashMap(byte[] bArr) {
        init(12);
    }
}
