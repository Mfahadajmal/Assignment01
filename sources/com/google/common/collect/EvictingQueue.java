package com.google.common.collect;

import com.google.common.flogger.context.ContextDataProvider;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EvictingQueue extends ForwardingQueue implements Serializable {
    private static final long serialVersionUID = 0;
    private final Queue delegate;
    final int maxSize;

    public EvictingQueue(int i) {
        ContextDataProvider.checkArgument(true, "maxSize (%s) must >= 0", i);
        this.delegate = new ArrayDeque(i);
        this.maxSize = i;
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection, java.util.Queue
    public final boolean add(Object obj) {
        obj.getClass();
        if (size() == this.maxSize) {
            this.delegate.remove();
        }
        this.delegate.add(obj);
        return true;
    }

    @Override // com.google.common.collect.ForwardingCollection, java.util.Collection
    public final boolean addAll(Collection collection) {
        boolean z;
        int size = collection.size();
        if (size >= this.maxSize) {
            clear();
            int i = this.maxSize;
            collection.getClass();
            int i2 = size - i;
            if (i2 >= 0) {
                z = true;
            } else {
                z = false;
            }
            ContextDataProvider.checkArgument(z, (Object) "number to skip cannot be negative");
            return ContextDataProvider.addAll(this, new FluentIterable(collection, i2).iterator());
        }
        return ContextDataProvider.addAll(this, collection.iterator());
    }

    @Override // com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    protected final /* synthetic */ Object delegate() {
        return this.delegate;
    }

    @Override // com.google.common.collect.ForwardingQueue, java.util.Queue
    public final boolean offer(Object obj) {
        add(obj);
        return true;
    }

    @Override // com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    protected final /* synthetic */ Collection delegate() {
        return this.delegate;
    }

    @Override // com.google.common.collect.ForwardingQueue, com.google.common.collect.ForwardingCollection, com.google.common.collect.ForwardingObject
    protected final Queue delegate() {
        return this.delegate;
    }
}
