package androidx.core.content;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.util.Log;
import android.view.Display;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ContextCompat$Api30Impl {
    static Context createAttributionContext(Context context, String str) {
        return context.createAttributionContext(str);
    }

    public static String getAttributionTag(Context context) {
        return context.getAttributionTag();
    }

    static Display getDisplayOrDefault(Context context) {
        try {
            return context.getDisplay();
        } catch (UnsupportedOperationException unused) {
            Log.w("ContextCompat", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_1(context, "The context:", " is not associated with any display. Return a fallback display instead."));
            return ((DisplayManager) context.getSystemService(DisplayManager.class)).getDisplay(0);
        }
    }
}
