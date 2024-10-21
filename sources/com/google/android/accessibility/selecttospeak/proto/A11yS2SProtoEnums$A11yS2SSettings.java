package com.google.android.accessibility.selecttospeak.proto;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class A11yS2SProtoEnums$A11yS2SSettings {
    public static final int UNSPECIFIED_SETTING$ar$edu = 1;
    public static final int MULTITASK_SETTING$ar$edu = 2;
    public static final int ENABLE_OCR_SETTING$ar$edu = 3;
    public static final int ENABLE_IMPROVED_ACCURACY_SETTING$ar$edu = 4;
    private static final /* synthetic */ int[] $VALUES$ar$edu$b344c7cf_0 = {UNSPECIFIED_SETTING$ar$edu, MULTITASK_SETTING$ar$edu, ENABLE_OCR_SETTING$ar$edu, ENABLE_IMPROVED_ACCURACY_SETTING$ar$edu};

    public static int forNumber$ar$edu$8845a5ec_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return 0;
                    }
                    return ENABLE_IMPROVED_ACCURACY_SETTING$ar$edu;
                }
                return ENABLE_OCR_SETTING$ar$edu;
            }
            return MULTITASK_SETTING$ar$edu;
        }
        return UNSPECIFIED_SETTING$ar$edu;
    }

    public static /* synthetic */ String toStringGeneratedcb0e5ccfc321d674(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    if (i != 4) {
                        return "null";
                    }
                    return "ENABLE_IMPROVED_ACCURACY_SETTING";
                }
                return "ENABLE_OCR_SETTING";
            }
            return "MULTITASK_SETTING";
        }
        return "UNSPECIFIED_SETTING";
    }

    public static int[] values$ar$edu$573f0f23_0() {
        return new int[]{UNSPECIFIED_SETTING$ar$edu, MULTITASK_SETTING$ar$edu, ENABLE_OCR_SETTING$ar$edu, ENABLE_IMPROVED_ACCURACY_SETTING$ar$edu};
    }
}
