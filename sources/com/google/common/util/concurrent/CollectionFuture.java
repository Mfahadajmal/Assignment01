package com.google.common.util.concurrent;

import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.common.collect.ImmutableCollection;
import com.google.common.flogger.context.ContextDataProvider;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CollectionFuture extends AggregateFuture {
    private List values;

    public CollectionFuture(ImmutableCollection immutableCollection, byte[] bArr) {
        this(immutableCollection);
        init();
    }

    public static /* bridge */ /* synthetic */ Object combine$ar$ds(List list) {
        Object obj;
        ArrayList newArrayListWithCapacity = ContextDataProvider.newArrayListWithCapacity(list.size());
        Iterator it = list.iterator();
        while (it.hasNext()) {
            SplitInstallSharedPreferences splitInstallSharedPreferences = (SplitInstallSharedPreferences) it.next();
            if (splitInstallSharedPreferences != null) {
                obj = splitInstallSharedPreferences.SplitInstallSharedPreferences$ar$context;
            } else {
                obj = null;
            }
            newArrayListWithCapacity.add(obj);
        }
        return DesugarCollections.unmodifiableList(newArrayListWithCapacity);
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public final void collectOneValue(int i, Object obj) {
        List list = this.values;
        if (list != null) {
            list.set(i, new SplitInstallSharedPreferences(obj));
        }
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public final void handleAllCompleted() {
        List list = this.values;
        if (list != null) {
            set(combine$ar$ds(list));
        }
    }

    @Override // com.google.common.util.concurrent.AggregateFuture
    public final void releaseResources$ar$edu(int i) {
        super.releaseResources$ar$edu(i);
        this.values = null;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CollectionFuture(ImmutableCollection immutableCollection) {
        super(immutableCollection, false, true);
        List newArrayListWithCapacity;
        if (immutableCollection.isEmpty()) {
            newArrayListWithCapacity = Collections.emptyList();
        } else {
            newArrayListWithCapacity = ContextDataProvider.newArrayListWithCapacity(immutableCollection.size());
        }
        for (int i = 0; i < immutableCollection.size(); i++) {
            newArrayListWithCapacity.add(null);
        }
        this.values = newArrayListWithCapacity;
    }
}
