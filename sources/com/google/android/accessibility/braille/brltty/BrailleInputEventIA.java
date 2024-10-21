package com.google.android.accessibility.braille.brltty;

import android.content.Context;
import android.os.Looper;
import android.provider.Settings;

/* compiled from: PG */
/* renamed from: com.google.android.accessibility.braille.brltty.BrailleInputEvent-IA, reason: invalid class name */
/* loaded from: classes.dex */
public final /* synthetic */ class BrailleInputEventIA {
    public static void assertMainThread() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
        } else {
            throw new IllegalStateException("Must be on main thread");
        }
    }

    public static boolean isGlobalLocationSettingEnabled(Context context) {
        if (Settings.Secure.getInt(context.getContentResolver(), "location_mode") == 0) {
            return false;
        }
        return true;
    }
}
