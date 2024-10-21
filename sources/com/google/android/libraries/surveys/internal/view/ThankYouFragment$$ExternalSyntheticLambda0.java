package com.google.android.libraries.surveys.internal.view;

import android.view.MotionEvent;
import android.view.View;

/* compiled from: PG */
/* loaded from: classes.dex */
public final /* synthetic */ class ThankYouFragment$$ExternalSyntheticLambda0 implements View.OnTouchListener {
    private final /* synthetic */ int switching_field;

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.switching_field != 0) {
            if (motionEvent.getAction() == 1) {
                view.performClick();
            }
            return true;
        }
        if (motionEvent.getAction() == 0) {
            view.setPressed(true);
        } else if (motionEvent.getAction() == 1) {
            view.performClick();
            view.setPressed(false);
        }
        return false;
    }
}
