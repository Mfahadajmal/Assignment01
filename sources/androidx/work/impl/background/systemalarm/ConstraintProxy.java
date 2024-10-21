package androidx.work.impl.background.systemalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.work.Logger;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
class ConstraintProxy extends BroadcastReceiver {
    public static final /* synthetic */ int ConstraintProxy$ar$NoOp = 0;
    private static final String TAG = Logger.tagWithPrefix("ConstraintProxy");

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class BatteryChargingProxy extends ConstraintProxy {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class BatteryNotLowProxy extends ConstraintProxy {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class NetworkStateProxy extends ConstraintProxy {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class StorageNotLowProxy extends ConstraintProxy {
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        Logger.get$ar$ds$16341a92_0();
        Objects.toString(intent);
        context.startService(CommandHandler.createConstraintsChangedIntent(context));
    }
}
