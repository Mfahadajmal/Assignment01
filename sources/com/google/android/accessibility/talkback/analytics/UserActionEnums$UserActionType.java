package com.google.android.accessibility.talkback.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class UserActionEnums$UserActionType {
    public static final int ACTION_UNKNOWN$ar$edu = 1;
    public static final int ACTION_PREFERENCE_SETTING$ar$edu = 2;
    public static final int ACTION_GESTURE$ar$edu = 3;
    public static final int ACTION_CONTEXT_MENU$ar$edu = 4;
    public static final int ACTION_SELECTOR$ar$edu = 5;
    private static final /* synthetic */ int[] $VALUES$ar$edu$25b3fd55_0 = {ACTION_UNKNOWN$ar$edu, ACTION_PREFERENCE_SETTING$ar$edu, ACTION_GESTURE$ar$edu, ACTION_CONTEXT_MENU$ar$edu, ACTION_SELECTOR$ar$edu};

    public static int forNumber$ar$edu$3370712f_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return 0;
                        }
                        return ACTION_SELECTOR$ar$edu;
                    }
                    return ACTION_CONTEXT_MENU$ar$edu;
                }
                return ACTION_GESTURE$ar$edu;
            }
            return ACTION_PREFERENCE_SETTING$ar$edu;
        }
        return ACTION_UNKNOWN$ar$edu;
    }

    public static int[] values$ar$edu$79002224_0() {
        return new int[]{ACTION_UNKNOWN$ar$edu, ACTION_PREFERENCE_SETTING$ar$edu, ACTION_GESTURE$ar$edu, ACTION_CONTEXT_MENU$ar$edu, ACTION_SELECTOR$ar$edu};
    }
}
