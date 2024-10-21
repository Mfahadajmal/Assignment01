package com.google.android.accessibility.brailleime.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleImeLogProto$CalibrationType {
    public static final int CALIBRATION_TYPE_INVALID$ar$edu = 1;
    public static final int CALIBRATION_TYPE_FIVE_FINGER$ar$edu = 2;
    public static final int CALIBRATION_TYPE_SIX_FINGER$ar$edu = 3;
    public static final int CALIBRATION_TYPE_SEVEN_FINGER$ar$edu = 4;
    public static final int CALIBRATION_TYPE_EIGHT_FINGER$ar$edu = 5;
    public static final int CALIBRATION_TYPE_MANUAL$ar$edu = 6;
    private static final /* synthetic */ int[] $VALUES$ar$edu$ceafd293_0 = {CALIBRATION_TYPE_INVALID$ar$edu, CALIBRATION_TYPE_FIVE_FINGER$ar$edu, CALIBRATION_TYPE_SIX_FINGER$ar$edu, CALIBRATION_TYPE_SEVEN_FINGER$ar$edu, CALIBRATION_TYPE_EIGHT_FINGER$ar$edu, CALIBRATION_TYPE_MANUAL$ar$edu};

    public static int forNumber$ar$edu$1d442578_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return 0;
                            }
                            return CALIBRATION_TYPE_MANUAL$ar$edu;
                        }
                        return CALIBRATION_TYPE_EIGHT_FINGER$ar$edu;
                    }
                    return CALIBRATION_TYPE_SEVEN_FINGER$ar$edu;
                }
                return CALIBRATION_TYPE_SIX_FINGER$ar$edu;
            }
            return CALIBRATION_TYPE_FIVE_FINGER$ar$edu;
        }
        return CALIBRATION_TYPE_INVALID$ar$edu;
    }

    public static int[] values$ar$edu$ca4339a5_0() {
        return new int[]{CALIBRATION_TYPE_INVALID$ar$edu, CALIBRATION_TYPE_FIVE_FINGER$ar$edu, CALIBRATION_TYPE_SIX_FINGER$ar$edu, CALIBRATION_TYPE_SEVEN_FINGER$ar$edu, CALIBRATION_TYPE_EIGHT_FINGER$ar$edu, CALIBRATION_TYPE_MANUAL$ar$edu};
    }
}
