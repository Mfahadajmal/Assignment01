package com.google.android.accessibility.brailleime.tutorial;

import _COROUTINE._BOUNDARY;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import com.google.android.accessibility.brailleime.input.BrailleInputView$CaptionText$$ExternalSyntheticLambda0;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TapMeAnimationDrawable extends Drawable {
    private final ValueAnimator animator;
    private final Paint circlePaint;

    public TapMeAnimationDrawable(Context context) {
        Paint paint = new Paint();
        this.circlePaint = paint;
        paint.setColor(context.getColor(R.color.text_highlight_color));
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f, 0.0f, 1.0f, 0.0f);
        this.animator = ofFloat;
        ofFloat.setDuration(1200L);
        ofFloat.addUpdateListener(new BrailleInputView$CaptionText$$ExternalSyntheticLambda0(this, 3));
        ofFloat.addListener(new AnonymousClass1(0));
        ofFloat.start();
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        float floatValue = ((Float) this.animator.getAnimatedValue()).floatValue() * getBounds().height();
        canvas.drawCircle(getBounds().width() / 2.0f, getBounds().height() / 2.0f, floatValue / 2.0f, this.circlePaint);
    }

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -2;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.circlePaint.setAlpha(i);
    }

    /* compiled from: PG */
    /* renamed from: com.google.android.accessibility.brailleime.tutorial.TapMeAnimationDrawable$1, reason: invalid class name */
    /* loaded from: classes.dex */
    final class AnonymousClass1 implements Animator.AnimatorListener {
        private final /* synthetic */ int switching_field;

        public AnonymousClass1(int i) {
            this.switching_field = i;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            if (this.switching_field != 0) {
                animator.setStartDelay(1000L);
                if (!_BOUNDARY.isRobolectric()) {
                    animator.start();
                    return;
                }
                return;
            }
            animator.setStartDelay(1000L);
            if (!_BOUNDARY.isRobolectric()) {
                animator.start();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
        }
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }
}
