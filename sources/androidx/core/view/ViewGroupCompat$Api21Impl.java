package androidx.core.view;

import android.view.ViewGroup;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewGroupCompat$Api21Impl {
    static int getNestedScrollAxes(ViewGroup viewGroup) {
        return viewGroup.getNestedScrollAxes();
    }

    public static boolean isTransitionGroup(ViewGroup viewGroup) {
        return viewGroup.isTransitionGroup();
    }

    static void setTransitionGroup(ViewGroup viewGroup, boolean z) {
        viewGroup.setTransitionGroup(z);
    }
}
