package com.google.android.libraries.performance.primes;

import androidx.lifecycle.Lifecycle;
import com.google.android.libraries.mdi.download.debug.common.filegroups.MddDebugListFragmentUiProviderImpl;
import com.google.android.libraries.performance.primes.metrics.trace.TikTokTraceConfigurations;
import com.google.android.libraries.performance.primes.metrics.trace.TraceMetricServiceImpl;
import com.google.android.libraries.performance.primes.transmitter.MetricTransmitter;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.SingletonImmutableSet;
import com.google.frameworks.client.data.android.DebugInterceptorProvider;
import com.google.frameworks.client.data.android.credential.CredentialStrategy;
import com.google.frameworks.client.data.android.impl.RpcIdInterceptor;
import com.google.frameworks.client.data.android.metrics.MetricsRecordingInterceptor;
import com.google.protobuf.MapEntryLite$Metadata;
import java.util.Iterator;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class PrimesConfigurations$Builder$$ExternalSyntheticLambda0 implements Provider {
    public final /* synthetic */ Object PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ PrimesConfigurations$Builder$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v11, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v18, types: [java.lang.Object, dagger.Lazy] */
    /* JADX WARN: Type inference failed for: r0v24, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v27, types: [java.lang.Object, dagger.Lazy] */
    /* JADX WARN: Type inference failed for: r0v47, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v11, types: [java.util.List, java.lang.Object] */
    @Override // javax.inject.Provider
    public final Object get() {
        int i;
        switch (this.switching_field) {
            case 0:
                return new SingletonImmutableSet((MetricTransmitter) this.PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0.get());
            case 1:
                return Boolean.valueOf(((MddDebugListFragmentUiProviderImpl) this.PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0).fragment.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED));
            case 2:
                ?? r0 = this.PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0;
                if (((Optional) r0.get()).isPresent()) {
                    return null;
                }
                return null;
            case 3:
                return false;
            case 4:
                return (TraceMetricServiceImpl) this.PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0.get();
            case 5:
                ?? r02 = this.PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0;
                if (((TikTokTraceConfigurations) r02.get()).isEnabled()) {
                    i = ((TikTokTraceConfigurations) r02.get()).getRateLimitPerSecond();
                } else {
                    i = 10;
                }
                return Integer.valueOf(i);
            case 6:
                return ImmutableList.of((Object) ((CredentialStrategy) this.PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0).strategyInterceptor());
            case 7:
                ImmutableList.Builder builder = new ImmutableList.Builder();
                Iterator it = ((MapEntryLite$Metadata) this.PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0).MapEntryLite$Metadata$ar$valueType.iterator();
                while (it.hasNext()) {
                    builder.add$ar$ds$4f674a09_0(((DebugInterceptorProvider) it.next()).get());
                }
                return builder.build();
            case 8:
                return ImmutableList.of((Object) new RpcIdInterceptor(this.PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0, 0));
            case 9:
                return ImmutableList.of((Object) new RpcIdInterceptor(((MapEntryLite$Metadata) this.PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0).MapEntryLite$Metadata$ar$keyType, 1), (Object) new MetricsRecordingInterceptor());
            default:
                return ImmutableList.of(this.PrimesConfigurations$Builder$$ExternalSyntheticLambda0$ar$f$0.get());
        }
    }
}
