package com.google.android.accessibility.talkback.dynamicfeature;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.play.core.splitinstall.SplitInstallSessionState;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class SplitApkDownloader$$ExternalSyntheticLambda3 implements OnSuccessListener {
    public final /* synthetic */ Object SplitApkDownloader$$ExternalSyntheticLambda3$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ SplitApkDownloader$$ExternalSyntheticLambda3(Object obj, int i) {
        this.switching_field = i;
        this.SplitApkDownloader$$ExternalSyntheticLambda3$ar$f$0 = obj;
    }

    @Override // com.google.android.gms.tasks.OnSuccessListener
    public final void onSuccess(Object obj) {
        if (this.switching_field != 0) {
            List<SplitInstallSessionState> list = (List) obj;
            if (list == null) {
                LogUtils.w("FeatureDownloader", "No install session.", new Object[0]);
                return;
            }
            for (SplitInstallSessionState splitInstallSessionState : list) {
                List moduleNames = splitInstallSessionState.moduleNames();
                if (!moduleNames.isEmpty()) {
                    ((SplitApkDownloader) this.SplitApkDownloader$$ExternalSyntheticLambda3$ar$f$0).installStatus.put((String) moduleNames.get(0), SplitApkDownloader.getStatus(splitInstallSessionState.status()));
                }
            }
            return;
        }
        LogUtils.v("FeatureDownloader", "Started to download %s.", ((String[]) this.SplitApkDownloader$$ExternalSyntheticLambda3$ar$f$0)[0]);
    }
}
