package com.google.android.libraries.performance.primes.transmitter;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface ExperimentsProvider {
    public static final ExperimentsProvider NOOP_PROVIDER = new ExperimentsProvider() { // from class: com.google.android.libraries.performance.primes.transmitter.ExperimentsProvider$$ExternalSyntheticLambda0
        @Override // com.google.android.libraries.performance.primes.transmitter.ExperimentsProvider
        public final ListenableFuture getExperimentIds() {
            int i = ImmutableList.ImmutableList$ar$NoOp;
            return ContextDataProvider.immediateFuture(RegularImmutableList.EMPTY);
        }
    };

    ListenableFuture getExperimentIds();
}
