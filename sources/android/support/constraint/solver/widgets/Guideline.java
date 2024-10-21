package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Guideline extends ConstraintWidget {
    public float mRelativePercent = -1.0f;
    public int mRelativeBegin = -1;
    public int mRelativeEnd = -1;
    private ConstraintAnchor mAnchor = this.mTop;
    public int mOrientation = 0;

    public Guideline() {
        this.mAnchors.clear();
        this.mAnchors.add(this.mAnchor);
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public final void addToSolver$ar$ds(LinearSystem linearSystem) {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null) {
            ConstraintAnchor anchor$ar$edu = constraintWidget.getAnchor$ar$edu(2);
            ConstraintAnchor anchor$ar$edu2 = constraintWidget.getAnchor$ar$edu(4);
            if (this.mOrientation == 0) {
                anchor$ar$edu = constraintWidget.getAnchor$ar$edu(3);
                anchor$ar$edu2 = constraintWidget.getAnchor$ar$edu(5);
            }
            if (this.mRelativeBegin != -1) {
                linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, linearSystem.createObjectVariable(this.mAnchor), linearSystem.createObjectVariable(anchor$ar$edu), this.mRelativeBegin, false));
                return;
            }
            if (this.mRelativeEnd != -1) {
                linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, linearSystem.createObjectVariable(this.mAnchor), linearSystem.createObjectVariable(anchor$ar$edu2), -this.mRelativeEnd, false));
                return;
            }
            if (this.mRelativePercent != -1.0f) {
                SolverVariable createObjectVariable = linearSystem.createObjectVariable(this.mAnchor);
                SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(anchor$ar$edu);
                SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(anchor$ar$edu2);
                float f = this.mRelativePercent;
                ArrayRow createRow = linearSystem.createRow();
                createRow.variables.put(createObjectVariable, -1.0f);
                createRow.variables.put(createObjectVariable2, 1.0f - f);
                createRow.variables.put(createObjectVariable3, f);
                linearSystem.addConstraint(createRow);
            }
        }
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public final ConstraintAnchor getAnchor$ar$edu(int i) {
        int i2 = i - 1;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        return null;
                    }
                }
            }
            if (this.mOrientation != 0) {
                return null;
            }
            return this.mAnchor;
        }
        if (this.mOrientation != 1) {
            return null;
        }
        return this.mAnchor;
    }

    public final void setOrientation(int i) {
        if (this.mOrientation == i) {
            return;
        }
        this.mOrientation = i;
        this.mAnchors.clear();
        if (this.mOrientation == 1) {
            this.mAnchor = this.mLeft;
        } else {
            this.mAnchor = this.mTop;
        }
        this.mAnchors.add(this.mAnchor);
    }

    @Override // android.support.constraint.solver.widgets.ConstraintWidget
    public final void updateFromSolver$ar$ds(LinearSystem linearSystem) {
        if (this.mParent == null) {
            return;
        }
        int objectVariableValue$ar$ds = LinearSystem.getObjectVariableValue$ar$ds(this.mAnchor);
        if (this.mOrientation == 1) {
            this.mX = objectVariableValue$ar$ds;
            this.mY = 0;
            setHeight(this.mParent.getHeight());
            setWidth(0);
            return;
        }
        this.mX = 0;
        this.mY = objectVariableValue$ar$ds;
        setWidth(this.mParent.getWidth());
        setHeight(0);
    }
}
