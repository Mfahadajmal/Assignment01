package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.Guideline;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class GuidelineReference extends WidgetRun {
    public GuidelineReference(ConstraintWidget constraintWidget) {
        super(constraintWidget);
        constraintWidget.mHorizontalRun.clear();
        constraintWidget.mVerticalRun.clear();
        this.orientation = ((Guideline) constraintWidget).mOrientation;
    }

    private final void addDependency(DependencyNode dependencyNode) {
        this.start.mDependencies.add(dependencyNode);
        dependencyNode.mTargets.add(this.start);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void apply() {
        ConstraintWidget constraintWidget = this.mWidget;
        Guideline guideline = (Guideline) constraintWidget;
        int i = guideline.mRelativeBegin;
        int i2 = guideline.mRelativeEnd;
        float f = guideline.mRelativePercent;
        if (guideline.mOrientation == 1) {
            if (i != -1) {
                this.start.mTargets.add(constraintWidget.mParent.mHorizontalRun.start);
                this.mWidget.mParent.mHorizontalRun.start.mDependencies.add(this.start);
                this.start.mMargin = i;
            } else if (i2 != -1) {
                this.start.mTargets.add(constraintWidget.mParent.mHorizontalRun.end);
                this.mWidget.mParent.mHorizontalRun.end.mDependencies.add(this.start);
                this.start.mMargin = -i2;
            } else {
                DependencyNode dependencyNode = this.start;
                dependencyNode.delegateToWidgetRun = true;
                dependencyNode.mTargets.add(constraintWidget.mParent.mHorizontalRun.end);
                this.mWidget.mParent.mHorizontalRun.end.mDependencies.add(this.start);
            }
            addDependency(this.mWidget.mHorizontalRun.start);
            addDependency(this.mWidget.mHorizontalRun.end);
            return;
        }
        if (i != -1) {
            this.start.mTargets.add(constraintWidget.mParent.mVerticalRun.start);
            this.mWidget.mParent.mVerticalRun.start.mDependencies.add(this.start);
            this.start.mMargin = i;
        } else if (i2 != -1) {
            this.start.mTargets.add(constraintWidget.mParent.mVerticalRun.end);
            this.mWidget.mParent.mVerticalRun.end.mDependencies.add(this.start);
            this.start.mMargin = -i2;
        } else {
            DependencyNode dependencyNode2 = this.start;
            dependencyNode2.delegateToWidgetRun = true;
            dependencyNode2.mTargets.add(constraintWidget.mParent.mVerticalRun.end);
            this.mWidget.mParent.mVerticalRun.end.mDependencies.add(this.start);
        }
        addDependency(this.mWidget.mVerticalRun.start);
        addDependency(this.mWidget.mVerticalRun.end);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void applyToWidget() {
        ConstraintWidget constraintWidget = this.mWidget;
        if (((Guideline) constraintWidget).mOrientation == 1) {
            constraintWidget.mX = this.start.value;
        } else {
            constraintWidget.mY = this.start.value;
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void clear() {
        this.start.clear();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final boolean supportsWrapComputation() {
        return false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public final void update(Dependency dependency) {
        DependencyNode dependencyNode = this.start;
        if (dependencyNode.readyToSolve && !dependencyNode.resolved) {
            this.start.resolve((int) ((((DependencyNode) dependencyNode.mTargets.get(0)).value * ((Guideline) this.mWidget).mRelativePercent) + 0.5f));
        }
    }
}
