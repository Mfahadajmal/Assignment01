package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.trackers.BatteryNotLowTracker;
import androidx.work.impl.model.WorkSpec;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatteryNotLowController extends BaseConstraintController {
    public BatteryNotLowController(BatteryNotLowTracker batteryNotLowTracker) {
        super(batteryNotLowTracker);
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public final int getReason() {
        return 5;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public final boolean hasConstraint(WorkSpec workSpec) {
        return workSpec.constraints.requiresBatteryNotLow;
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public final /* bridge */ /* synthetic */ boolean isConstrained(Object obj) {
        if (!((Boolean) obj).booleanValue()) {
            return true;
        }
        return false;
    }
}
