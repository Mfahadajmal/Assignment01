package com.google.android.libraries.performance.primes;

import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStatsCapture;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.crash.CrashMetricService;
import com.google.android.libraries.performance.primes.metrics.memory.MemoryMetricService;
import com.google.android.libraries.performance.primes.metrics.network.NetworkEvent;
import com.google.android.libraries.performance.primes.metrics.network.PrimesNetworkDaggerModule_ProvideNetworkMetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.timer.PrimesTimerDaggerModule_MetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.timer.PrimesTimerDaggerModule_ProvideCustomDurationMetricServiceFactory;
import com.google.android.libraries.performance.primes.metrics.timer.TimerEvent;
import com.google.apps.tiktok.tracing.Tracer;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import dagger.internal.SetFactory;
import java.util.Iterator;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.ExtensionMetric$MetricExtension;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class PrimesApiImpl implements PrimesApi {
    private final Provider crashMetricServiceProvider;
    private final Provider customDurationMetricServiceProvider;
    private final ProcessStatsCapture initializer$ar$class_merging$ar$class_merging;
    private final Provider memoryMetricServiceProvider;
    private final Provider metricServices;
    private final Provider networkMetricServiceProvider;
    private final Shutdown shutdown;
    private final Provider timerMetricServiceProvider;

    public PrimesApiImpl(Shutdown shutdown, Provider provider, Provider provider2, Provider provider3, Provider provider4, Provider provider5, Provider provider6, Optional optional, ProcessStatsCapture processStatsCapture) {
        this.shutdown = shutdown;
        this.metricServices = provider;
        this.crashMetricServiceProvider = provider2;
        this.memoryMetricServiceProvider = provider3;
        this.networkMetricServiceProvider = provider4;
        this.timerMetricServiceProvider = provider5;
        this.customDurationMetricServiceProvider = provider6;
        this.initializer$ar$class_merging$ar$class_merging = processStatsCapture;
        if (!((Boolean) optional.or(Boolean.FALSE)).booleanValue()) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/PrimesApiImpl", "initializeMetricServices", 109, "PrimesApiImpl.java")).log("Primes instant initialization");
            try {
                Tracer.checkTrace$ar$ds$c243405c_0(false);
                Iterator it = ((SetFactory) provider).get().iterator();
                while (it.hasNext()) {
                    ((MetricService) it.next()).onApplicationStartup();
                }
            } catch (RuntimeException e) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/PrimesApiImpl", "initializeMetricServices", BrailleInputEvent.CMD_EDIT_CUSTOM_LABEL, "PrimesApiImpl.java")).log("Primes failed to initialize");
                Shutdown shutdown2 = this.shutdown;
                if (!shutdown2.shutdown) {
                    shutdown2.shutdown = true;
                    ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/Shutdown", "shutdown", 33, "Shutdown.java")).log("Shutdown ...");
                }
            }
        }
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void initialize() {
        this.initializer$ar$class_merging$ar$class_merging.initialize();
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void recordDuration(NoPiiString noPiiString, long j, long j2, ExtensionMetric$MetricExtension extensionMetric$MetricExtension) {
        ((PrimesTimerDaggerModule_ProvideCustomDurationMetricServiceFactory) this.customDurationMetricServiceProvider).get().record(noPiiString, j, j2, extensionMetric$MetricExtension);
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void recordNetwork(NetworkEvent networkEvent) {
        ((PrimesNetworkDaggerModule_ProvideNetworkMetricServiceFactory) this.networkMetricServiceProvider).get().recordAsFuture$ar$ds(networkEvent);
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void startCrashMonitor() {
        ((CrashMetricService) this.crashMetricServiceProvider.get()).setPrimesExceptionHandlerAsDefaultHandler();
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void startMemoryMonitor() {
        ((MemoryMetricService) this.memoryMetricServiceProvider.get()).startMonitoring();
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final TimerEvent startTimer() {
        return ((PrimesTimerDaggerModule_MetricServiceFactory) this.timerMetricServiceProvider).get().start();
    }

    @Override // com.google.android.libraries.performance.primes.PrimesApi
    public final void stopTimer$ar$edu$2eed496a_0(TimerEvent timerEvent, NoPiiString noPiiString, ExtensionMetric$MetricExtension extensionMetric$MetricExtension, int i) {
        ((PrimesTimerDaggerModule_MetricServiceFactory) this.timerMetricServiceProvider).get().stopAsFuture$ar$edu(timerEvent, noPiiString, extensionMetric$MetricExtension, 1);
    }
}
