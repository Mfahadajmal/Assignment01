package com.google.android.accessibility.utils.preference;

import _COROUTINE._BOUNDARY;
import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import androidx.core.view.ViewCompat;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.marvin.talkback.R;
import com.google.android.material.appbar.AppBarLayout;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BasePreferencesFragment extends PreferenceFragmentCompat {
    protected abstract CharSequence getSubTitle();

    protected abstract CharSequence getTitle();

    protected abstract int getXmlResId();

    @Override // androidx.preference.PreferenceFragmentCompat
    public void onCreatePreferences(Bundle bundle, String str) {
        SpannableUtils$IdentifierSpan.addPreferencesFromResource(this, getXmlResId());
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        AppBarLayout appBarLayout;
        ActionBar actionBar;
        super.onResume();
        FragmentActivity activity = getActivity();
        activity.setTitle(getTitle());
        CharSequence subTitle = getSubTitle();
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21() && subTitle != null && (actionBar = activity.getActionBar()) != null) {
            actionBar.setSubtitle(subTitle);
        }
        if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21() && (appBarLayout = (AppBarLayout) activity.findViewById(R.id.app_bar)) != null) {
            appBarLayout.setExpanded(true);
            ViewCompat.setAccessibilityPaneTitle(appBarLayout, getTitle());
        }
    }
}
