package com.google.common.collect;

import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EmptyImmutableSetMultimap extends ImmutableSetMultimap {
    public static final EmptyImmutableSetMultimap INSTANCE = new EmptyImmutableSetMultimap();
    private static final long serialVersionUID = 0;

    private EmptyImmutableSetMultimap() {
        super(RegularImmutableMap.EMPTY, 0);
    }

    private Object readResolve() {
        return INSTANCE;
    }

    @Override // com.google.common.collect.ImmutableMultimap, com.google.common.collect.AbstractMultimap, com.google.common.collect.Multimap
    public final /* synthetic */ Map asMap() {
        return this.map;
    }
}
