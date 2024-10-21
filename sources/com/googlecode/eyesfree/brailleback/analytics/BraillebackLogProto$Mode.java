package com.googlecode.eyesfree.brailleback.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BraillebackLogProto$Mode {
    public static final int MODE_UNSPECIFIED$ar$edu = 1;
    public static final int MODE_ON_SCREEN$ar$edu = 2;
    public static final int MODE_PHYSICAL$ar$edu = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$67523b80_0 = {MODE_UNSPECIFIED$ar$edu, MODE_ON_SCREEN$ar$edu, MODE_PHYSICAL$ar$edu};

    public static int forNumber$ar$edu$61a0b6d5_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0;
                }
                return MODE_PHYSICAL$ar$edu;
            }
            return MODE_ON_SCREEN$ar$edu;
        }
        return MODE_UNSPECIFIED$ar$edu;
    }

    public static int[] values$ar$edu$37c79355_0() {
        return new int[]{MODE_UNSPECIFIED$ar$edu, MODE_ON_SCREEN$ar$edu, MODE_PHYSICAL$ar$edu};
    }
}
