package com.google.common.collect;

import com.google.common.collect.ImmutableCollection;
import com.google.common.flogger.context.ContextDataProvider;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.SortedSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ImmutableSet<E> extends ImmutableCollection<E> implements Set<E>, j$.util.Set<E> {
    private static final long serialVersionUID = 912559;
    private transient ImmutableList asList;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class Builder extends ImmutableCollection.ArrayBasedBuilder {
        public Builder() {
            super(4);
        }

        @Override // com.google.common.collect.ImmutableCollection.ArrayBasedBuilder, com.google.common.collect.ImmutableCollection.Builder
        /* renamed from: add, reason: merged with bridge method [inline-methods] */
        public Builder add$ar$ds$187ad64f_0(Object obj) {
            obj.getClass();
            super.add$ar$ds$9dd0f2d_0(obj);
            return this;
        }

        public final void add$ar$ds$edda7ab4_0(Object... objArr) {
            addAll(objArr, objArr.length);
        }

        public final void addAll$ar$ds$9575dc1a_0(Iterable iterable) {
            super.addAll$ar$ds(iterable);
        }

        public ImmutableSet build() {
            int i = this.size;
            if (i != 0) {
                if (i != 1) {
                    ImmutableSet construct = ImmutableSet.construct(i, this.contents);
                    this.size = construct.size();
                    this.forceCopy = true;
                    return construct;
                }
                Object obj = this.contents[0];
                obj.getClass();
                return new SingletonImmutableSet(obj);
            }
            return RegularImmutableSet.EMPTY;
        }

        public Builder combine(Builder builder) {
            addAll(builder.contents, builder.size);
            return this;
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
            return ImmutableSet.copyOf(this.elements);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int chooseTableSize(int i) {
        boolean z;
        int max = Math.max(i, 2);
        if (max < 751619276) {
            int highestOneBit = Integer.highestOneBit(max - 1);
            do {
                highestOneBit += highestOneBit;
            } while (highestOneBit * 0.7d < max);
            return highestOneBit;
        }
        if (max < 1073741824) {
            z = true;
        } else {
            z = false;
        }
        ContextDataProvider.checkArgument(z, (Object) "collection too large");
        return 1073741824;
    }

    public static ImmutableSet construct(int i, Object... objArr) {
        if (i != 0) {
            if (i != 1) {
                int chooseTableSize = chooseTableSize(i);
                Object[] objArr2 = new Object[chooseTableSize];
                int i2 = chooseTableSize - 1;
                int i3 = 0;
                int i4 = 0;
                for (int i5 = 0; i5 < i; i5++) {
                    Object obj = objArr[i5];
                    ContextDataProvider.checkElementNotNull$ar$ds(obj, i5);
                    int hashCode = obj.hashCode();
                    int smear = ContextDataProvider.smear(hashCode);
                    while (true) {
                        int i6 = smear & i2;
                        Object obj2 = objArr2[i6];
                        if (obj2 == null) {
                            objArr[i4] = obj;
                            objArr2[i6] = obj;
                            i3 += hashCode;
                            i4++;
                            break;
                        }
                        if (!obj2.equals(obj)) {
                            smear++;
                        }
                    }
                }
                Arrays.fill(objArr, i4, i, (Object) null);
                if (i4 == 1) {
                    Object obj3 = objArr[0];
                    obj3.getClass();
                    return new SingletonImmutableSet(obj3);
                }
                if (chooseTableSize(i4) < chooseTableSize / 2) {
                    return construct(i4, objArr);
                }
                int length = objArr.length;
                if (i4 < (length >> 1) + (length >> 2)) {
                    objArr = Arrays.copyOf(objArr, i4);
                }
                return new RegularImmutableSet(objArr, i3, objArr2, i2, i4);
            }
            Object obj4 = objArr[0];
            obj4.getClass();
            return new SingletonImmutableSet(obj4);
        }
        return RegularImmutableSet.EMPTY;
    }

    public static ImmutableSet copyOf(Collection collection) {
        if ((collection instanceof ImmutableSet) && !(collection instanceof SortedSet)) {
            ImmutableSet immutableSet = (ImmutableSet) collection;
            if (!immutableSet.isPartialView()) {
                return immutableSet;
            }
        }
        Object[] array = collection.toArray();
        return construct(array.length, array);
    }

    public static ImmutableSet of(Object obj, Object obj2) {
        return construct(2, obj, obj2);
    }

    private void readObject(ObjectInputStream objectInputStream) {
        throw new InvalidObjectException("Use SerializedForm");
    }

    @Override // com.google.common.collect.ImmutableCollection
    public ImmutableList asList() {
        ImmutableList immutableList = this.asList;
        if (immutableList == null) {
            ImmutableList createAsList = createAsList();
            this.asList = createAsList;
            return createAsList;
        }
        return immutableList;
    }

    public ImmutableList createAsList() {
        return ImmutableList.asImmutableList(toArray());
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof ImmutableSet) && isHashCodeFast() && ((ImmutableSet) obj).isHashCodeFast() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return ContextDataProvider.hashCodeImpl(this);
    }

    public boolean isHashCodeFast() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: iterator */
    public abstract UnmodifiableIterator listIterator();

    @Override // com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return new SerializedForm(toArray());
    }

    @SafeVarargs
    public static ImmutableSet of(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object... objArr) {
        ContextDataProvider.checkArgument(true, (Object) "the total number of elements must fit in an int");
        int length = objArr.length;
        int i = length + 6;
        Object[] objArr2 = new Object[i];
        objArr2[0] = obj;
        objArr2[1] = obj2;
        objArr2[2] = obj3;
        objArr2[3] = obj4;
        objArr2[4] = obj5;
        objArr2[5] = obj6;
        System.arraycopy(objArr, 0, objArr2, 6, length);
        return construct(i, objArr2);
    }

    public static ImmutableSet copyOf(Object[] objArr) {
        int length = objArr.length;
        if (length == 0) {
            return RegularImmutableSet.EMPTY;
        }
        if (length != 1) {
            return construct(length, (Object[]) objArr.clone());
        }
        return new SingletonImmutableSet(objArr[0]);
    }
}
