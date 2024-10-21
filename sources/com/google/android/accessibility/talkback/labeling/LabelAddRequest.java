package com.google.android.accessibility.talkback.labeling;

import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LabelAddRequest extends LabelClientRequest {
    private final Label label;
    private final HapticPatternParser$$ExternalSyntheticLambda1 listener$ar$class_merging$405b3fa8_0$ar$class_merging$ar$class_merging;

    public LabelAddRequest(ExecutionList.RunnableExecutorPair runnableExecutorPair, Label label, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        super(runnableExecutorPair);
        this.label = label;
        this.listener$ar$class_merging$405b3fa8_0$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ Object doInBackground() {
        return this.mClient$ar$class_merging.insertLabel(this.label, 0);
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        Label label = (Label) obj;
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = this.listener$ar$class_merging$405b3fa8_0$ar$class_merging$ar$class_merging;
        if (hapticPatternParser$$ExternalSyntheticLambda1 != null && label != null) {
            hapticPatternParser$$ExternalSyntheticLambda1.onLabelsInPackageChanged(label.mPackageName);
        }
    }
}
