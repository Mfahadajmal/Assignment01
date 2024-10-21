package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Direct {
    public static final BasicMeasure$Measure sMeasure = new BasicMeasure$Measure();
    public static int sHcount = 0;
    public static int sVcount = 0;

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0036, code lost:
    
        if (r8.hasDanglingDimension(0) != false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0066, code lost:
    
        if (r8.hasDanglingDimension(1) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0079, code lost:
    
        if (r8.hasResolvedTargets(1, r8.getHeight()) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x0069, code lost:
    
        if (r1 != 3) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x0049, code lost:
    
        if (r8.hasResolvedTargets(0, r8.getWidth()) != false) goto L86;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0039, code lost:
    
        if (r0 != 3) goto L81;
     */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0082  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x008c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0069  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean canMeasure$ar$ds(androidx.constraintlayout.core.widgets.ConstraintWidget r8) {
        /*
            int r0 = r8.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0()
            int r1 = r8.getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0()
            androidx.constraintlayout.core.widgets.ConstraintWidget r2 = r8.mParent
            if (r2 != 0) goto Ld
            r2 = 0
        Ld:
            if (r2 == 0) goto L12
            r2.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0()
        L12:
            if (r2 == 0) goto L17
            r2.getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0()
        L17:
            r2 = 2
            r3 = 0
            r4 = 3
            r5 = 0
            r6 = 1
            if (r0 == r6) goto L4b
            boolean r7 = r8.isResolvedHorizontally()
            if (r7 != 0) goto L4b
            if (r0 == r2) goto L4b
            if (r0 != r4) goto L39
            int r0 = r8.mMatchConstraintDefaultWidth
            if (r0 != 0) goto L3d
            float r0 = r8.mDimensionRatio
            int r0 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r0 != 0) goto L3d
            boolean r0 = r8.hasDanglingDimension(r5)
            if (r0 != 0) goto L4b
            goto L3d
        L39:
            if (r0 == r4) goto L3d
        L3b:
            r0 = r5
            goto L4c
        L3d:
            int r0 = r8.mMatchConstraintDefaultWidth
            if (r0 != r6) goto L3b
            int r0 = r8.getWidth()
            boolean r0 = r8.hasResolvedTargets(r5, r0)
            if (r0 == 0) goto L3b
        L4b:
            r0 = r6
        L4c:
            if (r1 == r6) goto L7b
            boolean r7 = r8.isResolvedVertically()
            if (r7 != 0) goto L7b
            if (r1 == r2) goto L7b
            if (r1 != r4) goto L69
            int r1 = r8.mMatchConstraintDefaultHeight
            if (r1 != 0) goto L6d
            float r1 = r8.mDimensionRatio
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 != 0) goto L6d
            boolean r1 = r8.hasDanglingDimension(r6)
            if (r1 != 0) goto L7b
            goto L6d
        L69:
            if (r1 == r4) goto L6d
        L6b:
            r1 = r5
            goto L7c
        L6d:
            int r1 = r8.mMatchConstraintDefaultHeight
            if (r1 != r6) goto L6b
            int r1 = r8.getHeight()
            boolean r1 = r8.hasResolvedTargets(r6, r1)
            if (r1 == 0) goto L6b
        L7b:
            r1 = r6
        L7c:
            float r8 = r8.mDimensionRatio
            int r8 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r8 <= 0) goto L8a
            if (r0 != 0) goto L89
            if (r1 != 0) goto L89
            r0 = r5
            r1 = r0
            goto L8a
        L89:
            return r6
        L8a:
            if (r0 == 0) goto L8f
            if (r1 == 0) goto L8f
            return r6
        L8f:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.Direct.canMeasure$ar$ds(androidx.constraintlayout.core.widgets.ConstraintWidget):boolean");
    }

    public static void horizontalSolvingPass$ar$class_merging(int i, ConstraintWidget constraintWidget, ConstraintLayout.Measurer measurer, boolean z) {
        boolean z2;
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        boolean z3;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        if (!constraintWidget.mHorizontalSolvingPass) {
            sHcount++;
            if (!(constraintWidget instanceof ConstraintWidgetContainer) && constraintWidget.isMeasureRequested() && canMeasure$ar$ds(constraintWidget)) {
                ConstraintWidgetContainer.measure$ar$class_merging$ar$ds(constraintWidget, measurer, new BasicMeasure$Measure());
            }
            ConstraintAnchor anchor$ar$edu$6d5b24e9_0 = constraintWidget.getAnchor$ar$edu$6d5b24e9_0(2);
            ConstraintAnchor anchor$ar$edu$6d5b24e9_02 = constraintWidget.getAnchor$ar$edu$6d5b24e9_0(4);
            int finalValue = anchor$ar$edu$6d5b24e9_0.getFinalValue();
            int finalValue2 = anchor$ar$edu$6d5b24e9_02.getFinalValue();
            HashSet hashSet = anchor$ar$edu$6d5b24e9_0.mDependents;
            if (hashSet != null && anchor$ar$edu$6d5b24e9_0.mHasFinalValue) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    int i2 = i + 1;
                    ConstraintAnchor constraintAnchor5 = (ConstraintAnchor) it.next();
                    ConstraintWidget constraintWidget2 = constraintAnchor5.mOwner;
                    boolean canMeasure$ar$ds = canMeasure$ar$ds(constraintWidget2);
                    if (constraintWidget2.isMeasureRequested() && canMeasure$ar$ds) {
                        ConstraintWidgetContainer.measure$ar$class_merging$ar$ds(constraintWidget2, measurer, new BasicMeasure$Measure());
                    }
                    ConstraintAnchor constraintAnchor6 = constraintWidget2.mLeft;
                    if ((constraintAnchor5 == constraintAnchor6 && (constraintAnchor4 = constraintWidget2.mRight.mTarget) != null && constraintAnchor4.mHasFinalValue) || (constraintAnchor5 == constraintWidget2.mRight && (constraintAnchor3 = constraintAnchor6.mTarget) != null && constraintAnchor3.mHasFinalValue)) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (constraintWidget2.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0() == 3 && !canMeasure$ar$ds) {
                        if (constraintWidget2.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0() == 3 && constraintWidget2.mMatchConstraintMaxWidth >= 0 && constraintWidget2.mMatchConstraintMinWidth >= 0 && ((constraintWidget2.mVisibility == 8 || (constraintWidget2.mMatchConstraintDefaultWidth == 0 && constraintWidget2.mDimensionRatio == 0.0f)) && !constraintWidget2.isInHorizontalChain() && z3 && !constraintWidget2.isInHorizontalChain())) {
                            solveHorizontalMatchConstraint$ar$class_merging(i2, constraintWidget, measurer, constraintWidget2, z);
                        }
                    } else if (!constraintWidget2.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor7 = constraintWidget2.mLeft;
                        if (constraintAnchor5 == constraintAnchor7 && constraintWidget2.mRight.mTarget == null) {
                            int margin = constraintAnchor7.getMargin() + finalValue;
                            constraintWidget2.setFinalHorizontal(margin, constraintWidget2.getWidth() + margin);
                            horizontalSolvingPass$ar$class_merging(i2, constraintWidget2, measurer, z);
                        } else {
                            ConstraintAnchor constraintAnchor8 = constraintWidget2.mRight;
                            if (constraintAnchor5 == constraintAnchor8 && constraintAnchor7.mTarget == null) {
                                int margin2 = finalValue - constraintAnchor8.getMargin();
                                constraintWidget2.setFinalHorizontal(margin2 - constraintWidget2.getWidth(), margin2);
                                horizontalSolvingPass$ar$class_merging(i2, constraintWidget2, measurer, z);
                            } else if (z3 && !constraintWidget2.isInHorizontalChain()) {
                                solveHorizontalCenterConstraints$ar$class_merging(i2, measurer, constraintWidget2, z);
                            }
                        }
                    }
                }
            }
            if (!(constraintWidget instanceof Guideline)) {
                HashSet hashSet2 = anchor$ar$edu$6d5b24e9_02.mDependents;
                if (hashSet2 != null && anchor$ar$edu$6d5b24e9_02.mHasFinalValue) {
                    Iterator it2 = hashSet2.iterator();
                    while (it2.hasNext()) {
                        int i3 = i + 1;
                        ConstraintAnchor constraintAnchor9 = (ConstraintAnchor) it2.next();
                        ConstraintWidget constraintWidget3 = constraintAnchor9.mOwner;
                        boolean canMeasure$ar$ds2 = canMeasure$ar$ds(constraintWidget3);
                        if (constraintWidget3.isMeasureRequested() && canMeasure$ar$ds2) {
                            ConstraintWidgetContainer.measure$ar$class_merging$ar$ds(constraintWidget3, measurer, new BasicMeasure$Measure());
                        }
                        ConstraintAnchor constraintAnchor10 = constraintWidget3.mLeft;
                        if ((constraintAnchor9 == constraintAnchor10 && (constraintAnchor2 = constraintWidget3.mRight.mTarget) != null && constraintAnchor2.mHasFinalValue) || (constraintAnchor9 == constraintWidget3.mRight && (constraintAnchor = constraintAnchor10.mTarget) != null && constraintAnchor.mHasFinalValue)) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (constraintWidget3.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0() == 3 && !canMeasure$ar$ds2) {
                            if (constraintWidget3.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0() == 3 && constraintWidget3.mMatchConstraintMaxWidth >= 0 && constraintWidget3.mMatchConstraintMinWidth >= 0 && (constraintWidget3.mVisibility == 8 || (constraintWidget3.mMatchConstraintDefaultWidth == 0 && constraintWidget3.mDimensionRatio == 0.0f))) {
                                if (!constraintWidget3.isInHorizontalChain() && z2 && !constraintWidget3.isInHorizontalChain()) {
                                    solveHorizontalMatchConstraint$ar$class_merging(i3, constraintWidget, measurer, constraintWidget3, z);
                                }
                            }
                        } else if (!constraintWidget3.isMeasureRequested()) {
                            ConstraintAnchor constraintAnchor11 = constraintWidget3.mLeft;
                            if (constraintAnchor9 == constraintAnchor11 && constraintWidget3.mRight.mTarget == null) {
                                int margin3 = constraintAnchor11.getMargin() + finalValue2;
                                constraintWidget3.setFinalHorizontal(margin3, constraintWidget3.getWidth() + margin3);
                                horizontalSolvingPass$ar$class_merging(i3, constraintWidget3, measurer, z);
                            } else {
                                ConstraintAnchor constraintAnchor12 = constraintWidget3.mRight;
                                if (constraintAnchor9 == constraintAnchor12 && constraintAnchor11.mTarget == null) {
                                    int margin4 = finalValue2 - constraintAnchor12.getMargin();
                                    constraintWidget3.setFinalHorizontal(margin4 - constraintWidget3.getWidth(), margin4);
                                    horizontalSolvingPass$ar$class_merging(i3, constraintWidget3, measurer, z);
                                } else if (z2 && !constraintWidget3.isInHorizontalChain()) {
                                    solveHorizontalCenterConstraints$ar$class_merging(i3, measurer, constraintWidget3, z);
                                }
                            }
                        }
                    }
                }
                constraintWidget.mHorizontalSolvingPass = true;
            }
        }
    }

    public static void solveBarrier$ar$class_merging$ar$ds(Barrier barrier, ConstraintLayout.Measurer measurer, int i, boolean z) {
        if (barrier.allSolved()) {
            if (i == 0) {
                horizontalSolvingPass$ar$class_merging(1, barrier, measurer, z);
            } else {
                verticalSolvingPass$ar$class_merging(1, barrier, measurer);
            }
        }
    }

    private static void solveHorizontalCenterConstraints$ar$class_merging(int i, ConstraintLayout.Measurer measurer, ConstraintWidget constraintWidget, boolean z) {
        float f;
        float f2 = constraintWidget.mHorizontalBiasPercent;
        int finalValue = constraintWidget.mLeft.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mRight.mTarget.getFinalValue();
        int margin = constraintWidget.mLeft.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mRight.getMargin();
        if (finalValue == finalValue2) {
            margin2 = finalValue2;
        }
        if (finalValue == finalValue2) {
            margin = finalValue;
        }
        if (finalValue == finalValue2) {
            f2 = 0.5f;
        }
        int width = constraintWidget.getWidth();
        int i2 = (margin2 - margin) - width;
        if (margin > margin2) {
            i2 = (margin - margin2) - width;
        }
        if (i2 > 0) {
            f = (f2 * i2) + 0.5f;
        } else {
            f = f2 * i2;
        }
        int i3 = ((int) f) + margin;
        int i4 = i3 + width;
        if (margin > margin2) {
            i4 = i3 - width;
        }
        constraintWidget.setFinalHorizontal(i3, i4);
        horizontalSolvingPass$ar$class_merging(i + 1, constraintWidget, measurer, z);
    }

    private static void solveHorizontalMatchConstraint$ar$class_merging(int i, ConstraintWidget constraintWidget, ConstraintLayout.Measurer measurer, ConstraintWidget constraintWidget2, boolean z) {
        int width;
        float f = constraintWidget2.mHorizontalBiasPercent;
        int finalValue = constraintWidget2.mLeft.mTarget.getFinalValue() + constraintWidget2.mLeft.getMargin();
        int finalValue2 = constraintWidget2.mRight.mTarget.getFinalValue() - constraintWidget2.mRight.getMargin();
        if (finalValue2 >= finalValue) {
            int width2 = constraintWidget2.getWidth();
            int i2 = finalValue2 - finalValue;
            if (constraintWidget2.mVisibility != 8) {
                int i3 = constraintWidget2.mMatchConstraintDefaultWidth;
                if (i3 == 2) {
                    if (constraintWidget instanceof ConstraintWidgetContainer) {
                        width = constraintWidget.getWidth();
                    } else {
                        width = constraintWidget.mParent.getWidth();
                    }
                    width2 = (int) (constraintWidget2.mHorizontalBiasPercent * 0.5f * width);
                } else if (i3 == 0) {
                    width2 = i2;
                }
                width2 = Math.max(constraintWidget2.mMatchConstraintMinWidth, width2);
                int i4 = constraintWidget2.mMatchConstraintMaxWidth;
                if (i4 > 0) {
                    width2 = Math.min(i4, width2);
                }
            }
            int i5 = finalValue + ((int) ((f * (i2 - width2)) + 0.5f));
            constraintWidget2.setFinalHorizontal(i5, width2 + i5);
            horizontalSolvingPass$ar$class_merging(i + 1, constraintWidget2, measurer, z);
        }
    }

    private static void solveVerticalCenterConstraints$ar$class_merging(int i, ConstraintLayout.Measurer measurer, ConstraintWidget constraintWidget) {
        float f;
        float f2 = constraintWidget.mVerticalBiasPercent;
        int finalValue = constraintWidget.mTop.mTarget.getFinalValue();
        int finalValue2 = constraintWidget.mBottom.mTarget.getFinalValue();
        int margin = constraintWidget.mTop.getMargin() + finalValue;
        int margin2 = finalValue2 - constraintWidget.mBottom.getMargin();
        if (finalValue == finalValue2) {
            margin2 = finalValue2;
        }
        if (finalValue == finalValue2) {
            margin = finalValue;
        }
        if (finalValue == finalValue2) {
            f2 = 0.5f;
        }
        int height = constraintWidget.getHeight();
        int i2 = (margin2 - margin) - height;
        if (margin > margin2) {
            i2 = (margin - margin2) - height;
        }
        if (i2 > 0) {
            f = (f2 * i2) + 0.5f;
        } else {
            f = f2 * i2;
        }
        int i3 = (int) f;
        int i4 = margin + i3;
        int i5 = i4 + height;
        if (margin > margin2) {
            i4 = margin - i3;
            i5 = i4 - height;
        }
        constraintWidget.setFinalVertical(i4, i5);
        verticalSolvingPass$ar$class_merging(i + 1, constraintWidget, measurer);
    }

    private static void solveVerticalMatchConstraint$ar$class_merging(int i, ConstraintWidget constraintWidget, ConstraintLayout.Measurer measurer, ConstraintWidget constraintWidget2) {
        int height;
        float f = constraintWidget2.mVerticalBiasPercent;
        int finalValue = constraintWidget2.mTop.mTarget.getFinalValue() + constraintWidget2.mTop.getMargin();
        int finalValue2 = constraintWidget2.mBottom.mTarget.getFinalValue() - constraintWidget2.mBottom.getMargin();
        if (finalValue2 >= finalValue) {
            int height2 = constraintWidget2.getHeight();
            int i2 = finalValue2 - finalValue;
            if (constraintWidget2.mVisibility != 8) {
                int i3 = constraintWidget2.mMatchConstraintDefaultHeight;
                if (i3 == 2) {
                    if (constraintWidget instanceof ConstraintWidgetContainer) {
                        height = constraintWidget.getHeight();
                    } else {
                        height = constraintWidget.mParent.getHeight();
                    }
                    height2 = (int) (f * 0.5f * height);
                } else if (i3 == 0) {
                    height2 = i2;
                }
                height2 = Math.max(constraintWidget2.mMatchConstraintMinHeight, height2);
                int i4 = constraintWidget2.mMatchConstraintMaxHeight;
                if (i4 > 0) {
                    height2 = Math.min(i4, height2);
                }
            }
            int i5 = finalValue + ((int) ((f * (i2 - height2)) + 0.5f));
            constraintWidget2.setFinalVertical(i5, height2 + i5);
            verticalSolvingPass$ar$class_merging(i + 1, constraintWidget2, measurer);
        }
    }

    public static void verticalSolvingPass$ar$class_merging(int i, ConstraintWidget constraintWidget, ConstraintLayout.Measurer measurer) {
        boolean z;
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        boolean z2;
        ConstraintAnchor constraintAnchor3;
        ConstraintAnchor constraintAnchor4;
        if (!constraintWidget.mVerticalSolvingPass) {
            sVcount++;
            if (!(constraintWidget instanceof ConstraintWidgetContainer) && constraintWidget.isMeasureRequested() && canMeasure$ar$ds(constraintWidget)) {
                ConstraintWidgetContainer.measure$ar$class_merging$ar$ds(constraintWidget, measurer, new BasicMeasure$Measure());
            }
            ConstraintAnchor anchor$ar$edu$6d5b24e9_0 = constraintWidget.getAnchor$ar$edu$6d5b24e9_0(3);
            ConstraintAnchor anchor$ar$edu$6d5b24e9_02 = constraintWidget.getAnchor$ar$edu$6d5b24e9_0(5);
            int finalValue = anchor$ar$edu$6d5b24e9_0.getFinalValue();
            int finalValue2 = anchor$ar$edu$6d5b24e9_02.getFinalValue();
            HashSet hashSet = anchor$ar$edu$6d5b24e9_0.mDependents;
            if (hashSet != null && anchor$ar$edu$6d5b24e9_0.mHasFinalValue) {
                Iterator it = hashSet.iterator();
                while (it.hasNext()) {
                    int i2 = i + 1;
                    ConstraintAnchor constraintAnchor5 = (ConstraintAnchor) it.next();
                    ConstraintWidget constraintWidget2 = constraintAnchor5.mOwner;
                    boolean canMeasure$ar$ds = canMeasure$ar$ds(constraintWidget2);
                    if (constraintWidget2.isMeasureRequested() && canMeasure$ar$ds) {
                        ConstraintWidgetContainer.measure$ar$class_merging$ar$ds(constraintWidget2, measurer, new BasicMeasure$Measure());
                    }
                    ConstraintAnchor constraintAnchor6 = constraintWidget2.mTop;
                    if ((constraintAnchor5 == constraintAnchor6 && (constraintAnchor4 = constraintWidget2.mBottom.mTarget) != null && constraintAnchor4.mHasFinalValue) || (constraintAnchor5 == constraintWidget2.mBottom && (constraintAnchor3 = constraintAnchor6.mTarget) != null && constraintAnchor3.mHasFinalValue)) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (constraintWidget2.getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0() == 3 && !canMeasure$ar$ds) {
                        if (constraintWidget2.getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0() == 3 && constraintWidget2.mMatchConstraintMaxHeight >= 0 && constraintWidget2.mMatchConstraintMinHeight >= 0 && (constraintWidget2.mVisibility == 8 || (constraintWidget2.mMatchConstraintDefaultHeight == 0 && constraintWidget2.mDimensionRatio == 0.0f))) {
                            if (!constraintWidget2.isInVerticalChain() && z2 && !constraintWidget2.isInVerticalChain()) {
                                solveVerticalMatchConstraint$ar$class_merging(i2, constraintWidget, measurer, constraintWidget2);
                            }
                        }
                    } else if (!constraintWidget2.isMeasureRequested()) {
                        ConstraintAnchor constraintAnchor7 = constraintWidget2.mTop;
                        if (constraintAnchor5 == constraintAnchor7 && constraintWidget2.mBottom.mTarget == null) {
                            int margin = constraintAnchor7.getMargin() + finalValue;
                            constraintWidget2.setFinalVertical(margin, constraintWidget2.getHeight() + margin);
                            verticalSolvingPass$ar$class_merging(i2, constraintWidget2, measurer);
                        } else {
                            ConstraintAnchor constraintAnchor8 = constraintWidget2.mBottom;
                            if (constraintAnchor5 == constraintAnchor8 && constraintAnchor7.mTarget == null) {
                                int margin2 = finalValue - constraintAnchor8.getMargin();
                                constraintWidget2.setFinalVertical(margin2 - constraintWidget2.getHeight(), margin2);
                                verticalSolvingPass$ar$class_merging(i2, constraintWidget2, measurer);
                            } else if (z2 && !constraintWidget2.isInVerticalChain()) {
                                solveVerticalCenterConstraints$ar$class_merging(i2, measurer, constraintWidget2);
                            }
                        }
                    }
                }
            }
            if (!(constraintWidget instanceof Guideline)) {
                HashSet hashSet2 = anchor$ar$edu$6d5b24e9_02.mDependents;
                if (hashSet2 != null && anchor$ar$edu$6d5b24e9_02.mHasFinalValue) {
                    Iterator it2 = hashSet2.iterator();
                    while (it2.hasNext()) {
                        int i3 = i + 1;
                        ConstraintAnchor constraintAnchor9 = (ConstraintAnchor) it2.next();
                        ConstraintWidget constraintWidget3 = constraintAnchor9.mOwner;
                        boolean canMeasure$ar$ds2 = canMeasure$ar$ds(constraintWidget3);
                        if (constraintWidget3.isMeasureRequested() && canMeasure$ar$ds2) {
                            ConstraintWidgetContainer.measure$ar$class_merging$ar$ds(constraintWidget3, measurer, new BasicMeasure$Measure());
                        }
                        ConstraintAnchor constraintAnchor10 = constraintWidget3.mTop;
                        if ((constraintAnchor9 == constraintAnchor10 && (constraintAnchor2 = constraintWidget3.mBottom.mTarget) != null && constraintAnchor2.mHasFinalValue) || (constraintAnchor9 == constraintWidget3.mBottom && (constraintAnchor = constraintAnchor10.mTarget) != null && constraintAnchor.mHasFinalValue)) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (constraintWidget3.getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0() == 3 && !canMeasure$ar$ds2) {
                            if (constraintWidget3.getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0() == 3 && constraintWidget3.mMatchConstraintMaxHeight >= 0 && constraintWidget3.mMatchConstraintMinHeight >= 0 && (constraintWidget3.mVisibility == 8 || (constraintWidget3.mMatchConstraintDefaultHeight == 0 && constraintWidget3.mDimensionRatio == 0.0f))) {
                                if (!constraintWidget3.isInVerticalChain() && z && !constraintWidget3.isInVerticalChain()) {
                                    solveVerticalMatchConstraint$ar$class_merging(i3, constraintWidget, measurer, constraintWidget3);
                                }
                            }
                        } else if (constraintWidget3.isMeasureRequested()) {
                            continue;
                        } else {
                            ConstraintAnchor constraintAnchor11 = constraintWidget3.mTop;
                            if (constraintAnchor9 == constraintAnchor11 && constraintWidget3.mBottom.mTarget == null) {
                                int margin3 = constraintAnchor11.getMargin() + finalValue2;
                                constraintWidget3.setFinalVertical(margin3, constraintWidget3.getHeight() + margin3);
                                verticalSolvingPass$ar$class_merging(i3, constraintWidget3, measurer);
                            } else {
                                ConstraintAnchor constraintAnchor12 = constraintWidget3.mBottom;
                                if (constraintAnchor9 == constraintAnchor12 && constraintAnchor11.mTarget == null) {
                                    int margin4 = finalValue2 - constraintAnchor12.getMargin();
                                    constraintWidget3.setFinalVertical(margin4 - constraintWidget3.getHeight(), margin4);
                                    verticalSolvingPass$ar$class_merging(i3, constraintWidget3, measurer);
                                } else if (z && !constraintWidget3.isInVerticalChain()) {
                                    solveVerticalCenterConstraints$ar$class_merging(i3, measurer, constraintWidget3);
                                }
                            }
                        }
                    }
                }
                ConstraintAnchor anchor$ar$edu$6d5b24e9_03 = constraintWidget.getAnchor$ar$edu$6d5b24e9_0(6);
                if (anchor$ar$edu$6d5b24e9_03.mDependents != null && anchor$ar$edu$6d5b24e9_03.mHasFinalValue) {
                    int finalValue3 = anchor$ar$edu$6d5b24e9_03.getFinalValue();
                    Iterator it3 = anchor$ar$edu$6d5b24e9_03.mDependents.iterator();
                    while (it3.hasNext()) {
                        int i4 = i + 1;
                        ConstraintAnchor constraintAnchor13 = (ConstraintAnchor) it3.next();
                        ConstraintWidget constraintWidget4 = constraintAnchor13.mOwner;
                        boolean canMeasure$ar$ds3 = canMeasure$ar$ds(constraintWidget4);
                        if (constraintWidget4.isMeasureRequested() && canMeasure$ar$ds3) {
                            ConstraintWidgetContainer.measure$ar$class_merging$ar$ds(constraintWidget4, measurer, new BasicMeasure$Measure());
                        }
                        if (constraintWidget4.getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0() != 3 || canMeasure$ar$ds3) {
                            if (!constraintWidget4.isMeasureRequested() && constraintAnchor13 == constraintWidget4.mBaseline) {
                                int margin5 = constraintAnchor13.getMargin() + finalValue3;
                                if (constraintWidget4.mHasBaseline) {
                                    int i5 = margin5 - constraintWidget4.mBaselineDistance;
                                    int i6 = constraintWidget4.mHeight + i5;
                                    constraintWidget4.mY = i5;
                                    constraintWidget4.mTop.setFinalValue(i5);
                                    constraintWidget4.mBottom.setFinalValue(i6);
                                    constraintWidget4.mBaseline.setFinalValue(margin5);
                                    constraintWidget4.mResolvedVertical = true;
                                }
                                verticalSolvingPass$ar$class_merging(i4, constraintWidget4, measurer);
                            }
                        }
                    }
                }
                constraintWidget.mVerticalSolvingPass = true;
            }
        }
    }
}
