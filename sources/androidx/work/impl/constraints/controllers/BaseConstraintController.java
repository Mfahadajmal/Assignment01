package androidx.work.impl.constraints.controllers;

import androidx.room.TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1;
import androidx.work.Constraints;
import androidx.work.impl.constraints.trackers.ConstraintTracker;
import androidx.work.impl.model.WorkSpec;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.CallbackFlowBuilder;
import kotlinx.coroutines.flow.Flow;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BaseConstraintController implements ConstraintController {
    public final ConstraintTracker tracker;

    public BaseConstraintController(ConstraintTracker constraintTracker) {
        this.tracker = constraintTracker;
    }

    public abstract int getReason();

    public boolean isConstrained(Object obj) {
        throw null;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public final boolean isCurrentlyConstrained(WorkSpec workSpec) {
        if (hasConstraint(workSpec) && isConstrained(this.tracker.readSystemState())) {
            return true;
        }
        return false;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public final Flow track(Constraints constraints) {
        constraints.getClass();
        return new CallbackFlowBuilder(new TriggerBasedInvalidationTracker$notifyInvalidatedObservers$2$invalidatedTableIds$1(this, (Continuation) null, 3));
    }
}
