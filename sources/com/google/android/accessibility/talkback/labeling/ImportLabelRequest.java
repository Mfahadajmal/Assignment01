package com.google.android.accessibility.talkback.labeling;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.ContentValues;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.gms.common.api.internal.GooglePlayServicesUpdatedReceiver;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.util.concurrent.ExecutionList;
import com.google.mlkit.logging.schema.AggregatedOnDeviceTextDetectionLogEvent;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ImportLabelRequest extends LabelClientRequest {
    private final GooglePlayServicesUpdatedReceiver.Callback callback$ar$class_merging$6fee2b00_0$ar$class_merging;
    private final List labels;
    private final boolean overrideExistingLabels;

    public ImportLabelRequest(ExecutionList.RunnableExecutorPair runnableExecutorPair, List list, boolean z, GooglePlayServicesUpdatedReceiver.Callback callback) {
        super(runnableExecutorPair);
        this.labels = list;
        this.overrideExistingLabels = z;
        this.callback$ar$class_merging$6fee2b00_0$ar$class_merging = callback;
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.List, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.List, java.lang.Object] */
    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ Object doInBackground() {
        if (!this.mClient$ar$class_merging.isInitialized()) {
            return 0;
        }
        this.mClient$ar$class_merging.deleteLabels(2);
        this.mClient$ar$class_merging.updateSourceType$ar$ds(1);
        List currentLabels = this.mClient$ar$class_merging.getCurrentLabels();
        if (currentLabels == null) {
            currentLabels = new ArrayList();
        }
        AggregatedOnDeviceTextDetectionLogEvent aggregatedOnDeviceTextDetectionLogEvent = new AggregatedOnDeviceTextDetectionLogEvent(currentLabels, this.labels);
        ?? r0 = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$logEventKey;
        int size = r0.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            this.mClient$ar$class_merging.insertLabel((Label) r0.get(i2), 1);
            i++;
        }
        if (this.overrideExistingLabels) {
            ?? r5 = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$eventCount;
            int size2 = r5.size();
            for (int i3 = 0; i3 < size2; i3++) {
                Label label = (Label) r5.get(i3);
                ExecutionList.RunnableExecutorPair runnableExecutorPair = this.mClient$ar$class_merging;
                long j = label.mId;
                LogUtils.d("LabelProviderClient", "Updating label source type", new Object[0]);
                if (runnableExecutorPair.checkClient()) {
                    if (j == -1) {
                        LogUtils.w("LabelProviderClient", "Cannot update label with no ID.", new Object[0]);
                    } else {
                        Uri withAppendedId = ContentUris.withAppendedId((Uri) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$runnable, j);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("sourceType", (Integer) 2);
                        try {
                            ((ContentProviderClient) runnableExecutorPair.ExecutionList$RunnableExecutorPair$ar$next).update(withAppendedId, contentValues, null, null);
                        } catch (RemoteException e) {
                            LogUtils.e("LabelProviderClient", "RemoteException caught!", new Object[0]);
                            LogUtils.d("LabelProviderClient", e.toString(), new Object[0]);
                        }
                    }
                }
            }
            ?? r02 = aggregatedOnDeviceTextDetectionLogEvent.AggregatedOnDeviceTextDetectionLogEvent$ar$inferenceDurationStats;
            int size3 = r02.size();
            for (int i4 = 0; i4 < size3; i4++) {
                this.mClient$ar$class_merging.insertLabel((Label) r02.get(i4), 1);
                i++;
            }
        }
        return Integer.valueOf(i);
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        Integer num = (Integer) obj;
        if (num != null) {
            this.callback$ar$class_merging$6fee2b00_0$ar$class_merging.onLabelImported(num.intValue());
        }
    }
}
