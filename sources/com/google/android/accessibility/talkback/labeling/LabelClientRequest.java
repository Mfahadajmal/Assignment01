package com.google.android.accessibility.talkback.labeling;

import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class LabelClientRequest {
    protected final ExecutionList.RunnableExecutorPair mClient$ar$class_merging;

    public LabelClientRequest(ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        this.mClient$ar$class_merging = runnableExecutorPair;
    }

    public abstract Object doInBackground();

    public void onPostExecute(Object obj) {
        throw null;
    }
}
