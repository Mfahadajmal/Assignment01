package com.google.android.libraries.performance.primes.foreground;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import io.grpc.okhttp.internal.OptionalMethod;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ForegroundStateCapture_Factory implements Factory {
    private final Provider contextProvider;
    private final Provider trackerProvider;
    private final Provider useDebouncedForegroundSignalsProvider;

    public ForegroundStateCapture_Factory(Provider provider, Provider provider2, Provider provider3) {
        this.contextProvider = provider;
        this.trackerProvider = provider2;
        this.useDebouncedForegroundSignalsProvider = provider3;
    }

    @Override // javax.inject.Provider
    /* renamed from: get$ar$class_merging$1fb8477_0$ar$class_merging, reason: merged with bridge method [inline-methods] */
    public final OptionalMethod get() {
        return new OptionalMethod((Context) ((InstanceFactory) this.contextProvider).instance, (ForegroundTracker) this.trackerProvider.get(), this.useDebouncedForegroundSignalsProvider);
    }
}
