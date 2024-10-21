package com.google.android.accessibility.accessibilitymenu.proto;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class A11ymenuSettingsEnums$A11ymenuSettings {
    public static final int UNSPECIFIED_SETTINGS$ar$edu = 1;
    public static final int A11YMENU_SETTINGS$ar$edu = 2;
    public static final int LARGE_BUTTON_SETTINGS$ar$edu = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$71da8a12_0 = {UNSPECIFIED_SETTINGS$ar$edu, A11YMENU_SETTINGS$ar$edu, LARGE_BUTTON_SETTINGS$ar$edu};

    public static int forNumber$ar$edu$8db3b02f_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0;
                }
                return LARGE_BUTTON_SETTINGS$ar$edu;
            }
            return A11YMENU_SETTINGS$ar$edu;
        }
        return UNSPECIFIED_SETTINGS$ar$edu;
    }

    public static /* synthetic */ String toStringGeneratedc97e382a2b02f4b7(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return "null";
                }
                return "LARGE_BUTTON_SETTINGS";
            }
            return "A11YMENU_SETTINGS";
        }
        return "UNSPECIFIED_SETTINGS";
    }

    public static int[] values$ar$edu$34ea8c43_0() {
        return new int[]{UNSPECIFIED_SETTINGS$ar$edu, A11YMENU_SETTINGS$ar$edu, LARGE_BUTTON_SETTINGS$ar$edu};
    }
}
