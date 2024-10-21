package com.google.common.collect;

import com.google.common.flogger.context.ContextDataProvider;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Iterators$PeekingImpl implements Iterator {
    public boolean hasPeeked;
    public final Iterator iterator;
    public Object peekedElement;

    public Iterators$PeekingImpl(Iterator it) {
        it.getClass();
        this.iterator = it;
    }

    @Override // java.util.Iterator
    public final boolean hasNext() {
        if (!this.hasPeeked && !this.iterator.hasNext()) {
            return false;
        }
        return true;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (!this.hasPeeked) {
            return this.iterator.next();
        }
        Object obj = this.peekedElement;
        this.hasPeeked = false;
        this.peekedElement = null;
        return obj;
    }

    @Override // java.util.Iterator
    public final void remove() {
        ContextDataProvider.checkState(!this.hasPeeked, "Can't remove after you've peeked at next");
        this.iterator.remove();
    }
}
