package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.Barrier;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import java.util.Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class HelperReferences extends WidgetRun {
    public HelperReferences(ConstraintWidget constraintWidget) {
        super(constraintWidget);
    }

    private final void addDependency(DependencyNode dependencyNode) {
        this.start.mDependencies.add(dependencyNode);
        dependencyNode.mTargets.add(this.start);
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void apply() {
        ConstraintWidget constraintWidget = this.mWidget;
        if (constraintWidget instanceof Barrier) {
            DependencyNode dependencyNode = this.start;
            dependencyNode.delegateToWidgetRun = true;
            Barrier barrier = (Barrier) constraintWidget;
            int i = barrier.mBarrierType;
            boolean z = barrier.mAllowsGoneWidget;
            int i2 = 0;
            if (i != 0) {
                if (i != 1) {
                    if (i != 2) {
                        if (i == 3) {
                            dependencyNode.mType$ar$edu$7d38fd09_0 = 7;
                            while (i2 < barrier.mWidgetsCount) {
                                ConstraintWidget constraintWidget2 = barrier.mWidgets[i2];
                                if (z || constraintWidget2.mVisibility != 8) {
                                    DependencyNode dependencyNode2 = constraintWidget2.mVerticalRun.end;
                                    dependencyNode2.mDependencies.add(this.start);
                                    this.start.mTargets.add(dependencyNode2);
                                }
                                i2++;
                            }
                            addDependency(this.mWidget.mVerticalRun.start);
                            addDependency(this.mWidget.mVerticalRun.end);
                            return;
                        }
                        return;
                    }
                    dependencyNode.mType$ar$edu$7d38fd09_0 = 6;
                    while (i2 < barrier.mWidgetsCount) {
                        ConstraintWidget constraintWidget3 = barrier.mWidgets[i2];
                        if (z || constraintWidget3.mVisibility != 8) {
                            DependencyNode dependencyNode3 = constraintWidget3.mVerticalRun.start;
                            dependencyNode3.mDependencies.add(this.start);
                            this.start.mTargets.add(dependencyNode3);
                        }
                        i2++;
                    }
                    addDependency(this.mWidget.mVerticalRun.start);
                    addDependency(this.mWidget.mVerticalRun.end);
                    return;
                }
                dependencyNode.mType$ar$edu$7d38fd09_0 = 5;
                while (i2 < barrier.mWidgetsCount) {
                    ConstraintWidget constraintWidget4 = barrier.mWidgets[i2];
                    if (z || constraintWidget4.mVisibility != 8) {
                        DependencyNode dependencyNode4 = constraintWidget4.mHorizontalRun.end;
                        dependencyNode4.mDependencies.add(this.start);
                        this.start.mTargets.add(dependencyNode4);
                    }
                    i2++;
                }
                addDependency(this.mWidget.mHorizontalRun.start);
                addDependency(this.mWidget.mHorizontalRun.end);
                return;
            }
            dependencyNode.mType$ar$edu$7d38fd09_0 = 4;
            while (i2 < barrier.mWidgetsCount) {
                ConstraintWidget constraintWidget5 = barrier.mWidgets[i2];
                if (z || constraintWidget5.mVisibility != 8) {
                    DependencyNode dependencyNode5 = constraintWidget5.mHorizontalRun.start;
                    dependencyNode5.mDependencies.add(this.start);
                    this.start.mTargets.add(dependencyNode5);
                }
                i2++;
            }
            addDependency(this.mWidget.mHorizontalRun.start);
            addDependency(this.mWidget.mHorizontalRun.end);
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void applyToWidget() {
        ConstraintWidget constraintWidget = this.mWidget;
        if (constraintWidget instanceof Barrier) {
            int i = ((Barrier) constraintWidget).mBarrierType;
            if (i != 0 && i != 1) {
                constraintWidget.mY = this.start.value;
            } else {
                constraintWidget.mX = this.start.value;
            }
        }
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final void clear() {
        this.mRunGroup = null;
        this.start.clear();
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun
    public final boolean supportsWrapComputation() {
        return false;
    }

    @Override // androidx.constraintlayout.core.widgets.analyzer.WidgetRun, androidx.constraintlayout.core.widgets.analyzer.Dependency
    public final void update(Dependency dependency) {
        Barrier barrier = (Barrier) this.mWidget;
        int i = barrier.mBarrierType;
        Iterator it = this.start.mTargets.iterator();
        int i2 = 0;
        int i3 = -1;
        while (it.hasNext()) {
            int i4 = ((DependencyNode) it.next()).value;
            if (i3 == -1 || i4 < i3) {
                i3 = i4;
            }
            if (i2 < i4) {
                i2 = i4;
            }
        }
        if (i != 0 && i != 2) {
            this.start.resolve(i2 + barrier.mMargin);
        } else {
            this.start.resolve(i3 + barrier.mMargin);
        }
    }
}
