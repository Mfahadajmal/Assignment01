package com.google.android.accessibility.braille.brailledisplay.settings;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.os.BuildCompat;
import androidx.preference.PreferenceCategory;
import androidx.preference.PreferenceViewHolder;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ProgressPreferenceCategory extends PreferenceCategory {
    public boolean progressActive;

    public ProgressPreferenceCategory(Context context) {
        super(context);
        setLayoutResource(R.layout.preference_progress_category);
    }

    @Override // androidx.preference.PreferenceCategory, androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(R.id.scanning_progress);
        int i = 8;
        if (BuildCompat.isAtLeastS()) {
            preferenceViewHolder.findViewById(R.id.icon_container).setVisibility(8);
        }
        if (true == this.progressActive) {
            i = 0;
        }
        findViewById.setVisibility(i);
    }

    public ProgressPreferenceCategory(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutResource(R.layout.preference_progress_category);
    }

    public ProgressPreferenceCategory(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutResource(R.layout.preference_progress_category);
    }

    public ProgressPreferenceCategory(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        setLayoutResource(R.layout.preference_progress_category);
    }
}
