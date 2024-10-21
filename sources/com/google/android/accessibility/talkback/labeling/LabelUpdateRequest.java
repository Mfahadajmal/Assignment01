package com.google.android.accessibility.talkback.labeling;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LabelUpdateRequest extends LabelClientRequest {
    private final Label label;
    private final HapticPatternParser$$ExternalSyntheticLambda1 listener$ar$class_merging$405b3fa8_0$ar$class_merging$ar$class_merging;

    public LabelUpdateRequest(ExecutionList.RunnableExecutorPair runnableExecutorPair, Label label, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        super(runnableExecutorPair);
        this.label = label;
        this.listener$ar$class_merging$405b3fa8_0$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ Object doInBackground() {
        boolean z = false;
        LogUtils.v("LabelUpdateRequest", "Spawning new LabelUpdateRequest(%d) for label: %s", Integer.valueOf(hashCode()), this.label);
        Label label = this.label;
        if (label.mId == -1) {
            return false;
        }
        ExecutionList.RunnableExecutorPair runnableExecutorPair = this.mClient$ar$class_merging;
        LogUtils.d("LabelProviderClient", "Updating label: %s.", label);
        if (runnableExecutorPair.checkClient()) {
            long j = label.mId;
            if (j == -1) {
                LogUtils.w("LabelProviderClient", "Cannot update label with no ID.", new Object[0]);
            } else {
                Uri withAppendedId = ContentUris.withAppendedId((Uri) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable, j);
                ContentValues buildContentValuesForLabel = ExecutionList.RunnableExecutorPair.buildContentValuesForLabel(label);
                buildContentValuesForLabel.put("sourceType", (Integer) 0);
                try {
                    if (((ContentProviderClient) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next).update(withAppendedId, buildContentValuesForLabel, null, null) > 0) {
                        z = true;
                    }
                } catch (RemoteException e) {
                    LogUtils.e("LabelProviderClient", "RemoteException caught!", new Object[0]);
                    LogUtils.d("LabelProviderClient", e.toString(), new Object[0]);
                }
            }
        }
        if (z) {
            ExecutionList.RunnableExecutorPair runnableExecutorPair2 = this.mClient$ar$class_merging;
            Label label2 = this.label;
            runnableExecutorPair2.deleteLabel$ar$ds(label2.mPackageName, label2.mViewName, label2.mLocale, label2.mPackageVersion);
        }
        return Boolean.valueOf(z);
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        Boolean bool = (Boolean) obj;
        LogUtils.v("LabelUpdateRequest", "LabelUpdateRequest(%d) complete. Result: %s", Integer.valueOf(hashCode()), bool);
        if (this.listener$ar$class_merging$405b3fa8_0$ar$class_merging$ar$class_merging != null && bool.booleanValue()) {
            this.listener$ar$class_merging$405b3fa8_0$ar$class_merging$ar$class_merging.onLabelsInPackageChanged(this.label.mPackageName);
        }
    }
}
