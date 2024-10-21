package com.google.android.accessibility.selecttospeak;

import android.net.Uri;
import com.google.android.gms.feedback.ThemeSettings;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HelpAndFeedbackUtils {
    public static final Uri HELP_FALLBACK_URI = Uri.parse("https://support.google.com/accessibility/android/answer/7349565");

    public static ThemeSettings getThemeSettings() {
        ThemeSettings themeSettings = new ThemeSettings();
        themeSettings.themeId = 1;
        return themeSettings;
    }
}
