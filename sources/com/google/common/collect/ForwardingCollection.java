package com.google.common.collect;

import java.util.Collection;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ForwardingCollection extends ForwardingObject implements Collection {
    public boolean add(Object obj) {
        return delegate().add(obj);
    }

    public boolean addAll(Collection collection) {
        return delegate().addAll(collection);
    }

    @Override // java.util.Collection
    public final void clear() {
        delegate().clear();
    }

    @Override // java.util.Collection
    public final boolean contains(Object obj) {
        return delegate().contains(obj);
    }

    @Override // java.util.Collection
    public final boolean containsAll(Collection collection) {
        return delegate().containsAll(collection);
    }

    @Override // com.google.common.collect.ForwardingObject
    protected /* bridge */ /* synthetic */ Object delegate() {
        throw null;
    }

    @Override // com.google.common.collect.ForwardingObject
    protected abstract Collection delegate();

    @Override // java.util.Collection
    public final boolean isEmpty() {
        return delegate().isEmpty();
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        return delegate().iterator();
    }

    @Override // java.util.Collection
    public final boolean remove(Object obj) {
        return delegate().remove(obj);
    }

    @Override // java.util.Collection
    public final boolean removeAll(Collection collection) {
        return delegate().removeAll(collection);
    }

    @Override // java.util.Collection
    public final boolean retainAll(Collection collection) {
        return delegate().retainAll(collection);
    }

    @Override // java.util.Collection
    public final int size() {
        return delegate().size();
    }

    @Override // java.util.Collection
    public final Object[] toArray() {
        return delegate().toArray();
    }

    @Override // java.util.Collection
    public final Object[] toArray(Object[] objArr) {
        return delegate().toArray(objArr);
    }
}
