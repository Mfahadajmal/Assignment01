package com.google.android.libraries.stitch.util;

import android.os.Handler;
import android.os.Looper;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ThreadUtil {
    private static final Object HANDLER_INIT_LOCK = new Object();
    private static Thread mainThread;
    private static volatile Handler sMainThreadHandler;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class CalledOnWrongThreadException extends RuntimeException {
        public CalledOnWrongThreadException() {
            super("Must be called on a background thread");
        }
    }

    public static void ensureBackgroundThread() {
        if (!isMainThread()) {
        } else {
            throw new CalledOnWrongThreadException();
        }
    }

    public static Handler getMainThreadHandler() {
        if (sMainThreadHandler == null) {
            synchronized (HANDLER_INIT_LOCK) {
                if (sMainThreadHandler == null) {
                    sMainThreadHandler = new Handler(Looper.getMainLooper());
                }
            }
        }
        return sMainThreadHandler;
    }

    public static boolean isMainThread() {
        return isMainThread(Thread.currentThread());
    }

    public static void postOnMainThread(Runnable runnable) {
        getMainThreadHandler().post(runnable);
    }

    public static boolean isMainThread(Thread thread) {
        if (mainThread == null) {
            mainThread = Looper.getMainLooper().getThread();
        }
        return thread == mainThread;
    }
}
