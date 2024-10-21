package com.google.android.libraries.performance.primes.metrics.memory;

import android.content.Context;
import com.google.android.libraries.performance.primes.foreground.ForegroundStateCapture_Factory;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory_Factory;
import com.google.android.libraries.performance.primes.metrics.startup.StartupMetricServiceImpl;
import com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceImpl;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySamplerFactory_Factory;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import io.grpc.okhttp.internal.OptionalMethod;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MemoryUsageCapture_Factory implements Factory {
    private final Provider applicationProvider;
    private final Provider configsProvider;
    private final Provider foregroundStateCaptureProvider;
    private final Provider memoizeConfigsProvider;
    private final Provider processStatsCaptureProvider;
    private final Provider readCorrectProcStatusProvider;
    private final /* synthetic */ int switching_field;

    public MemoryUsageCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, int i) {
        this.switching_field = i;
        this.configsProvider = provider;
        this.applicationProvider = provider2;
        this.readCorrectProcStatusProvider = provider3;
        this.memoizeConfigsProvider = provider4;
        this.processStatsCaptureProvider = provider5;
        this.foregroundStateCaptureProvider = provider6;
    }

    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    Provider provider = this.applicationProvider;
                    MetricRecorderFactory metricRecorderFactory = ((MetricRecorderFactory_Factory) this.foregroundStateCaptureProvider).get();
                    Executor executor = (Executor) provider.get();
                    Provider provider2 = this.memoizeConfigsProvider;
                    Provider provider3 = this.configsProvider;
                    return new TimerMetricServiceImpl(metricRecorderFactory, executor, DoubleCheck.lazy(this.readCorrectProcStatusProvider), DoubleCheck.lazy(provider3), this.processStatsCaptureProvider, ((ProbabilitySamplerFactory_Factory) provider2).get());
                }
                return new StartupMetricServiceImpl((ForegroundTracker) this.foregroundStateCaptureProvider.get(), this.readCorrectProcStatusProvider, DoubleCheck.lazy(this.memoizeConfigsProvider));
            }
            ForegroundTracker foregroundTracker = (ForegroundTracker) this.memoizeConfigsProvider.get();
            ListeningScheduledExecutorService listeningScheduledExecutorService = (ListeningScheduledExecutorService) this.foregroundStateCaptureProvider.get();
            Executor executor2 = (Executor) this.configsProvider.get();
            Provider provider4 = this.processStatsCaptureProvider;
            Lazy lazy = DoubleCheck.lazy(this.applicationProvider);
            DoubleCheck.lazy(provider4);
            return new MemoryMetricMonitor(foregroundTracker, listeningScheduledExecutorService, executor2, lazy);
        }
        Context context = (Context) ((InstanceFactory) this.applicationProvider).instance;
        ProcessStatsCapture processStatsCapture = (ProcessStatsCapture) this.processStatsCaptureProvider.get();
        OptionalMethod optionalMethod = ((ForegroundStateCapture_Factory) this.foregroundStateCaptureProvider).get();
        Provider provider5 = this.memoizeConfigsProvider;
        return new MemoryUsageCapture(this.configsProvider, context, this.readCorrectProcStatusProvider, provider5, processStatsCapture, optionalMethod);
    }

    public MemoryUsageCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, int i, byte[] bArr) {
        this.switching_field = i;
        this.memoizeConfigsProvider = provider;
        this.foregroundStateCaptureProvider = provider2;
        this.configsProvider = provider3;
        this.applicationProvider = provider4;
        this.processStatsCaptureProvider = provider5;
        this.readCorrectProcStatusProvider = provider6;
    }

    public MemoryUsageCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, int i, char[] cArr) {
        this.switching_field = i;
        this.foregroundStateCaptureProvider = provider;
        this.processStatsCaptureProvider = provider2;
        this.applicationProvider = provider3;
        this.configsProvider = provider4;
        this.readCorrectProcStatusProvider = provider5;
        this.memoizeConfigsProvider = provider6;
    }

    public MemoryUsageCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, int i, short[] sArr) {
        this.switching_field = i;
        this.foregroundStateCaptureProvider = provider;
        this.applicationProvider = provider2;
        this.readCorrectProcStatusProvider = provider3;
        this.configsProvider = provider4;
        this.processStatsCaptureProvider = provider5;
        this.memoizeConfigsProvider = provider6;
    }
}
