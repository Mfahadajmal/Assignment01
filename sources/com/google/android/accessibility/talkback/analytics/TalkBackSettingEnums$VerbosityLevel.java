package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$VerbosityLevel implements Internal.EnumLite {
    VERBOSITY_LEVEL_HIGH(0),
    VERBOSITY_LEVEL_CUSTOM(1),
    VERBOSITY_LEVEL_LOW(2);

    public final int value;

    TalkBackSettingEnums$VerbosityLevel(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$VerbosityLevel forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return VERBOSITY_LEVEL_LOW;
            }
            return VERBOSITY_LEVEL_CUSTOM;
        }
        return VERBOSITY_LEVEL_HIGH;
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
