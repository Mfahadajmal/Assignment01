package com.google.android.accessibility.talkback.labeling;

import android.app.Activity;
import com.google.android.accessibility.talkback.labeling.LabelManagerSummaryActivity;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.common.util.concurrent.ExecutionList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HasImportedLabelsRequest extends LabelClientRequest {
    private final HapticPatternParser$$ExternalSyntheticLambda1 listener$ar$class_merging$7fd8f04b_0$ar$class_merging$ar$class_merging$ar$class_merging;

    public HasImportedLabelsRequest(ExecutionList.RunnableExecutorPair runnableExecutorPair, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        super(runnableExecutorPair);
        this.listener$ar$class_merging$7fd8f04b_0$ar$class_merging$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0035, code lost:
    
        if (r3 != null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
    
        r3.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x0048, code lost:
    
        if (r3 == null) goto L20;
     */
    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final /* bridge */ /* synthetic */ java.lang.Object doInBackground() {
        /*
            r10 = this;
            r0 = 0
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r2 = "LabelProviderClient"
            java.lang.String r3 = "Has imported label request"
            com.google.android.libraries.accessibility.utils.log.LogUtils.d(r2, r3, r1)
            com.google.common.util.concurrent.ExecutionList$RunnableExecutorPair r1 = r10.mClient$ar$class_merging
            boolean r3 = r1.checkClient()
            if (r3 != 0) goto L13
            goto L4d
        L13:
            r3 = 0
            java.lang.String r7 = "sourceType = 1"
            java.lang.Object r4 = r1.ExecutionList$RunnableExecutorPair$ar$next     // Catch: java.lang.Throwable -> L31 android.os.RemoteException -> L33
            java.lang.Object r1 = r1.ExecutionList$RunnableExecutorPair$ar$runnable     // Catch: java.lang.Throwable -> L31 android.os.RemoteException -> L33
            java.lang.String[] r6 = com.google.android.accessibility.utils.labeling.LabelsTable.ALL_COLUMNS     // Catch: java.lang.Throwable -> L31 android.os.RemoteException -> L33
            r5 = r1
            android.net.Uri r5 = (android.net.Uri) r5     // Catch: java.lang.Throwable -> L31 android.os.RemoteException -> L33
            android.content.ContentProviderClient r4 = (android.content.ContentProviderClient) r4     // Catch: java.lang.Throwable -> L31 android.os.RemoteException -> L33
            r8 = 0
            r9 = 0
            android.database.Cursor r3 = r4.query(r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> L31 android.os.RemoteException -> L33
            if (r3 == 0) goto L35
            int r1 = r3.getCount()     // Catch: java.lang.Throwable -> L31 android.os.RemoteException -> L33
            if (r1 <= 0) goto L35
            r0 = 1
            goto L35
        L31:
            r0 = move-exception
            goto L52
        L33:
            r1 = move-exception
            goto L38
        L35:
            if (r3 == 0) goto L4d
            goto L4a
        L38:
            java.lang.String r4 = "RemoteException caught!"
            java.lang.Object[] r5 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L31
            com.google.android.libraries.accessibility.utils.log.LogUtils.e(r2, r4, r5)     // Catch: java.lang.Throwable -> L31
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> L31
            java.lang.Object[] r4 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L31
            com.google.android.libraries.accessibility.utils.log.LogUtils.d(r2, r1, r4)     // Catch: java.lang.Throwable -> L31
            if (r3 == 0) goto L4d
        L4a:
            r3.close()
        L4d:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        L52:
            if (r3 == 0) goto L57
            r3.close()
        L57:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.talkback.labeling.HasImportedLabelsRequest.doInBackground():java.lang.Object");
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        Boolean bool = (Boolean) obj;
        if (bool != null) {
            LabelManagerSummaryActivity.PackageLabelInfoAdapter packageLabelInfoAdapter = (LabelManagerSummaryActivity.PackageLabelInfoAdapter) this.listener$ar$class_merging$7fd8f04b_0$ar$class_merging$ar$class_merging$ar$class_merging.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0;
            Activity activity = packageLabelInfoAdapter.activity;
            boolean booleanValue = bool.booleanValue();
            if (!activity.isDestroyed()) {
                packageLabelInfoAdapter.revertButton.setEnabled(booleanValue);
            }
        }
    }
}
