package com.google.android.material.internal;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.transition.Transition;
import androidx.transition.TransitionValues;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import java.util.Map;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class TextScale extends Transition {
    private static final void captureValues$ar$ds$4906f17c_1(TransitionValues transitionValues) {
        View view = transitionValues.view;
        if (view instanceof TextView) {
            transitionValues.values.put("android:textscale:scale", Float.valueOf(((TextView) view).getScaleX()));
        }
    }

    @Override // androidx.transition.Transition
    public final void captureEndValues(TransitionValues transitionValues) {
        captureValues$ar$ds$4906f17c_1(transitionValues);
    }

    @Override // androidx.transition.Transition
    public final void captureStartValues(TransitionValues transitionValues) {
        captureValues$ar$ds$4906f17c_1(transitionValues);
    }

    @Override // androidx.transition.Transition
    public final Animator createAnimator(ViewGroup viewGroup, TransitionValues transitionValues, TransitionValues transitionValues2) {
        float f;
        if (transitionValues != null && transitionValues2 != null && (transitionValues.view instanceof TextView)) {
            View view = transitionValues2.view;
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                Map map = transitionValues.values;
                Map map2 = transitionValues2.values;
                float f2 = 1.0f;
                if (map.get("android:textscale:scale") != null) {
                    f = ((Float) map.get("android:textscale:scale")).floatValue();
                } else {
                    f = 1.0f;
                }
                if (map2.get("android:textscale:scale") != null) {
                    f2 = ((Float) map2.get("android:textscale:scale")).floatValue();
                }
                if (f != f2) {
                    ValueAnimator ofFloat = ValueAnimator.ofFloat(f, f2);
                    ofFloat.addUpdateListener(new BottomSheetBehavior.AnonymousClass3(textView, 2));
                    return ofFloat;
                }
                return null;
            }
            return null;
        }
        return null;
    }
}
