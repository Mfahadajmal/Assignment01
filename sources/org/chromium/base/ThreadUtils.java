package org.chromium.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.base.task.UiThreadTaskExecutor;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ThreadUtils {
    private static final Object sLock = new Object();
    private static volatile Handler sUiThreadHandler;
    private static volatile boolean sWillOverride;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ThreadChecker {
    }

    public static Handler getUiThreadHandler() {
        if (sUiThreadHandler == null) {
            Looper mainLooper = Looper.getMainLooper();
            synchronized (sLock) {
                if (sUiThreadHandler == null) {
                    Handler handler = new Handler(mainLooper);
                    PostTask.sUiThreadTaskExecutor = new UiThreadTaskExecutor(handler);
                    sUiThreadHandler = handler;
                    TraceEvent.sUiThreadReady = true;
                    if (TraceEvent.sEnabled) {
                        TraceEvent.ViewHierarchyDumper.updateEnabledState();
                    }
                } else if (sUiThreadHandler.getLooper() != mainLooper) {
                    throw new RuntimeException("UI thread looper is already set to " + sUiThreadHandler.getLooper() + " (Main thread looper is " + Looper.getMainLooper() + "), cannot set to new looper " + mainLooper);
                }
            }
        }
        return sUiThreadHandler;
    }

    public static Looper getUiThreadLooper() {
        return getUiThreadHandler().getLooper();
    }

    private static boolean isThreadPriorityAudio(int i) {
        if (Process.getThreadPriority(i) == -16) {
            return true;
        }
        return false;
    }

    public static void setThreadPriorityAudio(int i) {
        Process.setThreadPriority(i, -16);
    }
}
