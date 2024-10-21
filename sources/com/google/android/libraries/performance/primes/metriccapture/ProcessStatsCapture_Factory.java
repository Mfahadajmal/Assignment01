package com.google.android.libraries.performance.primes.metriccapture;

import android.content.Context;
import com.google.android.libraries.performance.primes.ConfigurationsModule_ProvideGlobalConfigurationsFactory;
import com.google.android.libraries.performance.primes.PrimesThreadsConfigurations;
import com.google.android.libraries.performance.primes.foreground.ForegroundStateCapture_Factory;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.android.libraries.performance.primes.metrics.core.MetricStamper;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitMetricService;
import com.google.android.libraries.performance.primes.metrics.jank.DisplayStats;
import com.google.android.play.core.splitinstall.SplitInstallInfoProvider;
import com.google.android.play.core.splitinstall.SplitInstallListenerRegistry;
import com.google.android.play.core.splitinstall.SplitInstallManagerImpl;
import com.google.android.play.core.splitinstall.SplitInstallModule_ProvideContextFactory;
import com.google.android.play.core.splitinstall.SplitInstallService;
import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.android.play.core.splitinstall.testing.FakeSplitInstallManager;
import com.google.common.base.Optional;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import java.io.File;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessStatsCapture_Factory implements Factory {
    private final Provider contextProvider;
    private final Provider foregroundStateCaptureProvider;
    private final Provider oomScoreAdjCaptureProvider;
    private final Provider processImportanceCaptureProvider;
    private final /* synthetic */ int switching_field;

    public ProcessStatsCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, int i) {
        this.switching_field = i;
        this.oomScoreAdjCaptureProvider = provider;
        this.processImportanceCaptureProvider = provider2;
        this.foregroundStateCaptureProvider = provider3;
        this.contextProvider = provider4;
    }

    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        Executor executor;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return new FakeSplitInstallManager(((SplitInstallModule_ProvideContextFactory) this.contextProvider).get(), (File) this.processImportanceCaptureProvider.get(), (SplitInstallInfoProvider) this.oomScoreAdjCaptureProvider.get(), DoubleCheck.lazy(this.foregroundStateCaptureProvider));
                        }
                        Provider provider = this.contextProvider;
                        Provider provider2 = this.oomScoreAdjCaptureProvider;
                        return new SplitInstallManagerImpl((SplitInstallService) provider.get(), (SplitInstallListenerRegistry) provider2.get(), (SplitInstallInfoProvider) this.foregroundStateCaptureProvider.get(), (SplitInstallSharedPreferences) this.processImportanceCaptureProvider.get());
                    }
                    return new ApplicationExitMetricService(null, null);
                }
                return new MetricStamper((Context) ((InstanceFactory) this.contextProvider).instance, ((ConfigurationsModule_ProvideGlobalConfigurationsFactory) this.foregroundStateCaptureProvider).get(), (String) this.processImportanceCaptureProvider.get(), this.oomScoreAdjCaptureProvider);
            }
            PrimesThreadsConfigurations primesThreadsConfigurations = (PrimesThreadsConfigurations) this.processImportanceCaptureProvider.get();
            Optional optional = (Optional) ((InstanceFactory) this.foregroundStateCaptureProvider).instance;
            if (primesThreadsConfigurations.getEnableDeferredTasks() && !optional.isPresent()) {
                executor = (Executor) this.contextProvider.get();
            } else {
                executor = (Executor) this.oomScoreAdjCaptureProvider.get();
            }
            executor.getClass();
            return executor;
        }
        return new ProcessStatsCapture(new DisplayStats(), new BatteryMetricService((char[]) null), ((ForegroundStateCapture_Factory) this.foregroundStateCaptureProvider).get(), (Context) ((InstanceFactory) this.contextProvider).instance);
    }

    public ProcessStatsCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, int i, byte[] bArr) {
        this.switching_field = i;
        this.contextProvider = provider;
        this.oomScoreAdjCaptureProvider = provider2;
        this.processImportanceCaptureProvider = provider3;
        this.foregroundStateCaptureProvider = provider4;
    }

    public ProcessStatsCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, int i, char[] cArr) {
        this.switching_field = i;
        this.contextProvider = provider;
        this.foregroundStateCaptureProvider = provider2;
        this.processImportanceCaptureProvider = provider3;
        this.oomScoreAdjCaptureProvider = provider4;
    }

    public ProcessStatsCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, int i, int[] iArr) {
        this.switching_field = i;
        this.contextProvider = provider;
        this.oomScoreAdjCaptureProvider = provider2;
        this.foregroundStateCaptureProvider = provider3;
        this.processImportanceCaptureProvider = provider4;
    }

    public ProcessStatsCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, int i, short[] sArr) {
        this.switching_field = i;
        this.foregroundStateCaptureProvider = provider;
        this.oomScoreAdjCaptureProvider = provider2;
        this.processImportanceCaptureProvider = provider3;
        this.contextProvider = provider4;
    }

    public ProcessStatsCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, int i, boolean[] zArr) {
        this.switching_field = i;
        this.contextProvider = provider;
        this.processImportanceCaptureProvider = provider2;
        this.oomScoreAdjCaptureProvider = provider3;
        this.foregroundStateCaptureProvider = provider4;
    }
}
