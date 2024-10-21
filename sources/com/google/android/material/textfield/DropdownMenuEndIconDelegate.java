package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import com.google.android.accessibility.brailleime.input.BrailleInputView$CaptionText$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.trainingcommon.tv.TvNavigationButton$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import com.google.android.material.shape.EdgeTreatment;
import io.grpc.internal.RetryingNameResolver;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DropdownMenuEndIconDelegate extends EndIconDelegate {
    private AccessibilityManager accessibilityManager;
    private final int animationFadeInDuration;
    private final TimeInterpolator animationFadeInterpolator;
    private final int animationFadeOutDuration;
    public AutoCompleteTextView autoCompleteTextView;
    private long dropdownPopupActivatedAt;
    public boolean dropdownPopupDirty;
    public boolean editTextHasFocus;
    public ValueAnimator fadeInAnim;
    private ValueAnimator fadeOutAnim;
    private boolean isEndIconChecked;
    private final View.OnFocusChangeListener onEditTextFocusChangeListener;
    private final View.OnClickListener onIconClickListener;
    private final RetryingNameResolver.ResolutionResultListener touchExplorationStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;

    public DropdownMenuEndIconDelegate(EndCompoundLayout endCompoundLayout) {
        super(endCompoundLayout);
        this.onIconClickListener = new TrainingActivity$$ExternalSyntheticLambda0(this, 16);
        this.onEditTextFocusChangeListener = new TvNavigationButton$$ExternalSyntheticLambda0(this, 4);
        this.touchExplorationStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = new RetryingNameResolver.ResolutionResultListener(this, null);
        this.dropdownPopupActivatedAt = Long.MAX_VALUE;
        this.animationFadeInDuration = DrawableUtils$OutlineCompatR.resolveInteger(endCompoundLayout.getContext(), R.attr.motionDurationShort3, 67);
        this.animationFadeOutDuration = DrawableUtils$OutlineCompatR.resolveInteger(endCompoundLayout.getContext(), R.attr.motionDurationShort3, 50);
        this.animationFadeInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(endCompoundLayout.getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
    }

    private final ValueAnimator getAlphaAnimator(int i, float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(this.animationFadeInterpolator);
        ofFloat.setDuration(i);
        ofFloat.addUpdateListener(new BrailleInputView$CaptionText$$ExternalSyntheticLambda0(this, 10));
        return ofFloat;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void afterEditTextChanged$ar$ds() {
        if (this.accessibilityManager.isTouchExplorationEnabled() && EdgeTreatment.isEditable(this.autoCompleteTextView) && !this.endIconView.hasFocus()) {
            this.autoCompleteTextView.dismissDropDown();
        }
        this.autoCompleteTextView.post(new FlagStore$$ExternalSyntheticLambda3(this, 16));
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final int getIconContentDescriptionResId() {
        return R.string.exposed_dropdown_menu_content_description;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final int getIconDrawableResId() {
        return R.drawable.mtrl_dropdown_arrow;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return this.onEditTextFocusChangeListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final RetryingNameResolver.ResolutionResultListener getTouchExplorationStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging() {
        return this.touchExplorationStateChangeListener$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final boolean isBoxBackgroundModeSupported(int i) {
        if (i != 0) {
            return true;
        }
        return false;
    }

    public final boolean isDropdownPopupActive() {
        long currentTimeMillis = System.currentTimeMillis() - this.dropdownPopupActivatedAt;
        if (currentTimeMillis >= 0 && currentTimeMillis <= 300) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final boolean isIconActivable() {
        return true;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final boolean isIconActivated() {
        return this.editTextHasFocus;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final boolean isIconCheckable() {
        return true;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final boolean isIconChecked() {
        return this.isEndIconChecked;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void onEditTextAttached(EditText editText) {
        if (editText instanceof AutoCompleteTextView) {
            AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) editText;
            this.autoCompleteTextView = autoCompleteTextView;
            autoCompleteTextView.setOnTouchListener(new DropdownMenuEndIconDelegate$$ExternalSyntheticLambda1(this, 0));
            this.autoCompleteTextView.setOnDismissListener(new AutoCompleteTextView.OnDismissListener() { // from class: com.google.android.material.textfield.DropdownMenuEndIconDelegate$$ExternalSyntheticLambda2
                @Override // android.widget.AutoCompleteTextView.OnDismissListener
                public final void onDismiss() {
                    DropdownMenuEndIconDelegate dropdownMenuEndIconDelegate = DropdownMenuEndIconDelegate.this;
                    dropdownMenuEndIconDelegate.updateDropdownPopupDirty();
                    dropdownMenuEndIconDelegate.setEndIconChecked(false);
                }
            });
            this.autoCompleteTextView.setThreshold(0);
            this.textInputLayout.endLayout.setErrorIconDrawable(null);
            if (!EdgeTreatment.isEditable(editText) && this.accessibilityManager.isTouchExplorationEnabled()) {
                this.endIconView.setImportantForAccessibility(2);
            }
            this.textInputLayout.setEndIconVisible(true);
            return;
        }
        throw new RuntimeException("EditText needs to be an AutoCompleteTextView if an Exposed Dropdown Menu is being used.");
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void onInitializeAccessibilityNodeInfo$ar$ds(AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        if (!EdgeTreatment.isEditable(this.autoCompleteTextView)) {
            accessibilityNodeInfoCompat.setClassName(Spinner.class.getName());
        }
        if (accessibilityNodeInfoCompat.isShowingHintText()) {
            accessibilityNodeInfoCompat.setHintText(null);
        }
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void onPopulateAccessibilityEvent$ar$ds(AccessibilityEvent accessibilityEvent) {
        if (this.accessibilityManager.isEnabled() && !EdgeTreatment.isEditable(this.autoCompleteTextView)) {
            boolean z = false;
            if ((accessibilityEvent.getEventType() == 32768 || accessibilityEvent.getEventType() == 8) && this.isEndIconChecked && !this.autoCompleteTextView.isPopupShowing()) {
                z = true;
            }
            if (accessibilityEvent.getEventType() == 1 || z) {
                showHideDropdown();
                updateDropdownPopupDirty();
            }
        }
    }

    public final void setEndIconChecked(boolean z) {
        if (this.isEndIconChecked != z) {
            this.isEndIconChecked = z;
            this.fadeInAnim.cancel();
            this.fadeOutAnim.start();
        }
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void setUp() {
        this.fadeInAnim = getAlphaAnimator(this.animationFadeInDuration, 0.0f, 1.0f);
        ValueAnimator alphaAnimator = getAlphaAnimator(this.animationFadeOutDuration, 1.0f, 0.0f);
        this.fadeOutAnim = alphaAnimator;
        alphaAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.DropdownMenuEndIconDelegate.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                DropdownMenuEndIconDelegate.this.refreshIconState();
                DropdownMenuEndIconDelegate.this.fadeInAnim.start();
            }
        });
        this.accessibilityManager = (AccessibilityManager) this.context.getSystemService("accessibility");
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final boolean shouldTintIconOnError() {
        return true;
    }

    public final void showHideDropdown() {
        if (this.autoCompleteTextView == null) {
            return;
        }
        if (isDropdownPopupActive()) {
            this.dropdownPopupDirty = false;
        }
        if (!this.dropdownPopupDirty) {
            setEndIconChecked(!this.isEndIconChecked);
            if (this.isEndIconChecked) {
                this.autoCompleteTextView.requestFocus();
                this.autoCompleteTextView.showDropDown();
                return;
            } else {
                this.autoCompleteTextView.dismissDropDown();
                return;
            }
        }
        this.dropdownPopupDirty = false;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void tearDown() {
        AutoCompleteTextView autoCompleteTextView = this.autoCompleteTextView;
        if (autoCompleteTextView != null) {
            autoCompleteTextView.setOnTouchListener(null);
            this.autoCompleteTextView.setOnDismissListener(null);
        }
    }

    public final void updateDropdownPopupDirty() {
        this.dropdownPopupDirty = true;
        this.dropdownPopupActivatedAt = System.currentTimeMillis();
    }
}
