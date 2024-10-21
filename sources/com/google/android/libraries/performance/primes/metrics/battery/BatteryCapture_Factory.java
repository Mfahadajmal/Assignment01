package com.google.android.libraries.performance.primes.metrics.battery;

import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.lifecycle.ProcessLifecycleOwner;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory_Factory;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.performance.primes.metrics.startup.StartupMetricRecordingService;
import com.google.android.libraries.performance.primes.metrics.trace.PrimesTraceDaggerModule_TimerMetricServiceSupportFactory;
import com.google.common.base.Optional;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import kotlinx.coroutines.scheduling.WorkQueue;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryCapture_Factory implements Factory {
    private final Provider applicationContextProvider;
    private final Provider batteryConfigurationsProvider;
    private final Provider clockProvider;
    private final /* synthetic */ int switching_field;
    private final Provider systemHealthCaptureProvider;
    private final Provider versionNameProvider;

    public BatteryCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, int i) {
        this.switching_field = i;
        this.versionNameProvider = provider;
        this.systemHealthCaptureProvider = provider2;
        this.clockProvider = provider3;
        this.batteryConfigurationsProvider = provider4;
        this.applicationContextProvider = provider5;
    }

    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        Object obj;
        MetricService metricService;
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        Provider provider = this.batteryConfigurationsProvider;
                        Provider provider2 = this.clockProvider;
                        Provider provider3 = this.versionNameProvider;
                        return new WorkQueue(this.applicationContextProvider, this.systemHealthCaptureProvider, provider3, provider2, provider);
                    }
                    Optional optional = (Optional) ((InstanceFactory) this.systemHealthCaptureProvider).instance;
                    Optional optional2 = (Optional) ((InstanceFactory) this.clockProvider).instance;
                    boolean isPresent = optional.isPresent();
                    Optional optional3 = ((PrimesTraceDaggerModule_TimerMetricServiceSupportFactory) this.batteryConfigurationsProvider).get();
                    if (isPresent) {
                        if (optional2.isPresent() && optional3.isPresent()) {
                            metricService = (MetricService) this.versionNameProvider.get();
                        } else {
                            metricService = (MetricService) this.applicationContextProvider.get();
                        }
                        obj = new SingletonImmutableSet(metricService);
                    } else {
                        obj = RegularImmutableSet.EMPTY;
                    }
                    obj.getClass();
                    return obj;
                }
                Provider provider4 = this.clockProvider;
                MetricRecorderFactory metricRecorderFactory = ((MetricRecorderFactory_Factory) this.systemHealthCaptureProvider).get();
                return new StartupMetricRecordingService(metricRecorderFactory, (Executor) this.batteryConfigurationsProvider.get(), DoubleCheck.lazy(this.applicationContextProvider), this.versionNameProvider);
            }
            return new ForegroundTracker((AppLifecycleMonitor) this.batteryConfigurationsProvider.get(), (ProcessLifecycleOwner) this.systemHealthCaptureProvider.get(), new BatteryMetricService((char[]) null), new BatteryMetricService((char[]) null), this.versionNameProvider);
        }
        String str = (String) this.versionNameProvider.get();
        Provider provider5 = this.clockProvider;
        WindowTrackerFactory windowTrackerFactory = ((SystemHealthCapture_Factory) this.systemHealthCaptureProvider).get();
        SpannableUtils$NonCopyableTextSpan spannableUtils$NonCopyableTextSpan = (SpannableUtils$NonCopyableTextSpan) provider5.get();
        return new BatteryCapture(str, windowTrackerFactory, spannableUtils$NonCopyableTextSpan, this.batteryConfigurationsProvider);
    }

    public BatteryCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, int i, byte[] bArr) {
        this.switching_field = i;
        this.batteryConfigurationsProvider = provider;
        this.systemHealthCaptureProvider = provider2;
        this.applicationContextProvider = provider3;
        this.clockProvider = provider4;
        this.versionNameProvider = provider5;
    }

    public BatteryCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, int i, char[] cArr) {
        this.switching_field = i;
        this.systemHealthCaptureProvider = provider;
        this.clockProvider = provider2;
        this.batteryConfigurationsProvider = provider3;
        this.applicationContextProvider = provider4;
        this.versionNameProvider = provider5;
    }

    public BatteryCapture_Factory(Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, int i, int[] iArr) {
        this.switching_field = i;
        this.applicationContextProvider = provider;
        this.systemHealthCaptureProvider = provider2;
        this.versionNameProvider = provider3;
        this.clockProvider = provider4;
        this.batteryConfigurationsProvider = provider5;
    }
}
