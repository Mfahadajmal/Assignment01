package com.google.mlkit.common.sdkinternal;

import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.mlkit.vision.common.internal.MobileVisionBase;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class OptionalModuleUtils$$ExternalSyntheticLambda1 implements OnFailureListener {
    private final /* synthetic */ int switching_field;

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                MobileVisionBase.GMS_LOGGER.e("MobileVisionBase", "Error preloading model resource", exc);
                return;
            } else {
                LogUtils.e("FeatureDownloader", "Fail to get session states. %s", exc);
                return;
            }
        }
        Log.e("OptionalModuleUtils", "Failed to request modules install request", exc);
    }
}
