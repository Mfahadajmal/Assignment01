package android.support.v4.app;

import android.support.v4.app.SpecialEffectsController;
import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DefaultSpecialEffectsController$SpecialEffectsInfo {
    public final SpecialEffectsController.Operation operation;

    public DefaultSpecialEffectsController$SpecialEffectsInfo(SpecialEffectsController.Operation operation) {
        this.operation = operation;
    }

    public final boolean isVisibilityUnchanged() {
        int i;
        View view = this.operation.fragment.mView;
        if (view != null) {
            i = SpecialEffectsController.Operation.State.Companion$ar$class_merging$243e65ba_0.asOperationState$ar$edu(view);
        } else {
            i = 0;
        }
        int i2 = this.operation.finalState$ar$edu;
        if (i != i2 && (i == 2 || i2 == 2)) {
            return false;
        }
        return true;
    }
}
