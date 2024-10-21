package com.google.android.libraries.performance.primes.metrics.jank;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.common.base.Optional;
import com.google.common.flogger.GoogleLogger;
import dagger.Lazy;
import java.util.concurrent.Executor;
import javax.inject.Provider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ActivityLevelJankMonitor implements AppLifecycleListener {
    private Activity currentActivity;
    private boolean enabled = false;
    private final Lazy frameMetricService;

    public ActivityLevelJankMonitor(Lazy<FrameMetricServiceImpl> lazy, final Optional<Provider<Boolean>> optional, Executor executor) {
        this.frameMetricService = lazy;
        executor.execute(new Runnable() { // from class: com.google.android.libraries.performance.primes.metrics.jank.ActivityLevelJankMonitor$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ActivityLevelJankMonitor.this.m200x22d674a9(optional);
            }
        });
    }

    /* renamed from: lambda$new$0$com-google-android-libraries-performance-primes-metrics-jank-ActivityLevelJankMonitor, reason: not valid java name */
    public /* synthetic */ void m200x22d674a9(Optional optional) {
        if (optional.isPresent() && !((Boolean) ((Provider) optional.get()).get()).booleanValue()) {
            return;
        }
        synchronized (this) {
            this.enabled = true;
            Activity activity = this.currentActivity;
            if (activity != null) {
                onActivityResumed(activity);
            }
            this.currentActivity = null;
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public synchronized void onActivityPaused(Activity activity) {
        if (this.enabled) {
            ((FrameMetricServiceImpl) this.frameMetricService.get()).stopAsFuture(activity);
        } else if (!activity.equals(this.currentActivity)) {
            ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/ActivityLevelJankMonitor", "onActivityPaused", 86, "ActivityLevelJankMonitor.java")).log("Activity mismatch (currentActivity=%s, activity=%s)", this.currentActivity, activity);
        }
        this.currentActivity = null;
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public synchronized void onActivityResumed(Activity activity) {
        if (this.enabled) {
            ((FrameMetricServiceImpl) this.frameMetricService.get()).start(activity);
        } else {
            this.currentActivity = activity;
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onActivityDestroyed(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onActivityStarted(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onActivityStopped(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onTrimMemory(int i) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onActivityCreated(Activity activity, Bundle bundle) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public /* synthetic */ void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}
