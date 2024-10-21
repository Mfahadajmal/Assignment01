package com.google.firebase.components;

import com.google.firebase.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Lazy implements Provider {
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider provider;

    public Lazy(Provider provider) {
        this.provider = provider;
    }

    @Override // com.google.firebase.inject.Provider
    public final Object get() {
        Object obj = this.instance;
        Object obj2 = UNINITIALIZED;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.instance;
                if (obj == obj2) {
                    obj = this.provider.get();
                    this.instance = obj;
                    this.provider = null;
                }
            }
        }
        return obj;
    }
}
