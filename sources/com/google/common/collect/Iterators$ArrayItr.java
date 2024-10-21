package com.google.common.collect;

/* compiled from: PG */
/* loaded from: classes.dex */
final class Iterators$ArrayItr extends UnmodifiableListIterator {
    static final UnmodifiableListIterator EMPTY = new Iterators$ArrayItr(new Object[0]);
    private final Object[] array;

    public Iterators$ArrayItr(Object[] objArr) {
        super(0, 0);
        this.array = objArr;
    }

    @Override // com.google.common.collect.UnmodifiableListIterator
    protected final Object get(int i) {
        return this.array[i];
    }
}
