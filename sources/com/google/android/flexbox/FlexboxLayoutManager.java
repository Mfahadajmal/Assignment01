package com.google.android.flexbox;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import com.android.settingslib.widget.MainSwitchBar;
import com.google.android.libraries.performance.primes.metrics.crash.applicationexit.ApplicationExitConfigurations;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FlexboxLayoutManager extends RecyclerView.LayoutManager implements FlexContainer, RecyclerView.SmoothScroller.ScrollVectorProvider {
    private static final Rect TEMP_RECT = new Rect();
    private int mAlignItems;
    private final Context mContext;
    public int mFlexDirection;
    public int mFlexWrap;
    private boolean mFromBottomToTop;
    public boolean mIsRtl;
    private LayoutState mLayoutState;
    public OrientationHelper mOrientationHelper;
    private View mParent;
    private SavedState mPendingSavedState;
    private RecyclerView.Recycler mRecycler;
    private RecyclerView.State mState;
    public OrientationHelper mSubOrientationHelper;
    private final int mMaxLine = -1;
    public List mFlexLines = new ArrayList();
    public final FlexboxHelper mFlexboxHelper = new FlexboxHelper(this);
    private final AnchorInfo mAnchorInfo = new AnchorInfo();
    private int mPendingScrollPosition = -1;
    private int mPendingScrollPositionOffset = Integer.MIN_VALUE;
    private int mLastWidth = Integer.MIN_VALUE;
    private int mLastHeight = Integer.MIN_VALUE;
    private final SparseArray mViewCache = new SparseArray();
    private int mDirtyPosition = -1;
    private final ApplicationExitConfigurations.Builder mFlexLinesResult$ar$class_merging = new ApplicationExitConfigurations.Builder();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AnchorInfo {
        public boolean mAssignedFromSavedState;
        public int mCoordinate;
        public int mFlexLinePosition;
        public boolean mLayoutFromEnd;
        public int mPerpendicularCoordinate = 0;
        public int mPosition;
        public boolean mValid;

        public AnchorInfo() {
        }

        public final void assignCoordinateFromPadding() {
            int startAfterPadding;
            FlexboxLayoutManager flexboxLayoutManager = FlexboxLayoutManager.this;
            if (!flexboxLayoutManager.isMainAxisDirectionHorizontal() && flexboxLayoutManager.mIsRtl) {
                if (this.mLayoutFromEnd) {
                    startAfterPadding = flexboxLayoutManager.mOrientationHelper.getEndAfterPadding();
                } else {
                    startAfterPadding = flexboxLayoutManager.mWidth - flexboxLayoutManager.mOrientationHelper.getStartAfterPadding();
                }
            } else if (this.mLayoutFromEnd) {
                startAfterPadding = flexboxLayoutManager.mOrientationHelper.getEndAfterPadding();
            } else {
                startAfterPadding = flexboxLayoutManager.mOrientationHelper.getStartAfterPadding();
            }
            this.mCoordinate = startAfterPadding;
        }

        public final void reset() {
            this.mPosition = -1;
            this.mFlexLinePosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            boolean z = false;
            this.mValid = false;
            this.mAssignedFromSavedState = false;
            FlexboxLayoutManager flexboxLayoutManager = FlexboxLayoutManager.this;
            if (flexboxLayoutManager.isMainAxisDirectionHorizontal()) {
                if (flexboxLayoutManager.mFlexWrap == 0) {
                    if (flexboxLayoutManager.mFlexDirection == 1) {
                        z = true;
                    }
                    this.mLayoutFromEnd = z;
                    return;
                }
                this.mLayoutFromEnd = false;
                return;
            }
            if (flexboxLayoutManager.mFlexWrap == 0) {
                if (flexboxLayoutManager.mFlexDirection == 3) {
                    z = true;
                }
                this.mLayoutFromEnd = z;
                return;
            }
            this.mLayoutFromEnd = false;
        }

        public final String toString() {
            return "AnchorInfo{mPosition=" + this.mPosition + ", mFlexLinePosition=" + this.mFlexLinePosition + ", mCoordinate=" + this.mCoordinate + ", mPerpendicularCoordinate=" + this.mPerpendicularCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + ", mAssignedFromSavedState=" + this.mAssignedFromSavedState + "}";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutParams extends RecyclerView.LayoutParams implements FlexItem {
        public static final Parcelable.Creator<LayoutParams> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(9);
        private int mAlignSelf;
        private float mFlexBasisPercent;
        private float mFlexGrow;
        private float mFlexShrink;
        private int mMaxHeight;
        private int mMaxWidth;
        private int mMinHeight;
        private int mMinWidth;
        private boolean mWrapBefore;

        public LayoutParams() {
            super(-2, -2);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getAlignSelf() {
            return this.mAlignSelf;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final float getFlexBasisPercent() {
            return this.mFlexBasisPercent;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final float getFlexGrow() {
            return this.mFlexGrow;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final float getFlexShrink() {
            return this.mFlexShrink;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getHeight() {
            return this.height;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getMarginBottom() {
            return this.bottomMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getMarginLeft() {
            return this.leftMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getMarginRight() {
            return this.rightMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getMarginTop() {
            return this.topMargin;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getMaxHeight() {
            return this.mMaxHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getMaxWidth() {
            return this.mMaxWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getMinHeight() {
            return this.mMinHeight;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getMinWidth() {
            return this.mMinWidth;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getOrder() {
            return 1;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final int getWidth() {
            return this.width;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final boolean isWrapBefore() {
            return this.mWrapBefore;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final void setMinHeight(int i) {
            this.mMinHeight = i;
        }

        @Override // com.google.android.flexbox.FlexItem
        public final void setMinWidth(int i) {
            this.mMinWidth = i;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeFloat(this.mFlexGrow);
            parcel.writeFloat(this.mFlexShrink);
            parcel.writeInt(this.mAlignSelf);
            parcel.writeFloat(this.mFlexBasisPercent);
            parcel.writeInt(this.mMinWidth);
            parcel.writeInt(this.mMinHeight);
            parcel.writeInt(this.mMaxWidth);
            parcel.writeInt(this.mMaxHeight);
            parcel.writeByte(this.mWrapBefore ? (byte) 1 : (byte) 0);
            parcel.writeInt(this.bottomMargin);
            parcel.writeInt(this.leftMargin);
            parcel.writeInt(this.rightMargin);
            parcel.writeInt(this.topMargin);
            parcel.writeInt(this.height);
            parcel.writeInt(this.width);
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
        }

        public LayoutParams(Parcel parcel) {
            super(-2, -2);
            this.mFlexGrow = 0.0f;
            this.mFlexShrink = 1.0f;
            this.mAlignSelf = -1;
            this.mFlexBasisPercent = -1.0f;
            this.mMaxWidth = 16777215;
            this.mMaxHeight = 16777215;
            this.mFlexGrow = parcel.readFloat();
            this.mFlexShrink = parcel.readFloat();
            this.mAlignSelf = parcel.readInt();
            this.mFlexBasisPercent = parcel.readFloat();
            this.mMinWidth = parcel.readInt();
            this.mMinHeight = parcel.readInt();
            this.mMaxWidth = parcel.readInt();
            this.mMaxHeight = parcel.readInt();
            this.mWrapBefore = parcel.readByte() != 0;
            this.bottomMargin = parcel.readInt();
            this.leftMargin = parcel.readInt();
            this.rightMargin = parcel.readInt();
            this.topMargin = parcel.readInt();
            this.height = parcel.readInt();
            this.width = parcel.readInt();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutState {
        public int mAvailable;
        public int mFlexLinePosition;
        public boolean mInfinite;
        public int mLastScrollDelta;
        public int mOffset;
        public int mPosition;
        public int mScrollingOffset;
        public boolean mShouldRecycle;
        public int mItemDirection = 1;
        public int mLayoutDirection = 1;

        public final String toString() {
            return "LayoutState{mAvailable=" + this.mAvailable + ", mFlexLinePosition=" + this.mFlexLinePosition + ", mPosition=" + this.mPosition + ", mOffset=" + this.mOffset + ", mScrollingOffset=" + this.mScrollingOffset + ", mLastScrollDelta=" + this.mLastScrollDelta + ", mItemDirection=" + this.mItemDirection + ", mLayoutDirection=" + this.mLayoutDirection + "}";
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new MainSwitchBar.SavedState.AnonymousClass1(10);
        public int mAnchorOffset;
        public int mAnchorPosition;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final boolean hasValidAnchor(int i) {
            int i2 = this.mAnchorPosition;
            if (i2 >= 0 && i2 < i) {
                return true;
            }
            return false;
        }

        public final void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        public final String toString() {
            return "SavedState{mAnchorPosition=" + this.mAnchorPosition + ", mAnchorOffset=" + this.mAnchorOffset + "}";
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
        }

        public SavedState(SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
        }

        public SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
        }
    }

    public FlexboxLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        RecyclerView.LayoutManager.Properties properties = getProperties(context, attributeSet, i, i2);
        int i3 = properties.orientation;
        if (i3 != 0) {
            if (i3 == 1) {
                if (properties.reverseLayout) {
                    setFlexDirection(3);
                } else {
                    setFlexDirection(2);
                }
            }
        } else if (properties.reverseLayout) {
            setFlexDirection(1);
        } else {
            setFlexDirection(0);
        }
        if (this.mFlexWrap != 1) {
            removeAllViews();
            clearFlexLines();
            this.mFlexWrap = 1;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            requestLayout();
        }
        if (this.mAlignItems != 4) {
            removeAllViews();
            clearFlexLines();
            this.mAlignItems = 4;
            requestLayout();
        }
        this.mContext = context;
    }

    private final void clearFlexLines() {
        this.mFlexLines.clear();
        this.mAnchorInfo.reset();
        this.mAnchorInfo.mPerpendicularCoordinate = 0;
    }

    private final int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() != 0) {
            int itemCount = state.getItemCount();
            ensureOrientationHelper();
            View findFirstReferenceChild = findFirstReferenceChild(itemCount);
            View findLastReferenceChild = findLastReferenceChild(itemCount);
            if (state.getItemCount() != 0 && findFirstReferenceChild != null && findLastReferenceChild != null) {
                return Math.min(this.mOrientationHelper.getTotalSpace(), this.mOrientationHelper.getDecoratedEnd(findLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild));
            }
            return 0;
        }
        return 0;
    }

    private final int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() != 0) {
            int itemCount = state.getItemCount();
            View findFirstReferenceChild = findFirstReferenceChild(itemCount);
            View findLastReferenceChild = findLastReferenceChild(itemCount);
            if (state.getItemCount() != 0 && findFirstReferenceChild != null && findLastReferenceChild != null) {
                int position$ar$ds = getPosition$ar$ds(findFirstReferenceChild);
                int position$ar$ds2 = getPosition$ar$ds(findLastReferenceChild);
                int decoratedEnd = this.mOrientationHelper.getDecoratedEnd(findLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild);
                FlexboxHelper flexboxHelper = this.mFlexboxHelper;
                int abs = Math.abs(decoratedEnd);
                int i = flexboxHelper.mIndexToFlexLine[position$ar$ds];
                if (i != 0 && i != -1) {
                    return Math.round((i * (abs / ((this.mFlexboxHelper.mIndexToFlexLine[position$ar$ds2] - i) + 1))) + (this.mOrientationHelper.getStartAfterPadding() - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild)));
                }
                return 0;
            }
            return 0;
        }
        return 0;
    }

    private final int computeScrollRange(RecyclerView.State state) {
        int position$ar$ds;
        if (getChildCount() != 0) {
            int itemCount = state.getItemCount();
            View findFirstReferenceChild = findFirstReferenceChild(itemCount);
            View findLastReferenceChild = findLastReferenceChild(itemCount);
            if (state.getItemCount() != 0 && findFirstReferenceChild != null && findLastReferenceChild != null) {
                View findOneVisibleChild$ar$ds$533b5fe5_0 = findOneVisibleChild$ar$ds$533b5fe5_0(0, getChildCount());
                if (findOneVisibleChild$ar$ds$533b5fe5_0 == null) {
                    position$ar$ds = -1;
                } else {
                    position$ar$ds = getPosition$ar$ds(findOneVisibleChild$ar$ds$533b5fe5_0);
                }
                int findLastVisibleItemPosition = findLastVisibleItemPosition();
                return (int) ((Math.abs(this.mOrientationHelper.getDecoratedEnd(findLastReferenceChild) - this.mOrientationHelper.getDecoratedStart(findFirstReferenceChild)) / ((findLastVisibleItemPosition - position$ar$ds) + 1)) * state.getItemCount());
            }
        }
        return 0;
    }

    private final void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = new LayoutState();
        }
    }

    private final void ensureOrientationHelper() {
        if (this.mOrientationHelper != null) {
            return;
        }
        if (isMainAxisDirectionHorizontal()) {
            if (this.mFlexWrap == 0) {
                this.mOrientationHelper = new OrientationHelper.AnonymousClass1(this);
                this.mSubOrientationHelper = new OrientationHelper.AnonymousClass2(this);
                return;
            } else {
                this.mOrientationHelper = new OrientationHelper.AnonymousClass2(this);
                this.mSubOrientationHelper = new OrientationHelper.AnonymousClass1(this);
                return;
            }
        }
        if (this.mFlexWrap == 0) {
            this.mOrientationHelper = new OrientationHelper.AnonymousClass2(this);
            this.mSubOrientationHelper = new OrientationHelper.AnonymousClass1(this);
        } else {
            this.mOrientationHelper = new OrientationHelper.AnonymousClass1(this);
            this.mSubOrientationHelper = new OrientationHelper.AnonymousClass2(this);
        }
    }

    private final int fill(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState) {
        int i;
        int i2;
        boolean z;
        int i3;
        int i4;
        int i5;
        int i6;
        boolean z2;
        View view;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        View view2;
        int i12 = layoutState.mScrollingOffset;
        if (i12 != Integer.MIN_VALUE) {
            int i13 = layoutState.mAvailable;
            if (i13 < 0) {
                layoutState.mScrollingOffset = i12 + i13;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        int i14 = layoutState.mAvailable;
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int i15 = i14;
        int i16 = 0;
        while (true) {
            if (i15 <= 0 && !this.mLayoutState.mInfinite) {
                break;
            }
            List list = this.mFlexLines;
            int i17 = layoutState.mPosition;
            if (i17 < 0 || i17 >= state.getItemCount() || (i = layoutState.mFlexLinePosition) < 0 || i >= list.size()) {
                break;
            }
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(layoutState.mFlexLinePosition);
            layoutState.mPosition = flexLine.mFirstIndex;
            if (isMainAxisDirectionHorizontal()) {
                int paddingLeft = getPaddingLeft();
                int paddingRight = getPaddingRight();
                int i18 = this.mWidth;
                int i19 = layoutState.mOffset;
                if (layoutState.mLayoutDirection == -1) {
                    i19 -= flexLine.mCrossSize;
                }
                int i20 = layoutState.mPosition;
                int i21 = i18 - paddingRight;
                float f = this.mAnchorInfo.mPerpendicularCoordinate;
                float f2 = this.mAnchorInfo.mPerpendicularCoordinate;
                float max = Math.max(0.0f, 0.0f);
                int i22 = flexLine.mItemCount;
                float f3 = i21 - f2;
                float f4 = paddingLeft - f;
                int i23 = i20;
                int i24 = 0;
                while (i23 < i20 + i22) {
                    View flexItemAt = getFlexItemAt(i23);
                    int i25 = i20;
                    int i26 = i14;
                    if (layoutState.mLayoutDirection == 1) {
                        calculateItemDecorationsForChild(flexItemAt, TEMP_RECT);
                        addView(flexItemAt);
                    } else {
                        calculateItemDecorationsForChild(flexItemAt, TEMP_RECT);
                        addView(flexItemAt, i24);
                        i24++;
                    }
                    int i27 = i24;
                    FlexboxHelper flexboxHelper = this.mFlexboxHelper;
                    boolean z3 = isMainAxisDirectionHorizontal;
                    long j = flexboxHelper.mMeasureSpecCache[i23];
                    int i28 = (int) j;
                    int extractHigherInt = flexboxHelper.extractHigherInt(j);
                    if (shouldMeasureChild(flexItemAt, i28, extractHigherInt, (LayoutParams) flexItemAt.getLayoutParams())) {
                        flexItemAt.measure(i28, extractHigherInt);
                    }
                    float leftDecorationWidth$ar$ds = r5.leftMargin + getLeftDecorationWidth$ar$ds(flexItemAt) + f4;
                    float rightDecorationWidth$ar$ds = f3 - (r5.rightMargin + getRightDecorationWidth$ar$ds(flexItemAt));
                    int topDecorationHeight$ar$ds = i19 + getTopDecorationHeight$ar$ds(flexItemAt);
                    if (this.mIsRtl) {
                        i10 = i22;
                        i11 = i23;
                        i9 = i19;
                        view2 = flexItemAt;
                        this.mFlexboxHelper.layoutSingleChildHorizontal(flexItemAt, flexLine, Math.round(rightDecorationWidth$ar$ds) - flexItemAt.getMeasuredWidth(), topDecorationHeight$ar$ds, Math.round(rightDecorationWidth$ar$ds), topDecorationHeight$ar$ds + flexItemAt.getMeasuredHeight());
                    } else {
                        i9 = i19;
                        i10 = i22;
                        i11 = i23;
                        view2 = flexItemAt;
                        this.mFlexboxHelper.layoutSingleChildHorizontal(view2, flexLine, Math.round(leftDecorationWidth$ar$ds), topDecorationHeight$ar$ds, Math.round(leftDecorationWidth$ar$ds) + view2.getMeasuredWidth(), topDecorationHeight$ar$ds + view2.getMeasuredHeight());
                    }
                    f4 = view2.getMeasuredWidth() + r5.rightMargin + getRightDecorationWidth$ar$ds(view2) + max + leftDecorationWidth$ar$ds;
                    f3 = rightDecorationWidth$ar$ds - (((view2.getMeasuredWidth() + r5.leftMargin) + getLeftDecorationWidth$ar$ds(view2)) + max);
                    i23 = i11 + 1;
                    i20 = i25;
                    i14 = i26;
                    isMainAxisDirectionHorizontal = z3;
                    i24 = i27;
                    i22 = i10;
                    i19 = i9;
                }
                i2 = i14;
                z = isMainAxisDirectionHorizontal;
                layoutState.mFlexLinePosition += this.mLayoutState.mLayoutDirection;
                i6 = flexLine.mCrossSize;
                i5 = i15;
            } else {
                i2 = i14;
                z = isMainAxisDirectionHorizontal;
                int paddingTop = getPaddingTop();
                int paddingBottom = getPaddingBottom();
                int i29 = this.mHeight;
                int i30 = layoutState.mOffset;
                if (layoutState.mLayoutDirection == -1) {
                    int i31 = flexLine.mCrossSize;
                    i4 = i30 + i31;
                    i3 = i30 - i31;
                } else {
                    i3 = i30;
                    i4 = i3;
                }
                int i32 = layoutState.mPosition;
                int i33 = i29 - paddingBottom;
                float f5 = this.mAnchorInfo.mPerpendicularCoordinate;
                float f6 = this.mAnchorInfo.mPerpendicularCoordinate;
                float max2 = Math.max(0.0f, 0.0f);
                int i34 = flexLine.mItemCount;
                float f7 = i33 - f6;
                float f8 = paddingTop - f5;
                int i35 = i32;
                int i36 = 0;
                while (i35 < i32 + i34) {
                    View flexItemAt2 = getFlexItemAt(i35);
                    FlexboxHelper flexboxHelper2 = this.mFlexboxHelper;
                    int i37 = i15;
                    long j2 = flexboxHelper2.mMeasureSpecCache[i35];
                    int i38 = (int) j2;
                    int extractHigherInt2 = flexboxHelper2.extractHigherInt(j2);
                    if (shouldMeasureChild(flexItemAt2, i38, extractHigherInt2, (LayoutParams) flexItemAt2.getLayoutParams())) {
                        flexItemAt2.measure(i38, extractHigherInt2);
                    }
                    float topDecorationHeight$ar$ds2 = f8 + r7.topMargin + getTopDecorationHeight$ar$ds(flexItemAt2);
                    float bottomDecorationHeight$ar$ds = f7 - (r7.rightMargin + getBottomDecorationHeight$ar$ds(flexItemAt2));
                    if (layoutState.mLayoutDirection == 1) {
                        calculateItemDecorationsForChild(flexItemAt2, TEMP_RECT);
                        addView(flexItemAt2);
                    } else {
                        calculateItemDecorationsForChild(flexItemAt2, TEMP_RECT);
                        addView(flexItemAt2, i36);
                        i36++;
                    }
                    int i39 = i36;
                    int leftDecorationWidth$ar$ds2 = i3 + getLeftDecorationWidth$ar$ds(flexItemAt2);
                    int rightDecorationWidth$ar$ds2 = i4 - getRightDecorationWidth$ar$ds(flexItemAt2);
                    if (this.mIsRtl) {
                        if (this.mFromBottomToTop) {
                            z2 = true;
                            view = flexItemAt2;
                            i7 = i34;
                            i8 = i32;
                            this.mFlexboxHelper.layoutSingleChildVertical(flexItemAt2, flexLine, true, rightDecorationWidth$ar$ds2 - flexItemAt2.getMeasuredWidth(), Math.round(bottomDecorationHeight$ar$ds) - flexItemAt2.getMeasuredHeight(), rightDecorationWidth$ar$ds2, Math.round(bottomDecorationHeight$ar$ds));
                        } else {
                            z2 = true;
                            view = flexItemAt2;
                            i7 = i34;
                            i8 = i32;
                            this.mFlexboxHelper.layoutSingleChildVertical(view, flexLine, true, rightDecorationWidth$ar$ds2 - view.getMeasuredWidth(), Math.round(topDecorationHeight$ar$ds2), rightDecorationWidth$ar$ds2, Math.round(topDecorationHeight$ar$ds2) + view.getMeasuredHeight());
                        }
                    } else {
                        z2 = true;
                        view = flexItemAt2;
                        i7 = i34;
                        i8 = i32;
                        if (this.mFromBottomToTop) {
                            this.mFlexboxHelper.layoutSingleChildVertical(view, flexLine, false, leftDecorationWidth$ar$ds2, Math.round(bottomDecorationHeight$ar$ds) - view.getMeasuredHeight(), leftDecorationWidth$ar$ds2 + view.getMeasuredWidth(), Math.round(bottomDecorationHeight$ar$ds));
                        } else {
                            this.mFlexboxHelper.layoutSingleChildVertical(view, flexLine, false, leftDecorationWidth$ar$ds2, Math.round(topDecorationHeight$ar$ds2), leftDecorationWidth$ar$ds2 + view.getMeasuredWidth(), Math.round(topDecorationHeight$ar$ds2) + view.getMeasuredHeight());
                        }
                    }
                    f8 = topDecorationHeight$ar$ds2 + view.getMeasuredHeight() + r7.topMargin + getBottomDecorationHeight$ar$ds(view) + max2;
                    f7 = bottomDecorationHeight$ar$ds - (((view.getMeasuredHeight() + r7.bottomMargin) + getTopDecorationHeight$ar$ds(view)) + max2);
                    i35++;
                    i36 = i39;
                    i15 = i37;
                    i34 = i7;
                    i32 = i8;
                }
                i5 = i15;
                layoutState.mFlexLinePosition += this.mLayoutState.mLayoutDirection;
                i6 = flexLine.mCrossSize;
            }
            i16 += i6;
            if (!z && this.mIsRtl) {
                layoutState.mOffset -= flexLine.mCrossSize * layoutState.mLayoutDirection;
            } else {
                layoutState.mOffset += flexLine.mCrossSize * layoutState.mLayoutDirection;
            }
            i15 = i5 - flexLine.mCrossSize;
            i14 = i2;
            isMainAxisDirectionHorizontal = z;
        }
        int i40 = i14;
        int i41 = layoutState.mAvailable - i16;
        layoutState.mAvailable = i41;
        int i42 = layoutState.mScrollingOffset;
        if (i42 != Integer.MIN_VALUE) {
            int i43 = i42 + i16;
            layoutState.mScrollingOffset = i43;
            if (i41 < 0) {
                layoutState.mScrollingOffset = i43 + i41;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        return i40 - layoutState.mAvailable;
    }

    private final View findFirstReferenceChild(int i) {
        View findReferenceChild = findReferenceChild(0, getChildCount(), i);
        if (findReferenceChild != null) {
            FlexboxHelper flexboxHelper = this.mFlexboxHelper;
            int i2 = flexboxHelper.mIndexToFlexLine[getPosition$ar$ds(findReferenceChild)];
            if (i2 != -1) {
                return findFirstReferenceViewInLine(findReferenceChild, (FlexLine) this.mFlexLines.get(i2));
            }
            return null;
        }
        return null;
    }

    private final View findFirstReferenceViewInLine(View view, FlexLine flexLine) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int i = flexLine.mItemCount;
        for (int i2 = 1; i2 < i; i2++) {
            View childAt = getChildAt(i2);
            if (childAt != null && childAt.getVisibility() != 8) {
                if (this.mIsRtl && !isMainAxisDirectionHorizontal) {
                    if (this.mOrientationHelper.getDecoratedEnd(view) >= this.mOrientationHelper.getDecoratedEnd(childAt)) {
                    }
                    view = childAt;
                } else {
                    if (this.mOrientationHelper.getDecoratedStart(view) <= this.mOrientationHelper.getDecoratedStart(childAt)) {
                    }
                    view = childAt;
                }
            }
        }
        return view;
    }

    private final View findLastReferenceChild(int i) {
        View findReferenceChild = findReferenceChild(getChildCount() - 1, -1, i);
        if (findReferenceChild == null) {
            return null;
        }
        return findLastReferenceViewInLine(findReferenceChild, (FlexLine) this.mFlexLines.get(this.mFlexboxHelper.mIndexToFlexLine[getPosition$ar$ds(findReferenceChild)]));
    }

    private final View findLastReferenceViewInLine(View view, FlexLine flexLine) {
        boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
        int childCount = getChildCount() - flexLine.mItemCount;
        for (int childCount2 = getChildCount() - 2; childCount2 > childCount - 1; childCount2--) {
            View childAt = getChildAt(childCount2);
            if (childAt != null && childAt.getVisibility() != 8) {
                if (this.mIsRtl && !isMainAxisDirectionHorizontal) {
                    if (this.mOrientationHelper.getDecoratedStart(view) <= this.mOrientationHelper.getDecoratedStart(childAt)) {
                    }
                    view = childAt;
                } else {
                    if (this.mOrientationHelper.getDecoratedEnd(view) >= this.mOrientationHelper.getDecoratedEnd(childAt)) {
                    }
                    view = childAt;
                }
            }
        }
        return view;
    }

    private final View findOneVisibleChild$ar$ds$533b5fe5_0(int i, int i2) {
        boolean z;
        int i3 = i;
        while (i3 != i2) {
            View childAt = getChildAt(i3);
            int paddingLeft = getPaddingLeft();
            int paddingTop = getPaddingTop();
            int paddingRight = this.mWidth - getPaddingRight();
            int paddingBottom = this.mHeight - getPaddingBottom();
            int decoratedLeft = getDecoratedLeft(childAt) - ((RecyclerView.LayoutParams) childAt.getLayoutParams()).leftMargin;
            int decoratedTop = getDecoratedTop(childAt) - ((RecyclerView.LayoutParams) childAt.getLayoutParams()).topMargin;
            int decoratedRight = getDecoratedRight(childAt) + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).rightMargin;
            int decoratedBottom = getDecoratedBottom(childAt) + ((RecyclerView.LayoutParams) childAt.getLayoutParams()).bottomMargin;
            boolean z2 = false;
            int i4 = 1;
            if (decoratedLeft < paddingRight && decoratedRight < paddingLeft) {
                z = false;
            } else {
                z = true;
            }
            if (decoratedTop >= paddingBottom || decoratedBottom >= paddingTop) {
                z2 = true;
            }
            if (z && z2) {
                return childAt;
            }
            if (i2 <= i) {
                i4 = -1;
            }
            i3 += i4;
        }
        return null;
    }

    private final View findReferenceChild(int i, int i2, int i3) {
        int i4;
        int position$ar$ds;
        ensureOrientationHelper();
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        View view = null;
        int i5 = i;
        View view2 = null;
        while (i5 != i2) {
            View childAt = getChildAt(i5);
            if (childAt != null && (position$ar$ds = getPosition$ar$ds(childAt)) >= 0 && position$ar$ds < i3) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    if (this.mOrientationHelper.getDecoratedStart(childAt) >= startAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) <= endAfterPadding) {
                        return childAt;
                    }
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            if (i2 > i) {
                i4 = 1;
            } else {
                i4 = -1;
            }
            i5 += i4;
        }
        if (view != null) {
            return view;
        }
        return view2;
    }

    private final int fixLayoutEndGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i2;
        int endAfterPadding;
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            int startAfterPadding = i - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding <= 0) {
                return 0;
            }
            i2 = handleScrollingMainOrientation(startAfterPadding, recycler, state);
        } else {
            int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i;
            if (endAfterPadding2 <= 0) {
                return 0;
            }
            i2 = -handleScrollingMainOrientation(-endAfterPadding2, recycler, state);
        }
        int i3 = i + i2;
        if (z && (endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i3) > 0) {
            this.mOrientationHelper.offsetChildren(endAfterPadding);
            return endAfterPadding + i2;
        }
        return i2;
    }

    private final int fixLayoutStartGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int i2;
        int startAfterPadding;
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            int endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i;
            if (endAfterPadding <= 0) {
                return 0;
            }
            i2 = handleScrollingMainOrientation(-endAfterPadding, recycler, state);
        } else {
            int startAfterPadding2 = i - this.mOrientationHelper.getStartAfterPadding();
            if (startAfterPadding2 <= 0) {
                return 0;
            }
            i2 = -handleScrollingMainOrientation(startAfterPadding2, recycler, state);
        }
        int i3 = i + i2;
        if (z && (startAfterPadding = i3 - this.mOrientationHelper.getStartAfterPadding()) > 0) {
            this.mOrientationHelper.offsetChildren(-startAfterPadding);
            return i2 - startAfterPadding;
        }
        return i2;
    }

    private final View getChildClosestToStart() {
        return getChildAt(0);
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x01e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int handleScrollingMainOrientation(int r18, android.support.v7.widget.RecyclerView.Recycler r19, android.support.v7.widget.RecyclerView.State r20) {
        /*
            Method dump skipped, instructions count: 512
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.handleScrollingMainOrientation(int, android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State):int");
    }

    private final int handleScrollingSubOrientation(int i) {
        int height;
        int i2;
        int i3;
        if (getChildCount() != 0 && i != 0) {
            ensureOrientationHelper();
            boolean isMainAxisDirectionHorizontal = isMainAxisDirectionHorizontal();
            if (isMainAxisDirectionHorizontal) {
                height = this.mParent.getWidth();
            } else {
                height = this.mParent.getHeight();
            }
            if (isMainAxisDirectionHorizontal) {
                i2 = this.mWidth;
            } else {
                i2 = this.mHeight;
            }
            if (getLayoutDirection() == 1) {
                int abs = Math.abs(i);
                if (i < 0) {
                    i3 = Math.min((i2 + this.mAnchorInfo.mPerpendicularCoordinate) - height, abs);
                } else if (this.mAnchorInfo.mPerpendicularCoordinate + i > 0) {
                    i3 = this.mAnchorInfo.mPerpendicularCoordinate;
                } else {
                    return i;
                }
            } else {
                if (i > 0) {
                    return Math.min((i2 - this.mAnchorInfo.mPerpendicularCoordinate) - height, i);
                }
                if (this.mAnchorInfo.mPerpendicularCoordinate + i < 0) {
                    i3 = this.mAnchorInfo.mPerpendicularCoordinate;
                } else {
                    return i;
                }
            }
            return -i3;
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0077 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00d9  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00ef A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void recycleByLayoutState(android.support.v7.widget.RecyclerView.Recycler r12, com.google.android.flexbox.FlexboxLayoutManager.LayoutState r13) {
        /*
            Method dump skipped, instructions count: 247
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.recycleByLayoutState(android.support.v7.widget.RecyclerView$Recycler, com.google.android.flexbox.FlexboxLayoutManager$LayoutState):void");
    }

    private final void recycleChildren(RecyclerView.Recycler recycler, int i, int i2) {
        while (i2 >= i) {
            removeAndRecycleViewAt(i2, recycler);
            i2--;
        }
    }

    private final void resolveInfiniteAmount() {
        int i;
        if (isMainAxisDirectionHorizontal()) {
            i = this.mHeightMode;
        } else {
            i = this.mWidthMode;
        }
        LayoutState layoutState = this.mLayoutState;
        boolean z = true;
        if (i != 0 && i != Integer.MIN_VALUE) {
            z = false;
        }
        layoutState.mInfinite = z;
    }

    private final boolean shouldMeasureChild(View view, int i, int i2, RecyclerView.LayoutParams layoutParams) {
        if (!view.isLayoutRequested() && this.mMeasurementCacheEnabled && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_12(view.getWidth(), i, layoutParams.width) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_12(view.getHeight(), i2, layoutParams.height)) {
            return false;
        }
        return true;
    }

    private final void updateDirtyPosition(int i) {
        if (i < findLastVisibleItemPosition()) {
            int childCount = getChildCount();
            this.mFlexboxHelper.ensureMeasureSpecCache(childCount);
            this.mFlexboxHelper.ensureMeasuredSizeCache(childCount);
            this.mFlexboxHelper.ensureIndexToFlexLine(childCount);
            if (i < this.mFlexboxHelper.mIndexToFlexLine.length) {
                this.mDirtyPosition = i;
                View childClosestToStart = getChildClosestToStart();
                if (childClosestToStart != null) {
                    this.mPendingScrollPosition = getPosition$ar$ds(childClosestToStart);
                    if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
                        this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToStart) + this.mOrientationHelper.getEndPadding();
                    } else {
                        this.mPendingScrollPositionOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
                    }
                }
            }
        }
    }

    private final void updateLayoutStateToFillEnd(AnchorInfo anchorInfo, boolean z, boolean z2) {
        int i;
        if (z2) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - getPaddingRight();
        } else {
            this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - anchorInfo.mCoordinate;
        }
        LayoutState layoutState = this.mLayoutState;
        layoutState.mPosition = anchorInfo.mPosition;
        layoutState.mItemDirection = 1;
        LayoutState layoutState2 = this.mLayoutState;
        layoutState2.mLayoutDirection = 1;
        layoutState2.mOffset = anchorInfo.mCoordinate;
        layoutState2.mScrollingOffset = Integer.MIN_VALUE;
        layoutState2.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (z && this.mFlexLines.size() > 1 && (i = anchorInfo.mFlexLinePosition) >= 0 && i < this.mFlexLines.size() - 1) {
            FlexLine flexLine = (FlexLine) this.mFlexLines.get(anchorInfo.mFlexLinePosition);
            LayoutState layoutState3 = this.mLayoutState;
            layoutState3.mFlexLinePosition++;
            layoutState3.mPosition += flexLine.mItemCount;
        }
    }

    private final void updateLayoutStateToFillStart(AnchorInfo anchorInfo, boolean z, boolean z2) {
        if (z2) {
            resolveInfiniteAmount();
        } else {
            this.mLayoutState.mInfinite = false;
        }
        if (!isMainAxisDirectionHorizontal() && this.mIsRtl) {
            this.mLayoutState.mAvailable = (this.mParent.getWidth() - anchorInfo.mCoordinate) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            this.mLayoutState.mAvailable = anchorInfo.mCoordinate - this.mOrientationHelper.getStartAfterPadding();
        }
        LayoutState layoutState = this.mLayoutState;
        layoutState.mPosition = anchorInfo.mPosition;
        layoutState.mItemDirection = 1;
        LayoutState layoutState2 = this.mLayoutState;
        layoutState2.mLayoutDirection = -1;
        layoutState2.mOffset = anchorInfo.mCoordinate;
        layoutState2.mScrollingOffset = Integer.MIN_VALUE;
        layoutState2.mFlexLinePosition = anchorInfo.mFlexLinePosition;
        if (z && anchorInfo.mFlexLinePosition > 0) {
            int size = this.mFlexLines.size();
            int i = anchorInfo.mFlexLinePosition;
            if (size > i) {
                FlexLine flexLine = (FlexLine) this.mFlexLines.get(i);
                r4.mFlexLinePosition--;
                this.mLayoutState.mPosition -= flexLine.mItemCount;
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean canScrollHorizontally() {
        int i;
        if (this.mFlexWrap == 0) {
            return isMainAxisDirectionHorizontal();
        }
        if (isMainAxisDirectionHorizontal()) {
            int i2 = this.mWidth;
            View view = this.mParent;
            if (view != null) {
                i = view.getWidth();
            } else {
                i = 0;
            }
            if (i2 <= i) {
                return false;
            }
            return true;
        }
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean canScrollVertically() {
        int i;
        if (this.mFlexWrap == 0) {
            if (!isMainAxisDirectionHorizontal()) {
                return true;
            }
            return false;
        }
        if (!isMainAxisDirectionHorizontal()) {
            int i2 = this.mHeight;
            View view = this.mParent;
            if (view != null) {
                i = view.getHeight();
            } else {
                i = 0;
            }
            if (i2 <= i) {
                return false;
            }
        }
        return true;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int computeHorizontalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    @Override // android.support.v7.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
    public final PointF computeScrollVectorForPosition(int i) {
        View childAt;
        int i2;
        if (getChildCount() != 0 && (childAt = getChildAt(0)) != null) {
            if (i < getPosition$ar$ds(childAt)) {
                i2 = -1;
            } else {
                i2 = 1;
            }
            float f = i2;
            if (isMainAxisDirectionHorizontal()) {
                return new PointF(0.0f, f);
            }
            return new PointF(f, 0.0f);
        }
        return null;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollExtent(RecyclerView.State state) {
        return computeScrollExtent(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollOffset(RecyclerView.State state) {
        return computeScrollOffset(state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int computeVerticalScrollRange(RecyclerView.State state) {
        return computeScrollRange(state);
    }

    public final int findLastVisibleItemPosition() {
        View findOneVisibleChild$ar$ds$533b5fe5_0 = findOneVisibleChild$ar$ds$533b5fe5_0(getChildCount() - 1, -1);
        if (findOneVisibleChild$ar$ds$533b5fe5_0 == null) {
            return -1;
        }
        return getPosition$ar$ds(findOneVisibleChild$ar$ds$533b5fe5_0);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getAlignContent() {
        return 5;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getAlignItems() {
        return this.mAlignItems;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getChildHeightMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(this.mHeight, this.mHeightMode, i2, i3, canScrollVertically());
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getChildWidthMeasureSpec(int i, int i2, int i3) {
        return getChildMeasureSpec(this.mWidth, this.mWidthMode, i2, i3, canScrollHorizontally());
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getDecorationLengthCrossAxis(View view) {
        int leftDecorationWidth$ar$ds;
        int rightDecorationWidth$ar$ds;
        if (isMainAxisDirectionHorizontal()) {
            leftDecorationWidth$ar$ds = getTopDecorationHeight$ar$ds(view);
            rightDecorationWidth$ar$ds = getBottomDecorationHeight$ar$ds(view);
        } else {
            leftDecorationWidth$ar$ds = getLeftDecorationWidth$ar$ds(view);
            rightDecorationWidth$ar$ds = getRightDecorationWidth$ar$ds(view);
        }
        return leftDecorationWidth$ar$ds + rightDecorationWidth$ar$ds;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getDecorationLengthMainAxis(View view, int i, int i2) {
        int topDecorationHeight$ar$ds;
        int bottomDecorationHeight$ar$ds;
        if (isMainAxisDirectionHorizontal()) {
            topDecorationHeight$ar$ds = getLeftDecorationWidth$ar$ds(view);
            bottomDecorationHeight$ar$ds = getRightDecorationWidth$ar$ds(view);
        } else {
            topDecorationHeight$ar$ds = getTopDecorationHeight$ar$ds(view);
            bottomDecorationHeight$ar$ds = getBottomDecorationHeight$ar$ds(view);
        }
        return topDecorationHeight$ar$ds + bottomDecorationHeight$ar$ds;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getFlexDirection() {
        return this.mFlexDirection;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final View getFlexItemAt(int i) {
        View view = (View) this.mViewCache.get(i);
        if (view != null) {
            return view;
        }
        return this.mRecycler.getViewForPosition(i);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getFlexItemCount() {
        return this.mState.getItemCount();
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final List getFlexLinesInternal() {
        return this.mFlexLines;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getFlexWrap() {
        return this.mFlexWrap;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getLargestMainSize() {
        if (this.mFlexLines.size() == 0) {
            return 0;
        }
        int size = this.mFlexLines.size();
        int i = Integer.MIN_VALUE;
        for (int i2 = 0; i2 < size; i2++) {
            i = Math.max(i, ((FlexLine) this.mFlexLines.get(i2)).mMainSize);
        }
        return i;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getMaxLine() {
        return this.mMaxLine;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final View getReorderedFlexItemAt(int i) {
        return getFlexItemAt(i);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final int getSumOfCrossSize() {
        int size = this.mFlexLines.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += ((FlexLine) this.mFlexLines.get(i2)).mCrossSize;
        }
        return i;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean isAutoMeasureEnabled() {
        return true;
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final boolean isMainAxisDirectionHorizontal() {
        int i = this.mFlexDirection;
        if (i == 0 || i == 1) {
            return true;
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onAdapterChanged$ar$ds() {
        removeAllViews();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onAttachedToWindow(RecyclerView recyclerView) {
        this.mParent = (View) recyclerView.getParent();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsAdded$ar$ds(int i, int i2) {
        updateDirtyPosition(i);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsMoved$ar$ds(int i, int i2) {
        updateDirtyPosition(Math.min(i, i2));
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsRemoved$ar$ds(int i, int i2) {
        updateDirtyPosition(i);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsUpdated$ar$ds(RecyclerView recyclerView, int i, int i2) {
        onItemsUpdated$ar$ds$5eb43a0c_0(i);
        updateDirtyPosition(i);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsUpdated$ar$ds$5eb43a0c_0(int i) {
        updateDirtyPosition(i);
    }

    /* JADX WARN: Code restructure failed: missing block: B:173:0x002e, code lost:
    
        if (r5 == 1) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:174:0x0038, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x0036, code lost:
    
        r5 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:177:0x0031, code lost:
    
        if (r5 != 1) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0034, code lost:
    
        if (r5 == 1) goto L21;
     */
    /* JADX WARN: Removed duplicated region for block: B:140:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0392  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x03be  */
    /* JADX WARN: Removed duplicated region for block: B:62:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x03a3  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0340  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02f9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x025e  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0222  */
    /* JADX WARN: Type inference failed for: r3v34, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.util.List, java.lang.Object] */
    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void onLayoutChildren(android.support.v7.widget.RecyclerView.Recycler r20, android.support.v7.widget.RecyclerView.State r21) {
        /*
            Method dump skipped, instructions count: 982
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.flexbox.FlexboxLayoutManager.onLayoutChildren(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State):void");
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onLayoutCompleted(RecyclerView.State state) {
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mDirtyPosition = -1;
        this.mAnchorInfo.reset();
        this.mViewCache.clear();
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final void onNewFlexItemAdded(View view, int i, int i2, FlexLine flexLine) {
        calculateItemDecorationsForChild(view, TEMP_RECT);
        if (isMainAxisDirectionHorizontal()) {
            int leftDecorationWidth$ar$ds = getLeftDecorationWidth$ar$ds(view) + getRightDecorationWidth$ar$ds(view);
            flexLine.mMainSize += leftDecorationWidth$ar$ds;
            flexLine.mDividerLengthInMainSize += leftDecorationWidth$ar$ds;
        } else {
            int topDecorationHeight$ar$ds = getTopDecorationHeight$ar$ds(view) + getBottomDecorationHeight$ar$ds(view);
            flexLine.mMainSize += topDecorationHeight$ar$ds;
            flexLine.mDividerLengthInMainSize += topDecorationHeight$ar$ds;
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            this.mPendingSavedState = (SavedState) parcelable;
            requestLayout();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        if (getChildCount() > 0) {
            View childClosestToStart = getChildClosestToStart();
            savedState2.mAnchorPosition = getPosition$ar$ds(childClosestToStart);
            savedState2.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
        } else {
            savedState2.invalidateAnchor();
        }
        return savedState2;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (isMainAxisDirectionHorizontal() && this.mFlexWrap != 0) {
            int handleScrollingSubOrientation = handleScrollingSubOrientation(i);
            this.mAnchorInfo.mPerpendicularCoordinate += handleScrollingSubOrientation;
            this.mSubOrientationHelper.offsetChildren(-handleScrollingSubOrientation);
            return handleScrollingSubOrientation;
        }
        int handleScrollingMainOrientation = handleScrollingMainOrientation(i, recycler, state);
        this.mViewCache.clear();
        return handleScrollingMainOrientation;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void scrollToPosition(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchor();
        }
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (!isMainAxisDirectionHorizontal() && (this.mFlexWrap != 0 || isMainAxisDirectionHorizontal())) {
            int handleScrollingSubOrientation = handleScrollingSubOrientation(i);
            this.mAnchorInfo.mPerpendicularCoordinate += handleScrollingSubOrientation;
            this.mSubOrientationHelper.offsetChildren(-handleScrollingSubOrientation);
            return handleScrollingSubOrientation;
        }
        int handleScrollingMainOrientation = handleScrollingMainOrientation(i, recycler, state);
        this.mViewCache.clear();
        return handleScrollingMainOrientation;
    }

    public final void setFlexDirection(int i) {
        if (this.mFlexDirection != i) {
            removeAllViews();
            this.mFlexDirection = i;
            this.mOrientationHelper = null;
            this.mSubOrientationHelper = null;
            clearFlexLines();
            requestLayout();
        }
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final void setFlexLines(List list) {
        this.mFlexLines = list;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void smoothScrollToPosition$ar$ds(RecyclerView recyclerView, int i) {
        RecyclerView.SmoothScroller smoothScroller = new RecyclerView.SmoothScroller(recyclerView.getContext());
        smoothScroller.mTargetPosition = i;
        startSmoothScroll(smoothScroller);
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final void updateViewCache(int i, View view) {
        this.mViewCache.put(i, view);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onDetachedFromWindow$ar$ds(RecyclerView recyclerView) {
    }

    @Override // com.google.android.flexbox.FlexContainer
    public final void onNewFlexLineAdded(FlexLine flexLine) {
    }
}
