package com.google.android.libraries.performance.primes;

import android.content.Context;
import com.google.android.accessibility.talkback.compositor.EventFeedback;
import com.google.android.libraries.concurrent.ExecutorDecorator_Factory;
import com.google.android.libraries.performance.primes.Shutdown_Factory;
import com.google.android.libraries.performance.primes.flags.impl.PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory;
import com.google.android.libraries.performance.primes.foreground.ForegroundConfigurationModule_DebouncedForegroundSignalAdapterFactoryFactory$InstanceHolder;
import com.google.android.libraries.performance.primes.foreground.ForegroundConfigurationModule_ProcessImportanceForegroundSignalAdapterFactoryFactory$InstanceHolder;
import com.google.android.libraries.performance.primes.foreground.ForegroundConfigurationModule_UseDebouncedForegroundSignalsInternalFactory;
import com.google.android.libraries.performance.primes.foreground.ForegroundStateCapture_Factory;
import com.google.android.libraries.performance.primes.initialization.PrimesInitializerImpl_Factory;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleTracker_Callbacks_Factory;
import com.google.android.libraries.performance.primes.metriccapture.ProcessImportanceCapture_Factory$InstanceHolder;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture_Factory;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryCapture_Factory;
import com.google.android.libraries.performance.primes.metrics.battery.BatteryMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.battery.StatsStorage_Factory;
import com.google.android.libraries.performance.primes.metrics.battery.SystemHealthCapture_Factory;
import com.google.android.libraries.performance.primes.metrics.core.MetricRecorderFactory_Factory;
import com.google.android.libraries.performance.primes.metrics.cpuprofiling.CpuProfilingServiceScheduler_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.CrashLoopMonitorSuppliers_ProcessNameSupplierImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.CrashLoopMonitorSuppliers_StorageDirSupplierImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.CrashLoopMonitor_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.CrashMetricFactory_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.EarlyCrashLoopMonitor_Factory;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitInfoCaptureImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.jank.FrameMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.jank.FrameTimeHistogram_Factory;
import com.google.android.libraries.performance.primes.metrics.jank.JankObserverFactory_Factory;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory_Factory;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryUsageCapture_Factory;
import com.google.android.libraries.performance.primes.metrics.memory.OomScoreAdjCapture_Factory$InstanceHolder;
import com.google.android.libraries.performance.primes.metrics.network.NetworkMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.network.PrimesNetworkDaggerModule_ProvideNetworkMetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.storage.StorageMetricServiceImpl_Factory;
import com.google.android.libraries.performance.primes.metrics.timer.PrimesTimerDaggerModule_MetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.timer.PrimesTimerDaggerModule_ProvideCustomDurationMetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.trace.PrimesTraceDaggerModule_TimerMetricServiceSupportFactory;
import com.google.android.libraries.performance.primes.persistent.PersistentStorage_Factory;
import com.google.android.libraries.performance.primes.sampling.PersistentRateLimiting_Factory;
import com.google.android.libraries.performance.primes.sampling.ProbabilitySamplerFactory_Factory;
import com.google.android.libraries.performance.primes.sampling.ProdSamplingModule_EnableSamplingFactory$InstanceHolder;
import com.google.common.base.Absent;
import com.google.common.base.Supplier;
import com.google.frameworks.client.data.android.cronet.CronetConfigurationModule_ProvidesCronetEngineFactory;
import dagger.internal.DelegateFactory;
import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import dagger.internal.InstanceFactory;
import dagger.internal.Provider;
import dagger.internal.SetFactory;
import dagger.internal.SingleCheck;
import j$.util.Optional;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DaggerProdInternalComponent {
    public static final Provider ABSENT_GUAVA_OPTIONAL_PROVIDER = InstanceFactory.create(Absent.INSTANCE);

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Builder {
        public Object DaggerProdInternalComponent$Builder$ar$setApplicationContext;
        public Object DaggerProdInternalComponent$Builder$ar$setApplicationExitConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setCrashConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setDisableAutomaticCrashInitToken;
        public Object DaggerProdInternalComponent$Builder$ar$setGlobalConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setMemoryConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier;
        public Object DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setNetworkConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setSharedPreferencesSupplier;
        public Object DaggerProdInternalComponent$Builder$ar$setStorageConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setThreadsConfigurations;
        public Object DaggerProdInternalComponent$Builder$ar$setTikTokTraceConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider;
        public Object DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider;

        public Builder() {
        }

        public final EventFeedback build() {
            Object obj;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            Object obj6;
            Object obj7;
            Object obj8;
            Object obj9;
            Object obj10;
            Object obj11;
            Object obj12;
            Object obj13;
            Object obj14;
            Object obj15;
            Object obj16;
            Object obj17;
            Object obj18 = this.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider;
            if (obj18 != null && (obj = this.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider) != null && (obj2 = this.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider) != null && (obj3 = this.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider) != null && (obj4 = this.DaggerProdInternalComponent$Builder$ar$setCrashConfigurationsProvider) != null && (obj5 = this.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier) != null && (obj6 = this.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider) != null && (obj7 = this.DaggerProdInternalComponent$Builder$ar$setNetworkConfigurationsProvider) != null && (obj8 = this.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider) != null && (obj9 = this.DaggerProdInternalComponent$Builder$ar$setMemoryConfigurationsProvider) != null && (obj10 = this.DaggerProdInternalComponent$Builder$ar$setThreadsConfigurations) != null && (obj11 = this.DaggerProdInternalComponent$Builder$ar$setDisableAutomaticCrashInitToken) != null && (obj12 = this.DaggerProdInternalComponent$Builder$ar$setGlobalConfigurationsProvider) != null && (obj13 = this.DaggerProdInternalComponent$Builder$ar$setApplicationExitConfigurationsProvider) != null && (obj14 = this.DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider) != null && (obj15 = this.DaggerProdInternalComponent$Builder$ar$setTikTokTraceConfigurationsProvider) != null && (obj16 = this.DaggerProdInternalComponent$Builder$ar$setSharedPreferencesSupplier) != null && (obj17 = this.DaggerProdInternalComponent$Builder$ar$setStorageConfigurationsProvider) != null) {
                return new EventFeedback((Optional) this.DaggerProdInternalComponent$Builder$ar$setApplicationContext, (Integer) obj18, (Boolean) obj, (Boolean) obj2, (Boolean) obj3, (Boolean) obj4, (Integer) obj5, (Boolean) obj6, (Boolean) obj7, (Boolean) obj8, (Boolean) obj9, (Double) obj10, (Boolean) obj11, (Boolean) obj12, (Boolean) obj13, (Integer) obj14, (Integer) obj15, (Double) obj16, (Double) obj17);
            }
            StringBuilder sb = new StringBuilder();
            if (this.DaggerProdInternalComponent$Builder$ar$setTraceConfigurationsProvider == null) {
                sb.append(" queueMode");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setBatteryConfigurationsProvider == null) {
                sb.append(" forceFeedbackEvenIfAudioPlaybackActive");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setCpuProfilingConfigurationsProvider == null) {
                sb.append(" forceFeedbackEvenIfMicrophoneActive");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setDebugMemoryConfigurationsProvider == null) {
                sb.append(" forceFeedbackEvenIfSsbActive");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setCrashConfigurationsProvider == null) {
                sb.append(" forceFeedbackEvenIfPhoneCallActive");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setMetricTransmittersSupplier == null) {
                sb.append(" ttsClearQueueGroup");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setMonitorAllActivitiesProvider == null) {
                sb.append(" ttsInterruptSameGroup");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setNetworkConfigurationsProvider == null) {
                sb.append(" ttsSkipDuplicate");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setJankConfigurationsProvider == null) {
                sb.append(" ttsAddToHistory");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setMemoryConfigurationsProvider == null) {
                sb.append(" ttsForceFeedback");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setThreadsConfigurations == null) {
                sb.append(" ttsPitch");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setDisableAutomaticCrashInitToken == null) {
                sb.append(" preventDeviceSleep");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setGlobalConfigurationsProvider == null) {
                sb.append(" refreshSourceNode");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setApplicationExitConfigurationsProvider == null) {
                sb.append(" advanceContinuousReading");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setTimerConfigurationsProvider == null) {
                sb.append(" haptic");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setTikTokTraceConfigurationsProvider == null) {
                sb.append(" earcon");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setSharedPreferencesSupplier == null) {
                sb.append(" earconRate");
            }
            if (this.DaggerProdInternalComponent$Builder$ar$setStorageConfigurationsProvider == null) {
                sb.append(" earconVolume");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }

        public final void setTtsPitch$ar$ds(double d) {
            this.DaggerProdInternalComponent$Builder$ar$setThreadsConfigurations = Double.valueOf(d);
        }

        public Builder(byte[] bArr, byte[] bArr2) {
            this();
            this.DaggerProdInternalComponent$Builder$ar$setApplicationContext = Optional.empty();
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class PresentGuavaOptionalProviderProvider implements Provider {
        private final Provider delegate;

        public PresentGuavaOptionalProviderProvider(Provider provider) {
            provider.getClass();
            this.delegate = provider;
        }

        @Override // javax.inject.Provider
        public final com.google.common.base.Optional get() {
            return com.google.common.base.Optional.of(this.delegate);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ProdInternalComponentImpl {
        private Provider activityLevelJankMonitorProvider;
        private Provider appExitCollectionEnabledProvider;
        private Provider appExitReasonsToReportProvider;
        private Provider appLifecycleMonitorProvider;
        private Provider appLifecycleTrackerProvider;
        private Provider applicationExitInfoCaptureImplProvider;
        private Provider applicationExitMetricServiceImplProvider;
        private Provider applicationExitMetricServiceProvider;
        private Provider batteryCaptureProvider;
        private Provider batteryMetricServiceImplProvider;
        private Provider batterySamplingParametersProvider;
        private Provider batteryServiceProvider;
        private Provider callbacksProvider;
        private Provider computeMaxAcceptedFrameTimeFromWindowProvider;
        private Provider cpuProfilingServiceProvider;
        private Provider cpuProfilingServiceProvider2;
        private Provider cpuProfilingServiceSchedulerProvider;
        private Provider cpuprofilingSamplingParametersProvider;
        private Provider crashLoopMonitorFlagsProvider;
        private Provider crashLoopMonitorProvider;
        private Provider crashMetricFactoryProvider;
        private Provider crashMetricServiceImplProvider;
        private Provider crashOnBadPrimesConfigurationProvider;
        private Provider crashServiceProvider;
        private Provider crashedTiktokTraceConfigsProvider;
        private Provider debugMemoryMetricServiceImplProvider;
        private Provider defaultToUnifiedFormatForTiktokTracesProvider;
        private Provider deferAfterMainLooperIdleOptionalOfBooleanProvider;
        private Provider deferrableExecutorProvider;
        private Provider disableDeferredInitializationForDebugOptionalOfObjectProvider;
        private Provider disableSamplingForDebugOptionalOfObjectProvider;
        private Provider earlyCrashLoopMonitorProvider;
        private Provider enableActiveTraceCollectionForCrashesProvider;
        private Provider enableCollectingAnrDiagnosticsProvider;
        private Provider enableSafeFormatArgsAsStringsProvider;
        private Provider enableStartupBaselineDiscardingProvider;
        private Provider enableUnifiedInitOptionalOfBooleanProvider;
        private Provider executorDecoratorProvider;
        private Provider factoryProvider;
        private Provider firstDrawTypeProvider;
        private Provider foregroundStateCaptureProvider;
        private Provider foregroundTrackerProvider;
        private Provider frameMetricServiceImplProvider;
        private Provider frameTimeHistogramProvider;
        private Provider handlerForFrameMetricsProvider;
        private Provider jankObserverFactoryProvider;
        private Provider jankPerfettoConfigurationsProvider;
        private Provider jankSamplingParametersProvider;
        private Provider jankServiceProvider;
        private Provider lightweightExecutorOptionalOfLooperProvider;
        private Provider memoizeConfigsProvider;
        private Provider memoryMetricMonitorProvider;
        private Provider memoryMetricServiceImplProvider;
        private Provider memorySamplingParametersProvider;
        private Provider memoryUsageCaptureProvider;
        private Provider metricDispatcherProvider;
        private Provider metricRecorderFactoryProvider;
        private Provider metricServiceProvider;
        private Provider metricServiceProvider2;
        private Provider metricServiceProvider3;
        private Provider metricServiceProvider4;
        private Provider metricServiceProvider5;
        private Provider metricStamperProvider;
        private Provider networkMetricCollectorProvider;
        private Provider networkMetricServiceImplProvider;
        private Provider networkMetricServiceProvider;
        private Provider networkSamplingParametersProvider;
        private Provider optionalOfClockProvider;
        private Provider optionalOfInteractionContextProvider;
        private Provider optionalOfInternalExecutorDecoratorProvider;
        private Provider optionalOfPrimesActiveTraceProvider;
        private Provider optionalOfProviderOfCrashMetricServiceProvider;
        private Provider optionalOfProviderOfMemoryMetricServiceProvider;
        private Provider optionalOfProviderOfNativeCrashHandlerProvider;
        private Provider optionalOfRecentLogsProvider;
        private Provider optionalOfStartupConfigurationsProvider;
        private Provider perfettoTriggerProvider;
        private Provider periodicMemoryCollectionPeriodMsProvider;
        private Provider persistentRateLimitingProvider;
        private Provider persistentStorageProvider;
        private Provider primesApiImplProvider;
        private Provider primesInitializerImplProvider;
        private Provider probabilitySamplerFactoryProvider;
        private Provider processLifecycleOwnerProvider;
        private Provider processNameSupplierImplProvider;
        private Provider processStatsCaptureProvider;
        private Provider provideApplicationExitConfigurationsProvider;
        private Provider provideBatteryConfigurationsProvider;
        private Provider provideClockProvider;
        private Provider provideCpuProfilingConfigurationsProvider;
        private Provider provideCrashConfigurationsProvider;
        private Provider provideCustomDurationMetricServiceProvider;
        private Provider provideDebugMemoryConfigurationsProvider;
        private Provider provideDeferrableExecutorProvider;
        private Provider provideGlobalConfigurationsProvider;
        private Provider provideHistogramProvider;
        private Provider provideJankConfigurationsProvider;
        private Provider provideListeningScheduledExecutorServiceProvider;
        private Provider provideMemoryConfigurationsProvider;
        private Provider provideMetricTransmittersProvider;
        private Provider provideNetworkConfigurationsProvider;
        private Provider provideNetworkMetricServiceProvider;
        public Provider providePrimesProvider;
        private Provider provideSharedPreferencesProvider;
        private Provider provideStartupConfigurationsProvider;
        private Provider provideStorageConfigurationsProvider;
        private Provider provideThreadConfigurationsProvider;
        private Provider provideTikTokTraceConfigurationsProvider;
        private Provider provideTimerConfigurationsProvider;
        private Provider provideTraceConfigurationsProvider;
        private Provider provideVersionNameProvider;
        private Provider randomProvider;
        private Provider readCorrectProcStatusProvider;
        private Provider recordingTimeoutsProvider;
        private Provider samplerFactoryProvider;
        private Provider setApplicationContextProvider;
        private Provider setApplicationExitConfigurationsProvider;
        private Provider setBatteryConfigurationsProvider;
        private Provider setCpuProfilingConfigurationsProvider;
        private Provider setCrashConfigurationsProvider;
        private Provider setDebugMemoryConfigurationsProvider;
        private Provider setDisableAutomaticCrashInitTokenProvider;
        private Provider setGlobalConfigurationsProvider;
        private Provider setJankConfigurationsProvider;
        private Provider setMemoryConfigurationsProvider;
        private Provider setMetricTransmittersSupplierProvider;
        private Provider setMonitorAllActivitiesProvider;
        private Provider setNetworkConfigurationsProvider;
        private Provider setOfMetricServiceProvider;
        private Provider setOfMetricTransmitterProvider;
        private Provider setSharedPreferencesSupplierProvider;
        private Provider setStorageConfigurationsProvider;
        private Provider setThreadsConfigurationsProvider;
        private Provider setTikTokTraceConfigurationsProvider;
        private Provider setTimerConfigurationsProvider;
        private Provider setTraceConfigurationsProvider;
        private Provider shutdownProvider;
        private Provider startupMetricRecordingServiceProvider;
        private Provider startupMetricServiceImplProvider;
        private Provider startupSamplingParametersProvider;
        private Provider statsStorageProvider;
        private Provider storageDirSupplierImplProvider;
        private Provider storageMetricServiceImplProvider;
        private Provider storageMetricServiceProvider;
        private Provider storageSamplingParametersProvider;
        private Provider systemHealthCaptureProvider;
        private Provider tickerProvider;
        private Provider timerMetricServiceImplProvider;
        private Provider timerMetricServiceSupportProvider;
        private Provider timerMetricServiceWithTracingImplProvider;
        private Provider timerSamplingParametersProvider;
        private Provider timerServiceProvider;
        private Provider traceMetricServiceImplProvider;
        private Provider traceSamplingParametersProvider;
        private Provider traceServiceProvider;
        private Provider useDebouncedForegroundSignalsInternalProvider;
        private Provider useDebouncedForegroundSignalsOptionalOfBooleanProvider;
        private Provider warmStartTypeProvider;
        private Provider windowTrackerFactoryProvider;

        public ProdInternalComponentImpl(Context context, Supplier supplier, com.google.common.base.Optional optional, com.google.common.base.Optional optional2, com.google.common.base.Optional optional3, com.google.common.base.Optional optional4, com.google.common.base.Optional optional5, com.google.common.base.Optional optional6, com.google.common.base.Optional optional7, com.google.common.base.Optional optional8, com.google.common.base.Optional optional9, com.google.common.base.Optional optional10, com.google.common.base.Optional optional11, com.google.common.base.Optional optional12, com.google.common.base.Optional optional13, com.google.common.base.Optional optional14, com.google.common.base.Optional optional15, com.google.common.base.Optional optional16, com.google.common.base.Optional optional17) {
            Factory create = InstanceFactory.create(optional16);
            this.setThreadsConfigurationsProvider = create;
            this.provideThreadConfigurationsProvider = DoubleCheck.provider(new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(create, 7));
            Provider provider = DaggerProdInternalComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
            this.optionalOfInternalExecutorDecoratorProvider = provider;
            ExecutorDecorator_Factory executorDecorator_Factory = new ExecutorDecorator_Factory(provider);
            this.executorDecoratorProvider = executorDecorator_Factory;
            this.provideListeningScheduledExecutorServiceProvider = DoubleCheck.provider(new ConfigurationsModule_ProvideSharedPreferencesFactory(this.provideThreadConfigurationsProvider, executorDecorator_Factory, 3, null));
            this.shutdownProvider = DoubleCheck.provider(Shutdown_Factory.InstanceHolder.INSTANCE);
            Factory create2 = InstanceFactory.create(supplier);
            this.setMetricTransmittersSupplierProvider = create2;
            this.provideMetricTransmittersProvider = DoubleCheck.provider(new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(create2, 15));
            Factory factory = SetFactory.EMPTY_FACTORY;
            SetFactory.Builder builder = new SetFactory.Builder(0, 1);
            builder.addCollectionProvider$ar$ds(this.provideMetricTransmittersProvider);
            SetFactory build = builder.build();
            this.setOfMetricTransmitterProvider = build;
            this.metricDispatcherProvider = SingleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(build, 4));
            this.setApplicationContextProvider = InstanceFactory.create(context);
            Factory create3 = InstanceFactory.create(optional3);
            this.setGlobalConfigurationsProvider = create3;
            this.provideGlobalConfigurationsProvider = new ConfigurationsModule_ProvideGlobalConfigurationsFactory(create3);
            Provider provider2 = this.setApplicationContextProvider;
            this.provideVersionNameProvider = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(provider2, 5));
            Provider provider3 = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(provider2, 15));
            this.readCorrectProcStatusProvider = provider3;
            Provider provider4 = this.setApplicationContextProvider;
            this.metricStamperProvider = DoubleCheck.provider(new ProcessStatsCapture_Factory((javax.inject.Provider) provider4, (javax.inject.Provider) this.provideGlobalConfigurationsProvider, (javax.inject.Provider) this.provideVersionNameProvider, (javax.inject.Provider) provider3, 2, (char[]) null));
            Provider provider5 = SingleCheck.provider(new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(provider4, 11));
            this.crashOnBadPrimesConfigurationProvider = provider5;
            Provider provider6 = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(provider5, 0));
            this.callbacksProvider = provider6;
            Provider provider7 = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(provider6, 2));
            this.appLifecycleTrackerProvider = provider7;
            this.appLifecycleMonitorProvider = DoubleCheck.provider(new ConfigurationsModule_ProvideSharedPreferencesFactory(this.setApplicationContextProvider, provider7, 4));
            Provider provider8 = DaggerProdInternalComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
            this.deferAfterMainLooperIdleOptionalOfBooleanProvider = provider8;
            Provider provider9 = this.provideListeningScheduledExecutorServiceProvider;
            Provider provider10 = DoubleCheck.provider(new DeferrableExecutor_Factory(provider9, this.appLifecycleMonitorProvider, provider8, 0));
            this.deferrableExecutorProvider = provider10;
            this.disableDeferredInitializationForDebugOptionalOfObjectProvider = provider8;
            this.provideDeferrableExecutorProvider = DoubleCheck.provider(new ProcessStatsCapture_Factory((javax.inject.Provider) provider10, (javax.inject.Provider) provider9, (javax.inject.Provider) this.provideThreadConfigurationsProvider, (javax.inject.Provider) provider8, 1, (byte[]) null));
            this.optionalOfClockProvider = provider8;
            Provider provider11 = SingleCheck.provider(new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(provider8, 13));
            this.provideClockProvider = provider11;
            Provider provider12 = SingleCheck.provider(new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(provider11, 12));
            this.randomProvider = provider12;
            Provider provider13 = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(provider12, 13));
            this.provideHistogramProvider = provider13;
            this.factoryProvider = new DeferrableExecutor_Factory(this.randomProvider, provider13, this.provideClockProvider, 5, (float[]) null);
            this.disableSamplingForDebugOptionalOfObjectProvider = DaggerProdInternalComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
            Provider provider14 = this.setApplicationContextProvider;
            this.samplerFactoryProvider = new BatteryCapture_Factory(provider14, this.provideDeferrableExecutorProvider, this.factoryProvider, ProdSamplingModule_EnableSamplingFactory$InstanceHolder.INSTANCE$ar$class_merging$20409b9b_0, this.disableSamplingForDebugOptionalOfObjectProvider, 4, (int[]) null);
            Provider provider15 = DaggerProdInternalComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
            this.optionalOfRecentLogsProvider = provider15;
            this.optionalOfInteractionContextProvider = provider15;
            this.enableSafeFormatArgsAsStringsProvider = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(provider14, 6));
            this.optionalOfPrimesActiveTraceProvider = DaggerProdInternalComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
            Provider provider16 = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 4));
            this.enableActiveTraceCollectionForCrashesProvider = provider16;
            this.metricRecorderFactoryProvider = new MetricRecorderFactory_Factory(this.metricDispatcherProvider, this.metricStamperProvider, this.shutdownProvider, this.samplerFactoryProvider, this.provideGlobalConfigurationsProvider, this.optionalOfRecentLogsProvider, this.optionalOfInteractionContextProvider, this.enableSafeFormatArgsAsStringsProvider, this.optionalOfPrimesActiveTraceProvider, provider16);
            Provider provider17 = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 5));
            this.enableCollectingAnrDiagnosticsProvider = provider17;
            this.applicationExitInfoCaptureImplProvider = new ApplicationExitInfoCaptureImpl_Factory(this.setApplicationContextProvider, provider17);
            Factory create4 = InstanceFactory.create(optional14);
            this.setSharedPreferencesSupplierProvider = create4;
            this.provideSharedPreferencesProvider = DoubleCheck.provider(new ConfigurationsModule_ProvideSharedPreferencesFactory(this.setApplicationContextProvider, create4, 0));
            Factory create5 = InstanceFactory.create(optional6);
            this.setApplicationExitConfigurationsProvider = create5;
            this.provideApplicationExitConfigurationsProvider = new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(create5, 0);
            this.appExitCollectionEnabledProvider = SingleCheck.provider(new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(this.setApplicationContextProvider, 17));
            Provider provider18 = SingleCheck.provider(new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(this.setApplicationContextProvider, 18));
            this.appExitReasonsToReportProvider = provider18;
            Provider provider19 = DoubleCheck.provider(new CronetConfigurationModule_ProvidesCronetEngineFactory(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.provideDeferrableExecutorProvider, this.applicationExitInfoCaptureImplProvider, this.provideSharedPreferencesProvider, this.provideApplicationExitConfigurationsProvider, this.appExitCollectionEnabledProvider, provider18, 1, null));
            this.applicationExitMetricServiceImplProvider = provider19;
            this.applicationExitMetricServiceProvider = new AppLifecycleTracker_Callbacks_Factory(provider19, 7);
            this.setBatteryConfigurationsProvider = InstanceFactory.create(optional12);
            this.processLifecycleOwnerProvider = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(this.setApplicationContextProvider, 3));
            Provider provider20 = DaggerProdInternalComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
            this.useDebouncedForegroundSignalsOptionalOfBooleanProvider = provider20;
            this.useDebouncedForegroundSignalsInternalProvider = new ForegroundConfigurationModule_UseDebouncedForegroundSignalsInternalFactory(provider20);
            this.foregroundTrackerProvider = DoubleCheck.provider(new BatteryCapture_Factory((javax.inject.Provider) this.appLifecycleMonitorProvider, (javax.inject.Provider) this.processLifecycleOwnerProvider, (javax.inject.Provider) ForegroundConfigurationModule_DebouncedForegroundSignalAdapterFactoryFactory$InstanceHolder.INSTANCE$ar$class_merging$b7090a01_0, (javax.inject.Provider) ForegroundConfigurationModule_ProcessImportanceForegroundSignalAdapterFactoryFactory$InstanceHolder.INSTANCE$ar$class_merging$3d11ae4b_0, (javax.inject.Provider) this.useDebouncedForegroundSignalsInternalProvider, 1, (byte[]) null));
            this.provideBatteryConfigurationsProvider = new ConfigurationsModule_ProvideBatteryConfigurationsFactory(this.setBatteryConfigurationsProvider);
            Provider provider21 = this.setApplicationContextProvider;
            PersistentStorage_Factory persistentStorage_Factory = new PersistentStorage_Factory(provider21, this.provideSharedPreferencesProvider);
            this.persistentStorageProvider = persistentStorage_Factory;
            this.statsStorageProvider = new StatsStorage_Factory(persistentStorage_Factory);
            SystemHealthCapture_Factory systemHealthCapture_Factory = new SystemHealthCapture_Factory(provider21);
            this.systemHealthCaptureProvider = systemHealthCapture_Factory;
            this.batteryCaptureProvider = DoubleCheck.provider(new BatteryCapture_Factory(this.provideVersionNameProvider, systemHealthCapture_Factory, this.provideClockProvider, this.provideBatteryConfigurationsProvider, provider21, 0));
            Provider provider22 = SingleCheck.provider(new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(provider21, 19));
            this.batterySamplingParametersProvider = provider22;
            Provider provider23 = DoubleCheck.provider(new BatteryMetricServiceImpl_Factory(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.appLifecycleMonitorProvider, this.foregroundTrackerProvider, this.provideListeningScheduledExecutorServiceProvider, this.provideBatteryConfigurationsProvider, this.statsStorageProvider, this.batteryCaptureProvider, provider22, this.provideDeferrableExecutorProvider));
            this.batteryMetricServiceImplProvider = provider23;
            this.batteryServiceProvider = new ConfigurationsModule_ProvideSharedPreferencesFactory(this.setBatteryConfigurationsProvider, provider23, 5, null);
            Factory create6 = InstanceFactory.create(optional9);
            this.setJankConfigurationsProvider = create6;
            this.provideJankConfigurationsProvider = new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(create6, 5);
            this.frameMetricServiceImplProvider = new DelegateFactory();
            Factory create7 = InstanceFactory.create(optional15);
            this.setMonitorAllActivitiesProvider = create7;
            this.activityLevelJankMonitorProvider = DoubleCheck.provider(new DeferrableExecutor_Factory(this.frameMetricServiceImplProvider, create7, this.provideDeferrableExecutorProvider, 2));
            this.frameTimeHistogramProvider = new FrameTimeHistogram_Factory(this.provideClockProvider);
            this.jankSamplingParametersProvider = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 10));
            Provider provider24 = SingleCheck.provider(new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(this.provideClockProvider, 14));
            this.tickerProvider = provider24;
            this.perfettoTriggerProvider = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(provider24, 6));
            Provider provider25 = DaggerProdInternalComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
            this.lightweightExecutorOptionalOfLooperProvider = provider25;
            Provider provider26 = DoubleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(provider25, 8));
            this.handlerForFrameMetricsProvider = provider26;
            this.windowTrackerFactoryProvider = new WindowTrackerFactory_Factory(provider26, this.provideListeningScheduledExecutorServiceProvider);
            this.computeMaxAcceptedFrameTimeFromWindowProvider = SingleCheck.provider(new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(this.setApplicationContextProvider, 20));
            this.jankPerfettoConfigurationsProvider = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 9));
            Provider provider27 = this.frameMetricServiceImplProvider;
            JankObserverFactory_Factory jankObserverFactory_Factory = new JankObserverFactory_Factory(provider27, this.handlerForFrameMetricsProvider);
            this.jankObserverFactoryProvider = jankObserverFactory_Factory;
            Provider provider28 = DoubleCheck.provider(new FrameMetricServiceImpl_Factory(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.appLifecycleMonitorProvider, this.provideJankConfigurationsProvider, this.activityLevelJankMonitorProvider, this.frameTimeHistogramProvider, this.jankSamplingParametersProvider, this.provideDeferrableExecutorProvider, this.perfettoTriggerProvider, this.windowTrackerFactoryProvider, this.computeMaxAcceptedFrameTimeFromWindowProvider, this.jankPerfettoConfigurationsProvider, jankObserverFactory_Factory));
            DelegateFactory delegateFactory = (DelegateFactory) provider27;
            if (delegateFactory.delegate == null) {
                delegateFactory.delegate = provider28;
                this.jankServiceProvider = new ConfigurationsModule_ProvideSharedPreferencesFactory(this.setJankConfigurationsProvider, this.frameMetricServiceImplProvider, 9, null);
                Factory create8 = InstanceFactory.create(optional5);
                this.setCrashConfigurationsProvider = create8;
                this.provideCrashConfigurationsProvider = new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(create8, 3);
                this.optionalOfProviderOfNativeCrashHandlerProvider = DaggerProdInternalComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
                this.probabilitySamplerFactoryProvider = new ProbabilitySamplerFactory_Factory(this.randomProvider);
                this.recordingTimeoutsProvider = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 16));
                this.crashedTiktokTraceConfigsProvider = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 2));
                this.crashLoopMonitorFlagsProvider = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 0));
                Provider provider29 = this.setApplicationContextProvider;
                this.storageDirSupplierImplProvider = new CrashLoopMonitorSuppliers_StorageDirSupplierImpl_Factory(provider29);
                CrashLoopMonitorSuppliers_ProcessNameSupplierImpl_Factory crashLoopMonitorSuppliers_ProcessNameSupplierImpl_Factory = new CrashLoopMonitorSuppliers_ProcessNameSupplierImpl_Factory(provider29);
                this.processNameSupplierImplProvider = crashLoopMonitorSuppliers_ProcessNameSupplierImpl_Factory;
                Provider provider30 = this.storageDirSupplierImplProvider;
                Provider provider31 = this.crashLoopMonitorFlagsProvider;
                EarlyCrashLoopMonitor_Factory earlyCrashLoopMonitor_Factory = new EarlyCrashLoopMonitor_Factory(provider30, crashLoopMonitorSuppliers_ProcessNameSupplierImpl_Factory, provider31);
                this.earlyCrashLoopMonitorProvider = earlyCrashLoopMonitor_Factory;
                this.crashLoopMonitorProvider = new CrashLoopMonitor_Factory(earlyCrashLoopMonitor_Factory, provider30, crashLoopMonitorSuppliers_ProcessNameSupplierImpl_Factory, this.provideDeferrableExecutorProvider, this.provideCrashConfigurationsProvider, this.metricRecorderFactoryProvider, provider31);
                this.foregroundStateCaptureProvider = new ForegroundStateCapture_Factory(provider29, this.foregroundTrackerProvider, this.useDebouncedForegroundSignalsInternalProvider);
                Provider provider32 = SingleCheck.provider(new ProcessStatsCapture_Factory(OomScoreAdjCapture_Factory$InstanceHolder.INSTANCE$ar$class_merging$7182b1a0_0, ProcessImportanceCapture_Factory$InstanceHolder.INSTANCE$ar$class_merging$e7ce35e5_0, this.foregroundStateCaptureProvider, provider29, 0));
                this.processStatsCaptureProvider = provider32;
                CrashMetricFactory_Factory crashMetricFactory_Factory = new CrashMetricFactory_Factory(provider32, SetFactory.EMPTY_FACTORY);
                this.crashMetricFactoryProvider = crashMetricFactory_Factory;
                Provider provider33 = DoubleCheck.provider(new MemoryMetricServiceImpl_Factory(this.metricRecorderFactoryProvider, this.provideDeferrableExecutorProvider, this.provideCrashConfigurationsProvider, this.optionalOfProviderOfNativeCrashHandlerProvider, this.appLifecycleMonitorProvider, this.foregroundTrackerProvider, this.probabilitySamplerFactoryProvider, this.recordingTimeoutsProvider, this.crashedTiktokTraceConfigsProvider, this.crashLoopMonitorFlagsProvider, this.crashLoopMonitorProvider, crashMetricFactory_Factory, 1, null));
                this.crashMetricServiceImplProvider = provider33;
                this.crashServiceProvider = new ConfigurationsModule_ProvideSharedPreferencesFactory(this.setCrashConfigurationsProvider, provider33, 8, null);
                Factory create9 = InstanceFactory.create(optional7);
                this.setNetworkConfigurationsProvider = create9;
                ConfigurationsModule_ProvideNetworkConfigurationsFactory configurationsModule_ProvideNetworkConfigurationsFactory = new ConfigurationsModule_ProvideNetworkConfigurationsFactory(create9);
                this.provideNetworkConfigurationsProvider = configurationsModule_ProvideNetworkConfigurationsFactory;
                this.networkMetricCollectorProvider = new AppLifecycleTracker_Callbacks_Factory(configurationsModule_ProvideNetworkConfigurationsFactory, 9);
                Provider provider34 = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 13));
                this.networkSamplingParametersProvider = provider34;
                Provider provider35 = DoubleCheck.provider(new NetworkMetricServiceImpl_Factory(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.foregroundTrackerProvider, this.provideListeningScheduledExecutorServiceProvider, this.provideNetworkConfigurationsProvider, this.networkMetricCollectorProvider, provider34, this.provideDeferrableExecutorProvider, this.processStatsCaptureProvider));
                this.networkMetricServiceImplProvider = provider35;
                this.networkMetricServiceProvider = new ConfigurationsModule_ProvideSharedPreferencesFactory(this.setNetworkConfigurationsProvider, provider35, 11, null);
                Factory create10 = InstanceFactory.create(optional13);
                this.setCpuProfilingConfigurationsProvider = create10;
                this.provideCpuProfilingConfigurationsProvider = new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(create10, 2);
                this.cpuprofilingSamplingParametersProvider = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 1));
                Provider provider36 = this.provideClockProvider;
                Provider provider37 = this.provideCpuProfilingConfigurationsProvider;
                Provider provider38 = this.setApplicationContextProvider;
                CpuProfilingServiceScheduler_Factory cpuProfilingServiceScheduler_Factory = new CpuProfilingServiceScheduler_Factory(provider36, provider37, provider38);
                this.cpuProfilingServiceSchedulerProvider = cpuProfilingServiceScheduler_Factory;
                Provider provider39 = DoubleCheck.provider(new StorageMetricServiceImpl_Factory(this.metricRecorderFactoryProvider, provider38, this.provideListeningScheduledExecutorServiceProvider, provider37, this.cpuprofilingSamplingParametersProvider, provider36, cpuProfilingServiceScheduler_Factory, 1, (byte[]) null));
                this.cpuProfilingServiceProvider = provider39;
                this.cpuProfilingServiceProvider2 = new ConfigurationsModule_ProvideSharedPreferencesFactory(this.setCpuProfilingConfigurationsProvider, provider39, 7, null);
                Factory create11 = InstanceFactory.create(optional8);
                this.setStorageConfigurationsProvider = create11;
                this.provideStorageConfigurationsProvider = new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(create11, 6);
                Provider provider40 = this.setApplicationContextProvider;
                this.persistentRateLimitingProvider = new PersistentRateLimiting_Factory(provider40, this.provideClockProvider, this.provideSharedPreferencesProvider);
                Provider provider41 = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(provider40, 18));
                this.storageSamplingParametersProvider = provider41;
                Provider provider42 = DoubleCheck.provider(new StorageMetricServiceImpl_Factory(this.metricRecorderFactoryProvider, this.setApplicationContextProvider, this.foregroundTrackerProvider, this.provideDeferrableExecutorProvider, this.provideStorageConfigurationsProvider, this.persistentRateLimitingProvider, provider41, 0));
                this.storageMetricServiceImplProvider = provider42;
                this.storageMetricServiceProvider = new AppLifecycleTracker_Callbacks_Factory(provider42, 12);
                this.setTimerConfigurationsProvider = InstanceFactory.create(optional4);
                this.setTraceConfigurationsProvider = InstanceFactory.create(optional11);
                Factory create12 = InstanceFactory.create(optional10);
                this.setTikTokTraceConfigurationsProvider = create12;
                this.provideTikTokTraceConfigurationsProvider = new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(create12, 8);
                this.provideTraceConfigurationsProvider = new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(this.setTraceConfigurationsProvider, 10);
                this.traceSamplingParametersProvider = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 20));
                Provider provider43 = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 3));
                this.defaultToUnifiedFormatForTiktokTracesProvider = provider43;
                Provider provider44 = DoubleCheck.provider(new StorageMetricServiceImpl_Factory(this.metricRecorderFactoryProvider, this.provideListeningScheduledExecutorServiceProvider, this.provideTikTokTraceConfigurationsProvider, this.provideTraceConfigurationsProvider, this.traceSamplingParametersProvider, provider43, this.probabilitySamplerFactoryProvider, 2, (char[]) null));
                this.traceMetricServiceImplProvider = provider44;
                this.timerMetricServiceSupportProvider = new PrimesTraceDaggerModule_TimerMetricServiceSupportFactory(this.setTraceConfigurationsProvider, this.setTikTokTraceConfigurationsProvider, provider44);
                this.provideTimerConfigurationsProvider = new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(this.setTimerConfigurationsProvider, 9);
                Provider provider45 = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 19));
                this.timerSamplingParametersProvider = provider45;
                Provider provider46 = DoubleCheck.provider(new MemoryUsageCapture_Factory(this.metricRecorderFactoryProvider, this.provideDeferrableExecutorProvider, this.provideTimerConfigurationsProvider, this.provideGlobalConfigurationsProvider, provider45, this.probabilitySamplerFactoryProvider, 3, (short[]) null));
                this.timerMetricServiceImplProvider = provider46;
                Provider provider47 = this.timerMetricServiceSupportProvider;
                Provider provider48 = DoubleCheck.provider(new ConfigurationsModule_ProvideSharedPreferencesFactory(provider46, provider47, 12));
                this.timerMetricServiceWithTracingImplProvider = provider48;
                Provider provider49 = this.setTimerConfigurationsProvider;
                Provider provider50 = this.setTraceConfigurationsProvider;
                this.timerServiceProvider = new BatteryCapture_Factory((javax.inject.Provider) provider49, (javax.inject.Provider) provider50, (javax.inject.Provider) provider47, (javax.inject.Provider) provider46, (javax.inject.Provider) provider48, 3, (char[]) null);
                this.traceServiceProvider = new DeferrableExecutor_Factory((javax.inject.Provider) provider50, (javax.inject.Provider) this.setTikTokTraceConfigurationsProvider, (javax.inject.Provider) this.traceMetricServiceImplProvider, 4, (int[]) null);
                Factory create13 = InstanceFactory.create(optional);
                this.setMemoryConfigurationsProvider = create13;
                this.provideMemoryConfigurationsProvider = new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(create13, 16);
                Provider provider51 = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 14));
                this.periodicMemoryCollectionPeriodMsProvider = provider51;
                this.memoryMetricMonitorProvider = DoubleCheck.provider(new MemoryUsageCapture_Factory((javax.inject.Provider) this.foregroundTrackerProvider, (javax.inject.Provider) this.provideListeningScheduledExecutorServiceProvider, (javax.inject.Provider) this.provideDeferrableExecutorProvider, (javax.inject.Provider) this.provideMemoryConfigurationsProvider, (javax.inject.Provider) provider51, (javax.inject.Provider) this.randomProvider, 1, (byte[]) null));
                Provider provider52 = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 11));
                this.memoizeConfigsProvider = provider52;
                Provider provider53 = this.provideMemoryConfigurationsProvider;
                Provider provider54 = this.setApplicationContextProvider;
                this.memoryUsageCaptureProvider = DoubleCheck.provider(new MemoryUsageCapture_Factory(provider53, provider54, this.readCorrectProcStatusProvider, provider52, this.processStatsCaptureProvider, this.foregroundStateCaptureProvider, 0));
                this.memorySamplingParametersProvider = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(provider54, 12));
                Provider provider55 = DaggerProdInternalComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
                this.enableUnifiedInitOptionalOfBooleanProvider = provider55;
                Provider provider56 = this.metricRecorderFactoryProvider;
                Provider provider57 = this.provideClockProvider;
                Provider provider58 = this.setApplicationContextProvider;
                this.memoryMetricServiceImplProvider = DoubleCheck.provider(new MemoryMetricServiceImpl_Factory(provider56, provider57, provider58, this.memoryMetricMonitorProvider, this.provideListeningScheduledExecutorServiceProvider, this.provideMemoryConfigurationsProvider, this.memoryUsageCaptureProvider, this.shutdownProvider, this.memorySamplingParametersProvider, this.provideDeferrableExecutorProvider, provider55, this.foregroundStateCaptureProvider, 0));
                Provider provider59 = DaggerProdInternalComponent.ABSENT_GUAVA_OPTIONAL_PROVIDER;
                this.optionalOfStartupConfigurationsProvider = provider59;
                this.provideStartupConfigurationsProvider = new AppLifecycleTracker_Callbacks_Factory(provider59, 10);
                Provider provider60 = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(provider58, 17));
                this.startupSamplingParametersProvider = provider60;
                this.startupMetricRecordingServiceProvider = DoubleCheck.provider(new BatteryCapture_Factory((javax.inject.Provider) this.metricRecorderFactoryProvider, (javax.inject.Provider) this.provideListeningScheduledExecutorServiceProvider, (javax.inject.Provider) this.provideDeferrableExecutorProvider, (javax.inject.Provider) this.provideStartupConfigurationsProvider, (javax.inject.Provider) provider60, 2, (char[]) null));
                this.enableStartupBaselineDiscardingProvider = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 7));
                this.firstDrawTypeProvider = SingleCheck.provider(new PhenotypeFlagsModule_CrashLoopMonitorFlagsFactory(this.setApplicationContextProvider, 8));
                Provider provider61 = SingleCheck.provider(new AppLifecycleTracker_Callbacks_Factory(this.setApplicationContextProvider, 1));
                this.warmStartTypeProvider = provider61;
                this.startupMetricServiceImplProvider = new MemoryUsageCapture_Factory(this.foregroundTrackerProvider, this.startupMetricRecordingServiceProvider, this.enableStartupBaselineDiscardingProvider, this.firstDrawTypeProvider, provider61, this.provideGlobalConfigurationsProvider, 2, (char[]) null);
                SetFactory.Builder builder2 = new SetFactory.Builder(2, 9);
                builder2.addCollectionProvider$ar$ds(this.applicationExitMetricServiceProvider);
                builder2.addCollectionProvider$ar$ds(this.batteryServiceProvider);
                builder2.addCollectionProvider$ar$ds(this.jankServiceProvider);
                builder2.addCollectionProvider$ar$ds(this.crashServiceProvider);
                builder2.addCollectionProvider$ar$ds(this.networkMetricServiceProvider);
                builder2.addCollectionProvider$ar$ds(this.cpuProfilingServiceProvider2);
                builder2.addCollectionProvider$ar$ds(this.storageMetricServiceProvider);
                builder2.addCollectionProvider$ar$ds(this.timerServiceProvider);
                builder2.addCollectionProvider$ar$ds(this.traceServiceProvider);
                builder2.addProvider$ar$ds(this.memoryMetricServiceImplProvider);
                builder2.addProvider$ar$ds(this.startupMetricServiceImplProvider);
                this.setOfMetricServiceProvider = builder2.build();
                this.metricServiceProvider = new ConfigurationsModule_ProvideSharedPreferencesFactory(this.setBatteryConfigurationsProvider, this.batteryMetricServiceImplProvider, 6, null);
                this.metricServiceProvider2 = new ConfigurationsModule_ProvideSharedPreferencesFactory(this.setJankConfigurationsProvider, this.frameMetricServiceImplProvider, 10, null);
                Factory create14 = InstanceFactory.create(optional2);
                this.setDebugMemoryConfigurationsProvider = create14;
                ConfigurationsModule_ProvideApplicationExitConfigurationsFactory configurationsModule_ProvideApplicationExitConfigurationsFactory = new ConfigurationsModule_ProvideApplicationExitConfigurationsFactory(create14, 4);
                this.provideDebugMemoryConfigurationsProvider = configurationsModule_ProvideApplicationExitConfigurationsFactory;
                this.debugMemoryMetricServiceImplProvider = DoubleCheck.provider(new ProcessStatsCapture_Factory((javax.inject.Provider) configurationsModule_ProvideApplicationExitConfigurationsFactory, (javax.inject.Provider) this.provideDeferrableExecutorProvider, (javax.inject.Provider) this.memoryMetricServiceImplProvider, (javax.inject.Provider) this.provideClockProvider, 3, (short[]) null));
                this.provideNetworkMetricServiceProvider = new PrimesNetworkDaggerModule_ProvideNetworkMetricServiceFactory(this.setNetworkConfigurationsProvider, this.networkMetricServiceImplProvider);
                this.metricServiceProvider3 = new AppLifecycleTracker_Callbacks_Factory(this.storageMetricServiceImplProvider, 11);
                Provider provider62 = this.setTimerConfigurationsProvider;
                Provider provider63 = this.timerMetricServiceSupportProvider;
                Provider provider64 = this.timerMetricServiceWithTracingImplProvider;
                Provider provider65 = this.timerMetricServiceImplProvider;
                this.metricServiceProvider4 = new PrimesTimerDaggerModule_MetricServiceFactory(provider62, provider63, provider64, provider65);
                this.metricServiceProvider5 = new DeferrableExecutor_Factory((javax.inject.Provider) this.setTraceConfigurationsProvider, (javax.inject.Provider) this.setTikTokTraceConfigurationsProvider, (javax.inject.Provider) this.traceMetricServiceImplProvider, 3, (int[]) null);
                this.provideCustomDurationMetricServiceProvider = new PrimesTimerDaggerModule_ProvideCustomDurationMetricServiceFactory(provider62, provider63, provider64, provider65);
                this.optionalOfProviderOfCrashMetricServiceProvider = new PresentGuavaOptionalProviderProvider(this.crashMetricServiceImplProvider);
                this.setDisableAutomaticCrashInitTokenProvider = InstanceFactory.create(optional17);
                PresentGuavaOptionalProviderProvider presentGuavaOptionalProviderProvider = new PresentGuavaOptionalProviderProvider(this.memoryMetricServiceImplProvider);
                this.optionalOfProviderOfMemoryMetricServiceProvider = presentGuavaOptionalProviderProvider;
                PrimesInitializerImpl_Factory primesInitializerImpl_Factory = new PrimesInitializerImpl_Factory(this.optionalOfProviderOfCrashMetricServiceProvider, this.setDisableAutomaticCrashInitTokenProvider, presentGuavaOptionalProviderProvider);
                this.primesInitializerImplProvider = primesInitializerImpl_Factory;
                Provider provider66 = this.provideListeningScheduledExecutorServiceProvider;
                Provider provider67 = this.shutdownProvider;
                Provider provider68 = this.setOfMetricServiceProvider;
                Provider provider69 = this.setOfMetricTransmitterProvider;
                Provider provider70 = this.provideNetworkConfigurationsProvider;
                Provider provider71 = this.metricServiceProvider;
                Provider provider72 = this.crashMetricServiceImplProvider;
                Provider provider73 = this.metricServiceProvider2;
                Provider provider74 = this.memoryMetricServiceImplProvider;
                PrimesApiImpl_Factory primesApiImpl_Factory = new PrimesApiImpl_Factory(provider66, provider67, provider68, provider69, provider70, provider71, provider72, provider73, provider74, provider74, this.debugMemoryMetricServiceImplProvider, provider74, this.provideNetworkMetricServiceProvider, this.metricServiceProvider3, this.metricServiceProvider4, this.metricServiceProvider5, this.provideCustomDurationMetricServiceProvider, this.startupMetricServiceImplProvider, this.enableUnifiedInitOptionalOfBooleanProvider, primesInitializerImpl_Factory);
                this.primesApiImplProvider = primesApiImpl_Factory;
                this.providePrimesProvider = DoubleCheck.provider(new ConfigurationsModule_ProvideSharedPreferencesFactory(primesApiImpl_Factory, this.crashOnBadPrimesConfigurationProvider, 2, null));
                return;
            }
            throw new IllegalStateException();
        }
    }
}
