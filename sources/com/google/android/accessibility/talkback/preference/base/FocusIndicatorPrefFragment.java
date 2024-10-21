package com.google.android.accessibility.talkback.preference.base;

import _COROUTINE._BOUNDARY;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.preference.Preference;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.accessibility.utils.preference.AccessibilitySuitePreferenceCategory;
import com.google.android.accessibility.utils.preference.AccessibilitySuiteRadioButtonPreference;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class FocusIndicatorPrefFragment extends TalkbackBaseFragment {
    private AccessibilitySuitePreferenceCategory colorPrefCategory;
    private SharedPreferences prefs;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum FocusIndicatorPref {
        DEFAULT_COLOR(R.string.title_pref_default_color, R.color.accessibility_focus_highlight_color),
        RED(R.string.title_pref_red, R.color.google_red300),
        ORANGE(R.string.title_pref_orange, R.color.google_orange300),
        YELLOW(R.string.title_pref_yellow, R.color.google_dark_yellow300),
        GREEN(R.string.title_pref_green, R.color.google_green300),
        BLUE(R.string.title_pref_blue, R.color.google_blue300),
        GREY(R.string.title_pref_grey, R.color.google_grey300);

        private final int colorId;
        private final int titleId;

        FocusIndicatorPref(int i, int i2) {
            this.titleId = i;
            this.colorId = i2;
        }

        public int getColorId() {
            return this.colorId;
        }

        public int getTitleId() {
            return this.titleId;
        }
    }

    public FocusIndicatorPrefFragment() {
        super(R.xml.focus_indicator_preferences);
    }

    private void colorSelected(String str) {
        int preferenceCount = this.colorPrefCategory.getPreferenceCount();
        for (int i = 0; i < preferenceCount; i++) {
            AccessibilitySuiteRadioButtonPreference accessibilitySuiteRadioButtonPreference = (AccessibilitySuiteRadioButtonPreference) this.colorPrefCategory.getPreference(i);
            accessibilitySuiteRadioButtonPreference.setChecked(TextUtils.equals(str, accessibilitySuiteRadioButtonPreference.getTitle().toString()));
        }
    }

    private int getColor(int i) {
        return getResources().getColor(i, null);
    }

    private void initBorderColorPref() {
        int i;
        this.colorPrefCategory = (AccessibilitySuitePreferenceCategory) findPreference(getString(R.string.pref_border_color_category_key));
        getContext();
        i = this.prefs.getInt(r1.getString(R.string.pref_border_color_key), getResources().getColor(R.color.accessibility_focus_highlight_color, null));
        for (FocusIndicatorPref focusIndicatorPref : FocusIndicatorPref.values()) {
            if (i == getColor(focusIndicatorPref.getColorId())) {
                colorSelected(getString(focusIndicatorPref.getTitleId()));
                return;
            }
        }
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.title_pref_category_manage_focus_indicator);
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        if (getContext() != null && _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21()) {
            this.prefs = SpannableUtils$IdentifierSpan.getSharedPreferences(getContext());
            initBorderColorPref();
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.preference.PreferenceManager.OnPreferenceTreeClickListener
    public boolean onPreferenceTreeClick(Preference preference) {
        if (preference.getParent().equals(this.colorPrefCategory)) {
            String charSequence = preference.getTitle().toString();
            colorSelected(charSequence);
            FocusIndicatorPref[] values = FocusIndicatorPref.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                FocusIndicatorPref focusIndicatorPref = values[i];
                if (TextUtils.equals(charSequence, getString(focusIndicatorPref.getTitleId()))) {
                    SpannableUtils$IdentifierSpan.putIntPref(this.prefs, getResources(), R.string.pref_border_color_key, getColor(focusIndicatorPref.getColorId()));
                    break;
                }
                i++;
            }
        }
        return super.onPreferenceTreeClick(preference);
    }
}
