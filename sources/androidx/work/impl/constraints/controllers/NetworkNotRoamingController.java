package androidx.work.impl.constraints.controllers;

import androidx.work.Logger;
import androidx.work.impl.constraints.NetworkState;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkNotRoamingController extends BaseConstraintController {
    static {
        Logger.tagWithPrefix("NetworkNotRoamingCtrlr");
    }

    public NetworkNotRoamingController(ConstraintTracker constraintTracker) {
        super(constraintTracker);
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public final int getReason() {
        return 7;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public final boolean hasConstraint(WorkSpec workSpec) {
        if (workSpec.constraints.requiredNetworkType$ar$edu == 4) {
            return true;
        }
        return false;
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public final /* bridge */ /* synthetic */ boolean isConstrained(Object obj) {
        NetworkState networkState = (NetworkState) obj;
        networkState.getClass();
        if (networkState.isConnected && networkState.isNotRoaming) {
            return false;
        }
        return true;
    }
}
