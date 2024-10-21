package com.google.android.gms.common.util;

import android.content.Context;
import android.util.Log;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class CrashUtils {
    public static void addDynamiteErrorToDropBox$ar$ds(Context context) {
        try {
            StrictModeUtils$VmPolicyBuilderCompatS.checkNotNull$ar$ds$ca384cd1_1(context);
        } catch (Exception e) {
            Log.e("CrashUtils", "Error adding exception to DropBox!", e);
        }
    }
}
