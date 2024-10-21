package com.android.talkback;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.BackStackRecord;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentFactory;
import android.support.v4.app.FragmentOnAttachListener;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.accessibility.talkback.HatsSurveyRequester;
import com.google.android.accessibility.talkback.preference.base.TalkBackPreferenceFragment;
import com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment;
import com.google.android.accessibility.talkback.preference.search.TalkBackSearchIndexablesProvider;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.libraries.performance.primes.metrics.jank.JankMetricService;
import com.google.android.libraries.surveys.SurveyRequest;
import com.google.android.marvin.talkback.R;
import googledata.experiments.mobile.accessibility_suite.features.HatsSurveyConfig;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackPreferencesActivity extends PreferencesActivity implements FragmentOnAttachListener {
    private HatsSurveyRequester hatsSurveyRequester;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class HatsRequesterViewModel extends ViewModel {
        public HatsSurveyRequester hatsSurveyRequester;
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class TalkBackSubSettings extends TalkBackPreferencesActivity {
    }

    private static final void assignSearchFragment$ar$ds(Intent intent) {
        if (TalkBackSearchIndexablesProvider.isFromSearchIndexablesContract(intent)) {
            TalkBackSearchIndexablesProvider.assignToFragmentFromSearch(intent);
        }
    }

    private static PreferenceFragmentCompat getFragmentByName(String str) {
        if (TextUtils.isEmpty(str)) {
            return new TalkBackPreferenceFragment();
        }
        try {
            return (PreferenceFragmentCompat) Class.forName(str).newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException unused) {
            LogUtils.d("PreferencesActivity", "Failed to load class: %s", str);
            return null;
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        String str;
        Intent intent = getIntent();
        if (intent != null) {
            str = intent.getStringExtra("FragmentName");
        } else {
            str = null;
        }
        return getFragmentByName(str);
    }

    @Override // android.support.v4.app.SupportActivity, android.app.Activity, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        HatsSurveyRequester hatsSurveyRequester;
        if (keyEvent.getKeyCode() == 4 && keyEvent.getAction() == 1 && (hatsSurveyRequester = this.hatsSurveyRequester) != null && hatsSurveyRequester.formFactorUtils.isAndroidTv && hatsSurveyRequester.surveyShown) {
            hatsSurveyRequester.dismissSurvey();
            return false;
        }
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.support.v4.app.FragmentOnAttachListener
    public final void onAttachFragment$ar$ds(Fragment fragment) {
        HatsSurveyRequester hatsSurveyRequester;
        if ((fragment instanceof TalkbackBaseFragment) && !(fragment instanceof TalkBackPreferenceFragment) && (hatsSurveyRequester = this.hatsSurveyRequester) != null) {
            hatsSurveyRequester.dismissSurvey();
            this.hatsSurveyRequester = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity, com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        String triggerId;
        assignSearchFragment$ar$ds(getIntent());
        getSupportFragmentManager().addFragmentOnAttachListener(this);
        super.onCreate(bundle);
        int layoutDirectionFromLocale = TextUtils.getLayoutDirectionFromLocale(Locale.getDefault());
        int layoutDirection = getResources().getConfiguration().getLayoutDirection();
        if (layoutDirectionFromLocale == 1 && layoutDirection != 1) {
            getWindow().getDecorView().setLayoutDirection(1);
        }
        if (SpannableUtils$IdentifierSpan.hasGmsCorePackage(this)) {
            HatsSurveyRequester hatsSurveyRequester = new HatsSurveyRequester(this);
            this.hatsSurveyRequester = hatsSurveyRequester;
            Context applicationContext = hatsSurveyRequester.activity.getApplicationContext();
            SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(applicationContext);
            if (hatsSurveyRequester.activity.findViewById(R.id.survey_prompt_parent_sheet) != null && !hatsSurveyRequester.formFactorUtils.isAndroidWear && !sharedPreferences.getBoolean("first_time_user", true) && SpannableUtils$IdentifierSpan.allowLinksOutOfSettings(applicationContext)) {
                if (hatsSurveyRequester.formFactorUtils.isAndroidTv) {
                    triggerId = HatsSurveyConfig.INSTANCE.get().tvTriggerId(applicationContext);
                } else {
                    triggerId = HatsSurveyConfig.INSTANCE.get().triggerId(applicationContext);
                }
                SurveyRequest.Builder builder = new SurveyRequest.Builder(applicationContext, triggerId);
                builder.requestSurveyCallback = new HatsSurveyRequester.AnonymousClass1(hatsSurveyRequester, 0);
                builder.enableProofMode = hatsSurveyRequester.proofMode;
                builder.apiKey = HatsSurveyConfig.INSTANCE.get().apiKey(applicationContext);
                SurveyRequest build = builder.build();
                JankMetricService jankMetricService = hatsSurveyRequester.surveysClient$ar$class_merging$ar$class_merging$ar$class_merging;
                JankMetricService.requestSurvey$ar$ds(build);
            }
            ((HatsRequesterViewModel) new ViewModelProvider(this).get(HatsRequesterViewModel.class)).hatsSurveyRequester = this.hatsSurveyRequester;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().mOnAttachListeners.remove(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        assignSearchFragment$ar$ds(getIntent());
        PreferenceFragmentCompat fragmentByName = getFragmentByName(intent.getStringExtra("FragmentName"));
        fragmentByName.setArguments(intent.getBundleExtra("FragmentArgs"));
        LogUtils.e("PreferencesActivity", "onNewIntent/getContainerId()= %s", Integer.valueOf(getContainerId()));
        BackStackRecord backStackRecord = new BackStackRecord(getSupportFragmentManager());
        backStackRecord.replace$ar$ds(getContainerId(), fragmentByName, null);
        backStackRecord.addToBackStack$ar$ds(null);
        backStackRecord.commit();
    }

    public final boolean onPreferenceStartFragment$ar$ds(Preference preference) {
        if (FormFactorUtils.getInstance().isAndroidWear && preference.getFragment() != null) {
            FragmentFactory fragmentFactory = getSupportFragmentManager().getFragmentFactory();
            getClassLoader();
            if (fragmentFactory.instantiate$ar$ds(preference.getFragment()) instanceof TalkbackBaseFragment) {
                Intent intent = new Intent(this, (Class<?>) TalkBackSubSettings.class);
                intent.putExtra("FragmentName", preference.getFragment());
                intent.putExtra("FragmentArgs", preference.getExtras());
                startActivity(intent);
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final boolean supportHatsSurvey() {
        return SpannableUtils$IdentifierSpan.hasGmsCorePackage(this);
    }
}
