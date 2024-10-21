package com.google.android.accessibility.brailleime.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleImeLogProto$State {
    public static final int STATE_UNSPECIFIED$ar$edu = 1;
    public static final int STATE_ON$ar$edu = 2;
    public static final int STATE_OFF$ar$edu = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$ecb83ceb_0 = {STATE_UNSPECIFIED$ar$edu, STATE_ON$ar$edu, STATE_OFF$ar$edu};

    public static int forNumber$ar$edu$1523a66e_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0;
                }
                return STATE_OFF$ar$edu;
            }
            return STATE_ON$ar$edu;
        }
        return STATE_UNSPECIFIED$ar$edu;
    }

    public static int[] values$ar$edu$4aadd11d_0() {
        return new int[]{STATE_UNSPECIFIED$ar$edu, STATE_ON$ar$edu, STATE_OFF$ar$edu};
    }
}
