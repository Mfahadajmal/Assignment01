package dagger.internal;

import dagger.Lazy;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class InstanceFactory implements Factory, Lazy {
    public final Object instance;

    private InstanceFactory(Object obj) {
        this.instance = obj;
    }

    public static Factory create(Object obj) {
        obj.getClass();
        return new InstanceFactory(obj);
    }

    @Override // javax.inject.Provider
    public final Object get() {
        return this.instance;
    }
}
