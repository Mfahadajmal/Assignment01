package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import com.google.android.accessibility.talkback.gesture.GestureShortcutMapping;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.SpannableUtils$NonCopyableTextSpan;
import com.google.android.accessibility.utils.material.A11yAlertDialogWrapper;
import com.google.android.accessibility.utils.preference.AccessibilitySuitePreferenceCategory;
import com.google.android.apps.common.inject.ApplicationModule;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TalkBackGestureShortcutPreferenceFragment extends TalkbackBaseFragment {
    private static final String TAG = "TalkBackGestureShortcutPreferenceFragment";
    private FormFactorUtils formFactorUtils;
    private final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;
    private SharedPreferences prefs;
    private final DialogInterface.OnClickListener resetGestureConfirmDialogPositive;
    private final Preference.OnPreferenceClickListener resetGesturePreferenceClickListener;

    public TalkBackGestureShortcutPreferenceFragment() {
        super(R.xml.gesture_preferences);
        this.resetGestureConfirmDialogPositive = new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackGestureShortcutPreferenceFragment$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                TalkBackGestureShortcutPreferenceFragment.this.m163x15f8973(dialogInterface, i);
            }
        };
        this.resetGesturePreferenceClickListener = new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackGestureShortcutPreferenceFragment$$ExternalSyntheticLambda1
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return TalkBackGestureShortcutPreferenceFragment.this.m164xb3762075(preference);
            }
        };
        this.onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackGestureShortcutPreferenceFragment.1
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                if (TalkBackGestureShortcutPreferenceFragment.this.getActivity() == null) {
                    LogUtils.w(TalkBackGestureShortcutPreferenceFragment.TAG, "Fragment is not attached to activity, do not update verbosity setting page.", new Object[0]);
                    return;
                }
                if (TextUtils.equals(str, TalkBackGestureShortcutPreferenceFragment.this.getString(R.string.pref_gesture_set_key))) {
                    TalkBackGestureShortcutPreferenceFragment talkBackGestureShortcutPreferenceFragment = TalkBackGestureShortcutPreferenceFragment.this;
                    ListPreference listPreference = (ListPreference) talkBackGestureShortcutPreferenceFragment.findPreference(talkBackGestureShortcutPreferenceFragment.getString(R.string.pref_gesture_set_key));
                    String string = sharedPreferences.getString(TalkBackGestureShortcutPreferenceFragment.this.getString(R.string.pref_gesture_set_key), TalkBackGestureShortcutPreferenceFragment.this.getString(R.string.pref_gesture_set_value_default));
                    if (listPreference != null) {
                        listPreference.setValue(string);
                    }
                    TalkBackGestureShortcutPreferenceFragment.this.updatePreferenceKey(Integer.parseInt(string));
                }
            }
        };
    }

    private void resetGestureShortcut() {
        SharedPreferences.Editor edit = this.prefs.edit();
        String[] stringArray = getResources().getStringArray(R.array.pref_shortcut_keys);
        int intFromStringPref = SpannableUtils$IdentifierSpan.getIntFromStringPref(this.prefs, getResources(), R.string.pref_gesture_set_key, R.string.pref_gesture_set_value_default);
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        for (String str : stringArray) {
            String prefKeyWithGestureSet = GestureShortcutMapping.getPrefKeyWithGestureSet(str, intFromStringPref);
            if (this.prefs.contains(prefKeyWithGestureSet)) {
                GestureListPreference gestureListPreference = (GestureListPreference) preferenceScreen.findPreference(prefKeyWithGestureSet);
                if (gestureListPreference != null) {
                    gestureListPreference.updateSummaryToDefaultValue();
                }
                edit.remove(prefKeyWithGestureSet);
            }
        }
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updatePreferenceKey(int i) {
        PreferenceScreen preferenceScreen = getPreferenceScreen();
        int preferenceCount = preferenceScreen.getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            Preference preference = preferenceScreen.getPreference(i2);
            if (preference instanceof AccessibilitySuitePreferenceCategory) {
                updatePreferenceKeyForGesture((AccessibilitySuitePreferenceCategory) preference, i);
            }
        }
    }

    private void updatePreferenceKeyForGesture(AccessibilitySuitePreferenceCategory accessibilitySuitePreferenceCategory, int i) {
        int preferenceCount = accessibilitySuitePreferenceCategory.getPreferenceCount();
        for (int i2 = 0; i2 < preferenceCount; i2++) {
            Preference preference = accessibilitySuitePreferenceCategory.getPreference(i2);
            if (preference instanceof GestureListPreference) {
                GestureListPreference gestureListPreference = (GestureListPreference) preference;
                String prefKeyWithGestureSet = GestureShortcutMapping.getPrefKeyWithGestureSet(preference.getKey(), i);
                gestureListPreference.setKey(prefKeyWithGestureSet);
                gestureListPreference.setSummary(GestureShortcutMapping.getActionString(getContext(), this.prefs.getString(prefKeyWithGestureSet, gestureListPreference.getDefaultValue())));
            }
        }
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.title_pref_category_manage_gestures);
    }

    /* renamed from: lambda$new$0$com-google-android-accessibility-talkback-preference-base-TalkBackGestureShortcutPreferenceFragment, reason: not valid java name */
    public /* synthetic */ void m163x15f8973(DialogInterface dialogInterface, int i) {
        PreferencesActivityUtils.announceText(getString(R.string.gestures_announce_reset_gesture_settings), getContext());
        resetGestureShortcut();
        dialogInterface.dismiss();
    }

    /* renamed from: lambda$new$2$com-google-android-accessibility-talkback-preference-base-TalkBackGestureShortcutPreferenceFragment, reason: not valid java name */
    public /* synthetic */ boolean m164xb3762075(Preference preference) {
        ApplicationModule createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging;
        int i;
        boolean isGestureSetEnabled$ar$ds = GestureShortcutMapping.isGestureSetEnabled$ar$ds(getContext(), this.prefs);
        Context context = getContext();
        getActivity().getSupportFragmentManager();
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging = SpannableUtils$NonCopyableTextSpan.createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging(context, 1);
        if (true != isGestureSetEnabled$ar$ds) {
            i = R.string.Reset_gesture_settings_dialog_title;
        } else {
            i = R.string.Reset_gesture_settings_set_dialog_title;
        }
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setTitle$ar$class_merging$51f49cd0_0$ar$ds(getString(i));
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setMessage$ar$class_merging$51f49cd0_0$ar$ds(getString(R.string.message_reset_gesture_settings_confirm_dialog));
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setPositiveButton$ar$class_merging$ar$ds(R.string.reset_button_in_reset_gesture_settings_confirm_dialog, this.resetGestureConfirmDialogPositive);
        createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.setNegativeButton$ar$class_merging$ar$ds(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.google.android.accessibility.talkback.preference.base.TalkBackGestureShortcutPreferenceFragment$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.cancel();
            }
        });
        A11yAlertDialogWrapper create = createA11yAlertDialogWrapperBuilder$ar$edu$ar$class_merging$ar$class_merging.create();
        create.show();
        if (!this.formFactorUtils.isAndroidWear) {
            A11yAlertDialogWrapper.focusCancelButton(create);
        }
        return true;
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        Context context = getContext();
        this.formFactorUtils = FormFactorUtils.getInstance();
        this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(context);
        Preference findPreference = findPreference(getString(R.string.pref_practice_gestures_entry_point_key));
        if (findPreference != null) {
            findPreference.setIntent(SpannableUtils$IdentifierSpan.createPracticeGesturesIntent(context));
        }
        boolean isGestureSetEnabled$ar$ds = GestureShortcutMapping.isGestureSetEnabled$ar$ds(context, this.prefs);
        Preference findPreference2 = findPreference(getString(R.string.pref_gesture_set_key));
        if (isGestureSetEnabled$ar$ds) {
            if (findPreference2 != null) {
                updatePreferenceKey(SpannableUtils$IdentifierSpan.getIntFromStringPref(this.prefs, getResources(), R.string.pref_gesture_set_key, R.string.pref_gesture_set_value_default));
            }
        } else if (findPreference2 != null) {
            getPreferenceScreen().removePreference$ar$ds(findPreference2);
        }
        findPreference(getString(R.string.pref_reset_gesture_settings_key)).setOnPreferenceClickListener(this.resetGesturePreferenceClickListener);
        if (FeatureSupport.isMultiFingerGestureSupported()) {
            String[] stringArray = context.getResources().getStringArray(R.array.pref_2finger_pass_through_shortcut_keys);
            String[] stringArray2 = context.getResources().getStringArray(R.array.pref_2finger_pass_through_shortcut_summary);
            if (stringArray.length == stringArray2.length) {
                for (int i = 0; i < stringArray.length; i++) {
                    GestureListPreference gestureListPreference = (GestureListPreference) findPreference(stringArray[i]);
                    if (gestureListPreference != null) {
                        if (this.formFactorUtils.isAndroidWear) {
                            gestureListPreference.setVisible(false);
                        } else {
                            gestureListPreference.setEnabled(false);
                            gestureListPreference.setSummaryWhenDisabled(context.getResources().getString(R.string.shortcut_not_customizable, stringArray2[i]));
                        }
                    }
                }
            }
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public void onDisplayPreferenceDialog(Preference preference) {
        if (preference instanceof GestureListPreference) {
            GesturePreferenceFragmentCompat createDialogFragment = ((GestureListPreference) preference).createDialogFragment();
            createDialogFragment.setTargetFragment(this, 0);
            createDialogFragment.show(getParentFragmentManager(), preference.getKey());
            return;
        }
        super.onDisplayPreferenceDialog(preference);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        this.prefs.unregisterOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        this.prefs.registerOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
    }
}
