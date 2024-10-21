package com.google.android.libraries.performance.primes.metrics.crash;

import android.content.Context;
import com.google.common.base.Supplier;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CrashLoopMonitorSuppliers$StorageDirSupplierImpl implements Supplier {
    private final Context context;
    private final /* synthetic */ int switching_field;

    public CrashLoopMonitorSuppliers$StorageDirSupplierImpl(Context context, int i) {
        this.switching_field = i;
        this.context = context;
    }

    /* JADX WARN: Code restructure failed: missing block: B:37:0x008d, code lost:
    
        if (r0 != null) goto L50;
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0050  */
    @Override // com.google.common.base.Supplier
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* synthetic */ java.lang.Object get() {
        /*
            r5 = this;
            int r0 = r5.switching_field
            if (r0 == 0) goto Lc7
            java.lang.String r0 = com.google.android.libraries.processinit.CurrentProcess.processName
            if (r0 == 0) goto La
            goto Lc2
        La:
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 28
            if (r0 < r1) goto L1a
            java.lang.String r0 = org.chromium.base.RadioUtils$$ExternalSyntheticApiModelOutline0.m312m()
            com.google.android.libraries.processinit.CurrentProcess.processName = r0
            java.lang.String r0 = com.google.android.libraries.processinit.CurrentProcess.processName
            goto Lc2
        L1a:
            java.lang.String r0 = "robolectric"
            java.lang.String r1 = android.os.Build.FINGERPRINT
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 != 0) goto L94
            java.lang.String r0 = "android.app.ActivityThread"
            java.lang.Class<com.google.android.libraries.processinit.CurrentProcess> r2 = com.google.android.libraries.processinit.CurrentProcess.class
            java.lang.ClassLoader r2 = r2.getClassLoader()     // Catch: java.lang.Throwable -> L47
            r3 = 0
            java.lang.Class r0 = java.lang.Class.forName(r0, r3, r2)     // Catch: java.lang.Throwable -> L47
            java.lang.String r2 = "currentProcessName"
            java.lang.reflect.Method r0 = r0.getDeclaredMethod(r2, r1)     // Catch: java.lang.Throwable -> L47
            r2 = 1
            r0.setAccessible(r2)     // Catch: java.lang.Throwable -> L47
            java.lang.Object r0 = r0.invoke(r1, r1)     // Catch: java.lang.Throwable -> L47
            boolean r2 = r0 instanceof java.lang.String     // Catch: java.lang.Throwable -> L47
            if (r2 == 0) goto L47
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Throwable -> L47
            goto L48
        L47:
            r0 = r1
        L48:
            com.google.android.libraries.processinit.CurrentProcess.processName = r0
            java.lang.String r0 = com.google.android.libraries.processinit.CurrentProcess.processName
            if (r0 == 0) goto L50
            goto Lc2
        L50:
            android.os.StrictMode$ThreadPolicy r0 = android.os.StrictMode.allowThreadDiskReads()
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r4 = "/proc/self/cmdline"
            r3.<init>(r4)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            r4 = 50
            r2.<init>(r3, r4)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            java.lang.String r3 = r2.readLine()     // Catch: java.lang.Throwable -> L71
            java.lang.String r3 = r3.trim()     // Catch: java.lang.Throwable -> L71
            r2.close()     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
            android.os.StrictMode.setThreadPolicy(r0)
            goto L89
        L71:
            r3 = move-exception
            r2.close()     // Catch: java.lang.Throwable -> L76
            goto L7a
        L76:
            r2 = move-exception
            r3.addSuppressed(r2)     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
        L7a:
            throw r3     // Catch: java.lang.Throwable -> L7b java.lang.Exception -> L7d
        L7b:
            r1 = move-exception
            goto L90
        L7d:
            r2 = move-exception
            java.lang.String r3 = "CurrentProcess"
            java.lang.String r4 = "Unable to read /proc/self/cmdline"
            android.util.Log.e(r3, r4, r2)     // Catch: java.lang.Throwable -> L7b
            android.os.StrictMode.setThreadPolicy(r0)
            r3 = r1
        L89:
            com.google.android.libraries.processinit.CurrentProcess.processName = r3
            java.lang.String r0 = com.google.android.libraries.processinit.CurrentProcess.processName
            if (r0 == 0) goto L94
            goto Lc2
        L90:
            android.os.StrictMode.setThreadPolicy(r0)
            throw r1
        L94:
            android.content.Context r0 = r5.context
            java.lang.String r2 = "activity"
            java.lang.Object r0 = r0.getSystemService(r2)
            android.app.ActivityManager r0 = (android.app.ActivityManager) r0
            java.util.List r0 = r0.getRunningAppProcesses()
            if (r0 == 0) goto Lbe
            int r2 = android.os.Process.myPid()
            java.util.Iterator r0 = r0.iterator()
        Lac:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto Lbe
            java.lang.Object r3 = r0.next()
            android.app.ActivityManager$RunningAppProcessInfo r3 = (android.app.ActivityManager.RunningAppProcessInfo) r3
            int r4 = r3.pid
            if (r4 != r2) goto Lac
            java.lang.String r1 = r3.processName
        Lbe:
            com.google.android.libraries.processinit.CurrentProcess.processName = r1
            java.lang.String r0 = com.google.android.libraries.processinit.CurrentProcess.processName
        Lc2:
            com.google.common.base.Optional r0 = com.google.common.base.Optional.fromNullable(r0)
            return r0
        Lc7:
            android.content.Context r0 = r5.context
            boolean r0 = com.google.android.libraries.directboot.DirectBootUtils.isDirectBoot(r0)
            if (r0 == 0) goto Ld2
            com.google.common.base.Absent r0 = com.google.common.base.Absent.INSTANCE
            goto Le3
        Ld2:
            android.content.Context r0 = r5.context
            java.io.File r1 = new java.io.File
            java.io.File r0 = r0.getFilesDir()
            java.lang.String r2 = "primes/crash"
            r1.<init>(r0, r2)
            com.google.common.base.Optional r0 = com.google.common.base.Optional.of(r1)
        Le3:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metrics.crash.CrashLoopMonitorSuppliers$StorageDirSupplierImpl.get():java.lang.Object");
    }
}
