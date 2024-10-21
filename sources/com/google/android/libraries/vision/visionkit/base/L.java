package com.google.android.libraries.vision.visionkit.base;

import android.util.Log;
import com.google.common.flogger.context.ContextDataProvider;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class L {
    public static final L log = new L();
    private final String tag;

    public L() {
        ContextDataProvider.checkArgument(true, (Object) "Android Logging mandates tags be less than 23 characters.");
        this.tag = "VisionKit";
    }

    private final String formatMessage(Object obj, String str, Object... objArr) {
        String str2;
        String formatMessage$ar$ds = formatMessage$ar$ds(str, objArr);
        if (obj instanceof String) {
            str2 = (String) obj;
        } else {
            String name = obj.getClass().getName();
            if (obj instanceof Class) {
                name = ((Class) obj).getName();
            }
            String[] split = name.split("\\.");
            int length = split.length;
            if (length == 0) {
                str2 = "";
            } else {
                str2 = split[length - 1];
            }
        }
        return "[" + str2 + "] " + formatMessage$ar$ds;
    }

    private static final String formatMessage$ar$ds(String str, Object... objArr) {
        if (objArr.length > 0) {
            return String.format(str, objArr);
        }
        return str;
    }

    private final boolean shouldLog(int i) {
        if (Log.isLoggable(this.tag, i)) {
            return true;
        }
        return false;
    }

    public final void e$ar$ds(Throwable th, Object... objArr) {
        if (shouldLog(6)) {
            Log.e(this.tag, formatMessage$ar$ds("Error in result from JNI layer", objArr), th);
        }
    }

    public final void i(Object obj, String str, Object... objArr) {
        if (shouldLog(4)) {
            formatMessage(obj, str, objArr);
        }
    }

    public final void v(Object obj, String str, Object... objArr) {
        if (shouldLog(2)) {
            formatMessage(obj, str, objArr);
        }
    }

    public final void w(Object obj, String str, Object... objArr) {
        if (shouldLog(5)) {
            Log.w(this.tag, formatMessage(obj, str, objArr));
        }
    }
}
