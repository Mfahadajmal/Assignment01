package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TwoTargetIconPreference extends Preference {
    private TwoTargetPreferenceAction twoTargetAction;

    public TwoTargetIconPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        this.twoTargetAction = new TwoTargetPreferenceAction(getContext(), this);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        this.twoTargetAction.onBindViewHolder(preferenceViewHolder);
    }

    public TwoTargetIconPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public TwoTargetIconPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public TwoTargetIconPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }
}
