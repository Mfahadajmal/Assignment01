package com.google.common.collect;

import com.google.common.collect.ImmutableSet;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.SortedSet;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NavigableSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ImmutableSortedSet extends ImmutableSet implements NavigableSet, SortedIterable, SortedSet {
    private static final long serialVersionUID = 912559;
    final transient Comparator comparator;
    transient ImmutableSortedSet descendingSet;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder extends ImmutableSet.Builder {
        private final Comparator comparator;

        public Builder(Comparator comparator) {
            comparator.getClass();
            this.comparator = comparator;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder
        /* renamed from: add */
        public final /* synthetic */ ImmutableSet.Builder add$ar$ds$187ad64f_0(Object obj) {
            super.add$ar$ds$187ad64f_0(obj);
            return this;
        }

        public final void add$ar$ds$10088e5_0(Object... objArr) {
            super.add$ar$ds$edda7ab4_0(objArr);
        }

        @Override // com.google.common.collect.ImmutableSet.Builder, com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public final /* synthetic */ void add$ar$ds$187ad64f_0(Object obj) {
            super.add$ar$ds$187ad64f_0(obj);
        }

        public final void add$ar$ds$7e8aa2c7_0(Object obj) {
            super.add$ar$ds$187ad64f_0(obj);
        }

        @Override // com.google.common.collect.ImmutableSet.Builder
        public final /* synthetic */ ImmutableSet.Builder combine(ImmutableSet.Builder builder) {
            super.combine(builder);
            return this;
        }

        @Override // com.google.common.collect.ImmutableSet.Builder
        public final ImmutableSortedSet build() {
            RegularImmutableSortedSet regularImmutableSortedSet;
            Object[] objArr = this.contents;
            int i = this.size;
            Comparator comparator = this.comparator;
            if (i == 0) {
                regularImmutableSortedSet = ImmutableSortedSet.emptySet(comparator);
            } else {
                ContextDataProvider.checkElementsNotNull$ar$ds$c35b535c_0(objArr, i);
                Arrays.sort(objArr, 0, i, comparator);
                int i2 = 1;
                for (int i3 = 1; i3 < i; i3++) {
                    Object obj = objArr[i3];
                    if (comparator.compare(obj, objArr[i2 - 1]) != 0) {
                        objArr[i2] = obj;
                        i2++;
                    }
                }
                Arrays.fill(objArr, i2, i, (Object) null);
                if (i2 < (objArr.length >> 1)) {
                    objArr = Arrays.copyOf(objArr, i2);
                }
                regularImmutableSortedSet = new RegularImmutableSortedSet(ImmutableList.asImmutableList(objArr, i2), comparator);
            }
            this.size = regularImmutableSortedSet.size();
            this.forceCopy = true;
            return regularImmutableSortedSet;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Comparator comparator;
        final Object[] elements;

        public SerializedForm(Comparator comparator, Object[] objArr) {
            this.comparator = comparator;
            this.elements = objArr;
        }

        Object readResolve() {
            Builder builder = new Builder(this.comparator);
            builder.add$ar$ds$10088e5_0(this.elements);
            return builder.build();
        }
    }

    public ImmutableSortedSet(Comparator comparator) {
        this.comparator = comparator;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RegularImmutableSortedSet emptySet(Comparator comparator) {
        if (NaturalOrdering.INSTANCE.equals(comparator)) {
            return RegularImmutableSortedSet.NATURAL_EMPTY_SET;
        }
        int i = ImmutableList.ImmutableList$ar$NoOp;
        return new RegularImmutableSortedSet(RegularImmutableList.EMPTY, comparator);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Deprecated
    public final void addFirst(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final void addLast(Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    public Object ceiling(Object obj) {
        Object next$ar$ds;
        next$ar$ds = ContextDataProvider.getNext$ar$ds(((RegularImmutableSortedSet) tailSet(obj, true)).listIterator());
        return next$ar$ds;
    }

    @Override // java.util.SortedSet, com.google.common.collect.SortedIterable
    public final Comparator comparator() {
        return this.comparator;
    }

    public abstract ImmutableSortedSet createDescendingSet();

    @Override // java.util.NavigableSet
    public abstract UnmodifiableIterator descendingIterator();

    @Override // java.util.NavigableSet
    public final /* bridge */ /* synthetic */ NavigableSet descendingSet() {
        ImmutableSortedSet immutableSortedSet = this.descendingSet;
        if (immutableSortedSet == null) {
            ImmutableSortedSet createDescendingSet = createDescendingSet();
            this.descendingSet = createDescendingSet;
            createDescendingSet.descendingSet = this;
            return createDescendingSet;
        }
        return immutableSortedSet;
    }

    @Override // java.util.SortedSet
    public Object first() {
        return listIterator().next();
    }

    @Override // java.util.NavigableSet
    public Object floor(Object obj) {
        return ContextDataProvider.getNext$ar$ds(headSet(obj, true).descendingIterator());
    }

    public final Object getFirst() {
        return first();
    }

    public final Object getLast() {
        return last();
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* bridge */ /* synthetic */ java.util.SortedSet headSet(Object obj) {
        return headSet(obj, false);
    }

    public abstract ImmutableSortedSet headSetImpl(Object obj, boolean z);

    @Override // java.util.NavigableSet
    public Object higher(Object obj) {
        Object next$ar$ds;
        next$ar$ds = ContextDataProvider.getNext$ar$ds(((RegularImmutableSortedSet) tailSet(obj, false)).listIterator());
        return next$ar$ds;
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: iterator */
    public abstract UnmodifiableIterator listIterator();

    @Override // java.util.SortedSet
    public Object last() {
        return descendingIterator().next();
    }

    @Override // java.util.NavigableSet
    public Object lower(Object obj) {
        return ContextDataProvider.getNext$ar$ds(headSet(obj, false).descendingIterator());
    }

    @Override // java.util.NavigableSet
    @Deprecated
    public final Object pollFirst() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet
    @Deprecated
    public final Object pollLast() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object removeFirst() {
        throw new UnsupportedOperationException();
    }

    @Deprecated
    public final Object removeLast() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* bridge */ /* synthetic */ java.util.SortedSet subSet(Object obj, Object obj2) {
        return subSet(obj, true, obj2, false);
    }

    public abstract ImmutableSortedSet subSetImpl(Object obj, boolean z, Object obj2, boolean z2);

    @Override // java.util.NavigableSet, java.util.SortedSet
    public final /* bridge */ /* synthetic */ java.util.SortedSet tailSet(Object obj) {
        return tailSet(obj, true);
    }

    public abstract ImmutableSortedSet tailSetImpl(Object obj, boolean z);

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int unsafeCompare(Object obj, Object obj2) {
        return this.comparator.compare(obj, obj2);
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(this.comparator, toArray());
    }

    @Override // java.util.NavigableSet
    public final ImmutableSortedSet headSet(Object obj, boolean z) {
        obj.getClass();
        return headSetImpl(obj, z);
    }

    @Override // java.util.NavigableSet
    public final ImmutableSortedSet subSet(Object obj, boolean z, Object obj2, boolean z2) {
        obj.getClass();
        obj2.getClass();
        ContextDataProvider.checkArgument(this.comparator.compare(obj, obj2) <= 0);
        return subSetImpl(obj, z, obj2, z2);
    }

    @Override // java.util.NavigableSet
    public final ImmutableSortedSet tailSet(Object obj, boolean z) {
        obj.getClass();
        return tailSetImpl(obj, z);
    }
}
