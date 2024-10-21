package com.google.android.libraries.performance.primes.metrics.crash.applicationexit;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ApplicationExitInfoCaptureImpl_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider enableCollectingAnrDiagnosticsProvider;

    public ApplicationExitInfoCaptureImpl_Factory(Provider provider, Provider provider2) {
        this.applicationProvider = provider;
        this.enableCollectingAnrDiagnosticsProvider = provider2;
    }

    @Override // javax.inject.Provider
    public final ApplicationExitInfoCaptureImpl get() {
        return new ApplicationExitInfoCaptureImpl((Context) ((InstanceFactory) this.applicationProvider).instance, this.enableCollectingAnrDiagnosticsProvider);
    }
}
