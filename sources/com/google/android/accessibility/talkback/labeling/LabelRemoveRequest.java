package com.google.android.accessibility.talkback.labeling;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LabelRemoveRequest extends LabelClientRequest {
    private final Label label;
    private final HapticPatternParser$$ExternalSyntheticLambda1 listener$ar$class_merging$405b3fa8_0$ar$class_merging$ar$class_merging;

    public LabelRemoveRequest(ExecutionList.RunnableExecutorPair runnableExecutorPair, Label label, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        super(runnableExecutorPair);
        this.label = label;
        this.listener$ar$class_merging$405b3fa8_0$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ Object doInBackground() {
        boolean z = false;
        LogUtils.v("LabelRemoveRequest", "Spawning new LabelRemoveRequest(%d) for label: %s", Integer.valueOf(hashCode()), this.label);
        Label label = this.label;
        if (label != null) {
            long j = label.mId;
            if (j != -1) {
                ExecutionList.RunnableExecutorPair runnableExecutorPair = this.mClient$ar$class_merging;
                LogUtils.d("LabelProviderClient", "Deleting label: %s.", Long.valueOf(j));
                if (runnableExecutorPair.checkClient()) {
                    if (j == -1) {
                        LogUtils.w("LabelProviderClient", "Cannot delete label with no ID.", new Object[0]);
                    } else {
                        try {
                            if (((ContentProviderClient) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next).delete(ContentUris.withAppendedId((Uri) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable, j), null, null) > 0) {
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
        }
        return false;
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        Boolean bool = (Boolean) obj;
        LogUtils.v("LabelRemoveRequest", "LabelRemoveRequest(%d) complete. Result: %s", Integer.valueOf(hashCode()), bool);
        if (this.listener$ar$class_merging$405b3fa8_0$ar$class_merging$ar$class_merging != null && bool.booleanValue()) {
            this.listener$ar$class_merging$405b3fa8_0$ar$class_merging$ar$class_merging.onLabelsInPackageChanged(this.label.mPackageName);
        }
    }
}
