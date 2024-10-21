package com.google.android.libraries.performance.primes.metrics.crash;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashLoopMonitorSuppliers_StorageDirSupplierImpl_Factory implements Factory {
    private final Provider contextProvider;

    public CrashLoopMonitorSuppliers_StorageDirSupplierImpl_Factory(Provider provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    public final CrashLoopMonitorSuppliers$StorageDirSupplierImpl get() {
        return new CrashLoopMonitorSuppliers$StorageDirSupplierImpl((Context) ((InstanceFactory) this.contextProvider).instance, 0);
    }
}
