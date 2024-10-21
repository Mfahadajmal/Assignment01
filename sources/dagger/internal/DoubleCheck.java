package dagger.internal;

import dagger.Lazy;
import google.internal.feedback.v1.SurveyServiceGrpc;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DoubleCheck implements Provider, Lazy {
    private static final Object UNINITIALIZED = new Object();
    private volatile Object instance = UNINITIALIZED;
    private volatile Provider provider;

    private DoubleCheck(Provider provider) {
        this.provider = provider;
    }

    public static Lazy lazy(Provider provider) {
        return new DoubleCheck(provider);
    }

    public static Provider provider(Provider provider) {
        if (provider instanceof DoubleCheck) {
            return provider;
        }
        return new DoubleCheck(provider);
    }

    @Override // javax.inject.Provider
    public final Object get() {
        Object obj = this.instance;
        Object obj2 = UNINITIALIZED;
        if (obj == obj2) {
            synchronized (this) {
                obj = this.instance;
                if (obj == obj2) {
                    obj = this.provider.get();
                    Object obj3 = this.instance;
                    if (obj3 != obj2 && obj3 != obj) {
                        throw new IllegalStateException("Scoped provider was invoked recursively returning different results: " + obj3 + " & " + obj + ". This is likely due to a circular dependency.");
                    }
                    this.instance = obj;
                    this.provider = null;
                }
            }
        }
        return obj;
    }

    public static Lazy lazy(javax.inject.Provider provider) {
        return lazy(SurveyServiceGrpc.asDaggerProvider(provider));
    }
}
