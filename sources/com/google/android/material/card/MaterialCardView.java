package com.google.android.material.card;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Checkable;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.ripple.RippleUtils;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MaterialCardView extends CardView implements Checkable, Shapeable {
    private static final int[] CHECKABLE_STATE_SET = {R.attr.state_checkable};
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private final MaterialCardViewHelper cardViewHelper;
    public boolean checked;
    private boolean isParentCardViewDoneInitializing;

    public MaterialCardView(Context context) {
        this(context, null);
    }

    public final boolean isCheckable() {
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        if (materialCardViewHelper != null && materialCardViewHelper.checkable) {
            return true;
        }
        return false;
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.checked;
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.cardViewHelper.updateClickable();
        EdgeTreatment.setParentAbsoluteElevation(this, this.cardViewHelper.bgDrawable);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 3);
        if (isCheckable()) {
            mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        if (this.checked) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName("androidx.cardview.widget.CardView");
        accessibilityEvent.setChecked(this.checked);
    }

    @Override // android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName("androidx.cardview.widget.CardView");
        accessibilityNodeInfo.setCheckable(isCheckable());
        accessibilityNodeInfo.setClickable(isClickable());
        accessibilityNodeInfo.setChecked(this.checked);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        super.onMeasure(i, i2);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        if (materialCardViewHelper.clickableForegroundDrawable != null) {
            if (materialCardViewHelper.materialCardView.mCompatPadding) {
                float calculateVerticalBackgroundPadding = materialCardViewHelper.calculateVerticalBackgroundPadding();
                i3 = (int) Math.ceil(calculateVerticalBackgroundPadding + calculateVerticalBackgroundPadding);
                float calculateHorizontalBackgroundPadding = materialCardViewHelper.calculateHorizontalBackgroundPadding();
                i4 = (int) Math.ceil(calculateHorizontalBackgroundPadding + calculateHorizontalBackgroundPadding);
            } else {
                i3 = 0;
                i4 = 0;
            }
            if (materialCardViewHelper.isCheckedIconEnd()) {
                i5 = ((measuredWidth - materialCardViewHelper.checkedIconMargin) - materialCardViewHelper.checkedIconSize) - i4;
            } else {
                i5 = materialCardViewHelper.checkedIconMargin;
            }
            if (materialCardViewHelper.isCheckedIconBottom()) {
                i6 = materialCardViewHelper.checkedIconMargin;
            } else {
                i6 = ((measuredHeight - materialCardViewHelper.checkedIconMargin) - materialCardViewHelper.checkedIconSize) - i3;
            }
            int i11 = i6;
            if (materialCardViewHelper.isCheckedIconEnd()) {
                i7 = materialCardViewHelper.checkedIconMargin;
            } else {
                i7 = ((measuredWidth - materialCardViewHelper.checkedIconMargin) - materialCardViewHelper.checkedIconSize) - i4;
            }
            if (materialCardViewHelper.isCheckedIconBottom()) {
                i8 = ((measuredHeight - materialCardViewHelper.checkedIconMargin) - materialCardViewHelper.checkedIconSize) - i3;
            } else {
                i8 = materialCardViewHelper.checkedIconMargin;
            }
            int i12 = i8;
            int layoutDirection = materialCardViewHelper.materialCardView.getLayoutDirection();
            if (layoutDirection == 1) {
                i9 = i5;
            } else {
                i9 = i7;
            }
            if (layoutDirection != 1) {
                i10 = i5;
            } else {
                i10 = i7;
            }
            materialCardViewHelper.clickableForegroundDrawable.setLayerInset(2, i10, i12, i9, i11);
        }
    }

    @Override // android.view.View
    public final void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public final void setBackgroundDrawable(Drawable drawable) {
        if (this.isParentCardViewDoneInitializing) {
            MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
            if (!materialCardViewHelper.isBackgroundOverwritten) {
                materialCardViewHelper.isBackgroundOverwritten = true;
            }
            super.setBackgroundDrawable(drawable);
        }
    }

    public final void setCardBackgroundColor(int i) {
        this.cardViewHelper.setCardBackgroundColor(ColorStateList.valueOf(i));
    }

    @Override // android.widget.Checkable
    public final void setChecked(boolean z) {
        if (this.checked != z) {
            toggle();
        }
    }

    @Override // android.view.View
    public final void setClickable(boolean z) {
        super.setClickable(z);
        MaterialCardViewHelper materialCardViewHelper = this.cardViewHelper;
        if (materialCardViewHelper != null) {
            materialCardViewHelper.updateClickable();
        }
    }

    @Override // com.google.android.material.shape.Shapeable
    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        RectF rectF = new RectF();
        rectF.set(this.cardViewHelper.bgDrawable.getBounds());
        setClipToOutline(shapeAppearanceModel.isRoundRect(rectF));
        this.cardViewHelper.setShapeAppearanceModel(shapeAppearanceModel);
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        MaterialCardViewHelper materialCardViewHelper;
        Drawable drawable;
        if (isCheckable() && isEnabled()) {
            this.checked = !this.checked;
            refreshDrawableState();
            if (Build.VERSION.SDK_INT > 26 && (drawable = (materialCardViewHelper = this.cardViewHelper).rippleDrawable) != null) {
                Rect bounds = drawable.getBounds();
                int i = bounds.bottom;
                materialCardViewHelper.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, i - 1);
                materialCardViewHelper.rippleDrawable.setBounds(bounds.left, bounds.top, bounds.right, i);
            }
            this.cardViewHelper.setChecked(this.checked, true);
        }
    }

    public MaterialCardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.marvin.talkback.R.attr.materialCardViewStyle);
    }

    public MaterialCardView(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, com.google.android.marvin.talkback.R.style.Widget_MaterialComponents_CardView), attributeSet, i);
        float elevation;
        this.checked = false;
        this.isParentCardViewDoneInitializing = true;
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(getContext(), attributeSet, R$styleable.MaterialCardView, i, com.google.android.marvin.talkback.R.style.Widget_MaterialComponents_CardView, new int[0]);
        MaterialCardViewHelper materialCardViewHelper = new MaterialCardViewHelper(this, attributeSet, i, com.google.android.marvin.talkback.R.style.Widget_MaterialComponents_CardView);
        this.cardViewHelper = materialCardViewHelper;
        materialCardViewHelper.setCardBackgroundColor(CardView.IMPL$ar$class_merging$ar$class_merging.getBackgroundColor$ar$class_merging$ar$class_merging(this.mCardViewDelegate$ar$class_merging$ar$class_merging));
        materialCardViewHelper.userContentPadding.set(this.mContentPadding.left, this.mContentPadding.top, this.mContentPadding.right, this.mContentPadding.bottom);
        float f = 0.0f;
        float calculateActualCornerPadding = ((!materialCardViewHelper.materialCardView.mPreventCornerOverlap || materialCardViewHelper.canClipToOutline()) && !materialCardViewHelper.shouldAddCornerPaddingOutsideCardBackground()) ? 0.0f : materialCardViewHelper.calculateActualCornerPadding();
        MaterialCardView materialCardView = materialCardViewHelper.materialCardView;
        if (materialCardView.mPreventCornerOverlap && materialCardView.mCompatPadding) {
            f = (float) ((1.0d - MaterialCardViewHelper.COS_45) * CardView.IMPL$ar$class_merging$ar$class_merging.getRadius$ar$class_merging$ar$class_merging(materialCardView.mCardViewDelegate$ar$class_merging$ar$class_merging));
        }
        float f2 = calculateActualCornerPadding - f;
        MaterialCardView materialCardView2 = materialCardViewHelper.materialCardView;
        int i2 = (int) f2;
        materialCardView2.mContentPadding.set(materialCardViewHelper.userContentPadding.left + i2, materialCardViewHelper.userContentPadding.top + i2, materialCardViewHelper.userContentPadding.right + i2, materialCardViewHelper.userContentPadding.bottom + i2);
        CardView.IMPL$ar$class_merging$ar$class_merging.updatePadding$ar$class_merging$ar$class_merging(materialCardView2.mCardViewDelegate$ar$class_merging$ar$class_merging);
        materialCardViewHelper.strokeColor = DrawableUtils$OutlineCompatR.getColorStateList(materialCardViewHelper.materialCardView.getContext(), obtainStyledAttributes, 11);
        if (materialCardViewHelper.strokeColor == null) {
            materialCardViewHelper.strokeColor = ColorStateList.valueOf(-1);
        }
        materialCardViewHelper.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(12, 0);
        boolean z = obtainStyledAttributes.getBoolean(0, false);
        materialCardViewHelper.checkable = z;
        materialCardViewHelper.materialCardView.setLongClickable(z);
        materialCardViewHelper.checkedIconTint = DrawableUtils$OutlineCompatR.getColorStateList(materialCardViewHelper.materialCardView.getContext(), obtainStyledAttributes, 6);
        Drawable drawable = DrawableUtils$OutlineCompatR.getDrawable(materialCardViewHelper.materialCardView.getContext(), obtainStyledAttributes, 2);
        if (drawable != null) {
            materialCardViewHelper.checkedIcon = drawable.mutate();
            DrawableCompat$Api21Impl.setTintList(materialCardViewHelper.checkedIcon, materialCardViewHelper.checkedIconTint);
            materialCardViewHelper.setChecked(materialCardViewHelper.materialCardView.checked, false);
        } else {
            materialCardViewHelper.checkedIcon = MaterialCardViewHelper.CHECKED_ICON_NONE;
        }
        LayerDrawable layerDrawable = materialCardViewHelper.clickableForegroundDrawable;
        if (layerDrawable != null) {
            layerDrawable.setDrawableByLayerId(com.google.android.marvin.talkback.R.id.mtrl_card_checked_layer_id, materialCardViewHelper.checkedIcon);
        }
        materialCardViewHelper.checkedIconSize = obtainStyledAttributes.getDimensionPixelSize(5, 0);
        materialCardViewHelper.checkedIconMargin = obtainStyledAttributes.getDimensionPixelSize(4, 0);
        materialCardViewHelper.checkedIconGravity = obtainStyledAttributes.getInteger(3, 8388661);
        materialCardViewHelper.rippleColor = DrawableUtils$OutlineCompatR.getColorStateList(materialCardViewHelper.materialCardView.getContext(), obtainStyledAttributes, 7);
        if (materialCardViewHelper.rippleColor == null) {
            materialCardViewHelper.rippleColor = ColorStateList.valueOf(FileUtils.getColor(materialCardViewHelper.materialCardView, com.google.android.marvin.talkback.R.attr.colorControlHighlight));
        }
        ColorStateList colorStateList = DrawableUtils$OutlineCompatR.getColorStateList(materialCardViewHelper.materialCardView.getContext(), obtainStyledAttributes, 1);
        materialCardViewHelper.foregroundContentDrawable.setFillColor(colorStateList == null ? ColorStateList.valueOf(0) : colorStateList);
        int[] iArr = RippleUtils.PRESSED_STATE_SET;
        Drawable drawable2 = materialCardViewHelper.rippleDrawable;
        if (drawable2 != null) {
            ((RippleDrawable) drawable2).setColor(materialCardViewHelper.rippleColor);
        } else {
            MaterialShapeDrawable materialShapeDrawable = materialCardViewHelper.compatRippleDrawable;
        }
        MaterialShapeDrawable materialShapeDrawable2 = materialCardViewHelper.bgDrawable;
        elevation = ((View) materialCardViewHelper.materialCardView.mCardViewDelegate$ar$class_merging$ar$class_merging.AnimationHandler$DurationScaleChangeListener33$ar$this$0).getElevation();
        materialShapeDrawable2.setElevation(elevation);
        materialCardViewHelper.foregroundContentDrawable.setStroke(materialCardViewHelper.strokeWidth, materialCardViewHelper.strokeColor);
        super.setBackgroundDrawable(materialCardViewHelper.insetDrawable(materialCardViewHelper.bgDrawable));
        materialCardViewHelper.fgDrawable = materialCardViewHelper.shouldUseClickableForeground() ? materialCardViewHelper.getClickableForeground() : materialCardViewHelper.foregroundContentDrawable;
        materialCardViewHelper.materialCardView.setForeground(materialCardViewHelper.insetDrawable(materialCardViewHelper.fgDrawable));
        obtainStyledAttributes.recycle();
    }
}
