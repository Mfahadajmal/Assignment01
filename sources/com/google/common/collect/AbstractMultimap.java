package com.google.common.collect;

import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
abstract class AbstractMultimap<K, V> implements Multimap<K, V> {
    private transient Map asMap;
    private transient Set keySet;

    @Override // com.google.common.collect.Multimap
    public Map asMap() {
        Map map = this.asMap;
        if (map == null) {
            Map createAsMap = createAsMap();
            this.asMap = createAsMap;
            return createAsMap;
        }
        return map;
    }

    public abstract Map createAsMap();

    public abstract Set createKeySet();

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Multimap)) {
            return false;
        }
        return asMap().equals(((Multimap) obj).asMap());
    }

    public final int hashCode() {
        return asMap().hashCode();
    }

    @Override // com.google.common.collect.Multimap
    public Set keySet() {
        Set set = this.keySet;
        if (set == null) {
            Set createKeySet = createKeySet();
            this.keySet = createKeySet;
            return createKeySet;
        }
        return set;
    }

    @Override // com.google.common.collect.Multimap
    public void put$ar$ds(Object obj, Object obj2) {
        throw null;
    }

    public final String toString() {
        return asMap().toString();
    }
}
