package com.google.android.material.behavior;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.marvin.talkback.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatL;
import com.google.android.material.drawable.DrawableUtils$OutlineCompatR;
import java.util.Iterator;
import java.util.LinkedHashSet;

/* compiled from: PG */
/* loaded from: classes.dex */
public class HideBottomViewOnScrollBehavior<V extends View> extends CoordinatorLayout.Behavior<V> {
    public ViewPropertyAnimator currentAnimator;
    private int currentState;
    private int enterAnimDuration;
    private TimeInterpolator enterAnimInterpolator;
    private int exitAnimDuration;
    private TimeInterpolator exitAnimInterpolator;
    private int height;
    private final LinkedHashSet onScrollStateChangedListeners;

    /* compiled from: PG */
    /* loaded from: classes.dex */
    public interface OnScrollStateChangedListener {
        void onStateChanged$ar$ds();
    }

    public HideBottomViewOnScrollBehavior() {
        this.onScrollStateChangedListeners = new LinkedHashSet();
        this.height = 0;
        this.currentState = 2;
    }

    private final void animateChildTo(View view, int i, long j, TimeInterpolator timeInterpolator) {
        this.currentAnimator = view.animate().translationY(i).setInterpolator(timeInterpolator).setDuration(j).setListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.behavior.HideBottomViewOnScrollBehavior.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                HideBottomViewOnScrollBehavior.this.currentAnimator = null;
            }
        });
    }

    private final void updateCurrentState(View view, int i) {
        this.currentState = i;
        Iterator it = this.onScrollStateChangedListeners.iterator();
        while (it.hasNext()) {
            ((OnScrollStateChangedListener) it.next()).onStateChanged$ar$ds();
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onLayoutChild(CoordinatorLayout coordinatorLayout, View view, int i) {
        this.height = view.getMeasuredHeight() + ((ViewGroup.MarginLayoutParams) view.getLayoutParams()).bottomMargin;
        this.enterAnimDuration = DrawableUtils$OutlineCompatR.resolveInteger(view.getContext(), R.attr.motionDurationLong2, 225);
        this.exitAnimDuration = DrawableUtils$OutlineCompatR.resolveInteger(view.getContext(), R.attr.motionDurationMedium4, 175);
        this.enterAnimInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(view.getContext(), R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        this.exitAnimInterpolator = DrawableUtils$OutlineCompatL.resolveThemeInterpolator(view.getContext(), R.attr.motionEasingEmphasizedInterpolator, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        return false;
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public final void onNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, int i, int i2, int i3, int i4, int i5, int[] iArr) {
        if (i2 > 0) {
            if (this.currentState != 1) {
                ViewPropertyAnimator viewPropertyAnimator = this.currentAnimator;
                if (viewPropertyAnimator != null) {
                    viewPropertyAnimator.cancel();
                    view.clearAnimation();
                }
                updateCurrentState(view, 1);
                animateChildTo(view, this.height, this.exitAnimDuration, this.exitAnimInterpolator);
                return;
            }
            return;
        }
        if (i2 < 0 && this.currentState != 2) {
            ViewPropertyAnimator viewPropertyAnimator2 = this.currentAnimator;
            if (viewPropertyAnimator2 != null) {
                viewPropertyAnimator2.cancel();
                view.clearAnimation();
            }
            updateCurrentState(view, 2);
            animateChildTo(view, 0, this.enterAnimDuration, this.enterAnimInterpolator);
        }
    }

    @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View view, View view2, View view3, int i, int i2) {
        if (i == 2) {
            return true;
        }
        return false;
    }

    public HideBottomViewOnScrollBehavior(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.onScrollStateChangedListeners = new LinkedHashSet();
        this.height = 0;
        this.currentState = 2;
    }
}
