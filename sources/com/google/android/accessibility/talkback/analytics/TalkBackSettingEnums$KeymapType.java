package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$KeymapType implements Internal.EnumLite {
    KEYMAP_DEFAULT(0),
    KEYMAP_CLASSIC(1);

    public final int value;

    TalkBackSettingEnums$KeymapType(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$KeymapType forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                return null;
            }
            return KEYMAP_CLASSIC;
        }
        return KEYMAP_DEFAULT;
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
