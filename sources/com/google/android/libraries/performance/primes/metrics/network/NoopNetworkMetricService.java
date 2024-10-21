package com.google.android.libraries.performance.primes.metrics.network;

import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NoopNetworkMetricService extends NetworkMetricService {
    @Override // com.google.android.libraries.performance.primes.metrics.network.NetworkMetricService
    public final void recordAsFuture$ar$ds(NetworkEvent networkEvent) {
        ListenableFuture listenableFuture = ImmediateFuture.NULL;
    }
}
