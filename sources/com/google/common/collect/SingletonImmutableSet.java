package com.google.common.collect;

import java.util.NoSuchElementException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SingletonImmutableSet extends ImmutableSet {
    final transient Object element;

    public SingletonImmutableSet(Object obj) {
        obj.getClass();
        this.element = obj;
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public final ImmutableList asList() {
        return ImmutableList.of(this.element);
    }

    @Override // com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection
    public final boolean contains(Object obj) {
        return this.element.equals(obj);
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final int copyIntoArray(Object[] objArr, int i) {
        objArr[i] = this.element;
        return i + 1;
    }

    @Override // com.google.common.collect.ImmutableSet, java.util.Collection, java.util.Set
    public final int hashCode() {
        return this.element.hashCode();
    }

    @Override // com.google.common.collect.ImmutableCollection
    public final boolean isPartialView() {
        return false;
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: iterator */
    public final UnmodifiableIterator listIterator() {
        final Object obj = this.element;
        return new UnmodifiableIterator(obj) { // from class: com.google.common.collect.Iterators$SingletonIterator
            private boolean done;
            private final Object value;

            {
                this.value = obj;
            }

            @Override // java.util.Iterator
            public final boolean hasNext() {
                if (!this.done) {
                    return true;
                }
                return false;
            }

            @Override // java.util.Iterator
            public final Object next() {
                if (!this.done) {
                    this.done = true;
                    return this.value;
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final int size() {
        return 1;
    }

    @Override // java.util.AbstractCollection
    public final String toString() {
        return "[" + this.element.toString() + "]";
    }

    @Override // com.google.common.collect.ImmutableSet, com.google.common.collect.ImmutableCollection
    public Object writeReplace() {
        return super.writeReplace();
    }
}
