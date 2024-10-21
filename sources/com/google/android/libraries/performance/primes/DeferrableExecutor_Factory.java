package com.google.android.libraries.performance.primes;

import android.content.Context;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricService;
import com.google.android.libraries.performance.primes.metrics.jank.ActivityLevelJankMonitor;
import com.google.android.libraries.performance.primes.metrics.trace.NoopTraceMetricService;
import com.google.android.libraries.performance.primes.sampling.ApproximateHistogram;
import com.google.android.libraries.performance.primes.sampling.SamplingStrategy;
import com.google.android.play.core.splitinstall.LazySplitInstallManager;
import com.google.android.play.core.splitinstall.SplitInstallModule_ProvideContextFactory;
import com.google.common.base.Optional;
import com.google.common.base.Suppliers$SupplierOfInstance;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.frameworks.client.data.android.ChannelConfig;
import com.google.frameworks.client.data.android.Transport$TransportConfig;
import com.google.mlkit.logging.schema.ImageInfo;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import io.grpc.internal.RetryingNameResolver;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DeferrableExecutor_Factory implements Factory {
    private final Provider deferAfterMainLooperIdleProvider;
    private final Provider executorProvider;
    private final Provider lifecycleMonitorProvider;
    private final /* synthetic */ int switching_field;

    public DeferrableExecutor_Factory(Provider provider, Provider provider2, Provider provider3, int i) {
        this.switching_field = i;
        this.executorProvider = provider;
        this.lifecycleMonitorProvider = provider2;
        this.deferAfterMainLooperIdleProvider = provider3;
    }

    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        Object obj;
        Object singletonImmutableSet;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                Provider provider = this.executorProvider;
                                return new LazySplitInstallManager(DoubleCheck.lazy(this.lifecycleMonitorProvider), DoubleCheck.lazy(this.deferAfterMainLooperIdleProvider), DoubleCheck.lazy(provider));
                            }
                            return new SamplingStrategy.Factory((Random) this.lifecycleMonitorProvider.get(), (ApproximateHistogram) this.deferAfterMainLooperIdleProvider.get(), (SpannableUtils$NonCopyableTextSpan) this.executorProvider.get());
                        }
                        Optional optional = (Optional) ((InstanceFactory) this.lifecycleMonitorProvider).instance;
                        Optional optional2 = (Optional) ((InstanceFactory) this.executorProvider).instance;
                        if (!optional.isPresent() && !optional2.isPresent()) {
                            singletonImmutableSet = RegularImmutableSet.EMPTY;
                        } else {
                            singletonImmutableSet = new SingletonImmutableSet((MetricService) this.deferAfterMainLooperIdleProvider.get());
                        }
                        singletonImmutableSet.getClass();
                        return singletonImmutableSet;
                    }
                    Optional optional3 = (Optional) ((InstanceFactory) this.lifecycleMonitorProvider).instance;
                    Optional optional4 = (Optional) ((InstanceFactory) this.executorProvider).instance;
                    if (!optional3.isPresent() && !optional4.isPresent()) {
                        obj = new NoopTraceMetricService();
                    } else {
                        obj = (ApplicationExitMetricService) this.deferAfterMainLooperIdleProvider.get();
                    }
                    obj.getClass();
                    return obj;
                }
                return new ActivityLevelJankMonitor(DoubleCheck.lazy(this.executorProvider), (Optional) ((InstanceFactory) this.lifecycleMonitorProvider).instance, (Executor) this.deferAfterMainLooperIdleProvider.get());
            }
            Provider provider2 = this.deferAfterMainLooperIdleProvider;
            Context context = ((SplitInstallModule_ProvideContextFactory) this.executorProvider).get();
            SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan = (SpannableUtils$NonCopyableTextSpan) provider2.get();
            RetryingNameResolver.ResolutionResultListener resolutionResultListener = (RetryingNameResolver.ResolutionResultListener) this.lifecycleMonitorProvider.get();
            ImageInfo.Builder builder = new ImageInfo.Builder(null);
            builder.setNameFormat$ar$ds("Grpc");
            ExecutorService newCachedThreadPool = Executors.newCachedThreadPool(ImageInfo.Builder.doBuild$ar$class_merging(builder));
            Suppliers$SupplierOfInstance suppliers$SupplierOfInstance = new Suppliers$SupplierOfInstance(false);
            ChannelConfig.Builder builder2 = new ChannelConfig.Builder(null);
            builder2.recordNetworkMetricsToPrimes = suppliers$SupplierOfInstance;
            builder2.recordCachingMetricsToPrimes = suppliers$SupplierOfInstance;
            builder2.recordBandwidthMetrics = suppliers$SupplierOfInstance;
            builder2.grpcIdleTimeoutMillis = new Suppliers$SupplierOfInstance(Long.valueOf(TimeUnit.MINUTES.toMillis(30L)));
            builder2.maxMessageSize = 4194304;
            byte b = builder2.set$0;
            builder2.grpcKeepAliveTimeMillis = Long.MAX_VALUE;
            builder2.set$0 = (byte) (3 | b);
            builder2.grpcKeepAliveTimeoutMillis = Transport$TransportConfig.DEFAULT_KEEPALIVE_TIMEOUT_MILLIS;
            builder2.set$0 = (byte) (4 | builder2.set$0);
            builder2.context = context;
            if (spannableUtils$NonCopyableTextSpan != null) {
                builder2.clock$ar$class_merging$83e7e07b_0$ar$class_merging$ar$class_merging = spannableUtils$NonCopyableTextSpan;
                if (resolutionResultListener != null) {
                    builder2.transport$ar$class_merging$b1c384e4_0$ar$class_merging$ar$class_merging$ar$class_merging = resolutionResultListener;
                    if (newCachedThreadPool != null) {
                        builder2.ioExecutor = newCachedThreadPool;
                        builder2.transportExecutor = newCachedThreadPool;
                        builder2.networkExecutor = newCachedThreadPool;
                        ChannelConfig autoBuild = builder2.autoBuild();
                        ContextDataProvider.checkState(true, "If authContextManager is set, networkExecutor must be set.");
                        return autoBuild;
                    }
                    throw new NullPointerException("Null ioExecutor");
                }
                throw new NullPointerException("Null transport");
            }
            throw new NullPointerException("Null clock");
        }
        return new DeferrableExecutor((ListeningScheduledExecutorService) this.executorProvider.get(), (AppLifecycleMonitor) this.lifecycleMonitorProvider.get(), this.deferAfterMainLooperIdleProvider);
    }

    public DeferrableExecutor_Factory(Provider provider, Provider provider2, Provider provider3, int i, byte[] bArr) {
        this.switching_field = i;
        this.executorProvider = provider;
        this.deferAfterMainLooperIdleProvider = provider2;
        this.lifecycleMonitorProvider = provider3;
    }

    public DeferrableExecutor_Factory(Provider provider, Provider provider2, Provider provider3, int i, float[] fArr) {
        this.switching_field = i;
        this.lifecycleMonitorProvider = provider;
        this.deferAfterMainLooperIdleProvider = provider2;
        this.executorProvider = provider3;
    }

    public DeferrableExecutor_Factory(Provider provider, Provider provider2, Provider provider3, int i, int[] iArr) {
        this.switching_field = i;
        this.lifecycleMonitorProvider = provider;
        this.executorProvider = provider2;
        this.deferAfterMainLooperIdleProvider = provider3;
    }
}
