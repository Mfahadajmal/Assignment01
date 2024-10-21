package com.google.android.material.textfield;

import android.R;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DrawableUtils;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStructure;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.autofill.AutofillId;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat$Api23Impl;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat$Api21Impl;
import androidx.core.text.BidiFormatter;
import androidx.core.text.TextDirectionHeuristicCompat;
import androidx.core.text.TextDirectionHeuristicsCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.transition.Fade;
import androidx.transition.TransitionManager;
import com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.CollapsingTextHelper;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.textfield.CutoutDrawable;
import io.grpc.ClientStreamTracer;
import io.grpc.internal.RetryingNameResolver;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.chromium.net.NetError;

/* compiled from: PG */
/* loaded from: classes.dex */
public class TextInputLayout extends LinearLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    private static final int[][] EDIT_TEXT_BACKGROUND_RIPPLE_STATE = {new int[]{R.attr.state_pressed}, new int[0]};
    public static final /* synthetic */ int TextInputLayout$ar$NoOp = 0;
    private ValueAnimator animator;
    private boolean areCornerRadiiRtl;
    private MaterialShapeDrawable boxBackground;
    private boolean boxBackgroundApplied;
    private int boxBackgroundColor;
    public int boxBackgroundMode;
    private int boxCollapsedPaddingTopPx;
    private final int boxLabelCutoutPaddingPx;
    private int boxStrokeColor;
    private int boxStrokeWidthDefaultPx;
    private int boxStrokeWidthFocusedPx;
    private int boxStrokeWidthPx;
    private MaterialShapeDrawable boxUnderlineDefault;
    private MaterialShapeDrawable boxUnderlineFocused;
    public final CollapsingTextHelper collapsingTextHelper;
    boolean counterEnabled;
    public int counterMaxLength;
    private int counterOverflowTextAppearance;
    private ColorStateList counterOverflowTextColor;
    public boolean counterOverflowed;
    private int counterTextAppearance;
    private ColorStateList counterTextColor;
    public TextView counterView;
    private ColorStateList cursorColor;
    private ColorStateList cursorErrorColor;
    private int defaultFilledBackgroundColor;
    private ColorStateList defaultHintTextColor;
    private int defaultStrokeColor;
    private int disabledColor;
    private int disabledFilledBackgroundColor;
    public EditText editText;
    public final LinkedHashSet editTextAttachedListeners;
    private Drawable endDummyDrawable;
    private int endDummyDrawableWidth;
    public final EndCompoundLayout endLayout;
    private boolean expandedHintEnabled;
    private StateListDrawable filledDropDownMenuBackground;
    private int focusedFilledBackgroundColor;
    private int focusedStrokeColor;
    private ColorStateList focusedTextColor;
    private boolean globalLayoutListenerAdded;
    private CharSequence hint;
    private boolean hintAnimationEnabled;
    private boolean hintEnabled;
    public boolean hintExpanded;
    private int hoveredFilledBackgroundColor;
    private int hoveredStrokeColor;
    private boolean inDrawableStateChanged;
    public final IndicatorViewController indicatorViewController;
    private final FrameLayout inputFrame;
    public boolean isProvidingHint;
    private EdgeTreatment lengthCounter$ar$class_merging$ar$class_merging;
    private int maxEms;
    private int maxWidth;
    private int minEms;
    private int minWidth;
    private Drawable originalEditTextEndDrawable;
    int originalEditTextMinimumHeight;
    private CharSequence originalHint;
    private MaterialShapeDrawable outlinedDropDownMenuBackground;
    public boolean placeholderEnabled;
    private Fade placeholderFadeIn;
    private Fade placeholderFadeOut;
    public CharSequence placeholderText;
    private int placeholderTextAppearance;
    private ColorStateList placeholderTextColor;
    private TextView placeholderTextView;
    public boolean restoringSavedState;
    private ShapeAppearanceModel shapeAppearanceModel;
    private Drawable startDummyDrawable;
    private int startDummyDrawableWidth;
    public final StartCompoundLayout startLayout;
    private ColorStateList strokeErrorColor;
    private final Rect tmpBoundsRect;
    private final Rect tmpRect;
    private final RectF tmpRectF;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class AccessibilityDelegate extends AccessibilityDelegateCompat {
        private final TextInputLayout layout;

        public AccessibilityDelegate(TextInputLayout textInputLayout) {
            this.layout = textInputLayout;
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            CharSequence charSequence;
            CharSequence charSequence2;
            boolean z;
            String str;
            TextView textView;
            super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfoCompat);
            EditText editText = this.layout.editText;
            CharSequence charSequence3 = null;
            if (editText != null) {
                charSequence = editText.getText();
            } else {
                charSequence = null;
            }
            TextInputLayout textInputLayout = this.layout;
            CharSequence hint = textInputLayout.getHint();
            CharSequence error = textInputLayout.getError();
            if (textInputLayout.placeholderEnabled) {
                charSequence2 = textInputLayout.placeholderText;
            } else {
                charSequence2 = null;
            }
            int i = textInputLayout.counterMaxLength;
            if (textInputLayout.counterEnabled && textInputLayout.counterOverflowed && (textView = textInputLayout.counterView) != null) {
                charSequence3 = textView.getContentDescription();
            }
            boolean isEmpty = TextUtils.isEmpty(charSequence);
            boolean z2 = !isEmpty;
            boolean z3 = !TextUtils.isEmpty(hint);
            boolean z4 = !this.layout.hintExpanded;
            boolean z5 = !TextUtils.isEmpty(error);
            if (!z5 && TextUtils.isEmpty(charSequence3)) {
                z = false;
            } else {
                z = true;
            }
            if (z3) {
                str = hint.toString();
            } else {
                str = "";
            }
            StartCompoundLayout startCompoundLayout = this.layout.startLayout;
            if (startCompoundLayout.prefixTextView.getVisibility() == 0) {
                accessibilityNodeInfoCompat.setLabelFor(startCompoundLayout.prefixTextView);
                accessibilityNodeInfoCompat.setTraversalAfter(startCompoundLayout.prefixTextView);
            } else {
                accessibilityNodeInfoCompat.setTraversalAfter(startCompoundLayout.startIconView);
            }
            if (z2) {
                accessibilityNodeInfoCompat.setText(charSequence);
            } else if (!TextUtils.isEmpty(str)) {
                accessibilityNodeInfoCompat.setText(str);
                if (z4 && charSequence2 != null) {
                    accessibilityNodeInfoCompat.setText(str + ", " + charSequence2.toString());
                }
            } else if (charSequence2 != null) {
                accessibilityNodeInfoCompat.setText(charSequence2);
            }
            if (!TextUtils.isEmpty(str)) {
                accessibilityNodeInfoCompat.setHintText(str);
                accessibilityNodeInfoCompat.setShowingHintText(isEmpty);
            }
            if (charSequence == null || charSequence.length() != i) {
                i = -1;
            }
            accessibilityNodeInfoCompat.mInfo.setMaxTextLength(i);
            if (z) {
                if (true != z5) {
                    error = charSequence3;
                }
                accessibilityNodeInfoCompat.mInfo.setError(error);
            }
            View view2 = this.layout.indicatorViewController.helperTextView;
            if (view2 != null) {
                accessibilityNodeInfoCompat.setLabelFor(view2);
            }
            this.layout.endLayout.getEndIconDelegate().onInitializeAccessibilityNodeInfo$ar$ds(accessibilityNodeInfoCompat);
        }

        @Override // androidx.core.view.AccessibilityDelegateCompat
        public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
            super.onPopulateAccessibilityEvent(view, accessibilityEvent);
            int i = TextInputLayout.TextInputLayout$ar$NoOp;
            this.layout.endLayout.getEndIconDelegate().onPopulateAccessibilityEvent$ar$ds(accessibilityEvent);
        }
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnEndIconChangedListener {
        void onEndIconChanged$ar$ds();
    }

    public TextInputLayout(Context context) {
        this(context, null);
    }

    private final void applyBoxAttributes() {
        ColorStateList valueOf;
        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
        if (materialShapeDrawable == null) {
            return;
        }
        ShapeAppearanceModel shapeAppearanceModel = materialShapeDrawable.getShapeAppearanceModel();
        ShapeAppearanceModel shapeAppearanceModel2 = this.shapeAppearanceModel;
        if (shapeAppearanceModel != shapeAppearanceModel2) {
            this.boxBackground.setShapeAppearanceModel(shapeAppearanceModel2);
        }
        if (this.boxBackgroundMode == 2 && canDrawStroke()) {
            this.boxBackground.setStroke(this.boxStrokeWidthPx, this.boxStrokeColor);
        }
        int i = this.boxBackgroundColor;
        if (this.boxBackgroundMode == 1) {
            i = ColorUtils.compositeColors(this.boxBackgroundColor, FileUtils.getColor(getContext(), com.google.android.marvin.talkback.R.attr.colorSurface, 0));
        }
        this.boxBackgroundColor = i;
        this.boxBackground.setFillColor(ColorStateList.valueOf(i));
        MaterialShapeDrawable materialShapeDrawable2 = this.boxUnderlineDefault;
        if (materialShapeDrawable2 != null && this.boxUnderlineFocused != null) {
            if (canDrawStroke()) {
                if (this.editText.isFocused()) {
                    valueOf = ColorStateList.valueOf(this.defaultStrokeColor);
                } else {
                    valueOf = ColorStateList.valueOf(this.boxStrokeColor);
                }
                materialShapeDrawable2.setFillColor(valueOf);
                this.boxUnderlineFocused.setFillColor(ColorStateList.valueOf(this.boxStrokeColor));
            }
            invalidate();
        }
        updateEditTextBoxBackgroundIfNeeded();
    }

    private final int calculateLabelMarginTop() {
        float collapsedTextHeight;
        if (this.hintEnabled) {
            int i = this.boxBackgroundMode;
            if (i != 0) {
                if (i != 2) {
                    return 0;
                }
                collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight() / 2.0f;
            } else {
                collapsedTextHeight = this.collapsingTextHelper.getCollapsedTextHeight();
            }
            return (int) collapsedTextHeight;
        }
        return 0;
    }

    private final boolean canDrawStroke() {
        if (this.boxStrokeWidthPx >= 0 && this.boxStrokeColor != 0) {
            return true;
        }
        return false;
    }

    private final void closeCutout() {
        if (cutoutEnabled()) {
            ((CutoutDrawable) this.boxBackground).setCutout(0.0f, 0.0f, 0.0f, 0.0f);
        }
    }

    private final Fade createPlaceholderFadeTransition() {
        Fade fade = new Fade();
        fade.mDuration = DrawableUtils$OutlineCompatR.resolveInteger(getContext(), com.google.android.marvin.talkback.R.attr.motionDurationShort2, 87);
        fade.mInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(getContext(), com.google.android.marvin.talkback.R.attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        return fade;
    }

    private final boolean cutoutEnabled() {
        if (this.hintEnabled && !TextUtils.isEmpty(this.hint) && (this.boxBackground instanceof CutoutDrawable)) {
            return true;
        }
        return false;
    }

    private final MaterialShapeDrawable getDropDownMaterialShapeDrawable(boolean z) {
        float dimensionPixelOffset;
        float f;
        ColorStateList colorStateList;
        float dimensionPixelOffset2 = getResources().getDimensionPixelOffset(com.google.android.marvin.talkback.R.dimen.mtrl_shape_corner_size_small_component);
        EditText editText = this.editText;
        if (editText instanceof MaterialAutoCompleteTextView) {
            dimensionPixelOffset = ((MaterialAutoCompleteTextView) editText).popupElevation;
        } else {
            dimensionPixelOffset = getResources().getDimensionPixelOffset(com.google.android.marvin.talkback.R.dimen.m3_comp_outlined_autocomplete_menu_container_elevation);
        }
        if (true != z) {
            f = 0.0f;
        } else {
            f = dimensionPixelOffset2;
        }
        int dimensionPixelOffset3 = getResources().getDimensionPixelOffset(com.google.android.marvin.talkback.R.dimen.mtrl_exposed_dropdown_menu_popup_vertical_padding);
        ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder();
        builder.setTopLeftCornerSize$ar$ds(f);
        builder.setTopRightCornerSize$ar$ds(f);
        builder.setBottomLeftCornerSize$ar$ds(dimensionPixelOffset2);
        builder.setBottomRightCornerSize$ar$ds(dimensionPixelOffset2);
        ShapeAppearanceModel shapeAppearanceModel = new ShapeAppearanceModel(builder);
        EditText editText2 = this.editText;
        if (editText2 instanceof MaterialAutoCompleteTextView) {
            colorStateList = ((MaterialAutoCompleteTextView) editText2).dropDownBackgroundTint;
        } else {
            colorStateList = null;
        }
        Context context = getContext();
        ShapeAppearanceModel shapeAppearanceModel2 = MaterialShapeDrawable.DEFAULT_INTERPOLATION_START_SHAPE_APPEARANCE_MODEL;
        if (colorStateList == null) {
            colorStateList = ColorStateList.valueOf(FileUtils.getColor(context, com.google.android.marvin.talkback.R.attr.colorSurface, MaterialShapeDrawable.class.getSimpleName()));
        }
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        materialShapeDrawable.initializeElevationOverlay(context);
        materialShapeDrawable.setFillColor(colorStateList);
        materialShapeDrawable.setElevation(dimensionPixelOffset);
        materialShapeDrawable.setShapeAppearanceModel(shapeAppearanceModel);
        MaterialShapeDrawable.MaterialShapeDrawableState materialShapeDrawableState = materialShapeDrawable.drawableState;
        if (materialShapeDrawableState.padding == null) {
            materialShapeDrawableState.padding = new Rect();
        }
        materialShapeDrawable.drawableState.padding.set(0, dimensionPixelOffset3, 0, dimensionPixelOffset3);
        materialShapeDrawable.invalidateSelf();
        return materialShapeDrawable;
    }

    private final int getLabelLeftBoundAlignedWithPrefixAndSuffix(int i, boolean z) {
        int suffixTextEndOffset;
        if (!z) {
            if (getPrefixText() != null) {
                suffixTextEndOffset = this.startLayout.getPrefixTextStartOffset();
            }
            suffixTextEndOffset = this.editText.getCompoundPaddingLeft();
        } else {
            if (getSuffixText() != null) {
                suffixTextEndOffset = this.endLayout.getSuffixTextEndOffset();
            }
            suffixTextEndOffset = this.editText.getCompoundPaddingLeft();
        }
        return i + suffixTextEndOffset;
    }

    private final int getLabelRightBoundAlignedWithPrefixAndSuffix(int i, boolean z) {
        int prefixTextStartOffset;
        if (!z) {
            if (getSuffixText() != null) {
                prefixTextStartOffset = this.endLayout.getSuffixTextEndOffset();
            }
            prefixTextStartOffset = this.editText.getCompoundPaddingRight();
        } else {
            if (getPrefixText() != null) {
                prefixTextStartOffset = this.startLayout.getPrefixTextStartOffset();
            }
            prefixTextStartOffset = this.editText.getCompoundPaddingRight();
        }
        return i - prefixTextStartOffset;
    }

    private final Drawable getOrCreateOutlinedDropDownMenuBackground() {
        if (this.outlinedDropDownMenuBackground == null) {
            this.outlinedDropDownMenuBackground = getDropDownMaterialShapeDrawable(true);
        }
        return this.outlinedDropDownMenuBackground;
    }

    private final void hidePlaceholderText() {
        TextView textView = this.placeholderTextView;
        if (textView != null && this.placeholderEnabled) {
            textView.setText((CharSequence) null);
            TransitionManager.beginDelayedTransition(this.inputFrame, this.placeholderFadeOut);
            this.placeholderTextView.setVisibility(4);
        }
    }

    private final boolean isSingleLineFilledTextField() {
        if (this.boxBackgroundMode == 1 && this.editText.getMinLines() <= 1) {
            return true;
        }
        return false;
    }

    static /* synthetic */ int lambda$new$0(Editable editable) {
        if (editable != null) {
            return editable.length();
        }
        return 0;
    }

    private final void onApplyBoxBackgroundMode() {
        int i = this.boxBackgroundMode;
        if (i != 0) {
            if (i != 1) {
                if (i == 2) {
                    if (this.hintEnabled && !(this.boxBackground instanceof CutoutDrawable)) {
                        ShapeAppearanceModel shapeAppearanceModel = this.shapeAppearanceModel;
                        if (shapeAppearanceModel == null) {
                            shapeAppearanceModel = new ShapeAppearanceModel();
                        }
                        this.boxBackground = new CutoutDrawable.ImplApi18(new CutoutDrawable.CutoutDrawableState(shapeAppearanceModel, new RectF()));
                    } else {
                        this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
                    }
                    this.boxUnderlineDefault = null;
                    this.boxUnderlineFocused = null;
                } else {
                    throw new IllegalArgumentException(i + " is illegal; only @BoxBackgroundMode constants are supported.");
                }
            } else {
                this.boxBackground = new MaterialShapeDrawable(this.shapeAppearanceModel);
                this.boxUnderlineDefault = new MaterialShapeDrawable();
                this.boxUnderlineFocused = new MaterialShapeDrawable();
            }
        } else {
            this.boxBackground = null;
            this.boxUnderlineDefault = null;
            this.boxUnderlineFocused = null;
        }
        updateEditTextBoxBackgroundIfNeeded();
        updateTextInputBoxState();
        if (this.boxBackgroundMode == 1) {
            if (DrawableUtils$OutlineCompatR.isFontScaleAtLeast2_0(getContext())) {
                this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.material_font_2_0_box_collapsed_padding_top);
            } else if (DrawableUtils$OutlineCompatR.isFontScaleAtLeast1_3(getContext())) {
                this.boxCollapsedPaddingTopPx = getResources().getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.material_font_1_3_box_collapsed_padding_top);
            }
        }
        if (this.editText != null && this.boxBackgroundMode == 1) {
            if (DrawableUtils$OutlineCompatR.isFontScaleAtLeast2_0(getContext())) {
                EditText editText = this.editText;
                editText.setPaddingRelative(editText.getPaddingStart(), getResources().getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.material_filled_edittext_font_2_0_padding_top), this.editText.getPaddingEnd(), getResources().getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.material_filled_edittext_font_2_0_padding_bottom));
            } else if (DrawableUtils$OutlineCompatR.isFontScaleAtLeast1_3(getContext())) {
                EditText editText2 = this.editText;
                editText2.setPaddingRelative(editText2.getPaddingStart(), getResources().getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.material_filled_edittext_font_1_3_padding_top), this.editText.getPaddingEnd(), getResources().getDimensionPixelSize(com.google.android.marvin.talkback.R.dimen.material_filled_edittext_font_1_3_padding_bottom));
            }
        }
        if (this.boxBackgroundMode != 0) {
            updateInputLayoutMargins();
        }
        EditText editText3 = this.editText;
        if (editText3 instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText3;
            if (autoCompleteTextView.getDropDownBackground() == null) {
                int i2 = this.boxBackgroundMode;
                if (i2 == 2) {
                    autoCompleteTextView.setDropDownBackgroundDrawable(getOrCreateOutlinedDropDownMenuBackground());
                    return;
                }
                if (i2 == 1) {
                    if (this.filledDropDownMenuBackground == null) {
                        StateListDrawable stateListDrawable = new StateListDrawable();
                        this.filledDropDownMenuBackground = stateListDrawable;
                        stateListDrawable.addState(new int[]{R.attr.state_above_anchor}, getOrCreateOutlinedDropDownMenuBackground());
                        this.filledDropDownMenuBackground.addState(new int[0], getDropDownMaterialShapeDrawable(false));
                    }
                    autoCompleteTextView.setDropDownBackgroundDrawable(this.filledDropDownMenuBackground);
                }
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00c3  */
    /* JADX WARN: Removed duplicated region for block: B:34:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void openCutout() {
        /*
            Method dump skipped, instructions count: 260
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.openCutout():void");
    }

    private static void recursiveSetEnabled(ViewGroup viewGroup, boolean z) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setEnabled(z);
            if (childAt instanceof ViewGroup) {
                recursiveSetEnabled((ViewGroup) childAt, z);
            }
        }
    }

    private final void setPlaceholderTextEnabled(boolean z) {
        if (this.placeholderEnabled == z) {
            return;
        }
        if (z) {
            TextView textView = this.placeholderTextView;
            if (textView != null) {
                this.inputFrame.addView(textView);
                this.placeholderTextView.setVisibility(0);
            }
        } else {
            TextView textView2 = this.placeholderTextView;
            if (textView2 != null) {
                textView2.setVisibility(8);
            }
            this.placeholderTextView = null;
        }
        this.placeholderEnabled = z;
    }

    private final void updateCounter() {
        if (this.counterView != null) {
            EditText editText = this.editText;
            updateCounter(editText == null ? null : editText.getText());
        }
    }

    private final void updateCounterTextAppearanceAndColor() {
        int i;
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        TextView textView = this.counterView;
        if (textView != null) {
            if (this.counterOverflowed) {
                i = this.counterOverflowTextAppearance;
            } else {
                i = this.counterTextAppearance;
            }
            setTextAppearanceCompatWithErrorFallback(textView, i);
            if (!this.counterOverflowed && (colorStateList2 = this.counterTextColor) != null) {
                this.counterView.setTextColor(colorStateList2);
            }
            if (this.counterOverflowed && (colorStateList = this.counterOverflowTextColor) != null) {
                this.counterView.setTextColor(colorStateList);
            }
        }
    }

    private final void updateCursorColor() {
        Drawable textCursorDrawable;
        Drawable textCursorDrawable2;
        ColorStateList colorStateList;
        ColorStateList colorStateList2 = this.cursorColor;
        if (colorStateList2 == null) {
            colorStateList2 = FileUtils.getColorStateListOrNull(getContext(), com.google.android.marvin.talkback.R.attr.colorControlActivated);
        }
        EditText editText = this.editText;
        if (editText != null) {
            textCursorDrawable = editText.getTextCursorDrawable();
            if (textCursorDrawable != null) {
                textCursorDrawable2 = this.editText.getTextCursorDrawable();
                Drawable mutate = textCursorDrawable2.mutate();
                if ((shouldShowError() || (this.counterView != null && this.counterOverflowed)) && (colorStateList = this.cursorErrorColor) != null) {
                    colorStateList2 = colorStateList;
                }
                DrawableCompat$Api21Impl.setTintList(mutate, colorStateList2);
            }
        }
    }

    private final void updateInputLayoutMargins() {
        if (this.boxBackgroundMode != 1) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.inputFrame.getLayoutParams();
            int calculateLabelMarginTop = calculateLabelMarginTop();
            if (calculateLabelMarginTop != layoutParams.topMargin) {
                layoutParams.topMargin = calculateLabelMarginTop;
                this.inputFrame.requestLayout();
            }
        }
    }

    private final void updatePlaceholderText() {
        EditText editText = this.editText;
        updatePlaceholderText(editText == null ? null : editText.getText());
    }

    private final void updateStrokeErrorColor(boolean z, boolean z2) {
        int defaultColor = this.strokeErrorColor.getDefaultColor();
        int colorForState = this.strokeErrorColor.getColorForState(new int[]{R.attr.state_hovered, R.attr.state_enabled}, defaultColor);
        int colorForState2 = this.strokeErrorColor.getColorForState(new int[]{R.attr.state_activated, R.attr.state_enabled}, defaultColor);
        if (z) {
            defaultColor = colorForState2;
        } else if (z2) {
            defaultColor = colorForState;
        }
        this.boxStrokeColor = defaultColor;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (view instanceof EditText) {
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(layoutParams);
            layoutParams2.gravity = (layoutParams2.gravity & NetError.ERR_SSL_VERSION_OR_CIPHER_MISMATCH) | 16;
            this.inputFrame.addView(view, layoutParams2);
            this.inputFrame.setLayoutParams(layoutParams);
            updateInputLayoutMargins();
            EditText editText = (EditText) view;
            if (this.editText == null) {
                int i2 = this.endLayout.endIconMode;
                this.editText = editText;
                int i3 = this.minEms;
                if (i3 != -1) {
                    setMinEms(i3);
                } else {
                    setMinWidth(this.minWidth);
                }
                int i4 = this.maxEms;
                if (i4 != -1) {
                    setMaxEms(i4);
                } else {
                    setMaxWidth(this.maxWidth);
                }
                this.boxBackgroundApplied = false;
                onApplyBoxBackgroundMode();
                AccessibilityDelegate accessibilityDelegate = new AccessibilityDelegate(this);
                EditText editText2 = this.editText;
                if (editText2 != null) {
                    ViewCompat.setAccessibilityDelegate(editText2, accessibilityDelegate);
                }
                CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
                Typeface typeface = this.editText.getTypeface();
                boolean collapsedTypefaceInternal = collapsingTextHelper.setCollapsedTypefaceInternal(typeface);
                boolean expandedTypefaceInternal = collapsingTextHelper.setExpandedTypefaceInternal(typeface);
                if (collapsedTypefaceInternal || expandedTypefaceInternal) {
                    collapsingTextHelper.recalculate();
                }
                this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
                CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
                float letterSpacing = this.editText.getLetterSpacing();
                if (collapsingTextHelper2.expandedLetterSpacing != letterSpacing) {
                    collapsingTextHelper2.expandedLetterSpacing = letterSpacing;
                    collapsingTextHelper2.recalculate();
                }
                int gravity = this.editText.getGravity();
                this.collapsingTextHelper.setCollapsedTextGravity((gravity & NetError.ERR_SSL_VERSION_OR_CIPHER_MISMATCH) | 48);
                this.collapsingTextHelper.setExpandedTextGravity(gravity);
                this.originalEditTextMinimumHeight = editText.getMinimumHeight();
                this.editText.addTextChangedListener(new TextWatcher(this, editText) { // from class: com.google.android.material.textfield.TextInputLayout.1
                    int previousLineCount;
                    final /* synthetic */ TextInputLayout this$0;
                    final /* synthetic */ EditText val$editText;

                    {
                        this.val$editText = editText;
                        this.this$0 = this;
                        this.previousLineCount = editText.getLineCount();
                    }

                    @Override // android.text.TextWatcher
                    public final void afterTextChanged(Editable editable) {
                        this.this$0.updateLabelState(!r0.restoringSavedState);
                        TextInputLayout textInputLayout = this.this$0;
                        if (textInputLayout.counterEnabled) {
                            textInputLayout.updateCounter(editable);
                        }
                        TextInputLayout textInputLayout2 = this.this$0;
                        if (textInputLayout2.placeholderEnabled) {
                            textInputLayout2.updatePlaceholderText(editable);
                        }
                        int lineCount = this.val$editText.getLineCount();
                        int i5 = this.previousLineCount;
                        if (lineCount != i5) {
                            if (lineCount < i5) {
                                EditText editText3 = this.val$editText;
                                TextInputLayout textInputLayout3 = this.this$0;
                                int minimumHeight = editText3.getMinimumHeight();
                                int i6 = textInputLayout3.originalEditTextMinimumHeight;
                                if (minimumHeight != i6) {
                                    this.val$editText.setMinimumHeight(i6);
                                }
                            }
                            this.previousLineCount = lineCount;
                        }
                    }

                    @Override // android.text.TextWatcher
                    public final void beforeTextChanged(CharSequence charSequence, int i5, int i6, int i7) {
                    }

                    @Override // android.text.TextWatcher
                    public final void onTextChanged(CharSequence charSequence, int i5, int i6, int i7) {
                    }
                });
                if (this.defaultHintTextColor == null) {
                    this.defaultHintTextColor = this.editText.getHintTextColors();
                }
                if (this.hintEnabled) {
                    if (TextUtils.isEmpty(this.hint)) {
                        CharSequence hint = this.editText.getHint();
                        this.originalHint = hint;
                        setHint(hint);
                        this.editText.setHint((CharSequence) null);
                    }
                    this.isProvidingHint = true;
                }
                if (Build.VERSION.SDK_INT >= 29) {
                    updateCursorColor();
                }
                if (this.counterView != null) {
                    updateCounter(this.editText.getText());
                }
                updateEditTextBackground();
                this.indicatorViewController.adjustIndicatorPadding();
                this.startLayout.bringToFront();
                this.endLayout.bringToFront();
                Iterator it = this.editTextAttachedListeners.iterator();
                while (it.hasNext()) {
                    ((RetryingNameResolver.ResolutionResultListener) it.next()).onEditTextAttached(this);
                }
                this.endLayout.updateSuffixTextViewPadding();
                if (!isEnabled()) {
                    editText.setEnabled(false);
                }
                updateLabelState(false, true);
                return;
            }
            throw new IllegalArgumentException("We already have an EditText, can only have one");
        }
        super.addView(view, i, layoutParams);
    }

    final void animateToExpansionFraction(float f) {
        if (this.collapsingTextHelper.expandedFraction == f) {
            return;
        }
        if (this.animator == null) {
            ValueAnimator valueAnimator = new ValueAnimator();
            this.animator = valueAnimator;
            valueAnimator.setInterpolator(DrawableUtils$OutlineCompatL.resolveThemeInterpolator(getContext(), com.google.android.marvin.talkback.R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
            this.animator.setDuration(DrawableUtils$OutlineCompatR.resolveInteger(getContext(), com.google.android.marvin.talkback.R.attr.motionDurationMedium4, 167));
            this.animator.addUpdateListener(new BottomSheetBehavior.AnonymousClass3(this, 6));
        }
        this.animator.setFloatValues(this.collapsingTextHelper.expandedFraction, f);
        this.animator.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void dispatchProvideAutofillStructure(ViewStructure viewStructure, int i) {
        AutofillId autofillId;
        EditText editText = this.editText;
        if (editText == null) {
            super.dispatchProvideAutofillStructure(viewStructure, i);
            return;
        }
        if (this.originalHint != null) {
            boolean z = this.isProvidingHint;
            this.isProvidingHint = false;
            CharSequence hint = editText.getHint();
            this.editText.setHint(this.originalHint);
            try {
                super.dispatchProvideAutofillStructure(viewStructure, i);
                return;
            } finally {
                this.editText.setHint(hint);
                this.isProvidingHint = z;
            }
        }
        autofillId = getAutofillId();
        viewStructure.setAutofillId(autofillId);
        onProvideAutofillStructure(viewStructure, i);
        onProvideAutofillVirtualStructure(viewStructure, i);
        viewStructure.setChildCount(this.inputFrame.getChildCount());
        for (int i2 = 0; i2 < this.inputFrame.getChildCount(); i2++) {
            View childAt = this.inputFrame.getChildAt(i2);
            ViewStructure newChild = viewStructure.newChild(i2);
            childAt.dispatchProvideAutofillStructure(newChild, i);
            if (childAt == this.editText) {
                newChild.setHint(getHint());
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void dispatchRestoreInstanceState(SparseArray sparseArray) {
        this.restoringSavedState = true;
        super.dispatchRestoreInstanceState(sparseArray);
        this.restoringSavedState = false;
    }

    @Override // android.view.View
    public final void draw(Canvas canvas) {
        MaterialShapeDrawable materialShapeDrawable;
        super.draw(canvas);
        if (this.hintEnabled) {
            this.collapsingTextHelper.draw(canvas);
        }
        if (this.boxUnderlineFocused != null && (materialShapeDrawable = this.boxUnderlineDefault) != null) {
            materialShapeDrawable.draw(canvas);
            if (this.editText.isFocused()) {
                Rect bounds = this.boxUnderlineFocused.getBounds();
                Rect bounds2 = this.boxUnderlineDefault.getBounds();
                float f = this.collapsingTextHelper.expandedFraction;
                int centerX = bounds2.centerX();
                bounds.left = AnimationUtils.lerp(centerX, bounds2.left, f);
                bounds.right = AnimationUtils.lerp(centerX, bounds2.right, f);
                this.boxUnderlineFocused.draw(canvas);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected final void drawableStateChanged() {
        boolean z;
        if (this.inDrawableStateChanged) {
            return;
        }
        boolean z2 = true;
        this.inDrawableStateChanged = true;
        super.drawableStateChanged();
        int[] drawableState = getDrawableState();
        CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
        if (collapsingTextHelper != null) {
            z = collapsingTextHelper.setState(drawableState);
        } else {
            z = false;
        }
        if (this.editText != null) {
            if (!isLaidOut() || !isEnabled()) {
                z2 = false;
            }
            updateLabelState(z2);
        }
        updateEditTextBackground();
        updateTextInputBoxState();
        if (z) {
            invalidate();
        }
        this.inDrawableStateChanged = false;
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final int getBaseline() {
        EditText editText = this.editText;
        if (editText != null) {
            return editText.getBaseline() + getPaddingTop() + calculateLabelMarginTop();
        }
        return super.getBaseline();
    }

    public final CharSequence getError() {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        if (indicatorViewController.errorEnabled) {
            return indicatorViewController.errorText;
        }
        return null;
    }

    public final int getErrorCurrentTextColors() {
        TextView textView = this.indicatorViewController.errorView;
        if (textView != null) {
            return textView.getCurrentTextColor();
        }
        return -1;
    }

    public final CharSequence getHint() {
        if (this.hintEnabled) {
            return this.hint;
        }
        return null;
    }

    public final CharSequence getPrefixText() {
        return this.startLayout.prefixText;
    }

    public final CharSequence getSuffixText() {
        return this.endLayout.suffixText;
    }

    public final boolean isHelperTextEnabled() {
        return this.indicatorViewController.helperTextEnabled;
    }

    @Override // android.view.View
    protected final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.collapsingTextHelper.maybeUpdateFontWeightAdjustment(configuration);
    }

    @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
    public final void onGlobalLayout() {
        this.endLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
        boolean z = false;
        this.globalLayoutListenerAdded = false;
        if (this.editText != null) {
            int max = Math.max(this.endLayout.getMeasuredHeight(), this.startLayout.getMeasuredHeight());
            if (this.editText.getMeasuredHeight() < max) {
                this.editText.setMinimumHeight(max);
                z = true;
            }
        }
        boolean updateDummyDrawables = updateDummyDrawables();
        if (!z && !updateDummyDrawables) {
            return;
        }
        this.editText.post(new FlagStore$$ExternalSyntheticLambda3(this, 17));
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    protected final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int compoundPaddingTop;
        int compoundPaddingBottom;
        super.onLayout(z, i, i2, i3, i4);
        EditText editText = this.editText;
        if (editText != null) {
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(this, editText, rect);
            if (this.boxUnderlineDefault != null) {
                this.boxUnderlineDefault.setBounds(rect.left, rect.bottom - this.boxStrokeWidthDefaultPx, rect.right, rect.bottom);
            }
            if (this.boxUnderlineFocused != null) {
                this.boxUnderlineFocused.setBounds(rect.left, rect.bottom - this.boxStrokeWidthFocusedPx, rect.right, rect.bottom);
            }
            if (this.hintEnabled) {
                this.collapsingTextHelper.setExpandedTextSize(this.editText.getTextSize());
                int gravity = this.editText.getGravity();
                this.collapsingTextHelper.setCollapsedTextGravity((gravity & NetError.ERR_SSL_VERSION_OR_CIPHER_MISMATCH) | 48);
                this.collapsingTextHelper.setExpandedTextGravity(gravity);
                CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
                if (this.editText != null) {
                    Rect rect2 = this.tmpBoundsRect;
                    boolean isLayoutRtl = DrawableUtils$OutlineCompatL.isLayoutRtl(this);
                    rect2.bottom = rect.bottom;
                    int i5 = this.boxBackgroundMode;
                    if (i5 != 1) {
                        if (i5 != 2) {
                            rect2.left = getLabelLeftBoundAlignedWithPrefixAndSuffix(rect.left, isLayoutRtl);
                            rect2.top = getPaddingTop();
                            rect2.right = getLabelRightBoundAlignedWithPrefixAndSuffix(rect.right, isLayoutRtl);
                        } else {
                            rect2.left = rect.left + this.editText.getPaddingLeft();
                            rect2.top = rect.top - calculateLabelMarginTop();
                            rect2.right = rect.right - this.editText.getPaddingRight();
                        }
                    } else {
                        rect2.left = getLabelLeftBoundAlignedWithPrefixAndSuffix(rect.left, isLayoutRtl);
                        rect2.top = rect.top + this.boxCollapsedPaddingTopPx;
                        rect2.right = getLabelRightBoundAlignedWithPrefixAndSuffix(rect.right, isLayoutRtl);
                    }
                    collapsingTextHelper.setCollapsedBounds(rect2.left, rect2.top, rect2.right, rect2.bottom);
                    CollapsingTextHelper collapsingTextHelper2 = this.collapsingTextHelper;
                    if (this.editText != null) {
                        Rect rect3 = this.tmpBoundsRect;
                        collapsingTextHelper2.getTextPaintExpanded(collapsingTextHelper2.tmpPaint);
                        float f = -collapsingTextHelper2.tmpPaint.ascent();
                        rect3.left = rect.left + this.editText.getCompoundPaddingLeft();
                        if (isSingleLineFilledTextField()) {
                            compoundPaddingTop = (int) (rect.centerY() - (f / 2.0f));
                        } else {
                            compoundPaddingTop = rect.top + this.editText.getCompoundPaddingTop();
                        }
                        rect3.top = compoundPaddingTop;
                        rect3.right = rect.right - this.editText.getCompoundPaddingRight();
                        if (isSingleLineFilledTextField()) {
                            compoundPaddingBottom = (int) (rect3.top + f);
                        } else {
                            compoundPaddingBottom = rect.bottom - this.editText.getCompoundPaddingBottom();
                        }
                        rect3.bottom = compoundPaddingBottom;
                        collapsingTextHelper2.setExpandedBounds(rect3.left, rect3.top, rect3.right, rect3.bottom);
                        this.collapsingTextHelper.recalculate();
                        if (cutoutEnabled() && !this.hintExpanded) {
                            openCutout();
                            return;
                        }
                        return;
                    }
                    throw new IllegalStateException();
                }
                throw new IllegalStateException();
            }
        }
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        EditText editText;
        super.onMeasure(i, i2);
        if (!this.globalLayoutListenerAdded) {
            this.endLayout.getViewTreeObserver().addOnGlobalLayoutListener(this);
            this.globalLayoutListenerAdded = true;
        }
        if (this.placeholderTextView != null && (editText = this.editText) != null) {
            this.placeholderTextView.setGravity(editText.getGravity());
            this.placeholderTextView.setPadding(this.editText.getCompoundPaddingLeft(), this.editText.getCompoundPaddingTop(), this.editText.getCompoundPaddingRight(), this.editText.getCompoundPaddingBottom());
        }
        this.endLayout.updateSuffixTextViewPadding();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x004f  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected final void onRestoreInstanceState(android.os.Parcelable r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.google.android.material.textfield.TextInputLayout.SavedState
            if (r0 != 0) goto L8
            super.onRestoreInstanceState(r6)
            return
        L8:
            com.google.android.material.textfield.TextInputLayout$SavedState r6 = (com.google.android.material.textfield.TextInputLayout.SavedState) r6
            android.os.Parcelable r0 = r6.mSuperState
            super.onRestoreInstanceState(r0)
            java.lang.CharSequence r0 = r6.error
            com.google.android.material.textfield.IndicatorViewController r1 = r5.indicatorViewController
            boolean r1 = r1.errorEnabled
            r2 = 1
            if (r1 != 0) goto L22
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 == 0) goto L1f
            goto L4b
        L1f:
            r5.setErrorEnabled(r2)
        L22:
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L46
            com.google.android.material.textfield.IndicatorViewController r1 = r5.indicatorViewController
            r1.cancelCaptionAnimator()
            r1.errorText = r0
            android.widget.TextView r3 = r1.errorView
            r3.setText(r0)
            int r3 = r1.captionDisplayed
            if (r3 == r2) goto L3a
            r1.captionToShow = r2
        L3a:
            int r2 = r1.captionToShow
            android.widget.TextView r4 = r1.errorView
            boolean r0 = r1.shouldAnimateCaptionView(r4, r0)
            r1.updateCaptionViewsVisibility(r3, r2, r0)
            goto L4b
        L46:
            com.google.android.material.textfield.IndicatorViewController r0 = r5.indicatorViewController
            r0.hideError()
        L4b:
            boolean r6 = r6.isEndIconChecked
            if (r6 == 0) goto L5a
            com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3 r6 = new com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3
            r0 = 18
            r1 = 0
            r6.<init>(r5, r0, r1)
            r5.post(r6)
        L5a:
            r5.requestLayout()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.onRestoreInstanceState(android.os.Parcelable):void");
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onRtlPropertiesChanged(int i) {
        super.onRtlPropertiesChanged(i);
        boolean z = true;
        if (i != 1) {
            z = false;
        }
        if (z != this.areCornerRadiiRtl) {
            float cornerSize = this.shapeAppearanceModel.topLeftCornerSize.getCornerSize(this.tmpRectF);
            float cornerSize2 = this.shapeAppearanceModel.topRightCornerSize.getCornerSize(this.tmpRectF);
            float cornerSize3 = this.shapeAppearanceModel.bottomLeftCornerSize.getCornerSize(this.tmpRectF);
            float cornerSize4 = this.shapeAppearanceModel.bottomRightCornerSize.getCornerSize(this.tmpRectF);
            ShapeAppearanceModel shapeAppearanceModel = this.shapeAppearanceModel;
            EdgeTreatment edgeTreatment = shapeAppearanceModel.topLeftCorner$ar$class_merging;
            EdgeTreatment edgeTreatment2 = shapeAppearanceModel.topRightCorner$ar$class_merging;
            EdgeTreatment edgeTreatment3 = shapeAppearanceModel.bottomLeftCorner$ar$class_merging;
            EdgeTreatment edgeTreatment4 = shapeAppearanceModel.bottomRightCorner$ar$class_merging;
            ShapeAppearanceModel.Builder builder = new ShapeAppearanceModel.Builder();
            builder.setTopLeftCorner$ar$ds$ar$class_merging(edgeTreatment2);
            builder.setTopRightCorner$ar$ds$ar$class_merging(edgeTreatment);
            builder.setBottomLeftCorner$ar$ds$ar$class_merging(edgeTreatment4);
            builder.setBottomRightCorner$ar$ds$ar$class_merging(edgeTreatment3);
            builder.setTopLeftCornerSize$ar$ds(cornerSize2);
            builder.setTopRightCornerSize$ar$ds(cornerSize);
            builder.setBottomLeftCornerSize$ar$ds(cornerSize4);
            builder.setBottomRightCornerSize$ar$ds(cornerSize3);
            ShapeAppearanceModel shapeAppearanceModel2 = new ShapeAppearanceModel(builder);
            this.areCornerRadiiRtl = z;
            MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
            if (materialShapeDrawable != null && materialShapeDrawable.getShapeAppearanceModel() != shapeAppearanceModel2) {
                this.shapeAppearanceModel = shapeAppearanceModel2;
                applyBoxAttributes();
            }
        }
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        if (shouldShowError()) {
            savedState.error = getError();
        }
        EndCompoundLayout endCompoundLayout = this.endLayout;
        boolean z = false;
        if (endCompoundLayout.hasEndIcon() && endCompoundLayout.endIconView.checked) {
            z = true;
        }
        savedState.isEndIconChecked = z;
        return savedState;
    }

    @Override // android.view.View
    public final void setEnabled(boolean z) {
        recursiveSetEnabled(this, z);
        super.setEnabled(z);
    }

    public final void setEndIconVisible(boolean z) {
        this.endLayout.setEndIconVisible(z);
    }

    public final void setErrorEnabled(boolean z) {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        if (indicatorViewController.errorEnabled == z) {
            return;
        }
        indicatorViewController.cancelCaptionAnimator();
        if (z) {
            indicatorViewController.errorView = new AppCompatTextView(indicatorViewController.context);
            indicatorViewController.errorView.setId(com.google.android.marvin.talkback.R.id.textinput_error);
            indicatorViewController.errorView.setTextAlignment(5);
            indicatorViewController.setErrorTextAppearance(indicatorViewController.errorTextAppearance);
            indicatorViewController.setErrorViewTextColor(indicatorViewController.errorViewTextColor);
            indicatorViewController.setErrorContentDescription(indicatorViewController.errorViewContentDescription);
            indicatorViewController.setErrorAccessibilityLiveRegion(indicatorViewController.errorViewAccessibilityLiveRegion);
            indicatorViewController.errorView.setVisibility(4);
            indicatorViewController.addIndicator(indicatorViewController.errorView, 0);
        } else {
            indicatorViewController.hideError();
            indicatorViewController.removeIndicator(indicatorViewController.errorView, 0);
            indicatorViewController.errorView = null;
            indicatorViewController.textInputView.updateEditTextBackground();
            indicatorViewController.textInputView.updateTextInputBoxState();
        }
        indicatorViewController.errorEnabled = z;
    }

    public final void setHelperTextEnabled(boolean z) {
        final IndicatorViewController indicatorViewController = this.indicatorViewController;
        if (indicatorViewController.helperTextEnabled == z) {
            return;
        }
        indicatorViewController.cancelCaptionAnimator();
        if (z) {
            indicatorViewController.helperTextView = new AppCompatTextView(indicatorViewController.context);
            indicatorViewController.helperTextView.setId(com.google.android.marvin.talkback.R.id.textinput_helper_text);
            indicatorViewController.helperTextView.setTextAlignment(5);
            indicatorViewController.helperTextView.setVisibility(4);
            indicatorViewController.helperTextView.setAccessibilityLiveRegion(1);
            indicatorViewController.setHelperTextAppearance(indicatorViewController.helperTextTextAppearance);
            indicatorViewController.setHelperTextViewTextColor(indicatorViewController.helperTextViewTextColor);
            indicatorViewController.addIndicator(indicatorViewController.helperTextView, 1);
            indicatorViewController.helperTextView.setAccessibilityDelegate(new View.AccessibilityDelegate() { // from class: com.google.android.material.textfield.IndicatorViewController.2
                @Override // android.view.View.AccessibilityDelegate
                public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                    super.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                    EditText editText = IndicatorViewController.this.textInputView.editText;
                    if (editText != null) {
                        accessibilityNodeInfo.setLabeledBy(editText);
                    }
                }
            });
        } else {
            indicatorViewController.cancelCaptionAnimator();
            int i = indicatorViewController.captionDisplayed;
            if (i == 2) {
                indicatorViewController.captionToShow = 0;
            }
            indicatorViewController.updateCaptionViewsVisibility(i, indicatorViewController.captionToShow, indicatorViewController.shouldAnimateCaptionView(indicatorViewController.helperTextView, ""));
            indicatorViewController.removeIndicator(indicatorViewController.helperTextView, 1);
            indicatorViewController.helperTextView = null;
            indicatorViewController.textInputView.updateEditTextBackground();
            indicatorViewController.textInputView.updateTextInputBoxState();
        }
        indicatorViewController.helperTextEnabled = z;
    }

    public final void setHint(CharSequence charSequence) {
        if (this.hintEnabled) {
            if (!TextUtils.equals(charSequence, this.hint)) {
                this.hint = charSequence;
                this.collapsingTextHelper.setText(charSequence);
                if (!this.hintExpanded) {
                    openCutout();
                }
            }
            sendAccessibilityEvent(2048);
        }
    }

    public final void setMaxEms(int i) {
        this.maxEms = i;
        EditText editText = this.editText;
        if (editText != null && i != -1) {
            editText.setMaxEms(i);
        }
    }

    public final void setMaxWidth(int i) {
        this.maxWidth = i;
        EditText editText = this.editText;
        if (editText != null && i != -1) {
            editText.setMaxWidth(i);
        }
    }

    public final void setMinEms(int i) {
        this.minEms = i;
        EditText editText = this.editText;
        if (editText != null && i != -1) {
            editText.setMinEms(i);
        }
    }

    public final void setMinWidth(int i) {
        this.minWidth = i;
        EditText editText = this.editText;
        if (editText != null && i != -1) {
            editText.setMinWidth(i);
        }
    }

    public final void setPlaceholderTextAppearance(int i) {
        this.placeholderTextAppearance = i;
        TextView textView = this.placeholderTextView;
        if (textView != null) {
            textView.setTextAppearance(i);
        }
    }

    public final void setPlaceholderTextColor(ColorStateList colorStateList) {
        if (this.placeholderTextColor != colorStateList) {
            this.placeholderTextColor = colorStateList;
            TextView textView = this.placeholderTextView;
            if (textView != null && colorStateList != null) {
                textView.setTextColor(colorStateList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setTextAppearanceCompatWithErrorFallback(TextView textView, int i) {
        try {
            textView.setTextAppearance(i);
            if (textView.getTextColors().getDefaultColor() != -65281) {
                return;
            }
        } catch (Exception unused) {
        }
        textView.setTextAppearance(com.google.android.marvin.talkback.R.style.TextAppearance_AppCompat_Caption);
        textView.setTextColor(ContextCompat$Api23Impl.getColor(getContext(), com.google.android.marvin.talkback.R.color.design_error));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean shouldShowError() {
        IndicatorViewController indicatorViewController = this.indicatorViewController;
        if (indicatorViewController.captionToShow == 1 && indicatorViewController.errorView != null && !TextUtils.isEmpty(indicatorViewController.errorText)) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean updateDummyDrawables() {
        boolean z;
        if (this.editText == null) {
            return false;
        }
        CheckableImageButton checkableImageButton = null;
        boolean z2 = true;
        if ((this.startLayout.startIconView.getDrawable() != null || (getPrefixText() != null && this.startLayout.prefixTextView.getVisibility() == 0)) && this.startLayout.getMeasuredWidth() > 0) {
            int measuredWidth = this.startLayout.getMeasuredWidth() - this.editText.getPaddingLeft();
            if (this.startDummyDrawable == null || this.startDummyDrawableWidth != measuredWidth) {
                ColorDrawable colorDrawable = new ColorDrawable();
                this.startDummyDrawable = colorDrawable;
                this.startDummyDrawableWidth = measuredWidth;
                colorDrawable.setBounds(0, 0, measuredWidth, 1);
            }
            Drawable[] compoundDrawablesRelative = this.editText.getCompoundDrawablesRelative();
            Drawable drawable = compoundDrawablesRelative[0];
            Drawable drawable2 = this.startDummyDrawable;
            if (drawable != drawable2) {
                this.editText.setCompoundDrawablesRelative(drawable2, compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
                z = true;
            }
            z = false;
        } else {
            if (this.startDummyDrawable != null) {
                Drawable[] compoundDrawablesRelative2 = this.editText.getCompoundDrawablesRelative();
                this.editText.setCompoundDrawablesRelative(null, compoundDrawablesRelative2[1], compoundDrawablesRelative2[2], compoundDrawablesRelative2[3]);
                this.startDummyDrawable = null;
                z = true;
            }
            z = false;
        }
        if ((this.endLayout.isErrorIconVisible() || ((this.endLayout.hasEndIcon() && this.endLayout.isEndIconVisible()) || this.endLayout.suffixText != null)) && this.endLayout.getMeasuredWidth() > 0) {
            int measuredWidth2 = this.endLayout.suffixTextView.getMeasuredWidth() - this.editText.getPaddingRight();
            EndCompoundLayout endCompoundLayout = this.endLayout;
            if (endCompoundLayout.isErrorIconVisible()) {
                checkableImageButton = endCompoundLayout.errorIconView;
            } else if (endCompoundLayout.hasEndIcon() && endCompoundLayout.isEndIconVisible()) {
                checkableImageButton = endCompoundLayout.endIconView;
            }
            if (checkableImageButton != null) {
                measuredWidth2 = measuredWidth2 + checkableImageButton.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).getMarginStart();
            }
            Drawable[] compoundDrawablesRelative3 = this.editText.getCompoundDrawablesRelative();
            Drawable drawable3 = this.endDummyDrawable;
            if (drawable3 != null && this.endDummyDrawableWidth != measuredWidth2) {
                this.endDummyDrawableWidth = measuredWidth2;
                drawable3.setBounds(0, 0, measuredWidth2, 1);
                this.editText.setCompoundDrawablesRelative(compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], this.endDummyDrawable, compoundDrawablesRelative3[3]);
                return true;
            }
            if (drawable3 == null) {
                ColorDrawable colorDrawable2 = new ColorDrawable();
                this.endDummyDrawable = colorDrawable2;
                this.endDummyDrawableWidth = measuredWidth2;
                colorDrawable2.setBounds(0, 0, measuredWidth2, 1);
            }
            Drawable drawable4 = compoundDrawablesRelative3[2];
            Drawable drawable5 = this.endDummyDrawable;
            if (drawable4 != drawable5) {
                this.originalEditTextEndDrawable = drawable4;
                this.editText.setCompoundDrawablesRelative(compoundDrawablesRelative3[0], compoundDrawablesRelative3[1], drawable5, compoundDrawablesRelative3[3]);
                return true;
            }
        } else if (this.endDummyDrawable != null) {
            Drawable[] compoundDrawablesRelative4 = this.editText.getCompoundDrawablesRelative();
            if (compoundDrawablesRelative4[2] == this.endDummyDrawable) {
                this.editText.setCompoundDrawablesRelative(compoundDrawablesRelative4[0], compoundDrawablesRelative4[1], this.originalEditTextEndDrawable, compoundDrawablesRelative4[3]);
            } else {
                z2 = z;
            }
            this.endDummyDrawable = null;
            return z2;
        }
        return z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateEditTextBackground() {
        Drawable background;
        TextView textView;
        EditText editText = this.editText;
        if (editText != null && this.boxBackgroundMode == 0 && (background = editText.getBackground()) != null) {
            Rect rect = DrawableUtils.INSETS_NONE;
            Drawable mutate = background.mutate();
            if (shouldShowError()) {
                mutate.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(getErrorCurrentTextColors(), PorterDuff.Mode.SRC_IN));
            } else if (this.counterOverflowed && (textView = this.counterView) != null) {
                mutate.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(textView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
            } else {
                mutate.clearColorFilter();
                this.editText.refreshDrawableState();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateEditTextBoxBackgroundIfNeeded() {
        Drawable drawable;
        EditText editText = this.editText;
        if (editText != null && this.boxBackground != null) {
            if ((this.boxBackgroundApplied || editText.getBackground() == null) && this.boxBackgroundMode != 0) {
                EditText editText2 = this.editText;
                if ((editText2 instanceof AutoCompleteTextView) && !EdgeTreatment.isEditable(editText2)) {
                    int color = FileUtils.getColor(this.editText, com.google.android.marvin.talkback.R.attr.colorControlHighlight);
                    int i = this.boxBackgroundMode;
                    if (i == 2) {
                        Context context = getContext();
                        MaterialShapeDrawable materialShapeDrawable = this.boxBackground;
                        int[][] iArr = EDIT_TEXT_BACKGROUND_RIPPLE_STATE;
                        int color2 = FileUtils.getColor(context, com.google.android.marvin.talkback.R.attr.colorSurface, "TextInputLayout");
                        MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(materialShapeDrawable.getShapeAppearanceModel());
                        int layer = FileUtils.layer(color, color2, 0.1f);
                        materialShapeDrawable2.setFillColor(new ColorStateList(iArr, new int[]{layer, 0}));
                        materialShapeDrawable2.setTint(color2);
                        ColorStateList colorStateList = new ColorStateList(iArr, new int[]{layer, color2});
                        MaterialShapeDrawable materialShapeDrawable3 = new MaterialShapeDrawable(materialShapeDrawable.getShapeAppearanceModel());
                        materialShapeDrawable3.setTint(-1);
                        drawable = new LayerDrawable(new Drawable[]{new RippleDrawable(colorStateList, materialShapeDrawable2, materialShapeDrawable3), materialShapeDrawable});
                    } else if (i == 1) {
                        MaterialShapeDrawable materialShapeDrawable4 = this.boxBackground;
                        int i2 = this.boxBackgroundColor;
                        drawable = new RippleDrawable(new ColorStateList(EDIT_TEXT_BACKGROUND_RIPPLE_STATE, new int[]{FileUtils.layer(color, i2, 0.1f), i2}), materialShapeDrawable4, materialShapeDrawable4);
                    } else {
                        drawable = null;
                    }
                } else {
                    drawable = this.boxBackground;
                }
                this.editText.setBackground(drawable);
                this.boxBackgroundApplied = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateLabelState(boolean z) {
        updateLabelState(z, false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void updateTextInputBoxState() {
        boolean z;
        int i;
        TextView textView;
        int i2;
        int i3;
        EditText editText;
        EditText editText2;
        if (this.boxBackground != null && this.boxBackgroundMode != 0) {
            boolean z2 = false;
            if (!isFocused() && ((editText2 = this.editText) == null || !editText2.hasFocus())) {
                z = false;
            } else {
                z = true;
            }
            if (isHovered() || ((editText = this.editText) != null && editText.isHovered())) {
                z2 = true;
            }
            if (!isEnabled()) {
                this.boxStrokeColor = this.disabledColor;
            } else if (shouldShowError()) {
                if (this.strokeErrorColor != null) {
                    updateStrokeErrorColor(z, z2);
                } else {
                    this.boxStrokeColor = getErrorCurrentTextColors();
                }
            } else {
                if (this.counterOverflowed && (textView = this.counterView) != null) {
                    if (this.strokeErrorColor != null) {
                        updateStrokeErrorColor(z, z2);
                    } else {
                        i = textView.getCurrentTextColor();
                    }
                } else if (z) {
                    i = this.focusedStrokeColor;
                } else if (z2) {
                    i = this.hoveredStrokeColor;
                } else {
                    i = this.defaultStrokeColor;
                }
                this.boxStrokeColor = i;
            }
            if (Build.VERSION.SDK_INT >= 29) {
                updateCursorColor();
            }
            EndCompoundLayout endCompoundLayout = this.endLayout;
            endCompoundLayout.updateErrorIconVisibility();
            EdgeTreatment.refreshIconDrawableState(endCompoundLayout.textInputLayout, endCompoundLayout.errorIconView, endCompoundLayout.errorIconTintList);
            endCompoundLayout.refreshEndIconDrawableState();
            if (endCompoundLayout.getEndIconDelegate().shouldTintIconOnError()) {
                if (endCompoundLayout.textInputLayout.shouldShowError() && endCompoundLayout.getEndIconDrawable() != null) {
                    Drawable mutate = endCompoundLayout.getEndIconDrawable().mutate();
                    DrawableCompat$Api21Impl.setTint(mutate, endCompoundLayout.textInputLayout.getErrorCurrentTextColors());
                    endCompoundLayout.endIconView.setImageDrawable(mutate);
                } else {
                    EdgeTreatment.applyIconTint(endCompoundLayout.textInputLayout, endCompoundLayout.endIconView, endCompoundLayout.endIconTintList, endCompoundLayout.endIconTintMode);
                }
            }
            this.startLayout.refreshStartIconDrawableState();
            if (this.boxBackgroundMode == 2) {
                int i4 = this.boxStrokeWidthPx;
                if (z && isEnabled()) {
                    i3 = this.boxStrokeWidthFocusedPx;
                    this.boxStrokeWidthPx = i3;
                } else {
                    i3 = this.boxStrokeWidthDefaultPx;
                    this.boxStrokeWidthPx = i3;
                }
                if (i3 != i4 && cutoutEnabled() && !this.hintExpanded) {
                    closeCutout();
                    openCutout();
                }
            }
            if (this.boxBackgroundMode == 1) {
                if (!isEnabled()) {
                    this.boxBackgroundColor = this.disabledFilledBackgroundColor;
                } else {
                    if (z2 && !z) {
                        i2 = this.hoveredFilledBackgroundColor;
                    } else if (z) {
                        i2 = this.focusedFilledBackgroundColor;
                    } else {
                        i2 = this.defaultFilledBackgroundColor;
                    }
                    this.boxBackgroundColor = i2;
                }
            }
            applyBoxAttributes();
        }
    }

    public TextInputLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, com.google.android.marvin.talkback.R.attr.textInputStyle);
    }

    private final void updateLabelState(boolean z, boolean z2) {
        ColorStateList colorStateList;
        TextView textView;
        int i;
        boolean isEnabled = isEnabled();
        EditText editText = this.editText;
        boolean z3 = (editText == null || TextUtils.isEmpty(editText.getText())) ? false : true;
        EditText editText2 = this.editText;
        boolean z4 = editText2 != null && editText2.hasFocus();
        ColorStateList colorStateList2 = this.defaultHintTextColor;
        if (colorStateList2 != null) {
            this.collapsingTextHelper.setCollapsedAndExpandedTextColor(colorStateList2);
        }
        if (!isEnabled) {
            ColorStateList colorStateList3 = this.defaultHintTextColor;
            if (colorStateList3 != null) {
                i = colorStateList3.getColorForState(new int[]{-16842910}, this.disabledColor);
            } else {
                i = this.disabledColor;
            }
            this.collapsingTextHelper.setCollapsedAndExpandedTextColor(ColorStateList.valueOf(i));
        } else if (shouldShowError()) {
            CollapsingTextHelper collapsingTextHelper = this.collapsingTextHelper;
            TextView textView2 = this.indicatorViewController.errorView;
            collapsingTextHelper.setCollapsedAndExpandedTextColor(textView2 != null ? textView2.getTextColors() : null);
        } else if (this.counterOverflowed && (textView = this.counterView) != null) {
            this.collapsingTextHelper.setCollapsedAndExpandedTextColor(textView.getTextColors());
        } else if (z4 && (colorStateList = this.focusedTextColor) != null) {
            this.collapsingTextHelper.setCollapsedTextColor(colorStateList);
        }
        if (!z3 && this.expandedHintEnabled && (!isEnabled() || !z4)) {
            if (z2 || !this.hintExpanded) {
                ValueAnimator valueAnimator = this.animator;
                if (valueAnimator != null && valueAnimator.isRunning()) {
                    this.animator.cancel();
                }
                if (z && this.hintAnimationEnabled) {
                    animateToExpansionFraction(0.0f);
                } else {
                    this.collapsingTextHelper.setExpansionFraction(0.0f);
                }
                if (cutoutEnabled() && !((CutoutDrawable) this.boxBackground).drawableState.cutoutBounds.isEmpty()) {
                    closeCutout();
                }
                this.hintExpanded = true;
                hidePlaceholderText();
                this.startLayout.onHintStateChanged(true);
                this.endLayout.onHintStateChanged(true);
                return;
            }
            return;
        }
        if (z2 || this.hintExpanded) {
            ValueAnimator valueAnimator2 = this.animator;
            if (valueAnimator2 != null && valueAnimator2.isRunning()) {
                this.animator.cancel();
            }
            if (z && this.hintAnimationEnabled) {
                animateToExpansionFraction(1.0f);
            } else {
                this.collapsingTextHelper.setExpansionFraction(1.0f);
            }
            this.hintExpanded = false;
            if (cutoutEnabled()) {
                openCutout();
            }
            updatePlaceholderText();
            this.startLayout.onHintStateChanged(false);
            this.endLayout.onHintStateChanged(false);
        }
    }

    /* JADX WARN: Type inference failed for: r1v7, types: [androidx.core.text.TextDirectionHeuristicCompat, java.lang.Object] */
    final void updateCounter(Editable editable) {
        BidiFormatter bidiFormatter;
        TextDirectionHeuristicCompat textDirectionHeuristicCompat;
        String str;
        int lambda$new$0 = lambda$new$0(editable);
        boolean z = this.counterOverflowed;
        int i = this.counterMaxLength;
        String str2 = null;
        if (i == -1) {
            this.counterView.setText(String.valueOf(lambda$new$0));
            this.counterView.setContentDescription(null);
            this.counterOverflowed = false;
        } else {
            this.counterOverflowed = lambda$new$0 > i;
            Context context = getContext();
            TextView textView = this.counterView;
            int i2 = this.counterMaxLength;
            boolean z2 = this.counterOverflowed;
            Integer valueOf = Integer.valueOf(lambda$new$0);
            textView.setContentDescription(context.getString(true != z2 ? com.google.android.marvin.talkback.R.string.character_counter_content_description : com.google.android.marvin.talkback.R.string.character_counter_overflowed_content_description, valueOf, Integer.valueOf(i2)));
            if (z != this.counterOverflowed) {
                updateCounterTextAppearanceAndColor();
            }
            ClientStreamTracer.StreamInfo.Builder builder = new ClientStreamTracer.StreamInfo.Builder(null);
            if (builder.previousAttempts == 2 && builder.ClientStreamTracer$StreamInfo$Builder$ar$callOptions == BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC) {
                bidiFormatter = builder.isTransparentRetry ? BidiFormatter.DEFAULT_RTL_INSTANCE : BidiFormatter.DEFAULT_LTR_INSTANCE;
            } else {
                bidiFormatter = new BidiFormatter(builder.isTransparentRetry, builder.previousAttempts, builder.ClientStreamTracer$StreamInfo$Builder$ar$callOptions);
            }
            TextView textView2 = this.counterView;
            String string = getContext().getString(com.google.android.marvin.talkback.R.string.character_counter_pattern, valueOf, Integer.valueOf(this.counterMaxLength));
            if (string != null) {
                boolean isRtl$ar$ds = bidiFormatter.mDefaultTextDirectionHeuristicCompat.isRtl$ar$ds(string, string.length());
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
                String str3 = "";
                if ((bidiFormatter.mFlags & 2) != 0) {
                    boolean isRtl$ar$ds2 = (isRtl$ar$ds ? TextDirectionHeuristicsCompat.RTL : TextDirectionHeuristicsCompat.LTR).isRtl$ar$ds(string, string.length());
                    if (!bidiFormatter.mIsRtlContext) {
                        if (isRtl$ar$ds2 || BidiFormatter.getEntryDir(string) == 1) {
                            str = BidiFormatter.LRM_STRING;
                            spannableStringBuilder.append((CharSequence) str);
                        } else {
                            isRtl$ar$ds2 = false;
                        }
                    }
                    str = (!bidiFormatter.mIsRtlContext || (isRtl$ar$ds2 && BidiFormatter.getEntryDir(string) != -1)) ? "" : BidiFormatter.RLM_STRING;
                    spannableStringBuilder.append((CharSequence) str);
                }
                if (isRtl$ar$ds != bidiFormatter.mIsRtlContext) {
                    spannableStringBuilder.append(true != isRtl$ar$ds ? (char) 8234 : (char) 8235);
                    spannableStringBuilder.append((CharSequence) string);
                    spannableStringBuilder.append((char) 8236);
                } else {
                    spannableStringBuilder.append((CharSequence) string);
                }
                if (isRtl$ar$ds) {
                    textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.RTL;
                } else {
                    textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.LTR;
                }
                boolean isRtl$ar$ds3 = textDirectionHeuristicCompat.isRtl$ar$ds(string, string.length());
                if (!bidiFormatter.mIsRtlContext) {
                    if (isRtl$ar$ds3 || BidiFormatter.getExitDir(string) == 1) {
                        str3 = BidiFormatter.LRM_STRING;
                        spannableStringBuilder.append((CharSequence) str3);
                        str2 = spannableStringBuilder.toString();
                    } else {
                        isRtl$ar$ds3 = false;
                    }
                }
                if (bidiFormatter.mIsRtlContext && (!isRtl$ar$ds3 || BidiFormatter.getExitDir(string) == -1)) {
                    str3 = BidiFormatter.RLM_STRING;
                }
                spannableStringBuilder.append((CharSequence) str3);
                str2 = spannableStringBuilder.toString();
            }
            textView2.setText(str2);
        }
        if (this.editText == null || z == this.counterOverflowed) {
            return;
        }
        updateLabelState(false);
        updateTextInputBoxState();
        updateEditTextBackground();
    }

    public final void updatePlaceholderText(Editable editable) {
        if (lambda$new$0(editable) == 0 && !this.hintExpanded) {
            if (this.placeholderTextView == null || !this.placeholderEnabled || TextUtils.isEmpty(this.placeholderText)) {
                return;
            }
            this.placeholderTextView.setText(this.placeholderText);
            TransitionManager.beginDelayedTransition(this.inputFrame, this.placeholderFadeIn);
            this.placeholderTextView.setVisibility(0);
            this.placeholderTextView.bringToFront();
            announceForAccessibility(this.placeholderText);
            return;
        }
        hidePlaceholderText();
    }

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new BottomSheetBehavior.SavedState.AnonymousClass1(8);
        CharSequence error;
        boolean isEndIconChecked;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.error = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.isEndIconChecked = parcel.readInt() == 1;
        }

        public final String toString() {
            return "TextInputLayout.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " error=" + String.valueOf(this.error) + "}";
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            TextUtils.writeToParcel(this.error, parcel, i);
            parcel.writeInt(this.isEndIconChecked ? 1 : 0);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0481  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x01fa  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x031e  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x038b  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x039a  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x03c2  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x03fc  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0434  */
    /* JADX WARN: Type inference failed for: r3v116 */
    /* JADX WARN: Type inference failed for: r3v48 */
    /* JADX WARN: Type inference failed for: r3v49, types: [int, boolean] */
    /* JADX WARN: Type inference failed for: r3v96 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public TextInputLayout(android.content.Context r20, android.util.AttributeSet r21, int r22) {
        /*
            Method dump skipped, instructions count: 1205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.textfield.TextInputLayout.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }
}
