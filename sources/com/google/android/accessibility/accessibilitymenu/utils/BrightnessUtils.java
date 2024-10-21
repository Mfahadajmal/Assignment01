package com.google.android.accessibility.accessibilitymenu.utils;

import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrightnessUtils {
    public static final int GAMMA_SPACE_MAX;

    static {
        int i;
        if (true != SpannableUtils$IdentifierSpan.isAtLeastR()) {
            i = 1023;
        } else {
            i = 65535;
        }
        GAMMA_SPACE_MAX = i;
    }
}
