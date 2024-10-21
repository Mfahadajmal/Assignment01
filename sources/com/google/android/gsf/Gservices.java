package com.google.android.gsf;

import android.content.ContentResolver;
import android.net.Uri;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Gservices {
    public static final GservicesQueryCachingDelegate sDelegate$ar$class_merging;

    static {
        Uri uri = GservicesConstants.CONTENT_URI;
        sDelegate$ar$class_merging = GservicesDelegateSupplier.get$ar$class_merging$6aa2c5a8_0();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x006e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean getBoolean$ar$ds$5696552e_0(android.content.ContentResolver r6, java.lang.String r7) {
        /*
            com.google.android.gsf.GservicesQueryCachingDelegate r0 = com.google.android.gsf.Gservices.sDelegate$ar$class_merging
            com.google.android.gsf.GservicesQueryCachingDelegate.getContentResolver$ar$ds(r6)
            monitor-enter(r0)
            r0.ensureCacheInitializedLocked(r6)     // Catch: java.lang.Throwable -> L79
            java.lang.Object r1 = r0.mVersionToken     // Catch: java.lang.Throwable -> L79
            java.util.HashMap r2 = r0.mBooleanCache     // Catch: java.lang.Throwable -> L79
            r3 = 0
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)     // Catch: java.lang.Throwable -> L79
            java.lang.Object r2 = com.google.android.gsf.GservicesQueryCachingDelegate.getValueLocked$ar$ds(r2, r7, r4)     // Catch: java.lang.Throwable -> L79
            java.lang.Boolean r2 = (java.lang.Boolean) r2     // Catch: java.lang.Throwable -> L79
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L79
            if (r2 == 0) goto L20
            boolean r6 = r2.booleanValue()
            goto L75
        L20:
            java.lang.String r6 = r0.getString$ar$ds(r6, r7)
            if (r6 == 0) goto L6c
            boolean r5 = r6.isEmpty()
            if (r5 == 0) goto L2d
            goto L6c
        L2d:
            java.util.regex.Pattern r5 = com.google.android.gsf.GservicesConstants.TRUE_PATTERN
            java.util.regex.Matcher r5 = r5.matcher(r6)
            boolean r5 = r5.matches()
            if (r5 == 0) goto L3f
            r3 = 1
            java.lang.Boolean r4 = java.lang.Boolean.valueOf(r3)
            goto L6d
        L3f:
            java.util.regex.Pattern r5 = com.google.android.gsf.GservicesConstants.FALSE_PATTERN
            java.util.regex.Matcher r5 = r5.matcher(r6)
            boolean r5 = r5.matches()
            if (r5 == 0) goto L4c
            goto L6d
        L4c:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "attempt to read Gservices key "
            r4.<init>(r5)
            r4.append(r7)
            java.lang.String r5 = " (value \""
            r4.append(r5)
            r4.append(r6)
            java.lang.String r6 = "\") as boolean"
            r4.append(r6)
            java.lang.String r6 = r4.toString()
            java.lang.String r4 = "Gservices"
            android.util.Log.w(r4, r6)
        L6c:
            r4 = r2
        L6d:
            monitor-enter(r0)
            java.util.HashMap r6 = r0.mBooleanCache     // Catch: java.lang.Throwable -> L76
            r0.putValueAndRemoveFromStringCacheLocked(r1, r6, r7, r4)     // Catch: java.lang.Throwable -> L76
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L76
            r6 = r3
        L75:
            return r6
        L76:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L76
            throw r6
        L79:
            r6 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L79
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gsf.Gservices.getBoolean$ar$ds$5696552e_0(android.content.ContentResolver, java.lang.String):boolean");
    }

    public static long getLong$ar$ds(ContentResolver contentResolver) {
        Object obj;
        long j;
        Long l;
        GservicesQueryCachingDelegate gservicesQueryCachingDelegate = sDelegate$ar$class_merging;
        GservicesQueryCachingDelegate.getContentResolver$ar$ds(contentResolver);
        synchronized (gservicesQueryCachingDelegate) {
            gservicesQueryCachingDelegate.ensureCacheInitializedLocked(contentResolver);
            obj = gservicesQueryCachingDelegate.mVersionToken;
            j = 0;
            l = (Long) GservicesQueryCachingDelegate.getValueLocked$ar$ds(gservicesQueryCachingDelegate.mLongCache, "android_id", 0L);
        }
        if (l != null) {
            return l.longValue();
        }
        String string$ar$ds = gservicesQueryCachingDelegate.getString$ar$ds(contentResolver, "android_id");
        if (string$ar$ds != null) {
            try {
                long parseLong = Long.parseLong(string$ar$ds);
                l = Long.valueOf(parseLong);
                j = parseLong;
            } catch (NumberFormatException unused) {
            }
        }
        synchronized (gservicesQueryCachingDelegate) {
            gservicesQueryCachingDelegate.putValueAndRemoveFromStringCacheLocked(obj, gservicesQueryCachingDelegate.mLongCache, "android_id", l);
        }
        return j;
    }
}
