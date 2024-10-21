package com.google.android.accessibility.braille.brailledisplay.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import com.android.settingslib.widget.FooterPreference;
import com.android.settingslib.widget.RadioButtonPreference;
import com.google.android.accessibility.braille.brailledisplay.OverlayDisplay;
import com.google.android.accessibility.braille.brailledisplay.settings.BrailleGradeActivity;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleGradeActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class BrailleGradeFragment extends PreferenceFragmentCompat {
        private RadioButtonPreference contractedPref;
        private final SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new OverlayDisplay.AnonymousClass1(this, 8);
        private FooterPreference tipsPref;
        private RadioButtonPreference uncontractedPref;

        public void onModelChanged() {
            boolean readContractedMode = BrailleUserPreferences.readContractedMode(getContext());
            this.contractedPref.setChecked(readContractedMode);
            this.uncontractedPref.setChecked(!readContractedMode);
        }

        /* renamed from: lambda$onCreatePreferences$0$com-google-android-accessibility-braille-brailledisplay-settings-BrailleGradeActivity$BrailleGradeFragment */
        public /* synthetic */ void m63x3652bde6(RadioButtonPreference radioButtonPreference) {
            BrailleUserPreferences.writeContractedMode(getContext(), !radioButtonPreference.isChecked());
        }

        /* renamed from: lambda$onCreatePreferences$1$com-google-android-accessibility-braille-brailledisplay-settings-BrailleGradeActivity$BrailleGradeFragment */
        public /* synthetic */ void m64xfb84af45(RadioButtonPreference radioButtonPreference) {
            BrailleUserPreferences.writeContractedMode(getContext(), radioButtonPreference.isChecked());
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            BrailleUserPreferences.getSharedPreferences$ar$ds(getContext()).registerOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.braille_grade);
            RadioButtonPreference radioButtonPreference = (RadioButtonPreference) findPreference(getString(R.string.pref_key_braille_grade_contracted));
            this.contractedPref = radioButtonPreference;
            final int i = 1;
            radioButtonPreference.mListener = new RadioButtonPreference.OnClickListener(this) { // from class: com.google.android.accessibility.braille.brailledisplay.settings.BrailleGradeActivity$BrailleGradeFragment$$ExternalSyntheticLambda1
                public final /* synthetic */ BrailleGradeActivity.BrailleGradeFragment f$0;

                {
                    this.f$0 = this;
                }

                @Override // com.android.settingslib.widget.RadioButtonPreference.OnClickListener
                public final void onRadioButtonClicked(RadioButtonPreference radioButtonPreference2) {
                    if (i != 0) {
                        this.f$0.m63x3652bde6(radioButtonPreference2);
                    } else {
                        this.f$0.m64xfb84af45(radioButtonPreference2);
                    }
                }
            };
            RadioButtonPreference radioButtonPreference2 = (RadioButtonPreference) findPreference(getString(R.string.pref_key_braille_grade_uncontracted));
            this.uncontractedPref = radioButtonPreference2;
            final int i2 = 0;
            radioButtonPreference2.mListener = new RadioButtonPreference.OnClickListener(this) { // from class: com.google.android.accessibility.braille.brailledisplay.settings.BrailleGradeActivity$BrailleGradeFragment$$ExternalSyntheticLambda1
                public final /* synthetic */ BrailleGradeActivity.BrailleGradeFragment f$0;

                {
                    this.f$0 = this;
                }

                @Override // com.android.settingslib.widget.RadioButtonPreference.OnClickListener
                public final void onRadioButtonClicked(RadioButtonPreference radioButtonPreference22) {
                    if (i2 != 0) {
                        this.f$0.m63x3652bde6(radioButtonPreference22);
                    } else {
                        this.f$0.m64xfb84af45(radioButtonPreference22);
                    }
                }
            };
            FooterPreference footerPreference = (FooterPreference) findPreference(getString(R.string.pref_key_braille_grade_tips));
            this.tipsPref = footerPreference;
            footerPreference.setSummary(getString(R.string.bd_braille_grade_tips));
            onModelChanged();
        }

        @Override // android.support.v4.app.Fragment
        public void onDestroy() {
            super.onDestroy();
            BrailleUserPreferences.getSharedPreferences$ar$ds(getContext()).unregisterOnSharedPreferenceChangeListener(this.onSharedPreferenceChangeListener);
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new BrailleGradeFragment();
    }
}
