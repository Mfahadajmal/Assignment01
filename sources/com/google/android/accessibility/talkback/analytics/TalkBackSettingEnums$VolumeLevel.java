package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$VolumeLevel implements Internal.EnumLite {
    VOLUME_LEVEL_MATCH(0),
    VOLUME_LEVEL_75(1),
    VOLUME_LEVEL_50(2),
    VOLUME_LEVEL_25(3);

    public final int value;

    TalkBackSettingEnums$VolumeLevel(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$VolumeLevel forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return null;
                    }
                    return VOLUME_LEVEL_25;
                }
                return VOLUME_LEVEL_50;
            }
            return VOLUME_LEVEL_75;
        }
        return VOLUME_LEVEL_MATCH;
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
