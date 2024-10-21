package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Optimizer {
    static final boolean[] sFlags = new boolean[3];

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkMatchParent(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, ConstraintWidget constraintWidget) {
        constraintWidget.mHorizontalResolution = -1;
        constraintWidget.mVerticalResolution = -1;
        if (constraintWidgetContainer.mListDimensionBehaviors$ar$edu[0] != 2 && constraintWidget.mListDimensionBehaviors$ar$edu[0] == 4) {
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
            int i = constraintAnchor.mMargin;
            int width = constraintWidgetContainer.getWidth() - constraintWidget.mRight.mMargin;
            constraintAnchor.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor);
            ConstraintAnchor constraintAnchor2 = constraintWidget.mRight;
            constraintAnchor2.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor2);
            linearSystem.addEquality(constraintWidget.mLeft.mSolverVariable, i);
            linearSystem.addEquality(constraintWidget.mRight.mSolverVariable, width);
            constraintWidget.mHorizontalResolution = 2;
            constraintWidget.mX = i;
            int i2 = width - i;
            constraintWidget.mWidth = i2;
            int i3 = constraintWidget.mMinWidth;
            if (i2 < i3) {
                constraintWidget.mWidth = i3;
            }
        }
        if (constraintWidgetContainer.mListDimensionBehaviors$ar$edu[1] != 2 && constraintWidget.mListDimensionBehaviors$ar$edu[1] == 4) {
            ConstraintAnchor constraintAnchor3 = constraintWidget.mTop;
            int i4 = constraintAnchor3.mMargin;
            int height = constraintWidgetContainer.getHeight() - constraintWidget.mBottom.mMargin;
            constraintAnchor3.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor3);
            ConstraintAnchor constraintAnchor4 = constraintWidget.mBottom;
            constraintAnchor4.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor4);
            linearSystem.addEquality(constraintWidget.mTop.mSolverVariable, i4);
            linearSystem.addEquality(constraintWidget.mBottom.mSolverVariable, height);
            if (constraintWidget.mBaselineDistance > 0 || constraintWidget.mVisibility == 8) {
                ConstraintAnchor constraintAnchor5 = constraintWidget.mBaseline;
                constraintAnchor5.mSolverVariable = linearSystem.createObjectVariable(constraintAnchor5);
                linearSystem.addEquality(constraintWidget.mBaseline.mSolverVariable, constraintWidget.mBaselineDistance + i4);
            }
            constraintWidget.mVerticalResolution = 2;
            constraintWidget.mY = i4;
            int i5 = height - i4;
            constraintWidget.mHeight = i5;
            int i6 = constraintWidget.mMinHeight;
            if (i5 < i6) {
                constraintWidget.mHeight = i6;
            }
        }
    }

    public static final boolean enabled(int i, int i2) {
        if ((i & i2) == i2) {
            return true;
        }
        return false;
    }
}
