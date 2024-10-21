package com.google.android.accessibility.utils.compat;

import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CompatUtils {
    private static final String TAG = "CompatUtils";

    private CompatUtils() {
    }

    public static Class getClass(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static Method getMethod(Class cls, String str, Class... clsArr) {
        if (cls != null && !TextUtils.isEmpty(str)) {
            try {
                return cls.getDeclaredMethod(str, clsArr);
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static Object invoke(Object obj, Object obj2, Method method, Object... objArr) {
        if (method == null) {
            return obj2;
        }
        try {
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            Log.e(TAG, "Exception in invoke: ".concat(String.valueOf(e.getClass().getSimpleName())));
            return obj2;
        }
    }
}
