package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$TypingLongClickInterval implements Internal.EnumLite {
    TYPING_LONG_CLICK_INTERVAL_500MS(0),
    TYPING_LONG_CLICK_INTERVAL_1000MS(1),
    TYPING_LONG_CLICK_INTERVAL_2000MS(2),
    TYPING_LONG_CLICK_INTERVAL_3000MS(3),
    TYPING_LONG_CLICK_INTERVAL_5000MS(4);

    public final int value;

    TalkBackSettingEnums$TypingLongClickInterval(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$TypingLongClickInterval forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            return null;
                        }
                        return TYPING_LONG_CLICK_INTERVAL_5000MS;
                    }
                    return TYPING_LONG_CLICK_INTERVAL_3000MS;
                }
                return TYPING_LONG_CLICK_INTERVAL_2000MS;
            }
            return TYPING_LONG_CLICK_INTERVAL_1000MS;
        }
        return TYPING_LONG_CLICK_INTERVAL_500MS;
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
