package com.google.android.gms.common.internal;

import j$.util.concurrent.ConcurrentHashMap;

/* compiled from: PG */
@Deprecated
/* loaded from: classes.dex */
public final class LibraryVersion {
    private final ConcurrentHashMap libraryVersionMap = new ConcurrentHashMap();
    private static final GmsLogger LOGGER = new GmsLogger("LibraryVersion", "");
    public static final LibraryVersion INSTANCE = new LibraryVersion();

    protected LibraryVersion() {
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0084  */
    @java.lang.Deprecated
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String getVersion(java.lang.String r9) {
        /*
            r8 = this;
            java.lang.String r0 = "Failed to get app version for libraryName: "
            java.lang.String r1 = "LibraryVersion"
            java.lang.String r2 = "Please provide a valid libraryName"
            com.google.android.gms.common.util.StrictModeUtils$VmPolicyBuilderCompatS.checkNotEmpty$ar$ds$c11d1227_0(r9, r2)
            j$.util.concurrent.ConcurrentHashMap r2 = r8.libraryVersionMap
            boolean r2 = r2.containsKey(r9)
            if (r2 == 0) goto L1a
            j$.util.concurrent.ConcurrentHashMap r0 = r8.libraryVersionMap
            java.lang.Object r9 = r0.get(r9)
            java.lang.String r9 = (java.lang.String) r9
            return r9
        L1a:
            java.util.Properties r2 = new java.util.Properties
            r2.<init>()
            r3 = 0
            java.lang.String r4 = "/%s.properties"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f
            r6 = 0
            r5[r6] = r9     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f
            java.lang.Class<com.google.android.gms.common.internal.LibraryVersion> r5 = com.google.android.gms.common.internal.LibraryVersion.class
            java.io.InputStream r4 = r5.getResourceAsStream(r4)     // Catch: java.lang.Throwable -> L6d java.io.IOException -> L6f
            if (r4 == 0) goto L50
            r2.load(r4)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            java.lang.String r5 = "version"
            java.lang.String r3 = r2.getProperty(r5, r3)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            com.google.android.gms.common.internal.GmsLogger r2 = com.google.android.gms.common.internal.LibraryVersion.LOGGER     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            java.lang.String r5 = " version is "
            java.lang.String r5 = _COROUTINE._BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(r3, r9, r5)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            r6 = 2
            boolean r6 = r2.canLog(r6)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            if (r6 == 0) goto L7d
            r2.prefix(r5)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            goto L7d
        L50:
            com.google.android.gms.common.internal.GmsLogger r2 = com.google.android.gms.common.internal.LibraryVersion.LOGGER     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            java.lang.String r5 = _COROUTINE._BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(r9, r0)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            r6 = 5
            boolean r6 = r2.canLog(r6)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            if (r6 == 0) goto L7d
            java.lang.String r2 = r2.prefix(r5)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            android.util.Log.w(r1, r2)     // Catch: java.lang.Throwable -> L65 java.io.IOException -> L68
            goto L7d
        L65:
            r9 = move-exception
            r3 = r4
            goto L9a
        L68:
            r2 = move-exception
            r7 = r4
            r4 = r3
            r3 = r7
            goto L71
        L6d:
            r9 = move-exception
            goto L9a
        L6f:
            r2 = move-exception
            r4 = r3
        L71:
            com.google.android.gms.common.internal.GmsLogger r5 = com.google.android.gms.common.internal.LibraryVersion.LOGGER     // Catch: java.lang.Throwable -> L6d
            java.lang.String r0 = _COROUTINE._BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_0(r9, r0)     // Catch: java.lang.Throwable -> L6d
            r5.e(r1, r0, r2)     // Catch: java.lang.Throwable -> L6d
            r7 = r4
            r4 = r3
            r3 = r7
        L7d:
            if (r4 == 0) goto L82
            com.google.android.gms.common.util.IOUtils.closeQuietly(r4)
        L82:
            if (r3 != 0) goto L94
            com.google.android.gms.common.internal.GmsLogger r0 = com.google.android.gms.common.internal.LibraryVersion.LOGGER
            r1 = 3
            boolean r1 = r0.canLog(r1)
            if (r1 == 0) goto L92
            java.lang.String r1 = ".properties file is dropped during release process. Failure to read app version is expected during Google internal testing where locally-built libraries are used"
            r0.prefix(r1)
        L92:
            java.lang.String r3 = "UNKNOWN"
        L94:
            j$.util.concurrent.ConcurrentHashMap r0 = r8.libraryVersionMap
            r0.put(r9, r3)
            return r3
        L9a:
            if (r3 == 0) goto L9f
            com.google.android.gms.common.util.IOUtils.closeQuietly(r3)
        L9f:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.internal.LibraryVersion.getVersion(java.lang.String):java.lang.String");
    }
}
