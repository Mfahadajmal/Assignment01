package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryChargingController extends BaseConstraintController {
    public BatteryChargingController(ConstraintTracker constraintTracker) {
        super(constraintTracker);
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public final int getReason() {
        return 6;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public final boolean hasConstraint(WorkSpec workSpec) {
        return workSpec.constraints.requiresCharging;
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public final /* bridge */ /* synthetic */ boolean isConstrained(Object obj) {
        if (!((Boolean) obj).booleanValue()) {
            return true;
        }
        return false;
    }
}
