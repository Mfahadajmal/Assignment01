package com.google.android.libraries.performance.primes.sampling;

import android.content.Context;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import androidx.preference.Preference;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.libraries.directboot.DirectBootUtils;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.metrics.MetricConfigurations;
import com.google.android.libraries.performance.primes.sampling.SamplingStrategy;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import dagger.Lazy;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import logs.proto.wireless.performance.mobile.ExtensionTalkback$TalkbackExtension;
import logs.proto.wireless.performance.mobile.SystemHealthProto$PackedHistogram;
import logs.proto.wireless.performance.mobile.SystemHealthProto$SamplingParameters;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Sampler {
    private static final RateLimiting NO_RATE_LIMIT = RateLimiting.fixed(Preference.DEFAULT_ORDER);
    public volatile SamplingStrategy samplingStrategy = SamplingStrategy.Factory.DEFAULT_UNKNOWN_SAMPLING_STRATEGY;
    public volatile boolean enabled = true;
    public volatile RateLimiting rateLimiter = NO_RATE_LIMIT;

    public Sampler(final Context context, final Executor executor, final SamplingStrategy.Factory factory, final Lazy lazy, boolean z, Optional optional, Provider provider) {
        final Provider provider2;
        if (z && !optional.isPresent()) {
            provider2 = provider;
        } else {
            provider2 = null;
        }
        submitFireAndForget(new Runnable() { // from class: com.google.android.libraries.performance.primes.sampling.Sampler$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                Sampler sampler = Sampler.this;
                Lazy lazy2 = lazy;
                Context context2 = context;
                if (DirectBootUtils.isUserUnlocked(context2)) {
                    sampler.fetchConfig(lazy2);
                } else {
                    DirectBootUtils.runWhenUnlocked(context2, new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(sampler, lazy2, executor, 16, (short[]) null));
                }
                if (sampler.enabled) {
                    Provider provider3 = provider2;
                    SamplingStrategy.Factory factory2 = factory;
                    if (provider3 == null) {
                        SystemHealthProto$PackedHistogram.Builder createBuilder = SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE.createBuilder();
                        int i = SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_ALWAYS_ON$ar$edu;
                        createBuilder.copyOnWrite();
                        SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters = (SystemHealthProto$SamplingParameters) createBuilder.instance;
                        int i2 = i - 1;
                        if (i != 0) {
                            systemHealthProto$SamplingParameters.samplingStrategy_ = i2;
                            systemHealthProto$SamplingParameters.bitField0_ |= 4;
                            sampler.samplingStrategy = factory2.create((SystemHealthProto$SamplingParameters) createBuilder.build());
                            return;
                        }
                        throw null;
                    }
                    try {
                        sampler.samplingStrategy = factory2.create((SystemHealthProto$SamplingParameters) provider3.get());
                    } catch (Throwable th) {
                        ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(th)).withInjectedLogSite("com/google/android/libraries/performance/primes/sampling/Sampler", "fetchSamplingParameters", BrailleInputEvent.CMD_TOGGLE_VOICE_FEEDBACK, "Sampler.java")).log("Couldn't get sampling strategy");
                        SystemHealthProto$PackedHistogram.Builder createBuilder2 = SystemHealthProto$SamplingParameters.DEFAULT_INSTANCE.createBuilder();
                        createBuilder2.copyOnWrite();
                        SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters2 = (SystemHealthProto$SamplingParameters) createBuilder2.instance;
                        systemHealthProto$SamplingParameters2.bitField0_ |= 2;
                        systemHealthProto$SamplingParameters2.sampleRatePermille_ = 1L;
                        int i3 = SystemHealthProto$SamplingParameters.SamplingStrategy.SAMPLING_STRATEGY_PROCESS_LEVEL_PROBABILITY$ar$edu;
                        createBuilder2.copyOnWrite();
                        SystemHealthProto$SamplingParameters systemHealthProto$SamplingParameters3 = (SystemHealthProto$SamplingParameters) createBuilder2.instance;
                        int i4 = i3 - 1;
                        if (i3 != 0) {
                            systemHealthProto$SamplingParameters3.samplingStrategy_ = i4;
                            systemHealthProto$SamplingParameters3.bitField0_ |= 4;
                            sampler.samplingStrategy = factory2.create((SystemHealthProto$SamplingParameters) createBuilder2.build());
                            return;
                        }
                        throw null;
                    }
                }
            }
        }, executor);
    }

    public static void submitFireAndForget(Runnable runnable, Executor executor) {
        executor.execute(runnable);
    }

    public final void fetchConfig(Lazy lazy) {
        try {
            MetricConfigurations metricConfigurations = (MetricConfigurations) lazy.get();
            this.enabled = metricConfigurations.isEnabled();
            this.rateLimiter = RateLimiting.fixed(metricConfigurations.getRateLimitPerSecond());
        } catch (Throwable th) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(th)).withInjectedLogSite("com/google/android/libraries/performance/primes/sampling/Sampler", "fetchConfig", ExtensionTalkback$TalkbackExtension.EventLatencyInfo.EventType.TYPE_FINGERPRINT_GESTURE$ar$edu, "Sampler.java")).log("Couldn't get config");
            this.enabled = false;
        }
    }
}
