package com.google.mlkit.logging.schema;

import android.content.Context;
import android.util.Log;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TranslatorOptions {
    public static int checkPermission(Context context, String str, int i, int i2) {
        try {
            return context.checkPermission(str, i, i2);
        } catch (RuntimeException unused) {
            return -1;
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (Log.isLoggable(str, 3)) {
            int length = objArr.length;
            Throwable th = null;
            if (length != 0) {
                Object obj = objArr[0];
                if (obj instanceof Throwable) {
                    th = (Throwable) obj;
                }
            }
            if (th == null && length > 0) {
                String.format(Locale.US, str2, objArr);
            }
        }
    }
}
