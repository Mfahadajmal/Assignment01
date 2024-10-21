package android.support.constraint.solver.widgets;

import android.support.constraint.solver.ArrayRow;
import android.support.constraint.solver.LinearSystem;
import android.support.constraint.solver.SolverVariable;
import com.google.common.util.concurrent.ExecutionList;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConstraintWidget {
    public static final float DEFAULT_BIAS = 0.5f;
    protected final ArrayList mAnchors;
    final ConstraintAnchor mBaseline;
    public int mBaselineDistance;
    final ConstraintAnchor mBottom;
    boolean mBottomHasCentered;
    final ConstraintAnchor mCenter;
    final ConstraintAnchor mCenterX;
    final ConstraintAnchor mCenterY;
    public Object mCompanionWidget;
    public float mDimensionRatio;
    public int mDimensionRatioSide;
    int mDistToBottom;
    int mDistToLeft;
    int mDistToRight;
    int mDistToTop;
    public int mDrawHeight;
    public int mDrawWidth;
    private int mDrawX;
    private int mDrawY;
    int mHeight;
    public float mHorizontalBiasPercent;
    boolean mHorizontalChainFixedPosition;
    public int mHorizontalChainStyle;
    public int mHorizontalDimensionBehaviour$ar$edu;
    ConstraintWidget mHorizontalNextWidget;
    public float mHorizontalWeight;
    boolean mHorizontalWrapVisited;
    final ConstraintAnchor mLeft;
    boolean mLeftHasCentered;
    protected int mMinHeight;
    protected int mMinWidth;
    protected int mOffsetX;
    protected int mOffsetY;
    public ConstraintWidget mParent;
    final ConstraintAnchor mRight;
    boolean mRightHasCentered;
    final ConstraintAnchor mTop;
    boolean mTopHasCentered;
    public float mVerticalBiasPercent;
    boolean mVerticalChainFixedPosition;
    public int mVerticalChainStyle;
    public int mVerticalDimensionBehaviour$ar$edu;
    ConstraintWidget mVerticalNextWidget;
    public float mVerticalWeight;
    boolean mVerticalWrapVisited;
    public int mVisibility;
    int mWidth;
    public int mWrapHeight;
    public int mWrapWidth;
    public int mX;
    public int mY;
    public int mHorizontalResolution = -1;
    public int mVerticalResolution = -1;
    public int mMatchConstraintDefaultWidth = 0;
    public int mMatchConstraintDefaultHeight = 0;
    public int mMatchConstraintMinWidth = 0;
    public int mMatchConstraintMaxWidth = 0;
    public int mMatchConstraintMinHeight = 0;
    public int mMatchConstraintMaxHeight = 0;

    public ConstraintWidget() {
        ConstraintAnchor constraintAnchor = new ConstraintAnchor(this, 2);
        this.mLeft = constraintAnchor;
        ConstraintAnchor constraintAnchor2 = new ConstraintAnchor(this, 3);
        this.mTop = constraintAnchor2;
        ConstraintAnchor constraintAnchor3 = new ConstraintAnchor(this, 4);
        this.mRight = constraintAnchor3;
        ConstraintAnchor constraintAnchor4 = new ConstraintAnchor(this, 5);
        this.mBottom = constraintAnchor4;
        ConstraintAnchor constraintAnchor5 = new ConstraintAnchor(this, 6);
        this.mBaseline = constraintAnchor5;
        ConstraintAnchor constraintAnchor6 = new ConstraintAnchor(this, 8);
        this.mCenterX = constraintAnchor6;
        ConstraintAnchor constraintAnchor7 = new ConstraintAnchor(this, 9);
        this.mCenterY = constraintAnchor7;
        this.mCenter = new ConstraintAnchor(this, 7);
        ArrayList arrayList = new ArrayList();
        this.mAnchors = arrayList;
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = 0.5f;
        this.mVerticalBiasPercent = 0.5f;
        this.mHorizontalDimensionBehaviour$ar$edu = 1;
        this.mVerticalDimensionBehaviour$ar$edu = 1;
        this.mVisibility = 0;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalWeight = 0.0f;
        this.mVerticalWeight = 0.0f;
        this.mHorizontalNextWidget = null;
        this.mVerticalNextWidget = null;
        arrayList.add(constraintAnchor);
        arrayList.add(constraintAnchor2);
        arrayList.add(constraintAnchor3);
        arrayList.add(constraintAnchor4);
        arrayList.add(constraintAnchor6);
        arrayList.add(constraintAnchor7);
        arrayList.add(constraintAnchor5);
    }

    private final void applyConstraints(LinearSystem linearSystem, boolean z, boolean z2, ConstraintAnchor constraintAnchor, ConstraintAnchor constraintAnchor2, int i, int i2, int i3, int i4, float f, boolean z3, boolean z4, int i5, int i6, int i7) {
        int i8 = i6;
        SolverVariable createObjectVariable = linearSystem.createObjectVariable(constraintAnchor);
        SolverVariable createObjectVariable2 = linearSystem.createObjectVariable(constraintAnchor2);
        SolverVariable createObjectVariable3 = linearSystem.createObjectVariable(constraintAnchor.mTarget);
        SolverVariable createObjectVariable4 = linearSystem.createObjectVariable(constraintAnchor2.mTarget);
        int margin = constraintAnchor.getMargin();
        int margin2 = constraintAnchor2.getMargin();
        int i9 = this.mVisibility;
        boolean z5 = i9 != 8;
        int i10 = i9 == 8 ? 0 : i3;
        boolean z6 = (!z5) | z2;
        if (createObjectVariable3 == null && createObjectVariable4 == null) {
            ArrayRow createRow = linearSystem.createRow();
            createRow.createRowEquals$ar$ds(createObjectVariable, i);
            linearSystem.addConstraint(createRow);
            if (z3) {
                return;
            }
            if (z) {
                linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
                return;
            } else {
                if (z6) {
                    linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i10, false));
                    return;
                }
                ArrayRow createRow2 = linearSystem.createRow();
                createRow2.createRowEquals$ar$ds(createObjectVariable2, i2);
                linearSystem.addConstraint(createRow2);
                return;
            }
        }
        if (createObjectVariable3 != null && createObjectVariable4 == null) {
            ArrayRow createRow3 = linearSystem.createRow();
            createRow3.createRowEquals$ar$ds$b19c6a08_0(createObjectVariable, createObjectVariable3, margin);
            linearSystem.addConstraint(createRow3);
            if (z) {
                linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
                return;
            }
            if (z3) {
                return;
            }
            if (z6) {
                ArrayRow createRow4 = linearSystem.createRow();
                createRow4.createRowEquals$ar$ds$b19c6a08_0(createObjectVariable2, createObjectVariable, i10);
                linearSystem.addConstraint(createRow4);
                return;
            } else {
                ArrayRow createRow5 = linearSystem.createRow();
                createRow5.createRowEquals$ar$ds(createObjectVariable2, i2);
                linearSystem.addConstraint(createRow5);
                return;
            }
        }
        if (createObjectVariable3 == null) {
            ArrayRow createRow6 = linearSystem.createRow();
            createRow6.createRowEquals$ar$ds$b19c6a08_0(createObjectVariable2, createObjectVariable4, -margin2);
            linearSystem.addConstraint(createRow6);
            if (z) {
                linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
                return;
            }
            if (z3) {
                return;
            }
            if (z6) {
                ArrayRow createRow7 = linearSystem.createRow();
                createRow7.createRowEquals$ar$ds$b19c6a08_0(createObjectVariable2, createObjectVariable, i10);
                linearSystem.addConstraint(createRow7);
                return;
            } else {
                ArrayRow createRow8 = linearSystem.createRow();
                createRow8.createRowEquals$ar$ds(createObjectVariable, i);
                linearSystem.addConstraint(createRow8);
                return;
            }
        }
        if (!z6) {
            if (z3) {
                linearSystem.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 3);
                linearSystem.addLowerThan(createObjectVariable2, createObjectVariable4, -margin2, 3);
                linearSystem.addConstraint(LinearSystem.createRowCentering(linearSystem, createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, true));
                return;
            }
            if (z4) {
                return;
            }
            int i11 = -margin2;
            if (i5 == 1) {
                if (i8 <= i10) {
                    i8 = i10;
                }
                if (i7 > 0) {
                    if (i7 < i8) {
                        i8 = i7;
                    } else {
                        linearSystem.addLowerThan(createObjectVariable2, createObjectVariable, i7, 3);
                    }
                }
                linearSystem.addEquality$ar$ds(createObjectVariable2, createObjectVariable, i8, 3);
                linearSystem.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 2);
                linearSystem.addLowerThan(createObjectVariable2, createObjectVariable4, i11, 2);
                linearSystem.addCentering$ar$ds(createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2);
                return;
            }
            if (i8 == 0 && i7 == 0) {
                ArrayRow createRow9 = linearSystem.createRow();
                createRow9.createRowEquals$ar$ds$b19c6a08_0(createObjectVariable, createObjectVariable3, margin);
                linearSystem.addConstraint(createRow9);
                ArrayRow createRow10 = linearSystem.createRow();
                createRow10.createRowEquals$ar$ds$b19c6a08_0(createObjectVariable2, createObjectVariable4, i11);
                linearSystem.addConstraint(createRow10);
                return;
            }
            if (i7 > 0) {
                linearSystem.addLowerThan(createObjectVariable2, createObjectVariable, i7, 3);
            }
            linearSystem.addGreaterThan(createObjectVariable, createObjectVariable3, margin, 2);
            linearSystem.addLowerThan(createObjectVariable2, createObjectVariable4, i11, 2);
            linearSystem.addCentering$ar$ds(createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2);
            return;
        }
        if (z) {
            linearSystem.addConstraint(LinearSystem.createRowEquals(linearSystem, createObjectVariable2, createObjectVariable, i4, true));
        } else {
            ArrayRow createRow11 = linearSystem.createRow();
            createRow11.createRowEquals$ar$ds$b19c6a08_0(createObjectVariable2, createObjectVariable, i10);
            linearSystem.addConstraint(createRow11);
        }
        int i12 = constraintAnchor.mStrength$ar$edu;
        if (i12 != constraintAnchor2.mStrength$ar$edu) {
            int i13 = -margin2;
            if (i12 == 2) {
                ArrayRow createRow12 = linearSystem.createRow();
                createRow12.createRowEquals$ar$ds$b19c6a08_0(createObjectVariable, createObjectVariable3, margin);
                linearSystem.addConstraint(createRow12);
                SolverVariable createSlackVariable = linearSystem.createSlackVariable();
                ArrayRow createRow13 = linearSystem.createRow();
                createRow13.createRowLowerThan$ar$ds(createObjectVariable2, createObjectVariable4, createSlackVariable, i13);
                linearSystem.addConstraint(createRow13);
                return;
            }
            SolverVariable createSlackVariable2 = linearSystem.createSlackVariable();
            ArrayRow createRow14 = linearSystem.createRow();
            createRow14.createRowGreaterThan$ar$ds(createObjectVariable, createObjectVariable3, createSlackVariable2, margin);
            linearSystem.addConstraint(createRow14);
            ArrayRow createRow15 = linearSystem.createRow();
            createRow15.createRowEquals$ar$ds$b19c6a08_0(createObjectVariable2, createObjectVariable4, i13);
            linearSystem.addConstraint(createRow15);
            return;
        }
        if (createObjectVariable3 == createObjectVariable4) {
            linearSystem.addConstraint(LinearSystem.createRowCentering(linearSystem, createObjectVariable, createObjectVariable3, 0, 0.5f, createObjectVariable4, createObjectVariable2, 0, true));
            return;
        }
        if (z4) {
            return;
        }
        int i14 = -margin2;
        int i15 = constraintAnchor.mConnectionType$ar$edu;
        SolverVariable createSlackVariable3 = linearSystem.createSlackVariable();
        ArrayRow createRow16 = linearSystem.createRow();
        createRow16.createRowGreaterThan$ar$ds(createObjectVariable, createObjectVariable3, createSlackVariable3, margin);
        if (i15 != 2) {
            linearSystem.addSingleError(createRow16, (int) (-createRow16.variables.get(createSlackVariable3)));
        }
        linearSystem.addConstraint(createRow16);
        int i16 = constraintAnchor2.mConnectionType$ar$edu;
        SolverVariable createSlackVariable4 = linearSystem.createSlackVariable();
        ArrayRow createRow17 = linearSystem.createRow();
        createRow17.createRowLowerThan$ar$ds(createObjectVariable2, createObjectVariable4, createSlackVariable4, i14);
        if (i16 != 2) {
            linearSystem.addSingleError(createRow17, (int) (-createRow17.variables.get(createSlackVariable4)));
        }
        linearSystem.addConstraint(createRow17);
        linearSystem.addConstraint(LinearSystem.createRowCentering(linearSystem, createObjectVariable, createObjectVariable3, margin, f, createObjectVariable4, createObjectVariable2, margin2, false));
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x01ef, code lost:
    
        if (r8 == (-1)) goto L140;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x01fe  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0209  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x02e4  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0459  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x03bf  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x01f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addToSolver$ar$ds(android.support.constraint.solver.LinearSystem r38) {
        /*
            Method dump skipped, instructions count: 1219
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidget.addToSolver$ar$ds(android.support.constraint.solver.LinearSystem):void");
    }

    public ConstraintAnchor getAnchor$ar$edu(int i) {
        switch (i - 1) {
            case 1:
                return this.mLeft;
            case 2:
                return this.mTop;
            case 3:
                return this.mRight;
            case 4:
                return this.mBottom;
            case 5:
                return this.mBaseline;
            case 6:
            default:
                return this.mCenter;
            case 7:
                return this.mCenterX;
            case 8:
                return this.mCenterY;
        }
    }

    public final int getBottom() {
        return this.mY + this.mHeight;
    }

    public final int getDrawX() {
        return this.mDrawX + this.mOffsetX;
    }

    public final int getDrawY() {
        return this.mDrawY + this.mOffsetY;
    }

    public final int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public final int getOptimizerWrapHeight() {
        int i = this.mHeight;
        if (this.mVerticalDimensionBehaviour$ar$edu == 3) {
            if (this.mMatchConstraintDefaultHeight == 1) {
                i = Math.max(this.mMatchConstraintMinHeight, i);
            } else {
                i = this.mMatchConstraintMinHeight;
                if (i > 0) {
                    this.mHeight = i;
                } else {
                    i = 0;
                }
            }
            int i2 = this.mMatchConstraintMaxHeight;
            if (i2 > 0 && i2 < i) {
                return i2;
            }
        }
        return i;
    }

    public final int getOptimizerWrapWidth() {
        int i = this.mWidth;
        if (this.mHorizontalDimensionBehaviour$ar$edu == 3) {
            if (this.mMatchConstraintDefaultWidth == 1) {
                i = Math.max(this.mMatchConstraintMinWidth, i);
            } else {
                i = this.mMatchConstraintMinWidth;
                if (i > 0) {
                    this.mWidth = i;
                } else {
                    i = 0;
                }
            }
            int i2 = this.mMatchConstraintMaxWidth;
            if (i2 > 0 && i2 < i) {
                return i2;
            }
        }
        return i;
    }

    public final int getRight() {
        return this.mX + this.mWidth;
    }

    public final int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public final boolean hasBaseline() {
        if (this.mBaselineDistance > 0) {
            return true;
        }
        return false;
    }

    public final void immediateConnect$ar$edu(int i, ConstraintWidget constraintWidget, int i2, int i3, int i4) {
        getAnchor$ar$edu(i).connect$ar$edu$ar$ds(constraintWidget.getAnchor$ar$edu(i2), i3, i4, 2, 0, true);
    }

    public final boolean isRoot() {
        if (this.mParent == null) {
            return true;
        }
        return false;
    }

    public void reset() {
        this.mLeft.reset();
        this.mTop.reset();
        this.mRight.reset();
        this.mBottom.reset();
        this.mBaseline.reset();
        this.mCenterX.reset();
        this.mCenterY.reset();
        this.mCenter.reset();
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mDrawX = 0;
        this.mDrawY = 0;
        this.mDrawWidth = 0;
        this.mDrawHeight = 0;
        this.mOffsetX = 0;
        this.mOffsetY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mWrapWidth = 0;
        this.mWrapHeight = 0;
        this.mHorizontalBiasPercent = 0.5f;
        this.mVerticalBiasPercent = 0.5f;
        this.mHorizontalDimensionBehaviour$ar$edu = 1;
        this.mVerticalDimensionBehaviour$ar$edu = 1;
        this.mCompanionWidget = null;
        this.mVisibility = 0;
        this.mHorizontalWrapVisited = false;
        this.mVerticalWrapVisited = false;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mHorizontalChainFixedPosition = false;
        this.mVerticalChainFixedPosition = false;
        this.mHorizontalWeight = 0.0f;
        this.mVerticalWeight = 0.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
    }

    public void resetSolverVariables$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        this.mLeft.resetSolverVariable$ar$ds();
        this.mTop.resetSolverVariable$ar$ds();
        this.mRight.resetSolverVariable$ar$ds();
        this.mBottom.resetSolverVariable$ar$ds();
        this.mBaseline.resetSolverVariable$ar$ds();
        this.mCenter.resetSolverVariable$ar$ds();
        this.mCenterX.resetSolverVariable$ar$ds();
        this.mCenterY.resetSolverVariable$ar$ds();
    }

    public final void setHeight(int i) {
        this.mHeight = i;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    public final void setHorizontalDimension(int i, int i2) {
        this.mX = i;
        int i3 = i2 - i;
        this.mWidth = i3;
        int i4 = this.mMinWidth;
        if (i3 < i4) {
            this.mWidth = i4;
        }
    }

    public final void setHorizontalDimensionBehaviour$ar$edu(int i) {
        this.mHorizontalDimensionBehaviour$ar$edu = i;
        if (i == 2) {
            setWidth(this.mWrapWidth);
        }
    }

    public final void setMinHeight(int i) {
        if (i < 0) {
            this.mMinHeight = 0;
        } else {
            this.mMinHeight = i;
        }
    }

    public final void setMinWidth(int i) {
        if (i < 0) {
            this.mMinWidth = 0;
        } else {
            this.mMinWidth = i;
        }
    }

    public void setOffset(int i, int i2) {
        this.mOffsetX = i;
        this.mOffsetY = i2;
    }

    public final void setVerticalDimension(int i, int i2) {
        this.mY = i;
        int i3 = i2 - i;
        this.mHeight = i3;
        int i4 = this.mMinHeight;
        if (i3 < i4) {
            this.mHeight = i4;
        }
    }

    public final void setVerticalDimensionBehaviour$ar$edu(int i) {
        this.mVerticalDimensionBehaviour$ar$edu = i;
        if (i == 2) {
            setHeight(this.mWrapHeight);
        }
    }

    public final void setWidth(int i) {
        this.mWidth = i;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public final String toString() {
        return "(" + this.mX + ", " + this.mY + ") - (" + this.mWidth + " x " + this.mHeight + ") wrap: (" + this.mWrapWidth + " x " + this.mWrapHeight + ")";
    }

    public void updateDrawPosition() {
        int i = this.mX;
        int i2 = this.mY;
        int i3 = this.mWidth + i;
        int i4 = this.mHeight + i2;
        this.mDrawX = i;
        this.mDrawY = i2;
        this.mDrawWidth = i3 - i;
        this.mDrawHeight = i4 - i2;
    }

    public void updateFromSolver$ar$ds(LinearSystem linearSystem) {
        int i;
        int i2;
        int objectVariableValue$ar$ds = LinearSystem.getObjectVariableValue$ar$ds(this.mLeft);
        int objectVariableValue$ar$ds2 = LinearSystem.getObjectVariableValue$ar$ds(this.mTop);
        int objectVariableValue$ar$ds3 = LinearSystem.getObjectVariableValue$ar$ds(this.mRight) - objectVariableValue$ar$ds;
        int objectVariableValue$ar$ds4 = LinearSystem.getObjectVariableValue$ar$ds(this.mBottom) - objectVariableValue$ar$ds2;
        this.mX = objectVariableValue$ar$ds;
        this.mY = objectVariableValue$ar$ds2;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (this.mHorizontalDimensionBehaviour$ar$edu == 1 && objectVariableValue$ar$ds3 < (i2 = this.mWidth)) {
            objectVariableValue$ar$ds3 = i2;
        }
        if (this.mVerticalDimensionBehaviour$ar$edu == 1 && objectVariableValue$ar$ds4 < (i = this.mHeight)) {
            objectVariableValue$ar$ds4 = i;
        }
        this.mWidth = objectVariableValue$ar$ds3;
        this.mHeight = objectVariableValue$ar$ds4;
        int i3 = this.mMinHeight;
        if (objectVariableValue$ar$ds4 < i3) {
            this.mHeight = i3;
        }
        int i4 = this.mMinWidth;
        if (objectVariableValue$ar$ds3 < i4) {
            this.mWidth = i4;
        }
    }
}
