package com.google.android.accessibility.brailleime.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum BrailleImeLogProto$LayoutCode implements Internal.EnumLite {
    AUTO_DETECT(0),
    SCREEN_AWAY(1),
    TABLETOP(2);

    public final int value;

    BrailleImeLogProto$LayoutCode(int i) {
        this.value = i;
    }

    public static BrailleImeLogProto$LayoutCode forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return TABLETOP;
            }
            return SCREEN_AWAY;
        }
        return AUTO_DETECT;
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
