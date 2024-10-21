package com.google.android.accessibility.utils.preference;

import _COROUTINE._BOUNDARY;
import android.app.ActionBar;
import android.graphics.drawable.Drawable;
import com.android.settingslib.collapsingtoolbar.CollapsingToolbarBaseActivity;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BasePreferencesActivity extends CollapsingToolbarBaseActivity {
    /* JADX INFO: Access modifiers changed from: protected */
    public final void prepareActionBar(Drawable drawable) {
        ActionBar actionBar = getActionBar();
        if (actionBar != null && drawable != null) {
            actionBar.setIcon(drawable);
        }
        if (!_BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21() && actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
