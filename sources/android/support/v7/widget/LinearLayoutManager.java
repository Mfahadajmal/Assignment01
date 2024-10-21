package android.support.v7.widget;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;
import android.support.v7.widget.GapWorker;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.preference.Preference;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LinearLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    final AnchorInfo mAnchorInfo;
    private int mInitialPrefetchItemCount;
    private boolean mLastStackFromEnd;
    private final LayoutChunkResult mLayoutChunkResult;
    private LayoutState mLayoutState;
    int mOrientation;
    OrientationHelper mOrientationHelper;
    SavedState mPendingSavedState;
    int mPendingScrollPosition;
    int mPendingScrollPositionOffset;
    private int[] mReusableIntPair;
    private boolean mReverseLayout;
    boolean mShouldReverseLayout;
    private boolean mSmoothScrollbarEnabled;
    private boolean mStackFromEnd;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AnchorInfo {
        int mCoordinate;
        boolean mLayoutFromEnd;
        OrientationHelper mOrientationHelper;
        int mPosition;
        boolean mValid;

        public AnchorInfo() {
            reset();
        }

        final void assignCoordinateFromPadding() {
            int startAfterPadding;
            if (this.mLayoutFromEnd) {
                startAfterPadding = this.mOrientationHelper.getEndAfterPadding();
            } else {
                startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
            }
            this.mCoordinate = startAfterPadding;
        }

        public final void assignFromView(View view, int i) {
            if (this.mLayoutFromEnd) {
                this.mCoordinate = this.mOrientationHelper.getDecoratedEnd(view) + this.mOrientationHelper.getTotalSpaceChange();
            } else {
                this.mCoordinate = this.mOrientationHelper.getDecoratedStart(view);
            }
            this.mPosition = i;
        }

        public final void assignFromViewAndKeepVisibleRect(View view, int i) {
            int totalSpaceChange = this.mOrientationHelper.getTotalSpaceChange();
            if (totalSpaceChange >= 0) {
                assignFromView(view, i);
                return;
            }
            this.mPosition = i;
            if (this.mLayoutFromEnd) {
                int endAfterPadding = (this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange) - this.mOrientationHelper.getDecoratedEnd(view);
                this.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - endAfterPadding;
                if (endAfterPadding > 0) {
                    int decoratedMeasurement = this.mCoordinate - this.mOrientationHelper.getDecoratedMeasurement(view);
                    int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
                    int min = decoratedMeasurement - (startAfterPadding + Math.min(this.mOrientationHelper.getDecoratedStart(view) - startAfterPadding, 0));
                    if (min < 0) {
                        this.mCoordinate += Math.min(endAfterPadding, -min);
                        return;
                    }
                    return;
                }
                return;
            }
            int decoratedStart = this.mOrientationHelper.getDecoratedStart(view);
            int startAfterPadding2 = decoratedStart - this.mOrientationHelper.getStartAfterPadding();
            this.mCoordinate = decoratedStart;
            if (startAfterPadding2 > 0) {
                int endAfterPadding2 = (this.mOrientationHelper.getEndAfterPadding() - Math.min(0, (this.mOrientationHelper.getEndAfterPadding() - totalSpaceChange) - this.mOrientationHelper.getDecoratedEnd(view))) - (decoratedStart + this.mOrientationHelper.getDecoratedMeasurement(view));
                if (endAfterPadding2 < 0) {
                    this.mCoordinate -= Math.min(startAfterPadding2, -endAfterPadding2);
                }
            }
        }

        final void reset() {
            this.mPosition = -1;
            this.mCoordinate = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mValid = false;
        }

        public final String toString() {
            return "AnchorInfo{mPosition=" + this.mPosition + ", mCoordinate=" + this.mCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + ", mValid=" + this.mValid + '}';
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutChunkResult {
        public int mConsumed;
        public boolean mFinished;
        public boolean mFocusable;
        public boolean mIgnoreConsumed;

        protected LayoutChunkResult() {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutState {
        int mAvailable;
        int mCurrentPosition;
        boolean mInfinite;
        int mItemDirection;
        int mLastScrollDelta;
        int mLayoutDirection;
        int mOffset;
        int mScrollingOffset;
        boolean mRecycle = true;
        int mExtraFillSpace = 0;
        int mNoRecycleSpace = 0;
        boolean mIsPreLayout = false;
        List mScrapList = null;

        public final void assignPositionFromScrapList() {
            assignPositionFromScrapList(null);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final boolean hasMore(RecyclerView.State state) {
            int i = this.mCurrentPosition;
            if (i >= 0 && i < state.getItemCount()) {
                return true;
            }
            return false;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final View next(RecyclerView.Recycler recycler) {
            List list = this.mScrapList;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    View view = ((RecyclerView.ViewHolder) this.mScrapList.get(i)).itemView;
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
                    if (!layoutParams.isItemRemoved() && this.mCurrentPosition == layoutParams.getViewLayoutPosition()) {
                        assignPositionFromScrapList(view);
                        return view;
                    }
                }
                return null;
            }
            View viewForPosition = recycler.getViewForPosition(this.mCurrentPosition);
            this.mCurrentPosition += this.mItemDirection;
            return viewForPosition;
        }

        public final void assignPositionFromScrapList(View view) {
            int viewLayoutPosition;
            int size = this.mScrapList.size();
            int i = 0;
            int i2 = Preference.DEFAULT_ORDER;
            View view2 = null;
            while (true) {
                if (i >= size) {
                    break;
                }
                View view3 = ((RecyclerView.ViewHolder) this.mScrapList.get(i)).itemView;
                RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view3.getLayoutParams();
                if (view3 != view && !layoutParams.isItemRemoved() && (viewLayoutPosition = (layoutParams.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection) >= 0 && viewLayoutPosition < i2) {
                    if (viewLayoutPosition == 0) {
                        view2 = view3;
                        break;
                    } else {
                        view2 = view3;
                        i2 = viewLayoutPosition;
                    }
                }
                i++;
            }
            if (view2 == null) {
                this.mCurrentPosition = -1;
            } else {
                this.mCurrentPosition = ((RecyclerView.LayoutParams) view2.getLayoutParams()).getViewLayoutPosition();
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new BackStackState.AnonymousClass1(7);
        boolean mAnchorLayoutFromEnd;
        int mAnchorOffset;
        int mAnchorPosition;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        final boolean hasValidAnchor() {
            if (this.mAnchorPosition >= 0) {
                return true;
            }
            return false;
        }

        final void invalidateAnchor() {
            this.mAnchorPosition = -1;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mAnchorOffset);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
        }

        public SavedState(SavedState savedState) {
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mAnchorOffset = savedState.mAnchorOffset;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
        }

        public SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mAnchorOffset = parcel.readInt();
            this.mAnchorLayoutFromEnd = parcel.readInt() == 1;
        }
    }

    public LinearLayoutManager(Context context) {
        this(1);
    }

    private final int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return AppCompatTextClassifierHelper$Api26Impl.computeScrollExtent(state, this.mOrientationHelper, findFirstVisibleChildClosestToStart$ar$ds(!this.mSmoothScrollbarEnabled), findFirstVisibleChildClosestToEnd$ar$ds(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private final int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return AppCompatTextClassifierHelper$Api26Impl.computeScrollOffset(state, this.mOrientationHelper, findFirstVisibleChildClosestToStart$ar$ds(!this.mSmoothScrollbarEnabled), findFirstVisibleChildClosestToEnd$ar$ds(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private final int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        ensureLayoutState();
        return AppCompatTextClassifierHelper$Api26Impl.computeScrollRange(state, this.mOrientationHelper, findFirstVisibleChildClosestToStart$ar$ds(!this.mSmoothScrollbarEnabled), findFirstVisibleChildClosestToEnd$ar$ds(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private final View findFirstPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(0, getChildCount());
    }

    private final View findLastPartiallyOrCompletelyInvisibleChild() {
        return findOnePartiallyOrCompletelyInvisibleChild(getChildCount() - 1, -1);
    }

    private final int fixLayoutEndGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int endAfterPadding2 = this.mOrientationHelper.getEndAfterPadding() - i;
        if (endAfterPadding2 > 0) {
            int i2 = -scrollBy(-endAfterPadding2, recycler, state);
            int i3 = i + i2;
            if (z && (endAfterPadding = this.mOrientationHelper.getEndAfterPadding() - i3) > 0) {
                this.mOrientationHelper.offsetChildren(endAfterPadding);
                return endAfterPadding + i2;
            }
            return i2;
        }
        return 0;
    }

    private final int fixLayoutStartGap(int i, RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int startAfterPadding2 = i - this.mOrientationHelper.getStartAfterPadding();
        if (startAfterPadding2 > 0) {
            int i2 = -scrollBy(startAfterPadding2, recycler, state);
            int i3 = i + i2;
            if (z && (startAfterPadding = i3 - this.mOrientationHelper.getStartAfterPadding()) > 0) {
                this.mOrientationHelper.offsetChildren(-startAfterPadding);
                return i2 - startAfterPadding;
            }
            return i2;
        }
        return 0;
    }

    private final View getChildClosestToEnd() {
        int childCount;
        if (this.mShouldReverseLayout) {
            childCount = 0;
        } else {
            childCount = getChildCount() - 1;
        }
        return getChildAt(childCount);
    }

    private final View getChildClosestToStart() {
        int i;
        if (this.mShouldReverseLayout) {
            i = getChildCount() - 1;
        } else {
            i = 0;
        }
        return getChildAt(i);
    }

    private final void recycleByLayoutState(RecyclerView.Recycler recycler, LayoutState layoutState) {
        if (layoutState.mRecycle && !layoutState.mInfinite) {
            int i = layoutState.mScrollingOffset;
            int i2 = layoutState.mNoRecycleSpace;
            if (layoutState.mLayoutDirection == -1) {
                int childCount = getChildCount();
                if (i >= 0) {
                    int end = (this.mOrientationHelper.getEnd() - i) + i2;
                    if (this.mShouldReverseLayout) {
                        for (int i3 = 0; i3 < childCount; i3++) {
                            View childAt = getChildAt(i3);
                            if (this.mOrientationHelper.getDecoratedStart(childAt) < end || this.mOrientationHelper.getTransformedStartWithDecoration(childAt) < end) {
                                recycleChildren(recycler, 0, i3);
                                return;
                            }
                        }
                        return;
                    }
                    int i4 = childCount - 1;
                    for (int i5 = i4; i5 >= 0; i5--) {
                        View childAt2 = getChildAt(i5);
                        if (this.mOrientationHelper.getDecoratedStart(childAt2) < end || this.mOrientationHelper.getTransformedStartWithDecoration(childAt2) < end) {
                            recycleChildren(recycler, i4, i5);
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            if (i >= 0) {
                int i6 = i - i2;
                int childCount2 = getChildCount();
                if (this.mShouldReverseLayout) {
                    int i7 = childCount2 - 1;
                    for (int i8 = i7; i8 >= 0; i8--) {
                        View childAt3 = getChildAt(i8);
                        if (this.mOrientationHelper.getDecoratedEnd(childAt3) > i6 || this.mOrientationHelper.getTransformedEndWithDecoration(childAt3) > i6) {
                            recycleChildren(recycler, i7, i8);
                            return;
                        }
                    }
                    return;
                }
                for (int i9 = 0; i9 < childCount2; i9++) {
                    View childAt4 = getChildAt(i9);
                    if (this.mOrientationHelper.getDecoratedEnd(childAt4) > i6 || this.mOrientationHelper.getTransformedEndWithDecoration(childAt4) > i6) {
                        recycleChildren(recycler, 0, i9);
                        return;
                    }
                }
            }
        }
    }

    private final void recycleChildren(RecyclerView.Recycler recycler, int i, int i2) {
        if (i != i2) {
            if (i2 <= i) {
                while (i > i2) {
                    removeAndRecycleViewAt(i, recycler);
                    i--;
                }
            } else {
                while (true) {
                    i2--;
                    if (i2 >= i) {
                        removeAndRecycleViewAt(i2, recycler);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    private final void resolveShouldLayoutReverse() {
        boolean z;
        if (this.mOrientation != 1 && isLayoutRTL()) {
            z = !this.mReverseLayout;
        } else {
            z = this.mReverseLayout;
        }
        this.mShouldReverseLayout = z;
    }

    private final void updateLayoutState(int i, int i2, boolean z, RecyclerView.State state) {
        int i3;
        int startAfterPadding;
        this.mLayoutState.mInfinite = resolveIsInfinite();
        this.mLayoutState.mLayoutDirection = i;
        int[] iArr = this.mReusableIntPair;
        iArr[0] = 0;
        int i4 = 1;
        iArr[1] = 0;
        calculateExtraLayoutSpace(state, iArr);
        int max = Math.max(0, this.mReusableIntPair[0]);
        int max2 = Math.max(0, this.mReusableIntPair[1]);
        if (i == 1) {
            i3 = max2;
        } else {
            i3 = max;
        }
        LayoutState layoutState = this.mLayoutState;
        layoutState.mExtraFillSpace = i3;
        if (i != 1) {
            max = max2;
        }
        layoutState.mNoRecycleSpace = max;
        if (i == 1) {
            layoutState.mExtraFillSpace = i3 + this.mOrientationHelper.getEndPadding();
            View childClosestToEnd = getChildClosestToEnd();
            LayoutState layoutState2 = this.mLayoutState;
            if (true == this.mShouldReverseLayout) {
                i4 = -1;
            }
            layoutState2.mItemDirection = i4;
            int position$ar$ds = getPosition$ar$ds(childClosestToEnd);
            LayoutState layoutState3 = this.mLayoutState;
            layoutState2.mCurrentPosition = position$ar$ds + layoutState3.mItemDirection;
            layoutState3.mOffset = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
            startAfterPadding = this.mOrientationHelper.getDecoratedEnd(childClosestToEnd) - this.mOrientationHelper.getEndAfterPadding();
        } else {
            View childClosestToStart = getChildClosestToStart();
            this.mLayoutState.mExtraFillSpace += this.mOrientationHelper.getStartAfterPadding();
            LayoutState layoutState4 = this.mLayoutState;
            if (true != this.mShouldReverseLayout) {
                i4 = -1;
            }
            layoutState4.mItemDirection = i4;
            int position$ar$ds2 = getPosition$ar$ds(childClosestToStart);
            LayoutState layoutState5 = this.mLayoutState;
            layoutState4.mCurrentPosition = position$ar$ds2 + layoutState5.mItemDirection;
            layoutState5.mOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart);
            startAfterPadding = (-this.mOrientationHelper.getDecoratedStart(childClosestToStart)) + this.mOrientationHelper.getStartAfterPadding();
        }
        LayoutState layoutState6 = this.mLayoutState;
        layoutState6.mAvailable = i2;
        if (z) {
            layoutState6.mAvailable = i2 - startAfterPadding;
        }
        layoutState6.mScrollingOffset = startAfterPadding;
    }

    private final void updateLayoutStateToFillEnd(int i, int i2) {
        this.mLayoutState.mAvailable = this.mOrientationHelper.getEndAfterPadding() - i2;
        LayoutState layoutState = this.mLayoutState;
        layoutState.mItemDirection = true != this.mShouldReverseLayout ? 1 : -1;
        layoutState.mCurrentPosition = i;
        layoutState.mLayoutDirection = 1;
        layoutState.mOffset = i2;
        layoutState.mScrollingOffset = Integer.MIN_VALUE;
    }

    private final void updateLayoutStateToFillStart(int i, int i2) {
        this.mLayoutState.mAvailable = i2 - this.mOrientationHelper.getStartAfterPadding();
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = i;
        layoutState.mItemDirection = true != this.mShouldReverseLayout ? -1 : 1;
        layoutState.mLayoutDirection = -1;
        layoutState.mOffset = i2;
        layoutState.mScrollingOffset = Integer.MIN_VALUE;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
    }

    protected void calculateExtraLayoutSpace(RecyclerView.State state, int[] iArr) {
        int i;
        int i2;
        if (state.mTargetPosition != -1) {
            i = this.mOrientationHelper.getTotalSpace();
        } else {
            i = 0;
        }
        int i3 = this.mLayoutState.mLayoutDirection;
        if (i3 == -1) {
            i2 = 0;
        } else {
            i2 = i;
        }
        if (i3 != -1) {
            i = 0;
        }
        iArr[0] = i;
        iArr[1] = i2;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean canScrollHorizontally() {
        if (this.mOrientation == 0) {
            return true;
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean canScrollVertically() {
        if (this.mOrientation == 1) {
            return true;
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void collectAdjacentPrefetchPositions$ar$class_merging(int i, int i2, RecyclerView.State state, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        int i3;
        if (1 == this.mOrientation) {
            i = i2;
        }
        if (getChildCount() != 0 && i != 0) {
            ensureLayoutState();
            if (i > 0) {
                i3 = 1;
            } else {
                i3 = -1;
            }
            updateLayoutState(i3, Math.abs(i), true, state);
            collectPrefetchPositionsForLayoutState$ar$class_merging(state, this.mLayoutState, layoutPrefetchRegistryImpl);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void collectInitialPrefetchPositions$ar$class_merging(int i, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        boolean z;
        int i2;
        SavedState savedState = this.mPendingSavedState;
        int i3 = -1;
        if (savedState != null && savedState.hasValidAnchor()) {
            SavedState savedState2 = this.mPendingSavedState;
            z = savedState2.mAnchorLayoutFromEnd;
            i2 = savedState2.mAnchorPosition;
        } else {
            resolveShouldLayoutReverse();
            z = this.mShouldReverseLayout;
            i2 = this.mPendingScrollPosition;
            if (i2 == -1) {
                i2 = z ? i - 1 : 0;
            }
        }
        if (true != z) {
            i3 = 1;
        }
        for (int i4 = 0; i4 < this.mInitialPrefetchItemCount && i2 >= 0 && i2 < i; i4++) {
            layoutPrefetchRegistryImpl.addPosition(i2, 0);
            i2 += i3;
        }
    }

    public void collectPrefetchPositionsForLayoutState$ar$class_merging(RecyclerView.State state, LayoutState layoutState, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        int i = layoutState.mCurrentPosition;
        if (i >= 0 && i < state.getItemCount()) {
            layoutPrefetchRegistryImpl.addPosition(i, Math.max(0, layoutState.mScrollingOffset));
        }
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
        if (getChildCount() == 0) {
            return null;
        }
        boolean z = false;
        int i2 = 1;
        if (i < getPosition$ar$ds(getChildAt(0))) {
            z = true;
        }
        if (z != this.mShouldReverseLayout) {
            i2 = -1;
        }
        float f = i2;
        if (this.mOrientation == 0) {
            return new PointF(f, 0.0f);
        }
        return new PointF(0.0f, f);
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0027 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0028 A[RETURN] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int convertFocusDirectionToLayoutDirection(int r6) {
        /*
            r5 = this;
            r0 = -1
            r1 = 1
            if (r6 == r1) goto L36
            r2 = 2
            if (r6 == r2) goto L29
            r2 = 17
            r3 = 0
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            if (r6 == r2) goto L22
            r2 = 33
            if (r6 == r2) goto L23
            r0 = 66
            if (r6 == r0) goto L21
            r0 = 130(0x82, float:1.82E-43)
            if (r6 == r0) goto L1b
            return r4
        L1b:
            int r6 = r5.mOrientation
            if (r6 != r1) goto L20
            return r1
        L20:
            return r4
        L21:
            r0 = r1
        L22:
            r1 = r3
        L23:
            int r6 = r5.mOrientation
            if (r6 != r1) goto L28
            return r0
        L28:
            return r4
        L29:
            int r6 = r5.mOrientation
            if (r6 != r1) goto L2e
            return r1
        L2e:
            boolean r6 = r5.isLayoutRTL()
            if (r6 == 0) goto L35
            return r0
        L35:
            return r1
        L36:
            int r6 = r5.mOrientation
            if (r6 != r1) goto L3b
            return r0
        L3b:
            boolean r6 = r5.isLayoutRTL()
            if (r6 == 0) goto L42
            return r1
        L42:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.LinearLayoutManager.convertFocusDirectionToLayoutDirection(int):int");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void ensureLayoutState() {
        if (this.mLayoutState == null) {
            this.mLayoutState = new LayoutState();
        }
    }

    final int fill(RecyclerView.Recycler recycler, LayoutState layoutState, RecyclerView.State state, boolean z) {
        int i = layoutState.mAvailable;
        int i2 = layoutState.mScrollingOffset;
        if (i2 != Integer.MIN_VALUE) {
            if (i < 0) {
                layoutState.mScrollingOffset = i2 + i;
            }
            recycleByLayoutState(recycler, layoutState);
        }
        int i3 = layoutState.mAvailable + layoutState.mExtraFillSpace;
        LayoutChunkResult layoutChunkResult = this.mLayoutChunkResult;
        while (true) {
            if ((!layoutState.mInfinite && i3 <= 0) || !layoutState.hasMore(state)) {
                break;
            }
            layoutChunkResult.mConsumed = 0;
            layoutChunkResult.mFinished = false;
            layoutChunkResult.mIgnoreConsumed = false;
            layoutChunkResult.mFocusable = false;
            layoutChunk(recycler, state, layoutState, layoutChunkResult);
            if (!layoutChunkResult.mFinished) {
                int i4 = layoutState.mOffset;
                int i5 = layoutChunkResult.mConsumed;
                layoutState.mOffset = i4 + (layoutState.mLayoutDirection * i5);
                if (!layoutChunkResult.mIgnoreConsumed || layoutState.mScrapList != null || !state.mInPreLayout) {
                    layoutState.mAvailable -= i5;
                    i3 -= i5;
                }
                int i6 = layoutState.mScrollingOffset;
                if (i6 != Integer.MIN_VALUE) {
                    int i7 = i6 + i5;
                    layoutState.mScrollingOffset = i7;
                    int i8 = layoutState.mAvailable;
                    if (i8 < 0) {
                        layoutState.mScrollingOffset = i7 + i8;
                    }
                    recycleByLayoutState(recycler, layoutState);
                }
                if (z && layoutChunkResult.mFocusable) {
                    break;
                }
            } else {
                break;
            }
        }
        return i - layoutState.mAvailable;
    }

    final View findFirstVisibleChildClosestToEnd$ar$ds(boolean z) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild$ar$ds(0, getChildCount(), z);
        }
        return findOneVisibleChild$ar$ds(getChildCount() - 1, -1, z);
    }

    final View findFirstVisibleChildClosestToStart$ar$ds(boolean z) {
        if (this.mShouldReverseLayout) {
            return findOneVisibleChild$ar$ds(getChildCount() - 1, -1, z);
        }
        return findOneVisibleChild$ar$ds(0, getChildCount(), z);
    }

    public final int findFirstVisibleItemPosition() {
        View findOneVisibleChild$ar$ds = findOneVisibleChild$ar$ds(0, getChildCount(), false);
        if (findOneVisibleChild$ar$ds == null) {
            return -1;
        }
        return getPosition$ar$ds(findOneVisibleChild$ar$ds);
    }

    public final int findLastVisibleItemPosition() {
        View findOneVisibleChild$ar$ds = findOneVisibleChild$ar$ds(getChildCount() - 1, -1, false);
        if (findOneVisibleChild$ar$ds == null) {
            return -1;
        }
        return getPosition$ar$ds(findOneVisibleChild$ar$ds);
    }

    final View findOnePartiallyOrCompletelyInvisibleChild(int i, int i2) {
        int i3;
        int i4;
        ensureLayoutState();
        if (i2 <= i && i2 >= i) {
            return getChildAt(i);
        }
        int decoratedStart = this.mOrientationHelper.getDecoratedStart(getChildAt(i));
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        if (decoratedStart < startAfterPadding) {
            i3 = 16388;
        } else {
            i3 = 4097;
        }
        if (decoratedStart < startAfterPadding) {
            i4 = 16644;
        } else {
            i4 = 4161;
        }
        if (this.mOrientation == 0) {
            return this.mHorizontalBoundCheck$ar$class_merging$ar$class_merging$ar$class_merging.findOneViewWithinBoundFlags(i, i2, i4, i3);
        }
        return this.mVerticalBoundCheck$ar$class_merging$ar$class_merging$ar$class_merging.findOneViewWithinBoundFlags(i, i2, i4, i3);
    }

    final View findOneVisibleChild$ar$ds(int i, int i2, boolean z) {
        int i3;
        ensureLayoutState();
        int i4 = this.mOrientation;
        if (true != z) {
            i3 = 320;
        } else {
            i3 = 24579;
        }
        if (i4 == 0) {
            return this.mHorizontalBoundCheck$ar$class_merging$ar$class_merging$ar$class_merging.findOneViewWithinBoundFlags(i, i2, i3, 320);
        }
        return this.mVerticalBoundCheck$ar$class_merging$ar$class_merging$ar$class_merging.findOneViewWithinBoundFlags(i, i2, i3, 320);
    }

    public View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z, boolean z2) {
        int i;
        int i2;
        int i3;
        boolean z3;
        boolean z4;
        ensureLayoutState();
        int childCount = getChildCount();
        if (z2) {
            i = -1;
            i3 = getChildCount() - 1;
            i2 = -1;
        } else {
            i = childCount;
            i2 = 1;
            i3 = 0;
        }
        int itemCount = state.getItemCount();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        View view = null;
        View view2 = null;
        View view3 = null;
        while (i3 != i) {
            View childAt = getChildAt(i3);
            int position$ar$ds = getPosition$ar$ds(childAt);
            int decoratedStart = this.mOrientationHelper.getDecoratedStart(childAt);
            int decoratedEnd = this.mOrientationHelper.getDecoratedEnd(childAt);
            if (position$ar$ds >= 0 && position$ar$ds < itemCount) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view3 == null) {
                        view3 = childAt;
                    }
                } else {
                    if (decoratedEnd <= startAfterPadding && decoratedStart < startAfterPadding) {
                        z3 = true;
                    } else {
                        z3 = false;
                    }
                    if (decoratedStart >= endAfterPadding && decoratedEnd > endAfterPadding) {
                        z4 = true;
                    } else {
                        z4 = false;
                    }
                    if (!z3 && !z4) {
                        return childAt;
                    }
                    if (z) {
                        if (!z4) {
                            if (view != null) {
                            }
                            view = childAt;
                        }
                        view2 = childAt;
                    } else {
                        if (!z3) {
                            if (view != null) {
                            }
                            view = childAt;
                        }
                        view2 = childAt;
                    }
                }
            }
            i3 += i2;
        }
        if (view == null) {
            if (view2 != null) {
                return view2;
            }
            return view3;
        }
        return view;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final View findViewByPosition(int i) {
        int childCount = getChildCount();
        if (childCount == 0) {
            return null;
        }
        int position$ar$ds = i - getPosition$ar$ds(getChildAt(0));
        if (position$ar$ds >= 0 && position$ar$ds < childCount) {
            View childAt = getChildAt(position$ar$ds);
            if (getPosition$ar$ds(childAt) == i) {
                return childAt;
            }
        }
        return super.findViewByPosition(i);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean isAutoMeasureEnabled() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final boolean isLayoutRTL() {
        if (getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean isLayoutReversed() {
        return this.mReverseLayout;
    }

    public void layoutChunk(RecyclerView.Recycler recycler, RecyclerView.State state, LayoutState layoutState, LayoutChunkResult layoutChunkResult) {
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        boolean z2;
        View next = layoutState.next(recycler);
        if (next == null) {
            layoutChunkResult.mFinished = true;
            return;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) next.getLayoutParams();
        if (layoutState.mScrapList == null) {
            boolean z3 = this.mShouldReverseLayout;
            if (layoutState.mLayoutDirection != -1) {
                z2 = false;
            } else {
                z2 = true;
            }
            if (z3 == z2) {
                addView(next);
            } else {
                addView(next, 0);
            }
        } else {
            boolean z4 = this.mShouldReverseLayout;
            if (layoutState.mLayoutDirection != -1) {
                z = false;
            } else {
                z = true;
            }
            if (z4 == z) {
                addDisappearingView(next);
            } else {
                addDisappearingView(next, 0);
            }
        }
        RecyclerView.LayoutParams layoutParams2 = (RecyclerView.LayoutParams) next.getLayoutParams();
        Rect itemDecorInsetsForChild = this.mRecyclerView.getItemDecorInsetsForChild(next);
        int i5 = itemDecorInsetsForChild.left + itemDecorInsetsForChild.right;
        int i6 = itemDecorInsetsForChild.top + itemDecorInsetsForChild.bottom;
        int childMeasureSpec = RecyclerView.LayoutManager.getChildMeasureSpec(this.mWidth, this.mWidthMode, getPaddingLeft() + getPaddingRight() + layoutParams2.leftMargin + layoutParams2.rightMargin + i5, layoutParams2.width, canScrollHorizontally());
        int childMeasureSpec2 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mHeight, this.mHeightMode, getPaddingTop() + getPaddingBottom() + layoutParams2.topMargin + layoutParams2.bottomMargin + i6, layoutParams2.height, canScrollVertically());
        if (shouldMeasureChild(next, childMeasureSpec, childMeasureSpec2, layoutParams2)) {
            next.measure(childMeasureSpec, childMeasureSpec2);
        }
        layoutChunkResult.mConsumed = this.mOrientationHelper.getDecoratedMeasurement(next);
        if (this.mOrientation == 1) {
            if (isLayoutRTL()) {
                i4 = this.mWidth - getPaddingRight();
                i = i4 - this.mOrientationHelper.getDecoratedMeasurementInOther(next);
            } else {
                i = getPaddingLeft();
                i4 = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + i;
            }
            if (layoutState.mLayoutDirection == -1) {
                i2 = layoutState.mOffset;
                i3 = i2 - layoutChunkResult.mConsumed;
            } else {
                i3 = layoutState.mOffset;
                i2 = layoutChunkResult.mConsumed + i3;
            }
        } else {
            int paddingTop = getPaddingTop();
            int decoratedMeasurementInOther = this.mOrientationHelper.getDecoratedMeasurementInOther(next) + paddingTop;
            if (layoutState.mLayoutDirection == -1) {
                int i7 = layoutState.mOffset;
                int i8 = i7 - layoutChunkResult.mConsumed;
                i4 = i7;
                i2 = decoratedMeasurementInOther;
                i = i8;
                i3 = paddingTop;
            } else {
                int i9 = layoutState.mOffset;
                int i10 = layoutChunkResult.mConsumed + i9;
                i = i9;
                i2 = decoratedMeasurementInOther;
                i3 = paddingTop;
                i4 = i10;
            }
        }
        layoutDecoratedWithMargins$ar$ds(next, i, i3, i4, i2);
        if (layoutParams.isItemRemoved() || layoutParams.isItemChanged()) {
            layoutChunkResult.mIgnoreConsumed = true;
        }
        layoutChunkResult.mFocusable = next.hasFocusable();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public View onFocusSearchFailed(View view, int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int convertFocusDirectionToLayoutDirection;
        View findLastPartiallyOrCompletelyInvisibleChild;
        View childClosestToEnd;
        View findFirstPartiallyOrCompletelyInvisibleChild;
        resolveShouldLayoutReverse();
        if (getChildCount() == 0 || (convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i)) == Integer.MIN_VALUE) {
            return null;
        }
        ensureLayoutState();
        updateLayoutState(convertFocusDirectionToLayoutDirection, (int) (this.mOrientationHelper.getTotalSpace() * 0.33333334f), false, state);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mScrollingOffset = Integer.MIN_VALUE;
        layoutState.mRecycle = false;
        fill(recycler, layoutState, state, true);
        if (convertFocusDirectionToLayoutDirection == -1) {
            if (this.mShouldReverseLayout) {
                findFirstPartiallyOrCompletelyInvisibleChild = findLastPartiallyOrCompletelyInvisibleChild();
            } else {
                findFirstPartiallyOrCompletelyInvisibleChild = findFirstPartiallyOrCompletelyInvisibleChild();
            }
            findLastPartiallyOrCompletelyInvisibleChild = findFirstPartiallyOrCompletelyInvisibleChild;
            convertFocusDirectionToLayoutDirection = -1;
        } else if (this.mShouldReverseLayout) {
            findLastPartiallyOrCompletelyInvisibleChild = findFirstPartiallyOrCompletelyInvisibleChild();
        } else {
            findLastPartiallyOrCompletelyInvisibleChild = findLastPartiallyOrCompletelyInvisibleChild();
        }
        if (convertFocusDirectionToLayoutDirection == -1) {
            childClosestToEnd = getChildClosestToStart();
        } else {
            childClosestToEnd = getChildClosestToEnd();
        }
        if (childClosestToEnd.hasFocusable()) {
            if (findLastPartiallyOrCompletelyInvisibleChild == null) {
                return null;
            }
            return childClosestToEnd;
        }
        return findLastPartiallyOrCompletelyInvisibleChild;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(findFirstVisibleItemPosition());
            accessibilityEvent.setToIndex(findLastVisibleItemPosition());
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
        RecyclerView.Adapter adapter = this.mRecyclerView.mAdapter;
        if (adapter != null && adapter.getItemCount() > 0) {
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_TO_POSITION);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        View findReferenceChild;
        boolean z;
        boolean z2;
        int i;
        boolean z3;
        boolean z4;
        int decoratedStart;
        int i2;
        int i3;
        int i4;
        boolean z5;
        int i5;
        int i6;
        int fixLayoutEndGap;
        int i7;
        View findViewByPosition;
        int decoratedStart2;
        int i8;
        int i9 = -1;
        if ((this.mPendingSavedState == null && this.mPendingScrollPosition == -1) || state.getItemCount() != 0) {
            SavedState savedState = this.mPendingSavedState;
            if (savedState != null && savedState.hasValidAnchor()) {
                this.mPendingScrollPosition = this.mPendingSavedState.mAnchorPosition;
            }
            ensureLayoutState();
            this.mLayoutState.mRecycle = false;
            resolveShouldLayoutReverse();
            View focusedChild = getFocusedChild();
            AnchorInfo anchorInfo = this.mAnchorInfo;
            if (anchorInfo.mValid && this.mPendingScrollPosition == -1 && this.mPendingSavedState == null) {
                if (focusedChild != null && (this.mOrientationHelper.getDecoratedStart(focusedChild) >= this.mOrientationHelper.getEndAfterPadding() || this.mOrientationHelper.getDecoratedEnd(focusedChild) <= this.mOrientationHelper.getStartAfterPadding())) {
                    this.mAnchorInfo.assignFromViewAndKeepVisibleRect(focusedChild, getPosition$ar$ds(focusedChild));
                }
            } else {
                anchorInfo.reset();
                AnchorInfo anchorInfo2 = this.mAnchorInfo;
                anchorInfo2.mLayoutFromEnd = this.mShouldReverseLayout ^ this.mStackFromEnd;
                if (!state.mInPreLayout && (i = this.mPendingScrollPosition) != -1) {
                    if (i >= 0 && i < state.getItemCount()) {
                        anchorInfo2.mPosition = this.mPendingScrollPosition;
                        SavedState savedState2 = this.mPendingSavedState;
                        if (savedState2 != null && savedState2.hasValidAnchor()) {
                            boolean z6 = this.mPendingSavedState.mAnchorLayoutFromEnd;
                            anchorInfo2.mLayoutFromEnd = z6;
                            if (z6) {
                                anchorInfo2.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingSavedState.mAnchorOffset;
                            } else {
                                anchorInfo2.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingSavedState.mAnchorOffset;
                            }
                        } else if (this.mPendingScrollPositionOffset == Integer.MIN_VALUE) {
                            View findViewByPosition2 = findViewByPosition(this.mPendingScrollPosition);
                            if (findViewByPosition2 != null) {
                                if (this.mOrientationHelper.getDecoratedMeasurement(findViewByPosition2) > this.mOrientationHelper.getTotalSpace()) {
                                    anchorInfo2.assignCoordinateFromPadding();
                                } else if (this.mOrientationHelper.getDecoratedStart(findViewByPosition2) - this.mOrientationHelper.getStartAfterPadding() < 0) {
                                    anchorInfo2.mCoordinate = this.mOrientationHelper.getStartAfterPadding();
                                    anchorInfo2.mLayoutFromEnd = false;
                                } else if (this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(findViewByPosition2) < 0) {
                                    anchorInfo2.mCoordinate = this.mOrientationHelper.getEndAfterPadding();
                                    anchorInfo2.mLayoutFromEnd = true;
                                } else {
                                    if (anchorInfo2.mLayoutFromEnd) {
                                        decoratedStart = this.mOrientationHelper.getDecoratedEnd(findViewByPosition2) + this.mOrientationHelper.getTotalSpaceChange();
                                    } else {
                                        decoratedStart = this.mOrientationHelper.getDecoratedStart(findViewByPosition2);
                                    }
                                    anchorInfo2.mCoordinate = decoratedStart;
                                }
                            } else {
                                if (getChildCount() > 0) {
                                    if (this.mPendingScrollPosition >= getPosition$ar$ds(getChildAt(0))) {
                                        z3 = false;
                                    } else {
                                        z3 = true;
                                    }
                                    if (z3 == this.mShouldReverseLayout) {
                                        z4 = true;
                                    } else {
                                        z4 = false;
                                    }
                                    anchorInfo2.mLayoutFromEnd = z4;
                                }
                                anchorInfo2.assignCoordinateFromPadding();
                            }
                        } else {
                            boolean z7 = this.mShouldReverseLayout;
                            anchorInfo2.mLayoutFromEnd = z7;
                            if (z7) {
                                anchorInfo2.mCoordinate = this.mOrientationHelper.getEndAfterPadding() - this.mPendingScrollPositionOffset;
                            } else {
                                anchorInfo2.mCoordinate = this.mOrientationHelper.getStartAfterPadding() + this.mPendingScrollPositionOffset;
                            }
                        }
                        this.mAnchorInfo.mValid = true;
                    } else {
                        this.mPendingScrollPosition = -1;
                        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
                    }
                }
                if (getChildCount() != 0) {
                    View focusedChild2 = getFocusedChild();
                    if (focusedChild2 != null) {
                        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) focusedChild2.getLayoutParams();
                        if (!layoutParams.isItemRemoved() && layoutParams.getViewLayoutPosition() >= 0 && layoutParams.getViewLayoutPosition() < state.getItemCount()) {
                            anchorInfo2.assignFromViewAndKeepVisibleRect(focusedChild2, getPosition$ar$ds(focusedChild2));
                            this.mAnchorInfo.mValid = true;
                        }
                    }
                    boolean z8 = this.mLastStackFromEnd;
                    boolean z9 = this.mStackFromEnd;
                    if (z8 == z9 && (findReferenceChild = findReferenceChild(recycler, state, anchorInfo2.mLayoutFromEnd, z9)) != null) {
                        anchorInfo2.assignFromView(findReferenceChild, getPosition$ar$ds(findReferenceChild));
                        if (!state.mInPreLayout && supportsPredictiveItemAnimations()) {
                            int decoratedStart3 = this.mOrientationHelper.getDecoratedStart(findReferenceChild);
                            int decoratedEnd = this.mOrientationHelper.getDecoratedEnd(findReferenceChild);
                            int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
                            int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
                            if (decoratedEnd <= startAfterPadding && decoratedStart3 < startAfterPadding) {
                                z = true;
                            } else {
                                z = false;
                            }
                            if (decoratedStart3 >= endAfterPadding && decoratedEnd > endAfterPadding) {
                                z2 = true;
                            } else {
                                z2 = false;
                            }
                            if (z || z2) {
                                if (true == anchorInfo2.mLayoutFromEnd) {
                                    startAfterPadding = endAfterPadding;
                                }
                                anchorInfo2.mCoordinate = startAfterPadding;
                            }
                        }
                        this.mAnchorInfo.mValid = true;
                    }
                }
                anchorInfo2.assignCoordinateFromPadding();
                anchorInfo2.mPosition = this.mStackFromEnd ? state.getItemCount() - 1 : 0;
                this.mAnchorInfo.mValid = true;
            }
            LayoutState layoutState = this.mLayoutState;
            if (layoutState.mLastScrollDelta >= 0) {
                i2 = 1;
            } else {
                i2 = -1;
            }
            layoutState.mLayoutDirection = i2;
            int[] iArr = this.mReusableIntPair;
            iArr[0] = 0;
            iArr[1] = 0;
            calculateExtraLayoutSpace(state, iArr);
            int max = Math.max(0, this.mReusableIntPair[0]) + this.mOrientationHelper.getStartAfterPadding();
            int max2 = Math.max(0, this.mReusableIntPair[1]) + this.mOrientationHelper.getEndPadding();
            if (state.mInPreLayout && (i7 = this.mPendingScrollPosition) != -1 && this.mPendingScrollPositionOffset != Integer.MIN_VALUE && (findViewByPosition = findViewByPosition(i7)) != null) {
                if (this.mShouldReverseLayout) {
                    i8 = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(findViewByPosition);
                    decoratedStart2 = this.mPendingScrollPositionOffset;
                } else {
                    decoratedStart2 = this.mOrientationHelper.getDecoratedStart(findViewByPosition) - this.mOrientationHelper.getStartAfterPadding();
                    i8 = this.mPendingScrollPositionOffset;
                }
                int i10 = i8 - decoratedStart2;
                if (i10 > 0) {
                    max += i10;
                } else {
                    max2 -= i10;
                }
            }
            AnchorInfo anchorInfo3 = this.mAnchorInfo;
            if (!anchorInfo3.mLayoutFromEnd ? true != this.mShouldReverseLayout : true == this.mShouldReverseLayout) {
                i9 = 1;
            }
            onAnchorReady(recycler, state, anchorInfo3, i9);
            detachAndScrapAttachedViews(recycler);
            this.mLayoutState.mInfinite = resolveIsInfinite();
            LayoutState layoutState2 = this.mLayoutState;
            layoutState2.mIsPreLayout = state.mInPreLayout;
            layoutState2.mNoRecycleSpace = 0;
            AnchorInfo anchorInfo4 = this.mAnchorInfo;
            if (anchorInfo4.mLayoutFromEnd) {
                updateLayoutStateToFillStart(anchorInfo4);
                LayoutState layoutState3 = this.mLayoutState;
                layoutState3.mExtraFillSpace = max;
                fill(recycler, layoutState3, state, false);
                LayoutState layoutState4 = this.mLayoutState;
                i4 = layoutState4.mOffset;
                int i11 = layoutState4.mCurrentPosition;
                int i12 = layoutState4.mAvailable;
                if (i12 > 0) {
                    max2 += i12;
                }
                updateLayoutStateToFillEnd(this.mAnchorInfo);
                LayoutState layoutState5 = this.mLayoutState;
                layoutState5.mExtraFillSpace = max2;
                layoutState5.mCurrentPosition += layoutState5.mItemDirection;
                fill(recycler, layoutState5, state, false);
                LayoutState layoutState6 = this.mLayoutState;
                i3 = layoutState6.mOffset;
                int i13 = layoutState6.mAvailable;
                if (i13 > 0) {
                    updateLayoutStateToFillStart(i11, i4);
                    LayoutState layoutState7 = this.mLayoutState;
                    layoutState7.mExtraFillSpace = i13;
                    fill(recycler, layoutState7, state, false);
                    i4 = this.mLayoutState.mOffset;
                }
            } else {
                updateLayoutStateToFillEnd(anchorInfo4);
                LayoutState layoutState8 = this.mLayoutState;
                layoutState8.mExtraFillSpace = max2;
                fill(recycler, layoutState8, state, false);
                LayoutState layoutState9 = this.mLayoutState;
                i3 = layoutState9.mOffset;
                int i14 = layoutState9.mCurrentPosition;
                int i15 = layoutState9.mAvailable;
                if (i15 > 0) {
                    max += i15;
                }
                updateLayoutStateToFillStart(this.mAnchorInfo);
                LayoutState layoutState10 = this.mLayoutState;
                layoutState10.mExtraFillSpace = max;
                layoutState10.mCurrentPosition += layoutState10.mItemDirection;
                fill(recycler, layoutState10, state, false);
                LayoutState layoutState11 = this.mLayoutState;
                i4 = layoutState11.mOffset;
                int i16 = layoutState11.mAvailable;
                if (i16 > 0) {
                    updateLayoutStateToFillEnd(i14, i3);
                    LayoutState layoutState12 = this.mLayoutState;
                    layoutState12.mExtraFillSpace = i16;
                    fill(recycler, layoutState12, state, false);
                    i3 = this.mLayoutState.mOffset;
                }
            }
            if (getChildCount() > 0) {
                if (this.mShouldReverseLayout ^ this.mStackFromEnd) {
                    int fixLayoutEndGap2 = fixLayoutEndGap(i3, recycler, state, true);
                    i5 = i4 + fixLayoutEndGap2;
                    i6 = i3 + fixLayoutEndGap2;
                    fixLayoutEndGap = fixLayoutStartGap(i5, recycler, state, false);
                } else {
                    int fixLayoutStartGap = fixLayoutStartGap(i4, recycler, state, true);
                    i5 = i4 + fixLayoutStartGap;
                    i6 = i3 + fixLayoutStartGap;
                    fixLayoutEndGap = fixLayoutEndGap(i6, recycler, state, false);
                }
                i4 = i5 + fixLayoutEndGap;
                i3 = i6 + fixLayoutEndGap;
            }
            if (state.mRunPredictiveAnimations && getChildCount() != 0 && !state.mInPreLayout && supportsPredictiveItemAnimations()) {
                List list = recycler.mUnmodifiableAttachedScrap;
                int size = list.size();
                int position$ar$ds = getPosition$ar$ds(getChildAt(0));
                int i17 = 0;
                int i18 = 0;
                for (int i19 = 0; i19 < size; i19++) {
                    RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) list.get(i19);
                    if (!viewHolder.isRemoved()) {
                        if (viewHolder.getLayoutPosition() >= position$ar$ds) {
                            z5 = false;
                        } else {
                            z5 = true;
                        }
                        if (z5 != this.mShouldReverseLayout) {
                            i17 += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
                        } else {
                            i18 += this.mOrientationHelper.getDecoratedMeasurement(viewHolder.itemView);
                        }
                    }
                }
                this.mLayoutState.mScrapList = list;
                if (i17 > 0) {
                    updateLayoutStateToFillStart(getPosition$ar$ds(getChildClosestToStart()), i4);
                    LayoutState layoutState13 = this.mLayoutState;
                    layoutState13.mExtraFillSpace = i17;
                    layoutState13.mAvailable = 0;
                    layoutState13.assignPositionFromScrapList();
                    fill(recycler, this.mLayoutState, state, false);
                }
                if (i18 > 0) {
                    updateLayoutStateToFillEnd(getPosition$ar$ds(getChildClosestToEnd()), i3);
                    LayoutState layoutState14 = this.mLayoutState;
                    layoutState14.mExtraFillSpace = i18;
                    layoutState14.mAvailable = 0;
                    layoutState14.assignPositionFromScrapList();
                    fill(recycler, this.mLayoutState, state, false);
                }
                this.mLayoutState.mScrapList = null;
            }
            if (!state.mInPreLayout) {
                OrientationHelper orientationHelper = this.mOrientationHelper;
                orientationHelper.mLastTotalSpace = orientationHelper.getTotalSpace();
            } else {
                this.mAnchorInfo.reset();
            }
            this.mLastStackFromEnd = this.mStackFromEnd;
            return;
        }
        removeAndRecycleAllViews(recycler);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void onLayoutCompleted(RecyclerView.State state) {
        this.mPendingSavedState = null;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mAnchorInfo.reset();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mPendingSavedState = savedState;
            if (this.mPendingScrollPosition != -1) {
                savedState.invalidateAnchor();
            }
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
            ensureLayoutState();
            boolean z = this.mLastStackFromEnd ^ this.mShouldReverseLayout;
            savedState2.mAnchorLayoutFromEnd = z;
            if (z) {
                View childClosestToEnd = getChildClosestToEnd();
                savedState2.mAnchorOffset = this.mOrientationHelper.getEndAfterPadding() - this.mOrientationHelper.getDecoratedEnd(childClosestToEnd);
                savedState2.mAnchorPosition = getPosition$ar$ds(childClosestToEnd);
            } else {
                View childClosestToStart = getChildClosestToStart();
                savedState2.mAnchorPosition = getPosition$ar$ds(childClosestToStart);
                savedState2.mAnchorOffset = this.mOrientationHelper.getDecoratedStart(childClosestToStart) - this.mOrientationHelper.getStartAfterPadding();
            }
        } else {
            savedState2.invalidateAnchor();
        }
        return savedState2;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean performAccessibilityAction(int i, Bundle bundle) {
        int min;
        if (super.performAccessibilityAction(i, bundle)) {
            return true;
        }
        if (i == 16908343 && bundle != null) {
            if (this.mOrientation == 1) {
                int i2 = bundle.getInt("android.view.accessibility.action.ARGUMENT_ROW_INT", -1);
                if (i2 < 0) {
                    return false;
                }
                RecyclerView recyclerView = this.mRecyclerView;
                min = Math.min(i2, getRowCountForAccessibility(recyclerView.mRecycler, recyclerView.mState) - 1);
            } else {
                int i3 = bundle.getInt("android.view.accessibility.action.ARGUMENT_COLUMN_INT", -1);
                if (i3 < 0) {
                    return false;
                }
                RecyclerView recyclerView2 = this.mRecyclerView;
                min = Math.min(i3, getColumnCountForAccessibility(recyclerView2.mRecycler, recyclerView2.mState) - 1);
            }
            if (min >= 0) {
                scrollToPositionWithOffset$ar$ds(min);
                return true;
            }
        }
        return false;
    }

    final boolean resolveIsInfinite() {
        if (this.mOrientationHelper.getMode() == 0 && this.mOrientationHelper.getEnd() == 0) {
            return true;
        }
        return false;
    }

    final int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int i2;
        if (getChildCount() != 0 && i != 0) {
            ensureLayoutState();
            this.mLayoutState.mRecycle = true;
            if (i > 0) {
                i2 = 1;
            } else {
                i2 = -1;
            }
            int abs = Math.abs(i);
            updateLayoutState(i2, abs, true, state);
            LayoutState layoutState = this.mLayoutState;
            int fill = layoutState.mScrollingOffset + fill(recycler, layoutState, state, false);
            if (fill >= 0) {
                if (abs > fill) {
                    i = i2 * fill;
                }
                this.mOrientationHelper.offsetChildren(-i);
                this.mLayoutState.mLastScrollDelta = i;
                return i;
            }
        }
        return 0;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return 0;
        }
        return scrollBy(i, recycler, state);
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

    public final void scrollToPositionWithOffset$ar$ds(int i) {
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = 0;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            savedState.invalidateAnchor();
        }
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return 0;
        }
        return scrollBy(i, recycler, state);
    }

    public final void setOrientation(int i) {
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "invalid orientation:"));
        }
        assertNotInLayoutOrScroll(null);
        if (i == this.mOrientation && this.mOrientationHelper != null) {
            return;
        }
        OrientationHelper createOrientationHelper = OrientationHelper.createOrientationHelper(this, i);
        this.mOrientationHelper = createOrientationHelper;
        this.mAnchorInfo.mOrientationHelper = createOrientationHelper;
        this.mOrientation = i;
        requestLayout();
    }

    public final void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (z == this.mReverseLayout) {
            return;
        }
        this.mReverseLayout = z;
        requestLayout();
    }

    public void setStackFromEnd(boolean z) {
        assertNotInLayoutOrScroll(null);
        if (this.mStackFromEnd == z) {
            return;
        }
        this.mStackFromEnd = z;
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean shouldMeasureTwice() {
        if (this.mHeightMode != 1073741824 && this.mWidthMode != 1073741824) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                ViewGroup.LayoutParams layoutParams = getChildAt(i).getLayoutParams();
                if (layoutParams.width < 0 && layoutParams.height < 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition$ar$ds(RecyclerView recyclerView, int i) {
        RecyclerView.SmoothScroller smoothScroller = new RecyclerView.SmoothScroller(recyclerView.getContext());
        smoothScroller.mTargetPosition = i;
        startSmoothScroll(smoothScroller);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public boolean supportsPredictiveItemAnimations() {
        if (this.mPendingSavedState == null && this.mLastStackFromEnd == this.mStackFromEnd) {
            return true;
        }
        return false;
    }

    public LinearLayoutManager(int i) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        setOrientation(i);
        setReverseLayout(false);
    }

    public LinearLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mOrientation = 1;
        this.mReverseLayout = false;
        this.mShouldReverseLayout = false;
        this.mStackFromEnd = false;
        this.mSmoothScrollbarEnabled = true;
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo = new AnchorInfo();
        this.mLayoutChunkResult = new LayoutChunkResult();
        this.mInitialPrefetchItemCount = 2;
        this.mReusableIntPair = new int[2];
        RecyclerView.LayoutManager.Properties properties = getProperties(context, attributeSet, i, i2);
        setOrientation(properties.orientation);
        setReverseLayout(properties.reverseLayout);
        setStackFromEnd(properties.stackFromEnd);
    }

    private final void updateLayoutStateToFillEnd(AnchorInfo anchorInfo) {
        updateLayoutStateToFillEnd(anchorInfo.mPosition, anchorInfo.mCoordinate);
    }

    private final void updateLayoutStateToFillStart(AnchorInfo anchorInfo) {
        updateLayoutStateToFillStart(anchorInfo.mPosition, anchorInfo.mCoordinate);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onDetachedFromWindow$ar$ds(RecyclerView recyclerView) {
    }

    public void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, AnchorInfo anchorInfo, int i) {
    }
}
