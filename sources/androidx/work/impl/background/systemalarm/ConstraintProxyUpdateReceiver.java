package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0;
import androidx.work.Logger;
import androidx.work.impl.WorkManagerImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConstraintProxyUpdateReceiver extends BroadcastReceiver {
    public static final /* synthetic */ int ConstraintProxyUpdateReceiver$ar$NoOp = 0;
    static final String TAG = Logger.tagWithPrefix("ConstrntProxyUpdtRecvr");

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        String str;
        if (intent != null) {
            str = intent.getAction();
        } else {
            str = null;
        }
        if (!"androidx.work.impl.background.systemalarm.UpdateProxies".equals(str)) {
            Logger.get$ar$ds$16341a92_0();
        } else {
            WorkManagerImpl.getInstance(context).mWorkTaskExecutor$ar$class_merging.executeOnTaskThread(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0(intent, context, goAsync(), 8));
        }
    }
}
