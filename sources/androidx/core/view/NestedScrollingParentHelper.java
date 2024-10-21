package androidx.core.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class NestedScrollingParentHelper {
    public int mNestedScrollAxesNonTouch;
    public int mNestedScrollAxesTouch;

    public final int getNestedScrollAxes() {
        return this.mNestedScrollAxesTouch | this.mNestedScrollAxesNonTouch;
    }

    public final void onNestedScrollAccepted(View view, View view2, int i) {
        onNestedScrollAccepted$ar$ds(i, 0);
    }

    public final void onNestedScrollAccepted$ar$ds(int i, int i2) {
        if (i2 == 1) {
            this.mNestedScrollAxesNonTouch = i;
        } else {
            this.mNestedScrollAxesTouch = i;
        }
    }

    public final void onStopNestedScroll$ar$ds(int i) {
        if (i == 1) {
            this.mNestedScrollAxesNonTouch = 0;
        } else {
            this.mNestedScrollAxesTouch = 0;
        }
    }

    public final NestedScrollingParentHelper setFrom$ar$class_merging(RecyclerView.ViewHolder viewHolder) {
        View view = viewHolder.itemView;
        this.mNestedScrollAxesNonTouch = view.getLeft();
        this.mNestedScrollAxesTouch = view.getTop();
        view.getRight();
        view.getBottom();
        return this;
    }
}
