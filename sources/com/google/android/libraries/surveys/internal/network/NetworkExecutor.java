package com.google.android.libraries.surveys.internal.network;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkExecutor {
    static volatile Executor networkExecutor;
    private static final Object networkExecutorLock = new Object();

    /* compiled from: PG */
    /* renamed from: com.google.android.libraries.surveys.internal.network.NetworkExecutor$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 implements ThreadFactory {
        private final /* synthetic */ int switching_field;
        private final AtomicInteger threadCount = new AtomicInteger(1);

        public AnonymousClass1(int i, byte[] bArr) {
            this.switching_field = i;
        }

        @Override // java.util.concurrent.ThreadFactory
        public final Thread newThread(Runnable runnable) {
            int i = this.switching_field;
            if (i != 0) {
                if (i != 1) {
                    return new Thread(runnable, "CrAsyncTask #" + this.threadCount.getAndIncrement());
                }
                Thread thread = new Thread(runnable);
                thread.setName("arch_disk_io_" + this.threadCount.getAndIncrement());
                return thread;
            }
            return new Thread(runnable, "Survey #" + this.threadCount.getAndIncrement());
        }

        public AnonymousClass1(int i) {
            this.switching_field = i;
        }

        public AnonymousClass1(int i, char[] cArr) {
            this.switching_field = i;
        }
    }

    public static Executor getNetworkExecutor() {
        if (networkExecutor == null) {
            synchronized (networkExecutorLock) {
                if (networkExecutor == null) {
                    networkExecutor = new ThreadPoolExecutor(1, 3, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new AnonymousClass1(0));
                    ((ThreadPoolExecutor) networkExecutor).allowCoreThreadTimeOut(true);
                }
            }
        }
        return networkExecutor;
    }
}
