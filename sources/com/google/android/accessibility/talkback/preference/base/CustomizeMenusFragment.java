package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.os.Bundle;
import com.google.android.accessibility.talkback.preference.PreferencesActivityUtils;
import com.google.android.accessibility.utils.FeatureSupport;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class CustomizeMenusFragment extends TalkbackBaseFragment {
    public CustomizeMenusFragment() {
        super(R.xml.customize_menus_preferences);
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.title_pref_manage_customize_menus);
    }

    @Override // com.google.android.accessibility.talkback.preference.base.TalkbackBaseFragment, com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        super.onCreatePreferences(bundle, str);
        Context context = getContext();
        if (context != null) {
            if (FeatureSupport.isMultiFingerGestureSupported() && !FormFactorUtils.getInstance().isAndroidWear) {
                return;
            }
            PreferencesActivityUtils.setSummary(context, getPreferenceManager(), R.string.pref_category_manage_context_menu_key, R.string.pref_category_context_menu_summary_single_finger);
            PreferencesActivityUtils.setSummary(context, getPreferenceManager(), R.string.pref_category_manage_selector_menu_key, R.string.pref_category_selector_menu_summary_single_finger);
        }
    }
}
