package com.google.android.accessibility.talkback.labeling;

import android.os.AsyncTask;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LabelTask extends AsyncTask {
    private final HapticPatternParser$$ExternalSyntheticLambda1 callback$ar$class_merging$bd967e45_0$ar$class_merging$ar$class_merging$ar$class_merging;
    private final LabelClientRequest request;

    public LabelTask(LabelClientRequest labelClientRequest, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        this.callback$ar$class_merging$bd967e45_0$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
        this.request = labelClientRequest;
    }

    @Override // android.os.AsyncTask
    protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
        return this.request.doInBackground();
    }

    @Override // android.os.AsyncTask
    protected final void onPostExecute(Object obj) {
        this.request.onPostExecute(obj);
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = this.callback$ar$class_merging$bd967e45_0$ar$class_merging$ar$class_merging$ar$class_merging;
        if (hapticPatternParser$$ExternalSyntheticLambda1 != null) {
            hapticPatternParser$$ExternalSyntheticLambda1.onTaskPostExecute(this.request);
        }
        super.onPostExecute(obj);
    }

    @Override // android.os.AsyncTask
    protected final void onPreExecute() {
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = this.callback$ar$class_merging$bd967e45_0$ar$class_merging$ar$class_merging$ar$class_merging;
        if (hapticPatternParser$$ExternalSyntheticLambda1 != null) {
            hapticPatternParser$$ExternalSyntheticLambda1.onTaskPreExecute(this.request);
        }
        super.onPreExecute();
    }
}
