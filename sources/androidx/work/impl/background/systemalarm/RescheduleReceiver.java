package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import androidx.work.Logger;
import androidx.work.impl.WorkManagerImpl;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public class RescheduleReceiver extends BroadcastReceiver {
    private static final String TAG = Logger.tagWithPrefix("RescheduleReceiver");

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Logger.get$ar$ds$16341a92_0();
        Objects.toString(intent);
        try {
            WorkManagerImpl workManagerImpl = WorkManagerImpl.getInstance(context);
            BroadcastReceiver.PendingResult goAsync = goAsync();
            synchronized (WorkManagerImpl.sLock) {
                BroadcastReceiver.PendingResult pendingResult = workManagerImpl.mRescheduleReceiverResult;
                if (pendingResult != null) {
                    pendingResult.finish();
                }
                workManagerImpl.mRescheduleReceiverResult = goAsync;
                if (workManagerImpl.mForceStopRunnableCompleted) {
                    workManagerImpl.mRescheduleReceiverResult.finish();
                    workManagerImpl.mRescheduleReceiverResult = null;
                }
            }
        } catch (IllegalStateException e) {
            Logger.get$ar$ds$16341a92_0();
            Log.e(TAG, "Cannot reschedule jobs. WorkManager needs to be initialized via a ContentProvider#onCreate() or an Application#onCreate().", e);
        }
    }
}
