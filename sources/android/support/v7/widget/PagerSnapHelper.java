package android.support.v7.widget;

import android.graphics.PointF;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.preference.Preference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PagerSnapHelper extends RecyclerView.OnFlingListener {
    private OrientationHelper mHorizontalHelper;
    private OrientationHelper mVerticalHelper;

    public PagerSnapHelper() {
        super(null);
    }

    private static final int distanceToCenter$ar$ds(View view, OrientationHelper orientationHelper) {
        return (orientationHelper.getDecoratedStart(view) + (orientationHelper.getDecoratedMeasurement(view) / 2)) - (orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2));
    }

    private static final View findCenterView$ar$ds(RecyclerView.LayoutManager layoutManager, OrientationHelper orientationHelper) {
        int i;
        int childCount = layoutManager.getChildCount();
        View view = null;
        if (childCount == 0) {
            return null;
        }
        int startAfterPadding = orientationHelper.getStartAfterPadding() + (orientationHelper.getTotalSpace() / 2);
        int i2 = 0;
        int i3 = Preference.DEFAULT_ORDER;
        while (i2 < childCount) {
            View childAt = layoutManager.getChildAt(i2);
            int abs = Math.abs((orientationHelper.getDecoratedStart(childAt) + (orientationHelper.getDecoratedMeasurement(childAt) / 2)) - startAfterPadding);
            if (abs < i3) {
                i = abs;
            } else {
                i = i3;
            }
            if (abs < i3) {
                view = childAt;
            }
            i2++;
            i3 = i;
        }
        return view;
    }

    private final OrientationHelper getHorizontalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mHorizontalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mHorizontalHelper = new OrientationHelper.AnonymousClass1(layoutManager);
        }
        return this.mHorizontalHelper;
    }

    private final OrientationHelper getVerticalHelper(RecyclerView.LayoutManager layoutManager) {
        OrientationHelper orientationHelper = this.mVerticalHelper;
        if (orientationHelper == null || orientationHelper.mLayoutManager != layoutManager) {
            this.mVerticalHelper = new OrientationHelper.AnonymousClass2(layoutManager);
        }
        return this.mVerticalHelper;
    }

    @Override // android.support.v7.widget.RecyclerView.OnFlingListener
    public final int[] calculateDistanceToFinalSnap(RecyclerView.LayoutManager layoutManager, View view) {
        int[] iArr = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            iArr[0] = distanceToCenter$ar$ds(view, getHorizontalHelper(layoutManager));
        } else {
            iArr[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            iArr[1] = distanceToCenter$ar$ds(view, getVerticalHelper(layoutManager));
        } else {
            iArr[1] = 0;
        }
        return iArr;
    }

    @Override // android.support.v7.widget.RecyclerView.OnFlingListener
    protected final RecyclerView.SmoothScroller createScroller(RecyclerView.LayoutManager layoutManager) {
        if (!(layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider)) {
            return null;
        }
        return new RecyclerView.SmoothScroller(this.mRecyclerView.getContext()) { // from class: android.support.v7.widget.PagerSnapHelper.1
            @Override // android.support.v7.widget.RecyclerView.SmoothScroller
            protected final float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 100.0f / displayMetrics.densityDpi;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.support.v7.widget.RecyclerView.SmoothScroller
            public final int calculateTimeForScrolling(int i) {
                return Math.min(100, super.calculateTimeForScrolling(i));
            }

            @Override // android.support.v7.widget.RecyclerView.SmoothScroller
            protected final void onTargetFound$ar$ds(View view, RecyclerView.SmoothScroller.Action action) {
                PagerSnapHelper pagerSnapHelper = PagerSnapHelper.this;
                int[] calculateDistanceToFinalSnap = pagerSnapHelper.calculateDistanceToFinalSnap(pagerSnapHelper.mRecyclerView.mLayout, view);
                int i = calculateDistanceToFinalSnap[0];
                int i2 = calculateDistanceToFinalSnap[1];
                int calculateTimeForDeceleration = calculateTimeForDeceleration(Math.max(Math.abs(i), Math.abs(i2)));
                if (calculateTimeForDeceleration > 0) {
                    action.update(i, i2, calculateTimeForDeceleration, this.mDecelerateInterpolator);
                }
            }
        };
    }

    @Override // android.support.v7.widget.RecyclerView.OnFlingListener
    public final View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return findCenterView$ar$ds(layoutManager, getVerticalHelper(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return findCenterView$ar$ds(layoutManager, getHorizontalHelper(layoutManager));
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.support.v7.widget.RecyclerView.OnFlingListener
    public final int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i2) {
        OrientationHelper orientationHelper;
        boolean z;
        PointF computeScrollVectorForPosition;
        int itemCount = layoutManager.getItemCount();
        if (itemCount != 0) {
            View view = null;
            if (layoutManager.canScrollVertically()) {
                orientationHelper = getVerticalHelper(layoutManager);
            } else if (layoutManager.canScrollHorizontally()) {
                orientationHelper = getHorizontalHelper(layoutManager);
            } else {
                orientationHelper = null;
            }
            if (orientationHelper == null) {
                return -1;
            }
            int childCount = layoutManager.getChildCount();
            boolean z2 = false;
            int i3 = Integer.MIN_VALUE;
            int i4 = Integer.MAX_VALUE;
            View view2 = null;
            for (int i5 = 0; i5 < childCount; i5++) {
                View childAt = layoutManager.getChildAt(i5);
                if (childAt != null) {
                    int distanceToCenter$ar$ds = distanceToCenter$ar$ds(childAt, orientationHelper);
                    if (distanceToCenter$ar$ds <= 0 && distanceToCenter$ar$ds > i3) {
                        view2 = childAt;
                        i3 = distanceToCenter$ar$ds;
                    }
                    if (distanceToCenter$ar$ds >= 0 && distanceToCenter$ar$ds < i4) {
                        view = childAt;
                        i4 = distanceToCenter$ar$ds;
                    }
                }
            }
            int i6 = 1;
            if (true != layoutManager.canScrollHorizontally()) {
                i = i2;
            }
            if (i > 0) {
                z = true;
            } else {
                z = false;
            }
            if (z && view != null) {
                return RecyclerView.LayoutManager.getPosition$ar$ds(view);
            }
            if (!z && view2 != null) {
                return RecyclerView.LayoutManager.getPosition$ar$ds(view2);
            }
            if (true == z) {
                view = view2;
            }
            if (view == null) {
                return -1;
            }
            int position$ar$ds = RecyclerView.LayoutManager.getPosition$ar$ds(view);
            int itemCount2 = layoutManager.getItemCount();
            if ((layoutManager instanceof RecyclerView.SmoothScroller.ScrollVectorProvider) && (computeScrollVectorForPosition = ((RecyclerView.SmoothScroller.ScrollVectorProvider) layoutManager).computeScrollVectorForPosition(itemCount2 - 1)) != null && (computeScrollVectorForPosition.x < 0.0f || computeScrollVectorForPosition.y < 0.0f)) {
                z2 = true;
            }
            if (z2 == z) {
                i6 = -1;
            }
            int i7 = position$ar$ds + i6;
            if (i7 >= 0 && i7 < itemCount) {
                return i7;
            }
        }
        return -1;
    }
}
