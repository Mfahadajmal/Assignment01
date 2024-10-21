package com.google.common.collect;

import com.google.common.flogger.context.ContextDataProvider;
import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Lists$ReverseList extends AbstractList {
    public final List forwardList;

    public Lists$ReverseList(List list) {
        list.getClass();
        this.forwardList = list;
    }

    private final int reverseIndex(int i) {
        int size = size();
        ContextDataProvider.checkElementIndex$ar$ds(i, size);
        return (size - 1) - i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        this.forwardList.add(reversePosition(i), obj);
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        this.forwardList.clear();
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return this.forwardList.get(reverseIndex(i));
    }

    @Override // java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, java.util.List
    public final Iterator iterator() {
        return listIterator();
    }

    @Override // java.util.AbstractList, java.util.List
    public final ListIterator listIterator(int i) {
        final ListIterator listIterator = this.forwardList.listIterator(reversePosition(i));
        return new ListIterator(this) { // from class: com.google.common.collect.Lists$ReverseList.1
            boolean canRemoveOrSet;
            final /* synthetic */ Lists$ReverseList this$0;

            {
                this.this$0 = this;
            }

            @Override // java.util.ListIterator
            public final void add(Object obj) {
                listIterator.add(obj);
                listIterator.previous();
                this.canRemoveOrSet = false;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public final boolean hasNext() {
                return listIterator.hasPrevious();
            }

            @Override // java.util.ListIterator
            public final boolean hasPrevious() {
                return listIterator.hasNext();
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public final Object next() {
                if (hasNext()) {
                    this.canRemoveOrSet = true;
                    return listIterator.previous();
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.ListIterator
            public final int nextIndex() {
                return this.this$0.reversePosition(listIterator.nextIndex());
            }

            @Override // java.util.ListIterator
            public final Object previous() {
                if (hasPrevious()) {
                    this.canRemoveOrSet = true;
                    return listIterator.next();
                }
                throw new NoSuchElementException();
            }

            @Override // java.util.ListIterator
            public final int previousIndex() {
                return nextIndex() - 1;
            }

            @Override // java.util.ListIterator, java.util.Iterator
            public final void remove() {
                ContextDataProvider.checkRemove(this.canRemoveOrSet);
                listIterator.remove();
                this.canRemoveOrSet = false;
            }

            @Override // java.util.ListIterator
            public final void set(Object obj) {
                ContextDataProvider.checkState(this.canRemoveOrSet);
                listIterator.set(obj);
            }
        };
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        return this.forwardList.remove(reverseIndex(i));
    }

    @Override // java.util.AbstractList
    protected final void removeRange(int i, int i2) {
        subList(i, i2).clear();
    }

    public final int reversePosition(int i) {
        int size = size();
        ContextDataProvider.checkPositionIndex$ar$ds(i, size);
        return size - i;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        return this.forwardList.set(reverseIndex(i), obj);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.forwardList.size();
    }

    @Override // java.util.AbstractList, java.util.List
    public final List subList(int i, int i2) {
        ContextDataProvider.checkPositionIndexes(i, i2, size());
        return ContextDataProvider.reverse(this.forwardList.subList(reversePosition(i2), reversePosition(i)));
    }
}
