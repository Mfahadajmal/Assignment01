package com.google.android.accessibility.brailleime.tutorial;

import _COROUTINE._BOUNDARY;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatDelegateImpl;
import android.view.View;
import com.google.android.accessibility.brailleime.input.BrailleInputView$CaptionText$$ExternalSyntheticLambda0;
import com.google.android.accessibility.brailleime.tutorial.TapMeAnimationDrawable;
import com.google.android.marvin.talkback.R;
import com.google.frameworks.client.data.android.interceptor.OrderVerifyingClientCall;
import java.util.List;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DotsFlashingAnimationView extends View {
    private final ValueAnimator animator;
    private final Paint dotBackgroundPaint;
    private final Paint dotFlashingPaint;
    private final Paint dotNumberPaint;
    private final int dotRadiusInPixels;
    public List dotTargets;
    private final boolean isTabletop;
    public int orientation;

    public DotsFlashingAnimationView(Context context, List list, int i, boolean z) {
        super(context);
        this.dotTargets = list;
        this.orientation = i;
        this.isTabletop = z;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(R.dimen.input_plane_dot_radius);
        this.dotRadiusInPixels = dimensionPixelSize;
        Paint paint = new Paint();
        this.dotBackgroundPaint = paint;
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(context.getColor(R.color.input_plane_dot_background_default));
        Paint paint2 = new Paint();
        this.dotNumberPaint = paint2;
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setTextSize(_BOUNDARY.getResourcesFloat(getResources(), R.dimen.input_plane_dot_number_size_multiplier) * dimensionPixelSize);
        paint2.setStrokeWidth(getResources().getDimension(R.dimen.input_plane_dot_number_stroke_width));
        paint2.setStyle(Paint.Style.FILL_AND_STROKE);
        paint2.setColor(context.getColor(R.color.input_plane_dot_number_default));
        Paint paint3 = new Paint();
        this.dotFlashingPaint = paint3;
        paint3.setColor(context.getColor(R.color.gesture_circle));
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.7f, 1.3f, 0.7f);
        this.animator = ofFloat;
        ofFloat.setDuration(1200L);
        ofFloat.addUpdateListener(new BrailleInputView$CaptionText$$ExternalSyntheticLambda0(this, 2));
        ofFloat.addListener(new TapMeAnimationDrawable.AnonymousClass1(1));
        ofFloat.start();
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        for (OrderVerifyingClientCall.State state : this.dotTargets) {
            canvas.save();
            int i = 0;
            if (AppCompatDelegateImpl.Api21Impl.isPhoneSizedDevice(getResources())) {
                if (this.orientation == 1) {
                    i = true != this.isTabletop ? 270 : 90;
                } else if (true == this.isTabletop) {
                    i = 180;
                }
            }
            canvas.rotate(i, state.getCenter().x, state.getCenter().y);
            int paintTextBaselineInPixels = _BOUNDARY.getPaintTextBaselineInPixels(this.dotNumberPaint);
            canvas.drawCircle(state.getCenter().x, state.getCenter().y, this.dotRadiusInPixels, this.dotBackgroundPaint);
            canvas.drawText(Integer.toString(state.type$ar$edu$88c656f2_0), state.getCenter().x, state.getCenter().y + paintTextBaselineInPixels, this.dotNumberPaint);
            float floatValue = ((Float) this.animator.getAnimatedValue()).floatValue();
            this.dotFlashingPaint.setAlpha((int) (((floatValue - 0.7f) * 180.0f) / 0.59999996f));
            canvas.drawCircle(state.getCenter().x, state.getCenter().y, this.dotRadiusInPixels * floatValue, this.dotFlashingPaint);
            canvas.restore();
        }
    }
}
