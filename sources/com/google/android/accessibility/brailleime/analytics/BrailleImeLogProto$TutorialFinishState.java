package com.google.android.accessibility.brailleime.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class BrailleImeLogProto$TutorialFinishState {
    public static final int UNSPECIFIED_TUTORIAL_FINISH_STATE$ar$edu = 1;
    public static final int FINISHED_BY_TUTORIAL_COMPLETED$ar$edu = 2;
    public static final int FINISHED_BY_TALKBACK_STOP$ar$edu = 3;
    public static final int FINISHED_BY_LAUNCH_SETTINGS$ar$edu = 4;
    public static final int FINISHED_BY_SWITCH_TO_NEXT_INPUTMETHOD$ar$edu = 5;
    private static final /* synthetic */ int[] $VALUES$ar$edu$6c2dcab3_0 = {UNSPECIFIED_TUTORIAL_FINISH_STATE$ar$edu, FINISHED_BY_TUTORIAL_COMPLETED$ar$edu, FINISHED_BY_TALKBACK_STOP$ar$edu, FINISHED_BY_LAUNCH_SETTINGS$ar$edu, FINISHED_BY_SWITCH_TO_NEXT_INPUTMETHOD$ar$edu};

    public static int forNumber$ar$edu$567b72a8_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return 0;
                        }
                        return FINISHED_BY_SWITCH_TO_NEXT_INPUTMETHOD$ar$edu;
                    }
                    return FINISHED_BY_LAUNCH_SETTINGS$ar$edu;
                }
                return FINISHED_BY_TALKBACK_STOP$ar$edu;
            }
            return FINISHED_BY_TUTORIAL_COMPLETED$ar$edu;
        }
        return UNSPECIFIED_TUTORIAL_FINISH_STATE$ar$edu;
    }

    public static int[] values$ar$edu$52da22d3_0() {
        return new int[]{UNSPECIFIED_TUTORIAL_FINISH_STATE$ar$edu, FINISHED_BY_TUTORIAL_COMPLETED$ar$edu, FINISHED_BY_TALKBACK_STOP$ar$edu, FINISHED_BY_LAUNCH_SETTINGS$ar$edu, FINISHED_BY_SWITCH_TO_NEXT_INPUTMETHOD$ar$edu};
    }
}
