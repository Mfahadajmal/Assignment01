package com.google.android.accessibility.utils;

import android.app.UiModeManager;
import android.content.Context;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class FormFactorUtils {
    public static FormFactorUtils instance;
    public final boolean isAndroidAuto;
    public final boolean isAndroidTv;
    public final boolean isAndroidWear;

    public FormFactorUtils(Context context) {
        this.isAndroidAuto = context.getPackageManager().hasSystemFeature("android.hardware.type.automotive");
        this.isAndroidWear = context.getPackageManager().hasSystemFeature("android.hardware.type.watch");
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        boolean z = false;
        if (uiModeManager != null && uiModeManager.getCurrentModeType() == 4) {
            z = true;
        }
        this.isAndroidTv = z;
    }

    public static FormFactorUtils getInstance() {
        FormFactorUtils formFactorUtils = instance;
        if (formFactorUtils != null) {
            return formFactorUtils;
        }
        throw new IllegalStateException("We should initialize FormFactorUtils before getting instance.");
    }
}
