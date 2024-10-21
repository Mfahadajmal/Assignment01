package com.google.android.libraries.surveys.internal.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.preference.Preference;
import com.google.android.libraries.surveys.R$styleable;
import com.google.android.material.button.MaterialButton;

/* compiled from: PG */
/* loaded from: classes.dex */
public class ButtonWithMaxTextSize extends MaterialButton {
    public ButtonWithMaxTextSize(Context context) {
        super(context);
    }

    private final void capTextSize(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ButtonWithMaxTextSize);
        setTextSize(0, Math.min(getTextSize(), obtainStyledAttributes.getDimensionPixelSize(0, Preference.DEFAULT_ORDER)));
        obtainStyledAttributes.recycle();
    }

    public ButtonWithMaxTextSize(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        capTextSize(context, attributeSet);
    }

    public ButtonWithMaxTextSize(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        capTextSize(context, attributeSet);
    }
}
