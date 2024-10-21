package org.chromium.base;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class PackageUtils {
    static {
        "0123456789ABCDEF".toCharArray();
    }

    public static PackageInfo getPackageInfo$ar$ds(String str) {
        try {
            return ContextUtils.sApplicationContext.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }
}
