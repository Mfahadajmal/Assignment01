package com.googlecode.eyesfree.brailleback.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BraillebackLogProto$State {
    public static final int STATE_UNSPECIFIED$ar$edu$bc488e37_0 = 1;
    public static final int STATE_ON$ar$edu$bc488e37_0 = 2;
    public static final int STATE_OFF$ar$edu$bc488e37_0 = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$c6dd64_0 = {STATE_UNSPECIFIED$ar$edu$bc488e37_0, STATE_ON$ar$edu$bc488e37_0, STATE_OFF$ar$edu$bc488e37_0};

    public static int forNumber$ar$edu$6c783fcb_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0;
                }
                return STATE_OFF$ar$edu$bc488e37_0;
            }
            return STATE_ON$ar$edu$bc488e37_0;
        }
        return STATE_UNSPECIFIED$ar$edu$bc488e37_0;
    }

    public static int[] values$ar$edu$dd1a4e6b_0() {
        return new int[]{STATE_UNSPECIFIED$ar$edu$bc488e37_0, STATE_ON$ar$edu$bc488e37_0, STATE_OFF$ar$edu$bc488e37_0};
    }
}
