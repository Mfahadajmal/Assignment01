package com.google.android.libraries.performance.primes.transmitter;

import com.google.common.base.Absent;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ZwiebackCookieOverrideProvider {
    public static final ZwiebackCookieOverrideProvider NOOP_PROVIDER = new ZwiebackCookieOverrideProvider() { // from class: com.google.android.libraries.performance.primes.transmitter.ZwiebackCookieOverrideProvider$$ExternalSyntheticLambda0
        @Override // com.google.android.libraries.performance.primes.transmitter.ZwiebackCookieOverrideProvider
        public final ListenableFuture getZwiebackCookieOverride() {
            return ContextDataProvider.immediateFuture(Absent.INSTANCE);
        }
    };

    ListenableFuture getZwiebackCookieOverride();
}
