package com.google.common.collect;

import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.common.flogger.context.ContextDataProvider;
import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ImmutableMultimap<K, V> extends BaseImmutableMultimap<K, V> implements Serializable {
    private static final long serialVersionUID = 0;
    final transient ImmutableMap<K, ? extends ImmutableCollection<V>> map;
    final transient int size = 0;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class FieldSettersHolder {
        static final SplitInstallSharedPreferences MAP_FIELD_SETTER$ar$class_merging$ar$class_merging$ar$class_merging = ContextDataProvider.getFieldSetter$ar$class_merging$ar$class_merging$ar$class_merging(ImmutableMultimap.class, "map");
        static final SplitInstallSharedPreferences SIZE_FIELD_SETTER$ar$class_merging$ar$class_merging$ar$class_merging = ContextDataProvider.getFieldSetter$ar$class_merging$ar$class_merging$ar$class_merging(ImmutableMultimap.class, "size");
    }

    public ImmutableMultimap(ImmutableMap immutableMap, int i) {
        this.map = immutableMap;
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public final ImmutableMap asMap() {
        return this.map;
    }

    @Override // com.google.common.collect.AbstractMultimap
    public final Map createAsMap() {
        throw new AssertionError("should never be called");
    }

    @Override // com.google.common.collect.AbstractMultimap
    public final Set createKeySet() {
        throw new AssertionError("unreachable");
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public final /* bridge */ /* synthetic */ Set keySet() {
        throw null;
    }

    @Override // com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    @Deprecated
    public final void put$ar$ds(Object obj, Object obj2) {
        throw null;
    }
}
