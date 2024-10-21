package com.google.android.accessibility.brailleime.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum BrailleImeLogProto$SettingsEvent implements Internal.EnumLite {
    UNSPECIFIED_SETTINGS(0),
    SWITCH_TO_CONTRACTED(1),
    SWITCH_TO_CONTRACTED_OFF(2),
    SWITCH_TO_SCREEN_AWAY(3),
    SWITCH_TO_TABLETOP(4),
    SWITCH_TO_AUTO_DETECT(5);

    public final int value;

    BrailleImeLogProto$SettingsEvent(int i) {
        this.value = i;
    }

    public static BrailleImeLogProto$SettingsEvent forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 4) {
                            if (i != 5) {
                                return null;
                            }
                            return SWITCH_TO_AUTO_DETECT;
                        }
                        return SWITCH_TO_TABLETOP;
                    }
                    return SWITCH_TO_SCREEN_AWAY;
                }
                return SWITCH_TO_CONTRACTED_OFF;
            }
            return SWITCH_TO_CONTRACTED;
        }
        return UNSPECIFIED_SETTINGS;
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
