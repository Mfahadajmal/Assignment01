package com.google.android.material.radiobutton;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatRadioButton;
import android.util.AttributeSet;
import androidx.core.widget.CompoundButtonCompat$Api21Impl;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MaterialRadioButton extends AppCompatRadioButton {
    private static final int[][] ENABLED_CHECKED_STATES = {new int[]{R.attr.state_enabled, R.attr.state_checked}, new int[]{R.attr.state_enabled, -16842912}, new int[]{-16842910, R.attr.state_checked}, new int[]{-16842910, -16842912}};
    private ColorStateList materialThemeColorsTintList;
    private boolean useMaterialThemeColors;

    public MaterialRadioButton(Context context, AttributeSet attributeSet) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, com.google.android.marvin.talkback.R.attr.radioButtonStyle, com.google.android.marvin.talkback.R.style.Widget_MaterialComponents_CompoundButton_RadioButton), attributeSet);
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialRadioButton, com.google.android.marvin.talkback.R.attr.radioButtonStyle, com.google.android.marvin.talkback.R.style.Widget_MaterialComponents_CompoundButton_RadioButton, new int[0]);
        if (obtainStyledAttributes.hasValue(0)) {
            CompoundButtonCompat$Api21Impl.setButtonTintList(this, DrawableUtils$OutlineCompatR.getColorStateList(context2, obtainStyledAttributes, 0));
        }
        this.useMaterialThemeColors = obtainStyledAttributes.getBoolean(1, false);
        obtainStyledAttributes.recycle();
    }

    @Override // android.widget.TextView, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.useMaterialThemeColors && CompoundButtonCompat$Api21Impl.getButtonTintList(this) == null) {
            this.useMaterialThemeColors = true;
            if (this.materialThemeColorsTintList == null) {
                int color = FileUtils.getColor(this, com.google.android.marvin.talkback.R.attr.colorControlActivated);
                int color2 = FileUtils.getColor(this, com.google.android.marvin.talkback.R.attr.colorOnSurface);
                int color3 = FileUtils.getColor(this, com.google.android.marvin.talkback.R.attr.colorSurface);
                int[][] iArr = ENABLED_CHECKED_STATES;
                int length = iArr.length;
                this.materialThemeColorsTintList = new ColorStateList(iArr, new int[]{FileUtils.layer(color3, color, 1.0f), FileUtils.layer(color3, color2, 0.54f), FileUtils.layer(color3, color2, 0.38f), FileUtils.layer(color3, color2, 0.38f)});
            }
            CompoundButtonCompat$Api21Impl.setButtonTintList(this, this.materialThemeColorsTintList);
        }
    }
}
