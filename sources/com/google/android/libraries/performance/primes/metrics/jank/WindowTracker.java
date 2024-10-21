package com.google.android.libraries.performance.primes.metrics.jank;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window$OnFrameMetricsAvailableListener;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.common.flogger.GoogleLogger;
import com.google.common.util.concurrent.ListeningScheduledExecutorService;
import com.google.search.mdi.aratea.proto.FeatureName;
import dagger.Lazy;
import java.util.Collections;
import java.util.Set;
import java.util.WeakHashMap;

/* compiled from: PG */
/* loaded from: classes.dex */
final class WindowTracker implements AppLifecycleListener {
    private Activity currentActivity;
    private final Window$OnFrameMetricsAvailableListener frameMetricsListener;
    private final Lazy handler;
    private boolean measuring;
    private final Set trackedWindows = Collections.newSetFromMap(new WeakHashMap());

    public WindowTracker(Lazy<Handler> lazy, ListeningScheduledExecutorService listeningScheduledExecutorService, Window$OnFrameMetricsAvailableListener window$OnFrameMetricsAvailableListener) {
        this.frameMetricsListener = window$OnFrameMetricsAvailableListener;
        this.handler = lazy;
    }

    private final synchronized void attachToCurrentActivity() {
        Activity activity = this.currentActivity;
        if (activity != null && this.trackedWindows.add(activity.getWindow())) {
            activity.getWindow().addOnFrameMetricsAvailableListener(this.frameMetricsListener, (Handler) this.handler.get());
            return;
        }
        ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/WindowTracker", "attachToCurrentActivity", 91, "WindowTracker.java")).log("Activity is null or already being tracked");
    }

    private final synchronized void detachFromActivity(Activity activity) {
        if (activity != null) {
            if (this.trackedWindows.remove(activity.getWindow())) {
                try {
                    activity.getWindow().removeOnFrameMetricsAvailableListener(this.frameMetricsListener);
                    return;
                } catch (RuntimeException e) {
                    ((GoogleLogger.Api) ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withCause(e)).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/WindowTracker", "detachFromActivity", 109, "WindowTracker.java")).log("Failed to detach the frame metrics listener");
                    return;
                }
            }
        }
        ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atFine()).withInjectedLogSite("com/google/android/libraries/performance/primes/metrics/jank/WindowTracker", "detachFromActivity", FeatureName.AI_WALLPAPER_SAMSUNG$ar$edu, "WindowTracker.java")).log("Activity is null or isn't being tracked");
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public void onActivityDestroyed(Activity activity) {
        synchronized (this) {
            detachFromActivity(activity);
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public void onActivityPaused(Activity activity) {
        synchronized (this) {
            this.currentActivity = null;
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public void onActivityResumed(Activity activity) {
        synchronized (this) {
            this.currentActivity = activity;
            if (this.measuring) {
                attachToCurrentActivity();
            }
        }
    }

    public synchronized void startCollecting() {
        this.measuring = true;
        attachToCurrentActivity();
    }

    public synchronized void stopCollecting() {
        this.measuring = false;
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
