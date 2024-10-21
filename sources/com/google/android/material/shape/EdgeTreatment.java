package com.google.android.material.shape;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.view.ViewCompat;
import com.google.android.material.elevation.ElevationOverlayProvider;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public class EdgeTreatment {
    public EdgeTreatment() {
    }

    public static void applyIconTint(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            if (colorStateList != null && colorStateList.isStateful()) {
                DrawableCompat$Api21Impl.setTintList(drawable, ColorStateList.valueOf(colorStateList.getColorForState(mergeIconState(textInputLayout, checkableImageButton), colorStateList.getDefaultColor())));
            } else {
                DrawableCompat$Api21Impl.setTintList(drawable, colorStateList);
            }
            if (mode != null) {
                DrawableCompat$Api21Impl.setTintMode(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public static ImageView.ScaleType convertScaleType(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i != 5) {
                            if (i != 6) {
                                return ImageView.ScaleType.CENTER;
                            }
                            return ImageView.ScaleType.CENTER_INSIDE;
                        }
                        return ImageView.ScaleType.CENTER_CROP;
                    }
                    return ImageView.ScaleType.FIT_END;
                }
                return ImageView.ScaleType.FIT_CENTER;
            }
            return ImageView.ScaleType.FIT_START;
        }
        return ImageView.ScaleType.FIT_XY;
    }

    public static EdgeTreatment createCornerTreatment$ar$class_merging(int i) {
        if (i != 1) {
            return new RoundedCornerTreatment();
        }
        return new CutCornerTreatment();
    }

    public static boolean isEditable(EditText editText) {
        if (editText.getInputType() != 0) {
            return true;
        }
        return false;
    }

    public static boolean isSwipeMostlyHorizontal(float f, float f2) {
        if (Math.abs(f) > Math.abs(f2)) {
            return true;
        }
        return false;
    }

    private static int[] mergeIconState(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton) {
        int[] drawableState = textInputLayout.getDrawableState();
        int[] drawableState2 = checkableImageButton.getDrawableState();
        int length = drawableState.length;
        int length2 = drawableState2.length;
        int[] copyOf = Arrays.copyOf(drawableState, length + length2);
        System.arraycopy(drawableState2, 0, copyOf, length, length2);
        return copyOf;
    }

    public static void refreshIconDrawableState(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() != null && colorStateList != null && colorStateList.isStateful()) {
            int colorForState = colorStateList.getColorForState(mergeIconState(textInputLayout, checkableImageButton), colorStateList.getDefaultColor());
            Drawable mutate = drawable.mutate();
            DrawableCompat$Api21Impl.setTintList(mutate, ColorStateList.valueOf(colorForState));
            checkableImageButton.setImageDrawable(mutate);
        }
    }

    public static void setElevation(View view, float f) {
        Drawable background = view.getBackground();
        if (background instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable) background).setElevation(f);
        }
    }

    private static void setIconClickable$ar$ds(CheckableImageButton checkableImageButton) {
        boolean hasOnClickListeners = checkableImageButton.hasOnClickListeners();
        checkableImageButton.setFocusable(hasOnClickListeners);
        checkableImageButton.setClickable(hasOnClickListeners);
        checkableImageButton.pressable = hasOnClickListeners;
        checkableImageButton.setLongClickable(false);
        int i = 1;
        if (true != hasOnClickListeners) {
            i = 2;
        }
        checkableImageButton.setImportantForAccessibility(i);
    }

    public static void setIconMinSize(CheckableImageButton checkableImageButton, int i) {
        checkableImageButton.setMinimumWidth(i);
        checkableImageButton.setMinimumHeight(i);
    }

    public static void setIconOnClickListener$ar$ds(CheckableImageButton checkableImageButton, View.OnClickListener onClickListener) {
        checkableImageButton.setOnClickListener(onClickListener);
        setIconClickable$ar$ds(checkableImageButton);
    }

    public static void setIconOnLongClickListener$ar$ds(CheckableImageButton checkableImageButton) {
        checkableImageButton.setOnLongClickListener(null);
        setIconClickable$ar$ds(checkableImageButton);
    }

    public static void setParentAbsoluteElevation(View view) {
        Drawable background = view.getBackground();
        if (background instanceof MaterialShapeDrawable) {
            setParentAbsoluteElevation(view, (MaterialShapeDrawable) background);
        }
    }

    public boolean forceIntersection() {
        return false;
    }

    public void getCornerPath$ar$ds$f7fef056_0(ShapePath shapePath, float f, float f2, float f3) {
        getCornerPath$ar$ds(shapePath, f, f3);
    }

    public void getEdgePath(float f, float f2, float f3, ShapePath shapePath) {
        shapePath.lineTo(f, 0.0f);
    }

    public /* synthetic */ EdgeTreatment(char[] cArr) {
    }

    public static void setParentAbsoluteElevation(View view, MaterialShapeDrawable materialShapeDrawable) {
        ElevationOverlayProvider elevationOverlayProvider = materialShapeDrawable.drawableState.elevationOverlayProvider;
        if (elevationOverlayProvider == null || !elevationOverlayProvider.elevationOverlayEnabled) {
            return;
        }
        float f = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            f += ViewCompat.Api21Impl.getElevation((View) parent);
        }
        MaterialShapeDrawable.MaterialShapeDrawableState materialShapeDrawableState = materialShapeDrawable.drawableState;
        if (materialShapeDrawableState.parentAbsoluteElevation != f) {
            materialShapeDrawableState.parentAbsoluteElevation = f;
            materialShapeDrawable.updateZ();
        }
    }

    public void getCornerPath$ar$ds(ShapePath shapePath, float f, float f2) {
    }
}
