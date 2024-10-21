package com.google.common.collect;

import com.google.common.base.Function;
import java.io.Serializable;
import java.util.AbstractSequentialList;
import java.util.List;
import java.util.ListIterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Lists$TransformingSequentialList extends AbstractSequentialList implements Serializable {
    private static final long serialVersionUID = 0;
    final List fromList;
    final Function function;

    public Lists$TransformingSequentialList(List list, Function function) {
        list.getClass();
        this.fromList = list;
        this.function = function;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean isEmpty() {
        return this.fromList.isEmpty();
    }

    @Override // java.util.AbstractSequentialList, java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        return new TransformedListIterator(this.fromList.listIterator(i)) { // from class: com.google.common.collect.Lists$TransformingSequentialList.1
            @Override // com.google.common.collect.TransformedIterator
            public final Object transform(Object obj) {
                return Lists$TransformingSequentialList.this.function.apply(obj);
            }
        };
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
