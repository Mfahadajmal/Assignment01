package com.google.common.collect;

import _COROUTINE._BOUNDARY;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class Maps$EntrySet extends Sets$ImprovedAbstractSet {
    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final void clear() {
        map().clear();
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean contains(Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        Object key = entry.getKey();
        Object safeGet = ContextDataProvider.safeGet(map(), key);
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_27(safeGet, entry.getValue())) {
            return false;
        }
        if (safeGet == null && !map().containsKey(key)) {
            return false;
        }
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return map().isEmpty();
    }

    public abstract Map map();

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean remove(Object obj) {
        if (contains(obj) && (obj instanceof Map.Entry)) {
            return map().keySet().remove(((Map.Entry) obj).getKey());
        }
        return false;
    }

    @Override // com.google.common.collect.Sets$ImprovedAbstractSet, java.util.AbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public final boolean removeAll(Collection collection) {
        try {
            collection.getClass();
            return ContextDataProvider.removeAllImpl(this, collection);
        } catch (UnsupportedOperationException unused) {
            return ContextDataProvider.removeAllImpl(this, collection.iterator());
        }
    }

    @Override // com.google.common.collect.Sets$ImprovedAbstractSet, java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean retainAll(Collection collection) {
        try {
            collection.getClass();
            return super.retainAll(collection);
        } catch (UnsupportedOperationException unused) {
            HashSet newHashSetWithExpectedSize = ContextDataProvider.newHashSetWithExpectedSize(collection.size());
            for (Object obj : collection) {
                if (contains(obj) && (obj instanceof Map.Entry)) {
                    newHashSetWithExpectedSize.add(((Map.Entry) obj).getKey());
                }
            }
            return map().keySet().retainAll(newHashSetWithExpectedSize);
        }
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return map().size();
    }
}
