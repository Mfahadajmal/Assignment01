package com.google.android.libraries.performance.primes.transmitter;

import com.google.common.base.Absent;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface AccountProvider {
    public static final AccountProvider NOOP_PROVIDER = new AccountProvider() { // from class: com.google.android.libraries.performance.primes.transmitter.AccountProvider$$ExternalSyntheticLambda0
        @Override // com.google.android.libraries.performance.primes.transmitter.AccountProvider
        public final ListenableFuture getAccountName() {
            return ContextDataProvider.immediateFuture(Absent.INSTANCE);
        }
    };

    ListenableFuture getAccountName();
}
