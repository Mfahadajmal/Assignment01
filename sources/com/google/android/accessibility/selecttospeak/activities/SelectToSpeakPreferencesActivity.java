package com.google.android.accessibility.selecttospeak.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import androidx.core.text.ICUCompat$Api24Impl;
import androidx.lifecycle.LifecycleOwner;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12;
import com.google.android.accessibility.selecttospeak.HelpAndFeedbackUtils;
import com.google.android.accessibility.selecttospeak.ScreenCapturePermissionHelper;
import com.google.android.accessibility.selecttospeak.SelectToSpeakService;
import com.google.android.accessibility.selecttospeak.overlayui.UIDelayedJobScheduler$scheduleAutoCollapse$1;
import com.google.android.accessibility.selecttospeak.tts.ChangeVoiceSettingsDialogUtil;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.WebActivity;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.gms.feedback.FeedbackOptions;
import com.google.android.gms.googlehelp.GoogleHelp;
import com.google.android.libraries.performance.primes.metrics.jank.WindowTrackerFactory;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceSubjectSegmentationInferenceLogEvent;
import googledata.experiments.mobile.accessibility_suite.features.S2sCommonConfig;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.coroutines.Continuation;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SelectToSpeakPreferencesActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class SelectToSpeakPreferenceFragment extends PreferenceFragmentCompat {
        private static final String HELP_URL = "https://support.google.com/accessibility/android/answer/6283677";
        private static final Set<String> OCR_SUPPORTED_LANGUAGES;
        private SwitchPreference ocrPreference;
        private final ScreenCapturePermissionHelper.ScreenCapturePermissionListener screenCapturePermissionListener = new ScreenCapturePermissionHelper.ScreenCapturePermissionListener() { // from class: com.google.android.accessibility.selecttospeak.activities.SelectToSpeakPreferencesActivity.SelectToSpeakPreferenceFragment.1
            public AnonymousClass1() {
            }

            @Override // com.google.android.accessibility.selecttospeak.ScreenCapturePermissionHelper.ScreenCapturePermissionListener
            public final void onAuthorizationFinished(boolean z) {
                if (SelectToSpeakPreferenceFragment.this.ocrPreference != null && !SelectToSpeakPreferenceFragment.this.getActivity().isDestroyed()) {
                    SelectToSpeakPreferenceFragment.this.ocrPreference.setChecked(z);
                }
                if (z) {
                    SelectToSpeakPreferenceFragment.this.saveOCRPreference(true);
                }
            }
        };
        private final Preference.OnPreferenceChangeListener ocrPreferenceChangeListener = new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(this, 11);

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* renamed from: com.google.android.accessibility.selecttospeak.activities.SelectToSpeakPreferencesActivity$SelectToSpeakPreferenceFragment$1 */
        /* loaded from: classes.dex */
        public final class AnonymousClass1 implements ScreenCapturePermissionHelper.ScreenCapturePermissionListener {
            public AnonymousClass1() {
            }

            @Override // com.google.android.accessibility.selecttospeak.ScreenCapturePermissionHelper.ScreenCapturePermissionListener
            public final void onAuthorizationFinished(boolean z) {
                if (SelectToSpeakPreferenceFragment.this.ocrPreference != null && !SelectToSpeakPreferenceFragment.this.getActivity().isDestroyed()) {
                    SelectToSpeakPreferenceFragment.this.ocrPreference.setChecked(z);
                }
                if (z) {
                    SelectToSpeakPreferenceFragment.this.saveOCRPreference(true);
                }
            }
        }

        /* renamed from: -$$Nest$fgetocrPreference */
        public static /* bridge */ /* synthetic */ SwitchPreference m87$$Nest$fgetocrPreference(SelectToSpeakPreferenceFragment selectToSpeakPreferenceFragment) {
            return selectToSpeakPreferenceFragment.ocrPreference;
        }

        /* renamed from: -$$Nest$fgetscreenCapturePermissionListener */
        public static /* bridge */ /* synthetic */ ScreenCapturePermissionHelper.ScreenCapturePermissionListener m88$$Nest$fgetscreenCapturePermissionListener(SelectToSpeakPreferenceFragment selectToSpeakPreferenceFragment) {
            return selectToSpeakPreferenceFragment.screenCapturePermissionListener;
        }

        /* renamed from: -$$Nest$msaveOCRPreference */
        public static /* bridge */ /* synthetic */ void m89$$Nest$msaveOCRPreference(SelectToSpeakPreferenceFragment selectToSpeakPreferenceFragment, boolean z) {
            selectToSpeakPreferenceFragment.saveOCRPreference(z);
        }

        static {
            HashSet hashSet = new HashSet();
            OCR_SUPPORTED_LANGUAGES = hashSet;
            hashSet.addAll(Arrays.asList("ca", "da", "nl", "en", "fi", "fr", "de", "hu", "it", "la", "no", "pl", "pt", "ro", "es", "sv", "tl", "tr"));
        }

        private void assignTtsIntent(boolean z) {
            Preference findPreference = findPreference(getString(R.string.s2s_pref_tts_settings_key));
            if (findPreference != null) {
                if (!z) {
                    getPreferenceScreen().removePreference$ar$ds(findPreference);
                    return;
                }
                Intent intent = new Intent("com.android.settings.TTS_SETTINGS");
                if (canHandleIntent(intent)) {
                    findPreference.setIntent(intent);
                } else {
                    getPreferenceScreen().removePreference$ar$ds(findPreference);
                }
            }
        }

        private void assignWebIntentToPreference(Preference preference, String str) {
            Uri parse = Uri.parse(str);
            Intent intent = new Intent("android.intent.action.VIEW", parse);
            FragmentActivity activity = getActivity();
            if (activity != null && !canHandleIntent(intent)) {
                Intent intent2 = new Intent(activity, (Class<?>) WebActivity.class);
                intent2.setData(parse);
                intent = intent2;
            }
            preference.setIntent(intent);
        }

        private boolean canHandleIntent(Intent intent) {
            List<ResolveInfo> queryIntentActivities;
            FragmentActivity activity = getActivity();
            if (activity == null || (queryIntentActivities = activity.getPackageManager().queryIntentActivities(intent, 0)) == null || queryIntentActivities.isEmpty()) {
                return false;
            }
            return true;
        }

        private void initializeChangeVoicePreference() {
            Preference findPreference;
            if (S2sCommonConfig.INSTANCE.get().enableChangeVoice(getContext()) && (findPreference = findPreference(getString(R.string.s2s_pref_change_voice_key))) != null) {
                findPreference.setOnPreferenceClickListener(new BrailleDisplaySettingsFragment.AnonymousClass2(this, 6));
            }
        }

        private void initializeHelpAndFeedbackPreference(boolean z) {
            PackageInfo packageInfo;
            Preference findPreference = findPreference(getString(R.string.s2s_pref_help_and_feedback_key));
            if (findPreference == null) {
                return;
            }
            if (!z) {
                getPreferenceScreen().removePreference$ar$ds(findPreference);
                return;
            }
            Context applicationContext = getActivity().getApplicationContext();
            Uri uri = HelpAndFeedbackUtils.HELP_FALLBACK_URI;
            try {
                packageInfo = applicationContext.getPackageManager().getPackageInfo("com.google.android.gms", 0);
            } catch (PackageManager.NameNotFoundException unused) {
                packageInfo = null;
            }
            if (packageInfo != null && packageInfo.versionCode > 9200000) {
                findPreference.setTitle(R.string.s2s_title_pref_help_and_feedback);
                findPreference.setOnPreferenceClickListener(new BrailleDisplaySettingsFragment.AnonymousClass2(this, 5));
            } else {
                findPreference.setTitle(R.string.s2s_title_pref_help);
                assignWebIntentToPreference(findPreference, "https://support.google.com/accessibility/android/answer/6283677");
            }
        }

        private void initializeOCRPreference() {
            String str;
            SwitchPreference switchPreference = (SwitchPreference) findPreference(getString(R.string.s2s_pref_ocr_key));
            this.ocrPreference = switchPreference;
            if (switchPreference != null) {
                switchPreference.setTitle(R.string.s2s_pref_ocr_title);
                if (isOCRSupportedWithCurrentLocale()) {
                    str = getString(R.string.s2s_pref_summary_ocr);
                } else {
                    str = getString(R.string.s2s_pref_summary_ocr) + "\n" + getString(R.string.s2s_warning_ocr_not_supported);
                }
                this.ocrPreference.setSummary(str);
                SelectToSpeakService selectToSpeakService = SelectToSpeakService.getInstance();
                if (SpannableUtils$IdentifierSpan.isAtLeastR()) {
                    this.ocrPreference.setEnabled(true);
                } else {
                    if (selectToSpeakService == null) {
                        this.ocrPreference.setEnabled(false);
                        return;
                    }
                    this.ocrPreference.setEnabled(true);
                    this.ocrPreference.setChecked(selectToSpeakService.screenCapturePermissionHelper.isAuthorizedForScreenCapture());
                    this.ocrPreference.setOnPreferenceChangeListener(this.ocrPreferenceChangeListener);
                }
            }
        }

        private static boolean isOCRSupportedWithCurrentLocale() {
            String language = Locale.getDefault().getLanguage();
            if (language == null) {
                return false;
            }
            return OCR_SUPPORTED_LANGUAGES.contains(language.split("[-_]+", -1)[0]);
        }

        public void saveOCRPreference(boolean z) {
            Context applicationContext = getActivity().getApplicationContext();
            SpannableUtils$IdentifierSpan.putBooleanPref(SpannableUtils$IdentifierSpan.getSharedPreferences(applicationContext), applicationContext.getResources(), R.string.s2s_pref_ocr_key, z);
        }

        /* renamed from: lambda$initializeChangeVoicePreference$0$com-google-android-accessibility-selecttospeak-activities-SelectToSpeakPreferencesActivity$SelectToSpeakPreferenceFragment */
        public /* synthetic */ boolean m90xaec384df(Preference preference) {
            FragmentActivity activity = getActivity();
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            activity.getClass();
            viewLifecycleOwner.getClass();
            ChangeVoiceSettingsDialogUtil.dialogJob = OnDeviceSubjectSegmentationInferenceLogEvent.launch$default$ar$ds$ar$edu(ICUCompat$Api24Impl.getLifecycleScope(viewLifecycleOwner), null, 0, new UIDelayedJobScheduler$scheduleAutoCollapse$1(activity, (Continuation) null, 2), 3);
            return true;
        }

        /* renamed from: lambda$initializeHelpAndFeedbackPreference$1$com-google-android-accessibility-selecttospeak-activities-SelectToSpeakPreferencesActivity$SelectToSpeakPreferenceFragment */
        public /* synthetic */ boolean m91x7e8bde85(Preference preference) {
            FragmentActivity activity = getActivity();
            GoogleHelp googleHelp = new GoogleHelp();
            googleHelp.enableSearch$ar$ds();
            googleHelp.fallbackSupportUri = HelpAndFeedbackUtils.HELP_FALLBACK_URI;
            FeedbackOptions.Builder builder = new FeedbackOptions.Builder();
            builder.setExcludePii$ar$ds();
            builder.setCategoryTag$ar$ds();
            builder.themeSettings = HelpAndFeedbackUtils.getThemeSettings();
            googleHelp.setFeedbackOptions$ar$ds(builder.build(), activity.getCacheDir());
            googleHelp.themeSettings = HelpAndFeedbackUtils.getThemeSettings();
            Intent buildHelpIntent = googleHelp.buildHelpIntent();
            buildHelpIntent.addFlags(268435456);
            new WindowTrackerFactory((Activity) activity).launch(buildHelpIntent);
            return true;
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            getPreferenceManager().setStorageDeviceProtected();
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.selecttospeak_preferences);
            boolean allowLinksOutOfSettings = SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(getActivity().getApplicationContext());
            assignTtsIntent(allowLinksOutOfSettings);
            initializeHelpAndFeedbackPreference(allowLinksOutOfSettings);
            initializeOCRPreference();
            initializeChangeVoicePreference();
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new SelectToSpeakPreferenceFragment();
    }
}
