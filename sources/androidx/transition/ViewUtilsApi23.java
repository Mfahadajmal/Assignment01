package androidx.transition;

import android.os.Build;
import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
class ViewUtilsApi23 extends ViewUtilsApi22 {
    private static boolean sTryHiddenSetTransitionVisibility = true;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api29Impl {
        static void setTransitionVisibility(View view, int i) {
            view.setTransitionVisibility(i);
        }
    }

    @Override // androidx.transition.ViewUtilsApi19
    public void setTransitionVisibility(View view, int i) {
        if (Build.VERSION.SDK_INT == 28) {
            super.setTransitionVisibility(view, i);
        } else if (sTryHiddenSetTransitionVisibility) {
            try {
                Api29Impl.setTransitionVisibility(view, i);
            } catch (NoSuchMethodError unused) {
                sTryHiddenSetTransitionVisibility = false;
            }
        }
    }
}
