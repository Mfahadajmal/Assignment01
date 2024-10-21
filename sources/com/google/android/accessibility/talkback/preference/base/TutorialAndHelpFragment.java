package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import androidx.preference.Preference;
import com.google.android.accessibility.talkback.HelpAndFeedbackUtils;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.talkback.trainingcommon.tv.VendorConfigReader;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.WebActivity;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TutorialAndHelpFragment extends TalkbackBaseFragment {
    public TutorialAndHelpFragment() {
        super(R.xml.help_preferences);
    }

    private void assignFeedbackIntentToPreference() {
        PackageInfo packageInfo;
        Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_help_and_feedback_key);
        if (findPreferenceByResId != null) {
            Context context = getContext();
            if (!FormFactorUtils.getInstance().isAndroidWear && !FormFactorUtils.getInstance().isAndroidTv) {
                try {
                    packageInfo = context.getPackageManager().getPackageInfo("com.google.android.gms", 0);
                } catch (PackageManager.NameNotFoundException unused) {
                    packageInfo = null;
                }
                if (packageInfo != null && packageInfo.versionCode > 9200000) {
                    findPreferenceByResId.setTitle(R.string.title_pref_help_and_feedback);
                    if (!FormFactorUtils.getInstance().isAndroidAuto) {
                        findPreferenceByResId.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TutorialAndHelpFragment$$ExternalSyntheticLambda0
                            @Override // androidx.preference.Preference.OnPreferenceClickListener
                            public final boolean onPreferenceClick(Preference preference) {
                                return TutorialAndHelpFragment.this.m179x6dc56896(preference);
                            }
                        });
                        return;
                    }
                    return;
                }
            }
            findPreferenceByResId.setTitle(R.string.title_pref_help);
            if (FormFactorUtils.getInstance().isAndroidTv) {
                findPreferenceByResId.setIntent(new Intent(getContext(), (Class<?>) WebActivity.class).setData(Uri.parse(PreferencesActivityUtils.HELP_URL)));
            }
        }
    }

    private void assignPracticeGesturesIntent() {
        Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_practice_gestures_entry_point_key);
        if (findPreferenceByResId == null) {
            return;
        }
        findPreferenceByResId.setIntent(SpannableUtils$IdentifierSpan.createPracticeGesturesIntent(getActivity()));
    }

    private void assignTutorialIntent() {
        Preference findPreferenceByResId = findPreferenceByResId(R.string.pref_tutorial_entry_point_key);
        if (findPreferenceByResId == null) {
            return;
        }
        findPreferenceByResId.setIntent(SpannableUtils$IdentifierSpan.createTutorialIntent(getActivity()));
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        boolean z = FormFactorUtils.getInstance().isAndroidTv;
        int i = R.string.title_pref_category_tutorial;
        if (z) {
            if (true != SpannableUtils$IdentifierSpan.shouldShowTraining(VendorConfigReader.retrieveConfig(getActivity()))) {
                i = R.string.title_pref_category_help_no_tutorial;
            }
            return getString(i);
        }
        return getText(R.string.title_pref_category_tutorial);
    }

    /* renamed from: lambda$assignFeedbackIntentToPreference$0$com-google-android-accessibility-talkback-preference-base-TutorialAndHelpFragment, reason: not valid java name */
    public /* synthetic */ boolean m179x6dc56896(Preference preference) {
        HelpAndFeedbackUtils.launchHelpAndFeedback(getActivity());
        return true;
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        assignTutorialIntent();
        assignPracticeGesturesIntent();
        assignFeedbackIntentToPreference();
    }
}
