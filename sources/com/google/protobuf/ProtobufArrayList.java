package com.google.protobuf;

import com.google.protobuf.Internal;
import java.util.Arrays;
import java.util.RandomAccess;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProtobufArrayList extends AbstractProtobufList implements RandomAccess {
    public static final ProtobufArrayList EMPTY_LIST = new ProtobufArrayList(new Object[0], 0, false);
    private Object[] array;
    private int size;

    ProtobufArrayList() {
        this(new Object[10], 0, true);
    }

    private final void ensureIndexInRange(int i) {
        if (i >= 0 && i < this.size) {
        } else {
            throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
        }
    }

    private final String makeOutOfBoundsExceptionMessage(int i) {
        return "Index:" + i + ", Size:" + this.size;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public final void add(int i, Object obj) {
        int i2;
        ensureIsMutable();
        if (i >= 0 && i <= (i2 = this.size)) {
            int i3 = i + 1;
            Object[] objArr = this.array;
            if (i2 < objArr.length) {
                System.arraycopy(objArr, i, objArr, i3, i2 - i);
            } else {
                Object[] objArr2 = new Object[((i2 * 3) / 2) + 1];
                System.arraycopy(objArr, 0, objArr2, 0, i);
                System.arraycopy(this.array, i, objArr2, i3, this.size - i);
                this.array = objArr2;
            }
            this.array[i] = obj;
            this.size++;
            this.modCount++;
            return;
        }
        throw new IndexOutOfBoundsException(makeOutOfBoundsExceptionMessage(i));
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        ensureIndexInRange(i);
        return this.array[i];
    }

    @Override // com.google.protobuf.Internal.ProtobufList, com.google.protobuf.Internal.BooleanList
    public final /* bridge */ /* synthetic */ Internal.ProtobufList mutableCopyWithCapacity(int i) {
        if (i >= this.size) {
            return new ProtobufArrayList(Arrays.copyOf(this.array, i), this.size, true);
        }
        throw new IllegalArgumentException();
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public final Object remove(int i) {
        ensureIsMutable();
        ensureIndexInRange(i);
        Object[] objArr = this.array;
        Object obj = objArr[i];
        if (i < this.size - 1) {
            System.arraycopy(objArr, i + 1, objArr, i, (r2 - i) - 1);
        }
        this.size--;
        this.modCount++;
        return obj;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.List
    public final Object set(int i, Object obj) {
        ensureIsMutable();
        ensureIndexInRange(i);
        Object[] objArr = this.array;
        Object obj2 = objArr[i];
        objArr[i] = obj;
        this.modCount++;
        return obj2;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    private ProtobufArrayList(Object[] objArr, int i, boolean z) {
        super(z);
        this.array = objArr;
        this.size = i;
    }

    @Override // com.google.protobuf.AbstractProtobufList, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean add(Object obj) {
        ensureIsMutable();
        int i = this.size;
        Object[] objArr = this.array;
        if (i == objArr.length) {
            this.array = Arrays.copyOf(objArr, ((i * 3) / 2) + 1);
        }
        Object[] objArr2 = this.array;
        int i2 = this.size;
        this.size = i2 + 1;
        objArr2[i2] = obj;
        this.modCount++;
        return true;
    }
}
