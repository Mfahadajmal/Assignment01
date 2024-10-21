package com.google.common.collect;

import com.google.common.flogger.context.ContextDataProvider;
import java.util.AbstractSet;
import java.util.Collection;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class Sets$ImprovedAbstractSet extends AbstractSet {
    @Override // java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean removeAll(Collection collection) {
        return ContextDataProvider.removeAllImpl(this, collection);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        collection.getClass();
        return super.retainAll(collection);
    }
}
