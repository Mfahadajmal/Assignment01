package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.SolverVariable;
import androidx.constraintlayout.core.widgets.analyzer.BasicMeasure$Measure;
import androidx.constraintlayout.core.widgets.analyzer.ChainRun;
import androidx.constraintlayout.core.widgets.analyzer.DependencyGraph;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.grpc.okhttp.internal.OptionalMethod;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ConstraintWidgetContainer extends WidgetContainer {
    int mPaddingLeft;
    int mPaddingTop;
    public int mPass;
    public final OptionalMethod mBasicMeasureSolver$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new OptionalMethod(this);
    public final DependencyGraph mDependencyGraph = new DependencyGraph(this);
    public ConstraintLayout.Measurer mMeasurer$ar$class_merging = null;
    public boolean mIsRtl = false;
    public final LinearSystem mSystem = new LinearSystem();
    public int mHorizontalChainsSize = 0;
    public int mVerticalChainsSize = 0;
    public ChainHead[] mVerticalChainsArray = new ChainHead[4];
    public ChainHead[] mHorizontalChainsArray = new ChainHead[4];
    public int mOptimizationLevel = 257;
    public boolean mWidthMeasuredTooSmall = false;
    public boolean mHeightMeasuredTooSmall = false;
    public WeakReference mVerticalWrapMin = null;
    public WeakReference mHorizontalWrapMin = null;
    public WeakReference mVerticalWrapMax = null;
    public WeakReference mHorizontalWrapMax = null;
    final HashSet mWidgetsToAdd = new HashSet();
    public final BasicMeasure$Measure mMeasure = new BasicMeasure$Measure();

    private final void addMaxWrap(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        this.mSystem.addGreaterThan(solverVariable, this.mSystem.createObjectVariable(constraintAnchor), 0, 5);
    }

    private final void addMinWrap(ConstraintAnchor constraintAnchor, SolverVariable solverVariable) {
        LinearSystem linearSystem = this.mSystem;
        linearSystem.addGreaterThan(linearSystem.createObjectVariable(constraintAnchor), solverVariable, 0, 5);
    }

    public static void measure$ar$class_merging$ar$ds(ConstraintWidget constraintWidget, ConstraintLayout.Measurer measurer, BasicMeasure$Measure basicMeasure$Measure) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int i;
        int i2;
        if (measurer == null) {
            return;
        }
        if (constraintWidget.mVisibility != 8 && !(constraintWidget instanceof Guideline) && !(constraintWidget instanceof Barrier)) {
            basicMeasure$Measure.horizontalBehavior$ar$edu = constraintWidget.getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0();
            basicMeasure$Measure.verticalBehavior$ar$edu = constraintWidget.getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0();
            basicMeasure$Measure.horizontalDimension = constraintWidget.getWidth();
            basicMeasure$Measure.verticalDimension = constraintWidget.getHeight();
            basicMeasure$Measure.measuredNeedsSolverPass = false;
            basicMeasure$Measure.measureStrategy = 0;
            if (basicMeasure$Measure.horizontalBehavior$ar$edu == 3) {
                z = true;
            } else {
                z = false;
            }
            if (basicMeasure$Measure.verticalBehavior$ar$edu == 3) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (z && constraintWidget.mDimensionRatio > 0.0f) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (z2 && constraintWidget.mDimensionRatio > 0.0f) {
                z4 = true;
            } else {
                z4 = false;
            }
            if (z && constraintWidget.hasDanglingDimension(0) && constraintWidget.mMatchConstraintDefaultWidth == 0 && !z3) {
                basicMeasure$Measure.horizontalBehavior$ar$edu = 2;
                if (z2 && constraintWidget.mMatchConstraintDefaultHeight == 0) {
                    basicMeasure$Measure.horizontalBehavior$ar$edu = 1;
                }
                z = false;
            }
            if (z2 && constraintWidget.hasDanglingDimension(1) && constraintWidget.mMatchConstraintDefaultHeight == 0 && !z4) {
                basicMeasure$Measure.verticalBehavior$ar$edu = 2;
                if (z && constraintWidget.mMatchConstraintDefaultWidth == 0) {
                    basicMeasure$Measure.verticalBehavior$ar$edu = 1;
                }
                z2 = false;
            }
            if (constraintWidget.isResolvedHorizontally()) {
                basicMeasure$Measure.horizontalBehavior$ar$edu = 1;
                z = false;
            }
            if (constraintWidget.isResolvedVertically()) {
                basicMeasure$Measure.verticalBehavior$ar$edu = 1;
                z2 = false;
            }
            if (z3) {
                if (constraintWidget.mResolvedMatchConstraintDefault[0] == 4) {
                    basicMeasure$Measure.horizontalBehavior$ar$edu = 1;
                } else if (!z2) {
                    if (basicMeasure$Measure.verticalBehavior$ar$edu == 1) {
                        i2 = basicMeasure$Measure.verticalDimension;
                    } else {
                        basicMeasure$Measure.horizontalBehavior$ar$edu = 2;
                        measurer.measure(constraintWidget, basicMeasure$Measure);
                        i2 = basicMeasure$Measure.measuredHeight;
                    }
                    basicMeasure$Measure.horizontalBehavior$ar$edu = 1;
                    basicMeasure$Measure.horizontalDimension = (int) (constraintWidget.mDimensionRatio * i2);
                }
            }
            if (z4) {
                if (constraintWidget.mResolvedMatchConstraintDefault[1] == 4) {
                    basicMeasure$Measure.verticalBehavior$ar$edu = 1;
                } else if (!z) {
                    if (basicMeasure$Measure.horizontalBehavior$ar$edu == 1) {
                        i = basicMeasure$Measure.horizontalDimension;
                    } else {
                        basicMeasure$Measure.verticalBehavior$ar$edu = 2;
                        measurer.measure(constraintWidget, basicMeasure$Measure);
                        i = basicMeasure$Measure.measuredWidth;
                    }
                    basicMeasure$Measure.verticalBehavior$ar$edu = 1;
                    float f = i;
                    if (constraintWidget.mDimensionRatioSide == -1) {
                        basicMeasure$Measure.verticalDimension = (int) (f / constraintWidget.mDimensionRatio);
                    } else {
                        basicMeasure$Measure.verticalDimension = (int) (constraintWidget.mDimensionRatio * f);
                    }
                }
            }
            measurer.measure(constraintWidget, basicMeasure$Measure);
            constraintWidget.setWidth(basicMeasure$Measure.measuredWidth);
            constraintWidget.setHeight(basicMeasure$Measure.measuredHeight);
            constraintWidget.mHasBaseline = basicMeasure$Measure.measuredHasBaseline;
            constraintWidget.setBaselineDistance(basicMeasure$Measure.measuredBaseline);
            basicMeasure$Measure.measureStrategy = 0;
            boolean z5 = basicMeasure$Measure.measuredNeedsSolverPass;
            return;
        }
        basicMeasure$Measure.measuredWidth = 0;
        basicMeasure$Measure.measuredHeight = 0;
    }

    private final void resetChains() {
        this.mHorizontalChainsSize = 0;
        this.mVerticalChainsSize = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addChain(ConstraintWidget constraintWidget, int i) {
        if (i == 0) {
            int i2 = this.mHorizontalChainsSize + 1;
            ChainHead[] chainHeadArr = this.mHorizontalChainsArray;
            int length = chainHeadArr.length;
            if (i2 >= length) {
                this.mHorizontalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr, length + length);
            }
            ChainHead[] chainHeadArr2 = this.mHorizontalChainsArray;
            int i3 = this.mHorizontalChainsSize;
            chainHeadArr2[i3] = new ChainHead(constraintWidget, 0, this.mIsRtl);
            this.mHorizontalChainsSize = i3 + 1;
            return;
        }
        int i4 = this.mVerticalChainsSize + 1;
        ChainHead[] chainHeadArr3 = this.mVerticalChainsArray;
        int length2 = chainHeadArr3.length;
        if (i4 >= length2) {
            this.mVerticalChainsArray = (ChainHead[]) Arrays.copyOf(chainHeadArr3, length2 + length2);
        }
        ChainHead[] chainHeadArr4 = this.mVerticalChainsArray;
        int i5 = this.mVerticalChainsSize;
        chainHeadArr4[i5] = new ChainHead(constraintWidget, 1, this.mIsRtl);
        this.mVerticalChainsSize = i5 + 1;
    }

    public final boolean directMeasureWithOrientation(boolean z, int i) {
        boolean z2;
        boolean z3;
        DependencyGraph dependencyGraph = this.mDependencyGraph;
        boolean z4 = false;
        int dimensionBehaviour$ar$edu = dependencyGraph.mWidgetcontainer.getDimensionBehaviour$ar$edu(0);
        int dimensionBehaviour$ar$edu2 = dependencyGraph.mWidgetcontainer.getDimensionBehaviour$ar$edu(1);
        int x = dependencyGraph.mWidgetcontainer.getX();
        int y = dependencyGraph.mWidgetcontainer.getY();
        if (z && (dimensionBehaviour$ar$edu == 2 || dimensionBehaviour$ar$edu2 == 2)) {
            ArrayList arrayList = dependencyGraph.mRuns;
            int size = arrayList.size();
            int i2 = 0;
            while (true) {
                if (i2 < size) {
                    WidgetRun widgetRun = (WidgetRun) arrayList.get(i2);
                    if (widgetRun.orientation == i && !widgetRun.supportsWrapComputation()) {
                        z3 = false;
                        break;
                    }
                    i2++;
                } else {
                    z3 = true;
                    break;
                }
            }
            if (i == 0) {
                if (z3 && dimensionBehaviour$ar$edu == 2) {
                    dependencyGraph.mWidgetcontainer.setHorizontalDimensionBehaviour$ar$edu$f268a847_0(1);
                    ConstraintWidgetContainer constraintWidgetContainer = dependencyGraph.mWidgetcontainer;
                    constraintWidgetContainer.setWidth(dependencyGraph.computeWrap(constraintWidgetContainer, 0));
                    ConstraintWidgetContainer constraintWidgetContainer2 = dependencyGraph.mWidgetcontainer;
                    constraintWidgetContainer2.mHorizontalRun.mDimension.resolve(constraintWidgetContainer2.getWidth());
                }
            } else if (z3 && dimensionBehaviour$ar$edu2 == 2) {
                dependencyGraph.mWidgetcontainer.setVerticalDimensionBehaviour$ar$edu$f268a847_0(1);
                ConstraintWidgetContainer constraintWidgetContainer3 = dependencyGraph.mWidgetcontainer;
                constraintWidgetContainer3.setHeight(dependencyGraph.computeWrap(constraintWidgetContainer3, 1));
                ConstraintWidgetContainer constraintWidgetContainer4 = dependencyGraph.mWidgetcontainer;
                constraintWidgetContainer4.mVerticalRun.mDimension.resolve(constraintWidgetContainer4.getHeight());
            }
        }
        if (i == 0) {
            if (dependencyGraph.mWidgetcontainer.mListDimensionBehaviors$ar$edu[0] == 1 || dependencyGraph.mWidgetcontainer.mListDimensionBehaviors$ar$edu[0] == 4) {
                int width = dependencyGraph.mWidgetcontainer.getWidth() + x;
                dependencyGraph.mWidgetcontainer.mHorizontalRun.end.resolve(width);
                dependencyGraph.mWidgetcontainer.mHorizontalRun.mDimension.resolve(width - x);
                z2 = true;
            }
            z2 = false;
        } else {
            if (dependencyGraph.mWidgetcontainer.mListDimensionBehaviors$ar$edu[1] == 1 || dependencyGraph.mWidgetcontainer.mListDimensionBehaviors$ar$edu[1] == 4) {
                int height = dependencyGraph.mWidgetcontainer.getHeight() + y;
                dependencyGraph.mWidgetcontainer.mVerticalRun.end.resolve(height);
                dependencyGraph.mWidgetcontainer.mVerticalRun.mDimension.resolve(height - y);
                z2 = true;
            }
            z2 = false;
        }
        dependencyGraph.measureWidgets();
        ArrayList arrayList2 = dependencyGraph.mRuns;
        int size2 = arrayList2.size();
        for (int i3 = 0; i3 < size2; i3++) {
            WidgetRun widgetRun2 = (WidgetRun) arrayList2.get(i3);
            if (widgetRun2.orientation == i && (widgetRun2.mWidget != dependencyGraph.mWidgetcontainer || widgetRun2.mResolved)) {
                widgetRun2.applyToWidget();
            }
        }
        ArrayList arrayList3 = dependencyGraph.mRuns;
        int size3 = arrayList3.size();
        int i4 = 0;
        while (true) {
            if (i4 < size3) {
                WidgetRun widgetRun3 = (WidgetRun) arrayList3.get(i4);
                if (widgetRun3.orientation == i && ((z2 || widgetRun3.mWidget != dependencyGraph.mWidgetcontainer) && (!widgetRun3.start.resolved || !widgetRun3.end.resolved || (!(widgetRun3 instanceof ChainRun) && !widgetRun3.mDimension.resolved)))) {
                    break;
                }
                i4++;
            } else {
                z4 = true;
                break;
            }
        }
        dependencyGraph.mWidgetcontainer.setHorizontalDimensionBehaviour$ar$edu$f268a847_0(dimensionBehaviour$ar$edu);
        dependencyGraph.mWidgetcontainer.setVerticalDimensionBehaviour$ar$edu$f268a847_0(dimensionBehaviour$ar$edu2);
        return z4;
    }

    public final void invalidateGraph() {
        this.mDependencyGraph.mNeedBuildGraph = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:184:0x05fe  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0615 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0623  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0635  */
    /* JADX WARN: Removed duplicated region for block: B:207:0x064f  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0935  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x0958 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0966 A[LOOP:14: B:244:0x0964->B:245:0x0966, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:258:0x09cb  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x09ec  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x09fc  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x0a3c  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0a3e  */
    /* JADX WARN: Removed duplicated region for block: B:283:0x0a34  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x09f6  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x09d9  */
    /* JADX WARN: Removed duplicated region for block: B:476:0x0a4d  */
    /* JADX WARN: Removed duplicated region for block: B:685:0x057b  */
    /* JADX WARN: Removed duplicated region for block: B:702:0x05ab A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:705:0x05b2  */
    /* JADX WARN: Removed duplicated region for block: B:712:0x05d0  */
    /* JADX WARN: Removed duplicated region for block: B:719:0x05e6  */
    /* JADX WARN: Removed duplicated region for block: B:721:0x05c8  */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6, types: [boolean] */
    /* JADX WARN: Type inference failed for: r4v7 */
    @Override // androidx.constraintlayout.core.widgets.WidgetContainer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void layout() {
        /*
            Method dump skipped, instructions count: 2653
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.layout():void");
    }

    /* JADX WARN: Removed duplicated region for block: B:110:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x042f  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0434  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x05c4  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0097  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void measure$ar$ds(int r20, int r21, int r22, int r23, int r24, int r25, int r26) {
        /*
            Method dump skipped, instructions count: 1482
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidgetContainer.measure$ar$ds(int, int, int, int, int, int, int):void");
    }

    public final boolean optimizeFor(int i) {
        if ((this.mOptimizationLevel & i) == i) {
            return true;
        }
        return false;
    }

    @Override // androidx.constraintlayout.core.widgets.WidgetContainer, androidx.constraintlayout.core.widgets.ConstraintWidget
    public final void reset() {
        this.mSystem.reset();
        this.mPaddingLeft = 0;
        this.mPaddingTop = 0;
        super.reset();
    }

    public final void setOptimizationLevel(int i) {
        this.mOptimizationLevel = i;
        LinearSystem.USE_DEPENDENCY_ORDERING = optimizeFor(512);
    }

    @Override // androidx.constraintlayout.core.widgets.ConstraintWidget
    public final void updateFromRuns(boolean z, boolean z2) {
        super.updateFromRuns(z, z2);
        int size = this.mChildren.size();
        for (int i = 0; i < size; i++) {
            ((ConstraintWidget) this.mChildren.get(i)).updateFromRuns(z, z2);
        }
    }
}
