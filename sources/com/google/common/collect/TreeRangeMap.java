package com.google.common.collect;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates$CompositionPredicate;
import com.google.common.base.Predicates$InPredicate;
import com.google.common.base.Predicates$NotPredicate;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TreeRangeMap implements RangeMap {
    public final NavigableMap entriesByLowerBound = new TreeMap();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class RangeMapEntry extends AbstractMapEntry {
        public final Range range;
        public final Object value;

        public RangeMapEntry(Range range, Object obj) {
            this.range = range;
            this.value = obj;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public final /* synthetic */ Object getKey() {
            return this.range;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Cut getLowerBound() {
            return this.range.lowerBound;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Cut getUpperBound() {
            return this.range.upperBound;
        }

        @Override // com.google.common.collect.AbstractMapEntry, java.util.Map.Entry
        public final Object getValue() {
            return this.value;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SubRangeMap implements RangeMap {
        public final Range subRange;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class SubRangeMapAsMap extends AbstractMap {

            /* JADX INFO: Access modifiers changed from: package-private */
            /* compiled from: PG */
            /* renamed from: com.google.common.collect.TreeRangeMap$SubRangeMap$SubRangeMapAsMap$2, reason: invalid class name */
            /* loaded from: classes.dex */
            public final class AnonymousClass2 extends Maps$EntrySet {
                public AnonymousClass2() {
                }

                @Override // com.google.common.collect.Maps$EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public final boolean isEmpty() {
                    if (!iterator().hasNext()) {
                        return true;
                    }
                    return false;
                }

                @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
                public final Iterator iterator() {
                    SubRangeMapAsMap subRangeMapAsMap = SubRangeMapAsMap.this;
                    if (SubRangeMap.this.subRange.isEmpty()) {
                        return Iterators$ArrayItr.EMPTY;
                    }
                    SubRangeMap subRangeMap = SubRangeMap.this;
                    return new AbstractIterator(subRangeMapAsMap, TreeRangeMap.this.entriesByLowerBound.tailMap((Cut) ContextDataProvider.firstNonNull((Cut) TreeRangeMap.this.entriesByLowerBound.floorKey(subRangeMap.subRange.lowerBound), SubRangeMap.this.subRange.lowerBound), true).values().iterator());
                }

                @Override // com.google.common.collect.Maps$EntrySet
                public final Map map() {
                    return SubRangeMapAsMap.this;
                }

                @Override // com.google.common.collect.Maps$EntrySet, com.google.common.collect.Sets$ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public final boolean retainAll(Collection collection) {
                    return SubRangeMapAsMap.this.removeEntryIf(new Predicates$NotPredicate(new Predicates$InPredicate(collection)));
                }

                @Override // com.google.common.collect.Maps$EntrySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                public final int size() {
                    Iterator it = iterator();
                    long j = 0;
                    while (it.hasNext()) {
                        it.next();
                        j++;
                    }
                    return ContextDataProvider.saturatedCast(j);
                }
            }

            public SubRangeMapAsMap() {
            }

            @Override // java.util.AbstractMap, java.util.Map
            public final void clear() {
                SubRangeMap subRangeMap = SubRangeMap.this;
                TreeRangeMap.this.remove(subRangeMap.subRange);
            }

            @Override // java.util.AbstractMap, java.util.Map
            public final boolean containsKey(Object obj) {
                if (get(obj) != null) {
                    return true;
                }
                return false;
            }

            @Override // java.util.AbstractMap, java.util.Map
            public final Set entrySet() {
                return new AnonymousClass2();
            }

            @Override // java.util.AbstractMap, java.util.Map
            public final Object get(Object obj) {
                RangeMapEntry rangeMapEntry;
                try {
                    if (obj instanceof Range) {
                        Range range = (Range) obj;
                        Range range2 = SubRangeMap.this.subRange;
                        if (range2.lowerBound.compareTo(range.lowerBound) <= 0 && range2.upperBound.compareTo(range.upperBound) >= 0 && !range.isEmpty()) {
                            if (range.lowerBound.compareTo(SubRangeMap.this.subRange.lowerBound) == 0) {
                                Map.Entry floorEntry = TreeRangeMap.this.entriesByLowerBound.floorEntry(range.lowerBound);
                                if (floorEntry != null) {
                                    rangeMapEntry = (RangeMapEntry) floorEntry.getValue();
                                } else {
                                    rangeMapEntry = null;
                                }
                            } else {
                                rangeMapEntry = (RangeMapEntry) TreeRangeMap.this.entriesByLowerBound.get(range.lowerBound);
                            }
                            if (rangeMapEntry != null && rangeMapEntry.range.isConnected(SubRangeMap.this.subRange) && rangeMapEntry.range.intersection(SubRangeMap.this.subRange).equals(range)) {
                                return rangeMapEntry.value;
                            }
                        }
                    }
                } catch (ClassCastException unused) {
                }
                return null;
            }

            @Override // java.util.AbstractMap, java.util.Map
            public final Set keySet() {
                return new Maps$KeySet(this) { // from class: com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.1
                    @Override // com.google.common.collect.Maps$KeySet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                    public final boolean remove(Object obj) {
                        if (SubRangeMapAsMap.this.remove(obj) != null) {
                            return true;
                        }
                        return false;
                    }

                    @Override // com.google.common.collect.Sets$ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
                    public final boolean retainAll(Collection collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(new Predicates$CompositionPredicate(new Predicates$NotPredicate(new Predicates$InPredicate(collection)), Maps$EntryFunction.KEY));
                    }
                };
            }

            @Override // java.util.AbstractMap, java.util.Map
            public final Object remove(Object obj) {
                Object obj2 = get(obj);
                if (obj2 != null) {
                    obj.getClass();
                    TreeRangeMap.this.remove((Range) obj);
                    return obj2;
                }
                return null;
            }

            public final boolean removeEntryIf(Predicate predicate) {
                ArrayList arrayList = new ArrayList();
                for (Map.Entry entry : new AnonymousClass2()) {
                    if (predicate.apply(entry)) {
                        arrayList.add((Range) entry.getKey());
                    }
                }
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    TreeRangeMap.this.remove((Range) arrayList.get(i));
                }
                if (arrayList.isEmpty()) {
                    return false;
                }
                return true;
            }

            @Override // java.util.AbstractMap, java.util.Map
            public final Collection values() {
                return new Maps$Values(this) { // from class: com.google.common.collect.TreeRangeMap.SubRangeMap.SubRangeMapAsMap.4
                    @Override // com.google.common.collect.Maps$Values, java.util.AbstractCollection, java.util.Collection
                    public final boolean removeAll(Collection collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(new Predicates$CompositionPredicate(new Predicates$InPredicate(collection), Maps$EntryFunction.VALUE));
                    }

                    @Override // com.google.common.collect.Maps$Values, java.util.AbstractCollection, java.util.Collection
                    public final boolean retainAll(Collection collection) {
                        return SubRangeMapAsMap.this.removeEntryIf(new Predicates$CompositionPredicate(new Predicates$NotPredicate(new Predicates$InPredicate(collection)), Maps$EntryFunction.VALUE));
                    }
                };
            }
        }

        public SubRangeMap(Range range) {
            this.subRange = range;
        }

        @Override // com.google.common.collect.RangeMap
        public final Map asMapOfRanges() {
            return new SubRangeMapAsMap();
        }

        public final boolean equals(Object obj) {
            if (obj instanceof RangeMap) {
                return new SubRangeMapAsMap().equals(((RangeMap) obj).asMapOfRanges());
            }
            return false;
        }

        public final int hashCode() {
            return new SubRangeMapAsMap().hashCode();
        }

        public final String toString() {
            return new SubRangeMapAsMap().toString();
        }
    }

    private final void putRangeMapEntry(Cut cut, Cut cut2, Object obj) {
        this.entriesByLowerBound.put(cut, new RangeMapEntry(new Range(cut, cut2), obj));
    }

    @Override // com.google.common.collect.RangeMap
    public final Map asMapOfRanges() {
        return new Maps$IteratorBasedAbstractMap(this, this.entriesByLowerBound.values());
    }

    public final boolean equals(Object obj) {
        if (obj instanceof RangeMap) {
            return asMapOfRanges().equals(((RangeMap) obj).asMapOfRanges());
        }
        return false;
    }

    public final int hashCode() {
        return asMapOfRanges().hashCode();
    }

    public final void remove(Range range) {
        if (range.isEmpty()) {
            return;
        }
        Map.Entry lowerEntry = this.entriesByLowerBound.lowerEntry(range.lowerBound);
        if (lowerEntry != null) {
            RangeMapEntry rangeMapEntry = (RangeMapEntry) lowerEntry.getValue();
            if (rangeMapEntry.getUpperBound().compareTo(range.lowerBound) > 0) {
                if (rangeMapEntry.getUpperBound().compareTo(range.upperBound) > 0) {
                    putRangeMapEntry(range.upperBound, rangeMapEntry.getUpperBound(), ((RangeMapEntry) lowerEntry.getValue()).value);
                }
                putRangeMapEntry(rangeMapEntry.getLowerBound(), range.lowerBound, ((RangeMapEntry) lowerEntry.getValue()).value);
            }
        }
        Map.Entry lowerEntry2 = this.entriesByLowerBound.lowerEntry(range.upperBound);
        if (lowerEntry2 != null) {
            RangeMapEntry rangeMapEntry2 = (RangeMapEntry) lowerEntry2.getValue();
            if (rangeMapEntry2.getUpperBound().compareTo(range.upperBound) > 0) {
                putRangeMapEntry(range.upperBound, rangeMapEntry2.getUpperBound(), ((RangeMapEntry) lowerEntry2.getValue()).value);
            }
        }
        this.entriesByLowerBound.subMap(range.lowerBound, range.upperBound).clear();
    }

    public final String toString() {
        return this.entriesByLowerBound.values().toString();
    }
}
