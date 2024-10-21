package com.google.android.accessibility.talkback.preference.base;

import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class NavigationMenuFragment extends TalkbackBaseFragment {
    public NavigationMenuFragment() {
        super(R.xml.navigation_menu_preferences);
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getTitle() {
        return getText(R.string.title_pref_category_navigation_menu);
    }
}
