package android.support.v7.util;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BatchingListUpdateCallback implements ListUpdateCallback {
    final ListUpdateCallback mWrapped;
    public int mLastEventType = 0;
    public int mLastEventPosition = -1;
    public int mLastEventCount = -1;

    public BatchingListUpdateCallback(ListUpdateCallback listUpdateCallback) {
        this.mWrapped = listUpdateCallback;
    }

    public final void dispatchLastEvent() {
        int i = this.mLastEventType;
        if (i == 0) {
            return;
        }
        if (i != 1) {
            if (i != 2) {
                ListUpdateCallback listUpdateCallback = this.mWrapped;
                ((AdapterListUpdateCallback) listUpdateCallback).mAdapter.mObservable.notifyItemRangeChanged(this.mLastEventPosition, this.mLastEventCount, null);
            } else {
                this.mWrapped.onRemoved(this.mLastEventPosition, this.mLastEventCount);
            }
        } else {
            this.mWrapped.onInserted(this.mLastEventPosition, this.mLastEventCount);
        }
        this.mLastEventType = 0;
    }

    public final void onChanged$ar$ds(int i, int i2) {
        int i3;
        int i4;
        int i5;
        if (this.mLastEventType == 3 && i <= (i4 = this.mLastEventCount + (i3 = this.mLastEventPosition)) && (i5 = i + 1) >= i3) {
            this.mLastEventPosition = Math.min(i, i3);
            this.mLastEventCount = Math.max(i4, i5) - this.mLastEventPosition;
        } else {
            dispatchLastEvent();
            this.mLastEventPosition = i;
            this.mLastEventCount = 1;
            this.mLastEventType = 3;
        }
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public final void onInserted(int i, int i2) {
        throw null;
    }

    public final void onMoved(int i, int i2) {
        dispatchLastEvent();
        ((AdapterListUpdateCallback) this.mWrapped).mAdapter.mObservable.notifyItemMoved(i, i2);
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public final void onRemoved(int i, int i2) {
        throw null;
    }
}
