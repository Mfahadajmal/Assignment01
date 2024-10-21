package androidx.work.impl.utils;

import android.content.Context;
import android.os.PowerManager;
import androidx.work.Logger;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WakeLocks {
    public static final String TAG = Logger.tagWithPrefix("WakeLocks");

    public static final PowerManager.WakeLock newWakeLock(Context context, String str) {
        context.getClass();
        Object systemService = context.getApplicationContext().getSystemService("power");
        systemService.getClass();
        String concat = "WorkManager: ".concat(str);
        PowerManager.WakeLock newWakeLock = ((PowerManager) systemService).newWakeLock(1, concat);
        synchronized (WakeLocksHolder.INSTANCE) {
        }
        newWakeLock.getClass();
        return newWakeLock;
    }
}
