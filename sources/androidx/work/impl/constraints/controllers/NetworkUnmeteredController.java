package androidx.work.impl.constraints.controllers;

import android.os.Build;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkUnmeteredController extends BaseConstraintController {
    public NetworkUnmeteredController(ConstraintTracker constraintTracker) {
        super(constraintTracker);
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public final int getReason() {
        return 7;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public final boolean hasConstraint(WorkSpec workSpec) {
        int i = workSpec.constraints.requiredNetworkType$ar$edu;
        if (i == 3) {
            return true;
        }
        if (Build.VERSION.SDK_INT >= 30 && i == 6) {
            return true;
        }
        return false;
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public final /* bridge */ /* synthetic */ boolean isConstrained(Object obj) {
        NetworkState networkState = (NetworkState) obj;
        networkState.getClass();
        if (networkState.isConnected && !networkState.isMetered) {
            return false;
        }
        return true;
    }
}
