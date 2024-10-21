package com.google.android.accessibility.talkback.analytics;

import com.google.protobuf.Internal;

/* compiled from: PG */
/* loaded from: classes.dex */
public enum TalkBackSettingEnums$DescriptionOrder implements Internal.EnumLite {
    ORDER_STATE_NAME_TYPE(0),
    ORDER_TYPE_NAME_STATE(1),
    ORDER_NAME_TYPE_STATE(2);

    public final int value;

    TalkBackSettingEnums$DescriptionOrder(int i) {
        this.value = i;
    }

    public static TalkBackSettingEnums$DescriptionOrder forNumber(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    return null;
                }
                return ORDER_NAME_TYPE_STATE;
            }
            return ORDER_TYPE_NAME_STATE;
        }
        return ORDER_STATE_NAME_TYPE;
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
