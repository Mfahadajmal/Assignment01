package com.google.android.accessibility.braille.common.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegateImpl;
import androidx.preference.CheckBoxPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.accessibility.braille.brailledisplay.controller.utils.BrailleKeyBindingUtils$$ExternalSyntheticLambda2;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.settings.LocaleLanguageActivity;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;
import j$.util.Collection;
import j$.util.List;
import j$.util.stream.Collectors;
import java.text.Collator;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* compiled from: PG */
/* loaded from: classes.dex */
public class LocaleLanguageActivity extends PreferencesActivity {
    private Locale locale;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class PreferredLocaleLanguageFragment extends PreferenceFragmentCompat {
        private PreferenceCategory languages;
        private Locale locale;

        private CheckBoxPreference createPreference(final BrailleLanguages.Code code, final List<BrailleLanguages.Code> list) {
            CheckBoxPreference checkBoxPreference = new CheckBoxPreference(getContext());
            checkBoxPreference.setTitle(code.getUserFacingName(getContext()));
            checkBoxPreference.setChecked(list.contains(code));
            checkBoxPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.google.android.accessibility.braille.common.settings.LocaleLanguageActivity$PreferredLocaleLanguageFragment$$ExternalSyntheticLambda0
                @Override // androidx.preference.Preference.OnPreferenceClickListener
                public final boolean onPreferenceClick(Preference preference) {
                    return LocaleLanguageActivity.PreferredLocaleLanguageFragment.this.m68x1c284ccb(list, code, preference);
                }
            });
            return checkBoxPreference;
        }

        private void refresh() {
            PreferenceCategory preferenceCategory = this.languages;
            synchronized (preferenceCategory) {
                List list = preferenceCategory.mPreferences;
                int size = list.size();
                while (true) {
                    size--;
                    if (size < 0) {
                        break;
                    } else {
                        preferenceCategory.removePreferenceInt((Preference) list.get(0));
                    }
                }
            }
            preferenceCategory.notifyHierarchyChanged();
            List list2 = (List) Collection.EL.stream(BrailleLanguages.getAvailableCodes(getContext())).filter(new BrailleKeyBindingUtils$$ExternalSyntheticLambda2(this, 17)).distinct().collect(Collectors.toList());
            final Collator collator = Collator.getInstance(Locale.getDefault());
            List.EL.sort(list2, new Comparator() { // from class: com.google.android.accessibility.braille.common.settings.LocaleLanguageActivity$PreferredLocaleLanguageFragment$$ExternalSyntheticLambda2
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return LocaleLanguageActivity.PreferredLocaleLanguageFragment.this.m70xc8f4a914(collator, (BrailleLanguages.Code) obj, (BrailleLanguages.Code) obj2);
                }
            });
            java.util.List<BrailleLanguages.Code> readAvailablePreferredCodes = BrailleUserPreferences.readAvailablePreferredCodes(getContext());
            Iterator it = list2.iterator();
            while (it.hasNext()) {
                this.languages.addPreference$ar$ds(createPreference((BrailleLanguages.Code) it.next(), readAvailablePreferredCodes));
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLocale(Locale locale) {
            this.locale = locale;
        }

        /* renamed from: lambda$createPreference$2$com-google-android-accessibility-braille-common-settings-LocaleLanguageActivity$PreferredLocaleLanguageFragment, reason: not valid java name */
        public /* synthetic */ boolean m68x1c284ccb(java.util.List list, BrailleLanguages.Code code, Preference preference) {
            if (((CheckBoxPreference) preference).isChecked()) {
                list.add(code);
                BrailleUserPreferences.writePreferredCodes(getContext(), list);
                return true;
            }
            BrailleUserPreferences.removePreferredCode(getContext(), code);
            return true;
        }

        /* renamed from: lambda$refresh$0$com-google-android-accessibility-braille-common-settings-LocaleLanguageActivity$PreferredLocaleLanguageFragment, reason: not valid java name */
        public /* synthetic */ boolean m69x157b9713(BrailleLanguages.Code code) {
            return code.locale.getLanguage().equals(this.locale.getLanguage());
        }

        /* renamed from: lambda$refresh$1$com-google-android-accessibility-braille-common-settings-LocaleLanguageActivity$PreferredLocaleLanguageFragment, reason: not valid java name */
        public /* synthetic */ int m70xc8f4a914(Collator collator, BrailleLanguages.Code code, BrailleLanguages.Code code2) {
            return collator.compare(code.getUserFacingName(getContext()), code2.getUserFacingName(getContext()));
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.braille_language_locale_preferences);
            this.languages = (PreferenceCategory) findPreference(getString(R.string.pref_braille_preferred_locale_languages_category_key));
            refresh();
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        this.locale = Locale.forLanguageTag(getIntent().getStringExtra("locale"));
        PreferredLocaleLanguageFragment preferredLocaleLanguageFragment = new PreferredLocaleLanguageFragment();
        preferredLocaleLanguageFragment.setLocale(this.locale);
        return preferredLocaleLanguageFragment;
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final String getFragmentTag() {
        return "PreferredLocaleLanguageActivity";
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity, com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity, android.support.v4.app.FragmentActivity, androidx.activity.ComponentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setTitle(AppCompatDelegateImpl.Api21Impl.toCharacterTitleCase(this.locale.getDisplayLanguage()));
    }
}
