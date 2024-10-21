package android.support.v4.app;

import android.support.v4.app.SpecialEffectsController;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import java.util.Objects;

/* compiled from: PG */
/* loaded from: classes.dex */
public final class DefaultSpecialEffectsController$AnimationEffect$onCommit$1 implements Animation.AnimationListener {
    public static final /* synthetic */ int DefaultSpecialEffectsController$AnimationEffect$onCommit$1$ar$NoOp = 0;
    final /* synthetic */ ViewGroup $container;
    final /* synthetic */ SpecialEffectsController.Operation $operation;
    final /* synthetic */ View $viewToAnimate;
    final /* synthetic */ DefaultSpecialEffectsController$AnimationEffect this$0;

    public DefaultSpecialEffectsController$AnimationEffect$onCommit$1(SpecialEffectsController.Operation operation, ViewGroup viewGroup, View view, DefaultSpecialEffectsController$AnimationEffect defaultSpecialEffectsController$AnimationEffect) {
        this.$operation = operation;
        this.$container = viewGroup;
        this.$viewToAnimate = view;
        this.this$0 = defaultSpecialEffectsController$AnimationEffect;
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationEnd(Animation animation) {
        animation.getClass();
        ViewGroup viewGroup = this.$container;
        viewGroup.post(new DefaultSpecialEffectsController$TransitionEffect$$ExternalSyntheticLambda0((Object) viewGroup, (Object) this.$viewToAnimate, (Object) this.this$0, 1, (byte[]) null));
        if (FragmentManager.isLoggingEnabled(2)) {
            Objects.toString(this.$operation);
        }
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationRepeat(Animation animation) {
        animation.getClass();
    }

    @Override // android.view.animation.Animation.AnimationListener
    public final void onAnimationStart(Animation animation) {
        animation.getClass();
        if (FragmentManager.isLoggingEnabled(2)) {
            Objects.toString(this.$operation);
        }
    }
}
