package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$PunctuationVerbosity implements Internal.EnumLite {
    PUNCTUATION_VERBOSITY_ALL(0),
    PUNCTUATION_VERBOSITY_MOST(1),
    PUNCTUATION_VERBOSITY_SOME(2);

    public final int value;

    TalkBackSettingEnums$PunctuationVerbosity(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$PunctuationVerbosity forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return PUNCTUATION_VERBOSITY_SOME;
            }
            return PUNCTUATION_VERBOSITY_MOST;
        }
        return PUNCTUATION_VERBOSITY_ALL;
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
