package com.google.android.libraries.stitch.util;

import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SystemProperties {
    public static final Method sGetStringMethod;

    static {
        Method method = null;
        try {
            try {
                Class<?> cls = Class.forName("android.os.SystemProperties");
                method = cls.getMethod("get", String.class, String.class);
                cls.getMethod("getInt", String.class, Integer.TYPE);
                cls.getMethod("getLong", String.class, Long.TYPE);
                cls.getMethod("getBoolean", String.class, Boolean.TYPE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } finally {
            sGetStringMethod = method;
        }
    }
}
