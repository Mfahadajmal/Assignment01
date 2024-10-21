package android.support.v7.widget;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.BackStackState;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView$SearchAutoComplete;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.lifecycle.LifecycleRegistry;
import androidx.preference.Preference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class StaggeredGridLayoutManager extends RecyclerView.LayoutManager implements RecyclerView.SmoothScroller.ScrollVectorProvider {
    private boolean mLastLayoutFromEnd;
    private boolean mLastLayoutRTL;
    private final LayoutState mLayoutState;
    private int mOrientation;
    private SavedState mPendingSavedState;
    private int[] mPrefetchDistances;
    OrientationHelper mPrimaryOrientation;
    private BitSet mRemainingSpans;
    OrientationHelper mSecondaryOrientation;
    private int mSizePerSpan;
    private int mSpanCount;
    Span[] mSpans;
    boolean mReverseLayout = false;
    boolean mShouldReverseLayout = false;
    int mPendingScrollPosition = -1;
    int mPendingScrollPositionOffset = Integer.MIN_VALUE;
    LifecycleRegistry.ObserverWithState mLazySpanLookup$ar$class_merging = new LifecycleRegistry.ObserverWithState();
    private int mGapStrategy = 2;
    private final Rect mTmpRect = new Rect();
    private final AnchorInfo mAnchorInfo = new AnchorInfo();
    private boolean mLaidOutInvalidFullSpan = false;
    private boolean mSmoothScrollbarEnabled = true;
    private final Runnable mCheckForGapsRunnable = new SearchView$SearchAutoComplete.AnonymousClass1(this, 2);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AnchorInfo {
        boolean mInvalidateOffsets;
        boolean mLayoutFromEnd;
        int mOffset;
        int mPosition;
        int[] mSpanReferenceLines;
        boolean mValid;

        public AnchorInfo() {
            reset();
        }

        final void reset() {
            this.mPosition = -1;
            this.mOffset = Integer.MIN_VALUE;
            this.mLayoutFromEnd = false;
            this.mInvalidateOffsets = false;
            this.mValid = false;
            int[] iArr = this.mSpanReferenceLines;
            if (iArr != null) {
                Arrays.fill(iArr, -1);
            }
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutParams extends RecyclerView.LayoutParams {
        boolean mFullSpan;
        Span mSpan;

        public LayoutParams(int i, int i2) {
            super(i, i2);
        }

        public final int getSpanIndex() {
            Span span = this.mSpan;
            if (span == null) {
                return -1;
            }
            return span.mIndex;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState implements Parcelable {
        public static final Parcelable.Creator<SavedState> CREATOR = new BackStackState.AnonymousClass1(9);
        boolean mAnchorLayoutFromEnd;
        int mAnchorPosition;
        List mFullSpanItems;
        boolean mLastLayoutRTL;
        boolean mReverseLayout;
        int[] mSpanLookup;
        int mSpanLookupSize;
        int[] mSpanOffsets;
        int mSpanOffsetsSize;
        int mVisibleAnchorPosition;

        public SavedState() {
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        final void invalidateAnchorPositionInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mAnchorPosition = -1;
            this.mVisibleAnchorPosition = -1;
        }

        final void invalidateSpanInfo() {
            this.mSpanOffsets = null;
            this.mSpanOffsetsSize = 0;
            this.mSpanLookupSize = 0;
            this.mSpanLookup = null;
            this.mFullSpanItems = null;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mAnchorPosition);
            parcel.writeInt(this.mVisibleAnchorPosition);
            parcel.writeInt(this.mSpanOffsetsSize);
            if (this.mSpanOffsetsSize > 0) {
                parcel.writeIntArray(this.mSpanOffsets);
            }
            parcel.writeInt(this.mSpanLookupSize);
            if (this.mSpanLookupSize > 0) {
                parcel.writeIntArray(this.mSpanLookup);
            }
            parcel.writeInt(this.mReverseLayout ? 1 : 0);
            parcel.writeInt(this.mAnchorLayoutFromEnd ? 1 : 0);
            parcel.writeInt(this.mLastLayoutRTL ? 1 : 0);
            parcel.writeList(this.mFullSpanItems);
        }

        public SavedState(SavedState savedState) {
            this.mSpanOffsetsSize = savedState.mSpanOffsetsSize;
            this.mAnchorPosition = savedState.mAnchorPosition;
            this.mVisibleAnchorPosition = savedState.mVisibleAnchorPosition;
            this.mSpanOffsets = savedState.mSpanOffsets;
            this.mSpanLookupSize = savedState.mSpanLookupSize;
            this.mSpanLookup = savedState.mSpanLookup;
            this.mReverseLayout = savedState.mReverseLayout;
            this.mAnchorLayoutFromEnd = savedState.mAnchorLayoutFromEnd;
            this.mLastLayoutRTL = savedState.mLastLayoutRTL;
            this.mFullSpanItems = savedState.mFullSpanItems;
        }

        public SavedState(Parcel parcel) {
            this.mAnchorPosition = parcel.readInt();
            this.mVisibleAnchorPosition = parcel.readInt();
            int readInt = parcel.readInt();
            this.mSpanOffsetsSize = readInt;
            if (readInt > 0) {
                int[] iArr = new int[readInt];
                this.mSpanOffsets = iArr;
                parcel.readIntArray(iArr);
            }
            int readInt2 = parcel.readInt();
            this.mSpanLookupSize = readInt2;
            if (readInt2 > 0) {
                int[] iArr2 = new int[readInt2];
                this.mSpanLookup = iArr2;
                parcel.readIntArray(iArr2);
            }
            this.mReverseLayout = parcel.readInt() == 1;
            this.mAnchorLayoutFromEnd = parcel.readInt() == 1;
            this.mLastLayoutRTL = parcel.readInt() == 1;
            this.mFullSpanItems = parcel.readArrayList(StaggeredGridLayoutManager$LazySpanLookup$FullSpanItem.class.getClassLoader());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Span {
        final int mIndex;
        final ArrayList mViews = new ArrayList();
        int mCachedStart = Integer.MIN_VALUE;
        int mCachedEnd = Integer.MIN_VALUE;
        int mDeletedSize = 0;

        public Span(int i) {
            this.mIndex = i;
        }

        static final LayoutParams getLayoutParams$ar$ds(View view) {
            return (LayoutParams) view.getLayoutParams();
        }

        final void calculateCachedEnd() {
            View view = (View) this.mViews.get(r0.size() - 1);
            LayoutParams layoutParams$ar$ds = getLayoutParams$ar$ds(view);
            this.mCachedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
            boolean z = layoutParams$ar$ds.mFullSpan;
        }

        final void calculateCachedStart() {
            View view = (View) this.mViews.get(0);
            LayoutParams layoutParams$ar$ds = getLayoutParams$ar$ds(view);
            this.mCachedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
            boolean z = layoutParams$ar$ds.mFullSpan;
        }

        final void clear() {
            this.mViews.clear();
            this.mCachedStart = Integer.MIN_VALUE;
            this.mCachedEnd = Integer.MIN_VALUE;
            this.mDeletedSize = 0;
        }

        public final int findFirstPartiallyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOnePartiallyVisibleChild$ar$ds(this.mViews.size() - 1, -1);
            }
            return findOnePartiallyVisibleChild$ar$ds(0, this.mViews.size());
        }

        public final int findLastPartiallyVisibleItemPosition() {
            if (StaggeredGridLayoutManager.this.mReverseLayout) {
                return findOnePartiallyVisibleChild$ar$ds(0, this.mViews.size());
            }
            return findOnePartiallyVisibleChild$ar$ds(this.mViews.size() - 1, -1);
        }

        final int findOnePartiallyVisibleChild$ar$ds(int i, int i2) {
            View view;
            boolean z;
            int startAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getStartAfterPadding();
            int endAfterPadding = StaggeredGridLayoutManager.this.mPrimaryOrientation.getEndAfterPadding();
            int i3 = i;
            while (true) {
                int i4 = -1;
                if (i3 == i2) {
                    return -1;
                }
                view = (View) this.mViews.get(i3);
                int decoratedStart = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedStart(view);
                int decoratedEnd = StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedEnd(view);
                boolean z2 = false;
                if (decoratedStart <= endAfterPadding) {
                    z = true;
                } else {
                    z = false;
                }
                if (decoratedEnd >= startAfterPadding) {
                    z2 = true;
                }
                if (!z || !z2 || (decoratedStart >= startAfterPadding && decoratedEnd <= endAfterPadding)) {
                    if (i2 > i) {
                        i4 = 1;
                    }
                    i3 += i4;
                }
            }
            return StaggeredGridLayoutManager.getPosition$ar$ds(view);
        }

        final int getEndLine() {
            int i = this.mCachedEnd;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        public final View getFocusableViewAfter(int i, int i2) {
            View view = null;
            if (i2 == -1) {
                int size = this.mViews.size();
                int i3 = 0;
                while (i3 < size) {
                    View view2 = (View) this.mViews.get(i3);
                    if ((StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.getPosition$ar$ds(view2) <= i) || ((!StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.getPosition$ar$ds(view2) >= i) || !view2.hasFocusable())) {
                        break;
                    }
                    i3++;
                    view = view2;
                }
            } else {
                int size2 = this.mViews.size() - 1;
                while (size2 >= 0) {
                    View view3 = (View) this.mViews.get(size2);
                    if ((StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.getPosition$ar$ds(view3) >= i) || ((!StaggeredGridLayoutManager.this.mReverseLayout && StaggeredGridLayoutManager.getPosition$ar$ds(view3) <= i) || !view3.hasFocusable())) {
                        break;
                    }
                    size2--;
                    view = view3;
                }
            }
            return view;
        }

        final int getStartLine() {
            int i = this.mCachedStart;
            if (i != Integer.MIN_VALUE) {
                return i;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }

        final void onOffset(int i) {
            int i2 = this.mCachedStart;
            if (i2 != Integer.MIN_VALUE) {
                this.mCachedStart = i2 + i;
            }
            int i3 = this.mCachedEnd;
            if (i3 != Integer.MIN_VALUE) {
                this.mCachedEnd = i3 + i;
            }
        }

        final void setLine(int i) {
            this.mCachedStart = i;
            this.mCachedEnd = i;
        }

        final int getEndLine(int i) {
            int i2 = this.mCachedEnd;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.mViews.size() == 0) {
                return i;
            }
            calculateCachedEnd();
            return this.mCachedEnd;
        }

        final int getStartLine(int i) {
            int i2 = this.mCachedStart;
            if (i2 != Integer.MIN_VALUE) {
                return i2;
            }
            if (this.mViews.size() == 0) {
                return i;
            }
            calculateCachedStart();
            return this.mCachedStart;
        }
    }

    public StaggeredGridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mSpanCount = -1;
        RecyclerView.LayoutManager.Properties properties = getProperties(context, attributeSet, i, i2);
        int i3 = properties.orientation;
        if (i3 != 0 && i3 != 1) {
            throw new IllegalArgumentException("invalid orientation.");
        }
        assertNotInLayoutOrScroll(null);
        if (i3 != this.mOrientation) {
            this.mOrientation = i3;
            OrientationHelper orientationHelper = this.mPrimaryOrientation;
            this.mPrimaryOrientation = this.mSecondaryOrientation;
            this.mSecondaryOrientation = orientationHelper;
            requestLayout();
        }
        int i4 = properties.spanCount;
        assertNotInLayoutOrScroll(null);
        if (i4 != this.mSpanCount) {
            this.mLazySpanLookup$ar$class_merging.clear();
            requestLayout();
            this.mSpanCount = i4;
            this.mRemainingSpans = new BitSet(i4);
            this.mSpans = new Span[this.mSpanCount];
            for (int i5 = 0; i5 < this.mSpanCount; i5++) {
                this.mSpans[i5] = new Span(i5);
            }
            requestLayout();
        }
        setReverseLayout(properties.reverseLayout);
        this.mLayoutState = new LayoutState();
        this.mPrimaryOrientation = OrientationHelper.createOrientationHelper(this, this.mOrientation);
        this.mSecondaryOrientation = OrientationHelper.createOrientationHelper(this, 1 - this.mOrientation);
    }

    private final int calculateScrollDirectionForPosition(int i) {
        boolean z;
        if (getChildCount() == 0) {
            if (!this.mShouldReverseLayout) {
                return -1;
            }
            return 1;
        }
        if (i >= getFirstChildPosition()) {
            z = false;
        } else {
            z = true;
        }
        if (z != this.mShouldReverseLayout) {
            return -1;
        }
        return 1;
    }

    private final int computeScrollExtent(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return AppCompatTextClassifierHelper$Api26Impl.computeScrollExtent(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    private final int computeScrollOffset(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return AppCompatTextClassifierHelper$Api26Impl.computeScrollOffset(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled, this.mShouldReverseLayout);
    }

    private final int computeScrollRange(RecyclerView.State state) {
        if (getChildCount() == 0) {
            return 0;
        }
        return AppCompatTextClassifierHelper$Api26Impl.computeScrollRange(state, this.mPrimaryOrientation, findFirstVisibleItemClosestToStart(!this.mSmoothScrollbarEnabled), findFirstVisibleItemClosestToEnd(!this.mSmoothScrollbarEnabled), this, this.mSmoothScrollbarEnabled);
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x024c  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x009c  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x019d  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0248  */
    /* JADX WARN: Type inference failed for: r5v29 */
    /* JADX WARN: Type inference failed for: r5v3 */
    /* JADX WARN: Type inference failed for: r5v4, types: [int, boolean] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final int fill(android.support.v7.widget.RecyclerView.Recycler r20, android.support.v7.widget.LayoutState r21, android.support.v7.widget.RecyclerView.State r22) {
        /*
            Method dump skipped, instructions count: 696
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.fill(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.LayoutState, android.support.v7.widget.RecyclerView$State):int");
    }

    private final void fixEndGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int endAfterPadding;
        int i;
        int maxEnd = getMaxEnd(Integer.MIN_VALUE);
        if (maxEnd != Integer.MIN_VALUE && (endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding() - maxEnd) > 0) {
            int i2 = -scrollBy(-endAfterPadding, recycler, state);
            if (z && (i = endAfterPadding - i2) > 0) {
                this.mPrimaryOrientation.offsetChildren(i);
            }
        }
    }

    private final void fixStartGap(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z) {
        int startAfterPadding;
        int minStart = getMinStart(Preference.DEFAULT_ORDER);
        if (minStart != Integer.MAX_VALUE && (startAfterPadding = minStart - this.mPrimaryOrientation.getStartAfterPadding()) > 0) {
            int scrollBy = startAfterPadding - scrollBy(startAfterPadding, recycler, state);
            if (z && scrollBy > 0) {
                this.mPrimaryOrientation.offsetChildren(-scrollBy);
            }
        }
    }

    private final int getMaxEnd(int i) {
        int endLine = this.mSpans[0].getEndLine(i);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int endLine2 = this.mSpans[i2].getEndLine(i);
            if (endLine2 > endLine) {
                endLine = endLine2;
            }
        }
        return endLine;
    }

    private final int getMinStart(int i) {
        int startLine = this.mSpans[0].getStartLine(i);
        for (int i2 = 1; i2 < this.mSpanCount; i2++) {
            int startLine2 = this.mSpans[i2].getStartLine(i);
            if (startLine2 < startLine) {
                startLine = startLine2;
            }
        }
        return startLine;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:25:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x003c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void handleUpdate(int r7, int r8, int r9) {
        /*
            r6 = this;
            boolean r0 = r6.mShouldReverseLayout
            if (r0 == 0) goto L9
            int r0 = r6.getLastChildPosition()
            goto Ld
        L9:
            int r0 = r6.getFirstChildPosition()
        Ld:
            r1 = 8
            if (r9 != r1) goto L1a
            if (r7 >= r8) goto L16
            int r2 = r8 + 1
            goto L1c
        L16:
            int r2 = r7 + 1
            r3 = r8
            goto L1d
        L1a:
            int r2 = r7 + r8
        L1c:
            r3 = r7
        L1d:
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r4 = r6.mLazySpanLookup$ar$class_merging
            r4.invalidateAfter(r3)
            r4 = 1
            if (r9 == r4) goto L3c
            r5 = 2
            if (r9 == r5) goto L36
            if (r9 == r1) goto L2b
            goto L41
        L2b:
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r9 = r6.mLazySpanLookup$ar$class_merging
            r9.offsetForRemoval(r7, r4)
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r7 = r6.mLazySpanLookup$ar$class_merging
            r7.offsetForAddition(r8, r4)
            goto L41
        L36:
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r9 = r6.mLazySpanLookup$ar$class_merging
            r9.offsetForRemoval(r7, r8)
            goto L41
        L3c:
            androidx.lifecycle.LifecycleRegistry$ObserverWithState r9 = r6.mLazySpanLookup$ar$class_merging
            r9.offsetForAddition(r7, r8)
        L41:
            if (r2 > r0) goto L44
            goto L56
        L44:
            boolean r7 = r6.mShouldReverseLayout
            if (r7 == 0) goto L4d
            int r7 = r6.getFirstChildPosition()
            goto L51
        L4d:
            int r7 = r6.getLastChildPosition()
        L51:
            if (r3 > r7) goto L56
            r6.requestLayout()
        L56:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.handleUpdate(int, int, int):void");
    }

    private final void measureChildWithDecorationsAndMargin$ar$ds(View view, int i, int i2) {
        calculateItemDecorationsForChild(view, this.mTmpRect);
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        int updateSpecWithExtra$ar$ds = updateSpecWithExtra$ar$ds(i, layoutParams.leftMargin + this.mTmpRect.left, layoutParams.rightMargin + this.mTmpRect.right);
        int updateSpecWithExtra$ar$ds2 = updateSpecWithExtra$ar$ds(i2, layoutParams.topMargin + this.mTmpRect.top, layoutParams.bottomMargin + this.mTmpRect.bottom);
        if (shouldMeasureChild(view, updateSpecWithExtra$ar$ds, updateSpecWithExtra$ar$ds2, layoutParams)) {
            view.measure(updateSpecWithExtra$ar$ds, updateSpecWithExtra$ar$ds2);
        }
    }

    private final boolean preferLastSpan(int i) {
        boolean z;
        boolean z2;
        int i2 = this.mOrientation;
        if (i != -1) {
            z = false;
        } else {
            z = true;
        }
        if (i2 == 0) {
            if (z == this.mShouldReverseLayout) {
                return false;
            }
            return true;
        }
        if (z != this.mShouldReverseLayout) {
            z2 = false;
        } else {
            z2 = true;
        }
        if (z2 != isLayoutRTL()) {
            return false;
        }
        return true;
    }

    private final void recycle(RecyclerView.Recycler recycler, LayoutState layoutState) {
        int min;
        int min2;
        if (layoutState.mRecycle && !layoutState.mInfinite) {
            if (layoutState.mAvailable == 0) {
                if (layoutState.mLayoutDirection == -1) {
                    recycleFromEnd(recycler, layoutState.mEndLine);
                    return;
                } else {
                    recycleFromStart(recycler, layoutState.mStartLine);
                    return;
                }
            }
            int i = 1;
            if (layoutState.mLayoutDirection == -1) {
                int i2 = layoutState.mStartLine;
                int startLine = this.mSpans[0].getStartLine(i2);
                while (i < this.mSpanCount) {
                    int startLine2 = this.mSpans[i].getStartLine(i2);
                    if (startLine2 > startLine) {
                        startLine = startLine2;
                    }
                    i++;
                }
                int i3 = i2 - startLine;
                if (i3 < 0) {
                    min2 = layoutState.mEndLine;
                } else {
                    min2 = layoutState.mEndLine - Math.min(i3, layoutState.mAvailable);
                }
                recycleFromEnd(recycler, min2);
                return;
            }
            int i4 = layoutState.mEndLine;
            int endLine = this.mSpans[0].getEndLine(i4);
            while (i < this.mSpanCount) {
                int endLine2 = this.mSpans[i].getEndLine(i4);
                if (endLine2 < endLine) {
                    endLine = endLine2;
                }
                i++;
            }
            int i5 = endLine - layoutState.mEndLine;
            if (i5 < 0) {
                min = layoutState.mStartLine;
            } else {
                min = Math.min(i5, layoutState.mAvailable) + layoutState.mStartLine;
            }
            recycleFromStart(recycler, min);
        }
    }

    private final void recycleFromEnd(RecyclerView.Recycler recycler, int i) {
        int childCount = getChildCount();
        while (true) {
            childCount--;
            if (childCount >= 0) {
                View childAt = getChildAt(childCount);
                if (this.mPrimaryOrientation.getDecoratedStart(childAt) >= i && this.mPrimaryOrientation.getTransformedStartWithDecoration(childAt) >= i) {
                    LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                    boolean z = layoutParams.mFullSpan;
                    if (layoutParams.mSpan.mViews.size() != 1) {
                        Span span = layoutParams.mSpan;
                        int size = span.mViews.size();
                        View view = (View) span.mViews.remove(size - 1);
                        LayoutParams layoutParams$ar$ds = Span.getLayoutParams$ar$ds(view);
                        layoutParams$ar$ds.mSpan = null;
                        if (layoutParams$ar$ds.isItemRemoved() || layoutParams$ar$ds.isItemChanged()) {
                            span.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
                        }
                        if (size == 1) {
                            span.mCachedStart = Integer.MIN_VALUE;
                        }
                        span.mCachedEnd = Integer.MIN_VALUE;
                        removeAndRecycleView(childAt, recycler);
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    private final void recycleFromStart(RecyclerView.Recycler recycler, int i) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) <= i && this.mPrimaryOrientation.getTransformedEndWithDecoration(childAt) <= i) {
                LayoutParams layoutParams = (LayoutParams) childAt.getLayoutParams();
                boolean z = layoutParams.mFullSpan;
                if (layoutParams.mSpan.mViews.size() != 1) {
                    Span span = layoutParams.mSpan;
                    View view = (View) span.mViews.remove(0);
                    LayoutParams layoutParams$ar$ds = Span.getLayoutParams$ar$ds(view);
                    layoutParams$ar$ds.mSpan = null;
                    if (span.mViews.size() == 0) {
                        span.mCachedEnd = Integer.MIN_VALUE;
                    }
                    if (layoutParams$ar$ds.isItemRemoved() || layoutParams$ar$ds.isItemChanged()) {
                        span.mDeletedSize -= StaggeredGridLayoutManager.this.mPrimaryOrientation.getDecoratedMeasurement(view);
                    }
                    span.mCachedStart = Integer.MIN_VALUE;
                    removeAndRecycleView(childAt, recycler);
                } else {
                    return;
                }
            } else {
                return;
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

    private final void setLayoutStateDirection(int i) {
        boolean z;
        LayoutState layoutState = this.mLayoutState;
        layoutState.mLayoutDirection = i;
        boolean z2 = this.mShouldReverseLayout;
        int i2 = 1;
        if (i != -1) {
            z = false;
        } else {
            z = true;
        }
        if (z2 != z) {
            i2 = -1;
        }
        layoutState.mItemDirection = i2;
    }

    private final void updateLayoutState(int i, RecyclerView.State state) {
        int i2;
        int i3;
        int i4;
        boolean z;
        LayoutState layoutState = this.mLayoutState;
        boolean z2 = false;
        layoutState.mAvailable = 0;
        layoutState.mCurrentPosition = i;
        if (isSmoothScrolling() && (i4 = state.mTargetPosition) != -1) {
            boolean z3 = this.mShouldReverseLayout;
            if (i4 >= i) {
                z = false;
            } else {
                z = true;
            }
            if (z3 == z) {
                i2 = this.mPrimaryOrientation.getTotalSpace();
                i3 = 0;
            } else {
                i3 = this.mPrimaryOrientation.getTotalSpace();
                i2 = 0;
            }
        } else {
            i2 = 0;
            i3 = 0;
        }
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null && recyclerView.mClipToPadding) {
            this.mLayoutState.mStartLine = this.mPrimaryOrientation.getStartAfterPadding() - i3;
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEndAfterPadding() + i2;
        } else {
            this.mLayoutState.mEndLine = this.mPrimaryOrientation.getEnd() + i2;
            this.mLayoutState.mStartLine = -i3;
        }
        LayoutState layoutState2 = this.mLayoutState;
        layoutState2.mStopInFocusable = false;
        layoutState2.mRecycle = true;
        if (this.mPrimaryOrientation.getMode() == 0 && this.mPrimaryOrientation.getEnd() == 0) {
            z2 = true;
        }
        layoutState2.mInfinite = z2;
    }

    private final void updateRemainingSpans(Span span, int i, int i2) {
        int i3 = span.mDeletedSize;
        if (i == -1) {
            if (span.getStartLine() + i3 <= i2) {
                this.mRemainingSpans.set(span.mIndex, false);
            }
        } else if (span.getEndLine() - i3 >= i2) {
            this.mRemainingSpans.set(span.mIndex, false);
        }
    }

    private static final int updateSpecWithExtra$ar$ds(int i, int i2, int i3) {
        if (i2 == 0) {
            if (i3 != 0) {
                i2 = 0;
            }
            return i;
        }
        int mode = View.MeasureSpec.getMode(i);
        if (mode != Integer.MIN_VALUE) {
            if (mode == 1073741824) {
                mode = 1073741824;
            }
            return i;
        }
        return View.MeasureSpec.makeMeasureSpec(Math.max(0, (View.MeasureSpec.getSize(i) - i2) - i3), mode);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void assertNotInLayoutOrScroll(String str) {
        if (this.mPendingSavedState == null) {
            super.assertNotInLayoutOrScroll(str);
        }
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

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean checkForGaps() {
        int firstChildPosition;
        if (getChildCount() != 0 && this.mGapStrategy != 0 && this.mIsAttachedToWindow) {
            if (this.mShouldReverseLayout) {
                firstChildPosition = getLastChildPosition();
                getFirstChildPosition();
            } else {
                firstChildPosition = getFirstChildPosition();
                getLastChildPosition();
            }
            if (firstChildPosition == 0 && hasGapsToFix() != null) {
                this.mLazySpanLookup$ar$class_merging.clear();
                requestSimpleAnimationsInNextLayout();
                requestLayout();
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x001a, code lost:
    
        if (r5.length < r4.mSpanCount) goto L13;
     */
    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void collectAdjacentPrefetchPositions$ar$class_merging(int r5, int r6, android.support.v7.widget.RecyclerView.State r7, android.support.v7.widget.GapWorker.LayoutPrefetchRegistryImpl r8) {
        /*
            r4 = this;
            r0 = 1
            int r1 = r4.mOrientation
            if (r0 != r1) goto L6
            r5 = r6
        L6:
            int r6 = r4.getChildCount()
            if (r6 == 0) goto L7a
            if (r5 != 0) goto Lf
            goto L7a
        Lf:
            r4.prepareLayoutStateForDelta(r5, r7)
            int[] r5 = r4.mPrefetchDistances
            r6 = 0
            if (r5 == 0) goto L1c
            int r0 = r4.mSpanCount
            int r5 = r5.length
            if (r5 >= r0) goto L22
        L1c:
            int r5 = r4.mSpanCount
            int[] r5 = new int[r5]
            r4.mPrefetchDistances = r5
        L22:
            r5 = r6
            r0 = r5
        L24:
            int r1 = r4.mSpanCount
            if (r5 >= r1) goto L54
            android.support.v7.widget.LayoutState r1 = r4.mLayoutState
            int r2 = r1.mItemDirection
            r3 = -1
            if (r2 != r3) goto L3a
            int r1 = r1.mStartLine
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r2 = r4.mSpans
            r2 = r2[r5]
            int r2 = r2.getStartLine(r1)
            goto L48
        L3a:
            android.support.v7.widget.StaggeredGridLayoutManager$Span[] r2 = r4.mSpans
            r2 = r2[r5]
            int r1 = r1.mEndLine
            int r1 = r2.getEndLine(r1)
            android.support.v7.widget.LayoutState r2 = r4.mLayoutState
            int r2 = r2.mEndLine
        L48:
            int r1 = r1 - r2
            if (r1 < 0) goto L51
            int[] r2 = r4.mPrefetchDistances
            r2[r0] = r1
            int r0 = r0 + 1
        L51:
            int r5 = r5 + 1
            goto L24
        L54:
            int[] r5 = r4.mPrefetchDistances
            java.util.Arrays.sort(r5, r6, r0)
        L59:
            if (r6 >= r0) goto L7a
            android.support.v7.widget.LayoutState r5 = r4.mLayoutState
            boolean r5 = r5.hasMore(r7)
            if (r5 == 0) goto L7a
            android.support.v7.widget.LayoutState r5 = r4.mLayoutState
            int r5 = r5.mCurrentPosition
            int[] r1 = r4.mPrefetchDistances
            r1 = r1[r6]
            r8.addPosition(r5, r1)
            android.support.v7.widget.LayoutState r5 = r4.mLayoutState
            int r1 = r5.mCurrentPosition
            int r2 = r5.mItemDirection
            int r1 = r1 + r2
            r5.mCurrentPosition = r1
            int r6 = r6 + 1
            goto L59
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.collectAdjacentPrefetchPositions$ar$class_merging(int, int, android.support.v7.widget.RecyclerView$State, android.support.v7.widget.GapWorker$LayoutPrefetchRegistryImpl):void");
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
        int calculateScrollDirectionForPosition = calculateScrollDirectionForPosition(i);
        PointF pointF = new PointF();
        if (calculateScrollDirectionForPosition == 0) {
            return null;
        }
        float f = calculateScrollDirectionForPosition;
        if (this.mOrientation == 0) {
            pointF.x = f;
            pointF.y = 0.0f;
        } else {
            pointF.x = 0.0f;
            pointF.y = f;
        }
        return pointF;
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

    final View findFirstVisibleItemClosestToEnd(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        View view = null;
        for (int childCount = getChildCount() - 1; childCount >= 0; childCount--) {
            View childAt = getChildAt(childCount);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            int decoratedEnd = this.mPrimaryOrientation.getDecoratedEnd(childAt);
            if (decoratedEnd > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedEnd > endAfterPadding && z) {
                    if (view == null) {
                        view = childAt;
                    }
                } else {
                    return childAt;
                }
            }
        }
        return view;
    }

    final View findFirstVisibleItemClosestToStart(boolean z) {
        int startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
        int endAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
        int childCount = getChildCount();
        View view = null;
        for (int i = 0; i < childCount; i++) {
            View childAt = getChildAt(i);
            int decoratedStart = this.mPrimaryOrientation.getDecoratedStart(childAt);
            if (this.mPrimaryOrientation.getDecoratedEnd(childAt) > startAfterPadding && decoratedStart < endAfterPadding) {
                if (decoratedStart < startAfterPadding && z) {
                    if (view == null) {
                        view = childAt;
                    }
                } else {
                    return childAt;
                }
            }
        }
        return view;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -1);
        }
        return new LayoutParams(-1, -2);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateLayoutParams(Context context, AttributeSet attributeSet) {
        return new LayoutParams(context, attributeSet);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int getColumnCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 1) {
            return Math.min(this.mSpanCount, state.getItemCount());
        }
        return -1;
    }

    final int getFirstChildPosition() {
        if (getChildCount() == 0) {
            return 0;
        }
        return getPosition$ar$ds(getChildAt(0));
    }

    final int getLastChildPosition() {
        int childCount = getChildCount();
        if (childCount == 0) {
            return 0;
        }
        return getPosition$ar$ds(getChildAt(childCount - 1));
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return Math.min(this.mSpanCount, state.getItemCount());
        }
        return -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x00cf  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d4  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00db A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x002e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00d6  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final android.view.View hasGapsToFix() {
        /*
            Method dump skipped, instructions count: 222
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.hasGapsToFix():android.view.View");
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean isAutoMeasureEnabled() {
        if (this.mGapStrategy != 0) {
            return true;
        }
        return false;
    }

    final boolean isLayoutRTL() {
        if (getLayoutDirection() == 1) {
            return true;
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean isLayoutReversed() {
        return this.mReverseLayout;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void offsetChildrenHorizontal(int i) {
        super.offsetChildrenHorizontal(i);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.mSpans[i2].onOffset(i);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void offsetChildrenVertical(int i) {
        super.offsetChildrenVertical(i);
        for (int i2 = 0; i2 < this.mSpanCount; i2++) {
            this.mSpans[i2].onOffset(i);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onAdapterChanged$ar$ds() {
        this.mLazySpanLookup$ar$class_merging.clear();
        for (int i = 0; i < this.mSpanCount; i++) {
            this.mSpans[i].clear();
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onDetachedFromWindow$ar$ds(RecyclerView recyclerView) {
        removeCallbacks$ar$ds(this.mCheckForGapsRunnable);
        for (int i = 0; i < this.mSpanCount; i++) {
            this.mSpans[i].clear();
        }
        recyclerView.requestLayout();
    }

    /* JADX WARN: Code restructure failed: missing block: B:109:0x0039, code lost:
    
        if (r8.mOrientation == 1) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:112:0x003e, code lost:
    
        if (r8.mOrientation == 0) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x004b, code lost:
    
        if (isLayoutRTL() == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0058, code lost:
    
        if (isLayoutRTL() == false) goto L41;
     */
    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View onFocusSearchFailed(android.view.View r9, int r10, android.support.v7.widget.RecyclerView.Recycler r11, android.support.v7.widget.RecyclerView.State r12) {
        /*
            Method dump skipped, instructions count: 333
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.onFocusSearchFailed(android.view.View, int, android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State):android.view.View");
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            View findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToStart(false);
            View findFirstVisibleItemClosestToEnd = findFirstVisibleItemClosestToEnd(false);
            if (findFirstVisibleItemClosestToStart != null && findFirstVisibleItemClosestToEnd != null) {
                int position$ar$ds = getPosition$ar$ds(findFirstVisibleItemClosestToStart);
                int position$ar$ds2 = getPosition$ar$ds(findFirstVisibleItemClosestToEnd);
                if (position$ar$ds < position$ar$ds2) {
                    accessibilityEvent.setFromIndex(position$ar$ds);
                    accessibilityEvent.setToIndex(position$ar$ds2);
                } else {
                    accessibilityEvent.setFromIndex(position$ar$ds2);
                    accessibilityEvent.setToIndex(position$ar$ds);
                }
            }
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName("android.support.v7.widget.StaggeredGridLayoutManager");
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        if (this.mOrientation == 0) {
            int spanIndex = layoutParams2.getSpanIndex();
            boolean z = layoutParams2.mFullSpan;
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain$ar$ds$c443ecb5_0(spanIndex, 1, -1, -1, false));
        } else {
            int spanIndex2 = layoutParams2.getSpanIndex();
            boolean z2 = layoutParams2.mFullSpan;
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain$ar$ds$c443ecb5_0(-1, -1, spanIndex2, 1, false));
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsAdded$ar$ds(int i, int i2) {
        handleUpdate(i, i2, 1);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsChanged$ar$ds() {
        this.mLazySpanLookup$ar$class_merging.clear();
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsMoved$ar$ds(int i, int i2) {
        handleUpdate(i, i2, 8);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsRemoved$ar$ds(int i, int i2) {
        handleUpdate(i, i2, 2);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsUpdated$ar$ds(RecyclerView recyclerView, int i, int i2) {
        handleUpdate(i, i2, 4);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        onLayoutChildren(recycler, state, true);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onLayoutCompleted(RecyclerView.State state) {
        this.mPendingScrollPosition = -1;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        this.mPendingSavedState = null;
        this.mAnchorInfo.reset();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            this.mPendingSavedState = savedState;
            if (this.mPendingScrollPosition != -1) {
                savedState.invalidateAnchorPositionInfo();
                this.mPendingSavedState.invalidateSpanInfo();
            }
            requestLayout();
        }
    }

    /* JADX WARN: Type inference failed for: r1v26, types: [java.util.List, java.lang.Object] */
    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final Parcelable onSaveInstanceState() {
        int firstChildPosition;
        View findFirstVisibleItemClosestToStart;
        int startLine;
        int startAfterPadding;
        Object obj;
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null) {
            return new SavedState(savedState);
        }
        SavedState savedState2 = new SavedState();
        savedState2.mReverseLayout = this.mReverseLayout;
        savedState2.mAnchorLayoutFromEnd = this.mLastLayoutFromEnd;
        savedState2.mLastLayoutRTL = this.mLastLayoutRTL;
        LifecycleRegistry.ObserverWithState observerWithState = this.mLazySpanLookup$ar$class_merging;
        if (observerWithState != null && (obj = observerWithState.LifecycleRegistry$ObserverWithState$ar$lifecycleObserver) != null) {
            savedState2.mSpanLookup = (int[]) obj;
            savedState2.mSpanLookupSize = savedState2.mSpanLookup.length;
            savedState2.mFullSpanItems = observerWithState.LifecycleRegistry$ObserverWithState$ar$state;
        } else {
            savedState2.mSpanLookupSize = 0;
        }
        int i = -1;
        if (getChildCount() > 0) {
            if (this.mLastLayoutFromEnd) {
                firstChildPosition = getLastChildPosition();
            } else {
                firstChildPosition = getFirstChildPosition();
            }
            savedState2.mAnchorPosition = firstChildPosition;
            if (this.mShouldReverseLayout) {
                findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToEnd(true);
            } else {
                findFirstVisibleItemClosestToStart = findFirstVisibleItemClosestToStart(true);
            }
            if (findFirstVisibleItemClosestToStart != null) {
                i = getPosition$ar$ds(findFirstVisibleItemClosestToStart);
            }
            savedState2.mVisibleAnchorPosition = i;
            int i2 = this.mSpanCount;
            savedState2.mSpanOffsetsSize = i2;
            savedState2.mSpanOffsets = new int[i2];
            for (int i3 = 0; i3 < this.mSpanCount; i3++) {
                if (this.mLastLayoutFromEnd) {
                    startLine = this.mSpans[i3].getEndLine(Integer.MIN_VALUE);
                    if (startLine != Integer.MIN_VALUE) {
                        startAfterPadding = this.mPrimaryOrientation.getEndAfterPadding();
                        startLine -= startAfterPadding;
                        savedState2.mSpanOffsets[i3] = startLine;
                    } else {
                        savedState2.mSpanOffsets[i3] = startLine;
                    }
                } else {
                    startLine = this.mSpans[i3].getStartLine(Integer.MIN_VALUE);
                    if (startLine != Integer.MIN_VALUE) {
                        startAfterPadding = this.mPrimaryOrientation.getStartAfterPadding();
                        startLine -= startAfterPadding;
                        savedState2.mSpanOffsets[i3] = startLine;
                    } else {
                        savedState2.mSpanOffsets[i3] = startLine;
                    }
                }
            }
        } else {
            savedState2.mAnchorPosition = -1;
            savedState2.mVisibleAnchorPosition = -1;
            savedState2.mSpanOffsetsSize = 0;
        }
        return savedState2;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onScrollStateChanged(int i) {
        if (i == 0) {
            checkForGaps();
        }
    }

    final void prepareLayoutStateForDelta(int i, RecyclerView.State state) {
        int firstChildPosition;
        int i2;
        if (i > 0) {
            firstChildPosition = getLastChildPosition();
            i2 = 1;
        } else {
            firstChildPosition = getFirstChildPosition();
            i2 = -1;
        }
        this.mLayoutState.mRecycle = true;
        updateLayoutState(firstChildPosition, state);
        setLayoutStateDirection(i2);
        LayoutState layoutState = this.mLayoutState;
        layoutState.mCurrentPosition = firstChildPosition + layoutState.mItemDirection;
        layoutState.mAvailable = Math.abs(i);
    }

    final int scrollBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getChildCount() == 0 || i == 0) {
            return 0;
        }
        prepareLayoutStateForDelta(i, state);
        int fill = fill(recycler, this.mLayoutState, state);
        if (this.mLayoutState.mAvailable >= fill) {
            if (i < 0) {
                i = -fill;
            } else {
                i = fill;
            }
        }
        this.mPrimaryOrientation.offsetChildren(-i);
        this.mLastLayoutFromEnd = this.mShouldReverseLayout;
        LayoutState layoutState = this.mLayoutState;
        layoutState.mAvailable = 0;
        recycle(recycler, layoutState);
        return i;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i, recycler, state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void scrollToPosition(int i) {
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.mAnchorPosition != i) {
            savedState.invalidateAnchorPositionInfo();
        }
        this.mPendingScrollPosition = i;
        this.mPendingScrollPositionOffset = Integer.MIN_VALUE;
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return scrollBy(i, recycler, state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void setMeasuredDimension(Rect rect, int i, int i2) {
        int chooseSize;
        int chooseSize2;
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            chooseSize2 = chooseSize(i2, rect.height() + paddingTop, getMinimumHeight());
            chooseSize = chooseSize(i, (this.mSizePerSpan * this.mSpanCount) + paddingLeft, getMinimumWidth());
        } else {
            chooseSize = chooseSize(i, rect.width() + paddingLeft, getMinimumWidth());
            chooseSize2 = chooseSize(i2, (this.mSizePerSpan * this.mSpanCount) + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    public final void setReverseLayout(boolean z) {
        assertNotInLayoutOrScroll(null);
        SavedState savedState = this.mPendingSavedState;
        if (savedState != null && savedState.mReverseLayout != z) {
            savedState.mReverseLayout = z;
        }
        this.mReverseLayout = z;
        requestLayout();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void smoothScrollToPosition$ar$ds(RecyclerView recyclerView, int i) {
        RecyclerView.SmoothScroller smoothScroller = new RecyclerView.SmoothScroller(recyclerView.getContext());
        smoothScroller.mTargetPosition = i;
        startSmoothScroll(smoothScroller);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean supportsPredictiveItemAnimations() {
        if (this.mPendingSavedState == null) {
            return true;
        }
        return false;
    }

    final void updateMeasureSpecs(int i) {
        this.mSizePerSpan = i / this.mSpanCount;
        View.MeasureSpec.makeMeasureSpec(i, this.mSecondaryOrientation.getMode());
    }

    /* JADX WARN: Code restructure failed: missing block: B:245:0x03d3, code lost:
    
        if (checkForGaps() != false) goto L233;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void onLayoutChildren(android.support.v7.widget.RecyclerView.Recycler r12, android.support.v7.widget.RecyclerView.State r13, boolean r14) {
        /*
            Method dump skipped, instructions count: 1020
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.StaggeredGridLayoutManager.onLayoutChildren(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State, boolean):void");
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }
}
