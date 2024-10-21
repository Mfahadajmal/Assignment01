package com.google.android.libraries.performance.primes.foreground;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.libraries.performance.primes.NoPiiString;
import com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener;
import com.google.android.libraries.performance.primes.metriccapture.ProcessStats;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessImportanceForegroundSignalAdapter extends AbstractForegroundSignalAdapter implements AppLifecycleListener {
    private final ForegroundListener foregroundListener;
    private NoPiiString stoppedActivityName;

    public ProcessImportanceForegroundSignalAdapter(ForegroundListener foregroundListener) {
        this.foregroundListener = foregroundListener;
    }

    @Override // com.google.android.libraries.performance.primes.foreground.AbstractForegroundSignalAdapter
    public final void emitBackgroundSignal(NoPiiString noPiiString) {
        this.foregroundListener.onAppToBackground(noPiiString);
    }

    @Override // com.google.android.libraries.performance.primes.foreground.AbstractForegroundSignalAdapter
    public final void emitForegroundSignal(NoPiiString noPiiString) {
        this.foregroundListener.onAppToForeground(noPiiString);
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        this.stoppedActivityName = null;
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final void onActivityDestroyed(Activity activity) {
        this.stoppedActivityName = null;
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final void onActivityResumed(Activity activity) {
        this.stoppedActivityName = null;
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final void onActivityStarted(Activity activity) {
        this.stoppedActivityName = null;
        observeForegroundSignal(NoPiiString.fromClass(activity.getClass()));
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final void onActivityStopped(Activity activity) {
        NoPiiString fromClass = NoPiiString.fromClass(activity.getClass());
        this.stoppedActivityName = fromClass;
        Context applicationContext = activity.getApplicationContext();
        if (!ProcessStats.processHasForegroundImportance(applicationContext, ProcessStats.getRunningAppProcesses(applicationContext))) {
            observeBackgroundSignal(fromClass);
        }
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final void onTrimMemory(int i) {
        NoPiiString noPiiString;
        if (i >= 20 && (noPiiString = this.stoppedActivityName) != null) {
            observeBackgroundSignal(noPiiString);
        }
        this.stoppedActivityName = null;
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivityPaused(Activity activity) {
    }

    @Override // com.google.android.libraries.performance.primes.lifecycle.AppLifecycleListener
    public final /* synthetic */ void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }
}
