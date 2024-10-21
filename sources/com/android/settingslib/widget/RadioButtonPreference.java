package com.android.settingslib.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import androidx.preference.CheckBoxPreference;
import androidx.preference.PreferenceViewHolder;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class RadioButtonPreference extends CheckBoxPreference {
    private View mAppendix;
    private int mAppendixVisibility;
    private ImageView mExtraWidget;
    private View mExtraWidgetContainer;
    public OnClickListener mListener;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnClickListener {
        void onRadioButtonClicked(RadioButtonPreference radioButtonPreference);
    }

    public RadioButtonPreference(Context context) {
        this(context, null);
    }

    private final void init() {
        setWidgetLayoutResource(R.layout.preference_widget_radiobutton);
        setLayoutResource(R.layout.preference_radio);
        setIconSpaceReserved(false);
    }

    @Override // androidx.preference.CheckBoxPreference, androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        int i;
        super.onBindViewHolder(preferenceViewHolder);
        View findViewById = preferenceViewHolder.findViewById(R.id.summary_container);
        if (findViewById != null) {
            if (true != TextUtils.isEmpty(getSummary())) {
                i = 0;
            } else {
                i = 8;
            }
            findViewById.setVisibility(i);
            View findViewById2 = preferenceViewHolder.findViewById(R.id.appendix);
            this.mAppendix = findViewById2;
            if (findViewById2 != null && this.mAppendixVisibility != -1) {
                findViewById2.setVisibility(0);
            }
        }
        this.mExtraWidget = (ImageView) preferenceViewHolder.findViewById(R.id.radio_extra_widget);
        View findViewById3 = preferenceViewHolder.findViewById(R.id.radio_extra_widget_container);
        this.mExtraWidgetContainer = findViewById3;
        ImageView imageView = this.mExtraWidget;
        if (imageView != null && findViewById3 != null) {
            imageView.setOnClickListener(null);
            this.mExtraWidgetContainer.setVisibility(8);
        }
    }

    @Override // androidx.preference.TwoStatePreference, androidx.preference.Preference
    public final void onClick() {
        OnClickListener onClickListener = this.mListener;
        if (onClickListener != null) {
            onClickListener.onRadioButtonClicked(this);
        }
    }

    public RadioButtonPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mListener = null;
        this.mAppendixVisibility = -1;
        init();
    }

    public RadioButtonPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mListener = null;
        this.mAppendixVisibility = -1;
        init();
    }
}
