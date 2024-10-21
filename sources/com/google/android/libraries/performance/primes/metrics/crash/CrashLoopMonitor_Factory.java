package com.google.android.libraries.performance.primes.metrics.crash;

import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory_Factory;
import com.google.android.libraries.storage.protostore.ProtoDataStoreFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashLoopMonitor_Factory implements Factory {
    private final Provider configsProvider;
    private final Provider deferredExecutorProvider;
    private final Provider earlyCrashLoopMonitorProvider;
    private final Provider flagsProvider;
    private final Provider metricRecorderFactoryProvider;
    private final Provider processNameSupplierProvider;
    private final Provider storageDirSupplierProvider;

    public CrashLoopMonitor_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Provider provider7) {
        this.earlyCrashLoopMonitorProvider = provider;
        this.storageDirSupplierProvider = provider2;
        this.processNameSupplierProvider = provider3;
        this.deferredExecutorProvider = provider4;
        this.configsProvider = provider5;
        this.metricRecorderFactoryProvider = provider6;
        this.flagsProvider = provider7;
    }

    @Override // javax.inject.Provider
    /* renamed from: get$ar$class_merging$eebabe29_0, reason: merged with bridge method [inline-methods] */
    public final ProtoDataStoreFactory get() {
        return new ProtoDataStoreFactory(((EarlyCrashLoopMonitor_Factory) this.earlyCrashLoopMonitorProvider).get(), ((CrashLoopMonitorSuppliers_StorageDirSupplierImpl_Factory) this.storageDirSupplierProvider).get(), ((CrashLoopMonitorSuppliers_ProcessNameSupplierImpl_Factory) this.processNameSupplierProvider).get(), (Executor) this.deferredExecutorProvider.get(), DoubleCheck.lazy(this.configsProvider), ((MetricRecorderFactory_Factory) this.metricRecorderFactoryProvider).get(), this.flagsProvider);
    }
}
