package com.google.android.accessibility.brailleime.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum BrailleImeLogProto$ContextMenuOption implements Internal.EnumLite {
    UNSPECIFIED_OPTION(0),
    CONTRACTED_ON_OFF(1),
    SEE_ALL_ACTIONS(2),
    TUTORIAL_OPEN(3),
    TUTORIAL_FINISH(4),
    GO_TO_SETTINGS(5),
    TYPING_LANGUAGE(6),
    CALIBRATION(7);

    public final int value;

    BrailleImeLogProto$ContextMenuOption(int i) {
        this.value = i;
    }

    public static BrailleImeLogProto$ContextMenuOption forNumber(int i) {
        switch (i) {
            case 0:
                return UNSPECIFIED_OPTION;
            case 1:
                return CONTRACTED_ON_OFF;
            case 2:
                return SEE_ALL_ACTIONS;
            case 3:
                return TUTORIAL_OPEN;
            case 4:
                return TUTORIAL_FINISH;
            case 5:
                return GO_TO_SETTINGS;
            case 6:
                return TYPING_LANGUAGE;
            case 7:
                return CALIBRATION;
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
