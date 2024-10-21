package com.google.android.accessibility.braille.common.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegateImpl;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.accessibility.braille.brailledisplay.platform.connect.bt.BtConnectManager$$ExternalSyntheticLambda1;
import com.google.android.accessibility.braille.common.settings.AddLanguageActivity;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.brailleime.input.BrailleInputPlanePhone$$ExternalSyntheticLambda1;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;
import j$.util.Collection;
import j$.util.List;
import j$.util.stream.Collectors;
import java.text.Collator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AddLanguageActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class AddLanguageFragment extends PreferenceFragmentCompat {
        private PreferenceCategory localeCategoryPreference;

        private Preference createAvailableLocalePreference(final Locale locale) {
            Preference preference = new Preference(getContext());
            preference.setTitle(AppCompatDelegateImpl.Api21Impl.toCharacterTitleCase(locale.getDisplayLanguage()));
            preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.braille.common.settings.AddLanguageActivity$AddLanguageFragment$$ExternalSyntheticLambda2
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference2) {
                    return AddLanguageActivity.AddLanguageFragment.this.m66x4c39bf51(locale, preference2);
                }
            });
            return preference;
        }

        private void finishContainerActivity() {
            if (getActivity() != null) {
                getActivity().finish();
            }
        }

        private void initPreferences() {
            List list = (List) Collection.EL.stream(BrailleLanguages.getAvailableCodes(getContext())).map(new BtConnectManager$$ExternalSyntheticLambda1(10)).distinct().collect(Collectors.toList());
            Locale locale = Locale.getDefault();
            List.EL.sort(list, new BrailleInputPlanePhone$$ExternalSyntheticLambda1(Collator.getInstance(locale), 1));
            int i = 0;
            while (true) {
                if (i < list.size()) {
                    if (((Locale) list.get(i)).getLanguage().equals(locale.getLanguage())) {
                        break;
                    } else {
                        i++;
                    }
                } else {
                    i = -1;
                    break;
                }
            }
            if (i != -1) {
                list.add(0, (Locale) list.remove(i));
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                this.localeCategoryPreference.addPreference$ar$ds(createAvailableLocalePreference((Locale) it.next()));
            }
            if (this.localeCategoryPreference.getPreferenceCount() == 0) {
                this.localeCategoryPreference.setVisible(false);
            }
        }

        /* renamed from: lambda$createAvailableLocalePreference$2$com-google-android-accessibility-braille-common-settings-AddLanguageActivity$AddLanguageFragment */
        public /* synthetic */ boolean m66x4c39bf51(Locale locale, Preference preference) {
            Intent intent = new Intent(getContext(), (Class<?>) LocaleLanguageActivity.class);
            intent.putExtra("locale", locale.getLanguage());
            getContext().startActivity(intent);
            finishContainerActivity();
            return true;
        }

        @Override // androidx.preference.PreferenceFragmentCompat, android.support.v4.app.Fragment
        public void onCreate(Bundle bundle) {
            super.onCreate(bundle);
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.braille_add_language_preferences);
            this.localeCategoryPreference = (PreferenceCategory) findPreference(getString(R.string.pref_braille_languages_category_key));
            initPreferences();
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new AddLanguageFragment();
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final String getFragmentTag() {
        return "BrailleLanguageActivity";
    }
}
