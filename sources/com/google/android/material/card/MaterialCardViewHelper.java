package com.google.android.material.card;

import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.accessibility.brailleime.input.BrailleInputView$CaptionText$$ExternalSyntheticLambda0;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;
import org.chromium.net.PrivateKeyType;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class MaterialCardViewHelper {
    public static final Drawable CHECKED_ICON_NONE;
    public static final double COS_45 = Math.cos(Math.toRadians(45.0d));
    public final MaterialShapeDrawable bgDrawable;
    public boolean checkable;
    public Drawable checkedIcon;
    public int checkedIconGravity;
    public int checkedIconMargin;
    public int checkedIconSize;
    public ColorStateList checkedIconTint;
    public LayerDrawable clickableForegroundDrawable;
    public MaterialShapeDrawable compatRippleDrawable;
    public Drawable fgDrawable;
    public final MaterialShapeDrawable foregroundContentDrawable;
    private MaterialShapeDrawable foregroundShapeDrawable;
    private ValueAnimator iconAnimator;
    private final TimeInterpolator iconFadeAnimInterpolator;
    private final int iconFadeInAnimDuration;
    private final int iconFadeOutAnimDuration;
    public final MaterialCardView materialCardView;
    public ColorStateList rippleColor;
    public Drawable rippleDrawable;
    private ShapeAppearanceModel shapeAppearanceModel;
    public ColorStateList strokeColor;
    public int strokeWidth;
    public final Rect userContentPadding = new Rect();
    public boolean isBackgroundOverwritten = false;
    public float checkedAnimationProgress = 0.0f;

    static {
        ColorDrawable colorDrawable;
        if (Build.VERSION.SDK_INT <= 28) {
            colorDrawable = new ColorDrawable();
        } else {
            colorDrawable = null;
        }
        CHECKED_ICON_NONE = colorDrawable;
    }

    public MaterialCardViewHelper(MaterialCardView materialCardView, AttributeSet attributeSet, int i, int i2) {
        this.materialCardView = materialCardView;
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(materialCardView.getContext(), attributeSet, i, R.style.Widget_MaterialComponents_CardView);
        this.bgDrawable = materialShapeDrawable;
        materialShapeDrawable.initializeElevationOverlay(materialCardView.getContext());
        materialShapeDrawable.setShadowColor$ar$ds();
        ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder(materialShapeDrawable.getShapeAppearanceModel());
        TypedArray obtainStyledAttributes = materialCardView.getContext().obtainStyledAttributes(attributeSet, R$styleable.CardView, i, R.style.CardView);
        if (obtainStyledAttributes.hasValue(3)) {
            builder.setAllCornerSizes(obtainStyledAttributes.getDimension(3, 0.0f));
        }
        this.foregroundContentDrawable = new MaterialShapeDrawable();
        setShapeAppearanceModel(new ShapeAppearanceModel(builder));
        this.iconFadeAnimInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(materialCardView.getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        this.iconFadeInAnimDuration = DrawableUtils$OutlineCompatR.resolveInteger(materialCardView.getContext(), R.attr.motionDurationShort2, 300);
        this.iconFadeOutAnimDuration = DrawableUtils$OutlineCompatR.resolveInteger(materialCardView.getContext(), R.attr.motionDurationShort1, 300);
        obtainStyledAttributes.recycle();
    }

    private static final float calculateCornerPaddingForCornerTreatment$ar$ds$ar$class_merging(EdgeTreatment edgeTreatment, float f) {
        if (edgeTreatment instanceof RoundedCornerTreatment) {
            return (float) ((1.0d - COS_45) * f);
        }
        if (edgeTreatment instanceof CutCornerTreatment) {
            return f / 2.0f;
        }
        return 0.0f;
    }

    public final float calculateActualCornerPadding() {
        float max = Math.max(calculateCornerPaddingForCornerTreatment$ar$ds$ar$class_merging(this.shapeAppearanceModel.topLeftCorner$ar$class_merging, this.bgDrawable.getTopLeftCornerResolvedSize()), calculateCornerPaddingForCornerTreatment$ar$ds$ar$class_merging(this.shapeAppearanceModel.topRightCorner$ar$class_merging, this.bgDrawable.getTopRightCornerResolvedSize()));
        EdgeTreatment edgeTreatment = this.shapeAppearanceModel.bottomRightCorner$ar$class_merging;
        MaterialShapeDrawable materialShapeDrawable = this.bgDrawable;
        float calculateCornerPaddingForCornerTreatment$ar$ds$ar$class_merging = calculateCornerPaddingForCornerTreatment$ar$ds$ar$class_merging(edgeTreatment, materialShapeDrawable.drawableState.shapeAppearanceModel.bottomRightCornerSize.getCornerSize(materialShapeDrawable.getBoundsAsRectF()));
        EdgeTreatment edgeTreatment2 = this.shapeAppearanceModel.bottomLeftCorner$ar$class_merging;
        MaterialShapeDrawable materialShapeDrawable2 = this.bgDrawable;
        return Math.max(max, Math.max(calculateCornerPaddingForCornerTreatment$ar$ds$ar$class_merging, calculateCornerPaddingForCornerTreatment$ar$ds$ar$class_merging(edgeTreatment2, materialShapeDrawable2.drawableState.shapeAppearanceModel.bottomLeftCornerSize.getCornerSize(materialShapeDrawable2.getBoundsAsRectF()))));
    }

    public final float calculateHorizontalBackgroundPadding() {
        float f;
        float maxCardElevation = this.materialCardView.getMaxCardElevation();
        if (shouldAddCornerPaddingOutsideCardBackground()) {
            f = calculateActualCornerPadding();
        } else {
            f = 0.0f;
        }
        return maxCardElevation + f;
    }

    public final float calculateVerticalBackgroundPadding() {
        float f;
        float maxCardElevation = this.materialCardView.getMaxCardElevation() * 1.5f;
        if (shouldAddCornerPaddingOutsideCardBackground()) {
            f = calculateActualCornerPadding();
        } else {
            f = 0.0f;
        }
        return maxCardElevation + f;
    }

    public final boolean canClipToOutline() {
        if (this.bgDrawable.isRoundRect()) {
            return true;
        }
        return false;
    }

    public final Drawable getClickableForeground() {
        if (this.rippleDrawable == null) {
            int[] iArr = RippleUtils.PRESSED_STATE_SET;
            this.foregroundShapeDrawable = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.rippleDrawable = new RippleDrawable(this.rippleColor, null, this.foregroundShapeDrawable);
        }
        if (this.clickableForegroundDrawable == null) {
            LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{this.rippleDrawable, this.foregroundContentDrawable, this.checkedIcon});
            this.clickableForegroundDrawable = layerDrawable;
            layerDrawable.setId(2, R.id.mtrl_card_checked_layer_id);
        }
        return this.clickableForegroundDrawable;
    }

    public final Drawable insetDrawable(Drawable drawable) {
        int i;
        int i2;
        if (this.materialCardView.mCompatPadding) {
            i2 = (int) Math.ceil(calculateVerticalBackgroundPadding());
            i = (int) Math.ceil(calculateHorizontalBackgroundPadding());
        } else {
            i = 0;
            i2 = 0;
        }
        return new InsetDrawable(drawable, i, i2, i, i2) { // from class: com.google.android.material.card.MaterialCardViewHelper.1
            @Override // android.graphics.drawable.Drawable
            public final int getMinimumHeight() {
                return -1;
            }

            @Override // android.graphics.drawable.Drawable
            public final int getMinimumWidth() {
                return -1;
            }

            @Override // android.graphics.drawable.InsetDrawable, android.graphics.drawable.DrawableWrapper, android.graphics.drawable.Drawable
            public final boolean getPadding(Rect rect) {
                return false;
            }
        };
    }

    public final boolean isCheckedIconBottom() {
        if ((this.checkedIconGravity & 80) == 80) {
            return true;
        }
        return false;
    }

    public final boolean isCheckedIconEnd() {
        if ((this.checkedIconGravity & 8388613) == 8388613) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setCardBackgroundColor(ColorStateList colorStateList) {
        this.bgDrawable.setFillColor(colorStateList);
    }

    public final void setChecked(boolean z, boolean z2) {
        float f;
        int i;
        Drawable drawable = this.checkedIcon;
        if (drawable != null) {
            int i2 = 0;
            float f2 = 0.0f;
            if (z2) {
                if (true == z) {
                    f2 = 1.0f;
                }
                if (z) {
                    f = 1.0f - this.checkedAnimationProgress;
                } else {
                    f = this.checkedAnimationProgress;
                }
                ValueAnimator valueAnimator = this.iconAnimator;
                if (valueAnimator != null) {
                    valueAnimator.cancel();
                    this.iconAnimator = null;
                }
                ValueAnimator ofFloat = ValueAnimator.ofFloat(this.checkedAnimationProgress, f2);
                this.iconAnimator = ofFloat;
                ofFloat.addUpdateListener(new BrailleInputView$CaptionText$$ExternalSyntheticLambda0(this, 6));
                this.iconAnimator.setInterpolator(this.iconFadeAnimInterpolator);
                ValueAnimator valueAnimator2 = this.iconAnimator;
                if (z) {
                    i = this.iconFadeInAnimDuration;
                } else {
                    i = this.iconFadeOutAnimDuration;
                }
                valueAnimator2.setDuration(i * f);
                this.iconAnimator.start();
                return;
            }
            if (true == z) {
                f2 = 1.0f;
            }
            if (true == z) {
                i2 = PrivateKeyType.INVALID;
            }
            drawable.setAlpha(i2);
            this.checkedAnimationProgress = f2;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        this.shapeAppearanceModel = shapeAppearanceModel;
        this.bgDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        this.bgDrawable.shadowBitmapDrawingEnable = !r0.isRoundRect();
        this.foregroundContentDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        MaterialShapeDrawable materialShapeDrawable = this.foregroundShapeDrawable;
        if (materialShapeDrawable != null) {
            materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        }
    }

    public final boolean shouldAddCornerPaddingOutsideCardBackground() {
        if (this.materialCardView.mPreventCornerOverlap && canClipToOutline() && this.materialCardView.mCompatPadding) {
            return true;
        }
        return false;
    }

    public final boolean shouldUseClickableForeground() {
        if (this.materialCardView.isClickable()) {
            return true;
        }
        View view = this.materialCardView;
        while (view.isDuplicateParentStateEnabled() && (view.getParent() instanceof View)) {
            view = (View) view.getParent();
        }
        return view.isClickable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateClickable() {
        Drawable drawable;
        Drawable drawable2 = this.fgDrawable;
        if (shouldUseClickableForeground()) {
            drawable = getClickableForeground();
        } else {
            drawable = this.foregroundContentDrawable;
        }
        this.fgDrawable = drawable;
        if (drawable2 != drawable) {
            if (this.materialCardView.getForeground() instanceof InsetDrawable) {
                ((InsetDrawable) this.materialCardView.getForeground()).setDrawable(drawable);
            } else {
                this.materialCardView.setForeground(insetDrawable(drawable));
            }
        }
    }
}
