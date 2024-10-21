package com.google.android.libraries.vision.visionkit.pipeline;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AccelerationAllowlistFlavor {
    public static final int DEFAULT$ar$edu = 1;
    public static final int BETA$ar$edu = 2;
    public static final int DISABLED$ar$edu = 3;
    private static final /* synthetic */ int[] $VALUES$ar$edu$f29ba922_0 = {DEFAULT$ar$edu, BETA$ar$edu, DISABLED$ar$edu};

    public static int forNumber$ar$edu$af789408_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return 0;
                }
                return DISABLED$ar$edu;
            }
            return BETA$ar$edu;
        }
        return DEFAULT$ar$edu;
    }

    public static int[] values$ar$edu$7f8b887f_0() {
        return new int[]{DEFAULT$ar$edu, BETA$ar$edu, DISABLED$ar$edu};
    }
}
