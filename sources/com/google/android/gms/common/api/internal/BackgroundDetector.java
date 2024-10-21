package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BackgroundDetector implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2 {
    public static final BackgroundDetector instance = new BackgroundDetector();
    public final AtomicBoolean background = new AtomicBoolean();
    public final AtomicBoolean stateKnown = new AtomicBoolean();
    public final ArrayList listeners = new ArrayList();
    public boolean initialized = false;

    private BackgroundDetector() {
    }

    private final void onBackgroundStateChanged(boolean z) {
        synchronized (instance) {
            Iterator it = this.listeners.iterator();
            while (it.hasNext()) {
                ((FloatingActionButton.ShadowDelegateImpl) it.next()).onBackgroundStateChanged(z);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        AtomicBoolean atomicBoolean = this.stateKnown;
        boolean compareAndSet = this.background.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        AtomicBoolean atomicBoolean = this.stateKnown;
        boolean compareAndSet = this.background.compareAndSet(true, false);
        atomicBoolean.set(true);
        if (compareAndSet) {
            onBackgroundStateChanged(false);
        }
    }

    @Override // android.content.ComponentCallbacks2
    public final void onTrimMemory(int i) {
        if (i == 20 && this.background.compareAndSet(false, true)) {
            this.stateKnown.set(true);
            onBackgroundStateChanged(true);
        }
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(Configuration configuration) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}
