package com.google.android.material.textfield;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.util.Property;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.libraries.vision.visionkit.base.FileUtils;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class IndicatorViewController {
    public Animator captionAnimator;
    private FrameLayout captionArea;
    public int captionDisplayed;
    private final int captionFadeInAnimationDuration;
    private final TimeInterpolator captionFadeInAnimationInterpolator;
    private final int captionFadeOutAnimationDuration;
    private final TimeInterpolator captionFadeOutAnimationInterpolator;
    public int captionToShow;
    private final int captionTranslationYAnimationDuration;
    private final TimeInterpolator captionTranslationYAnimationInterpolator;
    private final float captionTranslationYPx;
    public final Context context;
    public boolean errorEnabled;
    public CharSequence errorText;
    public int errorTextAppearance;
    public TextView errorView;
    public int errorViewAccessibilityLiveRegion;
    public CharSequence errorViewContentDescription;
    public ColorStateList errorViewTextColor;
    public CharSequence helperText;
    public boolean helperTextEnabled;
    public int helperTextTextAppearance;
    public TextView helperTextView;
    public ColorStateList helperTextViewTextColor;
    private LinearLayout indicatorArea;
    private int indicatorsAdded;
    public final TextInputLayout textInputView;

    public IndicatorViewController(TextInputLayout textInputLayout) {
        Context context = textInputLayout.getContext();
        this.context = context;
        this.textInputView = textInputLayout;
        this.captionTranslationYPx = context.getResources().getDimensionPixelSize(R.dimen.design_textinput_caption_translate_y);
        this.captionTranslationYAnimationDuration = DrawableUtils$OutlineCompatR.resolveInteger(context, R.attr.motionDurationShort4, 217);
        this.captionFadeInAnimationDuration = DrawableUtils$OutlineCompatR.resolveInteger(context, R.attr.motionDurationMedium4, 167);
        this.captionFadeOutAnimationDuration = DrawableUtils$OutlineCompatR.resolveInteger(context, R.attr.motionDurationShort4, 167);
        this.captionTranslationYAnimationInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(context, R.attr.motionEasingEmphasizedDecelerateInterpolator, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        this.captionFadeInAnimationInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(context, R.attr.motionEasingEmphasizedDecelerateInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
        this.captionFadeOutAnimationInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(context, R.attr.motionEasingLinearInterpolator, AnimationUtils.LINEAR_INTERPOLATOR);
    }

    private final void createCaptionAnimators(List list, boolean z, TextView textView, int i, int i2, int i3) {
        boolean z2;
        float f;
        int i4;
        TimeInterpolator timeInterpolator;
        if (textView != null && z) {
            if (i == i3 || i == i2) {
                if (i3 == i) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                Property property = View.ALPHA;
                if (i3 == i) {
                    f = 1.0f;
                } else {
                    f = 0.0f;
                }
                ObjectAnimator ofFloat = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) property, f);
                if (z2) {
                    i4 = this.captionFadeInAnimationDuration;
                } else {
                    i4 = this.captionFadeOutAnimationDuration;
                }
                ofFloat.setDuration(i4);
                if (z2) {
                    timeInterpolator = this.captionFadeInAnimationInterpolator;
                } else {
                    timeInterpolator = this.captionFadeOutAnimationInterpolator;
                }
                ofFloat.setInterpolator(timeInterpolator);
                if (i3 == i && i2 != 0) {
                    ofFloat.setStartDelay(this.captionFadeOutAnimationDuration);
                }
                list.add(ofFloat);
                if (i3 == i && i2 != 0) {
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(textView, (Property<TextView, Float>) View.TRANSLATION_Y, -this.captionTranslationYPx, 0.0f);
                    ofFloat2.setDuration(this.captionTranslationYAnimationDuration);
                    ofFloat2.setInterpolator(this.captionTranslationYAnimationInterpolator);
                    ofFloat2.setStartDelay(this.captionFadeOutAnimationDuration);
                    list.add(ofFloat2);
                }
            }
        }
    }

    private final TextView getCaptionViewFromDisplayState(int i) {
        if (i != 1) {
            if (i != 2) {
                return null;
            }
            return this.helperTextView;
        }
        return this.errorView;
    }

    private final int getIndicatorPadding(boolean z, int i, int i2) {
        if (z) {
            return this.context.getResources().getDimensionPixelSize(i);
        }
        return i2;
    }

    static final boolean isCaptionView$ar$ds(int i) {
        if (i == 0 || i == 1) {
            return true;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void addIndicator(TextView textView, int i) {
        if (this.indicatorArea == null && this.captionArea == null) {
            LinearLayout linearLayout = new LinearLayout(this.context);
            this.indicatorArea = linearLayout;
            linearLayout.setOrientation(0);
            this.textInputView.addView(this.indicatorArea, -1, -2);
            this.captionArea = new FrameLayout(this.context);
            this.indicatorArea.addView(this.captionArea, new LinearLayout.LayoutParams(0, -2, 1.0f));
            if (this.textInputView.editText != null) {
                adjustIndicatorPadding();
            }
        }
        if (isCaptionView$ar$ds(i)) {
            this.captionArea.setVisibility(0);
            this.captionArea.addView(textView);
        } else {
            this.indicatorArea.addView(textView, new LinearLayout.LayoutParams(-2, -2));
        }
        this.indicatorArea.setVisibility(0);
        this.indicatorsAdded++;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void adjustIndicatorPadding() {
        EditText editText;
        if (this.indicatorArea != null && (editText = this.textInputView.editText) != null) {
            boolean isFontScaleAtLeast1_3 = DrawableUtils$OutlineCompatR.isFontScaleAtLeast1_3(this.context);
            this.indicatorArea.setPaddingRelative(getIndicatorPadding(isFontScaleAtLeast1_3, R.dimen.material_helper_text_font_1_3_padding_horizontal, editText.getPaddingStart()), getIndicatorPadding(isFontScaleAtLeast1_3, R.dimen.material_helper_text_font_1_3_padding_top, this.context.getResources().getDimensionPixelSize(R.dimen.material_helper_text_default_padding_top)), getIndicatorPadding(isFontScaleAtLeast1_3, R.dimen.material_helper_text_font_1_3_padding_horizontal, editText.getPaddingEnd()), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void cancelCaptionAnimator() {
        Animator animator = this.captionAnimator;
        if (animator != null) {
            animator.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void hideError() {
        this.errorText = null;
        cancelCaptionAnimator();
        if (this.captionDisplayed == 1) {
            if (this.helperTextEnabled && !TextUtils.isEmpty(this.helperText)) {
                this.captionToShow = 2;
            } else {
                this.captionToShow = 0;
            }
        }
        updateCaptionViewsVisibility(this.captionDisplayed, this.captionToShow, shouldAnimateCaptionView(this.errorView, ""));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void removeIndicator(TextView textView, int i) {
        FrameLayout frameLayout;
        LinearLayout linearLayout = this.indicatorArea;
        if (linearLayout != null) {
            if (isCaptionView$ar$ds(i) && (frameLayout = this.captionArea) != null) {
                frameLayout.removeView(textView);
            } else {
                linearLayout.removeView(textView);
            }
            int i2 = this.indicatorsAdded - 1;
            this.indicatorsAdded = i2;
            LinearLayout linearLayout2 = this.indicatorArea;
            if (i2 == 0) {
                linearLayout2.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setErrorAccessibilityLiveRegion(int i) {
        this.errorViewAccessibilityLiveRegion = i;
        TextView textView = this.errorView;
        if (textView != null) {
            textView.setAccessibilityLiveRegion(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setErrorContentDescription(CharSequence charSequence) {
        this.errorViewContentDescription = charSequence;
        TextView textView = this.errorView;
        if (textView != null) {
            textView.setContentDescription(charSequence);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setErrorTextAppearance(int i) {
        this.errorTextAppearance = i;
        TextView textView = this.errorView;
        if (textView != null) {
            this.textInputView.setTextAppearanceCompatWithErrorFallback(textView, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setErrorViewTextColor(ColorStateList colorStateList) {
        this.errorViewTextColor = colorStateList;
        TextView textView = this.errorView;
        if (textView != null && colorStateList != null) {
            textView.setTextColor(colorStateList);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setHelperTextAppearance(int i) {
        this.helperTextTextAppearance = i;
        TextView textView = this.helperTextView;
        if (textView != null) {
            textView.setTextAppearance(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void setHelperTextViewTextColor(ColorStateList colorStateList) {
        this.helperTextViewTextColor = colorStateList;
        TextView textView = this.helperTextView;
        if (textView != null && colorStateList != null) {
            textView.setTextColor(colorStateList);
        }
    }

    public final boolean shouldAnimateCaptionView(TextView textView, CharSequence charSequence) {
        if (!this.textInputView.isLaidOut() || !this.textInputView.isEnabled()) {
            return false;
        }
        if (this.captionToShow != this.captionDisplayed || textView == null) {
            return true;
        }
        if (TextUtils.equals(textView.getText(), charSequence)) {
            return false;
        }
        return true;
    }

    public final void updateCaptionViewsVisibility(final int i, final int i2, boolean z) {
        TextView captionViewFromDisplayState;
        TextView captionViewFromDisplayState2;
        if (i == i2) {
            return;
        }
        if (z) {
            AnimatorSet animatorSet = new AnimatorSet();
            this.captionAnimator = animatorSet;
            ArrayList arrayList = new ArrayList();
            createCaptionAnimators(arrayList, this.helperTextEnabled, this.helperTextView, 2, i, i2);
            createCaptionAnimators(arrayList, this.errorEnabled, this.errorView, 1, i, i2);
            FileUtils.playTogether(animatorSet, arrayList);
            final TextView captionViewFromDisplayState3 = getCaptionViewFromDisplayState(i);
            final TextView captionViewFromDisplayState4 = getCaptionViewFromDisplayState(i2);
            animatorSet.addListener(new AnimatorListenerAdapter(this) { // from class: com.google.android.material.textfield.IndicatorViewController.1
                final /* synthetic */ IndicatorViewController this$0;

                {
                    this.this$0 = this;
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationEnd(Animator animator) {
                    TextView textView;
                    IndicatorViewController indicatorViewController = this.this$0;
                    indicatorViewController.captionDisplayed = i2;
                    indicatorViewController.captionAnimator = null;
                    TextView textView2 = captionViewFromDisplayState3;
                    if (textView2 != null) {
                        textView2.setVisibility(4);
                        if (i == 1 && (textView = this.this$0.errorView) != null) {
                            textView.setText((CharSequence) null);
                        }
                    }
                    TextView textView3 = captionViewFromDisplayState4;
                    if (textView3 != null) {
                        textView3.setTranslationY(0.0f);
                        captionViewFromDisplayState4.setAlpha(1.0f);
                    }
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public final void onAnimationStart(Animator animator) {
                    TextView textView = captionViewFromDisplayState4;
                    if (textView != null) {
                        textView.setVisibility(0);
                        captionViewFromDisplayState4.setAlpha(0.0f);
                    }
                }
            });
            animatorSet.start();
        } else if (i != i2) {
            if (i2 != 0 && (captionViewFromDisplayState2 = getCaptionViewFromDisplayState(i2)) != null) {
                captionViewFromDisplayState2.setVisibility(0);
                captionViewFromDisplayState2.setAlpha(1.0f);
            }
            if (i != 0 && (captionViewFromDisplayState = getCaptionViewFromDisplayState(i)) != null) {
                captionViewFromDisplayState.setVisibility(4);
                if (i == 1) {
                    captionViewFromDisplayState.setText((CharSequence) null);
                }
            }
            this.captionDisplayed = i2;
        }
        this.textInputView.updateEditTextBackground();
        this.textInputView.updateLabelState(z);
        this.textInputView.updateTextInputBoxState();
    }
}
