package com.google.android.accessibility.selecttospeak.proto;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class A11yS2SProtoEnums$A11yS2SEntryPoint {
    public static final int ENTRY_UNSPECIFIED$ar$edu = 1;
    public static final int ENTRY_A11Y_SERVICE$ar$edu = 2;
    public static final int ENTRY_CONTEXTUAL_POPUP$ar$edu = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$727e0b14_0 = {ENTRY_UNSPECIFIED$ar$edu, ENTRY_A11Y_SERVICE$ar$edu, ENTRY_CONTEXTUAL_POPUP$ar$edu};

    public static int forNumber$ar$edu$70dcef61_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0;
                }
                return ENTRY_CONTEXTUAL_POPUP$ar$edu;
            }
            return ENTRY_A11Y_SERVICE$ar$edu;
        }
        return ENTRY_UNSPECIFIED$ar$edu;
    }

    public static /* synthetic */ String toStringGeneratedd7c9859b585c301d(int i) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    return "null";
                }
                return "ENTRY_CONTEXTUAL_POPUP";
            }
            return "ENTRY_A11Y_SERVICE";
        }
        return "ENTRY_UNSPECIFIED";
    }

    public static int[] values$ar$edu$48623e11_0() {
        return new int[]{ENTRY_UNSPECIFIED$ar$edu, ENTRY_A11Y_SERVICE$ar$edu, ENTRY_CONTEXTUAL_POPUP$ar$edu};
    }
}
