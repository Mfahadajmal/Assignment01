package org.chromium.base.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import org.chromium.base.ThreadUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public class PostTask {
    private static volatile boolean sNativeInitialized;
    public static volatile Executor sPrenativeThreadPoolExecutorForTesting;
    public static UiThreadTaskExecutor sUiThreadTaskExecutor;
    public static final Object sPreNativeTaskRunnerLock = new Object();
    public static List sPreNativeTaskRunners = new ArrayList();
    public static final ChromeThreadPoolExecutor sPrenativeThreadPoolExecutor = new ChromeThreadPoolExecutor();
    private static final ThreadPoolTaskExecutor sThreadPoolTaskExecutor = new ThreadPoolTaskExecutor();

    private static TaskExecutor getTaskExecutorForTraits(int i) {
        if (i >= 6) {
            if (sUiThreadTaskExecutor == null) {
                ThreadUtils.getUiThreadHandler();
            }
            return sUiThreadTaskExecutor;
        }
        return sThreadPoolTaskExecutor;
    }

    private static void onNativeSchedulerReady() {
        List list;
        if (!sNativeInitialized) {
            sNativeInitialized = true;
            synchronized (sPreNativeTaskRunnerLock) {
                list = sPreNativeTaskRunners;
                sPreNativeTaskRunners = null;
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                ((TaskRunnerImpl) it.next()).initNativeTaskRunner();
            }
        }
    }

    public static void postTask(int i, Runnable runnable) {
        getTaskExecutorForTraits(i).postDelayedTask$ar$ds(i, runnable);
    }

    public static void runOrPostTask$ar$ds(Runnable runnable) {
        if (getTaskExecutorForTraits(7).canRunTaskImmediately$ar$ds()) {
            runnable.run();
        } else {
            postTask(7, runnable);
        }
    }
}
