package com.google.mlkit.logging.schema;

import androidx.preference.Preference;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextDetectionOptionalModuleLogEvent {
    public static /* synthetic */ int systemProp$default$ar$ds(String str, int i, int i2, int i3, int i4) {
        int i5;
        if ((i4 & 4) != 0) {
            i5 = 0;
        } else {
            i5 = 1;
        }
        int i6 = i2 | (i5 ^ 1);
        if ((i4 & 8) != 0) {
            i3 = Preference.DEFAULT_ORDER;
        }
        return TextDetectionOptionalModuleOptions.systemProp(str, i, i6, i3);
    }
}
