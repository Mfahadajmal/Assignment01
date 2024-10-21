package com.google.android.accessibility.braille.brailledisplay.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;
import com.google.android.material.slider.Slider;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AutoScrollActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AutoScrollFragment extends PreferenceFragmentCompat {
        private AutoScrollDurationPreference autoScrollDurationPref;
        private final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 6, null);

        private void refresh() {
            AutoScrollDurationPreference autoScrollDurationPreference = this.autoScrollDurationPref;
            int readAutoScrollDuration = BrailleUserPreferences.readAutoScrollDuration(getContext());
            Slider slider = autoScrollDurationPreference.slider;
            if (slider != null) {
                slider.setValue(readAutoScrollDuration / 1000.0f);
            }
        }

        /* renamed from: lambda$new$1$com-google-android-accessibility-braille-brailledisplay-settings-AutoScrollActivity$AutoScrollFragment, reason: not valid java name */
        public /* synthetic */ void m42x25ab6cc2(SharedPreferences sharedPreferences, String str) {
            if (str.equals(getString(R.string.pref_bd_auto_scroll_duration_key))) {
                refresh();
            }
        }

        /* renamed from: lambda$onCreatePreferences$0$com-google-android-accessibility-braille-brailledisplay-settings-AutoScrollActivity$AutoScrollFragment, reason: not valid java name */
        public /* synthetic */ boolean m43x1f0b43e6(Preference preference, Object obj) {
            Context context = getContext();
            BrailleUserPreferences.getSharedPreferences$ar$ds(context).edit().putBoolean(context.getString(R.string.pref_bd_auto_adjust_duration_enable_key), ((Boolean) obj).booleanValue()).apply();
            return true;
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.bd_auto_scroll);
            this.autoScrollDurationPref = (AutoScrollDurationPreference) findPreference(getString(R.string.pref_key_bd_auto_scroll_duration));
            SwitchPreference switchPreference = (SwitchPreference) findPreference(getString(R.string.pref_key_bd_auto_adjust_duration));
            switchPreference.setChecked(BrailleUserPreferences.readAutoAdjustDurationEnable(getContext()));
            switchPreference.setOnPreferenceChangeListener(new BrailleDisplaySettingsFragment$$ExternalSyntheticLambda12(this, 1));
        }

        @Override // android.support.v4.app.Fragment
        public void onPause() {
            super.onPause();
            BrailleUserPreferences.getSharedPreferences$ar$ds(getContext()).unregisterOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
        }

        @Override // android.support.v4.app.Fragment
        public void onResume() {
            super.onResume();
            refresh();
            BrailleUserPreferences.getSharedPreferences$ar$ds(getContext()).registerOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new AutoScrollFragment();
    }
}
