package com.google.android.libraries.accessibility.utils.log;

import _COROUTINE._BOUNDARY;
import android.util.Log;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.IllegalFormatException;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class LogUtils {
    public static String logTagPrefix = "";
    public static int minLogLevel = 6;
    public static ParameterCustomizer parameterCustomizer;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface ParameterCustomizer {
        Object customize(Object obj);
    }

    public static void d(String str, String str2, Object... objArr) {
        log(str, 3, str2, objArr);
    }

    public static void e(String str, String str2, Object... objArr) {
        log(str, 6, str2, objArr);
    }

    public static void i(String str, String str2, Object... objArr) {
        log(str, 4, str2, objArr);
    }

    public static void log(String str, int i, String str2, Object... objArr) {
        log(str, i, null, str2, objArr);
    }

    public static void logWithLimit$ar$ds(int i, int i2, String str, Object... objArr) {
        String format;
        if (i2 > 10) {
            return;
        }
        if (i2 == 10) {
            format = String.format("%s (%d); further messages suppressed", str, 10);
        } else {
            format = String.format("%s (%d)", str, Integer.valueOf(i2));
        }
        log("FailoverTextToSpeech", i, format, objArr);
    }

    public static boolean shouldLog$ar$ds() {
        if (minLogLevel <= 2) {
            return true;
        }
        return false;
    }

    public static void v(String str, String str2, Object... objArr) {
        log(str, 2, str2, objArr);
    }

    public static void w(String str, String str2, Object... objArr) {
        log(str, 5, str2, objArr);
    }

    public static void e(String str, Throwable th, String str2, Object... objArr) {
        log(str, 6, th, str2, objArr);
    }

    public static void log(String str, int i, Throwable th, String str2, Object... objArr) {
        if (i < minLogLevel) {
            return;
        }
        String valueOf = String.valueOf(logTagPrefix);
        String valueOf2 = String.valueOf(str);
        if (parameterCustomizer != null) {
            for (int i2 = 0; i2 < objArr.length; i2++) {
                objArr[i2] = parameterCustomizer.customize(objArr[i2]);
            }
        }
        try {
            String format = String.format(ContextDataProvider.nullToEmpty(str2), objArr);
            String concat = valueOf.concat(valueOf2);
            if (th == null) {
                Log.println(i, concat, format);
            } else {
                Log.println(i, concat, String.format("%s\n%s", format, Log.getStackTraceString(th)));
            }
        } catch (IllegalFormatException e) {
            Log.e("LogUtils", _BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_4(str2, "Bad formatting string: \"", "\""), e);
        }
    }
}
