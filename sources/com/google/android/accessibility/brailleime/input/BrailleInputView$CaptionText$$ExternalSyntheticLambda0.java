package com.google.android.accessibility.brailleime.input;

import android.animation.ValueAnimator;
import android.support.v7.widget.FastScroller;
import com.google.android.accessibility.brailleime.input.BrailleInputView;
import com.google.android.accessibility.brailleime.tutorial.DotsFlashingAnimationView;
import com.google.android.accessibility.brailleime.tutorial.TapMeAnimationDrawable;
import com.google.android.accessibility.brailleime.tutorial.TutorialAnimationView;
import com.google.android.accessibility.selecttospeak.ui.CollapsibleControlPanel;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.card.MaterialCardViewHelper;
import com.google.android.material.slider.BaseSlider;
import com.google.android.material.textfield.ClearTextEndIconDelegate;
import com.google.android.material.textfield.DropdownMenuEndIconDelegate;
import com.google.android.material.tooltip.TooltipDrawable;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class BrailleInputView$CaptionText$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Object BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ BrailleInputView$CaptionText$$ExternalSyntheticLambda0(Object obj, int i) {
        this.switching_field = i;
        this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        switch (this.switching_field) {
            case 0:
                ((BrailleInputView.CaptionText) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).this$0.invalidate();
                return;
            case 1:
                int floatValue = (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 255.0f);
                ((FastScroller) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).mVerticalThumbDrawable.setAlpha(floatValue);
                ((FastScroller) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).mVerticalTrackDrawable.setAlpha(floatValue);
                ((FastScroller) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).requestRedraw();
                return;
            case 2:
                ((DotsFlashingAnimationView) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).invalidate();
                return;
            case 3:
                ((TapMeAnimationDrawable) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).invalidateSelf();
                return;
            case 4:
                ((TutorialAnimationView.SwipeAnimation) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).invalidate.run();
                return;
            case 5:
                ((CollapsibleControlPanel.CollapseExpandAnimator) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).m93xfe6f1bb8(valueAnimator);
                return;
            case 6:
                float floatValue2 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                MaterialCardViewHelper materialCardViewHelper = (MaterialCardViewHelper) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0;
                materialCardViewHelper.checkedIcon.setAlpha((int) (255.0f * floatValue2));
                materialCardViewHelper.checkedAnimationProgress = floatValue2;
                return;
            case 7:
                float floatValue3 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                for (TooltipDrawable tooltipDrawable : ((BaseSlider) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).labels) {
                    tooltipDrawable.tooltipPivotY = 1.2f;
                    tooltipDrawable.tooltipScaleX = floatValue3;
                    tooltipDrawable.tooltipScaleY = floatValue3;
                    tooltipDrawable.labelOpacity = AnimationUtils.lerp(0.0f, 1.0f, 0.19f, 1.0f, floatValue3);
                    tooltipDrawable.invalidateSelf();
                }
                ((BaseSlider) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).postInvalidateOnAnimation();
                return;
            case 8:
                ((ClearTextEndIconDelegate) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).endIconView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                return;
            case 9:
                float floatValue4 = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ClearTextEndIconDelegate clearTextEndIconDelegate = (ClearTextEndIconDelegate) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0;
                clearTextEndIconDelegate.endIconView.setScaleX(floatValue4);
                clearTextEndIconDelegate.endIconView.setScaleY(floatValue4);
                return;
            default:
                ((DropdownMenuEndIconDelegate) this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0).endIconView.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                return;
        }
    }

    public BrailleInputView$CaptionText$$ExternalSyntheticLambda0(Object obj, int i, byte[] bArr) {
        this.switching_field = i;
        this.BrailleInputView$CaptionText$$ExternalSyntheticLambda0$ar$f$0 = obj;
    }
}
