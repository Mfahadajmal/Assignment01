package androidx.work.impl.constraints.trackers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.work.Logger;
import androidx.work.impl.utils.taskexecutor.WorkManagerTaskExecutor;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BroadcastReceiverConstraintTracker extends ConstraintTracker {
    private final BroadcastReceiver broadcastReceiver;

    public BroadcastReceiverConstraintTracker(Context context, WorkManagerTaskExecutor workManagerTaskExecutor) {
        super(context, workManagerTaskExecutor);
        this.broadcastReceiver = new BroadcastReceiver() { // from class: androidx.work.impl.constraints.trackers.BroadcastReceiverConstraintTracker$broadcastReceiver$1
            @Override // android.content.BroadcastReceiver
            public final void onReceive(Context context2, Intent intent) {
                context2.getClass();
                intent.getClass();
                BroadcastReceiverConstraintTracker.this.onBroadcastReceive(intent);
            }
        };
    }

    public abstract IntentFilter getIntentFilter();

    public abstract void onBroadcastReceive(Intent intent);

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public final void startTracking() {
        Logger.get$ar$ds$16341a92_0();
        int i = BroadcastReceiverConstraintTrackerKt.BroadcastReceiverConstraintTrackerKt$ar$NoOp;
        getClass().getSimpleName();
        this.appContext.registerReceiver(this.broadcastReceiver, getIntentFilter());
    }

    @Override // androidx.work.impl.constraints.trackers.ConstraintTracker
    public final void stopTracking() {
        Logger.get$ar$ds$16341a92_0();
        int i = BroadcastReceiverConstraintTrackerKt.BroadcastReceiverConstraintTrackerKt$ar$NoOp;
        getClass().getSimpleName();
        this.appContext.unregisterReceiver(this.broadcastReceiver);
    }
}
