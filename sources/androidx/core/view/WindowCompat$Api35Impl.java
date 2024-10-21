package androidx.core.view;

import android.util.TypedValue;
import android.view.Window;
import androidx.navigation.NavType;
import org.xmlpull.v1.XmlPullParserException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class WindowCompat$Api35Impl {
    public static final NavType checkNavType$navigation_runtime_release$ar$ds(TypedValue typedValue, NavType navType, NavType navType2, String str, String str2) {
        if (navType != null && navType != navType2) {
            throw new XmlPullParserException("Type is " + str + " but found " + str2 + ": " + typedValue.data);
        }
        if (navType == null) {
            return navType2;
        }
        return navType;
    }

    public static void setDecorFitsSystemWindows(Window window, boolean z) {
        window.setDecorFitsSystemWindows(z);
    }
}
