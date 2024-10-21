package com.google.android.accessibility.talkback.preference.base;

import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.provider.Settings;
import android.text.format.DateFormat;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.google.android.accessibility.talkback.RingerModeAndScreenMonitor;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.preference.base.PreferenceActionHelper;
import com.google.android.accessibility.talkback.speech.SpeakPasswordsManager;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AdvancedSettingFragment extends TalkbackBaseFragment {
    private static final String TAG = "AdvancedSettingFragment";
    private Context context;
    private SharedPreferences prefs;

    public AdvancedSettingFragment() {
        super(R.xml.advanced_preferences);
    }

    private String getSummaryForTimeFeedbackFormat() {
        Resources resources = getResources();
        int prefValueToTimeFeedbackFormat = RingerModeAndScreenMonitor.prefValueToTimeFeedbackFormat(resources, SpannableUtils$IdentifierSpan.getStringPref(this.prefs, resources, R.string.pref_time_feedback_format_key, R.string.pref_time_feedback_format_default));
        if (prefValueToTimeFeedbackFormat != 1) {
            if (prefValueToTimeFeedbackFormat != 2) {
                if (DateFormat.is24HourFormat(getContext())) {
                    return getString(R.string.pref_time_feedback_format_entries_24_hour);
                }
                return getString(R.string.pref_time_feedback_format_entries_12_hour);
            }
            return getString(R.string.pref_time_feedback_format_entries_24_hour);
        }
        return getString(R.string.pref_time_feedback_format_entries_12_hour);
    }

    private static boolean isTouchExplorationEnabled(ContentResolver contentResolver) {
        if (Settings.Secure.getInt(contentResolver, "touch_exploration_enabled", 0) != 1) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$onCreatePreferences$1(Preference preference, Preference preference2, Object obj) {
        boolean z;
        if (Integer.parseInt((String) obj) != 0) {
            z = true;
        } else {
            z = false;
        }
        preference.setEnabled(z);
        return true;
    }

    private void updateSpeakPasswordsPreference() {
        boolean alwaysSpeakPasswordsPref = SpeakPasswordsManager.getAlwaysSpeakPasswordsPref(this.context);
        TwoStatePreference twoStatePreference = (TwoStatePreference) findPreference(getString(R.string.pref_speak_passwords_without_headphones));
        if (twoStatePreference != null) {
            twoStatePreference.setChecked(alwaysSpeakPasswordsPref);
        }
    }

    private void updateTimeFeedbackFormatPreference() {
        ListPreference listPreference = (ListPreference) findPreference(getString(R.string.pref_time_feedback_format_key));
        if (listPreference != null) {
            listPreference.setSummaryProvider(new Preference.SummaryProvider() { // from class: com.google.android.accessibility.talkback.preference.base.AdvancedSettingFragment$$ExternalSyntheticLambda1
                @Override // androidx.preference.Preference.SummaryProvider
                public final CharSequence provideSummary(Preference preference) {
                    return AdvancedSettingFragment.this.m103x81a8f331(preference);
                }
            });
        }
    }

    private void updateTouchExplorationState() {
        ContentResolver contentResolver = this.context.getContentResolver();
        boolean booleanPref = SpannableUtils$IdentifierSpan.getBooleanPref(this.prefs, getResources(), R.string.pref_explore_by_touch_key, R.bool.pref_explore_by_touch_default);
        if (TalkBackService.isServiceActive()) {
            booleanPref = isTouchExplorationEnabled(contentResolver);
        }
        Preference findPreference = findPreference(getString(R.string.pref_single_tap_key));
        if (findPreference != null) {
            findPreference.setEnabled(booleanPref);
        }
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.title_pref_advanced_settings);
    }

    /* renamed from: lambda$updateTimeFeedbackFormatPreference$0$com-google-android-accessibility-talkback-preference-base-AdvancedSettingFragment, reason: not valid java name */
    public /* synthetic */ CharSequence m103x81a8f331(Preference preference) {
        return getSummaryForTimeFeedbackFormat();
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        boolean z;
        super.onCreatePreferences(bundle, str);
        Context context = getContext();
        this.context = context;
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        if (findPreference(getString(R.string.pref_policy_key)) != null) {
            PreferenceActionHelper.assignWebIntentToPreference(this, findPreference(getString(R.string.pref_policy_key)), PreferenceActionHelper.WebPage.WEB_PAGE_PRIVACY_POLICY);
        }
        if (findPreference(getString(R.string.pref_show_tos_key)) != null) {
            PreferenceActionHelper.assignWebIntentToPreference(this, findPreference(getString(R.string.pref_show_tos_key)), PreferenceActionHelper.WebPage.WEB_PAGE_TERMS_OF_SERVICE);
        }
        updateSpeakPasswordsPreference();
        Preference findPreference = findPreference(getString(R.string.pref_typing_confirmation_key));
        final Preference findPreference2 = findPreference(getString(R.string.pref_typing_long_press_duration_key));
        if (findPreference != null && findPreference2 != null) {
            if (SpannableUtils$IdentifierSpan.getIntFromStringPref(this.prefs, getResources(), R.string.pref_typing_confirmation_key, R.string.pref_typing_confirmation_default) != 0) {
                z = true;
            } else {
                z = false;
            }
            findPreference2.setEnabled(z);
            findPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.AdvancedSettingFragment$$ExternalSyntheticLambda0
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    AdvancedSettingFragment.lambda$onCreatePreferences$1(Preference.this, preference, obj);
                    return true;
                }
            });
        }
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        updateTouchExplorationState();
        updateTimeFeedbackFormatPreference();
    }
}
