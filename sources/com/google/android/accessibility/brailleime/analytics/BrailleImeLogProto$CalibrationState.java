package com.google.android.accessibility.brailleime.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleImeLogProto$CalibrationState {
    public static final int CALIBRATION_STATE_INVALID$ar$edu = 1;
    public static final int CALIBRATION_STATE_STARTED$ar$edu = 2;
    public static final int CALIBRATION_STATE_ENDED$ar$edu = 3;
    public static final int CALIBRATION_STATE_FAILED$ar$edu = 4;
    private static final /* synthetic */ int[] $VALUES$ar$edu$621ca638_0 = {CALIBRATION_STATE_INVALID$ar$edu, CALIBRATION_STATE_STARTED$ar$edu, CALIBRATION_STATE_ENDED$ar$edu, CALIBRATION_STATE_FAILED$ar$edu};

    public static int forNumber$ar$edu$425b3e52_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return 0;
                    }
                    return CALIBRATION_STATE_FAILED$ar$edu;
                }
                return CALIBRATION_STATE_ENDED$ar$edu;
            }
            return CALIBRATION_STATE_STARTED$ar$edu;
        }
        return CALIBRATION_STATE_INVALID$ar$edu;
    }

    public static int[] values$ar$edu$b7f930eb_0() {
        return new int[]{CALIBRATION_STATE_INVALID$ar$edu, CALIBRATION_STATE_STARTED$ar$edu, CALIBRATION_STATE_ENDED$ar$edu, CALIBRATION_STATE_FAILED$ar$edu};
    }
}
