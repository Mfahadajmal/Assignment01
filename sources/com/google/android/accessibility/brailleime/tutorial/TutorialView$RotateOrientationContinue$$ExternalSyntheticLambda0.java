package com.google.android.accessibility.brailleime.tutorial;

import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.view.View;
import android.widget.TextView;
import com.google.android.accessibility.brailleime.input.BrailleInputPlane;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0 implements ValueAnimator.AnimatorUpdateListener {
    public final /* synthetic */ Object TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0$ar$f$0;
    public final /* synthetic */ View TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0$ar$f$2;
    public final /* synthetic */ int f$1;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0(Object obj, int i, View view, int i2) {
        this.switching_field = i2;
        this.TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0$ar$f$0 = obj;
        this.f$1 = i;
        this.TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0$ar$f$2 = view;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.CharSequence, java.lang.Object] */
    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        if (this.switching_field != 0) {
            float floatValue = ((Float) valueAnimator.getAnimatedValue("ScaleX")).floatValue();
            float floatValue2 = ((Float) valueAnimator.getAnimatedValue("ScaleY")).floatValue();
            BrailleInputPlane brailleInputPlane = (BrailleInputPlane) this.TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0$ar$f$0;
            PointF[] pointFArr = brailleInputPlane.textPosition;
            int i = this.f$1;
            pointFArr[i].x = floatValue;
            brailleInputPlane.textPosition[i].y = brailleInputPlane.textBaseline + floatValue2;
            brailleInputPlane.dotCenterPosition[i].x = floatValue;
            brailleInputPlane.dotCenterPosition[i].y = floatValue2;
            this.TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0$ar$f$2.invalidate();
            return;
        }
        Spannable newSpannable = Spannable.Factory.getInstance().newSpannable(this.TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0$ar$f$0);
        newSpannable.setSpan(new BackgroundColorSpan(this.f$1), 0, ((Integer) valueAnimator.getAnimatedValue()).intValue(), 33);
        TextView textView = (TextView) this.TutorialView$RotateOrientationContinue$$ExternalSyntheticLambda0$ar$f$2;
        textView.setText(newSpannable);
        textView.invalidate();
    }
}
