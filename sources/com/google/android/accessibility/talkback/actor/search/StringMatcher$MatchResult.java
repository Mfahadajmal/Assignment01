package com.google.android.accessibility.talkback.actor.search;

import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class StringMatcher$MatchResult {
    public static int percentBrightness;
    public final int end;
    public final int start;

    public StringMatcher$MatchResult(int i, int i2) {
        this.start = i;
        this.end = i2;
    }

    public static int getDeviceBrightness(Context context) {
        try {
            return Settings.System.getInt(context.getContentResolver(), "screen_brightness");
        } catch (Settings.SettingNotFoundException unused) {
            LogUtils.e("BrightnessController", "Settings SCREEN_BRIGHTNESS is not found.", new Object[0]);
            return -1;
        }
    }

    public StringMatcher$MatchResult(Context context) {
        int integer;
        int integer2;
        if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
            Resources resources = context.getResources();
            int identifier = resources.getIdentifier("config_screenBrightnessSettingMinimumFloat", "dimen", "android");
            integer = (int) ((identifier == 0 ? 0.0f : resources.getFloat(identifier)) * 255.0f);
        } else {
            Resources resources2 = context.getResources();
            int identifier2 = resources2.getIdentifier("config_screenBrightnessSettingMinimum", "integer", "android");
            integer = identifier2 == 0 ? 1 : resources2.getInteger(identifier2);
        }
        this.start = integer;
        if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
            Resources resources3 = context.getResources();
            int identifier3 = resources3.getIdentifier("config_screenBrightnessSettingMaximumFloat", "dimen", "android");
            integer2 = (int) ((identifier3 == 0 ? 1.0f : resources3.getFloat(identifier3)) * 255.0f);
        } else {
            Resources resources4 = context.getResources();
            int identifier4 = resources4.getIdentifier("config_screenBrightnessSettingMaximum", "integer", "android");
            integer2 = identifier4 == 0 ? PrivateKeyType.INVALID : resources4.getInteger(identifier4);
        }
        this.end = integer2;
    }
}
