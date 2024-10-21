package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.HelperWidget;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HorizontalWidgetRun extends WidgetRun {
    private static final int[] sTempDimensions = new int[2];

    public HorizontalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        this.start.mType$ar$edu$7d38fd09_0 = 4;
        this.end.mType$ar$edu$7d38fd09_0 = 5;
        this.orientation = 0;
    }

    private static final void computeInsetRatio$ar$ds(int[] iArr, int i, int i2, int i3, int i4, float f, int i5) {
        int i6 = i4 - i3;
        int i7 = i2 - i;
        if (i5 != -1) {
            if (i5 != 0) {
                iArr[0] = i7;
                iArr[1] = (int) ((i7 * f) + 0.5f);
                return;
            } else {
                iArr[0] = (int) ((i6 * f) + 0.5f);
                iArr[1] = i6;
                return;
            }
        }
        float f2 = (i7 / f) + 0.5f;
        int i8 = (int) ((i6 * f) + 0.5f);
        if (i8 <= i7) {
            iArr[0] = i8;
            iArr[1] = i6;
            return;
        }
        int i9 = (int) f2;
        if (i9 <= i6) {
            iArr[0] = i7;
            iArr[1] = i9;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void apply() {
        ConstraintWidget constraintWidget;
        ConstraintWidget constraintWidget2;
        ConstraintWidget constraintWidget3;
        ConstraintWidget constraintWidget4 = this.mWidget;
        if (constraintWidget4.measured) {
            this.mDimension.resolve(constraintWidget4.getWidth());
        }
        if (!this.mDimension.resolved) {
            int horizontalDimensionBehaviour$ar$edu$cfe88bd7_0 = this.mWidget.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0();
            this.mDimensionBehavior$ar$edu = horizontalDimensionBehaviour$ar$edu$cfe88bd7_0;
            if (horizontalDimensionBehaviour$ar$edu$cfe88bd7_0 != 3) {
                if (this.mDimensionBehavior$ar$edu == 4 && (constraintWidget3 = this.mWidget.mParent) != null && (constraintWidget3.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0() == 1 || constraintWidget3.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0() == 4)) {
                    int width = (constraintWidget3.getWidth() - this.mWidget.mLeft.getMargin()) - this.mWidget.mRight.getMargin();
                    addTarget$ar$ds(this.start, constraintWidget3.mHorizontalRun.start, this.mWidget.mLeft.getMargin());
                    addTarget$ar$ds(this.end, constraintWidget3.mHorizontalRun.end, -this.mWidget.mRight.getMargin());
                    this.mDimension.resolve(width);
                    return;
                }
                if (this.mDimensionBehavior$ar$edu == 1) {
                    this.mDimension.resolve(this.mWidget.getWidth());
                }
            }
        } else if (this.mDimensionBehavior$ar$edu == 4 && (constraintWidget = this.mWidget.mParent) != null && (constraintWidget.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0() == 1 || constraintWidget.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0() == 4)) {
            addTarget$ar$ds(this.start, constraintWidget.mHorizontalRun.start, this.mWidget.mLeft.getMargin());
            addTarget$ar$ds(this.end, constraintWidget.mHorizontalRun.end, -this.mWidget.mRight.getMargin());
            return;
        }
        DimensionDependency dimensionDependency = this.mDimension;
        if (dimensionDependency.resolved) {
            ConstraintWidget constraintWidget5 = this.mWidget;
            if (constraintWidget5.measured) {
                ConstraintAnchor[] constraintAnchorArr = constraintWidget5.mListAnchors;
                ConstraintAnchor constraintAnchor = constraintAnchorArr[0];
                ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
                if (constraintAnchor2 != null && constraintAnchorArr[1].mTarget != null) {
                    if (constraintWidget5.isInHorizontalChain()) {
                        this.start.mMargin = this.mWidget.mListAnchors[0].getMargin();
                        this.end.mMargin = -this.mWidget.mListAnchors[1].getMargin();
                        return;
                    }
                    DependencyNode target$ar$ds = getTarget$ar$ds(this.mWidget.mListAnchors[0]);
                    if (target$ar$ds != null) {
                        addTarget$ar$ds(this.start, target$ar$ds, this.mWidget.mListAnchors[0].getMargin());
                    }
                    DependencyNode target$ar$ds2 = getTarget$ar$ds(this.mWidget.mListAnchors[1]);
                    if (target$ar$ds2 != null) {
                        addTarget$ar$ds(this.end, target$ar$ds2, -this.mWidget.mListAnchors[1].getMargin());
                    }
                    this.start.delegateToWidgetRun = true;
                    this.end.delegateToWidgetRun = true;
                    return;
                }
                if (constraintAnchor2 != null) {
                    DependencyNode target$ar$ds3 = getTarget$ar$ds(constraintAnchor);
                    if (target$ar$ds3 != null) {
                        addTarget$ar$ds(this.start, target$ar$ds3, this.mWidget.mListAnchors[0].getMargin());
                        addTarget$ar$ds(this.end, this.start, this.mDimension.value);
                        return;
                    }
                    return;
                }
                ConstraintAnchor constraintAnchor3 = constraintAnchorArr[1];
                if (constraintAnchor3.mTarget != null) {
                    DependencyNode target$ar$ds4 = getTarget$ar$ds(constraintAnchor3);
                    if (target$ar$ds4 != null) {
                        addTarget$ar$ds(this.end, target$ar$ds4, -this.mWidget.mListAnchors[1].getMargin());
                        addTarget$ar$ds(this.start, this.end, -this.mDimension.value);
                        return;
                    }
                    return;
                }
                if (!(constraintWidget5 instanceof HelperWidget) && constraintWidget5.mParent != null && constraintWidget5.getAnchor$ar$edu$6d5b24e9_0(7).mTarget == null) {
                    ConstraintWidget constraintWidget6 = this.mWidget;
                    addTarget$ar$ds(this.start, constraintWidget6.mParent.mHorizontalRun.start, constraintWidget6.getX());
                    addTarget$ar$ds(this.end, this.start, this.mDimension.value);
                    return;
                }
                return;
            }
        }
        if (this.mDimensionBehavior$ar$edu == 3) {
            ConstraintWidget constraintWidget7 = this.mWidget;
            int i = constraintWidget7.mMatchConstraintDefaultWidth;
            if (i != 2) {
                if (i == 3) {
                    if (constraintWidget7.mMatchConstraintDefaultHeight == 3) {
                        this.start.updateDelegate = this;
                        this.end.updateDelegate = this;
                        VerticalWidgetRun verticalWidgetRun = constraintWidget7.mVerticalRun;
                        verticalWidgetRun.start.updateDelegate = this;
                        verticalWidgetRun.end.updateDelegate = this;
                        dimensionDependency.updateDelegate = this;
                        if (constraintWidget7.isInVerticalChain()) {
                            this.mDimension.mTargets.add(this.mWidget.mVerticalRun.mDimension);
                            this.mWidget.mVerticalRun.mDimension.mDependencies.add(this.mDimension);
                            VerticalWidgetRun verticalWidgetRun2 = this.mWidget.mVerticalRun;
                            verticalWidgetRun2.mDimension.updateDelegate = this;
                            this.mDimension.mTargets.add(verticalWidgetRun2.start);
                            this.mDimension.mTargets.add(this.mWidget.mVerticalRun.end);
                            this.mWidget.mVerticalRun.start.mDependencies.add(this.mDimension);
                            this.mWidget.mVerticalRun.end.mDependencies.add(this.mDimension);
                        } else if (this.mWidget.isInHorizontalChain()) {
                            this.mWidget.mVerticalRun.mDimension.mTargets.add(this.mDimension);
                            this.mDimension.mDependencies.add(this.mWidget.mVerticalRun.mDimension);
                        } else {
                            this.mWidget.mVerticalRun.mDimension.mTargets.add(this.mDimension);
                        }
                    } else {
                        DimensionDependency dimensionDependency2 = constraintWidget7.mVerticalRun.mDimension;
                        dimensionDependency.mTargets.add(dimensionDependency2);
                        dimensionDependency2.mDependencies.add(this.mDimension);
                        this.mWidget.mVerticalRun.start.mDependencies.add(this.mDimension);
                        this.mWidget.mVerticalRun.end.mDependencies.add(this.mDimension);
                        DimensionDependency dimensionDependency3 = this.mDimension;
                        dimensionDependency3.delegateToWidgetRun = true;
                        dimensionDependency3.mDependencies.add(this.start);
                        this.mDimension.mDependencies.add(this.end);
                        this.start.mTargets.add(this.mDimension);
                        this.end.mTargets.add(this.mDimension);
                    }
                }
            } else {
                ConstraintWidget constraintWidget8 = constraintWidget7.mParent;
                if (constraintWidget8 != null) {
                    DimensionDependency dimensionDependency4 = constraintWidget8.mVerticalRun.mDimension;
                    dimensionDependency.mTargets.add(dimensionDependency4);
                    dimensionDependency4.mDependencies.add(this.mDimension);
                    DimensionDependency dimensionDependency5 = this.mDimension;
                    dimensionDependency5.delegateToWidgetRun = true;
                    dimensionDependency5.mDependencies.add(this.start);
                    this.mDimension.mDependencies.add(this.end);
                }
            }
        }
        ConstraintWidget constraintWidget9 = this.mWidget;
        ConstraintAnchor[] constraintAnchorArr2 = constraintWidget9.mListAnchors;
        ConstraintAnchor constraintAnchor4 = constraintAnchorArr2[0];
        ConstraintAnchor constraintAnchor5 = constraintAnchor4.mTarget;
        if (constraintAnchor5 != null && constraintAnchorArr2[1].mTarget != null) {
            if (constraintWidget9.isInHorizontalChain()) {
                this.start.mMargin = this.mWidget.mListAnchors[0].getMargin();
                this.end.mMargin = -this.mWidget.mListAnchors[1].getMargin();
                return;
            }
            DependencyNode target$ar$ds5 = getTarget$ar$ds(this.mWidget.mListAnchors[0]);
            DependencyNode target$ar$ds6 = getTarget$ar$ds(this.mWidget.mListAnchors[1]);
            if (target$ar$ds5 != null) {
                target$ar$ds5.addDependency(this);
            }
            if (target$ar$ds6 != null) {
                target$ar$ds6.addDependency(this);
            }
            this.mRunType$ar$edu = 4;
            return;
        }
        if (constraintAnchor5 != null) {
            DependencyNode target$ar$ds7 = getTarget$ar$ds(constraintAnchor4);
            if (target$ar$ds7 != null) {
                addTarget$ar$ds(this.start, target$ar$ds7, this.mWidget.mListAnchors[0].getMargin());
                addTarget(this.end, this.start, 1, this.mDimension);
                return;
            }
            return;
        }
        ConstraintAnchor constraintAnchor6 = constraintAnchorArr2[1];
        if (constraintAnchor6.mTarget != null) {
            DependencyNode target$ar$ds8 = getTarget$ar$ds(constraintAnchor6);
            if (target$ar$ds8 != null) {
                addTarget$ar$ds(this.end, target$ar$ds8, -this.mWidget.mListAnchors[1].getMargin());
                addTarget(this.start, this.end, -1, this.mDimension);
                return;
            }
            return;
        }
        if (!(constraintWidget9 instanceof HelperWidget) && (constraintWidget2 = constraintWidget9.mParent) != null) {
            addTarget$ar$ds(this.start, constraintWidget2.mHorizontalRun.start, constraintWidget9.getX());
            addTarget(this.end, this.start, 1, this.mDimension);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void applyToWidget() {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.resolved) {
            this.mWidget.mX = dependencyNode.value;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void clear() {
        this.mRunGroup = null;
        this.start.clear();
        this.end.clear();
        this.mDimension.clear();
        this.mResolved = false;
    }

    public final void reset() {
        this.mResolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.mDimension.resolved = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final boolean supportsWrapComputation() {
        if (this.mDimensionBehavior$ar$edu != 3 || this.mWidget.mMatchConstraintDefaultWidth == 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "HorizontalRun ".concat(String.valueOf(this.mWidget.mDebugName));
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public final void update(Dependency dependency) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        float f;
        float f2;
        float f3;
        int i = this.mRunType$ar$edu;
        int i2 = i - 1;
        if (i != 0) {
            if (i2 != 3) {
                if (!this.mDimension.resolved && this.mDimensionBehavior$ar$edu == 3) {
                    ConstraintWidget constraintWidget = this.mWidget;
                    int i3 = constraintWidget.mMatchConstraintDefaultWidth;
                    if (i3 != 2) {
                        if (i3 == 3) {
                            int i4 = constraintWidget.mMatchConstraintDefaultHeight;
                            if (i4 != 0 && i4 != 3) {
                                int i5 = constraintWidget.mDimensionRatioSide;
                                if (i5 != -1) {
                                    if (i5 != 0) {
                                        f = constraintWidget.mVerticalRun.mDimension.value;
                                        f2 = constraintWidget.mDimensionRatio;
                                    } else {
                                        f3 = constraintWidget.mVerticalRun.mDimension.value / constraintWidget.mDimensionRatio;
                                        this.mDimension.resolve((int) (f3 + 0.5f));
                                    }
                                } else {
                                    f = constraintWidget.mVerticalRun.mDimension.value;
                                    f2 = constraintWidget.mDimensionRatio;
                                }
                                f3 = f * f2;
                                this.mDimension.resolve((int) (f3 + 0.5f));
                            } else {
                                VerticalWidgetRun verticalWidgetRun = constraintWidget.mVerticalRun;
                                DependencyNode dependencyNode = verticalWidgetRun.start;
                                DependencyNode dependencyNode2 = verticalWidgetRun.end;
                                if (constraintWidget.mLeft.mTarget != null) {
                                    z = true;
                                } else {
                                    z = false;
                                }
                                if (constraintWidget.mTop.mTarget != null) {
                                    z2 = true;
                                } else {
                                    z2 = false;
                                }
                                if (constraintWidget.mRight.mTarget != null) {
                                    z3 = true;
                                } else {
                                    z3 = false;
                                }
                                if (constraintWidget.mBottom.mTarget != null) {
                                    z4 = true;
                                } else {
                                    z4 = false;
                                }
                                int i6 = constraintWidget.mDimensionRatioSide;
                                if (z && z2 && z3 && z4) {
                                    float f4 = constraintWidget.mDimensionRatio;
                                    if (dependencyNode.resolved && dependencyNode2.resolved) {
                                        DependencyNode dependencyNode3 = this.start;
                                        if (dependencyNode3.readyToSolve && this.end.readyToSolve) {
                                            int i7 = ((DependencyNode) dependencyNode3.mTargets.get(0)).value + this.start.mMargin;
                                            int i8 = ((DependencyNode) this.end.mTargets.get(0)).value - this.end.mMargin;
                                            int i9 = dependencyNode.value + dependencyNode.mMargin;
                                            int i10 = dependencyNode2.value - dependencyNode2.mMargin;
                                            int[] iArr = sTempDimensions;
                                            computeInsetRatio$ar$ds(iArr, i7, i8, i9, i10, f4, i6);
                                            this.mDimension.resolve(iArr[0]);
                                            this.mWidget.mVerticalRun.mDimension.resolve(iArr[1]);
                                            return;
                                        }
                                        return;
                                    }
                                    DependencyNode dependencyNode4 = this.start;
                                    if (dependencyNode4.resolved) {
                                        DependencyNode dependencyNode5 = this.end;
                                        if (dependencyNode5.resolved) {
                                            if (dependencyNode.readyToSolve && dependencyNode2.readyToSolve) {
                                                int i11 = dependencyNode4.value + dependencyNode4.mMargin;
                                                int i12 = dependencyNode5.value - dependencyNode5.mMargin;
                                                int i13 = ((DependencyNode) dependencyNode.mTargets.get(0)).value + dependencyNode.mMargin;
                                                int i14 = ((DependencyNode) dependencyNode2.mTargets.get(0)).value - dependencyNode2.mMargin;
                                                int[] iArr2 = sTempDimensions;
                                                computeInsetRatio$ar$ds(iArr2, i11, i12, i13, i14, f4, i6);
                                                this.mDimension.resolve(iArr2[0]);
                                                this.mWidget.mVerticalRun.mDimension.resolve(iArr2[1]);
                                            } else {
                                                return;
                                            }
                                        }
                                    }
                                    DependencyNode dependencyNode6 = this.start;
                                    if (dependencyNode6.readyToSolve && this.end.readyToSolve && dependencyNode.readyToSolve && dependencyNode2.readyToSolve) {
                                        int i15 = ((DependencyNode) dependencyNode6.mTargets.get(0)).value + this.start.mMargin;
                                        int i16 = ((DependencyNode) this.end.mTargets.get(0)).value - this.end.mMargin;
                                        int i17 = ((DependencyNode) dependencyNode.mTargets.get(0)).value + dependencyNode.mMargin;
                                        int i18 = ((DependencyNode) dependencyNode2.mTargets.get(0)).value - dependencyNode2.mMargin;
                                        int[] iArr3 = sTempDimensions;
                                        computeInsetRatio$ar$ds(iArr3, i15, i16, i17, i18, f4, i6);
                                        this.mDimension.resolve(iArr3[0]);
                                        this.mWidget.mVerticalRun.mDimension.resolve(iArr3[1]);
                                    } else {
                                        return;
                                    }
                                } else if (z && z3) {
                                    DependencyNode dependencyNode7 = this.start;
                                    if (dependencyNode7.readyToSolve && this.end.readyToSolve) {
                                        float f5 = constraintWidget.mDimensionRatio;
                                        int i19 = (((DependencyNode) this.end.mTargets.get(0)).value - this.end.mMargin) - (((DependencyNode) dependencyNode7.mTargets.get(0)).value + this.start.mMargin);
                                        if (i6 != -1 && i6 != 0) {
                                            int limitedDimension = getLimitedDimension(i19, 0);
                                            int i20 = (int) ((limitedDimension / f5) + 0.5f);
                                            int limitedDimension2 = getLimitedDimension(i20, 1);
                                            if (i20 != limitedDimension2) {
                                                limitedDimension = (int) ((limitedDimension2 * f5) + 0.5f);
                                            }
                                            this.mDimension.resolve(limitedDimension);
                                            this.mWidget.mVerticalRun.mDimension.resolve(limitedDimension2);
                                        } else {
                                            int limitedDimension3 = getLimitedDimension(i19, 0);
                                            int i21 = (int) ((limitedDimension3 * f5) + 0.5f);
                                            int limitedDimension4 = getLimitedDimension(i21, 1);
                                            if (i21 != limitedDimension4) {
                                                limitedDimension3 = (int) ((limitedDimension4 / f5) + 0.5f);
                                            }
                                            this.mDimension.resolve(limitedDimension3);
                                            this.mWidget.mVerticalRun.mDimension.resolve(limitedDimension4);
                                        }
                                    } else {
                                        return;
                                    }
                                } else if (z2 && z4) {
                                    if (dependencyNode.readyToSolve && dependencyNode2.readyToSolve) {
                                        float f6 = constraintWidget.mDimensionRatio;
                                        int i22 = (((DependencyNode) dependencyNode2.mTargets.get(0)).value - dependencyNode2.mMargin) - (((DependencyNode) dependencyNode.mTargets.get(0)).value + dependencyNode.mMargin);
                                        if (i6 != 0) {
                                            int limitedDimension5 = getLimitedDimension(i22, 1);
                                            int i23 = (int) ((limitedDimension5 / f6) + 0.5f);
                                            int limitedDimension6 = getLimitedDimension(i23, 0);
                                            if (i23 != limitedDimension6) {
                                                limitedDimension5 = (int) ((limitedDimension6 * f6) + 0.5f);
                                            }
                                            this.mDimension.resolve(limitedDimension6);
                                            this.mWidget.mVerticalRun.mDimension.resolve(limitedDimension5);
                                        } else {
                                            int limitedDimension7 = getLimitedDimension(i22, 1);
                                            int i24 = (int) ((limitedDimension7 * f6) + 0.5f);
                                            int limitedDimension8 = getLimitedDimension(i24, 0);
                                            if (i24 != limitedDimension8) {
                                                limitedDimension7 = (int) ((limitedDimension8 / f6) + 0.5f);
                                            }
                                            this.mDimension.resolve(limitedDimension8);
                                            this.mWidget.mVerticalRun.mDimension.resolve(limitedDimension7);
                                        }
                                    } else {
                                        return;
                                    }
                                }
                            }
                        }
                    } else {
                        ConstraintWidget constraintWidget2 = constraintWidget.mParent;
                        if (constraintWidget2 != null) {
                            if (constraintWidget2.mHorizontalRun.mDimension.resolved) {
                                this.mDimension.resolve((int) ((r6.value * constraintWidget.mMatchConstraintPercentWidth) + 0.5f));
                            }
                        }
                    }
                }
                DependencyNode dependencyNode8 = this.start;
                if (dependencyNode8.readyToSolve) {
                    DependencyNode dependencyNode9 = this.end;
                    if (dependencyNode9.readyToSolve) {
                        if (!dependencyNode8.resolved || !dependencyNode9.resolved || !this.mDimension.resolved) {
                            if (!this.mDimension.resolved && this.mDimensionBehavior$ar$edu == 3) {
                                ConstraintWidget constraintWidget3 = this.mWidget;
                                if (constraintWidget3.mMatchConstraintDefaultWidth == 0 && !constraintWidget3.isInHorizontalChain()) {
                                    DependencyNode dependencyNode10 = (DependencyNode) this.start.mTargets.get(0);
                                    DependencyNode dependencyNode11 = (DependencyNode) this.end.mTargets.get(0);
                                    int i25 = dependencyNode10.value;
                                    DependencyNode dependencyNode12 = this.start;
                                    int i26 = i25 + dependencyNode12.mMargin;
                                    int i27 = dependencyNode11.value + this.end.mMargin;
                                    dependencyNode12.resolve(i26);
                                    this.end.resolve(i27);
                                    this.mDimension.resolve(i27 - i26);
                                    return;
                                }
                            }
                            if (!this.mDimension.resolved && this.mDimensionBehavior$ar$edu == 3 && this.matchConstraintsType == 1 && this.start.mTargets.size() > 0 && this.end.mTargets.size() > 0) {
                                DependencyNode dependencyNode13 = (DependencyNode) this.start.mTargets.get(0);
                                DependencyNode dependencyNode14 = (DependencyNode) this.end.mTargets.get(0);
                                int min = Math.min((dependencyNode14.value + this.end.mMargin) - (dependencyNode13.value + this.start.mMargin), this.mDimension.wrapValue);
                                ConstraintWidget constraintWidget4 = this.mWidget;
                                int i28 = constraintWidget4.mMatchConstraintMaxWidth;
                                int max = Math.max(constraintWidget4.mMatchConstraintMinWidth, min);
                                if (i28 > 0) {
                                    max = Math.min(i28, max);
                                }
                                this.mDimension.resolve(max);
                            }
                            if (this.mDimension.resolved) {
                                DependencyNode dependencyNode15 = (DependencyNode) this.start.mTargets.get(0);
                                DependencyNode dependencyNode16 = (DependencyNode) this.end.mTargets.get(0);
                                int i29 = dependencyNode15.value;
                                DependencyNode dependencyNode17 = this.start;
                                int i30 = dependencyNode17.mMargin + i29;
                                int i31 = dependencyNode16.value;
                                int i32 = this.end.mMargin + i31;
                                float f7 = this.mWidget.mHorizontalBiasPercent;
                                if (dependencyNode15 == dependencyNode16) {
                                    f7 = 0.5f;
                                }
                                if (dependencyNode15 != dependencyNode16) {
                                    i31 = i32;
                                }
                                if (dependencyNode15 != dependencyNode16) {
                                    i29 = i30;
                                }
                                dependencyNode17.resolve((int) (i29 + 0.5f + (((i31 - i29) - this.mDimension.value) * f7)));
                                this.end.resolve(this.start.value + this.mDimension.value);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                return;
            }
            ConstraintWidget constraintWidget5 = this.mWidget;
            updateRunCenter$ar$ds(constraintWidget5.mLeft, constraintWidget5.mRight, 0);
            return;
        }
        throw null;
    }
}
