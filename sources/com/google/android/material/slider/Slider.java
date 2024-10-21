package com.google.android.material.slider;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public class Slider extends BaseSlider<Slider, Object, Object> {
    public Slider(Context context) {
        this(context, null);
    }

    public final float getValue() {
        return ((Float) getValues().get(0)).floatValue();
    }

    @Override // com.google.android.material.slider.BaseSlider
    protected final void pickActiveThumb$ar$ds() {
        if (getActiveThumbIndex() != -1) {
            return;
        }
        this.activeThumbIdx = 0;
    }

    public final /* bridge */ /* synthetic */ void setStepSize$ar$ds() {
        if (this.stepSize != 0.5f) {
            this.stepSize = 0.5f;
            this.dirtyConfig = true;
            postInvalidate();
        }
    }

    public final void setValue(float f) {
        setValues(Float.valueOf(f));
    }

    public final /* bridge */ /* synthetic */ void setValueFrom$ar$ds() {
        this.valueFrom = 0.5f;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public final /* bridge */ /* synthetic */ void setValueTo$ar$ds() {
        this.valueTo = 20.0f;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public Slider(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.sliderStyle);
    }

    public Slider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{android.R.attr.value});
        if (obtainStyledAttributes.hasValue(0)) {
            setValue(obtainStyledAttributes.getFloat(0, 0.0f));
        }
        obtainStyledAttributes.recycle();
    }
}
