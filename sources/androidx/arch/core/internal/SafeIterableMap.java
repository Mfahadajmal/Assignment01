package androidx.arch.core.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SafeIterableMap implements Iterable {
    public Entry mEnd;
    public final WeakHashMap mIterators = new WeakHashMap();
    public int mSize = 0;
    public Entry mStart;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AscendingIterator extends ListIterator {
        public AscendingIterator(Entry entry, Entry entry2) {
            super(entry, entry2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        public final Entry backward(Entry entry) {
            return entry.mPrevious;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        public final Entry forward(Entry entry) {
            return entry.mNext;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DescendingIterator extends ListIterator {
        public DescendingIterator(Entry entry, Entry entry2) {
            super(entry, entry2);
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        public final Entry backward(Entry entry) {
            return entry.mNext;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.ListIterator
        public final Entry forward(Entry entry) {
            return entry.mPrevious;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Entry implements Map.Entry {
        final Object mKey;
        Entry mNext;
        public Entry mPrevious;
        public final Object mValue;

        public Entry(Object obj, Object obj2) {
            this.mKey = obj;
            this.mValue = obj2;
        }

        @Override // java.util.Map.Entry
        public final boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.mKey.equals(entry.mKey) && this.mValue.equals(entry.mValue)) {
                return true;
            }
            return false;
        }

        @Override // java.util.Map.Entry
        public final Object getKey() {
            return this.mKey;
        }

        @Override // java.util.Map.Entry
        public final Object getValue() {
            return this.mValue;
        }

        @Override // java.util.Map.Entry
        public final int hashCode() {
            Object obj = this.mValue;
            return obj.hashCode() ^ this.mKey.hashCode();
        }

        @Override // java.util.Map.Entry
        public final Object setValue(Object obj) {
            throw new UnsupportedOperationException("An entry modification is not supported");
        }

        public final String toString() {
            return this.mKey + "=" + this.mValue;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class IteratorWithAdditions extends SupportRemove implements Iterator {
        private boolean mBeforeStart = true;
        private Entry mCurrent;

        public IteratorWithAdditions() {
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.mBeforeStart) {
                if (SafeIterableMap.this.mStart != null) {
                    return true;
                }
                return false;
            }
            Entry entry = this.mCurrent;
            if (entry != null && entry.mNext != null) {
                return true;
            }
            return false;
        }

        @Override // java.util.Iterator
        public final Map.Entry next() {
            Entry entry;
            if (this.mBeforeStart) {
                this.mBeforeStart = false;
                entry = SafeIterableMap.this.mStart;
            } else {
                Entry entry2 = this.mCurrent;
                entry = entry2 != null ? entry2.mNext : null;
            }
            this.mCurrent = entry;
            return this.mCurrent;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.SupportRemove
        public final void supportRemove(Entry entry) {
            boolean z;
            Entry entry2 = this.mCurrent;
            if (entry == entry2) {
                Entry entry3 = entry2.mPrevious;
                this.mCurrent = entry3;
                if (entry3 == null) {
                    z = true;
                } else {
                    z = false;
                }
                this.mBeforeStart = z;
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public abstract class ListIterator extends SupportRemove implements Iterator {
        Entry mExpectedEnd;
        Entry mNext;

        public ListIterator(Entry entry, Entry entry2) {
            this.mExpectedEnd = entry2;
            this.mNext = entry;
        }

        private final Entry nextNode() {
            Entry entry = this.mNext;
            Entry entry2 = this.mExpectedEnd;
            if (entry != entry2 && entry2 != null) {
                return forward(entry);
            }
            return null;
        }

        public abstract Entry backward(Entry entry);

        public abstract Entry forward(Entry entry);

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.mNext != null) {
                return true;
            }
            return false;
        }

        @Override // androidx.arch.core.internal.SafeIterableMap.SupportRemove
        public final void supportRemove(Entry entry) {
            if (this.mExpectedEnd == entry && entry == this.mNext) {
                this.mNext = null;
                this.mExpectedEnd = null;
            }
            Entry entry2 = this.mExpectedEnd;
            if (entry2 == entry) {
                this.mExpectedEnd = backward(entry2);
            }
            if (this.mNext == entry) {
                this.mNext = nextNode();
            }
        }

        @Override // java.util.Iterator
        public final Map.Entry next() {
            Entry entry = this.mNext;
            this.mNext = nextNode();
            return entry;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    abstract class SupportRemove {
        public abstract void supportRemove(Entry entry);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SafeIterableMap)) {
            return false;
        }
        SafeIterableMap safeIterableMap = (SafeIterableMap) obj;
        if (this.mSize != safeIterableMap.mSize) {
            return false;
        }
        Iterator it = iterator();
        Iterator it2 = safeIterableMap.iterator();
        while (it.hasNext() && it2.hasNext()) {
            Map.Entry next = ((ListIterator) it).next();
            Map.Entry next2 = ((ListIterator) it2).next();
            if (next == null) {
                if (next2 != null) {
                    return false;
                }
                next2 = null;
            }
            if (next != null && !next.equals(next2)) {
                return false;
            }
        }
        if (!it.hasNext() && !it2.hasNext()) {
            return true;
        }
        return false;
    }

    protected Entry get(Object obj) {
        Entry entry = this.mStart;
        while (entry != null && !entry.mKey.equals(obj)) {
            entry = entry.mNext;
        }
        return entry;
    }

    public final int hashCode() {
        Iterator it = iterator();
        int i = 0;
        while (it.hasNext()) {
            i += ((ListIterator) it).next().hashCode();
        }
        return i;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        AscendingIterator ascendingIterator = new AscendingIterator(this.mStart, this.mEnd);
        this.mIterators.put(ascendingIterator, false);
        return ascendingIterator;
    }

    public final IteratorWithAdditions iteratorWithAdditions() {
        IteratorWithAdditions iteratorWithAdditions = new IteratorWithAdditions();
        this.mIterators.put(iteratorWithAdditions, false);
        return iteratorWithAdditions;
    }

    public final Entry put(Object obj, Object obj2) {
        Entry entry = new Entry(obj, obj2);
        this.mSize++;
        Entry entry2 = this.mEnd;
        if (entry2 == null) {
            this.mStart = entry;
        } else {
            entry2.mNext = entry;
            entry.mPrevious = entry2;
        }
        this.mEnd = entry;
        return entry;
    }

    public final Object putIfAbsent(Object obj, Object obj2) {
        Entry entry = get(obj);
        if (entry != null) {
            return entry.mValue;
        }
        put(obj, obj2);
        return null;
    }

    public Object remove(Object obj) {
        Entry entry = get(obj);
        if (entry == null) {
            return null;
        }
        this.mSize--;
        if (!this.mIterators.isEmpty()) {
            Iterator it = this.mIterators.keySet().iterator();
            while (it.hasNext()) {
                ((SupportRemove) it.next()).supportRemove(entry);
            }
        }
        Entry entry2 = entry.mPrevious;
        Entry entry3 = entry.mNext;
        if (entry2 != null) {
            entry2.mNext = entry3;
        } else {
            this.mStart = entry3;
        }
        Entry entry4 = entry.mNext;
        if (entry4 != null) {
            entry4.mPrevious = entry2;
        } else {
            this.mEnd = entry2;
        }
        entry.mNext = null;
        entry.mPrevious = null;
        return entry.mValue;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[");
        Iterator it = iterator();
        while (it.hasNext()) {
            sb.append(((ListIterator) it).next().toString());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
