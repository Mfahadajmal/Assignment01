package com.google.android.libraries.performance.primes.metriccapture;

import android.app.ActivityManager;
import android.content.Context;
import android.os.PowerManager;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.RegularImmutableList;
import java.util.Collection;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ProcessStats {
    public static volatile ActivityManager activityManager;
    static volatile boolean callingProcessForegroundNotionEnabled;
    private static volatile String currentProcessName;

    private ProcessStats() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0039, code lost:
    
        if (r2 == null) goto L21;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String getCurrentProcessName() {
        /*
            java.lang.String r0 = com.google.android.libraries.performance.primes.metriccapture.ProcessStats.currentProcessName
            if (r0 == 0) goto L5
            goto L40
        L5:
            int r0 = android.os.Process.myPid()
            r1 = 0
            if (r0 > 0) goto Ld
            goto L3c
        Ld:
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L38
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L38
            java.lang.String r4 = "/proc/"
            java.lang.String r5 = "/cmdline"
            java.lang.String r0 = _COROUTINE._BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_7(r0, r4, r5)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L38
            r3.<init>(r0)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L38
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L31 java.io.IOException -> L38
            java.lang.String r0 = r2.readLine()     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L39
            r0.getClass()     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L39
            java.lang.String r1 = r0.trim()     // Catch: java.lang.Throwable -> L2e java.io.IOException -> L39
        L2a:
            r2.close()     // Catch: java.io.IOException -> L3c
            goto L3c
        L2e:
            r0 = move-exception
            r1 = r2
            goto L32
        L31:
            r0 = move-exception
        L32:
            if (r1 == 0) goto L37
            r1.close()     // Catch: java.io.IOException -> L37
        L37:
            throw r0
        L38:
            r2 = r1
        L39:
            if (r2 == 0) goto L3c
            goto L2a
        L3c:
            if (r1 == 0) goto L40
            com.google.android.libraries.performance.primes.metriccapture.ProcessStats.currentProcessName = r1
        L40:
            java.lang.String r0 = com.google.android.libraries.performance.primes.metriccapture.ProcessStats.currentProcessName
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.performance.primes.metriccapture.ProcessStats.getCurrentProcessName():java.lang.String");
    }

    public static RunningAppProcessInfoResponse getRunningAppProcesses(Context context) {
        Object systemService = context.getSystemService("activity");
        systemService.getClass();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) systemService).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            int i = ImmutableList.ImmutableList$ar$NoOp;
            return new RunningAppProcessInfoResponse(false, RegularImmutableList.EMPTY);
        }
        return new RunningAppProcessInfoResponse(true, ImmutableList.copyOf((Collection) runningAppProcesses));
    }

    public static String getShortProcessName(String str, String str2) {
        if (str2 != null && str != null && str2.startsWith(str)) {
            int length = str.length();
            if (str2.length() == length) {
                return null;
            }
            return str2.substring(length + 1);
        }
        return str2;
    }

    public static boolean isScreenOn(Context context) {
        Object systemService = context.getSystemService("power");
        systemService.getClass();
        return ((PowerManager) systemService).isInteractive();
    }

    public static boolean processHasForegroundImportance(Context context, RunningAppProcessInfoResponse runningAppProcessInfoResponse) {
        if (!runningAppProcessInfoResponse.getStatus) {
            return false;
        }
        ImmutableList<ActivityManager.RunningAppProcessInfo> runningAppProcessInfos = runningAppProcessInfoResponse.getRunningAppProcessInfos();
        String packageName = context.getPackageName();
        String valueOf = String.valueOf(packageName);
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcessInfos) {
            if (runningAppProcessInfo.importance == 100) {
                if (!runningAppProcessInfo.processName.equals(packageName)) {
                    if (runningAppProcessInfo.processName.startsWith(valueOf.concat(":"))) {
                    }
                }
                return true;
            }
        }
        return false;
    }
}
