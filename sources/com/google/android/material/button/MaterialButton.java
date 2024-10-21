package com.google.android.material.button;

import _COROUTINE._BOUNDARY;
import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.AppCompatBackgroundHelper;
import android.support.v7.widget.AppCompatButton;
import android.text.Layout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.CompoundButton;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.customview.view.AbsSavedState;
import androidx.navigation.NavArgument;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public class MaterialButton extends AppCompatButton implements Checkable, Shapeable {
    private static final int[] CHECKABLE_STATE_SET = {R.attr.state_checkable};
    private static final int[] CHECKED_STATE_SET = {R.attr.state_checked};
    private boolean broadcasting;
    private boolean checked;
    private Drawable icon;
    private int iconGravity;
    private int iconLeft;
    private int iconPadding;
    private int iconSize;
    private ColorStateList iconTint;
    private PorterDuff.Mode iconTintMode;
    private int iconTop;
    private final MaterialButtonHelper materialButtonHelper;
    private final LinkedHashSet onCheckedChangeListeners;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged$ar$ds();
    }

    public MaterialButton(Context context) {
        this(context, null);
    }

    private final boolean isIconEnd() {
        int i = this.iconGravity;
        if (i != 3 && i != 4) {
            return false;
        }
        return true;
    }

    private final boolean isIconStart() {
        int i = this.iconGravity;
        if (i == 1 || i == 2) {
            return true;
        }
        return false;
    }

    private final boolean isIconTop() {
        int i = this.iconGravity;
        if (i != 16 && i != 32) {
            return false;
        }
        return true;
    }

    private final boolean isUsingOriginalBackground() {
        MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
        if (materialButtonHelper != null && !materialButtonHelper.backgroundOverwritten) {
            return true;
        }
        return false;
    }

    private final void resetIconDrawable() {
        if (isIconStart()) {
            setCompoundDrawablesRelative(this.icon, null, null, null);
        } else if (isIconEnd()) {
            setCompoundDrawablesRelative(null, null, this.icon, null);
        } else if (isIconTop()) {
            setCompoundDrawablesRelative(null, this.icon, null, null);
        }
    }

    private final void updateIcon(boolean z) {
        Drawable drawable = this.icon;
        if (drawable != null) {
            Drawable mutate = drawable.mutate();
            this.icon = mutate;
            DrawableCompat$Api21Impl.setTintList(mutate, this.iconTint);
            PorterDuff.Mode mode = this.iconTintMode;
            if (mode != null) {
                DrawableCompat$Api21Impl.setTintMode(this.icon, mode);
            }
            int i = this.iconSize;
            if (i == 0) {
                i = this.icon.getIntrinsicWidth();
            }
            int i2 = this.iconSize;
            if (i2 == 0) {
                i2 = this.icon.getIntrinsicHeight();
            }
            Drawable drawable2 = this.icon;
            int i3 = this.iconLeft;
            int i4 = this.iconTop;
            drawable2.setBounds(i3, i4, i + i3, i2 + i4);
            this.icon.setVisible(true, z);
        }
        if (!z) {
            Drawable[] compoundDrawablesRelative = getCompoundDrawablesRelative();
            Drawable drawable3 = compoundDrawablesRelative[0];
            Drawable drawable4 = compoundDrawablesRelative[1];
            Drawable drawable5 = compoundDrawablesRelative[2];
            if ((isIconStart() && drawable3 != this.icon) || ((isIconEnd() && drawable5 != this.icon) || (isIconTop() && drawable4 != this.icon))) {
                resetIconDrawable();
                return;
            }
            return;
        }
        resetIconDrawable();
    }

    private final void updateIconPosition(int i, int i2) {
        Layout.Alignment alignment;
        boolean z;
        int min;
        if (this.icon != null && getLayout() != null) {
            boolean z2 = true;
            if (!isIconStart() && !isIconEnd()) {
                if (isIconTop()) {
                    this.iconLeft = 0;
                    if (this.iconGravity == 16) {
                        this.iconTop = 0;
                        updateIcon(false);
                        return;
                    }
                    int i3 = this.iconSize;
                    if (i3 == 0) {
                        i3 = this.icon.getIntrinsicHeight();
                    }
                    if (getLineCount() > 1) {
                        min = getLayout().getHeight();
                    } else {
                        TextPaint paint = getPaint();
                        String charSequence = getText().toString();
                        if (getTransformationMethod() != null) {
                            charSequence = getTransformationMethod().getTransformation(charSequence, this).toString();
                        }
                        Rect rect = new Rect();
                        paint.getTextBounds(charSequence, 0, charSequence.length(), rect);
                        min = Math.min(rect.height(), getLayout().getHeight());
                    }
                    int max = Math.max(0, (((((i2 - min) - getPaddingTop()) - i3) - this.iconPadding) - getPaddingBottom()) / 2);
                    if (this.iconTop != max) {
                        this.iconTop = max;
                        updateIcon(false);
                        return;
                    }
                    return;
                }
                return;
            }
            this.iconTop = 0;
            int textAlignment = getTextAlignment();
            if (textAlignment != 1) {
                if (textAlignment != 6 && textAlignment != 3) {
                    if (textAlignment != 4) {
                        alignment = Layout.Alignment.ALIGN_NORMAL;
                    }
                    alignment = Layout.Alignment.ALIGN_CENTER;
                }
                alignment = Layout.Alignment.ALIGN_OPPOSITE;
            } else {
                int gravity = getGravity() & 8388615;
                if (gravity != 1) {
                    if (gravity != 5 && gravity != 8388613) {
                        alignment = Layout.Alignment.ALIGN_NORMAL;
                    }
                    alignment = Layout.Alignment.ALIGN_OPPOSITE;
                }
                alignment = Layout.Alignment.ALIGN_CENTER;
            }
            int i4 = this.iconGravity;
            if (i4 != 1 && i4 != 3 && ((i4 != 2 || alignment != Layout.Alignment.ALIGN_NORMAL) && (this.iconGravity != 4 || alignment != Layout.Alignment.ALIGN_OPPOSITE))) {
                int i5 = this.iconSize;
                if (i5 == 0) {
                    i5 = this.icon.getIntrinsicWidth();
                }
                int lineCount = getLineCount();
                float f = 0.0f;
                for (int i6 = 0; i6 < lineCount; i6++) {
                    f = Math.max(f, getLayout().getLineWidth(i6));
                }
                int ceil = ((((i - ((int) Math.ceil(f))) - getPaddingEnd()) - i5) - this.iconPadding) - getPaddingStart();
                if (alignment == Layout.Alignment.ALIGN_CENTER) {
                    ceil /= 2;
                }
                if (getLayoutDirection() != 1) {
                    z = false;
                } else {
                    z = true;
                }
                if (this.iconGravity != 4) {
                    z2 = false;
                }
                if (z != z2) {
                    ceil = -ceil;
                }
                if (this.iconLeft != ceil) {
                    this.iconLeft = ceil;
                    updateIcon(false);
                    return;
                }
                return;
            }
            this.iconLeft = 0;
            updateIcon(false);
        }
    }

    final String getA11yClassName() {
        Class cls;
        if (!TextUtils.isEmpty(null)) {
            return null;
        }
        if (true != isCheckable()) {
            cls = Button.class;
        } else {
            cls = CompoundButton.class;
        }
        return cls.getName();
    }

    @Override // android.view.View
    public final ColorStateList getBackgroundTintList() {
        Object obj;
        NavArgument.Builder builder;
        if (isUsingOriginalBackground()) {
            obj = this.materialButtonHelper.backgroundTint;
        } else {
            AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
            if (appCompatBackgroundHelper != null && (builder = appCompatBackgroundHelper.mBackgroundTint$ar$class_merging) != null) {
                obj = builder.NavArgument$Builder$ar$type;
            } else {
                obj = null;
            }
        }
        return (ColorStateList) obj;
    }

    @Override // android.view.View
    public final PorterDuff.Mode getBackgroundTintMode() {
        Object obj;
        NavArgument.Builder builder;
        if (isUsingOriginalBackground()) {
            obj = this.materialButtonHelper.backgroundTintMode;
        } else {
            AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
            if (appCompatBackgroundHelper != null && (builder = appCompatBackgroundHelper.mBackgroundTint$ar$class_merging) != null) {
                obj = builder.defaultValue;
            } else {
                obj = null;
            }
        }
        return (PorterDuff.Mode) obj;
    }

    public final boolean isCheckable() {
        MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
        if (materialButtonHelper != null && materialButtonHelper.checkable) {
            return true;
        }
        return false;
    }

    @Override // android.widget.Checkable
    public final boolean isChecked() {
        return this.checked;
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (isUsingOriginalBackground()) {
            EdgeTreatment.setParentAbsoluteElevation(this, this.materialButtonHelper.getMaterialShapeDrawable());
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected final int[] onCreateDrawableState(int i) {
        int[] onCreateDrawableState = super.onCreateDrawableState(i + 2);
        if (isCheckable()) {
            mergeDrawableStates(onCreateDrawableState, CHECKABLE_STATE_SET);
        }
        if (this.checked) {
            mergeDrawableStates(onCreateDrawableState, CHECKED_STATE_SET);
        }
        return onCreateDrawableState;
    }

    @Override // android.support.v7.widget.AppCompatButton, android.view.View
    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(getA11yClassName());
        accessibilityEvent.setChecked(this.checked);
    }

    @Override // android.support.v7.widget.AppCompatButton, android.view.View
    public final void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(getA11yClassName());
        accessibilityNodeInfo.setCheckable(isCheckable());
        accessibilityNodeInfo.setChecked(this.checked);
        accessibilityNodeInfo.setClickable(isClickable());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.AppCompatButton, android.widget.TextView, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.widget.TextView, android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.mSuperState);
        setChecked(savedState.checked);
    }

    @Override // android.widget.TextView, android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.checked = this.checked;
        return savedState;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v7.widget.AppCompatButton, android.widget.TextView
    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        super.onTextChanged(charSequence, i, i2, i3);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.view.View
    public final boolean performClick() {
        if (this.materialButtonHelper.toggleCheckedStateOnClick) {
            toggle();
        }
        return super.performClick();
    }

    @Override // android.view.View
    public final void refreshDrawableState() {
        super.refreshDrawableState();
        if (this.icon != null) {
            if (this.icon.setState(getDrawableState())) {
                invalidate();
            }
        }
    }

    @Override // android.view.View
    public final void setBackground(Drawable drawable) {
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public final void setBackgroundColor(int i) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            if (materialButtonHelper.getMaterialShapeDrawable() != null) {
                materialButtonHelper.getMaterialShapeDrawable().setTint(i);
                return;
            }
            return;
        }
        super.setBackgroundColor(i);
    }

    @Override // android.support.v7.widget.AppCompatButton, android.view.View
    public final void setBackgroundDrawable(Drawable drawable) {
        if (isUsingOriginalBackground()) {
            if (drawable != getBackground()) {
                Log.w("MaterialButton", "MaterialButton manages its own background to control elevation, shape, color and states. Consider using backgroundTint, shapeAppearance and other attributes where available. A custom background will ignore these attributes and you should consider handling interaction states such as pressed, focused and disabled");
                this.materialButtonHelper.setBackgroundOverwritten();
                super.setBackgroundDrawable(drawable);
                return;
            }
            getBackground().setState(drawable.getState());
            return;
        }
        super.setBackgroundDrawable(drawable);
    }

    @Override // android.support.v7.widget.AppCompatButton, android.view.View
    public final void setBackgroundResource(int i) {
        Drawable drawable;
        if (i != 0) {
            drawable = AppCompatDelegate.Api33Impl.getDrawable(getContext(), i);
        } else {
            drawable = null;
        }
        setBackgroundDrawable(drawable);
    }

    @Override // android.view.View
    public final void setBackgroundTintList(ColorStateList colorStateList) {
        setSupportBackgroundTintList(colorStateList);
    }

    @Override // android.view.View
    public final void setBackgroundTintMode(PorterDuff.Mode mode) {
        setSupportBackgroundTintMode(mode);
    }

    @Override // android.widget.Checkable
    public final void setChecked(boolean z) {
        if (isCheckable() && isEnabled() && this.checked != z) {
            this.checked = z;
            refreshDrawableState();
            if (!(getParent() instanceof MaterialButtonToggleGroup)) {
                if (!this.broadcasting) {
                    this.broadcasting = true;
                    Iterator it = this.onCheckedChangeListeners.iterator();
                    while (it.hasNext()) {
                        ((OnCheckedChangeListener) it.next()).onCheckedChanged$ar$ds();
                    }
                    this.broadcasting = false;
                    return;
                }
                return;
            }
            throw null;
        }
    }

    @Override // android.view.View
    public final void setElevation(float f) {
        super.setElevation(f);
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.getMaterialShapeDrawable().setElevation(f);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setInternalBackground(Drawable drawable) {
        super.setBackgroundDrawable(drawable);
    }

    @Override // com.google.android.material.shape.Shapeable
    public final void setShapeAppearanceModel(ShapeAppearanceModel shapeAppearanceModel) {
        if (isUsingOriginalBackground()) {
            this.materialButtonHelper.setShapeAppearanceModel(shapeAppearanceModel);
            return;
        }
        throw new IllegalStateException("Attempted to set ShapeAppearanceModel on a MaterialButton which has an overwritten background.");
    }

    public final void setSupportBackgroundTintList(ColorStateList colorStateList) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            if (materialButtonHelper.backgroundTint != colorStateList) {
                materialButtonHelper.backgroundTint = colorStateList;
                if (materialButtonHelper.getMaterialShapeDrawable() != null) {
                    DrawableCompat$Api21Impl.setTintList(materialButtonHelper.getMaterialShapeDrawable(), materialButtonHelper.backgroundTint);
                    return;
                }
                return;
            }
            return;
        }
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            if (appCompatBackgroundHelper.mBackgroundTint$ar$class_merging == null) {
                appCompatBackgroundHelper.mBackgroundTint$ar$class_merging = new NavArgument.Builder();
            }
            NavArgument.Builder builder = appCompatBackgroundHelper.mBackgroundTint$ar$class_merging;
            builder.NavArgument$Builder$ar$type = colorStateList;
            builder.defaultValuePresent = true;
            appCompatBackgroundHelper.applySupportBackgroundTint();
        }
    }

    public final void setSupportBackgroundTintMode(PorterDuff.Mode mode) {
        if (isUsingOriginalBackground()) {
            MaterialButtonHelper materialButtonHelper = this.materialButtonHelper;
            if (materialButtonHelper.backgroundTintMode != mode) {
                materialButtonHelper.backgroundTintMode = mode;
                if (materialButtonHelper.getMaterialShapeDrawable() != null && materialButtonHelper.backgroundTintMode != null) {
                    DrawableCompat$Api21Impl.setTintMode(materialButtonHelper.getMaterialShapeDrawable(), materialButtonHelper.backgroundTintMode);
                    return;
                }
                return;
            }
            return;
        }
        AppCompatBackgroundHelper appCompatBackgroundHelper = this.mBackgroundTintHelper;
        if (appCompatBackgroundHelper != null) {
            if (appCompatBackgroundHelper.mBackgroundTint$ar$class_merging == null) {
                appCompatBackgroundHelper.mBackgroundTint$ar$class_merging = new NavArgument.Builder();
            }
            NavArgument.Builder builder = appCompatBackgroundHelper.mBackgroundTint$ar$class_merging;
            builder.defaultValue = mode;
            builder.isNullable = true;
            appCompatBackgroundHelper.applySupportBackgroundTint();
        }
    }

    @Override // android.view.View
    public final void setTextAlignment(int i) {
        super.setTextAlignment(i);
        updateIconPosition(getMeasuredWidth(), getMeasuredHeight());
    }

    @Override // android.widget.Checkable
    public final void toggle() {
        setChecked(!this.checked);
    }

    public MaterialButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.marvin.talkback.R.attr.materialButtonStyle);
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new BottomSheetBehavior.SavedState.AnonymousClass1(2);
        boolean checked;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            if (classLoader == null) {
                getClass().getClassLoader();
            }
            this.checked = parcel.readInt() == 1;
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.checked ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public MaterialButton(Context context, AttributeSet attributeSet, int i) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i, com.google.android.marvin.talkback.R.style.Widget_MaterialComponents_Button), attributeSet, i);
        this.onCheckedChangeListeners = new LinkedHashSet();
        this.checked = false;
        this.broadcasting = false;
        Context context2 = getContext();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context2, attributeSet, R$styleable.MaterialButton, i, com.google.android.marvin.talkback.R.style.Widget_MaterialComponents_Button, new int[0]);
        this.iconPadding = obtainStyledAttributes.getDimensionPixelSize(12, 0);
        this.iconTintMode = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(obtainStyledAttributes.getInt(15, -1), PorterDuff.Mode.SRC_IN);
        this.iconTint = DrawableUtils$OutlineCompatR.getColorStateList(getContext(), obtainStyledAttributes, 14);
        this.icon = DrawableUtils$OutlineCompatR.getDrawable(getContext(), obtainStyledAttributes, 10);
        this.iconGravity = obtainStyledAttributes.getInteger(11, 1);
        this.iconSize = obtainStyledAttributes.getDimensionPixelSize(13, 0);
        MaterialButtonHelper materialButtonHelper = new MaterialButtonHelper(this, new ShapeAppearanceModel(ShapeAppearanceModel.builder(context2, attributeSet, i, com.google.android.marvin.talkback.R.style.Widget_MaterialComponents_Button)));
        this.materialButtonHelper = materialButtonHelper;
        materialButtonHelper.insetLeft = obtainStyledAttributes.getDimensionPixelOffset(1, 0);
        materialButtonHelper.insetRight = obtainStyledAttributes.getDimensionPixelOffset(2, 0);
        materialButtonHelper.insetTop = obtainStyledAttributes.getDimensionPixelOffset(3, 0);
        materialButtonHelper.insetBottom = obtainStyledAttributes.getDimensionPixelOffset(4, 0);
        if (obtainStyledAttributes.hasValue(8)) {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(8, -1);
            materialButtonHelper.cornerRadius = dimensionPixelSize;
            materialButtonHelper.setShapeAppearanceModel(materialButtonHelper.shapeAppearanceModel.withCornerSize(dimensionPixelSize));
        }
        materialButtonHelper.strokeWidth = obtainStyledAttributes.getDimensionPixelSize(20, 0);
        materialButtonHelper.backgroundTintMode = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(obtainStyledAttributes.getInt(7, -1), PorterDuff.Mode.SRC_IN);
        materialButtonHelper.backgroundTint = DrawableUtils$OutlineCompatR.getColorStateList(materialButtonHelper.materialButton.getContext(), obtainStyledAttributes, 6);
        materialButtonHelper.strokeColor = DrawableUtils$OutlineCompatR.getColorStateList(materialButtonHelper.materialButton.getContext(), obtainStyledAttributes, 19);
        materialButtonHelper.rippleColor = DrawableUtils$OutlineCompatR.getColorStateList(materialButtonHelper.materialButton.getContext(), obtainStyledAttributes, 16);
        materialButtonHelper.checkable = obtainStyledAttributes.getBoolean(5, false);
        materialButtonHelper.elevation = obtainStyledAttributes.getDimensionPixelSize(9, 0);
        materialButtonHelper.toggleCheckedStateOnClick = obtainStyledAttributes.getBoolean(21, true);
        int paddingStart = materialButtonHelper.materialButton.getPaddingStart();
        int paddingTop = materialButtonHelper.materialButton.getPaddingTop();
        int paddingEnd = materialButtonHelper.materialButton.getPaddingEnd();
        int paddingBottom = materialButtonHelper.materialButton.getPaddingBottom();
        if (obtainStyledAttributes.hasValue(0)) {
            materialButtonHelper.setBackgroundOverwritten();
        } else {
            materialButtonHelper.updateBackground();
        }
        materialButtonHelper.materialButton.setPaddingRelative(paddingStart + materialButtonHelper.insetLeft, paddingTop + materialButtonHelper.insetTop, paddingEnd + materialButtonHelper.insetRight, paddingBottom + materialButtonHelper.insetBottom);
        obtainStyledAttributes.recycle();
        setCompoundDrawablePadding(this.iconPadding);
        updateIcon(this.icon != null);
    }
}
