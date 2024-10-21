package androidx.work.impl.utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import androidx.lifecycle.ViewModelStore;
import androidx.work.Logger;
import androidx.work.impl.WorkManagerImpl;
import java.util.concurrent.TimeUnit;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ForceStopRunnable implements Runnable {
    private static final String TAG = Logger.tagWithPrefix("ForceStopRunnable");
    private static final long TEN_YEARS = TimeUnit.DAYS.toMillis(3650);
    private final Context mContext;
    private final ViewModelStore mPreferenceUtils$ar$class_merging$ar$class_merging;
    private int mRetryCount = 0;
    private final WorkManagerImpl mWorkManager;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class BroadcastReceiver extends android.content.BroadcastReceiver {
        private static final String TAG = Logger.tagWithPrefix("ForceStopRunnable$Rcvr");

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent != null && "ACTION_FORCE_STOP_RESCHEDULE".equals(intent.getAction())) {
                Logger.get$ar$ds$16341a92_0();
                ForceStopRunnable.setAlarm(context);
            }
        }
    }

    public ForceStopRunnable(Context context, WorkManagerImpl workManagerImpl) {
        this.mContext = context.getApplicationContext();
        this.mWorkManager = workManagerImpl;
        this.mPreferenceUtils$ar$class_merging$ar$class_merging = workManagerImpl.mPreferenceUtils$ar$class_merging$ar$class_merging;
    }

    private static PendingIntent getPendingIntent(Context context, int i) {
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(context, (Class<?>) BroadcastReceiver.class));
        intent.setAction("ACTION_FORCE_STOP_RESCHEDULE");
        return PendingIntent.getBroadcast(context, -1, intent, i);
    }

    static void setAlarm(Context context) {
        int i;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService("alarm");
        if (Build.VERSION.SDK_INT >= 31) {
            i = 167772160;
        } else {
            i = 134217728;
        }
        PendingIntent pendingIntent = getPendingIntent(context, i);
        long currentTimeMillis = System.currentTimeMillis() + TEN_YEARS;
        if (alarmManager != null) {
            alarmManager.setExact(0, currentTimeMillis, pendingIntent);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:188:0x034a, code lost:
    
        if (r4 == false) goto L187;
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x034c, code lost:
    
        androidx.work.Logger.get$ar$ds$16341a92_0();
        r4 = r17.mWorkManager;
        androidx.work.impl.Schedulers.schedule(r4.mConfiguration, r4.mWorkDatabase, r4.mSchedulers);
     */
    /* JADX WARN: Code restructure failed: missing block: B:267:0x001e, code lost:
    
        if (r4 == false) goto L187;
     */
    /* JADX WARN: Finally extract failed */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x041e A[Catch: all -> 0x043f, TRY_LEAVE, TryCatch #17 {all -> 0x043f, blocks: (B:3:0x0006, B:5:0x0011, B:8:0x0022, B:10:0x0033, B:12:0x005e, B:14:0x0099, B:16:0x00a4, B:17:0x00b6, B:18:0x00be, B:20:0x00c4, B:23:0x00dc, B:25:0x00e2, B:26:0x00f7, B:28:0x00fd, B:29:0x010a, B:32:0x0104, B:36:0x00a9, B:37:0x010e, B:40:0x0112, B:55:0x0158, B:247:0x0160, B:58:0x0179, B:60:0x0180, B:62:0x0186, B:63:0x018a, B:65:0x0190, B:72:0x019c, B:68:0x01a2, B:75:0x01aa, B:76:0x01ae, B:78:0x01b4, B:81:0x01c0, B:84:0x01ca, B:92:0x01e8, B:95:0x01ed, B:96:0x01f1, B:118:0x01f2, B:144:0x026e, B:149:0x0279, B:209:0x028d, B:211:0x0297, B:153:0x02cc, B:156:0x02d7, B:191:0x02e5, B:161:0x02f0, B:165:0x02fb, B:167:0x0301, B:169:0x0307, B:171:0x0317, B:173:0x031b, B:175:0x0321, B:177:0x0331, B:181:0x036a, B:185:0x033a, B:189:0x034c, B:195:0x0360, B:100:0x03fa, B:111:0x0403, B:114:0x0410, B:115:0x041d, B:102:0x041e, B:105:0x0427, B:199:0x0340, B:132:0x03cb, B:133:0x03ce, B:51:0x03d2, B:52:0x03d8, B:264:0x042f, B:265:0x043e, B:266:0x0015), top: B:2:0x0006, inners: #50 }] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0403 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:155:0x02d2  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x033d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:203:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x028d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v32 */
    /* JADX WARN: Type inference failed for: r10v33, types: [int] */
    /* JADX WARN: Type inference failed for: r10v34 */
    /* JADX WARN: Type inference failed for: r10v35, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v36 */
    /* JADX WARN: Type inference failed for: r10v38, types: [boolean] */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v41 */
    /* JADX WARN: Type inference failed for: r10v42 */
    /* JADX WARN: Type inference failed for: r10v8 */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 1095
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.utils.ForceStopRunnable.run():void");
    }
}
