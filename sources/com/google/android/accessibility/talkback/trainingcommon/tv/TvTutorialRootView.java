package com.google.android.accessibility.talkback.trainingcommon.tv;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
class TvTutorialRootView extends LinearLayout {
    public TvTutorialRootView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final View focusSearch(View view, int i) {
        if (i == 66) {
            if (!(view instanceof TvNavigationButton)) {
                try {
                    return ((ViewGroup) getRootView().findViewById(R.id.training_navigation)).getChildAt(0);
                } catch (NullPointerException unused) {
                }
            }
            i = 66;
        }
        return super.focusSearch(view, i);
    }
}
