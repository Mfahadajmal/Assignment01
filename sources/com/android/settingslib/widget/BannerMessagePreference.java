package com.android.settingslib.widget;

import _COROUTINE._BOUNDARY;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.settingslib.R$styleable;
import com.google.android.marvin.talkback.R;
import com.google.mlkit.logging.schema.OnDeviceTextDetectionLoadLogEvent;

/* compiled from: PG */
/* loaded from: classes.dex */
public class BannerMessagePreference extends Preference {
    public static final boolean IS_AT_LEAST_S = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_21();
    public AttentionLevel mAttentionLevel;
    private final OnDeviceTextDetectionLoadLogEvent mDismissButtonInfo$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    private final ButtonInfo mNegativeButtonInfo;
    public final ButtonInfo mPositiveButtonInfo;
    public String mSubtitle;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public enum AttentionLevel {
        HIGH(0, R.color.banner_background_attention_high, R.color.banner_accent_attention_high),
        MEDIUM(1, R.color.banner_background_attention_medium, R.color.banner_accent_attention_medium),
        LOW(2, R.color.banner_background_attention_low, R.color.banner_accent_attention_low),
        NONE(3, R.color.banner_background_attention_none, R.color.banner_accent_attention_none);

        public final int mAccentColorResId;
        public final int mAttrValue;
        public final int mBackgroundColorResId;

        AttentionLevel(int i, int i2, int i3) {
            this.mAttrValue = i;
            this.mBackgroundColorResId = i2;
            this.mAccentColorResId = i3;
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class ButtonInfo {
        public Button mButton;
        public int mColor;
        private final boolean mIsVisible = true;
        public View.OnClickListener mListener;
        public CharSequence mText;

        final void setUpButton() {
            this.mButton.setText(this.mText);
            this.mButton.setOnClickListener(this.mListener);
            if (BannerMessagePreference.IS_AT_LEAST_S) {
                this.mButton.setTextColor(this.mColor);
            }
            if (!TextUtils.isEmpty(this.mText)) {
                this.mButton.setVisibility(0);
            } else {
                this.mButton.setVisibility(8);
            }
        }
    }

    public BannerMessagePreference(Context context) {
        super(context);
        this.mPositiveButtonInfo = new ButtonInfo();
        this.mNegativeButtonInfo = new ButtonInfo();
        this.mDismissButtonInfo$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new OnDeviceTextDetectionLoadLogEvent();
        this.mAttentionLevel = AttentionLevel.HIGH;
        init(context, null);
    }

    private final void init(Context context, AttributeSet attributeSet) {
        setSelectable(false);
        setLayoutResource(R.layout.settingslib_banner_message);
        if (IS_AT_LEAST_S && attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.BannerMessagePreference);
            int i = obtainStyledAttributes.getInt(0, 0);
            for (AttentionLevel attentionLevel : AttentionLevel.values()) {
                if (attentionLevel.mAttrValue == i) {
                    this.mAttentionLevel = attentionLevel;
                    this.mSubtitle = obtainStyledAttributes.getString(1);
                    obtainStyledAttributes.recycle();
                    return;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    @Override // androidx.preference.Preference
    public final void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        int i;
        super.onBindViewHolder(preferenceViewHolder);
        Context context = getContext();
        TextView textView = (TextView) preferenceViewHolder.findViewById(R.id.banner_title);
        CharSequence title = getTitle();
        textView.setText(title);
        int i2 = 8;
        if (title == null) {
            i = 8;
        } else {
            i = 0;
        }
        textView.setVisibility(i);
        ((TextView) preferenceViewHolder.findViewById(R.id.banner_summary)).setText(getSummary());
        this.mPositiveButtonInfo.mButton = (Button) preferenceViewHolder.findViewById(R.id.banner_positive_btn);
        this.mNegativeButtonInfo.mButton = (Button) preferenceViewHolder.findViewById(R.id.banner_negative_btn);
        Resources.Theme theme = context.getTheme();
        int color = context.getResources().getColor(this.mAttentionLevel.mAccentColorResId, theme);
        ImageView imageView = (ImageView) preferenceViewHolder.findViewById(R.id.banner_icon);
        if (imageView != null) {
            Drawable icon = getIcon();
            if (icon == null) {
                icon = getContext().getDrawable(R.drawable.ic_warning);
            }
            imageView.setImageDrawable(icon);
            if (this.mAttentionLevel != AttentionLevel.NONE) {
                imageView.setColorFilter(new PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN));
            }
        }
        if (IS_AT_LEAST_S) {
            int color2 = context.getResources().getColor(this.mAttentionLevel.mBackgroundColorResId, theme);
            preferenceViewHolder.mDividerAllowedAbove = false;
            preferenceViewHolder.mDividerAllowedBelow = false;
            preferenceViewHolder.itemView.getBackground().setTint(color2);
            this.mPositiveButtonInfo.mColor = color;
            this.mNegativeButtonInfo.mColor = color;
            this.mDismissButtonInfo$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.OnDeviceTextDetectionLoadLogEvent$ar$errorCode = (ImageButton) preferenceViewHolder.findViewById(R.id.banner_dismiss_btn);
            OnDeviceTextDetectionLoadLogEvent onDeviceTextDetectionLoadLogEvent = this.mDismissButtonInfo$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
            ((ImageButton) onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode).setOnClickListener(null);
            ((ImageButton) onDeviceTextDetectionLoadLogEvent.OnDeviceTextDetectionLoadLogEvent$ar$errorCode).setVisibility(8);
            TextView textView2 = (TextView) preferenceViewHolder.findViewById(R.id.banner_subtitle);
            textView2.setText(this.mSubtitle);
            if (this.mSubtitle != null) {
                i2 = 0;
            }
            textView2.setVisibility(i2);
        } else {
            preferenceViewHolder.mDividerAllowedAbove = true;
            preferenceViewHolder.mDividerAllowedBelow = true;
        }
        this.mPositiveButtonInfo.setUpButton();
        this.mNegativeButtonInfo.setUpButton();
    }

    public BannerMessagePreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mPositiveButtonInfo = new ButtonInfo();
        this.mNegativeButtonInfo = new ButtonInfo();
        this.mDismissButtonInfo$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new OnDeviceTextDetectionLoadLogEvent();
        this.mAttentionLevel = AttentionLevel.HIGH;
        init(context, attributeSet);
    }

    public BannerMessagePreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mPositiveButtonInfo = new ButtonInfo();
        this.mNegativeButtonInfo = new ButtonInfo();
        this.mDismissButtonInfo$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new OnDeviceTextDetectionLoadLogEvent();
        this.mAttentionLevel = AttentionLevel.HIGH;
        init(context, attributeSet);
    }

    public BannerMessagePreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mPositiveButtonInfo = new ButtonInfo();
        this.mNegativeButtonInfo = new ButtonInfo();
        this.mDismissButtonInfo$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new OnDeviceTextDetectionLoadLogEvent();
        this.mAttentionLevel = AttentionLevel.HIGH;
        init(context, attributeSet);
    }
}
