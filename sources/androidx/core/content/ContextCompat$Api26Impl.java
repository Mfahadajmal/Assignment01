package androidx.core.content;

import _COROUTINE._BOUNDARY;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import androidx.core.app.ActivityCompat;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ContextCompat$Api26Impl {
    public static Intent registerReceiver(Context context, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String str, Handler handler, int i) {
        if ((i & 4) != 0 && str == null) {
            String concat = String.valueOf(context.getPackageName()).concat(".DYNAMIC_RECEIVER_NOT_EXPORTED_PERMISSION");
            if (ActivityCompat.Api32Impl.checkSelfPermission(context, concat) == 0) {
                return context.registerReceiver(broadcastReceiver, intentFilter, concat, handler);
            }
            throw new RuntimeException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(concat, "Permission ", " is required by your application to receive broadcasts, please add it to your manifest"));
        }
        return context.registerReceiver(broadcastReceiver, intentFilter, str, handler, i & 1);
    }

    public static ComponentName startForegroundService(Context context, Intent intent) {
        return context.startForegroundService(intent);
    }
}
