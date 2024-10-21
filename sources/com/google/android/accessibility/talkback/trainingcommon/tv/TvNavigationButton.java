package com.google.android.accessibility.talkback.trainingcommon.tv;

import android.content.Context;
import android.graphics.Typeface;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TvNavigationButton extends Button {
    private final float scaleDefault;
    public final float scaleFocused;

    public TvNavigationButton(Context context) {
        super(context);
        float f;
        float f2;
        f = getResources().getFloat(R.dimen.tv_training_button_scale_default);
        this.scaleDefault = f;
        f2 = getResources().getFloat(R.dimen.tv_training_button_scale_focused);
        this.scaleFocused = f2;
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.tv_training_button_padding_horizontal);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen.tv_training_button_padding_vertical);
        setPadding(dimensionPixelSize, dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize2);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen.tv_training_button_margin);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, dimensionPixelSize3);
        setLayoutParams(layoutParams);
        setTextSize(0, context.getResources().getDimension(R.dimen.tv_training_button_text_size));
        setTypeface(Typeface.create("google-sans-text-medium", 0));
        setAllCaps(false);
        setGravity(8388627);
        setIncludeFontPadding(false);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnFocusChangeListener(new TvNavigationButton$$ExternalSyntheticLambda0(this, 0));
        onBlur();
    }

    public final void onBlur() {
        setBackgroundResource(R.drawable.tv_training_button);
        setTextColor(getResources().getColor(R.color.tv_training_button_text_color, getContext().getTheme()));
        startScaleAnimation(getScaleX(), this.scaleDefault);
    }

    public final void startScaleAnimation(float f, float f2) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(f, f2, f, f2, 1, 0.5f, 1, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(300L);
        startAnimation(scaleAnimation);
    }
}
