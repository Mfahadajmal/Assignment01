package com.google.android.libraries.security.app;

import android.content.ClipData;
import android.content.Intent;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class SaferPendingIntent {
    public static final ClipData SENTINEL_CLIP_DATA = ClipData.newIntent("", new Intent());

    public static boolean isSet(int i, int i2) {
        if ((i & i2) == i2) {
            return true;
        }
        return false;
    }
}
