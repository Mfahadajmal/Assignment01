package com.google.android.flexbox;

import android.view.View;
import androidx.preference.Preference;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FlexLine {
    boolean mAnyItemsHaveFlexGrow;
    boolean mAnyItemsHaveFlexShrink;
    int mCrossSize;
    int mDividerLengthInMainSize;
    int mFirstIndex;
    int mGoneItemCount;
    int mItemCount;
    int mLastIndex;
    int mMainSize;
    int mMaxBaseline;
    int mSumCrossSizeBefore;
    float mTotalFlexGrow;
    float mTotalFlexShrink;
    int mLeft = Preference.DEFAULT_ORDER;
    int mTop = Preference.DEFAULT_ORDER;
    int mRight = Integer.MIN_VALUE;
    int mBottom = Integer.MIN_VALUE;
    final List mIndicesAlignSelfStretch = new ArrayList();

    public final int getItemCountNotGone() {
        return this.mItemCount - this.mGoneItemCount;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updatePositionFromView(View view, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        this.mLeft = Math.min(this.mLeft, (view.getLeft() - flexItem.getMarginLeft()) - i);
        this.mTop = Math.min(this.mTop, (view.getTop() - flexItem.getMarginTop()) - i2);
        this.mRight = Math.max(this.mRight, view.getRight() + flexItem.getMarginRight() + i3);
        this.mBottom = Math.max(this.mBottom, view.getBottom() + flexItem.getMarginBottom() + i4);
    }
}
