package com.google.android.accessibility.brailleime.settings;

import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.accessibility.brailleime.BrailleImeActions;
import com.google.android.accessibility.talkback.selector.SelectorController$$ExternalSyntheticLambda4;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.PreferencesActivity;
import com.google.android.marvin.talkback.R;
import j$.util.DesugarArrays;
import j$.util.stream.Collectors;
import java.util.ArrayList;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BrailleImeGestureCommandActivity extends PreferencesActivity {

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public class BrailleImeGestureCommandFragment extends PreferenceFragmentCompat {
        /* renamed from: lambda$onCreatePreferences$0$com-google-android-accessibility-brailleime-settings-BrailleImeGestureCommandActivity$BrailleImeGestureCommandFragment, reason: not valid java name */
        public /* synthetic */ boolean m73x8d3a20e7(BrailleImeActions.Category category, BrailleImeActions.SubCategory subCategory, BrailleImeActions brailleImeActions) {
            if (brailleImeActions.category == category && brailleImeActions.subCategory == subCategory && brailleImeActions.isAvailable(getContext())) {
                return true;
            }
            return false;
        }

        @Override // androidx.preference.PreferenceFragmentCompat
        public void onCreatePreferences(Bundle bundle, String str) {
            String string;
            PreferenceCategory preferenceCategory;
            getPreferenceManager().setSharedPreferencesName("braille_keyboard");
            BrailleImeActions.Category category = (BrailleImeActions.Category) getActivity().getIntent().getSerializableExtra("category");
            SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, R.xml.brailleime_gesture_commands);
            getActivity().setTitle(getResources().getString(category.titleRes));
            int i = category.descriptionRes;
            Resources resources = getResources();
            if (i == 0) {
                string = "";
            } else {
                string = resources.getString(category.descriptionRes);
            }
            Preference findPreference = findPreference(getString(R.string.pref_key_brailleime_action_category_description));
            if (TextUtils.isEmpty(string)) {
                getPreferenceScreen().removePreference$ar$ds(findPreference);
            } else {
                findPreference.setSummary(string);
            }
            for (BrailleImeActions.SubCategory subCategory : new ArrayList(category.subCategoryList)) {
                if (category != BrailleImeActions.Category.TEXT_SELECTION_AND_EDITING || subCategory != BrailleImeActions.SubCategory.LINE) {
                    if (!TextUtils.isEmpty(subCategory.getName(getResources()))) {
                        preferenceCategory = new PreferenceCategory(getContext());
                        preferenceCategory.setTitle(subCategory.getName(getResources()));
                        getPreferenceScreen().addPreference$ar$ds(preferenceCategory);
                    } else {
                        preferenceCategory = null;
                    }
                    for (BrailleImeActions brailleImeActions : (List) DesugarArrays.stream(BrailleImeActions.values()).filter(new SelectorController$$ExternalSyntheticLambda4(this, category, subCategory, 1)).collect(Collectors.toList())) {
                        Preference preference = new Preference(getContext());
                        preference.setTitle(getResources().getString(brailleImeActions.descriptionRes));
                        if (brailleImeActions.iconRes != 0) {
                            preference.setLayoutResource(R.layout.braille_common_text_with_icon);
                            preference.setIcon(getContext().getDrawable(brailleImeActions.iconRes));
                        } else {
                            preference.setLayoutResource(R.layout.braille_common_text);
                        }
                        preference.setSelectable(false);
                        if (preferenceCategory != null) {
                            preferenceCategory.addPreference$ar$ds(preference);
                        } else {
                            getPreferenceScreen().addPreference$ar$ds(preference);
                        }
                    }
                }
            }
        }
    }

    @Override // com.google.android.accessibility.utils.preference.PreferencesActivity
    protected final PreferenceFragmentCompat createPreferenceFragment() {
        return new BrailleImeGestureCommandFragment();
    }
}
