package dagger.internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SingleCheck implements Provider {
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider provider;

    private SingleCheck(Provider provider) {
        this.provider = provider;
    }

    public static Provider provider(Provider provider) {
        if (!(provider instanceof SingleCheck) && !(provider instanceof DoubleCheck)) {
            return new SingleCheck(provider);
        }
        return provider;
    }

    @Override // javax.inject.Provider
    public final Object get() {
        Object obj = this.instance;
        if (obj == UNINITIALIZED) {
            Provider provider = this.provider;
            if (provider == null) {
                return this.instance;
            }
            Object obj2 = provider.get();
            this.instance = obj2;
            this.provider = null;
            return obj2;
        }
        return obj;
    }
}
