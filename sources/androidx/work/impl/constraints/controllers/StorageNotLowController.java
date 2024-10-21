package androidx.work.impl.constraints.controllers;

import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StorageNotLowController extends BaseConstraintController {
    public StorageNotLowController(ConstraintTracker constraintTracker) {
        super(constraintTracker);
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public final int getReason() {
        return 9;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public final boolean hasConstraint(WorkSpec workSpec) {
        return workSpec.constraints.requiresStorageNotLow;
    }

    @Override // androidx.work.impl.constraints.controllers.BaseConstraintController
    public final /* bridge */ /* synthetic */ boolean isConstrained(Object obj) {
        if (!((Boolean) obj).booleanValue()) {
            return true;
        }
        return false;
    }
}
