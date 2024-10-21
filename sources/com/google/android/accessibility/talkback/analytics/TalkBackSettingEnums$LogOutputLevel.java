package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$LogOutputLevel implements Internal.EnumLite {
    LOG_LEVEL_NONE(0),
    LOG_LEVEL_ASSERT(1),
    LOG_LEVEL_ERROR(2),
    LOG_LEVEL_WARN(3),
    LOG_LEVEL_INFO(4),
    LOG_LEVEL_DEBUG(5),
    LOG_LEVEL_VERBOSE(6);

    public final int value;

    TalkBackSettingEnums$LogOutputLevel(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$LogOutputLevel forNumber(int i) {
        switch (i) {
            case 0:
                return LOG_LEVEL_NONE;
            case 1:
                return LOG_LEVEL_ASSERT;
            case 2:
                return LOG_LEVEL_ERROR;
            case 3:
                return LOG_LEVEL_WARN;
            case 4:
                return LOG_LEVEL_INFO;
            case 5:
                return LOG_LEVEL_DEBUG;
            case 6:
                return LOG_LEVEL_VERBOSE;
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
