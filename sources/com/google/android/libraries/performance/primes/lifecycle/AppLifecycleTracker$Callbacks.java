package com.google.android.libraries.performance.primes.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.libraries.performance.primes.PrimesLoggerHolder;
import com.google.android.libraries.performance.primes.metrics.core.MetricDispatcher;
import com.google.common.flogger.GoogleLogger;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppLifecycleTracker$Callbacks implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    public static final /* synthetic */ int AppLifecycleTracker$Callbacks$ar$NoOp = 0;
    private final MetricDispatcher crashOnBadPrimesConfiguration$ar$class_merging;
    public final List lifecycleListeners = new CopyOnWriteArrayList();
    private final AtomicInteger createdCount = new AtomicInteger();
    private final AtomicInteger startedCount = new AtomicInteger();
    public final AtomicInteger resumedCount = new AtomicInteger();
    private final AtomicInteger pausedCount = new AtomicInteger();
    private final AtomicInteger stoppedCount = new AtomicInteger();
    private final AtomicInteger destroyedCount = new AtomicInteger();
    private boolean didCheck = false;

    public AppLifecycleTracker$Callbacks(MetricDispatcher metricDispatcher) {
        this.crashOnBadPrimesConfiguration$ar$class_merging = metricDispatcher;
    }

    private final void crashIfNotAllowlistedAndOnCreateDidNotComeFirst() {
        if (!this.didCheck && this.createdCount.get() == 0) {
            MetricDispatcher metricDispatcher = this.crashOnBadPrimesConfiguration$ar$class_merging;
            if (!MetricDispatcher.isRobolectric()) {
                ((GoogleLogger.Api) ((GoogleLogger.Api) PrimesLoggerHolder.singletonLogger.atWarning()).withInjectedLogSite("com/google/android/libraries/performance/primes/CrashOnBadPrimesConfiguration", "observedOutOfOrderLifecycleEvent", 43, "CrashOnBadPrimesConfiguration.java")).log("Primes did not observe lifecycle events in the expected order. %s is not initializing in Application#onCreate", metricDispatcher.MetricDispatcher$ar$metricTransmittersSupplier);
                if (!metricDispatcher.appIsAllowlistedForOutOfOrderLifecycleEvents()) {
                    throw new IllegalStateException(String.format("Primes did not observe lifecycle events in the expected order. %s is not initializing in Application#onCreate", metricDispatcher.MetricDispatcher$ar$metricTransmittersSupplier));
                }
            }
            this.didCheck = true;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        this.createdCount.incrementAndGet();
        Iterator it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            ((AppLifecycleListener) it.next()).onActivityCreated(activity, bundle);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
        if (this.destroyedCount.getAndIncrement() == 0) {
            crashIfNotAllowlistedAndOnCreateDidNotComeFirst();
        }
        Iterator it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            ((AppLifecycleListener) it.next()).onActivityDestroyed(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        if (this.pausedCount.getAndIncrement() == 0) {
            crashIfNotAllowlistedAndOnCreateDidNotComeFirst();
        }
        Iterator it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            ((AppLifecycleListener) it.next()).onActivityPaused(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        if (this.resumedCount.getAndIncrement() == 0) {
            crashIfNotAllowlistedAndOnCreateDidNotComeFirst();
        }
        Iterator it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            ((AppLifecycleListener) it.next()).onActivityResumed(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Iterator it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            ((AppLifecycleListener) it.next()).onActivitySaveInstanceState(activity, bundle);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
        if (this.startedCount.getAndIncrement() == 0) {
            crashIfNotAllowlistedAndOnCreateDidNotComeFirst();
        }
        Iterator it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            ((AppLifecycleListener) it.next()).onActivityStarted(activity);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
        if (this.stoppedCount.getAndIncrement() == 0) {
            crashIfNotAllowlistedAndOnCreateDidNotComeFirst();
        }
        Iterator it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            ((AppLifecycleListener) it.next()).onActivityStopped(activity);
        }
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        Iterator it = this.lifecycleListeners.iterator();
        while (it.hasNext()) {
            ((AppLifecycleListener) it.next()).onTrimMemory(i);
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }
}
