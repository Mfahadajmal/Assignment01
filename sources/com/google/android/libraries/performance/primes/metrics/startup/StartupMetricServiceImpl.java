package com.google.android.libraries.performance.primes.metrics.startup;

import com.google.android.libraries.performance.primes.ConfigurationsModule$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.PrimesConfigurations$Builder$$ExternalSyntheticLambda0;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.foreground.ForegroundListener;
import com.google.android.libraries.performance.primes.foreground.ForegroundTracker;
import com.google.android.libraries.performance.primes.metrics.core.MetricService;
import com.google.android.libraries.performance.primes.metrics.core.PrimesInstant;
import com.google.android.libraries.performance.primes.metrics.jank.JankObserverFactory;
import com.google.common.flogger.GoogleLogger;
import com.google.common.flogger.context.ContextDataProvider;
import dagger.Lazy;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartupMetricServiceImpl extends JankObserverFactory implements MetricService, ForegroundListener {
    private final ForegroundTracker foregroundTracker;
    private final Provider useUptime;

    public StartupMetricServiceImpl(ForegroundTracker foregroundTracker, Provider provider, Lazy lazy) {
        new AtomicBoolean();
        ContextDataProvider.memoize(new ConfigurationsModule$$ExternalSyntheticLambda0(provider, 9));
        this.foregroundTracker = foregroundTracker;
        this.useUptime = new PrimesConfigurations$Builder$$ExternalSyntheticLambda0(lazy, 3);
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public final void onAppToBackground(NoPiiString noPiiString) {
        this.foregroundTracker.unregister(this);
        StartupMeasure startupMeasure = StartupMeasure.instance;
        PrimesInstant primesInstant = startupMeasure.onDrawBasedFirstDrawnAt;
        PrimesInstant primesInstant2 = startupMeasure.preDrawBasedFirstDrawnAt;
        ((Boolean) this.useUptime.get()).booleanValue();
        ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atInfo()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/startup/StartupMetricServiceImpl", "onAppToBackground", 303, "StartupMetricServiceImpl.java")).log("missing firstDraw timestamp");
    }

    @Override // com.google.android.libraries.performance.primes.metrics.core.MetricService
    public final void onApplicationStartup() {
        this.foregroundTracker.register(this);
    }

    @Override // com.google.android.libraries.performance.primes.foreground.ForegroundListener
    public final /* synthetic */ void onAppToForeground(NoPiiString noPiiString) {
    }
}
