package dagger.internal;

import google.internal.feedback.v1.SurveyServiceGrpc;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProviderOfLazy implements Provider {
    private final Provider provider;

    private ProviderOfLazy(Provider provider) {
        this.provider = provider;
    }

    @Deprecated
    public static Provider create(javax.inject.Provider provider) {
        return new ProviderOfLazy(SurveyServiceGrpc.asDaggerProvider(provider));
    }

    @Override // javax.inject.Provider
    public final /* bridge */ /* synthetic */ Object get() {
        return DoubleCheck.lazy(this.provider);
    }
}
