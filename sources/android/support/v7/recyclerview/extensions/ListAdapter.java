package android.support.v7.recyclerview.extensions;

import android.support.v7.app.AppCompatDelegateImpl;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.widget.RecyclerView;
import androidx.work.impl.model.WorkName;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ListAdapter extends RecyclerView.Adapter {
    public final AsyncListDiffer mDiffer;
    private final AppCompatDelegateImpl.Api21Impl mListener$ar$class_merging$ar$class_merging;

    /* JADX INFO: Access modifiers changed from: protected */
    public ListAdapter(WorkName workName) {
        AppCompatDelegateImpl.Api21Impl api21Impl = new AppCompatDelegateImpl.Api21Impl();
        this.mListener$ar$class_merging$ar$class_merging = api21Impl;
        AsyncListDiffer asyncListDiffer = new AsyncListDiffer(new AdapterListUpdateCallback(this), workName);
        this.mDiffer = asyncListDiffer;
        asyncListDiffer.mListeners.add(api21Impl);
    }

    public final Object getItem(int i) {
        return this.mDiffer.mReadOnlyList.get(i);
    }

    @Override // android.support.v7.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return this.mDiffer.mReadOnlyList.size();
    }
}
