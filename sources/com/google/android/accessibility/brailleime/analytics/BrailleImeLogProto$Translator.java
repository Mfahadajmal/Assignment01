package com.google.android.accessibility.brailleime.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum BrailleImeLogProto$Translator implements Internal.EnumLite {
    UNSPECIFIED_TRANSLATOR(0),
    LIB_LOUIS(1);

    public final int value;

    BrailleImeLogProto$Translator(int i) {
        this.value = i;
    }

    public static BrailleImeLogProto$Translator forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                return null;
            }
            return LIB_LOUIS;
        }
        return UNSPECIFIED_TRANSLATOR;
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
