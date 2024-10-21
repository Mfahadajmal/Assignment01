package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageNotLowTracker extends BroadcastReceiverConstraintTracker {
    public StorageNotLowTracker(Context context, WorkManagerTaskExecutor workManagerTaskExecutor) {
        super(context, workManagerTaskExecutor);
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public final IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_OK");
        intentFilter.addAction("android.intent.action.DEVICE_STORAGE_LOW");
        return intentFilter;
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public final void onBroadcastReceive(Intent intent) {
        if (intent.getAction() != null) {
            Logger.get$ar$ds$16341a92_0();
            int i = StorageNotLowTrackerKt.StorageNotLowTrackerKt$ar$NoOp;
            intent.getAction();
            String action = intent.getAction();
            if (action != null) {
                int hashCode = action.hashCode();
                if (hashCode != -1181163412) {
                    if (hashCode == -730838620 && action.equals("android.intent.action.DEVICE_STORAGE_OK")) {
                        setState(true);
                        return;
                    }
                    return;
                }
                if (action.equals("android.intent.action.DEVICE_STORAGE_LOW")) {
                    setState(false);
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0031, code lost:
    
        if (r0.equals("android.intent.action.DEVICE_STORAGE_OK") == false) goto L18;
     */
    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* bridge */ /* synthetic */ java.lang.Object readSystemState() {
        /*
            r5 = this;
            android.content.IntentFilter r0 = r5.getIntentFilter()
            android.content.Context r1 = r5.appContext
            r2 = 0
            android.content.Intent r0 = r1.registerReceiver(r2, r0)
            r1 = 1
            if (r0 == 0) goto L3a
            java.lang.String r2 = r0.getAction()
            if (r2 != 0) goto L15
            goto L3a
        L15:
            java.lang.String r0 = r0.getAction()
            r2 = 0
            if (r0 == 0) goto L39
            int r3 = r0.hashCode()
            r4 = -1181163412(0xffffffffb998e06c, float:-2.9158907E-4)
            if (r3 == r4) goto L34
            r4 = -730838620(0xffffffffd47049a4, float:-4.12811054E12)
            if (r3 == r4) goto L2b
            goto L39
        L2b:
            java.lang.String r3 = "android.intent.action.DEVICE_STORAGE_OK"
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L3a
            goto L39
        L34:
            java.lang.String r1 = "android.intent.action.DEVICE_STORAGE_LOW"
            r0.equals(r1)
        L39:
            r1 = r2
        L3a:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.constraints.trackers.StorageNotLowTracker.readSystemState():java.lang.Object");
    }
}
