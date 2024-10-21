package com.google.android.libraries.directboot;

import android.content.Context;
import android.os.UserManager;
import android.support.v7.widget.DropDownListView;
import com.google.android.apps.aicore.client.api.internal.AiCoreBaseService$$ExternalSyntheticLambda4;
import com.google.common.util.concurrent.ImmediateFuture;
import com.google.common.util.concurrent.ListenableFuture;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DirectBootUtils {
    public static final /* synthetic */ int DirectBootUtils$ar$NoOp = 0;
    private static volatile boolean isUserUnlocked = false;
    private static UserManager userManager;

    private DirectBootUtils() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:26:0x0037, code lost:
    
        if (r3.isUserRunning(android.os.Process.myUserHandle()) == false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0039, code lost:
    
        r5 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean checkUserUnlocked(android.content.Context r7) {
        /*
            boolean r0 = com.google.android.libraries.directboot.DirectBootUtils.isUserUnlocked
            r1 = 1
            if (r0 == 0) goto L6
            return r1
        L6:
            java.lang.Class<com.google.android.libraries.directboot.DirectBootUtils> r0 = com.google.android.libraries.directboot.DirectBootUtils.class
            monitor-enter(r0)
            boolean r2 = com.google.android.libraries.directboot.DirectBootUtils.isUserUnlocked     // Catch: java.lang.Throwable -> L52
            if (r2 == 0) goto Lf
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L52
            return r1
        Lf:
            r2 = r1
        L10:
            r3 = 2
            r4 = 0
            r5 = 0
            if (r2 > r3) goto L48
            android.os.UserManager r3 = com.google.android.libraries.directboot.DirectBootUtils.userManager     // Catch: java.lang.Throwable -> L52
            if (r3 != 0) goto L23
            java.lang.Class<android.os.UserManager> r3 = android.os.UserManager.class
            java.lang.Object r3 = r7.getSystemService(r3)     // Catch: java.lang.Throwable -> L52
            android.os.UserManager r3 = (android.os.UserManager) r3     // Catch: java.lang.Throwable -> L52
            com.google.android.libraries.directboot.DirectBootUtils.userManager = r3     // Catch: java.lang.Throwable -> L52
        L23:
            android.os.UserManager r3 = com.google.android.libraries.directboot.DirectBootUtils.userManager     // Catch: java.lang.Throwable -> L52
            if (r3 != 0) goto L29
            r5 = r1
            goto L4c
        L29:
            boolean r6 = androidx.core.view.ViewCompat$$ExternalSyntheticApiModelOutline0.m(r3)     // Catch: java.lang.NullPointerException -> L3b java.lang.Throwable -> L52
            if (r6 != 0) goto L39
            android.os.UserHandle r6 = android.os.Process.myUserHandle()     // Catch: java.lang.NullPointerException -> L3b java.lang.Throwable -> L52
            boolean r7 = r3.isUserRunning(r6)     // Catch: java.lang.NullPointerException -> L3b java.lang.Throwable -> L52
            if (r7 != 0) goto L48
        L39:
            r5 = r1
            goto L48
        L3b:
            r3 = move-exception
            java.lang.String r5 = "DirectBootUtils"
            java.lang.String r6 = "Failed to check if user is unlocked."
            android.util.Log.w(r5, r6, r3)     // Catch: java.lang.Throwable -> L52
            com.google.android.libraries.directboot.DirectBootUtils.userManager = r4     // Catch: java.lang.Throwable -> L52
            int r2 = r2 + 1
            goto L10
        L48:
            if (r5 == 0) goto L4c
            com.google.android.libraries.directboot.DirectBootUtils.userManager = r4     // Catch: java.lang.Throwable -> L52
        L4c:
            if (r5 == 0) goto L50
            com.google.android.libraries.directboot.DirectBootUtils.isUserUnlocked = r1     // Catch: java.lang.Throwable -> L52
        L50:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L52
            return r5
        L52:
            r7 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L52
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.directboot.DirectBootUtils.checkUserUnlocked(android.content.Context):boolean");
    }

    public static Context getDeviceProtectedStorageContextOrFallback(Context context) {
        boolean isDeviceProtectedStorage;
        Context createDeviceProtectedStorageContext;
        isDeviceProtectedStorage = context.isDeviceProtectedStorage();
        if (isDeviceProtectedStorage) {
            return context;
        }
        createDeviceProtectedStorageContext = context.createDeviceProtectedStorageContext();
        return createDeviceProtectedStorageContext;
    }

    public static boolean isDirectBoot(Context context) {
        if (!checkUserUnlocked(context)) {
            return true;
        }
        return false;
    }

    public static boolean isUserUnlocked(Context context) {
        if (checkUserUnlocked(context)) {
            return true;
        }
        return false;
    }

    public static ListenableFuture runWhenUnlocked(Context context, Runnable runnable) {
        if (isUserUnlocked(context)) {
            runnable.run();
            return ImmediateFuture.NULL;
        }
        return DropDownListView.Api33Impl.getFuture(new AiCoreBaseService$$ExternalSyntheticLambda4(runnable, context, 2));
    }
}
