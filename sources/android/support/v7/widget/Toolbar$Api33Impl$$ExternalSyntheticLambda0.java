package android.support.v7.widget;

import android.support.v7.app.AppCompatDelegateImpl;
import android.window.OnBackInvokedCallback;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class Toolbar$Api33Impl$$ExternalSyntheticLambda0 implements OnBackInvokedCallback {
    public final /* synthetic */ Object Toolbar$Api33Impl$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ Toolbar$Api33Impl$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.Toolbar$Api33Impl$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, java.lang.Runnable] */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, kotlin.jvm.functions.Function0] */
    public final void onBackInvoked() {
        int i = this.switching_field;
        if (i != 0) {
            if (i != 1) {
                this.Toolbar$Api33Impl$$ExternalSyntheticLambda0$ar$f$0.invoke();
                return;
            } else {
                ((AppCompatDelegateImpl) this.Toolbar$Api33Impl$$ExternalSyntheticLambda0$ar$f$0).onBackPressed();
                return;
            }
        }
        this.Toolbar$Api33Impl$$ExternalSyntheticLambda0$ar$f$0.run();
    }
}
