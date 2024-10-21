package com.google.protobuf;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public class SmallSortedMap extends AbstractMap {
    public boolean isImmutable;
    private volatile EntrySet lazyEntrySet;
    public List entryList = Collections.emptyList();
    public Map overflowEntries = Collections.emptyMap();
    public Map overflowEntriesDescending = Collections.emptyMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Entry implements Map.Entry, Comparable {
        public final Comparable key;
        public Object value;

        public Entry(Comparable comparable, Object obj) {
            this.key = comparable;
            this.value = obj;
        }

        private static final boolean equals$ar$ds$50a05ab0_0(Object obj, Object obj2) {
            if (obj == null) {
                if (obj2 != null) {
                    return false;
                }
                return true;
            }
            return obj.equals(obj2);
        }

        @Override // java.lang.Comparable
        public final /* bridge */ /* synthetic */ int compareTo(Object obj) {
            return this.key.compareTo(((Entry) obj).key);
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Map.Entry)) {
                return false;
            }
            Map.Entry entry = (Map.Entry) obj;
            if (equals$ar$ds$50a05ab0_0(this.key, entry.getKey()) && equals$ar$ds$50a05ab0_0(this.value, entry.getValue())) {
                return true;
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public final /* synthetic */ Object getKey() {
            return this.key;
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            return this.value;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            int hashCode;
            Comparable comparable = this.key;
            int i = 0;
            if (comparable == null) {
                hashCode = 0;
            } else {
                hashCode = comparable.hashCode();
            }
            Object obj = this.value;
            if (obj != null) {
                i = obj.hashCode();
            }
            return hashCode ^ i;
        }

        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            SmallSortedMap.this.checkMutable();
            Object obj2 = this.value;
            this.value = obj;
            return obj2;
        }

        public final String toString() {
            return String.valueOf(this.key) + "=" + String.valueOf(this.value);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class EntryIterator implements Iterator {
        private Iterator lazyOverflowIterator;
        private boolean nextCalledBeforeRemove;
        private int pos = -1;

        public EntryIterator() {
        }

        private final Iterator getOverflowIterator() {
            if (this.lazyOverflowIterator == null) {
                this.lazyOverflowIterator = SmallSortedMap.this.overflowEntries.entrySet().iterator();
            }
            return this.lazyOverflowIterator;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.pos + 1 < SmallSortedMap.this.entryList.size()) {
                return true;
            }
            if (!SmallSortedMap.this.overflowEntries.isEmpty() && getOverflowIterator().hasNext()) {
                return true;
            }
            return false;
        }

        @Override // java.util.Iterator
        public final /* bridge */ /* synthetic */ Object next() {
            this.nextCalledBeforeRemove = true;
            int i = this.pos + 1;
            this.pos = i;
            if (i < SmallSortedMap.this.entryList.size()) {
                return (Map.Entry) SmallSortedMap.this.entryList.get(this.pos);
            }
            return (Map.Entry) getOverflowIterator().next();
        }

        @Override // java.util.Iterator
        public final void remove() {
            if (this.nextCalledBeforeRemove) {
                this.nextCalledBeforeRemove = false;
                SmallSortedMap.this.checkMutable();
                if (this.pos < SmallSortedMap.this.entryList.size()) {
                    SmallSortedMap smallSortedMap = SmallSortedMap.this;
                    int i = this.pos;
                    this.pos = i - 1;
                    smallSortedMap.removeArrayEntryAt(i);
                    return;
                }
                getOverflowIterator().remove();
                return;
            }
            throw new IllegalStateException("remove() was called before next()");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class EntrySet extends AbstractSet {
        public EntrySet() {
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final /* bridge */ /* synthetic */ boolean add(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            if (!contains(entry)) {
                SmallSortedMap.this.put((Comparable) entry.getKey(), entry.getValue());
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final void clear() {
            SmallSortedMap.this.clear();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean contains(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            Object obj2 = SmallSortedMap.this.get(entry.getKey());
            Object value = entry.getValue();
            if (obj2 == value) {
                return true;
            }
            if (obj2 != null && obj2.equals(value)) {
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
        public final Iterator iterator() {
            return new EntryIterator();
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final boolean remove(Object obj) {
            Map.Entry entry = (Map.Entry) obj;
            if (contains(entry)) {
                SmallSortedMap.this.remove(entry.getKey());
                return true;
            }
            return false;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
        public final int size() {
            return SmallSortedMap.this.size();
        }
    }

    private final int binarySearchInArray(Comparable comparable) {
        int size = this.entryList.size();
        int i = size - 1;
        int i2 = 0;
        if (i >= 0) {
            int compareTo = comparable.compareTo(((Entry) this.entryList.get(i)).key);
            if (compareTo > 0) {
                return -(size + 1);
            }
            if (compareTo == 0) {
                return i;
            }
        }
        while (i2 <= i) {
            int i3 = (i2 + i) / 2;
            int compareTo2 = comparable.compareTo(((Entry) this.entryList.get(i3)).key);
            if (compareTo2 < 0) {
                i = i3 - 1;
            } else if (compareTo2 > 0) {
                i2 = i3 + 1;
            } else {
                return i3;
            }
        }
        return -(i2 + 1);
    }

    private final SortedMap getOverflowEntriesMutable() {
        checkMutable();
        if (this.overflowEntries.isEmpty() && !(this.overflowEntries instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.overflowEntries = treeMap;
            this.overflowEntriesDescending = treeMap.descendingMap();
        }
        return (SortedMap) this.overflowEntries;
    }

    public final void checkMutable() {
        if (!this.isImmutable) {
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        checkMutable();
        if (!this.entryList.isEmpty()) {
            this.entryList.clear();
        }
        if (!this.overflowEntries.isEmpty()) {
            this.overflowEntries.clear();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        if (binarySearchInArray(comparable) < 0 && !this.overflowEntries.containsKey(comparable)) {
            return false;
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.lazyEntrySet == null) {
            this.lazyEntrySet = new EntrySet();
        }
        return this.lazyEntrySet;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SmallSortedMap)) {
            return super.equals(obj);
        }
        SmallSortedMap smallSortedMap = (SmallSortedMap) obj;
        int size = size();
        if (size != smallSortedMap.size()) {
            return false;
        }
        int numArrayEntries = getNumArrayEntries();
        if (numArrayEntries == smallSortedMap.getNumArrayEntries()) {
            for (int i = 0; i < numArrayEntries; i++) {
                if (!getArrayEntryAt(i).equals(smallSortedMap.getArrayEntryAt(i))) {
                    return false;
                }
            }
            if (numArrayEntries == size) {
                return true;
            }
            return this.overflowEntries.equals(smallSortedMap.overflowEntries);
        }
        return entrySet().equals(smallSortedMap.entrySet());
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int binarySearchInArray = binarySearchInArray(comparable);
        if (binarySearchInArray >= 0) {
            return ((Entry) this.entryList.get(binarySearchInArray)).value;
        }
        return this.overflowEntries.get(comparable);
    }

    public final Map.Entry getArrayEntryAt(int i) {
        return (Map.Entry) this.entryList.get(i);
    }

    public final int getNumArrayEntries() {
        return this.entryList.size();
    }

    public final Iterable getOverflowEntries() {
        if (this.overflowEntries.isEmpty()) {
            return Collections.emptySet();
        }
        return this.overflowEntries.entrySet();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int numArrayEntries = getNumArrayEntries();
        int i = 0;
        for (int i2 = 0; i2 < numArrayEntries; i2++) {
            i += ((Entry) this.entryList.get(i2)).hashCode();
        }
        if (this.overflowEntries.size() > 0) {
            return i + this.overflowEntries.hashCode();
        }
        return i;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object put(Comparable comparable, Object obj) {
        checkMutable();
        int binarySearchInArray = binarySearchInArray(comparable);
        if (binarySearchInArray >= 0) {
            return ((Entry) this.entryList.get(binarySearchInArray)).setValue(obj);
        }
        checkMutable();
        if (this.entryList.isEmpty() && !(this.entryList instanceof ArrayList)) {
            this.entryList = new ArrayList(16);
        }
        int i = -(binarySearchInArray + 1);
        if (i >= 16) {
            return getOverflowEntriesMutable().put(comparable, obj);
        }
        if (this.entryList.size() == 16) {
            Entry entry = (Entry) this.entryList.remove(15);
            getOverflowEntriesMutable().put(entry.key, entry.value);
        }
        this.entryList.add(i, new Entry(comparable, obj));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        checkMutable();
        Comparable comparable = (Comparable) obj;
        int binarySearchInArray = binarySearchInArray(comparable);
        if (binarySearchInArray >= 0) {
            return removeArrayEntryAt(binarySearchInArray);
        }
        if (this.overflowEntries.isEmpty()) {
            return null;
        }
        return this.overflowEntries.remove(comparable);
    }

    public final Object removeArrayEntryAt(int i) {
        checkMutable();
        Object obj = ((Entry) this.entryList.remove(i)).value;
        if (!this.overflowEntries.isEmpty()) {
            Iterator it = getOverflowEntriesMutable().entrySet().iterator();
            List list = this.entryList;
            Map.Entry entry = (Map.Entry) it.next();
            list.add(new Entry((Comparable) entry.getKey(), entry.getValue()));
            it.remove();
        }
        return obj;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.entryList.size() + this.overflowEntries.size();
    }
}
