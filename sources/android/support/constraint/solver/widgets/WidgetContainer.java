package android.support.constraint.solver.widgets;

import com.google.common.util.concurrent.ExecutionList;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class WidgetContainer extends ConstraintWidget {
    public final ArrayList mChildren = new ArrayList();

    public void layout() {
        updateDrawPosition();
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
        constraintWidget.mParent = null;
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public void reset() {
        this.mChildren.clear();
        super.reset();
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public final void resetSolverVariables$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        super.resetSolverVariables$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(runnableExecutorPair);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).resetSolverVariables$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(runnableExecutorPair);
        }
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public final void setOffset(int i, int i2) {
        super.setOffset(i, i2);
        int size = this.mChildren.size();
        for (int i3 = 0; i3 < size; i3++) {
            ((ConstraintWidget) this.mChildren.get(i3)).setOffset(this.mX + this.mOffsetX, this.mY + this.mOffsetY);
        }
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public final void updateDrawPosition() {
        super.updateDrawPosition();
        ArrayList arrayList = this.mChildren;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ConstraintWidget constraintWidget = (ConstraintWidget) this.mChildren.get(i);
                constraintWidget.setOffset(getDrawX(), getDrawY());
                if (!(constraintWidget instanceof ConstraintWidgetContainer)) {
                    constraintWidget.updateDrawPosition();
                }
            }
        }
    }
}
