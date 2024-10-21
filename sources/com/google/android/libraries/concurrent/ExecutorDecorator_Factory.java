package com.google.android.libraries.concurrent;

import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.common.base.Optional;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ExecutorDecorator_Factory implements Factory {
    private final Provider internalDecoratorProvider;

    public ExecutorDecorator_Factory(Provider provider) {
        this.internalDecoratorProvider = provider;
    }

    @Override // javax.inject.Provider
    /* renamed from: get$ar$class_merging$6750a917_0$ar$class_merging, reason: merged with bridge method [inline-methods] */
    public final AppLifecycleMonitor get() {
        return new AppLifecycleMonitor((Optional) ((InstanceFactory) this.internalDecoratorProvider).instance);
    }
}
