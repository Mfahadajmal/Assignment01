package com.google.common.android.concurrent;

import android.app.UiModeManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.common.base.Supplier;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class FutureCallbackRegistry$$ExternalSyntheticLambda0 implements Supplier {
    public final /* synthetic */ Object FutureCallbackRegistry$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ FutureCallbackRegistry$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.FutureCallbackRegistry$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.Object, androidx.lifecycle.ViewModelStoreOwner] */
    @Override // com.google.common.base.Supplier
    public final Object get() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                return new ViewModelProvider(this.FutureCallbackRegistry$$ExternalSyntheticLambda0$ar$f$0);
            }
            long j = SurveyUtils.TIMEOUT_MS;
            return (UiModeManager) ((Context) this.FutureCallbackRegistry$$ExternalSyntheticLambda0$ar$f$0).getSystemService("uimode");
        }
        return ((Fragment) this.FutureCallbackRegistry$$ExternalSyntheticLambda0$ar$f$0).getChildFragmentManager();
    }
}
