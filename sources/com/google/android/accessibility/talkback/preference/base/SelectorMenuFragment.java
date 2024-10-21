package com.google.android.accessibility.talkback.preference.base;

import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class SelectorMenuFragment extends TalkbackBaseFragment {
    public SelectorMenuFragment() {
        super(R.xml.selector_menu_preferences);
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.title_pref_category_selector_menu);
    }
}
