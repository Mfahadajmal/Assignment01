package com.google.android.accessibility.talkback.preference.base;

import android.content.Context;
import android.util.AttributeSet;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceViewHolder;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TwoTargetIconListPreference extends ListPreference {
    private TwoTargetPreferenceAction twoTargetAction;

    public TwoTargetIconListPreference(Context context) {
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

    public TwoTargetIconListPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    public TwoTargetIconListPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init();
    }

    public TwoTargetIconListPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init();
    }
}
