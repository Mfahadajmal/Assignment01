package com.google.android.material.datepicker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.widget.TextView;
import androidx.core.content.ContextCompat$Api23Impl;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;

/* compiled from: PG */
/* loaded from: classes.dex */
final class CalendarItemStyle {
    private final ColorStateList backgroundColor;
    private final Rect insets;
    private final ShapeAppearanceModel itemShape;
    private final ColorStateList strokeColor;
    private final int strokeWidth;
    private final ColorStateList textColor;

    private CalendarItemStyle(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, ShapeAppearanceModel shapeAppearanceModel, Rect rect) {
        ContextCompat$Api23Impl.checkArgumentNonnegative$ar$ds(rect.left);
        ContextCompat$Api23Impl.checkArgumentNonnegative$ar$ds(rect.top);
        ContextCompat$Api23Impl.checkArgumentNonnegative$ar$ds(rect.right);
        ContextCompat$Api23Impl.checkArgumentNonnegative$ar$ds(rect.bottom);
        this.insets = rect;
        this.textColor = colorStateList2;
        this.backgroundColor = colorStateList;
        this.strokeColor = colorStateList3;
        this.strokeWidth = i;
        this.itemShape = shapeAppearanceModel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static CalendarItemStyle create(Context context, int i) {
        if (i != 0) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.MaterialCalendarItem);
            Rect rect = new Rect(obtainStyledAttributes.getDimensionPixelOffset(0, 0), obtainStyledAttributes.getDimensionPixelOffset(2, 0), obtainStyledAttributes.getDimensionPixelOffset(1, 0), obtainStyledAttributes.getDimensionPixelOffset(3, 0));
            ColorStateList colorStateList = DrawableUtils$OutlineCompatR.getColorStateList(context, obtainStyledAttributes, 4);
            ColorStateList colorStateList2 = DrawableUtils$OutlineCompatR.getColorStateList(context, obtainStyledAttributes, 9);
            ColorStateList colorStateList3 = DrawableUtils$OutlineCompatR.getColorStateList(context, obtainStyledAttributes, 7);
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(8, 0);
            ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel(ShapeAppearanceModel.builder(context, obtainStyledAttributes.getResourceId(5, 0), obtainStyledAttributes.getResourceId(6, 0)));
            obtainStyledAttributes.recycle();
            return new CalendarItemStyle(colorStateList, colorStateList2, colorStateList3, dimensionPixelSize, shapeAppearanceModel, rect);
        }
        throw new IllegalArgumentException("Cannot create a CalendarItemStyle with a styleResId of 0");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getBottomInset() {
        return this.insets.bottom;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getTopInset() {
        return this.insets.top;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void styleItem(TextView textView) {
        styleItem(textView, null, null);
    }

    final void styleItem(TextView textView, ColorStateList colorStateList, ColorStateList colorStateList2) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable();
        materialShapeDrawable.setShapeAppearanceModel(this.itemShape);
        materialShapeDrawable2.setShapeAppearanceModel(this.itemShape);
        materialShapeDrawable.setFillColor(this.backgroundColor);
        materialShapeDrawable.setStroke(this.strokeWidth, this.strokeColor);
        textView.setTextColor(this.textColor);
        RippleDrawable rippleDrawable = new RippleDrawable(this.textColor.withAlpha(30), materialShapeDrawable, materialShapeDrawable2);
        Rect rect = this.insets;
        textView.setBackground(new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom));
    }
}
