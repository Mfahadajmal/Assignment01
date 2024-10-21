package com.google.android.libraries.performance.primes.lifecycle;

import android.app.Activity;
import android.os.Bundle;

/* compiled from: PG */
/* loaded from: classes.dex */
public interface AppLifecycleListener {
    void onActivityCreated(Activity activity, Bundle bundle);

    void onActivityDestroyed(Activity activity);

    void onActivityPaused(Activity activity);

    void onActivityResumed(Activity activity);

    void onActivitySaveInstanceState(Activity activity, Bundle bundle);

    void onActivityStarted(Activity activity);

    void onActivityStopped(Activity activity);

    void onTrimMemory(int i);
}
