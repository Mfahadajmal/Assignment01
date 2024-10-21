package com.google.android.libraries.performance.primes.metrics.crash;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashLoopMonitorSuppliers_ProcessNameSupplierImpl_Factory implements Factory {
    private final Provider contextProvider;

    public CrashLoopMonitorSuppliers_ProcessNameSupplierImpl_Factory(Provider provider) {
        this.contextProvider = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get$ar$class_merging, reason: merged with bridge method [inline-methods] */
    public final CrashLoopMonitorSuppliers$StorageDirSupplierImpl get() {
        return new CrashLoopMonitorSuppliers$StorageDirSupplierImpl((Context) ((InstanceFactory) this.contextProvider).instance, 1);
    }
}
