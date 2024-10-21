package androidx.constraintlayout.core.widgets;

import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ChainHead {
    public boolean mDefined;
    public final ConstraintWidget mFirst;
    public ConstraintWidget mFirstMatchConstraintWidget;
    public ConstraintWidget mFirstVisibleWidget;
    public boolean mHasComplexMatchWeights;
    public boolean mHasDefinedWeights;
    public boolean mHasUndefinedWeights;
    public ConstraintWidget mHead;
    public final boolean mIsRtl;
    public ConstraintWidget mLast;
    public ConstraintWidget mLastMatchConstraintWidget;
    public ConstraintWidget mLastVisibleWidget;
    public final int mOrientation;
    public int mTotalMargins;
    public int mTotalSize;
    public float mTotalWeight = 0.0f;
    public int mVisibleWidgets;
    public ArrayList mWeightedMatchConstraintsWidgets;
    public int mWidgetsCount;
    public int mWidgetsMatchCount;

    public ChainHead(ConstraintWidget constraintWidget, int i, boolean z) {
        this.mFirst = constraintWidget;
        this.mOrientation = i;
        this.mIsRtl = z;
    }
}
