package com.google.android.libraries.performance.primes.metrics.crash;

import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class EarlyCrashLoopMonitor_Factory implements Factory {
    private final Provider flagsProvider;
    private final Provider processNameSupplierProvider;
    private final Provider storageDirSupplierProvider;

    public EarlyCrashLoopMonitor_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.storageDirSupplierProvider = provider;
        this.processNameSupplierProvider = provider2;
        this.flagsProvider = provider3;
    }

    @Override // javax.inject.Provider
    public final EarlyCrashLoopMonitor get() {
        return new EarlyCrashLoopMonitor(((CrashLoopMonitorSuppliers_StorageDirSupplierImpl_Factory) this.storageDirSupplierProvider).get(), ((CrashLoopMonitorSuppliers_ProcessNameSupplierImpl_Factory) this.processNameSupplierProvider).get(), this.flagsProvider);
    }
}
