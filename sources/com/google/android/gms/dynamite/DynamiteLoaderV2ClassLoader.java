package com.google.android.gms.dynamite;

import android.os.Process;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DynamiteLoaderV2ClassLoader {
    private static ClassLoader classLoader;
    private static Thread thread;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class BlockedThread extends Thread {
        public BlockedThread(ThreadGroup threadGroup) {
            super(threadGroup, "GmsDynamite");
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Process.setThreadPriority(19);
            synchronized (this) {
                while (true) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                        return;
                    }
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x009f, code lost:
    
        if (r1 == null) goto L57;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized java.lang.ClassLoader get() {
        /*
            Method dump skipped, instructions count: 214
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.dynamite.DynamiteLoaderV2ClassLoader.get():java.lang.ClassLoader");
    }
}
