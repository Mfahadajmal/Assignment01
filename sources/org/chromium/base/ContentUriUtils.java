package org.chromium.base;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class ContentUriUtils {
    private static final Object sLock = new Object();

    private ContentUriUtils() {
    }

    public static boolean contentUriExists(String str) {
        boolean z;
        AssetFileDescriptor assetFileDescriptor = getAssetFileDescriptor(str);
        if (assetFileDescriptor != null) {
            z = true;
        } else {
            z = false;
        }
        if (assetFileDescriptor != null) {
            try {
                assetFileDescriptor.close();
            } catch (IOException unused) {
            }
        }
        return z;
    }

    public static boolean delete(String str) {
        if (ContextUtils.sApplicationContext.getContentResolver().delete(Uri.parse(str), null, null) > 0) {
            return true;
        }
        return false;
    }

    private static AssetFileDescriptor getAssetFileDescriptor(String str) {
        ContentResolver contentResolver = ContextUtils.sApplicationContext.getContentResolver();
        Uri parse = Uri.parse(str);
        try {
            if (isVirtualDocument(parse)) {
                String[] streamTypes = contentResolver.getStreamTypes(parse, "*/*");
                if (streamTypes != null && streamTypes.length > 0) {
                    AssetFileDescriptor openTypedAssetFileDescriptor = contentResolver.openTypedAssetFileDescriptor(parse, streamTypes[0], null);
                    if (openTypedAssetFileDescriptor != null && openTypedAssetFileDescriptor.getStartOffset() != 0) {
                        try {
                            openTypedAssetFileDescriptor.close();
                        } catch (IOException unused) {
                        }
                        throw new SecurityException("Cannot open files with non-zero offset type.");
                    }
                    return openTypedAssetFileDescriptor;
                }
            } else {
                ParcelFileDescriptor openFileDescriptor = contentResolver.openFileDescriptor(parse, "r");
                if (openFileDescriptor != null) {
                    return new AssetFileDescriptor(openFileDescriptor, 0L, -1L);
                }
            }
        } catch (Exception e) {
            Log.w("cr_".concat("ContentUriUtils"), String.format(Locale.US, "Cannot open content uri: %s", str), e);
        }
        return null;
    }

    public static String getContentUriFromFilePath(String str) {
        try {
            new File(str);
            synchronized (sLock) {
            }
            return null;
        } catch (IllegalArgumentException e) {
            Log.e("cr_".concat("ContentUriUtils"), String.format(Locale.US, "Cannot retrieve content uri from file: %s", str), e);
            return null;
        }
    }

    public static String getMimeType(String str) {
        ContentResolver contentResolver = ContextUtils.sApplicationContext.getContentResolver();
        Uri parse = Uri.parse(str);
        if (isVirtualDocument(parse)) {
            String[] streamTypes = contentResolver.getStreamTypes(parse, "*/*");
            if (streamTypes != null && streamTypes.length > 0) {
                return streamTypes[0];
            }
            return null;
        }
        return contentResolver.getType(parse);
    }

    private static boolean hasVirtualFlag(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("flags");
        if (columnIndex >= 0 && (cursor.getLong(columnIndex) & 512) != 0) {
            return true;
        }
        return false;
    }

    private static boolean isVirtualDocument(Uri uri) {
        if (uri != null && DocumentsContract.isDocumentUri(ContextUtils.sApplicationContext, uri)) {
            try {
                Cursor query = ContextUtils.sApplicationContext.getContentResolver().query(uri, null, null, null, null);
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            query.moveToFirst();
                            boolean hasVirtualFlag = hasVirtualFlag(query);
                            query.close();
                            return hasVirtualFlag;
                        }
                    } finally {
                    }
                }
                if (query != null) {
                    query.close();
                }
            } catch (NullPointerException unused) {
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0069, code lost:
    
        if (r0 != null) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String maybeGetDisplayName(java.lang.String r12) {
        /*
            android.net.Uri r6 = android.net.Uri.parse(r12)
            r7 = 0
            r8 = 0
            android.content.Context r0 = org.chromium.base.ContextUtils.sApplicationContext     // Catch: java.lang.Exception -> L74
            java.lang.String r9 = "_display_name"
            java.lang.String r10 = ""
            if (r6 != 0) goto Lf
            goto L6c
        Lf:
            android.content.ContentResolver r11 = r0.getContentResolver()     // Catch: java.lang.Exception -> L74
            r4 = 0
            r5 = 0
            r2 = 0
            r3 = 0
            r0 = r11
            r1 = r6
            android.database.Cursor r0 = r0.query(r1, r2, r3, r4, r5)     // Catch: java.lang.NullPointerException -> L6c java.lang.Exception -> L74
            if (r0 == 0) goto L69
            int r1 = r0.getCount()     // Catch: java.lang.Throwable -> L5f
            if (r1 <= 0) goto L69
            r0.moveToFirst()     // Catch: java.lang.Throwable -> L5f
            int r1 = r0.getColumnIndex(r9)     // Catch: java.lang.Throwable -> L5f
            r2 = -1
            if (r1 != r2) goto L33
        L2f:
            r0.close()     // Catch: java.lang.NullPointerException -> L6c java.lang.Exception -> L74
            goto L6c
        L33:
            java.lang.String r1 = r0.getString(r1)     // Catch: java.lang.Throwable -> L5f
            boolean r2 = hasVirtualFlag(r0)     // Catch: java.lang.Throwable -> L5f
            if (r2 == 0) goto L5a
        */
        //  java.lang.String r2 = "*/*"
        /*
            java.lang.String[] r2 = r11.getStreamTypes(r6, r2)     // Catch: java.lang.Throwable -> L5f
            if (r2 == 0) goto L5a
            int r3 = r2.length     // Catch: java.lang.Throwable -> L5f
            if (r3 <= 0) goto L5a
            android.webkit.MimeTypeMap r3 = android.webkit.MimeTypeMap.getSingleton()     // Catch: java.lang.Throwable -> L5f
            r2 = r2[r8]     // Catch: java.lang.Throwable -> L5f
            java.lang.String r2 = r3.getExtensionFromMimeType(r2)     // Catch: java.lang.Throwable -> L5f
            if (r2 == 0) goto L5a
            java.lang.String r3 = "."
            java.lang.String r1 = _COROUTINE._BOUNDARY._BOUNDARY$ar$MethodOutlining$dc56d17a_6(r2, r1, r3)     // Catch: java.lang.Throwable -> L5f
        L5a:
            r0.close()     // Catch: java.lang.NullPointerException -> L6c java.lang.Exception -> L74
            r10 = r1
            goto L6c
        L5f:
            r1 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L64
            goto L68
        L64:
            r0 = move-exception
            r1.addSuppressed(r0)     // Catch: java.lang.NullPointerException -> L6c java.lang.Exception -> L74
        L68:
            throw r1     // Catch: java.lang.NullPointerException -> L6c java.lang.Exception -> L74
        L69:
            if (r0 == 0) goto L6c
            goto L2f
        L6c:
            boolean r12 = android.text.TextUtils.isEmpty(r10)     // Catch: java.lang.Exception -> L74
            if (r12 == 0) goto L73
            return r7
        L73:
            return r10
        L74:
            r0 = move-exception
            java.util.Locale r1 = java.util.Locale.US
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            r2[r8] = r12
            java.lang.String r12 = "Cannot open content uri: %s"
            java.lang.String r12 = java.lang.String.format(r1, r12, r2)
            java.lang.String r1 = "ContentUriUtils"
            java.lang.String r2 = "cr_"
            java.lang.String r1 = r2.concat(r1)
            android.util.Log.w(r1, r12, r0)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.base.ContentUriUtils.maybeGetDisplayName(java.lang.String):java.lang.String");
    }

    public static int openContentUriForRead(String str) {
        AssetFileDescriptor assetFileDescriptor = getAssetFileDescriptor(str);
        if (assetFileDescriptor != null) {
            return assetFileDescriptor.getParcelFileDescriptor().detachFd();
        }
        return -1;
    }
}
