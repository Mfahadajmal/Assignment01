package com.google.common.collect;

import com.google.common.collect.ImmutableCollection;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.List;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.function.UnaryOperator;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ImmutableList<E> extends ImmutableCollection<E> implements List<E>, RandomAccess, j$.util.List<E> {
    private static final UnmodifiableListIterator EMPTY_ITR = new Itr(RegularImmutableList.EMPTY, 0);
    public static final /* synthetic */ int ImmutableList$ar$NoOp = 0;
    private static final long serialVersionUID = -889275714;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder<E> extends ImmutableCollection.ArrayBasedBuilder<E> {
        public Builder() {
            super(4);
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        public final /* synthetic */ void add$ar$ds$187ad64f_0(Object obj) {
            super.add$ar$ds$9dd0f2d_0(obj);
        }

        public final void add$ar$ds$4f674a09_0(Object obj) {
            super.add$ar$ds$9dd0f2d_0(obj);
        }

        public final void addAll$ar$ds$2104aa48_0(Iterable iterable) {
            super.addAll$ar$ds(iterable);
        }

        public final void addAll$ar$ds$f8df74bc_0(Iterator it) {
            while (it.hasNext()) {
                add$ar$ds$187ad64f_0(it.next());
            }
        }

        public final ImmutableList build() {
            this.forceCopy = true;
            return ImmutableList.asImmutableList(this.contents, this.size);
        }

        public Builder(int i) {
            super(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Itr extends UnmodifiableListIterator {
        private final ImmutableList list;

        public Itr(ImmutableList immutableList, int i) {
            super(immutableList.size(), i);
            this.list = immutableList;
        }

        @Override // com.google.common.collect.UnmodifiableListIterator
        protected final Object get(int i) {
            return this.list.get(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SerializedForm implements Serializable {
        private static final long serialVersionUID = 0;
        final Object[] elements;

        public SerializedForm(Object[] objArr) {
            this.elements = objArr;
        }

        Object readResolve() {
            return ImmutableList.copyOf(this.elements);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableList asImmutableList(Object[] objArr) {
        return asImmutableList(objArr, objArr.length);
    }

    public static Builder builderWithExpectedSize(int i) {
        ContextDataProvider.checkNonnegative$ar$ds(i, "expectedSize");
        return new Builder(i);
    }

    public static ImmutableList construct(Object... objArr) {
        ContextDataProvider.checkElementsNotNull$ar$ds$c35b535c_0(objArr, objArr.length);
        return asImmutableList(objArr);
    }

    public static ImmutableList copyOf(Iterable iterable) {
        iterable.getClass();
        if (iterable instanceof Collection) {
            return copyOf((Collection) iterable);
        }
        Iterator it = iterable.iterator();
        if (!it.hasNext()) {
            return RegularImmutableList.EMPTY;
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return of(next);
        }
        Builder builder = new Builder();
        builder.add$ar$ds$4f674a09_0(next);
        builder.addAll$ar$ds$f8df74bc_0(it);
        return builder.build();
    }

    public static ImmutableList of(Object obj) {
        return construct(obj);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    public static ImmutableList sortedCopyOf(Comparator comparator, Iterable iterable) {
        Collection collection;
        comparator.getClass();
        if (iterable instanceof Collection) {
            collection = (Collection) iterable;
        } else {
            Iterator it = iterable.iterator();
            ArrayList arrayList = new ArrayList();
            ContextDataProvider.addAll(arrayList, it);
            collection = arrayList;
        }
        Object[] array = collection.toArray();
        ContextDataProvider.checkElementsNotNull$ar$ds$c35b535c_0(array, array.length);
        Arrays.sort(array, comparator);
        return asImmutableList(array);
    }

    @Override // java.util.List
    @Deprecated
    public final void add(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List
    @Deprecated
    public final boolean addAll(int i, Collection collection) {
        throw new UnsupportedOperationException();
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
    public boolean contains(Object obj) {
        if (indexOf(obj) >= 0) {
            return true;
        }
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public int copyIntoArray(Object[] objArr, int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            objArr[i + i2] = get(i2);
        }
        return i + size;
    }

    @Override // java.util.Collection, java.util.List
    public final boolean equals(Object obj) {
        return ContextDataProvider.equalsImpl(this, obj);
    }

    @Override // java.util.Collection, java.util.List
    public final int hashCode() {
        int size = size();
        int i = 1;
        for (int i2 = 0; i2 < size; i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            if (obj.equals(get(i))) {
                return i;
            }
        }
        return -1;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: iterator */
    public final UnmodifiableIterator listIterator() {
        return iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        if (obj == null) {
            return -1;
        }
        for (int size = size() - 1; size >= 0; size--) {
            if (obj.equals(get(size))) {
                return size;
            }
        }
        return -1;
    }

    @Override // java.util.List
    /* renamed from: listIterator, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public final UnmodifiableListIterator listIterator() {
        return listIterator(0);
    }

    @Override // java.util.List
    @Deprecated
    public final Object remove(int i) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, j$.util.List
    public final /* synthetic */ void replaceAll(UnaryOperator unaryOperator) {
        List.CC.$default$replaceAll(this, unaryOperator);
    }

    public ImmutableList reverse() {
        if (size() <= 1) {
            return this;
        }
        return new ReverseImmutableList(this);
    }

    @Override // java.util.List
    @Deprecated
    public final Object set(int i, Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.List, j$.util.List
    public final /* synthetic */ void sort(Comparator comparator) {
        List.CC.$default$sort(this, comparator);
    }

    @Override // java.util.List
    public ImmutableList subList(int i, int i2) {
        ContextDataProvider.checkPositionIndexes(i, i2, size());
        int i3 = i2 - i;
        if (i3 == size()) {
            return this;
        }
        if (i3 == 0) {
            return RegularImmutableList.EMPTY;
        }
        return new SubList(i, i3);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ReverseImmutableList extends ImmutableList {
        private final transient ImmutableList forwardList;

        public ReverseImmutableList(ImmutableList immutableList) {
            this.forwardList = immutableList;
        }

        private final int reverseIndex(int i) {
            return (size() - 1) - i;
        }

        private final int reversePosition(int i) {
            return size() - i;
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
        public final boolean contains(Object obj) {
            return this.forwardList.contains(obj);
        }

        @Override // java.util.List
        public final Object get(int i) {
            ContextDataProvider.checkElementIndex$ar$ds(i, size());
            return this.forwardList.get(reverseIndex(i));
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public final int indexOf(Object obj) {
            int lastIndexOf = this.forwardList.lastIndexOf(obj);
            if (lastIndexOf >= 0) {
                return reverseIndex(lastIndexOf);
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean isPartialView() {
            return this.forwardList.isPartialView();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public final int lastIndexOf(Object obj) {
            int indexOf = this.forwardList.indexOf(obj);
            if (indexOf >= 0) {
                return reverseIndex(indexOf);
            }
            return -1;
        }

        @Override // com.google.common.collect.ImmutableList
        public final ImmutableList reverse() {
            return this.forwardList;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public final int size() {
            return this.forwardList.size();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public final ImmutableList subList(int i, int i2) {
            ContextDataProvider.checkPositionIndexes(i, i2, size());
            return this.forwardList.subList(reversePosition(i2), reversePosition(i)).reverse();
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public final /* bridge */ /* synthetic */ java.util.List subList(int i, int i2) {
            return subList(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SubList extends ImmutableList {
        final transient int length;
        final transient int offset;

        public SubList(int i, int i2) {
            this.offset = i;
            this.length = i2;
        }

        @Override // java.util.List
        public final Object get(int i) {
            ContextDataProvider.checkElementIndex$ar$ds(i, this.length);
            return ImmutableList.this.get(i + this.offset);
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final Object[] internalArray() {
            return ImmutableList.this.internalArray();
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final int internalArrayEnd() {
            return ImmutableList.this.internalArrayStart() + this.offset + this.length;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final int internalArrayStart() {
            return ImmutableList.this.internalArrayStart() + this.offset;
        }

        @Override // com.google.common.collect.ImmutableCollection
        public final boolean isPartialView() {
            return true;
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
        public final int size() {
            return this.length;
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public final ImmutableList subList(int i, int i2) {
            ContextDataProvider.checkPositionIndexes(i, i2, this.length);
            int i3 = this.offset;
            return ImmutableList.this.subList(i + i3, i2 + i3);
        }

        @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
        public Object writeReplace() {
            return super.writeReplace();
        }

        @Override // com.google.common.collect.ImmutableList, java.util.List
        public final /* bridge */ /* synthetic */ java.util.List subList(int i, int i2) {
            return subList(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ImmutableList asImmutableList(Object[] objArr, int i) {
        if (i == 0) {
            return RegularImmutableList.EMPTY;
        }
        return new RegularImmutableList(objArr, i);
    }

    public static ImmutableList of(Object obj, Object obj2) {
        return construct(obj, obj2);
    }

    public static ImmutableList of(Object obj, Object obj2, Object obj3) {
        return construct(obj, obj2, obj3);
    }

    @Override // java.util.List
    public final UnmodifiableListIterator listIterator(int i) {
        ContextDataProvider.checkPositionIndex$ar$ds(i, size());
        return isEmpty() ? EMPTY_ITR : new Itr(this, i);
    }

    public static ImmutableList of(Object obj, Object obj2, Object obj3, Object obj4) {
        return construct(obj, obj2, obj3, obj4);
    }

    public static ImmutableList of(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7) {
        return construct(obj, obj2, obj3, obj4, obj5, obj6, obj7);
    }

    public static ImmutableList of(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9) {
        return construct(obj, obj2, obj3, obj4, obj5, obj6, obj7, obj8, obj9);
    }

    @SafeVarargs
    public static ImmutableList of(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12, Object... objArr) {
        ContextDataProvider.checkArgument(true, (Object) "the total number of elements must fit in an int");
        int length = objArr.length;
        Object[] objArr2 = new Object[length + 12];
        objArr2[0] = obj;
        objArr2[1] = obj2;
        objArr2[2] = obj3;
        objArr2[3] = obj4;
        objArr2[4] = obj5;
        objArr2[5] = obj6;
        objArr2[6] = obj7;
        objArr2[7] = obj8;
        objArr2[8] = obj9;
        objArr2[9] = obj10;
        objArr2[10] = obj11;
        objArr2[11] = obj12;
        System.arraycopy(objArr, 0, objArr2, 12, length);
        return construct(objArr2);
    }

    public static ImmutableList copyOf(Collection collection) {
        if (!(collection instanceof ImmutableCollection)) {
            return construct(collection.toArray());
        }
        ImmutableList asList = ((ImmutableCollection) collection).asList();
        return asList.isPartialView() ? asImmutableList(asList.toArray()) : asList;
    }

    public static ImmutableList copyOf(Object[] objArr) {
        if (objArr.length == 0) {
            return RegularImmutableList.EMPTY;
        }
        return construct((Object[]) objArr.clone());
    }

    @Override // com.google.common.collect.ImmutableCollection
    @Deprecated
    public final ImmutableList asList() {
        return this;
    }
}
