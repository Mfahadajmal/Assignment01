package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$FocusIndicatorColor implements Internal.EnumLite {
    FOCUS_INDICATOR_COLOR_DEFAULT(0),
    FOCUS_INDICATOR_COLOR_RED(1),
    FOCUS_INDICATOR_COLOR_ORANGE(2),
    FOCUS_INDICATOR_COLOR_YELLOW(3),
    FOCUS_INDICATOR_COLOR_GREEN(4),
    FOCUS_INDICATOR_COLOR_BLUE(5),
    FOCUS_INDICATOR_COLOR_GREY(6);

    public final int value;

    TalkBackSettingEnums$FocusIndicatorColor(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$FocusIndicatorColor forNumber(int i) {
        switch (i) {
            case 0:
                return FOCUS_INDICATOR_COLOR_DEFAULT;
            case 1:
                return FOCUS_INDICATOR_COLOR_RED;
            case 2:
                return FOCUS_INDICATOR_COLOR_ORANGE;
            case 3:
                return FOCUS_INDICATOR_COLOR_YELLOW;
            case 4:
                return FOCUS_INDICATOR_COLOR_GREEN;
            case 5:
                return FOCUS_INDICATOR_COLOR_BLUE;
            case 6:
                return FOCUS_INDICATOR_COLOR_GREY;
            default:
                return null;
        }
    }

    @Override // com.google.protobuf.Internal.EnumLite
    public final int getNumber() {
        return this.value;
    }

    @Override // java.lang.Enum
    public final String toString() {
        return Integer.toString(this.value);
    }
}
