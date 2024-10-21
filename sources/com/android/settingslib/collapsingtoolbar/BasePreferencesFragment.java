package com.android.settingslib.collapsingtoolbar;

import _COROUTINE._BOUNDARY;
import android.support.v4.app.FragmentActivity;
import androidx.preference.PreferenceFragmentCompat;
import com.google.android.marvin.talkback.R;
import com.google.android.material.appbar.AppBarLayout;

/* compiled from: PG */
/* loaded from: classes.dex */
public abstract class BasePreferencesFragment extends PreferenceFragmentCompat {
    public abstract CharSequence getTitle();

    @Override // android.support.v4.app.Fragment
    public void onResume() {
        AppBarLayout appBarLayout;
        super.onResume();
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.setTitle(getTitle());
            if (_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21() && (appBarLayout = (AppBarLayout) activity.findViewById(R.id.app_bar)) != null) {
                appBarLayout.setExpanded(true);
            }
        }
    }
}
