package androidx.core.view;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewParent;
import androidx.navigation.ActivityNavigator$hostActivity$1;
import androidx.navigation.NavDestination;
import com.google.mlkit.logging.schema.OnDeviceSmartReplyLogEvent;
import kotlin.sequences.Sequence;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ViewParentCompat$Api21Impl {
    public static final String createRoute$ar$ds(String str) {
        if (str != null) {
            return "android-app://androidx.navigation/".concat(str);
        }
        return "";
    }

    public static final String getDisplayName$ar$ds(Context context, int i) {
        String valueOf;
        if (i <= 16777215) {
            return String.valueOf(i);
        }
        try {
            valueOf = context.getResources().getResourceName(i);
        } catch (Resources.NotFoundException unused) {
            valueOf = String.valueOf(i);
        }
        valueOf.getClass();
        return valueOf;
    }

    public static final Sequence getHierarchy$ar$ds(NavDestination navDestination) {
        navDestination.getClass();
        return OnDeviceSmartReplyLogEvent.generateSequence(navDestination, ActivityNavigator$hostActivity$1.INSTANCE$ar$class_merging$cf812260_0);
    }

    public static boolean onNestedFling(ViewParent viewParent, View view, float f, float f2, boolean z) {
        return viewParent.onNestedFling(view, f, f2, z);
    }

    public static boolean onNestedPreFling(ViewParent viewParent, View view, float f, float f2) {
        return viewParent.onNestedPreFling(view, f, f2);
    }

    public static void onNestedPreScroll(ViewParent viewParent, View view, int i, int i2, int[] iArr) {
        viewParent.onNestedPreScroll(view, i, i2, iArr);
    }

    public static void onNestedScroll(ViewParent viewParent, View view, int i, int i2, int i3, int i4) {
        viewParent.onNestedScroll(view, i, i2, i3, i4);
    }

    public static void onNestedScrollAccepted(ViewParent viewParent, View view, View view2, int i) {
        viewParent.onNestedScrollAccepted(view, view2, i);
    }

    public static boolean onStartNestedScroll(ViewParent viewParent, View view, View view2, int i) {
        return viewParent.onStartNestedScroll(view, view2, i);
    }

    public static void onStopNestedScroll(ViewParent viewParent, View view) {
        viewParent.onStopNestedScroll(view);
    }
}
