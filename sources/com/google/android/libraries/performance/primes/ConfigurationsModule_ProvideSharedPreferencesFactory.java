package com.google.android.libraries.performance.primes;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.android.libraries.concurrent.ExecutorDecorator_Factory;
import com.google.android.libraries.grpc.primes.PrimesCronetInterceptor;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleMonitor;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricService;
import com.google.android.libraries.performance.primes.metrics.battery.NoopBatteryMetricService;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage;
import com.google.android.libraries.performance.primes.metrics.core.MetricDispatcher;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.jank.JankMetricService;
import com.google.android.libraries.performance.primes.metrics.jank.NoopJankMetricService;
import com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceImpl;
import com.google.android.libraries.performance.primes.metrics.timer.TimerMetricServiceWithTracingImpl;
import com.google.android.libraries.performance.primes.metrics.trace.PrimesTraceDaggerModule_TimerMetricServiceSupportFactory;
import com.google.android.libraries.stitch.util.ThreadUtil;
import com.google.android.play.core.splitinstall.LazySplitInstallManager;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.frameworks.client.data.android.ChannelConfig;
import com.google.frameworks.client.data.android.auth.SpatulaStrategy;
import com.google.frameworks.client.data.android.auth.SpatulaStrategy_Factory;
import com.google.frameworks.client.data.android.credential.CredentialOption;
import com.google.mlkit.logging.schema.OnDeviceFaceMeshCreateLogEvent;
import com.google.protobuf.MapEntryLite$Metadata;
import com.google.search.mdi.aratea.proto.ArateaServiceGrpc;
import com.google.search.mdi.aratea.proto.rpcids.ArateaServiceConfig;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import io.grpc.ClientInterceptor;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.MetadataUtils$HeaderAttachingClientInterceptor;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import javax.inject.Provider;
import org.chromium.net.ExperimentalCronetEngine;
import org.chromium.net.RequestFinishedInfo;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConfigurationsModule_ProvideSharedPreferencesFactory implements Factory {
    private final Object ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider;
    private final Provider sharedPreferencesSupplierProvider;
    private final /* synthetic */ int switching_field;

    public ConfigurationsModule_ProvideSharedPreferencesFactory(Object obj, Provider provider, int i) {
        this.switching_field = i;
        this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider = obj;
        this.sharedPreferencesSupplierProvider = provider;
    }

    /* JADX WARN: Type inference failed for: r0v100, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v109, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v112, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v23, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v45, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v55, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v63, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v74, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v82, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v9, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v92, types: [javax.inject.Provider, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v12, types: [com.google.android.libraries.concurrent.InternalExecutorDecorator, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r1v29, types: [javax.inject.Provider, java.lang.Object] */
    @Override // javax.inject.Provider
    public final /* synthetic */ Object get() {
        Object obj;
        Object noopBatteryMetricService;
        Object singletonImmutableSet;
        Object obj2;
        Object obj3;
        Object noopJankMetricService;
        Object obj4;
        int i = 0;
        switch (this.switching_field) {
            case 0:
                SharedPreferences sharedPreferences = (SharedPreferences) ((Supplier) ((Optional) ((InstanceFactory) this.sharedPreferencesSupplierProvider).instance).or((Object) new ConfigurationsModule$$ExternalSyntheticLambda0((Context) ((InstanceFactory) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider).instance, i))).get();
                sharedPreferences.getClass();
                return sharedPreferences;
            case 1:
                MapEntryLite$Metadata mapEntryLite$Metadata = (MapEntryLite$Metadata) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get();
                SpatulaStrategy spatulaStrategy = ((SpatulaStrategy_Factory) this.sharedPreferencesSupplierProvider).get();
                ArateaServiceGrpc.ArateaServiceFutureStub arateaServiceFutureStub = (ArateaServiceGrpc.ArateaServiceFutureStub) ArateaServiceGrpc.ArateaServiceFutureStub.newStub(new ArateaServiceGrpc.AnonymousClass3(0), mapEntryLite$Metadata.get(ArateaServiceConfig.INSTANCE));
                AbstractStub build = arateaServiceFutureStub.build(arateaServiceFutureStub.channel, arateaServiceFutureStub.callOptions.withOption(CredentialOption.KEY, spatulaStrategy));
                ClientInterceptor[] clientInterceptorArr = new ClientInterceptor[1];
                PrimesCronetInterceptor primesCronetInterceptor = PrimesCronetInterceptor.instance;
                if (primesCronetInterceptor == null) {
                    synchronized (PrimesCronetInterceptor.class) {
                        primesCronetInterceptor = PrimesCronetInterceptor.instance;
                        if (primesCronetInterceptor == null) {
                            primesCronetInterceptor = new PrimesCronetInterceptor(MetadataUtils$HeaderAttachingClientInterceptor.getPrimesInterceptor$ar$class_merging());
                            PrimesCronetInterceptor.instance = primesCronetInterceptor;
                        }
                    }
                }
                clientInterceptorArr[0] = primesCronetInterceptor;
                return build.build(OnDeviceFaceMeshCreateLogEvent.intercept(build.channel, clientInterceptorArr), build.callOptions);
            case 2:
                int i2 = Primes.Primes$ar$NoOp;
                if (!ThreadUtil.isMainThread()) {
                    MetricDispatcher metricDispatcher = (MetricDispatcher) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get();
                    if (!MetricDispatcher.isRobolectric()) {
                        ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/CrashOnBadPrimesConfiguration", "observedBackgroundInitialization", 29, "CrashOnBadPrimesConfiguration.java")).log("Primes init triggered from background in package: %s", metricDispatcher.MetricDispatcher$ar$metricTransmittersSupplier);
                        if (!metricDispatcher.appIsAllowlistedForOutOfOrderLifecycleEvents()) {
                            throw new IllegalStateException(String.format("Primes init triggered from background in package: %s", metricDispatcher.MetricDispatcher$ar$metricTransmittersSupplier));
                        }
                    }
                }
                return new Primes(((PrimesApiImpl_Factory) this.sharedPreferencesSupplierProvider).get());
            case 3:
                PrimesThreadsConfigurations primesThreadsConfigurations = (PrimesThreadsConfigurations) this.sharedPreferencesSupplierProvider.get();
                AppLifecycleMonitor appLifecycleMonitor = ((ExecutorDecorator_Factory) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider).get();
                int primesMetricExecutorPoolSize = primesThreadsConfigurations.getPrimesMetricExecutorPoolSize();
                ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(primesMetricExecutorPoolSize, new PrimesExecutorsModule$PrimesThreadFactory(primesThreadsConfigurations.getPrimesMetricExecutorPriority()), new RejectedExecutionHandler() { // from class: com.google.android.libraries.performance.primes.PrimesExecutorsModule$DefaultRejectedExecutionHandler
                    @Override // java.util.concurrent.RejectedExecutionHandler
                    public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                        ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/PrimesExecutorsModule$DefaultRejectedExecutionHandler", "rejectedExecution", 76, "PrimesExecutorsModule.java")).log("Service rejected execution of %s", runnable);
                    }
                });
                scheduledThreadPoolExecutor.setMaximumPoolSize(primesMetricExecutorPoolSize);
                ListeningScheduledExecutorService listeningDecorator = ContextDataProvider.listeningDecorator((ScheduledExecutorService) scheduledThreadPoolExecutor);
                ?? r1 = appLifecycleMonitor.AppLifecycleMonitor$ar$tracker;
                if (r1 != 0) {
                    listeningDecorator = r1.decorate$ar$ds();
                }
                listeningDecorator.getClass();
                return listeningDecorator;
            case 4:
                return new AppLifecycleMonitor((Context) ((InstanceFactory) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider).instance, (StatsStorage) this.sharedPreferencesSupplierProvider.get());
            case 5:
                if (((Optional) ((InstanceFactory) this.sharedPreferencesSupplierProvider).instance).isPresent()) {
                    obj = new SingletonImmutableSet((MetricService) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get());
                } else {
                    obj = RegularImmutableSet.EMPTY;
                }
                obj.getClass();
                return obj;
            case 6:
                if (((Optional) ((InstanceFactory) this.sharedPreferencesSupplierProvider).instance).isPresent()) {
                    noopBatteryMetricService = (BatteryMetricService) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get();
                } else {
                    noopBatteryMetricService = new NoopBatteryMetricService();
                }
                noopBatteryMetricService.getClass();
                return noopBatteryMetricService;
            case 7:
                if (!((Optional) ((InstanceFactory) this.sharedPreferencesSupplierProvider).instance).isPresent()) {
                    singletonImmutableSet = RegularImmutableSet.EMPTY;
                } else {
                    singletonImmutableSet = new SingletonImmutableSet((MetricService) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get());
                }
                singletonImmutableSet.getClass();
                return singletonImmutableSet;
            case 8:
                if (((Optional) ((InstanceFactory) this.sharedPreferencesSupplierProvider).instance).isPresent()) {
                    obj2 = new SingletonImmutableSet((MetricService) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get());
                } else {
                    obj2 = RegularImmutableSet.EMPTY;
                }
                obj2.getClass();
                return obj2;
            case 9:
                if (((Optional) ((InstanceFactory) this.sharedPreferencesSupplierProvider).instance).isPresent()) {
                    obj3 = new SingletonImmutableSet((MetricService) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get());
                } else {
                    obj3 = RegularImmutableSet.EMPTY;
                }
                obj3.getClass();
                return obj3;
            case 10:
                if (((Optional) ((InstanceFactory) this.sharedPreferencesSupplierProvider).instance).isPresent()) {
                    noopJankMetricService = (JankMetricService) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get();
                } else {
                    noopJankMetricService = new NoopJankMetricService();
                }
                noopJankMetricService.getClass();
                return noopJankMetricService;
            case 11:
                if (((Optional) ((InstanceFactory) this.sharedPreferencesSupplierProvider).instance).isPresent()) {
                    obj4 = new SingletonImmutableSet((MetricService) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get());
                } else {
                    obj4 = RegularImmutableSet.EMPTY;
                }
                obj4.getClass();
                return obj4;
            case 12:
                return new TimerMetricServiceWithTracingImpl((TimerMetricServiceImpl) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get(), ((PrimesTraceDaggerModule_TimerMetricServiceSupportFactory) this.sharedPreferencesSupplierProvider).get());
            case 13:
                LazySplitInstallManager lazySplitInstallManager = (LazySplitInstallManager) this.sharedPreferencesSupplierProvider.get();
                lazySplitInstallManager.getClass();
                return lazySplitInstallManager;
            case 14:
                return new MapEntryLite$Metadata((ChannelConfig) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get(), ImmutableList.copyOf((Collection) ((InstanceFactory) this.sharedPreferencesSupplierProvider).instance));
            default:
                ExperimentalCronetEngine experimentalCronetEngine = (ExperimentalCronetEngine) this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider.get();
                Iterator it = ((Set) ((InstanceFactory) this.sharedPreferencesSupplierProvider).instance).iterator();
                while (it.hasNext()) {
                    experimentalCronetEngine.addRequestFinishedListener((RequestFinishedInfo.Listener) it.next());
                }
                experimentalCronetEngine.getClass();
                return experimentalCronetEngine;
        }
    }

    public ConfigurationsModule_ProvideSharedPreferencesFactory(Provider provider, Provider provider2, int i, char[] cArr) {
        this.switching_field = i;
        this.sharedPreferencesSupplierProvider = provider;
        this.ConfigurationsModule_ProvideSharedPreferencesFactory$ar$contextProvider = provider2;
    }
}
