package com.google.frameworks.client.data.android.auth;

import dagger.internal.Factory;
import io.grpc.internal.RetryingNameResolver;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpatulaStrategy_Factory implements Factory {
    private final Provider headerProvider;

    public SpatulaStrategy_Factory(Provider provider) {
        this.headerProvider = provider;
    }

    @Override // javax.inject.Provider
    public final SpatulaStrategy get() {
        return new SpatulaStrategy((RetryingNameResolver.ResolutionResultListener) this.headerProvider.get());
    }
}
