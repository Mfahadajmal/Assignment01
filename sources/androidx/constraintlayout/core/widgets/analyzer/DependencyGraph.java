package androidx.constraintlayout.core.widgets.analyzer;

import androidx.constraintlayout.core.widgets.ConstraintAnchor;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import androidx.constraintlayout.core.widgets.Guideline;
import androidx.constraintlayout.core.widgets.HelperWidget;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DependencyGraph {
    public final ConstraintWidgetContainer mContainer;
    final ArrayList mGroups;
    private final BasicMeasure$Measure mMeasure;
    public ConstraintLayout.Measurer mMeasurer$ar$class_merging;
    public boolean mNeedBuildGraph = true;
    public boolean mNeedRedoMeasures = true;
    public final ArrayList mRuns = new ArrayList();
    public final ConstraintWidgetContainer mWidgetcontainer;

    public DependencyGraph(ConstraintWidgetContainer constraintWidgetContainer) {
        new ArrayList();
        this.mMeasurer$ar$class_merging = null;
        this.mMeasure = new BasicMeasure$Measure();
        this.mGroups = new ArrayList();
        this.mWidgetcontainer = constraintWidgetContainer;
        this.mContainer = constraintWidgetContainer;
    }

    private final void applyGroup(DependencyNode dependencyNode, int i, int i2, DependencyNode dependencyNode2, ArrayList arrayList, RunGroup runGroup) {
        WidgetRun widgetRun = dependencyNode.mRun;
        if (widgetRun.mRunGroup == null) {
            ConstraintWidgetContainer constraintWidgetContainer = this.mWidgetcontainer;
            if (widgetRun != constraintWidgetContainer.mHorizontalRun && widgetRun != constraintWidgetContainer.mVerticalRun) {
                if (runGroup == null) {
                    runGroup = new RunGroup(widgetRun);
                    arrayList.add(runGroup);
                }
                widgetRun.mRunGroup = runGroup;
                ((ArrayList) runGroup.RunGroup$ar$mRuns).add(widgetRun);
                for (Dependency dependency : widgetRun.start.mDependencies) {
                    if (dependency instanceof DependencyNode) {
                        applyGroup((DependencyNode) dependency, i, 0, dependencyNode2, arrayList, runGroup);
                    }
                }
                for (Dependency dependency2 : widgetRun.end.mDependencies) {
                    if (dependency2 instanceof DependencyNode) {
                        applyGroup((DependencyNode) dependency2, i, 1, dependencyNode2, arrayList, runGroup);
                    }
                }
                if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                    for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.mDependencies) {
                        if (dependency3 instanceof DependencyNode) {
                            applyGroup((DependencyNode) dependency3, 1, 2, dependencyNode2, arrayList, runGroup);
                        }
                    }
                }
                Iterator it = widgetRun.start.mTargets.iterator();
                while (it.hasNext()) {
                    applyGroup((DependencyNode) it.next(), i, 0, dependencyNode2, arrayList, runGroup);
                }
                Iterator it2 = widgetRun.end.mTargets.iterator();
                while (it2.hasNext()) {
                    applyGroup((DependencyNode) it2.next(), i, 1, dependencyNode2, arrayList, runGroup);
                }
                if (i == 1 && (widgetRun instanceof VerticalWidgetRun)) {
                    Iterator it3 = ((VerticalWidgetRun) widgetRun).baseline.mTargets.iterator();
                    while (it3.hasNext()) {
                        applyGroup((DependencyNode) it3.next(), 1, 2, dependencyNode2, arrayList, runGroup);
                    }
                }
            }
        }
    }

    private final void findGroup(WidgetRun widgetRun, int i, ArrayList arrayList) {
        for (Dependency dependency : widgetRun.start.mDependencies) {
            if (dependency instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency, i, 0, widgetRun.end, arrayList, null);
            } else if (dependency instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency).start, i, 0, widgetRun.end, arrayList, null);
            }
        }
        for (Dependency dependency2 : widgetRun.end.mDependencies) {
            if (dependency2 instanceof DependencyNode) {
                applyGroup((DependencyNode) dependency2, i, 1, widgetRun.start, arrayList, null);
            } else if (dependency2 instanceof WidgetRun) {
                applyGroup(((WidgetRun) dependency2).end, i, 1, widgetRun.start, arrayList, null);
            }
        }
        if (i == 1) {
            for (Dependency dependency3 : ((VerticalWidgetRun) widgetRun).baseline.mDependencies) {
                if (dependency3 instanceof DependencyNode) {
                    applyGroup((DependencyNode) dependency3, 1, 2, null, arrayList, null);
                }
            }
        }
    }

    private final void measure$ar$edu(ConstraintWidget constraintWidget, int i, int i2, int i3, int i4) {
        BasicMeasure$Measure basicMeasure$Measure = this.mMeasure;
        basicMeasure$Measure.horizontalBehavior$ar$edu = i;
        basicMeasure$Measure.verticalBehavior$ar$edu = i3;
        basicMeasure$Measure.horizontalDimension = i2;
        basicMeasure$Measure.verticalDimension = i4;
        this.mMeasurer$ar$class_merging.measure(constraintWidget, basicMeasure$Measure);
        constraintWidget.setWidth(this.mMeasure.measuredWidth);
        constraintWidget.setHeight(this.mMeasure.measuredHeight);
        BasicMeasure$Measure basicMeasure$Measure2 = this.mMeasure;
        constraintWidget.mHasBaseline = basicMeasure$Measure2.measuredHasBaseline;
        constraintWidget.setBaselineDistance(basicMeasure$Measure2.measuredBaseline);
    }

    public final void basicMeasureWidgets$ar$ds(ConstraintWidgetContainer constraintWidgetContainer) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        ArrayList arrayList = constraintWidgetContainer.mChildren;
        int size = arrayList.size();
        for (int i6 = 0; i6 < size; i6++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) arrayList.get(i6);
            int[] iArr = constraintWidget.mListDimensionBehaviors$ar$edu;
            int i7 = iArr[0];
            int i8 = iArr[1];
            if (constraintWidget.mVisibility == 8) {
                constraintWidget.measured = true;
            } else {
                if (constraintWidget.mMatchConstraintPercentWidth < 1.0f && i7 == 3) {
                    constraintWidget.mMatchConstraintDefaultWidth = 2;
                }
                if (constraintWidget.mMatchConstraintPercentHeight < 1.0f && i8 == 3) {
                    constraintWidget.mMatchConstraintDefaultHeight = 2;
                }
                if (constraintWidget.mDimensionRatio > 0.0f) {
                    if (i7 == 3 && (i8 == 2 || i8 == 1)) {
                        constraintWidget.mMatchConstraintDefaultWidth = 3;
                    } else if (i8 == 3 && (i7 == 2 || i7 == 1)) {
                        constraintWidget.mMatchConstraintDefaultHeight = 3;
                    } else if (i7 == 3 && i8 == 3) {
                        if (constraintWidget.mMatchConstraintDefaultWidth == 0) {
                            constraintWidget.mMatchConstraintDefaultWidth = 3;
                        }
                        if (constraintWidget.mMatchConstraintDefaultHeight == 0) {
                            constraintWidget.mMatchConstraintDefaultHeight = 3;
                        }
                    }
                }
                if (i7 == 3 && constraintWidget.mMatchConstraintDefaultWidth == 1 && (constraintWidget.mLeft.mTarget == null || constraintWidget.mRight.mTarget == null)) {
                    i = 2;
                } else {
                    i = i7;
                }
                if (i8 == 3 && constraintWidget.mMatchConstraintDefaultHeight == 1 && (constraintWidget.mTop.mTarget == null || constraintWidget.mBottom.mTarget == null)) {
                    i2 = 2;
                } else {
                    i2 = i8;
                }
                HorizontalWidgetRun horizontalWidgetRun = constraintWidget.mHorizontalRun;
                horizontalWidgetRun.mDimensionBehavior$ar$edu = i;
                int i9 = constraintWidget.mMatchConstraintDefaultWidth;
                horizontalWidgetRun.matchConstraintsType = i9;
                VerticalWidgetRun verticalWidgetRun = constraintWidget.mVerticalRun;
                verticalWidgetRun.mDimensionBehavior$ar$edu = i2;
                int i10 = constraintWidget.mMatchConstraintDefaultHeight;
                verticalWidgetRun.matchConstraintsType = i10;
                if ((i != 4 && i != 1 && i != 2) || (i2 != 4 && i2 != 1 && i2 != 2)) {
                    if (i == 3 && (i2 == 2 || i2 == 1)) {
                        if (i9 == 3) {
                            if (i2 == 2) {
                                measure$ar$edu(constraintWidget, 2, 0, 2, 0);
                            }
                            int height = constraintWidget.getHeight();
                            measure$ar$edu(constraintWidget, 1, (int) ((height * constraintWidget.mDimensionRatio) + 0.5f), 1, height);
                            constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                            constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        } else if (i9 == 1) {
                            measure$ar$edu(constraintWidget, 2, 0, i2, 0);
                            constraintWidget.mHorizontalRun.mDimension.wrapValue = constraintWidget.getWidth();
                        } else if (i9 == 2) {
                            int i11 = constraintWidgetContainer.mListDimensionBehaviors$ar$edu[0];
                            if (i11 == 1 || i11 == 4) {
                                measure$ar$edu(constraintWidget, 1, (int) ((constraintWidget.mMatchConstraintPercentWidth * constraintWidgetContainer.getWidth()) + 0.5f), i2, constraintWidget.getHeight());
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr = constraintWidget.mListAnchors;
                            if (constraintAnchorArr[0].mTarget == null || constraintAnchorArr[1].mTarget == null) {
                                measure$ar$edu(constraintWidget, 2, 0, i2, 0);
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        }
                    }
                    if (i2 == 3 && (i == 2 || i == 1)) {
                        if (i10 == 3) {
                            if (i == 2) {
                                measure$ar$edu(constraintWidget, 2, 0, 2, 0);
                            }
                            int width = constraintWidget.getWidth();
                            float f = constraintWidget.mDimensionRatio;
                            if (constraintWidget.mDimensionRatioSide == -1) {
                                f = 1.0f / f;
                            }
                            measure$ar$edu(constraintWidget, 1, width, 1, (int) ((width * f) + 0.5f));
                            constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                            constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                            constraintWidget.measured = true;
                        } else if (i10 == 1) {
                            measure$ar$edu(constraintWidget, i, 0, 2, 0);
                            constraintWidget.mVerticalRun.mDimension.wrapValue = constraintWidget.getHeight();
                        } else if (i10 == 2) {
                            int i12 = constraintWidgetContainer.mListDimensionBehaviors$ar$edu[1];
                            if (i12 == 1 || i12 == 4) {
                                measure$ar$edu(constraintWidget, i, constraintWidget.getWidth(), 1, (int) ((constraintWidget.mMatchConstraintPercentHeight * constraintWidgetContainer.getHeight()) + 0.5f));
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        } else {
                            ConstraintAnchor[] constraintAnchorArr2 = constraintWidget.mListAnchors;
                            if (constraintAnchorArr2[2].mTarget == null || constraintAnchorArr2[3].mTarget == null) {
                                measure$ar$edu(constraintWidget, 2, 0, 3, 0);
                                constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                constraintWidget.measured = true;
                            }
                        }
                    }
                    if (i == 3 && i2 == 3) {
                        if (i9 != 1 && i10 != 1) {
                            if (i10 == 2 && i9 == 2) {
                                int[] iArr2 = constraintWidgetContainer.mListDimensionBehaviors$ar$edu;
                                if (iArr2[0] == 1 && iArr2[1] == 1) {
                                    measure$ar$edu(constraintWidget, 1, (int) ((constraintWidget.mMatchConstraintPercentWidth * constraintWidgetContainer.getWidth()) + 0.5f), 1, (int) ((constraintWidget.mMatchConstraintPercentHeight * constraintWidgetContainer.getHeight()) + 0.5f));
                                    constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                                    constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                                    constraintWidget.measured = true;
                                }
                            }
                        } else {
                            measure$ar$edu(constraintWidget, 2, 0, 2, 0);
                            constraintWidget.mHorizontalRun.mDimension.wrapValue = constraintWidget.getWidth();
                            constraintWidget.mVerticalRun.mDimension.wrapValue = constraintWidget.getHeight();
                        }
                    }
                } else {
                    int width2 = constraintWidget.getWidth();
                    if (i == 4) {
                        i3 = (constraintWidgetContainer.getWidth() - constraintWidget.mLeft.mMargin) - constraintWidget.mRight.mMargin;
                        i = 1;
                    } else {
                        i3 = width2;
                    }
                    int height2 = constraintWidget.getHeight();
                    if (i2 == 4) {
                        i5 = (constraintWidgetContainer.getHeight() - constraintWidget.mTop.mMargin) - constraintWidget.mBottom.mMargin;
                        i4 = 1;
                    } else {
                        i4 = i2;
                        i5 = height2;
                    }
                    measure$ar$edu(constraintWidget, i, i3, i4, i5);
                    constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                    constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                    constraintWidget.measured = true;
                }
            }
        }
    }

    public final void buildGraph() {
        ArrayList arrayList = this.mRuns;
        arrayList.clear();
        this.mContainer.mHorizontalRun.clear();
        this.mContainer.mVerticalRun.clear();
        arrayList.add(this.mContainer.mHorizontalRun);
        arrayList.add(this.mContainer.mVerticalRun);
        ArrayList arrayList2 = this.mContainer.mChildren;
        int size = arrayList2.size();
        HashSet hashSet = null;
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) arrayList2.get(i);
            if (constraintWidget instanceof Guideline) {
                arrayList.add(new GuidelineReference(constraintWidget));
            } else {
                if (constraintWidget.isInHorizontalChain()) {
                    if (constraintWidget.horizontalChainRun == null) {
                        constraintWidget.horizontalChainRun = new ChainRun(constraintWidget, 0);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.horizontalChainRun);
                } else {
                    arrayList.add(constraintWidget.mHorizontalRun);
                }
                if (constraintWidget.isInVerticalChain()) {
                    if (constraintWidget.verticalChainRun == null) {
                        constraintWidget.verticalChainRun = new ChainRun(constraintWidget, 1);
                    }
                    if (hashSet == null) {
                        hashSet = new HashSet();
                    }
                    hashSet.add(constraintWidget.verticalChainRun);
                } else {
                    arrayList.add(constraintWidget.mVerticalRun);
                }
                if (constraintWidget instanceof HelperWidget) {
                    arrayList.add(new HelperReferences(constraintWidget));
                }
            }
        }
        if (hashSet != null) {
            arrayList.addAll(hashSet);
        }
        int size2 = arrayList.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((WidgetRun) arrayList.get(i2)).clear();
        }
        int size3 = arrayList.size();
        for (int i3 = 0; i3 < size3; i3++) {
            WidgetRun widgetRun = (WidgetRun) arrayList.get(i3);
            if (widgetRun.mWidget != this.mContainer) {
                widgetRun.apply();
            }
        }
        this.mGroups.clear();
        RunGroup.index = 0;
        findGroup(this.mWidgetcontainer.mHorizontalRun, 0, this.mGroups);
        findGroup(this.mWidgetcontainer.mVerticalRun, 1, this.mGroups);
        this.mNeedBuildGraph = false;
    }

    public final int computeWrap(ConstraintWidgetContainer constraintWidgetContainer, int i) {
        DependencyNode dependencyNode;
        DependencyNode dependencyNode2;
        long wrapDimension;
        float f;
        long j;
        DependencyGraph dependencyGraph = this;
        ConstraintWidgetContainer constraintWidgetContainer2 = constraintWidgetContainer;
        int size = dependencyGraph.mGroups.size();
        int i2 = 0;
        long j2 = 0;
        long j3 = 0;
        while (i2 < size) {
            RunGroup runGroup = (RunGroup) dependencyGraph.mGroups.get(i2);
            Object obj = runGroup.RunGroup$ar$mFirstRun;
            if (!(obj instanceof ChainRun) ? !(i != 0 ? (obj instanceof VerticalWidgetRun) : (obj instanceof HorizontalWidgetRun)) : ((ChainRun) obj).orientation != i) {
                wrapDimension = j2;
            } else {
                if (i == 0) {
                    dependencyNode = constraintWidgetContainer2.mHorizontalRun.start;
                } else {
                    dependencyNode = constraintWidgetContainer2.mVerticalRun.start;
                }
                if (i == 0) {
                    dependencyNode2 = constraintWidgetContainer2.mHorizontalRun.end;
                } else {
                    dependencyNode2 = constraintWidgetContainer2.mVerticalRun.end;
                }
                boolean contains = ((WidgetRun) obj).start.mTargets.contains(dependencyNode);
                boolean contains2 = ((WidgetRun) runGroup.RunGroup$ar$mFirstRun).end.mTargets.contains(dependencyNode2);
                long wrapDimension2 = ((WidgetRun) runGroup.RunGroup$ar$mFirstRun).getWrapDimension();
                if (contains && contains2) {
                    long traverseStart = runGroup.traverseStart(((WidgetRun) runGroup.RunGroup$ar$mFirstRun).start, j2);
                    long traverseEnd = runGroup.traverseEnd(((WidgetRun) runGroup.RunGroup$ar$mFirstRun).end, j2);
                    long j4 = traverseStart - wrapDimension2;
                    WidgetRun widgetRun = (WidgetRun) runGroup.RunGroup$ar$mFirstRun;
                    long j5 = widgetRun.end.mMargin;
                    if (j4 >= (-r5)) {
                        j4 += j5;
                    }
                    long j6 = (-traverseEnd) - wrapDimension2;
                    long j7 = widgetRun.start.mMargin;
                    long j8 = j6 - j7;
                    if (j8 >= j7) {
                        j8 -= j7;
                    }
                    ConstraintWidget constraintWidget = widgetRun.mWidget;
                    if (i == 0) {
                        f = constraintWidget.mHorizontalBiasPercent;
                    } else {
                        f = constraintWidget.mVerticalBiasPercent;
                    }
                    float f2 = 1.0f - f;
                    if (f > 0.0f) {
                        j = (((float) j8) / f) + (((float) j4) / f2);
                    } else {
                        j = 0;
                    }
                    float f3 = (float) j;
                    wrapDimension = (j7 + ((((f * f3) + 0.5f) + wrapDimension2) + ((f3 * f2) + 0.5f))) - j5;
                } else if (contains) {
                    wrapDimension = Math.max(runGroup.traverseStart(((WidgetRun) runGroup.RunGroup$ar$mFirstRun).start, r0.mMargin), ((WidgetRun) runGroup.RunGroup$ar$mFirstRun).start.mMargin + wrapDimension2);
                } else if (contains2) {
                    wrapDimension = Math.max(-runGroup.traverseEnd(((WidgetRun) runGroup.RunGroup$ar$mFirstRun).end, r0.mMargin), (-((WidgetRun) runGroup.RunGroup$ar$mFirstRun).end.mMargin) + wrapDimension2);
                } else {
                    wrapDimension = (r0.start.mMargin + ((WidgetRun) runGroup.RunGroup$ar$mFirstRun).getWrapDimension()) - ((WidgetRun) runGroup.RunGroup$ar$mFirstRun).end.mMargin;
                }
            }
            j3 = Math.max(j3, wrapDimension);
            i2++;
            dependencyGraph = this;
            constraintWidgetContainer2 = constraintWidgetContainer;
            j2 = 0;
        }
        return (int) j3;
    }

    public final void measureWidgets() {
        boolean z;
        boolean z2;
        DimensionDependency dimensionDependency;
        ArrayList arrayList = this.mWidgetcontainer.mChildren;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ConstraintWidget constraintWidget = (ConstraintWidget) arrayList.get(i);
            if (!constraintWidget.measured) {
                int[] iArr = constraintWidget.mListDimensionBehaviors$ar$edu;
                int i2 = iArr[0];
                int i3 = iArr[1];
                int i4 = constraintWidget.mMatchConstraintDefaultWidth;
                int i5 = constraintWidget.mMatchConstraintDefaultHeight;
                if (i2 != 2 && (i2 != 3 || i4 != 1)) {
                    z = false;
                } else {
                    z = true;
                }
                if (i3 != 2 && (i3 != 3 || i5 != 1)) {
                    z2 = false;
                } else {
                    z2 = true;
                }
                DimensionDependency dimensionDependency2 = constraintWidget.mHorizontalRun.mDimension;
                boolean z3 = dimensionDependency2.resolved;
                DimensionDependency dimensionDependency3 = constraintWidget.mVerticalRun.mDimension;
                boolean z4 = dimensionDependency3.resolved;
                if (z3 && z4) {
                    measure$ar$edu(constraintWidget, 1, dimensionDependency2.value, 1, dimensionDependency3.value);
                    constraintWidget.measured = true;
                } else if (z3 && z2) {
                    measure$ar$edu(constraintWidget, 1, dimensionDependency2.value, 2, dimensionDependency3.value);
                    if (i3 == 3) {
                        constraintWidget.mVerticalRun.mDimension.wrapValue = constraintWidget.getHeight();
                    } else {
                        constraintWidget.mVerticalRun.mDimension.resolve(constraintWidget.getHeight());
                        constraintWidget.measured = true;
                    }
                } else if (z4 && z) {
                    measure$ar$edu(constraintWidget, 2, dimensionDependency2.value, 1, dimensionDependency3.value);
                    if (i2 == 3) {
                        constraintWidget.mHorizontalRun.mDimension.wrapValue = constraintWidget.getWidth();
                    } else {
                        constraintWidget.mHorizontalRun.mDimension.resolve(constraintWidget.getWidth());
                        constraintWidget.measured = true;
                    }
                }
                if (constraintWidget.measured && (dimensionDependency = constraintWidget.mVerticalRun.mBaselineDimension) != null) {
                    dimensionDependency.resolve(constraintWidget.mBaselineDistance);
                }
            }
        }
    }
}
