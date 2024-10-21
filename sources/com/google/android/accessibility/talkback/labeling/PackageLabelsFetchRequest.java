package com.google.android.accessibility.talkback.labeling;

import android.content.Context;
import android.content.pm.PackageManager;
import androidx.preference.Preference;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.labeling.Label;
import com.google.android.accessibility.utils.output.HapticPatternParser$$ExternalSyntheticLambda1;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.util.concurrent.ExecutionList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PackageLabelsFetchRequest extends LabelClientRequest {
    private final Context context;
    private final HapticPatternParser$$ExternalSyntheticLambda1 onLabelFetchedListener$ar$class_merging$1915ea8e_0$ar$class_merging$ar$class_merging;
    private final String packageName;

    public PackageLabelsFetchRequest(ExecutionList.RunnableExecutorPair runnableExecutorPair, Context context, String str, HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1) {
        super(runnableExecutorPair);
        this.context = context;
        this.packageName = str;
        this.onLabelFetchedListener$ar$class_merging$1915ea8e_0$ar$class_merging$ar$class_merging = hapticPatternParser$$ExternalSyntheticLambda1;
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ Object doInBackground() {
        int i;
        try {
            i = this.context.getPackageManager().getPackageInfo(this.packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtils.w("PackageLabelsFetchReq", "Unable to resolve package info during prefetch for %s", this.packageName);
            i = Preference.DEFAULT_ORDER;
        }
        return this.mClient$ar$class_merging.getLabelsForPackage(this.packageName, SpannableUtils$IdentifierSpan.getDefaultLocale(), i);
    }

    @Override // com.google.android.accessibility.talkback.labeling.LabelClientRequest
    public final /* bridge */ /* synthetic */ void onPostExecute(Object obj) {
        HapticPatternParser$$ExternalSyntheticLambda1 hapticPatternParser$$ExternalSyntheticLambda1 = this.onLabelFetchedListener$ar$class_merging$1915ea8e_0$ar$class_merging$ar$class_merging;
        Map map = (Map) obj;
        if (map != null && !map.isEmpty()) {
            Collection values = map.values();
            LogUtils.v("PackageRemovalReceiver", "Removing %d labels.", Integer.valueOf(values.size()));
            Iterator it = values.iterator();
            while (it.hasNext()) {
                ((CustomLabelManager) hapticPatternParser$$ExternalSyntheticLambda1.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).removeLabel((Label) it.next());
            }
        }
        ((TalkBackLabelManager) hapticPatternParser$$ExternalSyntheticLambda1.HapticPatternParser$$ExternalSyntheticLambda1$ar$f$0).shutdown();
    }
}
