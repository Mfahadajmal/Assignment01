package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.EditText;
import com.google.android.accessibility.brailleime.input.BrailleInputView$CaptionText$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.trainingcommon.TrainingActivity$$ExternalSyntheticLambda0;
import com.google.android.accessibility.talkback.trainingcommon.tv.TvNavigationButton$$ExternalSyntheticLambda0;
import com.google.android.libraries.phenotype.client.stable.FlagStore$$ExternalSyntheticLambda3;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class ClearTextEndIconDelegate extends EndIconDelegate {
    private final int animationFadeDuration;
    private final TimeInterpolator animationFadeInterpolator;
    private final int animationScaleDuration;
    private final TimeInterpolator animationScaleInterpolator;
    public EditText editText;
    private AnimatorSet iconInAnim;
    private ValueAnimator iconOutAnim;
    private final View.OnFocusChangeListener onFocusChangeListener;
    private final View.OnClickListener onIconClickListener;

    public ClearTextEndIconDelegate(EndCompoundLayout endCompoundLayout) {
        super(endCompoundLayout);
        this.onIconClickListener = new TrainingActivity$$ExternalSyntheticLambda0(this, 15);
        this.onFocusChangeListener = new TvNavigationButton$$ExternalSyntheticLambda0(this, 3);
        this.animationFadeDuration = DrawableUtils$OutlineCompatR.resolveInteger(endCompoundLayout.getContext(), R.attr.motionDurationShort3, 100);
        this.animationScaleDuration = DrawableUtils$OutlineCompatR.resolveInteger(endCompoundLayout.getContext(), R.attr.motionDurationShort3, 150);
        this.animationFadeInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(endCompoundLayout.getContext(), R.attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        this.animationScaleInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(endCompoundLayout.getContext(), R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
    }

    private final ValueAnimator getAlphaAnimator(float... fArr) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(fArr);
        ofFloat.setInterpolator(this.animationFadeInterpolator);
        ofFloat.setDuration(this.animationFadeDuration);
        ofFloat.addUpdateListener(new BrailleInputView$CaptionText$$ExternalSyntheticLambda0(this, 8));
        return ofFloat;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void afterEditTextChanged$ar$ds() {
        if (this.endLayout.suffixText != null) {
            return;
        }
        animateIcon(shouldBeVisible());
    }

    public final void animateIcon(boolean z) {
        boolean isEndIconVisible = this.endLayout.isEndIconVisible();
        if (z) {
            if (!this.iconInAnim.isRunning()) {
                this.iconOutAnim.cancel();
                this.iconInAnim.start();
                if (isEndIconVisible) {
                    this.iconInAnim.end();
                    return;
                }
                return;
            }
            return;
        }
        this.iconInAnim.cancel();
        this.iconOutAnim.start();
        if (!isEndIconVisible) {
            this.iconOutAnim.end();
        }
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final int getIconContentDescriptionResId() {
        return R.string.clear_text_end_icon_content_description;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final int getIconDrawableResId() {
        return R.drawable.mtrl_ic_cancel;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final View.OnFocusChangeListener getOnEditTextFocusChangeListener() {
        return this.onFocusChangeListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final View.OnFocusChangeListener getOnIconViewFocusChangeListener() {
        return this.onFocusChangeListener;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void onEditTextAttached(EditText editText) {
        this.editText = editText;
        this.textInputLayout.setEndIconVisible(shouldBeVisible());
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void onSuffixVisibilityChanged(boolean z) {
        if (this.endLayout.suffixText == null) {
            return;
        }
        animateIcon(z);
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void setUp() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        ofFloat.setInterpolator(this.animationScaleInterpolator);
        ofFloat.setDuration(this.animationScaleDuration);
        ofFloat.addUpdateListener(new BrailleInputView$CaptionText$$ExternalSyntheticLambda0(this, 9));
        ValueAnimator alphaAnimator = getAlphaAnimator(0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        this.iconInAnim = animatorSet;
        animatorSet.playTogether(ofFloat, alphaAnimator);
        this.iconInAnim.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                ClearTextEndIconDelegate.this.endLayout.setEndIconVisible(true);
            }
        });
        ValueAnimator alphaAnimator2 = getAlphaAnimator(1.0f, 0.0f);
        this.iconOutAnim = alphaAnimator2;
        alphaAnimator2.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.textfield.ClearTextEndIconDelegate.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                ClearTextEndIconDelegate.this.endLayout.setEndIconVisible(false);
            }
        });
    }

    public final boolean shouldBeVisible() {
        EditText editText = this.editText;
        if (editText != null) {
            if ((editText.hasFocus() || this.endIconView.hasFocus()) && this.editText.getText().length() > 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // com.google.android.material.textfield.EndIconDelegate
    public final void tearDown() {
        EditText editText = this.editText;
        if (editText != null) {
            editText.post(new FlagStore$$ExternalSyntheticLambda3(this, 15));
        }
    }
}
