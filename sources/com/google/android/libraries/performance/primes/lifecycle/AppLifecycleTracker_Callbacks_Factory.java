package com.google.android.libraries.performance.primes.lifecycle;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.gms.usagereporting.InternalUsageReportingClient;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.performance.primes.metrics.core.MetricDispatcher;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.core.perfetto.PerfettoTrigger;
import com.google.android.libraries.performance.primes.metrics.network.NetworkMetricCollector;
import com.google.android.libraries.performance.primes.metrics.startup.StartupConfigurations;
import com.google.android.libraries.performance.primes.sampling.ApproximateHistogram;
import com.google.android.play.core.splitinstall.SplitInstallInfoProvider;
import com.google.android.play.core.splitinstall.SplitInstallListenerRegistry;
import com.google.android.play.core.splitinstall.SplitInstallModule;
import com.google.android.play.core.splitinstall.SplitInstallModule_ProvideContextFactory;
import com.google.android.play.core.splitinstall.SplitInstallService;
import com.google.android.play.core.splitinstall.SplitInstallSharedPreferences;
import com.google.android.play.core.splitinstall.testing.LocalTestingConfigParser;
import com.google.common.base.Optional;
import com.google.common.base.Ticker;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.google.common.flogger.GoogleLogger;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import googledata.experiments.mobile.primes_android.features.StartupFeature;
import java.io.File;
import java.util.Random;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppLifecycleTracker_Callbacks_Factory implements Factory {
    private final Object AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider;
    private final /* synthetic */ int switching_field;

    public AppLifecycleTracker_Callbacks_Factory(Object obj, int i) {
        this.switching_field = i;
        this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider = obj;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v10, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v17, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v24, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v28, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v47, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v50, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v53, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v59, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v17, types: [javax.inject.Provider, java.lang.Object] */
    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        Object obj;
        Looper looper;
        String string;
        switch (this.switching_field) {
            case 0:
                return new AppLifecycleTracker$Callbacks((MetricDispatcher) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider.get());
            case 1:
                return Long.valueOf(StartupFeature.INSTANCE.get().warmStartType((Context) ((InstanceFactory) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider).instance));
            case 2:
                return new StatsStorage((AppLifecycleTracker$Callbacks) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider.get());
            case 3:
                return new ProcessLifecycleOwner((Context) ((InstanceFactory) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider).instance);
            case 4:
                return new MetricDispatcher(DoubleCheck.lazy((Provider) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider));
            case 5:
                Context context = (Context) ((InstanceFactory) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider).instance;
                PackageManager packageManager = context.getPackageManager();
                String packageName = context.getPackageName();
                try {
                    return packageManager.getPackageInfo(packageName, 0).versionName;
                } catch (PackageManager.NameNotFoundException e) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/core/PrimesCoreMetricDaggerModule", "provideVersionName", 89, "PrimesCoreMetricDaggerModule.java")).log("Failed to get PackageInfo for: %s", packageName);
                    return null;
                }
            case 6:
                return new PerfettoTrigger((Ticker) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider.get());
            case 7:
                if (Build.VERSION.SDK_INT >= 30) {
                    obj = new SingletonImmutableSet((MetricService) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider.get());
                } else {
                    obj = RegularImmutableSet.EMPTY;
                }
                obj.getClass();
                return obj;
            case 8:
                Optional optional = (Optional) ((InstanceFactory) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider).instance;
                if (optional.isPresent()) {
                    looper = (Looper) optional.get();
                } else {
                    HandlerThread handlerThread = new HandlerThread("Primes-Jank", 10);
                    handlerThread.start();
                    looper = handlerThread.getLooper();
                }
                return new Handler(looper);
            case 9:
                return new NetworkMetricCollector(this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider);
            case 10:
                Optional optional2 = (Optional) ((InstanceFactory) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider).instance;
                ImmutableMap.Builder builder = new ImmutableMap.Builder(null, null);
                builder.size = 1;
                return (StartupConfigurations) optional2.or(new StartupConfigurations(1, (Optional) builder.ImmutableMap$Builder$ar$alternatingKeysAndValues, (Optional) builder.ImmutableMap$Builder$ar$duplicateKey));
            case 11:
                BatteryMetricService batteryMetricService = (BatteryMetricService) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider.get();
                batteryMetricService.getClass();
                return batteryMetricService;
            case 12:
                return new SingletonImmutableSet((MetricService) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider.get());
            case 13:
                return new ApproximateHistogram((Random) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider.get());
            case 14:
                return new SplitInstallInfoProvider(((SplitInstallModule_ProvideContextFactory) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider).get());
            case 15:
                File file = (File) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider.get();
                if (file == null) {
                    return null;
                }
                return LocalTestingConfigParser.getLocalTestingConfig(file);
            case 16:
                Context context2 = ((SplitInstallModule_ProvideContextFactory) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider).get();
                try {
                    Bundle bundle = context2.getPackageManager().getApplicationInfo(context2.getPackageName(), BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE).metaData;
                    if (bundle != null && (string = bundle.getString("local_testing_dir")) != null) {
                        return new File(context2.getExternalFilesDir(null), string);
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
                return null;
            case 17:
                SplitInstallListenerRegistry splitInstallListenerRegistry = SplitInstallListenerRegistry.getInstance((Context) ((SplitInstallModule) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider).SplitInstallModule$ar$context);
                splitInstallListenerRegistry.getClass();
                return splitInstallListenerRegistry;
            case 18:
                return new SplitInstallService(((SplitInstallModule_ProvideContextFactory) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider).get());
            case 19:
                return new SplitInstallSharedPreferences(((SplitInstallModule_ProvideContextFactory) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider).get());
            default:
                return new InternalUsageReportingClient(((SplitInstallModule_ProvideContextFactory) this.AppLifecycleTracker_Callbacks_Factory$ar$crashOnBadPrimesConfigurationProvider).get(), new AppLifecycleMonitor((short[]) null, (byte[]) null).build());
        }
    }
}
