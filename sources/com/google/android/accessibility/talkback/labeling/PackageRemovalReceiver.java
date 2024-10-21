package com.google.android.accessibility.talkback.labeling;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PackageRemovalReceiver extends BroadcastReceiver {
    public static final IntentFilter INTENT_FILTER;

    static {
        IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_REMOVED");
        INTENT_FILTER = intentFilter;
        intentFilter.addDataScheme("package");
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        if ("android.intent.action.PACKAGE_REMOVED".equals(intent.getAction()) && !intent.getBooleanExtra("android.intent.extra.REPLACING", false)) {
            CustomLabelManager customLabelManager = new CustomLabelManager(context);
            String str = "";
            if (intent.getData() != null) {
                str = intent.getData().toString().replace("package:", "");
            }
            LogUtils.v("PackageRemovalReceiver", "Package %s removed.  Discarding associated labels.", str);
            HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = new HapticPatternParser$$ExternalSyntheticLambda1(customLabelManager, null);
            if (customLabelManager.isInitialized()) {
                new LabelTask(new PackageLabelsFetchRequest(customLabelManager.client$ar$class_merging$ae701839_0, customLabelManager.context, str, hapticPatternParser$$ExternalSyntheticLambda1), customLabelManager.taskCallback$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging).execute(new Void[0]);
            }
        }
    }
}
