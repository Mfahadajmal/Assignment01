package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.ArrayRow;
import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Guideline extends ConstraintWidget {
    private boolean mResolved;
    public float mRelativePercent = -1.0f;
    public int mRelativeBegin = -1;
    public int mRelativeEnd = -1;
    public ConstraintAnchor mAnchor = this.mTop;
    public int mOrientation = 0;

    public Guideline() {
        this.mAnchors.clear();
        this.mAnchors.add(this.mAnchor);
        int length = this.mListAnchors.length;
        for (int i = 0; i < 6; i++) {
            this.mListAnchors[i] = this.mAnchor;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final void addToSolver(LinearSystem linearSystem, boolean z) {
        boolean z2;
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null) {
            Object anchor$ar$edu$6d5b24e9_0 = constraintWidget.getAnchor$ar$edu$6d5b24e9_0(2);
            Object anchor$ar$edu$6d5b24e9_02 = constraintWidget.getAnchor$ar$edu$6d5b24e9_0(4);
            ConstraintWidget constraintWidget2 = this.mParent;
            boolean z3 = true;
            if (constraintWidget2 != null && constraintWidget2.mListDimensionBehaviors$ar$edu[0] == 2) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (this.mOrientation == 0) {
                anchor$ar$edu$6d5b24e9_0 = constraintWidget.getAnchor$ar$edu$6d5b24e9_0(3);
                anchor$ar$edu$6d5b24e9_02 = constraintWidget.getAnchor$ar$edu$6d5b24e9_0(5);
                ConstraintWidget constraintWidget3 = this.mParent;
                if (constraintWidget3 == null || constraintWidget3.mListDimensionBehaviors$ar$edu[1] != 2) {
                    z3 = false;
                }
                z2 = z3;
            }
            if (this.mResolved) {
                ConstraintAnchor constraintAnchor = this.mAnchor;
                if (constraintAnchor.mHasFinalValue) {
                    SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintAnchor);
                    linearSystem.addEquality(createObjectVariable, this.mAnchor.getFinalValue());
                    if (this.mRelativeBegin != -1) {
                        if (z2) {
                            linearSystem.addGreaterThan(linearSystem.createObjectVariable(anchor$ar$edu$6d5b24e9_02), createObjectVariable, 0, 5);
                        }
                    } else if (this.mRelativeEnd != -1 && z2) {
                        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(anchor$ar$edu$6d5b24e9_02);
                        linearSystem.addGreaterThan(createObjectVariable, linearSystem.createObjectVariable(anchor$ar$edu$6d5b24e9_0), 0, 5);
                        linearSystem.addGreaterThan(createObjectVariable2, createObjectVariable, 0, 5);
                    }
                    this.mResolved = false;
                    return;
                }
            }
            if (this.mRelativeBegin != -1) {
                SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(this.mAnchor);
                linearSystem.addEquality$ar$ds$e82a2b65_0(createObjectVariable3, linearSystem.createObjectVariable(anchor$ar$edu$6d5b24e9_0), this.mRelativeBegin, 8);
                if (z2) {
                    linearSystem.addGreaterThan(linearSystem.createObjectVariable(anchor$ar$edu$6d5b24e9_02), createObjectVariable3, 0, 5);
                    return;
                }
                return;
            }
            if (this.mRelativeEnd != -1) {
                SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(this.mAnchor);
                SolverVariable createObjectVariable5 = linearSystem.createObjectVariable(anchor$ar$edu$6d5b24e9_02);
                linearSystem.addEquality$ar$ds$e82a2b65_0(createObjectVariable4, createObjectVariable5, -this.mRelativeEnd, 8);
                if (z2) {
                    linearSystem.addGreaterThan(createObjectVariable4, linearSystem.createObjectVariable(anchor$ar$edu$6d5b24e9_0), 0, 5);
                    linearSystem.addGreaterThan(createObjectVariable5, createObjectVariable4, 0, 5);
                    return;
                }
                return;
            }
            if (this.mRelativePercent != -1.0f) {
                SolverVariable createObjectVariable6 = linearSystem.createObjectVariable(this.mAnchor);
                SolverVariable createObjectVariable7 = linearSystem.createObjectVariable(anchor$ar$edu$6d5b24e9_02);
                float f = this.mRelativePercent;
                ArrayRow createRow = linearSystem.createRow();
                createRow.variables$ar$class_merging.put(createObjectVariable6, -1.0f);
                createRow.variables$ar$class_merging.put(createObjectVariable7, f);
                linearSystem.addConstraint(createRow);
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final boolean allowedInBarrier() {
        return true;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final ConstraintAnchor getAnchor$ar$edu$6d5b24e9_0(int i) {
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

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final boolean isResolvedHorizontally() {
        return this.mResolved;
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final boolean isResolvedVertically() {
        return this.mResolved;
    }

    public final void setFinalValue(int i) {
        this.mAnchor.setFinalValue(i);
        this.mResolved = true;
    }

    public final void setOrientation(int i) {
        if (this.mOrientation != i) {
            this.mOrientation = i;
            this.mAnchors.clear();
            if (this.mOrientation == 1) {
                this.mAnchor = this.mLeft;
            } else {
                this.mAnchor = this.mTop;
            }
            this.mAnchors.add(this.mAnchor);
            int length = this.mListAnchors.length;
            for (int i2 = 0; i2 < 6; i2++) {
                this.mListAnchors[i2] = this.mAnchor;
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final void updateFromSolver(LinearSystem linearSystem, boolean z) {
        if (this.mParent == null) {
            return;
        }
        int objectVariableValue$ar$ds$8c6d81d4_0 = LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(this.mAnchor);
        if (this.mOrientation == 1) {
            this.mX = objectVariableValue$ar$ds$8c6d81d4_0;
            this.mY = 0;
            setHeight(this.mParent.getHeight());
            setWidth(0);
            return;
        }
        this.mX = 0;
        this.mY = objectVariableValue$ar$ds$8c6d81d4_0;
        setWidth(this.mParent.getWidth());
        setHeight(0);
    }
}
