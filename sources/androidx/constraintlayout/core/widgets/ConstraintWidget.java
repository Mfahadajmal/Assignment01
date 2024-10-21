package androidx.constraintlayout.core.widgets;

import androidx.constraintlayout.core.LinearSystem;
import androidx.constraintlayout.core.widgets.analyzer.ChainRun;
import androidx.constraintlayout.core.widgets.analyzer.DependencyNode;
import androidx.constraintlayout.core.widgets.analyzer.HorizontalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.VerticalWidgetRun;
import androidx.constraintlayout.core.widgets.analyzer.WidgetRun;
import androidx.preference.Preference;
import com.google.common.util.concurrent.ExecutionList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ConstraintWidget {
    public static final float DEFAULT_BIAS = 0.5f;
    public ChainRun horizontalChainRun;
    public int horizontalGroup;
    protected final ArrayList mAnchors;
    public final ConstraintAnchor mBaseline;
    public int mBaselineDistance;
    public final ConstraintAnchor mBottom;
    public final ConstraintAnchor mCenter;
    final ConstraintAnchor mCenterX;
    final ConstraintAnchor mCenterY;
    public float mCircleConstraintAngle;
    public Object mCompanionWidget;
    public String mDebugName;
    public float mDimensionRatio;
    public int mDimensionRatioSide;
    public boolean mHasBaseline;
    public int mHeight;
    public float mHorizontalBiasPercent;
    public int mHorizontalChainStyle;
    public int mHorizontalResolution;
    public boolean mHorizontalSolvingPass;
    public boolean mInVirtualLayout;
    public final boolean[] mIsInBarrier;
    public int mLastHorizontalMeasureSpec;
    public int mLastVerticalMeasureSpec;
    public final ConstraintAnchor mLeft;
    public final ConstraintAnchor[] mListAnchors;
    public final int[] mListDimensionBehaviors$ar$edu;
    public final ConstraintWidget[] mListNextMatchConstraintsWidget;
    public int mMatchConstraintDefaultHeight;
    public int mMatchConstraintDefaultWidth;
    public int mMatchConstraintMaxHeight;
    public int mMatchConstraintMaxWidth;
    public int mMatchConstraintMinHeight;
    public int mMatchConstraintMinWidth;
    public float mMatchConstraintPercentHeight;
    public float mMatchConstraintPercentWidth;
    public final int[] mMaxDimension;
    public int mMinHeight;
    public int mMinWidth;
    public final ConstraintWidget[] mNextChainWidget;
    public ConstraintWidget mParent;
    float mResolvedDimensionRatio;
    int mResolvedDimensionRatioSide;
    private boolean mResolvedHorizontal;
    public final int[] mResolvedMatchConstraintDefault;
    public boolean mResolvedVertical;
    public final ConstraintAnchor mRight;
    public final ConstraintAnchor mTop;
    public float mVerticalBiasPercent;
    public int mVerticalChainStyle;
    public int mVerticalResolution;
    public boolean mVerticalSolvingPass;
    public int mVisibility;
    public final float[] mWeight;
    int mWidth;
    public int mWrapBehaviorInParent;
    public int mX;
    public int mY;
    public ChainRun verticalChainRun;
    public int verticalGroup;
    public boolean measured = false;
    public HorizontalWidgetRun mHorizontalRun = null;
    public VerticalWidgetRun mVerticalRun = null;
    public final boolean[] isTerminalWidget = {true, true};
    private boolean mMeasureRequested = true;
    private final boolean mOptimizeWrapOnResolved = true;
    public int mWidthOverride = -1;
    public int mHeightOverride = -1;

    public ConstraintWidget() {
        new HashMap();
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        this.mWrapBehaviorInParent = 0;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mResolvedMatchConstraintDefault = new int[2];
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMaxWidth = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintMinHeight = 0;
        this.mMatchConstraintMaxHeight = 0;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        this.mMaxDimension = new int[]{Preference.DEFAULT_ORDER, Preference.DEFAULT_ORDER};
        this.mCircleConstraintAngle = Float.NaN;
        this.mHasBaseline = false;
        this.mInVirtualLayout = false;
        this.mLastHorizontalMeasureSpec = 0;
        this.mLastVerticalMeasureSpec = 0;
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
        ConstraintAnchor constraintAnchor8 = new ConstraintAnchor(this, 7);
        this.mCenter = constraintAnchor8;
        this.mListAnchors = new ConstraintAnchor[]{constraintAnchor, constraintAnchor3, constraintAnchor2, constraintAnchor4, constraintAnchor5, constraintAnchor8};
        ArrayList arrayList = new ArrayList();
        this.mAnchors = arrayList;
        this.mIsInBarrier = new boolean[2];
        this.mListDimensionBehaviors$ar$edu = new int[]{1, 1};
        this.mParent = null;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mBaselineDistance = 0;
        this.mHorizontalBiasPercent = 0.5f;
        this.mVerticalBiasPercent = 0.5f;
        this.mVisibility = 0;
        this.mDebugName = null;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        this.mWeight = new float[]{-1.0f, -1.0f};
        this.mListNextMatchConstraintsWidget = new ConstraintWidget[]{null, null};
        this.mNextChainWidget = new ConstraintWidget[]{null, null};
        this.horizontalGroup = -1;
        this.verticalGroup = -1;
        arrayList.add(constraintAnchor);
        arrayList.add(constraintAnchor2);
        arrayList.add(constraintAnchor3);
        arrayList.add(constraintAnchor4);
        arrayList.add(constraintAnchor6);
        arrayList.add(constraintAnchor7);
        arrayList.add(constraintAnchor8);
        arrayList.add(constraintAnchor5);
    }

    /* JADX WARN: Removed duplicated region for block: B:139:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x04b5  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x04d6  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x04dd  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x04c7  */
    /* JADX WARN: Removed duplicated region for block: B:293:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:347:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void applyConstraints$ar$edu(androidx.constraintlayout.core.LinearSystem r28, boolean r29, boolean r30, boolean r31, boolean r32, androidx.constraintlayout.core.SolverVariable r33, androidx.constraintlayout.core.SolverVariable r34, int r35, boolean r36, androidx.constraintlayout.core.widgets.ConstraintAnchor r37, androidx.constraintlayout.core.widgets.ConstraintAnchor r38, int r39, int r40, int r41, int r42, float r43, boolean r44, boolean r45, boolean r46, boolean r47, boolean r48, int r49, int r50, int r51, int r52, float r53, boolean r54) {
        /*
            Method dump skipped, instructions count: 1361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.applyConstraints$ar$edu(androidx.constraintlayout.core.LinearSystem, boolean, boolean, boolean, boolean, androidx.constraintlayout.core.SolverVariable, androidx.constraintlayout.core.SolverVariable, int, boolean, androidx.constraintlayout.core.widgets.ConstraintAnchor, androidx.constraintlayout.core.widgets.ConstraintAnchor, int, int, int, int, float, boolean, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    private final boolean isChainHead(int i) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        ConstraintAnchor[] constraintAnchorArr = this.mListAnchors;
        int i2 = i + i;
        ConstraintAnchor constraintAnchor3 = constraintAnchorArr[i2];
        ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
        if (constraintAnchor4 != null && constraintAnchor4.mTarget != constraintAnchor3 && (constraintAnchor2 = (constraintAnchor = constraintAnchorArr[i2 + 1]).mTarget) != null && constraintAnchor2.mTarget == constraintAnchor) {
            return true;
        }
        return false;
    }

    public final void addChildrenToSolverByDependency(ConstraintWidgetContainer constraintWidgetContainer, LinearSystem linearSystem, HashSet hashSet, int i, boolean z) {
        if (z) {
            if (hashSet.contains(this)) {
                Optimizer.checkMatchParent(constraintWidgetContainer, linearSystem, this);
                hashSet.remove(this);
                addToSolver(linearSystem, constraintWidgetContainer.optimizeFor(64));
            } else {
                return;
            }
        }
        if (i == 0) {
            HashSet hashSet2 = this.mLeft.mDependents;
            if (hashSet2 != null) {
                Iterator it = hashSet2.iterator();
                while (it.hasNext()) {
                    ((ConstraintAnchor) it.next()).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, 0, true);
                }
            }
            HashSet hashSet3 = this.mRight.mDependents;
            if (hashSet3 != null) {
                Iterator it2 = hashSet3.iterator();
                while (it2.hasNext()) {
                    ((ConstraintAnchor) it2.next()).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, 0, true);
                }
                return;
            }
            return;
        }
        HashSet hashSet4 = this.mTop.mDependents;
        if (hashSet4 != null) {
            Iterator it3 = hashSet4.iterator();
            while (it3.hasNext()) {
                ((ConstraintAnchor) it3.next()).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, 1, true);
            }
        }
        HashSet hashSet5 = this.mBottom.mDependents;
        if (hashSet5 != null) {
            Iterator it4 = hashSet5.iterator();
            while (it4.hasNext()) {
                ((ConstraintAnchor) it4.next()).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, 1, true);
            }
        }
        HashSet hashSet6 = this.mBaseline.mDependents;
        if (hashSet6 != null) {
            Iterator it5 = hashSet6.iterator();
            while (it5.hasNext()) {
                ((ConstraintAnchor) it5.next()).mOwner.addChildrenToSolverByDependency(constraintWidgetContainer, linearSystem, hashSet, 1, true);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean addFirst() {
        if (!(this instanceof VirtualLayout) && !(this instanceof Guideline)) {
            return false;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:169:0x040b, code lost:
    
        if (r6 == (-1)) goto L263;
     */
    /* JADX WARN: Code restructure failed: missing block: B:306:0x02dd, code lost:
    
        if (r6 == 0) goto L187;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0083, code lost:
    
        if (r50.mResolvedVertical != false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:361:0x015e, code lost:
    
        if (r50.mResolvedVertical != false) goto L84;
     */
    /* JADX WARN: Removed duplicated region for block: B:101:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0408  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0417 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0428  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0057  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x044e  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0477  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x06b0  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x06dc  */
    /* JADX WARN: Removed duplicated region for block: B:230:0x05cd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:239:0x05e2  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x05e8  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x05f6  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x065e  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x0661  */
    /* JADX WARN: Removed duplicated region for block: B:258:0x060c  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x05f0  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x05b2  */
    /* JADX WARN: Removed duplicated region for block: B:276:0x04b3  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x04c1  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x04eb  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:286:0x04c9  */
    /* JADX WARN: Removed duplicated region for block: B:287:0x04bb  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x03f1  */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:338:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:360:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:362:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0276  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x027e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void addToSolver(androidx.constraintlayout.core.LinearSystem r51, boolean r52) {
        /*
            Method dump skipped, instructions count: 1903
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.core.widgets.ConstraintWidget.addToSolver(androidx.constraintlayout.core.LinearSystem, boolean):void");
    }

    public boolean allowedInBarrier() {
        if (this.mVisibility != 8) {
            return true;
        }
        return false;
    }

    public final void createObjectVariables(LinearSystem linearSystem) {
        linearSystem.createObjectVariable(this.mLeft);
        linearSystem.createObjectVariable(this.mTop);
        linearSystem.createObjectVariable(this.mRight);
        linearSystem.createObjectVariable(this.mBottom);
        if (this.mBaselineDistance > 0) {
            linearSystem.createObjectVariable(this.mBaseline);
        }
    }

    public final void ensureWidgetRuns() {
        if (this.mHorizontalRun == null) {
            this.mHorizontalRun = new HorizontalWidgetRun(this);
        }
        if (this.mVerticalRun == null) {
            this.mVerticalRun = new VerticalWidgetRun(this);
        }
    }

    public ConstraintAnchor getAnchor$ar$edu$6d5b24e9_0(int i) {
        int i2 = i - 1;
        if (i2 != 1) {
            if (i2 != 2) {
                if (i2 != 3) {
                    if (i2 != 4) {
                        if (i2 != 5) {
                            return this.mCenter;
                        }
                        return this.mBaseline;
                    }
                    return this.mBottom;
                }
                return this.mRight;
            }
            return this.mTop;
        }
        return this.mLeft;
    }

    public final int getBottom() {
        return getY() + this.mHeight;
    }

    public final int getDimensionBehaviour$ar$edu(int i) {
        if (i == 0) {
            return getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0();
        }
        return getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0();
    }

    public final int getHeight() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mHeight;
    }

    public final int getHorizontalDimensionBehaviour$ar$edu$cfe88bd7_0() {
        return this.mListDimensionBehaviors$ar$edu[0];
    }

    public final ConstraintWidget getNextChainMember(int i) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i == 0) {
            ConstraintAnchor constraintAnchor3 = this.mRight;
            ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
            if (constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3) {
                return constraintAnchor4.mOwner;
            }
            return null;
        }
        if (i == 1 && (constraintAnchor2 = (constraintAnchor = this.mBottom).mTarget) != null && constraintAnchor2.mTarget == constraintAnchor) {
            return constraintAnchor2.mOwner;
        }
        return null;
    }

    public final ConstraintWidget getPreviousChainMember(int i) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i == 0) {
            ConstraintAnchor constraintAnchor3 = this.mLeft;
            ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
            if (constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3) {
                return constraintAnchor4.mOwner;
            }
            return null;
        }
        if (i == 1 && (constraintAnchor2 = (constraintAnchor = this.mTop).mTarget) != null && constraintAnchor2.mTarget == constraintAnchor) {
            return constraintAnchor2.mOwner;
        }
        return null;
    }

    public final int getRight() {
        return getX() + this.mWidth;
    }

    public final WidgetRun getRun(int i) {
        if (i == 0) {
            return this.mHorizontalRun;
        }
        if (i == 1) {
            return this.mVerticalRun;
        }
        return null;
    }

    public final int getVerticalDimensionBehaviour$ar$edu$cfe88bd7_0() {
        return this.mListDimensionBehaviors$ar$edu[1];
    }

    public final int getWidth() {
        if (this.mVisibility == 8) {
            return 0;
        }
        return this.mWidth;
    }

    public final int getX() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null) {
            return ((ConstraintWidgetContainer) constraintWidget).mPaddingLeft + this.mX;
        }
        return this.mX;
    }

    public final int getY() {
        ConstraintWidget constraintWidget = this.mParent;
        if (constraintWidget != null) {
            return ((ConstraintWidgetContainer) constraintWidget).mPaddingTop + this.mY;
        }
        return this.mY;
    }

    public final boolean hasDanglingDimension(int i) {
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (i == 0) {
            if (this.mLeft.mTarget != null) {
                i5 = 1;
            } else {
                i5 = 0;
            }
            if (this.mRight.mTarget != null) {
                i6 = 1;
            } else {
                i6 = 0;
            }
            if (i5 + i6 < 2) {
                return true;
            }
            return false;
        }
        if (this.mTop.mTarget != null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (this.mBottom.mTarget != null) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (this.mBaseline.mTarget != null) {
            i4 = 1;
        } else {
            i4 = 0;
        }
        if (i2 + i3 + i4 < 2) {
            return true;
        }
        return false;
    }

    public final boolean hasResolvedTargets(int i, int i2) {
        ConstraintAnchor constraintAnchor;
        ConstraintAnchor constraintAnchor2;
        if (i == 0) {
            ConstraintAnchor constraintAnchor3 = this.mLeft.mTarget;
            if (constraintAnchor3 != null && constraintAnchor3.mHasFinalValue && (constraintAnchor2 = this.mRight.mTarget) != null && constraintAnchor2.mHasFinalValue && (constraintAnchor2.getFinalValue() - this.mRight.getMargin()) - (this.mLeft.mTarget.getFinalValue() + this.mLeft.getMargin()) >= i2) {
                return true;
            }
            return false;
        }
        ConstraintAnchor constraintAnchor4 = this.mTop.mTarget;
        if (constraintAnchor4 != null && constraintAnchor4.mHasFinalValue && (constraintAnchor = this.mBottom.mTarget) != null && constraintAnchor.mHasFinalValue && (constraintAnchor.getFinalValue() - this.mBottom.getMargin()) - (this.mTop.mTarget.getFinalValue() + this.mTop.getMargin()) >= i2) {
            return true;
        }
        return false;
    }

    public final void immediateConnect$ar$edu$9ac28667_0(int i, ConstraintWidget constraintWidget, int i2, int i3, int i4) {
        getAnchor$ar$edu$6d5b24e9_0(i).connect$ar$ds(constraintWidget.getAnchor$ar$edu$6d5b24e9_0(i2), i3, i4, true);
    }

    public final boolean isInHorizontalChain() {
        ConstraintAnchor constraintAnchor = this.mLeft;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null || constraintAnchor2.mTarget != constraintAnchor) {
            ConstraintAnchor constraintAnchor3 = this.mRight;
            ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
            if (constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3) {
                return true;
            }
            return false;
        }
        return true;
    }

    public final boolean isInVerticalChain() {
        ConstraintAnchor constraintAnchor = this.mTop;
        ConstraintAnchor constraintAnchor2 = constraintAnchor.mTarget;
        if (constraintAnchor2 == null || constraintAnchor2.mTarget != constraintAnchor) {
            ConstraintAnchor constraintAnchor3 = this.mBottom;
            ConstraintAnchor constraintAnchor4 = constraintAnchor3.mTarget;
            if (constraintAnchor4 != null && constraintAnchor4.mTarget == constraintAnchor3) {
                return true;
            }
            return false;
        }
        return true;
    }

    public final boolean isMeasureRequested() {
        if (this.mMeasureRequested && this.mVisibility != 8) {
            return true;
        }
        return false;
    }

    public boolean isResolvedHorizontally() {
        if (this.mResolvedHorizontal) {
            return true;
        }
        if (this.mLeft.mHasFinalValue && this.mRight.mHasFinalValue) {
            return true;
        }
        return false;
    }

    public boolean isResolvedVertically() {
        if (this.mResolvedVertical) {
            return true;
        }
        if (this.mTop.mHasFinalValue && this.mBottom.mHasFinalValue) {
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
        this.mCircleConstraintAngle = Float.NaN;
        this.mWidth = 0;
        this.mHeight = 0;
        this.mDimensionRatio = 0.0f;
        this.mDimensionRatioSide = -1;
        this.mX = 0;
        this.mY = 0;
        this.mBaselineDistance = 0;
        this.mMinWidth = 0;
        this.mMinHeight = 0;
        this.mHorizontalBiasPercent = 0.5f;
        this.mVerticalBiasPercent = 0.5f;
        int[] iArr = this.mListDimensionBehaviors$ar$edu;
        iArr[0] = 1;
        iArr[1] = 1;
        this.mCompanionWidget = null;
        this.mVisibility = 0;
        this.mHorizontalChainStyle = 0;
        this.mVerticalChainStyle = 0;
        float[] fArr = this.mWeight;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.mHorizontalResolution = -1;
        this.mVerticalResolution = -1;
        int[] iArr2 = this.mMaxDimension;
        iArr2[0] = Integer.MAX_VALUE;
        iArr2[1] = Integer.MAX_VALUE;
        this.mMatchConstraintDefaultWidth = 0;
        this.mMatchConstraintDefaultHeight = 0;
        this.mMatchConstraintPercentWidth = 1.0f;
        this.mMatchConstraintPercentHeight = 1.0f;
        this.mMatchConstraintMaxWidth = Preference.DEFAULT_ORDER;
        this.mMatchConstraintMaxHeight = Preference.DEFAULT_ORDER;
        this.mMatchConstraintMinWidth = 0;
        this.mMatchConstraintMinHeight = 0;
        this.mResolvedDimensionRatioSide = -1;
        this.mResolvedDimensionRatio = 1.0f;
        boolean[] zArr = this.isTerminalWidget;
        zArr[0] = true;
        zArr[1] = true;
        this.mInVirtualLayout = false;
        boolean[] zArr2 = this.mIsInBarrier;
        zArr2[0] = false;
        zArr2[1] = false;
        this.mMeasureRequested = true;
        int[] iArr3 = this.mResolvedMatchConstraintDefault;
        iArr3[0] = 0;
        iArr3[1] = 0;
        this.mWidthOverride = -1;
        this.mHeightOverride = -1;
    }

    public final void resetFinalResolution() {
        this.mResolvedHorizontal = false;
        this.mResolvedVertical = false;
        this.mHorizontalSolvingPass = false;
        this.mVerticalSolvingPass = false;
        int size = this.mAnchors.size();
        for (int i = 0; i < size; i++) {
            ConstraintAnchor constraintAnchor = (ConstraintAnchor) this.mAnchors.get(i);
            constraintAnchor.mHasFinalValue = false;
            constraintAnchor.mFinalValue = 0;
        }
    }

    public void resetSolverVariables$ar$class_merging$9c63a7fc_0$ar$class_merging$ar$class_merging(ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        this.mLeft.resetSolverVariable$ar$ds$cb2131bd_0();
        this.mTop.resetSolverVariable$ar$ds$cb2131bd_0();
        this.mRight.resetSolverVariable$ar$ds$cb2131bd_0();
        this.mBottom.resetSolverVariable$ar$ds$cb2131bd_0();
        this.mBaseline.resetSolverVariable$ar$ds$cb2131bd_0();
        this.mCenter.resetSolverVariable$ar$ds$cb2131bd_0();
        this.mCenterX.resetSolverVariable$ar$ds$cb2131bd_0();
        this.mCenterY.resetSolverVariable$ar$ds$cb2131bd_0();
    }

    public final void setBaselineDistance(int i) {
        boolean z;
        this.mBaselineDistance = i;
        if (i > 0) {
            z = true;
        } else {
            z = false;
        }
        this.mHasBaseline = z;
    }

    public final void setFinalHorizontal(int i, int i2) {
        if (this.mResolvedHorizontal) {
            return;
        }
        this.mLeft.setFinalValue(i);
        this.mRight.setFinalValue(i2);
        this.mX = i;
        this.mWidth = i2 - i;
        this.mResolvedHorizontal = true;
    }

    public final void setFinalVertical(int i, int i2) {
        if (this.mResolvedVertical) {
            return;
        }
        this.mTop.setFinalValue(i);
        this.mBottom.setFinalValue(i2);
        this.mY = i;
        this.mHeight = i2 - i;
        if (this.mHasBaseline) {
            this.mBaseline.setFinalValue(i + this.mBaselineDistance);
        }
        this.mResolvedVertical = true;
    }

    public final void setHeight(int i) {
        this.mHeight = i;
        int i2 = this.mMinHeight;
        if (i < i2) {
            this.mHeight = i2;
        }
    }

    public final void setHorizontalDimensionBehaviour$ar$edu$f268a847_0(int i) {
        this.mListDimensionBehaviors$ar$edu[0] = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void setInBarrier(int i, boolean z) {
        this.mIsInBarrier[i] = z;
    }

    public final void setLastMeasureSpec(int i, int i2) {
        this.mLastHorizontalMeasureSpec = i;
        this.mLastVerticalMeasureSpec = i2;
        this.mMeasureRequested = false;
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

    public final void setVerticalDimensionBehaviour$ar$edu$f268a847_0(int i) {
        this.mListDimensionBehaviors$ar$edu[1] = i;
    }

    public final void setWidth(int i) {
        this.mWidth = i;
        int i2 = this.mMinWidth;
        if (i < i2) {
            this.mWidth = i2;
        }
    }

    public String toString() {
        String str = "";
        StringBuilder sb = new StringBuilder("");
        if (this.mDebugName != null) {
            str = "id: " + this.mDebugName + " ";
        }
        sb.append(str);
        sb.append("(");
        sb.append(this.mX);
        sb.append(", ");
        sb.append(this.mY);
        sb.append(") - (");
        sb.append(this.mWidth);
        sb.append(" x ");
        sb.append(this.mHeight);
        sb.append(")");
        return sb.toString();
    }

    public void updateFromRuns(boolean z, boolean z2) {
        int i;
        int i2;
        HorizontalWidgetRun horizontalWidgetRun = this.mHorizontalRun;
        boolean z3 = z & horizontalWidgetRun.mResolved;
        VerticalWidgetRun verticalWidgetRun = this.mVerticalRun;
        boolean z4 = z2 & verticalWidgetRun.mResolved;
        int i3 = horizontalWidgetRun.start.value;
        int i4 = verticalWidgetRun.start.value;
        int i5 = horizontalWidgetRun.end.value;
        int i6 = verticalWidgetRun.end.value;
        int i7 = i6 - i4;
        if (i5 - i3 < 0 || i7 < 0 || i3 == Integer.MIN_VALUE || i3 == Integer.MAX_VALUE || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE) {
            i5 = 0;
            i6 = 0;
            i3 = 0;
            i4 = 0;
        }
        if (z3) {
            this.mX = i3;
        }
        if (z4) {
            this.mY = i4;
        }
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        if (z3) {
            int i8 = i5 - i3;
            if (this.mListDimensionBehaviors$ar$edu[0] == 1 && i8 < (i2 = this.mWidth)) {
                i8 = i2;
            }
            this.mWidth = i8;
            int i9 = this.mMinWidth;
            if (i8 < i9) {
                this.mWidth = i9;
            }
        }
        if (z4) {
            int i10 = i6 - i4;
            if (this.mListDimensionBehaviors$ar$edu[1] == 1 && i10 < (i = this.mHeight)) {
                i10 = i;
            }
            this.mHeight = i10;
            int i11 = this.mMinHeight;
            if (i10 < i11) {
                this.mHeight = i11;
            }
        }
    }

    public void updateFromSolver(LinearSystem linearSystem, boolean z) {
        int i;
        int i2;
        VerticalWidgetRun verticalWidgetRun;
        HorizontalWidgetRun horizontalWidgetRun;
        int objectVariableValue$ar$ds$8c6d81d4_0 = LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(this.mLeft);
        int objectVariableValue$ar$ds$8c6d81d4_02 = LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(this.mTop);
        int objectVariableValue$ar$ds$8c6d81d4_03 = LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(this.mRight);
        int objectVariableValue$ar$ds$8c6d81d4_04 = LinearSystem.getObjectVariableValue$ar$ds$8c6d81d4_0(this.mBottom);
        if (z && (horizontalWidgetRun = this.mHorizontalRun) != null) {
            DependencyNode dependencyNode = horizontalWidgetRun.start;
            if (dependencyNode.resolved) {
                DependencyNode dependencyNode2 = horizontalWidgetRun.end;
                if (dependencyNode2.resolved) {
                    objectVariableValue$ar$ds$8c6d81d4_0 = dependencyNode.value;
                    objectVariableValue$ar$ds$8c6d81d4_03 = dependencyNode2.value;
                }
            }
        }
        if (z && (verticalWidgetRun = this.mVerticalRun) != null) {
            DependencyNode dependencyNode3 = verticalWidgetRun.start;
            if (dependencyNode3.resolved) {
                DependencyNode dependencyNode4 = verticalWidgetRun.end;
                if (dependencyNode4.resolved) {
                    objectVariableValue$ar$ds$8c6d81d4_02 = dependencyNode3.value;
                    objectVariableValue$ar$ds$8c6d81d4_04 = dependencyNode4.value;
                }
            }
        }
        if (objectVariableValue$ar$ds$8c6d81d4_03 - objectVariableValue$ar$ds$8c6d81d4_0 < 0 || objectVariableValue$ar$ds$8c6d81d4_04 - objectVariableValue$ar$ds$8c6d81d4_02 < 0 || objectVariableValue$ar$ds$8c6d81d4_0 == Integer.MIN_VALUE || objectVariableValue$ar$ds$8c6d81d4_0 == Integer.MAX_VALUE || objectVariableValue$ar$ds$8c6d81d4_02 == Integer.MIN_VALUE || objectVariableValue$ar$ds$8c6d81d4_02 == Integer.MAX_VALUE || objectVariableValue$ar$ds$8c6d81d4_03 == Integer.MIN_VALUE || objectVariableValue$ar$ds$8c6d81d4_03 == Integer.MAX_VALUE || objectVariableValue$ar$ds$8c6d81d4_04 == Integer.MIN_VALUE || objectVariableValue$ar$ds$8c6d81d4_04 == Integer.MAX_VALUE) {
            objectVariableValue$ar$ds$8c6d81d4_0 = 0;
            objectVariableValue$ar$ds$8c6d81d4_02 = 0;
            objectVariableValue$ar$ds$8c6d81d4_03 = 0;
            objectVariableValue$ar$ds$8c6d81d4_04 = 0;
        }
        this.mX = objectVariableValue$ar$ds$8c6d81d4_0;
        this.mY = objectVariableValue$ar$ds$8c6d81d4_02;
        if (this.mVisibility == 8) {
            this.mWidth = 0;
            this.mHeight = 0;
            return;
        }
        int i3 = objectVariableValue$ar$ds$8c6d81d4_03 - objectVariableValue$ar$ds$8c6d81d4_0;
        int[] iArr = this.mListDimensionBehaviors$ar$edu;
        int i4 = iArr[0];
        if (i4 == 1 && i3 < (i2 = this.mWidth)) {
            i3 = i2;
        }
        int i5 = objectVariableValue$ar$ds$8c6d81d4_04 - objectVariableValue$ar$ds$8c6d81d4_02;
        if (iArr[1] == 1 && i5 < (i = this.mHeight)) {
            i5 = i;
        }
        this.mWidth = i3;
        this.mHeight = i5;
        int i6 = this.mMinHeight;
        if (i5 < i6) {
            this.mHeight = i6;
        }
        int i7 = this.mMinWidth;
        if (i3 < i7) {
            this.mWidth = i7;
        } else {
            i7 = i3;
        }
        int i8 = this.mMatchConstraintMaxWidth;
        if (i8 > 0 && i4 == 3) {
            this.mWidth = Math.min(i7, i8);
        }
        int i9 = this.mMatchConstraintMaxHeight;
        if (i9 > 0 && this.mListDimensionBehaviors$ar$edu[1] == 3) {
            this.mHeight = Math.min(this.mHeight, i9);
        }
        int i10 = this.mWidth;
        if (i3 != i10) {
            this.mWidthOverride = i10;
        }
        int i11 = this.mHeight;
        if (i5 != i11) {
            this.mHeightOverride = i11;
        }
    }
}
