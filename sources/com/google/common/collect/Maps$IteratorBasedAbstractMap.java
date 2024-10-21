package com.google.common.collect;

import com.google.common.collect.TreeRangeMap;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
final class Maps$IteratorBasedAbstractMap extends AbstractMap {
    final Iterable entryIterable;
    final /* synthetic */ TreeRangeMap this$0;

    public Maps$IteratorBasedAbstractMap() {
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        ContextDataProvider.clear(entryIterator());
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        if (get(obj) != null) {
            return true;
        }
        return false;
    }

    public final Iterator entryIterator() {
        return this.entryIterable.iterator();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        return new Maps$EntrySet() { // from class: com.google.common.collect.Maps$IteratorBasedAbstractMap.1
            @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
            public final Iterator iterator() {
                return Maps$IteratorBasedAbstractMap.this.entryIterator();
            }

            @Override // com.google.common.collect.Maps$EntrySet
            public final Map map() {
                return Maps$IteratorBasedAbstractMap.this;
            }
        };
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        if (obj instanceof Range) {
            Range range = (Range) obj;
            TreeRangeMap treeRangeMap = this.this$0;
            TreeRangeMap.RangeMapEntry rangeMapEntry = (TreeRangeMap.RangeMapEntry) treeRangeMap.entriesByLowerBound.get(range.lowerBound);
            if (rangeMapEntry != null && rangeMapEntry.range.equals(range)) {
                return rangeMapEntry.value;
            }
            return null;
        }
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.this$0.entriesByLowerBound.size();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Maps$IteratorBasedAbstractMap(TreeRangeMap treeRangeMap, Iterable iterable) {
        this();
        this.this$0 = treeRangeMap;
        this.entryIterable = iterable;
    }
}
