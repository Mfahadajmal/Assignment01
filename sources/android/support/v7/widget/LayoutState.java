package android.support.v7.widget;

import android.support.v7.widget.RecyclerView;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class LayoutState {
    int mAvailable;
    int mCurrentPosition;
    boolean mInfinite;
    int mItemDirection;
    int mLayoutDirection;
    boolean mStopInFocusable;
    boolean mRecycle = true;
    int mStartLine = 0;
    int mEndLine = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean hasMore(RecyclerView.State state) {
        int i = this.mCurrentPosition;
        if (i >= 0 && i < state.getItemCount()) {
            return true;
        }
        return false;
    }

    public final String toString() {
        return "LayoutState{mAvailable=" + this.mAvailable + ", mCurrentPosition=" + this.mCurrentPosition + ", mItemDirection=" + this.mItemDirection + ", mLayoutDirection=" + this.mLayoutDirection + ", mStartLine=" + this.mStartLine + ", mEndLine=" + this.mEndLine + '}';
    }
}
