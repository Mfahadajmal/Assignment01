package com.google.common.collect;

import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
class Maps$KeySet extends Sets$ImprovedAbstractSet {
    final Map map;

    public Maps$KeySet(Map map) {
        map.getClass();
        this.map = map;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.map.clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean contains(Object obj) {
        return this.map.containsKey(obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.Set
    public Iterator iterator() {
        final Iterator it = this.map.entrySet().iterator();
        return new TransformedIterator(it) { // from class: com.google.common.collect.Maps$1
            @Override // com.google.common.collect.TransformedIterator
            public final /* bridge */ /* synthetic */ Object transform(Object obj) {
                return ((Map.Entry) obj).getKey();
            }
        };
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        if (contains(obj)) {
            this.map.remove(obj);
            return true;
        }
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return this.map.size();
    }
}
