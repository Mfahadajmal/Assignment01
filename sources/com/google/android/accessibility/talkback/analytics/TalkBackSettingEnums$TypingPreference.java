package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$TypingPreference implements Internal.EnumLite {
    DOUBLE_TAP_TO_ENTER(0),
    HOLD_LETTER_KEY_THEN_LIFT_TO_ENTER(1),
    HOLD_ANY_EKY_THEN_LIFT_TO_ENTER(2);

    public final int value;

    TalkBackSettingEnums$TypingPreference(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$TypingPreference forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return HOLD_ANY_EKY_THEN_LIFT_TO_ENTER;
            }
            return HOLD_LETTER_KEY_THEN_LIFT_TO_ENTER;
        }
        return DOUBLE_TAP_TO_ENTER;
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
