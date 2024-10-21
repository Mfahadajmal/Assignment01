package com.google.android.accessibility.talkback.preference.base;

import android.R;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.preference.PreferenceViewHolder;
import androidx.preference.SeekBarPreference;

/* compiled from: PG */
/* loaded from: classes.dex */
public class AccessibilitySeekBarPreference extends SeekBarPreference {
    public AccessibilitySeekBarPreference(Context context) {
        super(context);
    }

    @Override // androidx.preference.SeekBarPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.title);
        SeekBar seekBar = (SeekBar) preferenceViewHolder.findViewById(com.google.android.marvin.talkback.R.id.seekbar);
        if (textView != null && seekBar != null) {
            CharSequence title = getTitle();
            if (!TextUtils.isEmpty(title)) {
                seekBar.setContentDescription(title);
            }
            int[] iArr = ViewCompat.ACCESSIBILITY_ACTIONS_RESOURCE_IDS;
            textView.setLabelFor(-1);
        }
        preferenceViewHolder.itemView.setClickable(false);
    }

    public AccessibilitySeekBarPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
