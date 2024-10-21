package com.google.android.accessibility.talkback.labeling;

import com.google.common.util.concurrent.ExecutionList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LabelsFetchRequest extends LabelClientRequest {
    private final OnLabelsFetchedListener onLabelFetchedListener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnLabelsFetchedListener {
        void onLabelsFetched(List list);
    }

    public LabelsFetchRequest(ExecutionList.RunnableExecutorPair runnableExecutorPair, OnLabelsFetchedListener onLabelsFetchedListener) {
        super(runnableExecutorPair);
        this.onLabelFetchedListener = onLabelsFetchedListener;
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ Object doInBackground() {
        return this.mClient$ar$class_merging.getCurrentLabels();
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        OnLabelsFetchedListener onLabelsFetchedListener = this.onLabelFetchedListener;
        List list = (List) obj;
        if (onLabelsFetchedListener != null) {
            onLabelsFetchedListener.onLabelsFetched(list);
        }
    }
}
