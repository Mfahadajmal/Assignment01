package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.ListPreference;
import androidx.preference.ListPreferenceDialogFragmentCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceGroup;
import androidx.preference.SwitchPreference;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.talkback.preference.base.PunctuationListPreference;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.android.marvin.talkback.R;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;

/* compiled from: PG */
/* loaded from: classes.dex */
public class VerbosityPrefFragment extends TalkbackBaseFragment {
    private static final String TAG = "VerbosityPrefFragment";
    private ImmutableMap<String, Integer> listPreferenceKeyValueMap;
    private final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener;
    private SharedPreferences preferences;
    private ImmutableMap<String, Boolean> switchPreferenceKeyValueMap;
    private String verbosityValue;

    public VerbosityPrefFragment() {
        super(R.xml.verbosity_preferences);
        this.onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.google.android.accessibility.talkback.preference.base.VerbosityPrefFragment.1
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str) {
                if (VerbosityPrefFragment.this.isAdded() && VerbosityPrefFragment.this.getActivity() != null) {
                    if (TextUtils.equals(str, VerbosityPrefFragment.this.getString(R.string.pref_verbosity_preset_key))) {
                        ListPreference listPreference = (ListPreference) VerbosityPrefFragment.this.findPreference(R.string.pref_verbosity_preset_key);
                        VerbosityPrefFragment verbosityPrefFragment = VerbosityPrefFragment.this;
                        String string = verbosityPrefFragment.preferences.getString(verbosityPrefFragment.getString(R.string.pref_verbosity_preset_key), VerbosityPrefFragment.this.getString(R.string.pref_verbosity_preset_value_default));
                        if (listPreference != null) {
                            listPreference.setValue(string);
                        }
                        VerbosityPrefFragment.this.updateFragment(string);
                        VerbosityPrefFragment.this.announceVerbosityChange(string);
                        return;
                    }
                    if (TextUtils.equals(str, VerbosityPrefFragment.this.getString(R.string.pref_punctuation_key))) {
                        SwitchPreference switchPreference = (SwitchPreference) VerbosityPrefFragment.this.findPreference(R.string.pref_punctuation_key);
                        boolean z = sharedPreferences.getBoolean(VerbosityPrefFragment.this.getString(R.string.pref_punctuation_key), VerbosityPrefFragment.this.getResources().getBoolean(R.bool.pref_punctuation_default));
                        if (switchPreference != null) {
                            switchPreference.setChecked(z);
                            return;
                        }
                        return;
                    }
                    if (TextUtils.equals(str, VerbosityPrefFragment.this.getString(R.string.pref_punctuation_verbosity))) {
                        ListPreference listPreference2 = (ListPreference) VerbosityPrefFragment.this.findPreference(R.string.pref_punctuation_verbosity);
                        VerbosityPrefFragment verbosityPrefFragment2 = VerbosityPrefFragment.this;
                        String string2 = verbosityPrefFragment2.preferences.getString(verbosityPrefFragment2.getString(R.string.pref_punctuation_verbosity), VerbosityPrefFragment.this.getString(R.string.pref_punctuation_verbosity_default));
                        if (listPreference2 != null) {
                            listPreference2.setValue(string2);
                            return;
                        }
                        return;
                    }
                    return;
                }
                LogUtils.w(VerbosityPrefFragment.TAG, "Fragment is not attached to activity, do not update verbosity setting page.", new Object[0]);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void announceVerbosityChange(String str) {
        Context context = getContext();
        String verbosityChangeAnnouncement = getVerbosityChangeAnnouncement(str, context);
        if (verbosityChangeAnnouncement == null) {
            return;
        }
        PreferencesActivityUtils.announceText(verbosityChangeAnnouncement, context);
    }

    private void attachPreferenceListeners() {
        this.preferences.registerOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
    }

    private void buildMap() {
        ImmutableMap.Builder builder = new ImmutableMap.Builder();
        builder.put$ar$ds$de9b9d28_0(getString(R.string.pref_screenoff_key), Boolean.valueOf(getResources().getBoolean(R.bool.pref_screenoff_default)));
        builder.put$ar$ds$de9b9d28_0(getString(R.string.pref_a11y_hints_key), Boolean.valueOf(getResources().getBoolean(R.bool.pref_a11y_hints_default)));
        builder.put$ar$ds$de9b9d28_0(getString(R.string.pref_intonation_key), Boolean.valueOf(getResources().getBoolean(R.bool.pref_intonation_default)));
        builder.put$ar$ds$de9b9d28_0(getString(R.string.pref_phonetic_letters_key), Boolean.valueOf(getResources().getBoolean(R.bool.pref_phonetic_letters_default)));
        builder.put$ar$ds$de9b9d28_0(getString(R.string.pref_speak_roles_key), Boolean.valueOf(getResources().getBoolean(R.bool.pref_speak_roles_default)));
        builder.put$ar$ds$de9b9d28_0(getString(R.string.pref_speak_container_element_positions_key), Boolean.valueOf(getResources().getBoolean(R.bool.pref_speak_container_element_positions_default)));
        builder.put$ar$ds$de9b9d28_0(getString(R.string.pref_verbose_scroll_announcement_key), Boolean.valueOf(getResources().getBoolean(R.bool.pref_verbose_scroll_announcement_default)));
        builder.put$ar$ds$de9b9d28_0(getString(R.string.pref_speak_system_window_titles_key), Boolean.valueOf(getResources().getBoolean(R.bool.pref_speak_system_window_titles_default)));
        builder.put$ar$ds$de9b9d28_0(getString(R.string.pref_allow_frequent_content_change_announcement_key), Boolean.valueOf(getResources().getBoolean(R.bool.pref_allow_frequent_content_change_announcement_default)));
        builder.put$ar$ds$de9b9d28_0(getString(R.string.pref_speak_element_ids_key), Boolean.valueOf(getResources().getBoolean(R.bool.pref_speak_element_ids_default)));
        builder.put$ar$ds$de9b9d28_0(getString(R.string.pref_punctuation_key), Boolean.valueOf(getResources().getBoolean(R.bool.pref_punctuation_default)));
        this.switchPreferenceKeyValueMap = builder.buildOrThrow();
        ImmutableMap.Builder builder2 = new ImmutableMap.Builder();
        String string = getString(R.string.pref_keyboard_echo_on_screen_key);
        Integer valueOf = Integer.valueOf(R.string.pref_keyboard_echo_default);
        builder2.put$ar$ds$de9b9d28_0(string, valueOf);
        builder2.put$ar$ds$de9b9d28_0(getString(R.string.pref_keyboard_echo_physical_key), valueOf);
        builder2.put$ar$ds$de9b9d28_0(getString(R.string.pref_capital_letters_key), Integer.valueOf(R.string.pref_capital_letters_default));
        builder2.put$ar$ds$de9b9d28_0(getString(R.string.pref_punctuation_verbosity), Integer.valueOf(R.string.pref_punctuation_verbosity_default));
        this.listPreferenceKeyValueMap = builder2.buildOrThrow();
    }

    private ArrayList<Preference> collectDetailedPreferences() {
        ArrayList<Preference> arrayList = new ArrayList<>();
        PreferenceGroup preferenceGroup = (PreferenceGroup) findPreference(R.string.pref_verbosity_category_preset_settings_key);
        if (preferenceGroup != null) {
            for (int i = 0; i < preferenceGroup.getPreferenceCount(); i++) {
                Preference preference = preferenceGroup.getPreference(i);
                if (preference != null) {
                    arrayList.add(preference);
                }
            }
        }
        return arrayList;
    }

    private void copyVerbosityToUi(ArrayList<Preference> arrayList) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Preference preference = arrayList.get(i);
            String key = preference.getKey();
            Resources resources = getResources();
            if (key == null) {
                key = null;
            } else if (key.contains(resources.getString(R.string.pref_verbosity_preset_value_custom))) {
                key = TextUtils.substring(key, resources.getString(R.string.pref_verbosity_preset_value_custom).length() + 1, key.length());
            } else if (key.contains(resources.getString(R.string.pref_verbosity_preset_value_high))) {
                key = TextUtils.substring(key, resources.getString(R.string.pref_verbosity_preset_value_high).length() + 1, key.length());
            } else if (key.contains(resources.getString(R.string.pref_verbosity_preset_value_low))) {
                key = TextUtils.substring(key, resources.getString(R.string.pref_verbosity_preset_value_low).length() + 1, key.length());
            }
            preference.setKey(SpannableUtils$IdentifierSpan.toVerbosityPrefKey(this.verbosityValue, key));
            if (preference instanceof SwitchPreference) {
                ((SwitchPreference) preference).setChecked(SpannableUtils$IdentifierSpan.getPreferenceVerbosityBool(this.preferences, getResources(), this.verbosityValue, key, getDefaultValueForSwitchPreferences(key)));
            } else if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                String preferenceVerbosityString = SpannableUtils$IdentifierSpan.getPreferenceVerbosityString(this.preferences, getResources(), this.verbosityValue, key, getDefaultValueForListPreferences(key));
                if (preferenceVerbosityString != null) {
                    listPreference.setValue(preferenceVerbosityString);
                }
            } else {
                LogUtils.e(TAG, "Unhandled preference type %s", preference.getClass().getSimpleName());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Preference findPreference(int i) {
        return getPreferenceScreen().findPreference(getString(i));
    }

    private String getDefaultValueForListPreferences(String str) {
        Integer num;
        if (str != null) {
            num = (Integer) this.listPreferenceKeyValueMap.get(str);
        } else {
            num = null;
        }
        if (num == null) {
            return null;
        }
        return getString(num.intValue());
    }

    private boolean getDefaultValueForSwitchPreferences(String str) {
        Boolean bool = true;
        if (str != null) {
            bool = (Boolean) this.switchPreferenceKeyValueMap.get(str);
        }
        if (bool == null || bool.booleanValue()) {
            return true;
        }
        return false;
    }

    public static String getVerbosityChangeAnnouncement(String str, Context context) {
        if (TextUtils.isEmpty(verbosityValueToName(str, context))) {
            return null;
        }
        return String.format(context.getString(R.string.pref_verbosity_preset_change), verbosityValueToName(str, context));
    }

    private boolean isVerbosityValueHighOrLow() {
        if (!TextUtils.equals(this.verbosityValue, getString(R.string.pref_verbosity_preset_value_high)) && !TextUtils.equals(this.verbosityValue, getString(R.string.pref_verbosity_preset_value_low))) {
            return false;
        }
        return true;
    }

    private void setPreferenceDetailsEnable(ArrayList<Preference> arrayList, boolean z) {
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            arrayList.get(i).setEnabled(z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateFragment(String str) {
        if (TextUtils.equals(this.verbosityValue, str)) {
            return;
        }
        updatePreferences();
    }

    private void updatePreferences() {
        SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(getContext());
        this.preferences = sharedPreferences;
        this.verbosityValue = SpannableUtils$IdentifierSpan.getStringPref(sharedPreferences, getResources(), R.string.pref_verbosity_preset_key, R.string.pref_verbosity_preset_value_default);
        ArrayList<Preference> collectDetailedPreferences = collectDetailedPreferences();
        copyVerbosityToUi(collectDetailedPreferences);
        if (isVerbosityValueHighOrLow()) {
            setPreferenceDetailsEnable(collectDetailedPreferences, false);
        } else {
            setPreferenceDetailsEnable(collectDetailedPreferences, true);
        }
    }

    public static String verbosityValueToName(String str, Context context) {
        if (str.equals(context.getString(R.string.pref_verbosity_preset_value_high))) {
            return context.getString(R.string.pref_verbosity_preset_entry_high);
        }
        if (str.equals(context.getString(R.string.pref_verbosity_preset_value_custom))) {
            return context.getString(R.string.pref_verbosity_preset_entry_custom);
        }
        if (str.equals(context.getString(R.string.pref_verbosity_preset_value_low))) {
            return context.getString(R.string.pref_verbosity_preset_entry_low);
        }
        return null;
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.pref_verbosity_title);
    }

    @Override // android.support.v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        buildMap();
        updatePreferences();
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnDisplayPreferenceDialogListener
    public void onDisplayPreferenceDialog(Preference preference) {
        if (preference instanceof PunctuationListPreference) {
            ListPreferenceDialogFragmentCompat newInstance = PunctuationListPreference.CustomListPreferenceDialogFragment.newInstance(preference.getKey());
            newInstance.setTargetFragment(this, 0);
            newInstance.show(getParentFragmentManager(), "dialog_preference");
            return;
        }
        super.onDisplayPreferenceDialog(preference);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() {
        super.onPause();
        this.preferences.unregisterOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment, android.support.v4.app.Fragment
    public void onResume() {
        super.onResume();
        updateFragment(this.preferences.getString(getString(R.string.pref_verbosity_preset_key), getString(R.string.pref_verbosity_preset_value_default)));
        attachPreferenceListeners();
    }
}
