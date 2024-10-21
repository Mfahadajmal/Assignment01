package androidx.work.impl.constraints;

import android.net.ConnectivityManager;
import androidx.work.Constraints;
import androidx.work.impl.constraints.controllers.ConstraintController;
import androidx.work.impl.model.WorkSpec;
import kotlinx.coroutines.flow.CallbackFlowBuilder;
import kotlinx.coroutines.flow.Flow;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NetworkRequestConstraintController implements ConstraintController {
    public final ConnectivityManager connManager;
    public final long timeoutMs;

    public NetworkRequestConstraintController(ConnectivityManager connectivityManager, long j) {
        this.connManager = connectivityManager;
        this.timeoutMs = j;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public final boolean hasConstraint(WorkSpec workSpec) {
        if (workSpec.constraints.getRequiredNetworkRequest() != null) {
            return true;
        }
        return false;
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public final boolean isCurrentlyConstrained(WorkSpec workSpec) {
        if (!hasConstraint(workSpec)) {
            return false;
        }
        throw new IllegalStateException("isCurrentlyConstrained() must never be called onNetworkRequestConstraintController. isCurrentlyConstrained() is called only on older platforms where NetworkRequest isn't supported");
    }

    @Override // androidx.work.impl.constraints.controllers.ConstraintController
    public final Flow track(Constraints constraints) {
        constraints.getClass();
        return new CallbackFlowBuilder(new NetworkRequestConstraintController$track$1(constraints, this, null));
    }
}
