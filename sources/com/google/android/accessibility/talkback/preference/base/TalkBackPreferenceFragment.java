package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceGroup;
import com.android.talkback.TalkBackPreferencesActivity;
import com.google.android.accessibility.talkback.HatsSurveyRequester;
import com.google.android.accessibility.talkback.HelpAndFeedbackUtils;
import com.google.android.accessibility.talkback.NotificationActivity;
import com.google.android.accessibility.talkback.actor.ImageCaptioner;
import com.google.android.accessibility.talkback.training.OnboardingInitiator;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity;
import com.google.android.accessibility.talkback.trainingcommon.TrainingConfig;
import com.google.android.accessibility.talkback.trainingcommon.tv.VendorConfigReader;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.surveys.SurveyData;
import com.google.android.marvin.talkback.R;
import j$.util.Optional;
import j$.util.function.Consumer$CC;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackPreferenceFragment extends TalkbackBaseFragment {
    private Context context;
    private final FormFactorUtils formFactorUtils;
    private Optional<HatsSurveyRequester> hatsSurveyRequester;
    private final Preference.OnPreferenceChangeListener preferenceChangeListener;
    private SharedPreferences prefs;
    private SettingsMetricStore settingsMetricStore;

    public TalkBackPreferenceFragment() {
        super(R.xml.preferences);
        this.formFactorUtils = FormFactorUtils.getInstance();
        this.preferenceChangeListener = new Preference.OnPreferenceChangeListener(this) { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackPreferenceFragment.1
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object obj) {
                if ((preference instanceof ListPreference) && (obj instanceof String)) {
                    ListPreference listPreference = (ListPreference) preference;
                    int findIndexOfValue = listPreference.findIndexOfValue((String) obj);
                    CharSequence[] entries = listPreference.getEntries();
                    if (findIndexOfValue >= 0 && findIndexOfValue < entries.length) {
                        preference.setSummary(entries[findIndexOfValue].toString().replaceAll("%", "%%"));
                        return true;
                    }
                    preference.setSummary("");
                    return true;
                }
                return true;
            }
        };
    }

    private void assignNewFeaturesIntent() {
        Intent createTrainingIntent;
        Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_new_feature_in_talkback_entry_point_key);
        if (findPreferenceByResId != null) {
            FormFactorUtils formFactorUtils = this.formFactorUtils;
            if (!formFactorUtils.isAndroidTv) {
                if (formFactorUtils.isAndroidWear) {
                    createTrainingIntent = NotificationActivity.createStartIntent(this.context, R.string.wear_new_feature_page_title, R.string.wear_new_feature_page_content, Integer.MIN_VALUE, R.string.wear_new_feature_page_button_content_description, null);
                } else {
                    Context context = this.context;
                    int i = OnboardingInitiator.OnboardingInitiator$ar$NoOp;
                    createTrainingIntent = TrainingActivity.createTrainingIntent(context, TrainingConfig.TrainingId.TRAINING_ID_ON_BOARDING_TALKBACK, false);
                }
                findPreferenceByResId.setIntent(createTrainingIntent);
            }
        }
    }

    private void assignPlayStoreIntentToPreference() {
        Uri parse;
        Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_play_store_key);
        if (findPreferenceByResId != null) {
            PreferenceGroup preferenceGroup = (PreferenceGroup) findPreferenceByResId(R.string.pref_category_general_key);
            if (!getResources().getBoolean(R.bool.show_play_store)) {
                if (preferenceGroup != null) {
                    preferenceGroup.removePreference$ar$ds(findPreferenceByResId);
                    return;
                }
                return;
            }
            if (this.formFactorUtils.isAndroidWear) {
                parse = Uri.parse("market://details?id=com.google.android.marvin.talkback");
            } else {
                parse = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.marvin.talkback");
            }
            Intent intent = new Intent("android.intent.action.VIEW", parse);
            if (canHandleIntent(intent)) {
                findPreferenceByResId.setIntent(intent);
            } else if (preferenceGroup != null) {
                preferenceGroup.removePreference$ar$ds(findPreferenceByResId);
            }
        }
    }

    private void assignTtsSettingsIntent() {
        String str;
        PreferenceGroup preferenceGroup = (PreferenceGroup) findPreferenceByResId(R.string.pref_category_audio_key);
        Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_tts_settings_key);
        if (preferenceGroup != null && findPreferenceByResId != null) {
            if (true != this.formFactorUtils.isAndroidTv) {
                str = "com.android.settings.TTS_SETTINGS";
            } else {
                str = "android.settings.TTS_SETTINGS";
            }
            Intent intent = new Intent(str);
            if (!canHandleIntent(intent)) {
                preferenceGroup.removePreference$ar$ds(findPreferenceByResId);
            }
            findPreferenceByResId.setIntent(intent);
        }
    }

    private boolean canHandleIntent(Intent intent) {
        List<ResolveInfo> queryIntentActivities = this.context.getPackageManager().queryIntentActivities(intent, 0);
        if (queryIntentActivities == null || queryIntentActivities.isEmpty()) {
            return false;
        }
        return true;
    }

    private void fixListSummaries(PreferenceGroup preferenceGroup) {
        if (preferenceGroup != null) {
            int preferenceCount = preferenceGroup.getPreferenceCount();
            for (int i = 0; i < preferenceCount; i++) {
                Preference preference = preferenceGroup.getPreference(i);
                if (preference instanceof PreferenceGroup) {
                    fixListSummaries((PreferenceGroup) preference);
                } else if (preference instanceof ListPreference) {
                    this.preferenceChangeListener.onPreferenceChange(preference, ((ListPreference) preference).getValue());
                    preference.setOnPreferenceChangeListener(this.preferenceChangeListener);
                }
            }
        }
    }

    private static PackageInfo getPackageInfo(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$updateSurveyOption$5(HatsSurveyRequester hatsSurveyRequester) {
        if (hatsSurveyRequester.surveyAvailable && !hatsSurveyRequester.cachedSurveyData.isEmpty()) {
            hatsSurveyRequester.presentSurvey$ar$class_merging$ar$class_merging$ar$class_merging(hatsSurveyRequester.surveysClient$ar$class_merging$ar$class_merging$ar$class_merging, (SurveyData) hatsSurveyRequester.cachedSurveyData.get());
            hatsSurveyRequester.surveyAvailable = false;
            hatsSurveyRequester.cachedSurveyData = Optional.empty();
        }
    }

    private void removeCategory(int i) {
        PreferenceCategory preferenceCategory = (PreferenceCategory) findPreferenceByResId(i);
        if (preferenceCategory != null) {
            getPreferenceScreen().removePreference$ar$ds(preferenceCategory);
        }
    }

    private void removePreference(int i, int i2) {
        PreferenceGroup preferenceGroup = (PreferenceGroup) findPreferenceByResId(i);
        if (preferenceGroup != null) {
            SpannableUtils$IdentifierSpan.hidePreference(this.context, preferenceGroup, i2);
        }
    }

    private void showTalkBackVersion() {
        Preference findPreferenceByResId;
        String string;
        PackageInfo packageInfo = getPackageInfo(this.context);
        if (packageInfo != null && (findPreferenceByResId = findPreferenceByResId(R.string.pref_play_store_key)) != null) {
            Matcher matcher = Pattern.compile("[0-9]+\\.[0-9]+").matcher(String.valueOf(packageInfo.versionName));
            if (matcher.find()) {
                string = getString(R.string.summary_pref_play_store, matcher.group());
            } else {
                string = getString(R.string.summary_pref_play_store, String.valueOf(packageInfo.versionName));
            }
            findPreferenceByResId.setSummary(string);
        }
    }

    private void updateGeminiPreferenceState() {
        int i;
        Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_gemini_settings_key);
        if (findPreferenceByResId != null) {
            if (true != SpannableUtils$IdentifierSpan.getBooleanPref(this.prefs, this.context.getResources(), R.string.pref_gemini_enabled_key, R.bool.pref_gemini_opt_in_default)) {
                i = R.string.summary_pref_gemini_support_disabled;
            } else {
                i = R.string.summary_pref_gemini_support_enabled;
            }
            findPreferenceByResId.setSummary(i);
            return;
        }
        removePreference(R.string.pref_category_controls_key, R.string.pref_gemini_settings_key);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateSurveyOption, reason: merged with bridge method [inline-methods] */
    public void m174xc0ad4615() {
        final Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_survey_setting_entry_point_key);
        if (findPreferenceByResId == null) {
            return;
        }
        if (this.hatsSurveyRequester.isEmpty()) {
            findPreferenceByResId.setVisible(false);
        } else if (!this.hatsSurveyRequester.get().surveyAvailable) {
            findPreferenceByResId.setVisible(false);
        } else {
            findPreferenceByResId.setVisible(true);
            findPreferenceByResId.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackPreferenceFragment$$ExternalSyntheticLambda5
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return TalkBackPreferenceFragment.this.m176xcb1a1e3a(findPreferenceByResId, preference);
                }
            });
        }
    }

    private void updateTutorialAndHelpPreferencesForPhoneOrTablet() {
        Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_tutorial_and_help_key);
        if (findPreferenceByResId != null) {
            findPreferenceByResId.setIntent(SpannableUtils$IdentifierSpan.createTutorialIntent(getActivity()));
        }
        Preference findPreferenceByResId2 = findPreferenceByResId(R.string.pref_help_and_feedback_key);
        if (findPreferenceByResId2 != null) {
            findPreferenceByResId2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackPreferenceFragment$$ExternalSyntheticLambda3
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return TalkBackPreferenceFragment.this.m177xf5357861(preference);
                }
            });
        }
        Preference findPreferenceByResId3 = findPreferenceByResId(R.string.pref_gup_key);
        if (findPreferenceByResId3 != null) {
            findPreferenceByResId3.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackPreferenceFragment$$ExternalSyntheticLambda4
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return TalkBackPreferenceFragment.this.m178x3ad6bb00(preference);
                }
            });
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://support.google.com/accessibility/answer/7641084"));
            findPreferenceByResId3.setIntent(intent);
        }
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.talkback_preferences_title);
    }

    /* renamed from: lambda$onCreatePreferences$1$com-google-android-accessibility-talkback-preference-base-TalkBackPreferenceFragment, reason: not valid java name */
    public /* synthetic */ void m175x64e88b4(HatsSurveyRequester hatsSurveyRequester) {
        hatsSurveyRequester.setOnSurveyAvailableListener$ar$class_merging(new TalkBackPreferenceFragment$$ExternalSyntheticLambda6(this));
    }

    /* renamed from: lambda$updateSurveyOption$6$com-google-android-accessibility-talkback-preference-base-TalkBackPreferenceFragment, reason: not valid java name */
    public /* synthetic */ boolean m176xcb1a1e3a(Preference preference, Preference preference2) {
        this.hatsSurveyRequester.ifPresent(new Consumer() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackPreferenceFragment$$ExternalSyntheticLambda1
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                TalkBackPreferenceFragment.lambda$updateSurveyOption$5((HatsSurveyRequester) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        preference.setVisible(false);
        return true;
    }

    /* renamed from: lambda$updateTutorialAndHelpPreferencesForPhoneOrTablet$2$com-google-android-accessibility-talkback-preference-base-TalkBackPreferenceFragment, reason: not valid java name */
    public /* synthetic */ boolean m177xf5357861(Preference preference) {
        HelpAndFeedbackUtils.launchHelpAndFeedback(getActivity());
        return true;
    }

    /* renamed from: lambda$updateTutorialAndHelpPreferencesForPhoneOrTablet$3$com-google-android-accessibility-talkback-preference-base-TalkBackPreferenceFragment, reason: not valid java name */
    public /* synthetic */ boolean m178x3ad6bb00(Preference preference) {
        if (SpannableUtils$IdentifierSpan.isNetworkConnected(this.context)) {
            this.settingsMetricStore.onGupPreferenceClicked();
            return false;
        }
        return false;
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        int i;
        Preference findPreferenceByResId;
        super.onCreatePreferences(bundle, str);
        Context context = getContext();
        this.context = context;
        if (context == null) {
            return;
        }
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        this.settingsMetricStore = new SettingsMetricStore(this.context);
        fixListSummaries(getPreferenceScreen());
        Optional<HatsSurveyRequester> ofNullable = Optional.ofNullable(((TalkBackPreferencesActivity.HatsRequesterViewModel) new ViewModelProvider(getActivity()).get(TalkBackPreferencesActivity.HatsRequesterViewModel.class)).hatsSurveyRequester);
        this.hatsSurveyRequester = ofNullable;
        ofNullable.ifPresent(new Consumer() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackPreferenceFragment$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                TalkBackPreferenceFragment.this.m175x64e88b4((HatsSurveyRequester) obj);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
        assignNewFeaturesIntent();
        showTalkBackVersion();
        if (!SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(this.context) && !this.formFactorUtils.isAndroidTv) {
            SpannableUtils$IdentifierSpan.hidePreference(this.context, getPreferenceScreen(), R.string.pref_play_store_key);
            removeCategory(R.string.pref_category_legal_and_privacy_key);
            removePreference(R.string.pref_category_advanced_key, R.string.pref_manage_labels_key);
            removePreference(R.string.pref_category_audio_key, R.string.pref_tts_settings_key);
        } else {
            assignTtsSettingsIntent();
            assignPlayStoreIntentToPreference();
        }
        if (!FeatureSupport.isVibratorSupported(this.context) && (findPreferenceByResId = findPreferenceByResId(R.string.pref_sound_and_vibration_key)) != null) {
            findPreferenceByResId.setTitle(R.string.title_pref_sound);
        }
        if (!FeatureSupport.supportBrailleDisplay(this.context) && !FeatureSupport.supportBrailleKeyboard(this.context)) {
            removeCategory(R.string.pref_category_braille_key);
        }
        FormFactorUtils formFactorUtils = this.formFactorUtils;
        if (formFactorUtils.isAndroidTv) {
            Preference findPreferenceByResId2 = findPreferenceByResId(R.string.pref_tutorial_and_help_key);
            if (findPreferenceByResId2 != null) {
                if (true != SpannableUtils$IdentifierSpan.shouldShowTraining(VendorConfigReader.retrieveConfig(this.context))) {
                    i = R.string.title_pref_category_help_no_tutorial;
                } else {
                    i = R.string.title_pref_category_tutorial;
                }
                findPreferenceByResId2.setTitle(i);
                findPreferenceByResId2.setFragment(TutorialAndHelpFragment.class.getName());
            }
        } else if (formFactorUtils.isAndroidWear) {
            Preference findPreferenceByResId3 = findPreferenceByResId(R.string.pref_tutorial_key);
            if (findPreferenceByResId3 != null) {
                findPreferenceByResId3.setIntent(SpannableUtils$IdentifierSpan.createTutorialIntent(getActivity()));
            }
            findPreferenceByResId(R.string.pref_help_key);
        } else {
            updateTutorialAndHelpPreferencesForPhoneOrTablet();
        }
        if (!ImageCaptioner.supportsImageCaption$ar$ds()) {
            removePreference(R.string.pref_category_controls_key, R.string.pref_auto_image_captioning_key);
        }
        updateGeminiPreferenceState();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, android.support.v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        this.hatsSurveyRequester.ifPresent(new Consumer() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackPreferenceFragment$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                ((HatsSurveyRequester) obj).setOnSurveyAvailableListener$ar$class_merging(null);
            }

            public /* synthetic */ Consumer andThen(Consumer consumer) {
                return Consumer$CC.$default$andThen(this, consumer);
            }
        });
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        m174xc0ad4615();
        updateGeminiPreferenceState();
    }
}
