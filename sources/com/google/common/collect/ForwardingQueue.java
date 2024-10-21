package com.google.common.collect;

import java.util.Collection;
import java.util.Queue;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ForwardingQueue extends ForwardingCollection implements Queue {
    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    protected /* bridge */ /* synthetic */ Collection delegate() {
        throw null;
    }

    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    protected abstract Queue delegate();

    @Override // java.util.Queue
    public final Object element() {
        return delegate().element();
    }

    public boolean offer(Object obj) {
        return delegate().offer(obj);
    }

    @Override // java.util.Queue
    public final Object peek() {
        return delegate().peek();
    }

    @Override // java.util.Queue
    public final Object poll() {
        return delegate().poll();
    }

    @Override // java.util.Queue
    public final Object remove() {
        return delegate().remove();
    }
}
