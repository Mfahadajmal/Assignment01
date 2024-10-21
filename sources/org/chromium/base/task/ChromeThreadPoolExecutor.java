package org.chromium.base.task;

import android.os.AsyncTask;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.android.libraries.surveys.internal.network.NetworkExecutor;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ChromeThreadPoolExecutor extends ThreadPoolExecutor {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    private static final int MAXIMUM_POOL_SIZE;
    private static final BlockingQueue sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;

    static {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        CPU_COUNT = availableProcessors;
        CORE_POOL_SIZE = Math.max(2, Math.min(availableProcessors - 1, 4));
        MAXIMUM_POOL_SIZE = availableProcessors + availableProcessors + 1;
        sThreadFactory = new NetworkExecutor.AnonymousClass1(2, (char[]) null);
        sPoolWorkQueue = new ArrayBlockingQueue(BrailleInputEvent.CMD_NAV_TOP_OR_KEY_ACTIVATE);
    }

    public ChromeThreadPoolExecutor() {
        super(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 30L, TimeUnit.SECONDS, (BlockingQueue<Runnable>) sPoolWorkQueue, sThreadFactory);
        allowCoreThreadTimeOut(true);
    }

    @Override // java.util.concurrent.ThreadPoolExecutor, java.util.concurrent.Executor
    public final void execute(Runnable runnable) {
        String sb;
        int i;
        try {
            super.execute(runnable);
        } catch (RejectedExecutionException e) {
            HashMap hashMap = new HashMap();
            for (Runnable runnable2 : (Runnable[]) getQueue().toArray(new Runnable[0])) {
                Class<?> cls = runnable2.getClass();
                if (cls != AsyncTask$NamedFutureTask.class) {
                    if (cls.getEnclosingClass() == AsyncTask.class) {
                        cls = AsyncTask.class;
                    }
                    String name = cls.getName();
                    if (hashMap.containsKey(name)) {
                        i = ((Integer) hashMap.get(name)).intValue();
                    } else {
                        i = 0;
                    }
                    hashMap.put(name, Integer.valueOf(i + 1));
                } else {
                    throw null;
                }
            }
            StringBuilder sb2 = new StringBuilder();
            for (Map.Entry entry : hashMap.entrySet()) {
                if (((Integer) entry.getValue()).intValue() > 32) {
                    sb2.append((String) entry.getKey());
                    sb2.append(' ');
                }
            }
            if (sb2.length() == 0) {
                sb = "NO CLASSES FOUND";
            } else {
                sb = sb2.toString();
            }
            throw new RejectedExecutionException("Prominent classes in AsyncTask: ".concat(String.valueOf(sb)), e);
        }
    }
}
