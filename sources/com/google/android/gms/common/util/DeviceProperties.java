package com.google.android.gms.common.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DeviceProperties {
    private static volatile boolean checkSystemFeatureForIsFoldable = false;
    public static Boolean isAuto;
    private static Boolean isChinaWearable;
    public static Boolean isIoT;
    private static Boolean isWearable;

    public static boolean isWearable(Context context) {
        return isWearable(context.getPackageManager());
    }

    public static boolean isWearableWithoutPlayStore(Context context) {
        if (isChinaWearable == null) {
            isChinaWearable = Boolean.valueOf(context.getPackageManager().hasSystemFeature("cn.google"));
        }
        if (isChinaWearable.booleanValue() && Build.VERSION.SDK_INT >= 30) {
            return true;
        }
        return false;
    }

    public static boolean isWearable(PackageManager packageManager) {
        if (isWearable == null) {
            isWearable = Boolean.valueOf(packageManager.hasSystemFeature("android.hardware.type.watch"));
        }
        return isWearable.booleanValue();
    }
}
