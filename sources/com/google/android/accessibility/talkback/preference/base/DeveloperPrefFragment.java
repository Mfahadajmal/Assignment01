package com.google.android.accessibility.talkback.preference.base;

import _COROUTINE._BOUNDARY;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.TwoStatePreference;
import com.google.android.accessibility.talkback.TalkBackService;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import googledata.experiments.mobile.accessibility_suite.features.GestureSetConfig;

/* compiled from: PG */
/* loaded from: classes.dex */
public class DeveloperPrefFragment extends TalkbackBaseFragment {
    private static final String TAG = "DeveloperPrefFragment";
    private boolean contentObserverRegistered;
    private Context context;
    private A11yAlertDialogWrapper exploreByTouchDialog;
    private FragmentManager fragmentManager;
    private final Preference.OnPreferenceChangeListener logLevelChangeListener;
    private A11yAlertDialogWrapper logLevelOptInDialog;
    private int logOptInLevel;
    private final Preference.OnPreferenceChangeListener performanceStatsChangeListener;
    private A11yAlertDialogWrapper performanceStatsDialog;
    private SharedPreferences prefs;
    private final Preference.OnPreferenceChangeListener touchExplorationChangeListener;
    private final ContentObserver touchExploreObserver;
    private final Preference.OnPreferenceChangeListener treeDebugChangeListener;
    private A11yAlertDialogWrapper treeDebugDialog;
    private String versionInfo;

    public DeveloperPrefFragment() {
        super(R.xml.developer_preferences);
        this.logOptInLevel = 6;
        this.contentObserverRegistered = false;
        this.touchExploreObserver = new ContentObserver(new Handler()) { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment.1
            @Override // android.database.ContentObserver
            public void onChange(boolean z) {
                if (!z) {
                    DeveloperPrefFragment.this.updateTouchExplorationDisplay();
                }
            }
        };
        this.touchExplorationChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment.2
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object obj) {
                if (Boolean.TRUE.equals(obj)) {
                    return DeveloperPrefFragment.this.setTouchExplorationRequested(true);
                }
                DeveloperPrefFragment.this.exploreByTouchDialog.show();
                return false;
            }
        };
        this.treeDebugChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment.3
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object obj) {
                if (Boolean.TRUE.equals(obj)) {
                    DeveloperPrefFragment.this.treeDebugDialog.show();
                    return false;
                }
                DeveloperPrefFragment.this.disableAndRemoveGesture(R.string.pref_tree_debug_key, R.string.shortcut_value_print_node_tree);
                return true;
            }
        };
        this.performanceStatsChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment.4
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object obj) {
                if (Boolean.TRUE.equals(obj)) {
                    DeveloperPrefFragment.this.performanceStatsDialog.show();
                    return false;
                }
                DeveloperPrefFragment.this.disableAndRemoveGesture(R.string.pref_performance_stats_key, R.string.shortcut_value_print_performance_stats);
                return true;
            }
        };
        this.logLevelChangeListener = new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment.5
            @Override // androidx.preference.Preference.OnPreferenceChangeListener
            public boolean onPreferenceChange(Preference preference, Object obj) {
                DeveloperPrefFragment.this.logOptInLevel = Integer.parseInt((String) obj);
                if (DeveloperPrefFragment.this.logOptInLevel < 6) {
                    DeveloperPrefFragment.this.logLevelOptInDialog.show();
                    return false;
                }
                LogUtils.minLogLevel = DeveloperPrefFragment.this.logOptInLevel;
                return true;
            }
        };
    }

    private void checkTelevision() {
        if (FormFactorUtils.getInstance().isAndroidTv) {
            findPreference(getString(R.string.pref_tree_debug_reflect_key)).setSummary(getString(R.string.summary_pref_tree_debug_tv));
        }
    }

    private void initTouchExplorationPreference() {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        final TwoStatePreference twoStatePreference = (TwoStatePreference) findPreference(getString(R.string.pref_explore_by_touch_reflect_key));
        if (twoStatePreference == null) {
            return;
        }
        twoStatePreference.setPersistent(false);
        updateTouchExplorationDisplay();
        twoStatePreference.setOnPreferenceChangeListener(this.touchExplorationChangeListener);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(this.context, 1);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$ar$ds(R.string.dialog_title_disable_exploration);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setMessage$ar$class_merging$ar$ds(R.string.dialog_message_disable_exploration);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(android.R.string.cancel, null);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setOnCancelListener$ar$class_merging$ar$ds();
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                DeveloperPrefFragment.this.m133x817f599(twoStatePreference, dialogInterface, i);
            }
        });
        this.exploreByTouchDialog = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create();
    }

    private void initVersionInfo() {
        long j;
        this.versionInfo = null;
        try {
            PackageInfo packageInfo = this.context.getPackageManager().getPackageInfo(this.context.getPackageName(), 0);
            if (packageInfo != null) {
                if (SpannableUtils$IdentifierSpan.isAtLeastP()) {
                    j = packageInfo.getLongVersionCode();
                } else {
                    j = packageInfo.versionCode;
                }
                this.versionInfo = getString(R.string.talkback_preferences_subtitle, packageInfo.versionName + " (" + j + ")");
            }
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtils.e(TAG, "Can't find PackageInfo by the package name.", new Object[0]);
        }
    }

    private static boolean isTouchExplorationEnabled(ContentResolver contentResolver) {
        if (Settings.Secure.getInt(contentResolver, "touch_exploration_enabled", 0) != 1) {
            return false;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setTouchExplorationRequested(boolean z) {
        SpannableUtils$IdentifierSpan.putBooleanPref(SpannableUtils$IdentifierSpan.getSharedPreferences(this.context), getResources(), R.string.pref_explore_by_touch_key, z);
        if (TalkBackService.isServiceActive()) {
            TalkBackService talkBackService = TalkBackService.instance;
            if (z && talkBackService != null && talkBackService.shouldShowTutorial()) {
                talkBackService.showTutorial();
            }
            LogUtils.d(TAG, "TalkBack active, waiting for EBT request to take effect", new Object[0]);
            return false;
        }
        return true;
    }

    private static boolean shouldShowVersionInSubtitle() {
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21() && !FormFactorUtils.getInstance().isAndroidWear && !FormFactorUtils.getInstance().isAndroidTv) {
            return true;
        }
        return false;
    }

    private void updateDisplayForDiagnosisMode() {
        if (PreferencesActivityUtils.isDiagnosisModeOn(this.prefs, this.context.getResources())) {
            updateDisplayForDiagnosisModeOn();
        } else {
            updateDisplayForDiagnosisModeOff();
        }
    }

    private void updateDisplayForDiagnosisModeOff() {
        setEnabled(this.context, R.string.pref_tts_overlay_key, true);
        setEnabled(this.context, R.string.pref_echo_recognized_text_speech_key, true);
        setEnabled(this.context, R.string.pref_tree_debug_reflect_key, true);
        setEnabled(this.context, R.string.pref_log_overlay_key, true);
        setEnabled(this.context, R.string.pref_log_level_key, true);
    }

    private void updateDisplayForDiagnosisModeOn() {
        setEnabled(this.context, R.string.pref_tts_overlay_key, false);
        setEnabled(this.context, R.string.pref_echo_recognized_text_speech_key, false);
        setEnabled(this.context, R.string.pref_tree_debug_reflect_key, false);
        setEnabled(this.context, R.string.pref_log_overlay_key, false);
        setEnabled(this.context, R.string.pref_log_level_key, false);
    }

    private void updateDumpA11yEventPreferenceSummary() {
        Preference findPreference = findPreference(getString(R.string.pref_dump_a11y_event_key));
        if (findPreference != null && this.prefs != null) {
            int[] allEventTypes = SpannableUtils$IdentifierSpan.getAllEventTypes();
            int i = this.prefs.getInt(getString(R.string.pref_dump_event_mask_key), 0);
            int i2 = 0;
            for (int i3 = 0; i3 < 25; i3++) {
                if ((allEventTypes[i3] & i) != 0) {
                    i2++;
                }
            }
            findPreference.setSummary(getResources().getQuantityString(R.plurals.template_dump_event_count, i2, Integer.valueOf(i2)));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTouchExplorationDisplay() {
        boolean z;
        TwoStatePreference twoStatePreference = (TwoStatePreference) findPreference(getString(R.string.pref_explore_by_touch_reflect_key));
        if (twoStatePreference != null) {
            ContentResolver contentResolver = this.context.getContentResolver();
            Resources resources = getResources();
            SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(this.context);
            boolean booleanPref = SpannableUtils$IdentifierSpan.getBooleanPref(sharedPreferences, resources, R.string.pref_explore_by_touch_key, R.bool.pref_explore_by_touch_default);
            boolean isChecked = twoStatePreference.isChecked();
            if (TalkBackService.isServiceActive()) {
                z = isTouchExplorationEnabled(contentResolver);
            } else {
                z = booleanPref;
            }
            if (booleanPref != z) {
                LogUtils.d(TAG, "Set touch exploration preference to reflect actual state %b", Boolean.valueOf(z));
                SpannableUtils$IdentifierSpan.putBooleanPref(sharedPreferences, resources, R.string.pref_explore_by_touch_key, z);
            }
            if (isChecked != z) {
                twoStatePreference.setChecked(z);
            }
        }
    }

    protected void disableAndRemoveGesture(int i, int i2) {
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(this.context);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(getString(i), false);
        for (String str : getResources().getStringArray(R.array.pref_shortcut_keys)) {
            if (getString(i2).equals(sharedPreferences.getString(str, null))) {
                edit.putString(str, getString(R.string.shortcut_value_unassigned));
            }
        }
        edit.apply();
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getSubTitle() {
        if (shouldShowVersionInSubtitle()) {
            return this.versionInfo;
        }
        return null;
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.title_pref_category_developer_settings);
    }

    /* renamed from: lambda$initTouchExplorationPreference$7$com-google-android-accessibility-talkback-preference-base-DeveloperPrefFragment, reason: not valid java name */
    public /* synthetic */ void m133x817f599(TwoStatePreference twoStatePreference, DialogInterface dialogInterface, int i) {
        if (setTouchExplorationRequested(false)) {
            twoStatePreference.setChecked(false);
        }
    }

    /* renamed from: lambda$onCreatePreferences$0$com-google-android-accessibility-talkback-preference-base-DeveloperPrefFragment, reason: not valid java name */
    public /* synthetic */ void m134x7aaf9b66(TwoStatePreference twoStatePreference, DialogInterface dialogInterface, int i) {
        SpannableUtils$IdentifierSpan.storeBooleanAsync(this.prefs, getString(R.string.pref_tree_debug_key), true);
        twoStatePreference.setChecked(true);
    }

    /* renamed from: lambda$onCreatePreferences$1$com-google-android-accessibility-talkback-preference-base-DeveloperPrefFragment, reason: not valid java name */
    public /* synthetic */ void m135x5b28f167(TwoStatePreference twoStatePreference, DialogInterface dialogInterface, int i) {
        SpannableUtils$IdentifierSpan.storeBooleanAsync(this.prefs, getString(R.string.pref_performance_stats_key), true);
        twoStatePreference.setChecked(true);
    }

    /* renamed from: lambda$onCreatePreferences$2$com-google-android-accessibility-talkback-preference-base-DeveloperPrefFragment, reason: not valid java name */
    public /* synthetic */ void m136x3ba24768(ListPreference listPreference, DialogInterface dialogInterface, int i) {
        SpannableUtils$IdentifierSpan.putStringPref(this.prefs, getResources(), R.string.pref_log_level_key, Integer.toString(this.logOptInLevel));
        listPreference.setValue(Integer.toString(this.logOptInLevel));
        LogUtils.minLogLevel = this.logOptInLevel;
    }

    /* renamed from: lambda$onCreatePreferences$3$com-google-android-accessibility-talkback-preference-base-DeveloperPrefFragment, reason: not valid java name */
    public /* synthetic */ void m137x1c1b9d69(TwoStatePreference twoStatePreference, DialogInterface dialogInterface, int i) {
        SpannableUtils$IdentifierSpan.storeBooleanAsync(this.prefs, getString(R.string.pref_diagnosis_mode_key), true);
        twoStatePreference.setChecked(true);
        updateDisplayForDiagnosisModeOn();
    }

    /* renamed from: lambda$onCreatePreferences$4$com-google-android-accessibility-talkback-preference-base-DeveloperPrefFragment, reason: not valid java name */
    public /* synthetic */ boolean m138xfc94f36a(A11yAlertDialogWrapper a11yAlertDialogWrapper, Preference preference, Object obj) {
        if (Boolean.TRUE.equals(obj)) {
            a11yAlertDialogWrapper.show();
            return false;
        }
        updateDisplayForDiagnosisModeOff();
        return true;
    }

    /* renamed from: lambda$onCreatePreferences$5$com-google-android-accessibility-talkback-preference-base-DeveloperPrefFragment, reason: not valid java name */
    public /* synthetic */ boolean m139xdd0e496b(Preference preference, Object obj) {
        if (Boolean.TRUE.equals(obj)) {
            Toast.makeText(getContext(), R.string.toast_pref_talkback_gesture_detection, 1).show();
        }
        return true;
    }

    /* renamed from: lambda$onCreatePreferences$6$com-google-android-accessibility-talkback-preference-base-DeveloperPrefFragment, reason: not valid java name */
    public /* synthetic */ boolean m140xbd879f6c(Preference preference, Object obj) {
        this.prefs.edit().remove(this.context.getResources().getString(R.string.pref_gesture_set_key)).apply();
        return true;
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging2;
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging3;
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging4;
        String str2;
        super.onCreatePreferences(bundle, str);
        this.context = getContext();
        this.fragmentManager = getActivity().getSupportFragmentManager();
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(this.context);
        initVersionInfo();
        checkTelevision();
        initTouchExplorationPreference();
        Preference findPreference = findPreference(getString(R.string.pref_developer_version_code_key));
        if (!shouldShowVersionInSubtitle() && findPreference != null && (str2 = this.versionInfo) != null) {
            findPreference.setSummary(str2);
        } else {
            getPreferenceScreen().removePreference$ar$ds(findPreference);
        }
        final TwoStatePreference twoStatePreference = (TwoStatePreference) findPreference(getString(R.string.pref_tree_debug_reflect_key));
        if (twoStatePreference != null) {
            twoStatePreference.setOnPreferenceChangeListener(this.treeDebugChangeListener);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging4 = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(this.context, 1);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging4.setNegativeButton$ar$class_merging$ar$ds(android.R.string.cancel, null);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging4.setOnCancelListener$ar$class_merging$ar$ds();
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging4.setTitle$ar$class_merging$ar$ds(R.string.dialog_title_enable_tree_debug);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging4.setMessage$ar$class_merging$ar$ds(R.string.dialog_message_enable_tree_debug);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging4.setPositiveButton$ar$class_merging$ar$ds(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DeveloperPrefFragment.this.m134x7aaf9b66(twoStatePreference, dialogInterface, i);
                }
            });
            this.treeDebugDialog = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging4.create();
        }
        final TwoStatePreference twoStatePreference2 = (TwoStatePreference) findPreference(getString(R.string.pref_performance_stats_reflect_key));
        if (twoStatePreference2 != null) {
            twoStatePreference2.setOnPreferenceChangeListener(this.performanceStatsChangeListener);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging3 = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(this.context, 1);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging3.setNegativeButton$ar$class_merging$ar$ds(android.R.string.cancel, null);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging3.setOnCancelListener$ar$class_merging$ar$ds();
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging3.setTitle$ar$class_merging$ar$ds(R.string.dialog_title_enable_performance_stats);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging3.setMessage$ar$class_merging$ar$ds(R.string.dialog_message_enable_performance_stats);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging3.setPositiveButton$ar$class_merging$ar$ds(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment$$ExternalSyntheticLambda2
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DeveloperPrefFragment.this.m135x5b28f167(twoStatePreference2, dialogInterface, i);
                }
            });
            this.performanceStatsDialog = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging3.create();
        }
        final ListPreference listPreference = (ListPreference) findPreference(getString(R.string.pref_log_level_key));
        if (listPreference != null) {
            listPreference.setOnPreferenceChangeListener(this.logLevelChangeListener);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging2 = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(this.context, 1);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging2.setNegativeButton$ar$class_merging$ar$ds(android.R.string.cancel, null);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging2.setOnCancelListener$ar$class_merging$ar$ds();
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging2.setTitle$ar$class_merging$ar$ds(R.string.dialog_title_extend_log_level);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging2.setMessage$ar$class_merging$ar$ds(R.string.dialog_message_extend_log_level);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging2.setPositiveButton$ar$class_merging$ar$ds(R.string.dialog_ok_buggon_extend_log_level, new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment$$ExternalSyntheticLambda3
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DeveloperPrefFragment.this.m136x3ba24768(listPreference, dialogInterface, i);
                }
            });
            this.logLevelOptInDialog = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging2.create();
        }
        final TwoStatePreference twoStatePreference3 = (TwoStatePreference) findPreference(getString(R.string.pref_diagnosis_mode_key));
        if (twoStatePreference3 != null) {
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(this.context, 1);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(android.R.string.cancel, null);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setOnCancelListener$ar$class_merging$ar$ds();
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$ar$ds(R.string.dialog_title_diagnosis_mode);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setMessage$ar$class_merging$ar$ds(R.string.dialog_message_diagnosis_mode);
            createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment$$ExternalSyntheticLambda4
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    DeveloperPrefFragment.this.m137x1c1b9d69(twoStatePreference3, dialogInterface, i);
                }
            });
            final A11yAlertDialogWrapper create = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create();
            twoStatePreference3.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment$$ExternalSyntheticLambda5
                @Override // androidx.preference.Preference.OnPreferenceChangeListener
                public final boolean onPreferenceChange(Preference preference, Object obj) {
                    return DeveloperPrefFragment.this.m138xfc94f36a(create, preference, obj);
                }
            });
        }
        TwoStatePreference twoStatePreference4 = (TwoStatePreference) findPreference(getString(R.string.pref_talkback_gesture_detection_key));
        if (twoStatePreference4 != null) {
            if (SpannableUtils$IdentifierSpan.isAtLeastT()) {
                twoStatePreference4.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment$$ExternalSyntheticLambda6
                    @Override // androidx.preference.Preference.OnPreferenceChangeListener
                    public final boolean onPreferenceChange(Preference preference, Object obj) {
                        return DeveloperPrefFragment.this.m139xdd0e496b(preference, obj);
                    }
                });
            } else {
                getPreferenceScreen().removePreference$ar$ds(twoStatePreference4);
            }
        }
        TwoStatePreference twoStatePreference5 = (TwoStatePreference) findPreference(getString(R.string.pref_multiple_gesture_set_key));
        if (twoStatePreference5 != null) {
            if (SpannableUtils$IdentifierSpan.isAtLeastT() && GestureSetConfig.activateMultipleGestureSet(this.context)) {
                twoStatePreference5.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.DeveloperPrefFragment$$ExternalSyntheticLambda7
                    @Override // androidx.preference.Preference.OnPreferenceChangeListener
                    public final boolean onPreferenceChange(Preference preference, Object obj) {
                        return DeveloperPrefFragment.this.m140xbd879f6c(preference, obj);
                    }
                });
            } else {
                getPreferenceScreen().removePreference$ar$ds(twoStatePreference5);
            }
        }
        updateDisplayForDiagnosisMode();
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        if (this.contentObserverRegistered) {
            this.context.getContentResolver().unregisterContentObserver(this.touchExploreObserver);
        }
        super.onPause();
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        updateDumpA11yEventPreferenceSummary();
        TalkBackService talkBackService = TalkBackService.instance;
        if (talkBackService != null && talkBackService.supportsTouchScreen) {
            this.context.getContentResolver().registerContentObserver(Settings.Secure.getUriFor("touch_exploration_enabled"), false, this.touchExploreObserver);
            this.contentObserverRegistered = true;
        }
    }
}
