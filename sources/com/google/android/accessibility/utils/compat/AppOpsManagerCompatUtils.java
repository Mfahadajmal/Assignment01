package com.google.android.accessibility.utils.compat;

import android.app.AppOpsManager;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AppOpsManagerCompatUtils {
    private static final Class CLASS = AppOpsManager.class;
    private static final Field FIELD_OP_PROJECT_MEDIA;
    public static final Method METHOD_checkOpNoThrow;
    public static final int OP_PROJECT_MEDIA;

    /* JADX WARN: Removed duplicated region for block: B:6:0x0035 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    static {
        /*
            java.lang.Class<android.app.AppOpsManager> r0 = android.app.AppOpsManager.class
            com.google.android.accessibility.utils.compat.AppOpsManagerCompatUtils.CLASS = r0
            r1 = 3
            java.lang.Class[] r1 = new java.lang.Class[r1]
            java.lang.Class r2 = java.lang.Integer.TYPE
            r3 = 0
            r1[r3] = r2
            r3 = 1
            r1[r3] = r2
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            r3 = 2
            r1[r3] = r2
            java.lang.String r2 = "checkOpNoThrow"
            java.lang.reflect.Method r1 = com.google.android.accessibility.utils.compat.CompatUtils.getMethod(r0, r2, r1)
            com.google.android.accessibility.utils.compat.AppOpsManagerCompatUtils.METHOD_checkOpNoThrow = r1
            java.lang.String r1 = "OP_PROJECT_MEDIA"
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            r3 = 0
            if (r2 == 0) goto L27
        L25:
            r0 = r3
            goto L2b
        L27:
            java.lang.reflect.Field r0 = r0.getDeclaredField(r1)     // Catch: java.lang.Exception -> L25
        L2b:
            com.google.android.accessibility.utils.compat.AppOpsManagerCompatUtils.FIELD_OP_PROJECT_MEDIA = r0
            r1 = -1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            if (r0 != 0) goto L35
            goto L39
        L35:
            java.lang.Object r1 = r0.get(r3)     // Catch: java.lang.Exception -> L39
        L39:
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r0 = r1.intValue()
            com.google.android.accessibility.utils.compat.AppOpsManagerCompatUtils.OP_PROJECT_MEDIA = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.accessibility.utils.compat.AppOpsManagerCompatUtils.<clinit>():void");
    }
}
