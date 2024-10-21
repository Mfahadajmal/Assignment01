package android.support.v7.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class OrientationHelper {
    protected final RecyclerView.LayoutManager mLayoutManager;
    public int mLastTotalSpace = Integer.MIN_VALUE;
    final Rect mTmpRect = new Rect();

    /* compiled from: PG */
    /* renamed from: android.support.v7.widget.OrientationHelper$1, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass1 extends OrientationHelper {
        public AnonymousClass1(RecyclerView.LayoutManager layoutManager) {
            super(layoutManager);
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getDecoratedEnd(View view) {
            return this.mLayoutManager.getDecoratedRight(view) + ((RecyclerView.LayoutParams) view.getLayoutParams()).rightMargin;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getDecoratedMeasurement(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return RecyclerView.LayoutManager.getDecoratedMeasuredWidth$ar$ds(view) + layoutParams.leftMargin + layoutParams.rightMargin;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getDecoratedMeasurementInOther(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return RecyclerView.LayoutManager.getDecoratedMeasuredHeight$ar$ds(view) + layoutParams.topMargin + layoutParams.bottomMargin;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getDecoratedStart(View view) {
            return this.mLayoutManager.getDecoratedLeft(view) - ((RecyclerView.LayoutParams) view.getLayoutParams()).leftMargin;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getEnd() {
            return this.mLayoutManager.mWidth;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getEndAfterPadding() {
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            return layoutManager.mWidth - layoutManager.getPaddingRight();
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getEndPadding() {
            return this.mLayoutManager.getPaddingRight();
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getMode() {
            return this.mLayoutManager.mWidthMode;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getModeInOther() {
            return this.mLayoutManager.mHeightMode;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getStartAfterPadding() {
            return this.mLayoutManager.getPaddingLeft();
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getTotalSpace() {
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            return (layoutManager.mWidth - layoutManager.getPaddingLeft()) - this.mLayoutManager.getPaddingRight();
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getTransformedEndWithDecoration(View view) {
            this.mLayoutManager.getTransformedBoundingBox$ar$ds(view, this.mTmpRect);
            return this.mTmpRect.right;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getTransformedStartWithDecoration(View view) {
            this.mLayoutManager.getTransformedBoundingBox$ar$ds(view, this.mTmpRect);
            return this.mTmpRect.left;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final void offsetChildren(int i) {
            this.mLayoutManager.offsetChildrenHorizontal(i);
        }
    }

    /* compiled from: PG */
    /* renamed from: android.support.v7.widget.OrientationHelper$2, reason: invalid class name */
    /* loaded from: classes.dex */
    public final class AnonymousClass2 extends OrientationHelper {
        public AnonymousClass2(RecyclerView.LayoutManager layoutManager) {
            super(layoutManager);
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getDecoratedEnd(View view) {
            return this.mLayoutManager.getDecoratedBottom(view) + ((RecyclerView.LayoutParams) view.getLayoutParams()).bottomMargin;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getDecoratedMeasurement(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return RecyclerView.LayoutManager.getDecoratedMeasuredHeight$ar$ds(view) + layoutParams.topMargin + layoutParams.bottomMargin;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getDecoratedMeasurementInOther(View view) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            return RecyclerView.LayoutManager.getDecoratedMeasuredWidth$ar$ds(view) + layoutParams.leftMargin + layoutParams.rightMargin;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getDecoratedStart(View view) {
            return this.mLayoutManager.getDecoratedTop(view) - ((RecyclerView.LayoutParams) view.getLayoutParams()).topMargin;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getEnd() {
            return this.mLayoutManager.mHeight;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getEndAfterPadding() {
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            return layoutManager.mHeight - layoutManager.getPaddingBottom();
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getEndPadding() {
            return this.mLayoutManager.getPaddingBottom();
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getMode() {
            return this.mLayoutManager.mHeightMode;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getModeInOther() {
            return this.mLayoutManager.mWidthMode;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getStartAfterPadding() {
            return this.mLayoutManager.getPaddingTop();
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getTotalSpace() {
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            return (layoutManager.mHeight - layoutManager.getPaddingTop()) - this.mLayoutManager.getPaddingBottom();
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getTransformedEndWithDecoration(View view) {
            this.mLayoutManager.getTransformedBoundingBox$ar$ds(view, this.mTmpRect);
            return this.mTmpRect.bottom;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final int getTransformedStartWithDecoration(View view) {
            this.mLayoutManager.getTransformedBoundingBox$ar$ds(view, this.mTmpRect);
            return this.mTmpRect.top;
        }

        @Override // android.support.v7.widget.OrientationHelper
        public final void offsetChildren(int i) {
            this.mLayoutManager.offsetChildrenVertical(i);
        }
    }

    public OrientationHelper(RecyclerView.LayoutManager layoutManager) {
        this.mLayoutManager = layoutManager;
    }

    public static OrientationHelper createOrientationHelper(RecyclerView.LayoutManager layoutManager, int i) {
        if (i != 0) {
            return new AnonymousClass2(layoutManager);
        }
        return new AnonymousClass1(layoutManager);
    }

    public abstract int getDecoratedEnd(View view);

    public abstract int getDecoratedMeasurement(View view);

    public abstract int getDecoratedMeasurementInOther(View view);

    public abstract int getDecoratedStart(View view);

    public abstract int getEnd();

    public abstract int getEndAfterPadding();

    public abstract int getEndPadding();

    public abstract int getMode();

    public abstract int getModeInOther();

    public abstract int getStartAfterPadding();

    public abstract int getTotalSpace();

    public final int getTotalSpaceChange() {
        if (this.mLastTotalSpace == Integer.MIN_VALUE) {
            return 0;
        }
        return getTotalSpace() - this.mLastTotalSpace;
    }

    public abstract int getTransformedEndWithDecoration(View view);

    public abstract int getTransformedStartWithDecoration(View view);

    public abstract void offsetChildren(int i);
}
