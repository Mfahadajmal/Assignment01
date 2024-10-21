package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintWidgetContainer extends WidgetContainer {
    private Snapshot mSnapshot;
    int mWrapHeight;
    int mWrapWidth;
    protected final LinearSystem mSystem = new LinearSystem();
    private int mHorizontalChainsSize = 0;
    private int mVerticalChainsSize = 0;
    private ConstraintWidget[] mMatchConstraintsChainedWidgets = new ConstraintWidget[4];
    private ConstraintWidget[] mVerticalChainsArray = new ConstraintWidget[4];
    private ConstraintWidget[] mHorizontalChainsArray = new ConstraintWidget[4];
    public int mOptimizationLevel = 2;
    private final boolean[] flags = new boolean[3];
    private final ConstraintWidget[] mChainEnds = new ConstraintWidget[4];
    public boolean mWidthMeasuredTooSmall = false;
    public boolean mHeightMeasuredTooSmall = false;

    /* JADX WARN: Removed duplicated region for block: B:278:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x051e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0520 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:80:0x04e3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void applyHorizontalChain(android.support.constraint.solver.LinearSystem r31) {
        /*
            Method dump skipped, instructions count: 1399
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.applyHorizontalChain(android.support.constraint.solver.LinearSystem):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:288:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x053e  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0540 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0503  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void applyVerticalChain(android.support.constraint.solver.LinearSystem r31) {
        /*
            Method dump skipped, instructions count: 1431
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.applyVerticalChain(android.support.constraint.solver.LinearSystem):void");
    }

    private final int countMatchConstraintsChainedWidgets(LinearSystem linearSystem, ConstraintWidget[] constraintWidgetArr, ConstraintWidget constraintWidget, int i, boolean[] zArr) {
        boolean z;
        ConstraintWidget constraintWidget2;
        int i2;
        char c;
        boolean z2;
        ConstraintWidget constraintWidget3;
        char c2;
        zArr[0] = true;
        zArr[1] = false;
        ConstraintWidget constraintWidget4 = null;
        constraintWidgetArr[0] = null;
        constraintWidgetArr[2] = null;
        constraintWidgetArr[1] = null;
        constraintWidgetArr[3] = null;
        float f = 0.0f;
        int i3 = 5;
        if (i == 0) {
            ConstraintAnchor constraintAnchor = constraintWidget.mLeft.mTarget;
            if (constraintAnchor != null && constraintAnchor.mOwner != this) {
                z2 = false;
            } else {
                z2 = true;
            }
            constraintWidget.mHorizontalNextWidget = null;
            if (constraintWidget.mVisibility != 8) {
                constraintWidget3 = constraintWidget;
            } else {
                constraintWidget3 = null;
            }
            ConstraintWidget constraintWidget5 = constraintWidget;
            i2 = 0;
            ConstraintWidget constraintWidget6 = null;
            ConstraintWidget constraintWidget7 = constraintWidget3;
            while (constraintWidget5.mRight.mTarget != null) {
                constraintWidget5.mHorizontalNextWidget = constraintWidget4;
                if (constraintWidget5.mVisibility != 8) {
                    if (constraintWidget3 == null) {
                        constraintWidget3 = constraintWidget5;
                    }
                    if (constraintWidget7 != null && constraintWidget7 != constraintWidget5) {
                        constraintWidget7.mHorizontalNextWidget = constraintWidget5;
                    }
                    constraintWidget7 = constraintWidget5;
                } else {
                    ConstraintAnchor constraintAnchor2 = constraintWidget5.mLeft;
                    linearSystem.addEquality$ar$ds(constraintAnchor2.mSolverVariable, constraintAnchor2.mTarget.mSolverVariable, 0, 5);
                    linearSystem.addEquality$ar$ds(constraintWidget5.mRight.mSolverVariable, constraintWidget5.mLeft.mSolverVariable, 0, 5);
                }
                if (constraintWidget5.mVisibility != 8 && constraintWidget5.mHorizontalDimensionBehaviour$ar$edu == 3) {
                    if (constraintWidget5.mVerticalDimensionBehaviour$ar$edu == 3) {
                        zArr[0] = false;
                    }
                    if (constraintWidget5.mDimensionRatio <= f) {
                        zArr[0] = false;
                        int i4 = i2 + 1;
                        ConstraintWidget[] constraintWidgetArr2 = this.mMatchConstraintsChainedWidgets;
                        int length = constraintWidgetArr2.length;
                        if (i4 >= length) {
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr2, length + length);
                        }
                        this.mMatchConstraintsChainedWidgets[i2] = constraintWidget5;
                        i2 = i4;
                    }
                }
                ConstraintWidget constraintWidget8 = constraintWidget5.mRight.mTarget.mOwner;
                ConstraintAnchor constraintAnchor3 = constraintWidget8.mLeft.mTarget;
                if (constraintAnchor3 == null || constraintAnchor3.mOwner != constraintWidget5 || constraintWidget8 == constraintWidget5) {
                    break;
                }
                constraintWidget6 = constraintWidget8;
                constraintWidget5 = constraintWidget6;
                constraintWidget4 = null;
                f = 0.0f;
            }
            ConstraintAnchor constraintAnchor4 = constraintWidget5.mRight.mTarget;
            if (constraintAnchor4 != null && constraintAnchor4.mOwner != this) {
                z2 = false;
            }
            if (constraintWidget.mLeft.mTarget != null && constraintWidget6.mRight.mTarget != null) {
                c2 = 1;
            } else {
                c2 = 1;
                zArr[1] = true;
            }
            constraintWidget.mHorizontalChainFixedPosition = z2;
            constraintWidget6.mHorizontalNextWidget = null;
            constraintWidgetArr[0] = constraintWidget;
            constraintWidgetArr[2] = constraintWidget3;
            constraintWidgetArr[c2] = constraintWidget6;
            constraintWidgetArr[3] = constraintWidget7;
        } else {
            ConstraintAnchor constraintAnchor5 = constraintWidget.mTop.mTarget;
            if (constraintAnchor5 != null && constraintAnchor5.mOwner != this) {
                z = false;
            } else {
                z = true;
            }
            ConstraintWidget constraintWidget9 = null;
            constraintWidget.mVerticalNextWidget = null;
            if (constraintWidget.mVisibility != 8) {
                constraintWidget2 = constraintWidget;
            } else {
                constraintWidget2 = null;
            }
            ConstraintWidget constraintWidget10 = constraintWidget;
            int i5 = 0;
            ConstraintWidget constraintWidget11 = null;
            ConstraintWidget constraintWidget12 = constraintWidget2;
            ConstraintWidget constraintWidget13 = constraintWidget12;
            while (constraintWidget10.mBottom.mTarget != null) {
                constraintWidget10.mVerticalNextWidget = constraintWidget9;
                if (constraintWidget10.mVisibility != 8) {
                    if (constraintWidget12 == null) {
                        constraintWidget12 = constraintWidget10;
                    }
                    if (constraintWidget13 != null && constraintWidget13 != constraintWidget10) {
                        constraintWidget13.mVerticalNextWidget = constraintWidget10;
                    }
                    constraintWidget13 = constraintWidget10;
                } else {
                    ConstraintAnchor constraintAnchor6 = constraintWidget10.mTop;
                    linearSystem.addEquality$ar$ds(constraintAnchor6.mSolverVariable, constraintAnchor6.mTarget.mSolverVariable, 0, i3);
                    linearSystem.addEquality$ar$ds(constraintWidget10.mBottom.mSolverVariable, constraintWidget10.mTop.mSolverVariable, 0, i3);
                }
                if (constraintWidget10.mVisibility != 8 && constraintWidget10.mVerticalDimensionBehaviour$ar$edu == 3) {
                    if (constraintWidget10.mHorizontalDimensionBehaviour$ar$edu == 3) {
                        zArr[0] = false;
                    }
                    if (constraintWidget10.mDimensionRatio <= 0.0f) {
                        zArr[0] = false;
                        int i6 = i5 + 1;
                        ConstraintWidget[] constraintWidgetArr3 = this.mMatchConstraintsChainedWidgets;
                        int length2 = constraintWidgetArr3.length;
                        if (i6 >= length2) {
                            this.mMatchConstraintsChainedWidgets = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr3, length2 + length2);
                        }
                        this.mMatchConstraintsChainedWidgets[i5] = constraintWidget10;
                        i5 = i6;
                    }
                }
                ConstraintWidget constraintWidget14 = constraintWidget10.mBottom.mTarget.mOwner;
                ConstraintAnchor constraintAnchor7 = constraintWidget14.mTop.mTarget;
                if (constraintAnchor7 == null || constraintAnchor7.mOwner != constraintWidget10 || constraintWidget14 == constraintWidget10) {
                    break;
                }
                constraintWidget11 = constraintWidget14;
                constraintWidget10 = constraintWidget11;
                constraintWidget9 = null;
                i3 = 5;
            }
            i2 = i5;
            ConstraintAnchor constraintAnchor8 = constraintWidget10.mBottom.mTarget;
            if (constraintAnchor8 != null && constraintAnchor8.mOwner != this) {
                z = false;
            }
            if (constraintWidget.mTop.mTarget != null && constraintWidget11.mBottom.mTarget != null) {
                c = 1;
            } else {
                c = 1;
                zArr[1] = true;
            }
            constraintWidget.mVerticalChainFixedPosition = z;
            constraintWidget11.mVerticalNextWidget = null;
            constraintWidgetArr[0] = constraintWidget;
            constraintWidgetArr[2] = constraintWidget12;
            constraintWidgetArr[c] = constraintWidget11;
            constraintWidgetArr[3] = constraintWidget13;
        }
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addChain(ConstraintWidget constraintWidget, int i) {
        int i2 = 0;
        if (i == 0) {
            while (true) {
                ConstraintAnchor constraintAnchor = constraintWidget.mLeft;
                ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
                if (constraintAnchor2 == null) {
                    break;
                }
                ConstraintWidget constraintWidget2 = constraintAnchor2.mOwner;
                ConstraintAnchor constraintAnchor3 = constraintWidget2.mRight.mTarget;
                if (constraintAnchor3 == null || constraintAnchor3 != constraintAnchor || constraintWidget2 == constraintWidget) {
                    break;
                } else {
                    constraintWidget = constraintWidget2;
                }
            }
            while (true) {
                int i3 = this.mHorizontalChainsSize;
                if (i2 < i3) {
                    if (this.mHorizontalChainsArray[i2] != constraintWidget) {
                        i2++;
                    } else {
                        return;
                    }
                } else {
                    int i4 = i3 + 1;
                    ConstraintWidget[] constraintWidgetArr = this.mHorizontalChainsArray;
                    int length = constraintWidgetArr.length;
                    if (i4 >= length) {
                        this.mHorizontalChainsArray = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr, length + length);
                    }
                    ConstraintWidget[] constraintWidgetArr2 = this.mHorizontalChainsArray;
                    int i5 = this.mHorizontalChainsSize;
                    constraintWidgetArr2[i5] = constraintWidget;
                    this.mHorizontalChainsSize = i5 + 1;
                    return;
                }
            }
        } else {
            while (true) {
                ConstraintAnchor constraintAnchor4 = constraintWidget.mTop;
                ConstraintAnchor constraintAnchor5 = constraintAnchor4.mTarget;
                if (constraintAnchor5 == null) {
                    break;
                }
                ConstraintWidget constraintWidget3 = constraintAnchor5.mOwner;
                ConstraintAnchor constraintAnchor6 = constraintWidget3.mBottom.mTarget;
                if (constraintAnchor6 == null || constraintAnchor6 != constraintAnchor4 || constraintWidget3 == constraintWidget) {
                    break;
                } else {
                    constraintWidget = constraintWidget3;
                }
            }
            while (true) {
                int i6 = this.mVerticalChainsSize;
                if (i2 < i6) {
                    if (this.mVerticalChainsArray[i2] != constraintWidget) {
                        i2++;
                    } else {
                        return;
                    }
                } else {
                    int i7 = i6 + 1;
                    ConstraintWidget[] constraintWidgetArr3 = this.mVerticalChainsArray;
                    int length2 = constraintWidgetArr3.length;
                    if (i7 >= length2) {
                        this.mVerticalChainsArray = (ConstraintWidget[]) Arrays.copyOf(constraintWidgetArr3, length2 + length2);
                    }
                    ConstraintWidget[] constraintWidgetArr4 = this.mVerticalChainsArray;
                    int i8 = this.mVerticalChainsSize;
                    constraintWidgetArr4[i8] = constraintWidget;
                    this.mVerticalChainsSize = i8 + 1;
                    return;
                }
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:35:0x06bc, code lost:
    
        if (r3.mVisibility != 8) goto L244;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean addChildrenToSolver$ar$ds(android.support.constraint.solver.LinearSystem r20) {
        /*
            Method dump skipped, instructions count: 1779
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.addChildrenToSolver$ar$ds(android.support.constraint.solver.LinearSystem):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0164  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void findHorizontalWrapRecursive(android.support.constraint.solver.widgets.ConstraintWidget r11, boolean[] r12) {
        /*
            Method dump skipped, instructions count: 365
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.findHorizontalWrapRecursive(android.support.constraint.solver.widgets.ConstraintWidget, boolean[]):void");
    }

    public final void findVerticalWrapRecursive(ConstraintWidget constraintWidget, boolean[] zArr) {
        int i;
        ConstraintWidget constraintWidget2;
        int i2;
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor constraintAnchor3;
        int i3;
        boolean z;
        ConstraintAnchor constraintAnchor4;
        ConstraintAnchor constraintAnchor5;
        ConstraintAnchor constraintAnchor6;
        ConstraintAnchor constraintAnchor7;
        ConstraintWidget constraintWidget3;
        boolean z2 = false;
        r1 = 0;
        int i4 = 0;
        z2 = false;
        z2 = false;
        z2 = false;
        z2 = false;
        if (constraintWidget.mVerticalDimensionBehaviour$ar$edu == 3 && constraintWidget.mHorizontalDimensionBehaviour$ar$edu != 3 && constraintWidget.mDimensionRatio > 0.0f) {
            zArr[0] = false;
            return;
        }
        int optimizerWrapHeight = constraintWidget.getOptimizerWrapHeight();
        constraintWidget.mVerticalWrapVisited = true;
        if (constraintWidget instanceof Guideline) {
            Guideline guideline = (Guideline) constraintWidget;
            if (guideline.mOrientation == 0) {
                int i5 = guideline.mRelativeBegin;
                if (i5 != -1) {
                    i = i5;
                } else {
                    int i6 = guideline.mRelativeEnd;
                    i = 0;
                    if (i6 != -1) {
                        i4 = i6;
                    }
                }
            } else {
                i4 = optimizerWrapHeight;
                i = i4;
            }
        } else {
            ConstraintAnchor constraintAnchor8 = constraintWidget.mBaseline;
            if (constraintAnchor8.mTarget == null && constraintWidget.mTop.mTarget == null && constraintWidget.mBottom.mTarget == null) {
                i = optimizerWrapHeight + constraintWidget.mY;
            } else {
                ConstraintAnchor constraintAnchor9 = constraintWidget.mBottom.mTarget;
                if (constraintAnchor9 != null && (constraintAnchor7 = constraintWidget.mTop.mTarget) != null && (constraintAnchor9 == constraintAnchor7 || ((constraintWidget3 = constraintAnchor9.mOwner) == constraintAnchor7.mOwner && constraintWidget3 != constraintWidget.mParent))) {
                    zArr[0] = false;
                    return;
                }
                if (constraintAnchor8.isConnected()) {
                    ConstraintWidget constraintWidget4 = constraintWidget.mBaseline.mTarget.mOwner;
                    if (!constraintWidget4.mVerticalWrapVisited) {
                        findVerticalWrapRecursive(constraintWidget4, zArr);
                    }
                    int max = Math.max((constraintWidget4.mDistToTop - constraintWidget4.mHeight) + optimizerWrapHeight, optimizerWrapHeight);
                    int max2 = Math.max((constraintWidget4.mDistToBottom - constraintWidget4.mHeight) + optimizerWrapHeight, optimizerWrapHeight);
                    if (constraintWidget.mVisibility == 8) {
                        int i7 = constraintWidget.mHeight;
                        max -= i7;
                        max2 -= i7;
                    }
                    constraintWidget.mDistToTop = max;
                    constraintWidget.mDistToBottom = max2;
                    return;
                }
                ConstraintWidget constraintWidget5 = null;
                if (constraintWidget.mTop.isConnected()) {
                    ConstraintAnchor constraintAnchor10 = constraintWidget.mTop;
                    constraintWidget2 = constraintAnchor10.mTarget.mOwner;
                    i = constraintAnchor10.getMargin() + optimizerWrapHeight;
                    if (!constraintWidget2.isRoot() && !constraintWidget2.mVerticalWrapVisited) {
                        findVerticalWrapRecursive(constraintWidget2, zArr);
                    }
                } else {
                    i = optimizerWrapHeight;
                    constraintWidget2 = null;
                }
                if (constraintWidget.mBottom.isConnected()) {
                    ConstraintAnchor constraintAnchor11 = constraintWidget.mBottom;
                    ConstraintWidget constraintWidget6 = constraintAnchor11.mTarget.mOwner;
                    optimizerWrapHeight += constraintAnchor11.getMargin();
                    if (!constraintWidget6.isRoot() && !constraintWidget6.mVerticalWrapVisited) {
                        findVerticalWrapRecursive(constraintWidget6, zArr);
                    }
                    constraintWidget5 = constraintWidget6;
                }
                if (constraintWidget.mTop.mTarget != null && !constraintWidget2.isRoot()) {
                    if (constraintWidget.mTop.mTarget.mType$ar$edu$107da2f9_0 == 3) {
                        i3 = constraintWidget2.mDistToTop - constraintWidget2.getOptimizerWrapHeight();
                    } else {
                        if (constraintWidget.mTop.mTarget.mType$ar$edu$107da2f9_0 == 5) {
                            i3 = constraintWidget2.mDistToTop;
                        }
                        if (constraintWidget2.mTopHasCentered && ((constraintAnchor5 = constraintWidget2.mTop.mTarget) == null || constraintAnchor5.mOwner == constraintWidget || (constraintAnchor6 = constraintWidget2.mBottom.mTarget) == null || constraintAnchor6.mOwner == constraintWidget || constraintWidget2.mVerticalDimensionBehaviour$ar$edu == 3)) {
                            z = false;
                        } else {
                            z = true;
                        }
                        constraintWidget.mTopHasCentered = z;
                        if (z && ((constraintAnchor4 = constraintWidget2.mBottom.mTarget) == null || constraintAnchor4.mOwner != constraintWidget)) {
                            i += i - constraintWidget2.mDistToTop;
                        }
                    }
                    i += i3;
                    if (constraintWidget2.mTopHasCentered) {
                    }
                    z = true;
                    constraintWidget.mTopHasCentered = z;
                    if (z) {
                        i += i - constraintWidget2.mDistToTop;
                    }
                }
                if (constraintWidget.mBottom.mTarget != null && !constraintWidget5.isRoot()) {
                    if (constraintWidget.mBottom.mTarget.mType$ar$edu$107da2f9_0 == 5) {
                        i2 = constraintWidget5.mDistToBottom - constraintWidget5.getOptimizerWrapHeight();
                    } else {
                        if (constraintWidget.mBottom.mTarget.mType$ar$edu$107da2f9_0 == 3) {
                            i2 = constraintWidget5.mDistToBottom;
                        }
                        if (!constraintWidget5.mBottomHasCentered || ((constraintAnchor2 = constraintWidget5.mTop.mTarget) != null && constraintAnchor2.mOwner != constraintWidget && (constraintAnchor3 = constraintWidget5.mBottom.mTarget) != null && constraintAnchor3.mOwner != constraintWidget && constraintWidget5.mVerticalDimensionBehaviour$ar$edu != 3)) {
                            z2 = true;
                        }
                        constraintWidget.mBottomHasCentered = z2;
                        if (z2 && ((constraintAnchor = constraintWidget5.mTop.mTarget) == null || constraintAnchor.mOwner != constraintWidget)) {
                            i4 = optimizerWrapHeight + (optimizerWrapHeight - constraintWidget5.mDistToBottom);
                        }
                    }
                    optimizerWrapHeight += i2;
                    if (!constraintWidget5.mBottomHasCentered) {
                    }
                    z2 = true;
                    constraintWidget.mBottomHasCentered = z2;
                    if (z2) {
                        i4 = optimizerWrapHeight + (optimizerWrapHeight - constraintWidget5.mDistToBottom);
                    }
                }
            }
            i4 = optimizerWrapHeight;
        }
        if (constraintWidget.mVisibility == 8) {
            int i8 = constraintWidget.mHeight;
            i -= i8;
            i4 -= i8;
        }
        constraintWidget.mDistToTop = i;
        constraintWidget.mDistToBottom = i4;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0561  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0523  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x02f2  */
    /* JADX WARN: Removed duplicated region for block: B:246:0x03b2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0576  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x05da  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x05ea A[LOOP:16: B:276:0x05e8->B:277:0x05ea, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:280:0x05f4  */
    /* JADX WARN: Removed duplicated region for block: B:283:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:284:0x05d0  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0405  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x04fe  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x051b  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0528  */
    /* JADX WARN: Type inference failed for: r0v12, types: [android.support.constraint.solver.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r0v37, types: [int] */
    /* JADX WARN: Type inference failed for: r0v93 */
    /* JADX WARN: Type inference failed for: r0v94 */
    /* JADX WARN: Type inference failed for: r2v9, types: [android.support.constraint.solver.widgets.ConstraintWidget] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r4v24 */
    /* JADX WARN: Type inference failed for: r4v25 */
    /* JADX WARN: Type inference failed for: r4v26, types: [int] */
    /* JADX WARN: Type inference failed for: r4v30, types: [int] */
    /* JADX WARN: Type inference failed for: r7v12, types: [java.util.ArrayList] */
    @Override // android.support.constraint.solver.widgets.WidgetContainer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void layout() {
        /*
            Method dump skipped, instructions count: 1528
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.constraint.solver.widgets.ConstraintWidgetContainer.layout():void");
    }

    @Override // android.support.constraint.solver.widgets.WidgetContainer, android.support.constraint.solver.widgets.ConstraintWidget
    public final void reset() {
        this.mSystem.reset();
        super.reset();
    }
}
