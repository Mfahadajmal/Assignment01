package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class VerticalWidgetRun extends WidgetRun {
    public final DependencyNode baseline;
    DimensionDependency mBaselineDimension;

    public VerticalWidgetRun(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        DependencyNode dependencyNode = new DependencyNode(this);
        this.baseline = dependencyNode;
        this.mBaselineDimension = null;
        this.start.mType$ar$edu$7d38fd09_0 = 6;
        this.end.mType$ar$edu$7d38fd09_0 = 7;
        dependencyNode.mType$ar$edu$7d38fd09_0 = 8;
        this.orientation = 1;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0412  */
    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void apply() {
        /*
            Method dump skipped, instructions count: 1047
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun.apply():void");
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void applyToWidget() {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.resolved) {
            this.mWidget.mY = dependencyNode.value;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void clear() {
        this.mRunGroup = null;
        this.start.clear();
        this.end.clear();
        this.baseline.clear();
        this.mDimension.clear();
        this.mResolved = false;
    }

    public final void reset() {
        this.mResolved = false;
        this.start.clear();
        this.start.resolved = false;
        this.end.clear();
        this.end.resolved = false;
        this.baseline.clear();
        this.baseline.resolved = false;
        this.mDimension.resolved = false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final boolean supportsWrapComputation() {
        if (this.mDimensionBehavior$ar$edu != 3 || this.mWidget.mMatchConstraintDefaultHeight == 0) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "VerticalRun ".concat(String.valueOf(this.mWidget.mDebugName));
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public final void update(Dependency dependency) {
        float f;
        float f2;
        float f3;
        int i = this.mRunType$ar$edu;
        int i2 = i - 1;
        if (i != 0) {
            if (i2 != 3) {
                DimensionDependency dimensionDependency = this.mDimension;
                if (dimensionDependency.readyToSolve && !dimensionDependency.resolved && this.mDimensionBehavior$ar$edu == 3) {
                    ConstraintWidget constraintWidget = this.mWidget;
                    int i3 = constraintWidget.mMatchConstraintDefaultHeight;
                    if (i3 != 2) {
                        if (i3 == 3) {
                            DimensionDependency dimensionDependency2 = constraintWidget.mHorizontalRun.mDimension;
                            if (dimensionDependency2.resolved) {
                                int i4 = constraintWidget.mDimensionRatioSide;
                                if (i4 != -1) {
                                    if (i4 != 0) {
                                        f = dimensionDependency2.value;
                                        f2 = constraintWidget.mDimensionRatio;
                                    } else {
                                        f3 = dimensionDependency2.value * constraintWidget.mDimensionRatio;
                                        this.mDimension.resolve((int) (f3 + 0.5f));
                                    }
                                } else {
                                    f = dimensionDependency2.value;
                                    f2 = constraintWidget.mDimensionRatio;
                                }
                                f3 = f / f2;
                                this.mDimension.resolve((int) (f3 + 0.5f));
                            }
                        }
                    } else {
                        ConstraintWidget constraintWidget2 = constraintWidget.mParent;
                        if (constraintWidget2 != null) {
                            if (constraintWidget2.mVerticalRun.mDimension.resolved) {
                                this.mDimension.resolve((int) ((r2.value * constraintWidget.mMatchConstraintPercentHeight) + 0.5f));
                            }
                        }
                    }
                }
                DependencyNode dependencyNode = this.start;
                if (dependencyNode.readyToSolve) {
                    DependencyNode dependencyNode2 = this.end;
                    if (dependencyNode2.readyToSolve) {
                        if (!dependencyNode.resolved || !dependencyNode2.resolved || !this.mDimension.resolved) {
                            if (!this.mDimension.resolved && this.mDimensionBehavior$ar$edu == 3) {
                                ConstraintWidget constraintWidget3 = this.mWidget;
                                if (constraintWidget3.mMatchConstraintDefaultWidth == 0 && !constraintWidget3.isInVerticalChain()) {
                                    DependencyNode dependencyNode3 = (DependencyNode) this.start.mTargets.get(0);
                                    DependencyNode dependencyNode4 = (DependencyNode) this.end.mTargets.get(0);
                                    int i5 = dependencyNode3.value;
                                    DependencyNode dependencyNode5 = this.start;
                                    int i6 = i5 + dependencyNode5.mMargin;
                                    int i7 = dependencyNode4.value + this.end.mMargin;
                                    dependencyNode5.resolve(i6);
                                    this.end.resolve(i7);
                                    this.mDimension.resolve(i7 - i6);
                                    return;
                                }
                            }
                            if (!this.mDimension.resolved && this.mDimensionBehavior$ar$edu == 3 && this.matchConstraintsType == 1 && this.start.mTargets.size() > 0 && this.end.mTargets.size() > 0) {
                                DependencyNode dependencyNode6 = (DependencyNode) this.start.mTargets.get(0);
                                DependencyNode dependencyNode7 = (DependencyNode) this.end.mTargets.get(0);
                                int i8 = dependencyNode6.value + this.start.mMargin;
                                int i9 = dependencyNode7.value + this.end.mMargin;
                                DimensionDependency dimensionDependency3 = this.mDimension;
                                int i10 = i9 - i8;
                                int i11 = dimensionDependency3.wrapValue;
                                if (i10 < i11) {
                                    dimensionDependency3.resolve(i10);
                                } else {
                                    dimensionDependency3.resolve(i11);
                                }
                            }
                            if (this.mDimension.resolved && this.start.mTargets.size() > 0 && this.end.mTargets.size() > 0) {
                                DependencyNode dependencyNode8 = (DependencyNode) this.start.mTargets.get(0);
                                DependencyNode dependencyNode9 = (DependencyNode) this.end.mTargets.get(0);
                                int i12 = dependencyNode8.value;
                                DependencyNode dependencyNode10 = this.start;
                                int i13 = dependencyNode10.mMargin + i12;
                                int i14 = dependencyNode9.value;
                                int i15 = this.end.mMargin + i14;
                                float f4 = this.mWidget.mVerticalBiasPercent;
                                if (dependencyNode8 == dependencyNode9) {
                                    f4 = 0.5f;
                                }
                                if (dependencyNode8 != dependencyNode9) {
                                    i14 = i15;
                                }
                                if (dependencyNode8 != dependencyNode9) {
                                    i12 = i13;
                                }
                                dependencyNode10.resolve((int) (i12 + 0.5f + (((i14 - i12) - this.mDimension.value) * f4)));
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
            ConstraintWidget constraintWidget4 = this.mWidget;
            updateRunCenter$ar$ds(constraintWidget4.mTop, constraintWidget4.mBottom, 1);
            return;
        }
        throw null;
    }
}
