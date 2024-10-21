package android.support.v7.widget;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.GapWorker;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import androidx.activity.OnBackPressedDispatcher;
import androidx.concurrent.futures.DirectExecutor;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.work.impl.ToContinuation;
import com.google.android.accessibility.braille.brltty.BrailleInputEvent;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.mlkit.logging.schema.OnDevicePoseDetectionLogEvent;
import j$.util.DesugarCollections;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public class GridLayoutManager extends LinearLayoutManager {
    private static final Set sSupportedDirectionsForActionScrollInDirection = DesugarCollections.unmodifiableSet(new HashSet(Arrays.asList(17, 66, 33, Integer.valueOf(BrailleInputEvent.CMD_STOP_READING))));
    int[] mCachedBorders;
    int mColumnWithAccessibilityFocus;
    final Rect mDecorInsets;
    boolean mPendingSpanCountChange;
    private int mPositionTargetedByScrollInDirection;
    final SparseIntArray mPreLayoutSpanIndexCache;
    final SparseIntArray mPreLayoutSpanSizeCache;
    int mRowWithAccessibilityFocus;
    View[] mSet;
    public int mSpanCount;
    final SpanSizeLookup mSpanSizeLookup;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api21Impl {
        public static final Object await(ListenableFuture listenableFuture, Continuation continuation) {
            try {
                if (listenableFuture.isDone()) {
                    return _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_14(listenableFuture);
                }
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(OnDevicePoseDetectionLogEvent.intercepted(continuation), 1);
                cancellableContinuationImpl.initCancellability();
                listenableFuture.addListener(new ToContinuation((Object) listenableFuture, (Runnable) cancellableContinuationImpl, 1), DirectExecutor.INSTANCE);
                cancellableContinuationImpl.invokeOnCancellation(new OnBackPressedDispatcher.AnonymousClass1(listenableFuture, 3));
                Object result = cancellableContinuationImpl.getResult();
                CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
                return result;
            } catch (ExecutionException e) {
                throw nonNullCause(e);
            }
        }

        static boolean isAccessibilityFocused(View view) {
            return view.isAccessibilityFocused();
        }

        public static final Throwable nonNullCause(ExecutionException executionException) {
            Throwable cause = executionException.getCause();
            cause.getClass();
            return cause;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class DefaultSpanSizeLookup extends SpanSizeLookup {
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class LayoutParams extends RecyclerView.LayoutParams {
        int mSpanIndex;
        int mSpanSize;

        public LayoutParams(int i, int i2) {
            super(i, i2);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(ViewGroup.LayoutParams layoutParams) {
            super(layoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }

        public LayoutParams(ViewGroup.MarginLayoutParams marginLayoutParams) {
            super(marginLayoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SpanSizeLookup {
        final SparseIntArray mSpanIndexCache = new SparseIntArray();
        final SparseIntArray mSpanGroupIndexCache = new SparseIntArray();

        public static final int getSpanGroupIndex$ar$ds(int i, int i2) {
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            while (true) {
                i3++;
                if (i4 >= i) {
                    break;
                }
                if (i3 == i2) {
                    i5++;
                    i3 = 0;
                } else if (i3 > i2) {
                    i5++;
                    i3 = 1;
                }
                i4++;
            }
            if (i3 > i2) {
                return i5 + 1;
            }
            return i5;
        }

        public final void invalidateSpanGroupIndexCache() {
            this.mSpanGroupIndexCache.clear();
        }

        public final void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }
    }

    public GridLayoutManager(Context context, int i) {
        super(1);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.mPositionTargetedByScrollInDirection = -1;
        this.mRowWithAccessibilityFocus = -1;
        this.mColumnWithAccessibilityFocus = -1;
        setSpanCount(i);
    }

    private final void calculateItemBorders(int i) {
        int i2;
        int length;
        int[] iArr = this.mCachedBorders;
        int i3 = this.mSpanCount;
        int i4 = i3 + 1;
        if (iArr == null || (length = iArr.length) != i4 || iArr[length - 1] != i) {
            iArr = new int[i4];
        }
        int i5 = 0;
        iArr[0] = 0;
        int i6 = i / i3;
        int i7 = i % i3;
        int i8 = 0;
        for (int i9 = 1; i9 <= i3; i9++) {
            i5 += i7;
            if (i5 > 0 && i3 - i5 < i7) {
                i2 = i6 + 1;
                i5 -= i3;
            } else {
                i2 = i6;
            }
            i8 += i2;
            iArr[i9] = i8;
        }
        this.mCachedBorders = iArr;
    }

    private final void ensureViewSet() {
        View[] viewArr = this.mSet;
        if (viewArr != null) {
            if (viewArr.length == this.mSpanCount) {
                return;
            }
        }
        this.mSet = new View[this.mSpanCount];
    }

    private final int getColumnIndex(int i) {
        if (this.mOrientation == 0) {
            RecyclerView recyclerView = this.mRecyclerView;
            return getSpanGroupIndex(recyclerView.mRecycler, recyclerView.mState, i);
        }
        RecyclerView recyclerView2 = this.mRecyclerView;
        return getSpanIndex(recyclerView2.mRecycler, recyclerView2.mState, i);
    }

    private final Set getColumnIndices(int i) {
        return getRowOrColumnIndices(getColumnIndex(i), i);
    }

    private final int getRowIndex(int i) {
        if (this.mOrientation == 1) {
            RecyclerView recyclerView = this.mRecyclerView;
            return getSpanGroupIndex(recyclerView.mRecycler, recyclerView.mState, i);
        }
        RecyclerView recyclerView2 = this.mRecyclerView;
        return getSpanIndex(recyclerView2.mRecycler, recyclerView2.mState, i);
    }

    private final Set getRowIndices(int i) {
        return getRowOrColumnIndices(getRowIndex(i), i);
    }

    private final Set getRowOrColumnIndices(int i, int i2) {
        HashSet hashSet = new HashSet();
        RecyclerView recyclerView = this.mRecyclerView;
        int spanSize = getSpanSize(recyclerView.mRecycler, recyclerView.mState, i2);
        for (int i3 = i; i3 < i + spanSize; i3++) {
            hashSet.add(Integer.valueOf(i3));
        }
        return hashSet;
    }

    private final int getSpanGroupIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        if (!state.mInPreLayout) {
            return SpanSizeLookup.getSpanGroupIndex$ar$ds(i, this.mSpanCount);
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i);
        if (convertPreLayoutPositionToPostLayout == -1) {
            Log.w("GridLayoutManager", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Cannot find span size for pre layout position. "));
            return 0;
        }
        return SpanSizeLookup.getSpanGroupIndex$ar$ds(convertPreLayoutPositionToPostLayout, this.mSpanCount);
    }

    private final int getSpanIndex(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        if (!state.mInPreLayout) {
            return i % this.mSpanCount;
        }
        int i2 = this.mPreLayoutSpanIndexCache.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(i);
        if (convertPreLayoutPositionToPostLayout == -1) {
            Log.w("GridLayoutManager", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:"));
            return 0;
        }
        return convertPreLayoutPositionToPostLayout % this.mSpanCount;
    }

    private final int getSpanSize(RecyclerView.Recycler recycler, RecyclerView.State state, int i) {
        if (!state.mInPreLayout) {
            return 1;
        }
        int i2 = this.mPreLayoutSpanSizeCache.get(i, -1);
        if (i2 != -1) {
            return i2;
        }
        if (recycler.convertPreLayoutPositionToPostLayout(i) == -1) {
            Log.w("GridLayoutManager", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:"));
        }
        return 1;
    }

    private final void measureChild(View view, int i, boolean z) {
        int i2;
        int i3;
        LayoutParams layoutParams = (LayoutParams) view.getLayoutParams();
        Rect rect = layoutParams.mDecorInsets;
        int i4 = rect.top + rect.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
        int i5 = rect.left + rect.right + layoutParams.leftMargin + layoutParams.rightMargin;
        int spaceForSpanRange = getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
        if (this.mOrientation == 1) {
            i3 = getChildMeasureSpec(spaceForSpanRange, i, i5, layoutParams.width, false);
            i2 = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), this.mHeightMode, i4, layoutParams.height, true);
        } else {
            int childMeasureSpec = getChildMeasureSpec(spaceForSpanRange, i, i4, layoutParams.height, false);
            int childMeasureSpec2 = getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), this.mWidthMode, i5, layoutParams.width, true);
            i2 = childMeasureSpec;
            i3 = childMeasureSpec2;
        }
        measureChildWithDecorationsAndMargin(view, i3, i2, z);
    }

    private final void measureChildWithDecorationsAndMargin(View view, int i, int i2, boolean z) {
        boolean shouldMeasureChild;
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
        if (z) {
            shouldMeasureChild = true;
            if (this.mMeasurementCacheEnabled && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_12(view.getMeasuredWidth(), i, layoutParams.width) && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_12(view.getMeasuredHeight(), i2, layoutParams.height)) {
                shouldMeasureChild = false;
            }
        } else {
            shouldMeasureChild = shouldMeasureChild(view, i, i2, layoutParams);
        }
        if (shouldMeasureChild) {
            view.measure(i, i2);
        }
    }

    private final void updateMeasurements() {
        int paddingBottom;
        int paddingTop;
        if (this.mOrientation == 1) {
            paddingBottom = this.mWidth - getPaddingRight();
            paddingTop = getPaddingLeft();
        } else {
            paddingBottom = this.mHeight - getPaddingBottom();
            paddingTop = getPaddingTop();
        }
        calculateItemBorders(paddingBottom - paddingTop);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean checkLayoutParams(RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public final void collectPrefetchPositionsForLayoutState$ar$class_merging(RecyclerView.State state, LinearLayoutManager.LayoutState layoutState, GapWorker.LayoutPrefetchRegistryImpl layoutPrefetchRegistryImpl) {
        int i = this.mSpanCount;
        for (int i2 = 0; i2 < this.mSpanCount && layoutState.hasMore(state) && i > 0; i2++) {
            layoutPrefetchRegistryImpl.addPosition(layoutState.mCurrentPosition, Math.max(0, layoutState.mScrollingOffset));
            i--;
            layoutState.mCurrentPosition += layoutState.mItemDirection;
        }
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public final View findReferenceChild(RecyclerView.Recycler recycler, RecyclerView.State state, boolean z, boolean z2) {
        int i;
        int i2;
        int i3;
        int childCount = getChildCount();
        if (z2) {
            i = -1;
            i2 = getChildCount() - 1;
            i3 = -1;
        } else {
            i = childCount;
            i2 = 0;
            i3 = 1;
        }
        int itemCount = state.getItemCount();
        ensureLayoutState();
        int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        View view = null;
        View view2 = null;
        while (i2 != i) {
            View childAt = getChildAt(i2);
            int position$ar$ds = getPosition$ar$ds(childAt);
            if (position$ar$ds >= 0 && position$ar$ds < itemCount && getSpanIndex(recycler, state, position$ar$ds) == 0) {
                if (((RecyclerView.LayoutParams) childAt.getLayoutParams()).isItemRemoved()) {
                    if (view2 == null) {
                        view2 = childAt;
                    }
                } else {
                    if (this.mOrientationHelper.getDecoratedStart(childAt) < endAfterPadding && this.mOrientationHelper.getDecoratedEnd(childAt) >= startAfterPadding) {
                        return childAt;
                    }
                    if (view == null) {
                        view = childAt;
                    }
                }
            }
            i2 += i3;
        }
        if (view != null) {
            return view;
        }
        return view2;
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
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
            return Math.min(this.mSpanCount, getItemCount());
        }
        if (state.getItemCount() <= 0) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final int getRowCountForAccessibility(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (this.mOrientation == 0) {
            return Math.min(this.mSpanCount, getItemCount());
        }
        if (state.getItemCount() <= 0) {
            return 0;
        }
        return getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }

    final int getSpaceForSpanRange(int i, int i2) {
        if (this.mOrientation == 1 && isLayoutRTL()) {
            int[] iArr = this.mCachedBorders;
            int i3 = this.mSpanCount - i;
            return iArr[i3] - iArr[i3 - i2];
        }
        int[] iArr2 = this.mCachedBorders;
        return iArr2[i2 + i] - iArr2[i];
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0219, code lost:
    
        r1 = getPaddingTop() + r18.mCachedBorders[r6.mSpanIndex];
        r12 = r1;
        r1 = r18.mOrientationHelper.getDecoratedMeasurementInOther(r5) + r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x024b, code lost:
    
        java.util.Arrays.fill(r18.mSet, (java.lang.Object) null);
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0251, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x01b6, code lost:
    
        r12 = r21.mOffset;
        r1 = r12 + r7;
        r2 = 0;
        r3 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:107:0x01c0, code lost:
    
        if (r21.mLayoutDirection != (-1)) goto L84;
     */
    /* JADX WARN: Code restructure failed: missing block: B:108:0x01c2, code lost:
    
        r12 = r21.mOffset;
        r2 = r12 - r7;
        r1 = 0;
        r3 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:109:0x01d1, code lost:
    
        r12 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:110:0x01ca, code lost:
    
        r12 = r21.mOffset;
        r3 = r12 + r7;
        r1 = 0;
        r2 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:111:0x009b, code lost:
    
        r12 = r13 - 1;
        r14 = -1;
        r15 = -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0095, code lost:
    
        if (r11 != 1) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0097, code lost:
    
        r15 = 1;
        r14 = r13;
        r12 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x009f, code lost:
    
        r8 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00a0, code lost:
    
        if (r12 == r14) goto L111;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x00a2, code lost:
    
        r7 = r18.mSet[r12];
        r9 = (android.support.v7.widget.GridLayoutManager.LayoutParams) r7.getLayoutParams();
        r7 = getSpanSize(r19, r20, getPosition$ar$ds(r7));
        r9.mSpanSize = r7;
        r9.mSpanIndex = r8;
        r8 = r8 + r7;
        r12 = r12 + r15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00be, code lost:
    
        r1 = 0.0f;
        r2 = 0;
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00c1, code lost:
    
        if (r2 >= r13) goto L112;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00c3, code lost:
    
        r8 = r18.mSet[r2];
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c9, code lost:
    
        if (r21.mScrapList != null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00cc, code lost:
    
        if (r11 != 1) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00ce, code lost:
    
        addView(r8);
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00e3, code lost:
    
        calculateItemDecorationsForChild(r8, r18.mDecorInsets);
        measureChild(r8, r5, r12);
        r9 = r18.mOrientationHelper.getDecoratedMeasurement(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00f1, code lost:
    
        if (r9 <= r7) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00f3, code lost:
    
        r7 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00f4, code lost:
    
        r8 = r18.mOrientationHelper.getDecoratedMeasurementInOther(r8) / ((android.support.v7.widget.GridLayoutManager.LayoutParams) r8.getLayoutParams()).mSpanSize;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0107, code lost:
    
        if (r8 <= r1) goto L114;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0109, code lost:
    
        r1 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x010a, code lost:
    
        r2 = r2 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00d3, code lost:
    
        r12 = false;
        addView(r8, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00d8, code lost:
    
        r12 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00da, code lost:
    
        if (r11 != 1) goto L49;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00dc, code lost:
    
        addDisappearingView(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00e0, code lost:
    
        addDisappearingView(r8, 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x010d, code lost:
    
        if (r10 == false) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x010f, code lost:
    
        calculateItemBorders(java.lang.Math.max(java.lang.Math.round(r1 * r18.mSpanCount), r6));
        r7 = 0;
        r12 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x0120, code lost:
    
        if (r12 >= r13) goto L115;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x0122, code lost:
    
        r1 = r18.mSet[r12];
        measureChild(r1, 1073741824, true);
        r1 = r18.mOrientationHelper.getDecoratedMeasurement(r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x0132, code lost:
    
        if (r1 <= r7) goto L117;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0134, code lost:
    
        r7 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0135, code lost:
    
        r12 = r12 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x0138, code lost:
    
        r12 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0139, code lost:
    
        if (r12 >= r13) goto L118;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x013b, code lost:
    
        r1 = r18.mSet[r12];
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0145, code lost:
    
        if (r18.mOrientationHelper.getDecoratedMeasurement(r1) == r7) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0147, code lost:
    
        r2 = (android.support.v7.widget.GridLayoutManager.LayoutParams) r1.getLayoutParams();
        r5 = r2.mDecorInsets;
        r6 = ((r5.top + r5.bottom) + r2.topMargin) + r2.bottomMargin;
        r8 = ((r5.left + r5.right) + r2.leftMargin) + r2.rightMargin;
        r5 = getSpaceForSpanRange(r2.mSpanIndex, r2.mSpanSize);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0170, code lost:
    
        if (r18.mOrientation != 1) goto L71;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0172, code lost:
    
        r2 = getChildMeasureSpec(r5, 1073741824, r8, r2.width, false);
        r5 = android.view.View.MeasureSpec.makeMeasureSpec(r7 - r6, 1073741824);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0192, code lost:
    
        measureChildWithDecorationsAndMargin(r1, r2, r5, true);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x019a, code lost:
    
        r12 = r12 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x0182, code lost:
    
        r8 = android.view.View.MeasureSpec.makeMeasureSpec(r7 - r8, 1073741824);
        r5 = getChildMeasureSpec(r5, 1073741824, r6, r2.height, false);
        r2 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x019d, code lost:
    
        r22.mConsumed = r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01a3, code lost:
    
        if (r18.mOrientation != 1) goto L81;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x01a8, code lost:
    
        if (r21.mLayoutDirection != (-1)) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x01aa, code lost:
    
        r12 = r21.mOffset;
        r2 = 0;
        r3 = 0;
        r12 = r12 - r7;
        r1 = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01d2, code lost:
    
        r7 = 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x01d3, code lost:
    
        if (r7 >= r13) goto L121;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x01d5, code lost:
    
        r5 = r18.mSet[r7];
        r6 = (android.support.v7.widget.GridLayoutManager.LayoutParams) r5.getLayoutParams();
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x01e2, code lost:
    
        if (r18.mOrientation != 1) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x01e8, code lost:
    
        if (isLayoutRTL() == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x01ea, code lost:
    
        r2 = getPaddingLeft() + r18.mCachedBorders[r18.mSpanCount - r6.mSpanIndex];
        r3 = r2;
        r2 = r2 - r18.mOrientationHelper.getDecoratedMeasurementInOther(r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x022d, code lost:
    
        layoutDecoratedWithMargins$ar$ds(r5, r2, r12, r3, r1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0234, code lost:
    
        if (r6.isItemRemoved() != false) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x023a, code lost:
    
        if (r6.isItemChanged() == false) goto L123;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x023f, code lost:
    
        r22.mFocusable = r5.hasFocusable() | r22.mFocusable;
        r7 = r7 + 1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x023c, code lost:
    
        r22.mIgnoreConsumed = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0206, code lost:
    
        r2 = getPaddingLeft() + r18.mCachedBorders[r6.mSpanIndex];
        r3 = r18.mOrientationHelper.getDecoratedMeasurementInOther(r5) + r2;
     */
    @Override // android.support.v7.widget.LinearLayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void layoutChunk(android.support.v7.widget.RecyclerView.Recycler r19, android.support.v7.widget.RecyclerView.State r20, android.support.v7.widget.LinearLayoutManager.LayoutState r21, android.support.v7.widget.LinearLayoutManager.LayoutChunkResult r22) {
        /*
            Method dump skipped, instructions count: 598
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.layoutChunk(android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State, android.support.v7.widget.LinearLayoutManager$LayoutState, android.support.v7.widget.LinearLayoutManager$LayoutChunkResult):void");
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public final void onAnchorReady(RecyclerView.Recycler recycler, RecyclerView.State state, LinearLayoutManager.AnchorInfo anchorInfo, int i) {
        updateMeasurements();
        if (state.getItemCount() > 0 && !state.mInPreLayout) {
            int spanIndex = getSpanIndex(recycler, state, anchorInfo.mPosition);
            if (i == 1) {
                while (spanIndex > 0) {
                    int i2 = anchorInfo.mPosition;
                    if (i2 <= 0) {
                        break;
                    }
                    int i3 = i2 - 1;
                    anchorInfo.mPosition = i3;
                    spanIndex = getSpanIndex(recycler, state, i3);
                }
            } else {
                int itemCount = state.getItemCount() - 1;
                int i4 = anchorInfo.mPosition;
                while (i4 < itemCount) {
                    int i5 = i4 + 1;
                    int spanIndex2 = getSpanIndex(recycler, state, i5);
                    if (spanIndex2 <= spanIndex) {
                        break;
                    }
                    i4 = i5;
                    spanIndex = spanIndex2;
                }
                anchorInfo.mPosition = i4;
            }
        }
        ensureViewSet();
    }

    /* JADX WARN: Code restructure failed: missing block: B:62:0x00cd, code lost:
    
        if (r13 == r10) goto L51;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x00ed, code lost:
    
        if (r13 == r4) goto L69;
     */
    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.view.View onFocusSearchFailed(android.view.View r23, int r24, android.support.v7.widget.RecyclerView.Recycler r25, android.support.v7.widget.RecyclerView.State r26) {
        /*
            Method dump skipped, instructions count: 297
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.onFocusSearchFailed(android.view.View, int, android.support.v7.widget.RecyclerView$Recycler, android.support.v7.widget.RecyclerView$State):android.view.View");
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityNodeInfo(RecyclerView.Recycler recycler, RecyclerView.State state, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(recycler, state, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.setClassName(GridView.class.getName());
        RecyclerView.Adapter adapter = this.mRecyclerView.mAdapter;
        if (adapter != null && adapter.getItemCount() > 1) {
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_IN_DIRECTION);
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onInitializeAccessibilityNodeInfoForItem(RecyclerView.Recycler recycler, RecyclerView.State state, View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        LayoutParams layoutParams2 = (LayoutParams) layoutParams;
        int spanGroupIndex = getSpanGroupIndex(recycler, state, layoutParams2.getViewLayoutPosition());
        if (this.mOrientation == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain$ar$ds$c443ecb5_0(layoutParams2.mSpanIndex, layoutParams2.mSpanSize, spanGroupIndex, 1, false));
        } else {
            accessibilityNodeInfoCompat.setCollectionItemInfo(AccessibilityNodeInfoCompat.CollectionItemInfoCompat.obtain$ar$ds$c443ecb5_0(spanGroupIndex, 1, layoutParams2.mSpanIndex, layoutParams2.mSpanSize, false));
        }
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsAdded$ar$ds(int i, int i2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsChanged$ar$ds() {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsMoved$ar$ds(int i, int i2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsRemoved$ar$ds(int i, int i2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void onItemsUpdated$ar$ds(RecyclerView recyclerView, int i, int i2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        this.mSpanSizeLookup.invalidateSpanGroupIndexCache();
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public final void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.mInPreLayout) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
                int viewLayoutPosition = layoutParams.getViewLayoutPosition();
                this.mPreLayoutSpanSizeCache.put(viewLayoutPosition, layoutParams.mSpanSize);
                this.mPreLayoutSpanIndexCache.put(viewLayoutPosition, layoutParams.mSpanIndex);
            }
        }
        super.onLayoutChildren(recycler, state);
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public final void onLayoutCompleted(RecyclerView.State state) {
        View findViewByPosition;
        super.onLayoutCompleted(state);
        this.mPendingSpanCountChange = false;
        int i = this.mPositionTargetedByScrollInDirection;
        if (i != -1 && (findViewByPosition = findViewByPosition(i)) != null) {
            findViewByPosition.sendAccessibilityEvent(67108864);
            this.mPositionTargetedByScrollInDirection = -1;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:109:0x025c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x018c  */
    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean performAccessibilityAction(int r12, android.os.Bundle r13) {
        /*
            Method dump skipped, instructions count: 695
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: android.support.v7.widget.GridLayoutManager.performAccessibilityAction(int, android.os.Bundle):boolean");
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public final int scrollHorizontallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollHorizontallyBy(i, recycler, state);
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public final int scrollVerticallyBy(int i, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateMeasurements();
        ensureViewSet();
        return super.scrollVerticallyBy(i, recycler, state);
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final void setMeasuredDimension(Rect rect, int i, int i2) {
        int chooseSize;
        int chooseSize2;
        if (this.mCachedBorders == null) {
            super.setMeasuredDimension(rect, i, i2);
        }
        int paddingLeft = getPaddingLeft() + getPaddingRight();
        int paddingTop = getPaddingTop() + getPaddingBottom();
        if (this.mOrientation == 1) {
            chooseSize2 = chooseSize(i2, rect.height() + paddingTop, getMinimumHeight());
            chooseSize = chooseSize(i, this.mCachedBorders[r7.length - 1] + paddingLeft, getMinimumWidth());
        } else {
            chooseSize = chooseSize(i, rect.width() + paddingLeft, getMinimumWidth());
            chooseSize2 = chooseSize(i2, this.mCachedBorders[r5.length - 1] + paddingTop, getMinimumHeight());
        }
        setMeasuredDimension(chooseSize, chooseSize2);
    }

    public final void setSpanCount(int i) {
        if (i == this.mSpanCount) {
            return;
        }
        this.mPendingSpanCountChange = true;
        if (i > 0) {
            this.mSpanCount = i;
            this.mSpanSizeLookup.invalidateSpanIndexCache();
            requestLayout();
            return;
        }
        throw new IllegalArgumentException(_BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_3(i, "Span count should be at least 1. Provided "));
    }

    @Override // android.support.v7.widget.LinearLayoutManager
    public final void setStackFromEnd(boolean z) {
        if (!z) {
            super.setStackFromEnd(false);
            return;
        }
        throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
    }

    @Override // android.support.v7.widget.LinearLayoutManager, android.support.v7.widget.RecyclerView.LayoutManager
    public final boolean supportsPredictiveItemAnimations() {
        if (this.mPendingSavedState == null && !this.mPendingSpanCountChange) {
            return true;
        }
        return false;
    }

    @Override // android.support.v7.widget.RecyclerView.LayoutManager
    public final RecyclerView.LayoutParams generateLayoutParams(ViewGroup.LayoutParams layoutParams) {
        if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
            return new LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        }
        return new LayoutParams(layoutParams);
    }

    public GridLayoutManager(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.mPositionTargetedByScrollInDirection = -1;
        this.mRowWithAccessibilityFocus = -1;
        this.mColumnWithAccessibilityFocus = -1;
        setSpanCount(getProperties(context, attributeSet, i, i2).spanCount);
    }
}
