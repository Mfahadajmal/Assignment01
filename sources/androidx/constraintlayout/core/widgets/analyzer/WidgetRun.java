package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class WidgetRun implements Dependency {
    protected int mDimensionBehavior$ar$edu;
    RunGroup mRunGroup;
    public ConstraintWidget mWidget;
    public int matchConstraintsType;
    public final DimensionDependency mDimension = new DimensionDependency(this);
    public int orientation = 0;
    public boolean mResolved = false;
    public final DependencyNode start = new DependencyNode(this);
    public final DependencyNode end = new DependencyNode(this);
    protected int mRunType$ar$edu = 1;

    public WidgetRun(ConstraintWidget constraintWidget) {
        this.mWidget = constraintWidget;
    }

    public static final void addTarget$ar$ds(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i) {
        dependencyNode.mTargets.add(dependencyNode2);
        dependencyNode.mMargin = i;
        dependencyNode2.mDependencies.add(dependencyNode);
    }

    public static final DependencyNode getTarget$ar$ds(ConstraintAnchor constraintAnchor) {
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 != null) {
            int i = constraintAnchor2.mType$ar$edu$21f04e0f_0;
            ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
            int i2 = i - 1;
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 != 3) {
                        if (i2 != 4) {
                            if (i2 != 5) {
                                return null;
                            }
                            return constraintWidget.mVerticalRun.baseline;
                        }
                        return constraintWidget.mVerticalRun.end;
                    }
                    return constraintWidget.mHorizontalRun.end;
                }
                return constraintWidget.mVerticalRun.start;
            }
            return constraintWidget.mHorizontalRun.start;
        }
        return null;
    }

    public static final DependencyNode getTarget$ar$ds$554e726e_0(ConstraintAnchor constraintAnchor, int i) {
        WidgetRun widgetRun;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 != null) {
            ConstraintWidget constraintWidget = constraintAnchor2.mOwner;
            if (i == 0) {
                widgetRun = constraintWidget.mHorizontalRun;
            } else {
                widgetRun = constraintWidget.mVerticalRun;
            }
            int i2 = constraintAnchor2.mType$ar$edu$21f04e0f_0 - 1;
            if (i2 != 1 && i2 != 2) {
                if (i2 != 3 && i2 != 4) {
                    return null;
                }
                return widgetRun.end;
            }
            return widgetRun.start;
        }
        return null;
    }

    public final void addTarget(DependencyNode dependencyNode, DependencyNode dependencyNode2, int i, DimensionDependency dimensionDependency) {
        dependencyNode.mTargets.add(dependencyNode2);
        dependencyNode.mTargets.add(this.mDimension);
        dependencyNode.mMarginFactor = i;
        dependencyNode.mMarginDependency = dimensionDependency;
        dependencyNode2.mDependencies.add(dependencyNode);
        dimensionDependency.mDependencies.add(dependencyNode);
    }

    public abstract void apply();

    public abstract void applyToWidget();

    public abstract void clear();

    public final int getLimitedDimension(int i, int i2) {
        if (i2 == 0) {
            ConstraintWidget constraintWidget = this.mWidget;
            int i3 = constraintWidget.mMatchConstraintMaxWidth;
            int max = Math.max(constraintWidget.mMatchConstraintMinWidth, i);
            if (i3 > 0) {
                max = Math.min(i3, i);
            }
            if (max != i) {
                return max;
            }
            return i;
        }
        ConstraintWidget constraintWidget2 = this.mWidget;
        int i4 = constraintWidget2.mMatchConstraintMaxHeight;
        int max2 = Math.max(constraintWidget2.mMatchConstraintMinHeight, i);
        if (i4 > 0) {
            max2 = Math.min(i4, i);
        }
        if (max2 == i) {
            return i;
        }
        return max2;
    }

    public long getWrapDimension() {
        if (this.mDimension.resolved) {
            return r0.value;
        }
        return 0L;
    }

    public abstract boolean supportsWrapComputation();

    @Override // androidx.constraintlayout.core.widgets.analyzer.Dependency
    public void update(Dependency dependency) {
        throw null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0056, code lost:
    
        if (r11.matchConstraintsType == 3) goto L116;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void updateRunCenter$ar$ds(androidx.constraintlayout.core.widgets.ConstraintAnchor r10, androidx.constraintlayout.core.widgets.ConstraintAnchor r11, int r12) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.analyzer.WidgetRun.updateRunCenter$ar$ds(androidx.constraintlayout.core.widgets.ConstraintAnchor, androidx.constraintlayout.core.widgets.ConstraintAnchor, int):void");
    }
}
