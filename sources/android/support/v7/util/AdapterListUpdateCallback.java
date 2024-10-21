package android.support.v7.util;

import android.support.v7.widget.RecyclerView;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AdapterListUpdateCallback implements ListUpdateCallback {
    public final RecyclerView.Adapter mAdapter;

    public AdapterListUpdateCallback(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public final void onInserted(int i, int i2) {
        this.mAdapter.mObservable.notifyItemRangeInserted(i, i2);
    }

    @Override // android.support.v7.util.ListUpdateCallback
    public final void onRemoved(int i, int i2) {
        this.mAdapter.mObservable.notifyItemRangeRemoved(i, i2);
    }
}
