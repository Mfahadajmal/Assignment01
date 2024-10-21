package android.support.v4.app;

import android.content.Context;
import android.support.v4.app.SpecialEffectsController;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import androidx.work.impl.model.WorkName;
import java.util.Objects;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultSpecialEffectsController$AnimationEffect extends SpecialEffectsController.Effect {
    public final DefaultSpecialEffectsController$AnimationInfo animationInfo;

    public DefaultSpecialEffectsController$AnimationEffect(DefaultSpecialEffectsController$AnimationInfo defaultSpecialEffectsController$AnimationInfo) {
        defaultSpecialEffectsController$AnimationInfo.getClass();
        this.animationInfo = defaultSpecialEffectsController$AnimationInfo;
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final void onCancel(ViewGroup viewGroup) {
        SpecialEffectsController.Operation operation = this.animationInfo.operation;
        View view = operation.fragment.mView;
        view.clearAnimation();
        viewGroup.endViewTransition(view);
        this.animationInfo.operation.completeEffect(this);
        if (FragmentManager.isLoggingEnabled(2)) {
            Objects.toString(operation);
        }
    }

    @Override // android.support.v4.app.SpecialEffectsController.Effect
    public final void onCommit(ViewGroup viewGroup) {
        Object obj;
        if (this.animationInfo.isVisibilityUnchanged()) {
            this.animationInfo.operation.completeEffect(this);
            return;
        }
        Context context = viewGroup.getContext();
        DefaultSpecialEffectsController$AnimationInfo defaultSpecialEffectsController$AnimationInfo = this.animationInfo;
        SpecialEffectsController.Operation operation = defaultSpecialEffectsController$AnimationInfo.operation;
        View view = operation.fragment.mView;
        context.getClass();
        WorkName animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging = defaultSpecialEffectsController$AnimationInfo.getAnimation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging(context);
        if (animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging != null && (obj = animation$ar$class_merging$ar$class_merging$ar$class_merging$ar$class_merging.WorkName$ar$workSpecId) != null) {
            if (operation.finalState$ar$edu != 1) {
                view.startAnimation((Animation) obj);
                this.animationInfo.operation.completeEffect(this);
                return;
            }
            viewGroup.startViewTransition(view);
            FragmentAnim$EndViewTransitionAnimation fragmentAnim$EndViewTransitionAnimation = new FragmentAnim$EndViewTransitionAnimation((Animation) obj, viewGroup, view);
            fragmentAnim$EndViewTransitionAnimation.setAnimationListener(new DefaultSpecialEffectsController$AnimationEffect$onCommit$1(operation, viewGroup, view, this));
            view.startAnimation(fragmentAnim$EndViewTransitionAnimation);
            if (FragmentManager.isLoggingEnabled(2)) {
                Objects.toString(operation);
                return;
            }
            return;
        }
        throw new IllegalStateException("Required value was null.");
    }
}
