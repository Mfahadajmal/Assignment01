package org.chromium.base;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ApkAssets {
    private static String sLastError;

    public static long[] open(String str, String str2) {
        long[] jArr;
        AssetFileDescriptor assetFileDescriptor = null;
        sLastError = null;
        try {
            try {
                Context context = ContextUtils.sApplicationContext;
                if (!TextUtils.isEmpty(str2) && BundleUtils.isIsolatedSplitInstalled(str2)) {
                    context = BundleUtils.createIsolatedSplitContext(context, str2);
                }
                assetFileDescriptor = context.getAssets().openNonAssetFd(str);
                jArr = new long[]{assetFileDescriptor.getParcelFileDescriptor().detachFd(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength()};
            } catch (IOException e) {
                sLastError = "Error while loading asset " + str + " from " + str2 + ": " + e;
                if (!e.getMessage().equals("") && !e.getMessage().equals(str)) {
                    Log.e("cr_".concat("ApkAssets"), sLastError);
                }
                jArr = new long[]{-1, -1, -1};
            }
            return jArr;
        } finally {
            if (assetFileDescriptor != null) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException e2) {
                    Log.e("cr_".concat("ApkAssets"), "Unable to close AssetFileDescriptor", e2);
                }
            }
        }
    }

    private static String takeLastErrorString() {
        String str = sLastError;
        sLastError = null;
        return str;
    }
}
