package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import j$.util.DesugarCollections;
import j$.util.concurrent.ConcurrentHashMap;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class LazySet implements Provider {
    private volatile Set actualSet = null;
    private volatile Set providers = Collections.newSetFromMap(new ConcurrentHashMap());

    public LazySet(Collection collection) {
        this.providers.addAll(collection);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static LazySet fromCollection(Collection collection) {
        return new LazySet((Set) collection);
    }

    private final synchronized void updateSet() {
        Iterator it = this.providers.iterator();
        while (it.hasNext()) {
            this.actualSet.add(((Provider) it.next()).get());
        }
        this.providers = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final synchronized void add(Provider provider) {
        if (this.actualSet == null) {
            this.providers.add(provider);
        } else {
            this.actualSet.add(provider.get());
        }
    }

    @Override // com.google.firebase.inject.Provider
    public final /* bridge */ /* synthetic */ Object get() {
        if (this.actualSet == null) {
            synchronized (this) {
                if (this.actualSet == null) {
                    this.actualSet = Collections.newSetFromMap(new ConcurrentHashMap());
                    updateSet();
                }
            }
        }
        return DesugarCollections.unmodifiableSet(this.actualSet);
    }
}
