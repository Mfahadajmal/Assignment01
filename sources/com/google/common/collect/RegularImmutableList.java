package com.google.common.collect;

import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RegularImmutableList extends ImmutableList {
    public static final ImmutableList EMPTY = new RegularImmutableList(new Object[0], 0);
    final transient Object[] array;
    public final transient int size;

    public RegularImmutableList(Object[] objArr, int i) {
        this.array = objArr;
        this.size = i;
    }

    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public final int copyIntoArray(Object[] objArr, int i) {
        System.arraycopy(this.array, 0, objArr, i, this.size);
        return i + this.size;
    }

    @Override // java.util.List
    public final Object get(int i) {
        ContextDataProvider.checkElementIndex$ar$ds(i, this.size);
        Object obj = this.array[i];
        obj.getClass();
        return obj;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final Object[] internalArray() {
        return this.array;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int internalArrayEnd() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int internalArrayStart() {
        return 0;
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean isPartialView() {
        return false;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.size;
    }

    @Override // com.google.common.collect.ImmutableList, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return super.writeReplace();
    }
}
