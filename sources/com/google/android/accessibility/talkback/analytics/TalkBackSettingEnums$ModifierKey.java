package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$ModifierKey implements Internal.EnumLite {
    MODIFIER_KEY_ALT(0),
    MODIFIER_KEY_SEARCH(1),
    MODIFIER_KEY_UNSPECIFIED(2);

    public final int value;

    TalkBackSettingEnums$ModifierKey(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$ModifierKey forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return MODIFIER_KEY_UNSPECIFIED;
            }
            return MODIFIER_KEY_SEARCH;
        }
        return MODIFIER_KEY_ALT;
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
