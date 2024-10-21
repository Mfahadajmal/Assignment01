package com.google.common.collect;

import com.google.common.base.Function;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Lists$TransformingRandomAccessList extends AbstractList implements RandomAccess, Serializable {
    private static final long serialVersionUID = 0;
    final List fromList;
    final Function function;

    public Lists$TransformingRandomAccessList(List list, Function function) {
        list.getClass();
        this.fromList = list;
        this.function = function;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return this.function.apply(this.fromList.get(i));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.fromList.isEmpty();
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new TransformedListIterator(this.fromList.listIterator(i)) { // from class: com.google.common.collect.Lists$TransformingRandomAccessList.1
            @Override // com.google.common.collect.TransformedIterator
            public final Object transform(Object obj) {
                return Lists$TransformingRandomAccessList.this.function.apply(obj);
            }
        };
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        return this.function.apply(this.fromList.remove(i));
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        this.fromList.subList(i, i2).clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.fromList.size();
    }
}
