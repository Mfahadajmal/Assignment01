package com.google.android.accessibility.braille.brailledisplay.settings;

import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.text.ParcelableSpan;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.TtsSpan;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceScreen;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleElementsActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class BrailleElementsFragment extends PreferenceFragmentCompat {
        private PreferenceScreen preferenceScreen;

        private SpannableString getTextWithColorSpan(CharSequence charSequence, int i) {
            return getTextWithSpan(charSequence, new ForegroundColorSpan(getContext().getResources().getColor(i, null)));
        }

        private SpannableString getTextWithSpan(CharSequence charSequence, ParcelableSpan parcelableSpan) {
            SpannableString spannableString = new SpannableString(charSequence);
            spannableString.setSpan(parcelableSpan, 0, charSequence.length(), 33);
            return spannableString;
        }

        private SpannableString getTextWithTtsSpan(CharSequence charSequence, String str) {
            return getTextWithSpan(charSequence, new TtsSpan.TextBuilder(str).build());
        }

        private void setColorSpanTitle() {
            for (int i = 0; i < this.preferenceScreen.getPreferenceCount(); i++) {
                Preference preference = this.preferenceScreen.getPreference(i);
                CharSequence title = preference.getTitle();
                if (!TextUtils.isEmpty(title)) {
                    preference.setTitle(getTextWithColorSpan(title, R.color.settings_primary_text));
                    preference.setSelectable(false);
                }
            }
        }

        private void setHeadingWithLevelTitle() {
            NumberFormat numberInstance;
            String format;
            String format2;
            numberInstance = NumberFormat.getNumberInstance(Locale.getDefault());
            Preference findPreference = this.preferenceScreen.findPreference(getString(R.string.pref_key_bd_braille_elements_h1_to_h6));
            format = numberInstance.format(1L);
            format2 = numberInstance.format(6L);
            findPreference.setTitle(getString(R.string.bd_braille_elements_h1_to_h6, format, format2));
        }

        private void setTtsSpanTitle() {
            Preference findPreference = this.preferenceScreen.findPreference(getString(R.string.pref_key_bd_braille_elements_checked));
            Preference findPreference2 = this.preferenceScreen.findPreference(getString(R.string.pref_key_bd_braille_elements_expanded));
            findPreference.setTitle(getTextWithTtsSpan(getString(R.string.bd_braille_elements_checked), getString(R.string.bd_braille_elements_checked_content_description)));
            findPreference2.setTitle(getTextWithTtsSpan(getString(R.string.bd_braille_elements_expanded), getString(R.string.bd_braille_elements_expanded_content_description)));
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.bd_braille_elements);
            this.preferenceScreen = (PreferenceScreen) findPreference(getString(R.string.pref_key_braille_elements));
            setHeadingWithLevelTitle();
            setTtsSpanTitle();
            setColorSpanTitle();
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new BrailleElementsFragment();
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final String getFragmentTag() {
        return "BrailleElementsActivity";
    }
}
