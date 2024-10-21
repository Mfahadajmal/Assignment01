package org.chromium.base;

import com.google.android.accessibility.brailleime.BrailleIme$21$$ExternalSyntheticLambda1;
import j$.util.DesugarCollections;
import java.util.HashMap;
import java.util.Map;
import org.chromium.base.task.PostTask;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ApplicationStatus {
    private static final Map sActivityInfo = DesugarCollections.synchronizedMap(new HashMap());
    public static ObserverList sApplicationStateListeners;
    public static ApplicationStateListener sNativeApplicationStateListener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ApplicationStateListener {
    }

    static {
        DesugarCollections.synchronizedMap(new HashMap());
    }

    private ApplicationStatus() {
    }

    public static int getStateForApplication() {
        synchronized (sActivityInfo) {
        }
        return 0;
    }

    public static boolean hasVisibleActivities() {
        getStateForApplication();
        return false;
    }

    public static void isInitialized$ar$ds() {
        synchronized (sActivityInfo) {
        }
    }

    public static void registerApplicationStateListener(ApplicationStateListener applicationStateListener) {
        if (sApplicationStateListeners == null) {
            sApplicationStateListeners = new ObserverList();
        }
        sApplicationStateListeners.addObserver$ar$ds(applicationStateListener);
    }

    private static void registerThreadSafeNativeApplicationStateListener() {
        PostTask.runOrPostTask$ar$ds(new BrailleIme$21$$ExternalSyntheticLambda1(10));
    }
}
