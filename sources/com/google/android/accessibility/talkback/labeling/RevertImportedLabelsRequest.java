package com.google.android.accessibility.talkback.labeling;

import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class RevertImportedLabelsRequest extends LabelClientRequest {
    private final OnImportLabelsRevertedListener listener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnImportLabelsRevertedListener {
        void onImportLabelsReverted();
    }

    public RevertImportedLabelsRequest(ExecutionList.RunnableExecutorPair runnableExecutorPair, OnImportLabelsRevertedListener onImportLabelsRevertedListener) {
        super(runnableExecutorPair);
        this.listener = onImportLabelsRevertedListener;
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ Object doInBackground() {
        if (!this.mClient$ar$class_merging.isInitialized()) {
            return false;
        }
        ExecutionList.RunnableExecutorPair runnableExecutorPair = this.mClient$ar$class_merging;
        ExecutionList.RunnableExecutorPair runnableExecutorPair2 = this.mClient$ar$class_merging;
        boolean deleteLabels = runnableExecutorPair.deleteLabels(1);
        runnableExecutorPair2.updateSourceType$ar$ds(2);
        return Boolean.valueOf(deleteLabels);
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        Boolean bool = (Boolean) obj;
        if (bool != null && bool.booleanValue()) {
            this.listener.onImportLabelsReverted();
        }
    }
}
