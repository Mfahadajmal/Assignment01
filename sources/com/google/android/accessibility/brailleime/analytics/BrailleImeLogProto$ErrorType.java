package com.google.android.accessibility.brailleime.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleImeLogProto$ErrorType {
    public static final int UNSPECIFIED_TYPE$ar$edu = 1;
    public static final int TALK_BACK_OFF$ar$edu = 2;
    public static final int TOO_FEW_TOUCH_POINTS$ar$edu = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$dccd13de_0 = {UNSPECIFIED_TYPE$ar$edu, TALK_BACK_OFF$ar$edu, TOO_FEW_TOUCH_POINTS$ar$edu};

    public static int forNumber$ar$edu$1bdadeea_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0;
                }
                return TOO_FEW_TOUCH_POINTS$ar$edu;
            }
            return TALK_BACK_OFF$ar$edu;
        }
        return UNSPECIFIED_TYPE$ar$edu;
    }

    public static int[] values$ar$edu$b7d1353_0() {
        return new int[]{UNSPECIFIED_TYPE$ar$edu, TALK_BACK_OFF$ar$edu, TOO_FEW_TOUCH_POINTS$ar$edu};
    }
}
