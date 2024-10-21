package com.google.android.libraries.consentverifier.initializer;

import android.util.Log;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import kotlinx.coroutines.CancellableContinuationImpl;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class Initializer$$ExternalSyntheticLambda0 implements OnFailureListener {
    public final /* synthetic */ Object Initializer$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ Initializer$$ExternalSyntheticLambda0(String str, int i) {
        this.switching_field = i;
        this.Initializer$$ExternalSyntheticLambda0$ar$f$0 = str;
    }

    @Override // com.google.android.gms.tasks.OnFailureListener
    public final void onFailure(Exception exc) {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                boolean z = Initializer.flagInitialized;
                Log.w("CBVerifier", String.format("Fail to register phenotypeflags for %s. %s", this.Initializer$$ExternalSyntheticLambda0$ar$f$0, exc));
                return;
            } else {
                LogUtils.e("ScreenOcrController", "Failed to recognize text ", exc);
                ((CancellableContinuationImpl) this.Initializer$$ExternalSyntheticLambda0$ar$f$0).cancel(null);
                return;
            }
        }
        boolean z2 = Initializer.flagInitialized;
        Log.w("CBVerifier", String.format("Committing phenotypeflags for %s failed. %s", this.Initializer$$ExternalSyntheticLambda0$ar$f$0, exc));
    }

    public Initializer$$ExternalSyntheticLambda0(CancellableContinuationImpl cancellableContinuationImpl, int i) {
        this.switching_field = i;
        this.Initializer$$ExternalSyntheticLambda0$ar$f$0 = cancellableContinuationImpl;
    }
}
