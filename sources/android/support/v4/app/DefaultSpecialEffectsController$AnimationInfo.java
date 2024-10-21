package android.support.v4.app;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.support.v4.app.SpecialEffectsController;
import androidx.work.impl.model.WorkName;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultSpecialEffectsController$AnimationInfo extends DefaultSpecialEffectsController$SpecialEffectsInfo {
    private WorkName animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private boolean isAnimLoaded;
    private final boolean isPop;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultSpecialEffectsController$AnimationInfo(SpecialEffectsController.Operation operation, boolean z) {
        super(operation);
        operation.getClass();
        this.isPop = z;
    }

    public final WorkName getAnimation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(Context context) {
        boolean z;
        if (this.isAnimLoaded) {
            return this.animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        }
        SpecialEffectsController.Operation operation = this.operation;
        if (operation.finalState$ar$edu == 2) {
            z = true;
        } else {
            z = false;
        }
        WorkName loadAnimation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = _BOUNDARY.loadAnimation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(context, operation.fragment, z, this.isPop);
        this.animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = loadAnimation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
        this.isAnimLoaded = true;
        return loadAnimation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }
}
