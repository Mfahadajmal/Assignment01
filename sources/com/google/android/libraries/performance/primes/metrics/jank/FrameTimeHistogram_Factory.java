package com.google.android.libraries.performance.primes.metrics.jank;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import dagger.internal.Factory;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FrameTimeHistogram_Factory implements Factory {
    private final Provider clockProvider;

    public FrameTimeHistogram_Factory(Provider provider) {
        this.clockProvider = provider;
    }

    @Override // javax.inject.Provider
    public final FrameTimeHistogram get() {
        return new FrameTimeHistogram((SpannableUtils$NonCopyableTextSpan) this.clockProvider.get());
    }
}
