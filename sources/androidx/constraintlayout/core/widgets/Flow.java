package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Flow extends VirtualLayout {
    private final ArrayList mChainList = new ArrayList();

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final void addToSolver(LinearSystem linearSystem, boolean z) {
        super.addToSolver(linearSystem, z);
        if (this.mChainList.size() <= 0) {
            return;
        }
        throw null;
    }
}
