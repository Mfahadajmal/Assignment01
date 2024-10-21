package androidx.transition;

import android.os.Build;
import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
final class ViewGroupUtils {
    private static boolean sTryHiddenSuppressLayout = true;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class Api29Impl {
        static int getChildDrawingOrder(ViewGroup viewGroup, int i) {
            return viewGroup.getChildDrawingOrder(i);
        }

        static void suppressLayout(ViewGroup viewGroup, boolean z) {
            viewGroup.suppressLayout(z);
        }
    }

    private static void hiddenSuppressLayout(ViewGroup viewGroup, boolean z) {
        if (sTryHiddenSuppressLayout) {
            try {
                Api29Impl.suppressLayout(viewGroup, z);
            } catch (NoSuchMethodError unused) {
                sTryHiddenSuppressLayout = false;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void suppressLayout(ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            Api29Impl.suppressLayout(viewGroup, z);
        } else {
            hiddenSuppressLayout(viewGroup, z);
        }
    }
}
