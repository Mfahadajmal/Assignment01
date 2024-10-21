package org.chromium.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Process;
import android.preference.PreferenceManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ContextUtils {
    public static Context sApplicationContext;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    final class Holder {
        public static SharedPreferences sSharedPreferences;

        static {
            StrictModeContext allowDiskWrites = StrictModeContext.allowDiskWrites();
            try {
                SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(ContextUtils.sApplicationContext);
                allowDiskWrites.close();
                sSharedPreferences = defaultSharedPreferences;
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

    public static boolean isSdkSandboxProcess() {
        boolean isSdkSandbox;
        if (Build.VERSION.SDK_INT < 33) {
            return false;
        }
        isSdkSandbox = Process.isSdkSandbox();
        return isSdkSandbox;
    }

    public static Intent registerProtectedBroadcastReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        Intent registerReceiver;
        registerReceiver = context.registerReceiver(broadcastReceiver, intentFilter, null, null, 0);
        return registerReceiver;
    }
}
