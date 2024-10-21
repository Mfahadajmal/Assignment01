package androidx.work.impl.constraints.trackers;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryNotLowTracker extends BroadcastReceiverConstraintTracker {
    public BatteryNotLowTracker(Context context, WorkManagerTaskExecutor workManagerTaskExecutor) {
        super(context, workManagerTaskExecutor);
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public final IntentFilter getIntentFilter() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.BATTERY_OKAY");
        intentFilter.addAction("android.intent.action.BATTERY_LOW");
        return intentFilter;
    }

    @Override // androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker
    public final void onBroadcastReceive(Intent intent) {
        if (intent.getAction() != null) {
            Logger.get$ar$ds$16341a92_0();
            String str = BatteryNotLowTrackerKt.TAG;
            intent.getAction();
            String action = intent.getAction();
            if (action != null) {
                int hashCode = action.hashCode();
                if (hashCode != -1980154005) {
                    if (hashCode == 490310653 && action.equals("android.intent.action.BATTERY_LOW")) {
                        setState(false);
                        return;
                    }
                    return;
                }
                if (action.equals("android.intent.action.BATTERY_OKAY")) {
                    setState(true);
                }
            }
        }
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public final /* bridge */ /* synthetic */ Object readSystemState() {
        Intent registerReceiver = this.appContext.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        boolean z = false;
        if (registerReceiver == null) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(BatteryNotLowTrackerKt.TAG, "getInitialState - null intent received");
            return false;
        }
        int intExtra = registerReceiver.getIntExtra("status", -1);
        float intExtra2 = registerReceiver.getIntExtra("level", -1);
        float intExtra3 = registerReceiver.getIntExtra("scale", -1);
        if (intExtra == 1 || intExtra2 / intExtra3 > 0.15f) {
            z = true;
        }
        return Boolean.valueOf(z);
    }
}
