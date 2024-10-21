package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryChargingTracker extends BroadcastReceiverConstraintTracker {
    public BatteryChargingTracker(Context context, WorkManagerTaskExecutor workManagerTaskExecutor) {
        super(context, workManagerTaskExecutor);
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public final IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.os.action.CHARGING");
        intentFilter.addAction("android.os.action.DISCHARGING");
        return intentFilter;
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public final void onBroadcastReceive(Intent intent) {
        String action = intent.getAction();
        if (action != null) {
            Logger.get$ar$ds$16341a92_0();
            String str = BatteryChargingTrackerKt.TAG;
            switch (action.hashCode()) {
                case -1886648615:
                    if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
                        setState(false);
                        return;
                    }
                    return;
                case -54942926:
                    if (action.equals("android.os.action.DISCHARGING")) {
                        setState(false);
                        return;
                    }
                    return;
                case 948344062:
                    if (action.equals("android.os.action.CHARGING")) {
                        setState(true);
                        return;
                    }
                    return;
                case 1019184907:
                    if (action.equals("android.intent.action.ACTION_POWER_CONNECTED")) {
                        setState(true);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public final /* bridge */ /* synthetic */ Object readSystemState() {
        Intent registerReceiver = this.appContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z = false;
        if (registerReceiver == null) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(BatteryChargingTrackerKt.TAG, "getInitialState - null intent received");
            return false;
        }
        int intExtra = registerReceiver.getIntExtra("status", -1);
        if (intExtra == 2 || intExtra == 5) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
