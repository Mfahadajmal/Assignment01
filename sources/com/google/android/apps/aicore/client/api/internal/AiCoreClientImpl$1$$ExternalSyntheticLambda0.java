package com.google.android.apps.aicore.client.api.internal;

import android.view.View;
import androidx.core.view.ViewCompat;
import com.google.android.apps.aicore.aidl.IDownloadListener2;
import com.google.android.apps.aicore.client.api.AiCoreException;
import com.google.android.apps.aicore.client.api.AiFeature;
import com.google.android.apps.aicore.client.api.DownloadCallback;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class AiCoreClientImpl$1$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Object AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$b16e4a0c_0;
    public final /* synthetic */ Object AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$1;
    public final /* synthetic */ Object AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$2;
    public final /* synthetic */ Object AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$4;
    public final /* synthetic */ int f$3;
    private final /* synthetic */ int switching_field;

    public AiCoreClientImpl$1$$ExternalSyntheticLambda0(int i, ArrayList arrayList, ArrayList arrayList2, ArrayList arrayList3, ArrayList arrayList4, int i2) {
        this.switching_field = i2;
        this.f$3 = i;
        this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$2 = arrayList;
        this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$b16e4a0c_0 = arrayList2;
        this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$1 = arrayList3;
        this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$4 = arrayList4;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.google.android.apps.aicore.client.api.DownloadCallback, java.lang.Object] */
    @Override // java.lang.Runnable
    public final void run() {
        if (this.switching_field != 0) {
            for (int i = 0; i < this.f$3; i++) {
                ViewCompat.Api21Impl.setTransitionName((View) ((ArrayList) this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$2).get(i), (String) ((ArrayList) this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$b16e4a0c_0).get(i));
                ViewCompat.Api21Impl.setTransitionName((View) ((ArrayList) this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$1).get(i), (String) ((ArrayList) this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$4).get(i));
            }
            return;
        }
        this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$1.onDownloadFailed$ar$ds();
    }

    public /* synthetic */ AiCoreClientImpl$1$$ExternalSyntheticLambda0(IDownloadListener2.Stub stub, DownloadCallback downloadCallback, AiFeature aiFeature, int i, AiCoreException aiCoreException, int i2) {
        this.switching_field = i2;
        this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$0$ar$class_merging$b16e4a0c_0 = stub;
        this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$1 = downloadCallback;
        this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$2 = aiFeature;
        this.f$3 = i;
        this.AiCoreClientImpl$1$$ExternalSyntheticLambda0$ar$f$4 = aiCoreException;
    }
}
