package com.google.android.accessibility.selecttospeak.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class S2SDeveloperSettingsPreferenceActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class S2SDeveloperSettingsPreferenceFragment extends PreferenceFragmentCompat {
        private SharedPreferences prefs;

        public static /* synthetic */ boolean $r8$lambda$8sUfpu1M_YoPWKO73OthASijNmc(S2SDeveloperSettingsPreferenceFragment s2SDeveloperSettingsPreferenceFragment, Preference preference, Object obj) {
            onCreatePreferences$lambda$1(s2SDeveloperSettingsPreferenceFragment, preference, obj);
            return true;
        }

        /* renamed from: $r8$lambda$WV84ZfDaumbgMlH_fH482NY_-X0 */
        public static /* synthetic */ boolean m86$r8$lambda$WV84ZfDaumbgMlH_fH482NY_X0(S2SDeveloperSettingsPreferenceFragment s2SDeveloperSettingsPreferenceFragment, Preference preference, Object obj) {
            onCreatePreferences$lambda$0(s2SDeveloperSettingsPreferenceFragment, preference, obj);
            return true;
        }

        private static final boolean onCreatePreferences$lambda$0(S2SDeveloperSettingsPreferenceFragment s2SDeveloperSettingsPreferenceFragment, Preference preference, Object obj) {
            s2SDeveloperSettingsPreferenceFragment.getClass();
            preference.getClass();
            SharedPreferences sharedPreferences = s2SDeveloperSettingsPreferenceFragment.prefs;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("prefs");
                sharedPreferences = null;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            String key = preference.getKey();
            obj.getClass();
            edit.putBoolean(key, ((Boolean) obj).booleanValue()).apply();
            return true;
        }

        private static final boolean onCreatePreferences$lambda$1(S2SDeveloperSettingsPreferenceFragment s2SDeveloperSettingsPreferenceFragment, Preference preference, Object obj) {
            s2SDeveloperSettingsPreferenceFragment.getClass();
            preference.getClass();
            SharedPreferences sharedPreferences = s2SDeveloperSettingsPreferenceFragment.prefs;
            if (sharedPreferences == null) {
                Intrinsics.throwUninitializedPropertyAccessException("prefs");
                sharedPreferences = null;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            String key = preference.getKey();
            obj.getClass();
            edit.putBoolean(key, ((Boolean) obj).booleanValue()).apply();
            return true;
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            setPreferencesFromResource(R.xml.selecttospeak_developer_settings_preferences, str);
            SharedPreferences sharedPreferences = SpannableUtils$IdentifierSpan.getSharedPreferences(requireContext());
            sharedPreferences.getClass();
            this.prefs = sharedPreferences;
            SwitchPreference switchPreference = (SwitchPreference) findPreference(getString(R.string.s2s_pref_enable_snapshot_key));
            if (switchPreference != null) {
                switchPreference.setOnPreferenceChangeListener(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(this, 9));
            }
            SwitchPreference switchPreference2 = (SwitchPreference) findPreference(getString(R.string.s2s_pref_enable_snapshot_detail_info_key));
            if (switchPreference2 != null) {
                switchPreference2.setOnPreferenceChangeListener(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(this, 10));
            }
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new S2SDeveloperSettingsPreferenceFragment();
    }
}
