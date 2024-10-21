package com.google.android.accessibility.selecttospeak.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import com.google.android.accessibility.utils.SpannableUtils$IdentifierSpan;
import com.google.android.libraries.accessibility.utils.log.LogUtils;
import com.google.common.flogger.context.ContextDataProvider;
import java.util.Arrays;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class AnimatorHelper {
    public final View pivotView;
    private final View[] shadowViews;
    public long durationAnimateOn = 300;
    public long durationAnimateOff = 300;
    private long durationFadeIn = 300;
    public long durationFadeOut = 300;
    public final Interpolator interpolatorAnimateOn = new AccelerateDecelerateInterpolator();
    public final Interpolator interpolatorAnimateOff = new AccelerateDecelerateInterpolator();
    private final Interpolator interpolatorFadeIn = new LinearInterpolator();
    public final Interpolator interpolatorFadeOut = new LinearInterpolator();
    public int visibility = 1;

    public AnimatorHelper(View... viewArr) {
        this.pivotView = viewArr[0];
        this.shadowViews = (View[]) Arrays.copyOfRange(viewArr, 1, viewArr.length);
    }

    public static String directionToString(int i) {
        if (i != 1) {
            return "RIGHT";
        }
        return "LEFT";
    }

    private final int getHeight() {
        if (this.pivotView.getHeight() == 0) {
            return this.pivotView.getMeasuredHeight();
        }
        return this.pivotView.getHeight();
    }

    private final int getWidth() {
        if (this.pivotView.getWidth() == 0) {
            return this.pivotView.getMeasuredWidth();
        }
        return this.pivotView.getWidth();
    }

    public final Animator animateToDefaultStateIfOnScreen() {
        Interpolator interpolator;
        if (this.visibility == 0) {
            LogUtils.d("AnimationHelper", "Cannot animate to default state: invisible.", new Object[0]);
            return null;
        }
        View view = this.pivotView;
        Animator baseAnimator = baseAnimator(view.getTranslationX(), 0.0f, view.getTranslationY(), 0.0f, view.getAlpha(), 1.0f);
        long alpha = (1.0f - this.pivotView.getAlpha()) * ((float) this.durationFadeIn);
        long[] jArr = {alpha, SpannableUtils$IdentifierSpan.scaleValue(Math.abs(this.pivotView.getTranslationX()), 0.0f, this.pivotView.getWidth(), 0.0f, (float) this.durationAnimateOn), SpannableUtils$IdentifierSpan.scaleValue(Math.abs(this.pivotView.getTranslationY()), 0.0f, this.pivotView.getHeight(), 0.0f, (float) this.durationAnimateOn)};
        ContextDataProvider.checkArgument(true);
        long j = jArr[0];
        for (int i = 1; i < 3; i++) {
            long j2 = jArr[i];
            if (j2 > j) {
                j = j2;
            }
        }
        baseAnimator.setDuration(j);
        if (alpha == j) {
            interpolator = this.interpolatorFadeIn;
        } else {
            interpolator = this.interpolatorAnimateOn;
        }
        baseAnimator.setInterpolator(interpolator);
        baseAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.accessibility.selecttospeak.ui.AnimatorHelper.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                AnimatorHelper.this.visibility = 1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                AnimatorHelper.this.visibility = 4;
            }
        });
        LogUtils.d("AnimationHelper", "Animate to default state.", new Object[0]);
        return baseAnimator;
    }

    public final Animator baseAnimator(final float f, final float f2, final float f3, final float f4, final float f5, final float f6) {
        boolean z;
        final boolean z2;
        final boolean z3;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        if (f != f2) {
            z = true;
        } else {
            z = false;
        }
        if (f3 != f4) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (f5 != f6) {
            z3 = true;
        } else {
            z3 = false;
        }
        final boolean z4 = z;
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: com.google.android.accessibility.selecttospeak.ui.AnimatorHelper.6
            final /* synthetic */ AnimatorHelper this$0;

            {
                this.this$0 = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                if (z4) {
                    this.this$0.setTranslationX(SpannableUtils$IdentifierSpan.scaleValue(floatValue, 0.0f, 1.0f, f, f2));
                }
                if (z2) {
                    this.this$0.setTranslationY(SpannableUtils$IdentifierSpan.scaleValue(floatValue, 0.0f, 1.0f, f3, f4));
                }
                if (z3) {
                    this.this$0.setAlpha(SpannableUtils$IdentifierSpan.scaleValue(floatValue, 0.0f, 1.0f, f5, f6));
                }
            }
        });
        return ofFloat;
    }

    public final Animator fadeIn() {
        Animator baseAnimator = baseAnimator(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f);
        baseAnimator.setDuration(this.durationFadeIn);
        baseAnimator.setInterpolator(this.interpolatorFadeIn);
        baseAnimator.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.accessibility.selecttospeak.ui.AnimatorHelper.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                AnimatorHelper.this.visibility = 1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
                AnimatorHelper animatorHelper = AnimatorHelper.this;
                animatorHelper.visibility = 6;
                animatorHelper.setTranslationX(0.0f);
                AnimatorHelper.this.setTranslationY(0.0f);
                AnimatorHelper.this.setAlpha(0.0f);
                AnimatorHelper.this.setViewVisibility(0);
            }
        });
        LogUtils.d("AnimationHelper", "Fade in.", new Object[0]);
        return baseAnimator;
    }

    public final void getOffscreenTranslation(int[] iArr, int i) {
        int width = getWidth();
        getHeight();
        if (i != 1) {
            iArr[0] = width;
            iArr[1] = 0;
        } else {
            iArr[0] = -width;
            iArr[1] = 0;
        }
    }

    public final void hideImmediately() {
        LogUtils.d("AnimationHelper", "Hide immediately.", new Object[0]);
        setViewVisibility(4);
        setAlpha(0.0f);
        this.visibility = 0;
    }

    public final boolean isVisible() {
        if (this.pivotView.getVisibility() == 0 && this.pivotView.getAlpha() != 0.0f && Math.abs(this.pivotView.getTranslationX()) < getWidth() && Math.abs(this.pivotView.getTranslationY()) < getHeight()) {
            return true;
        }
        return false;
    }

    public final void setAlpha(float f) {
        this.pivotView.setAlpha(f);
        for (View view : this.shadowViews) {
            view.setAlpha(f);
        }
    }

    public final void setDuration(int i, long j) {
        if (i != 1) {
            if (i != 2) {
                if (i != 3) {
                    this.durationFadeOut = j;
                    return;
                } else {
                    this.durationFadeIn = j;
                    return;
                }
            }
            this.durationAnimateOff = j;
            return;
        }
        this.durationAnimateOn = j;
    }

    public final void setTranslationX(float f) {
        this.pivotView.setTranslationX(f);
        for (View view : this.shadowViews) {
            view.setTranslationX(f);
        }
    }

    public final void setTranslationY(float f) {
        this.pivotView.setTranslationY(f);
        for (View view : this.shadowViews) {
            view.setTranslationY(f);
        }
    }

    public final void setViewVisibility(int i) {
        this.pivotView.setVisibility(i);
        for (View view : this.shadowViews) {
            view.setVisibility(i);
        }
    }
}
