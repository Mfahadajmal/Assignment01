package com.google.android.material.textfield;

import _COROUTINE._BOUNDARY;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.marvin.talkback.R;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.shape.EdgeTreatment;
import com.google.common.util.concurrent.ExecutionList;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class StartCompoundLayout extends LinearLayout {
    private boolean hintExpanded;
    public CharSequence prefixText;
    public final TextView prefixTextView;
    private int startIconMinSize;
    private ColorStateList startIconTintList;
    private PorterDuff.Mode startIconTintMode;
    public final CheckableImageButton startIconView;
    private final TextInputLayout textInputLayout;

    public StartCompoundLayout(TextInputLayout textInputLayout, ExecutionList.RunnableExecutorPair runnableExecutorPair) {
        super(textInputLayout.getContext());
        this.textInputLayout = textInputLayout;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, 8388611));
        CheckableImageButton checkableImageButton = (CheckableImageButton) LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_start_icon, (ViewGroup) this, false);
        this.startIconView = checkableImageButton;
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.prefixTextView = appCompatTextView;
        if (DrawableUtils$OutlineCompatR.isFontScaleAtLeast1_3(getContext())) {
            ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).setMarginEnd(0);
        }
        setStartIconOnClickListener$ar$ds();
        setStartIconOnLongClickListener$ar$ds();
        int[] iArr = R$styleable.MaterialAutoCompleteTextView;
        if (runnableExecutorPair.hasValue(69)) {
            this.startIconTintList = DrawableUtils$OutlineCompatR.getColorStateList$ar$class_merging$ar$class_merging$ar$class_merging(getContext(), runnableExecutorPair, 69);
        }
        if (runnableExecutorPair.hasValue(70)) {
            this.startIconTintMode = _BOUNDARY.ArtificialStackFrames$ar$MethodMerging$dc56d17a_10(runnableExecutorPair.getInt(70, -1), null);
        }
        if (runnableExecutorPair.hasValue(66)) {
            Drawable drawable = runnableExecutorPair.getDrawable(66);
            checkableImageButton.setImageDrawable(drawable);
            if (drawable != null) {
                EdgeTreatment.applyIconTint(textInputLayout, checkableImageButton, this.startIconTintList, this.startIconTintMode);
                setStartIconVisible(true);
                refreshStartIconDrawableState();
            } else {
                setStartIconVisible(false);
                setStartIconOnClickListener$ar$ds();
                setStartIconOnLongClickListener$ar$ds();
                setStartIconContentDescription(null);
            }
            if (runnableExecutorPair.hasValue(65)) {
                setStartIconContentDescription(runnableExecutorPair.getText(65));
            }
            checkableImageButton.setCheckable(runnableExecutorPair.getBoolean(64, true));
        }
        int dimensionPixelSize = runnableExecutorPair.getDimensionPixelSize(67, getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size));
        if (dimensionPixelSize >= 0) {
            if (dimensionPixelSize != this.startIconMinSize) {
                this.startIconMinSize = dimensionPixelSize;
                EdgeTreatment.setIconMinSize(checkableImageButton, dimensionPixelSize);
            }
            if (runnableExecutorPair.hasValue(68)) {
                checkableImageButton.setScaleType(EdgeTreatment.convertScaleType(runnableExecutorPair.getInt(68, -1)));
            }
            appCompatTextView.setVisibility(8);
            appCompatTextView.setId(R.id.textinput_prefix_text);
            appCompatTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
            appCompatTextView.setAccessibilityLiveRegion(1);
            appCompatTextView.setTextAppearance(runnableExecutorPair.getResourceId(60, 0));
            if (runnableExecutorPair.hasValue(61)) {
                appCompatTextView.setTextColor(runnableExecutorPair.getColorStateList(61));
            }
            CharSequence text = runnableExecutorPair.getText(59);
            this.prefixText = true != TextUtils.isEmpty(text) ? text : null;
            appCompatTextView.setText(text);
            updateVisibility();
            addView(checkableImageButton);
            addView(appCompatTextView);
            return;
        }
        throw new IllegalArgumentException("startIconSize cannot be less than 0");
    }

    private final void updateVisibility() {
        int i;
        int i2 = 0;
        if (this.prefixText != null && !this.hintExpanded) {
            i = 0;
        } else {
            i = 8;
        }
        if (this.startIconView.getVisibility() != 0 && i != 0) {
            i2 = 8;
        }
        setVisibility(i2);
        this.prefixTextView.setVisibility(i);
        this.textInputLayout.updateDummyDrawables();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final int getPrefixTextStartOffset() {
        int i;
        if (isStartIconVisible()) {
            CheckableImageButton checkableImageButton = this.startIconView;
            i = checkableImageButton.getMeasuredWidth() + ((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams()).getMarginEnd();
        } else {
            i = 0;
        }
        return getPaddingStart() + this.prefixTextView.getPaddingStart() + i;
    }

    final boolean isStartIconVisible() {
        if (this.startIconView.getVisibility() == 0) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void onHintStateChanged(boolean z) {
        this.hintExpanded = z;
        updateVisibility();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected final void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        updatePrefixTextViewPadding();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void refreshStartIconDrawableState() {
        EdgeTreatment.refreshIconDrawableState(this.textInputLayout, this.startIconView, this.startIconTintList);
    }

    final void setStartIconContentDescription(CharSequence charSequence) {
        if (this.startIconView.getContentDescription() != charSequence) {
            this.startIconView.setContentDescription(charSequence);
        }
    }

    final void setStartIconOnClickListener$ar$ds() {
        EdgeTreatment.setIconOnClickListener$ar$ds(this.startIconView, null);
    }

    final void setStartIconOnLongClickListener$ar$ds() {
        EdgeTreatment.setIconOnLongClickListener$ar$ds(this.startIconView);
    }

    final void setStartIconVisible(boolean z) {
        int i;
        if (isStartIconVisible() != z) {
            CheckableImageButton checkableImageButton = this.startIconView;
            if (true != z) {
                i = 8;
            } else {
                i = 0;
            }
            checkableImageButton.setVisibility(i);
            updatePrefixTextViewPadding();
            updateVisibility();
        }
    }

    final void updatePrefixTextViewPadding() {
        int paddingStart;
        EditText editText = this.textInputLayout.editText;
        if (editText == null) {
            return;
        }
        if (isStartIconVisible()) {
            paddingStart = 0;
        } else {
            paddingStart = editText.getPaddingStart();
        }
        this.prefixTextView.setPaddingRelative(paddingStart, editText.getCompoundPaddingTop(), getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding), editText.getCompoundPaddingBottom());
    }
}
