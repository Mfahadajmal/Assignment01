package com.google.frameworks.client.data.android.auth;

import dagger.internal.Factory;
import io.grpc.internal.RetryingNameResolver;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpatulaHeaderDaggerModule_SpatulaProviderFactory implements Factory {
    private final Provider proxyClientProvider;

    public SpatulaHeaderDaggerModule_SpatulaProviderFactory(Provider provider) {
        this.proxyClientProvider = provider;
    }

    @Override // javax.inject.Provider
    public final /* bridge */ /* synthetic */ Object get() {
        return new RetryingNameResolver.ResolutionResultListener(this.proxyClientProvider, null);
    }
}
