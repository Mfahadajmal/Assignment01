package com.google.frameworks.client.data.android.auth;

import com.google.frameworks.client.data.android.auth.impl.AsyncSpatulaInterceptor;
import com.google.frameworks.client.data.android.credential.CredentialStrategy;
import com.google.frameworks.client.data.android.interceptor.AsyncClientInterceptor;
import io.grpc.Metadata;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SpatulaStrategy extends CredentialStrategy {
    private final RetryingNameResolver.ResolutionResultListener headerProvider$ar$class_merging$ar$class_merging$ar$class_merging;

    static {
        Metadata.Key.of("X-Goog-Spatula", Metadata.ASCII_STRING_MARSHALLER);
    }

    public SpatulaStrategy(RetryingNameResolver.ResolutionResultListener resolutionResultListener) {
        this.headerProvider$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
    }

    @Override // com.google.frameworks.client.data.android.credential.CredentialStrategy
    public final AsyncClientInterceptor strategyInterceptor() {
        return new AsyncSpatulaInterceptor(this.headerProvider$ar$class_merging$ar$class_merging$ar$class_merging);
    }
}
