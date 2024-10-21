package androidx.constraintlayout.core.widgets;

import com.google.common.util.concurrent.ExecutionList;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class WidgetContainer extends ConstraintWidget {
    public ArrayList mChildren = new ArrayList();

    public void layout() {
        ArrayList arrayList = this.mChildren;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
                if (constraintWidget instanceof WidgetContainer) {
                    ((WidgetContainer) constraintWidget).layout();
                }
            }
        }
    }

    public final void remove(ConstraintWidget constraintWidget) {
        this.mChildren.remove(constraintWidget);
        constraintWidget.reset();
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public void reset() {
        this.mChildren.clear();
        super.reset();
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final void resetSolverVariables$ar$class_merging$9c63a7fc_0$ar$class_merging$ar$class_merging(ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        super.resetSolverVariables$ar$class_merging$9c63a7fc_0$ar$class_merging$ar$class_merging(runnableExecutorPair);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).resetSolverVariables$ar$class_merging$9c63a7fc_0$ar$class_merging$ar$class_merging(runnableExecutorPair);
        }
    }
}
