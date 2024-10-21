package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$KeyboardEchoExt implements Internal.EnumLite {
    KEYBOARD_ECHO_EXT_BOTH(0),
    KEYBOARD_ECHO_EXT_CHARACTER(1),
    KEYBOARD_ECHO_EXT_NEVER(2),
    KEYBOARD_ECHO_EXT_WORD(3);

    public final int value;

    TalkBackSettingEnums$KeyboardEchoExt(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$KeyboardEchoExt forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    return KEYBOARD_ECHO_EXT_WORD;
                }
                return KEYBOARD_ECHO_EXT_NEVER;
            }
            return KEYBOARD_ECHO_EXT_CHARACTER;
        }
        return KEYBOARD_ECHO_EXT_BOTH;
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
