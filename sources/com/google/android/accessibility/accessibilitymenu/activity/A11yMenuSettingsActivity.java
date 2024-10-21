package com.google.android.accessibility.accessibilitymenu.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatTextViewAutoSizeHelper;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.libraries.social.licenses.LicenseMenuActivity;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class A11yMenuSettingsActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class A11yMenuPreferenceFragment extends PreferenceFragmentCompat {
        private void initializeHelpAndFeedbackPreference() {
            Preference findPreference = findPreference(getString(R.string.pref_help));
            if (findPreference != null) {
                if (SpannableUtils$IdentifierSpan.getVersionCodeCompat(getActivity().getApplicationContext(), "com.google.android.gms") > 9200000) {
                    findPreference.setTitle(R.string.pref_help_and_feedback_title);
                    findPreference.setOnPreferenceClickListener(new BrailleDisplaySettingsFragment.AnonymousClass2(this, 1));
                } else {
                    findPreference.setTitle(R.string.pref_help_title);
                    SpannableUtils$IdentifierSpan.assignWebIntentToPreference(this, findPreference, "https://support.google.com/accessibility/android/answer/9078941");
                }
            }
        }

        public static boolean isLargeButtonsEnabled(Context context) {
            boolean z;
            z = SpannableUtils$IdentifierSpan.getSharedPreferences(context).getBoolean(context.getResources().getString(R.string.pref_large_buttons), false);
            return z;
        }

        /* renamed from: lambda$initializeHelpAndFeedbackPreference$0$com-google-android-accessibility-accessibilitymenu-activity-A11yMenuSettingsActivity$A11yMenuPreferenceFragment, reason: not valid java name */
        public /* synthetic */ boolean m37xb0b13735(Preference preference) {
            FragmentActivity activity = getActivity();
            FeedbackOptions.Builder builder = new FeedbackOptions.Builder();
            builder.setExcludePii$ar$ds();
            builder.setCategoryTag$ar$ds();
            builder.themeSettings = AppCompatTextViewAutoSizeHelper.Api23Impl.getThemeSettings();
            FeedbackOptions build = builder.build();
            Uri parse = Uri.parse("https://support.google.com/accessibility/android/answer/9078941");
            GoogleHelp googleHelp = new GoogleHelp();
            googleHelp.enableSearch$ar$ds();
            googleHelp.fallbackSupportUri = parse;
            googleHelp.setFeedbackOptions$ar$ds(build, activity.getCacheDir());
            googleHelp.themeSettings = AppCompatTextViewAutoSizeHelper.Api23Impl.getThemeSettings();
            googleHelp.addAdditionalOverflowMenuItem$ar$ds(activity.getResources().getString(R.string.pref_item_licenses), new Intent(activity, (Class<?>) LicenseMenuActivity.class));
            Intent buildHelpIntent = googleHelp.buildHelpIntent();
            buildHelpIntent.addFlags(268435456);
            new WindowTrackerFactory((Activity) activity).launch(buildHelpIntent);
            return true;
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.a11ymenu_preferences);
            initializeHelpAndFeedbackPreference();
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new A11yMenuPreferenceFragment();
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final String getFragmentTag() {
        return A11yMenuPreferenceFragment.class.getSimpleName();
    }
}
