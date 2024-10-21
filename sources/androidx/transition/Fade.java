package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.util.Property;
import android.view.View;
import androidx.transition.Transition;
import com.google.android.marvin.talkback.R;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class Fade extends Visibility {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PG */
    /* loaded from: classes.dex */
    public final class FadeAnimatorListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private boolean mLayerTypeChanged = false;
        private final View mView;

        public FadeAnimatorListener(View view) {
            this.mView = view;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            ViewUtils.setTransitionAlpha(this.mView, 1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            onAnimationEnd(animator, false);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            if (this.mView.hasOverlappingRendering() && this.mView.getLayerType() == 0) {
                this.mLayerTypeChanged = true;
                this.mView.setLayerType(2, null);
            }
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionEnd(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionPause$ar$ds() {
            float f;
            if (this.mView.getVisibility() == 0) {
                f = ViewUtils.getTransitionAlpha(this.mView);
            } else {
                f = 0.0f;
            }
            this.mView.setTag(R.id.transition_pause_alpha, Float.valueOf(f));
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionResume$ar$ds() {
            this.mView.setTag(R.id.transition_pause_alpha, null);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionStart(Transition transition) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator, boolean z) {
            if (this.mLayerTypeChanged) {
                this.mView.setLayerType(0, null);
            }
            if (z) {
                return;
            }
            ViewUtils.setTransitionAlpha(this.mView, 1.0f);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final /* synthetic */ void onTransitionEnd(Transition transition, boolean z) {
            onTransitionEnd(transition);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionStart(Transition transition, boolean z) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public final void onTransitionCancel(Transition transition) {
        }
    }

    public Fade() {
    }

    private final Animator createAnimation(View view, float f, float f2) {
        if (f == f2) {
            return null;
        }
        ViewUtils.setTransitionAlpha(view, f);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, (Property<View, Float>) ViewUtils.TRANSITION_ALPHA, f2);
        FadeAnimatorListener fadeAnimatorListener = new FadeAnimatorListener(view);
        ofFloat.addListener(fadeAnimatorListener);
        getRootTransition().addListener$ar$ds(fadeAnimatorListener);
        return ofFloat;
    }

    private static float getStartAlpha(TransitionValues transitionValues, float f) {
        Float f2;
        if (transitionValues != null && (f2 = (Float) transitionValues.values.get("android:fade:transitionAlpha")) != null) {
            return f2.floatValue();
        }
        return f;
    }

    @Override // androidx.transition.Visibility, androidx.transition.Transition
    public final void captureStartValues(TransitionValues transitionValues) {
        Visibility.captureValues$ar$ds$4906f17c_0(transitionValues);
        Float f = (Float) transitionValues.view.getTag(R.id.transition_pause_alpha);
        if (f == null) {
            if (transitionValues.view.getVisibility() == 0) {
                f = Float.valueOf(ViewUtils.getTransitionAlpha(transitionValues.view));
            } else {
                f = Float.valueOf(0.0f);
            }
        }
        transitionValues.values.put("android:fade:transitionAlpha", f);
    }

    @Override // androidx.transition.Transition
    public final boolean isSeekingSupported() {
        return true;
    }

    @Override // androidx.transition.Visibility
    public final Animator onAppear$ar$ds(View view, TransitionValues transitionValues) {
        ViewUtilsApi19 viewUtilsApi19 = ViewUtils.IMPL;
        return createAnimation(view, getStartAlpha(transitionValues, 0.0f), 1.0f);
    }

    @Override // androidx.transition.Visibility
    public final Animator onDisappear$ar$ds(View view, TransitionValues transitionValues, TransitionValues transitionValues2) {
        ViewUtilsApi19 viewUtilsApi19 = ViewUtils.IMPL;
        Animator createAnimation = createAnimation(view, getStartAlpha(transitionValues, 1.0f), 0.0f);
        if (createAnimation == null) {
            ViewUtils.setTransitionAlpha(view, getStartAlpha(transitionValues2, 1.0f));
        }
        return createAnimation;
    }

    public Fade(int i) {
        this.mMode = i;
    }
}
