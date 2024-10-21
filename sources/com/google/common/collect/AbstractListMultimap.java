package com.google.common.collect;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
class AbstractListMultimap extends AbstractMapBasedMultimap implements ListMultimap {
    private static final long serialVersionUID = 6588350623831699109L;

    protected AbstractListMultimap(Map map) {
        super(map);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap
    public /* bridge */ /* synthetic */ Collection createCollection() {
        throw null;
    }

    /* JADX WARN: Type inference failed for: r2v1, types: [java.util.List, java.util.Collection] */
    @Override // com.google.common.collect.ListMultimap
    public final List get(Object obj) {
        Collection collection = (Collection) this.map.get(obj);
        if (collection == null) {
            collection = createCollection();
        }
        return wrapCollection(obj, collection);
    }

    @Override // com.google.common.collect.AbstractMapBasedMultimap
    public final Collection wrapCollection(Object obj, Collection collection) {
        return wrapList(obj, (List) collection, null);
    }

    public AbstractListMultimap(Map map, byte[] bArr) {
        this(map);
    }
}
