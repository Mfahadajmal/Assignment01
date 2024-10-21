package com.google.protobuf;

import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LazyField extends LazyFieldLite {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class LazyEntry implements Map.Entry {
        public Map.Entry entry;

        public LazyEntry(Map.Entry entry) {
            this.entry = entry;
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            return this.entry.getKey();
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            if (((LazyField) this.entry.getValue()) == null) {
                return null;
            }
            throw null;
        }

        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            if (obj instanceof MessageLite) {
                LazyField lazyField = (LazyField) this.entry.getValue();
                MessageLite messageLite = lazyField.value;
                lazyField.memoizedBytes = null;
                lazyField.value = (MessageLite) obj;
                return messageLite;
            }
            throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class LazyIterator implements Iterator {
        private final Iterator iterator;

        public LazyIterator(Iterator it) {
            this.iterator = it;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            return this.iterator.hasNext();
        }

        @Override // java.util.Iterator
        public final /* bridge */ /* synthetic */ Object next() {
            Map.Entry entry = (Map.Entry) this.iterator.next();
            if (entry.getValue() instanceof LazyField) {
                return new LazyEntry(entry);
            }
            return entry;
        }

        @Override // java.util.Iterator
        public final void remove() {
            this.iterator.remove();
        }
    }

    @Override // com.google.protobuf.LazyFieldLite
    public final boolean equals(Object obj) {
        throw null;
    }

    @Override // com.google.protobuf.LazyFieldLite
    public final int hashCode() {
        throw null;
    }

    public final String toString() {
        throw null;
    }
}
