package com.google.android.accessibility.talkback.analytics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TalkBackMistriggeringRecoveryEnums$TalkBackMistriggeringRecoveryType {
    public static final int TYPE_UNSPECIFIED$ar$edu = 1;
    public static final int TYPE_TALKBACK_EXIT_BANNER$ar$edu = 2;
    public static final int TYPE_AUTOMATIC_TURNOFF_LOCKSCREEN$ar$edu = 3;
    public static final int TYPE_AUTOMATIC_TURNOFF_SHUTDOWN$ar$edu = 4;
    public static final int TYPE_ACCESSIBILITY_SHORTCUT$ar$edu = 5;
    private static final /* synthetic */ int[] $VALUES$ar$edu$98edcbc2_0 = {TYPE_UNSPECIFIED$ar$edu, TYPE_TALKBACK_EXIT_BANNER$ar$edu, TYPE_AUTOMATIC_TURNOFF_LOCKSCREEN$ar$edu, TYPE_AUTOMATIC_TURNOFF_SHUTDOWN$ar$edu, TYPE_ACCESSIBILITY_SHORTCUT$ar$edu};

    public static int forNumber$ar$edu$891bf696_0(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return 0;
                        }
                        return TYPE_ACCESSIBILITY_SHORTCUT$ar$edu;
                    }
                    return TYPE_AUTOMATIC_TURNOFF_SHUTDOWN$ar$edu;
                }
                return TYPE_AUTOMATIC_TURNOFF_LOCKSCREEN$ar$edu;
            }
            return TYPE_TALKBACK_EXIT_BANNER$ar$edu;
        }
        return TYPE_UNSPECIFIED$ar$edu;
    }

    public static int[] values$ar$edu$c0fd6fff_0() {
        return new int[]{TYPE_UNSPECIFIED$ar$edu, TYPE_TALKBACK_EXIT_BANNER$ar$edu, TYPE_AUTOMATIC_TURNOFF_LOCKSCREEN$ar$edu, TYPE_AUTOMATIC_TURNOFF_SHUTDOWN$ar$edu, TYPE_ACCESSIBILITY_SHORTCUT$ar$edu};
    }
}
