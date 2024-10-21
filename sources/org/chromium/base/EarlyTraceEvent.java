package org.chromium.base;

import android.os.Process;
import android.os.SystemClock;
import java.util.ArrayList;
import java.util.List;
import org.chromium.base.ContextUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class EarlyTraceEvent {
    static volatile int sState;
    static final Object sLock = new Object();
    static final List sActivityStartupEvents = new ArrayList();
    static final List sActivityLaunchCauseEvents = new ArrayList();

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ActivityLaunchCauseEvent {
        final long mId;
        final int mLaunchCause;
        final long mTimeMs;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class ActivityStartupEvent {
        final long mId;
        final long mTimeMs;
    }

    public static void begin$ar$ds() {
        if (!enabled()) {
            return;
        }
        Process.myTid();
        System.nanoTime();
        SystemClock.currentThreadTimeMillis();
        synchronized (sLock) {
            if (enabled()) {
                throw null;
            }
        }
    }

    public static boolean enabled() {
        return false;
    }

    public static void end$ar$ds() {
        if (!enabled()) {
            return;
        }
        Process.myTid();
        System.nanoTime();
        SystemClock.currentThreadTimeMillis();
        synchronized (sLock) {
            if (enabled()) {
                throw null;
            }
        }
    }

    public static boolean getBackgroundStartupTracingFlag() {
        return false;
    }

    static void setBackgroundStartupTracingFlag(boolean z) {
        StrictModeContext allowDiskWrites = StrictModeContext.allowDiskWrites();
        try {
            ContextUtils.Holder.sSharedPreferences.edit().putBoolean("bg_startup_tracing", z).apply();
            allowDiskWrites.close();
        } catch (Throwable th) {
            try {
                allowDiskWrites.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }
}
