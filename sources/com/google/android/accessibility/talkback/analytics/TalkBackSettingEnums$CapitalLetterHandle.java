package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$CapitalLetterHandle implements Internal.EnumLite {
    DO_NOTHING(0),
    SAY_CAPITAL(1),
    CHANGE_PITCH(2),
    PLAY_SOUND(3);

    public final int value;

    TalkBackSettingEnums$CapitalLetterHandle(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$CapitalLetterHandle forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    return PLAY_SOUND;
                }
                return CHANGE_PITCH;
            }
            return SAY_CAPITAL;
        }
        return DO_NOTHING;
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
