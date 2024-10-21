package com.google.android.accessibility.talkback;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.feedback.ThemeSettings;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.social.licenses.LicenseMenuActivity;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class HelpAndFeedbackUtils {
    private static final Uri HELP_FALLBACK_URI = Uri.parse(PreferencesActivityUtils.HELP_URL);

    private static ThemeSettings getThemeSettings() {
        ThemeSettings themeSettings = new ThemeSettings();
        int i = 1;
        if (true == FormFactorUtils.getInstance().isAndroidTv) {
            i = 2;
        }
        themeSettings.themeId = i;
        return themeSettings;
    }

    public static void launchHelpAndFeedback(Activity activity) {
        GoogleHelp googleHelp = new GoogleHelp();
        googleHelp.enableSearch$ar$ds();
        googleHelp.fallbackSupportUri = HELP_FALLBACK_URI;
        FeedbackOptions.Builder builder = new FeedbackOptions.Builder();
        builder.setExcludePii$ar$ds();
        builder.setCategoryTag$ar$ds();
        builder.themeSettings = getThemeSettings();
        googleHelp.setFeedbackOptions$ar$ds(builder.build(), activity.getCacheDir());
        googleHelp.themeSettings = getThemeSettings();
        googleHelp.addAdditionalOverflowMenuItem$ar$ds(activity.getResources().getString(R.string.pref_item_licenses), new Intent(activity, (Class<?>) LicenseMenuActivity.class));
        new WindowTrackerFactory(activity).launch(googleHelp.buildHelpIntent().addFlags(268435456));
    }
}
