package com.google.android.libraries.surveys.internal.view;

import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.google.android.libraries.phenotype.client.stable.DefaultExperimentTokenDecorator;
import com.google.android.libraries.surveys.internal.utils.SurveyUtils;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class RatingView$$ExternalSyntheticLambda6 implements View.OnTouchListener {
    public final /* synthetic */ View RatingView$$ExternalSyntheticLambda6$ar$f$0;
    public final /* synthetic */ View RatingView$$ExternalSyntheticLambda6$ar$f$1;
    public final /* synthetic */ View RatingView$$ExternalSyntheticLambda6$ar$f$2;
    private final /* synthetic */ int switching_field;

    public /* synthetic */ RatingView$$ExternalSyntheticLambda6(CheckBox checkBox, View view, EditText editText, int i) {
        this.switching_field = i;
        this.RatingView$$ExternalSyntheticLambda6$ar$f$2 = checkBox;
        this.RatingView$$ExternalSyntheticLambda6$ar$f$1 = view;
        this.RatingView$$ExternalSyntheticLambda6$ar$f$0 = editText;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.switching_field != 0) {
            if (motionEvent.getAction() == 1) {
                if (motionEvent.getX() >= 0.0f) {
                    CheckBox checkBox = (CheckBox) this.RatingView$$ExternalSyntheticLambda6$ar$f$2;
                    if (motionEvent.getX() < checkBox.getWidth() && motionEvent.getY() >= 0.0f) {
                        if (motionEvent.getY() < this.RatingView$$ExternalSyntheticLambda6$ar$f$1.getHeight()) {
                            checkBox.performClick();
                        }
                    }
                }
                View view2 = this.RatingView$$ExternalSyntheticLambda6$ar$f$0;
                ((EditText) view2).requestFocus();
                SurveyUtils.showSoftKeyboard(view2);
            }
            return false;
        }
        int action = motionEvent.getAction();
        View view3 = this.RatingView$$ExternalSyntheticLambda6$ar$f$2;
        View view4 = this.RatingView$$ExternalSyntheticLambda6$ar$f$1;
        View view5 = this.RatingView$$ExternalSyntheticLambda6$ar$f$0;
        if (action != 0) {
            if (action != 1) {
                if (action != 2) {
                    return false;
                }
                if (!RatingView.isTouchWithinViewBounds(motionEvent, view)) {
                    ((FrameLayout) view4).setPressed(false);
                    RatingView ratingView = (RatingView) view5;
                    DefaultExperimentTokenDecorator.recolorImageViewIcon((ImageView) view3, ratingView.getContext(), ratingView.getColor(R.color.survey_grey_icon_color));
                }
            } else {
                FrameLayout frameLayout = (FrameLayout) view4;
                if (frameLayout.isPressed()) {
                    frameLayout.performClick();
                }
            }
        } else if (RatingView.isTouchWithinViewBounds(motionEvent, view)) {
            ((FrameLayout) view4).setPressed(true);
            RatingView ratingView2 = (RatingView) view5;
            DefaultExperimentTokenDecorator.recolorImageViewIcon((ImageView) view3, ratingView2.getContext(), ratingView2.getColor(R.color.survey_accent_color));
        }
        return true;
    }

    public /* synthetic */ RatingView$$ExternalSyntheticLambda6(RatingView ratingView, FrameLayout frameLayout, ImageView imageView, int i) {
        this.switching_field = i;
        this.RatingView$$ExternalSyntheticLambda6$ar$f$0 = ratingView;
        this.RatingView$$ExternalSyntheticLambda6$ar$f$1 = frameLayout;
        this.RatingView$$ExternalSyntheticLambda6$ar$f$2 = imageView;
    }
}
