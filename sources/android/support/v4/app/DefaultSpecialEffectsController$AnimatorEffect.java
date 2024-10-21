package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.SpecialEffectsController;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.BackEventCompat;
import androidx.work.impl.model.WorkName;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultSpecialEffectsController$AnimatorEffect extends SpecialEffectsController.Effect {
    private AnimatorSet animator;
    public final DefaultSpecialEffectsController$AnimationInfo animatorInfo;

    public DefaultSpecialEffectsController$AnimatorEffect(DefaultSpecialEffectsController$AnimationInfo defaultSpecialEffectsController$AnimationInfo) {
        defaultSpecialEffectsController$AnimationInfo.getClass();
        this.animatorInfo = defaultSpecialEffectsController$AnimationInfo;
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final boolean isSeekingSupported() {
        return true;
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final void onCancel(ViewGroup viewGroup) {
        AnimatorSet animatorSet = this.animator;
        if (animatorSet == null) {
            this.animatorInfo.operation.completeEffect(this);
            return;
        }
        SpecialEffectsController.Operation operation = this.animatorInfo.operation;
        if (operation.isSeeking) {
            DefaultSpecialEffectsController$Api26Impl.INSTANCE.reverse(animatorSet);
        } else {
            animatorSet.end();
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Objects.toString(operation);
            boolean z = operation.isSeeking;
        }
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final void onCommit(ViewGroup viewGroup) {
        SpecialEffectsController.Operation operation = this.animatorInfo.operation;
        AnimatorSet animatorSet = this.animator;
        if (animatorSet == null) {
            operation.completeEffect(this);
            return;
        }
        animatorSet.start();
        if (FragmentManager.isLoggingEnabled(2)) {
            Objects.toString(operation);
        }
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final void onProgress$ar$ds(BackEventCompat backEventCompat) {
        SpecialEffectsController.Operation operation = this.animatorInfo.operation;
        AnimatorSet animatorSet = this.animator;
        if (animatorSet == null) {
            operation.completeEffect(this);
            return;
        }
        if (Build.VERSION.SDK_INT >= 34 && operation.fragment.mTransitioning) {
            if (FragmentManager.isLoggingEnabled(2)) {
                Objects.toString(operation);
            }
            long j = DefaultSpecialEffectsController$Api24Impl.INSTANCE.totalDuration(animatorSet);
            long j2 = backEventCompat.progress * ((float) j);
            if (j2 == 0) {
                j2 = 1;
            }
            if (j2 == j) {
                j2 = (-1) + j;
            }
            if (FragmentManager.isLoggingEnabled(2)) {
                Objects.toString(animatorSet);
                Objects.toString(operation);
            }
            DefaultSpecialEffectsController$Api26Impl.INSTANCE.setCurrentPlayTime(animatorSet, j2);
        }
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final void onStart(final ViewGroup viewGroup) {
        Object obj;
        boolean z;
        if (!this.animatorInfo.isVisibilityUnchanged()) {
            Context context = viewGroup.getContext();
            DefaultSpecialEffectsController$AnimationInfo defaultSpecialEffectsController$AnimationInfo = this.animatorInfo;
            context.getClass();
            WorkName animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = defaultSpecialEffectsController$AnimationInfo.getAnimation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(context);
            if (animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null) {
                obj = animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WorkName$ar$name;
            } else {
                obj = null;
            }
            this.animator = (AnimatorSet) obj;
            final SpecialEffectsController.Operation operation = this.animatorInfo.operation;
            if (operation.finalState$ar$edu == 3) {
                z = true;
            } else {
                z = false;
            }
            final boolean z2 = z;
            final View view = operation.fragment.mView;
            viewGroup.startViewTransition(view);
            AnimatorSet animatorSet = this.animator;
            if (animatorSet != null) {
                animatorSet.addListener(new AnimatorListenerAdapter() { // from class: android.support.v4.app.DefaultSpecialEffectsController$AnimatorEffect$onStart$1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public final void onAnimationEnd(Animator animator) {
                        animator.getClass();
                        viewGroup.endViewTransition(view);
                        if (z2) {
                            SpecialEffectsController.Operation operation2 = operation;
                            View view2 = view;
                            int i = operation2.finalState$ar$edu;
                            view2.getClass();
                            SpecialEffectsController.Operation.State.applyState$ar$edu(i, view2, viewGroup);
                        }
                        DefaultSpecialEffectsController$AnimatorEffect defaultSpecialEffectsController$AnimatorEffect = this;
                        defaultSpecialEffectsController$AnimatorEffect.animatorInfo.operation.completeEffect(defaultSpecialEffectsController$AnimatorEffect);
                        if (FragmentManager.isLoggingEnabled(2)) {
                            Objects.toString(operation);
                        }
                    }
                });
            }
            AnimatorSet animatorSet2 = this.animator;
            if (animatorSet2 != null) {
                animatorSet2.setTarget(view);
            }
        }
    }
}
