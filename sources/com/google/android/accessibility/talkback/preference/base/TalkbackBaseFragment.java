package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.preference.Preference;
import com.google.android.accessibility.talkback.preference.TalkBackPreferenceFilter;
import com.google.android.accessibility.utils.FormFactorUtils;
import com.google.android.accessibility.utils.preference.BasePreferencesFragment;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class TalkbackBaseFragment extends BasePreferencesFragment {
    private static final int INVALID_VALUE = -1;
    private final int xmlResId;

    public TalkbackBaseFragment() {
        this.xmlResId = -1;
    }

    public Preference findPreferenceByResId(int i) {
        return findPreference(getString(i));
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public CharSequence getSubTitle() {
        return null;
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment
    public int getXmlResId() {
        return this.xmlResId;
    }

    @Override // com.google.android.accessibility.utils.preference.BasePreferencesFragment, androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        if (this.xmlResId != -1) {
            super.onCreatePreferences(bundle, str);
            Context context = getContext();
            if (context != null) {
                new TalkBackPreferenceFilter(context).filterPreferences(getPreferenceScreen());
            }
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        if (FormFactorUtils.getInstance().isAndroidWear && getListView() != null) {
            getListView().requestFocus();
        }
        return wrapSwipeDismissLayout(onCreateView);
    }

    public void setEnabled(Context context, int i, boolean z) {
        Preference findPreference = findPreference(context.getString(i));
        if (findPreference != null) {
            findPreference.setEnabled(z);
        }
    }

    protected View wrapSwipeDismissLayout(View view) {
        getActivity();
        return view;
    }

    public TalkbackBaseFragment(int i) {
        this.xmlResId = i;
    }
}
