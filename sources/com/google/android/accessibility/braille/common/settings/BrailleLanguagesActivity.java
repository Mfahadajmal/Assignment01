package com.google.android.accessibility.braille.common.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.ActionBarContextView;
import android.widget.ImageButton;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceViewHolder;
import com.google.android.accessibility.braille.common.BrailleUserPreferences;
import com.google.android.accessibility.braille.common.translate.BrailleLanguages;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleLanguagesActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class BrailleLanguageFragment extends PreferenceFragmentCompat {
        private PreferenceCategory languagesPreferenceCategory;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* compiled from: PG */
        /* loaded from: classes.dex */
        public final class LanguagePreference extends Preference {
            public LanguagePreference(Context context) {
                super(context);
                setSelectable(false);
                setWidgetLayoutResource(R.layout.image_preference);
            }

            @Override // androidx.preference.Preference
            public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
                super.onBindViewHolder(preferenceViewHolder);
                ImageButton imageButton = (ImageButton) preferenceViewHolder.findViewById(R.id.remove_button);
                DrawableCompat$Api21Impl.setTint(imageButton.getDrawable(), getContext().getColor(R.color.settings_primary_text));
                imageButton.setContentDescription(getContext().getString(R.string.remove_language_button_content_description, getTitle()));
                imageButton.setOnClickListener(new ActionBarContextView.AnonymousClass1(this, 17, null));
            }
        }

        private Preference createLanguagePreference(BrailleLanguages.Code code) {
            LanguagePreference languagePreference = new LanguagePreference(getContext());
            languagePreference.setTitle(code.getUserFacingName(getContext()));
            languagePreference.setKey(code.name());
            return languagePreference;
        }

        private List<Preference> getLanguagesCategoryList() {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < this.languagesPreferenceCategory.getPreferenceCount(); i++) {
                arrayList.add(this.languagesPreferenceCategory.getPreference(i));
            }
            return arrayList;
        }

        private void refresh() {
            List readAvailablePreferredCodes = BrailleUserPreferences.readAvailablePreferredCodes(getContext());
            List<Preference> languagesCategoryList = getLanguagesCategoryList();
            for (int i = 0; i < readAvailablePreferredCodes.size(); i++) {
                if (i < languagesCategoryList.size()) {
                    if (!((BrailleLanguages.Code) readAvailablePreferredCodes.get(i)).name().equals(languagesCategoryList.get(i).getKey())) {
                        updateLanguagePreference(this.languagesPreferenceCategory.getPreference(i), (BrailleLanguages.Code) readAvailablePreferredCodes.get(i));
                    }
                } else {
                    this.languagesPreferenceCategory.addPreference$ar$ds(createLanguagePreference((BrailleLanguages.Code) readAvailablePreferredCodes.get(i)));
                }
            }
            int preferenceCount = this.languagesPreferenceCategory.getPreferenceCount();
            while (true) {
                preferenceCount--;
                if (preferenceCount >= readAvailablePreferredCodes.size()) {
                    PreferenceCategory preferenceCategory = this.languagesPreferenceCategory;
                    preferenceCategory.removePreference$ar$ds(preferenceCategory.getPreference(preferenceCount));
                } else {
                    return;
                }
            }
        }

        private void updateLanguagePreference(Preference preference, BrailleLanguages.Code code) {
            preference.setTitle(code.getUserFacingName(getContext()));
            preference.setKey(code.name());
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.braille_languages_preferences);
            this.languagesPreferenceCategory = (PreferenceCategory) findPreference(getString(R.string.pref_braille_preferred_languages_category_key));
            Preference findPreference = findPreference(getString(R.string.pref_braille_add_language_preference_key));
            findPreference.setIntent(new Intent(getContext(), (Class<?>) AddLanguageActivity.class));
            DrawableCompat$Api21Impl.setTint(findPreference.getIcon(), getContext().getColor(R.color.settings_primary_text));
            refresh();
        }

        @Override // android.support.v4.app.Fragment
        public void onResume() {
            super.onResume();
            refresh();
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new BrailleLanguageFragment();
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final String getFragmentTag() {
        return "BrailleLanguageActivity";
    }
}
